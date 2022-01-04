package rr.language.completion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.RRExportDescrBuildings;
import rr.language.psi.RRFile;
import rr.language.psi.RRTags_;
import rr.language.psi.RRTokenType;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class BuildingTags extends RRCompletionProvider {

    public BuildingTags() {
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

        Collection<String> items = Arrays.stream(file
            .findChildByClass(RRExportDescrBuildings.class)
            .getTags_().getNode().getChildren(null))
            .filter(it -> it.getElementType().toString().equals("RRTokenType.ID"))
            .map(it -> it.getText())
            .collect(Collectors.toList());

        for (String item : items) {
            resultSet.addElement(LookupElementBuilder.create(item));
        }
    }
}