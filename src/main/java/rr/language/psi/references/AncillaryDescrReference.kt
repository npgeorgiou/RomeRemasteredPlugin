package rr.language.psi.references

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.util.IncorrectOperationException
import rr.language.RRUtil
import rr.language.RRUtil.findAllAncillaries
import rr.language.RRUtil.findAllAncillaryDescriptions
import rr.language.psi.RRAncillaryDef
import rr.language.psi.RRAncillaryDescrDef
import rr.language.psi.RRAncillaryDescrRef
import rr.language.psi.RRAncillaryNameDecl
import java.util.stream.Collectors

class AncillaryDescrReference(element: PsiElement, rangeInElement: TextRange?) :
    PsiReferenceBase<PsiElement?>(element, rangeInElement), PsiReference {
    override fun resolve(): PsiElement? {
        val descriptions = findAllAncillaryDescriptions(myElement!!.project)
            .filter { it.text == myElement!!.text }

        if (descriptions.isNotEmpty()) {
            return descriptions[0]
        }

        val names = findAllAncillaries(myElement!!.project)
            .map { it.ancillaryNameDecl }
            .filter { it!!.text == myElement!!.text }

        return names.firstOrNull()
    }

    @Throws(IncorrectOperationException::class)
    override fun handleElementRename(newName: String): PsiElement {
        (myElement as RRAncillaryDescrRef).setName(newName)
        return myElement as RRAncillaryDescrRef
    }

    override fun getVariants(): Array<Any> {
        return RRUtil.findAllAncillaryDescriptionsAsStrings(myElement!!.project)
            .map { LookupElementBuilder.create(it) }
            .toTypedArray()
    }
}
