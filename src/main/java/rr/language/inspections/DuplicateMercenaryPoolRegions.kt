package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import rr.language.psi.RRMercenaryPoolRegions
import rr.language.psi.RRVisitor

class DuplicateMercenaryPoolRegions : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitMercenaryPoolRegions(element: RRMercenaryPoolRegions) {
                val seenOnce = HashSet<String>()

                for (region in element.regionRefList) {
                    if (!seenOnce.add(region.text)) {
                        holder.registerProblem(region, "Duplicate region", ProblemHighlightType.LIKE_UNUSED_SYMBOL)
                    }
                }

            }
        }
    }
}