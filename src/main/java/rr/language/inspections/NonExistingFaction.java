package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.RRFactionRef;
import rr.language.psi.RRRegionRef;
import rr.language.psi.RRVisitor;

import java.util.Collection;

public class NonExistingFaction extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitFactionRef(@NotNull RRFactionRef element) {
                Collection<String> items = RRUtil.findAllFactionsAsStrings(element.getProject());

                if (!items.contains(element.getText())) {
                    holder.registerProblem(element, "Non existing faction", ProblemHighlightType.ERROR);
                }
            }
        };
    }
}