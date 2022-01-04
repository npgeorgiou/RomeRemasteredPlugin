package rr.language.psi;

import com.intellij.psi.tree.IElementType;
import rr.language.RRLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class RRTokenType extends IElementType {

    public RRTokenType(@NotNull @NonNls String debugName) {
        super(debugName, RRLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "RRTokenType." + super.toString();
    }

}
