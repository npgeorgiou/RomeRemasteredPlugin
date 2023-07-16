package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import rr.language.RRUtil.findAllUnitsAsStrings
import rr.language.Util.unquote
import rr.language.psi.RRStrUnitRef
import rr.language.psi.RRUnitRef
import rr.language.psi.RRVisitor

class NonExistingUnit : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitUnitRef(element: RRUnitRef) {
                val all = findAllUnitsAsStrings(element.project)
                if (!all.contains(element.text)) {
                    holder.registerProblem(element, "Non existing unit", ProblemHighlightType.ERROR)
                }
            }

            override fun visitStrUnitRef(element: RRStrUnitRef) {
                val all = findAllUnitsAsStrings(element.project)
                if (!all.contains(unquote(element.text))) {
                    holder.registerProblem(element, "Non existing unit", ProblemHighlightType.ERROR)
                }
            }
        }
    }
}