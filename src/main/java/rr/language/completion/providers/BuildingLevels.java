package rr.language.completion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRUtil;
import rr.language.psi.*;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BuildingLevels extends RRCompletionProvider {
    private boolean restrict = false;

    public BuildingLevels() {
    }

    public BuildingLevels(boolean restrict) {
        this.restrict = restrict;
    }

    @Override
    public void addCompletions(
        @NotNull CompletionParameters parameters,
        @NotNull ProcessingContext context,
        @NotNull CompletionResultSet resultSet
    ) {
        PsiElement typed = parameters.getPosition();
        Project project = parameters.getOriginalFile().getProject();

        Predicate<RRBuildingTree> filter = it -> true;

        if (restrict) {
            String buildingTree = typed.getParent().getPrevSibling().getPrevSibling().getText();
            filter = it -> it.getBuildingTreeNameDecl().getText().equals(buildingTree);
        }

        Collection<String> levels = RRUtil.findAllBuildingTrees(project).stream()
            .filter(filter)
            .flatMap(it -> it.getBuildingLevelList().stream())
            .map(it -> it.getFirstChild().getText())
            .collect(Collectors.toList());

        for (String level : levels) {
            resultSet.addElement(LookupElementBuilder.create(level));
        }
    }
}