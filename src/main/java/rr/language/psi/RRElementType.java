package rr.language.psi;

import com.intellij.psi.tree.IElementType;
import rr.language.RRLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class RRElementType extends IElementType {

    public RRElementType(@NotNull @NonNls String debugName) {
        super(debugName, RRLanguage.INSTANCE);
    }

}
