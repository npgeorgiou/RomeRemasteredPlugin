package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.project.Project
import rr.language.RRUtil
import rr.language.RRUtil.findAllSettlementsAsStrings
import rr.language.psi.*
import java.util.*
import java.util.stream.Collectors

class Settlement : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitSettlementNameDecl(element: RRSettlementNameDecl) {
//                if (ReferencesSearch.search(element).findAll().isEmpty()) {
//                    holder.registerProblem(
//                        element,
//                        "Settlement not used anywhere",
//                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
//                    );
//                }
                val uiTexts = findAllRegionAndSettlementUiNames(element.project)
                if (!uiTexts.contains(element.text)) {
                    holder.registerProblem(
                        element,
                        "No description in *_campaign_regions_and_settlement_names.txt",
                        ProblemHighlightType.ERROR
                    )
                }
            }

            override fun visitSettlementRef(element: RRSettlementRef) {
                val all = findAllSettlementsAsStrings(element.project)
                if (!all.contains(element.text)) {
                    holder.registerProblem(element, "Non existing settlement", ProblemHighlightType.ERROR)
                }
            }

            private fun findAllRegionAndSettlementUiNames(project: Project): List<String> {
                val file = RRUtil.findRRFile("_campaign_regions_and_settlement_names.txt", project)
                return Optional.ofNullable(file)
                    .map { it: RRFile ->
                        it.findChildByClass(
                            RRCampaignRegionsAndSettlementNames::class.java
                        )
                    }
                    .map { it: RRCampaignRegionsAndSettlementNames? -> it!!.regionOrSettlementNameMappingList }
                    .orElse(ArrayList()).stream()
                    .map { it: RRRegionOrSettlementNameMapping -> it.regionOrSettlementRef }
                    .map { it: RRRegionOrSettlementRef? -> it!!.id.text }
                    .collect(Collectors.toList())
            }
        }
    }
}