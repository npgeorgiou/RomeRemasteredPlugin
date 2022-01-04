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

public class RRCharacterItemImpl extends ASTWrapperPsiElement implements RRCharacterItem {

  public RRCharacterItemImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitCharacterItem(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RRAncillaryRef> getAncillaryRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRAncillaryRef.class);
  }

  @Override
  @NotNull
  public List<RRArmyUnitItem> getArmyUnitItemList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRArmyUnitItem.class);
  }

  @Override
  @NotNull
  public RRCharacterType getCharacterType() {
    return findNotNullChildByClass(RRCharacterType.class);
  }

  @Override
  @NotNull
  public RRCoordsWithXy getCoordsWithXy() {
    return findNotNullChildByClass(RRCoordsWithXy.class);
  }

  @Override
  @NotNull
  public RRName_ getName_() {
    return findNotNullChildByClass(RRName_.class);
  }

  @Override
  @NotNull
  public List<RRTraitItem> getTraitItemList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRTraitItem.class);
  }

  @Override
  @Nullable
  public PsiElement getId() {
    return findChildByType(ID);
  }

}
