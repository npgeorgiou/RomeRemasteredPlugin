// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRCharacterItem extends PsiElement {

  @NotNull
  List<RRAncillaryRef> getAncillaryRefList();

  @NotNull
  List<RRArmyUnitItem> getArmyUnitItemList();

  @NotNull
  RRCharacterType getCharacterType();

  @NotNull
  RRCoordsWithXy getCoordsWithXy();

  @NotNull
  RRName_ getName_();

  @NotNull
  List<RRTraitItem> getTraitItemList();

  @Nullable
  PsiElement getId();

}
