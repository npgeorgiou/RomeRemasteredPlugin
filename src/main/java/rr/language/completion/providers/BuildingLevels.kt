package rr.language.completion.providers

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext
import rr.language.RRUtil.findAllBuildingTrees
import rr.language.psi.RRBuildingTree
import java.util.stream.Collectors

class BuildingLevels(private var restrict: Boolean) : RRCompletionProvider() {

    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        resultSet: CompletionResultSet
    ) {
        val typed = parameters.position
        val project = parameters.originalFile.project
        var filter = { _: RRBuildingTree? -> true }

        if (restrict) {
            val buildingTree = typed.parent.prevSibling.prevSibling.text
            filter = { it?.buildingTreeNameDecl!!.text == buildingTree }
        }

        val levels = findAllBuildingTrees(project)
            .filter(filter)
            .flatMap { it.buildingLevelList }
            .map { it.firstChild.text }

        for (level in levels) {
            resultSet.addElement(LookupElementBuilder.create(level))
        }
    }
}