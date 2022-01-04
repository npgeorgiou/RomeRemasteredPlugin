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

public class RRUnitWeaponInfoImpl extends ASTWrapperPsiElement implements RRUnitWeaponInfo {

  public RRUnitWeaponInfoImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitUnitWeaponInfo(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public RRUnitDamageSoundType getUnitDamageSoundType() {
    return findChildByClass(RRUnitDamageSoundType.class);
  }

  @Override
  @Nullable
  public RRUnitDamageType getUnitDamageType() {
    return findChildByClass(RRUnitDamageType.class);
  }

  @Override
  @Nullable
  public RRUnitTechType getUnitTechType() {
    return findChildByClass(RRUnitTechType.class);
  }

  @Override
  @Nullable
  public RRUnitWeaponType getUnitWeaponType() {
    return findChildByClass(RRUnitWeaponType.class);
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

}
