package rr.language.completion.providers

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext
import rr.language.RRUtil.findAllBuildingTreesAsStrings

class BuildingTrees : RRCompletionProvider() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        resultSet: CompletionResultSet
    ) {
        val typed = parameters.position
        val project = parameters.originalFile.project
        val buildings = findAllBuildingTreesAsStrings(project)
        for (building in buildings) {
            resultSet.addElement(LookupElementBuilder.create(building))
        }
    }
}