package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import rr.language.RRUtil.findAllCulturesAsStrings
import rr.language.Util.unquote
import rr.language.psi.RRCultureRef
import rr.language.psi.RRStrCultureRef
import rr.language.psi.RRVisitor

class NonExistingCulture : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitCultureRef(element: RRCultureRef) {
                val all = findAllCulturesAsStrings(element.project)
                if (!all.contains(element.text)) {
                    holder.registerProblem(element, "Non existing culture", ProblemHighlightType.ERROR)
                }
            }

            override fun visitStrCultureRef(element: RRStrCultureRef) {
                val all = findAllCulturesAsStrings(element.project)
                if (!all.contains(unquote(element.text))) {
                    holder.registerProblem(element, "Non existing culture", ProblemHighlightType.ERROR)
                }
            }
        }
    }
}