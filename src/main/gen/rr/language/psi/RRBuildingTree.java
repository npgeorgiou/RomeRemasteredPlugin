// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRBuildingTree extends PsiElement {

  @NotNull
  List<RRBuildingLevel> getBuildingLevelList();

  @NotNull
  List<RRBuildingLevelRef> getBuildingLevelRefList();

  @Nullable
  RRBuildingTreeNameDecl getBuildingTreeNameDecl();

}
