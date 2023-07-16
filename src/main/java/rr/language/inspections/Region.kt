package rr.language.inspections

import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.project.Project
import rr.language.RRUtil
import rr.language.RRUtil.findAllRegionsAsStrings
import rr.language.RRUtil.findAllSettlementsAsStrings
import rr.language.psi.*
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

class Region : Inspector() {
    override fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
//            override fun visitRegionDef(element: RRRegionDef) {
////                var regionName = element.getRegionNameDecl();
////                if (ReferencesSearch.search(regionName).findAll().isEmpty()) {
////                    holder.registerProblem(
////                        element,
////                        "Region not used anywhere",
////                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
////                    );
////                }
////
////                var settlementName = element.getSettlementNameDecl();
////                if (ReferencesSearch.search(settlementName).findAll().isEmpty()) {
////                    holder.registerProblem(
////                        settlementName,
////                        "Settlement not used anywhere",
////                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
////                    );
////                }
//            }
//
//            override fun visitRegionNameDecl(element: RRRegionNameDecl) {
//                val uiTexts = findAllRegionAndSettlementUiNames(element.project)
//                if (!uiTexts.contains(element.text)) {
//                    holder.registerProblem(
//                        element,
//                        "No description in *_campaign_regions_and_settlement_names.txt",
//                        ProblemHighlightType.ERROR
//                    )
//                }
//            }
//
//            override fun visitRegionRef(element: RRRegionRef) {
//                val allRegions = findAllRegionsAsStrings(element.project)
//                if (!allRegions.contains(element.text)) {
//                    holder.registerProblem(element, "Non existing region", ProblemHighlightType.ERROR)
//                }
//            }
//
//            override fun visitRegionOrSettlementRef(element: RRRegionOrSettlementRef) {
//                val all = Stream.concat(
//                    findAllRegionsAsStrings(element.project).stream(),
//                    findAllSettlementsAsStrings(element.project).stream()
//                ).collect(Collectors.toList())
//                if (!all.contains(element.text)) {
//                    holder.registerProblem(
//                        element,
//                        "Non existing region or settlement",
//                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
//                    )
//                }
//            }

            private fun findAllRegionAndSettlementUiNames(project: Project): List<String> {
                val file = RRUtil.findRRFile("_campaign_regions_and_settlement_names.txt", project) ?: return emptyList()

                return file.findChildByClass(RRCampaignRegionsAndSettlementNames::class.java)!!
                    .regionOrSettlementNameMappingList
                    .map { it.regionOrSettlementRef }
                    .map { it!!.id.text }
            }
        }
    }
}