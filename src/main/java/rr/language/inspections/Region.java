package rr.language.inspections;

import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.search.searches.ReferencesSearch;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Region extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitRegionDef(@NotNull RRRegionDef element) {
//                var regionName = element.getRegionNameDecl();
//                if (ReferencesSearch.search(regionName).findAll().isEmpty()) {
//                    holder.registerProblem(
//                        element,
//                        "Region not used anywhere",
//                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
//                    );
//                }
//
//                var settlementName = element.getSettlementNameDecl();
//                if (ReferencesSearch.search(settlementName).findAll().isEmpty()) {
//                    holder.registerProblem(
//                        settlementName,
//                        "Settlement not used anywhere",
//                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
//                    );
//                }
            }

            @Override
            public void visitRegionRef(@NotNull RRRegionRef element) {
                var allRegions = RRUtil.findAllRegionsAsStrings(element.getProject());

                if (!allRegions.contains(element.getText())) {
                    holder.registerProblem(element, "Non existing region", ProblemHighlightType.ERROR);
                }
            }

            @Override
            public void visitRegionOrSettlementRef(@NotNull RRRegionOrSettlementRef element) {
                var all = Stream.concat(
                    RRUtil.findAllRegionsAsStrings(element.getProject()).stream(),
                    RRUtil.findAllSettlementsAsStrings(element.getProject()).stream()
                ).collect(Collectors.toList());

                if (!all.contains(element.getText())) {
                    holder.registerProblem(element, "Non existing region or settlement", ProblemHighlightType.ERROR);
                }
            }
        };
    }
}