package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.Util;
import rr.language.psi.*;

import java.util.Collection;
import java.util.stream.Collectors;

public class NonExistingUnit extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitUnitRef(@NotNull RRUnitRef element) {
                Collection<String> all = RRUtil.findAllUnitsAsStrings(element.getProject());

                if (!all.contains(element.getText())) {
                    holder.registerProblem(element, "Non existing unit", ProblemHighlightType.ERROR);
                }
            }

            public void visitStrUnitRef(@NotNull RRStrUnitRef element) {
                Collection<String> all = RRUtil.findAllUnitsAsStrings(element.getProject());

                if (!all.contains(Util.unquote(element.getText()))) {
                    holder.registerProblem(element, "Non existing unit", ProblemHighlightType.ERROR);
                }
            }
        };
    }
}