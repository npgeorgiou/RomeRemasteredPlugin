package rr.language.completion.providers

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext
import rr.language.RRUtil.findAllEthnicityMakeupsAsStrings

class EthnicityMakeups : RRCompletionProvider() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        resultSet: CompletionResultSet
    ) {
        val project = parameters.originalFile.project
        val items = findAllEthnicityMakeupsAsStrings(project)
        for (item in items) {
            resultSet.addElement(LookupElementBuilder.create(item))
        }
    }
}