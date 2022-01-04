package rr.language.completion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.RRExportDescrBuildings;
import rr.language.psi.RRFile;

import java.util.Collection;
import java.util.stream.Collectors;

public class ConditionAliases extends RRCompletionProvider {

    public ConditionAliases() {
    }

    @Override
    public void addCompletions(
        @NotNull CompletionParameters parameters,
        @NotNull ProcessingContext context,
        @NotNull CompletionResultSet resultSet
    ) {
        Project project = parameters.getOriginalFile().getProject();
        RRFile file = RRUtil.findRRFile("export_descr_buildings.txt", project);

        if (file == null) {
            return;
        }

        Collection<String> aliases = file
            .findChildByClass(RRExportDescrBuildings.class)
            .getConditionAliasList().stream()
            .map(it -> it.getFirstChild().getNextSibling().getNextSibling().getText())
            .collect(Collectors.toList());

        for (String alias : aliases) {
            resultSet.addElement(LookupElementBuilder.create(alias).withTypeText("alias"));
        }
    }
}