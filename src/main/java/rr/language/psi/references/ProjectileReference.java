package rr.language.psi.references;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRUtil;
import rr.language.psi.RRProjectileRef;
import rr.language.psi.RRWonderNameDecl;
import rr.language.psi.RRWonderRef;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectileReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public ProjectileReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        var items = RRUtil.findAllProjectiles(myElement.getProject()).stream()
            .filter(it -> it.getProjectileNameDecl().getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (items.isEmpty()) {
            return null;
        }

        return items.get(0).getProjectileNameDecl();
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRProjectileRef) myElement).setName(newName);
        return myElement;
    }

    public Object @NotNull [] getVariants() {
        return RRUtil.findAllProjectilesAsStrings(myElement.getProject()).stream()
            .map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}
