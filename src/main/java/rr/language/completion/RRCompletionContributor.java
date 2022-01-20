package rr.language.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionInitializationContext;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.lang.ASTNode;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PatternCondition;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import rr.language.Util;
import rr.language.completion.providers.*;
import rr.language.psi.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class RRCompletionContributor extends CompletionContributor {
    @Override
    public void beforeCompletion(@NotNull CompletionInitializationContext context) {
        // the reason I do this is that file autocompletion will not work, unless a dummy id that matches the *.txt
        // lexing rule is inserted, so that the parser will wrap it in a txt_fie_ element, on which getVariants can be called.
        // Default dummyIdentifier has a whitespace at the end ("IntellijIdeaRulezzz ") and that makes it not parsed as a txt file
        // "foo" on the other hand, nicely merges with the existing text, for example resulting in "foosomefile.txt"
        // This does not work in places that dont already have a file though, as "foo" by itself is not a valid TXT_FILE.
        // Solution: Add ID as a valid child of txt_file_
        context.setDummyIdentifier("foo");
        super.beforeCompletion(context);
    }

    private class Node {
        PsiElementPattern.Capture<PsiElement> pattern;
        RRCompletionProvider provider;
        Node[] children;
        Node parent;

        public Node(RRCompletionProvider provider) {
            this(null, provider);
        }

        public Node(PsiElementPattern.Capture<PsiElement> pattern, RRCompletionProvider provider) {
            this(pattern, provider, new Node[]{});
        }

        public Node(PsiElementPattern.Capture<PsiElement> pattern, RRCompletionProvider provider, Node... children) {
            this.pattern = pattern;
            this.provider = provider;
            this.children = children;

            if (children != null) {
                for (Node c : children) {
                    c.parent = this;
                }
            }
        }

        public boolean isRoot() {
            return this.parent == null;
        }

        public boolean isRepeatingBranchRoot() {
            return this instanceof RepeatingBranchRootNode;
        }

        public boolean isInRepeatingBranch() {
            return this.repeatingBranchRoot() != null && !this.isRepeatingBranchRoot();
        }

        public RepeatingBranchRootNode repeatingBranchRoot() {
            if (this.isRepeatingBranchRoot()) return (RepeatingBranchRootNode) this;

            if (this.isRoot()) return null;

            return this.parent.repeatingBranchRoot();
        }

        public RootNode root() {
            if (this.isRoot()) return (RootNode) this;

            return this.parent.root();
        }

        public Collection<Node> leaves() {
            return leaves(new ArrayList<>());
        }

        public Collection<Node> leaves(Collection<Node> acc) {
            for (Node child : children) {
                child.leaves(acc);
            }

            if (children.length == 0) {
                acc.add(this);
            }

            return acc;
        }

        public PsiElementPattern[] patternsTill(Node node) {
            return this.patternsTill(node, new ArrayList<>());
        }

        public PsiElementPattern[] patternsTill(Node node, Collection<ElementPattern> acc) {
            acc.add(this.pattern);

            if (this.equals(node)) {
                Collections.reverse((List<?>) acc);
                return acc.toArray(new PsiElementPattern[acc.size()]);
            }

            return this.parent.patternsTill(node, acc);
        }
    }

    private class RootNode extends Node {
        Class statement; // TODO: Maybe not needed

        public RootNode(Class statement, PsiElementPattern.Capture<PsiElement> pattern, Node... children) {
            super(pattern, null, children);
            this.statement = statement;
        }

        public RootNode(PsiElementPattern.Capture<PsiElement> pattern, Node... children) {
            super(pattern, null, children);
        }
    }

    private class ConditionalNode extends Node {
        PsiElementPattern.Capture<PsiElement> condition;

        public ConditionalNode(
            PsiElementPattern.Capture<PsiElement> pattern,
            RRCompletionProvider provider,
            PsiElementPattern.Capture<PsiElement> condition,
            Node... children
        ) {
            super(pattern, provider, children);
            this.condition = condition;
        }
    }

    private class RepeatingNode extends Node {
        PsiElementPattern.Capture<PsiElement> condition;

        public RepeatingNode(
            PsiElementPattern.Capture<PsiElement> pattern,
            PsiElementPattern.Capture<PsiElement> condition,
            RRCompletionProvider provider
        ) {
            this(pattern, condition, provider, new Node[]{});
        }

        public RepeatingNode(
            PsiElementPattern.Capture<PsiElement> pattern,
            PsiElementPattern.Capture<PsiElement> condition,
            RRCompletionProvider provider,
            Node... children
        ) {
            super(pattern, provider, children);
            this.condition = condition;
        }
    }

    private class RepeatingBranchRootNode extends Node {
        public RepeatingBranchRootNode(
            PsiElementPattern.Capture<PsiElement> pattern,
            RRCompletionProvider provider,
            Node... children
        ) {
            super(pattern, provider, children);
        }
    }

    protected void extendFor(Node node) {
        for (Node child : node.children) {
            extendFor(child);
        }

        if (node instanceof RootNode) return;

        if (node instanceof RepeatingNode) {
            PsiElementPattern repeatAfterPattern;
            RepeatingNode repeatNode = (RepeatingNode) node;
            if (repeatNode.condition != null) {
                repeatAfterPattern = repeatNode.condition;
            } else {
                PsiElementPattern[] patterns = node.patternsTill(node.root());
                repeatAfterPattern = isAfterPatterns(patterns);
            }

            extend(CompletionType.BASIC, repeatAfterPattern, node.provider);
        }

        if (node instanceof RepeatingBranchRootNode) {
            for (Node leaf : node.leaves()) {
                PsiElementPattern[] patterns = leaf.patternsTill(node);
                extend(CompletionType.BASIC, isAfterPatterns(patterns), node.provider);
            }
        }

        if (node.isInRepeatingBranch()) {
            PsiElementPattern[] patterns = node.parent.patternsTill(node.repeatingBranchRoot());
            patterns[0] = (PsiElementPattern) patterns[0].inside(node.root().statement);
            extend(CompletionType.BASIC, isAfterPatterns(patterns), node.provider);

            return;
        }

        if (node instanceof ConditionalNode) {
            node.parent.pattern = node.parent.pattern.and(((ConditionalNode) node).condition);
        }

        PsiElementPattern[] patterns = node.parent.patternsTill(node.root());
        extend(CompletionType.BASIC, isAfterPatterns(patterns), node.provider);
    }

    private PsiElementPattern<PsiElement, PsiElementPattern.Capture<PsiElement>> isAfterPatterns(PsiElementPattern... patterns) {
        return psiElement().with(new AfterPatterns(patterns));
    }

    private String[] settlementLevels() {
        return new String[]{
            "village",
            "town",
            "large_town",
            "city",
            "large_city",
            "huge_city"
        };
    }

    private String[] buildingCategory() {
        return new String[]{
            "core",
            "defence",
            "military",
            "trade",
            "water",
            "culture",
            "religious"
        };
    }

    private String[] agentType() {
        return new String[]{
            "diplomat",
            "spy",
            "assassin",
            "merchant"
        };
    }

    private String[] characterType() {
        return Stream.concat(
                Arrays.stream(new String[]{
                    "all",
                    "family",
                    "named character",
                    "general",
                    "admiral",
                }),
                Arrays.stream(agentType()))
            .toArray(String[]::new);
    }

    private String[] formations() {
        return Stream.concat(
                Arrays.stream(new String[]{
                    "square", "phalanx", "horde", "testudo", "wedge", "schiltrom", "shield_wall"
                }),
                Arrays.stream(agentType()))
            .toArray(String[]::new);
    }

    private String[] events() {
        return new String[]{
            "PreBattle",
            "PreBattleWithdrawal",
            "BattleAiCommenced",
            "BattleDelayPhaseCommenced",
            "BattleDeploymentPhaseCommenced",
            "BattleConflictPhaseCommenced",
            "BattlePlayerUnitAttacksEnemyUnit",
            "BattleEnemyUnitAttacksPlayerUnit",
            "BattlePlayerAttacksSettlementBuilding",
            "BattleEnemyAttacksSettlementBuilding",
            "BattleUnitGoesBerserk",
            "BattlePlayerUnitGoesBerserk",
            "BattleEnemyUnitGoesBerserk",
            "BattleUnitRouts",
            "BattlePlayerUnitRouts",
            "BattleEnemyUnitRouts",
            "BattlePlayerSiegeEngineDestroyed",
            "BattleEnemySiegeEngineDestroyed",
            "PostBattle",
            "BattleArmyRouted",
            "BattleGeneralKilled",
            "BattleGeneralRouted",
            "BattleReinforcementsArrive",
            "BattleSiegeEngineDestroyed",
            "BattleSiegeEngineDocksWall",
            "BattleGatesAttackedByEngine",
            "BattleGatesAttackedByPlayerEngine",
            "BattleGatesAttackedByEnemyEngine",
            "BattleBattleGatesDestroyedByEngine",
            "BattleWallsBreachedByEngine",
            "BattleWallsCaptured",
            "BattleFinished",
            "EnteredCityView",
            "BattleMinimapAction",
            "BattlePlayerUnitSelected",
            "EnterTacticalMode",
            "BattleReinforcementsHack",
            "FactionTurnStart",
            "FactionWarDeclared",
            "HordeFormed",
            "FactionTurnEnd",
            "HireMercenaries",
            "GeneralCaptureResidence",
            "GeneralCaptureWonder",
            "GeneralCaptureSettlement",
            "LeaderDestroyedFaction",
            "Disaster",
            "CharacterDamagedByDisaster",
            "GeneralAssaultsResidence",
            "OfferedForAdoption",
            "LesserGeneralOfferedForAdoption",
            "OfferedForMarriage",
            "BrotherAdopted",
            "BecomesFactionLeader",
            "BecomesFactionHeir",
            "TakeOffice",
            "CeasedFactionLeader",
            "CeasedFactionHeir",
            "LeaveOffice",
            "UngarrisonedFort",
            "LostLegionaryEagle",
            "CapturedLegionaryEagle",
            "RecapturedLegionaryEagle",
            "SenateExposure",
            "QuaestorInvestigationMinor",
            "QuaestorInvestigation",
            "QuaestorInvestigationMajor",
            "PopularSupportForOverthrow",
            "SenateReadyToOutlawFaction",
            "SenateOutlawsFaction",
            "NewTurnStart",
            "ScriptPromptCallback",
            "FactionDestroyed",
            "Birth",
            "CharacterComesOfAge",
            "CharacterMarries",
            "CharacterBecomesAFather",
            "CharacterTurnStart",
            "CharacterTurnEnd",
            "CharacterTurnEndInSettlement",
            "GeneralDevastatesTile",
            "SpyMission",
            "ExecutesASpyOnAMission",
            "LeaderOrderedSpyingMission",
            "AssassinationMission",
            "ExecutesAnAssassinOnAMission",
            "LeaderOrderedAssassination",
            "SufferAssassinationAttempt",
            "SabotageMission",
            "LeaderOrderedSabotage",
            "BriberyMission",
            "LeaderOrderedBribery",
            "AcceptBribe",
            "RefuseBribe",
            "Insurrection",
            "DiplomacyMission",
            "LeaderOrderedDiplomacy",
            "LeaderSenateMissionSuccess",
            "LeaderSenateMissionFailed",
            "ConstructionItemClicked",
            "RecruitmentItemClicked",
            "RecruitmentPopulated",
            "ConstructionPopulated",
            "AgentListPopulated",
            "MoveRetinuePopulated",
            "MoveRetinuePressed",
            "MoveRetinueAncillarySelected",
            "MoveRetinueAncillaryDeselected",
            "MissionSelected",
            "AgentSelected",
            "ScrollDidOpen",
            "UnitHasRouted",
            "BattleUnitActionStatus",
            "DiplomacyScrollPopulated",
            "ItemDeselected",
            "UnitInfoOpened",
            "AdvisorAudioStopped",
            "SettlementTurnStart",
            "SettlementTurnEnd",
            "NewAdmiralCreated",
            "UnitTrained",
            "GovernorUnitTrained",
            "BuildingCompleted",
            "GovernorBuildingCompleted",
            "PlugInCompleted",
            "GovernorPlugInCompleted",
            "AgentCreated",
            "GovernorAgentCreated",
            "BuildingDestroyed",
            "GovernorBuildingDestroyed",
            "CityRiots",
            "GovernorCityRiots",
            "CityRebels",
            "GovernorCityRebels",
            "GovernorThrowGames",
            "GovernorThrowRaces",
            "UngarrisonedSettlement",
            "EnslavePopulation",
            "ExterminatePopulation",
            "CitySacked",
            "CharacterSelected",
            "SettlementSelected",
            "EnemySettlementSelected",
            "MultiTurnMove",
            "CharacterPanelOpen",
            "SettlementPanelOpen",
            "FinancesPanelOpen",
            "FactionSummaryPanelOpen",
            "FamilyTreePanelOpen",
            "DiplomaticStandingPanelOpen",
            "SenateMissionsPanelOpen",
            "SenateOfficesPanelOpen",
            "DiplomacyPanelOpen",
            "PreBattlePanelOpen",
            "RecruitmentPanelOpen",
            "ConstructionPanelOpen",
            "TradePanelOpen",
            "HireMercenariesPanelOpen",
            "NavalAutoResolvePanelOpen",
            "IncomingMessage",
            "MessageOpen",
            "RequestBuildingAdvice",
            "RequestTrainingAdvice",
            "RequestMercenariesAdvice",
            "ButtonPressed",
            "ShortcutTriggered",
            "ScrollOpened",
            "ScrollClosed",
            "AdviceSupressed",
            "ScrollAdviceRequested",
            "PreBattleScrollAdviceRequested",
            "NavalPreBattleScrollAdviceRequested",
            "SettlementScrollAdviceRequested",
            "Idle",
            "AbandonShowMe",
            "ScriptedAdvice",
            "DeclineAutomatedSettlementManagement",
            "EscPressed",
            "GameReloaded",
            "FirstStratUpdates",
            "MovieStopped",
            "SelectionAssistPossible",
            "SettlementButtonPressed",
            "WorldScriptTerminate",
            "CampaignHudShown",
            "ContextPopupInteraction",
            "DiplomacyConstructingOffer",
            "DiplomacyConstructingCounterOffer",
            "DiplomacyOpponentPresentsOffer",
            "DiplomacyOpponentPresentsCounterOffer",
            "FactionSummary",
            "FactionSenate",
            "FactionSenatePolicy",
            "FactionSenateMissions",
            "FactionSenateOfficials",
            "FactionSenateFloor",
            "FactionFactions",
            "FactionDetails",
            "FactionFamilyTree",
            "FactionRankings",
            "FactionLists",
            "SettlementCharacter",
            "SettlementTrade",
            "SettlementOverview",
            "SpySelected",
            "DiplomatSelected",
            "AssassinSelected",
            "FleetSelected",
            "CampaignMapGesture",
            "CampaignDoingBadly",
            "BattleMapGesture",
            "HideBattleUI",
            "FeralNewsVisible",
            "AgentHubOpened",
            "MoveRetinueOpened",
            "OwnFactionDetailsOpened",
            "DiplomaticStandingShown",
            "FactionFinancesShown",
            "FamilyTreeShown",
            "SendAgentPanel",
            "SettlementDetailsShown",
            "CharacterInfoScreen",
            "FriendlyCharacterSelected",
            "EnemyCharacterSelected",
            "FriendlySettlementSelected",
            "MapOverlayOpened",
            "SiegeDetailsShown",
            "PreBattleScreen",
            "TacticalMapShown",
            "PostBattleScreen",
            "UnitsGrouped",
            "EnteredBattle",
            "AdvisorOpened",
            "FormationTypesShown",
            "MerchantSelected",
            "NavalCombatStarted",
            "MergeArmiesOpened",
            "RoutesBlockaded",
            "ElectionResults",
            "BattleToggleMenu",
            "AmbushMode",
            "NewsTabClosed",
            "NewsTabOpened",
            "BattleNewsTabOpened",
            "QuickListsOpened",
            "EmbargoIsAvailable",
            "RebelCharacterSelected",
            "HighTaxesCauseDisorder",
            "FailedToEndTurn",
            "AcquisitionMission",
            "SufferAcquisitionAttempt",
        };
    }

    private String[] conditions() {
        return new String[]{
            "I_InBattle",
            "WonBattle",
            "I_WonBattle",
            "Routs",
            "Ally_Routs",
            "GeneralHPLostRatioinBattle",
            "GeneralNumKillsInBattle",
            "GeneralFoughtInCombat",
            "PercentageOfArmyKilled",
            "I_PercentageOfArmyKilled",
            "PercentageEnemyKilled",
            "PercentageBodyguardKilled",
            "PercentageRoutedOffField",
            "NumKilledGenerals",
            "PercentageUnitCategory",
            "NumFriendsInBattle",
            "NumEnemiesInBattle",
            "GeneralFoughtFaction",
            "GeneralFoughtCulture",
            "I_ConflictType",
            "IsNightBattle",
            "BattleSuccess",
            "BattleOdds",
            "WasAttacker",
            "I_BattleAiAttacking",
            "I_BattleAiAttackingSettlement",
            "I_BattleAiDefendingSettlement",
            "I_BattleAiDefendingHill",
            "I_BattleAiDefendingCrossing",
            "I_BattleAiScouting",
            "I_BattleIsRiverBattle",
            "I_BattleIsSiegeBattle",
            "I_BattleIsSallyOutBattle",
            "I_BattleIsFortBattle",
            "I_BattleAttackerNumSiegeEngines",
            "I_BattleAttackerNumArtilleryCanPenetrateWalls",
            "I_BattleDefenderNumNonMissileUnitsOnWalls",
            "I_BattleDefenderNumMissileUnitsOnWalls",
            "I_BattleSettlementWallsBreached",
            "I_BattleSettlementGateDestroyed",
            "I_BattleSettlementTowerDefence",
            "I_BattleSettlementGateDefence",
            "I_BattleSettlementFortificationLevel",
            "BattleBuildingType",
            "I_BattleSettlementGateStrength",
            "I_BattleNumberOfRiverCrossings",
            "BattlePlayerUnitClass",
            "BattleEnemyUnitClass",
            "BattlePlayerUnitCategory",
            "BattleEnemyUnitCategory",
            "BattlePlayerUnitSiegeEngineClass",
            "BattleEnemyUnitSiegeEngineClass",
            "BattlePlayerUnitOnWalls",
            "BattleEnemyUnitOnWalls",
            "BattlePlayerCurrentFormation",
            "BattleEnemyCurrentFormation",
            "BattlePlayerUnitCloseFormation",
            "BattleEnemyUnitCloseFormation",
            "BattlePlayerUnitSpecialAbilitySupported",
            "BattleSelectedPlayerUnitSpecialAbilitySupported",
            "BattleEnemyUnitSpecialAbilitySupported",
            "BattlePlayerUnitSpecialAbilityActive",
            "BattleEnemyUnitSpecialAbilityActive",
            "BattlePlayerMountClass",
            "BattleEnemyMountClass",
            "BattlePlayerUnitMeleeStrength",
            "BattleEnemyUnitMeleeStrength",
            "BattlePlayerUnitMissileStrength",
            "BattleEnemyUnitMissileStrength",
            "BattlePlayerUnitSpecialFormation",
            "BattleEnemyUnitSpecialFormation",
            "BattlePlayerUnitEngaged",
            "BattleEnemyUnitEngaged",
            "BattlePlayerActionStatus",
            "BattleEnemyActionStatus",
            "BattlePlayerUnitMovingFast",
            "BattleEnemyUnitMovingFast",
            "BattleRangeOfAttack",
            "BattleDirectionOfAttack",
            "BattleIsMeleeAttack",
            "I_BattlePlayerArmyPercentageOfUnitClass",
            "I_BattleEnemyArmyPercentageOfUnitClass",
            "I_BattlePlayerArmyPercentageOfUnitCategory",
            "I_BattleEnemyArmyPercentageOfUnitCategory",
            "I_BattlePlayerArmyPercentageOfMountClass",
            "I_BattleEnemyArmyPercentageOfMountClass",
            "I_BattlePlayerArmyPercentageOfClassAndCategory",
            "I_BattleEnemyArmyPercentageOfClassAndCategory",
            "I_BattlePlayerArmyPercentageOfSpecialAbility",
            "I_BattleEnemyArmyPercentageOfSpecialAbility",
            "I_BattlePlayerArmyPercentageCanHide",
            "I_BattleEnemyArmyPercentageCanHide",
            "I_BattlePlayerArmyPercentageCanSwim",
            "I_BattleEnemyArmyPercentageCanSwim",
            "I_BattlePlayerArmyIsAttacker",
            "I_BattlePlayerAllianceOddsInFavour",
            "I_BattlePlayerAllianceOddsAgainst",
            "TotalSiegeWeapons",
            "I_BattleStarted",
            "I_BattleFinished",
            "I_BattleEnd",
            "I_BattleEndPending",
            "I_IsUnitMoveFastSet",
            "I_IsUnitMoving",
            "I_IsUnitIdle",
            "I_IsUnitRouting",
            "I_IsUnitUnderFire",
            "I_IsUnitEngaged",
            "I_IsUnitEngagedWithUnit",
            "I_UnitFormation",
            "I_PercentageUnitKilled",
            "I_UnitPercentageAmmoLeft",
            "I_UnitDistanceFromPosition",
            "I_UnitDistanceFromLine",
            "I_UnitDistanceFromUnit",
            "I_UnitInRangeOfUnit",
            "I_UnitDestroyed",
            "I_UnitEnemyUnitInRadius",
            "I_IsUnitGroupMoving",
            "I_IsUnitGroupEngaged",
            "I_IsUnitGroupIdle",
            "I_IsUnitGroupDestroyed",
            "I_PercentageUnitGroupKilled",
            "I_UnitGroupFormation",
            "I_UnitGroupDistanceFromPosition",
            "I_UnitGroupDistanceFromGroup",
            "I_UnitGroupInRangeOfUnit",
            "I_UnitInRangeOfUnitGroup",
            "I_UnitGroupInRangeOfUnitGroup",
            "I_PlayerInRangeOfUnitGroup",
            "I_PlayerInRangeOfUnit",
            "I_UnitTypeSelected",
            "UnitType",
            "I_UnitSelected",
            "I_MultipleUnitsSelected",
            "I_SpecificUnitsSelected",
            "I_IsCameraZoomingToUnit",
            "I_BattleEnemyArmyPercentageOfMatchingUnits",
            "I_BattleEnemyArmyNumberOfMatchingUnits",
            "I_BattlePlayerArmyPercentageOfMatchingUnits",
            "I_BattlePlayerArmyNumberOfMatchingUnits",
            "LocalPlayerHasManualReinforcements",
            "LocalPlayerHasAIReinforcements",
            "Trait",
            "HasAncillary",
            "FatherTrait",
            "FactionLeaderTrait",
            "Attribute",
            "RemainingMPPercentage",
            "I_RemainingMPPercentage",
            "I_CharacterCanMove",
            "NoActionThisTurn",
            "AgentType",
            "TrainedAgentType",
            "DisasterType",
            "CultureType",
            "OriginalFactionType",
            "OriginalCultureType",
            "IsGeneral",
            "IsAdmiral",
            "RemasteredEducation",
            "EndedInSettlement",
            "IsFactionLeader",
            "IsFactionHeir",
            "IsMarried",
            "AtSea",
            "InEnemyLands",
            "InBarbarianLands",
            "InUncivilisedLands",
            "IsBesieging",
            "IsUnderSiege",
            "I_WithdrawsBeforeBattle",
            "EndedInEnemyZOC",
            "AdviseAction",
            "I_CharacterTypeNearCharacterType",
            "I_CharacterTypeNearTile",
            "I_CharacterNameNearTile",
            "TradingResource",
            "RegionTradingResource",
            "TradingExotic",
            "DistanceCapital",
            "DistanceHome",
            "TradingGroup",
            "RegionMerchantCount",
            "PreciousMineCount",
            "IsFromFaction",
            "NightBattlesEnabled",
            "HasOffice",
            "FactionType",
            "TargetFactionType",
            "FactionCultureType",
            "TargetFactionCultureType",
            "TrainedUnitCategory",
            "UnitCategory",
            "SenateMissionTimeRemaining",
            "MedianTaxLevel",
            "ModeTaxLevel",
            "I_ModeTaxLevel",
            "MissionSuccessLevel",
            "MissionSucceeded",
            "MissionFactionTargetType",
            "MissionCultureTargetType",
            "DiplomaticStanceFromCharacter",
            "DiplomaticStanceFromFaction",
            "FactionHasAllies",
            "LosingMoney",
            "I_LosingMoney",
            "FactionIsAlive",
            "SupportCostsPercentage",
            "Treasury",
            "OnAWarFooting",
            "I_FactionBesieging",
            "I_FactionBesieged",
            "I_NumberOfSettlements",
            "I_NumberOfHeirs",
            "I_FactionNearTile",
            "SettlementsTaken",
            "BattlesFought",
            "BattlesWon",
            "BattlesLost",
            "DefensiveSiegesFought",
            "DefensiveSiegesWon",
            "OffensiveSiegesFought",
            "OffensiveSiegesWon",
            "FactionwideAncillaryExists",
            "IsAlly",
            "IsProtectorate",
            "IsProtector",
            "IsSameSuperfaction",
            "LocalPlayerBattlesFought",
            "Toggled",
            "MajorEventActive",
            "RandomPercent",
            "TrueCondition",
            "WorldwideAncillaryExists",
            "I_IsTutorialEnabled",
            "I_IsPlayerTurn",
            "ConstructionItemClicked",
            "RecruitmentItemClicked",
            "CharacterName",
            "ScrollDidOpen",
            "UnitHasRouted",
            "BattleUnitActionStatus",
            "I_AmountOfUnitInSettlement",
            "I_UnitCardSelected",
            "I_CompareCounter",
            "TestFaction",
            "LangIs",
            "I_TimerElapsed",
            "I_SoundPlaying",
            "HasResource",
            "SettlementRevoltingFrom",
            "IsCapital",
            "SettlementName",
            "GovernorBuildingExists",
            "SettlementBuildingExists",
            "HomeSettlementBuildingExists",
            "SettlementOrderLevel",
            "SettlementCapabilityLevel",
            "BuildingFinishedByGovernor",
            "SettlementBuildingFinished",
            "GovernorPlugInExists",
            "GovernorPlugInFinished",
            "GovernorTaxLevel",
            "SettlementTaxLevel",
            "GovernorInResidence",
            "GovernorLoyaltyLevel",
            "SettlementLoyaltyLevel",
            "RiotRisk",
            "BuildingQueueIdleDespiteCash",
            "TrainingQueueIdleDespiteCash",
            "I_SettlementExists",
            "I_SettlementOwner",
            "I_SettlementOwnerCulture",
            "I_SettlementLevel",
            "AdviseFinancialBuild",
            "AdviseBuild",
            "AdviseRecruit",
            "SettlementPopulationMaxedOut",
            "SettlementPopulationTooLow",
            "SettlementAutoManaged",
            "FeralSettlementAutoManaged",
            "PercentageOfPopulationInGarrison",
            "GarrisonToPopulationRatio",
            "HealthPercentage",
            "SettlementHasPlague",
            "IsFortGarrisoned",
            "IsSettlementGarrisoned",
            "IsSettlementRioting",
            "I_NumberUnitsInSettlement",
            "I_AdvisorSpeechPlaying",
            "CharacterIsLocal",
            "TargetCharacterIsLocal",
            "SettlementIsLocal",
            "TargetSettlementIsLocal",
            "RegionIsLocal",
            "TargetRegionIsLocal",
            "ArmyIsLocal",
            "ArmyIsGarrison",
            "TargetArmyIsLocal",
            "FactionIsLocal",
            "I_LocalFaction",
            "TargetFactionIsLocal",
            "I_TurnNumber",
            "I_MapName",
            "I_ThreadCount",
            "I_IsTriggerTrue",
            "IncomingMessageType",
            "I_AdvisorVerbosity",
            "I_AdvisorVisible",
            "I_CharacterSelected",
            "I_AgentSelected",
            "I_SettlementSelected",
            "ShortcutTriggered",
            "I_AdvancedStatsScrollIsOpen",
            "ButtonPressed",
            "ScrollOpened",
            "ScrollClosed",
            "ScrollAdviceRequested",
            "I_AnnotationDisplayed",
            "FeralUIType",
            "MerchantIsAvailableToBuild",
            "SettlementHasDamagedBuilding",
            "LocalPlayerHasReinforcements",
            "SettlementMerchantTradingWith",
            "SettlementOwnedBy",
            "FactionBuildingExists",
        };
    }

    private String[] commands() {
        return new String[]{
            "senate_mission_help_player",
            "senate_mission_assassination",
            "senate_mission_cease_hostilities",
            "senate_mission_declare_war",
            "senate_mission_broker_peace",
            "senate_mission_take_city",
            "move_to_settlement",
            "snap_to_settlement",
            "clear_restrict_strat_movement",
            "restrict_strat_movement",
            "disable_diplomacy_ui",
            "enable_diplomacy_voices",
            "enable_unit_voices",
            "hide_ui_element",
            "show_ui_element",
            "disable_ui_card",
            "disable_all_ui_cards",
            "enable_all_ui_cards",
            "disable_agent_hub_all",
            "enable_agent_hub_all",
            "disable_agent_hub",
            "enable_agent_hub",
            "point_at_agent_hub",
            "set_marriage_allowed",
            "trigger_marriage_proposal",
            "deselect_current_selection",
            "force_autoresolve_outcome",
            "force_diplomacy",
            "create_mercenary_pool",
            "force_agent_succeed",
            "allow_campaign_battles",
            "spawn_character_child",
            "stop_point_at_indicator",
            "stop_all_point_at_indicators",
            "set_battle",
            "restrict_battle_movement",
            "clear_restrict_battle_movement",
            "point_at_diplomacy_offer",
            "point_at_move_retinue",
            "disable_move_retinue_all",
            "enable_move_retinue_all",
            "disable_move_retinue",
            "enable_move_retinue",
            "block_unit_selection",
            "forced_gate_success",
            "strat_selection_unblocker",
            "clear_strat_selection_unblocker",
            "open_stop_tutorial_confirmation_dialog",
            "set_label",
            "goto",
            "force_deselect_trigger",
            "box_drag_selection",
            "force_settlement_tab",
            "click_drag_move",
            "toggle_minimap",
            "close_news_panel",
            "ui_card_selection_lock",
            "disable_specific_shortcut",
            "set_advice_page",
            "advance_completed_tasks",
            "set_min_formation_width",
            "script_log",
            "ai_active_set",
            "release_unit",
            "hiding_enabled_set",
            "swimming_enabled_set",
            "pause_battle",
            "unit_immediate_place",
            "unit_order_halt",
            "unit_order_move",
            "unit_order_move_to_orientation",
            "unit_order_move_relative",
            "unit_order_attack_unit",
            "unit_order_attack_closest_unit",
            "unit_order_change_formation",
            "unit_order_move_to_missile_range",
            "unit_order_turn",
            "unit_set_morale",
            "unit_unset_morale",
            "unit_set_weapon_upgrade",
            "unit_set_armour_upgrade",
            "unit_set_experience",
            "kill_unit",
            "reduce_unit_strength",
            "unit_set_guard_mode",
            "unit_set_skirmish_mode",
            "unit_set_fire_at_will_mode",
            "unit_set_formation_spacing",
            "unit_taunt",
            "unit_use_special_ability",
            "unit_group_enable_automation",
            "unit_group_automate_defend_position",
            "unit_group_automate_attack",
            "unit_group_immediate_place",
            "unit_group_order_halt",
            "unit_group_order_move_formed",
            "unit_group_order_move_unformed",
            "unit_group_order_relative_move_formed",
            "unit_group_order_relative_move_unformed",
            "unit_group_move_to_missile_range_of_unit",
            "unit_group_move_to_missile_range_of_group",
            "unit_group_order_attack_unit",
            "unit_group_order_attack_group",
            "unit_group_order_change_group_formation",
            "unit_group_order_turn",
            "unit_group_set_morale",
            "unit_group_unset_morale",
            "unit_group_change_unit_formation",
            "unit_group_set_guard_mode",
            "unit_group_set_skirmish_mode",
            "unit_group_set_fire_at_will_mode",
            "unit_group_set_formation_spacing",
            "force_ai_control",
            "finish_battle",
            "move_strat_camera",
            "set_strat_camera_speed",
            "snap_strat_camera",
            "zoom_strat_camera",
            "camera_restrictions_set",
            "camera_event_cuts_active_set",
            "camera_default_mode_set",
            "battle_default_camera",
            "battle_general_camera",
            "set_camera_bookmark",
            "use_camera_bookmark",
            "camera_position_at_bookmark_",
            "camera_zoom_to_bookmark",
            "camera_position",
            "camera_zoom_to",
            "camera_look_at_position",
            "camera_look_at_unit",
            "camera_track_unit",
            "camera_zoom_to_unit",
            "e_camera_zoom_to_unit",
            "inhibit_camera_input",
            "declare_prologue",
            "terminate_prologue",
            "provoke_rebellion",
            "move",
            "reposition_character",
            "replenish_action_points",
            "replenish_units",
            "spawn_character",
            "spawn_army",
            "engage_armies",
            "disable_popup",
            "end_benchmar",
            "disable_pause_shortcut_in_campaign",
            "override_superfaction_popularity",
            "set_faction_senate_standing",
            "set_faction_people_standing",
            "message_prompt",
            "include_script",
            "terminate_script",
            "break",
            "set_ao_visible",
            "set_all_ao_visible",
            "terminate_monitor",
            "declare_counter",
            "declare_persistent_counter",
            "inc_counter",
            "set_counter",
            "counter_operation",
            "store_counter",
            "retrieve_counter",
            "prepare_for_battle",
            "declare_show_me",
            "label_unit",
            "label_location",
            "define_unit_group",
            "undefine_unit_group",
            "remove_unit_from_group",
            "declare_timer",
            "restart_timer",
            "heed_pause",
            "wait",
            "campaign_wait",
            "battle_wait",
            "suspend_during_battle",
            "set_music_state",
            "release_music_contro",
            "play_sound_event",
            "play_sound_flourish",
            "stop_sound_event",
            "point_at_character",
            "point_at_settlement",
            "e_point_at_settlemen",
            "point_at_strat_position",
            "point_at_strat_position_alt",
            "point_at_message",
            "ui_flash_start",
            "ui_flash_stop",
            "settlement_flash_start",
            "settlement_flash_stop",
            "character_flash_start",
            "character_flash_stop",
            "point_at_location",
            "point_at_unit_pos",
            "point_at_unit_group_pos",
            "remove_battle_map_arrow",
            "enable_ui_card",
            "point_at_card",
            "point_at_unit_card",
            "e_point_at_unit_card",
            "show_mouse_button_animation",
            "show_movie",
            "hide_ui",
            "show_ui",
            "disable_ui",
            "enable_ui",
            "disable_entire_ui",
            "enable_entire_ui",
            "set_cards_selectable",
            "disable_cursor",
            "enable_cursor",
            "rename_settlement_in_region",
            "add_religion",
            "add_hidden_resource",
            "remove_hidden_resource",
            "destroy_building",
            "reveal_tile",
            "hide_all_revealed_tile",
            "play_video",
            "advance_advice_thread",
            "dismiss_advice",
            "dismiss_advisor",
            "suspend_unscripted_advice",
            "select_character",
            "e_select_characte",
            "select_settlement",
            "e_select_settlement",
            "call_object_shortcut",
            "simulate_mouse_click",
            "select_ui_element",
            "disable_shortcuts",
            "filter_unit_commands",
            "filter_unit_group_commands",
            "filter_unit_selection_commands",
            "filter_settlement_commands",
            "filter_character_commands",
            "filter_all_ui_commands",
            "ui_indicator",
            "ui_indicator_remove",
            "steal_esc_key",
            "highlight_recruitment_item",
            "highlight_construction_item",
            "show_annotations",
            "e_select_unit",
            "open_siege_scroll",
            "control_feral_anim",
            "select_captial",
            "show_building_info",
            "show_unit_info",
            "tactical_view_locked",
            "console_command",
        };
    }

    private String[] consoleCommands() {
        return new String[]{
            "kill_character",
            "give_trait",
            "give_ancillary",
            "process_cq",
            "add_population",
            "capture_settlement",
            "add_money",
            "diplomatic_stance",
            "give_trait_",
            "date",
            "season",
            "create_building",
            "create_unit",
            "destroy_unit",
            "sudo",
            "toggle_fow",
            "set_fow",
            "sudo",
            "become_protector",
            "invulnerable_general",
            "give_everything",
            "change_character_faction",
            "add_income",
            "add_expenditure",
            "control",
            "disable_ai",
            "surrender_regions",
            "run_ai",
        };
    }

    public RRCompletionContributor() {
        // descr_strat

        // type {building type} {building name}
        Node descr_strat_building_info = new RootNode(RRBuildingItem.class, psiElement(RRTypes.TYPE).inside(RRBuildingItem.class),
            new Node(psiElement(RRTypes.ID).afterLeaf(psiElement(RRTypes.TYPE)), new BuildingTrees(),
                // TODO: Retire that constructor, always maintain order of arguments. So, just pass null.
                new Node(new BuildingLevels(true))));

        // level {settlement level}
        Node descr_strat_settlement_level = new RootNode(psiElement(RRTypes.LEVEL),
            new Node(new HardcodedValues(settlementLevels())));

        // region {id}
        Node descr_strat_region = new RootNode(psiElement(RRTypes.REGION),
            new Node(new Regions()));

        // faction_creator {id}
        Node descr_strat_faction_creator = new RootNode(psiElement(RRTypes.FACTION_CREATOR),
            new Node(new Factions()));

        // character Flavius Julius, {character type}, leader, age 47, , x 89, y 82
        Node descr_strat_character_type = new RootNode(psiElement(RRTypes.CHARACTER),
            new RepeatingNode(id(), null, new Null(), // TODO: instantiate that in Nodes' constructor when arg is null
                new Node(psiElement(RRTypes.COMMA), new Null(),
                    new Node(null, new HardcodedValues(characterType())))));

        psiElement(RRTypes.COMMA).afterLeaf(psiElement(RRTypes.INT).afterLeaf(inside(RRTypes.ID)));
        // traits {id int}, {id int}
        Node descr_strat_traits = new RootNode(
            psiElement(RRTypes.TRAITS),
            new RepeatingNode(
                psiElement().andOr(
                    psiElement(RRTypes.ID),
                    psiElement(RRTypes.INT).afterLeaf(inside(RRTypes.ID)),
                    psiElement(RRTypes.COMMA).afterLeaf(psiElement(RRTypes.INT).afterLeaf(inside(RRTypes.ID)))
                ),
                null,
                new Traits()));

        // ancillaries {id}, {id}
        Node descr_strat_ancillaries = new RootNode(
            psiElement(RRTypes.ANCILLARIES),
            new RepeatingNode(
                psiElement().andOr(
                    psiElement(RRTypes.ID),
                    psiElement(RRTypes.COMMA).afterLeaf(inside(RRTypes.ID))
                ),
                null,
                new Ancillaries()));

        // core_attitudes        {id},	410 {id} [, {id}]...
        // faction_relationships {id},	410 {id} [, {id}]...
        Node descr_strat_core_attitudes_and_faction_relationships = new RootNode(
            psiElement().andOr(
                psiElement(RRTypes.CORE_ATTITUDES),
                psiElement(RRTypes.FACTION_RELATIONSHIPS)
            ),
            new Node(id(), new Factions(),
                new Node(psiElement(RRTypes.COMMA), new Null(),
                    new Node(number(), new Null(),
                        new RepeatingNode(
                            psiElement().andOr(
                                id(),
                                psiElement(RRTypes.COMMA)
                            ),
                            null,
                            new Factions())))));

        // export_descr_unit
        // category {category}
        extend(
            CompletionType.BASIC,
            psiElement().afterLeaf(psiElement(RRTypes.CATEGORY)),
            new HardcodedValues("infantry", "cavalry", "siege", "handler", "ship", "non_combatant")
        );

        // class {class}
        extend(
            CompletionType.BASIC,
            psiElement().afterLeaf(psiElement(RRTypes.CLASS).inside(psiElement(RRTypes.UNIT_ITEM_))),
            new HardcodedValues(
                "light",
                "heavy",
                "missile",
                "spearmen"
            )
        );

        // attributes {list<attribute>}
        Node export_descr_unit_attributes = new RootNode(
            psiElement(RRTypes.ATTRIBUTES),
            new RepeatingNode(
                psiElement().andOr(
                    inside(RRTypes.UNIT_ATTRIBUTE),
                    psiElement(RRTypes.COMMA).afterLeaf(inside(RRTypes.UNIT_ATTRIBUTE))
                ),
                null,
                new HardcodedValues(
                    "hide_forest",
                    "hide_improved_forest",
                    "hide_long_grass",
                    "hide_anywhere",
                    "frighten_foot",
                    "frighten_mounted",
                    "can_run_amok",
                    "warcry",
                    "can_sap",
                    "can_swim",
                    "hardy",
                    "very_hardy",
                    "power_charge",
                    "sea_faring",
                    "cantabrian_circle",
                    "general_unit",
                    "general_unit_upgrade \"marian_reforms\"",
                    "mercenary_unit",
                    "druid",
                    "screeching_women",
                    "no_custom",
                    "can_horde",
                    "command",
                    "legionary_name"
                )));

        // formation  1.2, 1.8, 2.4, 2.4, 6, {formation} [, {formation}]
        Node export_descr_unit_formation = new RootNode(
            sequence(
                psiElement(RRTypes.FORMATION),
                number(), psiElement(RRTypes.COMMA),
                number(), psiElement(RRTypes.COMMA),
                number(), psiElement(RRTypes.COMMA),
                number(), psiElement(RRTypes.COMMA),
                number(), psiElement(RRTypes.COMMA)
            ),
            new Node(
                inside(RRTypes.UNIT_FORMATION),
                new HardcodedValues(formations()),
                new Node(
                    psiElement(RRTypes.COMMA),
                    new Null(),
                    new Node(
                        new HardcodedValues(formations())
                    )
                )
            )
        );

        // stat_pri 1, 0, no, 0, 0, melee, simple, piercing, knife, 25 ,1
        Node export_descr_unit_stat_pri = new RootNode(psiElement().andOr( // TODO: replace andOr with any()
            psiElement(RRTypes.STAT_PRI),
            psiElement(RRTypes.STAT_SEC)
        ),
            new Node(number(), new Null(),
                new Node(psiElement(RRTypes.COMMA), new Null(),
                    new Node(number(), new Null(),
                        new Node(psiElement(RRTypes.COMMA), new Null(),
                            new Node(any(RRTypes.NO, RRTypes.ID), new Null(),
                                new Node(psiElement(RRTypes.COMMA), new Null(),
                                    new Node(number(), new Null(),
                                        new Node(psiElement(RRTypes.COMMA), new Null(),
                                            new Node(number(), new Null(),
                                                new Node(psiElement(RRTypes.COMMA), new Null(),
                                                    new Node(
                                                        psiElement().andOr(
                                                            psiElement(RRTypes.NO),
                                                            inside(RRTypes.UNIT_WEAPON_TYPE)
                                                        ),
                                                        new HardcodedValues("no", "melee", "thrown", "missile", "siege_missile"),
                                                        new Node(psiElement(RRTypes.COMMA), new Null(),
                                                            new Node(
                                                                psiElement().andOr(
                                                                    psiElement(RRTypes.NO),
                                                                    inside(RRTypes.UNIT_TECH_TYPE)
                                                                ),
                                                                new HardcodedValues("no", "simple", "other", "blade", "archery", "siege"),
                                                                new Node(psiElement(RRTypes.COMMA), new Null(),
                                                                    new Node(
                                                                        psiElement().andOr(
                                                                            psiElement(RRTypes.NO),
                                                                            inside(RRTypes.UNIT_DAMAGE_TYPE)
                                                                        ),
                                                                        new HardcodedValues("no", "piercing", "blunt", "slashing", "fire"),
                                                                        new Node(psiElement(RRTypes.COMMA), new Null(),
                                                                            new Node(
                                                                                inside(RRTypes.UNIT_DAMAGE_SOUND_TYPE),
                                                                                new HardcodedValues("none", "knife", "mace", "club", "axe", "sword", "spear")))))))))))))))))));

        // stat_pri_attr  {attribute} [, {attribute}]...
        Node export_descr_unit_stat_pri_attr = new RootNode(psiElement().andOr(
            psiElement(RRTypes.STAT_PRI_ATTR),
            psiElement(RRTypes.STAT_SEC_ATTR)
        ),
            new RepeatingNode(
                psiElement().andOr(
                    inside(RRTypes.UNIT_WEAPON_ATTRS),
                    psiElement(RRTypes.COMMA).afterLeaf(inside(RRTypes.UNIT_WEAPON_ATTRS))
                ),
                null,
                new HardcodedValues("no", "ap", "bp", "spear", "light_spear", "long_pike", "short_pike", "fire",
                    "prec", "thrown", "thrown_ap", "launching", "area", "spear_bonus_x")
            ));

        // stat_pri_armour  1, 2, 3, {sound} [, {sound}]
        Node export_descr_unit_stat_pri_armour = new RootNode(
            sequence(
                psiElement(RRTypes.STAT_PRI_ARMOUR),
                number(), psiElement(RRTypes.COMMA),
                number(), psiElement(RRTypes.COMMA),
                number(), psiElement(RRTypes.COMMA)
            ),
            new Node(
                inside(RRTypes.UNIT_SOUND_WHEN_HIT),
                new HardcodedValues("flesh", "leather", "wood", "metal"),
                new Node(
                    psiElement(RRTypes.COMMA),
                    new Null(),
                    new Node(
                        new HardcodedValues("flesh", "leather", "wood", "metal")
                    ))));

        // stat_sec_armour  1, 2, 3, {sound}
        Node export_descr_unit_stat_sec_armour = new RootNode(
            sequence(
                psiElement(RRTypes.STAT_SEC_ARMOUR),
                number(), psiElement(RRTypes.COMMA),
                number(), psiElement(RRTypes.COMMA)
            ),
            new Node(
                inside(RRTypes.UNIT_SOUND_WHEN_HIT),
                new HardcodedValues("flesh", "leather", "wood", "metal")
            ));

        // stat_mental 0, low, untrained
        Node export_descr_unit_stat_mental = new RootNode(
            sequence(psiElement(RRTypes.STAT_MENTAL), number(), psiElement(RRTypes.COMMA)),
            new Node(
                inside(RRTypes.UNIT_DISCIPLINE),
                new HardcodedValues("berserker", "impetuous", "low", "normal", "disciplined"),
                new Node(
                    psiElement(RRTypes.COMMA),
                    new Null(),
                    new Node(
                        new HardcodedValues("untrained", "trained", "highly_trained")
                    ))));

        // ownership {culture|faction}
        Node export_descr_unit_ownership = new RootNode(psiElement(RRTypes.OWNERSHIP),
            new Node(
                new FactionsAndCultures()));

        // ethnicity {faction} {region}
        Node export_descr_unit_ethnicity = new RootNode(psiElement(RRTypes.ETHNICITY),
            new Node(id(), new Factions(),
                new Node(psiElement(RRTypes.COMMA), null,
                    new Node(new EthnicityMakeups()))));

        // export_descr_buildings
        // settlement_min {settlement level}
        Node export_descr_buildings_settlement_level = new RootNode(psiElement(RRTypes.SETTLEMENT_MIN),
            new Node(
                new HardcodedValues(settlementLevels())));

        // tag {tag}
        Node export_descr_buildings_tag = new RootNode(psiElement(RRTypes.TAG),
            new Node(
                new BuildingTags()));

        // classification {type}
        Node export_descr_buildings_classification = new RootNode(psiElement(RRTypes.CLASSIFICATION),
            new Node(
                new HardcodedValues(buildingCategory())));

        // requires {requirement}
        Node export_descr_buildings_requires = new RootNode(
            psiElement().andOr(
                psiElement(RRTypes.REQUIRES),
                any(RRTypes.AND, RRTypes.OR, RRTypes.NOT)
            ),
            new Node(new HardcodedValues("factions")),                   // TODO: autocomplete
            new Node(new HardcodedValues("major_event")),                // TODO: autocomplete
            new Node(psiElement(RRTypes.RESOURCE), new HardcodedValues("resource"),
                new Node(new Resources())),
            new Node(new HardcodedValues("hidden_resource")),            // TODO: autocomplete
            new Node(psiElement(RRTypes.NO_BUILDING_TAGGED), new HardcodedValues("no_building_tagged"),
                new Node(new BuildingTags())),
            new Node(psiElement(RRTypes.BUILDING_PRESENT), new HardcodedValues("building_present"),
                new Node(new BuildingTrees())),
            new Node(psiElement(RRTypes.BUILDING_PRESENT_MIN_LEVEL), new HardcodedValues("building_present_min_level"),
                new Node(id(), new BuildingTrees(),
                    new Node(new BuildingLevels(true)))),
            new Node(new HardcodedValues("is_toggled")),                 // TODO: autocomplete
            new Node(new HardcodedValues("is_player")),
            new Node(new ConditionAliases())
        );

        // recruit {category}
        extend(
            CompletionType.BASIC,
            psiElement().afterLeaf(psiElement(RRTypes.RECRUIT)),
            new Units((it) -> Util.quote(it))
        );

        Node create_unit = new RootNode(psiElement(RRTypes.CREATE_UNIT),
            new Node(id(), null,
                new Node(new Units((it) -> Util.quote(it)))));


        // TODO: finish that
        // capability {
        //   {capability}
        //   {capability}
        //   ...
        // }
        Node export_descr_buildings_capability = new RootNode(
            any(
                sequence(RRTypes.CAPABILITY, RRTypes.OCB),
                inside(RRTypes.CAPABILITY_).and(beforeNewline())
            ),
            new Node(new HardcodedValues(
                "recruit",
                "agent",
                "taxable_income_bonus",
                "trade_base_income_bonus",
                "trade_level_bonus",
                "trade_fleet",
                "farming_level",
                "road_level",
                "mine_resource",
                "happiness_bonus",
                "law_bonus",
                "construction_cost_bonus_military",
                "construction_cost_bonus_religious",
                "construction_cost_bonus_defensive",
                "construction_cost_bonus_other",
                "construction_time_bonus_military",
                "construction_time_bonus_religious",
                "construction_time_bonus_defensive",
                "construction_time_bonus_other",
                "religious_belief",
                "population_health_bonus",
                "population_growth_bonus",
                "wall_level",
                "tower_level",
                "gate_strength",
                "gate_defences",
                "recruits_exp_bonus",
                "recruits_morale_bonus",
                "weapon_simple",
                "weapon_bladed",
                "weapon_missile",
                "weapon_other",
                "upgrade_bodyguard",
                "armour",
                "stage_races")),
            new Node(
                psiElement(RRTypes.AGENT_LIMIT_SETTLEMENT),
                new HardcodedValues("agent_limit_settlement"),
                new Node(new HardcodedValues(agentType()))
            )
        );

        // export_descr_ancillaries
        // ExcludedAncillaries {id}, {id}
        Node export_descr_ancillaries_excluded = new RootNode(
            psiElement(RRTypes.EXCLUDEDANCILLARIES),
            new RepeatingNode(
                psiElement().andOr(
                    psiElement(RRTypes.ID),
                    psiElement(RRTypes.COMMA).afterLeaf(inside(RRTypes.ID))
                ),
                null,
                new Ancillaries()));

        // AcquireAncillary {id}
        Node export_descr_ancillaries_acquire = new RootNode(
            psiElement(RRTypes.ACQUIREANCILLARY),
            new Node(
                new Ancillaries()));

        // export_descr_ancillaries
        // AntiTraits {id}, {id}
        Node export_descr_traits_antitraits = new RootNode(psiElement(RRTypes.ANTITRAITS),
            new RepeatingNode(
                psiElement().andOr(
                    psiElement(RRTypes.ID),
                    psiElement(RRTypes.COMMA).afterLeaf(inside(RRTypes.ID))
                ),
                null,
                new Traits()));

        // Affects {id}
        Node export_descr_traits_affects = new RootNode(psiElement(RRTypes.AFFECTS),
            new Node(new Traits()));

        // descr_mercenaries
        // regions {id} [{id}]...
        Node descr_mercenaries_regions = new RootNode(psiElement(RRTypes.REGIONS),
            new RepeatingNode(id(), null, new Regions()));

        // descr_mount
        // class {class}
        extend(
            CompletionType.BASIC,
            psiElement().afterLeaf(psiElement(RRTypes.CLASS).inside(psiElement(RRTypes.MOUNT_))),
            new HardcodedValues(
                "horse",
                "camel",
                "elephant",
                "chariot",
                "scorpion_cart"
            )
        );

        // descr_regions
        // TODO: resources


        // Stuff that are in more than one file

        // unit egyptian peltast exp 0
        Node unit = new RootNode(psiElement(RRTypes.UNIT),
            new Node(new Units()));

        // WhenToTest {id} (traits and ancillaries file)
        Node when_to_test = new RootNode(psiElement(RRTypes.WHENTOTEST),
            new Node(new HardcodedValues(events())));

        // monitor_event
        Node monitor_event = new RootNode(psiElement(RRTypes.MONITOR_EVENT),
            new Node(any(events()), new HardcodedValues(events()),
                new Node(new HardcodedValues(conditions()))));

        // if
        Node if_ = new RootNode(psiElement(RRTypes.IF),
            new Node(new HardcodedValues(conditions())));

        Node command = new RootNode(
            any(inside(RRTypes.SCRIPT_), inside(RRTypes.IF_)).and(beforeNewline()),
            new Node(new HardcodedValues(commands())));

        // console_command
        Node console_command = new RootNode(psiElement(RRTypes.CONSOLE_COMMAND),
            new Node(any(consoleCommands()), new HardcodedValues(consoleCommands())));

        extendFor(descr_strat_character_type);
        extendFor(descr_strat_region);
        extendFor(descr_strat_settlement_level);
        extendFor(descr_strat_faction_creator);
        extendFor(descr_strat_building_info);
        extendFor(descr_strat_traits);
        extendFor(descr_strat_ancillaries);
        extendFor(descr_strat_core_attitudes_and_faction_relationships);
        extendFor(export_descr_unit_attributes);
        extendFor(export_descr_unit_formation);
        extendFor(export_descr_unit_stat_pri);
        extendFor(export_descr_unit_stat_pri_attr);
        extendFor(export_descr_unit_stat_pri_armour);
        extendFor(export_descr_unit_stat_sec_armour);
        extendFor(export_descr_unit_stat_mental);
        extendFor(export_descr_unit_ownership);
        extendFor(export_descr_unit_ethnicity);
        extendFor(export_descr_buildings_settlement_level);
        extendFor(export_descr_buildings_tag);
        extendFor(export_descr_buildings_classification);
        extendFor(export_descr_buildings_requires);
        extendFor(export_descr_buildings_capability);
        extendFor(export_descr_ancillaries_excluded);
        extendFor(export_descr_ancillaries_acquire);
        extendFor(export_descr_traits_antitraits);
        extendFor(export_descr_traits_affects);
        extendFor(descr_mercenaries_regions);
        extendFor(unit);
        extendFor(when_to_test);
        extendFor(monitor_event);
        extendFor(if_);
        extendFor(command);
        extendFor(console_command);
        extendFor(create_unit);
    }

    private PsiElementPattern.Capture<PsiElement> sequence(PsiElementPattern.Capture<PsiElement>... patterns) {
        PsiElementPattern.Capture<PsiElement> acc = psiElement();
        boolean first = true;

        for (PsiElementPattern.Capture<PsiElement> pattern : patterns) {
            if (first) {
                first = false;

                acc = pattern;
                continue;
            }

            acc = pattern.afterLeaf(acc);
        }

        return acc;
    }

    private PsiElementPattern.Capture<PsiElement> sequence(IElementType... types) {
        PsiElementPattern.Capture<PsiElement> acc = psiElement();
        boolean first = true;

        for (IElementType type : types) {
            if (first) {
                first = false;

                acc = psiElement(type);
                continue;
            }

            acc = psiElement(type).afterLeaf(acc);
        }

        return acc;
    }

    //    private PsiElementPattern.Capture<PsiElement> afterItemUsage() {
//        // TODO: Dont know why I cant just do psiElement().afterSibling(psiElement(CobolItemUsage_.class));
//        return psiElement().afterLeaf(psiElement(CobolTypes.ID).withParent(psiElement(CobolItemUsage_.class)));
//    }
//
//    private ElementPattern<PsiElement> afterItemUsageThatHasParent() {
//        return psiElement().and(afterItemUsage()).and(previousItemHasParent());
//    }
//
    //
//    private ElementPattern<PsiElement> atFileLevel() {
//        // I don't know why I cant just us withParent(). PSI viewer doesn't show a intermediate node.
//        return psiElement().withElementType(CobolTypes.ID).withSuperParent(2, CobolFile.class);
//    }
//
//    private ElementPattern<PsiElement> atIdentificationDivision() {
//        return psiElement().withElementType(CobolTypes.ID).inside(CobolIdentificationDivision_.class);
//    }
//
//    private ElementPattern<PsiElement> atStatementBeginning() {
//        return psiElement()
//                .withElementType(CobolTypes.ID)
//                .inside(CobolProcedureDivision_.class)
//                .afterLeaf(psiElement(CobolTypes.DOT))
//                .and(afterNewline());
//    }
//
//    private ElementPattern<PsiElement> atProcedureDivisionLevel() {
//        return psiElement().withSuperParent(2, CobolProcedureDivision_.class).and(atStatementBeginning());
//    }
//
//    private ElementPattern<PsiElement> atEndProgramName() {
//        return psiElement().inside(CobolEndProgram_.class).andOr(
//                psiElement(CobolTypes.ID),
//                psiElement(CobolTypes.STRING)
//        );
//    }
//
    private PsiElementPattern.Capture<PsiElement> afterNewline() {
        return psiElement().with(new AfterNewline());
    }

    private PsiElementPattern.Capture<PsiElement> beforeNewline() {
        return psiElement().with(new BeforeNewline());
    }

    //
//    private PsiElementPattern<PsiElement, PsiElementPattern.Capture<PsiElement>> isInsideIfScope() {
//        return psiElement().with(new isInsideIfScope());
//    }
//
//    private PsiElementPattern.Capture<PsiElement> afterAny(IElementType... types) {
//        Collection<ElementPattern> patterns = Stream.of(types)
//                .map(it -> psiElement(it))
//                .collect(Collectors.toList());
//        // Ahh, java...
//        ElementPattern[] array = patterns.toArray(new ElementPattern[patterns.size()]);
//
//        return psiElement().afterLeaf(
//                psiElement().andOr(array)
//        );
//    }
//


    private PsiElementPattern.Capture<PsiElement> any(PsiElementPattern.Capture<PsiElement>... patterns) {
        return psiElement().andOr(patterns);
    }

    private PsiElementPattern.Capture<PsiElement> any(IElementType... types) {
        Collection<ElementPattern> patterns = Stream.of(types)
            .map(it -> psiElement(it))
            .collect(Collectors.toList());
        // Ahh, java...
        ElementPattern[] array = patterns.toArray(new ElementPattern[patterns.size()]);

        return psiElement().andOr(array);
    }

    private PsiElementPattern.Capture<PsiElement> any(String... strings) {
        Collection<ElementPattern> patterns = Stream.of(strings)
            .map(it -> psiElement().withText(it))
            .collect(Collectors.toList());
        // Ahh, java...
        ElementPattern[] array = patterns.toArray(new ElementPattern[patterns.size()]);

        return psiElement().andOr(array);
    }

    private PsiElementPattern.Capture<PsiElement> inside(IElementType type) {
        return psiElement().inside(psiElement(type));
    }

    //
//    private ElementPattern<PsiElement> previousItemHasParent() {
//        return psiElement().with(new PreviousItemHasParent(""));
//    }
//
//    private ElementPattern<PsiElement> isInListOfItems() {
//        return psiElement().with(new InItemList(""));
//    }
//
//    private PsiElementPattern.Capture<PsiElement> listOfItemsOrLiterals() {
//        return psiElement().andOr(
//                psiElement(CobolTypes.COMMA),
//                itemOrLiteral()
//        );
//    }
//
    private PsiElementPattern.Capture<PsiElement> listOfIds() {
        return psiElement().andOr(
            id(),
            psiElement(RRTypes.COMMA)
        );
    }

    private PsiElementPattern.Capture<PsiElement> id() {
        return psiElement(RRTypes.ID);
    }

    private PsiElementPattern.Capture<PsiElement> number() {
        return any(RRTypes.INT, RRTypes.FLOAT);
    }
}

