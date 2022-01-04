// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRTraitTriggerDef extends PsiElement {

  @Nullable
  RRCompositeCondition getCompositeCondition();

  @Nullable
  RREvent getEvent();

  @NotNull
  List<RRTraitRef> getTraitRefList();

  @Nullable
  PsiElement getId();

}
