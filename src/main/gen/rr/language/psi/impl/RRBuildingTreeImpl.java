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

public class RRBuildingTreeImpl extends ASTWrapperPsiElement implements RRBuildingTree {

  public RRBuildingTreeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitBuildingTree(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RRBuildingLevel> getBuildingLevelList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRBuildingLevel.class);
  }

  @Override
  @NotNull
  public List<RRBuildingLevelRef> getBuildingLevelRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRBuildingLevelRef.class);
  }

  @Override
  @Nullable
  public RRBuildingTreeNameDecl getBuildingTreeNameDecl() {
    return findChildByClass(RRBuildingTreeNameDecl.class);
  }

}
