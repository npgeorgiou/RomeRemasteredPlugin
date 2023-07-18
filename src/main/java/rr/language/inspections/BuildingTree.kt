package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import rr.language.RRUtil
import rr.language.psi.*

class BuildingTree : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitBuildingTreeNameDecl(element: RRBuildingTreeNameDecl) {
                val name = element.name
                val uiNames = RRUtil.findAllBuildingTreesUiNames(element.project).map { it.lowercase() }

                if (!uiNames.contains("${name}_name".lowercase())) {
                    holder.registerProblem(element, "Non existing description", ProblemHighlightType.WARNING)
                }
            }

            override fun visitBuildingTreeRef(element: RRBuildingTreeRef) {
                if (element.reference.resolve() == null) {
                    holder.registerProblem(element, "Non existing building tree", ProblemHighlightType.ERROR)
                }
            }

            override fun visitBuriedBuildingTreeOrLevelRef(element: RRBuriedBuildingTreeOrLevelRef) {
                if (element.reference.resolve() == null) {
                    holder.registerProblem(
                        element,
                        "Description not used anywhere",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
                    )
                }
            }

            override fun visitPlugin(element: RRPlugin) {
                holder.registerProblem(element, "This is not used by the game", ProblemHighlightType.LIKE_UNUSED_SYMBOL)
            }
        }
    }
}