package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.RRAncillaryRef;
import rr.language.psi.RRUnitRef;
import rr.language.psi.RRVisitor;

import java.util.Collection;
import java.util.stream.Collectors;

public class NonExistingAncillary extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitAncillaryRef(@NotNull RRAncillaryRef element) {
                Collection<String> all = RRUtil.findAllAncillaries(element.getProject()).stream()
                    .map(it -> it.getAncillaryNameDecl().getText())
                    .collect(Collectors.toList());

                if (!all.contains(element.getText())) {
                    holder.registerProblem(element, "Non existing ancillary", ProblemHighlightType.ERROR);
                }
            }
        };
    }
}