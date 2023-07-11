package rr.language.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiParserFacade;
import rr.language.RRFileType;
import rr.language.RRLanguage;
import rr.language.Util;

import java.util.Collection;
import java.util.stream.Collectors;

public class RRElementFactory {
    public static PsiElement createId(Project project, String id) {
        RRFile file = createFile(project, id);
        return file.getFirstChild();
    }

    public static RRScript_ createScript(Project project, String content) {
        String contents = "script" + "\n" +
            content + "\n" +
            "end_script";
        RRFile file = createFile(project, contents);
        return file.findChildByClass(RRScript_.class);
    }

    public static PsiElement createElementWithIds(Project project, String name) {
        String contents = "type             " + name + "\n" +
            "dictionary       foo";
        RRFile file = createFile(project, contents);

        return file.findChildByClass(RRExportDescrUnit.class).getUnitItem_List().get(0).getUnitNameDecl();
    }

    public static RRUnitRef createUnitRef(Project project, String name) {
        String contents = "pool foo\n" +
            "regions bar\n" +
            "unit " + name + ", exp 0 cost 0 replenish 0.0 - 0.0 max 0 initial 0";
        RRFile file = createFile(project, contents);

        return file.findChildByClass(RRDescrMercenaries.class).getMercenaryPoolList().get(0).getUnitRefList().get(0);
    }

    public static PsiElement createString(Project project, String string) {
        RRFile file = createFile(project, Util.quote(string));
        return file.getFirstChild();
    }

    public static RRFile createFile(Project project, String text) {
        String name = "dummy.txt";
        return (RRFile) PsiFileFactory.getInstance(project).createFileFromText(name, RRFileType.INSTANCE, text);
    }

    public static PsiElement createComment(Project project, String text) {
        RRFile file = createFile(project, text + "\nfoo");
        return file.getFirstChild();
    }

    public static PsiElement createWhitespace(Project project) {
        return PsiParserFacade.SERVICE.getInstance(project).createWhiteSpaceFromText(" ");
    }

    public static PsiElement createWhitespace(String string, Project project) {
        return PsiParserFacade.SERVICE.getInstance(project).createWhiteSpaceFromText(string);
    }

    public static PsiElement createNewline(Project project) {
        // TODO: That should be working. Extremely weird. Before deploying, fix that.
//         return PsiParserFacade.SERVICE.getInstance(project).createWhiteSpaceFromText(System.lineSeparator());
        return PsiParserFacade.SERVICE.getInstance(project).createWhiteSpaceFromText("\n");
    }
}
