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

public class RRNamesForFactionImpl extends ASTWrapperPsiElement implements RRNamesForFaction {

  public RRNamesForFactionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitNamesForFaction(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public RRFactionRef getFactionRef() {
    return findNotNullChildByClass(RRFactionRef.class);
  }

  @Override
  @NotNull
  public List<RRName_> getName_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRName_.class);
  }

}
