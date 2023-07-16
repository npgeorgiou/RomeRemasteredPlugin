package rr.language.inspections

import com.intellij.codeInspection.*
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiFile
import com.intellij.util.ObjectUtils
import rr.language.psi.RRFile
import rr.language.psi.RRVisitor

open class Inspector : LocalInspectionTool() {
    override fun buildVisitor(
        holder: ProblemsHolder,
        isOnTheFly: Boolean,
        session: LocalInspectionToolSession
    ): PsiElementVisitor {
        val file = ObjectUtils.tryCast(session.file, RRFile::class.java)
        return if (file != null) buildRRVisitor(holder, session) else RRVisitor()
    }

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        throw IllegalStateException()
    }

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor>? {
        throw IllegalStateException()
    }

    protected open fun buildRRVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession): RRVisitor {
        return object : RRVisitor() {
            override fun visitFile(file: PsiFile) {
                checkFile(file as RRFile, holder)
            }
        }
    }

    protected fun checkFile(file: RRFile, problemsHolder: ProblemsHolder) {}
}