package rr.language.colors;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import rr.language.psi.*;

public class RRHighlightingAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (
            element instanceof RRCoords ||
                element instanceof RRCoordsWithXy ||
                element instanceof RRCoordsNoComma ||
                element instanceof RRCoordsXyz ||
                element instanceof RRCoordsXyzNoComma
        ) {
            color(element, RRSyntaxHighlighter.COORDS_STYLE, holder);
        }

        if (
            element instanceof RRUnitNameDecl ||
            element instanceof RRUnitRef ||
            element instanceof RRAncillaryNameDecl ||
            element instanceof RRAncillaryRef ||
            element instanceof RRModelNameDecl ||
            element instanceof RRModelRef ||
            element instanceof RRMountNameDecl ||
            element instanceof RRMountRef ||
            element instanceof RRShipNameDecl ||
            element instanceof RRShipRef ||
            element instanceof RRAnimalNameDecl ||
            element instanceof RRAnimalRef ||
            element instanceof RRTraitNameDecl ||
            element instanceof RRTraitRef ||
            element instanceof RRRebelFactionNameDef ||
            element instanceof RRRebelFactionRef
        ) {
            color(element, RRSyntaxHighlighter.ID_STYLE, holder);
        }

        if (element instanceof RRTgaFile_) {
            color(element, RRSyntaxHighlighter.FILES_STYLE, holder);
        }
    }

    private void color(PsiElement element, TextAttributesKey style, AnnotationHolder holder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(element).textAttributes(style).create();
    }
}