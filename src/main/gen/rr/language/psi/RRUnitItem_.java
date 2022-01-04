// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRUnitItem_ extends PsiElement {

  @NotNull
  List<RREthnicityMakeupRef> getEthnicityMakeupRefList();

  @NotNull
  List<RRFactionOrCultureRef> getFactionOrCultureRefList();

  @NotNull
  List<RRFactionRef> getFactionRefList();

  @NotNull
  List<RRRebalanceBlock> getRebalanceBlockList();

  @Nullable
  RRUnitCategory getUnitCategory();

  @Nullable
  RRUnitClass getUnitClass();

  @NotNull
  RRUnitNameDecl getUnitNameDecl();

}
