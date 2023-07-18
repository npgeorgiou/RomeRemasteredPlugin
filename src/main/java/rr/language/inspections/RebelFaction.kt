package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.project.Project
import com.intellij.psi.search.searches.ReferencesSearch
import rr.language.RRUtil.findAllRebelFactions
import rr.language.RRUtil.findAllRebelFactionsAsStrings
import rr.language.RRUtil.findRRFile
import rr.language.psi.*
import java.util.*
import java.util.stream.Collectors

class RebelFaction : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitRebelFaction(element: RRRebelFaction) {
                val name = element.rebelFactionNameDef
                val hardcodedRebels = listOf("gladiator_uprising", "brigands", "pirates")
                if (hardcodedRebels.contains(name!!.text)) {
                    return
                }

                if (ReferencesSearch.search(name).findAll().isEmpty()) {
                    holder.registerProblem(
                        element,
                        "Rebel faction not used anywhere",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
                    )
                }

                val description = element.rebelFactionDescrDef
                val uiTexts = findAllRebelFactionUiNames(element.project)
                if (!uiTexts.contains(description.text)) {
                    holder.registerProblem(
                        description,
                        "No description in rebel_faction_descr.txt",
                        ProblemHighlightType.ERROR
                    )
                }
            }

            override fun visitRebelFactionRef(element: RRRebelFactionRef) {
                if (element.reference.resolve() == null) {
                    holder.registerProblem(
                        element,
                        "Non existing rebel faction",
                        ProblemHighlightType.ERROR
                    )
                }
            }

            override fun visitRebelFactionDescrRef(element: RRRebelFactionDescrRef) {
                if (element.reference.resolve() == null) {
                    holder.registerProblem(
                        element.id,
                        "Not used in descr_rebel_factions.txt",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
                    )
                }
            }

            private fun findAllRebelFactionUiNames(project: Project): List<String> {
                val file = findRRFile("text/rebel_faction_descr.txt", project) ?: return emptyList()

                return file.findChildByClass(RRRebelFactionDescr::class.java)!!
                    .rebelFactionDescrMappingList
                    .map { it.rebelFactionDescrRef }
                    .map { it!!.id.text }
            }
        }
    }
}