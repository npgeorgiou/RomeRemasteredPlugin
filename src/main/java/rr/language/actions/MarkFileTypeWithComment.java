package rr.language.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.RRElementFactory;
import rr.language.psi.RRFile;

import java.util.Arrays;
import java.util.Collection;

public class MarkFileTypeWithComment extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();

        String[] addMarkersTo = new String[]{
            "export_buildings.txt",
            "names.txt",
            "descr_regions.txt",
            "descr_mercenaries.txt",
            "descr_strat.txt",
            "descr_cultures.txt",
            "descr_names.txt",
            "descr_sm_major_events.txt",
            "descr_unit_variation.txt",
            "export_descr_ancillaries.txt",
            "export_descr_buildings.txt",
            "export_descr_character_traits.txt",
            "export_descr_unit.txt",
            "feral_descr_ai_personality.txt",
            "descr_faction_groups.txt",
            "descr_sm_factions.txt",
        };

        Collection<RRFile> files = RRUtil.findAllRRFiles(project);

        for (RRFile file : files) {
            String fileName = file.getVirtualFile().getName();

            if (!Arrays.asList(addMarkersTo).contains(fileName)) {
                continue;
            }

            if (file.getFirstChild().getText().startsWith(";" + fileName)) {
                continue;
            }

            PsiElement newline = RRElementFactory.createNewline(project);
            PsiElement comment = RRElementFactory.createComment(
                project,
                fileName + " DO NOT REMOVE THIS LINE, IT IS NEEDED FOR THE IDE PLUGIN TO WORK"
            );

            WriteCommandAction.writeCommandAction(project).withName("Foo").run(() ->
                file.addBefore(comment, file.getFirstChild())
            );
            WriteCommandAction.writeCommandAction(project).withName("Foo").run(() ->
                file.addAfter(newline, file.getFirstChild())
            );
            WriteCommandAction.writeCommandAction(project).withName("Foo").run(() ->
                file.addAfter(newline, file.getFirstChild())
            );
        }
    }
}
