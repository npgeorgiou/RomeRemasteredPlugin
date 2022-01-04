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

public class RRSettlementItemImpl extends ASTWrapperPsiElement implements RRSettlementItem {

  public RRSettlementItemImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitSettlementItem(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RRBuildingItem> getBuildingItemList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRBuildingItem.class);
  }

  @Override
  @NotNull
  public RRFactionRef getFactionRef() {
    return findNotNullChildByClass(RRFactionRef.class);
  }

  @Override
  @NotNull
  public RRRegionRef getRegionRef() {
    return findNotNullChildByClass(RRRegionRef.class);
  }

  @Override
  @NotNull
  public RRSettlementLevel getSettlementLevel() {
    return findNotNullChildByClass(RRSettlementLevel.class);
  }

  @Override
  @NotNull
  public RRSettlementPlan getSettlementPlan() {
    return findNotNullChildByClass(RRSettlementPlan.class);
  }

  @Override
  public String name() {
    return RRPsiImplUtil.name(this);
  }

}
