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

public class FactionsAndCultures extends RRCompletionProvider {

    public FactionsAndCultures() {
    }

    @Override
    public void addCompletions(
        @NotNull CompletionParameters parameters,
        @NotNull ProcessingContext context,
        @NotNull CompletionResultSet resultSet
    ) {
        Project project = parameters.getOriginalFile().getProject();
        RRFile file = RRUtil.findRRFile("descr_cultures.txt", project);

        if (file == null) {
            return;
        }

        Collection<String> items = RRUtil.findAllCulturesAsStrings(project);

        for (String item : items) {
            resultSet.addElement(LookupElementBuilder.create(item).withTypeText("culture"));
        }

        items = RRUtil.findAllFactionsAsStrings(project);

        for (String item : items) {
            resultSet.addElement(LookupElementBuilder.create(item).withTypeText("faction"));
        }
    }
}