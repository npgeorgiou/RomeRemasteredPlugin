package rr.language

import com.intellij.psi.PsiElement

object Util {
    @JvmStatic
    fun quote(string: String): String {
        return "\"" + string + "\""
    }

    @JvmStatic
    fun unquote(string: String): String {
        return if (string.startsWith("\"") && string.endsWith("\"")) {
            string.substring(1, string.length - 1)
        } else {
            string
        }
    }

    @JvmStatic
    fun removeLastOccurrence(string: String, search: String): String {
        return if (string.endsWith(search)) {
            string.substring(0, string.length - search.length)
        } else {
            string
        }
    }

    @JvmStatic
    fun replaceChildrenOf(of: PsiElement, with: PsiElement): PsiElement {
        val firstChild = of.firstChild
        val lastChild = of.lastChild
        of.addRange(with.firstChild, with.lastChild)
        of.deleteChildRange(firstChild, lastChild)
        return of
    }
}