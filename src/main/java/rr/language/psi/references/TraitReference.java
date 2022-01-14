package rr.language.psi.references;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.RRUtil;
import rr.language.psi.*;

import java.util.List;
import java.util.stream.Collectors;

public class TraitReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public TraitReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        RRFile file = RRUtil.findRRFile("export_descr_character_traits.txt", myElement.getProject());

        if (file == null) {
            return null;
        }

        List<RRTraitDef> traits = file.findChildByClass(RRExportDescrCharacterTraits.class)
            .getTraitDefList().stream()
            .filter(it -> it.getTraitNameDecl().getText().equals(myElement.getText()))
            .collect(Collectors.toList());

        if (traits.isEmpty()) {
            return null;
        }

        return traits.get(0).getTraitNameDecl();
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRTraitRef) myElement).setName(newName);
        return myElement;
    }

    public Object @NotNull [] getVariants() {
        return RRUtil.findAllTraitsAsStrings(myElement.getProject()).stream()
            .map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}
