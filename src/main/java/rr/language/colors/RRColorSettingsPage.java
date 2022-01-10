package rr.language.colors;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRIcons;

import javax.swing.*;
import java.util.Map;

public class RRColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Comments", RRSyntaxHighlighter.COMMENT_STYLE),
            new AttributesDescriptor("Punctuation", RRSyntaxHighlighter.PUNCTUATION_STYLE),
            new AttributesDescriptor("Keywords", RRSyntaxHighlighter.KEYWORDS_STYLE),
            new AttributesDescriptor("Constants", RRSyntaxHighlighter.CONSTANTS_STYLE),
            new AttributesDescriptor("Identifiers", RRSyntaxHighlighter.ID_STYLE),
            new AttributesDescriptor("Coordinates", RRSyntaxHighlighter.COORDS_STYLE),
            new AttributesDescriptor("Numbers", RRSyntaxHighlighter.NUMBER_STYLE),
            new AttributesDescriptor("Booleans", RRSyntaxHighlighter.BOOLEAN_STYLE),
            new AttributesDescriptor("Strings", RRSyntaxHighlighter.STRING_STYLE),
            new AttributesDescriptor("Files", RRSyntaxHighlighter.FILES_STYLE),
            new AttributesDescriptor("Events", RRSyntaxHighlighter.EVENTS_STYLE),
            new AttributesDescriptor("Conditions", RRSyntaxHighlighter.CONDITIONS_STYLE),
            new AttributesDescriptor("Commands", RRSyntaxHighlighter.COMMANDS_STYLE),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return RRIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new RRSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return ";a faction definition\n" +
                "faction\tromans_senate, comfortable_napoleon\n" +
                "denari\t5000\n" +
                "settlement\n" +
                "{\n" +
                "\tlevel city\n" +
                "\tregion Latium\n" +
                "\n" +
                "\tyear_founded 0\n" +
                "\tpopulation 7500\n" +
                "\tplan_set default_set\n" +
                "\tfaction_creator romans_julii\n" +
                "\tbuilding\n" +
                "\t{\n" +
                "\t\ttype core_building governors_palace\n" +
                "\t}\n" +
                "\tbuilding\n" +
                "\t{\n" +
                "\t\ttype defenses stone_wall\n" +
                "\t}\n" +
                "\n" +
                "character\tDecius Maxentius, named character, leader, age 60, , x 95, y 71\n" +
                "traits GoodCommander 1 , PoliticsSkill 2 , GoodAdministrator 2 , Austere 1\n" +
                "ancillaries doctor\n" +
                "army\n" +
                "unit\t\troman generals guard cavalry early\t\t\t\texp 0 armour 0 weapon_lvl 0\n" +
                "unit\t\troman hastati\t\t\t\texp 3 armour 1 weapon_lvl 0\n" +
                "unit\t\troman triarii\t\t\t\texp 3 armour 1 weapon_lvl 0" +
                "\n" +
                ";a unit definition\n" +
                "type             warband axe scythian\n" +
                "dictionary       warband_axe_scythian      ; Axemen\n" +
                "category         infantry\n" +
                "class            heavy\n" +
                "voice_type       Light_1\n" +
                "voice_indexes    0 1 2\n" +
                "soldier          warband_axe_scythia, 40, 0, 1.2\n" +
                "officer          barb_standard\n" +
                "attributes       sea_faring, hide_improved_forest, warcry\n" +
                "formation        1.2, 1.2, 2.4, 2.4, 4, square\n" +
                "stat_health      1, 0\n" +
                "stat_pri         11, 5, no, 0, 0, melee, blade, piercing, axe, 25 ,1\n" +
                "stat_pri_attr    no\n" +
                "stat_sec         0, 0, no, 0, 0, no, no, no, none, 25 ,1\n" +
                "stat_sec_attr    no\n" +
                "stat_pri_armour  3, 4, 2, leather\n" +
                "stat_sec_armour  0, 0, flesh\n" +
                "stat_heat        2\n" +
                "stat_ground      2, -2, 3, 2\n" +
                "stat_mental      8, impetuous, untrained\n" +
                "stat_charge_dist 40\n" +
                "stat_fire_delay  0\n" +
                "stat_food        60, 300\n" +
                "stat_cost        1, 450, 170, 50, 70, 450\n" +
                "ownership        scythia\n" +
                "ethnicity scythia, Scythia" +
                "\n" +
                ";a building definition\n" +
                "building market\n" +
                "{\n" +
                "\ticon trade\n" +
                "\tlevels trader market forum great_forum curia\n" +
                "\t{\n" +
                "\t\ttrader requires factions { barbarian, carthaginian, eastern, parthia, egyptian, greek, roman, }\n" +
                "\t\t{\n" +
                "\t\t\tcapability\n" +
                "\t\t\t{\n" +
                "\t\t\t\ttrade_base_income_bonus bonus 1\n" +
                "\t\t\t\tpopulation_growth_bonus bonus 1\n" +
                "\t\t\t}\n" +
                "\t\t\tconstruction  2\n" +
                "\t\t\tcost  600\n" +
                "\t\t\tsettlement_min town\n" +
                "\t\t\tupgrades\n" +
                "\t\t\t{\n" +
                "\t\t\t\tmarket\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t\tmarket requires factions { barbarian, carthaginian, eastern, parthia, egyptian, greek, roman, }\n" +
                "\t\t{\n" +
                "\t\t\tcapability\n" +
                "\t\t\t{\n" +
                "\t\t\t\tagent spy  0\n" +
                "\t\t\t\tagent merchant  0  requires is_toggled \"merchants\"\n" +
                "\t\t\t\ttrade_base_income_bonus bonus 2\n" +
                "\t\t\t\tpopulation_growth_bonus bonus 1\n" +
                "\t\t\t\tagent_limit_settlement merchant 1\n" +
                "\t\t\t}\n" +
                "\t\t\tconstruction  3\n" +
                "\t\t\tcost  1200\n" +
                "\t\t\tsettlement_min large_town\n" +
                "\t\t\tupgrades\n" +
                "\t\t\t{\n" +
                "\t\t\t\tforum\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\tplugins\n" +
                "\t{\n" +
                "\t}\n" +
                "}";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Rome Remastered";
    }

}
