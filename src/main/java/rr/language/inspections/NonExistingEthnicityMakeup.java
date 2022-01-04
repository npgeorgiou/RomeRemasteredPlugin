package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.RREthnicityMakeupRef;
import rr.language.psi.RRRegionRef;
import rr.language.psi.RRVisitor;

import java.util.Collection;
import java.util.stream.Collectors;

public class NonExistingEthnicityMakeup extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitEthnicityMakeupRef(@NotNull RREthnicityMakeupRef element) {
                Collection<String> allEthnicityMakeups = RRUtil.findAllEthnicityMakeupsAsStrings(element.getProject());

                if (!allEthnicityMakeups.contains(element.getText())) {
                    holder.registerProblem(element, "Non existing ethnicity makeup", ProblemHighlightType.ERROR);
                }
            }
        };
    }
}