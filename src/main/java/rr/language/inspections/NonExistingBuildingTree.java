package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.*;

import java.util.Collection;
import java.util.stream.Collectors;

public class NonExistingBuildingTree extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitBuildingTreeRef(@NotNull RRBuildingTreeRef element) {
                Collection<String> all = RRUtil.findAllBuildingTrees(element.getProject()).stream()
                    .map(it -> it.getBuildingTreeNameDecl().getText())
                    .collect(Collectors.toList());

                if (!all.contains(element.getText())) {
                    holder.registerProblem(element, "Non existing building tree", ProblemHighlightType.ERROR);
                }
            }
        };
    }
}