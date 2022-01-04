// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRDescrCultures extends PsiElement {

  @NotNull
  List<RRKVFValue> getKVFValueList();

  @NotNull
  List<RRCultureNameDecl> getCultureNameDeclList();

  @NotNull
  PsiElement getDescrCulturesMarker();

  @NotNull
  PsiElement getString();

}
