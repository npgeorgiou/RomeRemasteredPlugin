package rr.language.colors

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import rr.language.RRLexerAdapter
import rr.language.RRParserDefinition
import rr.language.psi.RRTypes
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey

class RRSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        return RRLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return pack(ATTRIBUTES[tokenType])
    }

    companion object {
        val BAD_CHARACTER_STYLE = createTextAttributesKey("RR_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)
        val MARKER_STYLE = createTextAttributesKey("RR_MARKER", DefaultLanguageHighlighterColors.DOC_COMMENT)
        val COMMENT_STYLE = createTextAttributesKey("RR_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val NUMBER_STYLE = createTextAttributesKey("RR_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val BOOLEAN_STYLE = createTextAttributesKey("RR_BOOLEAN", DefaultLanguageHighlighterColors.CONSTANT)
        val STRING_STYLE = createTextAttributesKey("RR_STRING", DefaultLanguageHighlighterColors.STRING)
        val PUNCTUATION_STYLE = createTextAttributesKey("RR_PUNCTUATION", DefaultLanguageHighlighterColors.BRACES)
        val OPERATORS_STYLE = createTextAttributesKey("RR_OPERATORS", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val COORDS_STYLE = createTextAttributesKey("RR_COORDS", DefaultLanguageHighlighterColors.LABEL)
        val CONSTANTS_STYLE = createTextAttributesKey("RR_CONSTANTS", DefaultLanguageHighlighterColors.CONSTANT)
        val FILES_STYLE = createTextAttributesKey("RR_FILES", DefaultLanguageHighlighterColors.LABEL)
        val EVENTS_STYLE = createTextAttributesKey("RR_EVENTS", DefaultLanguageHighlighterColors.CLASS_NAME)
        val CONDITIONS_STYLE = createTextAttributesKey("RR_CONDITIONS", DefaultLanguageHighlighterColors.FUNCTION_CALL)
        val COMMANDS_STYLE = createTextAttributesKey("RR_COMMANDS", DefaultLanguageHighlighterColors.FUNCTION_CALL)
        val CUSTOM_SCRIPT_HELPERS_STYLE = createTextAttributesKey("RR_CUSTOM_SCRIPT_HELPERS", DefaultLanguageHighlighterColors.LABEL)
        val KEYWORDS_STYLE = createTextAttributesKey("RR_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val ID_STYLE = createTextAttributesKey("RR_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)

        private val ATTRIBUTES: Map<IElementType, TextAttributesKey> = HashMap()

        init {
            fillMap(ATTRIBUTES, RRParserDefinition.MARKERS, MARKER_STYLE)
            fillMap(ATTRIBUTES, RRParserDefinition.PUNCTUATIONS, PUNCTUATION_STYLE)
            fillMap(ATTRIBUTES, RRParserDefinition.OPERATORS, OPERATORS_STYLE)
            fillMap(ATTRIBUTES, RRParserDefinition.KEYWORDS, KEYWORDS_STYLE)
            fillMap(ATTRIBUTES, RRParserDefinition.CONSTANTS, CONSTANTS_STYLE)
            fillMap(ATTRIBUTES, RRParserDefinition.NUMBERS, NUMBER_STYLE)
            fillMap(ATTRIBUTES, RRParserDefinition.FILES, FILES_STYLE)
            fillMap(ATTRIBUTES, RRParserDefinition.EVENTS, EVENTS_STYLE)
            fillMap(ATTRIBUTES, RRParserDefinition.CONDITIONS, CONDITIONS_STYLE)
            fillMap(ATTRIBUTES, RRParserDefinition.COMMANDS, COMMANDS_STYLE)
            fillMap(ATTRIBUTES, RRParserDefinition.CUSTOM_SCRIPT_HELPERS, CUSTOM_SCRIPT_HELPERS_STYLE)
            fillMap(ATTRIBUTES, BOOLEAN_STYLE, RRTypes.BOOLEAN)
            fillMap(ATTRIBUTES, STRING_STYLE, RRTypes.STRING)
            fillMap(ATTRIBUTES, ID_STYLE, RRTypes.ID)
            fillMap(ATTRIBUTES, COMMENT_STYLE, RRTypes.COMMENT)
            fillMap(ATTRIBUTES, BAD_CHARACTER_STYLE, TokenType.BAD_CHARACTER)
        }
    }
}
