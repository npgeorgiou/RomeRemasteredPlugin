package rr.language.completion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;

import java.util.Collection;
import java.util.stream.Collectors;

public class EthnicityMakeups extends RRCompletionProvider {

    public EthnicityMakeups() {
    }

    @Override
    public void addCompletions(
        @NotNull CompletionParameters parameters,
        @NotNull ProcessingContext context,
        @NotNull CompletionResultSet resultSet
    ) {
        Project project = parameters.getOriginalFile().getProject();

        Collection<String> items = RRUtil.findAllEthnicityMakeupsAsStrings(project);

        for (String item : items) {
            resultSet.addElement(LookupElementBuilder.create(item));
        }
    }
}