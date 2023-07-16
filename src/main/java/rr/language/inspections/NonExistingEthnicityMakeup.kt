package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import rr.language.RRUtil.findAllEthnicityMakeupsAsStrings
import rr.language.psi.RREthnicityMakeupRef
import rr.language.psi.RRVisitor

class NonExistingEthnicityMakeup : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitEthnicityMakeupRef(element: RREthnicityMakeupRef) {
                val allEthnicityMakeups = findAllEthnicityMakeupsAsStrings(element.project)
                if (!allEthnicityMakeups.contains(element.text)) {
                    holder.registerProblem(element, "Non existing ethnicity makeup", ProblemHighlightType.ERROR)
                }
            }
        }
    }
}