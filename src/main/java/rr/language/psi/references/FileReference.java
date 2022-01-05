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
import rr.language.psi.RRFile;

public class FileReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public FileReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        RRFile dsadadsa = RRUtil.findRRFile(myElement.getText(), myElement.getProject()); 
        return RRUtil.findRRFile(myElement.getText(), myElement.getProject());
    }

    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        // TODO: Maybe implement if I find a way to properly link File to references too.
        return myElement;
    }

    @Override
    public Object @NotNull [] getVariants() {
        return RRUtil.findAllRRFiles(myElement.getProject()).stream()
            .map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}
