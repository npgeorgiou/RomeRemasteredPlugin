package rr.language.completion.providers

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.util.ProcessingContext

/* Extended so that visibility of addCompletions can be made public, so that Providers can call other Providers.*/
open class RRCompletionProvider : CompletionProvider<CompletionParameters>() {
    public override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        resultSet: CompletionResultSet
    ) {
    }
}