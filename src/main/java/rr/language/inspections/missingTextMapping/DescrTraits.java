package rr.language.inspections.missingTextMapping;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import rr.language.RRUtil;
import rr.language.inspections.Inspector;
import rr.language.psi.RRTraitDef;
import rr.language.psi.RRTraitLevel;
import rr.language.psi.RRVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class DescrTraits extends Inspector {
    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitTraitDef(@NotNull RRTraitDef element) {
                Collection<String> uiTextKeys = RRUtil.findTextMappingsInFile("export_vnvs.txt", element.getProject()).stream()
                    .map(it -> it.getId().getText())
                    .collect(Collectors.toList());

                for (RRTraitLevel traitLevel : element.getTraitLevelList()) {
                    var levelName = traitLevel.getFirstChild().getNextSibling().getNextSibling();
                    var description = RRUtil.nextSibling(levelName, it -> it.getText().equals("Description")).getNextSibling().getNextSibling();
                    var effectsDescription = RRUtil.nextSibling(levelName, it -> it.getText().equals("EffectsDescription")).getNextSibling().getNextSibling();

                    var toCheck = new ArrayList<>(Arrays.asList(levelName,description,effectsDescription));

                    var gainMessageKeyword = RRUtil.nextSibling(levelName, it -> it.getText().equals("GainMessage"));
                    if (gainMessageKeyword != null) {
                        var gainMessage = gainMessageKeyword.getNextSibling().getNextSibling();
                        toCheck.add(gainMessage);
                    }

                    var loseMessageKeyword = RRUtil.nextSibling(levelName, it -> it.getText().equals("LoseMessage"));
                    if (loseMessageKeyword != null) {
                        var loseMessage = loseMessageKeyword.getNextSibling().getNextSibling();
                        toCheck.add(loseMessage);
                    }

                    var epithetKeyword = RRUtil.nextSibling(levelName, it -> it.getText().equals("Epithet"));
                    if (epithetKeyword != null) {
                        var epithet = epithetKeyword.getNextSibling().getNextSibling();
                        toCheck.add(epithet);
                    }

                    for (PsiElement e : toCheck) {
                        if (!uiTextKeys.contains(e.getText())) {
                            holder.registerProblem(e, "No description in export_vnvs.txt", ProblemHighlightType.ERROR);
                        }
                    }
                }
            }
        };
    }
}