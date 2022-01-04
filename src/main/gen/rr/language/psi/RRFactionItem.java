// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRFactionItem extends PsiElement {

  @Nullable
  RRAiPersonalityRef getAiPersonalityRef();

  @NotNull
  List<RRCharacterItem> getCharacterItemList();

  @NotNull
  List<RRCharacterRecordItem> getCharacterRecordItemList();

  @Nullable
  RRFactionRef getFactionRef();

  @NotNull
  List<RRRelativeItem> getRelativeItemList();

  @NotNull
  List<RRSettlementItem> getSettlementItemList();

  @Nullable
  PsiElement getId();

  @Nullable
  PsiElement getInt();

}
