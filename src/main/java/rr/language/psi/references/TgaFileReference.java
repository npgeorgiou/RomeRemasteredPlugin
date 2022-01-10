package rr.language.psi.references;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRUtil;
import rr.language.Util;

public class TgaFileReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public TgaFileReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        return RRUtil.findTgaFile(Util.unquote(myElement.getText()), myElement.getProject());
    }

    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        // TODO: Maybe implement if I find a way to properly link File to references too.
        return myElement;
    }

    @Override
    public Object @NotNull [] getVariants() {
        return RRUtil.findAllRRFiles(myElement.getProject()).stream()
            .map(it -> LookupElementBuilder.create(it.getVirtualFile().getName()))
            .toArray();
    }
}
