package rr.language.psi.impl;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import rr.language.RRUtil;
import rr.language.Util;
import rr.language.psi.*;
import rr.language.psi.references.*;

public class RRPsiImplUtil {
    //<editor-fold desc="Faction decl and refs">
    public static PsiElement setName(RRFactionNameDecl decl, String newName) {
        PsiElement id = RRElementFactory.createString(decl.getProject(), newName);
        decl.getFirstChild().replace(id);
        return decl;
    }

    public static String getName(RRFactionNameDecl decl) {
        return Util.unquote(decl.getFirstChild().getText());
    }

    public static PsiElement getNameIdentifier(RRFactionNameDecl decl) {
        return decl.getFirstChild();
    }

    public static PsiElement setName(RRFactionRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static FactionReference getReference(RRFactionRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new FactionReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="FactionGroup decl and refs">
    public static PsiElement setName(RRFactionGroupNameDecl decl, String newName) {
        PsiElement newDecl = RRElementFactory.createId(decl.getProject(), newName);
        return decl.replace(newDecl);
    }

    public static String getName(RRFactionGroupNameDecl e) {
        return e.getText();
    }

    public static PsiElement getNameIdentifier(RRFactionGroupNameDecl e) {
        return e;
    }

    public static PsiElement setName(RRStrFactionGroupRef ref, String newName) {
        PsiElement str = RRElementFactory.createString(ref.getProject(), newName);
        ref.getFirstChild().replace(str);
        return ref;
    }

    public static FactionGroupReference getReference(RRStrFactionGroupRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.STRING).getPsi().getTextRangeInParent();
        return new FactionGroupReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Culture decl and refs">
    public static PsiElement setName(RRCultureNameDecl decl, String newName) {
        PsiElement id = RRElementFactory.createString(decl.getProject(), newName);
        decl.getFirstChild().replace(id);
        return decl;
    }

    public static String getName(RRCultureNameDecl decl) {
        return Util.unquote(decl.getFirstChild().getText());
    }

    public static PsiElement getNameIdentifier(RRCultureNameDecl decl) {
        return decl.getFirstChild();
    }

    public static PsiElement setName(RRCultureRef ref, String newName) {
        RRUnitRef newRef = RRElementFactory.createUnitRef(ref.getProject(), newName);
        return ref.replace(newRef);
    }

    public static CultureReference getReference(RRCultureRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new CultureReference(ref, range);
    }

    public static PsiElement setName(RRStrCultureRef ref, String newName) {
        PsiElement str = RRElementFactory.createString(ref.getProject(), newName);
        ref.getFirstChild().replace(str);
        return ref;
    }

    public static CultureReference getReference(RRStrCultureRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.STRING).getPsi().getTextRangeInParent();
        return new CultureReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Faction or Culture ref">
    public static PsiElement setName(RRFactionOrCultureRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static FactionOrCultureReference getReference(RRFactionOrCultureRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new FactionOrCultureReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Building trees decl and refs">
    public static PsiElement setName(RRBuildingTreeNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRBuildingTreeNameDecl e) {
        return getNameIdentifier(e).getText();
    }

    public static PsiElement getNameIdentifier(RRBuildingTreeNameDecl e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRBuildingTreeRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static BuildingTreeReference getReference(RRBuildingTreeRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new BuildingTreeReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Building levels decl and refs">
    public static PsiElement setName(RRBuildingLevelNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRBuildingLevelNameDecl e) {
        return e.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RRBuildingLevelNameDecl e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRBuildingLevelRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static BuildingLevelReference getReference(RRBuildingLevelRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new BuildingLevelReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Buried building tree or level ref">
    public static PsiElement setName(RRBuriedBuildingTreeOrLevelRef ref, String newName) {
        String currentName = ref.getText();
        String currentPureName = RRUtil.removePostfixesFromExportBuildingsId(ref.getText(), ref.getProject());
        String postfix = currentName.replace(currentPureName, "");

        PsiElement id = RRElementFactory.createId(ref.getProject(), newName + postfix);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static BuildingTreeOrLevelReference getReference(RRBuriedBuildingTreeOrLevelRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new BuildingTreeOrLevelReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Region decl and refs">
    public static PsiElement setName(RRRegionNameDecl decl, String newName) {
        PsiElement id = RRElementFactory.createId(decl.getProject(), newName);
        decl.getFirstChild().replace(id);
        return decl;
    }

    public static String getName(RRRegionNameDecl decl) {
        return decl.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RRRegionNameDecl decl) {
        return decl.getFirstChild();
    }

    public static PsiElement setName(RRRegionRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static RegionReference getReference(RRRegionRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new RegionReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Ancillaries decl and refs">
    public static PsiElement setName(RRAncillaryNameDecl decl, String newName) {
        PsiElement id = RRElementFactory.createId(decl.getProject(), newName);
        decl.getFirstChild().replace(id);
        return decl;
    }

    public static String getName(RRAncillaryNameDecl decl) {
        return decl.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RRAncillaryNameDecl decl) {
        return decl.getFirstChild();
    }

    public static PsiElement setName(RRAncillaryRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static AncillaryReference getReference(RRAncillaryRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new AncillaryReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Traits decl and refs">
    public static PsiElement setName(RRTraitNameDecl decl, String newName) {
        PsiElement id = RRElementFactory.createId(decl.getProject(), newName);
        decl.getFirstChild().replace(id);
        return decl;
    }

    public static String getName(RRTraitNameDecl decl) {
        return decl.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RRTraitNameDecl decl) {
        return decl.getFirstChild();
    }

    public static PsiElement setName(RRTraitRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static TraitReference getReference(RRTraitRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new TraitReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Unit decl and refs">
    public static PsiElement setName(RRUnitNameDecl decl, String newName) {
        PsiElement newDecl = RRElementFactory.createUnitNameDecl(decl.getProject(), newName);
        return decl.replace(newDecl);
    }

    public static String getName(RRUnitNameDecl e) {
        return e.getText();
    }

    public static PsiElement getNameIdentifier(RRUnitNameDecl e) {
        return e;
    }

    public static PsiElement setName(RRUnitRef ref, String newName) {
        RRUnitRef newRef = RRElementFactory.createUnitRef(ref.getProject(), newName);
        return ref.replace(newRef);
    }

    public static UnitReference getReference(RRUnitRef ref) {
        TextRange range = new TextRange(
            ref.getFirstChild().getTextRangeInParent().getStartOffset(),
            ref.getLastChild().getTextRangeInParent().getEndOffset()
        );

        return new UnitReference(ref, range);
    }

    public static PsiElement setName(RRStrUnitRef ref, String newName) {
        PsiElement str = RRElementFactory.createString(ref.getProject(), newName);
        ref.getFirstChild().replace(str);
        return ref;
    }

    public static UnitReference getReference(RRStrUnitRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.STRING).getPsi().getTextRangeInParent();
        return new UnitReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Ethnicity decl and refs">
    public static PsiElement setName(RREthnicityNameDecl decl, String newName) {
        PsiElement id = RRElementFactory.createId(decl.getProject(), newName);
        decl.getFirstChild().replace(id);
        return decl;
    }

    public static String getName(RREthnicityNameDecl decl) {
        return decl.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RREthnicityNameDecl decl) {
        return decl.getFirstChild();
    }

    public static PsiElement setName(RREthnicityRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static EthnicityReference getReference(RREthnicityRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new EthnicityReference(ref, range);
    }

    public static PsiElement setName(RRStrEthnicityRef ref, String newName) {
        PsiElement str = RRElementFactory.createString(ref.getProject(), newName);
        ref.getFirstChild().replace(str);
        return ref;
    }

    public static EthnicityReference getReference(RRStrEthnicityRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.STRING).getPsi().getTextRangeInParent();
        return new EthnicityReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="AIPersonality decl and refs">
    public static PsiElement setName(RRAiPersonalityNameDecl decl, String newName) {
        PsiElement id = RRElementFactory.createId(decl.getProject(), newName);
        decl.getFirstChild().replace(id);
        return decl;
    }

    public static String getName(RRAiPersonalityNameDecl decl) {
        return decl.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RRAiPersonalityNameDecl decl) {
        return decl.getFirstChild();
    }

    public static PsiElement setName(RRAiPersonalityRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static AiPersonalityReference getReference(RRAiPersonalityRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new AiPersonalityReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="EthnicityMakeup decl and refs">
    public static PsiElement setName(RREthnicityMakeupNameDecl decl, String newName) {
        PsiElement id = RRElementFactory.createId(decl.getProject(), newName);
        decl.getFirstChild().replace(id);
        return decl;
    }

    public static String getName(RREthnicityMakeupNameDecl decl) {
        return decl.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RREthnicityMakeupNameDecl decl) {
        return decl.getFirstChild();
    }

    public static PsiElement setName(RREthnicityMakeupRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static EthnicityMakeupReference getReference(RREthnicityMakeupRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new EthnicityMakeupReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Resource decl and refs">
    public static PsiElement setName(RRResourceNameDecl decl, String newName) {
        PsiElement id = RRElementFactory.createString(decl.getProject(), newName);
        decl.getFirstChild().replace(id);
        return decl;
    }

    public static String getName(RRResourceNameDecl decl) {
        return Util.unquote(decl.getFirstChild().getText());
    }

    public static PsiElement getNameIdentifier(RRResourceNameDecl decl) {
        return decl.getFirstChild();
    }

    public static PsiElement setName(RRResourceRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static ResourceReference getReference(RRResourceRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new ResourceReference(ref, range);
    }
    //</editor-fold>

    public static String name(RRSettlementItem settlementItem) {
        return settlementItem.getNode().findChildByType(RRTypes.REGION_REF).getText();
    }
}
