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

public class RRBuildingConditionImpl extends ASTWrapperPsiElement implements RRBuildingCondition {

  public RRBuildingConditionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitBuildingCondition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public RRAliasC getAliasC() {
    return findChildByClass(RRAliasC.class);
  }

  @Override
  @Nullable
  public RRBuildingC getBuildingC() {
    return findChildByClass(RRBuildingC.class);
  }

  @Override
  @Nullable
  public RRBuildingMinLevelC getBuildingMinLevelC() {
    return findChildByClass(RRBuildingMinLevelC.class);
  }

  @Override
  @Nullable
  public RRFactionsC getFactionsC() {
    return findChildByClass(RRFactionsC.class);
  }

  @Override
  @Nullable
  public RRHiddenResourceC getHiddenResourceC() {
    return findChildByClass(RRHiddenResourceC.class);
  }

  @Override
  @Nullable
  public RRIsToggledC getIsToggledC() {
    return findChildByClass(RRIsToggledC.class);
  }

  @Override
  @Nullable
  public RRMajorEventC getMajorEventC() {
    return findChildByClass(RRMajorEventC.class);
  }

  @Override
  @Nullable
  public RRNoTagC getNoTagC() {
    return findChildByClass(RRNoTagC.class);
  }

  @Override
  @Nullable
  public RRResourceC getResourceC() {
    return findChildByClass(RRResourceC.class);
  }

}
