package rr.language.completion.providers

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext
import rr.language.RRUtil.findAllCulturesAsStrings
import rr.language.RRUtil.findAllFactionsAsStrings
import rr.language.RRUtil.findRRFile

class FactionsAndCultures : RRCompletionProvider() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        resultSet: CompletionResultSet
    ) {
        val project = parameters.originalFile.project
        val file = findRRFile("descr_cultures.txt", project) ?: return

        var items = findAllCulturesAsStrings(project)
        for (item in items) {
            resultSet.addElement(LookupElementBuilder.create(item).withTypeText("culture"))
        }

        items = findAllFactionsAsStrings(project)
        for (item in items) {
            resultSet.addElement(LookupElementBuilder.create(item).withTypeText("faction"))
        }
    }
}