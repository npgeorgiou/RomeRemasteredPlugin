package rr.language.actions

import com.intellij.lang.ASTNode
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.TokenSet
import com.intellij.psi.util.PsiTreeUtil
import rr.language.RRUtil.collectLeaves
import rr.language.RRUtil.findRRFile
import rr.language.psi.*
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException
import java.util.*
import java.util.stream.Collectors

class ExpandMacros : AnAction() {
    override fun update(e: AnActionEvent) {
        // Set the availability based on whether a project is open
        val project = e.project
        e.presentation.isEnabledAndVisible = project != null
    }

    override fun actionPerformed(event: AnActionEvent) {
        val file = event.getData(PlatformDataKeys.VIRTUAL_FILE) ?: return
        val project = event.project!!
        val rrFile = findRRFile(file.name, project)
        val copy = rrFile!!.copy()
        expandMacrosIn(copy, PsiTreeUtil.findChildrenOfAnyType(copy, RRMacroDef_::class.java), project)
        for (macroDef in PsiTreeUtil.findChildrenOfAnyType(copy, RRMacroDef_::class.java)) {
            macroDef.delete()
        }
        val path = file.path.replace("#", "@")
        try {
            val writer = BufferedWriter(FileWriter(path))
            writer.write(copy.text)
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun expandMacrosIn(statement: PsiElement?, macroDefs: Collection<RRMacroDef_>, project: Project?) {
        val macrosUsages = PsiTreeUtil.findChildrenOfAnyType(statement, RRMacro_::class.java)
        for (macroUsage in macrosUsages) {
            val name = macroUsage.macroName!!.text
            val args = macroUsage.macroArgList
            for (macroDef in macroDefs) {
                if (macroDef.macroName!!.text == name) {
                    val params = macroDef.macroParamList
                    var expandedString = macroDef.text
                    val paramsIterator: Iterator<RRMacroParam> = params.iterator()
                    val argsIterator: Iterator<RRMacroArg> = args.iterator()
                    while (paramsIterator.hasNext() && argsIterator.hasNext()) {
                        expandedString = expandedString.replace(
                            " " + paramsIterator.next().text,
                            " " + argsIterator.next().text
                        )
                    }
                    val createdMacroDef =
                        RRElementFactory.createScript(project, expandedString).statementList[0].macroDef_
                    for (s in createdMacroDef!!.statementList) {
                        expandMacrosIn(s, macroDefs, project)
                    }

                    // Take care of indentation of inserted elements.
                    val wsToAdd = PsiTreeUtil.prevLeaf(macroUsage)!!.text.replace("\n", "")
                    val createdWss = collectLeaves(
                        createdMacroDef,
                        { it: PsiElement? ->
                            Arrays.stream(
                                it!!.node.getChildren(TokenSet.WHITE_SPACE)
                            )
                                .map { astNode: ASTNode -> astNode.psi }
                                .collect(Collectors.toList())
                        },
                        ArrayList()
                    )
                    for (createdWs in createdWss) {
                        if (createdWs!!.text.contains("\n")) {
                            val newWs = RRElementFactory.createWhitespace(createdWs.text + wsToAdd, project)
                            createdWs.replace(newWs)
                        }
                    }
                    macroUsage.parent.addRangeAfter(
                        createdMacroDef.statementList[0],
                        createdMacroDef.statementList[createdMacroDef.statementList.size - 1],
                        macroUsage
                    )
                    macroUsage.delete()
                    break
                }
            }
        }
    }
}
