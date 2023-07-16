package rr.language.completion.providers

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext
import rr.language.RRUtil.findAllAncillariesAsStrings

class Ancillaries : RRCompletionProvider() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        resultSet: CompletionResultSet
    ) {
        val typed = parameters.position
        val project = parameters.originalFile.project
        val ancillaries = findAllAncillariesAsStrings(project)
        for (ancillary in ancillaries) {
            resultSet.addElement(LookupElementBuilder.create(ancillary))
        }
    }
}