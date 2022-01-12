package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.RRDescrNames;
import rr.language.psi.RRFile;
import rr.language.psi.RRTextMappingFormat;
import rr.language.psi.RRVisitor;

import java.util.Collection;
import java.util.stream.Collectors;

public class NamesNotInDescrNames extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitTextMappingFormat(@NotNull RRTextMappingFormat element) {
                if (!element.getContainingFile().getName().equals("names.txt")) {
                    return;
                }

                Collection<PsiElement> names = RRUtil.findTextMappingsInFile((RRFile) element.getContainingFile()).stream()
                    .map(it -> it.getString())
                    .collect(Collectors.toList());

                var file = RRUtil.findRRFile("descr_names.txt", element.getProject());
                if (file == null) {
                    return;
                }

                Collection<String> descr_names = file
                    .findChildByClass(RRDescrNames.class)
                    .getNamesForFactionList().stream()
                    .flatMap(it -> it.getName_List().stream())
                    .map(it -> it.getText())
                    .collect(Collectors.toList());

                for (PsiElement name : names) {
                    if (!descr_names.contains(name.getText())) {
                        holder.registerProblem(name, "Name not used in descr_names.txt", ProblemHighlightType.WARNING);
                    }
                }
            }
        };
    }
}