package rr.language.psi.references

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.util.IncorrectOperationException
import rr.language.RRUtil
import rr.language.psi.RRAncillaryRef

class AncillaryReference(element: PsiElement, rangeInElement: TextRange?) :
    PsiReferenceBase<PsiElement?>(element, rangeInElement), PsiReference {
    override fun resolve(): PsiElement? {
        val traits = RRUtil.findAllAncillaries(myElement!!.project)
            .map { it.ancillaryNameDecl }
            .filter { it!!.text == myElement!!.text }

        return traits.firstOrNull()
    }

    @Throws(IncorrectOperationException::class)
    override fun handleElementRename(newName: String): PsiElement {
        (myElement as RRAncillaryRef).setName(newName)
        return myElement as RRAncillaryRef
    }

    override fun getVariants(): Array<Any> {
        return RRUtil.findAllAncillariesAsStrings(myElement!!.project)
            .map { LookupElementBuilder.create(it) }
            .toTypedArray()
    }
}
