package rr.language;

import com.intellij.lang.Language;

public class RRLanguage extends Language {

    public static final RRLanguage INSTANCE = new RRLanguage();

    private RRLanguage() {
        super("RomeRemastered");
    }

}