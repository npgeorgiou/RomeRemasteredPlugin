package rr.language;

import com.intellij.lexer.FlexAdapter;

public class RRLexerAdapter extends FlexAdapter {

    public RRLexerAdapter() {
        super(new RRLexer(null));
    }

}
