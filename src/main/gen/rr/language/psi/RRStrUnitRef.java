// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import rr.language.psi.references.UnitReference;

public interface RRStrUnitRef extends PsiElement {

  @NotNull
  PsiElement getString();

  PsiElement setName(String newName);

  UnitReference getReference();

}
