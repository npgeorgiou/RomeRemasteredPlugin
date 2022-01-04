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

public class RRFeralDescrAiPersonalityImpl extends ASTWrapperPsiElement implements RRFeralDescrAiPersonality {

  public RRFeralDescrAiPersonalityImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitFeralDescrAiPersonality(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RRAiBuildingPriority> getAiBuildingPriorityList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRAiBuildingPriority.class);
  }

  @Override
  @NotNull
  public List<RRAiDiplomaticPriority> getAiDiplomaticPriorityList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRAiDiplomaticPriority.class);
  }

  @Override
  @NotNull
  public List<RRAiMilitaryPriority> getAiMilitaryPriorityList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRAiMilitaryPriority.class);
  }

  @Override
  @NotNull
  public List<RRAiPersonality> getAiPersonalityList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRAiPersonality.class);
  }

  @Override
  @NotNull
  public PsiElement getFeralDescrAiPersonalityMarker() {
    return findNotNullChildByType(FERAL_DESCR_AI_PERSONALITY_MARKER);
  }

}
