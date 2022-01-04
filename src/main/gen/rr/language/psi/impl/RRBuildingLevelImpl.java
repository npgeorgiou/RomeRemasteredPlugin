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

public class RRBuildingLevelImpl extends ASTWrapperPsiElement implements RRBuildingLevel {

  public RRBuildingLevelImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitBuildingLevel(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public RRBuildingLevelNameDecl getBuildingLevelNameDecl() {
    return findNotNullChildByClass(RRBuildingLevelNameDecl.class);
  }

  @Override
  @Nullable
  public RRBuildingLevelRef getBuildingLevelRef() {
    return findChildByClass(RRBuildingLevelRef.class);
  }

  @Override
  @NotNull
  public List<RRCapability_> getCapability_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRCapability_.class);
  }

  @Override
  @NotNull
  public List<RRRequirement> getRequirementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRRequirement.class);
  }

  @Override
  @NotNull
  public RRSettlementLevel getSettlementLevel() {
    return findNotNullChildByClass(RRSettlementLevel.class);
  }

}
