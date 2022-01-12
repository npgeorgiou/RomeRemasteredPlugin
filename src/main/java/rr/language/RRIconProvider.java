package rr.language;

import com.intellij.ide.IconProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import rr.language.psi.RRScript_;
import javax.swing.*;

public class RRIconProvider extends IconProvider {

    public Icon getIcon(@NotNull PsiElement psiElement, int flags) {
        PsiFile containingFile = psiElement.getContainingFile();

        if (containingFile != null && containingFile.getFirstChild() instanceof RRScript_) {
            return RRIcons.SCRIPT_FILE;
        }

        return null;
    }
}