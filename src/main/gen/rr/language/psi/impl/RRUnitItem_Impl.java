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

public class RRUnitItem_Impl extends ASTWrapperPsiElement implements RRUnitItem_ {

  public RRUnitItem_Impl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitUnitItem_(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RREthnicityMakeupRef> getEthnicityMakeupRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RREthnicityMakeupRef.class);
  }

  @Override
  @NotNull
  public List<RRFactionOrCultureRef> getFactionOrCultureRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRFactionOrCultureRef.class);
  }

  @Override
  @NotNull
  public List<RRFactionRef> getFactionRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRFactionRef.class);
  }

  @Override
  @NotNull
  public List<RRRebalanceBlock> getRebalanceBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRRebalanceBlock.class);
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
  @NotNull
  public RRUnitNameDecl getUnitNameDecl() {
    return findNotNullChildByClass(RRUnitNameDecl.class);
  }

}
