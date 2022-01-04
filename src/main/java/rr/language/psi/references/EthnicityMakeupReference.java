package rr.language.psi.references;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRUtil;
import rr.language.psi.*;

import java.util.List;
import java.util.stream.Collectors;

public class EthnicityMakeupReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public EthnicityMakeupReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        List<RREthnicityMakeup_> items = RRUtil.findAllEthnicityMakeups(myElement.getProject()).stream()
            .filter(it -> it.getEthnicityMakeupNameDecl().getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (items.isEmpty()) {
            return null;
        }

        return items.get(0).getEthnicityMakeupNameDecl();
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RREthnicityMakeupRef) myElement).setName(newName);
        return myElement;
    }
}
