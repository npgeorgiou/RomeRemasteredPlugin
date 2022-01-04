// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRAncillaryDef extends PsiElement {

  @Nullable
  RRAncillaryNameDecl getAncillaryNameDecl();

  @NotNull
  List<RRAncillaryRef> getAncillaryRefList();

  @NotNull
  List<RRCultureRef> getCultureRefList();

}
