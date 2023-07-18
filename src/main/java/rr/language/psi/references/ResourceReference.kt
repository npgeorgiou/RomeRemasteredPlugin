package rr.language.psi.references

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.util.IncorrectOperationException
import rr.language.RRUtil.findAllResources
import rr.language.RRUtil.findAllResourcesAsStrings
import rr.language.Util.unquote
import rr.language.psi.RRResourceNameDecl
import rr.language.psi.RRResourceRef
import java.util.stream.Collectors

class ResourceReference(element: PsiElement, rangeInElement: TextRange?) :
    PsiReferenceBase<PsiElement?>(element, rangeInElement), PsiReference {
    override fun resolve(): PsiElement? {
        val items = findAllResources(false, myElement!!.project)
            .filter { unquote(it.text) == unquote(myElement!!.text) }

        return items.firstOrNull()
    }

    @Throws(IncorrectOperationException::class)
    override fun handleElementRename(newName: String): PsiElement {
        (myElement as RRResourceRef).setName(newName)
        return myElement as RRResourceRef
    }

    override fun getVariants(): Array<Any> {
        val onlyHidden = myElement!!.prevSibling.prevSibling.text == "hidden_resource"
        return findAllResourcesAsStrings(onlyHidden, myElement!!.project)
            .map { LookupElementBuilder.create(it) }
            .toTypedArray()
    }
}
