package rr.language.colors

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import rr.language.psi.*

class RRHighlightingAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is RRCoords ||
            element is RRCoordsWithXy ||
            element is RRCoordsNoComma ||
            element is RRCoordsXyz ||
            element is RRCoordsXyzNoComma
        ) {
            color(element, RRSyntaxHighlighter.COORDS_STYLE, holder)
        }
        if (element is RRUnitNameDecl ||
            element is RRUnitRef ||
            element is RRAncillaryNameDecl ||
            element is RRAncillaryRef ||
            element is RRModelNameDecl ||
            element is RRModelRef ||
            element is RRMountNameDecl ||
            element is RRMountRef ||
            element is RRShipNameDecl ||
            element is RRShipRef ||
            element is RRAnimalNameDecl ||
            element is RRAnimalRef ||
            element is RRTraitNameDecl ||
            element is RRTraitRef ||
            element is RRRebelFactionNameDef ||
            element is RRRebelFactionRef
        ) {
            color(element, RRSyntaxHighlighter.ID_STYLE, holder)
        }
        if (element is RRTgaFile_) {
            color(element, RRSyntaxHighlighter.FILES_STYLE, holder)
        }
    }

    private fun color(element: PsiElement, style: TextAttributesKey, holder: AnnotationHolder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(element).textAttributes(style).create()
    }
}