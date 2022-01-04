// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRKVFValue extends PsiElement {

  @Nullable
  RRKVFArray getKVFArray();

  @Nullable
  RRKVFItem getKVFItem();

  @Nullable
  RRKVFObject getKVFObject();

  @Nullable
  PsiElement getBoolean();

  @Nullable
  PsiElement getFloat();

  @Nullable
  PsiElement getInt();

  @Nullable
  PsiElement getString();

}
