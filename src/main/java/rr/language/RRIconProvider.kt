package rr.language

import com.intellij.ide.IconProvider
import com.intellij.psi.PsiElement
import javax.swing.Icon

class RRIconProvider : IconProvider() {
    override fun getIcon(psiElement: PsiElement, flags: Int): Icon? {
        val containingFile = psiElement.containingFile ?: return null
        return if (!RRUtil.isScript(containingFile)) null else RRIcons.SCRIPT_FILE
    }
}