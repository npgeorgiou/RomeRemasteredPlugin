package rr.language.psi.references;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRUtil;
import rr.language.psi.*;

import java.util.List;
import java.util.stream.Collectors;

public class BuildingLevelReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public BuildingLevelReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        List<RRBuildingLevel> levels = RRUtil.findAllBuildingLevels(myElement.getProject()).stream()
            .filter(it -> it.getFirstChild().getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (levels.isEmpty()) {
            return null;
        }

        return levels.get(0).getBuildingLevelNameDecl();
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRBuildingLevelRef) myElement).setName(newName);
        return myElement;
    }
}
