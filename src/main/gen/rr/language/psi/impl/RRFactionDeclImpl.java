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

public class RRFactionDeclImpl extends ASTWrapperPsiElement implements RRFactionDecl {

  public RRFactionDeclImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitFactionDecl(this);
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
  public RRFactionNameDecl getFactionNameDecl() {
    return findNotNullChildByClass(RRFactionNameDecl.class);
  }

  @Override
  @NotNull
  public RRStrCultureRef getStrCultureRef() {
    return findNotNullChildByClass(RRStrCultureRef.class);
  }

  @Override
  @NotNull
  public RRStrEthnicityRef getStrEthnicityRef() {
    return findNotNullChildByClass(RRStrEthnicityRef.class);
  }

  @Override
  @NotNull
  public List<RRStrFactionGroupRef> getStrFactionGroupRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRStrFactionGroupRef.class);
  }

}
