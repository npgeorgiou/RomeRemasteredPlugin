package rr.language.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.RRElementFactory;
import rr.language.psi.RRFile;

import java.util.*;

public class AddFileTypeComments extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        // Example of how to use Grammar-kit to parse files, instead of Antlr. LightPsi file is used only for that,
        // remove it from project. It was just for this test.
        //File file = new File("C:\\Users\\nikol\\AppData\\Local\\Feral Interactive\\Total War ROME REMASTERED\\Mods\\Local Mods\\playground\\data\\descr_banners.txt");
        //var foo = LightPsi.parseFile(file, new RRParserDefinition());

        Project project = event.getProject();

        HashMap<RRFile, String> filesAndMarker = new HashMap<>();
        filesAndMarker.put(RRUtil.findRRFile("export_buildings.txt", project), "¬EXPORT_BUILDINGS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("names.txt", project), "¬NAMES_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("rebel_faction_descr.txt", project), "¬REBEL_FACTION_DESCR_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("export_vnvs.txt", project), "¬EXPORT_VNVS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("export_ancillaries.txt", project), "¬EXPORT_ANCILLARIES_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("landmarks.txt", project), "¬LANDMARKS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFileThatEndsWith("_campaign_regions_and_settlement_names.txt", project), "¬CAMPAIGN_REGIONS_AND_SETTLEMENT_NAMES_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_regions.txt", project), ";DESCR_REGIONS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_mercenaries.txt", project), ";DESCR_MERCENARIES_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_strat.txt", project), ";DESCR_STRAT_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_cultures.txt", project), ";DESCR_CULTURES_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_sm_major_events.txt", project), ";DESCR_SM_MAJOR_EVENTS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_unit_variation.txt", project), ";DESCR_UNIT_VARIATION_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("export_descr_ancillaries.txt", project), ";EXPORT_DESCR_ANCILLARIES_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("export_descr_buildings.txt", project), ";EXPORT_DESCR_BUILDINGS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("export_descr_character_traits.txt", project), ";EXPORT_DESCR_CHARACTER_TRAITS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("export_descr_unit.txt", project), ";EXPORT_DESCR_UNIT_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("feral_descr_ai_personality.txt", project), ";FERAL_DESCR_AI_PERSONALITY_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_faction_groups.txt", project), ";DESCR_FACTION_GROUPS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_sm_factions.txt", project), ";DESCR_SM_FACTIONS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_sm_resources.txt", project), ";DESCR_SM_RESOURCES_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("feral_descr_portraits_variation.txt", project), ";FERAL_DESCR_PORTRAITS_VARIATION_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_banners.txt", project), ";DESCR_BANNERS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_character.txt", project), ";DESCR_CHARACTER_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_building_battle.txt", project), ";DESCR_BUILDING_BATTLE_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_lbc_db.txt", project), ";DESCR_LBC_DB_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_offmap_models.txt", project), ";DESCR_OFFMAP_MODELS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_sm_landmarks.txt", project), ";DESCR_SM_LANDMARKS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_model_battle.txt", project), ";DESCR_MODEL_BATTLE_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_model_strat.txt", project), ";DESCR_MODEL_STRAT_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_disasters.txt", project), ";DESCR_DISASTERS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_mount.txt", project), ";DESCR_MOUNT_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_rebel_factions.txt", project), ";DESCR_REBEL_FACTIONS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("rebel_faction_descr_enums.txt", project), ";REBEL_FACTION_DESCR_ENUMS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_items.txt", project), ";DESCR_ITEMS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_sm_ambient_objects.txt", project), ";DESCR_SM_AMBIENT_OBJECTS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_beliefs.txt", project), ";DESCR_BELIEFS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_palette.txt", project), ";DESCR_PALETTE_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_ship.txt", project), ";DESCR_SHIP_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_projectile_new.txt", project), ";DESCR_PROJECTILE_NEW_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_animals.txt", project), ";DESCR_ANIMALS_MARKER DO NOT REMOVE");
        filesAndMarker.put(RRUtil.findRRFile("descr_win_conditions.txt", project), ";DESCR_WIN_CONDITIONS_MARKER DO NOT REMOVE");

        for (var entry : filesAndMarker.entrySet()) {
            var file = entry.getKey();
            var marker = entry.getValue();

            if (file == null) {
                continue;
            }

            if (file.getFirstChild().getText().contains("_MARKER DO NOT REMOVE")) {
                continue;
            }

            if (marker.startsWith("¬")) {
                marker = marker.replace("¬", Character.toString(0x000AC));
            }

            PsiElement newline = RRElementFactory.createNewline(project);
            PsiElement comment = RRElementFactory.createComment(project, marker);

            WriteCommandAction.writeCommandAction(project).withName("Foo").run(() ->
                file.addBefore(newline, file.getFirstChild())
            );
            WriteCommandAction.writeCommandAction(project).withName("Foo").run(() ->
                file.addBefore(comment, file.getFirstChild())
            );
        }
    }
}
