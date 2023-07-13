package rr.language.psi.references;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRUtil;
import rr.language.psi.RRRebelFactionDescrRef;

import java.util.stream.Collectors;

public class RebelFactionDescrReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public RebelFactionDescrReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        var items = RRUtil.findAllRebelFactions(myElement.getProject()).stream()
            .map(it -> it.getRebelFactionDescrDef())
            .filter(it -> it.getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (items.isEmpty()) {
            return null;
        }

        return items.get(0);
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRRebelFactionDescrRef) myElement).setName(newName);
        return myElement;
    }

    public Object @NotNull [] getVariants() {
        return new String[0];
    }
}
