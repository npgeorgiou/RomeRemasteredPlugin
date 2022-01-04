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

public class EthnicityReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public EthnicityReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        RRFile file = RRUtil.findRRFile("descr_unit_variation.txt", myElement.getProject());

        if (file == null) {
            return null;
        }

        List<RREthnicity_> items = file.findChildByClass(RRDescrUnitVariation.class)
            .getEthnicity_List().stream()
            .filter(it -> it.getEthnicityNameDecl().getText().equals(Util.unquote(myElement.getText())))
            .collect(Collectors.toList());

        if (items.isEmpty()) {
            return null;
        }

        return items.get(0).getEthnicityNameDecl();
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        if (myElement.getText().startsWith("\"")) {
            ((RRStrEthnicityRef) myElement).setName(newName);
        } else {
            ((RREthnicityRef) myElement).setName(newName);
        }

        return myElement;
    }
}
