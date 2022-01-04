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

public class RRDescrCulturesImpl extends ASTWrapperPsiElement implements RRDescrCultures {

  public RRDescrCulturesImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitDescrCultures(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RRKVFValue> getKVFValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRKVFValue.class);
  }

  @Override
  @NotNull
  public List<RRCultureNameDecl> getCultureNameDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRCultureNameDecl.class);
  }

  @Override
  @NotNull
  public PsiElement getDescrCulturesMarker() {
    return findNotNullChildByType(DESCR_CULTURES_MARKER);
  }

  @Override
  @NotNull
  public PsiElement getString() {
    return findNotNullChildByType(STRING);
  }

}
