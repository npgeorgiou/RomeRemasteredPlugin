package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.search.searches.ReferencesSearch;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.*;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Resource extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitResourceNameDecl(@NotNull RRResourceNameDecl element) {
                if (ReferencesSearch.search(element).findAll().isEmpty()) {
                    holder.registerProblem(
                        element,
                        "Resource not used anywhere",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
                    );
                }
            }

            public void visitResourceRef(@NotNull RRResourceRef element) {
                var items = RRUtil.findAllResourcesAsStrings(false, element.getProject());

                if (!items.contains(element.getText())) {
                    holder.registerProblem(
                        element,
                        "Non existing resource",
                        ProblemHighlightType.ERROR
                    );
                }
            }
        };
    }
}