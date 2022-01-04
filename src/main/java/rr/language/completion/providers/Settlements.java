package rr.language.completion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.RRDescrRegions;
import rr.language.psi.RRFile;

import java.util.Collection;
import java.util.stream.Collectors;

public class Settlements extends RRCompletionProvider {

    public Settlements() {
    }

    @Override
    public void addCompletions(
        @NotNull CompletionParameters parameters,
        @NotNull ProcessingContext context,
        @NotNull CompletionResultSet resultSet
    ) {
        PsiElement typed = parameters.getPosition();
        Project project = parameters.getOriginalFile().getProject();

        Collection<String> items = RRUtil.findAllRegions(project).stream()
            .map(it -> it.getFirstChild().getNextSibling().getNextSibling().getText())
            .collect(Collectors.toList());

        for (String item : items) {
            resultSet.addElement(LookupElementBuilder.create(item));
        }
    }
}