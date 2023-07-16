package rr.language.completion.providers

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext
import rr.language.RRUtil.findRRFile
import rr.language.psi.RRConditionAlias
import rr.language.psi.RRExportDescrBuildings
import java.util.stream.Collectors

class ConditionAliases : RRCompletionProvider() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        resultSet: CompletionResultSet
    ) {
        val project = parameters.originalFile.project
        val file = findRRFile("export_descr_buildings.txt", project) ?: return

        val aliases = file.findChildByClass(RRExportDescrBuildings::class.java)
            ?.conditionAliasList
            ?.map { it.firstChild.nextSibling.nextSibling.text } ?: return

        for (alias in aliases) {
            resultSet.addElement(LookupElementBuilder.create(alias).withTypeText("alias"))
        }
    }
}