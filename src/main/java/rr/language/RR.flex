package rr.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import rr.language.psi.RRTypes;
import com.intellij.psi.TokenType;

%%

%class RRLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

EOL  = [\r\n]
LINE_WS = [ \t]

EOL_WS = {EOL}+{LINE_WS}+
WS      = ({EOL}|{LINE_WS})+

COMMENT = [";""¬"][^\r\n]*

// Special things
SPEAR_BONUS_X = "spear_bonus_" {INT}
LOCAL = \"local\"|"local"

INT = [\+\-]?[0-9]+
FLOAT = [\+\-]?[0-9]*\.[0-9]+
STR_CHAR = [^\"\r\n\\]
STRING = \" {STR_CHAR}* \"

DIR_OR_FILE=[\w\d_#&-]+
PATH = {DIR_OR_FILE}([/\\]{DIR_OR_FILE})+
TXT_FILE=({PATH}|{DIR_OR_FILE})\.(txt|TXT)
TGA_FILE=({PATH}|{DIR_OR_FILE})\.(tga|TGA)
CAS_FILE=({PATH}|{DIR_OR_FILE})\.(cas|CAS)
RTM_FILE=({PATH}|{DIR_OR_FILE})\.(rtm|RTM)
WMV_FILE=({PATH}|{DIR_OR_FILE})\.(wmv|WMV)

// †ÎöÈ.íëé are in descr_names. Maybe just typos.
ID = ([:jletterdigit:])+ (\+|\'|\-|\!|\?|\†|\Î|\ö|\È|\.|\í|\ë|\é|[:jletterdigit:])* ([:jletterdigit:])*

%state DESCR_STRAT
%xstate DESCR_STRAT_NO_KEYWORDS

%state EXPORT_DESCR_BUILDINGS
%xstate EXPORT_DESCR_BUILDINGS_NO_KEYWORDS

%state EXPORT_DESCR_UNIT
%xstate EXPORT_DESCR_UNIT_NO_KEYWORDS

%state EXPORT_DESCR_ANCILLARIES
%xstate EXPORT_DESCR_ANCILLARIES_NO_KEYWORDS

%state EXPORT_DESCR_MERCENARIES
%xstate EXPORT_DESCR_MERCENARIES_NO_KEYWORDS

%xstate TEXT_MAPPING
%xstate TEXT_MAPPING_NO_KEYWORDS

%state DESCR_CHARACTER
%xstate DESCR_CHARACTER_NO_KEYWORDS

%state DESCR_MODEL_BATTLE_AND_STRAT
%xstate DESCR_MODEL_BATTLE_AND_STRAT_NO_KEYWORDS

%xstate DESCR_REBEL_FACTIONS
%xstate DESCR_REBEL_FACTIONS_REBEL_TYPE

%state DESCR_MOUNT
%xstate DESCR_MOUNT_NO_KEYWORDS

%state EXPORT_DESCR_TRAITS
%state DESCR_NAMES
%state DESCR_UNIT_VARIATION
%xstate FERAL_DESCR_AI_PERSONALITY
%state DESCR_FACTION_GROUPS
%state FERAL_DESCR_PORTRAITS_VARIATION
%state DESCR_BANNERS
%state DESCR_BUILDING_BATTLE
%state DESCR_LBC_DB
%state DESCR_OFFMAP_MODELS
%state DESCR_SM_LANDMARKS
%state DESCR_DISASTERS
%state DESCR_ITEMS
%state TEXT_MAPPING
%xstate ENUMS

%state SCRIPTS_EVENTS_CONDITIONS
//%state CONDITIONS
//%state EVENTS

%%
<DESCR_NAMES>{EOL_WS} {return RRTypes.EOL;}

";descr_strat.txt"[^\r\n]*                     {yybegin(DESCR_STRAT); return RRTypes.DESCR_STRAT_MARKER;}
";descr_cultures.txt"[^\r\n]*                  {return RRTypes.DESCR_CULTURES_MARKER;}
";descr_sm_factions.txt"[^\r\n]*               {return RRTypes.DESCR_SM_FACTIONS_MARKER;}
";feral_descr_ai_personality.txt"[^\r\n]*      {yybegin(FERAL_DESCR_AI_PERSONALITY); return RRTypes.FERAL_DESCR_AI_PERSONALITY_MARKER;}
";descr_faction_groups.txt"[^\r\n]*            {yybegin(DESCR_FACTION_GROUPS); return RRTypes.DESCR_FACTION_GROUPS_MARKER;}
";descr_sm_resources.txt"[^\r\n]*              {return RRTypes.DESCR_SM_RESOURCES_MARKER;}
";feral_descr_portraits_variation.txt"[^\r\n]* {yybegin(FERAL_DESCR_PORTRAITS_VARIATION); return RRTypes.FERAL_DESCR_PORTRAITS_VARIATION_MARKER;}
";descr_banners.txt"[^\r\n]*                   {yybegin(DESCR_BANNERS); return RRTypes.DESCR_BANNERS_MARKER;}
";descr_character.txt"[^\r\n]*                 {yybegin(DESCR_CHARACTER); return RRTypes.DESCR_CHARACTER_MARKER;}
";descr_building_battle.txt"[^\r\n]*           {yybegin(DESCR_BUILDING_BATTLE); return RRTypes.DESCR_BUILDING_BATTLE_MARKER;}
";descr_lbc_db.txt"[^\r\n]*                    {yybegin(DESCR_LBC_DB); return RRTypes.DESCR_LBC_DB_MARKER;}
";descr_offmap_models.txt"[^\r\n]*             {yybegin(DESCR_OFFMAP_MODELS); return RRTypes.DESCR_OFFMAP_MODELS_MARKER;}
";descr_sm_landmarks.txt"[^\r\n]*              {yybegin(DESCR_SM_LANDMARKS); return RRTypes.DESCR_SM_LANDMARKS_MARKER;}
";descr_model_battle.txt"[^\r\n]*              {yybegin(DESCR_MODEL_BATTLE_AND_STRAT); return RRTypes.DESCR_MODEL_BATTLE_MARKER;}
";descr_model_strat.txt"[^\r\n]*               {yybegin(DESCR_MODEL_BATTLE_AND_STRAT); return RRTypes.DESCR_MODEL_STRAT_MARKER;}
";descr_disasters.txt"[^\r\n]*                 {yybegin(DESCR_DISASTERS); return RRTypes.DESCR_DISASTERS_MARKER;}
";descr_mount.txt"[^\r\n]*                     {yybegin(DESCR_MOUNT); return RRTypes.DESCR_MOUNT_MARKER;}
";descr_rebel_factions.txt"[^\r\n]*            {yybegin(DESCR_REBEL_FACTIONS); return RRTypes.DESCR_REBEL_FACTIONS_MARKER;}
";descr_items.txt"[^\r\n]*                     {yybegin(DESCR_ITEMS); return RRTypes.DESCR_ITEMS_MARKER;}
";descr_sm_ambient_objects.txt"[^\r\n]*        {return RRTypes.DESCR_SM_AMBIENT_OBJECTS_MARKER;}
";descr_beliefs.txt"[^\r\n]*                   {return RRTypes.DESCR_BELIEFS_MARKER;}

// text mapping markers
";export_buildings.txt"[^\r\n]*                {yybegin(TEXT_MAPPING); return RRTypes.EXPORT_BUILDINGS_MARKER;}
";landmarks.txt"[^\r\n]*                       {yybegin(TEXT_MAPPING); return RRTypes.TEXT_MAPPING_MARKER;}
";names.txt"[^\r\n]*                           {yybegin(TEXT_MAPPING); return RRTypes.TEXT_MAPPING_MARKER;}
";rebel_faction_descr.txt"[^\r\n]*             {yybegin(TEXT_MAPPING); return RRTypes.TEXT_MAPPING_MARKER;}

// enum markers
";rebel_faction_descr_enums.txt"[^\r\n]*       {yybegin(ENUMS); return RRTypes.ENUMS_MARKER;}

// Special things
<SCRIPTS_EVENTS_CONDITIONS>{LOCAL}          {return RRTypes.LOCAL;}
{TGA_FILE}|\"{TGA_FILE}\" {return RRTypes.TGA_FILE;}
{TXT_FILE}|\"{TXT_FILE}\" {return RRTypes.TXT_FILE;}
{CAS_FILE}|\"{CAS_FILE}\" {return RRTypes.CAS_FILE;}
{RTM_FILE}|\"{RTM_FILE}\" {return RRTypes.RTM_FILE;}
{WMV_FILE}|\"{WMV_FILE}\" {return RRTypes.WMV_FILE;}

{WS}             {return TokenType.WHITE_SPACE;}
{COMMENT}        {return RRTypes.COMMENT;}
{INT}            {return RRTypes.INT;}
{FLOAT}          {return RRTypes.FLOAT;}
{STRING}         {return RRTypes.STRING;}
true|false       {return RRTypes.BOOLEAN;}

","              {return RRTypes.COMMA;}
"{"              {return RRTypes.OCB;}
"}"              {return RRTypes.CCB;}
"["              {return RRTypes.OSB;}
"]"              {return RRTypes.CSB;}
":"              {return RRTypes.COLON;}
"("              {return RRTypes.OP;}
")"              {return RRTypes.CP;}


{PATH}           {return RRTypes.PATH;}

"not"                           {return RRTypes.NOT;}
"!"                             {return RRTypes.EXCLAMATION;}
"and"                           {return RRTypes.AND;}
"&&"                            {return RRTypes.AMBERSANDS;}
"or"                            {return RRTypes.OR;}
"||"                            {return RRTypes.PIPES;}

// character_type
"all"                          {return RRTypes.ALL;}
"family"                       {return RRTypes.FAMILY;}
"named character"              {return RRTypes.NAMED_CHARACTER;}
"general"                      {return RRTypes.GENERAL;}
"admiral"                      {return RRTypes.ADMIRAL;}
"diplomat"                     {return RRTypes.DIPLOMAT;}
"spy"                          {return RRTypes.SPY;}
"assassin"                     {return RRTypes.ASSASSIN;}
"merchant"                     {return RRTypes.MERCHANT;}
// unit class
"light"                        {return RRTypes.LIGHT;}
"heavy"                        {return RRTypes.HEAVY;}
"skirmish"                     {return RRTypes.SKIRMISH;}
"missile"                      {return RRTypes.MISSILE;}
"spearmen"                     {return RRTypes.SPEARMEN;}
// unit category
"infantry"                     {return RRTypes.INFANTRY;}
"cavalry"                      {return RRTypes.CAVALRY;}
"siege"                        {return RRTypes.SIEGE;}
"handler"                      {return RRTypes.HANDLER;}
"ship"                         {return RRTypes.SHIP;}
"non_combatant"                {return RRTypes.NON_COMBATANT;}
// hide_type
"hide_forest"                  {return RRTypes.HIDE_FOREST;}
"hide_improved_forest"         {return RRTypes.HIDE_IMPROVED_FOREST;}
"hide_long_grass"              {return RRTypes.HIDE_LONG_GRASS;}
"hide_anywhere"                {return RRTypes.HIDE_ANYWHERE;}
// formations
"square"                       {return RRTypes.SQUARE;}
"square_hollow"                {return RRTypes.SQUARE_HOLLOW;}
"horde"                        {return RRTypes.HORDE;}
"column"                       {return RRTypes.COLUMN;}
"phalanx"                      {return RRTypes.PHALANX;}
"testudo"                      {return RRTypes.TESTUDO;}
"wedge"                        {return RRTypes.WEDGE;}
"shield_wall"                  {return RRTypes.SHIELD_WALL;}
"schiltrom"                    {return RRTypes.SCHILTROM;}
// mount type
"horse"                        {return RRTypes.HORSE;}
"camel"                        {return RRTypes.CAMEL;}
"elephant"                     {return RRTypes.ELEPHANT;}
"chariot"                      {return RRTypes.CHARIOT;}
"scorpion_cart"                {return RRTypes.SCORPION_CART;}
// settlement level
"village"                      {return RRTypes.VILLAGE;}
"town"                         {return RRTypes.TOWN;}
"large_town"                   {return RRTypes.LARGE_TOWN;}
"city"                         {return RRTypes.CITY;}
"large_city"                   {return RRTypes.LARGE_CITY;}
"huge_city"                    {return RRTypes.HUGE_CITY;}



<YYINITIAL>
{
    "type"                      {yybegin(EXPORT_DESCR_UNIT_NO_KEYWORDS); return RRTypes.TYPE;}
    "tags"                      {yybegin(EXPORT_DESCR_BUILDINGS); return RRTypes.TAGS;}
    "Ancillary"                 {yybegin(EXPORT_DESCR_ANCILLARIES); return RRTypes.ANCILLARY;}
    "Trait"                     {yybegin(EXPORT_DESCR_TRAITS); return RRTypes.TRAIT;}
    "pool"                      {yybegin(EXPORT_DESCR_MERCENARIES); return RRTypes.POOL;}
    "faction"                   {yybegin(DESCR_NAMES); return RRTypes.FACTION;}
    "skin"                      {yybegin(DESCR_UNIT_VARIATION); return RRTypes.SKIN;}
    "script"                    {yybegin(SCRIPTS_EVENTS_CONDITIONS); return RRTypes.SCRIPT;}

    // Here because descr_regions has no starting anchor on which I can start a sublexer.
    // Idea: Automatically add a comment on top of each file, with the file name, and use it as an ancor.
    "none"               {return RRTypes.NONE;}
    {ID}                 {return RRTypes.ID;}
}

// Unfortunately, RR allows free text instead of IDs in some places.
// This means that some what the lexer identifies as a keyword
// for example: "ancillaries druid", or "unit city militia exp 1 armour 0 weapon_lvl 0"
// Identified as keywords    ^^^^^            ^^^^
// Solution: Identify these places and start a sub-lexer.
<DESCR_STRAT>
{
    "end"                        {return RRTypes.END;}
    "summer"                     {return RRTypes.SUMMER;}
    "winter"                     {return RRTypes.WINTER;}
    "campaign"                   {return RRTypes.CAMPAIGN;}
    "playable"                   {return RRTypes.PLAYABLE;}
    "unlockable"                 {return RRTypes.UNLOCKABLE;}
    "nonplayable"                {return RRTypes.NONPLAYABLE;}
    "start_date"                 {return RRTypes.START_DATE;}
    "end_date"                   {return RRTypes.END_DATE;}
    "marian_reforms_disabled"    {return RRTypes.MARIAN_REFORMS_DISABLED;}
    "brigand_spawn_value"        {return RRTypes.BRIGAND_SPAWN_VALUE;}
    "pirate_spawn_value"         {return RRTypes.PIRATE_SPAWN_VALUE;}
    "landmark"                   {return RRTypes.LANDMARK;}
    "ambient_object"             {return RRTypes.AMBIENT_OBJECT;}
    "tag"                        {return RRTypes.TAG;}
    "index"                      {return RRTypes.INDEX;}
    "resource_quantity_enabled"  {return RRTypes.RESOURCE_QUANTITY_ENABLED;}
    "resource_quantity_disabled" {return RRTypes.RESOURCE_QUANTITY_DISABLED;}
    "resource"                   {return RRTypes.RESOURCE;}
    "faction"                    {return RRTypes.FACTION;}
    "superfaction"               {return RRTypes.SUPERFACTION;}
    "dead_until_resurrected"     {return RRTypes.DEAD_UNTIL_RESURRECTED;}
    "ai_do_not_attack"           {return RRTypes.AI_DO_NOT_ATTACK;}
    "ai_do_not_attack_faction"   {return RRTypes.AI_DO_NOT_ATTACK_FACTION;}
    "denari"                     {return RRTypes.DENARI;}
    "settlement"                 {return RRTypes.SETTLEMENT;}
    "level"                      {return RRTypes.LEVEL;}
    "region"                     {return RRTypes.REGION;}
    "year_founded"               {return RRTypes.YEAR_FOUNDED;}
    "population"                 {return RRTypes.POPULATION;}
    "settlement_tax"             {return RRTypes.SETTLEMENT_TAX;}
    "plan_set"                   {return RRTypes.PLAN_SET;}
    "faction_creator"            {return RRTypes.FACTION_CREATOR;}
    "default_set"                {return RRTypes.DEFAULT_SET;}
    "building"                   {return RRTypes.BUILDING;}
    "type"                       {return RRTypes.TYPE;}
    "character"                  {return RRTypes.CHARACTER;}
    "sub_faction"                {return RRTypes.SUB_FACTION;}
    "leader"                     {return RRTypes.LEADER;}
    "heir"                       {return RRTypes.HEIR;}
    "age"                        {return RRTypes.AGE;}
    "portrait_index"             {return RRTypes.PORTRAIT_INDEX;}
    "traits"                     {return RRTypes.TRAITS;}
    "ancillaries"                {yybegin(DESCR_STRAT_NO_KEYWORDS); return RRTypes.ANCILLARIES;}
    "army"                       {return RRTypes.ARMY;}
    "unit"                       {yybegin(DESCR_STRAT_NO_KEYWORDS); return RRTypes.UNIT;}
    "armour"                     {return RRTypes.ARMOUR;}
    "weapon_lvl"                 {return RRTypes.WEAPON_LVL;}
    "character"                  {return RRTypes.CHARACTER;}
    "character_record"           {return RRTypes.CHARACTER_RECORD;}
    "core_attitudes"             {return RRTypes.CORE_ATTITUDES;}
    "character_record"           {return RRTypes.CHARACTER_RECORD;}
    "command"                    {return RRTypes.COMMAND;}
    "influence"                  {return RRTypes.INFLUENCE;}
    "management"                 {return RRTypes.MANAGEMENT;}
    "subterfuge"                 {return RRTypes.SUBTERFUGE;}
    "alive"                      {return RRTypes.ALIVE;}
    "never_a_leader"             {return RRTypes.NEVER_A_LEADER;}
    "male"                       {return RRTypes.MALE;}
    "female"                     {return RRTypes.FEMALE;}
    "relative"                   {return RRTypes.RELATIVE;}
    "core_attitudes"             {return RRTypes.CORE_ATTITUDES;}
    "faction_relationships"      {return RRTypes.FACTION_RELATIONSHIPS;}
    "allied_to"                  {return RRTypes.ALLIED_TO;}
    "at_war_with"                {return RRTypes.AT_WAR_WITH;}
    "superfaction_setup"         {return RRTypes.SUPERFACTION_SETUP;}
    "default_hostile"            {return RRTypes.DEFAULT_HOSTILE;}
    "mission_queue"              {return RRTypes.MISSION_QUEUE;}
    "x"                          {return RRTypes.X;}
    "y"                          {return RRTypes.Y;}
    "script"                     {return RRTypes.SCRIPT;}
    "spawn_script"               {return RRTypes.SPAWN_SCRIPT;}
    "revolt"                     {return RRTypes.REVOLT;}
    "once_only"                  {return RRTypes.ONCE_ONLY;}
    {ID}                         {return RRTypes.ID;}
}
<DESCR_STRAT_NO_KEYWORDS>
{
    {WS}                      {return TokenType.WHITE_SPACE;}
    {COMMENT}                 {return RRTypes.COMMENT;}
    ","                       {return RRTypes.COMMA;}
    "exp"                     {yybegin(DESCR_STRAT); return RRTypes.EXP;}
    "army"                    {yybegin(DESCR_STRAT); return RRTypes.ARMY;}
    "character"               {yybegin(DESCR_STRAT); return RRTypes.CHARACTER;}
    "character_record"        {yybegin(DESCR_STRAT); return RRTypes.CHARACTER_RECORD;}
    {ID}                      {return RRTypes.ID;}
}

<EXPORT_DESCR_UNIT>
{
    "type"                          {yybegin(EXPORT_DESCR_UNIT_NO_KEYWORDS); return RRTypes.TYPE;}
    "category"                      {return RRTypes.CATEGORY;}
    "class"                         {return RRTypes.CLASS;}
    "voice_type"                    {return RRTypes.VOICE_TYPE;}
    "voice_indexes"                 {return RRTypes.VOICE_INDEXES;}
    "soldier"                       {yybegin(EXPORT_DESCR_UNIT_NO_KEYWORDS); return RRTypes.SOLDIER;}
    "soldiers"                      {return RRTypes.SOLDIERS;}
    "default"                       {return RRTypes.DEFAULT_LC;}
    "mount"                         {yybegin(EXPORT_DESCR_UNIT_NO_KEYWORDS); return RRTypes.MOUNT;}
    "mount_effect"                  {return RRTypes.MOUNT_EFFECT;}
    "officer"                       {return RRTypes.OFFICER;}
    "no"                            {return RRTypes.NO;}
    "frighten_foot"                 {return RRTypes.FRIGHTEN_FOOT;}
    "frighten_mounted"              {return RRTypes.FRIGHTEN_MOUNTED;}
    "can_run_amok"                  {return RRTypes.CAN_RUN_AMOK;}
    "warcry"                        {return RRTypes.WARCRY;}
    "can_sap"                       {return RRTypes.CAN_SAP;}
    "can_swim"                      {return RRTypes.CAN_SWIM;}
    "hardy"                         {return RRTypes.HARDY;}
    "very_hardy"                    {return RRTypes.VERY_HARDY;}
    "power_charge"                  {return RRTypes.POWER_CHARGE;}
    "sea_faring"                    {return RRTypes.SEA_FARING;}
    "cantabrian_circle"             {return RRTypes.CANTABRIAN_CIRCLE;}
    "general_unit"                  {return RRTypes.GENERAL_UNIT;}
    "general_unit_upgrade \"marian_reforms\"" {return RRTypes.GENERAL_UNIT_UPGRADE;}
    "mercenary_unit"                {return RRTypes.MERCENARY_UNIT;}
    "druid"                         {return RRTypes.DRUID;}
    "screeching_women"              {return RRTypes.SCREECHING_WOMEN;}
    "no_custom"                     {return RRTypes.NO_CUSTOM;}
    "is_peasant"                    {return RRTypes.IS_PEASANT;}
    "can_horde"                     {return RRTypes.CAN_HORDE;}
    "command"                       {return RRTypes.COMMAND;}
    "legionary_name"                {return RRTypes.LEGIONARY_NAME;}
    "melee"                         {return RRTypes.MELEE;}
    "thrown"                        {return RRTypes.THROWN;}
    "siege_missile"                 {return RRTypes.SIEGE_MISSILE;}
    "simple"                        {return RRTypes.SIMPLE;}
    "other"                         {return RRTypes.OTHER;}
    "blade"                         {return RRTypes.BLADE;}
    "archery"                       {return RRTypes.ARCHERY;}
    "piercing"                      {return RRTypes.PIERCING;}
    "blunt"                         {return RRTypes.BLUNT;}
    "slashing"                      {return RRTypes.SLASHING;}
    "fire"                          {return RRTypes.FIRE;}
    "none"                          {return RRTypes.NONE;}
    "knife"                         {return RRTypes.KNIFE;}
    "mace"                          {return RRTypes.MACE;}
    "club"                          {return RRTypes.CLUB;}
    "axe"                           {return RRTypes.AXE;}
    "sword"                         {return RRTypes.SWORD;}
    "spear"                         {return RRTypes.SPEAR;}
    "ap"                            {return RRTypes.AP;}
    "bp"                            {return RRTypes.BP;}
    "light_spear"                   {return RRTypes.LIGHT_SPEAR;}
    {SPEAR_BONUS_X}                 {return RRTypes.SPEAR_BONUS_X;}
    "long_pike"                     {return RRTypes.LONG_PIKE;}
    "short_pike"                    {return RRTypes.SHORT_PIKE;}
    "prec"                          {return RRTypes.PREC;}
    "thrown ap"                     {return RRTypes.THROWN_AP;}
    "launching"                     {return RRTypes.LAUNCHING;}
    "area"                          {return RRTypes.AREA;}
    "SPEAR_BONUS_X"                 {return RRTypes.SPEAR_BONUS_X;}
    "flesh"                         {return RRTypes.FLESH;}
    "leather"                       {return RRTypes.LEATHER;}
    "wood"                          {return RRTypes.WOOD;}
    "metal"                         {return RRTypes.METAL;}
    "berserker"                     {return RRTypes.BERSERKER;}
    "impetuous"                     {return RRTypes.IMPETUOUS;}
    "low"                           {return RRTypes.LOW;}
    "normal"                        {return RRTypes.NORMAL;}
    "disciplined"                   {return RRTypes.DISCIPLINED;}
    "untrained"                     {return RRTypes.UNTRAINED;}
    "trained"                       {return RRTypes.TRAINED;}
    "highly_trained"                {return RRTypes.HIGHLY_TRAINED;}
    "attributes"                    {return RRTypes.ATTRIBUTES;}
    "formation"                     {return RRTypes.FORMATION;}
    "stat_health"                   {return RRTypes.STAT_HEALTH;}
    "stat_pri_attr"                 {return RRTypes.STAT_PRI_ATTR;}
    "stat_pri"                      {return RRTypes.STAT_PRI;}
    "stat_sec"                      {return RRTypes.STAT_SEC;}
    "stat_sec_attr"                 {return RRTypes.STAT_SEC_ATTR;}
    "stat_pri_armour"               {return RRTypes.STAT_PRI_ARMOUR;}
    "stat_sec_armour"               {return RRTypes.STAT_SEC_ARMOUR;}
    "stat_heat"                     {return RRTypes.STAT_HEAT;}
    "stat_ground"                   {return RRTypes.STAT_GROUND;}
    "stat_mental"                   {return RRTypes.STAT_MENTAL;}
    "stat_charge_dist"              {return RRTypes.STAT_CHARGE_DIST;}
    "stat_fire_delay"               {return RRTypes.STAT_FIRE_DELAY;}
    "stat_food"                     {return RRTypes.STAT_FOOD;}
    "stat_cost"                     {return RRTypes.STAT_COST;}
    "don't_allow_mixed"             {return RRTypes.DONT_ALLOW_MIXED;}
    "don't_allow_regional"          {return RRTypes.DONT_ALLOW_REGIONAL;}
    "don't_allow_custom"            {return RRTypes.DONT_ALLOW_CUSTOM;}
    "tattoo_color"                  {return RRTypes.TATTOO_COLOR;}
    "unique_tattoo"                 {return RRTypes.UNIQUE_TATTOO;}
    "exclude_tattoo"                {return RRTypes.EXCLUDE_TATTOO;}
    "hair_color"                    {return RRTypes.HAIR_COLOR;}
    "hair_style"                    {return RRTypes.HAIR_STYLE;}
    "is_female"                     {return RRTypes.IS_FEMALE;}
    "rebalance_statblock"           {return RRTypes.REBALANCE_STATBLOCK;}
    "recruit_priority_offset"       {return RRTypes.RECRUIT_PRIORITY_OFFSET;}
    "ownership"                     {return RRTypes.OWNERSHIP;}
    "ethnicity"                     {return RRTypes.ETHNICITY;}
    {ID}                            {return RRTypes.ID;}
}
<EXPORT_DESCR_UNIT_NO_KEYWORDS>
{
    {WS}                      {return TokenType.WHITE_SPACE;}
    {COMMENT}                 {return RRTypes.COMMENT;}
    {INT}                     {return RRTypes.INT;}
    {FLOAT}                   {return RRTypes.FLOAT;}
    ","                       {return RRTypes.COMMA;}

    "dictionary"              {yybegin(EXPORT_DESCR_UNIT); return RRTypes.DICTIONARY;}
    "engine"                  {return RRTypes.ENGINE;}
    "ship"                    {return RRTypes.SHIP;}
    "animal"                  {return RRTypes.ANIMAL;}
    "mount"                   {return RRTypes.MOUNT;}
    "officer"                 {return RRTypes.OFFICER;}
    "mount_effect"            {yybegin(EXPORT_DESCR_UNIT); return RRTypes.MOUNT_EFFECT;}
    "attributes"              {yybegin(EXPORT_DESCR_UNIT); return RRTypes.ATTRIBUTES;}
    {ID}                      {return RRTypes.ID;}
}

<EXPORT_DESCR_BUILDINGS>
{
    "alias"                         {return RRTypes.ALIAS;}
    "requires"                      {return RRTypes.REQUIRES;}
    "no_building_tagged"            {return RRTypes.NO_BUILDING_TAGGED;}
    "building"                      {return RRTypes.BUILDING;}
    "queued"                        {return RRTypes.QUEUED;}
    "display_string"                {return RRTypes.DISPLAY_STRING;}
    "tag"                           {return RRTypes.TAG;}
    "core"                          {return RRTypes.CORE;}
    "defence"                       {return RRTypes.DEFENCE;}
    "military"                      {return RRTypes.MILITARY;}
    "trade"                         {return RRTypes.TRADE;}
    "water"                         {return RRTypes.WATER;}
    "culture"                       {return RRTypes.CULTURE;}
    "religious"                     {return RRTypes.RELIGIOUS;}
    "icon"                          {yybegin(EXPORT_DESCR_BUILDINGS_NO_KEYWORDS);return RRTypes.ICON;}
    "classification"                {return RRTypes.CLASSIFICATION;}
    "levels"                        {return RRTypes.LEVELS;}
    "plugins"                       {return RRTypes.PLUGINS;}
    "factions"                      {return RRTypes.FACTIONS;}
    "capability"                    {return RRTypes.CAPABILITY;}
    "faction_capability"            {return RRTypes.FACTION_CAPABILITY;}
    "taxable_income_bonus"          {return RRTypes.TAXABLE_INCOME_BONUS;}
    "trade_base_income_bonus"       {return RRTypes.TRADE_BASE_INCOME_BONUS;}
    "trade_level_bonus"             {return RRTypes.TRADE_LEVEL_BONUS;}
    "trade_fleet"                   {return RRTypes.TRADE_FLEET;}
    "farming_level"                 {return RRTypes.FARMING_LEVEL;}
    "road_level"                    {return RRTypes.ROAD_LEVEL;}
    "mine_resource"                 {return RRTypes.MINE_RESOURCE;}
    "happiness_bonus"               {return RRTypes.HAPPINESS_BONUS;}
    "law_bonus"                     {return RRTypes.LAW_BONUS;}
    "religious_belief"              {return RRTypes.RELIGIOUS_BELIEF;}
    "population_health_bonus"       {return RRTypes.POPULATION_HEALTH_BONUS;}
    "population_growth_bonus"       {return RRTypes.POPULATION_GROWTH_BONUS;}
    "construction_cost_bonus_military"  {return RRTypes.CONSTRUCTION_COST_BONUS_MILITARY;}
    "construction_cost_bonus_religious" {return RRTypes.CONSTRUCTION_COST_BONUS_RELIGIOUS;}
    "construction_cost_bonus_defensive" {return RRTypes.CONSTRUCTION_COST_BONUS_DEFENSIVE;}
    "construction_cost_bonus_other"     {return RRTypes.CONSTRUCTION_COST_BONUS_OTHER;}
    "construction_time_bonus_military"  {return RRTypes.CONSTRUCTION_TIME_BONUS_MILITARY;}
    "construction_time_bonus_religious" {return RRTypes.CONSTRUCTION_TIME_BONUS_RELIGIOUS;}
    "construction_time_bonus_defensive" {return RRTypes.CONSTRUCTION_TIME_BONUS_DEFENSIVE;}
    "construction_time_bonus_other"     {return RRTypes.CONSTRUCTION_TIME_BONUS_OTHER;}
    "wall_level"                    {return RRTypes.WALL_LEVEL;}
    "tower_level"                   {return RRTypes.TOWER_LEVEL;}
    "gate_strength"                 {return RRTypes.GATE_STRENGTH;}
    "gate_defences"                 {return RRTypes.GATE_DEFENCES;}
    "recruits_exp_bonus"            {return RRTypes.RECRUITS_EXP_BONUS;}
    "recruits_morale_bonus"         {return RRTypes.RECRUITS_MORALE_BONUS;}
    "weapon_simple"                 {return RRTypes.WEAPON_SIMPLE;}
    "weapon_bladed"                 {return RRTypes.WEAPON_BLADED;}
    "weapon_missile"                {return RRTypes.WEAPON_MISSILE;}
    "weapon_siege"                  {return RRTypes.WEAPON_SIEGE;}
    "weapon_other"                  {return RRTypes.WEAPON_OTHER;}
    "armour"                        {return RRTypes.ARMOUR;}
    "upgrade_bodyguard"             {return RRTypes.UPGRADE_BODYGUARD;}
    "stage_races"                   {return RRTypes.STAGE_RACES;}
    "stage_games"                   {return RRTypes.STAGE_GAMES;}
    "agent_limit_settlement"        {return RRTypes.AGENT_LIMIT_SETTLEMENT;}
    "dummy"                         {return RRTypes.DUMMY;}
    "Capability_TraitsAndRetinue"   {return RRTypes.CAPABILITY_TRAITSANDRETINUE;}
    "ai_destruction_hint"           {return RRTypes.AI_DESTRUCTION_HINT;}
    "recruit"                       {return RRTypes.RECRUIT;}
    "agent"                         {return RRTypes.AGENT;}
    "construction"                  {return RRTypes.CONSTRUCTION;}
    "cost"                          {return RRTypes.COST;}
    "settlement_min"                {return RRTypes.SETTLEMENT_MIN;}
    "diplomat"                      {return RRTypes.DIPLOMAT;}
    "spy"                           {return RRTypes.SPY;}
    "assassin"                      {return RRTypes.ASSASSIN;}
    "merchant"                      {return RRTypes.MERCHANT;}
    "upgrades"                      {return RRTypes.UPGRADES;}
    "bonus"                         {return RRTypes.BONUS;}
    "is_toggled"                    {return RRTypes.IS_TOGGLED;}
    "major_event"                   {return RRTypes.MAJOR_EVENT;}
    "is_player"                     {return RRTypes.IS_PLAYER;}
    "resource"                      {return RRTypes.RESOURCE;}
    "hidden_resource"               {return RRTypes.HIDDEN_RESOURCE;}
    "building_present"              {return RRTypes.BUILDING_PRESENT;}
    "building_present_min_level"    {return RRTypes.BUILDING_PRESENT_MIN_LEVEL;}
    {ID}                            {return RRTypes.ID;}
}
<EXPORT_DESCR_BUILDINGS_NO_KEYWORDS>
{
    {WS}                      {return TokenType.WHITE_SPACE;}
    {COMMENT}                 {return RRTypes.COMMENT;}
    "levels"                  {return RRTypes.LEVELS;}
    "{"                       {yybegin(EXPORT_DESCR_BUILDINGS);return RRTypes.OCB;}
    {ID}                      {return RRTypes.ID;}
}

<EXPORT_DESCR_ANCILLARIES>
{
    "Ancillary"           {yybegin(EXPORT_DESCR_ANCILLARIES_NO_KEYWORDS); return RRTypes.ANCILLARY;}
    "Image"               {return RRTypes.IMAGE;}
    "Unique"              {return RRTypes.UNIQUE;}
    "ExcludedAncillaries" {return RRTypes.EXCLUDEDANCILLARIES;}
    "ExcludeCultures"     {return RRTypes.EXCLUDECULTURES;}
    "Description"         {return RRTypes.DESCRIPTION;}
    "EffectsDescription"  {return RRTypes.EFFECTSDESCRIPTION;}
    "Effect"              {return RRTypes.EFFECT;}
    "FakeEffect"          {return RRTypes.FAKEEFFECT;}
    "Hidden"              {return RRTypes.HIDDEN;}
    "ShowStats"           {return RRTypes.SHOWSTATS;}
    "Trigger"             {return RRTypes.TRIGGER;}
    "WhenToTest"          {yybegin(SCRIPTS_EVENTS_CONDITIONS); return RRTypes.WHENTOTEST;}
    "AcquireAncillary"    {yybegin(EXPORT_DESCR_ANCILLARIES_NO_KEYWORDS); return RRTypes.ACQUIREANCILLARY;}
    "RemoveAncillary"     {return RRTypes.REMOVEANCILLARY;}
    "chance"              {return RRTypes.CHANCE_LC;}
    {ID}                  {return RRTypes.ID;}
}
<EXPORT_DESCR_ANCILLARIES_NO_KEYWORDS>
{
    {WS}                      {return TokenType.WHITE_SPACE;}
    {COMMENT}                 {return RRTypes.COMMENT;}
    "Image"                   {yybegin(EXPORT_DESCR_ANCILLARIES); return RRTypes.IMAGE;}
    "Hidden"                  {yybegin(EXPORT_DESCR_ANCILLARIES); return RRTypes.HIDDEN;}
    "ShowStats"               {yybegin(EXPORT_DESCR_ANCILLARIES); return RRTypes.SHOWSTATS;}
    "chance"                  {yybegin(EXPORT_DESCR_ANCILLARIES); return RRTypes.CHANCE_LC;}
    {ID}                      {return RRTypes.ID;}
}

<EXPORT_DESCR_TRAITS>
{
    "Trait"               {return RRTypes.TRAIT;}
    "Characters"          {return RRTypes.CHARACTERS;}
    "Hidden"              {return RRTypes.HIDDEN;}
    "NoGoingBackLevel"    {return RRTypes.NOGOINGBACKLEVEL;}
    "ExcludeCultures"     {return RRTypes.EXCLUDECULTURES;}
    "AntiTraits"          {return RRTypes.ANTITRAITS;}
    "Description"         {return RRTypes.DESCRIPTION;}
    "EffectsDescription"  {return RRTypes.EFFECTSDESCRIPTION;}
    "GainMessage"         {return RRTypes.GAINMESSAGE;}
    "Epithet"             {return RRTypes.EPITHET;}
    "Level"               {return RRTypes.LEVEL;}
    "WhenToTest"          {yybegin(SCRIPTS_EVENTS_CONDITIONS); return RRTypes.WHENTOTEST;}
    "LoseMessage"         {return RRTypes.LOSEMESSAGE;}
    "Threshold"           {return RRTypes.THRESHOLD;}
    "Effect"              {return RRTypes.EFFECT;}
    "Affects"             {return RRTypes.AFFECTS;}
    "Trigger"             {return RRTypes.TRIGGER;}
    "Chance"              {return RRTypes.CHANCE;}
    "Lose"                {return RRTypes.LOSE;}
    {ID}                  {return RRTypes.ID;}
}

<EXPORT_DESCR_MERCENARIES>
{
    "pool"             {return RRTypes.POOL;}
    "-"                {return RRTypes.DASH;}
    "regions"          {return RRTypes.REGIONS;}
    "unit"             {yybegin(EXPORT_DESCR_MERCENARIES_NO_KEYWORDS); return RRTypes.UNIT;}
    "exp"              {return RRTypes.EXP;}
    "cost"             {return RRTypes.COST;}
    "replenish"        {return RRTypes.REPLENISH;}
    "max"              {return RRTypes.MAX;}
    "initial"          {return RRTypes.INITIAL;}
    {ID}               {return RRTypes.ID;}
}
<EXPORT_DESCR_MERCENARIES_NO_KEYWORDS>
{
    {WS}                      {return TokenType.WHITE_SPACE;}
    {COMMENT}                 {return RRTypes.COMMENT;}
    ","                       {return RRTypes.COMMA;}
    "exp"                     {yybegin(EXPORT_DESCR_MERCENARIES); return RRTypes.EXP;}
    {ID}                      {return RRTypes.ID;}
}

<DESCR_NAMES>
{
    "faction"      {return RRTypes.FACTION;}
    "characters"   {return RRTypes.CHARACTERS_LC;}
    "surnames"     {return RRTypes.SURNAMES;}
    "women"        {return RRTypes.WOMEN;}
    {ID}           {return RRTypes.ID;}
}

<DESCR_UNIT_VARIATION>
{
    "male"              {return RRTypes.MALE;}
    "female"            {return RRTypes.FEMALE;}
    "ethnicities"       {return RRTypes.ETHNICITIES;}
    "hair_styles"       {return RRTypes.HAIR_STYLES;}
    "female_hair"       {return RRTypes.FEMALE_HAIR;}
    "beard_styles"      {return RRTypes.BEARD_STYLES;}
    "hair_color"        {return RRTypes.HAIR_COLOR;}
    "aged_hair_color"   {return RRTypes.AGED_HAIR_COLOR;}
    "region"            {return RRTypes.REGION;}
    "variant"           {return RRTypes.VARIANT;}
    "ethnicity"         {return RRTypes.ETHNICITY;}
    "distribution"      {return RRTypes.DISTRIBUTION;}
    {ID}                {return RRTypes.ID;}
}

<FERAL_DESCR_AI_PERSONALITY>
{
    {WS}                                {return TokenType.WHITE_SPACE;}
    {COMMENT}                           {return RRTypes.COMMENT;}
    {INT}                               {return RRTypes.INT;}
    "building_priority"                 {return RRTypes.BUILDING_PRIORITY;}
    "population_growth_bonus"           {return RRTypes.POPULATION_GROWTH_BONUS;}
    "population_loyalty_bonus"          {return RRTypes.POPULATION_LOYALTY_BONUS;}
    "population_health_bonus"           {return RRTypes.POPULATION_HEALTH_BONUS;}
    "trade_base_income_bonus"           {return RRTypes.TRADE_BASE_INCOME_BONUS;}
    "trade_level_bonus"                 {return RRTypes.TRADE_LEVEL_BONUS;}
    "trade_fleet"                       {return RRTypes.TRADE_FLEET;}
    "taxable_income_bonus"              {return RRTypes.TAXABLE_INCOME_BONUS;}
    "mine_resource_level"               {return RRTypes.MINE_RESOURCE_LEVEL;}
    "farming_level"                     {return RRTypes.FARMING_LEVEL;}
    "road_level"                        {return RRTypes.ROAD_LEVEL;}
    "gate_strength"                     {return RRTypes.GATE_STRENGTH;}
    "gate_defenses"                     {return RRTypes.GATE_DEFENSES;}
    "wall_level"                        {return RRTypes.WALL_LEVEL;}
    "tower_level"                       {return RRTypes.TOWER_LEVEL;}
    "stage_games"                       {return RRTypes.STAGE_GAMES;}
    "stage_races"                       {return RRTypes.STAGE_RACES;}
    "weapon_bladed"                     {return RRTypes.WEAPON_BLADED;}
    "weapon_missile"                    {return RRTypes.WEAPON_MISSILE;}
    "armour"                            {return RRTypes.ARMOUR;}
    "population_fire_risk_bonus"        {return RRTypes.POPULATION_FIRE_RISK_BONUS;}
    "bodyguard"                         {return RRTypes.BODYGUARD;}
    "recruits_morale_bonus"             {return RRTypes.RECRUITS_MORALE_BONUS;}
    "recruits_experience_bonus"         {return RRTypes.RECRUITS_EXPERIENCE_BONUS;}
    "happiness_bonus"                   {return RRTypes.HAPPINESS_BONUS;}
    "law_bonus"                         {return RRTypes.LAW_BONUS;}
    "military_priority"                 {return RRTypes.MILITARY_PRIORITY;}
    "mass"                              {return RRTypes.MASS;}
    "infantry_light"                    {return RRTypes.INFANTRY_LIGHT;}
    "infantry_heavy"                    {return RRTypes.INFANTRY_HEAVY;}
    "infantry_missile"                  {return RRTypes.INFANTRY_MISSILE;}
    "infantry_spearmen"                 {return RRTypes.INFANTRY_SPEARMEN;}
    "cavalry_light"                     {return RRTypes.CAVALRY_LIGHT;}
    "cavalry_heavy"                     {return RRTypes.CAVALRY_HEAVY;}
    "cavalry_missile"                   {return RRTypes.CAVALRY_MISSILE;}
    "siege_artillery"                   {return RRTypes.SIEGE_ARTILLERY;}
    "special"                           {return RRTypes.SPECIAL;}
    "sally_agression"                   {return RRTypes.SALLY_AGRESSION;}
    "sally_desperate"                   {return RRTypes.SALLY_DESPERATE;}
    "attack_risk_taker"                 {return RRTypes.ATTACK_RISK_TAKER;}
    "subterfuge_risk_taker"             {return RRTypes.SUBTERFUGE_RISK_TAKER;}
    "diplomatic_priority"               {return RRTypes.DIPLOMATIC_PRIORITY;}
    "aggresiveness"                     {return RRTypes.AGGRESIVENESS;}
    "personality"                       {return RRTypes.PERSONALITY;}
    {ID}                                {return RRTypes.ID;}
}

<DESCR_FACTION_GROUPS>
{
    "group"      {return RRTypes.GROUP;}
    "faction"    {return RRTypes.FACTION;}
    {ID}         {return RRTypes.ID;}
}

<FERAL_DESCR_PORTRAITS_VARIATION>
{
    "old"        {return RRTypes.OLD;}
    "civilian"   {return RRTypes.CIVILIAN;}
    "rogue"      {return RRTypes.ROGUE;}
    "young"      {return RRTypes.YOUNG;}
    {ID}         {return RRTypes.ID;}
}

<DESCR_BANNERS>
{
    "banner"            {return RRTypes.BANNER;}
    "model"             {return RRTypes.MODEL;}
    "skeleton"          {return RRTypes.SKELETON;}
    "outline"           {return RRTypes.OUTLINE;}
    "faction"           {return RRTypes.FACTION;}
    "standard_texture"  {return RRTypes.STANDARD_TEXTURE;}
    "rebels_texture"    {return RRTypes.REBELS_TEXTURE;}
    "routing_texture"   {return RRTypes.ROUTING_TEXTURE;}
    "ally_texture"      {return RRTypes.ALLY_TEXTURE;}
    {ID}                {return RRTypes.ID;}
}

<DESCR_BUILDING_BATTLE>
{
    "texture_variants"    {return RRTypes.TEXTURE_VARIANTS;}
    "winter"              {return RRTypes.WINTER;}
    "snowcover"           {return RRTypes.SNOWCOVER;}
    "snowfall"            {return RRTypes.SNOWFALL;}
    "spot_items"          {return RRTypes.SPOT_ITEMS;}
    "any"                 {return RRTypes.ANY;}
    "stat_cats"           {return RRTypes.STAT_CATS;}
    "full_health"         {return RRTypes.FULL_HEALTH;}
    "battle_stats"        {return RRTypes.BATTLE_STATS;}
    "transition_scripts"  {return RRTypes.TRANSITION_SCRIPTS;}
    "transition"          {return RRTypes.TRANSITION;}
    "duration"            {return RRTypes.DURATION;}
    "physical_switch"     {return RRTypes.PHYSICAL_SWITCH;}
    "start_items"         {return RRTypes.START_ITEMS;}
    "end_items"           {return RRTypes.END_ITEMS;}
    "include"             {return RRTypes.INCLUDE;}
    "localised_name"      {return RRTypes.LOCALISED_NAME;}
    "level"               {return RRTypes.LEVEL;}
    "min_health"          {return RRTypes.MIN_HEALTH;}
    "item"                {return RRTypes.ITEM;}
    "physical_info"       {return RRTypes.PHYSICAL_INFO;}
    "none"                {return RRTypes.NONE;}
    {ID}                  {return RRTypes.ID;}
}

<DESCR_LBC_DB>
{
    "faction"    {return RRTypes.FACTION;}
    "model"      {return RRTypes.MODEL;}
    {ID}         {return RRTypes.ID;}
}

<DESCR_OFFMAP_MODELS>
{
    "navy"              {return RRTypes.NAVY;}
    "faction"           {return RRTypes.FACTION;}
    "large"             {return RRTypes.LARGE;}
    "medium"            {return RRTypes.MEDIUM;}
    "small"             {return RRTypes.SMALL;}
    "settlement"        {return RRTypes.SETTLEMENT;}
    "culture"           {return RRTypes.CULTURE;}
    "level"             {return RRTypes.LEVEL;}
    "wonder"            {return RRTypes.WONDER;}
    "port"              {return RRTypes.PORT;}
    "fishing_village"   {return RRTypes.FISHING_VILLAGE;}
    "sea_port"          {return RRTypes.SEA_PORT;}
    "shipwright"        {return RRTypes.SHIPWRIGHT;}
    "dockyard"          {return RRTypes.DOCKYARD;}
    {ID}                {return RRTypes.ID;}
}

<DESCR_SM_LANDMARKS>
{
    "type"          {return RRTypes.TYPE;}
    "item"          {return RRTypes.ITEM;}
    "image"         {return RRTypes.IMAGE;}
    "julii_rtm"     {return RRTypes.JULII_RTM;}
    "brutii_rtm"    {return RRTypes.BRUTII_RTM;}
    "scipii_rtm"    {return RRTypes.SCIPII_RTM;}
    "movie"         {return RRTypes.MOVIE;}
    {ID}            {return RRTypes.ID;}
}

<DESCR_DISASTERS>
{
    "disaster"          {return RRTypes.DISASTER_LC;}
    "type"              {return RRTypes.TYPE;}
    "year"              {return RRTypes.YEAR;}
    "position"          {return RRTypes.POSITION;}
    "size"              {return RRTypes.SIZE;}
    "winter"            {return RRTypes.WINTER;}
    "summer"            {return RRTypes.SUMMER;}
    // disaster_type
    "earthquake"        {return RRTypes.EARTHQUAKE;}
    "flood"             {return RRTypes.FLOOD;}
    "horde"             {return RRTypes.HORDE;}
    "fire"              {return RRTypes.FIRE;}
    "riot"              {return RRTypes.RIOT;}
    "storm"             {return RRTypes.STORM;}
    "volcano"           {return RRTypes.VOLCANO;}
    {ID}                {return RRTypes.ID;}
}

<DESCR_ITEMS>
{
    "include"           {return RRTypes.INCLUDE;}
    "type"              {return RRTypes.TYPE;}
    "lod"               {return RRTypes.LOD;}
    "max_distance"      {return RRTypes.MAX_DISTANCE;}
    "model_rigid"       {return RRTypes.MODEL_RIGID;}
    {ID}                {return RRTypes.ID;}
}

<DESCR_MODEL_BATTLE_AND_STRAT>
{
    "type"                  {yybegin(DESCR_MODEL_BATTLE_AND_STRAT_NO_KEYWORDS); return RRTypes.TYPE;}
    "scale"                 {return RRTypes.SCALE;}
    "skeleton_horse"        {return RRTypes.SKELETON_HORSE;}
    "skeleton_elephant"     {return RRTypes.SKELETON_ELEPHANT;}
    "skeleton_chariot"      {return RRTypes.SKELETON_CHARIOT;}
    "skeleton_camel"        {return RRTypes.SKELETON_CAMEL;}
    "body"                  {return RRTypes.BODY;}
    "male"                  {return RRTypes.MALE;}
    "female"                {return RRTypes.FEMALE;}
    "angry_face"            {return RRTypes.ANGRY_FACE;}
    "medieval_features"     {return RRTypes.MEDIEVAL_FEATURES;}
    "tired"                 {return RRTypes.TIRED_K;}
    "yes"                   {return RRTypes.YES;}
    "no"|"No"               {return RRTypes.NO;}
    "Tired"                 {return RRTypes.TIRED_C;}
    "VeryTired"             {return RRTypes.VERYTIRED;}
    "aged"                  {return RRTypes.AGED;}
    "pbr_texture"           {return RRTypes.PBR_TEXTURE;}
    "texture"               {return RRTypes.TEXTURE;}
    "default"               {return RRTypes.DEFAULT;}
    "no_variation"          {return RRTypes.NO_VARIATION;}
    "model"                 {return RRTypes.MODEL;}
    "model_flexi"           {return RRTypes.MODEL_FLEXI;}
    "model_flexi_m"         {return RRTypes.MODEL_FLEXI_M;}
    "model_flexi_c"         {return RRTypes.MODEL_FLEXI_C;}
    "max"                   {return RRTypes.MAX;}
    "Default"               {return RRTypes.DEFAULT;}
    "Skinny"                {return RRTypes.SKINNY;}
    "SkinnyAthletic"        {return RRTypes.SKINNYATHLETIC;}
    "Athletic"              {return RRTypes.ATHLETIC;}
    "Bulky"                 {return RRTypes.BULKY;}
    "Overweight"            {return RRTypes.OVERWEIGHT;}
    "ignore_registry"       {return RRTypes.IGNORE_REGISTRY;}
    {ID}                    {return RRTypes.ID;}
}
<DESCR_MODEL_BATTLE_AND_STRAT_NO_KEYWORDS>
{
    {WS}                      {return TokenType.WHITE_SPACE;}
    {COMMENT}                 {return RRTypes.COMMENT;}
    "skeleton"                {yybegin(DESCR_MODEL_BATTLE_AND_STRAT); return RRTypes.SKELETON;}
    {ID}                      {return RRTypes.ID;}
}

<DESCR_REBEL_FACTIONS>
{
    {WS}                 {return TokenType.WHITE_SPACE;}
    {COMMENT}            {return RRTypes.COMMENT;}
    {INT}                {return RRTypes.INT;}
    "rebel_type"         {return RRTypes.REBEL_TYPE;}
    "category"           {yybegin(DESCR_REBEL_FACTIONS_REBEL_TYPE); return RRTypes.CATEGORY;}
    "description"        {return RRTypes.DESCRIPTION_LC;}
    "unit"               {return RRTypes.UNIT;}
    {ID}                 {return RRTypes.ID;}
}
<DESCR_REBEL_FACTIONS_REBEL_TYPE>
{
    {WS}                 {return TokenType.WHITE_SPACE;}
    {COMMENT}            {return RRTypes.COMMENT;}
    "peasant_revolt"     {return RRTypes.PEASANT_REVOLT;}
    "gladiator_revolt"   {return RRTypes.GLADIATOR_REVOLT;}
    "brigands"           {return RRTypes.BRIGANDS;}
    "pirates"            {return RRTypes.PIRATES;}
    "chance"             {yybegin(DESCR_REBEL_FACTIONS); return RRTypes.CHANCE_LC;}
    {ID}                 {return RRTypes.ID;}
}

<DESCR_MOUNT>
{
    "type"                       {yybegin(DESCR_MOUNT_NO_KEYWORDS); return RRTypes.TYPE;}
    "model"                      {yybegin(DESCR_MOUNT_NO_KEYWORDS); return RRTypes.MODEL;}
    "radius"                     {return RRTypes.RADIUS;}
    "x_radius"                   {return RRTypes.X_RADIUS;}
    "height"                     {return RRTypes.HEIGHT;}
    "mass"                       {return RRTypes.MASS;}
    "banner_height"              {return RRTypes.BANNER_HEIGHT;}
    "bouyancy_offset"            {return RRTypes.BOUYANCY_OFFSET;}
    "water_trail_effect"         {return RRTypes.WATER_TRAIL_EFFECT;}
    "water_trail_effect_running" {return RRTypes.WATER_TRAIL_EFFECT_RUNNING;}
    "root_node_height"           {return RRTypes.ROOT_NODE_HEIGHT;}
    "rider_offset"               {return RRTypes.RIDER_OFFSET;}
    "attack_delay"               {return RRTypes.ATTACK_DELAY;}
    "dead_radius"                {return RRTypes.DEAD_RADIUS;}
    "tusk_z"                     {return RRTypes.TUSK_Z;}
    "tusk_radius"                {return RRTypes.TUSK_RADIUS;}
    "riders"                     {return RRTypes.RIDERS;}
    "axle_width"                 {return RRTypes.AXLE_WIDTH;}
    "wheel_radius"               {return RRTypes.WHEEL_RADIUS;}
    "pivot_offset"               {return RRTypes.PIVOT_OFFSET;}
    "pole_length"                {return RRTypes.POLE_LENGTH;}
    "pole_pivot"                 {return RRTypes.POLE_PIVOT;}
    "pole_connect"               {return RRTypes.POLE_CONNECT;}
    "harness_connect"            {return RRTypes.HARNESS_CONNECT;}
    "scythe_radius"              {return RRTypes.SCYTHE_RADIUS;}
    "revs_per_attack"            {return RRTypes.REVS_PER_ATTACK;}
    "horse_type"                 {yybegin(DESCR_MOUNT_NO_KEYWORDS); return RRTypes.HORSE_TYPE;}
    "horse_offset"               {return RRTypes.HORSE_OFFSET;}
    "lods"                       {return RRTypes.LODS;}
    "lod"                        {return RRTypes.LOD;}
    "scorpion_offset"            {return RRTypes.SCORPION_OFFSET;}
    "scorpion_height"            {return RRTypes.SCORPION_HEIGHT;}
    "scorpion_forward_length"    {return RRTypes.SCORPION_FORWARD_LENGTH;}
    "scorpion_reload_ticks"      {return RRTypes.SCORPION_RELOAD_TICKS;}
    {ID}                         {return RRTypes.ID;}
}
<DESCR_MOUNT_NO_KEYWORDS>
{
    {WS}                      {return TokenType.WHITE_SPACE;}
    {COMMENT}                 {return RRTypes.COMMENT;}
    "class"                   {yybegin(DESCR_MOUNT); return RRTypes.CLASS;}
    "radius"                  {yybegin(DESCR_MOUNT); return RRTypes.RADIUS;}
    "horses"                  {yybegin(DESCR_MOUNT); return RRTypes.HORSES;}
    {ID}                      {return RRTypes.ID;}
}

<DESCR_CHARACTER>
{
    "type"                    {return RRTypes.TYPE;}
    "faction"                 {return RRTypes.FACTION;}
    "dictionary"              {return RRTypes.DICTIONARY;}
    "starting_action_points"  {return RRTypes.STARTING_ACTION_POINTS;}
    "actions"                 {return RRTypes.ACTIONS;}
    "wage_base"               {return RRTypes.WAGE_BASE;}
    "strat_card"              {return RRTypes.STRAT_CARD;}
    "strat_model"             {yybegin(DESCR_CHARACTER_NO_KEYWORDS); return RRTypes.STRAT_MODEL;}
    "battle_model"            {return RRTypes.BATTLE_MODEL;}
    "battle_equip"            {return RRTypes.BATTLE_EQUIP;}
    {ID}                      {return RRTypes.ID;}
}
<DESCR_CHARACTER_NO_KEYWORDS>{
    {WS}            {return TokenType.WHITE_SPACE;}
    {COMMENT}       {return RRTypes.COMMENT;}
    "type"          {yybegin(DESCR_CHARACTER); return RRTypes.TYPE;}
    "battle_model"  {yybegin(DESCR_CHARACTER); return RRTypes.BATTLE_MODEL;}
    "faction"       {yybegin(DESCR_CHARACTER); return RRTypes.FACTION;}
    {ID}            {return RRTypes.ID;}
}

<TEXT_MAPPING>
{
    {WS}         {return TokenType.WHITE_SPACE;}
    {COMMENT}    {return RRTypes.COMMENT;}
    "{"          {return RRTypes.OCB;}
    "}"          {yybegin(TEXT_MAPPING_NO_KEYWORDS);return RRTypes.CCB;}
    {ID}         {return RRTypes.ID;}
}
<TEXT_MAPPING_NO_KEYWORDS>{
    {WS}         {return TokenType.WHITE_SPACE;}
    {COMMENT}    {return RRTypes.COMMENT;}
    "{"          {yybegin(TEXT_MAPPING);return RRTypes.OCB;}

    [^\t{\r\n]+ {return RRTypes.STRING;}
}

<ENUMS>
{
    {WS}         {return TokenType.WHITE_SPACE;}
    {COMMENT}    {return RRTypes.COMMENT;}
    {ID}         {return RRTypes.ID;}
}

//<SCRIPT>
//{
//
//    {ID}                                          {return RRTypes.ID;}
//}
//
//<CONDITIONS>
//{
//    {ID}                                              {return RRTypes.ID;}
//}

<SCRIPTS_EVENTS_CONDITIONS>
{
    // Local thingy
    {LOCAL}                                 {return RRTypes.LOCAL;}

    // Stuff that need to be captured but belong to the surrounging "context"
    "Condition"                             {yybegin(SCRIPTS_EVENTS_CONDITIONS); return RRTypes.CONDITION;}

    // Events
    "PreBattle"                             {return RRTypes.PREBATTLE;}
    "PreBattleWithdrawal"                   {return RRTypes.PREBATTLEWITHDRAWAL;}
    "BattleAiCommenced"                     {return RRTypes.BATTLEAICOMMENCED;}
    "BattleDelayPhaseCommenced"             {return RRTypes.BATTLEDELAYPHASECOMMENCED;}
    "BattleDeploymentPhaseCommenced"        {return RRTypes.BATTLEDEPLOYMENTPHASECOMMENCED;}
    "BattleConflictPhaseCommenced"          {return RRTypes.BATTLECONFLICTPHASECOMMENCED;}
    "BattlePlayerUnitAttacksEnemyUnit"      {return RRTypes.BATTLEPLAYERUNITATTACKSENEMYUNIT;}
    "BattleEnemyUnitAttacksPlayerUnit"      {return RRTypes.BATTLEENEMYUNITATTACKSPLAYERUNIT;}
    "BattlePlayerAttacksSettlementBuilding" {return RRTypes.BATTLEPLAYERATTACKSSETTLEMENTBUILDING;}
    "BattleEnemyAttacksSettlementBuilding"  {return RRTypes.BATTLEENEMYATTACKSSETTLEMENTBUILDING;}
    "BattleUnitGoesBerserk"                 {return RRTypes.BATTLEUNITGOESBERSERK;}
    "BattlePlayerUnitGoesBerserk"           {return RRTypes.BATTLEPLAYERUNITGOESBERSERK;}
    "BattleEnemyUnitGoesBerserk"            {return RRTypes.BATTLEENEMYUNITGOESBERSERK;}
    "BattleUnitRouts"                       {return RRTypes.BATTLEUNITROUTS;}
    "BattlePlayerUnitRouts"                 {return RRTypes.BATTLEPLAYERUNITROUTS;}
    "BattleEnemyUnitRouts"                  {return RRTypes.BATTLEENEMYUNITROUTS;}
    "BattlePlayerSiegeEngineDestroyed"      {return RRTypes.BATTLEPLAYERSIEGEENGINEDESTROYED;}
    "BattleEnemySiegeEngineDestroyed"       {return RRTypes.BATTLEENEMYSIEGEENGINEDESTROYED;}
    "PostBattle"                            {return RRTypes.POSTBATTLE;}
    "BattleArmyRouted"                      {return RRTypes.BATTLEARMYROUTED;}
    "BattleGeneralKilled"                   {return RRTypes.BATTLEGENERALKILLED;}
    "BattleGeneralRouted"                   {return RRTypes.BATTLEGENERALROUTED;}
    "BattleReinforcementsArrive"            {return RRTypes.BATTLEREINFORCEMENTSARRIVE;}
    "BattleSiegeEngineDestroyed"            {return RRTypes.BATTLESIEGEENGINEDESTROYED;}
    "BattleSiegeEngineDocksWall"            {return RRTypes.BATTLESIEGEENGINEDOCKSWALL;}
    "BattleGatesAttackedByEngine"           {return RRTypes.BATTLEGATESATTACKEDBYENGINE;}
    "BattleGatesAttackedByPlayerEngine"     {return RRTypes.BATTLEGATESATTACKEDBYPLAYERENGINE;}
    "BattleGatesAttackedByEnemyEngine"      {return RRTypes.BATTLEGATESATTACKEDBYENEMYENGINE;}
    "BattleBattleGatesDestroyedByEngine"    {return RRTypes.BATTLEBATTLEGATESDESTROYEDBYENGINE;}
    "BattleWallsBreachedByEngine"           {return RRTypes.BATTLEWALLSBREACHEDBYENGINE;}
    "BattleWallsCaptured"                   {return RRTypes.BATTLEWALLSCAPTURED;}
    "BattleFinished"                        {return RRTypes.BATTLEFINISHED;}
    "EnteredCityView"                       {return RRTypes.ENTEREDCITYVIEW;}
    "BattleMinimapAction"                   {return RRTypes.BATTLEMINIMAPACTION;}
    "BattlePlayerUnitSelected"              {return RRTypes.BATTLEPLAYERUNITSELECTED;}
    "EnterTacticalMode"                     {return RRTypes.ENTERTACTICALMODE;}
    "BattleReinforcementsHack"              {return RRTypes.BATTLEREINFORCEMENTSHACK;}
    "FactionTurnStart"                      {return RRTypes.FACTIONTURNSTART;}
    "FactionWarDeclared"                    {return RRTypes.FACTIONWARDECLARED;}
    "HordeFormed"                           {return RRTypes.HORDEFORMED;}
    "FactionTurnEnd"                        {return RRTypes.FACTIONTURNEND;}
    "HireMercenaries"                       {return RRTypes.HIREMERCENARIES;}
    "GeneralCaptureResidence"               {return RRTypes.GENERALCAPTURERESIDENCE;}
    "GeneralCaptureWonder"                  {return RRTypes.GENERALCAPTUREWONDER;}
    "GeneralCaptureSettlement"              {return RRTypes.GENERALCAPTURESETTLEMENT;}
    "LeaderDestroyedFaction"                {return RRTypes.LEADERDESTROYEDFACTION;}
    "Disaster"                              {return RRTypes.DISASTER;}
    "CharacterDamagedByDisaster"            {return RRTypes.CHARACTERDAMAGEDBYDISASTER;}
    "GeneralAssaultsResidence"              {return RRTypes.GENERALASSAULTSRESIDENCE;}
    "OfferedForAdoption"                    {return RRTypes.OFFEREDFORADOPTION;}
    "LesserGeneralOfferedForAdoption"       {return RRTypes.LESSERGENERALOFFEREDFORADOPTION;}
    "OfferedForMarriage"                    {return RRTypes.OFFEREDFORMARRIAGE;}
    "BrotherAdopted"                        {return RRTypes.BROTHERADOPTED;}
    "BecomesFactionLeader"                  {return RRTypes.BECOMESFACTIONLEADER;}
    "BecomesFactionHeir"                    {return RRTypes.BECOMESFACTIONHEIR;}
    "TakeOffice"                            {return RRTypes.TAKEOFFICE;}
    "CeasedFactionLeader"                   {return RRTypes.CEASEDFACTIONLEADER;}
    "CeasedFactionHeir"                     {return RRTypes.CEASEDFACTIONHEIR;}
    "LeaveOffice"                           {return RRTypes.LEAVEOFFICE;}
    "UngarrisonedFort"                      {return RRTypes.UNGARRISONEDFORT;}
    "LostLegionaryEagle"                    {return RRTypes.LOSTLEGIONARYEAGLE;}
    "CapturedLegionaryEagle"                {return RRTypes.CAPTUREDLEGIONARYEAGLE;}
    "RecapturedLegionaryEagle"              {return RRTypes.RECAPTUREDLEGIONARYEAGLE;}
    "SenateExposure"                        {return RRTypes.SENATEEXPOSURE;}
    "QuaestorInvestigationMinor"            {return RRTypes.QUAESTORINVESTIGATIONMINOR;}
    "QuaestorInvestigation"                 {return RRTypes.QUAESTORINVESTIGATION;}
    "QuaestorInvestigationMajor"            {return RRTypes.QUAESTORINVESTIGATIONMAJOR;}
    "PopularSupportForOverthrow"            {return RRTypes.POPULARSUPPORTFOROVERTHROW;}
    "SenateReadyToOutlawFaction"            {return RRTypes.SENATEREADYTOOUTLAWFACTION;}
    "SenateOutlawsFaction"                  {return RRTypes.SENATEOUTLAWSFACTION;}
    "NewTurnStart"                          {return RRTypes.NEWTURNSTART;}
    "ScriptPromptCallback"                  {return RRTypes.SCRIPTPROMPTCALLBACK;}
    "FactionDestroyed"                      {return RRTypes.FACTIONDESTROYED;}
    "Birth"                                 {return RRTypes.BIRTH;}
    "CharacterComesOfAge"                   {return RRTypes.CHARACTERCOMESOFAGE;}
    "CharacterMarries"                      {return RRTypes.CHARACTERMARRIES;}
    "CharacterBecomesAFather"               {return RRTypes.CHARACTERBECOMESAFATHER;}
    "CharacterTurnStart"                    {return RRTypes.CHARACTERTURNSTART;}
    "CharacterTurnEnd"                      {return RRTypes.CHARACTERTURNEND;}
    "CharacterTurnEndInSettlement"          {return RRTypes.CHARACTERTURNENDINSETTLEMENT;}
    "GeneralDevastatesTile"                 {return RRTypes.GENERALDEVASTATESTILE;}
    "SpyMission"                            {return RRTypes.SPYMISSION;}
    "ExecutesASpyOnAMission"                {return RRTypes.EXECUTESASPYONAMISSION;}
    "LeaderOrderedSpyingMission"            {return RRTypes.LEADERORDEREDSPYINGMISSION;}
    "AssassinationMission"                  {return RRTypes.ASSASSINATIONMISSION;}
    "ExecutesAnAssassinOnAMission"          {return RRTypes.EXECUTESANASSASSINONAMISSION;}
    "LeaderOrderedAssassination"            {return RRTypes.LEADERORDEREDASSASSINATION;}
    "SufferAssassinationAttempt"            {return RRTypes.SUFFERASSASSINATIONATTEMPT;}
    "SabotageMission"                       {return RRTypes.SABOTAGEMISSION;}
    "LeaderOrderedSabotage"                 {return RRTypes.LEADERORDEREDSABOTAGE;}
    "BriberyMission"                        {return RRTypes.BRIBERYMISSION;}
    "LeaderOrderedBribery"                  {return RRTypes.LEADERORDEREDBRIBERY;}
    "AcceptBribe"                           {return RRTypes.ACCEPTBRIBE;}
    "RefuseBribe"                           {return RRTypes.REFUSEBRIBE;}
    "Insurrection"                          {return RRTypes.INSURRECTION;}
    "DiplomacyMission"                      {return RRTypes.DIPLOMACYMISSION;}
    "LeaderOrderedDiplomacy"                {return RRTypes.LEADERORDEREDDIPLOMACY;}
    "LeaderSenateMissionSuccess"            {return RRTypes.LEADERSENATEMISSIONSUCCESS;}
    "LeaderSenateMissionFailed"             {return RRTypes.LEADERSENATEMISSIONFAILED;}
    "ConstructionItemClicked"               {return RRTypes.CONSTRUCTIONITEMCLICKED;}
    "RecruitmentItemClicked"                {return RRTypes.RECRUITMENTITEMCLICKED;}
    "RecruitmentPopulated"                  {return RRTypes.RECRUITMENTPOPULATED;}
    "ConstructionPopulated"                 {return RRTypes.CONSTRUCTIONPOPULATED;}
    "AgentListPopulated"                    {return RRTypes.AGENTLISTPOPULATED;}
    "MoveRetinuePopulated"                  {return RRTypes.MOVERETINUEPOPULATED;}
    "MoveRetinuePressed"                    {return RRTypes.MOVERETINUEPRESSED;}
    "MoveRetinueAncillarySelected"          {return RRTypes.MOVERETINUEANCILLARYSELECTED;}
    "MoveRetinueAncillaryDeselected"        {return RRTypes.MOVERETINUEANCILLARYDESELECTED;}
    "MissionSelected"                       {return RRTypes.MISSIONSELECTED;}
    "AgentSelected"                         {return RRTypes.AGENTSELECTED;}
    "ScrollDidOpen"                         {return RRTypes.SCROLLDIDOPEN;}
    "UnitHasRouted"                         {return RRTypes.UNITHASROUTED;}
    "BattleUnitActionStatus"                {return RRTypes.BATTLEUNITACTIONSTATUS;}
    "DiplomacyScrollPopulated"              {return RRTypes.DIPLOMACYSCROLLPOPULATED;}
    "ItemDeselected"                        {return RRTypes.ITEMDESELECTED;}
    "UnitInfoOpened"                        {return RRTypes.UNITINFOOPENED;}
    "AdvisorAudioStopped"                   {return RRTypes.ADVISORAUDIOSTOPPED;}
    "SettlementTurnStart"                   {return RRTypes.SETTLEMENTTURNSTART;}
    "SettlementTurnEnd"                     {return RRTypes.SETTLEMENTTURNEND;}
    "NewAdmiralCreated"                     {return RRTypes.NEWADMIRALCREATED;}
    "UnitTrained"                           {return RRTypes.UNITTRAINED;}
    "GovernorUnitTrained"                   {return RRTypes.GOVERNORUNITTRAINED;}
    "BuildingCompleted"                     {return RRTypes.BUILDINGCOMPLETED;}
    "GovernorBuildingCompleted"             {return RRTypes.GOVERNORBUILDINGCOMPLETED;}
    "PlugInCompleted"                       {return RRTypes.PLUGINCOMPLETED;}
    "GovernorPlugInCompleted"               {return RRTypes.GOVERNORPLUGINCOMPLETED;}
    "AgentCreated"                          {return RRTypes.AGENTCREATED;}
    "GovernorAgentCreated"                  {return RRTypes.GOVERNORAGENTCREATED;}
    "BuildingDestroyed"                     {return RRTypes.BUILDINGDESTROYED;}
    "GovernorBuildingDestroyed"             {return RRTypes.GOVERNORBUILDINGDESTROYED;}
    "CityRiots"                             {return RRTypes.CITYRIOTS;}
    "GovernorCityRiots"                     {return RRTypes.GOVERNORCITYRIOTS;}
    "CityRebels"                            {return RRTypes.CITYREBELS;}
    "GovernorCityRebels"                    {return RRTypes.GOVERNORCITYREBELS;}
    "GovernorThrowGames"                    {return RRTypes.GOVERNORTHROWGAMES;}
    "GovernorThrowRaces"                    {return RRTypes.GOVERNORTHROWRACES;}
    "UngarrisonedSettlement"                {return RRTypes.UNGARRISONEDSETTLEMENT;}
    "EnslavePopulation"                     {return RRTypes.ENSLAVEPOPULATION;}
    "ExterminatePopulation"                 {return RRTypes.EXTERMINATEPOPULATION;}
    "CitySacked"                            {return RRTypes.CITYSACKED;}
    "CharacterSelected"                     {return RRTypes.CHARACTERSELECTED;}
    "SettlementSelected"                    {return RRTypes.SETTLEMENTSELECTED;}
    "EnemySettlementSelected"               {return RRTypes.ENEMYSETTLEMENTSELECTED;}
    "MultiTurnMove"                         {return RRTypes.MULTITURNMOVE;}
    "CharacterPanelOpen"                    {return RRTypes.CHARACTERPANELOPEN;}
    "SettlementPanelOpen"                   {return RRTypes.SETTLEMENTPANELOPEN;}
    "FinancesPanelOpen"                     {return RRTypes.FINANCESPANELOPEN;}
    "FactionSummaryPanelOpen"               {return RRTypes.FACTIONSUMMARYPANELOPEN;}
    "FamilyTreePanelOpen"                   {return RRTypes.FAMILYTREEPANELOPEN;}
    "DiplomaticStandingPanelOpen"           {return RRTypes.DIPLOMATICSTANDINGPANELOPEN;}
    "SenateMissionsPanelOpen"               {return RRTypes.SENATEMISSIONSPANELOPEN;}
    "SenateOfficesPanelOpen"                {return RRTypes.SENATEOFFICESPANELOPEN;}
    "DiplomacyPanelOpen"                    {return RRTypes.DIPLOMACYPANELOPEN;}
    "PreBattlePanelOpen"                    {return RRTypes.PREBATTLEPANELOPEN;}
    "RecruitmentPanelOpen"                  {return RRTypes.RECRUITMENTPANELOPEN;}
    "ConstructionPanelOpen"                 {return RRTypes.CONSTRUCTIONPANELOPEN;}
    "TradePanelOpen"                        {return RRTypes.TRADEPANELOPEN;}
    "HireMercenariesPanelOpen"              {return RRTypes.HIREMERCENARIESPANELOPEN;}
    "NavalAutoResolvePanelOpen"             {return RRTypes.NAVALAUTORESOLVEPANELOPEN;}
    "IncomingMessage"                       {return RRTypes.INCOMINGMESSAGE;}
    "MessageOpen"                           {return RRTypes.MESSAGEOPEN;}
    "RequestBuildingAdvice"                 {return RRTypes.REQUESTBUILDINGADVICE;}
    "RequestTrainingAdvice"                 {return RRTypes.REQUESTTRAININGADVICE;}
    "RequestMercenariesAdvice"              {return RRTypes.REQUESTMERCENARIESADVICE;}
    "ButtonPressed"                         {return RRTypes.BUTTONPRESSED;}
    "ShortcutTriggered"                     {return RRTypes.SHORTCUTTRIGGERED;}
    "ScrollOpened"                          {return RRTypes.SCROLLOPENED;}
    "ScrollClosed"                          {return RRTypes.SCROLLCLOSED;}
    "AdviceSupressed"                       {return RRTypes.ADVICESUPRESSED;}
    "ScrollAdviceRequested"                 {return RRTypes.SCROLLADVICEREQUESTED;}
    "PreBattleScrollAdviceRequested"        {return RRTypes.PREBATTLESCROLLADVICEREQUESTED;}
    "NavalPreBattleScrollAdviceRequested"   {return RRTypes.NAVALPREBATTLESCROLLADVICEREQUESTED;}
    "SettlementScrollAdviceRequested"       {return RRTypes.SETTLEMENTSCROLLADVICEREQUESTED;}
    "Idle"                                  {return RRTypes.IDLE;}
    "AbandonShowMe"                         {return RRTypes.ABANDONSHOWME;}
    "ScriptedAdvice"                        {return RRTypes.SCRIPTEDADVICE;}
    "DeclineAutomatedSettlementManagement"  {return RRTypes.DECLINEAUTOMATEDSETTLEMENTMANAGEMENT;}
    "EscPressed"                            {return RRTypes.ESCPRESSED;}
    "GameReloaded"                          {return RRTypes.GAMERELOADED;}
    "FirstStratUpdates"                     {return RRTypes.FIRSTSTRATUPDATES;}
    "MovieStopped"                          {return RRTypes.MOVIESTOPPED;}
    "SelectionAssistPossible"               {return RRTypes.SELECTIONASSISTPOSSIBLE;}
    "SettlementButtonPressed"               {return RRTypes.SETTLEMENTBUTTONPRESSED;}
    "WorldScriptTerminate"                  {return RRTypes.WORLDSCRIPTTERMINATE;}
    "CampaignHudShown"                      {return RRTypes.CAMPAIGNHUDSHOWN;}
    "ContextPopupInteraction"               {return RRTypes.CONTEXTPOPUPINTERACTION;}
    "DiplomacyConstructingOffer"            {return RRTypes.DIPLOMACYCONSTRUCTINGOFFER;}
    "DiplomacyConstructingCounterOffer"     {return RRTypes.DIPLOMACYCONSTRUCTINGCOUNTEROFFER;}
    "DiplomacyOpponentPresentsOffer"        {return RRTypes.DIPLOMACYOPPONENTPRESENTSOFFER;}
    "DiplomacyOpponentPresentsCounterOffer" {return RRTypes.DIPLOMACYOPPONENTPRESENTSCOUNTEROFFER;}
    "FactionSummary"                        {return RRTypes.FACTIONSUMMARY;}
    "FactionSenate"                         {return RRTypes.FACTIONSENATE;}
    "FactionSenatePolicy"                   {return RRTypes.FACTIONSENATEPOLICY;}
    "FactionSenateMissions"                 {return RRTypes.FACTIONSENATEMISSIONS;}
    "FactionSenateOfficials"                {return RRTypes.FACTIONSENATEOFFICIALS;}
    "FactionSenateFloor"                    {return RRTypes.FACTIONSENATEFLOOR;}
    "FactionFactions"                       {return RRTypes.FACTIONFACTIONS;}
    "FactionDetails"                        {return RRTypes.FACTIONDETAILS;}
    "FactionFamilyTree"                     {return RRTypes.FACTIONFAMILYTREE;}
    "FactionRankings"                       {return RRTypes.FACTIONRANKINGS;}
    "FactionLists"                          {return RRTypes.FACTIONLISTS;}
    "SettlementCharacter"                   {return RRTypes.SETTLEMENTCHARACTER;}
    "SettlementTrade"                       {return RRTypes.SETTLEMENTTRADE;}
    "SettlementOverview"                    {return RRTypes.SETTLEMENTOVERVIEW;}
    "SpySelected"                           {return RRTypes.SPYSELECTED;}
    "DiplomatSelected"                      {return RRTypes.DIPLOMATSELECTED;}
    "AssassinSelected"                      {return RRTypes.ASSASSINSELECTED;}
    "FleetSelected"                         {return RRTypes.FLEETSELECTED;}
    "CampaignMapGesture"                    {return RRTypes.CAMPAIGNMAPGESTURE;}
    "CampaignDoingBadly"                    {return RRTypes.CAMPAIGNDOINGBADLY;}
    "BattleMapGesture"                      {return RRTypes.BATTLEMAPGESTURE;}
    "HideBattleUI"                          {return RRTypes.HIDEBATTLEUI;}
    "FeralNewsVisible"                      {return RRTypes.FERALNEWSVISIBLE;}
    "AgentHubOpened"                        {return RRTypes.AGENTHUBOPENED;}
    "MoveRetinueOpened"                     {return RRTypes.MOVERETINUEOPENED;}
    "OwnFactionDetailsOpened"               {return RRTypes.OWNFACTIONDETAILSOPENED;}
    "DiplomaticStandingShown"               {return RRTypes.DIPLOMATICSTANDINGSHOWN;}
    "FactionFinancesShown"                  {return RRTypes.FACTIONFINANCESSHOWN;}
    "FamilyTreeShown"                       {return RRTypes.FAMILYTREESHOWN;}
    "SendAgentPanel"                        {return RRTypes.SENDAGENTPANEL;}
    "SettlementDetailsShown"                {return RRTypes.SETTLEMENTDETAILSSHOWN;}
    "CharacterInfoScreen"                   {return RRTypes.CHARACTERINFOSCREEN;}
    "FriendlyCharacterSelected"             {return RRTypes.FRIENDLYCHARACTERSELECTED;}
    "EnemyCharacterSelected"                {return RRTypes.ENEMYCHARACTERSELECTED;}
    "FriendlySettlementSelected"            {return RRTypes.FRIENDLYSETTLEMENTSELECTED;}
    "MapOverlayOpened"                      {return RRTypes.MAPOVERLAYOPENED;}
    "SiegeDetailsShown"                     {return RRTypes.SIEGEDETAILSSHOWN;}
    "PreBattleScreen"                       {return RRTypes.PREBATTLESCREEN;}
    "TacticalMapShown"                      {return RRTypes.TACTICALMAPSHOWN;}
    "PostBattleScreen"                      {return RRTypes.POSTBATTLESCREEN;}
    "UnitsGrouped"                          {return RRTypes.UNITSGROUPED;}
    "EnteredBattle"                         {return RRTypes.ENTEREDBATTLE;}
    "AdvisorOpened"                         {return RRTypes.ADVISOROPENED;}
    "FormationTypesShown"                   {return RRTypes.FORMATIONTYPESSHOWN;}
    "MerchantSelected"                      {return RRTypes.MERCHANTSELECTED;}
    "NavalCombatStarted"                    {return RRTypes.NAVALCOMBATSTARTED;}
    "MergeArmiesOpened"                     {return RRTypes.MERGEARMIESOPENED;}
    "RoutesBlockaded"                       {return RRTypes.ROUTESBLOCKADED;}
    "ElectionResults"                       {return RRTypes.ELECTIONRESULTS;}
    "BattleToggleMenu"                      {return RRTypes.BATTLETOGGLEMENU;}
    "AmbushMode"                            {return RRTypes.AMBUSHMODE;}
    "NewsTabClosed"                         {return RRTypes.NEWSTABCLOSED;}
    "NewsTabOpened"                         {return RRTypes.NEWSTABOPENED;}
    "BattleNewsTabOpened"                   {return RRTypes.BATTLENEWSTABOPENED;}
    "QuickListsOpened"                      {return RRTypes.QUICKLISTSOPENED;}
    "EmbargoIsAvailable"                    {return RRTypes.EMBARGOISAVAILABLE;}
    "RebelCharacterSelected"                {return RRTypes.REBELCHARACTERSELECTED;}
    "HighTaxesCauseDisorder"                {return RRTypes.HIGHTAXESCAUSEDISORDER;}
    "FailedToEndTurn"                       {return RRTypes.FAILEDTOENDTURN;}
    "AcquisitionMission"                    {return RRTypes.ACQUISITIONMISSION;}
    "SufferAcquisitionAttempt"              {return RRTypes.SUFFERACQUISITIONATTEMPT;}

    // conditions
    // operators
    ","                                               {return RRTypes.COMMA;}
    "="                                               {return RRTypes.EQUALS;}
    "=="                                              {return RRTypes.EQUALS;}
    "!="                                              {return RRTypes.NOT_EQUALS;}
    ">"                                               {return RRTypes.LARGER;}
    ">="                                              {return RRTypes.LARGER_OR_EQUAL;}
    "<"                                               {return RRTypes.SMALLER;}
    "<="                                              {return RRTypes.SMALLER_OR_EQUAL;}
    // Returns
    "AcquireAncillary"                                {yybegin(EXPORT_DESCR_ANCILLARIES); return RRTypes.ACQUIREANCILLARY;}
    "RemoveAncillary"                                 {yybegin(EXPORT_DESCR_ANCILLARIES); return RRTypes.REMOVEANCILLARY;}
    "Affects"                                         {yybegin(EXPORT_DESCR_TRAITS); return RRTypes.AFFECTS;}
    // conditions
    "I_InBattle"                                      {return RRTypes.I_INBATTLE;}
    "WonBattle"                                       {return RRTypes.WONBATTLE;}
    "I_WonBattle"                                     {return RRTypes.I_WONBATTLE;}
    "Routs"                                           {return RRTypes.ROUTS;}
    "Ally_Routs"                                      {return RRTypes.ALLY_ROUTS;}
    "GeneralHPLostRatioinBattle"                      {return RRTypes.GENERALHPLOSTRATIOINBATTLE;}
    "GeneralNumKillsInBattle"                         {return RRTypes.GENERALNUMKILLSINBATTLE;}
    "GeneralFoughtInCombat"                           {return RRTypes.GENERALFOUGHTINCOMBAT;}
    "PercentageOfArmyKilled"                          {return RRTypes.PERCENTAGEOFARMYKILLED;}
    "I_PercentageOfArmyKilled"                        {return RRTypes.I_PERCENTAGEOFARMYKILLED;}
    "PercentageEnemyKilled"                           {return RRTypes.PERCENTAGEENEMYKILLED;}
    "PercentageBodyguardKilled"                       {return RRTypes.PERCENTAGEBODYGUARDKILLED;}
    "PercentageRoutedOffField"                        {return RRTypes.PERCENTAGEROUTEDOFFFIELD;}
    "NumKilledGenerals"                               {return RRTypes.NUMKILLEDGENERALS;}
    "PercentageUnitCategory"                          {return RRTypes.PERCENTAGEUNITCATEGORY;}
    "NumFriendsInBattle"                              {return RRTypes.NUMFRIENDSINBATTLE;}
    "NumEnemiesInBattle"                              {return RRTypes.NUMENEMIESINBATTLE;}
    "GeneralFoughtFaction"                            {return RRTypes.GENERALFOUGHTFACTION;}
    "GeneralFoughtCulture"                            {return RRTypes.GENERALFOUGHTCULTURE;}
    "I_ConflictType"                                  {return RRTypes.I_CONFLICTTYPE;}
    "IsNightBattle"                                   {return RRTypes.ISNIGHTBATTLE;}
    "BattleSuccess"                                   {return RRTypes.BATTLESUCCESS;}
    "BattleOdds"                                      {return RRTypes.BATTLEODDS;}
    "WasAttacker"                                     {return RRTypes.WASATTACKER;}
    "I_BattleAiAttacking"                             {return RRTypes.I_BATTLEAIATTACKING;}
    "I_BattleAiAttackingSettlement"                   {return RRTypes.I_BATTLEAIATTACKINGSETTLEMENT;}
    "I_BattleAiDefendingSettlement"                   {return RRTypes.I_BATTLEAIDEFENDINGSETTLEMENT;}
    "I_BattleAiDefendingHill"                         {return RRTypes.I_BATTLEAIDEFENDINGHILL;}
    "I_BattleAiDefendingCrossing"                     {return RRTypes.I_BATTLEAIDEFENDINGCROSSING;}
    "I_BattleAiScouting"                              {return RRTypes.I_BATTLEAISCOUTING;}
    "I_BattleIsRiverBattle"                           {return RRTypes.I_BATTLEISRIVERBATTLE;}
    "I_BattleIsSiegeBattle"                           {return RRTypes.I_BATTLEISSIEGEBATTLE;}
    "I_BattleIsSallyOutBattle"                        {return RRTypes.I_BATTLEISSALLYOUTBATTLE;}
    "I_BattleIsFortBattle"                            {return RRTypes.I_BATTLEISFORTBATTLE;}
    "I_BattleAttackerNumSiegeEngines"                 {return RRTypes.I_BATTLEATTACKERNUMSIEGEENGINES;}
    "I_BattleAttackerNumArtilleryCanPenetrateWalls"   {return RRTypes.I_BATTLEATTACKERNUMARTILLERYCANPENETRATEWALLS;}
    "I_BattleDefenderNumNonMissileUnitsOnWalls"       {return RRTypes.I_BATTLEDEFENDERNUMNONMISSILEUNITSONWALLS;}
    "I_BattleDefenderNumMissileUnitsOnWalls"          {return RRTypes.I_BATTLEDEFENDERNUMMISSILEUNITSONWALLS;}
    "I_BattleSettlementWallsBreached"                 {return RRTypes.I_BATTLESETTLEMENTWALLSBREACHED;}
    "I_BattleSettlementGateDestroyed"                 {return RRTypes.I_BATTLESETTLEMENTGATEDESTROYED;}
    "I_BattleSettlementTowerDefence"                  {return RRTypes.I_BATTLESETTLEMENTTOWERDEFENCE;}
    "I_BattleSettlementGateDefence"                   {return RRTypes.I_BATTLESETTLEMENTGATEDEFENCE;}
    "I_BattleSettlementFortificationLevel"            {return RRTypes.I_BATTLESETTLEMENTFORTIFICATIONLEVEL;}
    "BattleBuildingType"                              {return RRTypes.BATTLEBUILDINGTYPE;}
    "I_BattleSettlementGateStrength"                  {return RRTypes.I_BATTLESETTLEMENTGATESTRENGTH;}
    "I_BattleNumberOfRiverCrossings"                  {return RRTypes.I_BATTLENUMBEROFRIVERCROSSINGS;}
    "BattlePlayerUnitClass"                           {return RRTypes.BATTLEPLAYERUNITCLASS;}
    "BattleEnemyUnitClass"                            {return RRTypes.BATTLEENEMYUNITCLASS;}
    "BattlePlayerUnitCategory"                        {return RRTypes.BATTLEPLAYERUNITCATEGORY;}
    "BattleEnemyUnitCategory"                         {return RRTypes.BATTLEENEMYUNITCATEGORY;}
    "BattlePlayerUnitSiegeEngineClass"                {return RRTypes.BATTLEPLAYERUNITSIEGEENGINECLASS;}
    "BattleEnemyUnitSiegeEngineClass"                 {return RRTypes.BATTLEENEMYUNITSIEGEENGINECLASS;}
    "BattlePlayerUnitOnWalls"                         {return RRTypes.BATTLEPLAYERUNITONWALLS;}
    "BattleEnemyUnitOnWalls"                          {return RRTypes.BATTLEENEMYUNITONWALLS;}
    "BattlePlayerCurrentFormation"                    {return RRTypes.BATTLEPLAYERCURRENTFORMATION;}
    "BattleEnemyCurrentFormation"                     {return RRTypes.BATTLEENEMYCURRENTFORMATION;}
    "BattlePlayerUnitCloseFormation"                  {return RRTypes.BATTLEPLAYERUNITCLOSEFORMATION;}
    "BattleEnemyUnitCloseFormation"                   {return RRTypes.BATTLEENEMYUNITCLOSEFORMATION;}
    "BattlePlayerUnitSpecialAbilitySupported"         {return RRTypes.BATTLEPLAYERUNITSPECIALABILITYSUPPORTED;}
    "BattleSelectedPlayerUnitSpecialAbilitySupported" {return RRTypes.BATTLESELECTEDPLAYERUNITSPECIALABILITYSUPPORTED;}
    "BattleEnemyUnitSpecialAbilitySupported"          {return RRTypes.BATTLEENEMYUNITSPECIALABILITYSUPPORTED;}
    "BattlePlayerUnitSpecialAbilityActive"            {return RRTypes.BATTLEPLAYERUNITSPECIALABILITYACTIVE;}
    "BattleEnemyUnitSpecialAbilityActive"             {return RRTypes.BATTLEENEMYUNITSPECIALABILITYACTIVE;}
    "BattlePlayerMountClass"                          {return RRTypes.BATTLEPLAYERMOUNTCLASS;}
    "BattleEnemyMountClass"                           {return RRTypes.BATTLEENEMYMOUNTCLASS;}
    "BattlePlayerUnitMeleeStrength"                   {return RRTypes.BATTLEPLAYERUNITMELEESTRENGTH;}
    "BattleEnemyUnitMeleeStrength"                    {return RRTypes.BATTLEENEMYUNITMELEESTRENGTH;}
    "BattlePlayerUnitMissileStrength"                 {return RRTypes.BATTLEPLAYERUNITMISSILESTRENGTH;}
    "BattleEnemyUnitMissileStrength"                  {return RRTypes.BATTLEENEMYUNITMISSILESTRENGTH;}
    "BattlePlayerUnitSpecialFormation"                {return RRTypes.BATTLEPLAYERUNITSPECIALFORMATION;}
    "BattleEnemyUnitSpecialFormation"                 {return RRTypes.BATTLEENEMYUNITSPECIALFORMATION;}
    "BattlePlayerUnitEngaged"                         {return RRTypes.BATTLEPLAYERUNITENGAGED;}
    "BattleEnemyUnitEngaged"                          {return RRTypes.BATTLEENEMYUNITENGAGED;}
    "BattlePlayerActionStatus"                        {return RRTypes.BATTLEPLAYERACTIONSTATUS;}
    "BattleEnemyActionStatus"                         {return RRTypes.BATTLEENEMYACTIONSTATUS;}
    "BattlePlayerUnitMovingFast"                      {return RRTypes.BATTLEPLAYERUNITMOVINGFAST;}
    "BattleEnemyUnitMovingFast"                       {return RRTypes.BATTLEENEMYUNITMOVINGFAST;}
    "BattleRangeOfAttack"                             {return RRTypes.BATTLERANGEOFATTACK;}
    "BattleDirectionOfAttack"                         {return RRTypes.BATTLEDIRECTIONOFATTACK;}
    "BattleIsMeleeAttack"                             {return RRTypes.BATTLEISMELEEATTACK;}
    "I_BattlePlayerArmyPercentageOfUnitClass"         {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGEOFUNITCLASS;}
    "I_BattleEnemyArmyPercentageOfUnitClass"          {return RRTypes.I_BATTLEENEMYARMYPERCENTAGEOFUNITCLASS;}
    "I_BattlePlayerArmyPercentageOfUnitCategory"      {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGEOFUNITCATEGORY;}
    "I_BattleEnemyArmyPercentageOfUnitCategory"       {return RRTypes.I_BATTLEENEMYARMYPERCENTAGEOFUNITCATEGORY;}
    "I_BattlePlayerArmyPercentageOfMountClass"        {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGEOFMOUNTCLASS;}
    "I_BattleEnemyArmyPercentageOfMountClass"         {return RRTypes.I_BATTLEENEMYARMYPERCENTAGEOFMOUNTCLASS;}
    "I_BattlePlayerArmyPercentageOfClassAndCategory"  {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGEOFCLASSANDCATEGORY;}
    "I_BattleEnemyArmyPercentageOfClassAndCategory"   {return RRTypes.I_BATTLEENEMYARMYPERCENTAGEOFCLASSANDCATEGORY;}
    "I_BattlePlayerArmyPercentageOfSpecialAbility"    {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGEOFSPECIALABILITY;}
    "I_BattleEnemyArmyPercentageOfSpecialAbility"     {return RRTypes.I_BATTLEENEMYARMYPERCENTAGEOFSPECIALABILITY;}
    "I_BattlePlayerArmyPercentageCanHide"             {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGECANHIDE;}
    "I_BattleEnemyArmyPercentageCanHide"              {return RRTypes.I_BATTLEENEMYARMYPERCENTAGECANHIDE;}
    "I_BattlePlayerArmyPercentageCanSwim"             {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGECANSWIM;}
    "I_BattleEnemyArmyPercentageCanSwim"              {return RRTypes.I_BATTLEENEMYARMYPERCENTAGECANSWIM;}
    "I_BattlePlayerArmyIsAttacker"                    {return RRTypes.I_BATTLEPLAYERARMYISATTACKER;}
    "I_BattlePlayerAllianceOddsInFavour"              {return RRTypes.I_BATTLEPLAYERALLIANCEODDSINFAVOUR;}
    "I_BattlePlayerAllianceOddsAgainst"               {return RRTypes.I_BATTLEPLAYERALLIANCEODDSAGAINST;}
    "TotalSiegeWeapons"                               {return RRTypes.TOTALSIEGEWEAPONS;}
    "I_BattleStarted"                                 {return RRTypes.I_BATTLESTARTED;}
    "I_BattleFinished"                                {return RRTypes.I_BATTLEFINISHED;}
    "I_BattleEnd"                                     {return RRTypes.I_BATTLEEND;}
    "I_BattleEndPending"                              {return RRTypes.I_BATTLEENDPENDING;}
    "I_IsUnitMoveFastSet"                             {return RRTypes.I_ISUNITMOVEFASTSET;}
    "I_IsUnitMoving"                                  {return RRTypes.I_ISUNITMOVING;}
    "I_IsUnitIdle"                                    {return RRTypes.I_ISUNITIDLE;}
    "I_IsUnitRouting"                                 {return RRTypes.I_ISUNITROUTING;}
    "I_IsUnitUnderFire"                               {return RRTypes.I_ISUNITUNDERFIRE;}
    "I_IsUnitEngaged"                                 {return RRTypes.I_ISUNITENGAGED;}
    "I_IsUnitEngagedWithUnit"                         {return RRTypes.I_ISUNITENGAGEDWITHUNIT;}
    "I_UnitFormation"                                 {return RRTypes.I_UNITFORMATION;}
    "I_PercentageUnitKilled"                          {return RRTypes.I_PERCENTAGEUNITKILLED;}
    "I_UnitPercentageAmmoLeft"                        {return RRTypes.I_UNITPERCENTAGEAMMOLEFT;}
    "I_UnitDistanceFromPosition"                      {return RRTypes.I_UNITDISTANCEFROMPOSITION;}
    "I_UnitDistanceFromLine"                          {return RRTypes.I_UNITDISTANCEFROMLINE;}
    "I_UnitDistanceFromUnit"                          {return RRTypes.I_UNITDISTANCEFROMUNIT;}
    "I_UnitInRangeOfUnit"                             {return RRTypes.I_UNITINRANGEOFUNIT;}
    "I_UnitDestroyed"                                 {return RRTypes.I_UNITDESTROYED;}
    "I_UnitEnemyUnitInRadius"                         {return RRTypes.I_UNITENEMYUNITINRADIUS;}
    "I_IsUnitGroupMoving"                             {return RRTypes.I_ISUNITGROUPMOVING;}
    "I_IsUnitGroupEngaged"                            {return RRTypes.I_ISUNITGROUPENGAGED;}
    "I_IsUnitGroupIdle"                               {return RRTypes.I_ISUNITGROUPIDLE;}
    "I_IsUnitGroupDestroyed"                          {return RRTypes.I_ISUNITGROUPDESTROYED;}
    "I_PercentageUnitGroupKilled"                     {return RRTypes.I_PERCENTAGEUNITGROUPKILLED;}
    "I_UnitGroupFormation"                            {return RRTypes.I_UNITGROUPFORMATION;}
    "I_UnitGroupDistanceFromPosition"                 {return RRTypes.I_UNITGROUPDISTANCEFROMPOSITION;}
    "I_UnitGroupDistanceFromGroup"                    {return RRTypes.I_UNITGROUPDISTANCEFROMGROUP;}
    "I_UnitGroupInRangeOfUnit"                        {return RRTypes.I_UNITGROUPINRANGEOFUNIT;}
    "I_UnitInRangeOfUnitGroup"                        {return RRTypes.I_UNITINRANGEOFUNITGROUP;}
    "I_UnitGroupInRangeOfUnitGroup"                   {return RRTypes.I_UNITGROUPINRANGEOFUNITGROUP;}
    "I_PlayerInRangeOfUnitGroup"                      {return RRTypes.I_PLAYERINRANGEOFUNITGROUP;}
    "I_PlayerInRangeOfUnit"                           {return RRTypes.I_PLAYERINRANGEOFUNIT;}
    "I_UnitTypeSelected"                              {return RRTypes.I_UNITTYPESELECTED;}
    "UnitType"                                        {return RRTypes.UNITTYPE;}
    "I_UnitSelected"                                  {return RRTypes.I_UNITSELECTED;}
    "I_MultipleUnitsSelected"                         {return RRTypes.I_MULTIPLEUNITSSELECTED;}
    "I_SpecificUnitsSelected"                         {return RRTypes.I_SPECIFICUNITSSELECTED;}
    "I_IsCameraZoomingToUnit"                         {return RRTypes.I_ISCAMERAZOOMINGTOUNIT;}
    "I_BattleEnemyArmyPercentageOfMatchingUnits"      {return RRTypes.I_BATTLEENEMYARMYPERCENTAGEOFMATCHINGUNITS;}
    "I_BattleEnemyArmyNumberOfMatchingUnits"          {return RRTypes.I_BATTLEENEMYARMYNUMBEROFMATCHINGUNITS;}
    "I_BattlePlayerArmyPercentageOfMatchingUnits"     {return RRTypes.I_BATTLEPLAYERARMYPERCENTAGEOFMATCHINGUNITS;}
    "I_BattlePlayerArmyNumberOfMatchingUnits"         {return RRTypes.I_BATTLEPLAYERARMYNUMBEROFMATCHINGUNITS;}
    "LocalPlayerHasManualReinforcements"              {return RRTypes.LOCALPLAYERHASMANUALREINFORCEMENTS;}
    "LocalPlayerHasAIReinforcements"                  {return RRTypes.LOCALPLAYERHASAIREINFORCEMENTS;}
    "Trait"                                           {return RRTypes.TRAIT;}
    "HasAncillary"                                    {return RRTypes.HASANCILLARY;}
    "FatherTrait"                                     {return RRTypes.FATHERTRAIT;}
    "FactionLeaderTrait"                              {return RRTypes.FACTIONLEADERTRAIT;}
    "Attribute"                                       {return RRTypes.ATTRIBUTE;}
    "RemainingMPPercentage"                           {return RRTypes.REMAININGMPPERCENTAGE;}
    "I_RemainingMPPercentage"                         {return RRTypes.I_REMAININGMPPERCENTAGE;}
    "I_CharacterCanMove"                              {return RRTypes.I_CHARACTERCANMOVE;}
    "NoActionThisTurn"                                {return RRTypes.NOACTIONTHISTURN;}
    "AgentType"                                       {return RRTypes.AGENTTYPE;}
    "TrainedAgentType"                                {return RRTypes.TRAINEDAGENTTYPE;}
    "DisasterType"                                    {return RRTypes.DISASTERTYPE;}
    "CultureType"                                     {return RRTypes.CULTURETYPE;}
    "OriginalFactionType"                             {return RRTypes.ORIGINALFACTIONTYPE;}
    "OriginalCultureType"                             {return RRTypes.ORIGINALCULTURETYPE;}
    "IsGeneral"                                       {return RRTypes.ISGENERAL;}
    "IsAdmiral"                                       {return RRTypes.ISADMIRAL;}
    "RemasteredEducation"                             {return RRTypes.REMASTEREDEDUCATION;}
    "EndedInSettlement"                               {return RRTypes.ENDEDINSETTLEMENT;}
    "IsFactionLeader"                                 {return RRTypes.ISFACTIONLEADER;}
    "IsFactionHeir"                                   {return RRTypes.ISFACTIONHEIR;}
    "IsMarried"                                       {return RRTypes.ISMARRIED;}
    "AtSea"                                           {return RRTypes.ATSEA;}
    "InEnemyLands"                                    {return RRTypes.INENEMYLANDS;}
    "InBarbarianLands"                                {return RRTypes.INBARBARIANLANDS;}
    "InUncivilisedLands"                              {return RRTypes.INUNCIVILISEDLANDS;}
    "IsBesieging"                                     {return RRTypes.ISBESIEGING;}
    "IsUnderSiege"                                    {return RRTypes.ISUNDERSIEGE;}
    "I_WithdrawsBeforeBattle"                         {return RRTypes.I_WITHDRAWSBEFOREBATTLE;}
    "EndedInEnemyZOC"                                 {return RRTypes.ENDEDINENEMYZOC;}
    "AdviseAction"                                    {return RRTypes.ADVISEACTION;}
    "I_CharacterTypeNearCharacterType"                {return RRTypes.I_CHARACTERTYPENEARCHARACTERTYPE;}
    "I_CharacterTypeNearTile"                         {return RRTypes.I_CHARACTERTYPENEARTILE;}
    "I_CharacterNameNearTile"                         {return RRTypes.I_CHARACTERNAMENEARTILE;}
    "TradingResource"                                 {return RRTypes.TRADINGRESOURCE;}
    "RegionTradingResource"                           {return RRTypes.REGIONTRADINGRESOURCE;}
    "TradingExotic"                                   {return RRTypes.TRADINGEXOTIC;}
    "DistanceCapital"                                 {return RRTypes.DISTANCECAPITAL;}
    "DistanceHome"                                    {return RRTypes.DISTANCEHOME;}
    "TradingGroup"                                    {return RRTypes.TRADINGGROUP;}
    "RegionMerchantCount"                             {return RRTypes.REGIONMERCHANTCOUNT;}
    "PreciousMineCount"                               {return RRTypes.PRECIOUSMINECOUNT;}
    "IsFromFaction"                                   {return RRTypes.ISFROMFACTION;}
    "NightBattlesEnabled"                             {return RRTypes.NIGHTBATTLESENABLED;}
    "HasOffice"                                       {return RRTypes.HASOFFICE;}
    "FactionType"                                     {return RRTypes.FACTIONTYPE;}
    "TargetFactionType"                               {return RRTypes.TARGETFACTIONTYPE;}
    "FactionCultureType"                              {return RRTypes.FACTIONCULTURETYPE;}
    "TargetFactionCultureType"                        {return RRTypes.TARGETFACTIONCULTURETYPE;}
    "TrainedUnitCategory"                             {return RRTypes.TRAINEDUNITCATEGORY;}
    "UnitCategory"                                    {return RRTypes.UNITCATEGORY;}
    "SenateMissionTimeRemaining"                      {return RRTypes.SENATEMISSIONTIMEREMAINING;}
    "MedianTaxLevel"                                  {return RRTypes.MEDIANTAXLEVEL;}
    "ModeTaxLevel"                                    {return RRTypes.MODETAXLEVEL;}
    "I_ModeTaxLevel"                                  {return RRTypes.I_MODETAXLEVEL;}
    "MissionSuccessLevel"                             {return RRTypes.MISSIONSUCCESSLEVEL;}
    "MissionSucceeded"                                {return RRTypes.MISSIONSUCCEEDED;}
    "MissionFactionTargetType"                        {return RRTypes.MISSIONFACTIONTARGETTYPE;}
    "MissionCultureTargetType"                        {return RRTypes.MISSIONCULTURETARGETTYPE;}
    "DiplomaticStanceFromCharacter"                   {return RRTypes.DIPLOMATICSTANCEFROMCHARACTER;}
    "DiplomaticStanceFromFaction"                     {return RRTypes.DIPLOMATICSTANCEFROMFACTION;}
    "FactionHasAllies"                                {return RRTypes.FACTIONHASALLIES;}
    "LosingMoney"                                     {return RRTypes.LOSINGMONEY;}
    "I_LosingMoney"                                   {return RRTypes.I_LOSINGMONEY;}
    "FactionIsAlive"                                  {return RRTypes.FACTIONISALIVE;}
    "SupportCostsPercentage"                          {return RRTypes.SUPPORTCOSTSPERCENTAGE;}
    "Treasury"                                        {return RRTypes.TREASURY;}
    "OnAWarFooting"                                   {return RRTypes.ONAWARFOOTING;}
    "I_FactionBesieging"                              {return RRTypes.I_FACTIONBESIEGING;}
    "I_FactionBesieged"                               {return RRTypes.I_FACTIONBESIEGED;}
    "I_NumberOfSettlements"                           {return RRTypes.I_NUMBEROFSETTLEMENTS;}
    "I_NumberOfHeirs"                                 {return RRTypes.I_NUMBEROFHEIRS;}
    "I_FactionNearTile"                               {return RRTypes.I_FACTIONNEARTILE;}
    "SettlementsTaken"                                {return RRTypes.SETTLEMENTSTAKEN;}
    "BattlesFought"                                   {return RRTypes.BATTLESFOUGHT;}
    "BattlesWon"                                      {return RRTypes.BATTLESWON;}
    "BattlesLost"                                     {return RRTypes.BATTLESLOST;}
    "DefensiveSiegesFought"                           {return RRTypes.DEFENSIVESIEGESFOUGHT;}
    "DefensiveSiegesWon"                              {return RRTypes.DEFENSIVESIEGESWON;}
    "OffensiveSiegesFought"                           {return RRTypes.OFFENSIVESIEGESFOUGHT;}
    "OffensiveSiegesWon"                              {return RRTypes.OFFENSIVESIEGESWON;}
    "FactionwideAncillaryExists"                      {return RRTypes.FACTIONWIDEANCILLARYEXISTS;}
    "IsAlly"                                          {return RRTypes.ISALLY;}
    "IsProtectorate"                                  {return RRTypes.ISPROTECTORATE;}
    "IsProtector"                                     {return RRTypes.ISPROTECTOR;}
    "IsSameSuperfaction"                              {return RRTypes.ISSAMESUPERFACTION;}
    "LocalPlayerBattlesFought"                        {return RRTypes.LOCALPLAYERBATTLESFOUGHT;}
    "Toggled"                                         {return RRTypes.TOGGLED;}
    "MajorEventActive"                                {return RRTypes.MAJOREVENTACTIVE;}
    "RandomPercent"                                   {return RRTypes.RANDOMPERCENT;}
    "TrueCondition"                                   {return RRTypes.TRUECONDITION;}
    "WorldwideAncillaryExists"                        {return RRTypes.WORLDWIDEANCILLARYEXISTS;}
    "I_IsTutorialEnabled"                             {return RRTypes.I_ISTUTORIALENABLED;}
    "I_IsPlayerTurn"                                  {return RRTypes.I_ISPLAYERTURN;}
    "ConstructionItemClicked"                         {return RRTypes.CONSTRUCTIONITEMCLICKED;}
    "RecruitmentItemClicked"                          {return RRTypes.RECRUITMENTITEMCLICKED;}
    "CharacterName"                                   {return RRTypes.CHARACTERNAME;}
    "ScrollDidOpen"                                   {return RRTypes.SCROLLDIDOPEN;}
    "UnitHasRouted"                                   {return RRTypes.UNITHASROUTED;}
    "BattleUnitActionStatus"                          {return RRTypes.BATTLEUNITACTIONSTATUS;}
    "I_AmountOfUnitInSettlement"                      {return RRTypes.I_AMOUNTOFUNITINSETTLEMENT;}
    "I_UnitCardSelected"                              {return RRTypes.I_UNITCARDSELECTED;}
    "I_CompareCounter"                                {return RRTypes.I_COMPARECOUNTER;}
    "TestFaction"                                     {return RRTypes.TESTFACTION;}
    "LangIs"                                          {return RRTypes.LANGIS;}
    "I_TimerElapsed"                                  {return RRTypes.I_TIMERELAPSED;}
    "I_SoundPlaying"                                  {return RRTypes.I_SOUNDPLAYING;}
    "HasResource"                                     {return RRTypes.HASRESOURCE;}
    "SettlementRevoltingFrom"                         {return RRTypes.SETTLEMENTREVOLTINGFROM;}
    "IsCapital"                                       {return RRTypes.ISCAPITAL;}
    "SettlementName"                                  {return RRTypes.SETTLEMENTNAME;}
    "GovernorBuildingExists"                          {return RRTypes.GOVERNORBUILDINGEXISTS;}
    "SettlementBuildingExists"                        {return RRTypes.SETTLEMENTBUILDINGEXISTS;}
    "HomeSettlementBuildingExists"                    {return RRTypes.HOMESETTLEMENTBUILDINGEXISTS;}
    "SettlementOrderLevel"                            {return RRTypes.SETTLEMENTORDERLEVEL;}
    "SettlementCapabilityLevel"                       {return RRTypes.SETTLEMENTCAPABILITYLEVEL;}
    "BuildingFinishedByGovernor"                      {return RRTypes.BUILDINGFINISHEDBYGOVERNOR;}
    "SettlementBuildingFinished"                      {return RRTypes.SETTLEMENTBUILDINGFINISHED;}
    "GovernorPlugInExists"                            {return RRTypes.GOVERNORPLUGINEXISTS;}
    "GovernorPlugInFinished"                          {return RRTypes.GOVERNORPLUGINFINISHED;}
    "GovernorTaxLevel"                                {return RRTypes.GOVERNORTAXLEVEL;}
    "SettlementTaxLevel"                              {return RRTypes.SETTLEMENTTAXLEVEL;}
    "GovernorInResidence"                             {return RRTypes.GOVERNORINRESIDENCE;}
    "GovernorLoyaltyLevel"                            {return RRTypes.GOVERNORLOYALTYLEVEL;}
    "SettlementLoyaltyLevel"                          {return RRTypes.SETTLEMENTLOYALTYLEVEL;}
    "RiotRisk"                                        {return RRTypes.RIOTRISK;}
    "BuildingQueueIdleDespiteCash"                    {return RRTypes.BUILDINGQUEUEIDLEDESPITECASH;}
    "TrainingQueueIdleDespiteCash"                    {return RRTypes.TRAININGQUEUEIDLEDESPITECASH;}
    "I_SettlementExists"                              {return RRTypes.I_SETTLEMENTEXISTS;}
    "I_SettlementOwner"                               {return RRTypes.I_SETTLEMENTOWNER;}
    "I_SettlementOwnerCulture"                        {return RRTypes.I_SETTLEMENTOWNERCULTURE;}
    "I_SettlementLevel"                               {return RRTypes.I_SETTLEMENTLEVEL;}
    "AdviseFinancialBuild"                            {return RRTypes.ADVISEFINANCIALBUILD;}
    "AdviseBuild"                                     {return RRTypes.ADVISEBUILD;}
    "AdviseRecruit"                                   {return RRTypes.ADVISERECRUIT;}
    "SettlementPopulationMaxedOut"                    {return RRTypes.SETTLEMENTPOPULATIONMAXEDOUT;}
    "SettlementPopulationTooLow"                      {return RRTypes.SETTLEMENTPOPULATIONTOOLOW;}
    "SettlementAutoManaged"                           {return RRTypes.SETTLEMENTAUTOMANAGED;}
    "FeralSettlementAutoManaged"                      {return RRTypes.FERALSETTLEMENTAUTOMANAGED;}
    "PercentageOfPopulationInGarrison"                {return RRTypes.PERCENTAGEOFPOPULATIONINGARRISON;}
    "GarrisonToPopulationRatio"                       {return RRTypes.GARRISONTOPOPULATIONRATIO;}
    "HealthPercentage"                                {return RRTypes.HEALTHPERCENTAGE;}
    "SettlementHasPlague"                             {return RRTypes.SETTLEMENTHASPLAGUE;}
    "IsFortGarrisoned"                                {return RRTypes.ISFORTGARRISONED;}
    "IsSettlementGarrisoned"                          {return RRTypes.ISSETTLEMENTGARRISONED;}
    "IsSettlementRioting"                             {return RRTypes.ISSETTLEMENTRIOTING;}
    "I_NumberUnitsInSettlement"                       {return RRTypes.I_NUMBERUNITSINSETTLEMENT;}
    "I_AdvisorSpeechPlaying"                          {return RRTypes.I_ADVISORSPEECHPLAYING;}
    "CharacterIsLocal"                                {return RRTypes.CHARACTERISLOCAL;}
    "TargetCharacterIsLocal"                          {return RRTypes.TARGETCHARACTERISLOCAL;}
    "SettlementIsLocal"                               {return RRTypes.SETTLEMENTISLOCAL;}
    "TargetSettlementIsLocal"                         {return RRTypes.TARGETSETTLEMENTISLOCAL;}
    "RegionIsLocal"                                   {return RRTypes.REGIONISLOCAL;}
    "TargetRegionIsLocal"                             {return RRTypes.TARGETREGIONISLOCAL;}
    "ArmyIsLocal"                                     {return RRTypes.ARMYISLOCAL;}
    "ArmyIsGarrison"                                  {return RRTypes.ARMYISGARRISON;}
    "TargetArmyIsLocal"                               {return RRTypes.TARGETARMYISLOCAL;}
    "FactionIsLocal"                                  {return RRTypes.FACTIONISLOCAL;}
    "I_LocalFaction"                                  {return RRTypes.I_LOCALFACTION;}
    "TargetFactionIsLocal"                            {return RRTypes.TARGETFACTIONISLOCAL;}
    "I_TurnNumber"                                    {return RRTypes.I_TURNNUMBER;}
    "I_MapName"                                       {return RRTypes.I_MAPNAME;}
    "I_ThreadCount"                                   {return RRTypes.I_THREADCOUNT;}
    "I_IsTriggerTrue"                                 {return RRTypes.I_ISTRIGGERTRUE;}
    "IncomingMessageType"                             {return RRTypes.INCOMINGMESSAGETYPE;}
    "I_AdvisorVerbosity"                              {return RRTypes.I_ADVISORVERBOSITY;}
    "I_AdvisorVisible"                                {return RRTypes.I_ADVISORVISIBLE;}
    "I_CharacterSelected"                             {return RRTypes.I_CHARACTERSELECTED;}
    "I_AgentSelected"                                 {return RRTypes.I_AGENTSELECTED;}
    "I_SettlementSelected"                            {return RRTypes.I_SETTLEMENTSELECTED;}
    "ShortcutTriggered"                               {return RRTypes.SHORTCUTTRIGGERED;}
    "I_AdvancedStatsScrollIsOpen"                     {return RRTypes.I_ADVANCEDSTATSSCROLLISOPEN;}
    "ButtonPressed"                                   {return RRTypes.BUTTONPRESSED;}
    "ScrollOpened"                                    {return RRTypes.SCROLLOPENED;}
    "ScrollClosed"                                    {return RRTypes.SCROLLCLOSED;}
    "ScrollAdviceRequested"                           {return RRTypes.SCROLLADVICEREQUESTED;}
    "I_AnnotationDisplayed"                           {return RRTypes.I_ANNOTATIONDISPLAYED;}
    "FeralUIType"                                     {return RRTypes.FERALUITYPE;}
    "MerchantIsAvailableToBuild"                      {return RRTypes.MERCHANTISAVAILABLETOBUILD;}
    "SettlementHasDamagedBuilding"                    {return RRTypes.SETTLEMENTHASDAMAGEDBUILDING;}
    "LocalPlayerHasReinforcements"                    {return RRTypes.LOCALPLAYERHASREINFORCEMENTS;}
    "SettlementMerchantTradingWith"                   {return RRTypes.SETTLEMENTMERCHANTTRADINGWITH;}
    "SettlementOwnedBy"                               {return RRTypes.SETTLEMENTOWNEDBY;}
    "FactionBuildingExists"                           {return RRTypes.FACTIONBUILDINGEXISTS;}
    // condition params (system constants)
    // conflict types
    "SuccessfulAmbush"                                {return RRTypes.SUCCESSFULAMBUSH;}
    "FailedAmbush"                                    {return RRTypes.FAILEDAMBUSH;}
    "Normal"                                          {return RRTypes.NORMAL;}
    "Siege"                                           {return RRTypes.SIEGE;}
    "SallyBesieger"                                   {return RRTypes.SALLYBESIEGER;}
    "Naval"                                           {return RRTypes.NAVAL;}
    "Withdraw"                                        {return RRTypes.WITHDRAW;}
    // battle success
    "close"                                           {return RRTypes.CLOSE;}
    "average"                                         {return RRTypes.AVERAGE;}
    "clear"                                           {return RRTypes.CLEAR;}
    "crushing"                                        {return RRTypes.CRUSHING;}
    // siege_engine_class
    "tower"                                           {return RRTypes.TOWER;}
    "ram"                                             {return RRTypes.RAM;}
    "ladder"                                          {return RRTypes.LADDER;}
    "sap_point"                                       {return RRTypes.SAP_POINT;}
    "catapult"                                        {return RRTypes.CATAPULT;}
    "trebuchet"                                       {return RRTypes.TREBUCHET;}
    "ballista"                                        {return RRTypes.BALLISTA;}
    "scorpion"                                        {return RRTypes.SCORPION;}
    // tower defence type
    "arrow_tower"                                     {return RRTypes.ARROW_TOWER;}
    "ballista_tower"                                  {return RRTypes.BALLISTA_TOWER;}
    // gate defence type
    "hot_sand"                                        {return RRTypes.HOT_SAND;}
    "burning_oil"                                     {return RRTypes.BURNING_OIL;}
    // battle building type
    "ambient"                                         {return RRTypes.AMBIENT;}
    "major"                                           {return RRTypes.MAJOR;}
    "wall"                                            {return RRTypes.WALL;}
    "tower"                                           {return RRTypes.TOWER;}
    "gate"                                            {return RRTypes.GATE;}
    // special abilities
    "drop_engines"                                    {return RRTypes.DROP_ENGINES;}
    "flaming_ammo"                                    {return RRTypes.FLAMING_AMMO;}
    "chant"                                           {return RRTypes.CHANT;}
    "curse"                                           {return RRTypes.CURSE;}
    "berserk"                                          {return RRTypes.BERSERK;}
    "rally"                                           {return RRTypes.RALLY;}
    "kill_elephants"                                  {return RRTypes.KILL_ELEPHANTS;}
    "move_and_shoot"                                  {return RRTypes.MOVE_AND_SHOOT;}
    "cantabrian_circle"                               {return RRTypes.CANTABRIAN_CIRCLE;}
    "shield_wall"                                     {return RRTypes.SHIELD_WALL;}
    "stealth"                                         {return RRTypes.STEALTH;}
    "feigned_rout"                                    {return RRTypes.FEIGNED_ROUT;}
    "schiltrom"                                       {return RRTypes.SCHILTROM;}
    // action status
    "idling"                                          {return RRTypes.IDLING;}
    "hiding"                                          {return RRTypes.HIDING;}
    "ready"                                           {return RRTypes.READY;}
    "reforming"                                       {return RRTypes.REFORMING;}
    "moving"                                          {return RRTypes.MOVING;}
    "withdrawing"                                     {return RRTypes.WITHDRAWING;}
    "missiles_firing"                                 {return RRTypes.MISSILES_FIRING;}
    "missiles_reloading"                              {return RRTypes.MISSILES_RELOADING;}
    "charging"                                        {return RRTypes.CHARGING;}
    "fighting"                                        {return RRTypes.FIGHTING;}
    "pursuing"                                        {return RRTypes.PURSUING;}
    "routing"                                         {return RRTypes.ROUTING;}
    "fighting_backs_to_the_walls"                     {return RRTypes.FIGHTING_BACKS_TO_THE_WALLS;}
    "running_amok"                                    {return RRTypes.RUNNING_AMOK;}
    "rallying"                                        {return RRTypes.RALLYING;}
    "dead"                                            {return RRTypes.DEAD;}
    "leaving_battle"                                  {return RRTypes.LEAVING_BATTLE;}
    "entering_battle"                                 {return RRTypes.ENTERING_BATTLE;}
    "left_battle"                                     {return RRTypes.LEFT_BATTLE;}
    // attack direction
    "front"                                           {return RRTypes.FRONT;}
    "flank"                                           {return RRTypes.FLANK;}
    "rear"                                            {return RRTypes.REAR;}
    // disaster_type
    "earthquake"                                      {return RRTypes.EARTHQUAKE;}
    "flood"                                           {return RRTypes.FLOOD;}
    "horde"                                           {return RRTypes.HORDE;}
    "fire"                                            {return RRTypes.FIRE;}
    "riot"                                            {return RRTypes.RIOT;}
    "storm"                                           {return RRTypes.STORM;}
    "volcano"                                         {return RRTypes.VOLCANO;}
    // character_action_advice
    "attack_enemy"                                    {return RRTypes.ATTACK_ENEMY;}
    "attack_settlement"                               {return RRTypes.ATTACK_SETTLEMENT;}
    "merge_armies"                                    {return RRTypes.MERGE_ARMIES;}
    "patrol_region"                                   {return RRTypes.PATROL_REGION;}
    "move_to_region"                                  {return RRTypes.MOVE_TO_REGION;}
    "do_nothing"                                      {return RRTypes.DO_NOTHING;}
    // tax_level
    "tax_low"                                         {return RRTypes.TAX_LOW;}
    "tax_normal"                                      {return RRTypes.TAX_NORMAL;}
    "tax_high"                                        {return RRTypes.TAX_HIGH;}
    "tax_extortionate"                                {return RRTypes.TAX_EXTORTIONATE;}
    // success_level
    "not_successful"                                  {return RRTypes.NOT_SUCCESSFUL;}
    "slightly_successful"                             {return RRTypes.SLIGHTLY_SUCCESSFUL;}
    "partly_successful"                               {return RRTypes.PARTLY_SUCCESSFUL;}
    "highly_successful"                               {return RRTypes.HIGHLY_SUCCESSFUL;}
    // diplomatic_stance
    "Allied"                                          {return RRTypes.ALLIED;}
    "Suspicious"                                      {return RRTypes.SUSPICIOUS;}
    "Neutral"                                         {return RRTypes.NEUTRAL;}
    "Hostile"                                         {return RRTypes.HOSTILE;}
    "AtWar"                                           {return RRTypes.ATWAR;}
    // loyalty_to_governor_level
    "loyalty_revolting"                               {return RRTypes.LOYALTY_REVOLTING;}
    "loyalty_rioting"                                 {return RRTypes.LOYALTY_RIOTING;}
    "loyalty_disillusioned"                           {return RRTypes.LOYALTY_DISILLUSIONED;}
    "loyalty_content"                                 {return RRTypes.LOYALTY_CONTENT;}
    "loyalty_happy"                                   {return RRTypes.LOYALTY_HAPPY;}
    // automanage_type
    "Troops"                                          {return RRTypes.TROOPS;}
    "Buildings"                                       {return RRTypes.BUILDINGS;}
    "Mechanics"                                       {return RRTypes.MECHANICS;}

    // Scripts
    // basic scripts stuff
    "script"                                     {return RRTypes.SCRIPT;}
    "end_script"                                 {return RRTypes.END_SCRIPT;}
    "return"                                     {return RRTypes.RETURN;}
    "if"                                         {return RRTypes.IF;}
    "if_not"                                     {return RRTypes.IF_NOT;}
    "end_if"                                     {return RRTypes.END_IF;}
    "while"                                      {return RRTypes.WHILE;}
    "while_not"                                  {return RRTypes.WHILE_NOT;}
    "end_while"                                  {return RRTypes.END_WHILE;}
    "for_each"                                   {return RRTypes.FOR_EACH;}
    "end_for"                                    {return RRTypes.END_FOR;}
    "in"                                         {return RRTypes.IN;}
    "world"                                      {return RRTypes.WORLD;}
    "settlement"                                 {return RRTypes.SETTLEMENT;}
    "character"                                  {return RRTypes.CHARACTER;}
    "unit"                                       {return RRTypes.UNIT;}
    "army"                                       {return RRTypes.ARMY;}
    "faction"                                    {return RRTypes.FACTION;}

    // commands
    "senate_mission_help_player"                 {return RRTypes.SENATE_MISSION_HELP_PLAYER;}
    "senate_mission_assassination"               {return RRTypes.SENATE_MISSION_ASSASSINATION;}
    "senate_mission_cease_hostilities"           {return RRTypes.SENATE_MISSION_CEASE_HOSTILITIES;}
    "senate_mission_declare_war"                 {return RRTypes.SENATE_MISSION_DECLARE_WAR;}
    "senate_mission_broker_peace"                {return RRTypes.SENATE_MISSION_BROKER_PEACE;}
    "senate_mission_take_city"                   {return RRTypes.SENATE_MISSION_TAKE_CITY;}
    "move_to_settlement"                         {return RRTypes.MOVE_TO_SETTLEMENT;}
    "snap_to_settlement"                         {return RRTypes.SNAP_TO_SETTLEMENT;}
    "clear_restrict_strat_movement"              {return RRTypes.CLEAR_RESTRICT_STRAT_MOVEMENT;}
    "restrict_strat_movement"                    {return RRTypes.RESTRICT_STRAT_MOVEMENT;}
    "disable_diplomacy_ui"                       {return RRTypes.DISABLE_DIPLOMACY_UI;}
    "enable_diplomacy_voices"                    {return RRTypes.ENABLE_DIPLOMACY_VOICES;}
    "enable_unit_voices"                         {return RRTypes.ENABLE_UNIT_VOICES;}
    "hide_ui_element"                            {return RRTypes.HIDE_UI_ELEMENT;}
    "show_ui_element"                            {return RRTypes.SHOW_UI_ELEMENT;}
    "enable_ui_card"                             {return RRTypes.ENABLE_UI_CARD;}
    "disable_ui_card"                            {return RRTypes.DISABLE_UI_CARD;}
    "disable_all_ui_cards"                       {return RRTypes.DISABLE_ALL_UI_CARDS;}
    "enable_all_ui_cards"                        {return RRTypes.ENABLE_ALL_UI_CARDS;}
    "disable_agent_hub_all"                      {return RRTypes.DISABLE_AGENT_HUB_ALL;}
    "enable_agent_hub_all"                       {return RRTypes.ENABLE_AGENT_HUB_ALL;}
    "disable_agent_hub"                          {return RRTypes.DISABLE_AGENT_HUB;}
    "enable_agent_hub"                           {return RRTypes.ENABLE_AGENT_HUB;}
    "point_at_agent_hub"                         {return RRTypes.POINT_AT_AGENT_HUB;}
    "set_marriage_allowed"                       {return RRTypes.SET_MARRIAGE_ALLOWED;}
    "trigger_marriage_proposal"                  {return RRTypes.TRIGGER_MARRIAGE_PROPOSAL;}
    "deselect_current_selection"                 {return RRTypes.DESELECT_CURRENT_SELECTION;}
    "force_autoresolve_outcome"                  {return RRTypes.FORCE_AUTORESOLVE_OUTCOME;}
    "force_diplomacy"                            {return RRTypes.FORCE_DIPLOMACY;}
    "create_mercenary_pool"                      {return RRTypes.CREATE_MERCENARY_POOL;}
    "force_agent_succeed"                        {return RRTypes.FORCE_AGENT_SUCCEED;}
    "allow_campaign_battles"                     {return RRTypes.ALLOW_CAMPAIGN_BATTLES;}
    "spawn_character_child"                      {return RRTypes.SPAWN_CHARACTER_CHILD;}
    "stop_point_at_indicator"                    {return RRTypes.STOP_POINT_AT_INDICATOR;}
    "stop_all_point_at_indicators"               {return RRTypes.STOP_ALL_POINT_AT_INDICATORS;}
    "set_battle"                                 {return RRTypes.SET_BATTLE;}
    "restrict_battle_movement"                   {return RRTypes.RESTRICT_BATTLE_MOVEMENT;}
    "clear_restrict_battle_movement"             {return RRTypes.CLEAR_RESTRICT_BATTLE_MOVEMENT;}
    "point_at_diplomacy_offer"                   {return RRTypes.POINT_AT_DIPLOMACY_OFFER;}
    "point_at_move_retinue"                      {return RRTypes.POINT_AT_MOVE_RETINUE;}
    "disable_move_retinue_all"                   {return RRTypes.DISABLE_MOVE_RETINUE_ALL;}
    "enable_move_retinue_all"                    {return RRTypes.ENABLE_MOVE_RETINUE_ALL;}
    "disable_move_retinue"                       {return RRTypes.DISABLE_MOVE_RETINUE;}
    "enable_move_retinue"                        {return RRTypes.ENABLE_MOVE_RETINUE;}
    "block_unit_selection"                       {return RRTypes.BLOCK_UNIT_SELECTION;}
    "forced_gate_success"                        {return RRTypes.FORCED_GATE_SUCCESS;}
    "strat_selection_unblocker"                  {return RRTypes.STRAT_SELECTION_UNBLOCKER;}
    "clear_strat_selection_unblocker"            {return RRTypes.CLEAR_STRAT_SELECTION_UNBLOCKER;}
    "open_stop_tutorial_confirmation_dialog"     {return RRTypes.OPEN_STOP_TUTORIAL_CONFIRMATION_DIALOG;}
    "set_label"                                  {return RRTypes.SET_LABEL;}
    "goto"                                       {return RRTypes.GOTO;}
    "force_deselect_trigger"                     {return RRTypes.FORCE_DESELECT_TRIGGER;}
    "box_drag_selection"                         {return RRTypes.BOX_DRAG_SELECTION;}
    "force_settlement_tab"                       {return RRTypes.FORCE_SETTLEMENT_TAB;}
    "click_drag_move"                            {return RRTypes.CLICK_DRAG_MOVE;}
    "toggle_minimap"                             {return RRTypes.TOGGLE_MINIMAP;}
    "close_news_panel"                           {return RRTypes.CLOSE_NEWS_PANEL;}
    "ui_card_selection_lock"                     {return RRTypes.UI_CARD_SELECTION_LOCK;}
    "disable_specific_shortcut"                  {return RRTypes.DISABLE_SPECIFIC_SHORTCUT;}
    "set_advice_page"                            {return RRTypes.SET_ADVICE_PAGE;}
    "advance_completed_tasks"                    {return RRTypes.ADVANCE_COMPLETED_TASKS;}
    "set_min_formation_width"                    {return RRTypes.SET_MIN_FORMATION_WIDTH;}
    "script_log"                                 {return RRTypes.SCRIPT_LOG;}
    "ai_active_set"                              {return RRTypes.AI_ACTIVE_SET;}
    "release_unit"                               {return RRTypes.RELEASE_UNIT;}
    "hiding_enabled_set"                         {return RRTypes.HIDING_ENABLED_SET;}
    "swimming_enabled_set"                       {return RRTypes.SWIMMING_ENABLED_SET;}
    "pause_battle"                               {return RRTypes.PAUSE_BATTLE;}
    "unit_immediate_place"                       {return RRTypes.UNIT_IMMEDIATE_PLACE;}
    "unit_order_halt"                            {return RRTypes.UNIT_ORDER_HALT;}
    "unit_order_move"                            {return RRTypes.UNIT_ORDER_MOVE;}
    "unit_order_move_to_orientation"             {return RRTypes.UNIT_ORDER_MOVE_TO_ORIENTATION;}
    "unit_order_move_relative"                   {return RRTypes.UNIT_ORDER_MOVE_RELATIVE;}
    "unit_order_attack_unit"                     {return RRTypes.UNIT_ORDER_ATTACK_UNIT;}
    "unit_order_attack_closest_unit"             {return RRTypes.UNIT_ORDER_ATTACK_CLOSEST_UNIT;}
    "unit_order_change_formation"                {return RRTypes.UNIT_ORDER_CHANGE_FORMATION;}
    "unit_order_move_to_missile_range"           {return RRTypes.UNIT_ORDER_MOVE_TO_MISSILE_RANGE;}
    "unit_order_turn"                            {return RRTypes.UNIT_ORDER_TURN;}
    "unit_set_morale"                            {return RRTypes.UNIT_SET_MORALE;}
    "unit_unset_morale"                          {return RRTypes.UNIT_UNSET_MORALE;}
    "unit_set_weapon_upgrade"                    {return RRTypes.UNIT_SET_WEAPON_UPGRADE;}
    "unit_set_armour_upgrade"                    {return RRTypes.UNIT_SET_ARMOUR_UPGRADE;}
    "unit_set_experience"                        {return RRTypes.UNIT_SET_EXPERIENCE;}
    "kill_unit"                                  {return RRTypes.KILL_UNIT;}
    "reduce_unit_strength"                       {return RRTypes.REDUCE_UNIT_STRENGTH;}
    "unit_set_guard_mode"                        {return RRTypes.UNIT_SET_GUARD_MODE;}
    "unit_set_skirmish_mode"                     {return RRTypes.UNIT_SET_SKIRMISH_MODE;}
    "unit_set_fire_at_will_mode"                 {return RRTypes.UNIT_SET_FIRE_AT_WILL_MODE;}
    "unit_set_formation_spacing"                 {return RRTypes.UNIT_SET_FORMATION_SPACING;}
    "unit_taunt"                                 {return RRTypes.UNIT_TAUNT;}
    "unit_use_special_ability"                   {return RRTypes.UNIT_USE_SPECIAL_ABILITY;}
    "unit_group_enable_automation"               {return RRTypes.UNIT_GROUP_ENABLE_AUTOMATION;}
    "unit_group_automate_defend_position"        {return RRTypes.UNIT_GROUP_AUTOMATE_DEFEND_POSITION;}
    "unit_group_automate_attack"                 {return RRTypes.UNIT_GROUP_AUTOMATE_ATTACK;}
    "unit_group_immediate_place"                 {return RRTypes.UNIT_GROUP_IMMEDIATE_PLACE;}
    "unit_group_order_halt"                      {return RRTypes.UNIT_GROUP_ORDER_HALT;}
    "unit_group_order_move_formed"               {return RRTypes.UNIT_GROUP_ORDER_MOVE_FORMED;}
    "unit_group_order_move_unformed"             {return RRTypes.UNIT_GROUP_ORDER_MOVE_UNFORMED;}
    "unit_group_order_relative_move_formed"      {return RRTypes.UNIT_GROUP_ORDER_RELATIVE_MOVE_FORMED;}
    "unit_group_order_relative_move_unformed"    {return RRTypes.UNIT_GROUP_ORDER_RELATIVE_MOVE_UNFORMED;}
    "unit_group_move_to_missile_range_of_unit"   {return RRTypes.UNIT_GROUP_MOVE_TO_MISSILE_RANGE_OF_UNIT;}
    "unit_group_move_to_missile_range_of_group"  {return RRTypes.UNIT_GROUP_MOVE_TO_MISSILE_RANGE_OF_GROUP;}
    "unit_group_order_attack_unit"               {return RRTypes.UNIT_GROUP_ORDER_ATTACK_UNIT;}
    "unit_group_order_attack_group"              {return RRTypes.UNIT_GROUP_ORDER_ATTACK_GROUP;}
    "unit_group_order_change_group_formation"    {return RRTypes.UNIT_GROUP_ORDER_CHANGE_GROUP_FORMATION;}
    "unit_group_order_turn"                      {return RRTypes.UNIT_GROUP_ORDER_TURN;}
    "unit_group_set_morale"                      {return RRTypes.UNIT_GROUP_SET_MORALE;}
    "unit_group_unset_morale"                    {return RRTypes.UNIT_GROUP_UNSET_MORALE;}
    "unit_group_change_unit_formation"           {return RRTypes.UNIT_GROUP_CHANGE_UNIT_FORMATION;}
    "unit_group_set_guard_mode"                  {return RRTypes.UNIT_GROUP_SET_GUARD_MODE;}
    "unit_group_set_skirmish_mode"               {return RRTypes.UNIT_GROUP_SET_SKIRMISH_MODE;}
    "unit_group_set_fire_at_will_mode"           {return RRTypes.UNIT_GROUP_SET_FIRE_AT_WILL_MODE;}
    "unit_group_set_formation_spacing"           {return RRTypes.UNIT_GROUP_SET_FORMATION_SPACING;}
    "force_ai_control"                           {return RRTypes.FORCE_AI_CONTROL;}
    "finish_battle"                              {return RRTypes.FINISH_BATTLE;}
    "move_strat_camera"                          {return RRTypes.MOVE_STRAT_CAMERA;}
    "set_strat_camera_speed"                     {return RRTypes.SET_STRAT_CAMERA_SPEED;}
    "snap_strat_camera"                          {return RRTypes.SNAP_STRAT_CAMERA;}
    "zoom_strat_camera"                          {return RRTypes.ZOOM_STRAT_CAMERA;}
    "camera_restrictions_set"                    {return RRTypes.CAMERA_RESTRICTIONS_SET;}
    "camera_event_cuts_active_set"               {return RRTypes.CAMERA_EVENT_CUTS_ACTIVE_SET;}
    "camera_default_mode_set"                    {return RRTypes.CAMERA_DEFAULT_MODE_SET;}
    "battle_default_camera"                      {return RRTypes.BATTLE_DEFAULT_CAMERA;}
    "battle_general_camera"                      {return RRTypes.BATTLE_GENERAL_CAMERA;}
    "set_camera_bookmark"                        {return RRTypes.SET_CAMERA_BOOKMARK;}
    "use_camera_bookmark"                        {return RRTypes.USE_CAMERA_BOOKMARK;}
    "camera_position_at_bookmark"                {return RRTypes.CAMERA_POSITION_AT_BOOKMARK_;}
    "camera_zoom_to_bookmark"                    {return RRTypes.CAMERA_ZOOM_TO_BOOKMARK;}
    "camera_position"                            {return RRTypes.CAMERA_POSITION;}
    "camera_zoom_to"                             {return RRTypes.CAMERA_ZOOM_TO;}
    "camera_look_at_position"                    {return RRTypes.CAMERA_LOOK_AT_POSITION;}
    "camera_look_at_unit"                        {return RRTypes.CAMERA_LOOK_AT_UNIT;}
    "camera_track_unit"                          {return RRTypes.CAMERA_TRACK_UNIT;}
    "camera_zoom_to_unit"                        {return RRTypes.CAMERA_ZOOM_TO_UNIT;}
    "e_camera_zoom_to_unit"                      {return RRTypes.E_CAMERA_ZOOM_TO_UNIT;}
    "inhibit_camera_input"                       {return RRTypes.INHIBIT_CAMERA_INPUT;}
    "declare_prologue"                           {return RRTypes.DECLARE_PROLOGUE;}
    "terminate_prologue"                         {return RRTypes.TERMINATE_PROLOGUE;}
    "provoke_rebellion"                          {return RRTypes.PROVOKE_REBELLION;}
    "move"                                       {return RRTypes.MOVE;}
    "reposition_character"                       {return RRTypes.REPOSITION_CHARACTER;}
    "replenish_action_points"                    {return RRTypes.REPLENISH_ACTION_POINTS;}
    "replenish_units"                            {return RRTypes.REPLENISH_UNITS;}
    "spawn_character"                            {return RRTypes.SPAWN_CHARACTER;}
    "spawn_army"                                 {return RRTypes.SPAWN_ARMY;}
    "engage_armies"                              {return RRTypes.ENGAGE_ARMIES;}
    "disable_popups"                             {return RRTypes.DISABLE_POPUPS;}
    "start_benchmark"                            {return RRTypes.START_BENCHMARK;}
    "end_benchmark"                              {return RRTypes.END_BENCHMARK;}
    "disable_pause_shortcut_in_campaign"         {return RRTypes.DISABLE_PAUSE_SHORTCUT_IN_CAMPAIGN;}
    "override_superfaction_popularity"           {return RRTypes.OVERRIDE_SUPERFACTION_POPULARITY;}
    "set_faction_senate_standing"                {return RRTypes.SET_FACTION_SENATE_STANDING;}
    "set_faction_people_standing"                {return RRTypes.SET_FACTION_PEOPLE_STANDING;}
    "message_prompt"                             {return RRTypes.MESSAGE_PROMPT;}
    "include_script"                             {return RRTypes.INCLUDE_SCRIPT;}
    "terminate_script"                           {return RRTypes.TERMINATE_SCRIPT;}
    "break"                                      {return RRTypes.BREAK;}
    "spawn_battle"                               {return RRTypes.SPAWN_BATTLE;}
    "end_battle"                                 {return RRTypes.END_BATTLE;}
    "set_ao_visible"                             {return RRTypes.SET_AO_VISIBLE;}
    "set_all_ao_visible"                         {return RRTypes.SET_ALL_AO_VISIBLE;}
    "monitor_conditions"                         {return RRTypes.MONITOR_CONDITIONS;}
    "monitor"                                    {return RRTypes.MONITOR;}
    "monitor_event"                              {return RRTypes.MONITOR_EVENT;}
    "end_monitor"                                {return RRTypes.END_MONITOR;}
    "terminate_monitor"                          {return RRTypes.TERMINATE_MONITOR;}
    "console_command"                            {return RRTypes.CONSOLE_COMMAND;}
    "declare_counter"                            {return RRTypes.DECLARE_COUNTER;}
    "declare_persistent_counter"                 {return RRTypes.DECLARE_PERSISTENT_COUNTER;}
    "inc_counter"                                {return RRTypes.INC_COUNTER;}
    "set_counter"                                {return RRTypes.SET_COUNTER;}
    "counter_operation"                          {return RRTypes.COUNTER_OPERATION;}
    "store_counter"                              {return RRTypes.STORE_COUNTER;}
    "retrieve_counter"                           {return RRTypes.RETRIEVE_COUNTER;}
    "prepare_for_battle"                         {return RRTypes.PREPARE_FOR_BATTLE;}
    "declare_show_me"                            {return RRTypes.DECLARE_SHOW_ME;}
    "label_unit"                                 {return RRTypes.LABEL_UNIT;}
    "label_location"                             {return RRTypes.LABEL_LOCATION;}
    "define_unit_group"                          {return RRTypes.DEFINE_UNIT_GROUP;}
    "undefine_unit_group"                        {return RRTypes.UNDEFINE_UNIT_GROUP;}
    "remove_unit_from_group"                     {return RRTypes.REMOVE_UNIT_FROM_GROUP;}
    "declare_timer"                              {return RRTypes.DECLARE_TIMER;}
    "restart_timer"                              {return RRTypes.RESTART_TIMER;}
    "heed_pause"                                 {return RRTypes.HEED_PAUSE;}
    "wait"                                       {return RRTypes.WAIT;}
    "campaign_wait"                              {return RRTypes.CAMPAIGN_WAIT;}
    "battle_wait"                                {return RRTypes.BATTLE_WAIT;}
    "suspend_during_battle"                      {return RRTypes.SUSPEND_DURING_BATTLE;}
    "set_music_state"                            {return RRTypes.SET_MUSIC_STATE;}
    "release_music_control"                      {return RRTypes.RELEASE_MUSIC_CONTROL;}
    "play_sound_event"                           {return RRTypes.PLAY_SOUND_EVENT;}
    "play_sound_flourish"                        {return RRTypes.PLAY_SOUND_FLOURISH;}
    "stop_sound_event"                           {return RRTypes.STOP_SOUND_EVENT;}
    "point_at_character"                         {return RRTypes.POINT_AT_CHARACTER;}
    "point_at_settlement"                        {return RRTypes.POINT_AT_SETTLEMENT;}
    "e_point_at_settlement"                      {return RRTypes.E_POINT_AT_SETTLEMENT;}
    "point_at_strat_position"                    {return RRTypes.POINT_AT_STRAT_POSITION;}
    "point_at_strat_position_alt"                {return RRTypes.POINT_AT_STRAT_POSITION_ALT;}
    "point_at_message"                           {return RRTypes.POINT_AT_MESSAGE;}
    "ui_flash_start"                             {return RRTypes.UI_FLASH_START;}
    "ui_flash_stop"                              {return RRTypes.UI_FLASH_STOP;}
    "settlement_flash_start"                     {return RRTypes.SETTLEMENT_FLASH_START;}
    "settlement_flash_stop"                      {return RRTypes.SETTLEMENT_FLASH_STOP;}
    "character_flash_start"                      {return RRTypes.CHARACTER_FLASH_START;}
    "character_flash_stop"                       {return RRTypes.CHARACTER_FLASH_STOP;}
    "point_at_location"                          {return RRTypes.POINT_AT_LOCATION;}
    "point_at_unit_pos"                          {return RRTypes.POINT_AT_UNIT_POS;}
    "point_at_unit_group_pos"                    {return RRTypes.POINT_AT_UNIT_GROUP_POS;}
    "remove_battle_map_arrow"                    {return RRTypes.REMOVE_BATTLE_MAP_ARROW;}
    "point_at_card"                              {return RRTypes.POINT_AT_CARD;}
    "point_at_unit_card"                         {return RRTypes.POINT_AT_UNIT_CARD;}
    "e_point_at_unit_card"                       {return RRTypes.E_POINT_AT_UNIT_CARD;}
    "show_mouse_button_animation"                {return RRTypes.SHOW_MOUSE_BUTTON_ANIMATION;}
    "show_movie"                                 {return RRTypes.SHOW_MOVIE;}
    "hide_ui"                                    {return RRTypes.HIDE_UI;}
    "show_ui"                                    {return RRTypes.SHOW_UI;}
    "disable_ui"                                 {return RRTypes.DISABLE_UI;}
    "enable_ui"                                  {return RRTypes.ENABLE_UI;}
    "disable_entire_ui"                          {return RRTypes.DISABLE_ENTIRE_UI;}
    "enable_entire_ui"                           {return RRTypes.ENABLE_ENTIRE_UI;}
    "set_cards_selectable"                       {return RRTypes.SET_CARDS_SELECTABLE;}
    "disable_cursor"                             {return RRTypes.DISABLE_CURSOR;}
    "enable_cursor"                              {return RRTypes.ENABLE_CURSOR;}
    "rename_settlement_in_region"                {return RRTypes.RENAME_SETTLEMENT_IN_REGION;}
    "add_religion"                               {return RRTypes.ADD_RELIGION;}
    "add_hidden_resource"                        {return RRTypes.ADD_HIDDEN_RESOURCE;}
    "remove_hidden_resource"                     {return RRTypes.REMOVE_HIDDEN_RESOURCE;}
    "destroy_building"                           {return RRTypes.DESTROY_BUILDING;}
    "reveal_tile"                                {return RRTypes.REVEAL_TILE;}
    "hide_all_revealed_tiles"                    {return RRTypes.HIDE_ALL_REVEALED_TILES;}
    "play_video"                                 {return RRTypes.PLAY_VIDEO;}
    "advance_advice_thread"                      {return RRTypes.ADVANCE_ADVICE_THREAD;}
    "dismiss_advice"                             {return RRTypes.DISMISS_ADVICE;}
    "dismiss_advisor"                            {return RRTypes.DISMISS_ADVISOR;}
    "suspend_unscripted_advice"                  {return RRTypes.SUSPEND_UNSCRIPTED_ADVICE;}
    "select_character"                           {return RRTypes.SELECT_CHARACTER;}
    "e_select_character"                         {return RRTypes.E_SELECT_CHARACTER;}
    "select_settlement"                          {return RRTypes.SELECT_SETTLEMENT;}
    "e_select_settlement"                        {return RRTypes.E_SELECT_SETTLEMENT;}
    "call_object_shortcut"                       {return RRTypes.CALL_OBJECT_SHORTCUT;}
    "simulate_mouse_click"                       {return RRTypes.SIMULATE_MOUSE_CLICK;}
    "select_ui_element"                          {return RRTypes.SELECT_UI_ELEMENT;}
    "disable_shortcuts"                          {return RRTypes.DISABLE_SHORTCUTS;}
    "filter_unit_commands"                       {return RRTypes.FILTER_UNIT_COMMANDS;}
    "filter_unit_group_commands"                 {return RRTypes.FILTER_UNIT_GROUP_COMMANDS;}
    "filter_unit_selection_commands"             {return RRTypes.FILTER_UNIT_SELECTION_COMMANDS;}
    "filter_settlement_commands"                 {return RRTypes.FILTER_SETTLEMENT_COMMANDS;}
    "filter_character_commands"                  {return RRTypes.FILTER_CHARACTER_COMMANDS;}
    "filter_all_ui_commands"                     {return RRTypes.FILTER_ALL_UI_COMMANDS;}
    "ui_indicator"                               {return RRTypes.UI_INDICATOR;}
    "ui_indicator_remove"                        {return RRTypes.UI_INDICATOR_REMOVE;}
    "steal_esc_key"                              {return RRTypes.STEAL_ESC_KEY;}
    "highlight_recruitment_item"                 {return RRTypes.HIGHLIGHT_RECRUITMENT_ITEM;}
    "highlight_construction_item"                {return RRTypes.HIGHLIGHT_CONSTRUCTION_ITEM;}
    "show_annotations"                           {return RRTypes.SHOW_ANNOTATIONS;}
    "e_select_unit"                              {return RRTypes.E_SELECT_UNIT;}
    "open_siege_scroll"                          {return RRTypes.OPEN_SIEGE_SCROLL;}
    "control_feral_anim"                         {return RRTypes.CONTROL_FERAL_ANIM;}
    "select_captial"                             {return RRTypes.SELECT_CAPTIAL;}
    "show_building_info"                         {return RRTypes.SHOW_BUILDING_INFO;}
    "show_unit_info"                             {return RRTypes.SHOW_UNIT_INFO;}
    "tactical_view_locked"                       {return RRTypes.TACTICAL_VIEW_LOCKED;}
    // console commands
    "kill_character"                             {return RRTypes.KILL_CHARACTER;}
    "give_trait"                                 {return RRTypes.GIVE_TRAIT;}
    "give_ancillary"                             {return RRTypes.GIVE_ANCILLARY;}
    "process_cq"                                 {return RRTypes.PROCESS_CQ;}
    "add_population"                             {return RRTypes.ADD_POPULATION;}
    "capture_settlement"                         {return RRTypes.CAPTURE_SETTLEMENT;}
    "add_money"                                  {return RRTypes.ADD_MONEY;}
    "diplomatic_stance"                          {return RRTypes.DIPLOMATIC_STANCE;}
    "date"                                       {return RRTypes.DATE;}
    "season"                                     {return RRTypes.SEASON;}
    "create_building"                            {return RRTypes.CREATE_BUILDING;}
    "create_unit"                                {return RRTypes.CREATE_UNIT;}
    "destroy_unit"                               {return RRTypes.DESTROY_UNIT;}
    "quit_game"                                  {return RRTypes.QUIT_GAME;}
    "toggle_fow"                                 {return RRTypes.TOGGLE_FOW;}
    "set_fow"                                    {return RRTypes.SET_FOW;}
    "become_protector"                           {return RRTypes.BECOME_PROTECTOR;}
    "invulnerable_general"                       {return RRTypes.INVULNERABLE_GENERAL;}
    "provoke_rebellion"                          {return RRTypes.PROVOKE_REBELLION;}
    "change_character_faction"                   {return RRTypes.CHANGE_CHARACTER_FACTION;}
    "add_income"                                 {return RRTypes.ADD_INCOME;}
    "add_expenditure"                            {return RRTypes.ADD_EXPENDITURE;}
    "disable_ai"                                 {return RRTypes.DISABLE_AI;}
    "surrender_regions"                          {return RRTypes.SURRENDER_REGIONS;}
    "run_ai"                                     {return RRTypes.RUN_AI;}
    "give_everything"                            {return RRTypes.GIVE_EVERYTHING;}
    "control"                                    {return RRTypes.CONTROL;}
    "force_battle_victory"                       {return RRTypes.FORCE_BATTLE_VICTORY;}
    // command params
    "+"                                          {return RRTypes.PLUS;}
    "-"                                          {return RRTypes.DASH;}
    "/"                                          {return RRTypes.SLASH;}
    "*"                                          {return RRTypes.STAR;}
    "regions"                                    {return RRTypes.REGIONS;}
    "cost"                                       {return RRTypes.COST;}
    "replenish"                                  {return RRTypes.REPLENISH;}
    "max"                                        {return RRTypes.MAX;}
    "initial"                                    {return RRTypes.INITIAL;}
    "local_character"                            {return RRTypes.LOCAL_CHARACTER;}
    "local_faction"                              {return RRTypes.LOCAL_FACTION;}
    "local_settlement"                           {return RRTypes.LOCAL_SETTLEMENT;}
    "Ancillary"                                  {return RRTypes.ANCILLARY;}
    "Character"                                  {return RRTypes.CHARACTER_UCF;}
    "scale"                                      {return RRTypes.SCALE;}
    "yes"                                        {return RRTypes.YES;}
    "no"                                         {return RRTypes.NO;}
    "sudo"                                       {return RRTypes.SUDO;}
    "summer"                                     {return RRTypes.SUMMER;}
    "winter"                                     {return RRTypes.WINTER;}
    "spring"                                     {return RRTypes.SPRING;}
    "autumn"                                     {return RRTypes.AUTUMN;}
    "Command"                                    {return RRTypes.COMMAND_UCF;}
    "Influence"                                  {return RRTypes.INFLUENCE_UCF;}
    "Management"                                 {return RRTypes.MANAGEMENT_UCF;}
    "Subterfuge"                                 {return RRTypes.SUBTERFUGE_UCF;}
    "faction"                                    {return RRTypes.FACTION;}
    "sub_faction"                                {return RRTypes.SUB_FACTION;}
    "character"                                  {return RRTypes.CHARACTER;}
    "command"                                    {return RRTypes.COMMAND;}
    "influence"                                  {return RRTypes.INFLUENCE;}
    "management"                                 {return RRTypes.MANAGEMENT;}
    "subterfuge"                                 {return RRTypes.SUBTERFUGE;}
    "soldiers"                                   {return RRTypes.SOLDIERS;}
    "age"                                        {return RRTypes.AGE;}
    "x"                                          {return RRTypes.X;}
    "y"                                          {return RRTypes.Y;}
    "exp"                                        {return RRTypes.EXP;}
    "armour"                                     {return RRTypes.ARMOUR;}
    "weapon_lvl"                                 {return RRTypes.WEAPON_LVL;}
    "end"                                        {return RRTypes.END;}
    "allied"                                     {return RRTypes.ALLIED;}
    "neutral"                                    {return RRTypes.NEUTRAL;}
    "war"                                        {return RRTypes.WAR;}
    "on"                                         {return RRTypes.ON;}
    "label"                                      {return RRTypes.LABEL;}
    "off"                                        {return RRTypes.OFF;}
    "circle"                                     {return RRTypes.CIRCLE;}
    "arrow"                                      {return RRTypes.ARROW;}
    "accept"                                     {return RRTypes.ACCEPT;}
    "reject"                                     {return RRTypes.REJECT;}
    "show"                                       {return RRTypes.SHOW;}
    "hide"                                       {return RRTypes.HIDE;}
    "units"                                      {return RRTypes.UNITS;}
    "characters"                                 {return RRTypes.CHARACTERS_LC;}
    "buildings"                                  {return RRTypes.BUILDINGS;}
    "passengers"                                 {return RRTypes.PASSENGERS;}
    "recruitment"                                {return RRTypes.RECRUITMENT;}
    "mercenaries"                                {return RRTypes.MERCENARIES;}
    "field_construction"                         {return RRTypes.FIELD_CONSTRUCTION;}
    "building"                                   {return RRTypes.BUILDING;}
    "construction"                               {return RRTypes.CONSTRUCTION;}
    "Agent"                                      {return RRTypes.AGENT_UCF;}
    "Mission"                                    {return RRTypes.MISSION_UCF;}
    "up"                                         {return RRTypes.UP;}
    "down"                                       {return RRTypes.DOWN;}
    "back"                                       {return RRTypes.BACK;}
    "left"                                       {return RRTypes.LEFT;}
    "right"                                      {return RRTypes.RIGHT;}
    "top_left"                                   {return RRTypes.TOP_LEFT;}
    "top_right"                                  {return RRTypes.TOP_RIGHT;}
    "bot_left"                                   {return RRTypes.BOT_LEFT;}
    "bot_right"                                  {return RRTypes.BOT_RIGHT;}
    "run"                                        {return RRTypes.RUN;}
    "walk"                                       {return RRTypes.WALK;}
    "relative"                                   {return RRTypes.RELATIVE;}
    "absolute"                                   {return RRTypes.ABSOLUTE;}
    "loose"                                      {return RRTypes.LOOSE;}
    "tight"                                      {return RRTypes.TIGHT;}
    "beserk"                                     {return RRTypes.BESERK;}
    "impetuous"                                  {return RRTypes.IMPETUOUS;}
    "high"                                       {return RRTypes.HIGH;}
    "firm"                                       {return RRTypes.FIRM;}
    "shaken"                                     {return RRTypes.SHAKEN;}
    "wavering"                                   {return RRTypes.WAVERING;}
    "tag"                                        {return RRTypes.TAG;}
    "tw"                                         {return RRTypes.TW;}
    "rts"                                        {return RRTypes.RTS;}
    "user_pref"                                  {return RRTypes.USER_PREF;}
    {ID}         {return RRTypes.ID;}
}

[^]                                 { return TokenType.BAD_CHARACTER;}
