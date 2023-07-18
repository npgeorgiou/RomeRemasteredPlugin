package rr.language.psi.references

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.util.IncorrectOperationException
import rr.language.RRUtil.findAllBuildingLevels
import rr.language.RRUtil.findAllBuildingLevelsAsStrings
import rr.language.RRUtil.findAllBuildingTreesAsStrings
import rr.language.RRUtil.findAllBuildingTrees
import rr.language.RRUtil.removePostfixesFromExportBuildingsId
import rr.language.psi.RRBuriedBuildingTreeOrLevelRef

class BuildingTreeOrLevelReference(element: PsiElement, rangeInElement: TextRange?) :
    PsiReferenceBase<PsiElement?>(element, rangeInElement), PsiReference {
    override fun resolve(): PsiElement? {
        val project = myElement!!.project
        val name = removePostfixesFromExportBuildingsId(myElement!!.text, project)

        val trees = findAllBuildingTrees(project)
            .filter { it.buildingTreeNameDecl!!.text == name }
            .map { it.buildingTreeNameDecl }

        if (trees.isNotEmpty()) {
            return trees.first()
        }

        val levels = findAllBuildingLevels(project)
            .filter { it.firstChild.text == name }
            .map { it.buildingLevelNameDecl }

        return levels.firstOrNull()
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return resolve() == element
    }

    @Throws(IncorrectOperationException::class)
    override fun handleElementRename(newName: String): PsiElement {
        (myElement as RRBuriedBuildingTreeOrLevelRef).setName(newName)
        return myElement as RRBuriedBuildingTreeOrLevelRef
    }

    override fun getVariants(): Array<Any> {
        val all = findAllBuildingTreesAsStrings(myElement!!.project) +
                findAllBuildingLevelsAsStrings(myElement!!.project)

        return all.map { LookupElementBuilder.create(it) }.toTypedArray()
    }
}
