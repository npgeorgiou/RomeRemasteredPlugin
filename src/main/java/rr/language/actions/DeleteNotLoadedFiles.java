package rr.language.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.psi.RRBuildingLevel;
import rr.language.psi.RRBuildingTree;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteNotLoadedFiles extends AnAction {

    String[] filesToDelete = new String[]{
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
        "menu_text_descr.txt"
    };

    @Override
    public void update(AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();

        for (String file : filesToDelete) {
            try {
                Files.deleteIfExists(Paths.get(project.getBasePath(), file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
