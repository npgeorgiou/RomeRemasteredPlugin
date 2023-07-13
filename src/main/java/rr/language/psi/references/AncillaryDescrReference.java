package rr.language.psi.references;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRUtil;
import rr.language.psi.RRAncillaryDescrRef;

import java.util.stream.Collectors;

public class AncillaryDescrReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public AncillaryDescrReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        var descriptions = RRUtil.findAllAncillaryDescriptions(myElement.getProject()).stream()
            .filter(it -> it.getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (!descriptions.isEmpty()) {
            return descriptions.get(0);
        }

        var names = RRUtil.findAllAncillaries(myElement.getProject()).stream()
            .map(it -> it.getAncillaryNameDecl())
            .filter(it -> it.getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (names.isEmpty()) {
            return null;
        }

        return names.get(0);
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRAncillaryDescrRef) myElement).setName(newName);
        return myElement;
    }

    public Object @NotNull [] getVariants() {
        return new String[0];
    }
}
