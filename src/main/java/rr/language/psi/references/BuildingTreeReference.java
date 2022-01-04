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

public class BuildingTreeReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public BuildingTreeReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        List<RRBuildingTree> trees = RRUtil.findAllBuildingTrees(myElement.getProject()).stream()
            .filter(it -> it.getBuildingTreeNameDecl().getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (trees.isEmpty()) {
            return null;
        }

        return trees.get(0).getBuildingTreeNameDecl();
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRBuildingTreeRef) myElement).setName(newName);
        return myElement;
    }
}
