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

public class CultureReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public CultureReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        List<RRCultureNameDecl> cultures = RRUtil.findAllCultures(myElement.getProject()).stream()
            .filter(it -> Util.unquote(it.getText()).equals(Util.unquote(myElement.getText())))
            .collect(Collectors.toList());

        if (!cultures.isEmpty()) {
            return cultures.get(0);
        }

        return null;
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        if (myElement.getText().startsWith("\"")) {
            ((RRStrCultureRef) myElement).setName(newName);
        } else {
            ((RRCultureRef) myElement).setName(newName);
        }

        return myElement;
    }

    @Override
    public Object @NotNull [] getVariants() {
        return RRUtil.findAllCulturesAsStrings(myElement.getProject()).stream()
            .map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}
