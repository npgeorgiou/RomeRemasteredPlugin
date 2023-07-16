package rr.language.colors

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import rr.language.RRIcons
import javax.swing.Icon

class RRColorSettingsPage : ColorSettingsPage {
    override fun getIcon(): Icon {
        return RRIcons.FILE
    }

    override fun getHighlighter(): SyntaxHighlighter {
        return RRSyntaxHighlighter()
    }

    override fun getDemoText(): String {
        return """;DESCR_STRAT_MARKER DO NOT REMOVE
;my campaign script
campaign        sucessor_wars
playable
    seleucid
    ptolemies
end
unlockable
end
nonplayable
end

start_date  -272 summer
end_date    599 summer

brigand_spawn_value 50
pirate_spawn_value  95

; resources
resource slaves, 1, 189, 250
resource grain,  2, 183, 253
resource furs,   3, 142, 248

;factions
faction seleucid, balanced_stalin
denari 3000

settlement
{
    level city
    region Syria

    year_founded 0
    population 7370
    settlement_tax 51
    plan_set default_set
    faction_creator romans_julii
    building
    {
        type core_building governors_palace
    }
    building
    {
        type defenses stone_wall
    }
}
settlement
{
    level town
    region Dayuan

    year_founded 0
    population 1280
    settlement_tax 51
    plan_set default_set
    faction_creator pontus
    building
    {
        type core_building governors_house
    }
    building
    {
        type market trader
    }
}

; City: Mazaka
; Region: Cappadocia
character    Sarpedon Syriakos, named character, command 0, influence 0, management 0, subterfuge 0, age 17, , x 224, y 156
traits NaturalIntelligence 5 , NaturalCharisma 5 , NaturalEnergy 4 , Temperament 4 , Selflessness 2 , Loyalty 2 , TurnsAlive 1 , SeleukidHeterogenes 1 , SmoothTalker 1 , RhetoricSkill 1 , GetRidOfSenate 1 , YearsPassed 4 , FamilyMember 1
ancillaries family_retainer
army
unit        hellenistic cavalry generals bodyguard 2    exp 1 armour 0 weapon_lvl 0
unit        hellenistic infantry pantodapoi             exp 0 armour 0 weapon_lvl 0
unit        hellenistic infantry pantodapoi             exp 0 armour 0 weapon_lvl 0
unit        greek skirmisher peltastai                  exp 0 armour 0 weapon_lvl 0

; City: Ekbatana
; Region: Media_Magna
character    Theodoros Syriakos, named character, heir, command 0, influence 0, management 0, subterfuge 0, age 19, , x 285, y 149
traits TheodorosSyriakosBiography 1 , NaturalIntelligence 5 , NaturalCharisma 4 , NaturalEnergy 4 , Temperament 4 , Selflessness 4 , Loyalty 4 , TurnsAlive 2 , SeleukidHeterogenes 1 , Pious 1 , Drink 1 , GetRidOfSenate 1 , YearsPassed 4 , FirstBorn 1 , FamilyMember 1
ancillaries family_retainer
army
unit        hellenistic cavalry generals bodyguard 2    exp 0 armour 0 weapon_lvl 0
unit        hellenistic infantry pantodapoi             exp 0 armour 0 weapon_lvl 0
unit        hellenistic infantry pantodapoi             exp 0 armour 0 weapon_lvl 0
unit        eastern missile thanvare payahdag           exp 0 armour 0 weapon_lvl 0

; City: Marakanda
; Region: Sogdiana
character    Molon, general, command 0, influence 0, management 0, subterfuge 0, age 20, , x 354, y 199
army
unit        steppe missile cavalry daha baexdzhyntae    exp 0 armour 0 weapon_lvl 0
unit        eastern missile eransahr arshtbara          exp 0 armour 0 weapon_lvl 0

; Region: Sea
character    Leon Pergamoumysiakes, admiral, command 0, influence 0, management 0, subterfuge 0, age 20, , x 227, y 142
army
unit        generic ship kerkuroi    exp 0 armour 0 weapon_lvl 0

; Region: Syria
character    Keraias Pergamoumysiakes, diplomat, command 0, influence 0, management 0, subterfuge 0, age 20, , x 232, y 144
traits GoodDiplomat 3 , DiplomatTraining 2

; Region: Sittacene
character    Iason Pergamoumysiakes, spy, command 0, influence 0, management 0, subterfuge 0, age 20, , x 273, y 140
traits GoodSpy 2 , SpyTurnsAlive 1 , AgentTraining 2

character_record    Apame,                  female, command 0, influence 0, management 0, subterfuge 0, age 80, alive, never_a_leader
character_record    Asia,                   female, command 0, influence 0, management 0, subterfuge 0, age 58, alive, never_a_leader
character_record    Stratonike,             female, command 0, influence 0, management 0, subterfuge 0, age 43, alive, never_a_leader
character_record    Antiochis Syriakos,     female, command 0, influence 0, management 0, subterfuge 0, age 38, alive, never_a_leader

relative     Sarpedon Syriakos,    Apame,    Stratonike,    end
relative     Theodoros Syriakos,    Asia,    Antiochis Syriakos,    end

faction    ptolemies, religious_caesar
denari 5000

settlement
{
    level large_city
    region Delta_Neilou

    year_founded 0
    population 12045
    settlement_tax 51
    plan_set default_set
    faction_creator numidia
    building
    {
        type core_building proconsuls_palace
    }
}

; City: Alexandreia
; Region: Delta_Occidentalis
character    Soter Ptolemaios, named character, leader, command 0, influence 0, management 0, subterfuge 0, age 97, , x 211, y 108
traits NaturalIntelligence 4 , NaturalCharisma 2 , NaturalEnergy 5 , Temperament 4 , Selflessness 5 , Loyalty 5 , TurnsAlive 8 , PtolemaioiMakedon 1 , GoodTactician 1 , PublicFaith 2 , MathematicsSkill 2 , YearsPassed 4 , NumAncillariesAcquired 3 , FamilyMember 1
ancillaries librarian , master_embalmer, spear_carrier
army
unit        hellenistic cavalry generals bodyguard 3    exp 1 armour 0 weapon_lvl 0
unit        hellenistic cavalry machimoi hippeis        exp 1 armour 0 weapon_lvl 0
unit        hellenistic infantry machimoi phalangitai   exp 0 armour 0 weapon_lvl 0
unit        hellenistic infantry pantodapoi             exp 0 armour 0 weapon_lvl 0
unit        greek skirmisher peltastai                  exp 0 armour 0 weapon_lvl 0
unit        greek skirmisher akontistai                 exp 0 armour 0 weapon_lvl 0

; Region: Judea
character    Ploutos Salaminios, spy, command 0, influence 0, management 0, subterfuge 0, age 20, , x 234, y 120
traits GoodSpy 3 , SpyTurnsAlive 1 , AgentTraining 2
ancillaries poisoner

character_record    Lamia,               female, command 0, influence 0, management 0, subterfuge 0, age 90, alive, never_a_leader
character_record    Eirene Ptolemaios,   female, command 0, influence 0, management 0, subterfuge 0, age 73, alive, never_a_leader

relative     Soter Ptolemaios, Lamia, Eirene Ptolemaios, Eirene Ptolemaios, end

core_attitudes    seleucid,    600    ptolemies
core_attitudes    ptolemies,   450    seleucid
faction_relationships     seleucid,  at_war_with    ptolemies
faction_relationships     ptolemies, at_war_with    seleucid

script
empty_script.txt
"""
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? {
        return null
    }

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
        return DESCRIPTORS
    }

