package rr.language;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.ImageUtil;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import rr.language.psi.RRFile;
import rr.language.psi.RRRgb;
import rr.language.psi.RRTgaFile_;
import rr.language.psi.RRTxtFile_;
import rr.language.psi.references.TgaFileReference;

import javax.swing.*;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

public class RRLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result
    ) {
        boolean isTxtFile = element instanceof RRTxtFile_;
        boolean isTgaFile = element instanceof RRTgaFile_;
        boolean isRGB = element instanceof RRRgb;

        if (isTxtFile) {
            RRFile file = RRUtil.findRRFile(element.getText(), element.getProject());

            if (file == null) return;

            RelatedItemLineMarkerInfo<PsiElement> marker = NavigationGutterIconBuilder.create(RRIcons.FILE)
                .setTarget(file)
                .setTooltipText("Navigate to file")
                .createLineMarkerInfo(element);

            result.add(marker);
            return;
        }

        if (isTgaFile) {
//            PsiFile file = (new TgaFileReference(element)).resolve();
//
//            if (file == null) return;
//
//            RelatedItemLineMarkerInfo<PsiElement> marker = NavigationGutterIconBuilder.create(RRIcons.TGA_FILE)
//                .setTarget(file)
//                .setTooltipText("Navigate to file")
//                .createLineMarkerInfo(element);
//
//            result.add(marker);
//            return;
        }

        if (isRGB) {
            RelatedItemLineMarkerInfo<PsiElement> marker = NavigationGutterIconBuilder
                .create(createIconFor((RRRgb) element))
                .setTarget(null)
                .createLineMarkerInfo(element);

            result.add(marker);
            return;
        }
    }

    private Icon createIconFor(RRRgb element) {
        String[] colors = element.getText().split("\\s+");
        int red = Math.round(Float.parseFloat(colors[0]) * 255);
        int green = Math.round(Float.parseFloat(colors[1]) * 255);
        int blue = Math.round(Float.parseFloat(colors[2]) * 255);

        BufferedImage image = ImageUtil.createImage(10, 10, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(new JBColor(new Color(0, 0, 128), new Color(red, green, blue)));
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        return new ImageIcon(image);
    }

}