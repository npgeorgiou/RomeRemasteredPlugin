package rr.language.psi.references;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRUtil;
import rr.language.psi.RRRegionRef;

import java.util.stream.Collectors;

public class RegionReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public RegionReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        var regions = RRUtil.findAllRegions(myElement.getProject()).stream()
            .map(it -> it.getRegionNameDecl())
            .filter(it -> it.getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (regions.isEmpty()) {
            return null;
        }

        return regions.get(0);
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRRegionRef) myElement).setName(newName);
        return myElement;
    }
}
