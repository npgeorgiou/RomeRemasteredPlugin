package rr.language.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import rr.language.RRFileType;
import rr.language.RRLanguage;

public class RRFile extends PsiFileBase {

    public RRFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, RRLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return RRFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "RomeRemastered File";
    }

}