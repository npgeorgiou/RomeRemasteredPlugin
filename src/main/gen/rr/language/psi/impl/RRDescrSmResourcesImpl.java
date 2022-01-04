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

public class RRDescrSmResourcesImpl extends ASTWrapperPsiElement implements RRDescrSmResources {

  public RRDescrSmResourcesImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitDescrSmResources(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RRResourceDecl> getResourceDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRResourceDecl.class);
  }

  @Override
  @NotNull
  public PsiElement getDescrSmResourcesMarker() {
    return findNotNullChildByType(DESCR_SM_RESOURCES_MARKER);
  }

  @Override
  @NotNull
  public PsiElement getString() {
    return findNotNullChildByType(STRING);
  }

}
