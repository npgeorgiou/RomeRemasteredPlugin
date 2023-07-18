package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.project.Project
import rr.language.RRUtil
import rr.language.RRUtil.findAllTraitDescriptions
import rr.language.psi.*
import java.util.stream.Collectors

class Traits : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitTraitDescrDef(element: RRTraitDescrDef) {
                val uiTexts = findAllTraitUiNames(element.project)
                if (!uiTexts.contains(element.text)) {
                    holder.registerProblem(
                        element,
                        "No description in export_vnvs.txt",
                        ProblemHighlightType.ERROR
                    )
                }
            }

            override fun visitTraitDescrRef(element: RRTraitDescrRef) {
                val all: Collection<String> = findAllTraitDescriptions(element.project)
                    .map { it.text }

                if (!all.contains(element.text)) {
                    holder.registerProblem(
                        element,
                        "Non existing trait description",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
                    )
                }
            }

            override fun visitTraitRef(element: RRTraitRef) {
                if (element.reference.resolve() == null) {
                    holder.registerProblem(element, "Non existing trait", ProblemHighlightType.ERROR)
                }
            }

            private fun findAllTraitUiNames(project: Project): List<String> {
                val file = RRUtil.findRRFile("text/export_vnvs.txt", project) ?: return emptyList()

                return file.findChildByClass(RRExportVnvs::class.java)!!
                    .exportVnvsItemList
                    .map { it.traitDescrRef }
                    .map { it!!.id.text }
            }
        }
    }
}