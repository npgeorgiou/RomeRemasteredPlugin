// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRRegionDef extends PsiElement {

  @NotNull
  RRFactionRef getFactionRef();

  @NotNull
  RRRegionNameDecl getRegionNameDecl();

  @NotNull
  List<RRResourceRef> getResourceRefList();

}
