// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRFactionDecl extends PsiElement {

  @NotNull
  List<RRKVFItem> getKVFItemList();

  @NotNull
  RRFactionNameDecl getFactionNameDecl();

  @NotNull
  RRStrCultureRef getStrCultureRef();

  @NotNull
  RRStrEthnicityRef getStrEthnicityRef();

  @NotNull
  List<RRStrFactionGroupRef> getStrFactionGroupRefList();

}
