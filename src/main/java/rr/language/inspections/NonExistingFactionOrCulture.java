package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.RRFactionOrCultureRef;
import rr.language.psi.RRFactionRef;
import rr.language.psi.RRVisitor;

import java.util.Collection;

public class NonExistingFactionOrCulture extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitFactionOrCultureRef(@NotNull RRFactionOrCultureRef element) {
                Collection<String> items = RRUtil.findAllFactionsAsStrings(element.getProject());

                if (!items.contains(element.getText())) {
                    items = RRUtil.findAllCulturesAsStrings(element.getProject());

                    if (!items.contains(element.getText())) {
                        holder.registerProblem(element, "Non existing faction or culture", ProblemHighlightType.ERROR);
                    }
                }


            }
        };
    }
}