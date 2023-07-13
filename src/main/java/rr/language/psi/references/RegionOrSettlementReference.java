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
import rr.language.Util;
import rr.language.psi.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegionOrSettlementReference extends PsiReferenceBase<PsiElement> implements PsiReference {

    public RegionOrSettlementReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
    }

    @Override
    public @Nullable PsiElement resolve() {
        List<RRRegionNameDecl> factions = RRUtil.findAllRegions(myElement.getProject()).stream()
            .map(it -> it.getRegionNameDecl())
            .filter(it -> myElement.getText().equals(it.getText()))
            .collect(Collectors.toList());

        if (!factions.isEmpty()) {
            return factions.get(0);
        }

        List<RRSettlementNameDecl> cultures = RRUtil.findAllSettlements(myElement.getProject()).stream()
            .filter(it -> myElement.getText().equals(it.getText()))
            .collect(Collectors.toList());

        if (!cultures.isEmpty()) {
            return cultures.get(0);
        }

        return null;
    }

    @Override
    public PsiElement handleElementRename(String newName) throws IncorrectOperationException {
        ((RRFactionOrCultureRef) myElement).setName(newName);
        return myElement;
    }

    public Object @NotNull [] getVariants() {
        return Stream.concat(
                RRUtil.findAllRegionsAsStrings(myElement.getProject()).stream(),
                RRUtil.findAllSettlementsAsStrings(myElement.getProject()).stream()
            ).map(it -> LookupElementBuilder.create(it))
            .toArray();
    }
}