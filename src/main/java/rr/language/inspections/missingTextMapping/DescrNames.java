package rr.language.inspections.missingTextMapping;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.inspections.Inspector;
import rr.language.psi.RRNamesForFaction;
import rr.language.psi.RRTypes;
import rr.language.psi.RRVisitor;

import java.util.Collection;
import java.util.stream.Collectors;

public class DescrNames extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitNamesForFaction(@NotNull RRNamesForFaction element) {

                Collection<PsiElement> descr_names = PsiTreeUtil.findChildrenOfType(element, PsiElement.class).stream()
                    .filter(it -> it.getNode().getElementType() == RRTypes.NAME_)
                    .collect(Collectors.toList());

                Collection<String> names = RRUtil.findTextMappingsInFile("names.txt", element.getProject()).stream()
                    .map(it -> it.getString().getText())
                    .collect(Collectors.toList());

                for (PsiElement descr_name : descr_names) {
                    if (!names.contains(descr_name.getText())) {
                        holder.registerProblem(descr_name, "Name does not exist in names.txt", ProblemHighlightType.WARNING);
                    }
                }
            }
        };
    }
}