package rr.language.psi.impl;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import rr.language.RRUtil;
import rr.language.Util;
import rr.language.psi.*;
import rr.language.psi.references.*;

public class RRPsiImplUtil {
    //<editor-fold desc="txt refs">
    public static PsiElement setName(RRTxtFile_ fileName, String newName) {
        // TODO: Maybe implement if I find a way to properly link File to references too.
        return fileName;
    }

    public static TxtFileReference getReference(RRTxtFile_ ref) {
        TextRange range = ref.getFirstChild().getTextRangeInParent();
        return new TxtFileReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="tga refs">
    public static PsiElement setName(RRTgaFile_ fileName, String newName) {
        // TODO: Maybe implement if I find a way to properly link File to references too.
        return fileName;
    }

    public static TgaFileReference getReference(RRTgaFile_ ref) {
        TextRange range = ref.getFirstChild().getTextRangeInParent();
        return new TgaFileReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Ambient objects decl and refs">
    public static PsiElement setName(RRAmbientObjectNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createString(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRAmbientObjectNameDecl e) {
        return Util.unquote(e.getFirstChild().getText());
    }

    public static PsiElement getNameIdentifier(RRAmbientObjectNameDecl e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRAmbientObjectRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static AmbiantObjectReference getReference(RRAmbientObjectRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new AmbiantObjectReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Faction decl and refs">
    public static PsiElement setName(RRFactionNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createString(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRFactionNameDecl e) {
        return Util.unquote(e.getFirstChild().getText());
    }

    public static PsiElement getNameIdentifier(RRFactionNameDecl e) {
        return e.getFirstChild();
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

    public static PsiElement setName(RRStrFactionRef ref, String newName) {
        PsiElement str = RRElementFactory.createString(ref.getProject(), newName);
        ref.getFirstChild().replace(str);
        return ref;
    }

    public static FactionReference getReference(RRStrFactionRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.STRING).getPsi().getTextRangeInParent();
        return new FactionReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="FactionGroup decl and refs">
    public static PsiElement setName(RRFactionGroupNameDecl e, String newName) {
        PsiElement newId = RRElementFactory.createId(e.getProject(), newName);
        return e.replace(newId);
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
    public static PsiElement setName(RRCultureNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createString(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRCultureNameDecl e) {
        return Util.unquote(e.getFirstChild().getText());
    }

    public static PsiElement getNameIdentifier(RRCultureNameDecl e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRCultureRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
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

    //<editor-fold desc="Religion decl and refs">
    public static PsiElement setName(RRReligionNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createString(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRReligionNameDecl e) {
        return Util.unquote(e.getFirstChild().getText());
    }

    public static PsiElement getNameIdentifier(RRReligionNameDecl e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRReligionRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static ReligionReference getReference(RRReligionRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new ReligionReference(ref, range);
    }

    public static PsiElement setName(RRStrReligionRef ref, String newName) {
        PsiElement str = RRElementFactory.createString(ref.getProject(), newName);
        ref.getFirstChild().replace(str);
        return ref;
    }

    public static ReligionReference getReference(RRStrReligionRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.STRING).getPsi().getTextRangeInParent();
        return new ReligionReference(ref, range);
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

    //<editor-fold desc="Region or Settlement ref">
    public static PsiElement setName(RRRegionOrSettlementRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static RegionOrSettlementReference getReference(RRRegionOrSettlementRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new RegionOrSettlementReference(ref, range);
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
    public static PsiElement setName(RRRegionNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRRegionNameDecl e) {
        return e.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RRRegionNameDecl e) {
        return e.getFirstChild();
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

    //<editor-fold desc="Settlement decl and refs">
    public static PsiElement setName(RRSettlementNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRSettlementNameDecl e) {
        return e.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RRSettlementNameDecl e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRSettlementRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static SettlementReference getReference(RRSettlementRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new SettlementReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Rebels">
    public static PsiElement setName(RRRebelFactionNameDef e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRRebelFactionNameDef e) {
        return e.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RRRebelFactionNameDef e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRRebelFactionRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static RebelFactionReference getReference(RRRebelFactionRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new RebelFactionReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Ancillaries decl and refs">
    public static PsiElement setName(RRAncillaryNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRAncillaryNameDecl e) {
        return e.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RRAncillaryNameDecl e) {
        return e.getFirstChild();
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
    public static PsiElement setName(RRTraitNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRTraitNameDecl e) {
        return e.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RRTraitNameDecl e) {
        return e.getFirstChild();
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
    public static PsiElement setName(RRUnitNameDecl e, String newName) {
        PsiElement elementWithDesiredChildren = RRElementFactory.createElementWithIds(e.getProject(), newName);
        return Util.replaceChildrenOf(e, elementWithDesiredChildren);
    }

    public static String getName(RRUnitNameDecl e) {
        return e.getText();
    }

    public static PsiElement getNameIdentifier(RRUnitNameDecl e) {
        return e;
    }

    public static PsiElement setName(RRUnitRef e, String newName) {
        PsiElement elementWithDesiredChildren = RRElementFactory.createElementWithIds(e.getProject(), newName);
        return Util.replaceChildrenOf(e, elementWithDesiredChildren);
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
        TextRange range = ref.getFirstChild().getTextRangeInParent();
        return new UnitReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Ethnicity decl and refs">
    public static PsiElement setName(RREthnicityNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RREthnicityNameDecl e) {
        return e.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RREthnicityNameDecl e) {
        return e.getFirstChild();
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
    public static PsiElement setName(RRAiPersonalityNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRAiPersonalityNameDecl e) {
        return e.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RRAiPersonalityNameDecl e) {
        return e.getFirstChild();
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
    public static PsiElement setName(RREthnicityMakeupNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RREthnicityMakeupNameDecl e) {
        return e.getFirstChild().getText();
    }

    public static PsiElement getNameIdentifier(RREthnicityMakeupNameDecl e) {
        return e.getFirstChild();
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
    public static PsiElement setName(RRResourceNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createString(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRResourceNameDecl e) {
        return Util.unquote(e.getFirstChild().getText());
    }

    public static PsiElement getNameIdentifier(RRResourceNameDecl e) {
        return e.getFirstChild();
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

    //<editor-fold desc="Wonder decl and refs">
    public static PsiElement setName(RRWonderNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRWonderNameDecl e) {
        return e.getText();
    }

    public static PsiElement getNameIdentifier(RRWonderNameDecl e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRWonderRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static WonderReference getReference(RRWonderRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new WonderReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Model decl and refs">
    public static PsiElement setName(RRModelNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRModelNameDecl e) {
        return e.getText();
    }

    public static PsiElement getNameIdentifier(RRModelNameDecl e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRModelRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static ModelReference getReference(RRModelRef ref) {
        TextRange range = ref.getFirstChild().getTextRangeInParent();
        return new ModelReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Mount decl and refs">
    public static PsiElement setName(RRMountNameDecl e, String newName) {
        PsiElement elementWithDesiredChildren = RRElementFactory.createElementWithIds(e.getProject(), newName);
        return Util.replaceChildrenOf(e, elementWithDesiredChildren);
    }

    public static String getName(RRMountNameDecl e) {
        return e.getText();
    }

    public static PsiElement getNameIdentifier(RRMountNameDecl e) {
        return e;
    }

    public static PsiElement setName(RRMountRef e, String newName) {
        PsiElement elementWithDesiredChildren = RRElementFactory.createElementWithIds(e.getProject(), newName);
        return Util.replaceChildrenOf(e, elementWithDesiredChildren);
    }

    public static MountReference getReference(RRMountRef ref) {
        TextRange range = new TextRange(
            ref.getFirstChild().getTextRangeInParent().getStartOffset(),
            ref.getLastChild().getTextRangeInParent().getEndOffset()
        );
        return new MountReference(ref, range);
    }

    //</editor-fold>

    //<editor-fold desc="Ship decl and refs">
    public static PsiElement setName(RRShipNameDecl e, String newName) {
        PsiElement elementWithDesiredChildren = RRElementFactory.createElementWithIds(e.getProject(), newName);
        return Util.replaceChildrenOf(e, elementWithDesiredChildren);
    }

    public static String getName(RRShipNameDecl e) {
        return e.getText();
    }

    public static PsiElement getNameIdentifier(RRShipNameDecl e) {
        return e;
    }

    public static PsiElement setName(RRShipRef e, String newName) {
        PsiElement elementWithDesiredChildren = RRElementFactory.createElementWithIds(e.getProject(), newName);
        return Util.replaceChildrenOf(e, elementWithDesiredChildren);
    }

    public static ShipReference getReference(RRShipRef ref) {
        TextRange range = new TextRange(
            ref.getFirstChild().getTextRangeInParent().getStartOffset(),
            ref.getLastChild().getTextRangeInParent().getEndOffset()
        );
        return new ShipReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Projectile decl and refs">
    public static PsiElement setName(RRProjectileNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRProjectileNameDecl e) {
        return e.getText();
    }

    public static PsiElement getNameIdentifier(RRProjectileNameDecl e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRProjectileRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static ProjectileReference getReference(RRProjectileRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new ProjectileReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Animal decl and refs">
    public static PsiElement setName(RRAnimalNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRAnimalNameDecl e) {
        return e.getText();
    }

    public static PsiElement getNameIdentifier(RRAnimalNameDecl e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRAnimalRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static AnimalReference getReference(RRAnimalRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new AnimalReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Disaster decl and refs">
    public static PsiElement setName(RRDisasterNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRDisasterNameDecl e) {
        return e.getText();
    }

    public static PsiElement getNameIdentifier(RRDisasterNameDecl e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRDisasterRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static DisasterReference getReference(RRDisasterRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new DisasterReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Counter decl and refs">
    public static PsiElement setName(RRCounterNameDecl e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRCounterNameDecl e) {
        return e.getText();
    }

    public static PsiElement getNameIdentifier(RRCounterNameDecl e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRCounterRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static CounterReference getReference(RRCounterRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new CounterReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Ancillary descriptions decl and refs">
    public static PsiElement setName(RRAncillaryDescrDef e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRAncillaryDescrDef e) {
        return e.getText();
    }

    public static PsiElement getNameIdentifier(RRAncillaryDescrDef e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRAncillaryDescrRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static AncillaryDescrReference getReference(RRAncillaryDescrRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new AncillaryDescrReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Trait descriptions decl and refs">
    public static PsiElement setName(RRTraitDescrDef e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRTraitDescrDef e) {
        return e.getText();
    }

    public static PsiElement getNameIdentifier(RRTraitDescrDef e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRTraitDescrRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static TraitDescrReference getReference(RRTraitDescrRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new TraitDescrReference(ref, range);
    }
    //</editor-fold>

    //<editor-fold desc="Rebel factions descriptions decl and refs">
    public static PsiElement setName(RRRebelFactionDescrDef e, String newName) {
        PsiElement id = RRElementFactory.createId(e.getProject(), newName);
        e.getFirstChild().replace(id);
        return e;
    }

    public static String getName(RRRebelFactionDescrDef e) {
        return e.getText();
    }

    public static PsiElement getNameIdentifier(RRRebelFactionDescrDef e) {
        return e.getFirstChild();
    }

    public static PsiElement setName(RRRebelFactionDescrRef ref, String newName) {
        PsiElement id = RRElementFactory.createId(ref.getProject(), newName);
        ref.getFirstChild().replace(id);
        return ref;
    }

    public static RebelFactionDescrReference getReference(RRRebelFactionDescrRef ref) {
        TextRange range = ref.getNode().findChildByType(RRTypes.ID).getPsi().getTextRangeInParent();
        return new RebelFactionDescrReference(ref, range);
    }
    //</editor-fold>

    public static String name(RRSettlementItem settlementItem) {
        return settlementItem.getNode().findChildByType(RRTypes.REGION_REF).getText();
    }
}
