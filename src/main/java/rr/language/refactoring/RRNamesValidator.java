package rr.language.refactoring;

import com.intellij.lang.refactoring.NamesValidator;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import rr.language.psi.RRElementFactory;

public class RRNamesValidator implements NamesValidator {
    @Override
    public boolean isKeyword(@NotNull String name, Project project) {
        return false;
    }

    @Override
    public boolean isIdentifier(@NotNull String name, Project project) {
//        return RRElementFactory.createId(project, name).getNode().getElementType().toString().equals("RRTokenType.ID");

        // By default, intellij does not recognize special symbols and whitespace as valid identifier chars.
        // This means that using refactor renaming to rename to something like:
        // mines+2, or
        // east heavy cavalry (unit name)
        // will make intellij stop the renaming with message "Inserted identifier is not valid"
        return true;
    }
}