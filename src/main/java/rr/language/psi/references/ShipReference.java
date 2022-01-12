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
import rr.language.psi.RRMountRef;
import rr.language.psi.RRShipRef;

import java.util.stream.Collectors;

public class ShipReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public ShipReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        var items = RRUtil.findAllShips(myElement.getProject()).stream()
            .filter(it -> it.getShipNameDecl().getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (items.isEmpty()) {
            return null;
        }

        return items.get(0).getShipNameDecl();
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRShipRef) myElement).setName(newName);
        return myElement;
    }

    public Object @NotNull [] getVariants() {
        return RRUtil.findAllShipsAsStrings(myElement.getProject()).stream()
            .map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}
