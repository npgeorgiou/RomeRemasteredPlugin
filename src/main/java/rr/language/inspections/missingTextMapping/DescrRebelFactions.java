package rr.language.inspections.missingTextMapping;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.inspections.Inspector;
import rr.language.psi.RRRebelsNameDecl;
import rr.language.psi.RRVisitor;

import java.util.Collection;
import java.util.stream.Collectors;

public class DescrRebelFactions extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitRebelsNameDecl(@NotNull RRRebelsNameDecl element) {
                Collection<String> uiTexts = RRUtil.findTextMappingsInFile("rebel_faction_descr.txt", element.getProject()).stream()
                    .map(it -> it.getId().getText())
                    .collect(Collectors.toList());

                if (!uiTexts.contains(element.getText())) {
                    holder.registerProblem(element, "No ui text mapping in rebel_faction_descr.txt", ProblemHighlightType.ERROR);
                }
            }
        };
    }
}