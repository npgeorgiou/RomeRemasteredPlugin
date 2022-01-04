// This is a generated file. Not intended for manual editing.
package rr.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static rr.language.psi.RRTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class RRParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return RRFile(b, l + 1);
  }

  /* ********************************************************** */
  // OSB (KVF_item|KVF_value COMMA)* CSB
  public static boolean KVF_array(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KVF_array")) return false;
    if (!nextTokenIs(b, OSB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OSB);
    r = r && KVF_array_1(b, l + 1);
    r = r && consumeToken(b, CSB);
    exit_section_(b, m, KVF_ARRAY, r);
    return r;
  }

  // (KVF_item|KVF_value COMMA)*
  private static boolean KVF_array_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KVF_array_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!KVF_array_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "KVF_array_1", c)) break;
    }
    return true;
  }

  // KVF_item|KVF_value COMMA
  private static boolean KVF_array_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KVF_array_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = KVF_item(b, l + 1);
    if (!r) r = KVF_array_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KVF_value COMMA
  private static boolean KVF_array_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KVF_array_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = KVF_value(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // KVF_key COLON KVF_value COMMA
  public static boolean KVF_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KVF_item")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = KVF_key(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && KVF_value(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, KVF_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // STRING
  public static boolean KVF_key(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KVF_key")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    exit_section_(b, m, KVF_KEY, r);
    return r;
  }

  /* ********************************************************** */
  // OCB (KVF_key COLON KVF_value COMMA)* CCB
  public static boolean KVF_object(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KVF_object")) return false;
    if (!nextTokenIs(b, OCB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OCB);
    r = r && KVF_object_1(b, l + 1);
    r = r && consumeToken(b, CCB);
    exit_section_(b, m, KVF_OBJECT, r);
    return r;
  }

  // (KVF_key COLON KVF_value COMMA)*
  private static boolean KVF_object_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KVF_object_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!KVF_object_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "KVF_object_1", c)) break;
    }
    return true;
  }

  // KVF_key COLON KVF_value COMMA
  private static boolean KVF_object_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KVF_object_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = KVF_key(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && KVF_value(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // KVF_item|KVF_object|KVF_array|STRING|number|BOOLEAN
  public static boolean KVF_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KVF_value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, KVF_VALUE, "<kvf value>");
    r = KVF_item(b, l + 1);
    if (!r) r = KVF_object(b, l + 1);
    if (!r) r = KVF_array(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = number(b, l + 1);
    if (!r) r = consumeToken(b, BOOLEAN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // descr_strat|
  //           export_descr_unit|
  //           export_descr_buildings|
  //           export_descr_ancillaries|
  //           export_descr_traits|
  //           descr_regions|
  //           descr_mercenaries|
  //           descr_names|
  //           names|
  //           descr_unit_variation|
  //           export_buildings|
  //           descr_cultures|
  //           feral_descr_ai_personality|
  //           descr_faction_groups|
  //           descr_sm_factions|
  //           kv_format|
  // // ############### descr_strat start #################################
  static boolean RRFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RRFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = descr_strat(b, l + 1);
    if (!r) r = export_descr_unit(b, l + 1);
    if (!r) r = export_descr_buildings(b, l + 1);
    if (!r) r = export_descr_ancillaries(b, l + 1);
    if (!r) r = export_descr_traits(b, l + 1);
    if (!r) r = descr_regions(b, l + 1);
    if (!r) r = descr_mercenaries(b, l + 1);
    if (!r) r = descr_names(b, l + 1);
    if (!r) r = names(b, l + 1);
    if (!r) r = descr_unit_variation(b, l + 1);
    if (!r) r = export_buildings(b, l + 1);
    if (!r) r = descr_cultures(b, l + 1);
    if (!r) r = feral_descr_ai_personality(b, l + 1);
    if (!r) r = descr_faction_groups(b, l + 1);
    if (!r) r = descr_sm_factions(b, l + 1);
    if (!r) r = kv_format(b, l + 1);
    if (!r) r = consumeToken(b, RRFILE_16_0);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDLING|HIDING|READY|REFORMING|MOVING|WITHDRAWING|MISSILES_FIRING|MISSILES_RELOADING|CHARGING|FIGHTING|PURSUING|ROUTING|FIGHTING_BACKS_TO_THE_WALLS|RUNNING_AMOK|RALLYING|DEAD|LEAVING_BATTLE|ENTERING_BATTLE|LEFT_BATTLE|
  public static boolean action_status(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action_status")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ACTION_STATUS, "<action status>");
    r = consumeToken(b, IDLING);
    if (!r) r = consumeToken(b, HIDING);
    if (!r) r = consumeToken(b, READY);
    if (!r) r = consumeToken(b, REFORMING);
    if (!r) r = consumeToken(b, MOVING);
    if (!r) r = consumeToken(b, WITHDRAWING);
    if (!r) r = consumeToken(b, MISSILES_FIRING);
    if (!r) r = consumeToken(b, MISSILES_RELOADING);
    if (!r) r = consumeToken(b, CHARGING);
    if (!r) r = consumeToken(b, FIGHTING);
    if (!r) r = consumeToken(b, PURSUING);
    if (!r) r = consumeToken(b, ROUTING);
    if (!r) r = consumeToken(b, FIGHTING_BACKS_TO_THE_WALLS);
    if (!r) r = consumeToken(b, RUNNING_AMOK);
    if (!r) r = consumeToken(b, RALLYING);
    if (!r) r = consumeToken(b, DEAD);
    if (!r) r = consumeToken(b, LEAVING_BATTLE);
    if (!r) r = consumeToken(b, ENTERING_BATTLE);
    if (!r) r = consumeToken(b, LEFT_BATTLE);
    if (!r) r = consumeToken(b, ACTION_STATUS_19_0);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AGENT agent_type INT
  static boolean agent_recruit_capability(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "agent_recruit_capability")) return false;
    if (!nextTokenIs(b, AGENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AGENT);
    r = r && agent_type(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DIPLOMAT|SPY|MERCHANT|ASSASSIN
  public static boolean agent_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "agent_type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, AGENT_TYPE, "<agent type>");
    r = consumeToken(b, DIPLOMAT);
    if (!r) r = consumeToken(b, SPY);
    if (!r) r = consumeToken(b, MERCHANT);
    if (!r) r = consumeToken(b, ASSASSIN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BUILDING_PRIORITY ID
  //                          POPULATION_GROWTH_BONUS INT
  //                          POPULATION_LOYALTY_BONUS INT
  //                          POPULATION_HEALTH_BONUS INT
  //                          TRADE_BASE_INCOME_BONUS INT
  //                          TRADE_LEVEL_BONUS INT
  //                          TRADE_FLEET INT
  //                          TAXABLE_INCOME_BONUS INT
  //                          MINE_RESOURCE_LEVEL INT
  //                          FARMING_LEVEL INT
  //                          ROAD_LEVEL INT
  //                          GATE_STRENGTH INT
  //                          GATE_DEFENSES INT
  //                          WALL_LEVEL INT
  //                          TOWER_LEVEL INT
  //                          STAGE_GAMES INT
  //                          STAGE_RACES INT
  //                          WEAPON_BLADED INT
  //                          WEAPON_MISSILE INT
  //                          ARMOUR INT
  //                          POPULATION_FIRE_RISK_BONUS INT
  //                          BODYGUARD INT
  //                          RECRUITS_MORALE_BONUS INT
  //                          RECRUITS_EXPERIENCE_BONUS INT
  //                          HAPPINESS_BONUS INT
  //                          LAW_BONUS INT
  public static boolean ai_building_priority(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ai_building_priority")) return false;
    if (!nextTokenIs(b, BUILDING_PRIORITY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BUILDING_PRIORITY, ID, POPULATION_GROWTH_BONUS, INT, POPULATION_LOYALTY_BONUS, INT, POPULATION_HEALTH_BONUS, INT, TRADE_BASE_INCOME_BONUS, INT, TRADE_LEVEL_BONUS, INT, TRADE_FLEET, INT, TAXABLE_INCOME_BONUS, INT, MINE_RESOURCE_LEVEL, INT, FARMING_LEVEL, INT, ROAD_LEVEL, INT, GATE_STRENGTH, INT, GATE_DEFENSES, INT, WALL_LEVEL, INT, TOWER_LEVEL, INT, STAGE_GAMES, INT, STAGE_RACES, INT, WEAPON_BLADED, INT, WEAPON_MISSILE, INT, ARMOUR, INT, POPULATION_FIRE_RISK_BONUS, INT, BODYGUARD, INT, RECRUITS_MORALE_BONUS, INT, RECRUITS_EXPERIENCE_BONUS, INT, HAPPINESS_BONUS, INT, LAW_BONUS, INT);
    exit_section_(b, m, AI_BUILDING_PRIORITY, r);
    return r;
  }

  /* ********************************************************** */
  // DIPLOMATIC_PRIORITY ID
  //                            AGGRESIVENESS INT
  public static boolean ai_diplomatic_priority(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ai_diplomatic_priority")) return false;
    if (!nextTokenIs(b, DIPLOMATIC_PRIORITY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DIPLOMATIC_PRIORITY, ID, AGGRESIVENESS, INT);
    exit_section_(b, m, AI_DIPLOMATIC_PRIORITY, r);
    return r;
  }

  /* ********************************************************** */
  // MILITARY_PRIORITY ID
  //                          MASS INT
  //                          INFANTRY_LIGHT INT
  //                          INFANTRY_HEAVY INT
  //                          INFANTRY_MISSILE INT
  //                          INFANTRY_SPEARMEN INT
  //                          CAVALRY_LIGHT INT
  //                          CAVALRY_HEAVY INT
  //                          CAVALRY_MISSILE INT
  //                          SIEGE_ARTILLERY INT
  //                          SPECIAL INT
  //                          SALLY_AGRESSION INT
  //                          SALLY_DESPERATE INT
  //                          ATTACK_RISK_TAKER INT
  //                          SUBTERFUGE_RISK_TAKER INT
  public static boolean ai_military_priority(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ai_military_priority")) return false;
    if (!nextTokenIs(b, MILITARY_PRIORITY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, MILITARY_PRIORITY, ID, MASS, INT, INFANTRY_LIGHT, INT, INFANTRY_HEAVY, INT, INFANTRY_MISSILE, INT, INFANTRY_SPEARMEN, INT, CAVALRY_LIGHT, INT, CAVALRY_HEAVY, INT, CAVALRY_MISSILE, INT, SIEGE_ARTILLERY, INT, SPECIAL, INT, SALLY_AGRESSION, INT, SALLY_DESPERATE, INT, ATTACK_RISK_TAKER, INT, SUBTERFUGE_RISK_TAKER, INT);
    exit_section_(b, m, AI_MILITARY_PRIORITY, r);
    return r;
  }

  /* ********************************************************** */
  // PERSONALITY ai_personality_name_decl
  //                    BUILDING_PRIORITY ID
  //                    MILITARY_PRIORITY ID
  //                    DIPLOMATIC_PRIORITY ID
  public static boolean ai_personality(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ai_personality")) return false;
    if (!nextTokenIs(b, PERSONALITY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PERSONALITY);
    r = r && ai_personality_name_decl(b, l + 1);
    r = r && consumeTokens(b, 0, BUILDING_PRIORITY, ID, MILITARY_PRIORITY, ID, DIPLOMATIC_PRIORITY, ID);
    exit_section_(b, m, AI_PERSONALITY, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean ai_personality_name_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ai_personality_name_decl")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, AI_PERSONALITY_NAME_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean ai_personality_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ai_personality_ref")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, AI_PERSONALITY_REF, r);
    return r;
  }

  /* ********************************************************** */
  // !(condition_alias|building_tree)
  static boolean aliasRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aliasRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !aliasRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // condition_alias|building_tree
  private static boolean aliasRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aliasRecover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = condition_alias(b, l + 1);
    if (!r) r = building_tree(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean alias_c(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "alias_c")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, ALIAS_C, r);
    return r;
  }

  /* ********************************************************** */
  // ANCILLARY ancillary_name_decl
  //                     (HIDDEN)*
  //                     (SHOWSTATS)*
  //                     IMAGE TGA_FILE*
  //                     (UNIQUE)*
  //                     (EXCLUDEDANCILLARIES <<list ancillary_ref>>)*
  //                     (EXCLUDECULTURES list_of_IDs)*
  //                     DESCRIPTION ID
  //                     EFFECTSDESCRIPTION list_of_IDs
  //                     (EFFECT ID INT)*
  //                     (FAKEEFFECT ID INT)*
  public static boolean ancillary_def(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ANCILLARY_DEF, "<ancillary def>");
    r = consumeToken(b, ANCILLARY);
    p = r; // pin = 1
    r = r && report_error_(b, ancillary_name_decl(b, l + 1));
    r = p && report_error_(b, ancillary_def_2(b, l + 1)) && r;
    r = p && report_error_(b, ancillary_def_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, IMAGE)) && r;
    r = p && report_error_(b, ancillary_def_5(b, l + 1)) && r;
    r = p && report_error_(b, ancillary_def_6(b, l + 1)) && r;
    r = p && report_error_(b, ancillary_def_7(b, l + 1)) && r;
    r = p && report_error_(b, ancillary_def_8(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, DESCRIPTION, ID, EFFECTSDESCRIPTION)) && r;
    r = p && report_error_(b, list_of_IDs(b, l + 1)) && r;
    r = p && report_error_(b, ancillary_def_13(b, l + 1)) && r;
    r = p && ancillary_def_14(b, l + 1) && r;
    exit_section_(b, l, m, r, p, RRParser::ancillary_def_recover);
    return r || p;
  }

  // (HIDDEN)*
  private static boolean ancillary_def_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, HIDDEN)) break;
      if (!empty_element_parsed_guard_(b, "ancillary_def_2", c)) break;
    }
    return true;
  }

  // (SHOWSTATS)*
  private static boolean ancillary_def_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SHOWSTATS)) break;
      if (!empty_element_parsed_guard_(b, "ancillary_def_3", c)) break;
    }
    return true;
  }

  // TGA_FILE*
  private static boolean ancillary_def_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, TGA_FILE)) break;
      if (!empty_element_parsed_guard_(b, "ancillary_def_5", c)) break;
    }
    return true;
  }

  // (UNIQUE)*
  private static boolean ancillary_def_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def_6")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, UNIQUE)) break;
      if (!empty_element_parsed_guard_(b, "ancillary_def_6", c)) break;
    }
    return true;
  }

  // (EXCLUDEDANCILLARIES <<list ancillary_ref>>)*
  private static boolean ancillary_def_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def_7")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ancillary_def_7_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ancillary_def_7", c)) break;
    }
    return true;
  }

  // EXCLUDEDANCILLARIES <<list ancillary_ref>>
  private static boolean ancillary_def_7_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def_7_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXCLUDEDANCILLARIES);
    r = r && list(b, l + 1, RRParser::ancillary_ref);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EXCLUDECULTURES list_of_IDs)*
  private static boolean ancillary_def_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def_8")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ancillary_def_8_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ancillary_def_8", c)) break;
    }
    return true;
  }

  // EXCLUDECULTURES list_of_IDs
  private static boolean ancillary_def_8_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def_8_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXCLUDECULTURES);
    r = r && list_of_IDs(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EFFECT ID INT)*
  private static boolean ancillary_def_13(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def_13")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ancillary_def_13_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ancillary_def_13", c)) break;
    }
    return true;
  }

  // EFFECT ID INT
  private static boolean ancillary_def_13_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def_13_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, EFFECT, ID, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // (FAKEEFFECT ID INT)*
  private static boolean ancillary_def_14(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def_14")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ancillary_def_14_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ancillary_def_14", c)) break;
    }
    return true;
  }

  // FAKEEFFECT ID INT
  private static boolean ancillary_def_14_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def_14_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FAKEEFFECT, ID, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !(ANCILLARY|TRIGGER)
  static boolean ancillary_def_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !ancillary_def_recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ANCILLARY|TRIGGER
  private static boolean ancillary_def_recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_def_recover_0")) return false;
    boolean r;
    r = consumeToken(b, ANCILLARY);
    if (!r) r = consumeToken(b, TRIGGER);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean ancillary_name_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_name_decl")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, ANCILLARY_NAME_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean ancillary_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_ref")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, ANCILLARY_REF, r);
    return r;
  }

  /* ********************************************************** */
  // TRIGGER ID
  //                             WHENTOTEST event
  //                             (CONDITION composite_condition)?
  //                             (ACQUIREANCILLARY|REMOVEANCILLARY) ancillary_ref CHANCE_LOWERCASE INT
  public static boolean ancillary_trigger_def(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_trigger_def")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ANCILLARY_TRIGGER_DEF, "<ancillary trigger def>");
    r = consumeTokens(b, 1, TRIGGER, ID, WHENTOTEST);
    p = r; // pin = 1
    r = r && report_error_(b, event(b, l + 1));
    r = p && report_error_(b, ancillary_trigger_def_4(b, l + 1)) && r;
    r = p && report_error_(b, ancillary_trigger_def_5(b, l + 1)) && r;
    r = p && report_error_(b, ancillary_ref(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, CHANCE_LOWERCASE, INT)) && r;
    exit_section_(b, l, m, r, p, RRParser::ancillary_trigger_def_recover);
    return r || p;
  }

  // (CONDITION composite_condition)?
  private static boolean ancillary_trigger_def_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_trigger_def_4")) return false;
    ancillary_trigger_def_4_0(b, l + 1);
    return true;
  }

  // CONDITION composite_condition
  private static boolean ancillary_trigger_def_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_trigger_def_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONDITION);
    r = r && composite_condition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ACQUIREANCILLARY|REMOVEANCILLARY
  private static boolean ancillary_trigger_def_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_trigger_def_5")) return false;
    boolean r;
    r = consumeToken(b, ACQUIREANCILLARY);
    if (!r) r = consumeToken(b, REMOVEANCILLARY);
    return r;
  }

  /* ********************************************************** */
  // !TRIGGER
  static boolean ancillary_trigger_def_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ancillary_trigger_def_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, TRIGGER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AND|AMBERSANDS
  static boolean and_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "and_op")) return false;
    if (!nextTokenIs(b, "", AMBERSANDS, AND)) return false;
    boolean r;
    r = consumeToken(b, AND);
    if (!r) r = consumeToken(b, AMBERSANDS);
    return r;
  }

  /* ********************************************************** */
  // UNIT unit_ref EXP INT ARMOUR INT WEAPON_LVL INT
  public static boolean army_unit_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "army_unit_item")) return false;
    if (!nextTokenIs(b, UNIT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, UNIT);
    r = r && unit_ref(b, l + 1);
    r = r && consumeTokens(b, 0, EXP, INT, ARMOUR, INT, WEAPON_LVL, INT);
    exit_section_(b, m, ARMY_UNIT_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // FRONT|FLANK|REAR
  public static boolean attack_direction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attack_direction")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ATTACK_DIRECTION, "<attack direction>");
    r = consumeToken(b, FRONT);
    if (!r) r = consumeToken(b, FLANK);
    if (!r) r = consumeToken(b, REAR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TROOPS|BUILDINGS|MECHANICS
  public static boolean automanage_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "automanage_type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, AUTOMANAGE_TYPE, "<automanage type>");
    r = consumeToken(b, TROOPS);
    if (!r) r = consumeToken(b, BUILDINGS);
    if (!r) r = consumeToken(b, MECHANICS);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AMBIENT|MAJOR|WALL|TOWER|GATE
  public static boolean battle_building_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "battle_building_type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BATTLE_BUILDING_TYPE, "<battle building type>");
    r = consumeToken(b, AMBIENT);
    if (!r) r = consumeToken(b, MAJOR);
    if (!r) r = consumeToken(b, WALL);
    if (!r) r = consumeToken(b, TOWER);
    if (!r) r = consumeToken(b, GATE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // CLOSE|AVERAGE|CLEAR|CRUSHING
  public static boolean battle_success(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "battle_success")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BATTLE_SUCCESS, "<battle success>");
    r = consumeToken(b, CLOSE);
    if (!r) r = consumeToken(b, AVERAGE);
    if (!r) r = consumeToken(b, CLEAR);
    if (!r) r = consumeToken(b, CRUSHING);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BUILDING_PRESENT building_tree_ref
  public static boolean building_c(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_c")) return false;
    if (!nextTokenIs(b, BUILDING_PRESENT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, BUILDING_C, null);
    r = consumeToken(b, BUILDING_PRESENT);
    p = r; // pin = 1
    r = r && building_tree_ref(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // alias_c|factions_c|major_event_c|resource_c|hidden_resource_c|no_tag_c|building_min_level_c|building_c|is_toggled_c
  public static boolean building_condition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_condition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BUILDING_CONDITION, "<building condition>");
    r = alias_c(b, l + 1);
    if (!r) r = factions_c(b, l + 1);
    if (!r) r = major_event_c(b, l + 1);
    if (!r) r = resource_c(b, l + 1);
    if (!r) r = hidden_resource_c(b, l + 1);
    if (!r) r = no_tag_c(b, l + 1);
    if (!r) r = building_min_level_c(b, l + 1);
    if (!r) r = building_c(b, l + 1);
    if (!r) r = is_toggled_c(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TYPE building_tree_ref building_level_ref
  static boolean building_info(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_info")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, TYPE);
    p = r; // pin = 1
    r = r && report_error_(b, building_tree_ref(b, l + 1));
    r = p && building_level_ref(b, l + 1) && r;
    exit_section_(b, l, m, r, p, RRParser::not_CCB);
    return r || p;
  }

  /* ********************************************************** */
  // BUILDING OCB building_info CCB
  public static boolean building_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_item")) return false;
    if (!nextTokenIs(b, BUILDING)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, BUILDING_ITEM, null);
    r = consumeTokens(b, 2, BUILDING, OCB);
    p = r; // pin = 2
    r = r && report_error_(b, building_info(b, l + 1));
    r = p && consumeToken(b, CCB) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // building_level_name_decl requirement
  //                        OCB
  //                          (AI_DESTRUCTION_HINT requirement)?
  //                          CAPABILITY OCB
  //                            (capability_ requirement?)*
  //                          CCB
  //                          CONSTRUCTION INT
  //                          COST INT
  //                          SETTLEMENT_MIN settlement_level
  //                          UPGRADES OCB
  //                            building_level_ref?
  //                          CCB
  //                        CCB
  public static boolean building_level(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_level")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = building_level_name_decl(b, l + 1);
    r = r && requirement(b, l + 1);
    r = r && consumeToken(b, OCB);
    r = r && building_level_3(b, l + 1);
    r = r && consumeTokens(b, 0, CAPABILITY, OCB);
    r = r && building_level_6(b, l + 1);
    r = r && consumeTokens(b, 0, CCB, CONSTRUCTION, INT, COST, INT, SETTLEMENT_MIN);
    r = r && settlement_level(b, l + 1);
    r = r && consumeTokens(b, 0, UPGRADES, OCB);
    r = r && building_level_16(b, l + 1);
    r = r && consumeTokens(b, 0, CCB, CCB);
    exit_section_(b, m, BUILDING_LEVEL, r);
    return r;
  }

  // (AI_DESTRUCTION_HINT requirement)?
  private static boolean building_level_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_level_3")) return false;
    building_level_3_0(b, l + 1);
    return true;
  }

  // AI_DESTRUCTION_HINT requirement
  private static boolean building_level_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_level_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AI_DESTRUCTION_HINT);
    r = r && requirement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (capability_ requirement?)*
  private static boolean building_level_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_level_6")) return false;
    while (true) {
      int c = current_position_(b);
      if (!building_level_6_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "building_level_6", c)) break;
    }
    return true;
  }

  // capability_ requirement?
  private static boolean building_level_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_level_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = capability_(b, l + 1);
    r = r && building_level_6_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // requirement?
  private static boolean building_level_6_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_level_6_0_1")) return false;
    requirement(b, l + 1);
    return true;
  }

  // building_level_ref?
  private static boolean building_level_16(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_level_16")) return false;
    building_level_ref(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ID
  public static boolean building_level_name_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_level_name_decl")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, BUILDING_LEVEL_NAME_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean building_level_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_level_ref")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, BUILDING_LEVEL_REF, r);
    return r;
  }

  /* ********************************************************** */
  // BUILDING_PRESENT_MIN_LEVEL building_tree_ref building_level_ref
  public static boolean building_min_level_c(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_min_level_c")) return false;
    if (!nextTokenIs(b, BUILDING_PRESENT_MIN_LEVEL)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, BUILDING_MIN_LEVEL_C, null);
    r = consumeToken(b, BUILDING_PRESENT_MIN_LEVEL);
    p = r; // pin = 1
    r = r && report_error_(b, building_tree_ref(b, l + 1));
    r = p && building_level_ref(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // BUILDING building_tree_name_decl OCB
  //                         (TAG ID)?
  //                         ICON ID
  //                         LEVELS building_level_ref+ OCB
  //                           building_level*
  //                         CCB
  //                         PLUGINS OCB CCB
  //                       CCB
  public static boolean building_tree(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_tree")) return false;
    if (!nextTokenIs(b, BUILDING)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, BUILDING_TREE, null);
    r = consumeToken(b, BUILDING);
    p = r; // pin = 1
    r = r && report_error_(b, building_tree_name_decl(b, l + 1));
    r = p && report_error_(b, consumeToken(b, OCB)) && r;
    r = p && report_error_(b, building_tree_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, ICON, ID, LEVELS)) && r;
    r = p && report_error_(b, building_tree_7(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, OCB)) && r;
    r = p && report_error_(b, building_tree_9(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, CCB, PLUGINS, OCB, CCB, CCB)) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (TAG ID)?
  private static boolean building_tree_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_tree_3")) return false;
    building_tree_3_0(b, l + 1);
    return true;
  }

  // TAG ID
  private static boolean building_tree_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_tree_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, TAG, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // building_level_ref+
  private static boolean building_tree_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_tree_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = building_level_ref(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!building_level_ref(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "building_tree_7", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // building_level*
  private static boolean building_tree_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_tree_9")) return false;
    while (true) {
      int c = current_position_(b);
      if (!building_level(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "building_tree_9", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ID
  public static boolean building_tree_name_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_tree_name_decl")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, BUILDING_TREE_NAME_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean building_tree_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "building_tree_ref")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, BUILDING_TREE_REF, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean buried_building_tree_or_level_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "buried_building_tree_or_level_ref")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, BURIED_BUILDING_TREE_OR_LEVEL_REF, r);
    return r;
  }

  /* ********************************************************** */
  // CAMPAIGN ID
  //                      PLAYABLE ID* END
  //                      UNLOCKABLE ID* END
  //                      NONPLAYABLE ID* END
  //                      START_DATE INT season
  //                      END_DATE INT season
  //                      MARIAN_REFORMS_DISABLED?
  //                      (BRIGAND_SPAWN_VALUE INT)?
  //                      (PIRATE_SPAWN_VALUE INT)?
  public static boolean campaign_section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "campaign_section")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CAMPAIGN_SECTION, "<campaign section>");
    r = consumeTokens(b, 0, CAMPAIGN, ID, PLAYABLE);
    r = r && campaign_section_3(b, l + 1);
    r = r && consumeTokens(b, 0, END, UNLOCKABLE);
    r = r && campaign_section_6(b, l + 1);
    r = r && consumeTokens(b, 0, END, NONPLAYABLE);
    r = r && campaign_section_9(b, l + 1);
    r = r && consumeTokens(b, 0, END, START_DATE, INT);
    r = r && season(b, l + 1);
    r = r && consumeTokens(b, 0, END_DATE, INT);
    r = r && season(b, l + 1);
    r = r && campaign_section_17(b, l + 1);
    r = r && campaign_section_18(b, l + 1);
    r = r && campaign_section_19(b, l + 1);
    exit_section_(b, l, m, r, false, RRParser::recover_campaign_section);
    return r;
  }

  // ID*
  private static boolean campaign_section_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "campaign_section_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, ID)) break;
      if (!empty_element_parsed_guard_(b, "campaign_section_3", c)) break;
    }
    return true;
  }

  // ID*
  private static boolean campaign_section_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "campaign_section_6")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, ID)) break;
      if (!empty_element_parsed_guard_(b, "campaign_section_6", c)) break;
    }
    return true;
  }

  // ID*
  private static boolean campaign_section_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "campaign_section_9")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, ID)) break;
      if (!empty_element_parsed_guard_(b, "campaign_section_9", c)) break;
    }
    return true;
  }

  // MARIAN_REFORMS_DISABLED?
  private static boolean campaign_section_17(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "campaign_section_17")) return false;
    consumeToken(b, MARIAN_REFORMS_DISABLED);
    return true;
  }

  // (BRIGAND_SPAWN_VALUE INT)?
  private static boolean campaign_section_18(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "campaign_section_18")) return false;
    campaign_section_18_0(b, l + 1);
    return true;
  }

  // BRIGAND_SPAWN_VALUE INT
  private static boolean campaign_section_18_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "campaign_section_18_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BRIGAND_SPAWN_VALUE, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PIRATE_SPAWN_VALUE INT)?
  private static boolean campaign_section_19(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "campaign_section_19")) return false;
    campaign_section_19_0(b, l + 1);
    return true;
  }

  // PIRATE_SPAWN_VALUE INT
  private static boolean campaign_section_19_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "campaign_section_19_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PIRATE_SPAWN_VALUE, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (unit_recruit_capability|agent_recruit_capability|effect_capability) requirement?
  public static boolean capability_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "capability_")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CAPABILITY_, "<capability>");
    r = capability__0(b, l + 1);
    r = r && capability__1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // unit_recruit_capability|agent_recruit_capability|effect_capability
  private static boolean capability__0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "capability__0")) return false;
    boolean r;
    r = unit_recruit_capability(b, l + 1);
    if (!r) r = agent_recruit_capability(b, l + 1);
    if (!r) r = effect_capability(b, l + 1);
    return r;
  }

  // requirement?
  private static boolean capability__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "capability__1")) return false;
    requirement(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ATTACK_ENEMY|ATTACK_SETTLEMENT|MERGE_ARMIES|PATROL_REGION|MOVE_TO_REGION|DO_NOTHING
  public static boolean character_action_advice(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_action_advice")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CHARACTER_ACTION_ADVICE, "<character action advice>");
    r = consumeToken(b, ATTACK_ENEMY);
    if (!r) r = consumeToken(b, ATTACK_SETTLEMENT);
    if (!r) r = consumeToken(b, MERGE_ARMIES);
    if (!r) r = consumeToken(b, PATROL_REGION);
    if (!r) r = consumeToken(b, MOVE_TO_REGION);
    if (!r) r = consumeToken(b, DO_NOTHING);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // CHARACTER (SUB_FACTION ID COMMA)? name_ COMMA character_type COMMA ((LEADER|HEIR) COMMA)? AGE INT COMMA COMMA coords_with_xy (COMMA PORTRAIT_INDEX INT)?
  //                    (TRAITS list_of_trait_items)?
  //                    (ANCILLARIES <<list ancillary_ref>>)?
  //                    (ARMY army_unit_item*)?
  public static boolean character_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item")) return false;
    if (!nextTokenIs(b, CHARACTER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CHARACTER);
    r = r && character_item_1(b, l + 1);
    r = r && name_(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && character_type(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && character_item_6(b, l + 1);
    r = r && consumeTokens(b, 0, AGE, INT, COMMA, COMMA);
    r = r && coords_with_xy(b, l + 1);
    r = r && character_item_12(b, l + 1);
    r = r && character_item_13(b, l + 1);
    r = r && character_item_14(b, l + 1);
    r = r && character_item_15(b, l + 1);
    exit_section_(b, m, CHARACTER_ITEM, r);
    return r;
  }

  // (SUB_FACTION ID COMMA)?
  private static boolean character_item_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item_1")) return false;
    character_item_1_0(b, l + 1);
    return true;
  }

  // SUB_FACTION ID COMMA
  private static boolean character_item_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SUB_FACTION, ID, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((LEADER|HEIR) COMMA)?
  private static boolean character_item_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item_6")) return false;
    character_item_6_0(b, l + 1);
    return true;
  }

  // (LEADER|HEIR) COMMA
  private static boolean character_item_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = character_item_6_0_0(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // LEADER|HEIR
  private static boolean character_item_6_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item_6_0_0")) return false;
    boolean r;
    r = consumeToken(b, LEADER);
    if (!r) r = consumeToken(b, HEIR);
    return r;
  }

  // (COMMA PORTRAIT_INDEX INT)?
  private static boolean character_item_12(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item_12")) return false;
    character_item_12_0(b, l + 1);
    return true;
  }

  // COMMA PORTRAIT_INDEX INT
  private static boolean character_item_12_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item_12_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, PORTRAIT_INDEX, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // (TRAITS list_of_trait_items)?
  private static boolean character_item_13(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item_13")) return false;
    character_item_13_0(b, l + 1);
    return true;
  }

  // TRAITS list_of_trait_items
  private static boolean character_item_13_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item_13_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TRAITS);
    r = r && list_of_trait_items(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ANCILLARIES <<list ancillary_ref>>)?
  private static boolean character_item_14(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item_14")) return false;
    character_item_14_0(b, l + 1);
    return true;
  }

  // ANCILLARIES <<list ancillary_ref>>
  private static boolean character_item_14_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item_14_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ANCILLARIES);
    r = r && list(b, l + 1, RRParser::ancillary_ref);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ARMY army_unit_item*)?
  private static boolean character_item_15(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item_15")) return false;
    character_item_15_0(b, l + 1);
    return true;
  }

  // ARMY army_unit_item*
  private static boolean character_item_15_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item_15_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ARMY);
    r = r && character_item_15_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // army_unit_item*
  private static boolean character_item_15_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_item_15_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!army_unit_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "character_item_15_0_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // CHARACTER_RECORD name_ COMMA
  //                      sex COMMA
  //                      COMMAND INT COMMA
  //                      INFLUENCE INT COMMA
  //                      MANAGEMENT INT COMMA
  //                      SUBTERFUGE INT COMMA
  //                      AGE INT COMMA
  //                      ALIVE COMMA
  //                      NEVER_A_LEADER
  public static boolean character_record_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_record_item")) return false;
    if (!nextTokenIs(b, CHARACTER_RECORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CHARACTER_RECORD);
    r = r && name_(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && sex(b, l + 1);
    r = r && consumeTokens(b, 0, COMMA, COMMAND, INT, COMMA, INFLUENCE, INT, COMMA, MANAGEMENT, INT, COMMA, SUBTERFUGE, INT, COMMA, AGE, INT, COMMA, ALIVE, COMMA, NEVER_A_LEADER);
    exit_section_(b, m, CHARACTER_RECORD_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // ALL|FAMILY|NAMED_CHARACTER|GENERAL|ADMIRAL|agent_type
  public static boolean character_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character_type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CHARACTER_TYPE, "<character type>");
    r = consumeToken(b, ALL);
    if (!r) r = consumeToken(b, FAMILY);
    if (!r) r = consumeToken(b, NAMED_CHARACTER);
    if (!r) r = consumeToken(b, GENERAL);
    if (!r) r = consumeToken(b, ADMIRAL);
    if (!r) r = agent_type(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // EQUALS|LARGER|LARGER_OR_EQUAL|SMALLER|SMALLER_OR_EQUAL|
  static boolean comparison_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comparison_op")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS);
    if (!r) r = consumeToken(b, LARGER);
    if (!r) r = consumeToken(b, LARGER_OR_EQUAL);
    if (!r) r = consumeToken(b, SMALLER);
    if (!r) r = consumeToken(b, SMALLER_OR_EQUAL);
    if (!r) r = consumeToken(b, COMPARISON_OP_5_0);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // maybe_negated_c ((AND|OR) maybe_negated_c)*
  static boolean composite_c(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "composite_c")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = maybe_negated_c(b, l + 1);
    p = r; // pin = 1
    r = r && composite_c_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ((AND|OR) maybe_negated_c)*
  private static boolean composite_c_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "composite_c_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!composite_c_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "composite_c_1", c)) break;
    }
    return true;
  }

  // (AND|OR) maybe_negated_c
  private static boolean composite_c_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "composite_c_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = composite_c_1_0_0(b, l + 1);
    r = r && maybe_negated_c(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // AND|OR
  private static boolean composite_c_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "composite_c_1_0_0")) return false;
    boolean r;
    r = consumeToken(b, AND);
    if (!r) r = consumeToken(b, OR);
    return r;
  }

  /* ********************************************************** */
  // maybe_negated_condition (and_op maybe_negated_condition)*
  public static boolean composite_condition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "composite_condition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMPOSITE_CONDITION, "<composite condition>");
    r = maybe_negated_condition(b, l + 1);
    r = r && composite_condition_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (and_op maybe_negated_condition)*
  private static boolean composite_condition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "composite_condition_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!composite_condition_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "composite_condition_1", c)) break;
    }
    return true;
  }

  // and_op maybe_negated_condition
  private static boolean composite_condition_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "composite_condition_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = and_op(b, l + 1);
    r = r && maybe_negated_condition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // I_INBATTLE|
  //                 WONBATTLE|
  //                 I_WONBATTLE ID|
  //                 ROUTS|
  //                 ALLY_ROUTS|
  //                 GENERALHPLOSTRATIOINBATTLE comparison_op number|
  //                 GENERALNUMKILLSINBATTLE comparison_op number|
  //                 GENERALFOUGHTINCOMBAT|
  //                 PERCENTAGEOFARMYKILLED comparison_op number|
  //                 I_PERCENTAGEOFARMYKILLED INT INT comparison_op INT|
  //                 PERCENTAGEENEMYKILLED comparison_op number|
  //                 PERCENTAGEBODYGUARDKILLED comparison_op number|
  //                 PERCENTAGEROUTEDOFFFIELD comparison_op number|
  //                 NUMKILLEDGENERALS comparison_op number|
  //                 PERCENTAGEUNITCATEGORY unit_category comparison_op number|
  //                 NUMFRIENDSINBATTLE comparison_op number|
  //                 NUMENEMIESINBATTLE comparison_op number|
  //                 GENERALFOUGHTFACTION ID|
  //                 GENERALFOUGHTCULTURE ID|
  //                 I_CONFLICTTYPE conflict_type|
  //                 ISNIGHTBATTLE|
  //                 BATTLESUCCESS comparison_op battle_success|
  //                 BATTLEODDS comparison_op number|
  //                 WASATTACKER|
  //                 I_BATTLEAIATTACKING|
  //                 I_BATTLEAIATTACKINGSETTLEMENT|
  //                 I_BATTLEAIDEFENDINGSETTLEMENT|
  //                 I_BATTLEAIDEFENDINGHILL|
  //                 I_BATTLEAIDEFENDINGCROSSING|
  //                 I_BATTLEAISCOUTING|
  //                 I_BATTLEISRIVERBATTLE|
  //                 I_BATTLEISSIEGEBATTLE|
  //                 I_BATTLEISSALLYOUTBATTLE|
  //                 I_BATTLEISFORTBATTLE|
  //                 I_BATTLEATTACKERNUMSIEGEENGINES siege_engine_class|
  //                 I_BATTLEATTACKERNUMARTILLERYCANPENETRATEWALLS comparison_op INT|
  //                 I_BATTLEDEFENDERNUMNONMISSILEUNITSONWALLS comparison_op INT|
  //                 I_BATTLEDEFENDERNUMMISSILEUNITSONWALLS comparison_op INT|
  //                 I_BATTLESETTLEMENTWALLSBREACHED|
  //                 I_BATTLESETTLEMENTGATEDESTROYED|
  //                 I_BATTLESETTLEMENTTOWERDEFENCE tower_defence_type|
  //                 I_BATTLESETTLEMENTGATEDEFENCE gate_defence_type|
  //                 I_BATTLESETTLEMENTFORTIFICATIONLEVEL comparison_op INT|
  //                 BATTLEBUILDINGTYPE EQUALS battle_building_type|
  //                 I_BATTLESETTLEMENTGATESTRENGTH comparison_op INT|
  //                 I_BATTLENUMBEROFRIVERCROSSINGS comparison_op INT|
  //                 BATTLEPLAYERUNITCLASS EQUALS unit_class|
  //                 BATTLEENEMYUNITCLASS EQUALS unit_class|
  //                 BATTLEPLAYERUNITCATEGORY EQUALS unit_category|
  //                 BATTLEENEMYUNITCATEGORY EQUALS unit_category|
  //                 BATTLEPLAYERUNITSIEGEENGINECLASS EQUALS siege_engine_class|
  //                 BATTLEENEMYUNITSIEGEENGINECLASS EQUALS siege_engine_class|
  //                 BATTLEPLAYERUNITONWALLS|
  //                 BATTLEENEMYUNITONWALLS|
  //                 BATTLEPLAYERCURRENTFORMATION EQUALS unit_formation|
  //                 BATTLEENEMYCURRENTFORMATION EQUALS unit_formation|
  //                 BATTLEPLAYERUNITCLOSEFORMATION|
  //                 BATTLEENEMYUNITCLOSEFORMATION|
  //                 BATTLEPLAYERUNITSPECIALABILITYSUPPORTED comparison_op special_ability|
  //                 BATTLESELECTEDPLAYERUNITSPECIALABILITYSUPPORTED comparison_op special_ability|
  //                 BATTLEENEMYUNITSPECIALABILITYSUPPORTED comparison_op special_ability|
  //                 BATTLEPLAYERUNITSPECIALABILITYACTIVE|
  //                 BATTLEENEMYUNITSPECIALABILITYACTIVE|
  //                 BATTLEPLAYERMOUNTCLASS comparison_op mount_class|
  //                 BATTLEENEMYMOUNTCLASS comparison_op mount_class|
  //                 BATTLEPLAYERUNITMELEESTRENGTH comparison_op number|
  //                 BATTLEENEMYUNITMELEESTRENGTH comparison_op number|
  //                 BATTLEPLAYERUNITMISSILESTRENGTH comparison_op number|
  //                 BATTLEENEMYUNITMISSILESTRENGTH comparison_op number|
  //                 BATTLEPLAYERUNITSPECIALFORMATION EQUALS unit_formation|
  //                 BATTLEENEMYUNITSPECIALFORMATION EQUALS unit_formation|
  //                 BATTLEPLAYERUNITENGAGED|
  //                 BATTLEENEMYUNITENGAGED|
  //                 BATTLEPLAYERACTIONSTATUS EQUALS action_status|
  //                 BATTLEENEMYACTIONSTATUS EQUALS action_status|
  //                 BATTLEPLAYERUNITMOVINGFAST|
  //                 BATTLEENEMYUNITMOVINGFAST|
  //                 BATTLERANGEOFATTACK comparison_op number|
  //                 BATTLEDIRECTIONOFATTACK EQUALS attack_direction|
  //                 BATTLEISMELEEATTACK|
  //                 I_BATTLEPLAYERARMYPERCENTAGEOFUNITCLASS unit_class comparison_op number|
  //                 I_BATTLEENEMYARMYPERCENTAGEOFUNITCLASS unit_class comparison_op number|
  //                 I_BATTLEPLAYERARMYPERCENTAGEOFUNITCATEGORY unit_category comparison_op number|
  //                 I_BATTLEENEMYARMYPERCENTAGEOFUNITCATEGORY unit_category comparison_op number|
  //                 I_BATTLEPLAYERARMYPERCENTAGEOFMOUNTCLASS mount_class comparison_op number|
  //                 I_BATTLEENEMYARMYPERCENTAGEOFMOUNTCLASS mount_class comparison_op number|
  //                 I_BATTLEPLAYERARMYPERCENTAGEOFCLASSANDCATEGORY unit_class unit_category comparison_op number|
  //                 I_BATTLEENEMYARMYPERCENTAGEOFCLASSANDCATEGORY unit_class unit_category comparison_op number|
  //                 I_BATTLEPLAYERARMYPERCENTAGEOFSPECIALABILITY special_ability comparison_op number|
  //                 I_BATTLEENEMYARMYPERCENTAGEOFSPECIALABILITY special_ability comparison_op number|
  //                 I_BATTLEPLAYERARMYPERCENTAGECANHIDE hide_type comparison_op number|
  //                 I_BATTLEENEMYARMYPERCENTAGECANHIDE hide_type comparison_op number|
  //                 I_BATTLEPLAYERARMYPERCENTAGECANSWIM comparison_op number|
  //                 I_BATTLEENEMYARMYPERCENTAGECANSWIM comparison_op number|
  //                 I_BATTLEPLAYERARMYISATTACKER|
  //                 I_BATTLEPLAYERALLIANCEODDSINFAVOUR comparison_op INT|
  //                 I_BATTLEPLAYERALLIANCEODDSAGAINST comparison_op INT|
  //                 TOTALSIEGEWEAPONS comparison_op INT|
  //                 I_BATTLESTARTED|
  //                 I_BATTLEFINISHED|
  //                 I_BATTLEEND|
  //                 I_BATTLEENDPENDING|
  //                 I_ISUNITMOVEFASTSET ID|
  //                 I_ISUNITMOVING ID|
  //                 I_ISUNITIDLE ID|
  //                 I_ISUNITROUTING ID|
  //                 I_ISUNITUNDERFIRE ID|
  //                 I_ISUNITENGAGED ID|
  //                 I_ISUNITENGAGEDWITHUNIT ID ID|
  //                 I_UNITFORMATION ID EQUALS unit_formation|
  //                 I_PERCENTAGEUNITKILLED ID comparison_op INT|
  //                 I_UNITPERCENTAGEAMMOLEFT ID comparison_op INT|
  //                 I_UNITDISTANCEFROMPOSITION ID INT INT comparison_op INT|
  //                 I_UNITDISTANCEFROMLINE ID ID ID comparison_op INT|
  //                 I_UNITDISTANCEFROMUNIT ID ID comparison_op INT|
  //                 I_UNITINRANGEOFUNIT ID ID|
  //                 I_UNITDESTROYED ID|
  //                 I_UNITENEMYUNITINRADIUS ID INT|
  //                 I_ISUNITGROUPMOVING ID|
  //                 I_ISUNITGROUPENGAGED ID|
  //                 I_ISUNITGROUPIDLE ID|
  //                 I_ISUNITGROUPDESTROYED ID|
  //                 I_PERCENTAGEUNITGROUPKILLED ID comparison_op INT|
  //                 I_UNITGROUPFORMATION ID EQUALS ID|
  //                 I_UNITGROUPDISTANCEFROMPOSITION ID INT comparison_op INT|
  //                 I_UNITGROUPDISTANCEFROMGROUP ID ID comparison_op INT|
  //                 I_UNITGROUPINRANGEOFUNIT ID ID|
  //                 I_UNITINRANGEOFUNITGROUP ID ID|
  //                 I_UNITGROUPINRANGEOFUNITGROUP ID ID|
  //                 I_PLAYERINRANGEOFUNITGROUP ID|
  //                 I_PLAYERINRANGEOFUNIT ID|
  //                 I_UNITTYPESELECTED ID|
  //                 UNITTYPE ID|
  //                 I_UNITSELECTED ID|
  //                 I_MULTIPLEUNITSSELECTED|
  //                 I_SPECIFICUNITSSELECTED ID*|
  //                 I_ISCAMERAZOOMINGTOUNIT ID|
  //                 I_BATTLEENEMYARMYPERCENTAGEOFMATCHINGUNITS ID comparison_op number|
  //                 I_BATTLEENEMYARMYNUMBEROFMATCHINGUNITS ID comparison_op INT|
  //                 I_BATTLEPLAYERARMYPERCENTAGEOFMATCHINGUNITS ID comparison_op number|
  //                 I_BATTLEPLAYERARMYNUMBEROFMATCHINGUNITS ID comparison_op INT|
  //                 LOCALPLAYERHASMANUALREINFORCEMENTS|
  //                 LOCALPLAYERHASAIREINFORCEMENTS|
  //                 TRAIT ID comparison_op INT|
  //                 HASANCILLARY ID|
  //                 FATHERTRAIT ID comparison_op INT|
  //                 FACTIONLEADERTRAIT ID comparison_op INT|
  //                 ATTRIBUTE ID comparison_op INT|
  //                 REMAININGMPPERCENTAGE comparison_op number|
  //                 I_REMAININGMPPERCENTAGE name_ comparison_op number|
  //                 I_CHARACTERCANMOVE name_|
  //                 NOACTIONTHISTURN|
  //                 AGENTTYPE EQUALS character_type|
  //                 TRAINEDAGENTTYPE EQUALS character_type|
  //                 DISASTERTYPE disaster_type|
  //                 CULTURETYPE ID|
  //                 ORIGINALFACTIONTYPE ID|
  //                 ORIGINALCULTURETYPE ID|
  //                 ISGENERAL|
  //                 ISADMIRAL|
  //                 REMASTEREDEDUCATION|
  //                 ENDEDINSETTLEMENT|
  //                 ISFACTIONLEADER|
  //                 ISFACTIONHEIR|
  //                 ISMARRIED|
  //                 ATSEA|
  //                 INENEMYLANDS|
  //                 INBARBARIANLANDS|
  //                 INUNCIVILISEDLANDS|
  //                 ISBESIEGING|
  //                 ISUNDERSIEGE|
  //                 I_WITHDRAWSBEFOREBATTLE|
  //                 ENDEDINENEMYZOC|
  //                 ADVISEACTION EQUALS character_action_advice|
  //                 I_CHARACTERTYPENEARCHARACTERTYPE ID character_type INT ID character_type|
  //                 I_CHARACTERTYPENEARTILE ID character_type INT coords|
  //                 I_CHARACTERNAMENEARTILE name_ character_type INT coords|
  //                 TRADINGRESOURCE ID?|
  //                 REGIONTRADINGRESOURCE ID|
  //                 TRADINGEXOTIC|
  //                 DISTANCECAPITAL comparison_op INT|
  //                 DISTANCEHOME comparison_op INT|
  //                 TRADINGGROUP ID|
  //                 REGIONMERCHANTCOUNT comparison_op INT|
  //                 PRECIOUSMINECOUNT comparison_op INT|
  //                 ISFROMFACTION ID|
  //                 NIGHTBATTLESENABLED|
  //                 WORLDWIDEANCILLARYEXISTS|
  //                 HASOFFICE ID|
  //                 FACTIONTYPE ID|
  //                 TARGETFACTIONTYPE ID|
  //                 FACTIONCULTURETYPE ID|
  //                 TARGETFACTIONCULTURETYPE ID|
  //                 TRAINEDUNITCATEGORY unit_category|
  //                 UNITCATEGORY EQUALS unit_category|
  //                 SENATEMISSIONTIMEREMAINING comparison_op INT|
  //                 MEDIANTAXLEVEL comparison_op tax_level|
  //                 MODETAXLEVEL comparison_op tax_level|
  //                 I_MODETAXLEVEL ID comparison_op tax_level|
  //                 MISSIONSUCCESSLEVEL comparison_op success_level|
  //                 MISSIONSUCCEEDED|
  //                 MISSIONFACTIONTARGETTYPE ID|
  //                 MISSIONCULTURETARGETTYPE ID|
  //                 DIPLOMATICSTANCEFROMCHARACTER name_ comparison_op diplomatic_stance|
  //                 DIPLOMATICSTANCEFROMFACTION ID comparison_op diplomatic_stance|
  //                 FACTIONHASALLIES|
  //                 LOSINGMONEY|
  //                 I_LOSINGMONEY ID|
  //                 FACTIONISALIVE ID|
  //                 SUPPORTCOSTSPERCENTAGE comparison_op number|
  //                 TREASURY comparison_op INT|
  //                 ONAWARFOOTING|
  //                 I_FACTIONBESIEGING ID|
  //                 I_FACTIONBESIEGED ID|
  //                 I_NUMBEROFSETTLEMENTS ID comparison_op INT|
  //                 I_NUMBEROFHEIRS ID comparison_op INT|
  //                 I_FACTIONNEARTILE ID INT coords|
  //                 SETTLEMENTSTAKEN comparison_op INT|
  //                 BATTLESFOUGHT comparison_op INT|
  //                 BATTLESWON comparison_op INT|
  //                 BATTLESLOST comparison_op INT|
  //                 DEFENSIVESIEGESFOUGHT comparison_op INT|
  //                 DEFENSIVESIEGESWON comparison_op INT|
  //                 OFFENSIVESIEGESFOUGHT comparison_op INT|
  //                 OFFENSIVESIEGESWON comparison_op INT|
  //                 FACTIONWIDEANCILLARYEXISTS ID true|
  //                 ISALLY ID|
  //                 ISPROTECTORATE ID|
  //                 ISPROTECTOR ID|
  //                 ISSAMESUPERFACTION ID|
  //                 LOCALPLAYERBATTLESFOUGHT comparison_op INT|
  //                 TOGGLED ID+|
  //                 MAJOREVENTACTIVE STRING ID|
  //                 RANDOMPERCENT comparison_op INT|
  //                 TRUECONDITION|
  //                 WORLDWIDEANCILLARYEXISTS ID BOOLEAN|
  //                 I_ISTUTORIALENABLED|
  //                 I_ISPLAYERTURN|
  //                 CONSTRUCTIONITEMCLICKED ID|
  //                 RECRUITMENTITEMCLICKED name_|
  //                 CHARACTERNAME name_|
  //                 SCROLLDIDOPEN ID|
  //                 UNITHASROUTED ID|
  //                 BATTLEUNITACTIONSTATUS ID action_status|
  //                 I_AMOUNTOFUNITINSETTLEMENT ID INT name_|
  //                 I_UNITCARDSELECTED name_ INT|
  //                 I_COMPARECOUNTER ID comparison_op INT|
  //                 TESTFACTION ID|
  //                 LANGIS ID|
  //                 I_TIMERELAPSED ID ID comparison_op INT|
  //                 I_SOUNDPLAYING ID|
  //                 HASRESOURCE ID|
  //                 SETTLEMENTREVOLTINGFROM ID|
  //                 ISCAPITAL|
  //                 SETTLEMENTNAME ID|
  //                 GOVERNORBUILDINGEXISTS EQUALS ID|
  //                 SETTLEMENTBUILDINGEXISTS comparison_op ID|
  //                 HOMESETTLEMENTBUILDINGEXISTS comparison_op ID|
  //                 SETTLEMENTORDERLEVEL law comparison_op INT|
  //                 SETTLEMENTCAPABILITYLEVEL ID comparison_op INT|
  //                 BUILDINGFINISHEDBYGOVERNOR EQUALS ID|
  //                 SETTLEMENTBUILDINGFINISHED comparison_op ID|
  //                 GOVERNORPLUGINEXISTS comparison_op ID|
  //                 GOVERNORPLUGINFINISHED comparison_op ID|
  //                 GOVERNORTAXLEVEL comparison_op tax_level|
  //                 SETTLEMENTTAXLEVEL comparison_op tax_level|
  //                 GOVERNORINRESIDENCE|
  //                 GOVERNORLOYALTYLEVEL comparison_op loyalty_to_governor_level|
  //                 SETTLEMENTLOYALTYLEVEL comparison_op loyalty_to_governor_level|
  //                 RIOTRISK comparison_op 30|
  //                 BUILDINGQUEUEIDLEDESPITECASH|
  //                 TRAININGQUEUEIDLEDESPITECASH|
  //                 I_SETTLEMENTEXISTS ID|
  //                 I_SETTLEMENTOWNER ID EQUALS ID|
  //                 I_SETTLEMENTOWNERCULTURE ID EQUALS ID|
  //                 I_SETTLEMENTLEVEL ID comparison_op settlement_level|
  //                 ADVISEFINANCIALBUILD EQUALS ID|
  //                 ADVISEBUILD EQUALS ID|
  //                 ADVISERECRUIT EQUALS name_|
  //                 SETTLEMENTPOPULATIONMAXEDOUT|
  //                 SETTLEMENTPOPULATIONTOOLOW|
  //                 SETTLEMENTAUTOMANAGED automanage_type|
  //                 FERALSETTLEMENTAUTOMANAGED automanage_type|
  //                 PERCENTAGEOFPOPULATIONINGARRISON comparison_op INT|
  //                 GARRISONTOPOPULATIONRATIO comparison_op number|
  //                 GARRISONSETTLEMENTRATIO comparison_op number|
  //                 HEALTHPERCENTAGE comparison_op INT|
  //                 SETTLEMENTHASPLAGUE|
  //                 ISFORTGARRISONED|
  //                 ISSETTLEMENTGARRISONED|
  //                 ISSETTLEMENTRIOTING|
  //                 I_NUMBERUNITSINSETTLEMENT ID ID comparison_op INT|
  //                 I_ADVISORSPEECHPLAYING|
  //                 CHARACTERISLOCAL|
  //                 TARGETCHARACTERISLOCAL|
  //                 SETTLEMENTISLOCAL|
  //                 TARGETSETTLEMENTISLOCAL|
  //                 REGIONISLOCAL|
  //                 TARGETREGIONISLOCAL|
  //                 ARMYISLOCAL|
  //                 ARMYISGARRISON|
  //                 TARGETARMYISLOCAL|
  //                 FACTIONISLOCAL|
  //                 I_LOCALFACTION ID|
  //                 TARGETFACTIONISLOCAL|
  //                 I_TURNNUMBER comparison_op INT|
  //                 I_MAPNAME TXT_FILE|
  //                 I_THREADCOUNT ID comparison_op INT|
  //                 I_ISTRIGGERTRUE ID|
  //                 INCOMINGMESSAGETYPE ID|
  //                 I_ADVISORVERBOSITY comparison_op INT|
  //                 I_ADVISORVISIBLE|
  //                 I_CHARACTERSELECTED name_|
  //                 I_AGENTSELECTED character_type|
  //                 I_SETTLEMENTSELECTED ID|
  //                 SHORTCUTTRIGGERED ID ID|
  //                 I_ADVANCEDSTATSSCROLLISOPEN|
  //                 BUTTONPRESSED ID|
  //                 SCROLLOPENED ID|
  //                 SCROLLCLOSED ID|
  //                 SCROLLADVICEREQUESTED ID|
  //                 I_ANNOTATIONDISPLAYED ID|
  //                 FERALUITYPE ID|
  //                 MERCHANTISAVAILABLETOBUILD|
  //                 SETTLEMENTHASDAMAGEDBUILDING|
  //                 LOCALPLAYERHASREINFORCEMENTS|
  //                 SETTLEMENTMERCHANTTRADINGWITH ID|       // This is not documented but used.
  //                 SETTLEMENTOWNEDBY ID|                   // This is not documented but used.
  //                 FACTIONBUILDINGEXISTS comparison_op ID| // This is not documented but used.
  //               
  public static boolean condition_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition_")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONDITION_, "<condition>");
    r = consumeToken(b, I_INBATTLE);
    if (!r) r = consumeToken(b, WONBATTLE);
    if (!r) r = parseTokens(b, 0, I_WONBATTLE, ID);
    if (!r) r = consumeToken(b, ROUTS);
    if (!r) r = consumeToken(b, ALLY_ROUTS);
    if (!r) r = condition__5(b, l + 1);
    if (!r) r = condition__6(b, l + 1);
    if (!r) r = consumeToken(b, GENERALFOUGHTINCOMBAT);
    if (!r) r = condition__8(b, l + 1);
    if (!r) r = condition__9(b, l + 1);
    if (!r) r = condition__10(b, l + 1);
    if (!r) r = condition__11(b, l + 1);
    if (!r) r = condition__12(b, l + 1);
    if (!r) r = condition__13(b, l + 1);
    if (!r) r = condition__14(b, l + 1);
    if (!r) r = condition__15(b, l + 1);
    if (!r) r = condition__16(b, l + 1);
    if (!r) r = parseTokens(b, 0, GENERALFOUGHTFACTION, ID);
    if (!r) r = parseTokens(b, 0, GENERALFOUGHTCULTURE, ID);
    if (!r) r = condition__19(b, l + 1);
    if (!r) r = consumeToken(b, ISNIGHTBATTLE);
    if (!r) r = condition__21(b, l + 1);
    if (!r) r = condition__22(b, l + 1);
    if (!r) r = consumeToken(b, WASATTACKER);
    if (!r) r = consumeToken(b, I_BATTLEAIATTACKING);
    if (!r) r = consumeToken(b, I_BATTLEAIATTACKINGSETTLEMENT);
    if (!r) r = consumeToken(b, I_BATTLEAIDEFENDINGSETTLEMENT);
    if (!r) r = consumeToken(b, I_BATTLEAIDEFENDINGHILL);
    if (!r) r = consumeToken(b, I_BATTLEAIDEFENDINGCROSSING);
    if (!r) r = consumeToken(b, I_BATTLEAISCOUTING);
    if (!r) r = consumeToken(b, I_BATTLEISRIVERBATTLE);
    if (!r) r = consumeToken(b, I_BATTLEISSIEGEBATTLE);
    if (!r) r = consumeToken(b, I_BATTLEISSALLYOUTBATTLE);
    if (!r) r = consumeToken(b, I_BATTLEISFORTBATTLE);
    if (!r) r = condition__34(b, l + 1);
    if (!r) r = condition__35(b, l + 1);
    if (!r) r = condition__36(b, l + 1);
    if (!r) r = condition__37(b, l + 1);
    if (!r) r = consumeToken(b, I_BATTLESETTLEMENTWALLSBREACHED);
    if (!r) r = consumeToken(b, I_BATTLESETTLEMENTGATEDESTROYED);
    if (!r) r = condition__40(b, l + 1);
    if (!r) r = condition__41(b, l + 1);
    if (!r) r = condition__42(b, l + 1);
    if (!r) r = condition__43(b, l + 1);
    if (!r) r = condition__44(b, l + 1);
    if (!r) r = condition__45(b, l + 1);
    if (!r) r = condition__46(b, l + 1);
    if (!r) r = condition__47(b, l + 1);
    if (!r) r = condition__48(b, l + 1);
    if (!r) r = condition__49(b, l + 1);
    if (!r) r = condition__50(b, l + 1);
    if (!r) r = condition__51(b, l + 1);
    if (!r) r = consumeToken(b, BATTLEPLAYERUNITONWALLS);
    if (!r) r = consumeToken(b, BATTLEENEMYUNITONWALLS);
    if (!r) r = condition__54(b, l + 1);
    if (!r) r = condition__55(b, l + 1);
    if (!r) r = consumeToken(b, BATTLEPLAYERUNITCLOSEFORMATION);
    if (!r) r = consumeToken(b, BATTLEENEMYUNITCLOSEFORMATION);
    if (!r) r = condition__58(b, l + 1);
    if (!r) r = condition__59(b, l + 1);
    if (!r) r = condition__60(b, l + 1);
    if (!r) r = consumeToken(b, BATTLEPLAYERUNITSPECIALABILITYACTIVE);
    if (!r) r = consumeToken(b, BATTLEENEMYUNITSPECIALABILITYACTIVE);
    if (!r) r = condition__63(b, l + 1);
    if (!r) r = condition__64(b, l + 1);
    if (!r) r = condition__65(b, l + 1);
    if (!r) r = condition__66(b, l + 1);
    if (!r) r = condition__67(b, l + 1);
    if (!r) r = condition__68(b, l + 1);
    if (!r) r = condition__69(b, l + 1);
    if (!r) r = condition__70(b, l + 1);
    if (!r) r = consumeToken(b, BATTLEPLAYERUNITENGAGED);
    if (!r) r = consumeToken(b, BATTLEENEMYUNITENGAGED);
    if (!r) r = condition__73(b, l + 1);
    if (!r) r = condition__74(b, l + 1);
    if (!r) r = consumeToken(b, BATTLEPLAYERUNITMOVINGFAST);
    if (!r) r = consumeToken(b, BATTLEENEMYUNITMOVINGFAST);
    if (!r) r = condition__77(b, l + 1);
    if (!r) r = condition__78(b, l + 1);
    if (!r) r = consumeToken(b, BATTLEISMELEEATTACK);
    if (!r) r = condition__80(b, l + 1);
    if (!r) r = condition__81(b, l + 1);
    if (!r) r = condition__82(b, l + 1);
    if (!r) r = condition__83(b, l + 1);
    if (!r) r = condition__84(b, l + 1);
    if (!r) r = condition__85(b, l + 1);
    if (!r) r = condition__86(b, l + 1);
    if (!r) r = condition__87(b, l + 1);
    if (!r) r = condition__88(b, l + 1);
    if (!r) r = condition__89(b, l + 1);
    if (!r) r = condition__90(b, l + 1);
    if (!r) r = condition__91(b, l + 1);
    if (!r) r = condition__92(b, l + 1);
    if (!r) r = condition__93(b, l + 1);
    if (!r) r = consumeToken(b, I_BATTLEPLAYERARMYISATTACKER);
    if (!r) r = condition__95(b, l + 1);
    if (!r) r = condition__96(b, l + 1);
    if (!r) r = condition__97(b, l + 1);
    if (!r) r = consumeToken(b, I_BATTLESTARTED);
    if (!r) r = consumeToken(b, I_BATTLEFINISHED);
    if (!r) r = consumeToken(b, I_BATTLEEND);
    if (!r) r = consumeToken(b, I_BATTLEENDPENDING);
    if (!r) r = parseTokens(b, 0, I_ISUNITMOVEFASTSET, ID);
    if (!r) r = parseTokens(b, 0, I_ISUNITMOVING, ID);
    if (!r) r = parseTokens(b, 0, I_ISUNITIDLE, ID);
    if (!r) r = parseTokens(b, 0, I_ISUNITROUTING, ID);
    if (!r) r = parseTokens(b, 0, I_ISUNITUNDERFIRE, ID);
    if (!r) r = parseTokens(b, 0, I_ISUNITENGAGED, ID);
    if (!r) r = parseTokens(b, 0, I_ISUNITENGAGEDWITHUNIT, ID, ID);
    if (!r) r = condition__109(b, l + 1);
    if (!r) r = condition__110(b, l + 1);
    if (!r) r = condition__111(b, l + 1);
    if (!r) r = condition__112(b, l + 1);
    if (!r) r = condition__113(b, l + 1);
    if (!r) r = condition__114(b, l + 1);
    if (!r) r = parseTokens(b, 0, I_UNITINRANGEOFUNIT, ID, ID);
    if (!r) r = parseTokens(b, 0, I_UNITDESTROYED, ID);
    if (!r) r = parseTokens(b, 0, I_UNITENEMYUNITINRADIUS, ID, INT);
    if (!r) r = parseTokens(b, 0, I_ISUNITGROUPMOVING, ID);
    if (!r) r = parseTokens(b, 0, I_ISUNITGROUPENGAGED, ID);
    if (!r) r = parseTokens(b, 0, I_ISUNITGROUPIDLE, ID);
    if (!r) r = parseTokens(b, 0, I_ISUNITGROUPDESTROYED, ID);
    if (!r) r = condition__122(b, l + 1);
    if (!r) r = parseTokens(b, 0, I_UNITGROUPFORMATION, ID, EQUALS, ID);
    if (!r) r = condition__124(b, l + 1);
    if (!r) r = condition__125(b, l + 1);
    if (!r) r = parseTokens(b, 0, I_UNITGROUPINRANGEOFUNIT, ID, ID);
    if (!r) r = parseTokens(b, 0, I_UNITINRANGEOFUNITGROUP, ID, ID);
    if (!r) r = parseTokens(b, 0, I_UNITGROUPINRANGEOFUNITGROUP, ID, ID);
    if (!r) r = parseTokens(b, 0, I_PLAYERINRANGEOFUNITGROUP, ID);
    if (!r) r = parseTokens(b, 0, I_PLAYERINRANGEOFUNIT, ID);
    if (!r) r = parseTokens(b, 0, I_UNITTYPESELECTED, ID);
    if (!r) r = parseTokens(b, 0, UNITTYPE, ID);
    if (!r) r = parseTokens(b, 0, I_UNITSELECTED, ID);
    if (!r) r = consumeToken(b, I_MULTIPLEUNITSSELECTED);
    if (!r) r = condition__135(b, l + 1);
    if (!r) r = parseTokens(b, 0, I_ISCAMERAZOOMINGTOUNIT, ID);
    if (!r) r = condition__137(b, l + 1);
    if (!r) r = condition__138(b, l + 1);
    if (!r) r = condition__139(b, l + 1);
    if (!r) r = condition__140(b, l + 1);
    if (!r) r = consumeToken(b, LOCALPLAYERHASMANUALREINFORCEMENTS);
    if (!r) r = consumeToken(b, LOCALPLAYERHASAIREINFORCEMENTS);
    if (!r) r = condition__143(b, l + 1);
    if (!r) r = parseTokens(b, 0, HASANCILLARY, ID);
    if (!r) r = condition__145(b, l + 1);
    if (!r) r = condition__146(b, l + 1);
    if (!r) r = condition__147(b, l + 1);
    if (!r) r = condition__148(b, l + 1);
    if (!r) r = condition__149(b, l + 1);
    if (!r) r = condition__150(b, l + 1);
    if (!r) r = consumeToken(b, NOACTIONTHISTURN);
    if (!r) r = condition__152(b, l + 1);
    if (!r) r = condition__153(b, l + 1);
    if (!r) r = condition__154(b, l + 1);
    if (!r) r = parseTokens(b, 0, CULTURETYPE, ID);
    if (!r) r = parseTokens(b, 0, ORIGINALFACTIONTYPE, ID);
    if (!r) r = parseTokens(b, 0, ORIGINALCULTURETYPE, ID);
    if (!r) r = consumeToken(b, ISGENERAL);
    if (!r) r = consumeToken(b, ISADMIRAL);
    if (!r) r = consumeToken(b, REMASTEREDEDUCATION);
    if (!r) r = consumeToken(b, ENDEDINSETTLEMENT);
    if (!r) r = consumeToken(b, ISFACTIONLEADER);
    if (!r) r = consumeToken(b, ISFACTIONHEIR);
    if (!r) r = consumeToken(b, ISMARRIED);
    if (!r) r = consumeToken(b, ATSEA);
    if (!r) r = consumeToken(b, INENEMYLANDS);
    if (!r) r = consumeToken(b, INBARBARIANLANDS);
    if (!r) r = consumeToken(b, INUNCIVILISEDLANDS);
    if (!r) r = consumeToken(b, ISBESIEGING);
    if (!r) r = consumeToken(b, ISUNDERSIEGE);
    if (!r) r = consumeToken(b, I_WITHDRAWSBEFOREBATTLE);
    if (!r) r = consumeToken(b, ENDEDINENEMYZOC);
    if (!r) r = condition__173(b, l + 1);
    if (!r) r = condition__174(b, l + 1);
    if (!r) r = condition__175(b, l + 1);
    if (!r) r = condition__176(b, l + 1);
    if (!r) r = condition__177(b, l + 1);
    if (!r) r = parseTokens(b, 0, REGIONTRADINGRESOURCE, ID);
    if (!r) r = consumeToken(b, TRADINGEXOTIC);
    if (!r) r = condition__180(b, l + 1);
    if (!r) r = condition__181(b, l + 1);
    if (!r) r = parseTokens(b, 0, TRADINGGROUP, ID);
    if (!r) r = condition__183(b, l + 1);
    if (!r) r = condition__184(b, l + 1);
    if (!r) r = parseTokens(b, 0, ISFROMFACTION, ID);
    if (!r) r = consumeToken(b, NIGHTBATTLESENABLED);
    if (!r) r = consumeToken(b, WORLDWIDEANCILLARYEXISTS);
    if (!r) r = parseTokens(b, 0, HASOFFICE, ID);
    if (!r) r = parseTokens(b, 0, FACTIONTYPE, ID);
    if (!r) r = parseTokens(b, 0, TARGETFACTIONTYPE, ID);
    if (!r) r = parseTokens(b, 0, FACTIONCULTURETYPE, ID);
    if (!r) r = parseTokens(b, 0, TARGETFACTIONCULTURETYPE, ID);
    if (!r) r = condition__193(b, l + 1);
    if (!r) r = condition__194(b, l + 1);
    if (!r) r = condition__195(b, l + 1);
    if (!r) r = condition__196(b, l + 1);
    if (!r) r = condition__197(b, l + 1);
    if (!r) r = condition__198(b, l + 1);
    if (!r) r = condition__199(b, l + 1);
    if (!r) r = consumeToken(b, MISSIONSUCCEEDED);
    if (!r) r = parseTokens(b, 0, MISSIONFACTIONTARGETTYPE, ID);
    if (!r) r = parseTokens(b, 0, MISSIONCULTURETARGETTYPE, ID);
    if (!r) r = condition__203(b, l + 1);
    if (!r) r = condition__204(b, l + 1);
    if (!r) r = consumeToken(b, FACTIONHASALLIES);
    if (!r) r = consumeToken(b, LOSINGMONEY);
    if (!r) r = parseTokens(b, 0, I_LOSINGMONEY, ID);
    if (!r) r = parseTokens(b, 0, FACTIONISALIVE, ID);
    if (!r) r = condition__209(b, l + 1);
    if (!r) r = condition__210(b, l + 1);
    if (!r) r = consumeToken(b, ONAWARFOOTING);
    if (!r) r = parseTokens(b, 0, I_FACTIONBESIEGING, ID);
    if (!r) r = parseTokens(b, 0, I_FACTIONBESIEGED, ID);
    if (!r) r = condition__214(b, l + 1);
    if (!r) r = condition__215(b, l + 1);
    if (!r) r = condition__216(b, l + 1);
    if (!r) r = condition__217(b, l + 1);
    if (!r) r = condition__218(b, l + 1);
    if (!r) r = condition__219(b, l + 1);
    if (!r) r = condition__220(b, l + 1);
    if (!r) r = condition__221(b, l + 1);
    if (!r) r = condition__222(b, l + 1);
    if (!r) r = condition__223(b, l + 1);
    if (!r) r = condition__224(b, l + 1);
    if (!r) r = parseTokens(b, 0, FACTIONWIDEANCILLARYEXISTS, ID, TRUE);
    if (!r) r = parseTokens(b, 0, ISALLY, ID);
    if (!r) r = parseTokens(b, 0, ISPROTECTORATE, ID);
    if (!r) r = parseTokens(b, 0, ISPROTECTOR, ID);
    if (!r) r = parseTokens(b, 0, ISSAMESUPERFACTION, ID);
    if (!r) r = condition__230(b, l + 1);
    if (!r) r = condition__231(b, l + 1);
    if (!r) r = parseTokens(b, 0, MAJOREVENTACTIVE, STRING, ID);
    if (!r) r = condition__233(b, l + 1);
    if (!r) r = consumeToken(b, TRUECONDITION);
    if (!r) r = parseTokens(b, 0, WORLDWIDEANCILLARYEXISTS, ID, BOOLEAN);
    if (!r) r = consumeToken(b, I_ISTUTORIALENABLED);
    if (!r) r = consumeToken(b, I_ISPLAYERTURN);
    if (!r) r = parseTokens(b, 0, CONSTRUCTIONITEMCLICKED, ID);
    if (!r) r = condition__239(b, l + 1);
    if (!r) r = condition__240(b, l + 1);
    if (!r) r = parseTokens(b, 0, SCROLLDIDOPEN, ID);
    if (!r) r = parseTokens(b, 0, UNITHASROUTED, ID);
    if (!r) r = condition__243(b, l + 1);
    if (!r) r = condition__244(b, l + 1);
    if (!r) r = condition__245(b, l + 1);
    if (!r) r = condition__246(b, l + 1);
    if (!r) r = parseTokens(b, 0, TESTFACTION, ID);
    if (!r) r = parseTokens(b, 0, LANGIS, ID);
    if (!r) r = condition__249(b, l + 1);
    if (!r) r = parseTokens(b, 0, I_SOUNDPLAYING, ID);
    if (!r) r = parseTokens(b, 0, HASRESOURCE, ID);
    if (!r) r = parseTokens(b, 0, SETTLEMENTREVOLTINGFROM, ID);
    if (!r) r = consumeToken(b, ISCAPITAL);
    if (!r) r = parseTokens(b, 0, SETTLEMENTNAME, ID);
    if (!r) r = parseTokens(b, 0, GOVERNORBUILDINGEXISTS, EQUALS, ID);
    if (!r) r = condition__256(b, l + 1);
    if (!r) r = condition__257(b, l + 1);
    if (!r) r = condition__258(b, l + 1);
    if (!r) r = condition__259(b, l + 1);
    if (!r) r = parseTokens(b, 0, BUILDINGFINISHEDBYGOVERNOR, EQUALS, ID);
    if (!r) r = condition__261(b, l + 1);
    if (!r) r = condition__262(b, l + 1);
    if (!r) r = condition__263(b, l + 1);
    if (!r) r = condition__264(b, l + 1);
    if (!r) r = condition__265(b, l + 1);
    if (!r) r = consumeToken(b, GOVERNORINRESIDENCE);
    if (!r) r = condition__267(b, l + 1);
    if (!r) r = condition__268(b, l + 1);
    if (!r) r = condition__269(b, l + 1);
    if (!r) r = consumeToken(b, BUILDINGQUEUEIDLEDESPITECASH);
    if (!r) r = consumeToken(b, TRAININGQUEUEIDLEDESPITECASH);
    if (!r) r = parseTokens(b, 0, I_SETTLEMENTEXISTS, ID);
    if (!r) r = parseTokens(b, 0, I_SETTLEMENTOWNER, ID, EQUALS, ID);
    if (!r) r = parseTokens(b, 0, I_SETTLEMENTOWNERCULTURE, ID, EQUALS, ID);
    if (!r) r = condition__275(b, l + 1);
    if (!r) r = parseTokens(b, 0, ADVISEFINANCIALBUILD, EQUALS, ID);
    if (!r) r = parseTokens(b, 0, ADVISEBUILD, EQUALS, ID);
    if (!r) r = condition__278(b, l + 1);
    if (!r) r = consumeToken(b, SETTLEMENTPOPULATIONMAXEDOUT);
    if (!r) r = consumeToken(b, SETTLEMENTPOPULATIONTOOLOW);
    if (!r) r = condition__281(b, l + 1);
    if (!r) r = condition__282(b, l + 1);
    if (!r) r = condition__283(b, l + 1);
    if (!r) r = condition__284(b, l + 1);
    if (!r) r = condition__285(b, l + 1);
    if (!r) r = condition__286(b, l + 1);
    if (!r) r = consumeToken(b, SETTLEMENTHASPLAGUE);
    if (!r) r = consumeToken(b, ISFORTGARRISONED);
    if (!r) r = consumeToken(b, ISSETTLEMENTGARRISONED);
    if (!r) r = consumeToken(b, ISSETTLEMENTRIOTING);
    if (!r) r = condition__291(b, l + 1);
    if (!r) r = consumeToken(b, I_ADVISORSPEECHPLAYING);
    if (!r) r = consumeToken(b, CHARACTERISLOCAL);
    if (!r) r = consumeToken(b, TARGETCHARACTERISLOCAL);
    if (!r) r = consumeToken(b, SETTLEMENTISLOCAL);
    if (!r) r = consumeToken(b, TARGETSETTLEMENTISLOCAL);
    if (!r) r = consumeToken(b, REGIONISLOCAL);
    if (!r) r = consumeToken(b, TARGETREGIONISLOCAL);
    if (!r) r = consumeToken(b, ARMYISLOCAL);
    if (!r) r = consumeToken(b, ARMYISGARRISON);
    if (!r) r = consumeToken(b, TARGETARMYISLOCAL);
    if (!r) r = consumeToken(b, FACTIONISLOCAL);
    if (!r) r = parseTokens(b, 0, I_LOCALFACTION, ID);
    if (!r) r = consumeToken(b, TARGETFACTIONISLOCAL);
    if (!r) r = condition__305(b, l + 1);
    if (!r) r = parseTokens(b, 0, I_MAPNAME, TXT_FILE);
    if (!r) r = condition__307(b, l + 1);
    if (!r) r = parseTokens(b, 0, I_ISTRIGGERTRUE, ID);
    if (!r) r = parseTokens(b, 0, INCOMINGMESSAGETYPE, ID);
    if (!r) r = condition__310(b, l + 1);
    if (!r) r = consumeToken(b, I_ADVISORVISIBLE);
    if (!r) r = condition__312(b, l + 1);
    if (!r) r = condition__313(b, l + 1);
    if (!r) r = parseTokens(b, 0, I_SETTLEMENTSELECTED, ID);
    if (!r) r = parseTokens(b, 0, SHORTCUTTRIGGERED, ID, ID);
    if (!r) r = consumeToken(b, I_ADVANCEDSTATSSCROLLISOPEN);
    if (!r) r = parseTokens(b, 0, BUTTONPRESSED, ID);
    if (!r) r = parseTokens(b, 0, SCROLLOPENED, ID);
    if (!r) r = parseTokens(b, 0, SCROLLCLOSED, ID);
    if (!r) r = parseTokens(b, 0, SCROLLADVICEREQUESTED, ID);
    if (!r) r = parseTokens(b, 0, I_ANNOTATIONDISPLAYED, ID);
    if (!r) r = parseTokens(b, 0, FERALUITYPE, ID);
    if (!r) r = consumeToken(b, MERCHANTISAVAILABLETOBUILD);
    if (!r) r = consumeToken(b, SETTLEMENTHASDAMAGEDBUILDING);
    if (!r) r = consumeToken(b, LOCALPLAYERHASREINFORCEMENTS);
    if (!r) r = parseTokens(b, 0, SETTLEMENTMERCHANTTRADINGWITH, ID);
    if (!r) r = parseTokens(b, 0, SETTLEMENTOWNEDBY, ID);
    if (!r) r = condition__328(b, l + 1);
    if (!r) r = consumeToken(b, CONDITION__329_0);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // GENERALHPLOSTRATIOINBATTLE comparison_op number
  private static boolean condition__5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GENERALHPLOSTRATIOINBATTLE);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // GENERALNUMKILLSINBATTLE comparison_op number
  private static boolean condition__6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GENERALNUMKILLSINBATTLE);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PERCENTAGEOFARMYKILLED comparison_op number
  private static boolean condition__8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PERCENTAGEOFARMYKILLED);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_PERCENTAGEOFARMYKILLED INT INT comparison_op INT
  private static boolean condition__9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__9")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_PERCENTAGEOFARMYKILLED, INT, INT);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // PERCENTAGEENEMYKILLED comparison_op number
  private static boolean condition__10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__10")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PERCENTAGEENEMYKILLED);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PERCENTAGEBODYGUARDKILLED comparison_op number
  private static boolean condition__11(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__11")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PERCENTAGEBODYGUARDKILLED);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PERCENTAGEROUTEDOFFFIELD comparison_op number
  private static boolean condition__12(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__12")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PERCENTAGEROUTEDOFFFIELD);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NUMKILLEDGENERALS comparison_op number
  private static boolean condition__13(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__13")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMKILLEDGENERALS);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PERCENTAGEUNITCATEGORY unit_category comparison_op number
  private static boolean condition__14(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__14")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PERCENTAGEUNITCATEGORY);
    r = r && unit_category(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NUMFRIENDSINBATTLE comparison_op number
  private static boolean condition__15(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__15")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMFRIENDSINBATTLE);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NUMENEMIESINBATTLE comparison_op number
  private static boolean condition__16(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__16")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMENEMIESINBATTLE);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_CONFLICTTYPE conflict_type
  private static boolean condition__19(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__19")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_CONFLICTTYPE);
    r = r && conflict_type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLESUCCESS comparison_op battle_success
  private static boolean condition__21(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__21")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLESUCCESS);
    r = r && comparison_op(b, l + 1);
    r = r && battle_success(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEODDS comparison_op number
  private static boolean condition__22(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__22")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLEODDS);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEATTACKERNUMSIEGEENGINES siege_engine_class
  private static boolean condition__34(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__34")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEATTACKERNUMSIEGEENGINES);
    r = r && siege_engine_class(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEATTACKERNUMARTILLERYCANPENETRATEWALLS comparison_op INT
  private static boolean condition__35(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__35")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEATTACKERNUMARTILLERYCANPENETRATEWALLS);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEDEFENDERNUMNONMISSILEUNITSONWALLS comparison_op INT
  private static boolean condition__36(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__36")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEDEFENDERNUMNONMISSILEUNITSONWALLS);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEDEFENDERNUMMISSILEUNITSONWALLS comparison_op INT
  private static boolean condition__37(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__37")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEDEFENDERNUMMISSILEUNITSONWALLS);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLESETTLEMENTTOWERDEFENCE tower_defence_type
  private static boolean condition__40(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__40")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLESETTLEMENTTOWERDEFENCE);
    r = r && tower_defence_type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLESETTLEMENTGATEDEFENCE gate_defence_type
  private static boolean condition__41(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__41")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLESETTLEMENTGATEDEFENCE);
    r = r && gate_defence_type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLESETTLEMENTFORTIFICATIONLEVEL comparison_op INT
  private static boolean condition__42(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__42")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLESETTLEMENTFORTIFICATIONLEVEL);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEBUILDINGTYPE EQUALS battle_building_type
  private static boolean condition__43(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__43")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEBUILDINGTYPE, EQUALS);
    r = r && battle_building_type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLESETTLEMENTGATESTRENGTH comparison_op INT
  private static boolean condition__44(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__44")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLESETTLEMENTGATESTRENGTH);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLENUMBEROFRIVERCROSSINGS comparison_op INT
  private static boolean condition__45(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__45")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLENUMBEROFRIVERCROSSINGS);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEPLAYERUNITCLASS EQUALS unit_class
  private static boolean condition__46(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__46")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEPLAYERUNITCLASS, EQUALS);
    r = r && unit_class(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEENEMYUNITCLASS EQUALS unit_class
  private static boolean condition__47(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__47")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEENEMYUNITCLASS, EQUALS);
    r = r && unit_class(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEPLAYERUNITCATEGORY EQUALS unit_category
  private static boolean condition__48(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__48")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEPLAYERUNITCATEGORY, EQUALS);
    r = r && unit_category(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEENEMYUNITCATEGORY EQUALS unit_category
  private static boolean condition__49(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__49")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEENEMYUNITCATEGORY, EQUALS);
    r = r && unit_category(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEPLAYERUNITSIEGEENGINECLASS EQUALS siege_engine_class
  private static boolean condition__50(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__50")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEPLAYERUNITSIEGEENGINECLASS, EQUALS);
    r = r && siege_engine_class(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEENEMYUNITSIEGEENGINECLASS EQUALS siege_engine_class
  private static boolean condition__51(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__51")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEENEMYUNITSIEGEENGINECLASS, EQUALS);
    r = r && siege_engine_class(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEPLAYERCURRENTFORMATION EQUALS unit_formation
  private static boolean condition__54(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__54")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEPLAYERCURRENTFORMATION, EQUALS);
    r = r && unit_formation(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEENEMYCURRENTFORMATION EQUALS unit_formation
  private static boolean condition__55(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__55")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEENEMYCURRENTFORMATION, EQUALS);
    r = r && unit_formation(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEPLAYERUNITSPECIALABILITYSUPPORTED comparison_op special_ability
  private static boolean condition__58(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__58")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLEPLAYERUNITSPECIALABILITYSUPPORTED);
    r = r && comparison_op(b, l + 1);
    r = r && special_ability(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLESELECTEDPLAYERUNITSPECIALABILITYSUPPORTED comparison_op special_ability
  private static boolean condition__59(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__59")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLESELECTEDPLAYERUNITSPECIALABILITYSUPPORTED);
    r = r && comparison_op(b, l + 1);
    r = r && special_ability(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEENEMYUNITSPECIALABILITYSUPPORTED comparison_op special_ability
  private static boolean condition__60(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__60")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLEENEMYUNITSPECIALABILITYSUPPORTED);
    r = r && comparison_op(b, l + 1);
    r = r && special_ability(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEPLAYERMOUNTCLASS comparison_op mount_class
  private static boolean condition__63(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__63")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLEPLAYERMOUNTCLASS);
    r = r && comparison_op(b, l + 1);
    r = r && mount_class(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEENEMYMOUNTCLASS comparison_op mount_class
  private static boolean condition__64(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__64")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLEENEMYMOUNTCLASS);
    r = r && comparison_op(b, l + 1);
    r = r && mount_class(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEPLAYERUNITMELEESTRENGTH comparison_op number
  private static boolean condition__65(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__65")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLEPLAYERUNITMELEESTRENGTH);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEENEMYUNITMELEESTRENGTH comparison_op number
  private static boolean condition__66(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__66")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLEENEMYUNITMELEESTRENGTH);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEPLAYERUNITMISSILESTRENGTH comparison_op number
  private static boolean condition__67(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__67")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLEPLAYERUNITMISSILESTRENGTH);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEENEMYUNITMISSILESTRENGTH comparison_op number
  private static boolean condition__68(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__68")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLEENEMYUNITMISSILESTRENGTH);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEPLAYERUNITSPECIALFORMATION EQUALS unit_formation
  private static boolean condition__69(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__69")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEPLAYERUNITSPECIALFORMATION, EQUALS);
    r = r && unit_formation(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEENEMYUNITSPECIALFORMATION EQUALS unit_formation
  private static boolean condition__70(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__70")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEENEMYUNITSPECIALFORMATION, EQUALS);
    r = r && unit_formation(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEPLAYERACTIONSTATUS EQUALS action_status
  private static boolean condition__73(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__73")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEPLAYERACTIONSTATUS, EQUALS);
    r = r && action_status(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEENEMYACTIONSTATUS EQUALS action_status
  private static boolean condition__74(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__74")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEENEMYACTIONSTATUS, EQUALS);
    r = r && action_status(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLERANGEOFATTACK comparison_op number
  private static boolean condition__77(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__77")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLERANGEOFATTACK);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEDIRECTIONOFATTACK EQUALS attack_direction
  private static boolean condition__78(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__78")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEDIRECTIONOFATTACK, EQUALS);
    r = r && attack_direction(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEPLAYERARMYPERCENTAGEOFUNITCLASS unit_class comparison_op number
  private static boolean condition__80(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__80")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEPLAYERARMYPERCENTAGEOFUNITCLASS);
    r = r && unit_class(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEENEMYARMYPERCENTAGEOFUNITCLASS unit_class comparison_op number
  private static boolean condition__81(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__81")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEENEMYARMYPERCENTAGEOFUNITCLASS);
    r = r && unit_class(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEPLAYERARMYPERCENTAGEOFUNITCATEGORY unit_category comparison_op number
  private static boolean condition__82(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__82")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEPLAYERARMYPERCENTAGEOFUNITCATEGORY);
    r = r && unit_category(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEENEMYARMYPERCENTAGEOFUNITCATEGORY unit_category comparison_op number
  private static boolean condition__83(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__83")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEENEMYARMYPERCENTAGEOFUNITCATEGORY);
    r = r && unit_category(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEPLAYERARMYPERCENTAGEOFMOUNTCLASS mount_class comparison_op number
  private static boolean condition__84(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__84")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEPLAYERARMYPERCENTAGEOFMOUNTCLASS);
    r = r && mount_class(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEENEMYARMYPERCENTAGEOFMOUNTCLASS mount_class comparison_op number
  private static boolean condition__85(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__85")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEENEMYARMYPERCENTAGEOFMOUNTCLASS);
    r = r && mount_class(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEPLAYERARMYPERCENTAGEOFCLASSANDCATEGORY unit_class unit_category comparison_op number
  private static boolean condition__86(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__86")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEPLAYERARMYPERCENTAGEOFCLASSANDCATEGORY);
    r = r && unit_class(b, l + 1);
    r = r && unit_category(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEENEMYARMYPERCENTAGEOFCLASSANDCATEGORY unit_class unit_category comparison_op number
  private static boolean condition__87(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__87")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEENEMYARMYPERCENTAGEOFCLASSANDCATEGORY);
    r = r && unit_class(b, l + 1);
    r = r && unit_category(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEPLAYERARMYPERCENTAGEOFSPECIALABILITY special_ability comparison_op number
  private static boolean condition__88(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__88")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEPLAYERARMYPERCENTAGEOFSPECIALABILITY);
    r = r && special_ability(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEENEMYARMYPERCENTAGEOFSPECIALABILITY special_ability comparison_op number
  private static boolean condition__89(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__89")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEENEMYARMYPERCENTAGEOFSPECIALABILITY);
    r = r && special_ability(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEPLAYERARMYPERCENTAGECANHIDE hide_type comparison_op number
  private static boolean condition__90(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__90")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEPLAYERARMYPERCENTAGECANHIDE);
    r = r && hide_type(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEENEMYARMYPERCENTAGECANHIDE hide_type comparison_op number
  private static boolean condition__91(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__91")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEENEMYARMYPERCENTAGECANHIDE);
    r = r && hide_type(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEPLAYERARMYPERCENTAGECANSWIM comparison_op number
  private static boolean condition__92(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__92")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEPLAYERARMYPERCENTAGECANSWIM);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEENEMYARMYPERCENTAGECANSWIM comparison_op number
  private static boolean condition__93(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__93")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEENEMYARMYPERCENTAGECANSWIM);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEPLAYERALLIANCEODDSINFAVOUR comparison_op INT
  private static boolean condition__95(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__95")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEPLAYERALLIANCEODDSINFAVOUR);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEPLAYERALLIANCEODDSAGAINST comparison_op INT
  private static boolean condition__96(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__96")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_BATTLEPLAYERALLIANCEODDSAGAINST);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // TOTALSIEGEWEAPONS comparison_op INT
  private static boolean condition__97(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__97")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TOTALSIEGEWEAPONS);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_UNITFORMATION ID EQUALS unit_formation
  private static boolean condition__109(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__109")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_UNITFORMATION, ID, EQUALS);
    r = r && unit_formation(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_PERCENTAGEUNITKILLED ID comparison_op INT
  private static boolean condition__110(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__110")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_PERCENTAGEUNITKILLED, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_UNITPERCENTAGEAMMOLEFT ID comparison_op INT
  private static boolean condition__111(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__111")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_UNITPERCENTAGEAMMOLEFT, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_UNITDISTANCEFROMPOSITION ID INT INT comparison_op INT
  private static boolean condition__112(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__112")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_UNITDISTANCEFROMPOSITION, ID, INT, INT);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_UNITDISTANCEFROMLINE ID ID ID comparison_op INT
  private static boolean condition__113(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__113")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_UNITDISTANCEFROMLINE, ID, ID, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_UNITDISTANCEFROMUNIT ID ID comparison_op INT
  private static boolean condition__114(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__114")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_UNITDISTANCEFROMUNIT, ID, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_PERCENTAGEUNITGROUPKILLED ID comparison_op INT
  private static boolean condition__122(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__122")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_PERCENTAGEUNITGROUPKILLED, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_UNITGROUPDISTANCEFROMPOSITION ID INT comparison_op INT
  private static boolean condition__124(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__124")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_UNITGROUPDISTANCEFROMPOSITION, ID, INT);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_UNITGROUPDISTANCEFROMGROUP ID ID comparison_op INT
  private static boolean condition__125(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__125")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_UNITGROUPDISTANCEFROMGROUP, ID, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_SPECIFICUNITSSELECTED ID*
  private static boolean condition__135(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__135")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_SPECIFICUNITSSELECTED);
    r = r && condition__135_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ID*
  private static boolean condition__135_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__135_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, ID)) break;
      if (!empty_element_parsed_guard_(b, "condition__135_1", c)) break;
    }
    return true;
  }

  // I_BATTLEENEMYARMYPERCENTAGEOFMATCHINGUNITS ID comparison_op number
  private static boolean condition__137(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__137")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_BATTLEENEMYARMYPERCENTAGEOFMATCHINGUNITS, ID);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEENEMYARMYNUMBEROFMATCHINGUNITS ID comparison_op INT
  private static boolean condition__138(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__138")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_BATTLEENEMYARMYNUMBEROFMATCHINGUNITS, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEPLAYERARMYPERCENTAGEOFMATCHINGUNITS ID comparison_op number
  private static boolean condition__139(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__139")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_BATTLEPLAYERARMYPERCENTAGEOFMATCHINGUNITS, ID);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_BATTLEPLAYERARMYNUMBEROFMATCHINGUNITS ID comparison_op INT
  private static boolean condition__140(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__140")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_BATTLEPLAYERARMYNUMBEROFMATCHINGUNITS, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // TRAIT ID comparison_op INT
  private static boolean condition__143(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__143")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, TRAIT, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // FATHERTRAIT ID comparison_op INT
  private static boolean condition__145(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__145")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FATHERTRAIT, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // FACTIONLEADERTRAIT ID comparison_op INT
  private static boolean condition__146(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__146")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FACTIONLEADERTRAIT, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // ATTRIBUTE ID comparison_op INT
  private static boolean condition__147(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__147")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ATTRIBUTE, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // REMAININGMPPERCENTAGE comparison_op number
  private static boolean condition__148(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__148")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REMAININGMPPERCENTAGE);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_REMAININGMPPERCENTAGE name_ comparison_op number
  private static boolean condition__149(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__149")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_REMAININGMPPERCENTAGE);
    r = r && name_(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_CHARACTERCANMOVE name_
  private static boolean condition__150(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__150")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_CHARACTERCANMOVE);
    r = r && name_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // AGENTTYPE EQUALS character_type
  private static boolean condition__152(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__152")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AGENTTYPE, EQUALS);
    r = r && character_type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // TRAINEDAGENTTYPE EQUALS character_type
  private static boolean condition__153(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__153")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, TRAINEDAGENTTYPE, EQUALS);
    r = r && character_type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DISASTERTYPE disaster_type
  private static boolean condition__154(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__154")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DISASTERTYPE);
    r = r && disaster_type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ADVISEACTION EQUALS character_action_advice
  private static boolean condition__173(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__173")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ADVISEACTION, EQUALS);
    r = r && character_action_advice(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_CHARACTERTYPENEARCHARACTERTYPE ID character_type INT ID character_type
  private static boolean condition__174(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__174")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_CHARACTERTYPENEARCHARACTERTYPE, ID);
    r = r && character_type(b, l + 1);
    r = r && consumeTokens(b, 0, INT, ID);
    r = r && character_type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_CHARACTERTYPENEARTILE ID character_type INT coords
  private static boolean condition__175(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__175")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_CHARACTERTYPENEARTILE, ID);
    r = r && character_type(b, l + 1);
    r = r && consumeToken(b, INT);
    r = r && coords(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_CHARACTERNAMENEARTILE name_ character_type INT coords
  private static boolean condition__176(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__176")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_CHARACTERNAMENEARTILE);
    r = r && name_(b, l + 1);
    r = r && character_type(b, l + 1);
    r = r && consumeToken(b, INT);
    r = r && coords(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // TRADINGRESOURCE ID?
  private static boolean condition__177(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__177")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TRADINGRESOURCE);
    r = r && condition__177_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ID?
  private static boolean condition__177_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__177_1")) return false;
    consumeToken(b, ID);
    return true;
  }

  // DISTANCECAPITAL comparison_op INT
  private static boolean condition__180(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__180")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DISTANCECAPITAL);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // DISTANCEHOME comparison_op INT
  private static boolean condition__181(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__181")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DISTANCEHOME);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // REGIONMERCHANTCOUNT comparison_op INT
  private static boolean condition__183(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__183")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REGIONMERCHANTCOUNT);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // PRECIOUSMINECOUNT comparison_op INT
  private static boolean condition__184(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__184")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PRECIOUSMINECOUNT);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // TRAINEDUNITCATEGORY unit_category
  private static boolean condition__193(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__193")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TRAINEDUNITCATEGORY);
    r = r && unit_category(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // UNITCATEGORY EQUALS unit_category
  private static boolean condition__194(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__194")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, UNITCATEGORY, EQUALS);
    r = r && unit_category(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SENATEMISSIONTIMEREMAINING comparison_op INT
  private static boolean condition__195(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__195")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SENATEMISSIONTIMEREMAINING);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // MEDIANTAXLEVEL comparison_op tax_level
  private static boolean condition__196(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__196")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MEDIANTAXLEVEL);
    r = r && comparison_op(b, l + 1);
    r = r && tax_level(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // MODETAXLEVEL comparison_op tax_level
  private static boolean condition__197(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__197")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MODETAXLEVEL);
    r = r && comparison_op(b, l + 1);
    r = r && tax_level(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_MODETAXLEVEL ID comparison_op tax_level
  private static boolean condition__198(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__198")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_MODETAXLEVEL, ID);
    r = r && comparison_op(b, l + 1);
    r = r && tax_level(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // MISSIONSUCCESSLEVEL comparison_op success_level
  private static boolean condition__199(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__199")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MISSIONSUCCESSLEVEL);
    r = r && comparison_op(b, l + 1);
    r = r && success_level(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DIPLOMATICSTANCEFROMCHARACTER name_ comparison_op diplomatic_stance
  private static boolean condition__203(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__203")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DIPLOMATICSTANCEFROMCHARACTER);
    r = r && name_(b, l + 1);
    r = r && comparison_op(b, l + 1);
    r = r && diplomatic_stance(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DIPLOMATICSTANCEFROMFACTION ID comparison_op diplomatic_stance
  private static boolean condition__204(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__204")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DIPLOMATICSTANCEFROMFACTION, ID);
    r = r && comparison_op(b, l + 1);
    r = r && diplomatic_stance(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SUPPORTCOSTSPERCENTAGE comparison_op number
  private static boolean condition__209(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__209")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SUPPORTCOSTSPERCENTAGE);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // TREASURY comparison_op INT
  private static boolean condition__210(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__210")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TREASURY);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_NUMBEROFSETTLEMENTS ID comparison_op INT
  private static boolean condition__214(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__214")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_NUMBEROFSETTLEMENTS, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_NUMBEROFHEIRS ID comparison_op INT
  private static boolean condition__215(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__215")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_NUMBEROFHEIRS, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_FACTIONNEARTILE ID INT coords
  private static boolean condition__216(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__216")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_FACTIONNEARTILE, ID, INT);
    r = r && coords(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SETTLEMENTSTAKEN comparison_op INT
  private static boolean condition__217(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__217")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SETTLEMENTSTAKEN);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLESFOUGHT comparison_op INT
  private static boolean condition__218(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__218")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLESFOUGHT);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLESWON comparison_op INT
  private static boolean condition__219(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__219")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLESWON);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLESLOST comparison_op INT
  private static boolean condition__220(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__220")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BATTLESLOST);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // DEFENSIVESIEGESFOUGHT comparison_op INT
  private static boolean condition__221(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__221")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DEFENSIVESIEGESFOUGHT);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // DEFENSIVESIEGESWON comparison_op INT
  private static boolean condition__222(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__222")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DEFENSIVESIEGESWON);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // OFFENSIVESIEGESFOUGHT comparison_op INT
  private static boolean condition__223(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__223")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OFFENSIVESIEGESFOUGHT);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // OFFENSIVESIEGESWON comparison_op INT
  private static boolean condition__224(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__224")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OFFENSIVESIEGESWON);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // LOCALPLAYERBATTLESFOUGHT comparison_op INT
  private static boolean condition__230(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__230")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LOCALPLAYERBATTLESFOUGHT);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // TOGGLED ID+
  private static boolean condition__231(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__231")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TOGGLED);
    r = r && condition__231_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ID+
  private static boolean condition__231_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__231_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, ID)) break;
      if (!empty_element_parsed_guard_(b, "condition__231_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // RANDOMPERCENT comparison_op INT
  private static boolean condition__233(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__233")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RANDOMPERCENT);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // RECRUITMENTITEMCLICKED name_
  private static boolean condition__239(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__239")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RECRUITMENTITEMCLICKED);
    r = r && name_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CHARACTERNAME name_
  private static boolean condition__240(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__240")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CHARACTERNAME);
    r = r && name_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BATTLEUNITACTIONSTATUS ID action_status
  private static boolean condition__243(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__243")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BATTLEUNITACTIONSTATUS, ID);
    r = r && action_status(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_AMOUNTOFUNITINSETTLEMENT ID INT name_
  private static boolean condition__244(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__244")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_AMOUNTOFUNITINSETTLEMENT, ID, INT);
    r = r && name_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_UNITCARDSELECTED name_ INT
  private static boolean condition__245(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__245")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_UNITCARDSELECTED);
    r = r && name_(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_COMPARECOUNTER ID comparison_op INT
  private static boolean condition__246(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__246")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_COMPARECOUNTER, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_TIMERELAPSED ID ID comparison_op INT
  private static boolean condition__249(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__249")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_TIMERELAPSED, ID, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // SETTLEMENTBUILDINGEXISTS comparison_op ID
  private static boolean condition__256(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__256")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SETTLEMENTBUILDINGEXISTS);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // HOMESETTLEMENTBUILDINGEXISTS comparison_op ID
  private static boolean condition__257(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__257")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HOMESETTLEMENTBUILDINGEXISTS);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // SETTLEMENTORDERLEVEL law comparison_op INT
  private static boolean condition__258(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__258")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SETTLEMENTORDERLEVEL, LAW);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // SETTLEMENTCAPABILITYLEVEL ID comparison_op INT
  private static boolean condition__259(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__259")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SETTLEMENTCAPABILITYLEVEL, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // SETTLEMENTBUILDINGFINISHED comparison_op ID
  private static boolean condition__261(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__261")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SETTLEMENTBUILDINGFINISHED);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // GOVERNORPLUGINEXISTS comparison_op ID
  private static boolean condition__262(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__262")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GOVERNORPLUGINEXISTS);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // GOVERNORPLUGINFINISHED comparison_op ID
  private static boolean condition__263(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__263")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GOVERNORPLUGINFINISHED);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // GOVERNORTAXLEVEL comparison_op tax_level
  private static boolean condition__264(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__264")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GOVERNORTAXLEVEL);
    r = r && comparison_op(b, l + 1);
    r = r && tax_level(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SETTLEMENTTAXLEVEL comparison_op tax_level
  private static boolean condition__265(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__265")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SETTLEMENTTAXLEVEL);
    r = r && comparison_op(b, l + 1);
    r = r && tax_level(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // GOVERNORLOYALTYLEVEL comparison_op loyalty_to_governor_level
  private static boolean condition__267(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__267")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GOVERNORLOYALTYLEVEL);
    r = r && comparison_op(b, l + 1);
    r = r && loyalty_to_governor_level(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SETTLEMENTLOYALTYLEVEL comparison_op loyalty_to_governor_level
  private static boolean condition__268(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__268")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SETTLEMENTLOYALTYLEVEL);
    r = r && comparison_op(b, l + 1);
    r = r && loyalty_to_governor_level(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // RIOTRISK comparison_op 30
  private static boolean condition__269(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__269")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RIOTRISK);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, "30");
    exit_section_(b, m, null, r);
    return r;
  }

  // I_SETTLEMENTLEVEL ID comparison_op settlement_level
  private static boolean condition__275(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__275")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_SETTLEMENTLEVEL, ID);
    r = r && comparison_op(b, l + 1);
    r = r && settlement_level(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ADVISERECRUIT EQUALS name_
  private static boolean condition__278(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__278")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ADVISERECRUIT, EQUALS);
    r = r && name_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SETTLEMENTAUTOMANAGED automanage_type
  private static boolean condition__281(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__281")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SETTLEMENTAUTOMANAGED);
    r = r && automanage_type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FERALSETTLEMENTAUTOMANAGED automanage_type
  private static boolean condition__282(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__282")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FERALSETTLEMENTAUTOMANAGED);
    r = r && automanage_type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PERCENTAGEOFPOPULATIONINGARRISON comparison_op INT
  private static boolean condition__283(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__283")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PERCENTAGEOFPOPULATIONINGARRISON);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // GARRISONTOPOPULATIONRATIO comparison_op number
  private static boolean condition__284(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__284")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GARRISONTOPOPULATIONRATIO);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // GARRISONSETTLEMENTRATIO comparison_op number
  private static boolean condition__285(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__285")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GARRISONSETTLEMENTRATIO);
    r = r && comparison_op(b, l + 1);
    r = r && number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // HEALTHPERCENTAGE comparison_op INT
  private static boolean condition__286(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__286")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HEALTHPERCENTAGE);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_NUMBERUNITSINSETTLEMENT ID ID comparison_op INT
  private static boolean condition__291(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__291")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_NUMBERUNITSINSETTLEMENT, ID, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_TURNNUMBER comparison_op INT
  private static boolean condition__305(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__305")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_TURNNUMBER);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_THREADCOUNT ID comparison_op INT
  private static boolean condition__307(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__307")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, I_THREADCOUNT, ID);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_ADVISORVERBOSITY comparison_op INT
  private static boolean condition__310(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__310")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_ADVISORVERBOSITY);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_CHARACTERSELECTED name_
  private static boolean condition__312(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__312")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_CHARACTERSELECTED);
    r = r && name_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // I_AGENTSELECTED character_type
  private static boolean condition__313(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__313")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_AGENTSELECTED);
    r = r && character_type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FACTIONBUILDINGEXISTS comparison_op ID
  private static boolean condition__328(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__328")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FACTIONBUILDINGEXISTS);
    r = r && comparison_op(b, l + 1);
    r = r && consumeToken(b, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ALIAS ID OCB
  //                        requirement
  //                        DISPLAY_STRING ID
  //                      CCB
  public static boolean condition_alias(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition_alias")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONDITION_ALIAS, "<condition alias>");
    r = consumeTokens(b, 1, ALIAS, ID, OCB);
    p = r; // pin = 1
    r = r && report_error_(b, requirement(b, l + 1));
    r = p && report_error_(b, consumeTokens(b, -1, DISPLAY_STRING, ID, CCB)) && r;
    exit_section_(b, l, m, r, p, RRParser::aliasRecover);
    return r || p;
  }

  /* ********************************************************** */
  // SUCCESSFULAMBUSH|FAILEDAMBUSH|NORMAL|SIEGE|SALLYBESIEGER|NAVAL|WITHDRAW
  public static boolean conflict_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conflict_type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONFLICT_TYPE, "<conflict type>");
    r = consumeToken(b, SUCCESSFULAMBUSH);
    if (!r) r = consumeToken(b, FAILEDAMBUSH);
    if (!r) r = consumeToken(b, NORMAL);
    if (!r) r = consumeToken(b, SIEGE);
    if (!r) r = consumeToken(b, SALLYBESIEGER);
    if (!r) r = consumeToken(b, NAVAL);
    if (!r) r = consumeToken(b, WITHDRAW);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INT COMMA INT
  public static boolean coords(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "coords")) return false;
    if (!nextTokenIs(b, INT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, INT, COMMA, INT);
    exit_section_(b, m, COORDS, r);
    return r;
  }

  /* ********************************************************** */
  // X INT COMMA Y INT
  public static boolean coords_with_xy(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "coords_with_xy")) return false;
    if (!nextTokenIs(b, X)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, X, INT, COMMA, Y, INT);
    exit_section_(b, m, COORDS_WITH_XY, r);
    return r;
  }

  /* ********************************************************** */
  // CORE_ATTITUDES faction_ref COMMA INT <<list faction_ref>>
  public static boolean core_attitudes_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "core_attitudes_item")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CORE_ATTITUDES_ITEM, "<core attitudes item>");
    r = consumeToken(b, CORE_ATTITUDES);
    p = r; // pin = 1
    r = r && report_error_(b, faction_ref(b, l + 1));
    r = p && report_error_(b, consumeTokens(b, -1, COMMA, INT)) && r;
    r = p && list(b, l + 1, RRParser::faction_ref) && r;
    exit_section_(b, l, m, r, p, RRParser::core_attitudes_recover);
    return r || p;
  }

  /* ********************************************************** */
  // !(CORE_ATTITUDES|FACTION_RELATIONSHIPS)
  static boolean core_attitudes_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "core_attitudes_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !core_attitudes_recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // CORE_ATTITUDES|FACTION_RELATIONSHIPS
  private static boolean core_attitudes_recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "core_attitudes_recover_0")) return false;
    boolean r;
    r = consumeToken(b, CORE_ATTITUDES);
    if (!r) r = consumeToken(b, FACTION_RELATIONSHIPS);
    return r;
  }

  /* ********************************************************** */
  // STRING
  public static boolean culture_name_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "culture_name_decl")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    exit_section_(b, m, CULTURE_NAME_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // DESCR_CULTURES_MARKER
  //                    STRING COLON OSB
  //                      (culture_name_decl COLON KVF_value COMMA)+
  //                    CSB COMMA
  public static boolean descr_cultures(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_cultures")) return false;
    if (!nextTokenIs(b, DESCR_CULTURES_MARKER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DESCR_CULTURES_MARKER, STRING, COLON, OSB);
    r = r && descr_cultures_4(b, l + 1);
    r = r && consumeTokens(b, 0, CSB, COMMA);
    exit_section_(b, m, DESCR_CULTURES, r);
    return r;
  }

  // (culture_name_decl COLON KVF_value COMMA)+
  private static boolean descr_cultures_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_cultures_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = descr_cultures_4_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!descr_cultures_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "descr_cultures_4", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // culture_name_decl COLON KVF_value COMMA
  private static boolean descr_cultures_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_cultures_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = culture_name_decl(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && KVF_value(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DESCR_FACTION_GROUPS_MARKER
  //                          faction_group*
  public static boolean descr_faction_groups(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_faction_groups")) return false;
    if (!nextTokenIs(b, DESCR_FACTION_GROUPS_MARKER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DESCR_FACTION_GROUPS_MARKER);
    r = r && descr_faction_groups_1(b, l + 1);
    exit_section_(b, m, DESCR_FACTION_GROUPS, r);
    return r;
  }

  // faction_group*
  private static boolean descr_faction_groups_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_faction_groups_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!faction_group(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "descr_faction_groups_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // mercenary_pool+
  public static boolean descr_mercenaries(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_mercenaries")) return false;
    if (!nextTokenIs(b, POOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = mercenary_pool(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!mercenary_pool(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "descr_mercenaries", c)) break;
    }
    exit_section_(b, m, DESCR_MERCENARIES, r);
    return r;
  }

  /* ********************************************************** */
  // names_for_faction+
  public static boolean descr_names(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_names")) return false;
    if (!nextTokenIs(b, FACTION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = names_for_faction(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!names_for_faction(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "descr_names", c)) break;
    }
    exit_section_(b, m, DESCR_NAMES, r);
    return r;
  }

  /* ********************************************************** */
  // region_def+
  public static boolean descr_regions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_regions")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = region_def(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!region_def(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "descr_regions", c)) break;
    }
    exit_section_(b, m, DESCR_REGIONS, r);
    return r;
  }

  /* ********************************************************** */
  // DESCR_SM_FACTIONS_MARKER
  //                       STRING COLON OSB
  //                         faction_decl+
  //                       CSB COMMA
  public static boolean descr_sm_factions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_sm_factions")) return false;
    if (!nextTokenIs(b, DESCR_SM_FACTIONS_MARKER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DESCR_SM_FACTIONS_MARKER, STRING, COLON, OSB);
    r = r && descr_sm_factions_4(b, l + 1);
    r = r && consumeTokens(b, 0, CSB, COMMA);
    exit_section_(b, m, DESCR_SM_FACTIONS, r);
    return r;
  }

  // faction_decl+
  private static boolean descr_sm_factions_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_sm_factions_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = faction_decl(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!faction_decl(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "descr_sm_factions_4", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // campaign_section
  //                 landmark_section?
  //                 resources_section
  //                 sound_emitters_section?
  //                 events_section?
  //                 factions_section
  //                 diplomacy_section
  //                 SCRIPT TXT_FILE (COMMA ONCE_ONLY)?
  public static boolean descr_strat(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_strat")) return false;
    if (!nextTokenIs(b, CAMPAIGN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = campaign_section(b, l + 1);
    r = r && descr_strat_1(b, l + 1);
    r = r && resources_section(b, l + 1);
    r = r && descr_strat_3(b, l + 1);
    r = r && descr_strat_4(b, l + 1);
    r = r && factions_section(b, l + 1);
    r = r && diplomacy_section(b, l + 1);
    r = r && consumeTokens(b, 0, SCRIPT, TXT_FILE);
    r = r && descr_strat_9(b, l + 1);
    exit_section_(b, m, DESCR_STRAT, r);
    return r;
  }

  // landmark_section?
  private static boolean descr_strat_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_strat_1")) return false;
    landmark_section(b, l + 1);
    return true;
  }

  // sound_emitters_section?
  private static boolean descr_strat_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_strat_3")) return false;
    sound_emitters_section(b, l + 1);
    return true;
  }

  // events_section?
  private static boolean descr_strat_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_strat_4")) return false;
    events_section(b, l + 1);
    return true;
  }

  // (COMMA ONCE_ONLY)?
  private static boolean descr_strat_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_strat_9")) return false;
    descr_strat_9_0(b, l + 1);
    return true;
  }

  // COMMA ONCE_ONLY
  private static boolean descr_strat_9_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_strat_9_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, ONCE_ONLY);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SKIN OCB skin_* CCB
  //                          ETHNICITIES OCB ethnicity_* CCB
  //                          REGION OCB ethnicity_makeup_* CCB
  public static boolean descr_unit_variation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_unit_variation")) return false;
    if (!nextTokenIs(b, SKIN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SKIN, OCB);
    r = r && descr_unit_variation_2(b, l + 1);
    r = r && consumeTokens(b, 0, CCB, ETHNICITIES, OCB);
    r = r && descr_unit_variation_6(b, l + 1);
    r = r && consumeTokens(b, 0, CCB, REGION, OCB);
    r = r && descr_unit_variation_10(b, l + 1);
    r = r && consumeToken(b, CCB);
    exit_section_(b, m, DESCR_UNIT_VARIATION, r);
    return r;
  }

  // skin_*
  private static boolean descr_unit_variation_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_unit_variation_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!skin_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "descr_unit_variation_2", c)) break;
    }
    return true;
  }

  // ethnicity_*
  private static boolean descr_unit_variation_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_unit_variation_6")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ethnicity_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "descr_unit_variation_6", c)) break;
    }
    return true;
  }

  // ethnicity_makeup_*
  private static boolean descr_unit_variation_10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "descr_unit_variation_10")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ethnicity_makeup_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "descr_unit_variation_10", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // core_attitudes_item+
  //                       faction_relationships_item+
  //                       superfaction_setup_item*
  public static boolean diplomacy_section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "diplomacy_section")) return false;
    if (!nextTokenIs(b, CORE_ATTITUDES)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = diplomacy_section_0(b, l + 1);
    r = r && diplomacy_section_1(b, l + 1);
    r = r && diplomacy_section_2(b, l + 1);
    exit_section_(b, m, DIPLOMACY_SECTION, r);
    return r;
  }

  // core_attitudes_item+
  private static boolean diplomacy_section_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "diplomacy_section_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = core_attitudes_item(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!core_attitudes_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "diplomacy_section_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // faction_relationships_item+
  private static boolean diplomacy_section_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "diplomacy_section_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = faction_relationships_item(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!faction_relationships_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "diplomacy_section_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // superfaction_setup_item*
  private static boolean diplomacy_section_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "diplomacy_section_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!superfaction_setup_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "diplomacy_section_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ALLIED|SUSPICIOUS|NEUTRAL|HOSTILE|ATWAR
  public static boolean diplomatic_stance(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "diplomatic_stance")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DIPLOMATIC_STANCE, "<diplomatic stance>");
    r = consumeToken(b, ALLIED);
    if (!r) r = consumeToken(b, SUSPICIOUS);
    if (!r) r = consumeToken(b, NEUTRAL);
    if (!r) r = consumeToken(b, HOSTILE);
    if (!r) r = consumeToken(b, ATWAR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // EARTHQUAKE|FLOOD|HORDE|FIRE|RIOT|STORM|VOLCANO
  public static boolean disaster_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "disaster_type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DISASTER_TYPE, "<disaster type>");
    r = consumeToken(b, EARTHQUAKE);
    if (!r) r = consumeToken(b, FLOOD);
    if (!r) r = consumeToken(b, HORDE);
    if (!r) r = consumeToken(b, FIRE);
    if (!r) r = consumeToken(b, RIOT);
    if (!r) r = consumeToken(b, STORM);
    if (!r) r = consumeToken(b, VOLCANO);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (
  //                         TAXABLE_INCOME_BONUS|TRADE_BASE_INCOME_BONUS|
  //                         TRADE_FLEET|FARMING_LEVEL|ROAD_LEVEL|MINE_RESOURCE|
  //                         HAPPINESS_BONUS|LAW_BONUS|
  //                         POPULATION_HEALTH_BONUS|POPULATION_GROWTH_BONUS|
  //                         WALL_LEVEL|TOWER_LEVEL|GATE_STRENGTH|GATE_DEFENCES|
  //                         RECRUITS_EXP_BONUS|RECRUITS_MORALE_BONUS|
  //                         WEAPON_SIMPLE|WEAPON_BLADED|WEAPON_MISSILE|ARMOUR|
  //                         STAGE_RACES|STAGE_GAMES|
  //                         AGENT_LIMIT_SETTLEMENT agent_type|
  //                         DUMMY CAPABILITY_TRAITSANDRETINUE // what is that?
  //                       ) BONUS? INT
  static boolean effect_capability(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "effect_capability")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = effect_capability_0(b, l + 1);
    r = r && effect_capability_1(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // TAXABLE_INCOME_BONUS|TRADE_BASE_INCOME_BONUS|
  //                         TRADE_FLEET|FARMING_LEVEL|ROAD_LEVEL|MINE_RESOURCE|
  //                         HAPPINESS_BONUS|LAW_BONUS|
  //                         POPULATION_HEALTH_BONUS|POPULATION_GROWTH_BONUS|
  //                         WALL_LEVEL|TOWER_LEVEL|GATE_STRENGTH|GATE_DEFENCES|
  //                         RECRUITS_EXP_BONUS|RECRUITS_MORALE_BONUS|
  //                         WEAPON_SIMPLE|WEAPON_BLADED|WEAPON_MISSILE|ARMOUR|
  //                         STAGE_RACES|STAGE_GAMES|
  //                         AGENT_LIMIT_SETTLEMENT agent_type|
  //                         DUMMY CAPABILITY_TRAITSANDRETINUE
  private static boolean effect_capability_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "effect_capability_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TAXABLE_INCOME_BONUS);
    if (!r) r = consumeToken(b, TRADE_BASE_INCOME_BONUS);
    if (!r) r = consumeToken(b, TRADE_FLEET);
    if (!r) r = consumeToken(b, FARMING_LEVEL);
    if (!r) r = consumeToken(b, ROAD_LEVEL);
    if (!r) r = consumeToken(b, MINE_RESOURCE);
    if (!r) r = consumeToken(b, HAPPINESS_BONUS);
    if (!r) r = consumeToken(b, LAW_BONUS);
    if (!r) r = consumeToken(b, POPULATION_HEALTH_BONUS);
    if (!r) r = consumeToken(b, POPULATION_GROWTH_BONUS);
    if (!r) r = consumeToken(b, WALL_LEVEL);
    if (!r) r = consumeToken(b, TOWER_LEVEL);
    if (!r) r = consumeToken(b, GATE_STRENGTH);
    if (!r) r = consumeToken(b, GATE_DEFENCES);
    if (!r) r = consumeToken(b, RECRUITS_EXP_BONUS);
    if (!r) r = consumeToken(b, RECRUITS_MORALE_BONUS);
    if (!r) r = consumeToken(b, WEAPON_SIMPLE);
    if (!r) r = consumeToken(b, WEAPON_BLADED);
    if (!r) r = consumeToken(b, WEAPON_MISSILE);
    if (!r) r = consumeToken(b, ARMOUR);
    if (!r) r = consumeToken(b, STAGE_RACES);
    if (!r) r = consumeToken(b, STAGE_GAMES);
    if (!r) r = effect_capability_0_22(b, l + 1);
    if (!r) r = parseTokens(b, 0, DUMMY, CAPABILITY_TRAITSANDRETINUE);
    exit_section_(b, m, null, r);
    return r;
  }

  // AGENT_LIMIT_SETTLEMENT agent_type
  private static boolean effect_capability_0_22(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "effect_capability_0_22")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AGENT_LIMIT_SETTLEMENT);
    r = r && agent_type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BONUS?
  private static boolean effect_capability_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "effect_capability_1")) return false;
    consumeToken(b, BONUS);
    return true;
  }

  /* ********************************************************** */
  // ethnicity_name_decl OCB
  //             HAIR_STYLES <<list (OP INT COMMA FLOAT CP)>>
  //             FEMALE_HAIR <<list (OP INT COMMA FLOAT CP)>>
  //             BEARD_STYLES <<list (OP INT COMMA FLOAT CP)>>
  //             HAIR_COLOR <<list (OP ID COMMA FLOAT CP)>>
  //             AGED_HAIR_COLOR <<list (OP ID COMMA FLOAT CP)>>
  //           CCB
  public static boolean ethnicity_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ethnicity_")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ethnicity_name_decl(b, l + 1);
    r = r && consumeTokens(b, 0, OCB, HAIR_STYLES);
    r = r && list(b, l + 1, RRParser::ethnicity__3_0);
    r = r && consumeToken(b, FEMALE_HAIR);
    r = r && list(b, l + 1, RRParser::ethnicity__5_0);
    r = r && consumeToken(b, BEARD_STYLES);
    r = r && list(b, l + 1, RRParser::ethnicity__7_0);
    r = r && consumeToken(b, HAIR_COLOR);
    r = r && list(b, l + 1, RRParser::ethnicity__9_0);
    r = r && consumeToken(b, AGED_HAIR_COLOR);
    r = r && list(b, l + 1, RRParser::ethnicity__11_0);
    r = r && consumeToken(b, CCB);
    exit_section_(b, m, ETHNICITY_, r);
    return r;
  }

  // OP INT COMMA FLOAT CP
  private static boolean ethnicity__3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ethnicity__3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OP, INT, COMMA, FLOAT, CP);
    exit_section_(b, m, null, r);
    return r;
  }

  // OP INT COMMA FLOAT CP
  private static boolean ethnicity__5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ethnicity__5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OP, INT, COMMA, FLOAT, CP);
    exit_section_(b, m, null, r);
    return r;
  }

  // OP INT COMMA FLOAT CP
  private static boolean ethnicity__7_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ethnicity__7_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OP, INT, COMMA, FLOAT, CP);
    exit_section_(b, m, null, r);
    return r;
  }

  // OP ID COMMA FLOAT CP
  private static boolean ethnicity__9_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ethnicity__9_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OP, ID, COMMA, FLOAT, CP);
    exit_section_(b, m, null, r);
    return r;
  }

  // OP ID COMMA FLOAT CP
  private static boolean ethnicity__11_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ethnicity__11_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OP, ID, COMMA, FLOAT, CP);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ethnicity_makeup_name_decl OCB
  //               (VARIANT OCB
  //                  ETHNICITY ethnicity_ref
  //                  DISTRIBUTION INT
  //                CCB)*
  //             CCB
  public static boolean ethnicity_makeup_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ethnicity_makeup_")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ethnicity_makeup_name_decl(b, l + 1);
    r = r && consumeToken(b, OCB);
    r = r && ethnicity_makeup__2(b, l + 1);
    r = r && consumeToken(b, CCB);
    exit_section_(b, m, ETHNICITY_MAKEUP_, r);
    return r;
  }

  // (VARIANT OCB
  //                  ETHNICITY ethnicity_ref
  //                  DISTRIBUTION INT
  //                CCB)*
  private static boolean ethnicity_makeup__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ethnicity_makeup__2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ethnicity_makeup__2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ethnicity_makeup__2", c)) break;
    }
    return true;
  }

  // VARIANT OCB
  //                  ETHNICITY ethnicity_ref
  //                  DISTRIBUTION INT
  //                CCB
  private static boolean ethnicity_makeup__2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ethnicity_makeup__2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, VARIANT, OCB, ETHNICITY);
    r = r && ethnicity_ref(b, l + 1);
    r = r && consumeTokens(b, 0, DISTRIBUTION, INT, CCB);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean ethnicity_makeup_name_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ethnicity_makeup_name_decl")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, ETHNICITY_MAKEUP_NAME_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean ethnicity_makeup_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ethnicity_makeup_ref")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, ETHNICITY_MAKEUP_REF, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean ethnicity_name_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ethnicity_name_decl")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, ETHNICITY_NAME_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean ethnicity_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ethnicity_ref")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, ETHNICITY_REF, r);
    return r;
  }

  /* ********************************************************** */
  // PREBATTLE|PREBATTLEWITHDRAWAL|BATTLEAICOMMENCED|BATTLEDELAYPHASECOMMENCED|BATTLEDEPLOYMENTPHASECOMMENCED|BATTLECONFLICTPHASECOMMENCED|BATTLEPLAYERUNITATTACKSENEMYUNIT|BATTLEENEMYUNITATTACKSPLAYERUNIT|BATTLEPLAYERATTACKSSETTLEMENTBUILDING|BATTLEENEMYATTACKSSETTLEMENTBUILDING|BATTLEUNITGOESBERSERK|BATTLEPLAYERUNITGOESBERSERK|BATTLEENEMYUNITGOESBERSERK|BATTLEUNITROUTS|BATTLEPLAYERUNITROUTS|BATTLEENEMYUNITROUTS|BATTLEPLAYERSIEGEENGINEDESTROYED|BATTLEENEMYSIEGEENGINEDESTROYED|POSTBATTLE|BATTLEARMYROUTED|BATTLEGENERALKILLED|BATTLEGENERALROUTED|BATTLEREINFORCEMENTSARRIVE|BATTLESIEGEENGINEDESTROYED|BATTLESIEGEENGINEDOCKSWALL|BATTLEGATESATTACKEDBYENGINE|BATTLEGATESATTACKEDBYPLAYERENGINE|BATTLEGATESATTACKEDBYENEMYENGINE|BATTLEBATTLEGATESDESTROYEDBYENGINE|BATTLEWALLSBREACHEDBYENGINE|BATTLEWALLSCAPTURED|BATTLEFINISHED|ENTEREDCITYVIEW|BATTLEMINIMAPACTION|BATTLEPLAYERUNITSELECTED|ENTERTACTICALMODE|BATTLEREINFORCEMENTSHACK|FACTIONTURNSTART|FACTIONWARDECLARED|HORDEFORMED|FACTIONTURNEND|HIREMERCENARIES|GENERALCAPTURERESIDENCE|GENERALCAPTUREWONDER|GENERALCAPTURESETTLEMENT|LEADERDESTROYEDFACTION|DISASTER|CHARACTERDAMAGEDBYDISASTER|GENERALASSAULTSRESIDENCE|OFFEREDFORADOPTION|LESSERGENERALOFFEREDFORADOPTION|OFFEREDFORMARRIAGE|BROTHERADOPTED|BECOMESFACTIONLEADER|BECOMESFACTIONHEIR|TAKEOFFICE|CEASEDFACTIONLEADER|CEASEDFACTIONHEIR|LEAVEOFFICE|UNGARRISONEDFORT|LOSTLEGIONARYEAGLE|CAPTUREDLEGIONARYEAGLE|RECAPTUREDLEGIONARYEAGLE|SENATEEXPOSURE|QUAESTORINVESTIGATIONMINOR|QUAESTORINVESTIGATION|QUAESTORINVESTIGATIONMAJOR|POPULARSUPPORTFOROVERTHROW|SENATEREADYTOOUTLAWFACTION|SENATEOUTLAWSFACTION|NEWTURNSTART|SCRIPTPROMPTCALLBACK|FACTIONDESTROYED|BIRTH|CHARACTERCOMESOFAGE|CHARACTERMARRIES|CHARACTERBECOMESAFATHER|CHARACTERTURNSTART|CHARACTERTURNEND|CHARACTERTURNENDINSETTLEMENT|GENERALDEVASTATESTILE|SPYMISSION|EXECUTESASPYONAMISSION|LEADERORDEREDSPYINGMISSION|ASSASSINATIONMISSION|EXECUTESANASSASSINONAMISSION|LEADERORDEREDASSASSINATION|SUFFERASSASSINATIONATTEMPT|SABOTAGEMISSION|LEADERORDEREDSABOTAGE|BRIBERYMISSION|LEADERORDEREDBRIBERY|ACCEPTBRIBE|REFUSEBRIBE|INSURRECTION|DIPLOMACYMISSION|LEADERORDEREDDIPLOMACY|LEADERSENATEMISSIONSUCCESS|LEADERSENATEMISSIONFAILED|CONSTRUCTIONITEMCLICKED|RECRUITMENTITEMCLICKED|RECRUITMENTPOPULATED|CONSTRUCTIONPOPULATED|AGENTLISTPOPULATED|MOVERETINUEPOPULATED|MOVERETINUEPRESSED|MOVERETINUEANCILLARYSELECTED|MOVERETINUEANCILLARYDESELECTED|MISSIONSELECTED|AGENTSELECTED|SCROLLDIDOPEN|UNITHASROUTED|BATTLEUNITACTIONSTATUS|DIPLOMACYSCROLLPOPULATED|ITEMDESELECTED|UNITINFOOPENED|ADVISORAUDIOSTOPPED|SETTLEMENTTURNSTART|SETTLEMENTTURNEND|NEWADMIRALCREATED|UNITTRAINED|GOVERNORUNITTRAINED|BUILDINGCOMPLETED|GOVERNORBUILDINGCOMPLETED|PLUGINCOMPLETED|GOVERNORPLUGINCOMPLETED|AGENTCREATED|GOVERNORAGENTCREATED|BUILDINGDESTROYED|GOVERNORBUILDINGDESTROYED|CITYRIOTS|GOVERNORCITYRIOTS|CITYREBELS|GOVERNORCITYREBELS|GOVERNORTHROWGAMES|GOVERNORTHROWRACES|UNGARRISONEDSETTLEMENT|ENSLAVEPOPULATION|EXTERMINATEPOPULATION|CITYSACKED|CHARACTERSELECTED|SETTLEMENTSELECTED|ENEMYSETTLEMENTSELECTED|MULTITURNMOVE|CHARACTERPANELOPEN|SETTLEMENTPANELOPEN|FINANCESPANELOPEN|FACTIONSUMMARYPANELOPEN|FAMILYTREEPANELOPEN|DIPLOMATICSTANDINGPANELOPEN|SENATEMISSIONSPANELOPEN|SENATEOFFICESPANELOPEN|DIPLOMACYPANELOPEN|PREBATTLEPANELOPEN|RECRUITMENTPANELOPEN|CONSTRUCTIONPANELOPEN|TRADEPANELOPEN|HIREMERCENARIESPANELOPEN|NAVALAUTORESOLVEPANELOPEN|INCOMINGMESSAGE|MESSAGEOPEN|REQUESTBUILDINGADVICE|REQUESTTRAININGADVICE|REQUESTMERCENARIESADVICE|BUTTONPRESSED|SHORTCUTTRIGGERED|SCROLLOPENED|SCROLLCLOSED|ADVICESUPRESSED|SCROLLADVICEREQUESTED|PREBATTLESCROLLADVICEREQUESTED|NAVALPREBATTLESCROLLADVICEREQUESTED|SETTLEMENTSCROLLADVICEREQUESTED|IDLE|ABANDONSHOWME|SCRIPTEDADVICE|DECLINEAUTOMATEDSETTLEMENTMANAGEMENT|ESCPRESSED|GAMERELOADED|FIRSTSTRATUPDATES|MOVIESTOPPED|SELECTIONASSISTPOSSIBLE|SETTLEMENTBUTTONPRESSED|WORLDSCRIPTTERMINATE|CAMPAIGNHUDSHOWN|CONTEXTPOPUPINTERACTION|DIPLOMACYCONSTRUCTINGOFFER|DIPLOMACYCONSTRUCTINGCOUNTEROFFER|DIPLOMACYOPPONENTPRESENTSOFFER|DIPLOMACYOPPONENTPRESENTSCOUNTEROFFER|FACTIONSUMMARY|FACTIONSENATE|FACTIONSENATEPOLICY|FACTIONSENATEMISSIONS|FACTIONSENATEOFFICIALS|FACTIONSENATEFLOOR|FACTIONFACTIONS|FACTIONDETAILS|FACTIONFAMILYTREE|FACTIONRANKINGS|FACTIONLISTS|SETTLEMENTCHARACTER|SETTLEMENTTRADE|SETTLEMENTOVERVIEW|SPYSELECTED|DIPLOMATSELECTED|ASSASSINSELECTED|FLEETSELECTED|CAMPAIGNMAPGESTURE|CAMPAIGNDOINGBADLY|BATTLEMAPGESTURE|HIDEBATTLEUI|FERALNEWSVISIBLE|AGENTHUBOPENED|MOVERETINUEOPENED|OWNFACTIONDETAILSOPENED|DIPLOMATICSTANDINGSHOWN|FACTIONFINANCESSHOWN|FAMILYTREESHOWN|SENDAGENTPANEL|SETTLEMENTDETAILSSHOWN|CHARACTERINFOSCREEN|FRIENDLYCHARACTERSELECTED|ENEMYCHARACTERSELECTED|FRIENDLYSETTLEMENTSELECTED|MAPOVERLAYOPENED|SIEGEDETAILSSHOWN|PREBATTLESCREEN|TACTICALMAPSHOWN|POSTBATTLESCREEN|UNITSGROUPED|ENTEREDBATTLE|ADVISOROPENED|FORMATIONTYPESSHOWN|MERCHANTSELECTED|NAVALCOMBATSTARTED|MERGEARMIESOPENED|ROUTESBLOCKADED|ELECTIONRESULTS|BATTLETOGGLEMENU|AMBUSHMODE|NEWSTABCLOSED|NEWSTABOPENED|BATTLENEWSTABOPENED|QUICKLISTSOPENED|EMBARGOISAVAILABLE|REBELCHARACTERSELECTED|HIGHTAXESCAUSEDISORDER|FAILEDTOENDTURN|
  // ACQUISITIONMISSION|SUFFERACQUISITIONATTEMPT
  public static boolean event(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "event")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EVENT, "<event>");
    r = consumeToken(b, PREBATTLE);
    if (!r) r = consumeToken(b, PREBATTLEWITHDRAWAL);
    if (!r) r = consumeToken(b, BATTLEAICOMMENCED);
    if (!r) r = consumeToken(b, BATTLEDELAYPHASECOMMENCED);
    if (!r) r = consumeToken(b, BATTLEDEPLOYMENTPHASECOMMENCED);
    if (!r) r = consumeToken(b, BATTLECONFLICTPHASECOMMENCED);
    if (!r) r = consumeToken(b, BATTLEPLAYERUNITATTACKSENEMYUNIT);
    if (!r) r = consumeToken(b, BATTLEENEMYUNITATTACKSPLAYERUNIT);
    if (!r) r = consumeToken(b, BATTLEPLAYERATTACKSSETTLEMENTBUILDING);
    if (!r) r = consumeToken(b, BATTLEENEMYATTACKSSETTLEMENTBUILDING);
    if (!r) r = consumeToken(b, BATTLEUNITGOESBERSERK);
    if (!r) r = consumeToken(b, BATTLEPLAYERUNITGOESBERSERK);
    if (!r) r = consumeToken(b, BATTLEENEMYUNITGOESBERSERK);
    if (!r) r = consumeToken(b, BATTLEUNITROUTS);
    if (!r) r = consumeToken(b, BATTLEPLAYERUNITROUTS);
    if (!r) r = consumeToken(b, BATTLEENEMYUNITROUTS);
    if (!r) r = consumeToken(b, BATTLEPLAYERSIEGEENGINEDESTROYED);
    if (!r) r = consumeToken(b, BATTLEENEMYSIEGEENGINEDESTROYED);
    if (!r) r = consumeToken(b, POSTBATTLE);
    if (!r) r = consumeToken(b, BATTLEARMYROUTED);
    if (!r) r = consumeToken(b, BATTLEGENERALKILLED);
    if (!r) r = consumeToken(b, BATTLEGENERALROUTED);
    if (!r) r = consumeToken(b, BATTLEREINFORCEMENTSARRIVE);
    if (!r) r = consumeToken(b, BATTLESIEGEENGINEDESTROYED);
    if (!r) r = consumeToken(b, BATTLESIEGEENGINEDOCKSWALL);
    if (!r) r = consumeToken(b, BATTLEGATESATTACKEDBYENGINE);
    if (!r) r = consumeToken(b, BATTLEGATESATTACKEDBYPLAYERENGINE);
    if (!r) r = consumeToken(b, BATTLEGATESATTACKEDBYENEMYENGINE);
    if (!r) r = consumeToken(b, BATTLEBATTLEGATESDESTROYEDBYENGINE);
    if (!r) r = consumeToken(b, BATTLEWALLSBREACHEDBYENGINE);
    if (!r) r = consumeToken(b, BATTLEWALLSCAPTURED);
    if (!r) r = consumeToken(b, BATTLEFINISHED);
    if (!r) r = consumeToken(b, ENTEREDCITYVIEW);
    if (!r) r = consumeToken(b, BATTLEMINIMAPACTION);
    if (!r) r = consumeToken(b, BATTLEPLAYERUNITSELECTED);
    if (!r) r = consumeToken(b, ENTERTACTICALMODE);
    if (!r) r = consumeToken(b, BATTLEREINFORCEMENTSHACK);
    if (!r) r = consumeToken(b, FACTIONTURNSTART);
    if (!r) r = consumeToken(b, FACTIONWARDECLARED);
    if (!r) r = consumeToken(b, HORDEFORMED);
    if (!r) r = consumeToken(b, FACTIONTURNEND);
    if (!r) r = consumeToken(b, HIREMERCENARIES);
    if (!r) r = consumeToken(b, GENERALCAPTURERESIDENCE);
    if (!r) r = consumeToken(b, GENERALCAPTUREWONDER);
    if (!r) r = consumeToken(b, GENERALCAPTURESETTLEMENT);
    if (!r) r = consumeToken(b, LEADERDESTROYEDFACTION);
    if (!r) r = consumeToken(b, DISASTER);
    if (!r) r = consumeToken(b, CHARACTERDAMAGEDBYDISASTER);
    if (!r) r = consumeToken(b, GENERALASSAULTSRESIDENCE);
    if (!r) r = consumeToken(b, OFFEREDFORADOPTION);
    if (!r) r = consumeToken(b, LESSERGENERALOFFEREDFORADOPTION);
    if (!r) r = consumeToken(b, OFFEREDFORMARRIAGE);
    if (!r) r = consumeToken(b, BROTHERADOPTED);
    if (!r) r = consumeToken(b, BECOMESFACTIONLEADER);
    if (!r) r = consumeToken(b, BECOMESFACTIONHEIR);
    if (!r) r = consumeToken(b, TAKEOFFICE);
    if (!r) r = consumeToken(b, CEASEDFACTIONLEADER);
    if (!r) r = consumeToken(b, CEASEDFACTIONHEIR);
    if (!r) r = consumeToken(b, LEAVEOFFICE);
    if (!r) r = consumeToken(b, UNGARRISONEDFORT);
    if (!r) r = consumeToken(b, LOSTLEGIONARYEAGLE);
    if (!r) r = consumeToken(b, CAPTUREDLEGIONARYEAGLE);
    if (!r) r = consumeToken(b, RECAPTUREDLEGIONARYEAGLE);
    if (!r) r = consumeToken(b, SENATEEXPOSURE);
    if (!r) r = consumeToken(b, QUAESTORINVESTIGATIONMINOR);
    if (!r) r = consumeToken(b, QUAESTORINVESTIGATION);
    if (!r) r = consumeToken(b, QUAESTORINVESTIGATIONMAJOR);
    if (!r) r = consumeToken(b, POPULARSUPPORTFOROVERTHROW);
    if (!r) r = consumeToken(b, SENATEREADYTOOUTLAWFACTION);
    if (!r) r = consumeToken(b, SENATEOUTLAWSFACTION);
    if (!r) r = consumeToken(b, NEWTURNSTART);
    if (!r) r = consumeToken(b, SCRIPTPROMPTCALLBACK);
    if (!r) r = consumeToken(b, FACTIONDESTROYED);
    if (!r) r = consumeToken(b, BIRTH);
    if (!r) r = consumeToken(b, CHARACTERCOMESOFAGE);
    if (!r) r = consumeToken(b, CHARACTERMARRIES);
    if (!r) r = consumeToken(b, CHARACTERBECOMESAFATHER);
    if (!r) r = consumeToken(b, CHARACTERTURNSTART);
    if (!r) r = consumeToken(b, CHARACTERTURNEND);
    if (!r) r = consumeToken(b, CHARACTERTURNENDINSETTLEMENT);
    if (!r) r = consumeToken(b, GENERALDEVASTATESTILE);
    if (!r) r = consumeToken(b, SPYMISSION);
    if (!r) r = consumeToken(b, EXECUTESASPYONAMISSION);
    if (!r) r = consumeToken(b, LEADERORDEREDSPYINGMISSION);
    if (!r) r = consumeToken(b, ASSASSINATIONMISSION);
    if (!r) r = consumeToken(b, EXECUTESANASSASSINONAMISSION);
    if (!r) r = consumeToken(b, LEADERORDEREDASSASSINATION);
    if (!r) r = consumeToken(b, SUFFERASSASSINATIONATTEMPT);
    if (!r) r = consumeToken(b, SABOTAGEMISSION);
    if (!r) r = consumeToken(b, LEADERORDEREDSABOTAGE);
    if (!r) r = consumeToken(b, BRIBERYMISSION);
    if (!r) r = consumeToken(b, LEADERORDEREDBRIBERY);
    if (!r) r = consumeToken(b, ACCEPTBRIBE);
    if (!r) r = consumeToken(b, REFUSEBRIBE);
    if (!r) r = consumeToken(b, INSURRECTION);
    if (!r) r = consumeToken(b, DIPLOMACYMISSION);
    if (!r) r = consumeToken(b, LEADERORDEREDDIPLOMACY);
    if (!r) r = consumeToken(b, LEADERSENATEMISSIONSUCCESS);
    if (!r) r = consumeToken(b, LEADERSENATEMISSIONFAILED);
    if (!r) r = consumeToken(b, CONSTRUCTIONITEMCLICKED);
    if (!r) r = consumeToken(b, RECRUITMENTITEMCLICKED);
    if (!r) r = consumeToken(b, RECRUITMENTPOPULATED);
    if (!r) r = consumeToken(b, CONSTRUCTIONPOPULATED);
    if (!r) r = consumeToken(b, AGENTLISTPOPULATED);
    if (!r) r = consumeToken(b, MOVERETINUEPOPULATED);
    if (!r) r = consumeToken(b, MOVERETINUEPRESSED);
    if (!r) r = consumeToken(b, MOVERETINUEANCILLARYSELECTED);
    if (!r) r = consumeToken(b, MOVERETINUEANCILLARYDESELECTED);
    if (!r) r = consumeToken(b, MISSIONSELECTED);
    if (!r) r = consumeToken(b, AGENTSELECTED);
    if (!r) r = consumeToken(b, SCROLLDIDOPEN);
    if (!r) r = consumeToken(b, UNITHASROUTED);
    if (!r) r = consumeToken(b, BATTLEUNITACTIONSTATUS);
    if (!r) r = consumeToken(b, DIPLOMACYSCROLLPOPULATED);
    if (!r) r = consumeToken(b, ITEMDESELECTED);
    if (!r) r = consumeToken(b, UNITINFOOPENED);
    if (!r) r = consumeToken(b, ADVISORAUDIOSTOPPED);
    if (!r) r = consumeToken(b, SETTLEMENTTURNSTART);
    if (!r) r = consumeToken(b, SETTLEMENTTURNEND);
    if (!r) r = consumeToken(b, NEWADMIRALCREATED);
    if (!r) r = consumeToken(b, UNITTRAINED);
    if (!r) r = consumeToken(b, GOVERNORUNITTRAINED);
    if (!r) r = consumeToken(b, BUILDINGCOMPLETED);
    if (!r) r = consumeToken(b, GOVERNORBUILDINGCOMPLETED);
    if (!r) r = consumeToken(b, PLUGINCOMPLETED);
    if (!r) r = consumeToken(b, GOVERNORPLUGINCOMPLETED);
    if (!r) r = consumeToken(b, AGENTCREATED);
    if (!r) r = consumeToken(b, GOVERNORAGENTCREATED);
    if (!r) r = consumeToken(b, BUILDINGDESTROYED);
    if (!r) r = consumeToken(b, GOVERNORBUILDINGDESTROYED);
    if (!r) r = consumeToken(b, CITYRIOTS);
    if (!r) r = consumeToken(b, GOVERNORCITYRIOTS);
    if (!r) r = consumeToken(b, CITYREBELS);
    if (!r) r = consumeToken(b, GOVERNORCITYREBELS);
    if (!r) r = consumeToken(b, GOVERNORTHROWGAMES);
    if (!r) r = consumeToken(b, GOVERNORTHROWRACES);
    if (!r) r = consumeToken(b, UNGARRISONEDSETTLEMENT);
    if (!r) r = consumeToken(b, ENSLAVEPOPULATION);
    if (!r) r = consumeToken(b, EXTERMINATEPOPULATION);
    if (!r) r = consumeToken(b, CITYSACKED);
    if (!r) r = consumeToken(b, CHARACTERSELECTED);
    if (!r) r = consumeToken(b, SETTLEMENTSELECTED);
    if (!r) r = consumeToken(b, ENEMYSETTLEMENTSELECTED);
    if (!r) r = consumeToken(b, MULTITURNMOVE);
    if (!r) r = consumeToken(b, CHARACTERPANELOPEN);
    if (!r) r = consumeToken(b, SETTLEMENTPANELOPEN);
    if (!r) r = consumeToken(b, FINANCESPANELOPEN);
    if (!r) r = consumeToken(b, FACTIONSUMMARYPANELOPEN);
    if (!r) r = consumeToken(b, FAMILYTREEPANELOPEN);
    if (!r) r = consumeToken(b, DIPLOMATICSTANDINGPANELOPEN);
    if (!r) r = consumeToken(b, SENATEMISSIONSPANELOPEN);
    if (!r) r = consumeToken(b, SENATEOFFICESPANELOPEN);
    if (!r) r = consumeToken(b, DIPLOMACYPANELOPEN);
    if (!r) r = consumeToken(b, PREBATTLEPANELOPEN);
    if (!r) r = consumeToken(b, RECRUITMENTPANELOPEN);
    if (!r) r = consumeToken(b, CONSTRUCTIONPANELOPEN);
    if (!r) r = consumeToken(b, TRADEPANELOPEN);
    if (!r) r = consumeToken(b, HIREMERCENARIESPANELOPEN);
    if (!r) r = consumeToken(b, NAVALAUTORESOLVEPANELOPEN);
    if (!r) r = consumeToken(b, INCOMINGMESSAGE);
    if (!r) r = consumeToken(b, MESSAGEOPEN);
    if (!r) r = consumeToken(b, REQUESTBUILDINGADVICE);
    if (!r) r = consumeToken(b, REQUESTTRAININGADVICE);
    if (!r) r = consumeToken(b, REQUESTMERCENARIESADVICE);
    if (!r) r = consumeToken(b, BUTTONPRESSED);
    if (!r) r = consumeToken(b, SHORTCUTTRIGGERED);
    if (!r) r = consumeToken(b, SCROLLOPENED);
    if (!r) r = consumeToken(b, SCROLLCLOSED);
    if (!r) r = consumeToken(b, ADVICESUPRESSED);
    if (!r) r = consumeToken(b, SCROLLADVICEREQUESTED);
    if (!r) r = consumeToken(b, PREBATTLESCROLLADVICEREQUESTED);
    if (!r) r = consumeToken(b, NAVALPREBATTLESCROLLADVICEREQUESTED);
    if (!r) r = consumeToken(b, SETTLEMENTSCROLLADVICEREQUESTED);
    if (!r) r = consumeToken(b, IDLE);
    if (!r) r = consumeToken(b, ABANDONSHOWME);
    if (!r) r = consumeToken(b, SCRIPTEDADVICE);
    if (!r) r = consumeToken(b, DECLINEAUTOMATEDSETTLEMENTMANAGEMENT);
    if (!r) r = consumeToken(b, ESCPRESSED);
    if (!r) r = consumeToken(b, GAMERELOADED);
    if (!r) r = consumeToken(b, FIRSTSTRATUPDATES);
    if (!r) r = consumeToken(b, MOVIESTOPPED);
    if (!r) r = consumeToken(b, SELECTIONASSISTPOSSIBLE);
    if (!r) r = consumeToken(b, SETTLEMENTBUTTONPRESSED);
    if (!r) r = consumeToken(b, WORLDSCRIPTTERMINATE);
    if (!r) r = consumeToken(b, CAMPAIGNHUDSHOWN);
    if (!r) r = consumeToken(b, CONTEXTPOPUPINTERACTION);
    if (!r) r = consumeToken(b, DIPLOMACYCONSTRUCTINGOFFER);
    if (!r) r = consumeToken(b, DIPLOMACYCONSTRUCTINGCOUNTEROFFER);
    if (!r) r = consumeToken(b, DIPLOMACYOPPONENTPRESENTSOFFER);
    if (!r) r = consumeToken(b, DIPLOMACYOPPONENTPRESENTSCOUNTEROFFER);
    if (!r) r = consumeToken(b, FACTIONSUMMARY);
    if (!r) r = consumeToken(b, FACTIONSENATE);
    if (!r) r = consumeToken(b, FACTIONSENATEPOLICY);
    if (!r) r = consumeToken(b, FACTIONSENATEMISSIONS);
    if (!r) r = consumeToken(b, FACTIONSENATEOFFICIALS);
    if (!r) r = consumeToken(b, FACTIONSENATEFLOOR);
    if (!r) r = consumeToken(b, FACTIONFACTIONS);
    if (!r) r = consumeToken(b, FACTIONDETAILS);
    if (!r) r = consumeToken(b, FACTIONFAMILYTREE);
    if (!r) r = consumeToken(b, FACTIONRANKINGS);
    if (!r) r = consumeToken(b, FACTIONLISTS);
    if (!r) r = consumeToken(b, SETTLEMENTCHARACTER);
    if (!r) r = consumeToken(b, SETTLEMENTTRADE);
    if (!r) r = consumeToken(b, SETTLEMENTOVERVIEW);
    if (!r) r = consumeToken(b, SPYSELECTED);
    if (!r) r = consumeToken(b, DIPLOMATSELECTED);
    if (!r) r = consumeToken(b, ASSASSINSELECTED);
    if (!r) r = consumeToken(b, FLEETSELECTED);
    if (!r) r = consumeToken(b, CAMPAIGNMAPGESTURE);
    if (!r) r = consumeToken(b, CAMPAIGNDOINGBADLY);
    if (!r) r = consumeToken(b, BATTLEMAPGESTURE);
    if (!r) r = consumeToken(b, HIDEBATTLEUI);
    if (!r) r = consumeToken(b, FERALNEWSVISIBLE);
    if (!r) r = consumeToken(b, AGENTHUBOPENED);
    if (!r) r = consumeToken(b, MOVERETINUEOPENED);
    if (!r) r = consumeToken(b, OWNFACTIONDETAILSOPENED);
    if (!r) r = consumeToken(b, DIPLOMATICSTANDINGSHOWN);
    if (!r) r = consumeToken(b, FACTIONFINANCESSHOWN);
    if (!r) r = consumeToken(b, FAMILYTREESHOWN);
    if (!r) r = consumeToken(b, SENDAGENTPANEL);
    if (!r) r = consumeToken(b, SETTLEMENTDETAILSSHOWN);
    if (!r) r = consumeToken(b, CHARACTERINFOSCREEN);
    if (!r) r = consumeToken(b, FRIENDLYCHARACTERSELECTED);
    if (!r) r = consumeToken(b, ENEMYCHARACTERSELECTED);
    if (!r) r = consumeToken(b, FRIENDLYSETTLEMENTSELECTED);
    if (!r) r = consumeToken(b, MAPOVERLAYOPENED);
    if (!r) r = consumeToken(b, SIEGEDETAILSSHOWN);
    if (!r) r = consumeToken(b, PREBATTLESCREEN);
    if (!r) r = consumeToken(b, TACTICALMAPSHOWN);
    if (!r) r = consumeToken(b, POSTBATTLESCREEN);
    if (!r) r = consumeToken(b, UNITSGROUPED);
    if (!r) r = consumeToken(b, ENTEREDBATTLE);
    if (!r) r = consumeToken(b, ADVISOROPENED);
    if (!r) r = consumeToken(b, FORMATIONTYPESSHOWN);
    if (!r) r = consumeToken(b, MERCHANTSELECTED);
    if (!r) r = consumeToken(b, NAVALCOMBATSTARTED);
    if (!r) r = consumeToken(b, MERGEARMIESOPENED);
    if (!r) r = consumeToken(b, ROUTESBLOCKADED);
    if (!r) r = consumeToken(b, ELECTIONRESULTS);
    if (!r) r = consumeToken(b, BATTLETOGGLEMENU);
    if (!r) r = consumeToken(b, AMBUSHMODE);
    if (!r) r = consumeToken(b, NEWSTABCLOSED);
    if (!r) r = consumeToken(b, NEWSTABOPENED);
    if (!r) r = consumeToken(b, BATTLENEWSTABOPENED);
    if (!r) r = consumeToken(b, QUICKLISTSOPENED);
    if (!r) r = consumeToken(b, EMBARGOISAVAILABLE);
    if (!r) r = consumeToken(b, REBELCHARACTERSELECTED);
    if (!r) r = consumeToken(b, HIGHTAXESCAUSEDISORDER);
    if (!r) r = consumeToken(b, FAILEDTOENDTURN);
    if (!r) r = consumeToken(b, ACQUISITIONMISSION);
    if (!r) r = consumeToken(b, SUFFERACQUISITIONATTEMPT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  public static boolean events_section(PsiBuilder b, int l) {
    Marker m = enter_section_(b);
    exit_section_(b, m, EVENTS_SECTION, true);
    return true;
  }

  /* ********************************************************** */
  // EXPORT_BUILDINGS_MARKER
  //                      (OCB buried_building_tree_or_level_ref CCB STRING)+
  public static boolean export_buildings(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "export_buildings")) return false;
    if (!nextTokenIs(b, EXPORT_BUILDINGS_MARKER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXPORT_BUILDINGS_MARKER);
    r = r && export_buildings_1(b, l + 1);
    exit_section_(b, m, EXPORT_BUILDINGS, r);
    return r;
  }

  // (OCB buried_building_tree_or_level_ref CCB STRING)+
  private static boolean export_buildings_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "export_buildings_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = export_buildings_1_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!export_buildings_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "export_buildings_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // OCB buried_building_tree_or_level_ref CCB STRING
  private static boolean export_buildings_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "export_buildings_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OCB);
    r = r && buried_building_tree_or_level_ref(b, l + 1);
    r = r && consumeTokens(b, 0, CCB, STRING);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ancillary_def+ ancillary_trigger_def+
  public static boolean export_descr_ancillaries(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "export_descr_ancillaries")) return false;
    if (!nextTokenIs(b, ANCILLARY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = export_descr_ancillaries_0(b, l + 1);
    r = r && export_descr_ancillaries_1(b, l + 1);
    exit_section_(b, m, EXPORT_DESCR_ANCILLARIES, r);
    return r;
  }

  // ancillary_def+
  private static boolean export_descr_ancillaries_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "export_descr_ancillaries_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ancillary_def(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!ancillary_def(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "export_descr_ancillaries_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // ancillary_trigger_def+
  private static boolean export_descr_ancillaries_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "export_descr_ancillaries_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ancillary_trigger_def(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!ancillary_trigger_def(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "export_descr_ancillaries_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // tags_
  //                            condition_alias*
  //                            building_tree+
  public static boolean export_descr_buildings(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "export_descr_buildings")) return false;
    if (!nextTokenIs(b, TAGS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tags_(b, l + 1);
    r = r && export_descr_buildings_1(b, l + 1);
    r = r && export_descr_buildings_2(b, l + 1);
    exit_section_(b, m, EXPORT_DESCR_BUILDINGS, r);
    return r;
  }

  // condition_alias*
  private static boolean export_descr_buildings_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "export_descr_buildings_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!condition_alias(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "export_descr_buildings_1", c)) break;
    }
    return true;
  }

  // building_tree+
  private static boolean export_descr_buildings_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "export_descr_buildings_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = building_tree(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!building_tree(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "export_descr_buildings_2", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // trait_def+ trait_trigger_def+
  public static boolean export_descr_traits(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "export_descr_traits")) return false;
    if (!nextTokenIs(b, TRAIT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = export_descr_traits_0(b, l + 1);
    r = r && export_descr_traits_1(b, l + 1);
    exit_section_(b, m, EXPORT_DESCR_TRAITS, r);
    return r;
  }

  // trait_def+
  private static boolean export_descr_traits_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "export_descr_traits_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = trait_def(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!trait_def(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "export_descr_traits_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // trait_trigger_def+
  private static boolean export_descr_traits_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "export_descr_traits_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = trait_trigger_def(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!trait_trigger_def(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "export_descr_traits_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // unit_item_+
  public static boolean export_descr_unit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "export_descr_unit")) return false;
    if (!nextTokenIs(b, TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = unit_item_(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!unit_item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "export_descr_unit", c)) break;
    }
    exit_section_(b, m, EXPORT_DESCR_UNIT, r);
    return r;
  }

  /* ********************************************************** */
  // faction_name_decl COLON OCB
  //                    STRING COLON STRING COMMA
  //                    STRING COLON STRING COMMA
  //                    STRING COLON str_culture_ref COMMA
  //                    STRING COLON str_ethnicity_ref COMMA
  //                    STRING COLON OSB (<<list str_faction_group_ref>> COMMA)? CSB COMMA
  //                    KVF_item+
  //                  CCB COMMA
  public static boolean faction_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_decl")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = faction_name_decl(b, l + 1);
    r = r && consumeTokens(b, 0, COLON, OCB, STRING, COLON, STRING, COMMA, STRING, COLON, STRING, COMMA, STRING, COLON);
    r = r && str_culture_ref(b, l + 1);
    r = r && consumeTokens(b, 0, COMMA, STRING, COLON);
    r = r && str_ethnicity_ref(b, l + 1);
    r = r && consumeTokens(b, 0, COMMA, STRING, COLON, OSB);
    r = r && faction_decl_22(b, l + 1);
    r = r && consumeTokens(b, 0, CSB, COMMA);
    r = r && faction_decl_25(b, l + 1);
    r = r && consumeTokens(b, 0, CCB, COMMA);
    exit_section_(b, m, FACTION_DECL, r);
    return r;
  }

  // (<<list str_faction_group_ref>> COMMA)?
  private static boolean faction_decl_22(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_decl_22")) return false;
    faction_decl_22_0(b, l + 1);
    return true;
  }

  // <<list str_faction_group_ref>> COMMA
  private static boolean faction_decl_22_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_decl_22_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = list(b, l + 1, RRParser::str_faction_group_ref);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // KVF_item+
  private static boolean faction_decl_25(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_decl_25")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = KVF_item(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!KVF_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "faction_decl_25", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // GROUP faction_group_name_decl
  //                   (FACTION faction_ref)*
  public static boolean faction_group(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_group")) return false;
    if (!nextTokenIs(b, GROUP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GROUP);
    r = r && faction_group_name_decl(b, l + 1);
    r = r && faction_group_2(b, l + 1);
    exit_section_(b, m, FACTION_GROUP, r);
    return r;
  }

  // (FACTION faction_ref)*
  private static boolean faction_group_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_group_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!faction_group_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "faction_group_2", c)) break;
    }
    return true;
  }

  // FACTION faction_ref
  private static boolean faction_group_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_group_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FACTION);
    r = r && faction_ref(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean faction_group_name_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_group_name_decl")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, FACTION_GROUP_NAME_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // FACTION faction_ref COMMA ai_personality_ref
  //                                  (SUPERFACTION ID)?
  //                                  DENARI INT
  //                                  settlement_item*
  //                                  character_item*
  //                                  character_record_item*
  //                                  relative_item*
  public static boolean faction_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_item")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FACTION_ITEM, "<faction item>");
    r = consumeToken(b, FACTION);
    p = r; // pin = 1
    r = r && report_error_(b, faction_ref(b, l + 1));
    r = p && report_error_(b, consumeToken(b, COMMA)) && r;
    r = p && report_error_(b, ai_personality_ref(b, l + 1)) && r;
    r = p && report_error_(b, faction_item_4(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, DENARI, INT)) && r;
    r = p && report_error_(b, faction_item_7(b, l + 1)) && r;
    r = p && report_error_(b, faction_item_8(b, l + 1)) && r;
    r = p && report_error_(b, faction_item_9(b, l + 1)) && r;
    r = p && faction_item_10(b, l + 1) && r;
    exit_section_(b, l, m, r, p, RRParser::faction_recover);
    return r || p;
  }

  // (SUPERFACTION ID)?
  private static boolean faction_item_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_item_4")) return false;
    faction_item_4_0(b, l + 1);
    return true;
  }

  // SUPERFACTION ID
  private static boolean faction_item_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_item_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SUPERFACTION, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // settlement_item*
  private static boolean faction_item_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_item_7")) return false;
    while (true) {
      int c = current_position_(b);
      if (!settlement_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "faction_item_7", c)) break;
    }
    return true;
  }

  // character_item*
  private static boolean faction_item_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_item_8")) return false;
    while (true) {
      int c = current_position_(b);
      if (!character_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "faction_item_8", c)) break;
    }
    return true;
  }

  // character_record_item*
  private static boolean faction_item_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_item_9")) return false;
    while (true) {
      int c = current_position_(b);
      if (!character_record_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "faction_item_9", c)) break;
    }
    return true;
  }

  // relative_item*
  private static boolean faction_item_10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_item_10")) return false;
    while (true) {
      int c = current_position_(b);
      if (!relative_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "faction_item_10", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // STRING
  public static boolean faction_name_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_name_decl")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    exit_section_(b, m, FACTION_NAME_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean faction_or_culture_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_or_culture_ref")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, FACTION_OR_CULTURE_REF, r);
    return r;
  }

  /* ********************************************************** */
  // !(FACTION|CORE_ATTITUDES)
  static boolean faction_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !faction_recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // FACTION|CORE_ATTITUDES
  private static boolean faction_recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_recover_0")) return false;
    boolean r;
    r = consumeToken(b, FACTION);
    if (!r) r = consumeToken(b, CORE_ATTITUDES);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean faction_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_ref")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, FACTION_REF, r);
    return r;
  }

  /* ********************************************************** */
  // FACTION_RELATIONSHIPS faction_ref COMMA INT <<list faction_ref>>
  public static boolean faction_relationships_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_relationships_item")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FACTION_RELATIONSHIPS_ITEM, "<faction relationships item>");
    r = consumeToken(b, FACTION_RELATIONSHIPS);
    p = r; // pin = 1
    r = r && report_error_(b, faction_ref(b, l + 1));
    r = p && report_error_(b, consumeTokens(b, -1, COMMA, INT)) && r;
    r = p && list(b, l + 1, RRParser::faction_ref) && r;
    exit_section_(b, l, m, r, p, RRParser::faction_relationships_recover);
    return r || p;
  }

  /* ********************************************************** */
  // !(FACTION_RELATIONSHIPS|SUPERFACTION_SETUP|SCRIPT)
  static boolean faction_relationships_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_relationships_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !faction_relationships_recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // FACTION_RELATIONSHIPS|SUPERFACTION_SETUP|SCRIPT
  private static boolean faction_relationships_recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "faction_relationships_recover_0")) return false;
    boolean r;
    r = consumeToken(b, FACTION_RELATIONSHIPS);
    if (!r) r = consumeToken(b, SUPERFACTION_SETUP);
    if (!r) r = consumeToken(b, SCRIPT);
    return r;
  }

  /* ********************************************************** */
  // FACTIONS OCB <<list faction_or_culture_ref>> COMMA CCB
  public static boolean factions_c(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "factions_c")) return false;
    if (!nextTokenIs(b, FACTIONS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FACTIONS_C, null);
    r = consumeTokens(b, 1, FACTIONS, OCB);
    p = r; // pin = 1
    r = r && report_error_(b, list(b, l + 1, RRParser::faction_or_culture_ref));
    r = p && report_error_(b, consumeTokens(b, -1, COMMA, CCB)) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // faction_item*
  public static boolean factions_section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "factions_section")) return false;
    Marker m = enter_section_(b, l, _NONE_, FACTIONS_SECTION, "<factions section>");
    while (true) {
      int c = current_position_(b);
      if (!faction_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "factions_section", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // FERAL_DESCR_AI_PERSONALITY_MARKER
  //                                ai_building_priority+
  //                                ai_military_priority+
  //                                ai_diplomatic_priority+
  //                                ai_personality+
  public static boolean feral_descr_ai_personality(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "feral_descr_ai_personality")) return false;
    if (!nextTokenIs(b, FERAL_DESCR_AI_PERSONALITY_MARKER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FERAL_DESCR_AI_PERSONALITY_MARKER);
    r = r && feral_descr_ai_personality_1(b, l + 1);
    r = r && feral_descr_ai_personality_2(b, l + 1);
    r = r && feral_descr_ai_personality_3(b, l + 1);
    r = r && feral_descr_ai_personality_4(b, l + 1);
    exit_section_(b, m, FERAL_DESCR_AI_PERSONALITY, r);
    return r;
  }

  // ai_building_priority+
  private static boolean feral_descr_ai_personality_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "feral_descr_ai_personality_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ai_building_priority(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!ai_building_priority(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "feral_descr_ai_personality_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // ai_military_priority+
  private static boolean feral_descr_ai_personality_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "feral_descr_ai_personality_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ai_military_priority(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!ai_military_priority(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "feral_descr_ai_personality_2", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // ai_diplomatic_priority+
  private static boolean feral_descr_ai_personality_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "feral_descr_ai_personality_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ai_diplomatic_priority(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!ai_diplomatic_priority(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "feral_descr_ai_personality_3", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // ai_personality+
  private static boolean feral_descr_ai_personality_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "feral_descr_ai_personality_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ai_personality(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!ai_personality(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "feral_descr_ai_personality_4", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // HOT_SAND|BURNING_OIL
  public static boolean gate_defence_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "gate_defence_type")) return false;
    if (!nextTokenIs(b, "<gate defence type>", BURNING_OIL, HOT_SAND)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, GATE_DEFENCE_TYPE, "<gate defence type>");
    r = consumeToken(b, HOT_SAND);
    if (!r) r = consumeToken(b, BURNING_OIL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // HIDDEN_RESOURCE ID
  public static boolean hidden_resource_c(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hidden_resource_c")) return false;
    if (!nextTokenIs(b, HIDDEN_RESOURCE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, HIDDEN_RESOURCE_C, null);
    r = consumeTokens(b, 1, HIDDEN_RESOURCE, ID);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // HIDE_FOREST|HIDE_IMPROVED_FOREST|HIDE_LONG_GRASS|HIDE_ANYWHERE
  public static boolean hide_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hide_type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, HIDE_TYPE, "<hide type>");
    r = consumeToken(b, HIDE_FOREST);
    if (!r) r = consumeToken(b, HIDE_IMPROVED_FOREST);
    if (!r) r = consumeToken(b, HIDE_LONG_GRASS);
    if (!r) r = consumeToken(b, HIDE_ANYWHERE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IS_TOGGLED STRING
  public static boolean is_toggled_c(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "is_toggled_c")) return false;
    if (!nextTokenIs(b, IS_TOGGLED)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, IS_TOGGLED_C, null);
    r = consumeTokens(b, 1, IS_TOGGLED, STRING);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // KVF_item
  public static boolean kv_format(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "kv_format")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = KVF_item(b, l + 1);
    exit_section_(b, m, KV_FORMAT, r);
    return r;
  }

  /* ********************************************************** */
  // LANDMARK ID INT COMMA INT
  public static boolean landmark_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "landmark_item")) return false;
    if (!nextTokenIs(b, LANDMARK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LANDMARK, ID, INT, COMMA, INT);
    exit_section_(b, m, LANDMARK_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // landmark_item*
  public static boolean landmark_section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "landmark_section")) return false;
    Marker m = enter_section_(b, l, _NONE_, LANDMARK_SECTION, "<landmark section>");
    while (true) {
      int c = current_position_(b);
      if (!landmark_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "landmark_section", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // <<item>> (COMMA <<item>>)*
  static boolean list(PsiBuilder b, int l, Parser _item) {
    if (!recursion_guard_(b, l, "list")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = _item.parse(b, l);
    r = r && list_1(b, l + 1, _item);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA <<item>>)*
  private static boolean list_1(PsiBuilder b, int l, Parser _item) {
    if (!recursion_guard_(b, l, "list_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!list_1_0(b, l + 1, _item)) break;
      if (!empty_element_parsed_guard_(b, "list_1", c)) break;
    }
    return true;
  }

  // COMMA <<item>>
  private static boolean list_1_0(PsiBuilder b, int l, Parser _item) {
    if (!recursion_guard_(b, l, "list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && _item.parse(b, l);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // <<list ID>>
  static boolean list_of_IDs(PsiBuilder b, int l) {
    return list(b, l + 1, ID_parser_);
  }

  /* ********************************************************** */
  // <<list (character_type)>>
  static boolean list_of_character_types(PsiBuilder b, int l) {
    return list(b, l + 1, RRParser::list_of_character_types_0_0);
  }

  // (character_type)
  private static boolean list_of_character_types_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_of_character_types_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = character_type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // <<list (mount_class INT)>>
  static boolean list_of_mount_effects(PsiBuilder b, int l) {
    return list(b, l + 1, RRParser::list_of_mount_effects_0_0);
  }

  // mount_class INT
  private static boolean list_of_mount_effects_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_of_mount_effects_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = mount_class(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // <<list name_>>
  static boolean list_of_names(PsiBuilder b, int l) {
    return list(b, l + 1, RRParser::name_);
  }

  /* ********************************************************** */
  // <<list trait_item>>
  static boolean list_of_trait_items(PsiBuilder b, int l) {
    return list(b, l + 1, RRParser::trait_item);
  }

  /* ********************************************************** */
  // <<list unit_attribute>>
  public static boolean list_of_unit_attributes(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_of_unit_attributes")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LIST_OF_UNIT_ATTRIBUTES, "<list of unit attributes>");
    r = list(b, l + 1, RRParser::unit_attribute);
    exit_section_(b, l, m, r, false, RRParser::not_formation);
    return r;
  }

  /* ********************************************************** */
  // <<list unit_weapon_attrs>>
  public static boolean list_of_unit_weapon_attributes(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_of_unit_weapon_attributes")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LIST_OF_UNIT_WEAPON_ATTRIBUTES, "<list of unit weapon attributes>");
    r = list(b, l + 1, RRParser::unit_weapon_attrs);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LOYALTY_REVOLTING|LOYALTY_RIOTING|LOYALTY_DISILLUSIONED|LOYALTY_CONTENT|LOYALTY_HAPPY
  public static boolean loyalty_to_governor_level(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "loyalty_to_governor_level")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LOYALTY_TO_GOVERNOR_LEVEL, "<loyalty to governor level>");
    r = consumeToken(b, LOYALTY_REVOLTING);
    if (!r) r = consumeToken(b, LOYALTY_RIOTING);
    if (!r) r = consumeToken(b, LOYALTY_DISILLUSIONED);
    if (!r) r = consumeToken(b, LOYALTY_CONTENT);
    if (!r) r = consumeToken(b, LOYALTY_HAPPY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // MAJOR_EVENT STRING
  public static boolean major_event_c(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "major_event_c")) return false;
    if (!nextTokenIs(b, MAJOR_EVENT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MAJOR_EVENT_C, null);
    r = consumeTokens(b, 1, MAJOR_EVENT, STRING);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // NOT? building_condition
  static boolean maybe_negated_c(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "maybe_negated_c")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = maybe_negated_c_0(b, l + 1);
    r = r && building_condition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NOT?
  private static boolean maybe_negated_c_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "maybe_negated_c_0")) return false;
    consumeToken(b, NOT);
    return true;
  }

  /* ********************************************************** */
  // NOT? condition_
  public static boolean maybe_negated_condition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "maybe_negated_condition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MAYBE_NEGATED_CONDITION, "<maybe negated condition>");
    r = maybe_negated_condition_0(b, l + 1);
    r = r && condition_(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // NOT?
  private static boolean maybe_negated_condition_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "maybe_negated_condition_0")) return false;
    consumeToken(b, NOT);
    return true;
  }

  /* ********************************************************** */
  // POOL ID
  //                     REGIONS (region_ref COMMA?)+
  //                     (UNIT unit_ref COMMA EXP INT COST INT REPLENISH FLOAT DASH FLOAT MAX INT INITIAL INT)*
  public static boolean mercenary_pool(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mercenary_pool")) return false;
    if (!nextTokenIs(b, POOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, POOL, ID, REGIONS);
    r = r && mercenary_pool_3(b, l + 1);
    r = r && mercenary_pool_4(b, l + 1);
    exit_section_(b, m, MERCENARY_POOL, r);
    return r;
  }

  // (region_ref COMMA?)+
  private static boolean mercenary_pool_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mercenary_pool_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = mercenary_pool_3_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!mercenary_pool_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "mercenary_pool_3", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // region_ref COMMA?
  private static boolean mercenary_pool_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mercenary_pool_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = region_ref(b, l + 1);
    r = r && mercenary_pool_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean mercenary_pool_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mercenary_pool_3_0_1")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  // (UNIT unit_ref COMMA EXP INT COST INT REPLENISH FLOAT DASH FLOAT MAX INT INITIAL INT)*
  private static boolean mercenary_pool_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mercenary_pool_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!mercenary_pool_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "mercenary_pool_4", c)) break;
    }
    return true;
  }

  // UNIT unit_ref COMMA EXP INT COST INT REPLENISH FLOAT DASH FLOAT MAX INT INITIAL INT
  private static boolean mercenary_pool_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mercenary_pool_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, UNIT);
    r = r && unit_ref(b, l + 1);
    r = r && consumeTokens(b, 0, COMMA, EXP, INT, COST, INT, REPLENISH, FLOAT, DASH, FLOAT, MAX, INT, INITIAL, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ID INT ID (ID)?
  public static boolean mission(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mission")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ID, INT, ID);
    r = r && mission_3(b, l + 1);
    exit_section_(b, m, MISSION, r);
    return r;
  }

  // (ID)?
  private static boolean mission_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mission_3")) return false;
    consumeToken(b, ID);
    return true;
  }

  /* ********************************************************** */
  // MISSION_QUEUE faction_ref OCB mission* CCB
  public static boolean mission_queue_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mission_queue_item")) return false;
    if (!nextTokenIs(b, MISSION_QUEUE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MISSION_QUEUE);
    r = r && faction_ref(b, l + 1);
    r = r && consumeToken(b, OCB);
    r = r && mission_queue_item_3(b, l + 1);
    r = r && consumeToken(b, CCB);
    exit_section_(b, m, MISSION_QUEUE_ITEM, r);
    return r;
  }

  // mission*
  private static boolean mission_queue_item_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mission_queue_item_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!mission(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "mission_queue_item_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // HORSE|CAMEL|ELEPHANT|CHARIOT
  public static boolean mount_class(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mount_class")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MOUNT_CLASS, "<mount class>");
    r = consumeToken(b, HORSE);
    if (!r) r = consumeToken(b, CAMEL);
    if (!r) r = consumeToken(b, ELEPHANT);
    if (!r) r = consumeToken(b, CHARIOT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ID+
  public static boolean name_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "name_")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, ID)) break;
      if (!empty_element_parsed_guard_(b, "name_", c)) break;
    }
    exit_section_(b, m, NAME_, r);
    return r;
  }

  /* ********************************************************** */
  // (OCB ID CCB names_name)+
  public static boolean names(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names")) return false;
    if (!nextTokenIs(b, OCB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = names_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!names_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "names", c)) break;
    }
    exit_section_(b, m, NAMES, r);
    return r;
  }

  // OCB ID CCB names_name
  private static boolean names_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OCB, ID, CCB);
    r = r && names_name(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // FACTION COLON faction_ref EOL*
  //                     CHARACTERS_LC EOL*
  //                       (name_ EOL)*
  //                     (SURNAMES EOL*
  //                       (name_ EOL)*)?
  //                     WOMEN EOL*
  //                       (name_ EOL*)*
  public static boolean names_for_faction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_for_faction")) return false;
    if (!nextTokenIs(b, FACTION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FACTION, COLON);
    r = r && faction_ref(b, l + 1);
    r = r && names_for_faction_3(b, l + 1);
    r = r && consumeToken(b, CHARACTERS_LC);
    r = r && names_for_faction_5(b, l + 1);
    r = r && names_for_faction_6(b, l + 1);
    r = r && names_for_faction_7(b, l + 1);
    r = r && consumeToken(b, WOMEN);
    r = r && names_for_faction_9(b, l + 1);
    r = r && names_for_faction_10(b, l + 1);
    exit_section_(b, m, NAMES_FOR_FACTION, r);
    return r;
  }

  // EOL*
  private static boolean names_for_faction_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_for_faction_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, EOL)) break;
      if (!empty_element_parsed_guard_(b, "names_for_faction_3", c)) break;
    }
    return true;
  }

  // EOL*
  private static boolean names_for_faction_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_for_faction_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, EOL)) break;
      if (!empty_element_parsed_guard_(b, "names_for_faction_5", c)) break;
    }
    return true;
  }

  // (name_ EOL)*
  private static boolean names_for_faction_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_for_faction_6")) return false;
    while (true) {
      int c = current_position_(b);
      if (!names_for_faction_6_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "names_for_faction_6", c)) break;
    }
    return true;
  }

  // name_ EOL
  private static boolean names_for_faction_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_for_faction_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = name_(b, l + 1);
    r = r && consumeToken(b, EOL);
    exit_section_(b, m, null, r);
    return r;
  }

  // (SURNAMES EOL*
  //                       (name_ EOL)*)?
  private static boolean names_for_faction_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_for_faction_7")) return false;
    names_for_faction_7_0(b, l + 1);
    return true;
  }

  // SURNAMES EOL*
  //                       (name_ EOL)*
  private static boolean names_for_faction_7_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_for_faction_7_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SURNAMES);
    r = r && names_for_faction_7_0_1(b, l + 1);
    r = r && names_for_faction_7_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // EOL*
  private static boolean names_for_faction_7_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_for_faction_7_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, EOL)) break;
      if (!empty_element_parsed_guard_(b, "names_for_faction_7_0_1", c)) break;
    }
    return true;
  }

  // (name_ EOL)*
  private static boolean names_for_faction_7_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_for_faction_7_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!names_for_faction_7_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "names_for_faction_7_0_2", c)) break;
    }
    return true;
  }

  // name_ EOL
  private static boolean names_for_faction_7_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_for_faction_7_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = name_(b, l + 1);
    r = r && consumeToken(b, EOL);
    exit_section_(b, m, null, r);
    return r;
  }

  // EOL*
  private static boolean names_for_faction_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_for_faction_9")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, EOL)) break;
      if (!empty_element_parsed_guard_(b, "names_for_faction_9", c)) break;
    }
    return true;
  }

  // (name_ EOL*)*
  private static boolean names_for_faction_10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_for_faction_10")) return false;
    while (true) {
      int c = current_position_(b);
      if (!names_for_faction_10_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "names_for_faction_10", c)) break;
    }
    return true;
  }

  // name_ EOL*
  private static boolean names_for_faction_10_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_for_faction_10_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = name_(b, l + 1);
    r = r && names_for_faction_10_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // EOL*
  private static boolean names_for_faction_10_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_for_faction_10_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, EOL)) break;
      if (!empty_element_parsed_guard_(b, "names_for_faction_10_0_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ID*
  public static boolean names_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "names_name")) return false;
    Marker m = enter_section_(b, l, _NONE_, NAMES_NAME, "<names name>");
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, ID)) break;
      if (!empty_element_parsed_guard_(b, "names_name", c)) break;
    }
    exit_section_(b, l, m, true, false, RRParser::recover_names_name);
    return true;
  }

  /* ********************************************************** */
  // NO_BUILDING_TAGGED ID QUEUED?
  public static boolean no_tag_c(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "no_tag_c")) return false;
    if (!nextTokenIs(b, NO_BUILDING_TAGGED)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, NO_TAG_C, null);
    r = consumeTokens(b, 1, NO_BUILDING_TAGGED, ID);
    p = r; // pin = 1
    r = r && no_tag_c_2(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // QUEUED?
  private static boolean no_tag_c_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "no_tag_c_2")) return false;
    consumeToken(b, QUEUED);
    return true;
  }

  /* ********************************************************** */
  // !CCB
  static boolean not_CCB(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_CCB")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, CCB);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !FORMATION
  static boolean not_formation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_formation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, FORMATION);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !STAT_HEALTH
  static boolean not_stat_health(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_stat_health")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, STAT_HEALTH);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INT|FLOAT
  static boolean number(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "number")) return false;
    if (!nextTokenIs(b, "", FLOAT, INT)) return false;
    boolean r;
    r = consumeToken(b, INT);
    if (!r) r = consumeToken(b, FLOAT);
    return r;
  }

  /* ********************************************************** */
  // SOLDIER ID COMMA INT COMMA INT COMMA number
  //                     (ENGINE ID)?
  //                     (SHIP ID+)?
  //                     (ANIMAL ID)?
  //                     (MOUNT name_)?
  //                     (OFFICER ID)*
  //                     (MOUNT_EFFECT list_of_mount_effects)?
  //                     ATTRIBUTES list_of_unit_attributes
  //                     FORMATION number COMMA number COMMA number COMMA number COMMA INT COMMA unit_formations
  //                     STAT_HEALTH INT COMMA INT
  //                     STAT_PRI unit_weapon_info
  //                     STAT_PRI_ATTR (NO|list_of_unit_weapon_attributes)
  //                     STAT_SEC (NO|unit_weapon_info)
  //                     STAT_SEC_ATTR (NO|list_of_unit_weapon_attributes)
  //                     STAT_PRI_ARMOUR INT COMMA INT COMMA INT COMMA unit_sound_when_hit (COMMA unit_sound_when_hit)?
  //                     STAT_SEC_ARMOUR INT COMMA INT COMMA unit_sound_when_hit
  //                     STAT_HEAT INT
  //                     STAT_GROUND INT COMMA INT COMMA INT COMMA INT
  //                     STAT_MENTAL INT COMMA unit_discipline COMMA unit_training
  //                     STAT_CHARGE_DIST INT
  //                     STAT_FIRE_DELAY INT
  //                     STAT_FOOD INT COMMA INT
  //                     STAT_COST INT COMMA INT COMMA INT COMMA INT COMMA INT COMMA INT
  public static boolean rebalance_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block")) return false;
    if (!nextTokenIs(b, SOLDIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, REBALANCE_BLOCK, null);
    r = consumeTokens(b, 2, SOLDIER, ID, COMMA, INT, COMMA, INT, COMMA);
    p = r; // pin = 2
    r = r && report_error_(b, number(b, l + 1));
    r = p && report_error_(b, rebalance_block_8(b, l + 1)) && r;
    r = p && report_error_(b, rebalance_block_9(b, l + 1)) && r;
    r = p && report_error_(b, rebalance_block_10(b, l + 1)) && r;
    r = p && report_error_(b, rebalance_block_11(b, l + 1)) && r;
    r = p && report_error_(b, rebalance_block_12(b, l + 1)) && r;
    r = p && report_error_(b, rebalance_block_13(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, ATTRIBUTES)) && r;
    r = p && report_error_(b, list_of_unit_attributes(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, FORMATION)) && r;
    r = p && report_error_(b, number(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COMMA)) && r;
    r = p && report_error_(b, number(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COMMA)) && r;
    r = p && report_error_(b, number(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COMMA)) && r;
    r = p && report_error_(b, number(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, COMMA, INT, COMMA)) && r;
    r = p && report_error_(b, unit_formations(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, STAT_HEALTH, INT, COMMA, INT, STAT_PRI)) && r;
    r = p && report_error_(b, unit_weapon_info(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, STAT_PRI_ATTR)) && r;
    r = p && report_error_(b, rebalance_block_35(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, STAT_SEC)) && r;
    r = p && report_error_(b, rebalance_block_37(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, STAT_SEC_ATTR)) && r;
    r = p && report_error_(b, rebalance_block_39(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, STAT_PRI_ARMOUR, INT, COMMA, INT, COMMA, INT, COMMA)) && r;
    r = p && report_error_(b, unit_sound_when_hit(b, l + 1)) && r;
    r = p && report_error_(b, rebalance_block_48(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, STAT_SEC_ARMOUR, INT, COMMA, INT, COMMA)) && r;
    r = p && report_error_(b, unit_sound_when_hit(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, STAT_HEAT, INT, STAT_GROUND, INT, COMMA, INT, COMMA, INT, COMMA, INT, STAT_MENTAL, INT, COMMA)) && r;
    r = p && report_error_(b, unit_discipline(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COMMA)) && r;
    r = p && report_error_(b, unit_training(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, STAT_CHARGE_DIST, INT, STAT_FIRE_DELAY, INT, STAT_FOOD, INT, COMMA, INT, STAT_COST, INT, COMMA, INT, COMMA, INT, COMMA, INT, COMMA, INT, COMMA, INT)) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (ENGINE ID)?
  private static boolean rebalance_block_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_8")) return false;
    rebalance_block_8_0(b, l + 1);
    return true;
  }

  // ENGINE ID
  private static boolean rebalance_block_8_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_8_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ENGINE, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // (SHIP ID+)?
  private static boolean rebalance_block_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_9")) return false;
    rebalance_block_9_0(b, l + 1);
    return true;
  }

  // SHIP ID+
  private static boolean rebalance_block_9_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_9_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SHIP);
    r = r && rebalance_block_9_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ID+
  private static boolean rebalance_block_9_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_9_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, ID)) break;
      if (!empty_element_parsed_guard_(b, "rebalance_block_9_0_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // (ANIMAL ID)?
  private static boolean rebalance_block_10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_10")) return false;
    rebalance_block_10_0(b, l + 1);
    return true;
  }

  // ANIMAL ID
  private static boolean rebalance_block_10_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_10_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ANIMAL, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MOUNT name_)?
  private static boolean rebalance_block_11(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_11")) return false;
    rebalance_block_11_0(b, l + 1);
    return true;
  }

  // MOUNT name_
  private static boolean rebalance_block_11_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_11_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MOUNT);
    r = r && name_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (OFFICER ID)*
  private static boolean rebalance_block_12(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_12")) return false;
    while (true) {
      int c = current_position_(b);
      if (!rebalance_block_12_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "rebalance_block_12", c)) break;
    }
    return true;
  }

  // OFFICER ID
  private static boolean rebalance_block_12_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_12_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OFFICER, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MOUNT_EFFECT list_of_mount_effects)?
  private static boolean rebalance_block_13(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_13")) return false;
    rebalance_block_13_0(b, l + 1);
    return true;
  }

  // MOUNT_EFFECT list_of_mount_effects
  private static boolean rebalance_block_13_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_13_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MOUNT_EFFECT);
    r = r && list_of_mount_effects(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NO|list_of_unit_weapon_attributes
  private static boolean rebalance_block_35(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_35")) return false;
    boolean r;
    r = consumeToken(b, NO);
    if (!r) r = list_of_unit_weapon_attributes(b, l + 1);
    return r;
  }

  // NO|unit_weapon_info
  private static boolean rebalance_block_37(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_37")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NO);
    if (!r) r = unit_weapon_info(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NO|list_of_unit_weapon_attributes
  private static boolean rebalance_block_39(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_39")) return false;
    boolean r;
    r = consumeToken(b, NO);
    if (!r) r = list_of_unit_weapon_attributes(b, l + 1);
    return r;
  }

  // (COMMA unit_sound_when_hit)?
  private static boolean rebalance_block_48(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_48")) return false;
    rebalance_block_48_0(b, l + 1);
    return true;
  }

  // COMMA unit_sound_when_hit
  private static boolean rebalance_block_48_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rebalance_block_48_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && unit_sound_when_hit(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !(landmark_section)
  static boolean recover_campaign_section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover_campaign_section")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !recover_campaign_section_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (landmark_section)
  private static boolean recover_campaign_section_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover_campaign_section_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = landmark_section(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !OCB
  static boolean recover_names_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover_names_name")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, OCB);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !(STAT_PRI_ATTR|STAT_SEC_ATTR)
  static boolean recover_stat_pri_attr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover_stat_pri_attr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !recover_stat_pri_attr_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // STAT_PRI_ATTR|STAT_SEC_ATTR
  private static boolean recover_stat_pri_attr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover_stat_pri_attr_0")) return false;
    boolean r;
    r = consumeToken(b, STAT_PRI_ATTR);
    if (!r) r = consumeToken(b, STAT_SEC_ATTR);
    return r;
  }

  /* ********************************************************** */
  // region_name_decl
  //                  ID
  //                  faction_ref
  //                  ID
  //                  INT INT INT
  //                  (NONE|list_of_IDs)
  //                  INT
  //                  INT
  public static boolean region_def(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "region_def")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = region_name_decl(b, l + 1);
    r = r && consumeToken(b, ID);
    r = r && faction_ref(b, l + 1);
    r = r && consumeTokens(b, 0, ID, INT, INT, INT);
    r = r && region_def_7(b, l + 1);
    r = r && consumeTokens(b, 0, INT, INT);
    exit_section_(b, m, REGION_DEF, r);
    return r;
  }

  // NONE|list_of_IDs
  private static boolean region_def_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "region_def_7")) return false;
    boolean r;
    r = consumeToken(b, NONE);
    if (!r) r = list_of_IDs(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean region_name_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "region_name_decl")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, REGION_NAME_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean region_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "region_ref")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, REGION_REF, r);
    return r;
  }

  /* ********************************************************** */
  // RELATIVE name_ COMMA name_ COMMA (list_of_names COMMA)? end
  public static boolean relative_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relative_item")) return false;
    if (!nextTokenIs(b, RELATIVE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RELATIVE);
    r = r && name_(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && name_(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && relative_item_5(b, l + 1);
    r = r && consumeToken(b, END);
    exit_section_(b, m, RELATIVE_ITEM, r);
    return r;
  }

  // (list_of_names COMMA)?
  private static boolean relative_item_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relative_item_5")) return false;
    relative_item_5_0(b, l + 1);
    return true;
  }

  // list_of_names COMMA
  private static boolean relative_item_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relative_item_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = list_of_names(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // REQUIRES composite_c
  public static boolean requirement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "requirement")) return false;
    if (!nextTokenIs(b, REQUIRES)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REQUIRES);
    r = r && composite_c(b, l + 1);
    exit_section_(b, m, REQUIREMENT, r);
    return r;
  }

  /* ********************************************************** */
  // RESOURCE ID
  public static boolean resource_c(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_c")) return false;
    if (!nextTokenIs(b, RESOURCE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, RESOURCE_C, null);
    r = consumeTokens(b, 1, RESOURCE, ID);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // RESOURCE ID COMMA INT COMMA coords
  public static boolean resource_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_item")) return false;
    if (!nextTokenIs(b, RESOURCE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, RESOURCE, ID, COMMA, INT, COMMA);
    r = r && coords(b, l + 1);
    exit_section_(b, m, RESOURCE_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // RESOURCE_QUANTITY_DISABLED resource_item* END
  public static boolean resource_quantity_disabled_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_quantity_disabled_")) return false;
    if (!nextTokenIs(b, RESOURCE_QUANTITY_DISABLED)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RESOURCE_QUANTITY_DISABLED);
    r = r && resource_quantity_disabled__1(b, l + 1);
    r = r && consumeToken(b, END);
    exit_section_(b, m, RESOURCE_QUANTITY_DISABLED_, r);
    return r;
  }

  // resource_item*
  private static boolean resource_quantity_disabled__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_quantity_disabled__1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!resource_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "resource_quantity_disabled__1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // RESOURCE_QUANTITY_ENABLED resource_item* END
  public static boolean resource_quantity_enabled_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_quantity_enabled_")) return false;
    if (!nextTokenIs(b, RESOURCE_QUANTITY_ENABLED)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RESOURCE_QUANTITY_ENABLED);
    r = r && resource_quantity_enabled__1(b, l + 1);
    r = r && consumeToken(b, END);
    exit_section_(b, m, RESOURCE_QUANTITY_ENABLED_, r);
    return r;
  }

  // resource_item*
  private static boolean resource_quantity_enabled__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_quantity_enabled__1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!resource_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "resource_quantity_enabled__1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // resource_quantity_enabled_
  //     resource_quantity_disabled_
  public static boolean resources_section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resources_section")) return false;
    if (!nextTokenIs(b, RESOURCE_QUANTITY_ENABLED)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = resource_quantity_enabled_(b, l + 1);
    r = r && resource_quantity_disabled_(b, l + 1);
    exit_section_(b, m, RESOURCES_SECTION, r);
    return r;
  }

  /* ********************************************************** */
  // SUMMER|WINTER
  public static boolean season(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "season")) return false;
    if (!nextTokenIs(b, "<season>", SUMMER, WINTER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SEASON, "<season>");
    r = consumeToken(b, SUMMER);
    if (!r) r = consumeToken(b, WINTER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // SETTLEMENT OCB
  //                           LEVEL settlement_level
  //                           REGION region_ref
  //                           YEAR_FOUNDED INT
  //                           POPULATION INT
  //                           PLAN_SET settlement_plan
  //                           FACTION_CREATOR faction_ref
  //                           building_item*
  //                         CCB
  public static boolean settlement_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "settlement_item")) return false;
    if (!nextTokenIs(b, SETTLEMENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SETTLEMENT, OCB, LEVEL);
    r = r && settlement_level(b, l + 1);
    r = r && consumeToken(b, REGION);
    r = r && region_ref(b, l + 1);
    r = r && consumeTokens(b, 0, YEAR_FOUNDED, INT, POPULATION, INT, PLAN_SET);
    r = r && settlement_plan(b, l + 1);
    r = r && consumeToken(b, FACTION_CREATOR);
    r = r && faction_ref(b, l + 1);
    r = r && settlement_item_14(b, l + 1);
    r = r && consumeToken(b, CCB);
    exit_section_(b, m, SETTLEMENT_ITEM, r);
    return r;
  }

  // building_item*
  private static boolean settlement_item_14(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "settlement_item_14")) return false;
    while (true) {
      int c = current_position_(b);
      if (!building_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "settlement_item_14", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // VILLAGE|TOWN|LARGE_TOWN|CITY|LARGE_CITY|HUGE_CITY
  public static boolean settlement_level(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "settlement_level")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SETTLEMENT_LEVEL, "<settlement level>");
    r = consumeToken(b, VILLAGE);
    if (!r) r = consumeToken(b, TOWN);
    if (!r) r = consumeToken(b, LARGE_TOWN);
    if (!r) r = consumeToken(b, CITY);
    if (!r) r = consumeToken(b, LARGE_CITY);
    if (!r) r = consumeToken(b, HUGE_CITY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DEFAULT_SET|
  public static boolean settlement_plan(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "settlement_plan")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SETTLEMENT_PLAN, "<settlement plan>");
    r = consumeToken(b, DEFAULT_SET);
    if (!r) r = consumeToken(b, SETTLEMENT_PLAN_1_0);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // MALE|FEMALE
  public static boolean sex(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sex")) return false;
    if (!nextTokenIs(b, "<sex>", FEMALE, MALE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SEX, "<sex>");
    r = consumeToken(b, MALE);
    if (!r) r = consumeToken(b, FEMALE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TOWER|RAM|LADDER|SAP_POINT|CATAPULT|TREBUCHET|BALLISTA|SCORPION
  public static boolean siege_engine_class(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "siege_engine_class")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SIEGE_ENGINE_CLASS, "<siege engine class>");
    r = consumeToken(b, TOWER);
    if (!r) r = consumeToken(b, RAM);
    if (!r) r = consumeToken(b, LADDER);
    if (!r) r = consumeToken(b, SAP_POINT);
    if (!r) r = consumeToken(b, CATAPULT);
    if (!r) r = consumeToken(b, TREBUCHET);
    if (!r) r = consumeToken(b, BALLISTA);
    if (!r) r = consumeToken(b, SCORPION);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ID OCB
  //             MALE TGA_FILE INT INT INT
  //             FEMALE TGA_FILE INT INT INT
  //           CCB
  public static boolean skin_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "skin_")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ID, OCB, MALE, TGA_FILE, INT, INT, INT, FEMALE, TGA_FILE, INT, INT, INT, CCB);
    exit_section_(b, m, SKIN_, r);
    return r;
  }

  /* ********************************************************** */
  public static boolean sound_emitters_section(PsiBuilder b, int l) {
    Marker m = enter_section_(b);
    exit_section_(b, m, SOUND_EMITTERS_SECTION, true);
    return true;
  }

  /* ********************************************************** */
  // TESTUDO|PHALANX|WEDGE|DROP_ENGINES|FLAMING_AMMO|WARCRY|CHANT|CURSE|BERSERK|RALLY|KILL_ELEPHANTS|MOVE_AND_SHOOT|CANTABRIAN_CIRCLE|SHIELD_WALL|STEALTH|FEIGNED_ROUT|SCHILTROM
  public static boolean special_ability(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "special_ability")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SPECIAL_ABILITY, "<special ability>");
    r = consumeToken(b, TESTUDO);
    if (!r) r = consumeToken(b, PHALANX);
    if (!r) r = consumeToken(b, WEDGE);
    if (!r) r = consumeToken(b, DROP_ENGINES);
    if (!r) r = consumeToken(b, FLAMING_AMMO);
    if (!r) r = consumeToken(b, WARCRY);
    if (!r) r = consumeToken(b, CHANT);
    if (!r) r = consumeToken(b, CURSE);
    if (!r) r = consumeToken(b, BERSERK);
    if (!r) r = consumeToken(b, RALLY);
    if (!r) r = consumeToken(b, KILL_ELEPHANTS);
    if (!r) r = consumeToken(b, MOVE_AND_SHOOT);
    if (!r) r = consumeToken(b, CANTABRIAN_CIRCLE);
    if (!r) r = consumeToken(b, SHIELD_WALL);
    if (!r) r = consumeToken(b, STEALTH);
    if (!r) r = consumeToken(b, FEIGNED_ROUT);
    if (!r) r = consumeToken(b, SCHILTROM);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // STRING
  public static boolean str_culture_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "str_culture_ref")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    exit_section_(b, m, STR_CULTURE_REF, r);
    return r;
  }

  /* ********************************************************** */
  // STRING
  public static boolean str_ethnicity_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "str_ethnicity_ref")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    exit_section_(b, m, STR_ETHNICITY_REF, r);
    return r;
  }

  /* ********************************************************** */
  // STRING
  public static boolean str_faction_group_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "str_faction_group_ref")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    exit_section_(b, m, STR_FACTION_GROUP_REF, r);
    return r;
  }

  /* ********************************************************** */
  // STRING
  public static boolean str_unit_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "str_unit_ref")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    exit_section_(b, m, STR_UNIT_REF, r);
    return r;
  }

  /* ********************************************************** */
  // NOT_SUCCESSFUL|SLIGHTLY_SUCCESSFUL|PARTLY_SUCCESSFUL|HIGHLY_SUCCESSFUL
  public static boolean success_level(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "success_level")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SUCCESS_LEVEL, "<success level>");
    r = consumeToken(b, NOT_SUCCESSFUL);
    if (!r) r = consumeToken(b, SLIGHTLY_SUCCESSFUL);
    if (!r) r = consumeToken(b, PARTLY_SUCCESSFUL);
    if (!r) r = consumeToken(b, HIGHLY_SUCCESSFUL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // SUPERFACTION_SETUP ID
  //                             (DEFAULT_HOSTILE ID)*
  //                             mission_queue_item*
  public static boolean superfaction_setup_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "superfaction_setup_item")) return false;
    if (!nextTokenIs(b, SUPERFACTION_SETUP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SUPERFACTION_SETUP, ID);
    r = r && superfaction_setup_item_2(b, l + 1);
    r = r && superfaction_setup_item_3(b, l + 1);
    exit_section_(b, m, SUPERFACTION_SETUP_ITEM, r);
    return r;
  }

  // (DEFAULT_HOSTILE ID)*
  private static boolean superfaction_setup_item_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "superfaction_setup_item_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!superfaction_setup_item_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "superfaction_setup_item_2", c)) break;
    }
    return true;
  }

  // DEFAULT_HOSTILE ID
  private static boolean superfaction_setup_item_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "superfaction_setup_item_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DEFAULT_HOSTILE, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // mission_queue_item*
  private static boolean superfaction_setup_item_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "superfaction_setup_item_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!mission_queue_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "superfaction_setup_item_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // !(condition_alias|building_tree)
  static boolean tagsRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tagsRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !tagsRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // condition_alias|building_tree
  private static boolean tagsRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tagsRecover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = condition_alias(b, l + 1);
    if (!r) r = building_tree(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // TAGS OCB list_of_IDs CCB
  public static boolean tags_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tags_")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TAGS_, "<tags>");
    r = consumeTokens(b, 1, TAGS, OCB);
    p = r; // pin = 1
    r = r && report_error_(b, list_of_IDs(b, l + 1));
    r = p && consumeToken(b, CCB) && r;
    exit_section_(b, l, m, r, p, RRParser::tagsRecover);
    return r || p;
  }

  /* ********************************************************** */
  // TAX_LOW|TAX_NORMAL|TAX_HIGH|TAX_EXTORTIONATE
  public static boolean tax_level(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tax_level")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TAX_LEVEL, "<tax level>");
    r = consumeToken(b, TAX_LOW);
    if (!r) r = consumeToken(b, TAX_NORMAL);
    if (!r) r = consumeToken(b, TAX_HIGH);
    if (!r) r = consumeToken(b, TAX_EXTORTIONATE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ARROW_TOWER|BALLISTA_TOWER|NONE
  public static boolean tower_defence_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tower_defence_type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TOWER_DEFENCE_TYPE, "<tower defence type>");
    r = consumeToken(b, ARROW_TOWER);
    if (!r) r = consumeToken(b, BALLISTA_TOWER);
    if (!r) r = consumeToken(b, NONE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TRAIT trait_name_decl
  //                 CHARACTERS list_of_character_types
  //                 HIDDEN?
  //                 (EXCLUDECULTURES <<list ID>>)*
  //                 (NOGOINGBACKLEVEL INT)?
  //                 (ANTITRAITS <<list trait_ref>>)?
  //               trait_level*
  public static boolean trait_def(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_def")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TRAIT_DEF, "<trait def>");
    r = consumeToken(b, TRAIT);
    p = r; // pin = 1
    r = r && report_error_(b, trait_name_decl(b, l + 1));
    r = p && report_error_(b, consumeToken(b, CHARACTERS)) && r;
    r = p && report_error_(b, list_of_character_types(b, l + 1)) && r;
    r = p && report_error_(b, trait_def_4(b, l + 1)) && r;
    r = p && report_error_(b, trait_def_5(b, l + 1)) && r;
    r = p && report_error_(b, trait_def_6(b, l + 1)) && r;
    r = p && report_error_(b, trait_def_7(b, l + 1)) && r;
    r = p && trait_def_8(b, l + 1) && r;
    exit_section_(b, l, m, r, p, RRParser::trait_def_recover);
    return r || p;
  }

  // HIDDEN?
  private static boolean trait_def_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_def_4")) return false;
    consumeToken(b, HIDDEN);
    return true;
  }

  // (EXCLUDECULTURES <<list ID>>)*
  private static boolean trait_def_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_def_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!trait_def_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "trait_def_5", c)) break;
    }
    return true;
  }

  // EXCLUDECULTURES <<list ID>>
  private static boolean trait_def_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_def_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXCLUDECULTURES);
    r = r && list(b, l + 1, ID_parser_);
    exit_section_(b, m, null, r);
    return r;
  }

  // (NOGOINGBACKLEVEL INT)?
  private static boolean trait_def_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_def_6")) return false;
    trait_def_6_0(b, l + 1);
    return true;
  }

  // NOGOINGBACKLEVEL INT
  private static boolean trait_def_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_def_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, NOGOINGBACKLEVEL, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ANTITRAITS <<list trait_ref>>)?
  private static boolean trait_def_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_def_7")) return false;
    trait_def_7_0(b, l + 1);
    return true;
  }

  // ANTITRAITS <<list trait_ref>>
  private static boolean trait_def_7_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_def_7_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ANTITRAITS);
    r = r && list(b, l + 1, RRParser::trait_ref);
    exit_section_(b, m, null, r);
    return r;
  }

  // trait_level*
  private static boolean trait_def_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_def_8")) return false;
    while (true) {
      int c = current_position_(b);
      if (!trait_level(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "trait_def_8", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // !(TRAIT|TRIGGER)
  static boolean trait_def_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_def_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !trait_def_recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // TRAIT|TRIGGER
  private static boolean trait_def_recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_def_recover_0")) return false;
    boolean r;
    r = consumeToken(b, TRAIT);
    if (!r) r = consumeToken(b, TRIGGER);
    return r;
  }

  /* ********************************************************** */
  // trait_ref INT
  public static boolean trait_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_item")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = trait_ref(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, TRAIT_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // LEVEL ID
  //                   DESCRIPTION ID
  //                   EFFECTSDESCRIPTION ID
  //                   (GAINMESSAGE ID)?
  //                   (LOSEMESSAGE ID)?
  //                   (EPITHET ID)?
  //                   THRESHOLD INT
  //                   (EFFECT ID INT)*
  public static boolean trait_level(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_level")) return false;
    if (!nextTokenIs(b, LEVEL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LEVEL, ID, DESCRIPTION, ID, EFFECTSDESCRIPTION, ID);
    r = r && trait_level_6(b, l + 1);
    r = r && trait_level_7(b, l + 1);
    r = r && trait_level_8(b, l + 1);
    r = r && consumeTokens(b, 0, THRESHOLD, INT);
    r = r && trait_level_11(b, l + 1);
    exit_section_(b, m, TRAIT_LEVEL, r);
    return r;
  }

  // (GAINMESSAGE ID)?
  private static boolean trait_level_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_level_6")) return false;
    trait_level_6_0(b, l + 1);
    return true;
  }

  // GAINMESSAGE ID
  private static boolean trait_level_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_level_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, GAINMESSAGE, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LOSEMESSAGE ID)?
  private static boolean trait_level_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_level_7")) return false;
    trait_level_7_0(b, l + 1);
    return true;
  }

  // LOSEMESSAGE ID
  private static boolean trait_level_7_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_level_7_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LOSEMESSAGE, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EPITHET ID)?
  private static boolean trait_level_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_level_8")) return false;
    trait_level_8_0(b, l + 1);
    return true;
  }

  // EPITHET ID
  private static boolean trait_level_8_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_level_8_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, EPITHET, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EFFECT ID INT)*
  private static boolean trait_level_11(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_level_11")) return false;
    while (true) {
      int c = current_position_(b);
      if (!trait_level_11_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "trait_level_11", c)) break;
    }
    return true;
  }

  // EFFECT ID INT
  private static boolean trait_level_11_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_level_11_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, EFFECT, ID, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean trait_name_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_name_decl")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, TRAIT_NAME_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean trait_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_ref")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, TRAIT_REF, r);
    return r;
  }

  /* ********************************************************** */
  // TRIGGER ID
  //                         WHENTOTEST event
  //                         (CONDITION composite_condition)?
  //                       (AFFECTS trait_ref LOSE? INT CHANCE INT)*
  public static boolean trait_trigger_def(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_trigger_def")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TRAIT_TRIGGER_DEF, "<trait trigger def>");
    r = consumeTokens(b, 1, TRIGGER, ID, WHENTOTEST);
    p = r; // pin = 1
    r = r && report_error_(b, event(b, l + 1));
    r = p && report_error_(b, trait_trigger_def_4(b, l + 1)) && r;
    r = p && trait_trigger_def_5(b, l + 1) && r;
    exit_section_(b, l, m, r, p, RRParser::trait_trigger_def_recover);
    return r || p;
  }

  // (CONDITION composite_condition)?
  private static boolean trait_trigger_def_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_trigger_def_4")) return false;
    trait_trigger_def_4_0(b, l + 1);
    return true;
  }

  // CONDITION composite_condition
  private static boolean trait_trigger_def_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_trigger_def_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONDITION);
    r = r && composite_condition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (AFFECTS trait_ref LOSE? INT CHANCE INT)*
  private static boolean trait_trigger_def_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_trigger_def_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!trait_trigger_def_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "trait_trigger_def_5", c)) break;
    }
    return true;
  }

  // AFFECTS trait_ref LOSE? INT CHANCE INT
  private static boolean trait_trigger_def_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_trigger_def_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AFFECTS);
    r = r && trait_ref(b, l + 1);
    r = r && trait_trigger_def_5_0_2(b, l + 1);
    r = r && consumeTokens(b, 0, INT, CHANCE, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // LOSE?
  private static boolean trait_trigger_def_5_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_trigger_def_5_0_2")) return false;
    consumeToken(b, LOSE);
    return true;
  }

  /* ********************************************************** */
  // !TRIGGER
  static boolean trait_trigger_def_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trait_trigger_def_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, TRIGGER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // hide_type|
  //                     FRIGHTEN_FOOT|FRIGHTEN_MOUNTED|CAN_RUN_AMOK|WARCRY|
  //                     CAN_SAP|HARDY|VERY_HARDY|SEA_FARING|CANTABRIAN_CIRCLE|
  //                     GENERAL_UNIT|GENERAL_UNIT_UPGRADE|COMMAND|MERCENARY_UNIT|DRUID|SCREECHING_WOMEN|
  //                     NO_CUSTOM
  public static boolean unit_attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_attribute")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIT_ATTRIBUTE, "<unit attribute>");
    r = hide_type(b, l + 1);
    if (!r) r = consumeToken(b, FRIGHTEN_FOOT);
    if (!r) r = consumeToken(b, FRIGHTEN_MOUNTED);
    if (!r) r = consumeToken(b, CAN_RUN_AMOK);
    if (!r) r = consumeToken(b, WARCRY);
    if (!r) r = consumeToken(b, CAN_SAP);
    if (!r) r = consumeToken(b, HARDY);
    if (!r) r = consumeToken(b, VERY_HARDY);
    if (!r) r = consumeToken(b, SEA_FARING);
    if (!r) r = consumeToken(b, CANTABRIAN_CIRCLE);
    if (!r) r = consumeToken(b, GENERAL_UNIT);
    if (!r) r = consumeToken(b, GENERAL_UNIT_UPGRADE);
    if (!r) r = consumeToken(b, COMMAND);
    if (!r) r = consumeToken(b, MERCENARY_UNIT);
    if (!r) r = consumeToken(b, DRUID);
    if (!r) r = consumeToken(b, SCREECHING_WOMEN);
    if (!r) r = consumeToken(b, NO_CUSTOM);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INFANTRY|CAVALRY|SIEGE|HANDLER|SHIP|NON_COMBATANT
  public static boolean unit_category(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_category")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIT_CATEGORY, "<unit category>");
    r = consumeToken(b, INFANTRY);
    if (!r) r = consumeToken(b, CAVALRY);
    if (!r) r = consumeToken(b, SIEGE);
    if (!r) r = consumeToken(b, HANDLER);
    if (!r) r = consumeToken(b, SHIP);
    if (!r) r = consumeToken(b, NON_COMBATANT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LIGHT|HEAVY|SKIRMISH|MISSILE|SPEARMEN
  public static boolean unit_class(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_class")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIT_CLASS, "<unit class>");
    r = consumeToken(b, LIGHT);
    if (!r) r = consumeToken(b, HEAVY);
    if (!r) r = consumeToken(b, SKIRMISH);
    if (!r) r = consumeToken(b, MISSILE);
    if (!r) r = consumeToken(b, SPEARMEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NONE|KNIFE|MACE|AXE|SWORD|SPEAR
  public static boolean unit_damage_sound_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_damage_sound_type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIT_DAMAGE_SOUND_TYPE, "<unit damage sound type>");
    r = consumeToken(b, NONE);
    if (!r) r = consumeToken(b, KNIFE);
    if (!r) r = consumeToken(b, MACE);
    if (!r) r = consumeToken(b, AXE);
    if (!r) r = consumeToken(b, SWORD);
    if (!r) r = consumeToken(b, SPEAR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PIERCING|BLUNT|SLASHING|FIRE
  public static boolean unit_damage_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_damage_type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIT_DAMAGE_TYPE, "<unit damage type>");
    r = consumeToken(b, PIERCING);
    if (!r) r = consumeToken(b, BLUNT);
    if (!r) r = consumeToken(b, SLASHING);
    if (!r) r = consumeToken(b, FIRE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BERSERKER|IMPETUOUS|LOW|NORMAL|DISCIPLINED
  public static boolean unit_discipline(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_discipline")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIT_DISCIPLINE, "<unit discipline>");
    r = consumeToken(b, BERSERKER);
    if (!r) r = consumeToken(b, IMPETUOUS);
    if (!r) r = consumeToken(b, LOW);
    if (!r) r = consumeToken(b, NORMAL);
    if (!r) r = consumeToken(b, DISCIPLINED);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // SQUARE|SQUARE_HOLLOW|COLUMN|HORDE|PHALANX|TESTUDO|WEDGE
  public static boolean unit_formation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_formation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIT_FORMATION, "<unit formation>");
    r = consumeToken(b, SQUARE);
    if (!r) r = consumeToken(b, SQUARE_HOLLOW);
    if (!r) r = consumeToken(b, COLUMN);
    if (!r) r = consumeToken(b, HORDE);
    if (!r) r = consumeToken(b, PHALANX);
    if (!r) r = consumeToken(b, TESTUDO);
    if (!r) r = consumeToken(b, WEDGE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // unit_formation (COMMA unit_formation)?
  public static boolean unit_formations(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_formations")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIT_FORMATIONS, "<unit formations>");
    r = unit_formation(b, l + 1);
    r = r && unit_formations_1(b, l + 1);
    exit_section_(b, l, m, r, false, RRParser::not_stat_health);
    return r;
  }

  // (COMMA unit_formation)?
  private static boolean unit_formations_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_formations_1")) return false;
    unit_formations_1_0(b, l + 1);
    return true;
  }

  // COMMA unit_formation
  private static boolean unit_formations_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_formations_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && unit_formation(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // TYPE unit_name_decl
  //                DICTIONARY ID
  //                CATEGORY unit_category
  //                CLASS unit_class
  //                VOICE_TYPE ID ID?
  //                (VOICE_INDEXES INT INT INT)?
  //                rebalance_block
  //                OWNERSHIP <<list faction_or_culture_ref>>
  //                (ETHNICITY faction_ref COMMA ethnicity_makeup_ref
  //                          (COMMA DONT_ALLOW_MIXED)?
  //                          (COMMA DONT_ALLOW_REGIONAL)?
  //                          (COMMA DONT_ALLOW_CUSTOM)?
  //                          (TATTOO_COLOR ID)?
  //                          (HAIR_COLOR ID)?
  //                          (HAIR_STYLE INT)?
  //                          (IS_FEMALE)?)*
  //                (REBALANCE_STATBLOCK rebalance_block)?
  public static boolean unit_item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item_")) return false;
    if (!nextTokenIs(b, TYPE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, UNIT_ITEM_, null);
    r = consumeToken(b, TYPE);
    r = r && unit_name_decl(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, consumeTokens(b, -1, DICTIONARY, ID, CATEGORY));
    r = p && report_error_(b, unit_category(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, CLASS)) && r;
    r = p && report_error_(b, unit_class(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, VOICE_TYPE, ID)) && r;
    r = p && report_error_(b, unit_item__10(b, l + 1)) && r;
    r = p && report_error_(b, unit_item__11(b, l + 1)) && r;
    r = p && report_error_(b, rebalance_block(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, OWNERSHIP)) && r;
    r = p && report_error_(b, list(b, l + 1, RRParser::faction_or_culture_ref)) && r;
    r = p && report_error_(b, unit_item__15(b, l + 1)) && r;
    r = p && unit_item__16(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ID?
  private static boolean unit_item__10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__10")) return false;
    consumeToken(b, ID);
    return true;
  }

  // (VOICE_INDEXES INT INT INT)?
  private static boolean unit_item__11(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__11")) return false;
    unit_item__11_0(b, l + 1);
    return true;
  }

  // VOICE_INDEXES INT INT INT
  private static boolean unit_item__11_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__11_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, VOICE_INDEXES, INT, INT, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ETHNICITY faction_ref COMMA ethnicity_makeup_ref
  //                          (COMMA DONT_ALLOW_MIXED)?
  //                          (COMMA DONT_ALLOW_REGIONAL)?
  //                          (COMMA DONT_ALLOW_CUSTOM)?
  //                          (TATTOO_COLOR ID)?
  //                          (HAIR_COLOR ID)?
  //                          (HAIR_STYLE INT)?
  //                          (IS_FEMALE)?)*
  private static boolean unit_item__15(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15")) return false;
    while (true) {
      int c = current_position_(b);
      if (!unit_item__15_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "unit_item__15", c)) break;
    }
    return true;
  }

  // ETHNICITY faction_ref COMMA ethnicity_makeup_ref
  //                          (COMMA DONT_ALLOW_MIXED)?
  //                          (COMMA DONT_ALLOW_REGIONAL)?
  //                          (COMMA DONT_ALLOW_CUSTOM)?
  //                          (TATTOO_COLOR ID)?
  //                          (HAIR_COLOR ID)?
  //                          (HAIR_STYLE INT)?
  //                          (IS_FEMALE)?
  private static boolean unit_item__15_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ETHNICITY);
    r = r && faction_ref(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && ethnicity_makeup_ref(b, l + 1);
    r = r && unit_item__15_0_4(b, l + 1);
    r = r && unit_item__15_0_5(b, l + 1);
    r = r && unit_item__15_0_6(b, l + 1);
    r = r && unit_item__15_0_7(b, l + 1);
    r = r && unit_item__15_0_8(b, l + 1);
    r = r && unit_item__15_0_9(b, l + 1);
    r = r && unit_item__15_0_10(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA DONT_ALLOW_MIXED)?
  private static boolean unit_item__15_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15_0_4")) return false;
    unit_item__15_0_4_0(b, l + 1);
    return true;
  }

  // COMMA DONT_ALLOW_MIXED
  private static boolean unit_item__15_0_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15_0_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, DONT_ALLOW_MIXED);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA DONT_ALLOW_REGIONAL)?
  private static boolean unit_item__15_0_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15_0_5")) return false;
    unit_item__15_0_5_0(b, l + 1);
    return true;
  }

  // COMMA DONT_ALLOW_REGIONAL
  private static boolean unit_item__15_0_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15_0_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, DONT_ALLOW_REGIONAL);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA DONT_ALLOW_CUSTOM)?
  private static boolean unit_item__15_0_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15_0_6")) return false;
    unit_item__15_0_6_0(b, l + 1);
    return true;
  }

  // COMMA DONT_ALLOW_CUSTOM
  private static boolean unit_item__15_0_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15_0_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, DONT_ALLOW_CUSTOM);
    exit_section_(b, m, null, r);
    return r;
  }

  // (TATTOO_COLOR ID)?
  private static boolean unit_item__15_0_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15_0_7")) return false;
    unit_item__15_0_7_0(b, l + 1);
    return true;
  }

  // TATTOO_COLOR ID
  private static boolean unit_item__15_0_7_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15_0_7_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, TATTOO_COLOR, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // (HAIR_COLOR ID)?
  private static boolean unit_item__15_0_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15_0_8")) return false;
    unit_item__15_0_8_0(b, l + 1);
    return true;
  }

  // HAIR_COLOR ID
  private static boolean unit_item__15_0_8_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15_0_8_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, HAIR_COLOR, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // (HAIR_STYLE INT)?
  private static boolean unit_item__15_0_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15_0_9")) return false;
    unit_item__15_0_9_0(b, l + 1);
    return true;
  }

  // HAIR_STYLE INT
  private static boolean unit_item__15_0_9_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15_0_9_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, HAIR_STYLE, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  // (IS_FEMALE)?
  private static boolean unit_item__15_0_10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__15_0_10")) return false;
    consumeToken(b, IS_FEMALE);
    return true;
  }

  // (REBALANCE_STATBLOCK rebalance_block)?
  private static boolean unit_item__16(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__16")) return false;
    unit_item__16_0(b, l + 1);
    return true;
  }

  // REBALANCE_STATBLOCK rebalance_block
  private static boolean unit_item__16_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_item__16_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REBALANCE_STATBLOCK);
    r = r && rebalance_block(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ID+
  public static boolean unit_name_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_name_decl")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, ID)) break;
      if (!empty_element_parsed_guard_(b, "unit_name_decl", c)) break;
    }
    exit_section_(b, m, UNIT_NAME_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // RECRUIT str_unit_ref INT
  static boolean unit_recruit_capability(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_recruit_capability")) return false;
    if (!nextTokenIs(b, RECRUIT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RECRUIT);
    r = r && str_unit_ref(b, l + 1);
    r = r && consumeToken(b, INT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ID+
  public static boolean unit_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_ref")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, ID)) break;
      if (!empty_element_parsed_guard_(b, "unit_ref", c)) break;
    }
    exit_section_(b, m, UNIT_REF, r);
    return r;
  }

  /* ********************************************************** */
  // FLESH|LEATHER|WOOD|METAL
  public static boolean unit_sound_when_hit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_sound_when_hit")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIT_SOUND_WHEN_HIT, "<unit sound when hit>");
    r = consumeToken(b, FLESH);
    if (!r) r = consumeToken(b, LEATHER);
    if (!r) r = consumeToken(b, WOOD);
    if (!r) r = consumeToken(b, METAL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // SIMPLE|OTHER|BLADE|ARCHERY|SIEGE
  public static boolean unit_tech_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_tech_type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIT_TECH_TYPE, "<unit tech type>");
    r = consumeToken(b, SIMPLE);
    if (!r) r = consumeToken(b, OTHER);
    if (!r) r = consumeToken(b, BLADE);
    if (!r) r = consumeToken(b, ARCHERY);
    if (!r) r = consumeToken(b, SIEGE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // UNTRAINED|TRAINED|HIGHLY_TRAINED
  public static boolean unit_training(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_training")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIT_TRAINING, "<unit training>");
    r = consumeToken(b, UNTRAINED);
    if (!r) r = consumeToken(b, TRAINED);
    if (!r) r = consumeToken(b, HIGHLY_TRAINED);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AP|BP|SPEAR|LIGHT_SPEAR|LONG_PIKE|SHORT_PIKE|FIRE|
  //                       PREC|THROWN|THROWN_AP|LAUNCHING|AREA|SPEAR_BONUS_X
  public static boolean unit_weapon_attrs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_weapon_attrs")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIT_WEAPON_ATTRS, "<unit weapon attrs>");
    r = consumeToken(b, AP);
    if (!r) r = consumeToken(b, BP);
    if (!r) r = consumeToken(b, SPEAR);
    if (!r) r = consumeToken(b, LIGHT_SPEAR);
    if (!r) r = consumeToken(b, LONG_PIKE);
    if (!r) r = consumeToken(b, SHORT_PIKE);
    if (!r) r = consumeToken(b, FIRE);
    if (!r) r = consumeToken(b, PREC);
    if (!r) r = consumeToken(b, THROWN);
    if (!r) r = consumeToken(b, THROWN_AP);
    if (!r) r = consumeToken(b, LAUNCHING);
    if (!r) r = consumeToken(b, AREA);
    if (!r) r = consumeToken(b, SPEAR_BONUS_X);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INT COMMA
  //                      INT COMMA
  //                      (NO|ID) COMMA // descr_projectile and descr_projectile_new
  //                      INT COMMA
  //                      INT COMMA
  //                      (NO|unit_weapon_type) COMMA
  //                      (NO|unit_tech_type)  COMMA
  //                      (NO|unit_damage_type) COMMA
  //                      unit_damage_sound_type COMMA
  //                      INT COMMA
  //                      number
  public static boolean unit_weapon_info(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_weapon_info")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, UNIT_WEAPON_INFO, "<unit weapon info>");
    r = consumeTokens(b, 0, INT, COMMA, INT, COMMA);
    r = r && unit_weapon_info_4(b, l + 1);
    r = r && consumeTokens(b, 5, COMMA, INT, COMMA, INT, COMMA);
    p = r; // pin = 10
    r = r && report_error_(b, unit_weapon_info_10(b, l + 1));
    r = p && report_error_(b, consumeToken(b, COMMA)) && r;
    r = p && report_error_(b, unit_weapon_info_12(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COMMA)) && r;
    r = p && report_error_(b, unit_weapon_info_14(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COMMA)) && r;
    r = p && report_error_(b, unit_damage_sound_type(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, COMMA, INT, COMMA)) && r;
    r = p && number(b, l + 1) && r;
    exit_section_(b, l, m, r, p, RRParser::recover_stat_pri_attr);
    return r || p;
  }

  // NO|ID
  private static boolean unit_weapon_info_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_weapon_info_4")) return false;
    boolean r;
    r = consumeToken(b, NO);
    if (!r) r = consumeToken(b, ID);
    return r;
  }

  // NO|unit_weapon_type
  private static boolean unit_weapon_info_10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_weapon_info_10")) return false;
    boolean r;
    r = consumeToken(b, NO);
    if (!r) r = unit_weapon_type(b, l + 1);
    return r;
  }

  // NO|unit_tech_type
  private static boolean unit_weapon_info_12(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_weapon_info_12")) return false;
    boolean r;
    r = consumeToken(b, NO);
    if (!r) r = unit_tech_type(b, l + 1);
    return r;
  }

  // NO|unit_damage_type
  private static boolean unit_weapon_info_14(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_weapon_info_14")) return false;
    boolean r;
    r = consumeToken(b, NO);
    if (!r) r = unit_damage_type(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // MELEE|THROWN|MISSILE|SIEGE_MISSILE
  public static boolean unit_weapon_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unit_weapon_type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNIT_WEAPON_TYPE, "<unit weapon type>");
    r = consumeToken(b, MELEE);
    if (!r) r = consumeToken(b, THROWN);
    if (!r) r = consumeToken(b, MISSILE);
    if (!r) r = consumeToken(b, SIEGE_MISSILE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  static final Parser ID_parser_ = (b, l) -> consumeToken(b, ID);
}
