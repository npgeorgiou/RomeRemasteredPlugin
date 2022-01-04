package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.Util;
import rr.language.psi.*;

import java.util.Collection;

public class NonExistingCulture extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitCultureRef(@NotNull RRCultureRef element) {
                Collection<String> all = RRUtil.findAllCulturesAsStrings(element.getProject());

                if (!all.contains(element.getText())) {
                    holder.registerProblem(element, "Non existing culture", ProblemHighlightType.ERROR);
                }
            }

            public void visitStrCultureRef(@NotNull RRStrCultureRef element) {
                Collection<String> all = RRUtil.findAllCulturesAsStrings(element.getProject());

                if (!all.contains(Util.unquote(element.getText()))) {
                    holder.registerProblem(element, "Non existing culture", ProblemHighlightType.ERROR);
                }
            }
        };
    }
}