package rr.language.psi.references;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRUtil;
import rr.language.psi.RRBuildingLevel;
import rr.language.psi.RRBuildingTree;
import rr.language.psi.RRBuriedBuildingTreeOrLevelRef;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuildingTreeOrLevelReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public BuildingTreeOrLevelReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        @NotNull Project project = myElement.getProject();

        final String name = RRUtil.removePostfixesFromExportBuildingsId(myElement.getText(), project);

        List<RRBuildingTree> trees = RRUtil.findAllBuildingTrees(project).stream()
            .filter(it -> it.getBuildingTreeNameDecl().getText().equals(name))
            .collect(Collectors.toList());

        if (!trees.isEmpty()) {
            return trees.get(0).getBuildingTreeNameDecl();
        }

        List<RRBuildingLevel> levels = RRUtil.findAllBuildingLevels(project).stream()
            .filter(it -> it.getFirstChild().getText().equals(name))
            .collect(Collectors.toList());

        if (!levels.isEmpty()) {
            return levels.get(0).getBuildingLevelNameDecl();
        }

        return null;
    }

    @Override
    public boolean isReferenceTo(@NotNull PsiElement element) {
        return Objects.equals(resolve(), element);
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRBuriedBuildingTreeOrLevelRef) myElement).setName(newName);
        return myElement;
    }

    public Object @NotNull [] getVariants() {
        return Stream.concat(
                RRUtil.findAllBuildingTreesAsStrings(myElement.getProject()).stream(),
                RRUtil.findAllBuildingLevelsAsStrings(myElement.getProject()).stream()
            ).map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}
