package rr.language;

import com.intellij.ide.IconProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import rr.language.psi.RRScript_;

import javax.swing.*;
import java.util.Arrays;

public class RRIconProvider extends IconProvider {

    public Icon getIcon(@NotNull PsiElement psiElement, int flags) {
        PsiFile containingFile = psiElement.getContainingFile();

        if (containingFile == null) return null;

        long script = Arrays.stream(containingFile.getChildren())
            .filter(it -> it instanceof RRScript_).count();

        if (script == 0) return null;

        return RRIcons.SCRIPT_FILE;
    }
}