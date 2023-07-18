package rr.language.psi.references

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.util.IncorrectOperationException
import rr.language.RRUtil.findAllUnits
import rr.language.RRUtil.findAllUnitsAsStrings
import rr.language.Util.unquote
import rr.language.psi.RRStrUnitRef
import rr.language.psi.RRUnitRef

class UnitReference(element: PsiElement, rangeInElement: TextRange?) :
    PsiReferenceBase<PsiElement?>(element, rangeInElement), PsiReference {
    override fun resolve(): PsiElement? {
        val units = findAllUnits(myElement!!.project)
            .map { it.unitNameDecl }
            .filter { it.text == unquote(myElement!!.text) }

        return units.firstOrNull()
    }

    @Throws(IncorrectOperationException::class)
    override fun handleElementRename(newName: String): PsiElement {
        if (myElement!!.text.startsWith("\"")) {
            (myElement as RRStrUnitRef).setName(newName)
        } else {
            (myElement as RRUnitRef).setName(newName)
        }

        return myElement as PsiElement
    }

    override fun getVariants(): Array<Any> {
        return findAllUnitsAsStrings(myElement!!.project)
            .map { LookupElementBuilder.create(it) }
            .toTypedArray()
    }
}
