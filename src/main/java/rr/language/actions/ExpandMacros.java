package rr.language.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
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

        var path = file.getPath().replace("#", "@");
        try {
            String str = copy.getText();
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(str);

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
                        System.out.println(1);
                        System.out.println(1);
                    }

                    var script = RRElementFactory.createScript(project, expandedString);

                    var createdMacroDef = script.getStatementList().get(0).getMacroDef_();

                    for (RRStatement s : createdMacroDef.getStatementList()) {
                        expandMacrosIn(s, macroDefs, project);
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
