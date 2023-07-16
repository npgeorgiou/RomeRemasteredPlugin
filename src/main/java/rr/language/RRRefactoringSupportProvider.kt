package rr.language

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement

class RRRefactoringSupportProvider : RefactoringSupportProvider() {
    override fun isMemberInplaceRenameAvailable(elementToRename: PsiElement, context: PsiElement?): Boolean {
        return true
    }
}