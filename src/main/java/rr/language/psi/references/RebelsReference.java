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
import rr.language.psi.RRRebelFaction;
import rr.language.psi.RRRebelsRef;
import rr.language.psi.RRRegionDef;
import rr.language.psi.RRRegionRef;

import java.util.List;
import java.util.stream.Collectors;

public class RebelsReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public RebelsReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        List<RRRebelFaction> items = RRUtil.findAllRebels(myElement.getProject()).stream()
            .filter(it -> it.getRebelsNameDecl().getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (items.isEmpty()) {
            return null;
        }

        return items.get(0).getRebelsNameDecl();
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRRebelsRef) myElement).setName(newName);
        return myElement;
    }

    @Override
    public Object @NotNull [] getVariants() {
        return RRUtil.findAllRebelsAsStrings(myElement.getProject()).stream()
            .map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}
