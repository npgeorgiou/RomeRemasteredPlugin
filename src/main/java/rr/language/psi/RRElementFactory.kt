package rr.language.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiParserFacade
import rr.language.RRFileType
import rr.language.Util.quote

object RRElementFactory {
    fun createId(project: Project?, id: String?): PsiElement {
        val file = createFile(project, id)
        return file.firstChild
    }

    fun createScript(project: Project?, content: String): RRScript_ {
        val contents = """
             script
             $content
             end_script
             """
        val file = createFile(project, contents)
        return file.findChildByClass(RRScript_::class.java)!!
    }

    fun createIdWithPotentialWhitespaces(project: Project?, name: String?): PsiElement {
        val contents = """
             ;EXPORT_DESCR_UNIT_MARKER DO NOT REMOVE
             type $name
             dictionary foo
             """

        val file = createFile(project, contents)
        return file.findChildByClass(RRExportDescrUnit::class.java)!!.unitItem_List[0].unitNameDecl
    }

    fun createUnitRef(project: Project?, name: String): RRUnitRef {
        val contents = """
             pool foo
             regions bar
             unit $name, exp 0 cost 0 replenish 0.0 - 0.0 max 0 initial 0
             """

        val file = createFile(project, contents)
        return file.findChildByClass(RRDescrMercenaries::class.java)!!.mercenaryPoolList[0].unitRefList[0]
    }

    fun createString(project: Project?, string: String?): PsiElement {
        val file = createFile(project, quote(string!!))
        return file.firstChild
    }

    fun createFile(project: Project?, text: String?): RRFile {
        val name = "dummy.txt"
        return PsiFileFactory.getInstance(project).createFileFromText(name, RRFileType.INSTANCE, text!!) as RRFile
    }

    fun createComment(project: Project?, text: String?): PsiElement {
        val file = createFile(project, text)
        return file.firstChild
    }

    fun createWhitespace(project: Project?): PsiElement {
        return PsiParserFacade.getInstance(project).createWhiteSpaceFromText(" ")
    }

    fun createWhitespace(string: String?, project: Project?): PsiElement {
        return PsiParserFacade.getInstance(project).createWhiteSpaceFromText(string!!)
    }

    fun createNewline(project: Project?): PsiElement {
        // TODO: That should be working. Extremely weird. Before deploying, fix that.
//         return PsiParserFacade.SERVICE.getInstance(project).createWhiteSpaceFromText(System.lineSeparator());
        return PsiParserFacade.getInstance(project).createWhiteSpaceFromText("\n")
    }
}
