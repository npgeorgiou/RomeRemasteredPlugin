// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRBuildingLevel extends PsiElement {

  @NotNull
  RRBuildingLevelNameDecl getBuildingLevelNameDecl();

  @Nullable
  RRBuildingLevelRef getBuildingLevelRef();

  @NotNull
  List<RRCapability_> getCapability_List();

  @NotNull
  List<RRRequirement> getRequirementList();

  @Nullable
  RRSettlementLevel getSettlementLevel();

}
