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
import rr.language.psi.RRTgaFile_;
import rr.language.psi.RRTxtFile_;
import rr.language.psi.references.TgaFileReference;

import javax.swing.*;
import javax.swing.text.IconView;
import java.util.Collection;

public class RRLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result
    ) {
        boolean isTxtFile = element instanceof RRTxtFile_;
        boolean isTgaFile = element instanceof RRTgaFile_;

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
            PsiFile file = (new TgaFileReference(element)).resolve();

            if (file == null)
                return;

            RelatedItemLineMarkerInfo<PsiElement> marker = NavigationGutterIconBuilder.create(RRIcons.TGA_FILE)
                .setTarget(file)
                .setTooltipText("Navigate to file")
                .createLineMarkerInfo(element);

            result.add(marker);
        }

    }

}