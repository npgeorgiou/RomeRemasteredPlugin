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
import rr.language.Util;
import rr.language.psi.*;

import java.util.List;
import java.util.stream.Collectors;

public class FactionReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public FactionReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        List<RRFactionNameDecl> factions = RRUtil.findAllFactions(myElement.getProject()).stream()
            .filter(it -> Util.unquote(myElement.getText()).equals(Util.unquote(it.getText())))
            .collect(Collectors.toList());

        if (factions.isEmpty()) {
            return null;
        }

        return factions.get(0);
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        if (myElement.getText().startsWith("\"")) {
            ((RRStrFactionRef) myElement).setName(newName);
        } else {
            ((RRFactionRef) myElement).setName(newName);
        }

        return myElement;
    }

    @Override
    public Object @NotNull [] getVariants() {
        return RRUtil.findAllFactionsAsStrings(myElement.getProject()).stream()
            .map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}
