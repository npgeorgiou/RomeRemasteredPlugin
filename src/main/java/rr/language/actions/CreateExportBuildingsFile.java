package rr.language.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileChooser.ex.FileSystemTreeImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import rr.language.RRFileType;
import rr.language.RRUtil;
import rr.language.Util;
import rr.language.psi.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateExportBuildingsFile extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();

        Collection<RRBuildingTree> buildingTrees = RRUtil.findAllBuildingTrees(project);

        List<String> lines = new ArrayList<>();

        lines.add(";export_buildings.txt DO NOT REMOVE THIS LINE, IT IS NEEDED FOR THE IDE PLUGIN TO WORK");
        lines.add("");

        lines.add(";repairs, dismantles, and upgrades");
        lines.add("{generic_repair}	REPAIR");
        lines.add("{generic_repair_desc}	REPAIR DAMAGE TO BUILDING");
        lines.add("{generic_repair_desc_short}	REPAIR DAMAGE TO BUILDING");
        lines.add("{generic_dismantle}	DISMANTLE");
        lines.add("{generic_dismantle_desc}	DISMANTLE BUILDING, RECOUPING SOME OF THE VALUE OF THE BUILDING");
        lines.add("{generic_dismantle_desc_short}	DISMANTLE BUILDING, RECOUPING SOME OF THE VALUE OF THE BUILDING");
        lines.add("{settlement_upgrade_fort}	UPGRADE FORT");
        lines.add("{settlement_upgrade_fort_desc}	UPGRADE FORT TO A VILLAGE");
        lines.add("{settlement_upgrade_fort_desc_short}	UPGRADE FORT TO A VILLAGE");
        lines.add("{settlement_upgrade_village}	UPGRADE VILLAGE");
        lines.add("{settlement_upgrade_village_desc}	UPGRADE VILLAGE TO TOWN");
        lines.add("{settlement_upgrade_village_desc_short}	UPGRADE VILLAGE TO TOWN");
        lines.add("{settlement_upgrade_town}	UPGRADE TOWN");
        lines.add("{settlement_upgrade_town_desc}	UPGRADE TOWN TO A LARGE TOWN");
        lines.add("{settlement_upgrade_town_desc_short}	UPGRADE TOWN TO A LARGE TOWN");
        lines.add("{settlement_upgrade_large_town}	UPGRADE LARGE TOWN");
        lines.add("{settlement_upgrade_large_town_desc}	UPGRADE TOWN TO A CITY");
        lines.add("{settlement_upgrade_large_town_desc_short}	UPGRADE TOWN TO A CITY");
        lines.add("{settlement_upgrade_city}	UPGRADE CITY");
        lines.add("{settlement_upgrade_city_desc}	UPGRADE CITY TO A LARGE CITY");
        lines.add("{settlement_upgrade_city_desc_short}	UPGRADE CITY TO A LARGE CITY");
        lines.add("{settlement_upgrade_large_city}	UPGRADE LARGE CITY");
        lines.add("{settlement_upgrade_large_city_desc}	UPGRADE CITY TO A HUGE CITY");
        lines.add("{settlement_upgrade_large_city_desc_short}	UPGRADE CITY TO A HUGE CITY");
        lines.add("");

        lines.add(";building trees");
        for (RRBuildingTree buildingTree : buildingTrees) {
            String treeName = buildingTree.getBuildingTreeNameDecl().getText();

            lines.add("{" + treeName + "_name}  TODO: Write me");
        }
        lines.add("");

        lines.add(";building levels");
        lines.add("");

        Collection<String> cultures = RRUtil.findAllCulturesAsStrings(project);

        for (RRBuildingTree buildingTree : buildingTrees) {
            for (RRBuildingLevel buildingLevel : buildingTree.getBuildingLevelList()) {
                String levelName = buildingLevel.getBuildingLevelNameDecl().getText();

                lines.add(";" + levelName);
                lines.add("{" + levelName + "}  TODO: Write me");
                lines.add("{" + levelName + "_desc}  WARNING! This baseline description should never appear on screen!");
                lines.add("{" + levelName + "_desc_short}  WARNING! This baseline short description should never appear on screen!");
                lines.add("");

                // TODO: Rename getFactionsC to getFactionsOrCultures!!!
                List<String> allowedFactionsAndCultures = buildingLevel.getRequirementList().get(0)
                    .getBuildingConditionList().stream()
                    .filter(it -> it.getFactionsC() != null)
                    .map(it -> it.getFactionsC())
                    .flatMap(it -> it.getFactionOrCultureRefList().stream())
                    .map(it -> it.getText())
                    .collect(Collectors.toList());

                List<String> excludedCultures = cultures.stream()
                    .filter(it -> !allowedFactionsAndCultures.contains(it))
                    .collect(Collectors.toList());

                for (String excludedCulture : excludedCultures) {
                    lines.add("{" + levelName + "_" + excludedCulture + "_desc}  WARNING! This text should never appear on screen!");
                    lines.add("{" + levelName + "_" + excludedCulture + "_desc_short}  WARNING! This text should never appear on screen!");
                    lines.add("");
                }

                for (String allowedFactionOrCulture : allowedFactionsAndCultures) {
                    lines.add("{" + levelName + "_" + allowedFactionOrCulture + "}  TODO: Write me");
                    lines.add("{" + levelName + "_" + allowedFactionOrCulture + "_desc}  TODO: Write me");
                    lines.add("{" + levelName + "_" + allowedFactionOrCulture + "_desc_short}  TODO: Write me");
                    lines.add("");
                }
            }
        }

        try {
            Files.write(
                Paths.get(project.getBasePath(), "/text/export_buildings_new.txt"),
                lines,
                StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
