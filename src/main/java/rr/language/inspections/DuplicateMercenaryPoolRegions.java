package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import rr.language.psi.RRMercenaryPoolRegions;
import rr.language.psi.RRVisitor;

import java.util.Collection;
import java.util.HashSet;

public class DuplicateMercenaryPoolRegions extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitMercenaryPoolRegions(@NotNull RRMercenaryPoolRegions element) {
                Collection<String> seenOnce = new HashSet<>();

                for (PsiElement region : element.getRegionRefList()) {
                    if (!seenOnce.add(region.getText())) {
                        holder.registerProblem(region, "Duplicate region", ProblemHighlightType.LIKE_UNUSED_SYMBOL);
                    };
                }
            }
        };
    }
}