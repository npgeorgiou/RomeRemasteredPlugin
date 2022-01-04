// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRTraitDef extends PsiElement {

  @NotNull
  List<RRCharacterType> getCharacterTypeList();

  @NotNull
  List<RRTraitLevel> getTraitLevelList();

  @Nullable
  RRTraitNameDecl getTraitNameDecl();

  @NotNull
  List<RRTraitRef> getTraitRefList();

  @Nullable
  PsiElement getInt();

}
