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
                val hardcodedRebels: List<String> = mutableListOf("gladiator_uprising", "brigands", "pirates")
                if (!hardcodedRebels.contains(name.text)) {
                    if (ReferencesSearch.search(name).findAll().isEmpty()) {
                        holder.registerProblem(
                            element,
                            "Rebel faction not used anywhere",
                            ProblemHighlightType.LIKE_UNUSED_SYMBOL
                        )
                    }
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
                val items = findAllRebelFactionsAsStrings(element.project)
                if (!items.contains(element.text)) {
                    holder.registerProblem(
                        element,
                        "Non existing rebel faction",
                        ProblemHighlightType.ERROR
                    )
                }
            }

            override fun visitRebelFactionDescrRef(element: RRRebelFactionDescrRef) {
                val rebelDescriptionIds = findAllRebelFactions(element.project).stream()
                    .map { it: RRRebelFaction -> it.rebelFactionDescrDef.text }
                    .collect(Collectors.toList())
                if (!rebelDescriptionIds.contains(element.id.text)) {
                    holder.registerProblem(
                        element.id,
                        "Not used in descr_rebel_factions.txt",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
                    )
                }
            }

            private fun findAllRebelFactionUiNames(project: Project): List<String> {
                val file = findRRFile("rebel_faction_descr.txt", project)
                return Optional.ofNullable(file)
                    .map { it: RRFile ->
                        it.findChildByClass(
                            RRRebelFactionDescr::class.java
                        )
                    }
                    .map { it: RRRebelFactionDescr? -> it!!.rebelFactionDescrMappingList }
                    .orElse(ArrayList()).stream()
                    .map { it: RRRebelFactionDescrMapping -> it.rebelFactionDescrRef }
                    .map { it: RRRebelFactionDescrRef? -> it!!.id.text }
                    .collect(Collectors.toList())
            }
        }
    }
}