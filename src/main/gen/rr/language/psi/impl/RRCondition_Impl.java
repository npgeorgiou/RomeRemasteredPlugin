// This is a generated file. Not intended for manual editing.
package rr.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static rr.language.psi.RRTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import rr.language.psi.*;

public class RRCondition_Impl extends ASTWrapperPsiElement implements RRCondition_ {

  public RRCondition_Impl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitCondition_(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public RRActionStatus getActionStatus() {
    return findChildByClass(RRActionStatus.class);
  }

  @Override
  @Nullable
  public RRAttackDirection getAttackDirection() {
    return findChildByClass(RRAttackDirection.class);
  }

  @Override
  @Nullable
  public RRAutomanageType getAutomanageType() {
    return findChildByClass(RRAutomanageType.class);
  }

  @Override
  @Nullable
  public RRBattleBuildingType getBattleBuildingType() {
    return findChildByClass(RRBattleBuildingType.class);
  }

  @Override
  @Nullable
  public RRBattleSuccess getBattleSuccess() {
    return findChildByClass(RRBattleSuccess.class);
  }

  @Override
  @Nullable
  public RRCharacterActionAdvice getCharacterActionAdvice() {
    return findChildByClass(RRCharacterActionAdvice.class);
  }

  @Override
  @Nullable
  public RRCharacterType getCharacterType() {
    return findChildByClass(RRCharacterType.class);
  }

  @Override
  @Nullable
  public RRConflictType getConflictType() {
    return findChildByClass(RRConflictType.class);
  }

  @Override
  @Nullable
  public RRCoords getCoords() {
    return findChildByClass(RRCoords.class);
  }

  @Override
  @Nullable
  public RRDiplomaticStance getDiplomaticStance() {
    return findChildByClass(RRDiplomaticStance.class);
  }

  @Override
  @Nullable
  public RRDisasterType getDisasterType() {
    return findChildByClass(RRDisasterType.class);
  }

  @Override
  @Nullable
  public RRGateDefenceType getGateDefenceType() {
    return findChildByClass(RRGateDefenceType.class);
  }

  @Override
  @Nullable
  public RRHideType getHideType() {
    return findChildByClass(RRHideType.class);
  }

  @Override
  @Nullable
  public RRLoyaltyToGovernorLevel getLoyaltyToGovernorLevel() {
    return findChildByClass(RRLoyaltyToGovernorLevel.class);
  }

  @Override
  @Nullable
  public RRMountClass getMountClass() {
    return findChildByClass(RRMountClass.class);
  }

  @Override
  @Nullable
  public RRName_ getName_() {
    return findChildByClass(RRName_.class);
  }

  @Override
  @Nullable
  public RRSettlementLevel getSettlementLevel() {
    return findChildByClass(RRSettlementLevel.class);
  }

  @Override
  @Nullable
  public RRSiegeEngineClass getSiegeEngineClass() {
    return findChildByClass(RRSiegeEngineClass.class);
  }

  @Override
  @Nullable
  public RRSpecialAbility getSpecialAbility() {
    return findChildByClass(RRSpecialAbility.class);
  }

  @Override
  @Nullable
  public RRSuccessLevel getSuccessLevel() {
    return findChildByClass(RRSuccessLevel.class);
  }

  @Override
  @Nullable
  public RRTaxLevel getTaxLevel() {
    return findChildByClass(RRTaxLevel.class);
  }

  @Override
  @Nullable
  public RRTowerDefenceType getTowerDefenceType() {
    return findChildByClass(RRTowerDefenceType.class);
  }

  @Override
  @Nullable
  public RRUnitCategory getUnitCategory() {
    return findChildByClass(RRUnitCategory.class);
  }

  @Override
  @Nullable
  public RRUnitClass getUnitClass() {
    return findChildByClass(RRUnitClass.class);
  }

  @Override
  @Nullable
  public RRUnitFormation getUnitFormation() {
    return findChildByClass(RRUnitFormation.class);
  }

  @Override
  @Nullable
  public PsiElement getBoolean() {
    return findChildByType(BOOLEAN);
  }

  @Override
  @Nullable
  public PsiElement getFloat() {
    return findChildByType(FLOAT);
  }

  @Override
  @Nullable
  public PsiElement getId() {
    return findChildByType(ID);
  }

  @Override
  @Nullable
  public PsiElement getInt() {
    return findChildByType(INT);
  }

  @Override
  @Nullable
  public PsiElement getString() {
    return findChildByType(STRING);
  }

  @Override
  @Nullable
  public PsiElement getTxtFile() {
    return findChildByType(TXT_FILE);
  }

}
