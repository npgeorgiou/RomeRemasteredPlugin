package rr.language.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import rr.language.RRUtil.findAllBuildingTrees
import rr.language.RRUtil.findAllCulturesAsStrings
import rr.language.psi.RRBuildingCondition
import rr.language.psi.RRFactionOrCultureRef
import rr.language.psi.RRFactionsC
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

class CreateExportBuildingsFile : AnAction() {
    override fun update(e: AnActionEvent) {
        // Set the availability based on whether a project is open
        val project = e.project
        e.presentation.isEnabledAndVisible = project != null
    }

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project
        val buildingTrees = findAllBuildingTrees(project)
        val lines: MutableList<String> = ArrayList()
        val commentChar = Character.toString(0x000AC)

        lines.add(commentChar + "export_buildings.txt DO NOT REMOVE THIS LINE, IT IS NEEDED FOR THE IDE PLUGIN TO WORK")
        lines.add("")
        lines.add(commentChar + "repairs, dismantles, and upgrades")
        lines.add("{generic_repair}	REPAIR")
        lines.add("{generic_repair_desc}	REPAIR DAMAGE TO BUILDING")
        lines.add("{generic_repair_desc_short}	REPAIR DAMAGE TO BUILDING")
        lines.add("{generic_dismantle}	DISMANTLE")
        lines.add("{generic_dismantle_desc}	DISMANTLE BUILDING, RECOUPING SOME OF THE VALUE OF THE BUILDING")
        lines.add("{generic_dismantle_desc_short}	DISMANTLE BUILDING, RECOUPING SOME OF THE VALUE OF THE BUILDING")
        lines.add("{settlement_upgrade_fort}	UPGRADE FORT")
        lines.add("{settlement_upgrade_fort_desc}	UPGRADE FORT TO A VILLAGE")
        lines.add("{settlement_upgrade_fort_desc_short}	UPGRADE FORT TO A VILLAGE")
        lines.add("{settlement_upgrade_village}	UPGRADE VILLAGE")
        lines.add("{settlement_upgrade_village_desc}	UPGRADE VILLAGE TO TOWN")
        lines.add("{settlement_upgrade_village_desc_short}	UPGRADE VILLAGE TO TOWN")
        lines.add("{settlement_upgrade_town}	UPGRADE TOWN")
        lines.add("{settlement_upgrade_town_desc}	UPGRADE TOWN TO A LARGE TOWN")
        lines.add("{settlement_upgrade_town_desc_short}	UPGRADE TOWN TO A LARGE TOWN")
        lines.add("{settlement_upgrade_large_town}	UPGRADE LARGE TOWN")
        lines.add("{settlement_upgrade_large_town_desc}	UPGRADE TOWN TO A CITY")
        lines.add("{settlement_upgrade_large_town_desc_short}	UPGRADE TOWN TO A CITY")
        lines.add("{settlement_upgrade_city}	UPGRADE CITY")
        lines.add("{settlement_upgrade_city_desc}	UPGRADE CITY TO A LARGE CITY")
        lines.add("{settlement_upgrade_city_desc_short}	UPGRADE CITY TO A LARGE CITY")
        lines.add("{settlement_upgrade_large_city}	UPGRADE LARGE CITY")
        lines.add("{settlement_upgrade_large_city_desc}	UPGRADE CITY TO A HUGE CITY")
        lines.add("{settlement_upgrade_large_city_desc_short}	UPGRADE CITY TO A HUGE CITY")
        lines.add("")
        lines.add(commentChar + "building trees")

        for (buildingTree in buildingTrees) {
            val treeName = buildingTree.buildingTreeNameDecl!!.text
            lines.add("{" + treeName + "_name}  TODO: Write me")
        }

        lines.add("")
        lines.add(commentChar + "building levels")
        lines.add("")

        val cultures = findAllCulturesAsStrings(project)
        for (buildingTree in buildingTrees) {
            for (buildingLevel in buildingTree.buildingLevelList) {
                val levelName = buildingLevel.buildingLevelNameDecl.text
                lines.add(commentChar + "" + levelName)
                lines.add("{$levelName}  TODO: Write me")
                lines.add("{" + levelName + "_desc}  WARNING! This baseline description should never appear on screen!")
                lines.add("{" + levelName + "_desc_short}  WARNING! This baseline short description should never appear on screen!")
                lines.add("")

                val allowedFactionsAndCultures = buildingLevel.requirementList[0]
                    .buildingConditionList.stream()
                    .filter { it: RRBuildingCondition -> it.factionsC != null }
                    .map { it: RRBuildingCondition -> it.factionsC }
                    .flatMap { it: RRFactionsC? -> it!!.factionOrCultureRefList.stream() }
                    .map { it: RRFactionOrCultureRef -> it.text }
                    .collect(Collectors.toList())
                val excludedCultures = cultures.stream()
                    .filter { it: String -> !allowedFactionsAndCultures.contains(it) }
                    .collect(Collectors.toList())

                for (excludedCulture in excludedCultures) {
                    lines.add("{" + levelName + "_" + excludedCulture + "_desc}  WARNING! This text should never appear on screen!")
                    lines.add("{" + levelName + "_" + excludedCulture + "_desc_short}  WARNING! This text should never appear on screen!")
                    lines.add("")
                }

                for (allowedFactionOrCulture in allowedFactionsAndCultures) {
                    lines.add("{" + levelName + "_" + allowedFactionOrCulture + "}  TODO: Write me")
                    lines.add("{" + levelName + "_" + allowedFactionOrCulture + "_desc}  TODO: Write me")
                    lines.add("{" + levelName + "_" + allowedFactionOrCulture + "_desc_short}  TODO: Write me")
                    lines.add("")
                }
            }
        }
        try {
            Files.write(
                Paths.get(project!!.basePath, "/text/export_buildings_new.txt"),
                lines,
                StandardCharsets.UTF_8
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
