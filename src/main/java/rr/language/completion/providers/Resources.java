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
        // TODO: Get it from descr_sm_resources instead?
        Project project = parameters.getOriginalFile().getProject();
        RRFile file = RRUtil.findRRFile("descr_strat.txt", project);

        if (file == null) {
            return;
        }

//        @Nullable RRDescrStrat descrStrat = file.findChildByClass(RRDescrStrat.class);
//
//        Collection<String> resourcesWithQuantityEnabled = descrStrat.getResourcesSection()
//            .getResourceQuantityEnabled_()
//            .getResourceItemList().stream()
//            .map(it -> it.getId().getText())
//            .collect(Collectors.toList());
//
//        Collection<String> resourcesWithQuantityDisabled = descrStrat.getResourcesSection()
//            .getResourceQuantityDisabled_()
//            .getResourceItemList().stream()
//            .map(it -> it.getId().getText())
//            .collect(Collectors.toList());
//
//        Collection<String> resources = Stream.concat(
//            resourcesWithQuantityEnabled.stream(),
//            resourcesWithQuantityDisabled.stream()
//        ).distinct().collect(Collectors.toList());
//
//        for (String resource : resources) {
//            resultSet.addElement(LookupElementBuilder.create(resource));
//        }
    }
}