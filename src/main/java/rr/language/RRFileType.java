package rr.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class RRFileType extends LanguageFileType {

    public static final RRFileType INSTANCE = new RRFileType();

    private RRFileType() {
        super(RRLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "RomeRemastered File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "RomeRemastered language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "txt";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return RRIcons.FILE;
    }

}