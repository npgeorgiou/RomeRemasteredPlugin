package rr.language.completion.providers

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.lang.ASTNode
import com.intellij.util.ProcessingContext
import rr.language.RRUtil.findRRFile
import rr.language.psi.RRExportDescrBuildings
import java.util.*
import java.util.stream.Collectors

class BuildingTags : RRCompletionProvider() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        resultSet: CompletionResultSet
    ) {
        val project = parameters.originalFile.project
        val file = findRRFile("export_descr_buildings.txt", project) ?: return

        val items = file.findChildByClass(RRExportDescrBuildings::class.java)
            ?.tags_!!.node.getChildren(null)
            .filter { it.elementType.toString() == "RRTokenType.ID" }
            .map { it.text }

        for (item in items) {
            resultSet.addElement(LookupElementBuilder.create(item))
        }
    }
}