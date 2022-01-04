package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.*;

import java.util.*;
import java.util.stream.Collectors;

public class DuplicateMercenaryPoolRegions extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitMercenaryPool(@NotNull RRMercenaryPool element) {
                Collection<PsiElement> regions = PsiTreeUtil.findChildrenOfType(element, PsiElement.class).stream()
                    .filter(it -> it instanceof RRRegionRef)
                    .collect(Collectors.toList());

                Collection<String> seenOnce = new HashSet<>();

                for (PsiElement region : regions) {
                    if (!seenOnce.add(region.getText())) {
                        holder.registerProblem(region, "Duplicate region", ProblemHighlightType.LIKE_UNUSED_SYMBOL);
                    };
                }
            }
        };
    }
}