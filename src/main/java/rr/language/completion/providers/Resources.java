package rr.language.completion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRUtil;
import rr.language.psi.*;
import rr.language.psi.impl.RRLandmarkSectionImpl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Resources extends RRCompletionProvider {

    public Resources() {
    }

    @Override
    public void addCompletions(
        @NotNull CompletionParameters parameters,
        @NotNull ProcessingContext context,
        @NotNull CompletionResultSet resultSet
    ) {
        PsiElement typed = parameters.getPosition();
        Project project = parameters.getOriginalFile().getProject();

        Collection<String> items = RRUtil.findAllResourcesAsStrings(false, project);

        for (String item : items) {
            resultSet.addElement(LookupElementBuilder.create(item));
        }
    }
}