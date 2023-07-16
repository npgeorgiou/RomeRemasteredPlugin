package rr.language.completion.providers

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext
import rr.language.RRUtil.findAllUnitsAsStrings

class Units : RRCompletionProvider {
    var decorator = { it: String? -> it }

    constructor()
    constructor(decorator: (String?) -> String?) {
        this.decorator = decorator
    }

    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        resultSet: CompletionResultSet
    ) {
        val typed = parameters.position
        val project = parameters.originalFile.project

        // Filter out non-faction units
//        Predicate<PsiElement> filter = it -> true;
//        @Nullable RRFactionItem faction = PsiTreeUtil.getNonStrictParentOfType(typed, RRFactionItem.class);
//        if (faction != null) {
//            String factionName = faction.getFirstChild().getNextSibling().getNextSibling().getText();
//            filter = it -> it.getNode()
//                    .findChildByType(RRTypes.OWNERSHIP)
//                    .getPsi().getNextSibling().getNextSibling()
//                    .getText().equals(factionName);
//
//        }

        val names = findAllUnitsAsStrings(project).map { decorator(it) }

        for (name in names) {
            resultSet.addElement(LookupElementBuilder.create(name!!))
        }
    }
}