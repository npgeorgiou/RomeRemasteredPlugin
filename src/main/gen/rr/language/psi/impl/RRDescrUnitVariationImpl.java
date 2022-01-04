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

public class RRDescrUnitVariationImpl extends ASTWrapperPsiElement implements RRDescrUnitVariation {

  public RRDescrUnitVariationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitDescrUnitVariation(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RREthnicity_> getEthnicity_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RREthnicity_.class);
  }

  @Override
  @NotNull
  public List<RREthnicityMakeup_> getEthnicityMakeup_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RREthnicityMakeup_.class);
  }

  @Override
  @NotNull
  public List<RRSkin_> getSkin_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRSkin_.class);
  }

}
