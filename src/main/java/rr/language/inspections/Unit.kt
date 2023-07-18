package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.project.Project
import rr.language.RRUtil
import rr.language.RRUtil.findAllUnitsAsStrings
import rr.language.Util.unquote
import rr.language.psi.*
import java.util.stream.Collectors

class Unit : Inspector() {
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

            override fun visitUnitDescrDef(element: RRUnitDescrDef) {
                val uiTexts = findAllUnitUiNames(element.project)

                if (!uiTexts.contains(element.text)) {
                    holder.registerProblem(
                        element,
                        "No description in export_units.txt",
                        ProblemHighlightType.ERROR
                    )
                }

                val descrName = "${element.text}_descr"
                if (!uiTexts.contains(descrName)) {
                    holder.registerProblem(
                        element,
                        "No $descrName in export_units.txt",
                        ProblemHighlightType.ERROR
                    )
                }

                val descrShortName = "${element.text}_descr_short"
                if (!uiTexts.contains(descrShortName)) {
                    holder.registerProblem(
                        element,
                        "No $descrShortName in export_units.txt",
                        ProblemHighlightType.ERROR
                    )
                }
            }

            override fun visitUnitDescrRef(element: RRUnitDescrRef) {
                if (element.reference.resolve() == null) {
                    holder.registerProblem(
                        element,
                        "Non existing unit description",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
                    )
                }
            }

            private fun findAllUnitUiNames(project: Project): List<String> {
                val file = RRUtil.findRRFile("text/export_units.txt", project)

                return file?.findChildByClass(RRExportUnits::class.java)
                    ?.exportUnitsItemList
                    ?.map { it.unitDescrRef }
                    ?.map { it!!.id.text } ?: emptyList()
            }
        }
    }
}