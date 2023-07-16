package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import rr.language.RRUtil.findAllBuildingTrees
import rr.language.psi.RRBuildingTree
import rr.language.psi.RRBuildingTreeRef
import rr.language.psi.RRVisitor
import java.util.stream.Collectors

class NonExistingBuildingTree : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitBuildingTreeRef(element: RRBuildingTreeRef) {
                val all: Collection<String> = findAllBuildingTrees(element.project).stream()
                    .map { it: RRBuildingTree ->
                        it.buildingTreeNameDecl!!
                            .text
                    }
                    .collect(Collectors.toList())
                if (!all.contains(element.text)) {
                    holder.registerProblem(element, "Non existing building tree", ProblemHighlightType.ERROR)
                }
            }
        }
    }
}