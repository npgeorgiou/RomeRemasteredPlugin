package rr.language.psi.references

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.util.IncorrectOperationException
import rr.language.RRUtil.findAllAiPersonalities
import rr.language.RRUtil.findAllAiPersonalitiesAsStrings
import rr.language.psi.RRAiPersonalityRef

class AiPersonalityReference(element: PsiElement, rangeInElement: TextRange?) :
    PsiReferenceBase<PsiElement?>(element, rangeInElement), PsiReference {
    override fun resolve(): PsiElement? {
        val items = findAllAiPersonalities(myElement!!.project)
            .map { it.aiPersonalityNameDecl }
            .filter { it.text == myElement!!.text }

        return items.firstOrNull()
    }

    @Throws(IncorrectOperationException::class)
    override fun handleElementRename(newName: String): PsiElement {
        (myElement as RRAiPersonalityRef).setName(newName)
        return myElement as RRAiPersonalityRef
    }

    override fun getVariants(): Array<Any> {
        return findAllAiPersonalitiesAsStrings(myElement!!.project)
            .map { LookupElementBuilder.create(it) }.toTypedArray()
    }
}
