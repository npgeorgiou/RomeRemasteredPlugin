package rr.language.psi.impl

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import rr.language.RRUtil.removePostfixesFromExportBuildingsId
import rr.language.Util.replaceChildrenOf
import rr.language.Util.unquote
import rr.language.psi.*
import rr.language.psi.references.*

class RRPsiImplUtil {
    companion object {
        //<editor-fold desc="txt refs">
        @JvmStatic
        @Override
        fun setName(fileName: RRTxtFile_, newName: String?): PsiElement {
            // TODO: Maybe implement if I find a way to properly link File to references too.
            return fileName
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRTxtFile_): TxtFileReference {
            val range = ref.firstChild.textRangeInParent
            return TxtFileReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="tga refs">
        @JvmStatic
        @Override
        fun setName(fileName: RRTgaFile_, newName: String?): PsiElement {
            // TODO: Maybe implement if I find a way to properly link File to references too.
            return fileName
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRTgaFile_): TgaFileReference {
            val range = ref.firstChild.textRangeInParent
            return TgaFileReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Ambient objects">
        @JvmStatic
        @Override
        fun setName(e: RRAmbientObjectNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createString(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRAmbientObjectNameDecl): String {
            return unquote(e.firstChild.text)
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRAmbientObjectNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRAmbientObjectRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRAmbientObjectRef): AmbiantObjectReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return AmbiantObjectReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Faction">
        @JvmStatic
        @Override
        fun setName(e: RRFactionNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createString(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRFactionNameDecl): String {
            return unquote(e.firstChild.text)
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRFactionNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRFactionRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRFactionRef): FactionReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return FactionReference(ref, range)
        }

        @JvmStatic
        @Override
        fun setName(ref: RRStrFactionRef, newName: String?): PsiElement {
            val str = RRElementFactory.createString(ref.project, newName)
            ref.firstChild.replace(str)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRStrFactionRef): FactionReference {
            val range = ref.node.findChildByType(RRTypes.STRING)!!.psi.textRangeInParent
            return FactionReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="FactionGroup">
        @JvmStatic
        @Override
        fun setName(e: RRFactionGroupNameDecl, newName: String?): PsiElement {
            val newId = RRElementFactory.createId(e.project, newName)
            return e.replace(newId)
        }

        @JvmStatic
        @Override
        fun getName(e: RRFactionGroupNameDecl): String {
            return e.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRFactionGroupNameDecl): PsiElement {
            return e
        }

        @JvmStatic
        @Override
        fun setName(ref: RRStrFactionGroupRef, newName: String?): PsiElement {
            val str = RRElementFactory.createString(ref.project, newName)
            ref.firstChild.replace(str)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRStrFactionGroupRef): FactionGroupReference {
            val range = ref.node.findChildByType(RRTypes.STRING)!!.psi.textRangeInParent
            return FactionGroupReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Culture">
        @JvmStatic
        @Override
        fun setName(e: RRCultureNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createString(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRCultureNameDecl): String {
            return unquote(e.firstChild.text)
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRCultureNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRCultureRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRCultureRef): CultureReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return CultureReference(ref, range)
        }

        @JvmStatic
        @Override
        fun setName(ref: RRStrCultureRef, newName: String?): PsiElement {
            val str = RRElementFactory.createString(ref.project, newName)
            ref.firstChild.replace(str)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRStrCultureRef): CultureReference {
            val range = ref.node.findChildByType(RRTypes.STRING)!!.psi.textRangeInParent
            return CultureReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Religion">
        @JvmStatic
        @Override
        fun setName(e: RRReligionNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createString(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRReligionNameDecl): String {
            return unquote(e.firstChild.text)
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRReligionNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRReligionRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRReligionRef): ReligionReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return ReligionReference(ref, range)
        }

        @JvmStatic
        @Override
        fun setName(ref: RRStrReligionRef, newName: String?): PsiElement {
            val str = RRElementFactory.createString(ref.project, newName)
            ref.firstChild.replace(str)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRStrReligionRef): ReligionReference {
            val range = ref.node.findChildByType(RRTypes.STRING)!!.psi.textRangeInParent
            return ReligionReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Faction or Culture">
        @JvmStatic
        @Override
        fun setName(ref: RRFactionOrCultureRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRFactionOrCultureRef): FactionOrCultureReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return FactionOrCultureReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Region or Settlement">
        @JvmStatic
        @Override
        fun setName(ref: RRRegionOrSettlementRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRRegionOrSettlementRef): RegionOrSettlementReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return RegionOrSettlementReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Building trees">
        @JvmStatic
        @Override
        fun setName(e: RRBuildingTreeNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRBuildingTreeNameDecl): String {
            return getNameIdentifier(e).text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRBuildingTreeNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRBuildingTreeRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRBuildingTreeRef): BuildingTreeReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return BuildingTreeReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Building levels">
        @JvmStatic
        @Override
        fun setName(e: RRBuildingLevelNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRBuildingLevelNameDecl): String {
            return e.firstChild.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRBuildingLevelNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRBuildingLevelRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRBuildingLevelRef): BuildingLevelReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return BuildingLevelReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Buried building tree or level">
        @JvmStatic
        @Override
        fun setName(ref: RRBuriedBuildingTreeOrLevelRef, newName: String): PsiElement {
            val currentName = ref.text
            val currentPureName = removePostfixesFromExportBuildingsId(ref.text, ref.project)
            val postfix = currentName.replace(currentPureName, "")
            val id = RRElementFactory.createId(ref.project, newName + postfix)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRBuriedBuildingTreeOrLevelRef): BuildingTreeOrLevelReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return BuildingTreeOrLevelReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Region">
        @JvmStatic
        @Override
        fun setName(e: RRRegionNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRRegionNameDecl): String {
            return e.firstChild.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRRegionNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRRegionRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRRegionRef): RegionReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return RegionReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Settlement">
        @JvmStatic
        @Override
        fun setName(e: RRSettlementNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRSettlementNameDecl): String {
            return e.firstChild.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRSettlementNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRSettlementRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRSettlementRef): SettlementReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return SettlementReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Rebels">
        @JvmStatic
        @Override
        fun setName(e: RRRebelFactionNameDef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRRebelFactionNameDef): String {
            return e.firstChild.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRRebelFactionNameDef): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRRebelFactionRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRRebelFactionRef): RebelFactionReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return RebelFactionReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Ancillaries">
        @JvmStatic
        @Override
        fun setName(e: RRAncillaryNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRAncillaryNameDecl): String {
            return e.firstChild.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRAncillaryNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRAncillaryRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRAncillaryRef): AncillaryReference {
            val range = ref.firstChild.textRangeInParent
            return AncillaryReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Traits">
        @JvmStatic
        @Override
        fun setName(e: RRTraitNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRTraitNameDecl): String {
            return e.firstChild.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRTraitNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRTraitRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRTraitRef): TraitReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return TraitReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Unit">
        @JvmStatic
        @Override
        fun setName(e: RRUnitNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createIdWithPotentialWhitespaces(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRUnitNameDecl): String {
            return e.firstChild.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRUnitNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(e: RRUnitRef, newName: String?): PsiElement {
            val elementWithDesiredChildren = RRElementFactory.createIdWithPotentialWhitespaces(e.project, newName)
            return replaceChildrenOf(e, elementWithDesiredChildren)
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRUnitRef): UnitReference {
            val range = TextRange(
                ref.firstChild.textRangeInParent.startOffset,
                ref.lastChild.textRangeInParent.endOffset
            )
            return UnitReference(ref, range)
        }

        @JvmStatic
        @Override
        fun setName(ref: RRStrUnitRef, newName: String?): PsiElement {
            val str = RRElementFactory.createString(ref.project, newName)
            ref.firstChild.replace(str)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRStrUnitRef): UnitReference {
            val range = ref.firstChild.textRangeInParent
            return UnitReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Ethnicity">
        @JvmStatic
        @Override
        fun setName(e: RREthnicityNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RREthnicityNameDecl): String {
            return e.firstChild.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RREthnicityNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RREthnicityRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RREthnicityRef): EthnicityReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return EthnicityReference(ref, range)
        }

        @JvmStatic
        @Override
        fun setName(ref: RRStrEthnicityRef, newName: String?): PsiElement {
            val str = RRElementFactory.createString(ref.project, newName)
            ref.firstChild.replace(str)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRStrEthnicityRef): EthnicityReference {
            val range = ref.node.findChildByType(RRTypes.STRING)!!.psi.textRangeInParent
            return EthnicityReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="AIPersonality">
        @JvmStatic
        @Override
        fun setName(e: RRAiPersonalityNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRAiPersonalityNameDecl): String {
            return e.firstChild.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRAiPersonalityNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRAiPersonalityRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRAiPersonalityRef): AiPersonalityReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return AiPersonalityReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="EthnicityMakeup">
        @JvmStatic
        @Override
        fun setName(e: RREthnicityMakeupNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RREthnicityMakeupNameDecl): String {
            return e.firstChild.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RREthnicityMakeupNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RREthnicityMakeupRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RREthnicityMakeupRef): EthnicityMakeupReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return EthnicityMakeupReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Resource">
        @JvmStatic
        @Override
        fun setName(e: RRResourceNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createString(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRResourceNameDecl): String {
            return unquote(e.firstChild.text)
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRResourceNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRResourceRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRResourceRef): ResourceReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return ResourceReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Wonder">
        @JvmStatic
        @Override
        fun setName(e: RRWonderNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRWonderNameDecl): String {
            return e.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRWonderNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRWonderRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRWonderRef): WonderReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return WonderReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Model">
        @JvmStatic
        @Override
        fun setName(e: RRModelNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRModelNameDecl): String {
            return e.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRModelNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRModelRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRModelRef): ModelReference {
            val range = ref.firstChild.textRangeInParent
            return ModelReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Mount">
        @JvmStatic
        @Override
        fun setName(e: RRMountNameDecl, newName: String?): PsiElement {
            val elementWithDesiredChildren = RRElementFactory.createIdWithPotentialWhitespaces(e.project, newName)
            return replaceChildrenOf(e, elementWithDesiredChildren)
        }

        @JvmStatic
        @Override
        fun getName(e: RRMountNameDecl): String {
            return e.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRMountNameDecl): PsiElement {
            return e
        }

        @JvmStatic
        @Override
        fun setName(e: RRMountRef, newName: String?): PsiElement {
            val elementWithDesiredChildren = RRElementFactory.createIdWithPotentialWhitespaces(e.project, newName)
            return replaceChildrenOf(e, elementWithDesiredChildren)
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRMountRef): MountReference {
            val range = TextRange(
                ref.firstChild.textRangeInParent.startOffset,
                ref.lastChild.textRangeInParent.endOffset
            )
            return MountReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Ship">
        @JvmStatic
        @Override
        fun setName(e: RRShipNameDecl, newName: String?): PsiElement {
            val elementWithDesiredChildren = RRElementFactory.createIdWithPotentialWhitespaces(e.project, newName)
            return replaceChildrenOf(e, elementWithDesiredChildren)
        }

        @JvmStatic
        @Override
        fun getName(e: RRShipNameDecl): String {
            return e.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRShipNameDecl): PsiElement {
            return e
        }

        @JvmStatic
        @Override
        fun setName(e: RRShipRef, newName: String?): PsiElement {
            val elementWithDesiredChildren = RRElementFactory.createIdWithPotentialWhitespaces(e.project, newName)
            return replaceChildrenOf(e, elementWithDesiredChildren)
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRShipRef): ShipReference {
            val range = TextRange(
                ref.firstChild.textRangeInParent.startOffset,
                ref.lastChild.textRangeInParent.endOffset
            )
            return ShipReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Projectile">
        @JvmStatic
        @Override
        fun setName(e: RRProjectileNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRProjectileNameDecl): String {
            return e.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRProjectileNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRProjectileRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRProjectileRef): ProjectileReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return ProjectileReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Animal">
        @JvmStatic
        @Override
        fun setName(e: RRAnimalNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRAnimalNameDecl): String {
            return e.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRAnimalNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRAnimalRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRAnimalRef): AnimalReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return AnimalReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Disaster">
        @JvmStatic
        @Override
        fun setName(e: RRDisasterNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRDisasterNameDecl): String {
            return e.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRDisasterNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRDisasterRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRDisasterRef): DisasterReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return DisasterReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Counter">
        @JvmStatic
        @Override
        fun setName(e: RRCounterNameDecl, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRCounterNameDecl): String {
            return e.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRCounterNameDecl): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRCounterRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRCounterRef): CounterReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return CounterReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Unit descriptions">
        @JvmStatic
        @Override
        fun setName(e: RRUnitDescrDef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRUnitDescrDef): String {
            return e.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRUnitDescrDef): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRUnitDescrRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRUnitDescrRef): UnitDescrReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return UnitDescrReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Ancillary descriptions">
        @JvmStatic
        @Override
        fun setName(e: RRAncillaryDescrDef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRAncillaryDescrDef): String {
            return e.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRAncillaryDescrDef): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRAncillaryDescrRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRAncillaryDescrRef): AncillaryDescrReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return AncillaryDescrReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Trait descriptions">
        @JvmStatic
        @Override
        fun setName(e: RRTraitDescrDef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRTraitDescrDef): String {
            return e.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRTraitDescrDef): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRTraitDescrRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRTraitDescrRef): TraitDescrReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return TraitDescrReference(ref, range)
        }
        //</editor-fold>

        //<editor-fold desc="Rebel factions descriptions">
        @JvmStatic
        @Override
        fun setName(e: RRRebelFactionDescrDef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(e.project, newName)
            e.firstChild.replace(id)
            return e
        }

        @JvmStatic
        @Override
        fun getName(e: RRRebelFactionDescrDef): String {
            return e.text
        }

        @JvmStatic
        @Override
        fun getNameIdentifier(e: RRRebelFactionDescrDef): PsiElement {
            return e.firstChild
        }

        @JvmStatic
        @Override
        fun setName(ref: RRRebelFactionDescrRef, newName: String?): PsiElement {
            val id = RRElementFactory.createId(ref.project, newName)
            ref.firstChild.replace(id)
            return ref
        }

        @JvmStatic
        @Override
        fun getReference(ref: RRRebelFactionDescrRef): RebelFactionDescrReference {
            val range = ref.node.findChildByType(RRTypes.ID)!!.psi.textRangeInParent
            return RebelFactionDescrReference(ref, range)
        }
        //</editor-fold>

        @JvmStatic
        @Override
        fun name(settlementItem: RRSettlementItem): String {
            return settlementItem.node.findChildByType(RRTypes.REGION_REF)!!.text
        }
    }
}