    override fun getColorDescriptors(): Array<ColorDescriptor> {
        return ColorDescriptor.EMPTY_ARRAY
    }

    override fun getDisplayName(): String {
        return "Rome Remastered"
    }

    companion object {
        private val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Markers", RRSyntaxHighlighter.MARKER_STYLE),
            AttributesDescriptor("Comments", RRSyntaxHighlighter.COMMENT_STYLE),
            AttributesDescriptor("Punctuation", RRSyntaxHighlighter.PUNCTUATION_STYLE),
            AttributesDescriptor("Operators", RRSyntaxHighlighter.OPERATORS_STYLE),
            AttributesDescriptor("Keywords", RRSyntaxHighlighter.KEYWORDS_STYLE),
            AttributesDescriptor("Constants", RRSyntaxHighlighter.CONSTANTS_STYLE),
            AttributesDescriptor("Identifiers", RRSyntaxHighlighter.ID_STYLE),
            AttributesDescriptor("Coordinates", RRSyntaxHighlighter.COORDS_STYLE),
            AttributesDescriptor("Numbers", RRSyntaxHighlighter.NUMBER_STYLE),
            AttributesDescriptor("Booleans", RRSyntaxHighlighter.BOOLEAN_STYLE),
            AttributesDescriptor("Strings", RRSyntaxHighlighter.STRING_STYLE),
            AttributesDescriptor("Files", RRSyntaxHighlighter.FILES_STYLE),
            AttributesDescriptor("Events", RRSyntaxHighlighter.EVENTS_STYLE),
            AttributesDescriptor("Conditions", RRSyntaxHighlighter.CONDITIONS_STYLE),
            AttributesDescriptor("Commands", RRSyntaxHighlighter.COMMANDS_STYLE),
            AttributesDescriptor("Custom script helpers", RRSyntaxHighlighter.CUSTOM_SCRIPT_HELPERS_STYLE)
        )
    }
}
