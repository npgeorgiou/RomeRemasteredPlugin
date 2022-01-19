package rr.language.colors;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import rr.language.RRLexerAdapter;
import rr.language.RRParserDefinition;
import rr.language.psi.RRTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class RRSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey BAD_CHARACTER_STYLE = createTextAttributesKey("RR_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);
    public static final TextAttributesKey COMMENT_STYLE = createTextAttributesKey("RR_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey NUMBER_STYLE = createTextAttributesKey("RR_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey BOOLEAN_STYLE = createTextAttributesKey("RR_BOOLEAN", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey STRING_STYLE = createTextAttributesKey("RR_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey PUNCTUATION_STYLE = createTextAttributesKey("RR_PUNCTUATION", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey OPERATORS_STYLE = createTextAttributesKey("RR_OPERATORS", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey COORDS_STYLE = createTextAttributesKey("RR_COORDS", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey CONSTANTS_STYLE = createTextAttributesKey("RR_CONSTANTS", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey FILES_STYLE = createTextAttributesKey("RR_FILES", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey EVENTS_STYLE = createTextAttributesKey("RR_EVENTS", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey CONDITIONS_STYLE = createTextAttributesKey("RR_CONDITIONS", DefaultLanguageHighlighterColors.FUNCTION_CALL);
    public static final TextAttributesKey COMMANDS_STYLE = createTextAttributesKey("RR_COMMANDS", DefaultLanguageHighlighterColors.FUNCTION_CALL);
    public static final TextAttributesKey CUSTOM_SCRIPT_HELPERS_STYLE = createTextAttributesKey("RR_CUSTOM_SCRIPT_HELPERS", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey KEYWORDS_STYLE = createTextAttributesKey("RR_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey ID_STYLE = createTextAttributesKey("RR_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);


    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<>();

    static {
        fillMap(ATTRIBUTES, RRParserDefinition.FILE_TYPE_MARKERS, COMMENT_STYLE);
        fillMap(ATTRIBUTES, RRParserDefinition.PUNCTUATIONS, PUNCTUATION_STYLE);
        fillMap(ATTRIBUTES, RRParserDefinition.OPERATORS, OPERATORS_STYLE);
        fillMap(ATTRIBUTES, RRParserDefinition.KEYWORDS, KEYWORDS_STYLE);
        fillMap(ATTRIBUTES, RRParserDefinition.CONSTANTS, CONSTANTS_STYLE);
        fillMap(ATTRIBUTES, RRParserDefinition.NUMBERS, NUMBER_STYLE);
        fillMap(ATTRIBUTES, RRParserDefinition.FILES, FILES_STYLE);
        fillMap(ATTRIBUTES, RRParserDefinition.EVENTS, EVENTS_STYLE);
        fillMap(ATTRIBUTES, RRParserDefinition.CONDITIONS, CONDITIONS_STYLE);
        fillMap(ATTRIBUTES, RRParserDefinition.COMMANDS, COMMANDS_STYLE);
        fillMap(ATTRIBUTES, RRParserDefinition.CUSTOM_SCRIPT_HELPERS, CUSTOM_SCRIPT_HELPERS_STYLE);
        fillMap(ATTRIBUTES, BOOLEAN_STYLE, RRTypes.BOOLEAN);
        fillMap(ATTRIBUTES, STRING_STYLE, RRTypes.STRING);
        fillMap(ATTRIBUTES, ID_STYLE, RRTypes.ID);
        fillMap(ATTRIBUTES, COMMENT_STYLE, RRTypes.COMMENT);
        fillMap(ATTRIBUTES, BAD_CHARACTER_STYLE, TokenType.BAD_CHARACTER);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new RRLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        return pack(ATTRIBUTES.get(tokenType));
    }

}
