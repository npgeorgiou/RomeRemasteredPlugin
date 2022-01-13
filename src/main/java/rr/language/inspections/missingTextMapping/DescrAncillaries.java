package rr.language.inspections.missingTextMapping;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.inspections.Inspector;
import rr.language.psi.RRAncillaryDef;
import rr.language.psi.RRVisitor;

import java.util.Collection;
import java.util.stream.Collectors;

public class DescrAncillaries extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitAncillaryDef(@NotNull RRAncillaryDef element) {
                Collection<String> uiTextKeys = RRUtil.findTextMappingsInFile("export_ancillaries.txt", element.getProject()).stream()
                    .map(it -> it.getId().getText())
                    .collect(Collectors.toList());

                PsiElement name = element.getAncillaryNameDecl();
                PsiElement description = RRUtil.nextSibling(name, it -> it.getText().equals("Description")).getNextSibling().getNextSibling();
                PsiElement effectsDescription = RRUtil.nextSibling(name, it -> it.getText().equals("EffectsDescription")).getNextSibling().getNextSibling();

                var message = "No description in export_ancillaries.txt";

                if (!uiTextKeys.contains(name.getText())) {
                    holder.registerProblem(name, message, ProblemHighlightType.ERROR);
                }
                if (!uiTextKeys.contains(description.getText())) {
                    holder.registerProblem(description, message, ProblemHighlightType.ERROR);
                }
                if (!uiTextKeys.contains(effectsDescription.getText())) {
                    holder.registerProblem(effectsDescription, message, ProblemHighlightType.ERROR);
                }
            }
        };
    }
}