package rr.language.psi.references

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.util.IncorrectOperationException
import rr.language.RRUtil.findAllSettlements
import rr.language.RRUtil.findAllSettlementsAsStrings
import rr.language.psi.RRSettlementRef

class SettlementReference(element: PsiElement, rangeInElement: TextRange?) :
    PsiReferenceBase<PsiElement?>(element, rangeInElement), PsiReference {
    override fun resolve(): PsiElement? {
        val items = findAllSettlements(myElement!!.project)
            .filter { it.text == myElement!!.text }

        return items.firstOrNull()
    }

    @Throws(IncorrectOperationException::class)
    override fun handleElementRename(newName: String): PsiElement {
        (myElement as RRSettlementRef).setName(newName)
        return myElement as RRSettlementRef
    }

    override fun getVariants(): Array<Any> {
        return findAllSettlementsAsStrings(myElement!!.project)
            .map { LookupElementBuilder.create(it) }
            .toTypedArray()
    }
}
