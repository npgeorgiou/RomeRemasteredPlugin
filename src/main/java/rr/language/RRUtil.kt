package rr.language

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import rr.language.Util.removeLastOccurrence
import rr.language.Util.unquote
import rr.language.psi.*
import java.io.File
import java.util.*
import java.util.function.Function
import java.util.function.Predicate
import java.util.stream.Collectors


object RRUtil {
    @JvmStatic
    fun findAllTgaFiles(project: Project?): List<PsiFile?> {
        return FilenameIndex.getAllFilesByExt(project!!, "tga").stream()
            .map { it: VirtualFile? -> PsiManager.getInstance(project).findFile(it!!) }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findTgaFile(path: String, project: Project?): PsiFile? {
        val files = findAllTgaFiles(project)
        return files.find { it!!.virtualFile.path.endsWith("/$path") }
    }

    @JvmStatic
    fun findAllRRFiles(project: Project?): List<RRFile> {
        val virtualFiles = FileTypeIndex.getFiles(
            RRFileType.INSTANCE,
            GlobalSearchScope.allScope(project!!)
        )

        return virtualFiles
            .map { it: VirtualFile? ->
                // Very big files cant be cast to RRfiles.
                try {
                    return@map PsiManager.getInstance(project).findFile(it!!) as RRFile
                } catch (e: Exception) {
                    return@map null
                }
            }
            .filterNotNull()
    }

    @JvmStatic
    fun findRRFile(path: String, project: Project): RRFile? {
        val file = File(project.basePath + "/" + path)
        val virtualFile = LocalFileSystem.getInstance().findFileByIoFile(file)

        if (virtualFile == null) {
            return findRRFileThatEndsWith(path, project)
        }

        return PsiManager.getInstance(project).findFile(virtualFile) as RRFile
    }

    @JvmStatic
    fun findRRFileThatEndsWith(path: String, project: Project?): RRFile? {
        val files = findAllRRFiles(project)
        return files.find { it.virtualFile.path.endsWith(path) }
    }

    @JvmStatic
    fun findAllUnits(project: Project): List<RRUnitItem_> {
        val file = findRRFile("export_descr_unit.txt", project) ?: return emptyList()

        return file.findChildByClass(RRExportDescrUnit::class.java)!!.unitItem_List
    }

    @JvmStatic
    fun findAllUnitsAsStrings(project: Project): List<String> {
        return findAllUnits(project).map { it.unitNameDecl.text }
    }

    @JvmStatic
    fun findAllAiPersonalities(project: Project): List<RRAiPersonality> {
        val file = findRRFile("feral_descr_ai_personality.txt", project) ?: return emptyList()

        return file.findChildByClass(RRFeralDescrAiPersonality::class.java)!!.aiPersonalityList
    }

    @JvmStatic
    fun findAllAiPersonalitiesAsStrings(project: Project): List<String> {
        return findAllAiPersonalities(project).map { it.aiPersonalityNameDecl.text }
    }

    @JvmStatic
    fun findAllRegions(project: Project): List<RRRegionDef> {
        val file = findRRFile("descr_regions.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrRegions::class.java)!!.regionDefList
    }

    @JvmStatic
    fun findAllRegionsAsStrings(project: Project): List<String> {
        return findAllRegions(project).map { it.firstChild.text }
    }

    @JvmStatic
    fun findAllSettlements(project: Project): List<RRSettlementNameDecl> {
        val file = findRRFile("descr_regions.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrRegions::class.java)!!.regionDefList
            .map { it.settlementNameDecl }
    }

    @JvmStatic
    fun findAllSettlementsAsStrings(project: Project): List<String> {
        return findAllSettlements(project).map { it.firstChild.text }
    }

    @JvmStatic
    fun findAllRebelFactions(project: Project): List<RRRebelFaction> {
        val file = findRRFile("descr_rebel_factions.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrRebelFactions::class.java)!!.rebelFactionList
            // If rebel_type is an enum, rebelFactionNameDef won't be there
            .filter { it.rebelFactionNameDef != null}
    }

    @JvmStatic
    fun findAllRebelFactionsAsStrings(project: Project): List<String> {
        return findAllRebelFactions(project).map { it.rebelFactionNameDef!!.text }
    }

    @JvmStatic
    fun findAllWonders(project: Project): List<RRWonderNameDecl> {
        val file = findRRFile("descr_sm_landmarks.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrSmLandmarks::class.java)!!.wonderNameDeclList
    }

    @JvmStatic
    fun findAllWondersAsStrings(project: Project): List<String> {
        return findAllWonders(project).map { it.text }
    }

    @JvmStatic
    fun findAllProjectiles(project: Project): List<RRProjectile_> {
        val file = findRRFile("descr_projectile_new.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrProjectile::class.java)!!.projectile_List
    }

    @JvmStatic
    fun findAllProjectilesAsStrings(project: Project): List<String> {
        return findAllProjectiles(project).map { it.projectileNameDecl.text }
    }

    @JvmStatic
    fun findAllAnimals(project: Project): List<RRAnimal_> {
        val file = findRRFile("descr_animals.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrAnimals::class.java)!!.animal_List
    }

    @JvmStatic
    fun findAllAnimalsAsStrings(project: Project): List<String> {
        return findAllAnimals(project).map { it.animalNameDecl.text }
    }

    @JvmStatic
    fun findAllDisasters(project: Project): List<RRDisaster_> {
        val file = findRRFile("descr_disasters.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrDisasters::class.java)!!.disaster_List
    }

    @JvmStatic
    fun findAllDisastersAsStrings(project: Project): List<String> {
        return findAllDisasters(project).map { it.disasterNameDecl.text }
    }

    @JvmStatic
    fun findAllCounters(element: PsiElement): List<RRCounterNameDecl> {
        val file = element.containingFile as RRFile
        return PsiTreeUtil.findChildrenOfAnyType(file, RRCounterNameDecl::class.java).toList()
    }

    @JvmStatic
    fun findAllCountersAsStrings(element: PsiElement): List<String> {
        return findAllCounters(element).map { it: RRCounterNameDecl -> it.text }
    }

    @JvmStatic
    fun findAllMounts(project: Project): List<RRMount_> {
        val file = findRRFile("descr_mount.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrMount::class.java)!!.mount_List
    }

    @JvmStatic
    fun findAllMountsAsStrings(project: Project): List<String> {
        return findAllMounts(project).map { it.mountNameDecl!!.text }
    }

    @JvmStatic
    fun findAllShips(project: Project): List<RRShip_> {
        val file = findRRFile("descr_ship.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrShip::class.java)!!.ship_List
    }

    @JvmStatic
    fun findAllShipsAsStrings(project: Project): List<String> {
        return findAllShips(project).map { it.shipNameDecl.text }
    }

    @JvmStatic
    fun findAllModels(project: Project): List<RRModel_> {
        val battleModels = findRRFile("descr_model_battle.txt", project)
            ?.findChildByClass(RRDescrModelBattle::class.java)!!
            .model_List

        val stratModels = findRRFile("descr_model_strat.txt", project)
            ?.findChildByClass(RRDescrModelStrat::class.java)!!
            .model_List

        return battleModels + stratModels
    }

    @JvmStatic
    fun findAllModelsAsStrings(project: Project): List<String> {
        return findAllModels(project).map { it.modelNameDecl.text }
    }

    @JvmStatic
    fun findAllEthnicityMakeups(project: Project): List<RREthnicityMakeup_> {
        val file = findRRFile("descr_unit_variation.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrUnitVariation::class.java)!!.ethnicityMakeup_List
    }

    @JvmStatic
    fun findAllEthnicityMakeupsAsStrings(project: Project): List<String> {
        return findAllEthnicityMakeups(project).map { it.ethnicityMakeupNameDecl.text }
    }

    @JvmStatic
    fun findAllFactions(project: Project): List<RRFactionNameDecl> {
        val file = findRRFile("descr_sm_factions.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrSmFactions::class.java)!!.factionDeclList
            .map { it.factionNameDecl }
    }

    @JvmStatic
    fun findAllFactionsAsStrings(project: Project): List<String> {
        return findAllFactions(project).map { unquote(it.text) }
    }

    @JvmStatic
    fun findAllReligions(project: Project): List<RRReligionNameDecl> {
        val file = findRRFile("descr_beliefs.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrBeliefs::class.java)!!.religion_List.map { it.religionNameDecl }
    }

    @JvmStatic
    fun findAllReligionsAsStrings(project: Project): List<String> {
        return findAllReligions(project).map { unquote(it.text) }
    }

    @JvmStatic
    fun findAllFactionGroups(project: Project): List<RRFactionGroup> {
        val file = findRRFile("descr_faction_groups.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrFactionGroups::class.java)!!.factionGroupList
    }

    fun findAllFactionGroupsAsStrings(project: Project): List<String> {
        return findAllFactionGroups(project).map { it.factionGroupNameDecl.text }
    }

    @JvmStatic
    fun findAllCultures(project: Project): List<RRCultureNameDecl> {
        val file = findRRFile("descr_cultures.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrCultures::class.java)!!.cultureNameDeclList
    }

    @JvmStatic
    fun findAllCulturesAsStrings(project: Project): List<String> {
        return findAllCultures(project).map { unquote(it.text) }
    }

    @JvmStatic
    fun findAllAmbientObjects(project: Project): List<RRAmbientObjectNameDecl> {
        val file = findRRFile("descr_sm_ambient_objects.txt", project) ?: return emptyList()

        return file.findChildByClass(RRDescrSmAmbientObjects::class.java)!!.ambientObjectDeclList
            .map { it.ambientObjectNameDecl }
    }

    @JvmStatic
    fun findAllAmbientObjectsAsStrings(project: Project): List<String> {
        return findAllAmbientObjects(project).map { unquote(it.text) }
    }

    @JvmStatic
    fun findAllResources(hidden: Boolean, project: Project): List<RRResourceNameDecl> {
        val file = findRRFile("descr_sm_resources.txt", project) ?: return emptyList()

        var filter = { _: RRResourceDecl -> true }
        if (hidden) {
            filter = { it: RRResourceDecl -> unquote(it.resourceType.text) == "hidden" }
        }

        return file.findChildByClass(RRDescrSmResources::class.java)!!.resourceDeclList
            .filter(filter)
            .map { it.resourceNameDecl }
    }

    @JvmStatic
    fun findAllResourcesAsStrings(hidden: Boolean, project: Project): List<String> {
        return findAllResources(hidden, project).map { unquote(it.text) }
    }

    @JvmStatic
    fun findAllBuildingTrees(project: Project): List<RRBuildingTree> {
        val file = findRRFile("export_descr_buildings.txt", project) ?: return emptyList()
        return file.findChildByClass(RRExportDescrBuildings::class.java)!!.buildingTreeList
    }

    @JvmStatic
    fun findAllBuildingTreesAsStrings(project: Project): List<String> {
        return findAllBuildingTrees(project).map { it.buildingTreeNameDecl!!.text }
    }

    @JvmStatic
    fun findAllBuildingLevels(project: Project): List<RRBuildingLevel> {
        return findAllBuildingTrees(project).flatMap { it.buildingLevelList }
    }

    fun findAllBuildingTreesUiNames(project: Project): List<String> {
        val file = findRRFile("export_buildings.txt", project) ?: return emptyList()

        return file.findChildByClass(RRExportBuildings::class.java)!!
            .buriedBuildingTreeOrLevelRefList
            .map { it.id.text }
    }

    @JvmStatic
    fun findAllBuildingLevelsAsStrings(project: Project): List<String> {
        return findAllBuildingLevels(project).map { it.buildingLevelNameDecl.text }
    }

    @JvmStatic
    fun findAllAncillaries(project: Project): List<RRAncillaryDef> {
        val file = findRRFile("export_descr_ancillaries.txt", project) ?: return emptyList()

        return getFromCacheOr(file, {
            file.findChildByClass(RRExportDescrAncillaries::class.java)!!.ancillaryDefList
        })
    }

    @JvmStatic
    fun findAllAncillariesAsStrings(project: Project): List<String> {
        return findAllAncillaries(project).map { it.ancillaryNameDecl!!.text }
    }

    @JvmStatic
    fun findAllAncillaryDescriptions(project: Project): List<RRAncillaryDescrDef> {
        return findAllAncillaries(project).flatMap { it.ancillaryDescrDefList }
    }

    @JvmStatic
    fun findAllAncillaryDescriptionsAsStrings(project: Project): List<String> {
        val ancillaries = findAllAncillaries(project)
        val descriptionDefs = ancillaries.flatMap { it.ancillaryDescrDefList }.map { it.text } +
                ancillaries.map { it.ancillaryNameDecl!!.text }

        return descriptionDefs
    }

    @JvmStatic
    fun findAllUnitDescriptions(project: Project): List<RRUnitDescrDef> {
        return findAllUnits(project).map { it.unitDescrDef!! }
    }

    @JvmStatic
    fun findAllTraits(project: Project): List<RRTraitDef> {
        val file = findRRFile("export_descr_character_traits.txt", project) ?: return emptyList()

        return getFromCacheOr(file, {
            file.findChildByClass(RRExportDescrCharacterTraits::class.java)!!.traitDefList
        })
    }

    @JvmStatic
    fun findAllTraitsAsStrings(project: Project): List<String> {
        return findAllTraits(project)
            .map { it.traitNameDecl!!.text }
    }

    @JvmStatic
    fun findAllTraitDescriptions(project: Project): List<RRTraitDescrDef> {
        return findAllTraits(project)
            .flatMap { it.traitLevelList }
            .flatMap { it.traitDescrDefList }
    }

    @JvmStatic
    fun removePostfixesFromExportBuildingsId(id: String, project: Project): String {
        var name = removeLastOccurrence(id, "_name")
        name = removeLastOccurrence(name, "_desc_short")
        name = removeLastOccurrence(name, "_desc")

        val factionsAndCulturesNames = findAllFactionsAsStrings(project) + findAllCulturesAsStrings(project)
        for (factionOrCultureName in factionsAndCulturesNames) {
            name = removeLastOccurrence(name, "_$factionOrCultureName")
        }

        return name
    }

    fun removePostfixesFromExportUnitsId(id: String): String {
        var name = removeLastOccurrence(id, "_descr_short")
        name = removeLastOccurrence(name, "_descr")
        return name
    }

    @JvmStatic
    fun isScript(file: PsiFile): Boolean {
        return file.text.contains("script\n") && file.text.contains("end_script")
    }

    @JvmStatic
    fun collectLeaves(
        element: PsiElement,
        mapper: Function<PsiElement?, Collection<PsiElement?>?>,
        acc: MutableCollection<PsiElement?>
    ): Collection<PsiElement?> {
        acc.addAll(mapper.apply(element)!!)
        for (child in element.children) {
            collectLeaves(child, mapper, acc)
        }
        return acc
    }

    fun getSiblingsBetween(start: PsiElement, end: PsiElement): Collection<PsiElement>? {
        val list: MutableCollection<PsiElement> = ArrayList()
        list.add(start)
        var next = start.nextSibling
        while (true) {
            if (next == null) {
                return null
            }
            list.add(next)
            if (next == end) {
                break
            }
            next = next.nextSibling
        }
        return list
    }

    fun <T : PsiElement> previousSibling(
        element: T,
        foundCondition: Predicate<PsiElement?>
    ): T? {
        var prev: PsiElement = element
        while (prev.prevSibling.also { prev = it } != null) {
            if (foundCondition.test(prev)) return prev as T
        }
        return null
    }

    fun <T : PsiElement> nextSibling(
        element: T,
        foundCondition: Predicate<PsiElement?>
    ): T? {
        var next: PsiElement = element
        while (next.nextSibling.also { next = it } != null) {
            if (foundCondition.test(next)) return next as T
        }
        return null
    }

    private var cache: HashMap<String, Any> = HashMap()
    private fun <T> getFromCacheOr(file: RRFile, fetcher: () -> T): T {
        val path = file.virtualFile.name
        val modificationStamp = file.virtualFile.modificationStamp
        val key = "$path:$modificationStamp"

        if (cache.containsKey(key)) {
            return cache[key] as T
        }

        val value = fetcher()

        val previousKey = cache.filterKeys { it.startsWith("$path:") }.keys.firstOrNull()
        if (previousKey != null) {
            cache.remove(previousKey)
        }

        cache[key] = value as Any
        return value
    }
}
