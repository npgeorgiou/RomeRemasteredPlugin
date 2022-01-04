// This is a generated file. Not intended for manual editing.
package rr.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RRRebalanceBlock extends PsiElement {

  @Nullable
  RRListOfUnitAttributes getListOfUnitAttributes();

  @NotNull
  List<RRListOfUnitWeaponAttributes> getListOfUnitWeaponAttributesList();

  @NotNull
  List<RRMountClass> getMountClassList();

  @Nullable
  RRName_ getName_();

  @Nullable
  RRUnitDiscipline getUnitDiscipline();

  @Nullable
  RRUnitFormations getUnitFormations();

  @NotNull
  List<RRUnitSoundWhenHit> getUnitSoundWhenHitList();

  @Nullable
  RRUnitTraining getUnitTraining();

  @NotNull
  List<RRUnitWeaponInfo> getUnitWeaponInfoList();

}
