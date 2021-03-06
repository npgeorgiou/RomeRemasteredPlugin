package rr.language.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import jnr.posix.JavaLibCHelper;
import org.jetbrains.annotations.NotNull;
import rr.language.RRParserDefinition;
import rr.language.RRUtil;
import rr.language.psi.RRElementFactory;
import rr.language.psi.RRFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class AddFileTypeComments extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }

    String[] addMarkersTo = new String[]{
        "export_buildings.txt",
        "names.txt",
        "descr_regions.txt",
        "descr_mercenaries.txt",
        "descr_strat.txt",
        "descr_cultures.txt",
        "descr_sm_major_events.txt",
        "descr_unit_variation.txt",
        "export_descr_ancillaries.txt",
        "export_descr_buildings.txt",
        "export_descr_character_traits.txt",
        "export_descr_unit.txt",
        "feral_descr_ai_personality.txt",
        "descr_faction_groups.txt",
        "descr_sm_factions.txt",
        "descr_sm_resources.txt",
        "feral_descr_portraits_variation.txt",
        "descr_banners.txt",
        "descr_character.txt",
        "descr_building_battle.txt",
        "descr_lbc_db.txt",
        "descr_offmap_models.txt",
        "landmarks.txt",
        "descr_sm_landmarks.txt",
        "descr_model_battle.txt",
        "descr_model_strat.txt",
        "descr_disasters.txt",
        "descr_mount.txt",
        "descr_rebel_factions.txt",
        "rebel_faction_descr.txt",
        "rebel_faction_descr_enums.txt",
        "descr_items.txt",
        "descr_sm_ambient_objects.txt",
        "descr_beliefs.txt",
        "descr_palette.txt",
        "descr_ship.txt",
        "descr_projectile_new.txt",
        "descr_animals.txt",
        "export_vnvs.txt",
        "export_ancillaries.txt",
    };

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        // Example of how to use Grammar-kit to parse files, instead of Antlr. LightPsi file is used only for that,
        // remove it from project. It was just for this test.
        //File file = new File("C:\\Users\\nikol\\AppData\\Local\\Feral Interactive\\Total War ROME REMASTERED\\Mods\\Local Mods\\playground\\data\\descr_banners.txt");
        //var foo = LightPsi.parseFile(file, new RRParserDefinition());

        Project project = event.getProject();

        Collection<RRFile> files = RRUtil.findAllRRFiles(project);

        for (RRFile file : files) {
            String fileName = file.getVirtualFile().getName();

            if (!Arrays.asList(addMarkersTo).contains(fileName)) {
                continue;
            }

            if ( file.getFirstChild().getText().startsWith(";" + fileName)) {
                continue;
            }

            // For now skip that. This file bugs when it has a ; comment on top.
            // I could put a ?? comment, but ?? char behaves weirdly when you add it to a string
            if (fileName.endsWith("export_vnvs.txt")) {
                continue;
            }

            PsiElement newline = RRElementFactory.createNewline(project);
            PsiElement comment = RRElementFactory.createComment(
                project,
                fileName + " DO NOT REMOVE THIS LINE, IT IS NEEDED FOR THE IDE PLUGIN TO WORK"
            );

            WriteCommandAction.writeCommandAction(project).withName("Foo").run(() ->
                file.addBefore(newline, file.getFirstChild())
            );
            WriteCommandAction.writeCommandAction(project).withName("Foo").run(() ->
                file.addBefore(comment, file.getFirstChild())
            );
        }
    }
//
//    private boolean isScript(VirtualFile file) {
//        text = file.
//    }
}
