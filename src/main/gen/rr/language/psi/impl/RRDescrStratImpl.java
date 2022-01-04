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

public class RRDescrStratImpl extends ASTWrapperPsiElement implements RRDescrStrat {

  public RRDescrStratImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitDescrStrat(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public RRCampaignSection getCampaignSection() {
    return findNotNullChildByClass(RRCampaignSection.class);
  }

  @Override
  @NotNull
  public RRDiplomacySection getDiplomacySection() {
    return findNotNullChildByClass(RRDiplomacySection.class);
  }

  @Override
  @Nullable
  public RREventsSection getEventsSection() {
    return findChildByClass(RREventsSection.class);
  }

  @Override
  @NotNull
  public RRFactionsSection getFactionsSection() {
    return findNotNullChildByClass(RRFactionsSection.class);
  }

  @Override
  @Nullable
  public RRLandmarkSection getLandmarkSection() {
    return findChildByClass(RRLandmarkSection.class);
  }

  @Override
  @NotNull
  public RRResourcesSection getResourcesSection() {
    return findNotNullChildByClass(RRResourcesSection.class);
  }

  @Override
  @Nullable
  public RRSoundEmittersSection getSoundEmittersSection() {
    return findChildByClass(RRSoundEmittersSection.class);
  }

  @Override
  @NotNull
  public PsiElement getTxtFile() {
    return findNotNullChildByType(TXT_FILE);
  }

}
