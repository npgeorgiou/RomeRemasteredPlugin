package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.project.Project
import rr.language.RRUtil.findAllTraitDescriptions
import rr.language.RRUtil.findAllTraitsAsStrings
import rr.language.RRUtil.findRRFile
import rr.language.psi.*
import java.util.*
import java.util.stream.Collectors

class Traits : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitTraitDescrDef(element: RRTraitDescrDef) {
                val uiTexts = findAllTraitUiNames(element.project)
                if (!uiTexts.contains(element.text)) {
                    holder.registerProblem(
                        element,
                        "No description in rebel_faction_descr.txt",
                        ProblemHighlightType.ERROR
                    )
                }
            }

            override fun visitTraitDescrRef(element: RRTraitDescrRef) {
                val all: Collection<String> = findAllTraitDescriptions(element.project).stream()
                    .map { it: RRTraitDescrDef -> it.text }
                    .collect(Collectors.toList())
                if (!all.contains(element.text)) {
                    holder.registerProblem(
                        element,
                        "Non existing trait description",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
                    )
                }
            }

            override fun visitTraitRef(element: RRTraitRef) {
                val all = findAllTraitsAsStrings(element.project)
                if (!all.contains(element.text)) {
                    holder.registerProblem(element, "Non existing trait", ProblemHighlightType.ERROR)
                }
            }

            private fun findAllTraitUiNames(project: Project): List<String> {
                val file = findRRFile("text/export_vnvs.txt", project)
                return Optional.ofNullable(file)
                    .map { it: RRFile ->
                        it.findChildByClass(
                            RRExportVnvs::class.java
                        )
                    }
                    .map { it: RRExportVnvs? -> it!!.exportVnvsItemList }
                    .orElse(ArrayList()).stream()
                    .map { it: RRExportVnvsItem -> it.traitDescrRef }
                    .map { it: RRTraitDescrRef? -> it!!.id.text }
                    .collect(Collectors.toList())
            }
        }
    }
}