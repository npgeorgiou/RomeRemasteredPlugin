package rr.language.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.RRElementFactory;
import rr.language.psi.RRMacroDef_;
import rr.language.psi.RRMacro_;
import rr.language.psi.RRStatement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ExpandMacros extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        var file = event.getData(PlatformDataKeys.VIRTUAL_FILE);
        if (file == null) return;

        var project = event.getProject();

        var rrFile = RRUtil.findRRFile(file.getName(), project);
        var copy = rrFile.copy();

        expandMacrosIn(copy, PsiTreeUtil.findChildrenOfAnyType(copy, RRMacroDef_.class), project);

        for (RRMacroDef_ macroDef : PsiTreeUtil.findChildrenOfAnyType(copy, RRMacroDef_.class)) {
            macroDef.delete();
        }

        var text = copy.getText();

        var interpolations = StringUtils.substringsBetween(text, "#", "#");
        if (interpolations != null) {
            Set<String> uniqueInterpolations = new HashSet<>(Arrays.asList(interpolations));
            for (var interpolation : uniqueInterpolations) {
                text = text.replace("#" + interpolation + "#", interpolation);
            }
        }


        var path = file.getPath().replace("#", "@");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(text);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void expandMacrosIn(PsiElement statement, Collection<RRMacroDef_> macroDefs, Project project) {
        var macrosUsages = PsiTreeUtil.findChildrenOfAnyType(statement, RRMacro_.class);

        for (RRMacro_ macroUsage : macrosUsages) {
            var name = macroUsage.getMacroName().getText();
            var args = macroUsage.getMacroArgList();

            for (RRMacroDef_ macroDef : macroDefs) {
                if (macroDef.getMacroName().getText().equals(name)) {
                    var params = macroDef.getMacroParamList();

                    var expandedString = macroDef.getText();
                    var paramsIterator = params.iterator();
                    var argsIterator = args.iterator();
                    while (paramsIterator.hasNext() && argsIterator.hasNext()) {
                        expandedString = expandedString.replace(
                            " " + paramsIterator.next().getText(),
                            " " + argsIterator.next().getText()
                        );
                    }

                    var createdMacroDef = RRElementFactory.createScript(project, expandedString).
                        getStatementList().get(0).getMacroDef_();

                    for (RRStatement s : createdMacroDef.getStatementList()) {
                        expandMacrosIn(s, macroDefs, project);
                    }

                    // Take care of indentation of inserted elements.
                    var wsToAdd = PsiTreeUtil.prevLeaf(macroUsage).getText().replace("\n", "");
                    var createdWss = RRUtil.collectLeaves(
                        createdMacroDef,
                        it -> Arrays.stream(it.getNode().getChildren(TokenSet.WHITE_SPACE))
                            .map(astNode -> astNode.getPsi())
                            .collect(Collectors.toList()),
                        new ArrayList<>()
                    );

                    for (var createdWs : createdWss) {
                        if (createdWs.getText().contains("\n")) {
                            var newWs = RRElementFactory.createWhitespace(createdWs.getText() + wsToAdd, project);
                            createdWs.replace(newWs);
                        }
                    }

                    macroUsage.getParent().addRangeAfter(
                        createdMacroDef.getStatementList().get(0),
                        createdMacroDef.getStatementList().get(createdMacroDef.getStatementList().size() - 1),
                        macroUsage
                    );

                    macroUsage.delete();
                    break;
                }
            }
        }
    }
}
