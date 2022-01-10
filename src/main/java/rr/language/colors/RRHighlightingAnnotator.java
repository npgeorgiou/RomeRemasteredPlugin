package rr.language.colors;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import rr.language.psi.RRCoords;
import rr.language.psi.RRCoordsNoComma;
import rr.language.psi.RRCoordsWithXy;
import rr.language.psi.RRCoordsXyz;

public class RRHighlightingAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (
            element instanceof RRCoords ||
            element instanceof RRCoordsWithXy ||
            element instanceof RRCoordsNoComma ||
            element instanceof RRCoordsXyz
        ) {
            color(element, RRSyntaxHighlighter.COORDS_STYLE, holder);
        }
    }

    private void color(PsiElement element, TextAttributesKey style, AnnotationHolder holder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(element).textAttributes(style).create();
    }
}