package rr.language.completion.providers

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext
import rr.language.RRUtil.findAllResourcesAsStrings

class Resources : RRCompletionProvider() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        resultSet: CompletionResultSet
    ) {
        val typed = parameters.position
        val project = parameters.originalFile.project
        val items = findAllResourcesAsStrings(false, project)
        for (item in items) {
            resultSet.addElement(LookupElementBuilder.create(item))
        }
    }
}