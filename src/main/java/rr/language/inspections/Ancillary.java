package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ancillary extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitAncillaryDescrDef(@NotNull RRAncillaryDescrDef element) {
                var uiTexts = findAllAncillaryUiNames(element.getProject());

                if (!uiTexts.contains(element.getText())) {
                    holder.registerProblem(
                        element,
                        "No description in export_ancillaries.txt",
                        ProblemHighlightType.ERROR
                    );
                }
            }

            @Override
            public void visitAncillaryNameDecl(@NotNull RRAncillaryNameDecl element) {
                var uiTexts = findAllAncillaryUiNames(element.getProject());

                if (!uiTexts.contains(element.getText())) {
                    holder.registerProblem(
                        element,
                        "No description in export_ancillaries.txt",
                        ProblemHighlightType.ERROR
                    );
                }
            }

            @Override
            public void visitAncillaryDescrRef(@NotNull RRAncillaryDescrRef element) {
                Collection<String> all = RRUtil.findAllAncillaryDescriptions(element.getProject()).stream()
                    .map(it -> it.getText())
                    .collect(Collectors.toList());

                var names = RRUtil.findAllAncillaries(element.getProject()).stream()
                    .map(it -> it.getAncillaryNameDecl())
                    .filter(it -> it.getText().equals(element.getText()))
                    .map(it -> it.getText())
                    .collect(Collectors.toList());

                all.addAll(names);

                if (!all.contains(element.getText())) {
                    holder.registerProblem(
                        element,
                        "Non existing ancillary description",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
                    );
                }
            }

            @Override
            public void visitAncillaryRef(@NotNull RRAncillaryRef element) {
                Collection<String> all = RRUtil.findAllAncillaries(element.getProject()).stream()
                    .map(it -> it.getAncillaryNameDecl().getText())
                    .collect(Collectors.toList());

                if (!all.contains(element.getText())) {
                    holder.registerProblem(element, "Non existing ancillary", ProblemHighlightType.ERROR);
                }
            }

            private List<String> findAllAncillaryUiNames(Project project) {
                RRFile file = RRUtil.findRRFile("export_ancillaries.txt", project);

                return Optional.ofNullable(file)
                    .map(it -> it.findChildByClass(RRExportAncillaries.class))
                    .map(it -> it.getExportAncillariesItemList())
                    .orElse(new ArrayList<>()).stream()
                    .map(it -> it.getAncillaryDescrRef())
                    .map(it -> it.getId().getText())
                    .collect(Collectors.toList());
            }
        };
    }
}