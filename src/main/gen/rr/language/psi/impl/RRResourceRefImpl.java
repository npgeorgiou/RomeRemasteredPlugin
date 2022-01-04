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
import rr.language.psi.references.ResourceReference;

public class RRResourceRefImpl extends ASTWrapperPsiElement implements RRResourceRef {

  public RRResourceRefImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitResourceRef(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getId() {
    return findNotNullChildByType(ID);
  }

  @Override
  public PsiElement setName(String newName) {
    return RRPsiImplUtil.setName(this, newName);
  }

  @Override
  public ResourceReference getReference() {
    return RRPsiImplUtil.getReference(this);
  }

}
