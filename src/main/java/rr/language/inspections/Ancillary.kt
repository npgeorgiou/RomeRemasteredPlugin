package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.project.Project
import rr.language.RRUtil.findAllAncillaries
import rr.language.RRUtil.findAllAncillaryDescriptions
import rr.language.RRUtil.findRRFile
import rr.language.psi.*
import java.util.*
import java.util.stream.Collectors

class Ancillary : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitAncillaryDescrDef(element: RRAncillaryDescrDef) {
                val uiTexts = findAllAncillaryUiNames(element.project)
                if (!uiTexts.contains(element.text)) {
                    holder.registerProblem(
                        element,
                        "No description in export_ancillaries.txt",
                        ProblemHighlightType.ERROR
                    )
                }
            }

            override fun visitAncillaryNameDecl(element: RRAncillaryNameDecl) {
                val uiTexts = findAllAncillaryUiNames(element.project)
                if (!uiTexts.contains(element.text)) {
                    holder.registerProblem(
                        element,
                        "No description in export_ancillaries.txt",
                        ProblemHighlightType.ERROR
                    )
                }
            }

            override fun visitAncillaryDescrRef(element: RRAncillaryDescrRef) {
                if (element.reference.resolve() == null) {
                    holder.registerProblem(
                        element,
                        "Non existing ancillary description",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
                    )
                }
            }

            override fun visitAncillaryRef(element: RRAncillaryRef) {
                if (element.reference.resolve() == null) {
                    holder.registerProblem(element, "Non existing ancillary", ProblemHighlightType.ERROR)
                }
            }

            private fun findAllAncillaryUiNames(project: Project): List<String> {
                val file = findRRFile("text/export_ancillaries.txt", project)

                return file?.findChildByClass(RRExportAncillaries::class.java)
                    ?.exportAncillariesItemList
                    ?.map { it.ancillaryDescrRef }
                    ?.map { it!!.id.text } ?: emptyList()
            }
        }
    }
}