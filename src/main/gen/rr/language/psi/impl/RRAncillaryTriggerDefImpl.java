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

public class RRAncillaryTriggerDefImpl extends ASTWrapperPsiElement implements RRAncillaryTriggerDef {

  public RRAncillaryTriggerDefImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitAncillaryTriggerDef(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public RRAncillaryRef getAncillaryRef() {
    return findChildByClass(RRAncillaryRef.class);
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
  @Nullable
  public PsiElement getId() {
    return findChildByType(ID);
  }

  @Override
  @Nullable
  public PsiElement getInt() {
    return findChildByType(INT);
  }

}
