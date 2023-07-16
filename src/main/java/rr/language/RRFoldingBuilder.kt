package rr.language

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.util.PsiTreeUtil
import rr.language.psi.*
import rr.language.psi.impl.RROffmapWonderImpl

class RRFoldingBuilder : FoldingBuilderEx(), DumbAware {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val file = root as RRFile
        val folds: MutableList<FoldingDescriptor> = ArrayList()
        if (quick) {
            return folds.toTypedArray<FoldingDescriptor>()
        }

        // Comment folding
        val processedComments: MutableSet<PsiElement> = HashSet()
        PsiTreeUtil.processElements(file) { element: PsiElement ->
            // Comment folding
            if (element.node.elementType === RRTypes.COMMENT) {
                val end = findFoldingEndForComment(element, processedComments)
                if (end != null) {
                    folds.add(
                        FoldingDescriptor(
                            element,
                            element.textRange.startOffset,
                            end.textRange.endOffset,
                            null,
                            (if (element.text.length > 20) element.text.substring(0, 20) else element.text) + "..."
                        )
                    )
                }
            }
            true
        }

        // KV format folding
        for (element in PsiTreeUtil.findChildrenOfType(file, RRKVFValue::class.java)) {
            fold(element, element.firstChild, element.lastChild, "...", folds)
        }

