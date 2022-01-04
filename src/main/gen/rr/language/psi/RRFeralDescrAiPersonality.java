// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRFeralDescrAiPersonality extends PsiElement {

  @NotNull
  List<RRAiBuildingPriority> getAiBuildingPriorityList();

  @NotNull
  List<RRAiDiplomaticPriority> getAiDiplomaticPriorityList();

  @NotNull
  List<RRAiMilitaryPriority> getAiMilitaryPriorityList();

  @NotNull
  List<RRAiPersonality> getAiPersonalityList();

  @NotNull
  PsiElement getFeralDescrAiPersonalityMarker();

}
