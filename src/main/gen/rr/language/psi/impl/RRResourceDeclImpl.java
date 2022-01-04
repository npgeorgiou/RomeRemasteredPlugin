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

public class RRResourceDeclImpl extends ASTWrapperPsiElement implements RRResourceDecl {

  public RRResourceDeclImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitResourceDecl(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RRKVFItem> getKVFItemList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRKVFItem.class);
  }

  @Override
  @NotNull
  public RRResourceNameDecl getResourceNameDecl() {
    return findNotNullChildByClass(RRResourceNameDecl.class);
  }

  @Override
  @NotNull
  public RRResourceType getResourceType() {
    return findNotNullChildByClass(RRResourceType.class);
  }

  @Override
  @NotNull
  public PsiElement getString() {
    return findNotNullChildByType(STRING);
  }

}
