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

public class RRFactionItemImpl extends ASTWrapperPsiElement implements RRFactionItem {

  public RRFactionItemImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitFactionItem(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public RRAiPersonalityRef getAiPersonalityRef() {
    return findChildByClass(RRAiPersonalityRef.class);
  }

  @Override
  @NotNull
  public List<RRCharacterItem> getCharacterItemList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRCharacterItem.class);
  }

  @Override
  @NotNull
  public List<RRCharacterRecordItem> getCharacterRecordItemList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRCharacterRecordItem.class);
  }

  @Override
  @Nullable
  public RRFactionRef getFactionRef() {
    return findChildByClass(RRFactionRef.class);
  }

  @Override
  @NotNull
  public List<RRRelativeItem> getRelativeItemList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRRelativeItem.class);
  }

  @Override
  @NotNull
  public List<RRSettlementItem> getSettlementItemList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRSettlementItem.class);
  }

  @Override
  @Nullable
  public PsiElement getId() {
    return findChildByType(ID);
  }

  @Override
  @Nullable
  public PsiElement getInt() {
    return findChildByType(INT);
  }

}
