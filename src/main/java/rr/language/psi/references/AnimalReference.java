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
import rr.language.psi.RRAnimalRef;
import rr.language.psi.RRProjectileRef;

import java.util.stream.Collectors;

public class AnimalReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public AnimalReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        var items = RRUtil.findAllAnimals(myElement.getProject()).stream()
            .filter(it -> it.getAnimalNameDecl().getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (items.isEmpty()) {
            return null;
        }

        return items.get(0).getAnimalNameDecl();
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRAnimalRef) myElement).setName(newName);
        return myElement;
    }

    public Object @NotNull [] getVariants() {
        return RRUtil.findAllAnimalsAsStrings(myElement.getProject()).stream()
            .map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}
