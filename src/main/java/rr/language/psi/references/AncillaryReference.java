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

public class AncillaryReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public AncillaryReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        RRFile file = RRUtil.findRRFile("export_descr_ancillaries.txt", myElement.getProject());

        if (file == null) {
            return null;
        }

        List<RRAncillaryDef> ancillaries = file.findChildByClass(RRExportDescrAncillaries.class)
            .getAncillaryDefList().stream()
            .filter(it -> it.getAncillaryNameDecl().getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (ancillaries.isEmpty()) {
            return null;
        }

        return ancillaries.get(0).getAncillaryNameDecl();
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRAncillaryRef) myElement).setName(newName);
        return myElement;
    }
}
