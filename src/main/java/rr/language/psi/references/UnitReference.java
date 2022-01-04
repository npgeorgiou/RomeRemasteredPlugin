package rr.language.psi.references;

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

public class UnitReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public UnitReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        List<RRUnitItem_> units = RRUtil.findAllUnits(myElement.getProject()).stream()
            .filter(it -> it.getUnitNameDecl().getText().equals(Util.unquote(myElement.getText())))
            .collect(Collectors.toList());

        if (units.isEmpty()) {
            return null;
        }

        return units.get(0).getUnitNameDecl();
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        if (myElement.getText().startsWith("\"")) {
            ((RRStrUnitRef) myElement).setName(newName);
        } else {
            ((RRUnitRef) myElement).setName(newName);
        }

        return myElement;
    }
}