        // descr_strat file
        for (element in PsiTreeUtil.findChildrenOfType(file, RRCampaignSection::class.java)) {
            fold(
                element, element.firstChild.nextSibling.nextSibling.nextSibling,
                element.lastChild,
                "...",
                folds
            )
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRLandmarkSection::class.java)) {
            // Section might have no children.
            if (element.firstChild != null) {
                fold(
                    element, element.firstChild.nextSibling,
                    element.lastChild,
                    "...",
                    folds
                )
            }
        }
        for (element in PsiTreeUtil.findChildrenOfType(
            file,
            RRResourceQuantityEnabled_::class.java
        )) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(
            file,
            RRResourceQuantityDisabled_::class.java
        )) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRResourcesSection::class.java)) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRFactionItem::class.java)) {
            fold(
                element,
                element.firstChild.nextSibling.nextSibling.nextSibling,
                element.lastChild,
                "...",
                folds
            )
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRSettlementItem::class.java)) {
            fold(element, element.firstChild.nextSibling, element.lastChild, " " + element.name(), folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRCharacterItem::class.java)) {
            fold(element, element.characterType.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRDiplomacySection::class.java)) {
            fold(element, element.firstChild, element.lastChild, "DIPLOMACY SECTION...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(
            file,
            RRSuperfactionSetupItem::class.java
        )) {
            fold(
                element,
                element.firstChild.nextSibling.nextSibling.nextSibling,
                element.lastChild,
                "...",
                folds
            )
        }

        // export_descr_unit file
        for (element in PsiTreeUtil.findChildrenOfType(file, RRUnitItem_::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling.nextSibling, element.lastChild, "...", folds)
        }

        // export_descr_buildings
        for (element in PsiTreeUtil.findChildrenOfType(file, RRBuildingTree::class.java)) {
            fold(element, element.node.findChildByType(RRTypes.OCB)!!.psi, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRBuildingLevel::class.java)) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }

        // export_descr_ancillaries
        for (element in PsiTreeUtil.findChildrenOfType(file, RRAncillaryDef::class.java)) {
            fold(
                element,
                element.firstChild.nextSibling.nextSibling.nextSibling,
                element.lastChild,
                "...",
                folds
            )
        }
        for (element in PsiTreeUtil.findChildrenOfType(
            file,
            RRAncillaryTriggerDef::class.java
        )) {
            fold(
                element,
                element.firstChild.nextSibling.nextSibling.nextSibling,
                element.lastChild,
                "...",
                folds
            )
        }

        // export_descr_traits
        for (element in PsiTreeUtil.findChildrenOfType(file, RRTraitDef::class.java)) {
            fold(
                element,
                element.firstChild.nextSibling.nextSibling.nextSibling,
                element.lastChild,
                "...",
                folds
            )
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRTraitTriggerDef::class.java)) {
            fold(
                element,
                element.firstChild.nextSibling.nextSibling.nextSibling,
                element.lastChild,
                "...",
                folds
            )
        }

        // descr_mercenaries
        for (element in PsiTreeUtil.findChildrenOfType(file, RRMercenaryPool::class.java)) {
            fold(
                element,
                element.firstChild.nextSibling.nextSibling.nextSibling,
                element.lastChild,
                "...",
                folds
            )
        }

        // descr_regions
        for (element in PsiTreeUtil.findChildrenOfType(file, RRRegionDef::class.java)) {
            fold(
                element,
                element.firstChild.nextSibling.nextSibling,
                element.lastChild,
                "...",
                folds
            )
        }

        // descr_unit_variation
        for (element in PsiTreeUtil.findChildrenOfType(file, RRSkin_::class.java)) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RREthnicity_::class.java)) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RREthnicityMakeup_::class.java)) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }

        // feral_descr_ai_personality file
        for (element in PsiTreeUtil.findChildrenOfType(file, RRAiBuildingPriority::class.java)) {
            fold(element, element.id.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRAiMilitaryPriority::class.java)) {
            fold(element, element.id.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(
            file,
            RRAiDiplomaticPriority::class.java
        )) {
            fold(element, element.id.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRAiPersonality::class.java)) {
            fold(element, element.aiPersonalityNameDecl.nextSibling, element.lastChild, "...", folds)
        }

        // descr_faction_groups file
        for (element in PsiTreeUtil.findChildrenOfType(file, RRFactionGroup::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling, element.lastChild, "...", folds)
        }

        // descr_sm_factions file
        for (element in PsiTreeUtil.findChildrenOfType(file, RRFactionDecl::class.java)) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }

        // descr_sm_resources file
        for (element in PsiTreeUtil.findChildrenOfType(file, RRResourceDecl::class.java)) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }

        // feral_descr_portraits_variation file
        for (element in PsiTreeUtil.findChildrenOfType(
            file,
            RRCulturePortraitVariation::class.java
        )) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }

        // descr_banners file
        for (element in PsiTreeUtil.findChildrenOfType(file, RRCultureBanner::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRFactionBanner::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling.nextSibling, element.lastChild, "...", folds)
        }

        // descr_character file
        for (element in PsiTreeUtil.findChildrenOfType(file, RRCharacterTypeDef::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(
            file,
            RRCharacterTypeFactionDef::class.java
        )) {
            fold(element, element.firstChild.nextSibling.nextSibling.nextSibling, element.lastChild, "...", folds)
        }

        // descr_building_battle file
        for (element in PsiTreeUtil.findChildrenOfType(file, RRTextureVariants_::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRTextureVariant::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRSpotItems_::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRSpotItem::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRStatCategories::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRStatCategory::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRTransitionScripts_::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRTransitionScript::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRConstructionSite::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling, element.lastChild, "...", folds)
        }

        // descr_offmap_models
        for (element in PsiTreeUtil.findChildrenOfType(file, RROffmapNavy::class.java)) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RROffmapSettlement::class.java)) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RROffmapWonderImpl::class.java)) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RROffmapPort::class.java)) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }

        // descr_model_battle, descr_model_strat
        for (element in PsiTreeUtil.findChildrenOfType(file, RRModel_::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling.nextSibling, element.lastChild, "...", folds)
        }

        // descr_mount
        for (element in PsiTreeUtil.findChildrenOfType(file, RRMount_::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling.nextSibling, element.lastChild, "...", folds)
        }

        // descr_beliefs
        for (element in PsiTreeUtil.findChildrenOfType(file, RRReligion_::class.java)) {
            fold(element, element.firstChild.nextSibling, element.lastChild, "...", folds)
        }

        // descr_projectiles_new
        for (element in PsiTreeUtil.findChildrenOfType(file, RRProjectile_::class.java)) {
            fold(element, element.projectileNameDecl.nextSibling, element.lastChild, "...", folds)
        }

        // rebel_faction_descr
        for (element in PsiTreeUtil.findChildrenOfType(file, RRRebelFaction::class.java)) {
            fold(element, element.rebelFactionNameDef.nextSibling, element.lastChild, "...", folds)
        }

        // scripts
        for (element in PsiTreeUtil.findChildrenOfType(file, RRIf_::class.java)) {
            fold(element, element.conditionAtomList[0].nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRIfNot_::class.java)) {
            fold(element, element.conditionAtomList[0].nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRWhile_::class.java)) {
            fold(element, element.conditionAtomList[0].nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRWhileNot_::class.java)) {
            fold(element, element.conditionAtomList[0].nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRForEach_::class.java)) {
            fold(element, element.scope!!.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRMonitor_::class.java)) {
            fold(element, element.conditionAtomList[0].nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRMonitorConditions_::class.java)) {
            fold(element, element.conditionAtomList[0].nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRMonitorEvent_::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRSpawnBattle_::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRBenchmark_::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling, element.lastChild, "...", folds)
        }
        for (element in PsiTreeUtil.findChildrenOfType(file, RRMacroDef_::class.java)) {
            fold(element, element.firstChild.nextSibling.nextSibling, element.lastChild, "...", folds)
        }
        return folds.toTypedArray<FoldingDescriptor>()
    }

    override fun getPlaceholderText(node: ASTNode): String? {
        return null
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        return false
    }

    companion object {
        private fun fold(
            e: PsiElement,
            l: PsiElement?,
            r: PsiElement?,
            placeholderText: String,
            result: MutableList<FoldingDescriptor>
        ) {
            if (l != null && r != null) {
                result.add(FoldingDescriptor(e, l.textRange.startOffset, r.textRange.endOffset, null, placeholderText))
            }
        }

        private fun findFoldingEndForComment(
            comment: PsiElement,
            processedComments: MutableSet<PsiElement>
        ): PsiElement? {
            if (processedComments.contains(comment)) return null
            var next = comment.nextSibling
            var end: PsiElement? = null
            while (next != null) {
                val elementType = next.node.elementType
                if (elementType !== RRTypes.COMMENT && elementType !== TokenType.WHITE_SPACE) {
                    break
                }
                if (elementType === RRTypes.COMMENT) {
                    end = next
                    processedComments.add(next)
                }
                next = next.nextSibling
            }
            return end
        }
    }
}
