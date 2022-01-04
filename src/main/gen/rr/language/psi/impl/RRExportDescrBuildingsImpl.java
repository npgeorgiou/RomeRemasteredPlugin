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

public class RRExportDescrBuildingsImpl extends ASTWrapperPsiElement implements RRExportDescrBuildings {

  public RRExportDescrBuildingsImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitExportDescrBuildings(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RRBuildingTree> getBuildingTreeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRBuildingTree.class);
  }

  @Override
  @NotNull
  public List<RRConditionAlias> getConditionAliasList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRConditionAlias.class);
  }

  @Override
  @NotNull
  public RRTags_ getTags_() {
    return findNotNullChildByClass(RRTags_.class);
  }

}
