package rr.language.psi.references

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.util.IncorrectOperationException
import rr.language.RRUtil
import rr.language.psi.RRAncillaryDescrRef

class UnitDescrReference(element: PsiElement, rangeInElement: TextRange?) :
    PsiReferenceBase<PsiElement?>(element, rangeInElement), PsiReference {
    override fun resolve(): PsiElement? {
        val name = RRUtil.removePostfixesFromExportUnitsId(myElement!!.text)

        val descriptions = RRUtil.findAllUnitDescriptions(myElement!!.project)
            .filter { it.text == name }

        return descriptions.firstOrNull()
    }

    @Throws(IncorrectOperationException::class)
    override fun handleElementRename(newName: String): PsiElement {
        (myElement as RRAncillaryDescrRef).setName(newName)
        return myElement as RRAncillaryDescrRef
    }

    override fun getVariants(): Array<Any> {
        return RRUtil.findAllUnitDescriptions(myElement!!.project)
            .map { LookupElementBuilder.create(it)}
            .toTypedArray()
    }
}
