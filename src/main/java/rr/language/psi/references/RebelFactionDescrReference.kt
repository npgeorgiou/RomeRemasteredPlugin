package rr.language.psi.references

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.util.IncorrectOperationException
import rr.language.RRUtil
import rr.language.psi.RRRebelFactionDescrRef

class RebelFactionDescrReference(element: PsiElement, rangeInElement: TextRange?) :
    PsiReferenceBase<PsiElement?>(element, rangeInElement), PsiReference {
    override fun resolve(): PsiElement? {
        val items = RRUtil.findAllRebelFactions(myElement!!.project)
            .map { it.rebelFactionDescrDef }
            .filter { it.text == myElement!!.text }

        return items.firstOrNull()
    }

    @Throws(IncorrectOperationException::class)
    override fun handleElementRename(newName: String): PsiElement {
        (myElement as RRRebelFactionDescrRef).setName(newName)
        return myElement as RRRebelFactionDescrRef
    }

    override fun getVariants(): Array<Any> {
        return RRUtil.findAllRebelFactionsAsStrings(myElement!!.project)
            .map { LookupElementBuilder.create(it) }
            .toTypedArray()

    }
}
