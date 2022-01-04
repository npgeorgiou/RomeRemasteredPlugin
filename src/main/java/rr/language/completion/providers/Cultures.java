package rr.language.completion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.Util;
import rr.language.psi.RRDescrStrat;
import rr.language.psi.RRFile;
import rr.language.psi.RRKvFormat;

import java.util.Collection;
import java.util.stream.Collectors;

public class Cultures extends RRCompletionProvider {

    public Cultures() {
    }

    @Override
    public void addCompletions(
        @NotNull CompletionParameters parameters,
        @NotNull ProcessingContext context,
        @NotNull CompletionResultSet resultSet
    ) {
        PsiElement typed = parameters.getPosition();
        Project project = parameters.getOriginalFile().getProject();

        Collection<String> items = RRUtil.findAllCulturesAsStrings(project);

        for (String item : items) {
            resultSet.addElement(LookupElementBuilder.create(item));
        }
    }
}