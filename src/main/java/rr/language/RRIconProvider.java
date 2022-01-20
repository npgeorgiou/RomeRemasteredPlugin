package rr.language;

import com.intellij.ide.IconProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class RRIconProvider extends IconProvider {

    public Icon getIcon(@NotNull PsiElement psiElement, int flags) {
        PsiFile containingFile = psiElement.getContainingFile();

        if (containingFile == null) return null;

        if (!RRUtil.isScript(containingFile)) return null;

        return RRIcons.SCRIPT_FILE;
    }
}