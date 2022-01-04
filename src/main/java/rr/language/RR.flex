package rr.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import rr.language.psi.RRTypes;
import com.intellij.psi.TokenType;

%%

%class RRLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

EOL  = [\r\n]
LINE_WS = [ \t]

EOL_WS = {EOL}+{LINE_WS}+
WS      = ({EOL}|{LINE_WS})+

EXPORT_BUILDINGS_MARKER = ";export_buildings.txt"[^\r\n]*
DESCR_CULTURES_MARKER = ";descr_cultures.txt"[^\r\n]*
DESCR_SM_FACTIONS_MARKER = ";descr_sm_factions.txt"[^\r\n]*
DESCR_SM_RESOURCES_MARKER = ";descr_sm_resources.txt"[^\r\n]*
FERAL_DESCR_AI_PERSONALITY_MARKER = ";feral_descr_ai_personality.txt"[^\r\n]*
DESCR_FACTION_GROUPS_MARKER = ";descr_faction_groups.txt"[^\r\n]*
FERAL_DESCR_PORTRAITS_VARIATION_MARKER = ";feral_descr_portraits_variation.txt"[^\r\n]*

COMMENT = [";""¬"][^\r\n]*
INT = [\+\-]?[0-9]+
FLOAT = [\+\-]?[0-9]+\.[0-9]+
STR_CHAR = [^\"\r\n\\]
STRING = \" {STR_CHAR}* \"

TXT_FILE=[^ \r\n]*\.txt
TGA_FILE=[^ \r\n]*\.tga

SPEAR_BONUS_X = "spear_bonus_" {INT}
// †ÎöÈ.íëé are in descr_names. Maybe just typos.
ID = ([:jletterdigit:])+ (\+|\'|\-|\!|\?|\†|\Î|\ö|\È|\.|\í|\ë|\é|[:jletterdigit:])* ([:jletterdigit:])*

%state DESCR_STRAT
%xstate DESCR_STRAT_NO_KEYWORDS

%state EXPORT_DESCR_BUILDINGS
%xstate EXPORT_DESCR_BUILDINGS_NO_KEYWORDS

%state EXPORT_DESCR_UNIT
%xstate EXPORT_DESCR_UNIT_NO_KEYWORDS

%state EXPORT_DESCR_ANCILLARIES
%xstate EXPORT_DESCR_ANCILLARIES_NO_KEYWORDS

%state EXPORT_DESCR_MERCENARIES
%xstate EXPORT_DESCR_MERCENARIES_NO_KEYWORDS

%xstate EXPORT_BUILDINGS
%xstate EXPORT_BUILDINGS_NO_KEYWORDS

%state EXPORT_DESCR_TRAITS
%state DESCR_NAMES
%state DESCR_UNIT_VARIATION
%xstate FERAL_DESCR_AI_PERSONALITY
%state DESCR_FACTION_GROUPS
%state FERAL_DESCR_PORTRAITS_VARIATION

%state CONDITIONS

%%
<DESCR_NAMES>{EOL_WS} {return RRTypes.EOL;}

{EXPORT_BUILDINGS_MARKER}                {yybegin(EXPORT_BUILDINGS); return RRTypes.EXPORT_BUILDINGS_MARKER;}
{DESCR_CULTURES_MARKER}                  {return RRTypes.DESCR_CULTURES_MARKER;}
{DESCR_SM_FACTIONS_MARKER}               {return RRTypes.DESCR_SM_FACTIONS_MARKER;}
{FERAL_DESCR_AI_PERSONALITY_MARKER}      {yybegin(FERAL_DESCR_AI_PERSONALITY); return RRTypes.FERAL_DESCR_AI_PERSONALITY_MARKER;}
{DESCR_FACTION_GROUPS_MARKER}            {yybegin(DESCR_FACTION_GROUPS); return RRTypes.DESCR_FACTION_GROUPS_MARKER;}
{DESCR_SM_RESOURCES_MARKER}              {return RRTypes.DESCR_SM_RESOURCES_MARKER;}
{FERAL_DESCR_PORTRAITS_VARIATION_MARKER} {yybegin(FERAL_DESCR_PORTRAITS_VARIATION); return RRTypes.FERAL_DESCR_PORTRAITS_VARIATION_MARKER;}


{WS}             {return TokenType.WHITE_SPACE;}
{COMMENT}        {return RRTypes.COMMENT;}
{INT}            {return RRTypes.INT;}
{FLOAT}          {return RRTypes.FLOAT;}
{STRING}         {return RRTypes.STRING;}
true|false       {return RRTypes.BOOLEAN;}

","              {return RRTypes.COMMA;}
"{"              {return RRTypes.OCB;}
"}"              {return RRTypes.CCB;}
"["              {return RRTypes.OSB;}
"]"              {return RRTypes.CSB;}
":"              {return RRTypes.COLON;}
"("              {return RRTypes.OP;}
")"              {return RRTypes.CP;}

{TGA_FILE}       {return RRTypes.TGA_FILE;}
{TXT_FILE}       {return RRTypes.TXT_FILE;}

// character_type
"all"                                             {return RRTypes.ALL;}
"family"                                          {return RRTypes.FAMILY;}
"named character"                                 {return RRTypes.NAMED_CHARACTER;}
"general"                                         {return RRTypes.GENERAL;}
"admiral"                                         {return RRTypes.ADMIRAL;}
"diplomat"                                        {return RRTypes.DIPLOMAT;}
"spy"                                             {return RRTypes.SPY;}
"assassin"                                        {return RRTypes.ASSASSIN;}
"merchant"                                        {return RRTypes.MERCHANT;}
// unit class
"light"                                           {return RRTypes.LIGHT;}
"heavy"                                           {return RRTypes.HEAVY;}
"skirmish"                                        {return RRTypes.SKIRMISH;}
"missile"                                         {return RRTypes.MISSILE;}
"spearmen"                                        {return RRTypes.SPEARMEN;}
// unit category
"infantry"                                        {return RRTypes.INFANTRY;}
"cavalry"                                         {return RRTypes.CAVALRY;}
"siege"                                           {return RRTypes.SIEGE;}
"handler"                                         {return RRTypes.HANDLER;}
"ship"                                            {return RRTypes.SHIP;}
"non_combatant"                                   {return RRTypes.NON_COMBATANT;}
// hide_type
"hide_forest"                                     {return RRTypes.HIDE_FOREST;}
"hide_improved_forest"                            {return RRTypes.HIDE_IMPROVED_FOREST;}
"hide_long_grass"                                 {return RRTypes.HIDE_LONG_GRASS;}
"hide_anywhere"                                   {return RRTypes.HIDE_ANYWHERE;}
// formations
"square"                                          {return RRTypes.SQUARE;}
"square_hollow"                                   {return RRTypes.SQUARE_HOLLOW;}
"horde"                                           {return RRTypes.HORDE;}
"column"                                          {return RRTypes.COLUMN;}
"phalanx"                                         {return RRTypes.PHALANX;}
"testudo"                                         {return RRTypes.TESTUDO;}
"wedge"                                           {return RRTypes.WEDGE;}
// mount_class
"horse"                                           {return RRTypes.HORSE;}
"camel"                                           {return RRTypes.CAMEL;}
"elephant"                                        {return RRTypes.ELEPHANT;}
"chariot"                                         {return RRTypes.CHARIOT;}

// events
"PreBattle"                             {return RRTypes.PREBATTLE;}
"PreBattleWithdrawal"                   {return RRTypes.PREBATTLEWITHDRAWAL;}
"BattleAiCommenced"                     {return RRTypes.BATTLEAICOMMENCED;}
"BattleDelayPhaseCommenced"             {return RRTypes.BATTLEDELAYPHASECOMMENCED;}
"BattleDeploymentPhaseCommenced"        {return RRTypes.BATTLEDEPLOYMENTPHASECOMMENCED;}
"BattleConflictPhaseCommenced"          {return RRTypes.BATTLECONFLICTPHASECOMMENCED;}
"BattlePlayerUnitAttacksEnemyUnit"      {return RRTypes.BATTLEPLAYERUNITATTACKSENEMYUNIT;}
"BattleEnemyUnitAttacksPlayerUnit"      {return RRTypes.BATTLEENEMYUNITATTACKSPLAYERUNIT;}
"BattlePlayerAttacksSettlementBuilding" {return RRTypes.BATTLEPLAYERATTACKSSETTLEMENTBUILDING;}
"BattleEnemyAttacksSettlementBuilding"  {return RRTypes.BATTLEENEMYATTACKSSETTLEMENTBUILDING;}
"BattleUnitGoesBerserk"                 {return RRTypes.BATTLEUNITGOESBERSERK;}
"BattlePlayerUnitGoesBerserk"           {return RRTypes.BATTLEPLAYERUNITGOESBERSERK;}
"BattleEnemyUnitGoesBerserk"            {return RRTypes.BATTLEENEMYUNITGOESBERSERK;}
"BattleUnitRouts"                       {return RRTypes.BATTLEUNITROUTS;}
"BattlePlayerUnitRouts"                 {return RRTypes.BATTLEPLAYERUNITROUTS;}
"BattleEnemyUnitRouts"                  {return RRTypes.BATTLEENEMYUNITROUTS;}
"BattlePlayerSiegeEngineDestroyed"      {return RRTypes.BATTLEPLAYERSIEGEENGINEDESTROYED;}
"BattleEnemySiegeEngineDestroyed"       {return RRTypes.BATTLEENEMYSIEGEENGINEDESTROYED;}
"PostBattle"                            {return RRTypes.POSTBATTLE;}
"BattleArmyRouted"                      {return RRTypes.BATTLEARMYROUTED;}
"BattleGeneralKilled"                   {return RRTypes.BATTLEGENERALKILLED;}
"BattleGeneralRouted"                   {return RRTypes.BATTLEGENERALROUTED;}
"BattleReinforcementsArrive"            {return RRTypes.BATTLEREINFORCEMENTSARRIVE;}
"BattleSiegeEngineDestroyed"            {return RRTypes.BATTLESIEGEENGINEDESTROYED;}
"BattleSiegeEngineDocksWall"            {return RRTypes.BATTLESIEGEENGINEDOCKSWALL;}
"BattleGatesAttackedByEngine"           {return RRTypes.BATTLEGATESATTACKEDBYENGINE;}
"BattleGatesAttackedByPlayerEngine"     {return RRTypes.BATTLEGATESATTACKEDBYPLAYERENGINE;}
"BattleGatesAttackedByEnemyEngine"      {return RRTypes.BATTLEGATESATTACKEDBYENEMYENGINE;}
"BattleBattleGatesDestroyedByEngine"    {return RRTypes.BATTLEBATTLEGATESDESTROYEDBYENGINE;}
"BattleWallsBreachedByEngine"           {return RRTypes.BATTLEWALLSBREACHEDBYENGINE;}
"BattleWallsCaptured"                   {return RRTypes.BATTLEWALLSCAPTURED;}
"BattleFinished"                        {return RRTypes.BATTLEFINISHED;}
"EnteredCityView"                       {return RRTypes.ENTEREDCITYVIEW;}
"BattleMinimapAction"                   {return RRTypes.BATTLEMINIMAPACTION;}
"BattlePlayerUnitSelected"              {return RRTypes.BATTLEPLAYERUNITSELECTED;}
"EnterTacticalMode"                     {return RRTypes.ENTERTACTICALMODE;}
"BattleReinforcementsHack"              {return RRTypes.BATTLEREINFORCEMENTSHACK;}
"FactionTurnStart"                      {return RRTypes.FACTIONTURNSTART;}
"FactionWarDeclared"                    {return RRTypes.FACTIONWARDECLARED;}
"HordeFormed"                           {return RRTypes.HORDEFORMED;}
"FactionTurnEnd"                        {return RRTypes.FACTIONTURNEND;}
"HireMercenaries"                       {return RRTypes.HIREMERCENARIES;}
"GeneralCaptureResidence"               {return RRTypes.GENERALCAPTURERESIDENCE;}
"GeneralCaptureWonder"                  {return RRTypes.GENERALCAPTUREWONDER;}
"GeneralCaptureSettlement"              {return RRTypes.GENERALCAPTURESETTLEMENT;}
"LeaderDestroyedFaction"                {return RRTypes.LEADERDESTROYEDFACTION;}
"Disaster"                              {return RRTypes.DISASTER;}
"CharacterDamagedByDisaster"            {return RRTypes.CHARACTERDAMAGEDBYDISASTER;}
"GeneralAssaultsResidence"              {return RRTypes.GENERALASSAULTSRESIDENCE;}
"OfferedForAdoption"                    {return RRTypes.OFFEREDFORADOPTION;}
"LesserGeneralOfferedForAdoption"       {return RRTypes.LESSERGENERALOFFEREDFORADOPTION;}
"OfferedForMarriage"                    {return RRTypes.OFFEREDFORMARRIAGE;}
"BrotherAdopted"                        {return RRTypes.BROTHERADOPTED;}
"BecomesFactionLeader"                  {return RRTypes.BECOMESFACTIONLEADER;}
"BecomesFactionHeir"                    {return RRTypes.BECOMESFACTIONHEIR;}
"TakeOffice"                            {return RRTypes.TAKEOFFICE;}
"CeasedFactionLeader"                   {return RRTypes.CEASEDFACTIONLEADER;}
"CeasedFactionHeir"                     {return RRTypes.CEASEDFACTIONHEIR;}
"LeaveOffice"                           {return RRTypes.LEAVEOFFICE;}
"UngarrisonedFort"                      {return RRTypes.UNGARRISONEDFORT;}
"LostLegionaryEagle"                    {return RRTypes.LOSTLEGIONARYEAGLE;}
"CapturedLegionaryEagle"                {return RRTypes.CAPTUREDLEGIONARYEAGLE;}
"RecapturedLegionaryEagle"              {return RRTypes.RECAPTUREDLEGIONARYEAGLE;}
"SenateExposure"                        {return RRTypes.SENATEEXPOSURE;}
"QuaestorInvestigationMinor"            {return RRTypes.QUAESTORINVESTIGATIONMINOR;}
"QuaestorInvestigation"                 {return RRTypes.QUAESTORINVESTIGATION;}
"QuaestorInvestigationMajor"            {return RRTypes.QUAESTORINVESTIGATIONMAJOR;}
"PopularSupportForOverthrow"            {return RRTypes.POPULARSUPPORTFOROVERTHROW;}
"SenateReadyToOutlawFaction"            {return RRTypes.SENATEREADYTOOUTLAWFACTION;}
"SenateOutlawsFaction"                  {return RRTypes.SENATEOUTLAWSFACTION;}
"NewTurnStart"                          {return RRTypes.NEWTURNSTART;}
"ScriptPromptCallback"                  {return RRTypes.SCRIPTPROMPTCALLBACK;}
"FactionDestroyed"                      {return RRTypes.FACTIONDESTROYED;}
"Birth"                                 {return RRTypes.BIRTH;}
"CharacterComesOfAge"                   {return RRTypes.CHARACTERCOMESOFAGE;}
"CharacterMarries"                      {return RRTypes.CHARACTERMARRIES;}
"CharacterBecomesAFather"               {return RRTypes.CHARACTERBECOMESAFATHER;}
"CharacterTurnStart"                    {return RRTypes.CHARACTERTURNSTART;}
"CharacterTurnEnd"                      {return RRTypes.CHARACTERTURNEND;}
"CharacterTurnEndInSettlement"          {return RRTypes.CHARACTERTURNENDINSETTLEMENT;}
"GeneralDevastatesTile"                 {return RRTypes.GENERALDEVASTATESTILE;}
"SpyMission"                            {return RRTypes.SPYMISSION;}
"ExecutesASpyOnAMission"                {return RRTypes.EXECUTESASPYONAMISSION;}
"LeaderOrderedSpyingMission"            {return RRTypes.LEADERORDEREDSPYINGMISSION;}
"AssassinationMission"                  {return RRTypes.ASSASSINATIONMISSION;}
"ExecutesAnAssassinOnAMission"          {return RRTypes.EXECUTESANASSASSINONAMISSION;}
"LeaderOrderedAssassination"            {return RRTypes.LEADERORDEREDASSASSINATION;}
"SufferAssassinationAttempt"            {return RRTypes.SUFFERASSASSINATIONATTEMPT;}
"SabotageMission"                       {return RRTypes.SABOTAGEMISSION;}
"LeaderOrderedSabotage"                 {return RRTypes.LEADERORDEREDSABOTAGE;}
"BriberyMission"                        {return RRTypes.BRIBERYMISSION;}
"LeaderOrderedBribery"                  {return RRTypes.LEADERORDEREDBRIBERY;}
"AcceptBribe"                           {return RRTypes.ACCEPTBRIBE;}
"RefuseBribe"                           {return RRTypes.REFUSEBRIBE;}
"Insurrection"                          {return RRTypes.INSURRECTION;}
"DiplomacyMission"                      {return RRTypes.DIPLOMACYMISSION;}
"LeaderOrderedDiplomacy"                {return RRTypes.LEADERORDEREDDIPLOMACY;}
"LeaderSenateMissionSuccess"            {return RRTypes.LEADERSENATEMISSIONSUCCESS;}
"LeaderSenateMissionFailed"             {return RRTypes.LEADERSENATEMISSIONFAILED;}
"ConstructionItemClicked"               {return RRTypes.CONSTRUCTIONITEMCLICKED;}
"RecruitmentItemClicked"                {return RRTypes.RECRUITMENTITEMCLICKED;}
"RecruitmentPopulated"                  {return RRTypes.RECRUITMENTPOPULATED;}
"ConstructionPopulated"                 {return RRTypes.CONSTRUCTIONPOPULATED;}
"AgentListPopulated"                    {return RRTypes.AGENTLISTPOPULATED;}
"MoveRetinuePopulated"                  {return RRTypes.MOVERETINUEPOPULATED;}
"MoveRetinuePressed"                    {return RRTypes.MOVERETINUEPRESSED;}
"MoveRetinueAncillarySelected"          {return RRTypes.MOVERETINUEANCILLARYSELECTED;}
"MoveRetinueAncillaryDeselected"        {return RRTypes.MOVERETINUEANCILLARYDESELECTED;}
"MissionSelected"                       {return RRTypes.MISSIONSELECTED;}
"AgentSelected"                         {return RRTypes.AGENTSELECTED;}
"ScrollDidOpen"                         {return RRTypes.SCROLLDIDOPEN;}
"UnitHasRouted"                         {return RRTypes.UNITHASROUTED;}
"BattleUnitActionStatus"                {return RRTypes.BATTLEUNITACTIONSTATUS;}
"DiplomacyScrollPopulated"              {return RRTypes.DIPLOMACYSCROLLPOPULATED;}
"ItemDeselected"                        {return RRTypes.ITEMDESELECTED;}
"UnitInfoOpened"                        {return RRTypes.UNITINFOOPENED;}
"AdvisorAudioStopped"                   {return RRTypes.ADVISORAUDIOSTOPPED;}
"SettlementTurnStart"                   {return RRTypes.SETTLEMENTTURNSTART;}
"SettlementTurnEnd"                     {return RRTypes.SETTLEMENTTURNEND;}
"NewAdmiralCreated"                     {return RRTypes.NEWADMIRALCREATED;}
"UnitTrained"                           {return RRTypes.UNITTRAINED;}
"GovernorUnitTrained"                   {return RRTypes.GOVERNORUNITTRAINED;}
"BuildingCompleted"                     {return RRTypes.BUILDINGCOMPLETED;}
"GovernorBuildingCompleted"             {return RRTypes.GOVERNORBUILDINGCOMPLETED;}
"PlugInCompleted"                       {return RRTypes.PLUGINCOMPLETED;}
"GovernorPlugInCompleted"               {return RRTypes.GOVERNORPLUGINCOMPLETED;}
"AgentCreated"                          {return RRTypes.AGENTCREATED;}
"GovernorAgentCreated"                  {return RRTypes.GOVERNORAGENTCREATED;}
"BuildingDestroyed"                     {return RRTypes.BUILDINGDESTROYED;}
"GovernorBuildingDestroyed"             {return RRTypes.GOVERNORBUILDINGDESTROYED;}
"CityRiots"                             {return RRTypes.CITYRIOTS;}
"GovernorCityRiots"                     {return RRTypes.GOVERNORCITYRIOTS;}
"CityRebels"                            {return RRTypes.CITYREBELS;}
"GovernorCityRebels"                    {return RRTypes.GOVERNORCITYREBELS;}
"GovernorThrowGames"                    {return RRTypes.GOVERNORTHROWGAMES;}
"GovernorThrowRaces"                    {return RRTypes.GOVERNORTHROWRACES;}
"UngarrisonedSettlement"                {return RRTypes.UNGARRISONEDSETTLEMENT;}
"EnslavePopulation"                     {return RRTypes.ENSLAVEPOPULATION;}
"ExterminatePopulation"                 {return RRTypes.EXTERMINATEPOPULATION;}
"CitySacked"                            {return RRTypes.CITYSACKED;}
"CharacterSelected"                     {return RRTypes.CHARACTERSELECTED;}
"SettlementSelected"                    {return RRTypes.SETTLEMENTSELECTED;}
"EnemySettlementSelected"               {return RRTypes.ENEMYSETTLEMENTSELECTED;}
"MultiTurnMove"                         {return RRTypes.MULTITURNMOVE;}
"CharacterPanelOpen"                    {return RRTypes.CHARACTERPANELOPEN;}
"SettlementPanelOpen"                   {return RRTypes.SETTLEMENTPANELOPEN;}
"FinancesPanelOpen"                     {return RRTypes.FINANCESPANELOPEN;}
"FactionSummaryPanelOpen"               {return RRTypes.FACTIONSUMMARYPANELOPEN;}
"FamilyTreePanelOpen"                   {return RRTypes.FAMILYTREEPANELOPEN;}
"DiplomaticStandingPanelOpen"           {return RRTypes.DIPLOMATICSTANDINGPANELOPEN;}
"SenateMissionsPanelOpen"               {return RRTypes.SENATEMISSIONSPANELOPEN;}
"SenateOfficesPanelOpen"                {return RRTypes.SENATEOFFICESPANELOPEN;}
"DiplomacyPanelOpen"                    {return RRTypes.DIPLOMACYPANELOPEN;}
"PreBattlePanelOpen"                    {return RRTypes.PREBATTLEPANELOPEN;}
"RecruitmentPanelOpen"                  {return RRTypes.RECRUITMENTPANELOPEN;}
"ConstructionPanelOpen"                 {return RRTypes.CONSTRUCTIONPANELOPEN;}
"TradePanelOpen"                        {return RRTypes.TRADEPANELOPEN;}
"HireMercenariesPanelOpen"              {return RRTypes.HIREMERCENARIESPANELOPEN;}
"NavalAutoResolvePanelOpen"             {return RRTypes.NAVALAUTORESOLVEPANELOPEN;}
"IncomingMessage"                       {return RRTypes.INCOMINGMESSAGE;}
"MessageOpen"                           {return RRTypes.MESSAGEOPEN;}
"RequestBuildingAdvice"                 {return RRTypes.REQUESTBUILDINGADVICE;}
"RequestTrainingAdvice"                 {return RRTypes.REQUESTTRAININGADVICE;}
"RequestMercenariesAdvice"              {return RRTypes.REQUESTMERCENARIESADVICE;}
"ButtonPressed"                         {return RRTypes.BUTTONPRESSED;}
"ShortcutTriggered"                     {return RRTypes.SHORTCUTTRIGGERED;}
"ScrollOpened"                          {return RRTypes.SCROLLOPENED;}
"ScrollClosed"                          {return RRTypes.SCROLLCLOSED;}
"AdviceSupressed"                       {return RRTypes.ADVICESUPRESSED;}
"ScrollAdviceRequested"                 {return RRTypes.SCROLLADVICEREQUESTED;}
"PreBattleScrollAdviceRequested"        {return RRTypes.PREBATTLESCROLLADVICEREQUESTED;}
"NavalPreBattleScrollAdviceRequested"   {return RRTypes.NAVALPREBATTLESCROLLADVICEREQUESTED;}
"SettlementScrollAdviceRequested"       {return RRTypes.SETTLEMENTSCROLLADVICEREQUESTED;}
"Idle"                                  {return RRTypes.IDLE;}
"AbandonShowMe"                         {return RRTypes.ABANDONSHOWME;}
"ScriptedAdvice"                        {return RRTypes.SCRIPTEDADVICE;}
"DeclineAutomatedSettlementManagement"  {return RRTypes.DECLINEAUTOMATEDSETTLEMENTMANAGEMENT;}
"EscPressed"                            {return RRTypes.ESCPRESSED;}
"GameReloaded"                          {return RRTypes.GAMERELOADED;}
"FirstStratUpdates"                     {return RRTypes.FIRSTSTRATUPDATES;}
"MovieStopped"                          {return RRTypes.MOVIESTOPPED;}
"SelectionAssistPossible"               {return RRTypes.SELECTIONASSISTPOSSIBLE;}
"SettlementButtonPressed"               {return RRTypes.SETTLEMENTBUTTONPRESSED;}
"WorldScriptTerminate"                  {return RRTypes.WORLDSCRIPTTERMINATE;}
"CampaignHudShown"                      {return RRTypes.CAMPAIGNHUDSHOWN;}
"ContextPopupInteraction"               {return RRTypes.CONTEXTPOPUPINTERACTION;}
"DiplomacyConstructingOffer"            {return RRTypes.DIPLOMACYCONSTRUCTINGOFFER;}
"DiplomacyConstructingCounterOffer"     {return RRTypes.DIPLOMACYCONSTRUCTINGCOUNTEROFFER;}
"DiplomacyOpponentPresentsOffer"        {return RRTypes.DIPLOMACYOPPONENTPRESENTSOFFER;}
"DiplomacyOpponentPresentsCounterOffer" {return RRTypes.DIPLOMACYOPPONENTPRESENTSCOUNTEROFFER;}
"FactionSummary"                        {return RRTypes.FACTIONSUMMARY;}
"FactionSenate"                         {return RRTypes.FACTIONSENATE;}
"FactionSenatePolicy"                   {return RRTypes.FACTIONSENATEPOLICY;}
"FactionSenateMissions"                 {return RRTypes.FACTIONSENATEMISSIONS;}
"FactionSenateOfficials"                {return RRTypes.FACTIONSENATEOFFICIALS;}
"FactionSenateFloor"                    {return RRTypes.FACTIONSENATEFLOOR;}
"FactionFactions"                       {return RRTypes.FACTIONFACTIONS;}
"FactionDetails"                        {return RRTypes.FACTIONDETAILS;}
"FactionFamilyTree"                     {return RRTypes.FACTIONFAMILYTREE;}
"FactionRankings"                       {return RRTypes.FACTIONRANKINGS;}
"FactionLists"                          {return RRTypes.FACTIONLISTS;}
"SettlementCharacter"                   {return RRTypes.SETTLEMENTCHARACTER;}
"SettlementTrade"                       {return RRTypes.SETTLEMENTTRADE;}
"SettlementOverview"                    {return RRTypes.SETTLEMENTOVERVIEW;}
"SpySelected"                           {return RRTypes.SPYSELECTED;}
"DiplomatSelected"                      {return RRTypes.DIPLOMATSELECTED;}
"AssassinSelected"                      {return RRTypes.ASSASSINSELECTED;}
"FleetSelected"                         {return RRTypes.FLEETSELECTED;}
"CampaignMapGesture"                    {return RRTypes.CAMPAIGNMAPGESTURE;}
"CampaignDoingBadly"                    {return RRTypes.CAMPAIGNDOINGBADLY;}
"BattleMapGesture"                      {return RRTypes.BATTLEMAPGESTURE;}
"HideBattleUI"                          {return RRTypes.HIDEBATTLEUI;}
"FeralNewsVisible"                      {return RRTypes.FERALNEWSVISIBLE;}
"AgentHubOpened"                        {return RRTypes.AGENTHUBOPENED;}
"MoveRetinueOpened"                     {return RRTypes.MOVERETINUEOPENED;}
"OwnFactionDetailsOpened"               {return RRTypes.OWNFACTIONDETAILSOPENED;}
"DiplomaticStandingShown"               {return RRTypes.DIPLOMATICSTANDINGSHOWN;}
"FactionFinancesShown"                  {return RRTypes.FACTIONFINANCESSHOWN;}
"FamilyTreeShown"                       {return RRTypes.FAMILYTREESHOWN;}
"SendAgentPanel"                        {return RRTypes.SENDAGENTPANEL;}
"SettlementDetailsShown"                {return RRTypes.SETTLEMENTDETAILSSHOWN;}
"CharacterInfoScreen"                   {return RRTypes.CHARACTERINFOSCREEN;}
"FriendlyCharacterSelected"             {return RRTypes.FRIENDLYCHARACTERSELECTED;}
"EnemyCharacterSelected"                {return RRTypes.ENEMYCHARACTERSELECTED;}
"FriendlySettlementSelected"            {return RRTypes.FRIENDLYSETTLEMENTSELECTED;}
"MapOverlayOpened"                      {return RRTypes.MAPOVERLAYOPENED;}
"SiegeDetailsShown"                     {return RRTypes.SIEGEDETAILSSHOWN;}
"PreBattleScreen"                       {return RRTypes.PREBATTLESCREEN;}
"TacticalMapShown"                      {return RRTypes.TACTICALMAPSHOWN;}
"PostBattleScreen"                      {return RRTypes.POSTBATTLESCREEN;}
"UnitsGrouped"                          {return RRTypes.UNITSGROUPED;}
"EnteredBattle"                         {return RRTypes.ENTEREDBATTLE;}
"AdvisorOpened"                         {return RRTypes.ADVISOROPENED;}
"FormationTypesShown"                   {return RRTypes.FORMATIONTYPESSHOWN;}
"MerchantSelected"                      {return RRTypes.MERCHANTSELECTED;}
"NavalCombatStarted"                    {return RRTypes.NAVALCOMBATSTARTED;}
"MergeArmiesOpened"                     {return RRTypes.MERGEARMIESOPENED;}
"RoutesBlockaded"                       {return RRTypes.ROUTESBLOCKADED;}
"ElectionResults"                       {return RRTypes.ELECTIONRESULTS;}
"BattleToggleMenu"                      {return RRTypes.BATTLETOGGLEMENU;}
"AmbushMode"                            {return RRTypes.AMBUSHMODE;}
"NewsTabClosed"                         {return RRTypes.NEWSTABCLOSED;}
"NewsTabOpened"                         {return RRTypes.NEWSTABOPENED;}
"BattleNewsTabOpened"                   {return RRTypes.BATTLENEWSTABOPENED;}
"QuickListsOpened"                      {return RRTypes.QUICKLISTSOPENED;}
"EmbargoIsAvailable"                    {return RRTypes.EMBARGOISAVAILABLE;}
"RebelCharacterSelected"                {return RRTypes.REBELCHARACTERSELECTED;}
"HighTaxesCauseDisorder"                {return RRTypes.HIGHTAXESCAUSEDISORDER;}
"FailedToEndTurn"                       {return RRTypes.FAILEDTOENDTURN;}
"AcquisitionMission"                    {return RRTypes.ACQUISITIONMISSION;}
"SufferAcquisitionAttempt"              {return RRTypes.SUFFERACQUISITIONATTEMPT;}


<YYINITIAL>
{
    "campaign"                  {yybegin(DESCR_STRAT); return RRTypes.CAMPAIGN;}
    "type"                      {yybegin(EXPORT_DESCR_UNIT_NO_KEYWORDS); return RRTypes.TYPE;}
    "tags"                      {yybegin(EXPORT_DESCR_BUILDINGS); return RRTypes.TAGS;}
    "Ancillary"                 {yybegin(EXPORT_DESCR_ANCILLARIES); return RRTypes.ANCILLARY;}
    "Trait"                     {yybegin(EXPORT_DESCR_TRAITS); return RRTypes.TRAIT;}
    "pool"                      {yybegin(EXPORT_DESCR_MERCENARIES); return RRTypes.POOL;}
    "faction"                   {yybegin(DESCR_NAMES); return RRTypes.FACTION;}
    "skin"                      {yybegin(DESCR_UNIT_VARIATION); return RRTypes.SKIN;}

    // Here because descr_regions has no starting anchor on which I can start a sublexer.
    // Idea: Automatically add a comment on top of each file, with the file name, and use it as an ancor.
    "none"               {return RRTypes.NONE;}
    {ID}                 {return RRTypes.ID;}
}

// Unfortunately, RR allows free text instead of IDs in some places.
// This means that some what the lexer identifies as a keyword
// for example: "ancillaries druid", or "unit city militia exp 1 armour 0 weapon_lvl 0"
// Identified as keywords    ^^^^^            ^^^^
// Solution: Identify these places and start a sub-lexer.
<DESCR_STRAT>
{
    "end"                        {return RRTypes.END;}
    "summer"                     {return RRTypes.SUMMER;}
    "winter"                     {return RRTypes.WINTER;}
    "campaign"                   {return RRTypes.CAMPAIGN;}
    "playable"                   {return RRTypes.PLAYABLE;}
    "unlockable"                 {return RRTypes.UNLOCKABLE;}
    "nonplayable"                {return RRTypes.NONPLAYABLE;}
    "start_date"                 {return RRTypes.START_DATE;}
    "end_date"                   {return RRTypes.END_DATE;}
    "marian_reforms_disabled"    {return RRTypes.MARIAN_REFORMS_DISABLED;}
    "brigand_spawn_value"        {return RRTypes.BRIGAND_SPAWN_VALUE;}
    "pirate_spawn_value"         {return RRTypes.PIRATE_SPAWN_VALUE;}
    "landmark"                   {return RRTypes.LANDMARK;}
    "resource_quantity_enabled"  {return RRTypes.RESOURCE_QUANTITY_ENABLED;}
    "resource_quantity_disabled" {return RRTypes.RESOURCE_QUANTITY_DISABLED;}
    "resource"                   {return RRTypes.RESOURCE;}
    "faction"                    {return RRTypes.FACTION;}
    "superfaction"               {return RRTypes.SUPERFACTION;}
    "denari"                     {return RRTypes.DENARI;}
    "settlement"                 {return RRTypes.SETTLEMENT;}
    "level"                      {return RRTypes.LEVEL;}
    "village"                    {return RRTypes.VILLAGE;}
    "town"                       {return RRTypes.TOWN;}
    "large_town"                 {return RRTypes.LARGE_TOWN;}
    "city"                       {return RRTypes.CITY;}
    "large_city"                 {return RRTypes.LARGE_CITY;}
    "huge_city"                  {return RRTypes.HUGE_CITY;}
    "region"                     {return RRTypes.REGION;}
    "year_founded"               {return RRTypes.YEAR_FOUNDED;}
    "population"                 {return RRTypes.POPULATION;}
    "plan_set"                   {return RRTypes.PLAN_SET;}
    "faction_creator"            {return RRTypes.FACTION_CREATOR;}
    "default_set"                {return RRTypes.DEFAULT_SET;}
    "building"                   {return RRTypes.BUILDING;}
    "type"                       {return RRTypes.TYPE;}
    "character"                  {return RRTypes.CHARACTER;}
    "sub_faction"                {return RRTypes.SUB_FACTION;}
    "leader"                     {return RRTypes.LEADER;}
    "heir"                       {return RRTypes.HEIR;}
    "age"                        {return RRTypes.AGE;}
    "portrait_index"             {return RRTypes.PORTRAIT_INDEX;}
    "traits"                     {return RRTypes.TRAITS;}
    "ancillaries"                {yybegin(DESCR_STRAT_NO_KEYWORDS); return RRTypes.ANCILLARIES;}
    "army"                       {return RRTypes.ARMY;}
    "unit"                       {yybegin(DESCR_STRAT_NO_KEYWORDS); return RRTypes.UNIT;}
    "armour"                     {return RRTypes.ARMOUR;}
    "weapon_lvl"                 {return RRTypes.WEAPON_LVL;}
    "character"                  {return RRTypes.CHARACTER;}
    "character_record"           {return RRTypes.CHARACTER_RECORD;}
    "core_attitudes"             {return RRTypes.CORE_ATTITUDES;}
    "character_record"           {return RRTypes.CHARACTER_RECORD;}
    "command"                    {return RRTypes.COMMAND;}
    "influence"                  {return RRTypes.INFLUENCE;}
    "management"                 {return RRTypes.MANAGEMENT;}
    "subterfuge"                 {return RRTypes.SUBTERFUGE;}
    "alive"                      {return RRTypes.ALIVE;}
    "never_a_leader"             {return RRTypes.NEVER_A_LEADER;}
    "male"                       {return RRTypes.MALE;}
    "female"                     {return RRTypes.FEMALE;}
    "relative"                   {return RRTypes.RELATIVE;}
    "core_attitudes"             {return RRTypes.CORE_ATTITUDES;}
    "faction_relationships"      {return RRTypes.FACTION_RELATIONSHIPS;}
    "superfaction_setup"         {return RRTypes.SUPERFACTION_SETUP;}
    "default_hostile"            {return RRTypes.DEFAULT_HOSTILE;}
    "mission_queue"              {return RRTypes.MISSION_QUEUE;}
    "x"                          {return RRTypes.X;}
    "y"                          {return RRTypes.Y;}
    "script"                     {return RRTypes.SCRIPT;}
    "once_only"                  {return RRTypes.ONCE_ONLY;}
    {ID}                         {return RRTypes.ID;}
}
<DESCR_STRAT_NO_KEYWORDS>
{
    {WS}                      {return TokenType.WHITE_SPACE;}
    {COMMENT}                 {return RRTypes.COMMENT;}
    ","                       {return RRTypes.COMMA;}
    "exp"                     {yybegin(DESCR_STRAT); return RRTypes.EXP;}
    "army"                    {yybegin(DESCR_STRAT); return RRTypes.ARMY;}
    "character"               {yybegin(DESCR_STRAT); return RRTypes.CHARACTER;}
    "character_record"        {yybegin(DESCR_STRAT); return RRTypes.CHARACTER_RECORD;}
    {ID}                      {return RRTypes.ID;}
}

<EXPORT_DESCR_UNIT>
{
    "type"                          {yybegin(EXPORT_DESCR_UNIT_NO_KEYWORDS); return RRTypes.TYPE;}
    "category"                      {return RRTypes.CATEGORY;}
    "class"                         {return RRTypes.CLASS;}
    "voice_type"                    {return RRTypes.VOICE_TYPE;}
    "voice_indexes"                 {return RRTypes.VOICE_INDEXES;}
    "soldier"                       {yybegin(EXPORT_DESCR_UNIT_NO_KEYWORDS); return RRTypes.SOLDIER;}
    "mount_effect"                  {return RRTypes.MOUNT_EFFECT;}
    "no"                            {return RRTypes.NO;}
    "frighten_foot"                 {return RRTypes.FRIGHTEN_FOOT;}
    "frighten_mounted"              {return RRTypes.FRIGHTEN_MOUNTED;}
    "can_run_amok"                  {return RRTypes.CAN_RUN_AMOK;}
    "warcry"                        {return RRTypes.WARCRY;}
    "can_sap"                       {return RRTypes.CAN_SAP;}
    "hardy"                         {return RRTypes.HARDY;}
    "command"                       {return RRTypes.COMMAND;}
    "very_hardy"                    {return RRTypes.VERY_HARDY;}
    "sea_faring"                    {return RRTypes.SEA_FARING;}
    "cantabrian_circle"             {return RRTypes.CANTABRIAN_CIRCLE;}
    "general_unit"                  {return RRTypes.GENERAL_UNIT;}
    "general_unit_upgrade \"marian_reforms\"" {return RRTypes.GENERAL_UNIT_UPGRADE;}
    "mercenary_unit"                {return RRTypes.MERCENARY_UNIT;}
    "druid"                         {return RRTypes.DRUID;}
    "screeching_women"              {return RRTypes.SCREECHING_WOMEN;}
    "no_custom"                     {return RRTypes.NO_CUSTOM;}
    "melee"                         {return RRTypes.MELEE;}
    "thrown"                        {return RRTypes.THROWN;}
    "siege_missile"                 {return RRTypes.SIEGE_MISSILE;}
    "simple"                        {return RRTypes.SIMPLE;}
    "other"                         {return RRTypes.OTHER;}
    "blade"                         {return RRTypes.BLADE;}
    "archery"                       {return RRTypes.ARCHERY;}
    "piercing"                      {return RRTypes.PIERCING;}
    "blunt"                         {return RRTypes.BLUNT;}
    "slashing"                      {return RRTypes.SLASHING;}
    "fire"                          {return RRTypes.FIRE;}
    "none"                          {return RRTypes.NONE;}
    "knife"                         {return RRTypes.KNIFE;}
    "mace"                          {return RRTypes.MACE;}
    "axe"                           {return RRTypes.AXE;}
    "sword"                         {return RRTypes.SWORD;}
    "spear"                         {return RRTypes.SPEAR;}
    "ap"                            {return RRTypes.AP;}
    "bp"                            {return RRTypes.BP;}
    "light_spear"                   {return RRTypes.LIGHT_SPEAR;}
    {SPEAR_BONUS_X}                 {return RRTypes.SPEAR_BONUS_X;}
    "long_pike"                     {return RRTypes.LONG_PIKE;}
    "short_pike"                    {return RRTypes.SHORT_PIKE;}
    "prec"                          {return RRTypes.PREC;}
    "thrown ap"                     {return RRTypes.THROWN_AP;}
    "launching"                     {return RRTypes.LAUNCHING;}
    "area"                          {return RRTypes.AREA;}
    "SPEAR_BONUS_X"                 {return RRTypes.SPEAR_BONUS_X;}
    "flesh"                         {return RRTypes.FLESH;}
    "leather"                       {return RRTypes.LEATHER;}
    "wood"                          {return RRTypes.WOOD;}
    "metal"                         {return RRTypes.METAL;}
    "berserker"                     {return RRTypes.BERSERKER;}
    "impetuous"                     {return RRTypes.IMPETUOUS;}
    "low"                           {return RRTypes.LOW;}
    "normal"                        {return RRTypes.NORMAL;}
    "disciplined"                   {return RRTypes.DISCIPLINED;}
    "untrained"                     {return RRTypes.UNTRAINED;}
    "trained"                       {return RRTypes.TRAINED;}
    "highly_trained"                {return RRTypes.HIGHLY_TRAINED;}
    "attributes"                    {return RRTypes.ATTRIBUTES;}
    "formation"                     {return RRTypes.FORMATION;}
    "stat_health"                   {return RRTypes.STAT_HEALTH;}
    "stat_pri_attr"                 {return RRTypes.STAT_PRI_ATTR;}
    "stat_pri"                      {return RRTypes.STAT_PRI;}
    "stat_sec"                      {return RRTypes.STAT_SEC;}
    "stat_sec_attr"                 {return RRTypes.STAT_SEC_ATTR;}
    "stat_pri_armour"               {return RRTypes.STAT_PRI_ARMOUR;}
    "stat_sec_armour"               {return RRTypes.STAT_SEC_ARMOUR;}
    "stat_heat"                     {return RRTypes.STAT_HEAT;}
    "stat_ground"                   {return RRTypes.STAT_GROUND;}
    "stat_mental"                   {return RRTypes.STAT_MENTAL;}
    "stat_charge_dist"              {return RRTypes.STAT_CHARGE_DIST;}
    "stat_fire_delay"               {return RRTypes.STAT_FIRE_DELAY;}
    "stat_food"                     {return RRTypes.STAT_FOOD;}
    "stat_cost"                     {return RRTypes.STAT_COST;}
    "don't_allow_mixed"             {return RRTypes.DONT_ALLOW_MIXED;}
    "don't_allow_regional"          {return RRTypes.DONT_ALLOW_REGIONAL;}
    "don't_allow_custom"            {return RRTypes.DONT_ALLOW_CUSTOM;}
    "tattoo_color"                  {return RRTypes.TATTOO_COLOR;}
    "hair_color"                    {return RRTypes.HAIR_COLOR;}
    "hair_style"                    {return RRTypes.HAIR_STYLE;}
    "is_female"                     {return RRTypes.IS_FEMALE;}
    "rebalance_statblock"           {return RRTypes.REBALANCE_STATBLOCK;}
    "ownership"                     {return RRTypes.OWNERSHIP;}
    "ethnicity"                     {return RRTypes.ETHNICITY;}
    {ID}                            {return RRTypes.ID;}
}
<EXPORT_DESCR_UNIT_NO_KEYWORDS>
{
    {WS}                      {return TokenType.WHITE_SPACE;}
    {COMMENT}                 {return RRTypes.COMMENT;}
    {INT}                     {return RRTypes.INT;}
    {FLOAT}                   {return RRTypes.FLOAT;}
    ","                       {return RRTypes.COMMA;}

    "dictionary"              {yybegin(EXPORT_DESCR_UNIT); return RRTypes.DICTIONARY;}
    "engine"                  {return RRTypes.ENGINE;}
    "ship"                    {return RRTypes.SHIP;}
    "animal"                  {return RRTypes.ANIMAL;}
    "mount"                   {return RRTypes.MOUNT;}
    "officer"                 {return RRTypes.OFFICER;}
    "mount_effect"            {yybegin(EXPORT_DESCR_UNIT); return RRTypes.MOUNT_EFFECT;}
    "attributes"              {yybegin(EXPORT_DESCR_UNIT); return RRTypes.ATTRIBUTES;}
    {ID}                      {return RRTypes.ID;}
}

<EXPORT_DESCR_BUILDINGS>
{
    "alias"                         {return RRTypes.ALIAS;}
    "requires"                      {return RRTypes.REQUIRES;}
    "no_building_tagged"            {return RRTypes.NO_BUILDING_TAGGED;}
    "building"                      {return RRTypes.BUILDING;}
    "queued"                        {return RRTypes.QUEUED;}
    "display_string"                {return RRTypes.DISPLAY_STRING;}
    "tag"                           {return RRTypes.TAG;}
    "icon"                          {yybegin(EXPORT_DESCR_BUILDINGS_NO_KEYWORDS);return RRTypes.ICON;}
    "plugins"                       {return RRTypes.PLUGINS;}
    "factions"                      {return RRTypes.FACTIONS;}
    "capability"                    {return RRTypes.CAPABILITY;}
    "taxable_income_bonus"          {return RRTypes.TAXABLE_INCOME_BONUS;}
    "trade_base_income_bonus"       {return RRTypes.TRADE_BASE_INCOME_BONUS;}
    "trade_fleet"                   {return RRTypes.TRADE_FLEET;}
    "farming_level"                 {return RRTypes.FARMING_LEVEL;}
    "road_level"                    {return RRTypes.ROAD_LEVEL;}
    "mine_resource"                 {return RRTypes.MINE_RESOURCE;}
    "happiness_bonus"               {return RRTypes.HAPPINESS_BONUS;}
    "law_bonus"                     {return RRTypes.LAW_BONUS;}
    "population_health_bonus"       {return RRTypes.POPULATION_HEALTH_BONUS;}
    "population_growth_bonus"       {return RRTypes.POPULATION_GROWTH_BONUS;}
    "wall_level"                    {return RRTypes.WALL_LEVEL;}
    "tower_level"                   {return RRTypes.TOWER_LEVEL;}
    "gate_strength"                 {return RRTypes.GATE_STRENGTH;}
    "gate_defences"                 {return RRTypes.GATE_DEFENCES;}
    "recruits_exp_bonus"            {return RRTypes.RECRUITS_EXP_BONUS;}
    "recruits_morale_bonus"         {return RRTypes.RECRUITS_MORALE_BONUS;}
    "weapon_simple"                 {return RRTypes.WEAPON_SIMPLE;}
    "weapon_bladed"                 {return RRTypes.WEAPON_BLADED;}
    "weapon_missile"                {return RRTypes.WEAPON_MISSILE;}
    "armour"                        {return RRTypes.ARMOUR;}
    "stage_races"                   {return RRTypes.STAGE_RACES;}
    "stage_games"                   {return RRTypes.STAGE_GAMES;}
    "agent_limit_settlement"        {return RRTypes.AGENT_LIMIT_SETTLEMENT;}
    "dummy"                         {return RRTypes.DUMMY;}
    "Capability_TraitsAndRetinue"   {return RRTypes.CAPABILITY_TRAITSANDRETINUE;}
    "ai_destruction_hint"           {return RRTypes.AI_DESTRUCTION_HINT;}
    "recruit"                       {return RRTypes.RECRUIT;}
    "agent"                         {return RRTypes.AGENT;}
    "construction"                  {return RRTypes.CONSTRUCTION;}
    "cost"                          {return RRTypes.COST;}
    "settlement_min"                {return RRTypes.SETTLEMENT_MIN;}
    "village"                       {return RRTypes.VILLAGE;}
    "town"                          {return RRTypes.TOWN;}
    "large_town"                    {return RRTypes.LARGE_TOWN;}
    "city"                          {return RRTypes.CITY;}
    "large_city"                    {return RRTypes.LARGE_CITY;}
    "huge_city"                     {return RRTypes.HUGE_CITY;}
    "diplomat"                      {return RRTypes.DIPLOMAT;}
    "spy"                           {return RRTypes.SPY;}
    "assassin"                      {return RRTypes.ASSASSIN;}
    "merchant"                      {return RRTypes.MERCHANT;}
    "upgrades"                      {return RRTypes.UPGRADES;}
    "bonus"                         {return RRTypes.BONUS;}
    "is_toggled"                    {return RRTypes.IS_TOGGLED;}
    "major_event"                   {return RRTypes.MAJOR_EVENT;}
    "resource"                      {return RRTypes.RESOURCE;}
    "hidden_resource"               {return RRTypes.HIDDEN_RESOURCE;}
    "building_present"              {return RRTypes.BUILDING_PRESENT;}
    "building_present_min_level"    {return RRTypes.BUILDING_PRESENT_MIN_LEVEL;}
    "and"                           {return RRTypes.AND;}
    "or"                            {return RRTypes.OR;}
    "not"                           {return RRTypes.NOT;}
    {ID}                            {return RRTypes.ID;}
}
<EXPORT_DESCR_BUILDINGS_NO_KEYWORDS>
{
    {WS}                      {return TokenType.WHITE_SPACE;}
    {COMMENT}                 {return RRTypes.COMMENT;}
    "levels"                  {return RRTypes.LEVELS;}
    "{"                       {yybegin(EXPORT_DESCR_BUILDINGS);return RRTypes.OCB;}
    {ID}                      {return RRTypes.ID;}
}

<EXPORT_DESCR_ANCILLARIES>
{
    "Ancillary"           {yybegin(EXPORT_DESCR_ANCILLARIES_NO_KEYWORDS); return RRTypes.ANCILLARY;}
    "Image"               {return RRTypes.IMAGE;}
    "Unique"              {return RRTypes.UNIQUE;}
    "ExcludedAncillaries" {return RRTypes.EXCLUDEDANCILLARIES;}
    "ExcludeCultures"     {return RRTypes.EXCLUDECULTURES;}
    "Description"         {return RRTypes.DESCRIPTION;}
    "EffectsDescription"  {return RRTypes.EFFECTSDESCRIPTION;}
    "Effect"              {return RRTypes.EFFECT;}
    "FakeEffect"          {return RRTypes.FAKEEFFECT;}
    "Hidden"              {return RRTypes.HIDDEN;}
    "ShowStats"           {return RRTypes.SHOWSTATS;}
    "Trigger"             {return RRTypes.TRIGGER;}
    "WhenToTest"          {return RRTypes.WHENTOTEST;}
    "Condition"           {yybegin(CONDITIONS); return RRTypes.CONDITION;}
    "AcquireAncillary"    {yybegin(EXPORT_DESCR_ANCILLARIES_NO_KEYWORDS); return RRTypes.ACQUIREANCILLARY;}
    "RemoveAncillary"     {return RRTypes.REMOVEANCILLARY;}
    "chance"              {return RRTypes.CHANCE_LOWERCASE;}
    "and"                 {return RRTypes.AND;}
    "not"                 {return RRTypes.NOT;}
    {ID}                  {return RRTypes.ID;}
}
<EXPORT_DESCR_ANCILLARIES_NO_KEYWORDS>
{
    {WS}                      {return TokenType.WHITE_SPACE;}
    {COMMENT}                 {return RRTypes.COMMENT;}
    "Image"                   {yybegin(EXPORT_DESCR_ANCILLARIES); return RRTypes.IMAGE;}
    "Hidden"                  {yybegin(EXPORT_DESCR_ANCILLARIES); return RRTypes.HIDDEN;}
    "ShowStats"               {yybegin(EXPORT_DESCR_ANCILLARIES); return RRTypes.SHOWSTATS;}
    "chance"                  {yybegin(EXPORT_DESCR_ANCILLARIES); return RRTypes.CHANCE_LOWERCASE;}
    {ID}                      {return RRTypes.ID;}
}

<EXPORT_DESCR_TRAITS>
{
    "Trait"               {return RRTypes.TRAIT;}
    "Characters"          {return RRTypes.CHARACTERS;}
    "Hidden"              {return RRTypes.HIDDEN;}
    "NoGoingBackLevel"    {return RRTypes.NOGOINGBACKLEVEL;}
    "ExcludeCultures"     {return RRTypes.EXCLUDECULTURES;}
    "AntiTraits"          {return RRTypes.ANTITRAITS;}
    "Description"         {return RRTypes.DESCRIPTION;}
    "EffectsDescription"  {return RRTypes.EFFECTSDESCRIPTION;}
    "GainMessage"         {return RRTypes.GAINMESSAGE;}
    "Epithet"             {return RRTypes.EPITHET;}
    "Level"               {return RRTypes.LEVEL;}
    "WhenToTest"          {return RRTypes.WHENTOTEST;}
    "LoseMessage"         {return RRTypes.LOSEMESSAGE;}
    "Threshold"           {return RRTypes.THRESHOLD;}
    "Effect"              {return RRTypes.EFFECT;}
    "Affects"             {return RRTypes.AFFECTS;}
    "Trigger"             {return RRTypes.TRIGGER;}
    "Condition"           {yybegin(CONDITIONS); return RRTypes.CONDITION;}
    "Chance"              {return RRTypes.CHANCE;}
    "Lose"                {return RRTypes.LOSE;}
    "and"                 {return RRTypes.AND;}
    "not"                 {return RRTypes.NOT;}
    "&&"                  {return RRTypes.AMBERSANDS;}
    {ID}                  {return RRTypes.ID;}
}

<EXPORT_DESCR_MERCENARIES>
{
    "pool"             {return RRTypes.POOL;}
    "-"                {return RRTypes.DASH;}
    "regions"          {return RRTypes.REGIONS;}
    "unit"             {yybegin(EXPORT_DESCR_MERCENARIES_NO_KEYWORDS); return RRTypes.UNIT;}
    "exp"              {return RRTypes.EXP;}
    "cost"             {return RRTypes.COST;}
    "replenish"        {return RRTypes.REPLENISH;}
    "max"              {return RRTypes.MAX;}
    "initial"          {return RRTypes.INITIAL;}
    {ID}               {return RRTypes.ID;}
}
<EXPORT_DESCR_MERCENARIES_NO_KEYWORDS>
{
    {WS}                      {return TokenType.WHITE_SPACE;}
    {COMMENT}                 {return RRTypes.COMMENT;}
    ","                       {return RRTypes.COMMA;}
    "exp"                     {yybegin(EXPORT_DESCR_MERCENARIES); return RRTypes.EXP;}
    {ID}                      {return RRTypes.ID;}
}

<DESCR_NAMES>
{
    "faction"      {return RRTypes.FACTION;}
    "characters"   {return RRTypes.CHARACTERS_LC;}
    "surnames"     {return RRTypes.SURNAMES;}
    "women"        {return RRTypes.WOMEN;}
    {ID}           {return RRTypes.ID;}
}

<DESCR_UNIT_VARIATION>
{
    "male"              {return RRTypes.MALE;}
    "female"            {return RRTypes.FEMALE;}
    "ethnicities"       {return RRTypes.ETHNICITIES;}
    "hair_styles"       {return RRTypes.HAIR_STYLES;}
    "female_hair"       {return RRTypes.FEMALE_HAIR;}
    "beard_styles"      {return RRTypes.BEARD_STYLES;}
    "hair_color"        {return RRTypes.HAIR_COLOR;}
    "aged_hair_color"   {return RRTypes.AGED_HAIR_COLOR;}
    "region"            {return RRTypes.REGION;}
    "variant"           {return RRTypes.VARIANT;}
    "ethnicity"         {return RRTypes.ETHNICITY;}
    "distribution"      {return RRTypes.DISTRIBUTION;}
    {ID}                {return RRTypes.ID;}
}

<FERAL_DESCR_AI_PERSONALITY>
{
    {WS}                         {return TokenType.WHITE_SPACE;}
    {COMMENT}                    {return RRTypes.COMMENT;}
    {INT}                        {return RRTypes.INT;}
    "building_priority"          {return RRTypes.BUILDING_PRIORITY;}
    "population_growth_bonus"    {return RRTypes.POPULATION_GROWTH_BONUS;}
    "population_loyalty_bonus"   {return RRTypes.POPULATION_LOYALTY_BONUS;}
    "population_health_bonus"    {return RRTypes.POPULATION_HEALTH_BONUS;}
    "trade_base_income_bonus"    {return RRTypes.TRADE_BASE_INCOME_BONUS;}
    "trade_level_bonus"          {return RRTypes.TRADE_LEVEL_BONUS;}
    "trade_fleet"                {return RRTypes.TRADE_FLEET;}
    "taxable_income_bonus"       {return RRTypes.TAXABLE_INCOME_BONUS;}
    "mine_resource_level"        {return RRTypes.MINE_RESOURCE_LEVEL;}
    "farming_level"              {return RRTypes.FARMING_LEVEL;}
    "road_level"                 {return RRTypes.ROAD_LEVEL;}
    "gate_strength"              {return RRTypes.GATE_STRENGTH;}
    "gate_defenses"              {return RRTypes.GATE_DEFENSES;}
    "wall_level"                 {return RRTypes.WALL_LEVEL;}
    "tower_level"                {return RRTypes.TOWER_LEVEL;}
    "stage_games"                {return RRTypes.STAGE_GAMES;}
    "stage_races"                {return RRTypes.STAGE_RACES;}
    "weapon_bladed"              {return RRTypes.WEAPON_BLADED;}
    "weapon_missile"             {return RRTypes.WEAPON_MISSILE;}
    "armour"                     {return RRTypes.ARMOUR;}
    "population_fire_risk_bonus" {return RRTypes.POPULATION_FIRE_RISK_BONUS;}
    "bodyguard"                  {return RRTypes.BODYGUARD;}
    "recruits_morale_bonus"      {return RRTypes.RECRUITS_MORALE_BONUS;}
    "recruits_experience_bonus"  {return RRTypes.RECRUITS_EXPERIENCE_BONUS;}
    "happiness_bonus"            {return RRTypes.HAPPINESS_BONUS;}
    "law_bonus"                  {return RRTypes.LAW_BONUS;}
    "military_priority"          {return RRTypes.MILITARY_PRIORITY;}
    "mass"                       {return RRTypes.MASS;}
    "infantry_light"             {return RRTypes.INFANTRY_LIGHT;}
    "infantry_heavy"             {return RRTypes.INFANTRY_HEAVY;}
    "infantry_missile"           {return RRTypes.INFANTRY_MISSILE;}
    "infantry_spearmen"          {return RRTypes.INFANTRY_SPEARMEN;}
    "cavalry_light"              {return RRTypes.CAVALRY_LIGHT;}
    "cavalry_heavy"              {return RRTypes.CAVALRY_HEAVY;}
    "cavalry_missile"            {return RRTypes.CAVALRY_MISSILE;}
    "siege_artillery"            {return RRTypes.SIEGE_ARTILLERY;}
    "special"                    {return RRTypes.SPECIAL;}
    "sally_agression"            {return RRTypes.SALLY_AGRESSION;}
    "sally_desperate"            {return RRTypes.SALLY_DESPERATE;}
    "attack_risk_taker"          {return RRTypes.ATTACK_RISK_TAKER;}
    "subterfuge_risk_taker"      {return RRTypes.SUBTERFUGE_RISK_TAKER;}
    "diplomatic_priority"        {return RRTypes.DIPLOMATIC_PRIORITY;}
    "aggresiveness"              {return RRTypes.AGGRESIVENESS;}
    "personality"                {return RRTypes.PERSONALITY;}
    {ID}                         {return RRTypes.ID;}
}

<DESCR_FACTION_GROUPS>
{
    "group"      {return RRTypes.GROUP;}
    "faction"    {return RRTypes.FACTION;}
    {ID}         {return RRTypes.ID;}
}

<FERAL_DESCR_PORTRAITS_VARIATION>
{
    "old"        {return RRTypes.OLD;}
    "civilian"   {return RRTypes.CIVILIAN;}
    "rogue"      {return RRTypes.ROGUE;}
    "young"      {return RRTypes.YOUNG;}
    {ID}         {return RRTypes.ID;}
}

<EXPORT_BUILDINGS>
{
    {WS}         {return TokenType.WHITE_SPACE;}
    {COMMENT}    {return RRTypes.COMMENT;}
    "{"          {return RRTypes.OCB;}
    "}"          {yybegin(EXPORT_BUILDINGS_NO_KEYWORDS);return RRTypes.CCB;}
    {ID}         {return RRTypes.ID;}
}
<EXPORT_BUILDINGS_NO_KEYWORDS>{
    {WS}         {return TokenType.WHITE_SPACE;}
    {COMMENT}    {return RRTypes.COMMENT;}
    "{"          {yybegin(EXPORT_BUILDINGS);return RRTypes.OCB;}

    [^\t{\r\n]* {return RRTypes.STRING;}
}

<CONDITIONS>
{
    {WS}                                              {return TokenType.WHITE_SPACE;}
    {COMMENT}                                         {return RRTypes.COMMENT;}
    {INT}                                             {return RRTypes.INT;}
    {FLOAT}                                           {return RRTypes.FLOAT;}
    ","                                               {return RRTypes.COMMA;}
    "="                                               {return RRTypes.EQUALS;}
    ">"                                               {return RRTypes.LARGER;}
    ">="                                              {return RRTypes.LARGER_OR_EQUAL;}
    "<"                                               {return RRTypes.SMALLER;}
    "<="                                              {return RRTypes.SMALLER_OR_EQUAL;}

    // Returns
    "AcquireAncillary"                                {yybegin(EXPORT_DESCR_ANCILLARIES); return RRTypes.ACQUIREANCILLARY;}
    "RemoveAncillary"                                 {yybegin(EXPORT_DESCR_ANCILLARIES); return RRTypes.REMOVEANCILLARY;}
    "Affects"                                         {yybegin(EXPORT_DESCR_TRAITS); return RRTypes.AFFECTS;}

    // conditions
    "I_InBattle"                                      {return RRTypes.I_INBATTLE;}
    "WonBattle"                                       {return RRTypes.WONBATTLE;}
    "I_WonBattle"                                     {return RRTypes.I_WONBATTLE;}
    "Routs"                                           {return RRTypes.ROUTS;}
    "Ally_Routs"                                      {return RRTypes.ALLY_ROUTS;}
    "GeneralHPLostRatioinBattle"                      {return RRTypes.GENERALHPLOSTRATIOINBATTLE;}
    "GeneralNumKillsInBattle"                         {return RRTypes.GENERALNUMKILLSINBATTLE;}
    "GeneralFoughtInCombat"                           {return RRTypes.GENERALFOUGHTINCOMBAT;}
    "PercentageOfArmyKilled"                          {return RRTypes.PERCENTAGEOFARMYKILLED;}
    "I_PercentageOfArmyKilled"                        {return RRTypes.I_PERCENTAGEOFARMYKILLED;}
    "PercentageEnemyKilled"                           {return RRTypes.PERCENTAGEENEMYKILLED;}
    "PercentageBodyguardKilled"                       {return RRTypes.PERCENTAGEBODYGUARDKILLED;}
    "PercentageRoutedOffField"                        {return RRTypes.PERCENTAGEROUTEDOFFFIELD;}
    "NumKilledGenerals"                               {return RRTypes.NUMKILLEDGENERALS;}
    "PercentageUnitCategory"                          {return RRTypes.PERCENTAGEUNITCATEGORY;}
    "NumFriendsInBattle"                              {return RRTypes.NUMFRIENDSINBATTLE;}
    "NumEnemiesInBattle"                              {return RRTypes.NUMENEMIESINBATTLE;}
    "GeneralFoughtFaction"                            {return RRTypes.GENERALFOUGHTFACTION;}
    "GeneralFoughtCulture"                            {return RRTypes.GENERALFOUGHTCULTURE;}
    "I_ConflictType"                                  {return RRTypes.I_CONFLICTTYPE;}
    "IsNightBattle"                                   {return RRTypes.ISNIGHTBATTLE;}
    "BattleSuccess"                                   {return RRTypes.BATTLESUCCESS;}
    "BattleOdds"                                      {return RRTypes.BATTLEODDS;}
    "WasAttacker"                                     {return RRTypes.WASATTACKER;}
    "I_BattleAiAttacking"                             {return RRTypes.I_BATTLEAIATTACKING;}
    "I_BattleAiAttackingSettlement"                   {return RRTypes.I_BATTLEAIATTACKINGSETTLEMENT;}
    "I_BattleAiDefendingSettlement"                   {return RRTypes.I_BATTLEAIDEFENDINGSETTLEMENT;}
    "I_BattleAiDefendingHill"                         {return RRTypes.I_BATTLEAIDEFENDINGHILL;}
    "I_BattleAiDefendingCrossing"                     {return RRTypes.I_BATTLEAIDEFENDINGCROSSING;}
    "I_BattleAiScouting"                              {return RRTypes.I_BATTLEAISCOUTING;}
    "I_BattleIsRiverBattle"                           {return RRTypes.I_BATTLEISRIVERBATTLE;}
    "I_BattleIsSiegeBattle"                           {return RRTypes.I_BATTLEISSIEGEBATTLE;}
    "I_BattleIsSallyOutBattle"                        {return RRTypes.I_BATTLEISSALLYOUTBATTLE;}
    "I_BattleIsFortBattle"                            {return RRTypes.I_BATTLEISFORTBATTLE;}
    "I_BattleAttackerNumSiegeEngines"                 {return RRTypes.I_BATTLEATTACKERNUMSIEGEENGINES;}
    "I_BattleAttackerNumArtilleryCanPenetrateWalls"   {return RRTypes.I_BATTLEATTACKERNUMARTILLERYCANPENETRATEWALLS;}
    "I_BattleDefenderNumNonMissileUnitsOnWalls"       {return RRTypes.I_BATTLEDEFENDERNUMNONMISSILEUNITSONWALLS;}
    "I_BattleDefenderNumMissileUnitsOnWalls"          {return RRTypes.I_BATTLEDEFENDERNUMMISSILEUNITSONWALLS;}
    "I_BattleSettlementWallsBreached"                 {return RRTypes.I_BATTLESETTLEMENTWALLSBREACHED;}
    "I_BattleSettlementGateDestroyed"                 {return RRTypes.I_BATTLESETTLEMENTGATEDESTROYED;}
    "I_BattleSettlementTowerDefence"                  {return RRTypes.I_BATTLESETTLEMENTTOWERDEFENCE;}
    "I_BattleSettlementGateDefence"                   {return RRTypes.I_BATTLESETTLEMENTGATEDEFENCE;}
    "I_BattleSettlementFortificationLevel"            {return RRTypes.I_BATTLESETTLEMENTFORTIFICATIONLEVEL;}
    "BattleBuildingType"                              {return RRTypes.BATTLEBUILDINGTYPE;}
    "I_BattleSettlementGateStrength"                  {return RRTypes.I_BATTLESETTLEMENTGATESTRENGTH;}
    "I_BattleNumberOfRiverCrossings"                  {return RRTypes.I_BATTLENUMBEROFRIVERCROSSINGS;}
    "BattlePlayerUnitClass"                           {return RRTypes.BATTLEPLAYERUNITCLASS;}
    "BattleEnemyUnitClass"                            {return RRTypes.BATTLEENEMYUNITCLASS;}
    "BattlePlayerUnitCategory"                        {return RRTypes.BATTLEPLAYERUNITCATEGORY;}
    "BattleEnemyUnitCategory"                         {return RRTypes.BATTLEENEMYUNITCATEGORY;}
    "BattlePlayerUnitSiegeEngineClass"                {return RRTypes.BATTLEPLAYERUNITSIEGEENGINECLASS;}
    "BattleEnemyUnitSiegeEngineClass"                 {return RRTypes.BATTLEENEMYUNITSIEGEENGINECLASS;}
    "BattlePlayerUnitOnWalls"                         {return RRTypes.BATTLEPLAYERUNITONWALLS;}
    "BattleEnemyUnitOnWalls"                          {return RRTypes.BATTLEENEMYUNITONWALLS;}
    "BattlePlayerCurrentFormation"                    {return RRTypes.BATTLEPLAYERCURRENTFORMATION;}
    "BattleEnemyCurrentFormation"                     {return RRTypes.BATTLEENEMYCURRENTFORMATION;}
    "BattlePlayerUnitCloseFormation"                  {return RRTypes.BATTLEPLAYERUNITCLOSEFORMATION;}
    "BattleEnemyUnitCloseFormation"                   {return RRTypes.BATTLEENEMYUNITCLOSEFORMATION;}
    "BattlePlayerUnitSpecialAbilitySupported"         {return RRTypes.BATTLEPLAYERUNITSPECIALABILITYSUPPORTED;}
    "BattleSelectedPlayerUnitSpecialAbilitySupported" {return RRTypes.BATTLESELECTEDPLAYERUNITSPECIALABILITYSUPPORTED;}
    "BattleEnemyUnitSpecialAbilitySupported"          {return RRTypes.BATTLEENEMYUNITSPECIALABILITYSUPPORTED;}
    "BattlePlayerUnitSpecialAbilityActive"            {return RRTypes.BATTLEPLAYERUNITSPECIALABILITYACTIVE;}
    "BattleEnemyUnitSpecialAbilityActive"             {return RRTypes.BATTLEENEMYUNITSPECIALABILITYACTIVE;}
    "BattlePlayerMountClass"                          {return RRTypes.BATTLEPLAYERMOUNTCLASS;}
    "BattleEnemyMountClass"                           {return RRTypes.BATTLEENEMYMOUNTCLASS;}
    "BattlePlayerUnitMeleeStrength"                   {return RRTypes.BATTLEPLAYERUNITMELEESTRENGTH;}
    "BattleEnemyUnitMeleeStrength"                    {return RRTypes.BATTLEENEMYUNITMELEESTRENGTH;}
    "BattlePlayerUnitMissileStrength"                 {return RRTypes.BATTLEPLAYERUNITMISSILESTRENGTH;}
    "BattleEnemyUnitMissileStrength"                  {return RRTypes.BATTLEENEMYUNITMISSILESTRENGTH;}
    "BattlePlayerUnitSpecialFormation"                {return RRTypes.BATTLEPLAYERUNITSPECIALFORMATION;}
    "BattleEnemyUnitSpecialFormation"                 {return RRTypes.BATTLEENEMYUNITSPECIALFORMATION;}
    "BattlePlayerUnitEngaged"                         {return RRTypes.BATTLEPLAYERUNITENGAGED;}
    "BattleEnemyUnitEngaged"                          {return RRTypes.BATTLEENEMYUNITENGAGED;}
    "BattlePlayerActionStatus"                        {return RRTypes.BATTLEPLAYERACTIONSTATUS;}
    "BattleEnemyActionStatus"                         {return RRTypes.BATTLEENEMYACTIONSTATUS;}
    "BattlePlayerUnitMovingFast"                      {return RRTypes.BATTLEPLAYERUNITMOVINGFAST;}
    "BattleEnemyUnitMovingFast"                       {return RRTypes.BATTLEENEMYUNITMOVINGFAST;}
    "BattleRangeOfAttack"                             {return RRTypes.BATTLERANGEOFATTACK;}
    "BattleDirectionOfAttack"                         {return RRTypes.BATTLEDIRECTIONOFATTACK;}
    "BattleIsMeleeAttack"                             {return RRTypes.BATTLEISMELEEATTACK;}
    "I_BattlePlayerArmyPercentageOfUnitClass"         {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGEOFUNITCLASS;}
    "I_BattleEnemyArmyPercentageOfUnitClass"          {return RRTypes.I_BATTLEENEMYARMYPERCENTAGEOFUNITCLASS;}
    "I_BattlePlayerArmyPercentageOfUnitCategory"      {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGEOFUNITCATEGORY;}
    "I_BattleEnemyArmyPercentageOfUnitCategory"       {return RRTypes.I_BATTLEENEMYARMYPERCENTAGEOFUNITCATEGORY;}
    "I_BattlePlayerArmyPercentageOfMountClass"        {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGEOFMOUNTCLASS;}
    "I_BattleEnemyArmyPercentageOfMountClass"         {return RRTypes.I_BATTLEENEMYARMYPERCENTAGEOFMOUNTCLASS;}
    "I_BattlePlayerArmyPercentageOfClassAndCategory"  {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGEOFCLASSANDCATEGORY;}
    "I_BattleEnemyArmyPercentageOfClassAndCategory"   {return RRTypes.I_BATTLEENEMYARMYPERCENTAGEOFCLASSANDCATEGORY;}
    "I_BattlePlayerArmyPercentageOfSpecialAbility"    {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGEOFSPECIALABILITY;}
    "I_BattleEnemyArmyPercentageOfSpecialAbility"     {return RRTypes.I_BATTLEENEMYARMYPERCENTAGEOFSPECIALABILITY;}
    "I_BattlePlayerArmyPercentageCanHide"             {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGECANHIDE;}
    "I_BattleEnemyArmyPercentageCanHide"              {return RRTypes.I_BATTLEENEMYARMYPERCENTAGECANHIDE;}
    "I_BattlePlayerArmyPercentageCanSwim"             {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGECANSWIM;}
    "I_BattleEnemyArmyPercentageCanSwim"              {return RRTypes.I_BATTLEENEMYARMYPERCENTAGECANSWIM;}
    "I_BattlePlayerArmyIsAttacker"                    {return RRTypes.I_BATTLEPLAYERARMYISATTACKER;}
    "I_BattlePlayerAllianceOddsInFavour"              {return RRTypes.I_BATTLEPLAYERALLIANCEODDSINFAVOUR;}
    "I_BattlePlayerAllianceOddsAgainst"               {return RRTypes.I_BATTLEPLAYERALLIANCEODDSAGAINST;}
    "TotalSiegeWeapons"                               {return RRTypes.TOTALSIEGEWEAPONS;}
    "I_BattleStarted"                                 {return RRTypes.I_BATTLESTARTED;}
    "I_BattleFinished"                                {return RRTypes.I_BATTLEFINISHED;}
    "I_BattleEnd"                                     {return RRTypes.I_BATTLEEND;}
    "I_BattleEndPending"                              {return RRTypes.I_BATTLEENDPENDING;}
    "I_IsUnitMoveFastSet"                             {return RRTypes.I_ISUNITMOVEFASTSET;}
    "I_IsUnitMoving"                                  {return RRTypes.I_ISUNITMOVING;}
    "I_IsUnitIdle"                                    {return RRTypes.I_ISUNITIDLE;}
    "I_IsUnitRouting"                                 {return RRTypes.I_ISUNITROUTING;}
    "I_IsUnitUnderFire"                               {return RRTypes.I_ISUNITUNDERFIRE;}
    "I_IsUnitEngaged"                                 {return RRTypes.I_ISUNITENGAGED;}
    "I_IsUnitEngagedWithUnit"                         {return RRTypes.I_ISUNITENGAGEDWITHUNIT;}
    "I_UnitFormation"                                 {return RRTypes.I_UNITFORMATION;}
    "I_PercentageUnitKilled"                          {return RRTypes.I_PERCENTAGEUNITKILLED;}
    "I_UnitPercentageAmmoLeft"                        {return RRTypes.I_UNITPERCENTAGEAMMOLEFT;}
    "I_UnitDistanceFromPosition"                      {return RRTypes.I_UNITDISTANCEFROMPOSITION;}
    "I_UnitDistanceFromLine"                          {return RRTypes.I_UNITDISTANCEFROMLINE;}
    "I_UnitDistanceFromUnit"                          {return RRTypes.I_UNITDISTANCEFROMUNIT;}
    "I_UnitInRangeOfUnit"                             {return RRTypes.I_UNITINRANGEOFUNIT;}
    "I_UnitDestroyed"                                 {return RRTypes.I_UNITDESTROYED;}
    "I_UnitEnemyUnitInRadius"                         {return RRTypes.I_UNITENEMYUNITINRADIUS;}
    "I_IsUnitGroupMoving"                             {return RRTypes.I_ISUNITGROUPMOVING;}
    "I_IsUnitGroupEngaged"                            {return RRTypes.I_ISUNITGROUPENGAGED;}
    "I_IsUnitGroupIdle"                               {return RRTypes.I_ISUNITGROUPIDLE;}
    "I_IsUnitGroupDestroyed"                          {return RRTypes.I_ISUNITGROUPDESTROYED;}
    "I_PercentageUnitGroupKilled"                     {return RRTypes.I_PERCENTAGEUNITGROUPKILLED;}
    "I_UnitGroupFormation"                            {return RRTypes.I_UNITGROUPFORMATION;}
    "I_UnitGroupDistanceFromPosition"                 {return RRTypes.I_UNITGROUPDISTANCEFROMPOSITION;}
    "I_UnitGroupDistanceFromGroup"                    {return RRTypes.I_UNITGROUPDISTANCEFROMGROUP;}
    "I_UnitGroupInRangeOfUnit"                        {return RRTypes.I_UNITGROUPINRANGEOFUNIT;}
    "I_UnitInRangeOfUnitGroup"                        {return RRTypes.I_UNITINRANGEOFUNITGROUP;}
    "I_UnitGroupInRangeOfUnitGroup"                   {return RRTypes.I_UNITGROUPINRANGEOFUNITGROUP;}
    "I_PlayerInRangeOfUnitGroup"                      {return RRTypes.I_PLAYERINRANGEOFUNITGROUP;}
    "I_PlayerInRangeOfUnit"                           {return RRTypes.I_PLAYERINRANGEOFUNIT;}
    "I_UnitTypeSelected"                              {return RRTypes.I_UNITTYPESELECTED;}
    "UnitType"                                        {return RRTypes.UNITTYPE;}
    "I_UnitSelected"                                  {return RRTypes.I_UNITSELECTED;}
    "I_MultipleUnitsSelected"                         {return RRTypes.I_MULTIPLEUNITSSELECTED;}
    "I_SpecificUnitsSelected"                         {return RRTypes.I_SPECIFICUNITSSELECTED;}
    "I_IsCameraZoomingToUnit"                         {return RRTypes.I_ISCAMERAZOOMINGTOUNIT;}
    "I_BattleEnemyArmyPercentageOfMatchingUnits"      {return RRTypes.I_BATTLEENEMYARMYPERCENTAGEOFMATCHINGUNITS;}
    "I_BattleEnemyArmyNumberOfMatchingUnits"          {return RRTypes.I_BATTLEENEMYARMYNUMBEROFMATCHINGUNITS;}
    "I_BattlePlayerArmyPercentageOfMatchingUnits"     {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGEOFMATCHINGUNITS;}
    "I_BattlePlayerArmyNumberOfMatchingUnits"         {return RRTypes.I_BATTLEPLAYERARMYNUMBEROFMATCHINGUNITS;}
    "LocalPlayerHasManualReinforcements"              {return RRTypes.LOCALPLAYERHASMANUALREINFORCEMENTS;}
    "LocalPlayerHasAIReinforcements"                  {return RRTypes.LOCALPLAYERHASAIREINFORCEMENTS;}
    "Trait"                                           {return RRTypes.TRAIT;}
    "HasAncillary"                                    {return RRTypes.HASANCILLARY;}
    "FatherTrait"                                     {return RRTypes.FATHERTRAIT;}
    "FactionLeaderTrait"                              {return RRTypes.FACTIONLEADERTRAIT;}
    "Attribute"                                       {return RRTypes.ATTRIBUTE;}
    "RemainingMPPercentage"                           {return RRTypes.REMAININGMPPERCENTAGE;}
    "I_RemainingMPPercentage"                         {return RRTypes.I_REMAININGMPPERCENTAGE;}
    "I_CharacterCanMove"                              {return RRTypes.I_CHARACTERCANMOVE;}
    "NoActionThisTurn"                                {return RRTypes.NOACTIONTHISTURN;}
    "AgentType"                                       {return RRTypes.AGENTTYPE;}
    "TrainedAgentType"                                {return RRTypes.TRAINEDAGENTTYPE;}
    "DisasterType"                                    {return RRTypes.DISASTERTYPE;}
    "CultureType"                                     {return RRTypes.CULTURETYPE;}
    "OriginalFactionType"                             {return RRTypes.ORIGINALFACTIONTYPE;}
    "OriginalCultureType"                             {return RRTypes.ORIGINALCULTURETYPE;}
    "IsGeneral"                                       {return RRTypes.ISGENERAL;}
    "IsAdmiral"                                       {return RRTypes.ISADMIRAL;}
    "RemasteredEducation"                             {return RRTypes.REMASTEREDEDUCATION;}
    "EndedInSettlement"                               {return RRTypes.ENDEDINSETTLEMENT;}
    "IsFactionLeader"                                 {return RRTypes.ISFACTIONLEADER;}
    "IsFactionHeir"                                   {return RRTypes.ISFACTIONHEIR;}
    "IsMarried"                                       {return RRTypes.ISMARRIED;}
    "AtSea"                                           {return RRTypes.ATSEA;}
    "InEnemyLands"                                    {return RRTypes.INENEMYLANDS;}
    "InBarbarianLands"                                {return RRTypes.INBARBARIANLANDS;}
    "InUncivilisedLands"                              {return RRTypes.INUNCIVILISEDLANDS;}
    "IsBesieging"                                     {return RRTypes.ISBESIEGING;}
    "IsUnderSiege"                                    {return RRTypes.ISUNDERSIEGE;}
    "I_WithdrawsBeforeBattle"                         {return RRTypes.I_WITHDRAWSBEFOREBATTLE;}
    "EndedInEnemyZOC"                                 {return RRTypes.ENDEDINENEMYZOC;}
    "AdviseAction"                                    {return RRTypes.ADVISEACTION;}
    "I_CharacterTypeNearCharacterType"                {return RRTypes.I_CHARACTERTYPENEARCHARACTERTYPE;}
    "I_CharacterTypeNearTile"                         {return RRTypes.I_CHARACTERTYPENEARTILE;}
    "I_CharacterNameNearTile"                         {return RRTypes.I_CHARACTERNAMENEARTILE;}
    "TradingResource"                                 {return RRTypes.TRADINGRESOURCE;}
    "RegionTradingResource"                           {return RRTypes.REGIONTRADINGRESOURCE;}
    "TradingExotic"                                   {return RRTypes.TRADINGEXOTIC;}
    "DistanceCapital"                                 {return RRTypes.DISTANCECAPITAL;}
    "DistanceHome"                                    {return RRTypes.DISTANCEHOME;}
    "TradingGroup"                                    {return RRTypes.TRADINGGROUP;}
    "RegionMerchantCount"                             {return RRTypes.REGIONMERCHANTCOUNT;}
    "PreciousMineCount"                               {return RRTypes.PRECIOUSMINECOUNT;}
    "IsFromFaction"                                   {return RRTypes.ISFROMFACTION;}
    "NightBattlesEnabled"                             {return RRTypes.NIGHTBATTLESENABLED;}
    "HasOffice"                                       {return RRTypes.HASOFFICE;}
    "FactionType"                                     {return RRTypes.FACTIONTYPE;}
    "TargetFactionType"                               {return RRTypes.TARGETFACTIONTYPE;}
    "FactionCultureType"                              {return RRTypes.FACTIONCULTURETYPE;}
    "TargetFactionCultureType"                        {return RRTypes.TARGETFACTIONCULTURETYPE;}
    "TrainedUnitCategory"                             {return RRTypes.TRAINEDUNITCATEGORY;}
    "UnitCategory"                                    {return RRTypes.UNITCATEGORY;}
    "SenateMissionTimeRemaining"                      {return RRTypes.SENATEMISSIONTIMEREMAINING;}
    "MedianTaxLevel"                                  {return RRTypes.MEDIANTAXLEVEL;}
    "ModeTaxLevel"                                    {return RRTypes.MODETAXLEVEL;}
    "I_ModeTaxLevel"                                  {return RRTypes.I_MODETAXLEVEL;}
    "MissionSuccessLevel"                             {return RRTypes.MISSIONSUCCESSLEVEL;}
    "MissionSucceeded"                                {return RRTypes.MISSIONSUCCEEDED;}
    "MissionFactionTargetType"                        {return RRTypes.MISSIONFACTIONTARGETTYPE;}
    "MissionCultureTargetType"                        {return RRTypes.MISSIONCULTURETARGETTYPE;}
    "DiplomaticStanceFromCharacter"                   {return RRTypes.DIPLOMATICSTANCEFROMCHARACTER;}
    "DiplomaticStanceFromFaction"                     {return RRTypes.DIPLOMATICSTANCEFROMFACTION;}
    "FactionHasAllies"                                {return RRTypes.FACTIONHASALLIES;}
    "LosingMoney"                                     {return RRTypes.LOSINGMONEY;}
    "I_LosingMoney"                                   {return RRTypes.I_LOSINGMONEY;}
    "FactionIsAlive"                                  {return RRTypes.FACTIONISALIVE;}
    "SupportCostsPercentage"                          {return RRTypes.SUPPORTCOSTSPERCENTAGE;}
    "Treasury"                                        {return RRTypes.TREASURY;}
    "OnAWarFooting"                                   {return RRTypes.ONAWARFOOTING;}
    "I_FactionBesieging"                              {return RRTypes.I_FACTIONBESIEGING;}
    "I_FactionBesieged"                               {return RRTypes.I_FACTIONBESIEGED;}
    "I_NumberOfSettlements"                           {return RRTypes.I_NUMBEROFSETTLEMENTS;}
    "I_NumberOfHeirs"                                 {return RRTypes.I_NUMBEROFHEIRS;}
    "I_FactionNearTile"                               {return RRTypes.I_FACTIONNEARTILE;}
    "SettlementsTaken"                                {return RRTypes.SETTLEMENTSTAKEN;}
    "BattlesFought"                                   {return RRTypes.BATTLESFOUGHT;}
    "BattlesWon"                                      {return RRTypes.BATTLESWON;}
    "BattlesLost"                                     {return RRTypes.BATTLESLOST;}
    "DefensiveSiegesFought"                           {return RRTypes.DEFENSIVESIEGESFOUGHT;}
    "DefensiveSiegesWon"                              {return RRTypes.DEFENSIVESIEGESWON;}
    "OffensiveSiegesFought"                           {return RRTypes.OFFENSIVESIEGESFOUGHT;}
    "OffensiveSiegesWon"                              {return RRTypes.OFFENSIVESIEGESWON;}
    "FactionwideAncillaryExists"                      {return RRTypes.FACTIONWIDEANCILLARYEXISTS;}
    "IsAlly"                                          {return RRTypes.ISALLY;}
    "IsProtectorate"                                  {return RRTypes.ISPROTECTORATE;}
    "IsProtector"                                     {return RRTypes.ISPROTECTOR;}
    "IsSameSuperfaction"                              {return RRTypes.ISSAMESUPERFACTION;}
    "LocalPlayerBattlesFought"                        {return RRTypes.LOCALPLAYERBATTLESFOUGHT;}
    "Toggled"                                         {return RRTypes.TOGGLED;}
    "MajorEventActive"                                {return RRTypes.MAJOREVENTACTIVE;}
    "RandomPercent"                                   {return RRTypes.RANDOMPERCENT;}
    "TrueCondition"                                   {return RRTypes.TRUECONDITION;}
    "WorldwideAncillaryExists"                        {return RRTypes.WORLDWIDEANCILLARYEXISTS;}
    "I_IsTutorialEnabled"                             {return RRTypes.I_ISTUTORIALENABLED;}
    "I_IsPlayerTurn"                                  {return RRTypes.I_ISPLAYERTURN;}
    "ConstructionItemClicked"                         {return RRTypes.CONSTRUCTIONITEMCLICKED;}
    "RecruitmentItemClicked"                          {return RRTypes.RECRUITMENTITEMCLICKED;}
    "CharacterName"                                   {return RRTypes.CHARACTERNAME;}
    "ScrollDidOpen"                                   {return RRTypes.SCROLLDIDOPEN;}
    "UnitHasRouted"                                   {return RRTypes.UNITHASROUTED;}
    "BattleUnitActionStatus"                          {return RRTypes.BATTLEUNITACTIONSTATUS;}
    "I_AmountOfUnitInSettlement"                      {return RRTypes.I_AMOUNTOFUNITINSETTLEMENT;}
    "I_UnitCardSelected"                              {return RRTypes.I_UNITCARDSELECTED;}
    "I_CompareCounter"                                {return RRTypes.I_COMPARECOUNTER;}
    "TestFaction"                                     {return RRTypes.TESTFACTION;}
    "LangIs"                                          {return RRTypes.LANGIS;}
    "I_TimerElapsed"                                  {return RRTypes.I_TIMERELAPSED;}
    "I_SoundPlaying"                                  {return RRTypes.I_SOUNDPLAYING;}
    "HasResource"                                     {return RRTypes.HASRESOURCE;}
    "SettlementRevoltingFrom"                         {return RRTypes.SETTLEMENTREVOLTINGFROM;}
    "IsCapital"                                       {return RRTypes.ISCAPITAL;}
    "SettlementName"                                  {return RRTypes.SETTLEMENTNAME;}
    "GovernorBuildingExists"                          {return RRTypes.GOVERNORBUILDINGEXISTS;}
    "SettlementBuildingExists"                        {return RRTypes.SETTLEMENTBUILDINGEXISTS;}
    "HomeSettlementBuildingExists"                    {return RRTypes.HOMESETTLEMENTBUILDINGEXISTS;}
    "SettlementOrderLevel"                            {return RRTypes.SETTLEMENTORDERLEVEL;}
    "SettlementCapabilityLevel"                       {return RRTypes.SETTLEMENTCAPABILITYLEVEL;}
    "BuildingFinishedByGovernor"                      {return RRTypes.BUILDINGFINISHEDBYGOVERNOR;}
    "SettlementBuildingFinished"                      {return RRTypes.SETTLEMENTBUILDINGFINISHED;}
    "GovernorPlugInExists"                            {return RRTypes.GOVERNORPLUGINEXISTS;}
    "GovernorPlugInFinished"                          {return RRTypes.GOVERNORPLUGINFINISHED;}
    "GovernorTaxLevel"                                {return RRTypes.GOVERNORTAXLEVEL;}
    "SettlementTaxLevel"                              {return RRTypes.SETTLEMENTTAXLEVEL;}
    "GovernorInResidence"                             {return RRTypes.GOVERNORINRESIDENCE;}
    "GovernorLoyaltyLevel"                            {return RRTypes.GOVERNORLOYALTYLEVEL;}
    "SettlementLoyaltyLevel"                          {return RRTypes.SETTLEMENTLOYALTYLEVEL;}
    "RiotRisk"                                        {return RRTypes.RIOTRISK;}
    "BuildingQueueIdleDespiteCash"                    {return RRTypes.BUILDINGQUEUEIDLEDESPITECASH;}
    "TrainingQueueIdleDespiteCash"                    {return RRTypes.TRAININGQUEUEIDLEDESPITECASH;}
    "I_SettlementExists"                              {return RRTypes.I_SETTLEMENTEXISTS;}
    "I_SettlementOwner"                               {return RRTypes.I_SETTLEMENTOWNER;}
    "I_SettlementOwnerCulture"                        {return RRTypes.I_SETTLEMENTOWNERCULTURE;}
    "I_SettlementLevel"                               {return RRTypes.I_SETTLEMENTLEVEL;}
    "AdviseFinancialBuild"                            {return RRTypes.ADVISEFINANCIALBUILD;}
    "AdviseBuild"                                     {return RRTypes.ADVISEBUILD;}
    "AdviseRecruit"                                   {return RRTypes.ADVISERECRUIT;}
    "SettlementPopulationMaxedOut"                    {return RRTypes.SETTLEMENTPOPULATIONMAXEDOUT;}
    "SettlementPopulationTooLow"                      {return RRTypes.SETTLEMENTPOPULATIONTOOLOW;}
    "SettlementAutoManaged"                           {return RRTypes.SETTLEMENTAUTOMANAGED;}
    "FeralSettlementAutoManaged"                      {return RRTypes.FERALSETTLEMENTAUTOMANAGED;}
    "PercentageOfPopulationInGarrison"                {return RRTypes.PERCENTAGEOFPOPULATIONINGARRISON;}
    "GarrisonToPopulationRatio"                       {return RRTypes.GARRISONTOPOPULATIONRATIO;}
    "HealthPercentage"                                {return RRTypes.HEALTHPERCENTAGE;}
    "SettlementHasPlague"                             {return RRTypes.SETTLEMENTHASPLAGUE;}
    "IsFortGarrisoned"                                {return RRTypes.ISFORTGARRISONED;}
    "IsSettlementGarrisoned"                          {return RRTypes.ISSETTLEMENTGARRISONED;}
    "IsSettlementRioting"                             {return RRTypes.ISSETTLEMENTRIOTING;}
    "I_NumberUnitsInSettlement"                       {return RRTypes.I_NUMBERUNITSINSETTLEMENT;}
    "I_AdvisorSpeechPlaying"                          {return RRTypes.I_ADVISORSPEECHPLAYING;}
    "CharacterIsLocal"                                {return RRTypes.CHARACTERISLOCAL;}
    "TargetCharacterIsLocal"                          {return RRTypes.TARGETCHARACTERISLOCAL;}
    "SettlementIsLocal"                               {return RRTypes.SETTLEMENTISLOCAL;}
    "TargetSettlementIsLocal"                         {return RRTypes.TARGETSETTLEMENTISLOCAL;}
    "RegionIsLocal"                                   {return RRTypes.REGIONISLOCAL;}
    "TargetRegionIsLocal"                             {return RRTypes.TARGETREGIONISLOCAL;}
    "ArmyIsLocal"                                     {return RRTypes.ARMYISLOCAL;}
    "ArmyIsGarrison"                                  {return RRTypes.ARMYISGARRISON;}
    "TargetArmyIsLocal"                               {return RRTypes.TARGETARMYISLOCAL;}
    "FactionIsLocal"                                  {return RRTypes.FACTIONISLOCAL;}
    "I_LocalFaction"                                  {return RRTypes.I_LOCALFACTION;}
    "TargetFactionIsLocal"                            {return RRTypes.TARGETFACTIONISLOCAL;}
    "I_TurnNumber"                                    {return RRTypes.I_TURNNUMBER;}
    "I_MapName"                                       {return RRTypes.I_MAPNAME;}
    "I_ThreadCount"                                   {return RRTypes.I_THREADCOUNT;}
    "I_IsTriggerTrue"                                 {return RRTypes.I_ISTRIGGERTRUE;}
    "IncomingMessageType"                             {return RRTypes.INCOMINGMESSAGETYPE;}
    "I_AdvisorVerbosity"                              {return RRTypes.I_ADVISORVERBOSITY;}
    "I_AdvisorVisible"                                {return RRTypes.I_ADVISORVISIBLE;}
    "I_CharacterSelected"                             {return RRTypes.I_CHARACTERSELECTED;}
    "I_AgentSelected"                                 {return RRTypes.I_AGENTSELECTED;}
    "I_SettlementSelected"                            {return RRTypes.I_SETTLEMENTSELECTED;}
    "ShortcutTriggered"                               {return RRTypes.SHORTCUTTRIGGERED;}
    "I_AdvancedStatsScrollIsOpen"                     {return RRTypes.I_ADVANCEDSTATSSCROLLISOPEN;}
    "ButtonPressed"                                   {return RRTypes.BUTTONPRESSED;}
    "ScrollOpened"                                    {return RRTypes.SCROLLOPENED;}
    "ScrollClosed"                                    {return RRTypes.SCROLLCLOSED;}
    "ScrollAdviceRequested"                           {return RRTypes.SCROLLADVICEREQUESTED;}
    "I_AnnotationDisplayed"                           {return RRTypes.I_ANNOTATIONDISPLAYED;}
    "FeralUIType"                                     {return RRTypes.FERALUITYPE;}
    "MerchantIsAvailableToBuild"                      {return RRTypes.MERCHANTISAVAILABLETOBUILD;}
    "SettlementHasDamagedBuilding"                    {return RRTypes.SETTLEMENTHASDAMAGEDBUILDING;}
    "LocalPlayerHasReinforcements"                    {return RRTypes.LOCALPLAYERHASREINFORCEMENTS;}
    "SettlementMerchantTradingWith"                   {return RRTypes.SETTLEMENTMERCHANTTRADINGWITH;}
    "SettlementOwnedBy"                               {return RRTypes.SETTLEMENTOWNEDBY;}
    "FactionBuildingExists"                           {return RRTypes.FACTIONBUILDINGEXISTS;}

    // condition params (system constants)
    // conflict types
    "SuccessfulAmbush"                                {return RRTypes.SUCCESSFULAMBUSH;}
    "FailedAmbush"                                    {return RRTypes.FAILEDAMBUSH;}
    "Normal"                                          {return RRTypes.NORMAL;}
    "Siege"                                           {return RRTypes.SIEGE;}
    "SallyBesieger"                                   {return RRTypes.SALLYBESIEGER;}
    "Naval"                                           {return RRTypes.NAVAL;}
    "Withdraw"                                        {return RRTypes.WITHDRAW;}
    // battle success
    "close"                                           {return RRTypes.CLOSE;}
    "average"                                         {return RRTypes.AVERAGE;}
    "clear"                                           {return RRTypes.CLEAR;}
    "crushing"                                        {return RRTypes.CRUSHING;}
    // siege_engine_class
    "tower"                                           {return RRTypes.TOWER;}
    "ram"                                             {return RRTypes.RAM;}
    "ladder"                                          {return RRTypes.LADDER;}
    "sap_point"                                       {return RRTypes.SAP_POINT;}
    "catapult"                                        {return RRTypes.CATAPULT;}
    "trebuchet"                                       {return RRTypes.TREBUCHET;}
    "ballista"                                        {return RRTypes.BALLISTA;}
    "scorpion"                                        {return RRTypes.SCORPION;}
    // tower defence type
    "arrow_tower"                                     {return RRTypes.ARROW_TOWER;}
    "ballista_tower"                                  {return RRTypes.BALLISTA_TOWER;}
    // gate defence type
    "hot_sand"                                        {return RRTypes.HOT_SAND;}
    "burning_oil"                                     {return RRTypes.BURNING_OIL;}
    // battle building type
    "ambient"                                         {return RRTypes.AMBIENT;}
    "major"                                           {return RRTypes.MAJOR;}
    "wall"                                            {return RRTypes.WALL;}
    "tower"                                           {return RRTypes.TOWER;}
    "gate"                                            {return RRTypes.GATE;}
    // special abilities
    "drop_engines"                                    {return RRTypes.DROP_ENGINES;}
    "flaming_ammo"                                    {return RRTypes.FLAMING_AMMO;}
    "chant"                                           {return RRTypes.CHANT;}
    "curse"                                           {return RRTypes.CURSE;}
    "berserk"                                          {return RRTypes.BERSERK;}
    "rally"                                           {return RRTypes.RALLY;}
    "kill_elephants"                                  {return RRTypes.KILL_ELEPHANTS;}
    "move_and_shoot"                                  {return RRTypes.MOVE_AND_SHOOT;}
    "cantabrian_circle"                               {return RRTypes.CANTABRIAN_CIRCLE;}
    "shield_wall"                                     {return RRTypes.SHIELD_WALL;}
    "stealth"                                         {return RRTypes.STEALTH;}
    "feigned_rout"                                    {return RRTypes.FEIGNED_ROUT;}
    "schiltrom"                                       {return RRTypes.SCHILTROM;}
    // action status
    "idling"                                          {return RRTypes.IDLING;}
    "hiding"                                          {return RRTypes.HIDING;}
    "ready"                                           {return RRTypes.READY;}
    "reforming"                                       {return RRTypes.REFORMING;}
    "moving"                                          {return RRTypes.MOVING;}
    "withdrawing"                                     {return RRTypes.WITHDRAWING;}
    "missiles_firing"                                 {return RRTypes.MISSILES_FIRING;}
    "missiles_reloading"                              {return RRTypes.MISSILES_RELOADING;}
    "charging"                                        {return RRTypes.CHARGING;}
    "fighting"                                        {return RRTypes.FIGHTING;}
    "pursuing"                                        {return RRTypes.PURSUING;}
    "routing"                                         {return RRTypes.ROUTING;}
    "fighting_backs_to_the_walls"                     {return RRTypes.FIGHTING_BACKS_TO_THE_WALLS;}
    "running_amok"                                    {return RRTypes.RUNNING_AMOK;}
    "rallying"                                        {return RRTypes.RALLYING;}
    "dead"                                            {return RRTypes.DEAD;}
    "leaving_battle"                                  {return RRTypes.LEAVING_BATTLE;}
    "entering_battle"                                 {return RRTypes.ENTERING_BATTLE;}
    "left_battle"                                     {return RRTypes.LEFT_BATTLE;}
    // attack direction
    "front"                                           {return RRTypes.FRONT;}
    "flank"                                           {return RRTypes.FLANK;}
    "rear"                                            {return RRTypes.REAR;}
    // disaster_type
    "earthquake"                                      {return RRTypes.EARTHQUAKE;}
    "flood"                                           {return RRTypes.FLOOD;}
    "horde"                                           {return RRTypes.HORDE;}
    "fire"                                            {return RRTypes.FIRE;}
    "riot"                                            {return RRTypes.RIOT;}
    "storm"                                           {return RRTypes.STORM;}
    "volcano"                                         {return RRTypes.VOLCANO;}
    // character_action_advice
    "attack_enemy"                                    {return RRTypes.ATTACK_ENEMY;}
    "attack_settlement"                               {return RRTypes.ATTACK_SETTLEMENT;}
    "merge_armies"                                    {return RRTypes.MERGE_ARMIES;}
    "patrol_region"                                   {return RRTypes.PATROL_REGION;}
    "move_to_region"                                  {return RRTypes.MOVE_TO_REGION;}
    "do_nothing"                                      {return RRTypes.DO_NOTHING;}
    // tax_level
    "tax_low"                                         {return RRTypes.TAX_LOW;}
    "tax_normal"                                      {return RRTypes.TAX_NORMAL;}
    "tax_high"                                        {return RRTypes.TAX_HIGH;}
    "tax_extortionate"                                {return RRTypes.TAX_EXTORTIONATE;}
    // success_level
    "not_successful"                                  {return RRTypes.NOT_SUCCESSFUL;}
    "slightly_successful"                             {return RRTypes.SLIGHTLY_SUCCESSFUL;}
    "partly_successful"                               {return RRTypes.PARTLY_SUCCESSFUL;}
    "highly_successful"                               {return RRTypes.HIGHLY_SUCCESSFUL;}
    // diplomatic_stance
    "Allied"                                          {return RRTypes.ALLIED;}
    "Suspicious"                                      {return RRTypes.SUSPICIOUS;}
    "Neutral"                                         {return RRTypes.NEUTRAL;}
    "Hostile"                                         {return RRTypes.HOSTILE;}
    "AtWar"                                           {return RRTypes.ATWAR;}
    // loyalty_to_governor_level
    "loyalty_revolting"                               {return RRTypes.LOYALTY_REVOLTING;}
    "loyalty_rioting"                                 {return RRTypes.LOYALTY_RIOTING;}
    "loyalty_disillusioned"                           {return RRTypes.LOYALTY_DISILLUSIONED;}
    "loyalty_content"                                 {return RRTypes.LOYALTY_CONTENT;}
    "loyalty_happy"                                   {return RRTypes.LOYALTY_HAPPY;}
    // automanage_type
    "Troops"                                          {return RRTypes.TROOPS;}
    "Buildings"                                       {return RRTypes.BUILDINGS;}
    "Mechanics"                                       {return RRTypes.MECHANICS;}

    "and"                                             {return RRTypes.AND;}
    "not"                                             {return RRTypes.NOT;}
    "&&"                                              {return RRTypes.AMBERSANDS;}
    {ID}                                              {return RRTypes.ID;}
}

[^]                                 { return TokenType.BAD_CHARACTER;}
