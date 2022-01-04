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

public class RRUnitNameDeclImpl extends ASTWrapperPsiElement implements RRUnitNameDecl {

  public RRUnitNameDeclImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitUnitNameDecl(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public PsiElement setName(String newName) {
    return RRPsiImplUtil.setName(this, newName);
  }

  @Override
  public String getName() {
    return RRPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return RRPsiImplUtil.getNameIdentifier(this);
  }

}
