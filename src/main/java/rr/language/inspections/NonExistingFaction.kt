package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import rr.language.RRUtil.findAllFactionsAsStrings
import rr.language.psi.RRFactionRef
import rr.language.psi.RRVisitor

class NonExistingFaction : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitFactionRef(element: RRFactionRef) {
                val items: Collection<String> = findAllFactionsAsStrings(element.project)
                if (!items.contains(element.text)) {
                    holder.registerProblem(element, "Non existing faction", ProblemHighlightType.ERROR)
                }
            }
        }
    }
}