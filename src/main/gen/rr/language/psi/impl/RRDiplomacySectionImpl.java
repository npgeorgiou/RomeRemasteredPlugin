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

public class RRDiplomacySectionImpl extends ASTWrapperPsiElement implements RRDiplomacySection {

  public RRDiplomacySectionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RRVisitor visitor) {
    visitor.visitDiplomacySection(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RRVisitor) accept((RRVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RRCoreAttitudesItem> getCoreAttitudesItemList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRCoreAttitudesItem.class);
  }

  @Override
  @NotNull
  public List<RRFactionRelationshipsItem> getFactionRelationshipsItemList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRFactionRelationshipsItem.class);
  }

  @Override
  @NotNull
  public List<RRSuperfactionSetupItem> getSuperfactionSetupItemList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RRSuperfactionSetupItem.class);
  }

}
