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
import rr.language.psi.RRCultureRef;
import rr.language.psi.RRResourceNameDecl;
import rr.language.psi.RRResourceRef;
import rr.language.psi.RRStrCultureRef;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public ResourceReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        List<RRResourceNameDecl> items = RRUtil.findAllResources(false, myElement.getProject()).stream()
            .filter(it -> Util.unquote(it.getText()).equals(Util.unquote(myElement.getText())))
            .collect(Collectors.toList());

        if (!items.isEmpty()) {
            return items.get(0);
        }

        return null;
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRResourceRef) myElement).setName(newName);
        return myElement;
    }

    @Override
    public Object @NotNull [] getVariants() {
        boolean onlyHidden = myElement.getPrevSibling().getPrevSibling().getText().equals("hidden_resource");
        return RRUtil.findAllResourcesAsStrings(onlyHidden, myElement.getProject()).stream()
            .map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}
