package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.*;

import java.util.*;
import java.util.stream.Collectors;

public class Traits extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitTraitDescrDef(@NotNull RRTraitDescrDef element) {
                var uiTexts = findAllTraitUiNames(element.getProject());

                if (!uiTexts.contains(element.getText())) {
                    holder.registerProblem(
                        element,
                        "No description in rebel_faction_descr.txt",
                        ProblemHighlightType.ERROR
                    );
                }
            }

            @Override
            public void visitTraitDescrRef(@NotNull RRTraitDescrRef element) {
                Collection<String> all = RRUtil.findAllTraitDescriptions(element.getProject()).stream()
                    .map(it -> it.getText())
                    .collect(Collectors.toList());

                if (!all.contains(element.getText())) {
                    holder.registerProblem(
                        element,
                        "Non existing trait description",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
                    );
                }
            }

            @Override
            public void visitTraitRef(@NotNull RRTraitRef element) {
                Collection<String> all = RRUtil.findAllTraitsAsStrings(element.getProject());

                if (!all.contains(element.getText())) {
                    holder.registerProblem(element, "Non existing trait", ProblemHighlightType.ERROR);
                }
            }

            private List<String> findAllTraitUiNames(Project project) {
                RRFile file = RRUtil.findRRFile("export_vnvs.txt", project);

                return Optional.ofNullable(file)
                    .map(it -> it.findChildByClass(RRExportVnvs.class))
                    .map(it -> it.getExportVnvsItemList())
                    .orElse(new ArrayList<>()).stream()
                    .map(it -> it.getTraitDescrRef())
                    .map(it -> it.getId().getText())
                    .collect(Collectors.toList());
            }
        };
    }
}