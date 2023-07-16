package rr.language

import com.intellij.openapi.application.QueryExecutorBase
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.util.Processor
import rr.language.psi.RRBuildingLevelNameDecl
import rr.language.psi.RRBuildingTreeNameDecl

class RRReferencesSearch : QueryExecutorBase<PsiReference?, ReferencesSearch.SearchParameters>(true) {
    override fun processQuery(
        queryParameters: ReferencesSearch.SearchParameters,
        consumer: Processor<in PsiReference?>
    ) {
        val element = queryParameters.elementToSearch
        if (element is RRBuildingTreeNameDecl || element is RRBuildingLevelNameDecl) {
            extendSearchTo("${element.text}_desc", element, queryParameters)
            extendSearchTo("${element.text}_desc_short", element, queryParameters)
            extendSearchTo("${element.text}_name", element, queryParameters)

            val factionsAndCulturesNames = RRUtil.findAllFactionsAsStrings(element.project)
            factionsAndCulturesNames.addAll(RRUtil.findAllCulturesAsStrings(element.project))
            for (factionOrCultureName in factionsAndCulturesNames) {
                extendSearchTo("${element.text}_$factionOrCultureName", element, queryParameters)
                extendSearchTo("${element.text}_${factionOrCultureName}_desc", element, queryParameters)
                extendSearchTo("${element.text}_${factionOrCultureName}_desc_short", element, queryParameters)
            }
        }
    }

    private fun extendSearchTo(word: String, element: PsiElement, queryParameters: ReferencesSearch.SearchParameters) {
        queryParameters.optimizer.searchWord(word, queryParameters.effectiveSearchScope, false, element)
    }
}