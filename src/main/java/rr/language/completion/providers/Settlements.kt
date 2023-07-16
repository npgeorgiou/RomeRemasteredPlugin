package rr.language.completion.providers

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext
import rr.language.RRUtil.findAllRegions
import rr.language.psi.RRRegionDef
import java.util.stream.Collectors

class Settlements : RRCompletionProvider() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        resultSet: CompletionResultSet
    ) {
        val typed = parameters.position
        val project = parameters.originalFile.project
        val items: Collection<String> = findAllRegions(project).stream()
            .map { it: RRRegionDef -> it.firstChild.nextSibling.nextSibling.text }
            .collect(Collectors.toList())
        for (item in items) {
            resultSet.addElement(LookupElementBuilder.create(item))
        }
    }
}