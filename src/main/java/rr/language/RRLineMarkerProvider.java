package rr.language;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import rr.language.psi.RRFile;

import javax.swing.*;
import javax.swing.text.IconView;
import java.util.Collection;

public class RRLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result
    ) {
        boolean isTxtFile = element.getNode().getElementType().toString().equals("RRTokenType.TXT_FILE");
        boolean isTgaFile = element.getNode().getElementType().toString().equals("RRTokenType.TGA_FILE");

        if (isTxtFile) {
            RRFile file = RRUtil.findRRFile(element.getText(), element.getProject());

            if (file == null) return;

            RelatedItemLineMarkerInfo<PsiElement> marker = NavigationGutterIconBuilder.create(RRIcons.FILE)
                .setTarget(file)
                .setTooltipText("Navigate to file")
                .createLineMarkerInfo(element);

            result.add(marker);
        }

        if (isTgaFile) {
            PsiFile file = RRUtil.findTgaFile(element.getText(), element.getProject());

            if (file == null) return;

            RelatedItemLineMarkerInfo<PsiElement> marker = NavigationGutterIconBuilder.create(RRIcons.TGA_FILE)
                .setTarget(file)
                .setTooltipText("Navigate to file")
                .createLineMarkerInfo(element);

            result.add(marker);
        }

    }

}