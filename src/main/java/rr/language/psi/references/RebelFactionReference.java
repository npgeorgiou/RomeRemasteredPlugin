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
import rr.language.psi.RRRebelFactionNameDef;
import rr.language.psi.RRRebelFactionRef;

import java.util.List;
import java.util.stream.Collectors;

public class RebelFactionReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public RebelFactionReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        List<RRRebelFactionNameDef> items = RRUtil.findAllRebelFactions(myElement.getProject()).stream()
            .map(it -> it.getRebelFactionNameDef())
            .filter(it -> it.getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (items.isEmpty()) {
            return null;
        }

        return items.get(0);
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRRebelFactionRef) myElement).setName(newName);
        return myElement;
    }

    @Override
    public Object @NotNull [] getVariants() {
        return RRUtil.findAllRebelFactionsAsStrings(myElement.getProject()).stream()
            .map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}
