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
import rr.language.psi.RRCounterNameDecl;
import rr.language.psi.RRCounterRef;
import rr.language.psi.RRDisaster_;
import rr.language.psi.RRWonderRef;

import java.util.List;
import java.util.stream.Collectors;

public class CounterReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public CounterReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        List<RRCounterNameDecl> items = RRUtil.findAllCounters(myElement).stream()
            .filter(it -> it.getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (items.isEmpty()) {
            return null;
        }

        return items.get(0);
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRCounterRef) myElement).setName(newName);
        return myElement;
    }

    public Object @NotNull [] getVariants() {
        return RRUtil.findAllCountersAsStrings(myElement).stream()
            .map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}
