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
import rr.language.psi.RRCultureNameDecl;
import rr.language.psi.RRFactionItem;
import rr.language.psi.RRFactionNameDecl;
import rr.language.psi.RRFactionOrCultureRef;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FactionOrCultureReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public FactionOrCultureReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        List<RRFactionNameDecl> factions = RRUtil.findAllFactions(myElement.getProject()).stream()
            .filter(it -> myElement.getText().equals(Util.unquote(it.getText())))
            .collect(Collectors.toList());

        if (!factions.isEmpty()) {
            return factions.get(0);
        }

        List<RRCultureNameDecl> cultures = RRUtil.findAllCultures(myElement.getProject()).stream()
            .filter(it -> myElement.getText().equals(Util.unquote(it.getText())))
            .collect(Collectors.toList());

        if (!cultures.isEmpty()) {
            return cultures.get(0);
        }

        return null;
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRFactionOrCultureRef) myElement).setName(newName);
        return myElement;
    }

    public Object @NotNull [] getVariants() {
        return Stream.concat(
                RRUtil.findAllFactionsAsStrings(myElement.getProject()).stream(),
                RRUtil.findAllFactionsAsStrings(myElement.getProject()).stream()
            ).map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}
