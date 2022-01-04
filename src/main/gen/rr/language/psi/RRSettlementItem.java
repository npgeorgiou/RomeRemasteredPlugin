// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRSettlementItem extends PsiElement {

  @NotNull
  List<RRBuildingItem> getBuildingItemList();

  @NotNull
  RRFactionRef getFactionRef();

  @NotNull
  RRRegionRef getRegionRef();

  @NotNull
  RRSettlementLevel getSettlementLevel();

  @NotNull
  RRSettlementPlan getSettlementPlan();

  String name();

}
