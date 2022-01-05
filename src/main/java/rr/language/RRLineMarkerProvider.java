package rr.language;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import rr.language.psi.RRFile;

import java.util.Collection;

public class RRLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result
    ) {
        boolean isTxtFile = element.getNode().getElementType().toString().equals("RRTokenType.TXT_FILE");
        if (!isTxtFile) return;

        RRFile file = RRUtil.findRRFile(element.getText(), element.getProject());

        if (file == null) return;

        RelatedItemLineMarkerInfo<PsiElement> marker = NavigationGutterIconBuilder.create(RRIcons.FILE)
            .setTarget(file)
            .setTooltipText("Navigate to file")
            .createLineMarkerInfo(element);

        result.add(marker);
    }

}