package rr.language;

import com.intellij.openapi.application.QueryExecutorBase;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.util.Processor;
import rr.language.psi.RRBuildingLevelNameDecl;
import rr.language.psi.RRBuildingTreeNameDecl;

import java.util.Collection;

public class RRReferencesSearch extends QueryExecutorBase<PsiReference, ReferencesSearch.SearchParameters> {

    @Override
    public void processQuery(
        ReferencesSearch.SearchParameters queryParameters,
        Processor<? super PsiReference> consumer
    ) {
        PsiElement element = queryParameters.getElementToSearch();

        if (element instanceof RRBuildingTreeNameDecl | element instanceof RRBuildingLevelNameDecl) {
            extendSearchTo(element.getText() + "_desc", element, queryParameters);
            extendSearchTo(element.getText() + "_desc_short", element, queryParameters);
            extendSearchTo(element.getText() + "_name", element, queryParameters);

            Collection<String> factionsAndCulturesNames = RRUtil.findAllFactionsAsStrings(element.getProject());
            factionsAndCulturesNames.addAll(RRUtil.findAllCulturesAsStrings(element.getProject()));
            for (String factionOrCultureName : factionsAndCulturesNames) {
                extendSearchTo(element.getText() + "_" + factionOrCultureName, element, queryParameters);
                extendSearchTo(element.getText() + "_" + factionOrCultureName + "_desc", element, queryParameters);
                extendSearchTo(element.getText() + "_" + factionOrCultureName + "_desc_short", element, queryParameters);
            }
        }
    }

    private void extendSearchTo(String word, PsiElement element, ReferencesSearch.SearchParameters queryParameters) {
        queryParameters.getOptimizer().searchWord(word, queryParameters.getEffectiveSearchScope(), false, element);
    }
}