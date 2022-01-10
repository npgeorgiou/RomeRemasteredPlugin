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

public class ReligionReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public ReligionReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        List<RRReligionNameDecl> items = RRUtil.findAllReligions(myElement.getProject()).stream()
            .filter(it -> Util.unquote(myElement.getText()).equals(Util.unquote(it.getText())))
            .collect(Collectors.toList());

        if (items.isEmpty()) {
            return null;
        }

        return items.get(0);
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        if (myElement.getText().startsWith("\"")) {
            ((RRStrReligionRef) myElement).setName(newName);
        } else {
            ((RRReligionRef) myElement).setName(newName);
        }

        return myElement;
    }

    @Override
    public Object @NotNull [] getVariants() {
        return RRUtil.findAllReligionsAsStrings(myElement.getProject()).stream()
            .map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}
