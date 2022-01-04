package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.*;

import java.util.Collection;
import java.util.stream.Collectors;

public class NonExistingRegion extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitRegionRef(@NotNull RRRegionRef element) {
                Collection<String> allRegions = RRUtil.findAllRegionsAsStrings(element.getProject());

                if (!allRegions.contains(element.getText())) {
                    holder.registerProblem(element, "Non existing region", ProblemHighlightType.ERROR);
                }
            }
        };
    }
}