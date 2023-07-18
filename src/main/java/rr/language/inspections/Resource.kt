package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.search.searches.ReferencesSearch
import rr.language.RRUtil.findAllResourcesAsStrings
import rr.language.psi.RRResourceNameDecl
import rr.language.psi.RRResourceRef
import rr.language.psi.RRVisitor

class Resource : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitResourceNameDecl(element: RRResourceNameDecl) {
                if (ReferencesSearch.search(element).findAll().isEmpty()) {
                    holder.registerProblem(
                        element,
                        "Resource not used anywhere",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
                    )
                }
            }

            override fun visitResourceRef(element: RRResourceRef) {
                if (element.reference.resolve() == null) {
                    holder.registerProblem(element, "Non existing resource", ProblemHighlightType.ERROR)
                }
            }
        }
    }
}