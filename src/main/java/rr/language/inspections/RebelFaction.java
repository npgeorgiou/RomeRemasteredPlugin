package rr.language.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.searches.ReferencesSearch;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RebelFaction extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitRebelFaction(@NotNull RRRebelFaction element) {
                var name = element.getRebelFactionNameDef();

                var hardcodedRebels = Arrays.asList("gladiator_uprising", "brigands", "pirates");
                if (!hardcodedRebels.contains(name.getText())) {
                    if (ReferencesSearch.search(name).findAll().isEmpty()) {
                        holder.registerProblem(
                            element,
                            "Rebel faction not used anywhere",
                            ProblemHighlightType.LIKE_UNUSED_SYMBOL
                        );
                    }
                }

                var description = element.getRebelFactionDescrDef();
                var uiTexts = findAllRebelFactionUiNames(element.getProject());

                if (!uiTexts.contains(description.getText())) {
                    holder.registerProblem(
                        description,
                        "No description in rebel_faction_descr.txt",
                        ProblemHighlightType.ERROR
                    );
                }
            }

            @Override
            public void visitRebelFactionRef(@NotNull RRRebelFactionRef element) {
                var items = RRUtil.findAllRebelFactionsAsStrings(element.getProject());

                if (!items.contains(element.getText())) {
                    holder.registerProblem(
                        element,
                        "Non existing rebel faction",
                        ProblemHighlightType.ERROR
                    );
                }
            }

            @Override
            public void visitRebelFactionDescrRef(@NotNull RRRebelFactionDescrRef element) {
                var rebelDescriptionIds = RRUtil.findAllRebelFactions(element.getProject()).stream()
                    .map(it -> it.getRebelFactionDescrDef().getText())
                    .collect(Collectors.toList());

                if (!rebelDescriptionIds.contains(element.getId().getText())) {
                    holder.registerProblem(
                        element.getId(),
                        "Not used in descr_rebel_factions.txt",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL
                    );
                }
            }

            private List<String> findAllRebelFactionUiNames(Project project) {
                RRFile file = RRUtil.findRRFile("rebel_faction_descr.txt", project);

                return Optional.ofNullable(file)
                    .map(it -> it.findChildByClass(RRRebelFactionDescr.class))
                    .map(it -> it.getRebelFactionDescrMappingList())
                    .orElse(new ArrayList<>()).stream()
                    .map(it -> it.getRebelFactionDescrRef())
                    .map(it -> it.getId().getText())
                    .collect(Collectors.toList());
            }
        };
    }
}