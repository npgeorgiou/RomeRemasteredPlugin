package rr.language.psi.references

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.util.IncorrectOperationException
import rr.language.RRUtil.findAllRebelFactions
import rr.language.RRUtil.findAllRebelFactionsAsStrings
import rr.language.psi.RRRebelFactionRef

class RebelFactionReference(element: PsiElement, rangeInElement: TextRange?) :
    PsiReferenceBase<PsiElement?>(element, rangeInElement), PsiReference {
    override fun resolve(): PsiElement? {
        val items = findAllRebelFactions(myElement!!.project)
            .map { it.rebelFactionNameDef }
            .filter { it!!.text == myElement!!.text }

        return items.firstOrNull()
    }

    @Throws(IncorrectOperationException::class)
    override fun handleElementRename(newName: String): PsiElement {
        (myElement as RRRebelFactionRef).setName(newName)
        return myElement as RRRebelFactionRef
    }

    override fun getVariants(): Array<Any> {
        return findAllRebelFactionsAsStrings(myElement!!.project)
            .map { LookupElementBuilder.create(it) }
            .toTypedArray()
    }
}
