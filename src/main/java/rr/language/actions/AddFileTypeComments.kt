package rr.language.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import rr.language.RRUtil.findRRFile
import rr.language.psi.RRElementFactory
import rr.language.psi.RRFile

class AddFileTypeComments : AnAction() {
    override fun update(e: AnActionEvent) {
        // Set the availability based on whether a project is open
        val project = e.project
        e.presentation.isEnabledAndVisible = project != null
    }

    override fun actionPerformed(event: AnActionEvent) {
        // Example of how to use Grammar-kit to parse files, instead of Antlr. LightPsi file is used only for that,
        // remove it from project. It was just for this test.
        //File file = new File("C:\\Users\\nikol\\AppData\\Local\\Feral Interactive\\Total War ROME REMASTERED\\Mods\\Local Mods\\playground\\data\\descr_banners.txt");
        //var foo = LightPsi.parseFile(file, new RRParserDefinition());
        val project = event.project!!
        val filesAndMarker = HashMap<RRFile?, String>()
        filesAndMarker[findRRFile("text/export_units.txt", project)] = "¬EXPORT_UNITS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("text/export_buildings.txt", project)] = "¬EXPORT_BUILDINGS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("text/names.txt", project)] = "¬NAMES_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("text/rebel_faction_descr.txt", project)] = "¬REBEL_FACTION_DESCR_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("text/export_vnvs.txt", project)] = "¬EXPORT_VNVS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("text/export_ancillaries.txt", project)] = "¬EXPORT_ANCILLARIES_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("text/landmarks.txt", project)] = "¬LANDMARKS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("_campaign_regions_and_settlement_names.txt", project)] = "¬CAMPAIGN_REGIONS_AND_SETTLEMENT_NAMES_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_regions.txt", project)] = ";DESCR_REGIONS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_mercenaries.txt", project)] = ";DESCR_MERCENARIES_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_strat.txt", project)] = ";DESCR_STRAT_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_cultures.txt", project)] = ";DESCR_CULTURES_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_sm_major_events.txt", project)] = ";DESCR_SM_MAJOR_EVENTS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_unit_variation.txt", project)] = ";DESCR_UNIT_VARIATION_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("export_descr_ancillaries.txt", project)] = ";EXPORT_DESCR_ANCILLARIES_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("export_descr_buildings.txt", project)] = ";EXPORT_DESCR_BUILDINGS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("export_descr_character_traits.txt", project)] = ";EXPORT_DESCR_CHARACTER_TRAITS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("export_descr_unit.txt", project)] = ";EXPORT_DESCR_UNIT_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("feral_descr_ai_personality.txt", project)] = ";FERAL_DESCR_AI_PERSONALITY_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_faction_groups.txt", project)] = ";DESCR_FACTION_GROUPS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_sm_factions.txt", project)] = ";DESCR_SM_FACTIONS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_sm_resources.txt", project)] = ";DESCR_SM_RESOURCES_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("feral_descr_portraits_variation.txt", project)] = ";FERAL_DESCR_PORTRAITS_VARIATION_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_banners.txt", project)] = ";DESCR_BANNERS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_character.txt", project)] = ";DESCR_CHARACTER_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_building_battle.txt", project)] = ";DESCR_BUILDING_BATTLE_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_lbc_db.txt", project)] = ";DESCR_LBC_DB_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_offmap_models.txt", project)] = ";DESCR_OFFMAP_MODELS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_sm_landmarks.txt", project)] = ";DESCR_SM_LANDMARKS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_model_battle.txt", project)] = ";DESCR_MODEL_BATTLE_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_model_strat.txt", project)] = ";DESCR_MODEL_STRAT_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_disasters.txt", project)] = ";DESCR_DISASTERS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_mount.txt", project)] = ";DESCR_MOUNT_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_rebel_factions.txt", project)] = ";DESCR_REBEL_FACTIONS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_items.txt", project)] = ";DESCR_ITEMS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_sm_ambient_objects.txt", project)] = ";DESCR_SM_AMBIENT_OBJECTS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_beliefs.txt", project)] = ";DESCR_BELIEFS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_palette.txt", project)] = ";DESCR_PALETTE_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_ship.txt", project)] = ";DESCR_SHIP_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_projectile_new.txt", project)] = ";DESCR_PROJECTILE_NEW_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_animals.txt", project)] = ";DESCR_ANIMALS_MARKER DO NOT REMOVE"
        filesAndMarker[findRRFile("descr_win_conditions.txt", project)] = ";DESCR_WIN_CONDITIONS_MARKER DO NOT REMOVE"

        for (entry in filesAndMarker.entries) {
            val file = entry.key
            var marker = entry.value

            if (file == null) {
                continue
            }

            if (file.firstChild.text.contains("_MARKER DO NOT REMOVE")) {
                continue
            }

            if (marker.startsWith("¬")) {
                marker = marker.replace("¬", Character.toString(0x000AC))
            }

            val newline = RRElementFactory.createNewline(project)
            val comment = RRElementFactory.createComment(project, marker)

            WriteCommandAction.runWriteCommandAction(project, { file.addBefore(newline, file.firstChild) })
            WriteCommandAction.runWriteCommandAction(project, { file.addBefore(comment, file.firstChild) })
        }
    }
}