class AfterNewline extends PatternCondition<PsiElement> {
    public AfterNewline() {
        super("After newline");
    }

    @Override
    public boolean accepts(@NotNull PsiElement element, ProcessingContext context) {
        PsiElement foo = PsiTreeUtil.prevLeaf(element);
        ASTNode asdas = element.getNode();
        ASTNode dsds = element.getNode().getTreePrev();

        if (foo instanceof PsiWhiteSpace) {
            boolean aaa = foo.textContains('\n');
            return foo.textContains('\n');
        }

        return false;
    }
}

class BeforeNewline extends PatternCondition<PsiElement> {
    public BeforeNewline() {
        super("Before newline");
    }

    @Override
    public boolean accepts(@NotNull PsiElement element, ProcessingContext context) {
        PsiElement nextLeaf = PsiTreeUtil.nextLeaf(element);

        if (nextLeaf instanceof PsiWhiteSpace) {
            return nextLeaf.textContains('\n');
        }

        return false;
    }
}

//
//class StatementAlreadyHas extends PatternCondition<PsiElement> {
//    Collection<IElementType> types = new ArrayList<>();
//
//    public StatementAlreadyHas(IElementType type) {
//        super("Statement already has Element of that type");
//        this.types.add(type);
//    }
//
//    public StatementAlreadyHas(IElementType... types) {
//        super("Statement already has Element of that type");
//        this.types = new ArrayList<>(Arrays.asList(types));
//    }
//
//    @Override
//    public boolean accepts(@NotNull PsiElement element, ProcessingContext context) {
//        // There is probably a better way to do that "is it the only of that type in the statement" check...
//        CobolStatement_ statementOfTyped = PsiTreeUtil.getParentOfType(element, CobolStatement_.class);
//
//        if (statementOfTyped == null) return false;
//
//        PsiElement prevLeaf = element;
//        while ((prevLeaf = PsiTreeUtil.prevLeaf(prevLeaf)) != null) {
//            // If out of statement boundaries, exit.
//            CobolStatement_ statementOfLeaf = PsiTreeUtil.getParentOfType(prevLeaf, CobolStatement_.class);
//
//            if (!statementOfTyped.equals(statementOfLeaf)) {
//                break;
//            }
//
//            if (types.contains(prevLeaf.getNode().getElementType())) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//}
//
//class isInsideIfScope extends PatternCondition<PsiElement> {
//    public isInsideIfScope() {
//        super("Statement already has Element of that type");
//    }
//
//    @Override
//    public boolean accepts(@NotNull PsiElement element, ProcessingContext context) {
//        PsiElement prevLeaf = element;
//        while ((prevLeaf = PsiTreeUtil.prevLeaf(prevLeaf)) != null) {
//
//            if (prevLeaf.getNode().getElementType() == CobolTypes.DOT) {
//                return false;
//            }
//
//            if (prevLeaf.getNode().getElementType() == CobolTypes.IF) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//}
//
//class PreviousItemHasParent extends PatternCondition<PsiElement> {
//
//    public PreviousItemHasParent(String debugMethodName) {
//        super("Previous item has parent");
//    }
//
//    @Override
//    public boolean accepts(@NotNull PsiElement element, ProcessingContext context) {
//        CobolItemUsage_ previousItemUsage = CobolUtil.previousItemUsage(element, true);
//        CobolItemDecl_ itemDecl = previousItemUsage.declaration();
//
//        if (itemDecl == null) {
//            return false;
//        }
//
//        if (itemDecl.parent() != null) {
//            return true;
//        }
//
//        return false;
//    }
//}
//
//class InItemList extends PatternCondition<PsiElement> {
//
//    public InItemList(String debugMethodName) {
//        super("In item list");
//    }
//
//    @Override
//    public boolean accepts(@NotNull PsiElement element, ProcessingContext context) {
//        PsiElement prevLeaf = element;
//
//        int itemsInRow = 0;
//
//        while (prevLeaf != null) {
//            if (
//                    prevLeaf.getNode().getElementType() != CobolTypes.ID
//                            && prevLeaf.getNode().getElementType() != CobolTypes.OF
//                            && prevLeaf.getNode().getElementType() != CobolTypes.COMMA
//            ) {
//                return false;
//            }
//
//            if (prevLeaf.getNode().getElementType() == CobolTypes.ID && prevLeaf.getParent() instanceof CobolItemUsage_) {
//                itemsInRow++;
//            }
//
//            if (prevLeaf.getNode().getElementType() == CobolTypes.OF) {
//                itemsInRow--;
//            }
//
//            if (itemsInRow == 2) {
//                return true;
//            }
//
//            prevLeaf = PsiTreeUtil.prevVisibleLeaf(prevLeaf);
//        }
//
//        return false;
//    }
//}
//

class AfterPatterns extends PatternCondition<PsiElement> {
    ArrayList<PsiElementPattern> patterns;

    public AfterPatterns(PsiElementPattern... patterns) {
        super("Is after patterns");
        this.patterns = new ArrayList<>(Arrays.asList(patterns));
    }

    @Override
    public boolean accepts(@NotNull PsiElement element, ProcessingContext context) {
        int indexOfCurrentPattern = patterns.size() - 1;
        PsiElementPattern currentPattern = patterns.get(indexOfCurrentPattern);

        PsiElement currentElement = PsiTreeUtil.prevVisibleLeaf(element);

        if (!currentPattern.accepts(currentElement)) {
            return false;
        }

        while ((currentElement = PsiTreeUtil.prevVisibleLeaf(currentElement)) != null) {
            if (indexOfCurrentPattern == 0) {
                break;
            }

            if (currentPattern.accepts(currentElement)) {
                continue;
            }

            indexOfCurrentPattern--;
            currentPattern = patterns.get(indexOfCurrentPattern);

            if (!currentPattern.accepts(currentElement)) {
                return false;
            }
        }

        return indexOfCurrentPattern == 0;
    }
}
