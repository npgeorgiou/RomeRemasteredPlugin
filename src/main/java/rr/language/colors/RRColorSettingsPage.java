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
            new AttributesDescriptor("Markers", RRSyntaxHighlighter.MARKER_STYLE),
            new AttributesDescriptor("Comments", RRSyntaxHighlighter.COMMENT_STYLE),
            new AttributesDescriptor("Punctuation", RRSyntaxHighlighter.PUNCTUATION_STYLE),
            new AttributesDescriptor("Operators", RRSyntaxHighlighter.OPERATORS_STYLE),
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
            new AttributesDescriptor("Custom script helpers", RRSyntaxHighlighter.CUSTOM_SCRIPT_HELPERS_STYLE),
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
        return ";DESCR_STRAT_MARKER DO NOT REMOVE\n" +
            ";my campaign script\n" +
            "campaign        sucessor_wars\n" +
            "playable\n" +
            "    seleucid\n" +
            "    ptolemies\n" +
            "end\n" +
            "unlockable\n" +
            "end\n" +
            "nonplayable\n" +
            "end\n" +
            "\n" +
            "start_date  -272 summer\n" +
            "end_date    599 summer\n" +
            "\n" +
            "brigand_spawn_value 50\n" +
            "pirate_spawn_value  95\n" +
            "\n" +
            "; resources\n" +
            "resource slaves, 1, 189, 250\n" +
            "resource grain,  2, 183, 253\n" +
            "resource furs,   3, 142, 248\n" +
            "\n" +
            ";factions\n" +
            "faction seleucid, balanced_stalin\n" +
            "denari 3000\n" +
            "\n" +
            "settlement\n" +
            "{\n" +
            "    level city\n" +
            "    region Syria\n" +
            "\n" +
            "    year_founded 0\n" +
            "    population 7370\n" +
            "    settlement_tax 51\n" +
            "    plan_set default_set\n" +
            "    faction_creator romans_julii\n" +
            "    building\n" +
            "    {\n" +
            "        type core_building governors_palace\n" +
            "    }\n" +
            "    building\n" +
            "    {\n" +
            "        type defenses stone_wall\n" +
            "    }\n" +
            "}\n" +
            "settlement\n" +
            "{\n" +
            "    level town\n" +
            "    region Dayuan\n" +
            "\n" +
            "    year_founded 0\n" +
            "    population 1280\n" +
            "    settlement_tax 51\n" +
            "    plan_set default_set\n" +
            "    faction_creator pontus\n" +
            "    building\n" +
            "    {\n" +
            "        type core_building governors_house\n" +
            "    }\n" +
            "    building\n" +
            "    {\n" +
            "        type market trader\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "; City: Mazaka\n" +
            "; Region: Cappadocia\n" +
            "character    Sarpedon Syriakos, named character, command 0, influence 0, management 0, subterfuge 0, age 17, , x 224, y 156\n" +
            "traits NaturalIntelligence 5 , NaturalCharisma 5 , NaturalEnergy 4 , Temperament 4 , Selflessness 2 , Loyalty 2 , TurnsAlive 1 , SeleukidHeterogenes 1 , SmoothTalker 1 , RhetoricSkill 1 , GetRidOfSenate 1 , YearsPassed 4 , FamilyMember 1\n" +
            "ancillaries family_retainer\n" +
            "army\n" +
            "unit        hellenistic cavalry generals bodyguard 2    exp 1 armour 0 weapon_lvl 0\n" +
            "unit        hellenistic infantry pantodapoi             exp 0 armour 0 weapon_lvl 0\n" +
            "unit        hellenistic infantry pantodapoi             exp 0 armour 0 weapon_lvl 0\n" +
            "unit        greek skirmisher peltastai                  exp 0 armour 0 weapon_lvl 0\n" +
            "\n" +
            "; City: Ekbatana\n" +
            "; Region: Media_Magna\n" +
            "character    Theodoros Syriakos, named character, heir, command 0, influence 0, management 0, subterfuge 0, age 19, , x 285, y 149\n" +
            "traits TheodorosSyriakosBiography 1 , NaturalIntelligence 5 , NaturalCharisma 4 , NaturalEnergy 4 , Temperament 4 , Selflessness 4 , Loyalty 4 , TurnsAlive 2 , SeleukidHeterogenes 1 , Pious 1 , Drink 1 , GetRidOfSenate 1 , YearsPassed 4 , FirstBorn 1 , FamilyMember 1\n" +
            "ancillaries family_retainer\n" +
            "army\n" +
            "unit        hellenistic cavalry generals bodyguard 2    exp 0 armour 0 weapon_lvl 0\n" +
            "unit        hellenistic infantry pantodapoi             exp 0 armour 0 weapon_lvl 0\n" +
            "unit        hellenistic infantry pantodapoi             exp 0 armour 0 weapon_lvl 0\n" +
            "unit        eastern missile thanvare payahdag           exp 0 armour 0 weapon_lvl 0\n" +
            "\n" +
            "; City: Marakanda\n" +
            "; Region: Sogdiana\n" +
            "character    Molon, general, command 0, influence 0, management 0, subterfuge 0, age 20, , x 354, y 199\n" +
            "army\n" +
            "unit        steppe missile cavalry daha baexdzhyntae    exp 0 armour 0 weapon_lvl 0\n" +
            "unit        eastern missile eransahr arshtbara          exp 0 armour 0 weapon_lvl 0\n" +
            "\n" +
            "; Region: Sea\n" +
            "character    Leon Pergamoumysiakes, admiral, command 0, influence 0, management 0, subterfuge 0, age 20, , x 227, y 142\n" +
            "army\n" +
            "unit        generic ship kerkuroi    exp 0 armour 0 weapon_lvl 0\n" +
            "\n" +
            "; Region: Syria\n" +
            "character    Keraias Pergamoumysiakes, diplomat, command 0, influence 0, management 0, subterfuge 0, age 20, , x 232, y 144\n" +
            "traits GoodDiplomat 3 , DiplomatTraining 2\n" +
            "\n" +
            "; Region: Sittacene\n" +
            "character    Iason Pergamoumysiakes, spy, command 0, influence 0, management 0, subterfuge 0, age 20, , x 273, y 140\n" +
            "traits GoodSpy 2 , SpyTurnsAlive 1 , AgentTraining 2\n" +
            "\n" +
            "character_record    Apame,                  female, command 0, influence 0, management 0, subterfuge 0, age 80, alive, never_a_leader\n" +
            "character_record    Asia,                   female, command 0, influence 0, management 0, subterfuge 0, age 58, alive, never_a_leader\n" +
            "character_record    Stratonike,             female, command 0, influence 0, management 0, subterfuge 0, age 43, alive, never_a_leader\n" +
            "character_record    Antiochis Syriakos,     female, command 0, influence 0, management 0, subterfuge 0, age 38, alive, never_a_leader\n" +
            "\n" +
            "relative     Sarpedon Syriakos,    Apame,    Stratonike,    end\n" +
            "relative     Theodoros Syriakos,    Asia,    Antiochis Syriakos,    end\n" +
            "\n" +
            "faction    ptolemies, religious_caesar\n" +
            "denari 5000\n" +
            "\n" +
            "settlement\n" +
            "{\n" +
            "    level large_city\n" +
            "    region Delta_Neilou\n" +
            "\n" +
            "    year_founded 0\n" +
            "    population 12045\n" +
            "    settlement_tax 51\n" +
            "    plan_set default_set\n" +
            "    faction_creator numidia\n" +
            "    building\n" +
            "    {\n" +
            "        type core_building proconsuls_palace\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "; City: Alexandreia\n" +
            "; Region: Delta_Occidentalis\n" +
            "character    Soter Ptolemaios, named character, leader, command 0, influence 0, management 0, subterfuge 0, age 97, , x 211, y 108\n" +
            "traits NaturalIntelligence 4 , NaturalCharisma 2 , NaturalEnergy 5 , Temperament 4 , Selflessness 5 , Loyalty 5 , TurnsAlive 8 , PtolemaioiMakedon 1 , GoodTactician 1 , PublicFaith 2 , MathematicsSkill 2 , YearsPassed 4 , NumAncillariesAcquired 3 , FamilyMember 1\n" +
            "ancillaries librarian , master_embalmer, spear_carrier\n" +
            "army\n" +
            "unit        hellenistic cavalry generals bodyguard 3    exp 1 armour 0 weapon_lvl 0\n" +
            "unit        hellenistic cavalry machimoi hippeis        exp 1 armour 0 weapon_lvl 0\n" +
            "unit        hellenistic infantry machimoi phalangitai   exp 0 armour 0 weapon_lvl 0\n" +
            "unit        hellenistic infantry pantodapoi             exp 0 armour 0 weapon_lvl 0\n" +
            "unit        greek skirmisher peltastai                  exp 0 armour 0 weapon_lvl 0\n" +
            "unit        greek skirmisher akontistai                 exp 0 armour 0 weapon_lvl 0\n" +
            "\n" +
            "; Region: Judea\n" +
            "character    Ploutos Salaminios, spy, command 0, influence 0, management 0, subterfuge 0, age 20, , x 234, y 120\n" +
            "traits GoodSpy 3 , SpyTurnsAlive 1 , AgentTraining 2\n" +
            "ancillaries poisoner\n" +
            "\n" +
            "character_record    Lamia,               female, command 0, influence 0, management 0, subterfuge 0, age 90, alive, never_a_leader\n" +
            "character_record    Eirene Ptolemaios,   female, command 0, influence 0, management 0, subterfuge 0, age 73, alive, never_a_leader\n" +
            "\n" +
            "relative     Soter Ptolemaios, Lamia, Eirene Ptolemaios, Eirene Ptolemaios, end\n" +
            "\n" +
            "core_attitudes    seleucid,    600    ptolemies\n" +
            "core_attitudes    ptolemies,   450    seleucid\n" +
            "faction_relationships     seleucid,  at_war_with    ptolemies\n" +
            "faction_relationships     ptolemies, at_war_with    seleucid\n" +
            "\n" +
            "script\n" +
            "empty_script.txt\n";
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
