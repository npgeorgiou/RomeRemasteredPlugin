package rr.language.psi.references

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.util.IncorrectOperationException
import rr.language.RRUtil.findAllRegions
import rr.language.RRUtil.findAllRegionsAsStrings
import rr.language.RRUtil.findAllSettlements
import rr.language.RRUtil.findAllSettlementsAsStrings
import rr.language.psi.RRRegionOrSettlementRef

class RegionOrSettlementReference(element: PsiElement, rangeInElement: TextRange?) :
    PsiReferenceBase<PsiElement?>(element, rangeInElement), PsiReference {
    override fun resolve(): PsiElement? {
        val regions = findAllRegions(myElement!!.project)
            .map { it.regionNameDecl }
            .filter { myElement!!.text == it.text }

        if (regions.isNotEmpty()) {
            return regions[0]
        }

        val settlements = findAllSettlements(myElement!!.project)
            .filter { myElement!!.text == it.text }

        return settlements.firstOrNull()
    }

    @Throws(IncorrectOperationException::class)
    override fun handleElementRename(newName: String): PsiElement {
        (myElement as RRRegionOrSettlementRef).setName(newName)
        return myElement as RRRegionOrSettlementRef
    }

    override fun getVariants(): Array<Any> {
        return (findAllRegionsAsStrings(myElement!!.project) + findAllSettlementsAsStrings(myElement!!.project))
            .map { it: String? -> LookupElementBuilder.create(it!!) }
            .toTypedArray()
    }
}
