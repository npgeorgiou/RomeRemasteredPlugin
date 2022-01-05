package rr.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.psi.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RRFoldingBuilder extends FoldingBuilderEx implements DumbAware {

    private static void fold(@NotNull PsiElement e,
                             @Nullable PsiElement l,
                             @Nullable PsiElement r,
                             @NotNull String placeholderText,
                             @NotNull List<FoldingDescriptor> result) {
        if (l != null && r != null) {
            result.add(new FoldingDescriptor(e, l.getTextRange().getStartOffset(), r.getTextRange().getEndOffset(), null, placeholderText));
        }
    }

    @Override
    public @NotNull FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {

        RRFile file = (RRFile) root;
        final List<FoldingDescriptor> folds = new ArrayList<>();

        if (!quick) {
            // Comment folding
            Set<PsiElement> processedComments = new HashSet<>();
            PsiTreeUtil.processElements(file, element -> {
                // Comment folding
                if (element.getNode().getElementType() == RRTypes.COMMENT) {
                    PsiElement end = findFoldingEndForComment(element, processedComments);

                    if (end != null) {
                        folds.add(new FoldingDescriptor(
                            element,
                            element.getTextRange().getStartOffset(),
                            end.getTextRange().getEndOffset(),
                            null,
                            (element.getText().length() > 20 ? element.getText().substring(0, 20) : element.getText()).concat("...")
                        ));
                    }
                }
                return true;
            });

            // KV format folding
            for (RRKVFValue element : PsiTreeUtil.findChildrenOfType(file, RRKVFValue.class)) {
                fold(element, element.getFirstChild(), element.getLastChild(), "...", folds);
            }

            // descr_strat file
            // Sections
            for (RRCampaignSection element : PsiTreeUtil.findChildrenOfType(file, RRCampaignSection.class)) {
                fold(
                    element, element.getFirstChild().getNextSibling().getNextSibling().getNextSibling(),
                    element.getLastChild(),
                    "...",
                    folds
                );
            }

            for (RRLandmarkSection element : PsiTreeUtil.findChildrenOfType(file, RRLandmarkSection.class)) {
                // Section might have no children.
                if (element.getFirstChild() != null) {
                    fold(
                        element, element.getFirstChild().getNextSibling(),
                        element.getLastChild(),
                        "...",
                        folds
                    );
                }
            }

            for (RRResourceQuantityEnabled_ element : PsiTreeUtil.findChildrenOfType(file, RRResourceQuantityEnabled_.class)) {
                fold(element, element.getFirstChild().getNextSibling(), element.getLastChild(), "...", folds);
            }

            for (RRResourceQuantityDisabled_ element : PsiTreeUtil.findChildrenOfType(file, RRResourceQuantityDisabled_.class)) {
                fold(element, element.getFirstChild().getNextSibling(), element.getLastChild(), "...", folds);
            }

            for (RRFactionItem element : PsiTreeUtil.findChildrenOfType(file, RRFactionItem.class)) {
                fold(
                    element,
                    element.getFirstChild().getNextSibling().getNextSibling().getNextSibling(),
                    element.getLastChild(),
                    "...",
                    folds
                );
            }

            for (RRSettlementItem element : PsiTreeUtil.findChildrenOfType(file, RRSettlementItem.class)) {
                fold(element, element.getFirstChild().getNextSibling(), element.getLastChild(), " ".concat(element.name()), folds);
            }

            for (RRCharacterItem element : PsiTreeUtil.findChildrenOfType(file, RRCharacterItem.class)) {
                fold(element, element.getCharacterType().getNextSibling(), element.getLastChild(), "...", folds);
            }

            for (RRDiplomacySection element : PsiTreeUtil.findChildrenOfType(file, RRDiplomacySection.class)) {
                fold(element, element.getFirstChild(), element.getLastChild(), "DIPLOMACY SECTION...", folds);
            }

            for (RRSuperfactionSetupItem element : PsiTreeUtil.findChildrenOfType(file, RRSuperfactionSetupItem.class)) {
                fold(
                    element,
                    element.getFirstChild().getNextSibling().getNextSibling().getNextSibling(),
                    element.getLastChild(),
                    "...",
                    folds
                );
            }

            // export_descr_unit file
            for (RRUnitItem_ element : PsiTreeUtil.findChildrenOfType(file, RRUnitItem_.class)) {
                fold(element, element.getNode().findChildByType(RRTypes.DICTIONARY).getPsi(), element.getLastChild(), "...", folds);
            }

            // export_descr_buildings
            for (RRBuildingTree element : PsiTreeUtil.findChildrenOfType(file, RRBuildingTree.class)) {
                fold(element, element.getNode().findChildByType(RRTypes.OCB).getPsi(), element.getLastChild(), "...", folds);
            }

            for (RRBuildingLevel element : PsiTreeUtil.findChildrenOfType(file, RRBuildingLevel.class)) {
                fold(element, element.getNode().findChildByType(RRTypes.REQUIREMENT).getPsi(), element.getLastChild(), "...", folds);
            }

            // export_descr_ancillaries
            for (RRAncillaryDef element : PsiTreeUtil.findChildrenOfType(file, RRAncillaryDef.class)) {
                fold(
                    element,
                    element.getFirstChild().getNextSibling().getNextSibling().getNextSibling(),
                    element.getLastChild(),
                    "...",
                    folds
                );
            }

            for (RRAncillaryTriggerDef element : PsiTreeUtil.findChildrenOfType(file, RRAncillaryTriggerDef.class)) {
                fold(
                    element,
                    element.getFirstChild().getNextSibling().getNextSibling().getNextSibling(),
                    element.getLastChild(),
                    "...",
                    folds
                );
            }

            // export_descr_traits
            for (RRTraitDef element : PsiTreeUtil.findChildrenOfType(file, RRTraitDef.class)) {
                fold(
                    element,
                    element.getFirstChild().getNextSibling().getNextSibling().getNextSibling(),
                    element.getLastChild(),
                    "...",
                    folds
                );
            }

            for (RRTraitTriggerDef element : PsiTreeUtil.findChildrenOfType(file, RRTraitTriggerDef.class)) {
                fold(
                    element,
                    element.getFirstChild().getNextSibling().getNextSibling().getNextSibling(),
                    element.getLastChild(),
                    "...",
                    folds
                );
            }

            // descr_mercenaries
            for (RRMercenaryPool element : PsiTreeUtil.findChildrenOfType(file, RRMercenaryPool.class)) {
                fold(
                    element,
                    element.getFirstChild().getNextSibling().getNextSibling().getNextSibling(),
                    element.getLastChild(),
                    "...",
                    folds
                );
            }

            // descr_regions
            for (RRRegionDef element : PsiTreeUtil.findChildrenOfType(file, RRRegionDef.class)) {
                fold(
                    element,
                    element.getFirstChild().getNextSibling().getNextSibling(),
                    element.getLastChild(),
                    "...",
                    folds
                );
            }

            // descr_names
            for (RRNamesForFaction element : PsiTreeUtil.findChildrenOfType(file, RRNamesForFaction.class)) {
                fold(
                    element,
                    element.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling(),
                    element.getLastChild(),
                    "...",
                    folds
                );
            }

            // descr_unit_variation
            for (RRSkin_ element : PsiTreeUtil.findChildrenOfType(file, RRSkin_.class)) {
                fold(element, element.getFirstChild().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RREthnicity_ element : PsiTreeUtil.findChildrenOfType(file, RREthnicity_.class)) {
                fold(element, element.getFirstChild().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RREthnicityMakeup_ element : PsiTreeUtil.findChildrenOfType(file, RREthnicityMakeup_.class)) {
                fold(element, element.getFirstChild().getNextSibling(), element.getLastChild(), "...", folds);
            }

            // feral_descr_ai_personality file
            for (RRAiBuildingPriority element : PsiTreeUtil.findChildrenOfType(file, RRAiBuildingPriority.class)) {
                fold(element, element.getId().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RRAiMilitaryPriority element : PsiTreeUtil.findChildrenOfType(file, RRAiMilitaryPriority.class)) {
                fold(element, element.getId().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RRAiDiplomaticPriority element : PsiTreeUtil.findChildrenOfType(file, RRAiDiplomaticPriority.class)) {
                fold(element, element.getId().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RRAiPersonality element : PsiTreeUtil.findChildrenOfType(file, RRAiPersonality.class)) {
                fold(element, element.getAiPersonalityNameDecl().getNextSibling(), element.getLastChild(), "...", folds);
            }

            // descr_faction_groups file
            for (RRFactionGroup element : PsiTreeUtil.findChildrenOfType(file, RRFactionGroup.class)) {
                fold(element, element.getFirstChild().getNextSibling().getNextSibling(), element.getLastChild(), "...", folds);
            }

            // descr_sm_factions file
            for (RRFactionDecl element : PsiTreeUtil.findChildrenOfType(file, RRFactionDecl.class)) {
                fold(element, element.getFirstChild().getNextSibling(), element.getLastChild(), "...", folds);
            }

            // descr_sm_resources file
            for (RRResourceDecl element : PsiTreeUtil.findChildrenOfType(file, RRResourceDecl.class)) {
                fold(element, element.getFirstChild().getNextSibling(), element.getLastChild(), "...", folds);
            }

            // feral_descr_portraits_variation file
            for (RRCulturePortraitVariation element : PsiTreeUtil.findChildrenOfType(file, RRCulturePortraitVariation.class)) {
                fold(element, element.getFirstChild().getNextSibling(), element.getLastChild(), "...", folds);
            }

            // descr_banners file
            for (RRCultureBanner element : PsiTreeUtil.findChildrenOfType(file, RRCultureBanner.class)) {
                fold(element, element.getFirstChild().getNextSibling().getNextSibling().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RRFactionBanner element : PsiTreeUtil.findChildrenOfType(file, RRFactionBanner.class)) {
                fold(element, element.getFirstChild().getNextSibling().getNextSibling().getNextSibling(), element.getLastChild(), "...", folds);
            }

            // descr_character file
            for (RRCharacterTypeDef element : PsiTreeUtil.findChildrenOfType(file, RRCharacterTypeDef.class)) {
                fold(element, element.getFirstChild().getNextSibling().getNextSibling().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RRCharacterTypeFactionDef element : PsiTreeUtil.findChildrenOfType(file, RRCharacterTypeFactionDef.class)) {
                fold(element, element.getFirstChild().getNextSibling().getNextSibling().getNextSibling(), element.getLastChild(), "...", folds);
            }

            // descr_building_battle file
            for (RRTextureVariants_ element : PsiTreeUtil.findChildrenOfType(file, RRTextureVariants_.class)) {
                fold(element, element.getFirstChild().getNextSibling().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RRTextureVariant element : PsiTreeUtil.findChildrenOfType(file, RRTextureVariant.class)) {
                fold(element, element.getFirstChild().getNextSibling().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RRSpotItems_ element : PsiTreeUtil.findChildrenOfType(file, RRSpotItems_.class)) {
                fold(element, element.getFirstChild().getNextSibling().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RRSpotItem element : PsiTreeUtil.findChildrenOfType(file, RRSpotItem.class)) {
                fold(element, element.getFirstChild().getNextSibling().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RRStatCategories element : PsiTreeUtil.findChildrenOfType(file, RRStatCategories.class)) {
                fold(element, element.getFirstChild().getNextSibling().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RRStatCategory element : PsiTreeUtil.findChildrenOfType(file, RRStatCategory.class)) {
                fold(element, element.getFirstChild().getNextSibling().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RRTransitionScripts_ element : PsiTreeUtil.findChildrenOfType(file, RRTransitionScripts_.class)) {
                fold(element, element.getFirstChild().getNextSibling().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RRTransitionScript element : PsiTreeUtil.findChildrenOfType(file, RRTransitionScript.class)) {
                fold(element, element.getFirstChild().getNextSibling().getNextSibling().getNextSibling(), element.getLastChild(), "...", folds);
            }
            for (RRConstructionSite element : PsiTreeUtil.findChildrenOfType(file, RRConstructionSite.class)) {
                fold(element, element.getFirstChild().getNextSibling().getNextSibling(), element.getLastChild(), "...", folds);
            }
        }

        return folds.toArray(new FoldingDescriptor[folds.size()]);
    }

    @Override
    public @Nullable String getPlaceholderText(@NotNull ASTNode node) {
        return null;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return false;
    }

    private static PsiElement findFoldingEndForComment(@NotNull PsiElement comment, @NotNull Set<PsiElement> processedComments) {
        if (processedComments.contains(comment)) return null;

        PsiElement next = comment.getNextSibling();
        PsiElement end = null;

        while (next != null) {
            IElementType elementType = next.getNode().getElementType();

            if (elementType != RRTypes.COMMENT && elementType != TokenType.WHITE_SPACE) {
                break;
            }

            if (elementType == RRTypes.COMMENT) {
                end = next;
                processedComments.add(next);
            }

            next = next.getNextSibling();
        }

        return end;
    }
}
