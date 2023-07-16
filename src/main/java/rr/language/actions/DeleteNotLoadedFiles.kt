package rr.language.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

class DeleteNotLoadedFiles : AnAction() {
    var filesToDelete = arrayOf(
        "debug_descr_advice.txt",
        "descr_actions.txt",
        "descr_agent_ancillary.txt",
        "descr_arrow_trail_effects.txt",
        "descr_arrow_trail_effects_final.txt",
        "descr_arrow_trail_effects_new.txt",
        "descr_building_effects.txt",
        "descr_burning_building.txt",
        "descr_burning_men_effect.txt",
        "descr_burning_men_effect_final.txt",
        "descr_burning_men_effect_new.txt",
        "descr_burning_oil_effect.txt",
        "descr_cursor_actions_battle.txt",
        "descr_cursor_actions_classic.txt",
        "descr_db_test.txt",
        "descr_disasters.txt",
        "descr_effects_final.txt",
        "descr_grass.txt",
        "descr_ground_type_db.txt",
        "descr_ground_types.txt",
        "descr_landscape_cliffs.txt",
        "descr_landscape_ground_types.txt",
        "descr_landscape_rivers.txt",
        "descr_landscape_tile_placement.txt",
        "descr_landscape_tiles.txt",
        "descr_model_battle_template.txt",
        "descr_model_strat_template.txt",
        "descr_new_vegetation.txt",
        "descr_pallete.txt",
        "descr_projectile.txt",
        "descr_simple_skeleton.txt",
        "descr_skeleton.txt",
        "descr_skydome.txt",
        "descr_sm_forts_ports_watchtowers.txt",
        "descr_sm_settlements.txt",
        "descr_surface_fire.txt",
        "descr_water_interaction_effects.txt",
        "descr_water_interaction_effects_new.txt",
        "descr_water_types.txt",
        "descr_world_map.txt",
        "lookup_battle_descriptions.txt",
        "lookup_campaign_descriptions.txt",
        "menu_text_descr.txt",
        "export_descr_vnvs_enums.txt"
    )

    override fun update(e: AnActionEvent) {
        // Set the availability based on whether a project is open
        val project = e.project
        e.presentation.isEnabledAndVisible = project != null
    }

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project
        for (file in filesToDelete) {
            try {
                Files.deleteIfExists(Paths.get(project!!.basePath!!, file))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
