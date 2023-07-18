package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import rr.language.RRUtil
import rr.language.RRUtil.findAllBuildingLevels
import rr.language.psi.RRBuildingLevel
import rr.language.psi.RRBuildingLevelRef
import rr.language.psi.RRVisitor
import java.util.stream.Collectors

class BuildingLevel : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitBuildingLevel(element: RRBuildingLevel) {
                val levelName = element.buildingLevelNameDecl.name
                val uiTexts = RRUtil.findAllBuildingTreesUiNames(element.project).map { it.lowercase() }

                fun checkUiTextsFor(uiName: String) {
                    val needle = uiName.lowercase()

                    if (!uiTexts.contains(needle)) {
                        holder.registerProblem(
                            element.buildingLevelNameDecl,
                            "No $uiName in export_buildings.txt",
                            ProblemHighlightType.WARNING
                        )
                    }

                    val descName = "${needle}_desc"
                    if (!uiTexts.contains(descName)) {
                        holder.registerProblem(
                            element.buildingLevelNameDecl,
                            "No $descName in export_buildings.txt",
                            ProblemHighlightType.WARNING
                        )
                    }

                    val descShortName = "${needle}_desc_short"
                    if (!uiTexts.contains(descShortName)) {
                        holder.registerProblem(
                            element.buildingLevelNameDecl,
                            "No $descShortName in export_buildings.txt",
                            ProblemHighlightType.WARNING
                        )
                    }
                }

                // disabled for now
//                checkUiTextsFor(levelName)

                val levelRequirement = element.requirementList.firstOrNull()
                if (levelRequirement != null) {
                    val allowedFactionsAndCultures = levelRequirement.buildingConditionList
                        .filter { it.factionsC != null }
                        .map { it.factionsC }
                        .flatMap { it!!.factionOrCultureRefList }
                        .map { it.text }

                    allowedFactionsAndCultures.forEach {
//                        checkUiTextsFor("${levelName}_$it")
                    }
                }
            }

            override fun visitBuildingLevelRef(element: RRBuildingLevelRef) {
                if (element.reference.resolve() == null) {
                    holder.registerProblem(element, "Non existing building level", ProblemHighlightType.ERROR)
                }
            }
        }
    }
}