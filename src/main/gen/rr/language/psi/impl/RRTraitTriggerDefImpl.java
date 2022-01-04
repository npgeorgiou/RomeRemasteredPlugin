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

public class RRTraitTriggerDefImpl extends ASTWrapperPsiElement implements RRTraitTriggerDef {

  public RRTraitTriggerDefImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitTraitTriggerDef(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public RRCompositeCondition getCompositeCondition() {
    return findChildByClass(RRCompositeCondition.class);
  }

  @Override
  @Nullable
  public RREvent getEvent() {
    return findChildByClass(RREvent.class);
  }

  @Override
  @NotNull
  public List<RRTraitRef> getTraitRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRTraitRef.class);
  }

  @Override
  @Nullable
  public PsiElement getId() {
    return findChildByType(ID);
  }

}
