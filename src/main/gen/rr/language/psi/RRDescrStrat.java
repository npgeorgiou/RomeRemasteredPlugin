// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRDescrStrat extends PsiElement {

  @NotNull
  RRCampaignSection getCampaignSection();

  @NotNull
  RRDiplomacySection getDiplomacySection();

  @Nullable
  RREventsSection getEventsSection();

  @NotNull
  RRFactionsSection getFactionsSection();

  @Nullable
  RRLandmarkSection getLandmarkSection();

  @NotNull
  RRResourcesSection getResourcesSection();

  @Nullable
  RRSoundEmittersSection getSoundEmittersSection();

  @NotNull
  PsiElement getTxtFile();

}
