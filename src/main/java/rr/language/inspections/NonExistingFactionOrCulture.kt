package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import rr.language.RRUtil.findAllCulturesAsStrings
import rr.language.RRUtil.findAllFactionsAsStrings
import rr.language.psi.RRFactionOrCultureRef
import rr.language.psi.RRVisitor

class NonExistingFactionOrCulture : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitFactionOrCultureRef(element: RRFactionOrCultureRef) {
                var items: Collection<String?> = findAllFactionsAsStrings(element.project)
                if (!items.contains(element.text)) {
                    items = findAllCulturesAsStrings(element.project)
                    if (!items.contains(element.text)) {
                        holder.registerProblem(element, "Non existing faction or culture", ProblemHighlightType.ERROR)
                    }
                }
            }
        }
    }
}