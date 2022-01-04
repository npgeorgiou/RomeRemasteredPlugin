// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRExportDescrBuildings extends PsiElement {

  @NotNull
  List<RRBuildingTree> getBuildingTreeList();

  @NotNull
  List<RRConditionAlias> getConditionAliasList();

  @NotNull
  RRTags_ getTags_();

}
