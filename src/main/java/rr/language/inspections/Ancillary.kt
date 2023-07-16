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
                val all: MutableCollection<String> = findAllAncillaryDescriptions(element.project).stream()
                    .map { it: RRAncillaryDescrDef -> it.text }
                    .collect(Collectors.toList())
                val names = findAllAncillaries(element.project).stream()
                    .map { it: RRAncillaryDef -> it.ancillaryNameDecl }
                    .filter { it: RRAncillaryNameDecl? -> it!!.text == element.text }
                    .map { it: RRAncillaryNameDecl? -> it!!.text }
                    .collect(Collectors.toList())
                all.addAll(names)
                if (!all.contains(element.text)) {
                    holder.registerProblem(
                        element,
                        "Non existing ancillary description",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
                    )
                }
            }

            override fun visitAncillaryRef(element: RRAncillaryRef) {
                val all: Collection<String> = findAllAncillaries(element.project).stream()
                    .map { it: RRAncillaryDef ->
                        it.ancillaryNameDecl!!
                            .text
                    }
                    .collect(Collectors.toList())
                if (!all.contains(element.text)) {
                    holder.registerProblem(element, "Non existing ancillary", ProblemHighlightType.ERROR)
                }
            }

            private fun findAllAncillaryUiNames(project: Project): List<String> {
                val file = findRRFile("export_ancillaries.txt", project)
                return Optional.ofNullable(file)
                    .map { it: RRFile ->
                        it.findChildByClass(
                            RRExportAncillaries::class.java
                        )
                    }
                    .map { it: RRExportAncillaries? -> it!!.exportAncillariesItemList }
                    .orElse(ArrayList()).stream()
                    .map { it: RRExportAncillariesItem -> it.ancillaryDescrRef }
                    .map { it: RRAncillaryDescrRef? -> it!!.id.text }
                    .collect(Collectors.toList())
            }
        }
    }
}