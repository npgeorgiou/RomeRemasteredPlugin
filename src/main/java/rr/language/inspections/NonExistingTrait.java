package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.RRTraitRef;
import rr.language.psi.RRUnitRef;
import rr.language.psi.RRVisitor;

import java.util.Collection;
import java.util.stream.Collectors;

public class NonExistingTrait extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitTraitRef(@NotNull RRTraitRef element) {
                Collection<String> all = RRUtil.findAllTraitsAsStrings(element.getProject());

                if (!all.contains(element.getText())) {
                    holder.registerProblem(element, "Non existing trait", ProblemHighlightType.ERROR);
                }
            }
        };
    }
}