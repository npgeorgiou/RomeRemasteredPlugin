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

public class RRCapability_Impl extends ASTWrapperPsiElement implements RRCapability_ {

  public RRCapability_Impl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitCapability_(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public RRAgentType getAgentType() {
    return findChildByClass(RRAgentType.class);
  }

  @Override
  @Nullable
  public RRRequirement getRequirement() {
    return findChildByClass(RRRequirement.class);
  }

  @Override
  @Nullable
  public RRStrUnitRef getStrUnitRef() {
    return findChildByClass(RRStrUnitRef.class);
  }

  @Override
  @NotNull
  public PsiElement getInt() {
    return findNotNullChildByType(INT);
  }

}
