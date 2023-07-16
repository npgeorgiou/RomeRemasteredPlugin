package rr.language

import com.intellij.lexer.FlexAdapter

class RRLexerAdapter : FlexAdapter(RRLexer(null))
