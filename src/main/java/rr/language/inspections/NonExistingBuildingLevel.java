package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.RRBuildingLevelRef;
import rr.language.psi.RRBuildingTreeRef;
import rr.language.psi.RRVisitor;

import java.util.Collection;
import java.util.stream.Collectors;

public class NonExistingBuildingLevel extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitBuildingLevelRef(@NotNull RRBuildingLevelRef element) {
                Collection<String> all = RRUtil.findAllBuildingLevels(element.getProject()).stream()
                    .map(it -> it.getBuildingLevelNameDecl().getText())
                    .collect(Collectors.toList());

                if (!all.contains(element.getText())) {
                    holder.registerProblem(element, "Non existing building level", ProblemHighlightType.ERROR);
                }
            }
        };
    }
}