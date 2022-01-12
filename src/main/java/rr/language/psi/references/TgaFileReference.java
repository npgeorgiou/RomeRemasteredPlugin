package rr.language.psi.references;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRUtil;
import rr.language.Util;
import rr.language.psi.RRBuildingTree;
import rr.language.psi.RRResourceDecl;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class TgaFileReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public TgaFileReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    public TgaFileReference(@NotNull PsiElement element) {
        super(element);
    }

    @Override
    public @Nullable PsiFile resolve() {
        String name = Util.unquote(myElement.getText());

        if (isBuildingIcon()) {
            name = "ui/building_icons/" + myElement.getText() + ".tga";
        }

        return RRUtil.findTgaFile(name, myElement.getProject());
    }

    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        // TODO: Maybe implement if I find a way to properly link File to references too.
        return myElement;
    }

    @Override
    public Object @NotNull [] getVariants() {
        Predicate<PsiFile> filter = it -> true;
        Function<PsiFile, String> map = it -> it.getVirtualFile().getName();

        if (isBuildingIcon()) {
            filter = it -> it.getVirtualFile().getPath().contains("ui/building_icons/");
            map = it -> it.getVirtualFile().getName().replace(".tga", "");
        }

        return RRUtil.findAllTgaFiles(myElement.getProject()).stream()
            .filter(filter)
            .map(map)
            .map(it -> LookupElementBuilder.create(it))
            .toArray();
    }

    private boolean isBuildingIcon() {
        return myElement.getParent() instanceof RRBuildingTree;
    }
}
