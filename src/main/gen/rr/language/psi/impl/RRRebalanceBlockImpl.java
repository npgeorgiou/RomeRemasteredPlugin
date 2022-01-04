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

public class RRRebalanceBlockImpl extends ASTWrapperPsiElement implements RRRebalanceBlock {

  public RRRebalanceBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitRebalanceBlock(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public RRListOfUnitAttributes getListOfUnitAttributes() {
    return findChildByClass(RRListOfUnitAttributes.class);
  }

  @Override
  @NotNull
  public List<RRListOfUnitWeaponAttributes> getListOfUnitWeaponAttributesList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRListOfUnitWeaponAttributes.class);
  }

  @Override
  @NotNull
  public List<RRMountClass> getMountClassList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRMountClass.class);
  }

  @Override
  @Nullable
  public RRName_ getName_() {
    return findChildByClass(RRName_.class);
  }

  @Override
  @Nullable
  public RRUnitDiscipline getUnitDiscipline() {
    return findChildByClass(RRUnitDiscipline.class);
  }

  @Override
  @Nullable
  public RRUnitFormations getUnitFormations() {
    return findChildByClass(RRUnitFormations.class);
  }

  @Override
  @NotNull
  public List<RRUnitSoundWhenHit> getUnitSoundWhenHitList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRUnitSoundWhenHit.class);
  }

  @Override
  @Nullable
  public RRUnitTraining getUnitTraining() {
    return findChildByClass(RRUnitTraining.class);
  }

  @Override
  @NotNull
  public List<RRUnitWeaponInfo> getUnitWeaponInfoList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRUnitWeaponInfo.class);
  }

}
