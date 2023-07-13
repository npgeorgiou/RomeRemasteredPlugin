package rr.language.inspections.missingTextMapping;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.inspections.Inspector;
import rr.language.psi.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DescrRebelFactions extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitRebelFaction(@NotNull RRRebelFaction element) {
                Collection<String> uiTexts = RRUtil.findTextMappingsInFile("rebel_faction_descr.txt", element.getProject()).stream()
                    .map(it -> it.getId().getText())
                    .collect(Collectors.toList());

                PsiElement description = element.getNode().findChildByType(RRTypes.DESCRIPTION_LC).getPsi().getNextSibling().getNextSibling();

                if (!uiTexts.contains(description.getText())) {
                    holder.registerProblem(description, "No description in rebel_faction_descr.txt", ProblemHighlightType.ERROR);
                }
            }

            @Override
            public void visitTextMappingFormat(@NotNull RRTextMappingFormat element) {
                if (!element.getContainingFile().getVirtualFile().getName().equals("rebel_faction_descr.txt")) {
                    return;
                }

                Collection<String> rebelDescriptionIds = RRUtil.findAllRebels(element.getProject()).stream()
                    .map(it -> it.getDescription().getText())
                    .collect(Collectors.toList());

                for (RRTextMappingItem mapping : element.getTextMappingItemList()) {
                    if (!rebelDescriptionIds.contains(mapping.getId().getText())) {
                        holder.registerProblem(mapping.getId(), "Not used in descr_rebel_factions.txt", ProblemHighlightType.WARNING);
                    }
                }
            }
        };
    }
}