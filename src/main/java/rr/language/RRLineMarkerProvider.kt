package rr.language

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.psi.PsiElement
import com.intellij.util.ui.ImageUtil
import rr.language.psi.RRRgb
import rr.language.psi.RRTgaFile_
import rr.language.psi.RRTxtFile_
import java.awt.Color
import java.awt.image.BufferedImage
import javax.swing.Icon
import javax.swing.ImageIcon

class RRLineMarkerProvider : RelatedItemLineMarkerProvider() {
    override fun collectNavigationMarkers(
        element: PsiElement,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>?>
    ) {
        if (element is RRTxtFile_) {
            val file = RRUtil.findRRFile(element.text, element.project) ?: return
            var icon = RRIcons.FILE
            if (RRUtil.isScript(file)) {
                icon = RRIcons.SCRIPT_FILE
            }
            val marker = NavigationGutterIconBuilder.create(icon)
                .setTarget(file)
                .setTooltipText("Navigate to file")
                .createLineMarkerInfo(element.firstChild)
            result.add(marker)
            return
        }

        if (element is RRTgaFile_) {
//            PsiFile file = (new TgaFileReference(element)).resolve();
//
//            if (file == null) return;
//
//            RelatedItemLineMarkerInfo<PsiElement> marker = NavigationGutterIconBuilder.create(RRIcons.TGA_FILE)
//                .setTarget(file)
//                .setTooltipText("Navigate to file")
//                .createLineMarkerInfo(element.id!!);
//
//            result.add(marker);
//            return;
        }

        if (element is RRRgb) {
            val marker = NavigationGutterIconBuilder
                .create(createIconFor(element))
                .setTarget(null)
                .createLineMarkerInfo(element.firstChild)
            result.add(marker)
            return
        }
    }

    private fun createIconFor(element: RRRgb): Icon {
        val colors: Array<String> = if (element.text.contains(",")) {
            element.text.split(",\\s*".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        } else {
            element.text.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }

        val red = colors[0].toInt()
        val green = colors[1].toInt()
        val blue = colors[2].toInt()
        val image = ImageUtil.createImage(10, 10, BufferedImage.TYPE_INT_RGB)
        val graphics = image.createGraphics()
        graphics.paint = Color(red, green, blue)
        graphics.fillRect(0, 0, image.width, image.height)
        return ImageIcon(image)
    }
}