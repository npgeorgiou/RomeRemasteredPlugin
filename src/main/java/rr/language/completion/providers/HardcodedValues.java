package rr.language.completion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class HardcodedValues extends RRCompletionProvider {
    String[] values;

    public HardcodedValues(String... values) {
        this.values = values;
    }

    @Override
    public void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
        PsiElement typed = parameters.getPosition();
        for (String value : values) {
            resultSet.addElement(LookupElementBuilder.create(value));
        }
    }
}