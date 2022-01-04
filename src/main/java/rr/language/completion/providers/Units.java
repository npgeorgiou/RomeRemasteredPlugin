package rr.language.completion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Function;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRUtil;
import rr.language.psi.*;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Units extends RRCompletionProvider {

    Function<String, String> decorator = (it) -> it;

    public Units() {
    }

    public Units(Function<String, String> decorator) {
        this.decorator = decorator;
    }

    @Override
    public void addCompletions(
        @NotNull CompletionParameters parameters,
        @NotNull ProcessingContext context,
        @NotNull CompletionResultSet resultSet
    ) {
        PsiElement typed = parameters.getPosition();
        Project project = parameters.getOriginalFile().getProject();

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

        Collection<String> names = RRUtil.findAllUnitsAsStrings(project).stream()
            .map(it -> decorator.fun(it))
            .collect(Collectors.toList());

        for (String name : names) {
            resultSet.addElement(LookupElementBuilder.create(name));
        }
    }
}