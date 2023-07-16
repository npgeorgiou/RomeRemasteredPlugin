package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import rr.language.RRUtil.findAllBuildingLevels
import rr.language.psi.RRBuildingLevel
import rr.language.psi.RRBuildingLevelRef
import rr.language.psi.RRVisitor
import java.util.stream.Collectors

class NonExistingBuildingLevel : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitBuildingLevelRef(element: RRBuildingLevelRef) {
                val all: Collection<String> = findAllBuildingLevels(element.project).stream()
                    .map { it: RRBuildingLevel -> it.buildingLevelNameDecl.text }
                    .collect(Collectors.toList())
                if (!all.contains(element.text)) {
                    holder.registerProblem(element, "Non existing building level", ProblemHighlightType.ERROR)
                }
            }
        }
    }
}