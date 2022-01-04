package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRUtil;
import rr.language.completion.providers.Ancillaries;
import rr.language.psi.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DescrNamesNotInNames extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitNamesForFaction(@NotNull RRNamesForFaction element) {

                Collection<PsiElement> descr_names = PsiTreeUtil.findChildrenOfType(element, PsiElement.class).stream()
                    .filter(it -> it.getNode().getElementType() == RRTypes.NAME_)
                    .collect(Collectors.toList());

                RRFile file = RRUtil.findRRFile("names.txt", element.getProject());

                if (file == null) {
                    return;
                }

                Collection<String> names = file
                    .findChildByClass(RRNames.class)
                    .getNamesNameList().stream()
                    .map(it -> it.getText())
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