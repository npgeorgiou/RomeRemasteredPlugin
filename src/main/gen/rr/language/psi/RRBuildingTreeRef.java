// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import rr.language.psi.references.BuildingTreeReference;

public interface RRBuildingTreeRef extends PsiElement {

  @NotNull
  PsiElement getId();

  PsiElement setName(String newName);

  BuildingTreeReference getReference();

}
