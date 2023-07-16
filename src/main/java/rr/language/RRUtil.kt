package rr.language

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VfsUtil
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
import java.util.stream.Stream


object RRUtil {
    @JvmStatic
    fun findAllTgaFiles(project: Project?): MutableCollection<PsiFile?> {
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

        val a = 1
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

//        val files = findAllRRFiles(project)
//        return files.find { it.virtualFile.path.endsWith("/$path") }
    }

    @JvmStatic
    fun findRRFileThatEndsWith(path: String, project: Project?): RRFile? {
        val files = findAllRRFiles(project)
        return files.find { it.virtualFile.path.endsWith("/$path") }
    }

    fun findTextMappingsInFile(fileName: String, project: Project): Collection<RRTextMappingItem> {
        val file = findRRFile(fileName, project)
        return findTextMappingsInFile(file)
    }

    fun findTextMappingsInFile(file: RRFile?): Collection<RRTextMappingItem> {
        return if (file == null) {
            ArrayList()
        } else (file.firstChild as RRTextMappingFormat).textMappingItemList
    }

    @JvmStatic
    fun findAllUnits(project: Project): Collection<RRUnitItem_> {
        val file = findRRFile("export_descr_unit.txt", project)
        val opt = Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRExportDescrUnit::class.java
                )
            }
            .map { it: RRExportDescrUnit? -> it!!.unitItem_List }
        return opt.orElse(ArrayList())
    }

    @JvmStatic
    fun findAllUnitsAsStrings(project: Project): Collection<String> {
        return findAllUnits(project).stream()
            .map { it: RRUnitItem_ -> it.unitNameDecl.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllAiPersonalities(project: Project): Collection<RRAiPersonality> {
        val file = findRRFile("feral_descr_ai_personality.txt", project)
        val opt = Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRFeralDescrAiPersonality::class.java
                )
            }
            .map { it: RRFeralDescrAiPersonality? -> it!!.aiPersonalityList }
        return opt.orElse(ArrayList())
    }

    @JvmStatic
    fun findAllAiPersonalitiesAsStrings(project: Project): Collection<String> {
        return findAllAiPersonalities(project).stream()
            .map { it: RRAiPersonality -> it.aiPersonalityNameDecl.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllRegions(project: Project): Collection<RRRegionDef> {
        val file = findRRFile("descr_regions.txt", project)
        val opt = Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrRegions::class.java
                )
            }
            .map { it: RRDescrRegions? -> it!!.regionDefList }
        return opt.orElse(ArrayList())
    }

    @JvmStatic
    fun findAllRegionsAsStrings(project: Project): Collection<String> {
        return findAllRegions(project).stream()
            .map { it: RRRegionDef -> it.firstChild.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllSettlements(project: Project): Collection<RRSettlementNameDecl> {
        val file = findRRFile("descr_regions.txt", project)
        return Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrRegions::class.java
                )
            }
            .map { it: RRDescrRegions? -> it!!.regionDefList }
            .orElse(ArrayList()).stream()
            .map { it: RRRegionDef -> it.settlementNameDecl }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllSettlementsAsStrings(project: Project): Collection<String> {
        return findAllSettlements(project).stream()
            .map { it: RRSettlementNameDecl -> it.firstChild.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllRebelFactions(project: Project): Collection<RRRebelFaction> {
        val file = findRRFile("descr_rebel_factions.txt", project)
        val opt = Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrRebelFactions::class.java
                )
            }
            .map { it: RRDescrRebelFactions? -> it!!.rebelFactionList }
        return opt.orElse(ArrayList())
    }

    @JvmStatic
    fun findAllRebelFactionsAsStrings(project: Project): Collection<String> {
        return findAllRebelFactions(project).stream()
            .map { it: RRRebelFaction -> it.rebelFactionNameDef.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllWonders(project: Project): Collection<RRWonderNameDecl> {
        val file = findRRFile("descr_sm_landmarks.txt", project)
        val opt = Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrSmLandmarks::class.java
                )
            }
            .map { it: RRDescrSmLandmarks? -> it!!.wonderNameDeclList }
        return opt.orElse(ArrayList())
    }

    @JvmStatic
    fun findAllWondersAsStrings(project: Project): Collection<String> {
        return findAllWonders(project).stream()
            .map { it: RRWonderNameDecl -> it.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllProjectiles(project: Project): Collection<RRProjectile_> {
        val file = findRRFile("descr_projectile_new.txt", project)
        val opt = Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrProjectile::class.java
                )
            }
            .map { it: RRDescrProjectile? -> it!!.projectile_List }
        return opt.orElse(ArrayList())
    }

    @JvmStatic
    fun findAllProjectilesAsStrings(project: Project): Collection<String> {
        return findAllProjectiles(project).stream()
            .map { it: RRProjectile_ -> it.projectileNameDecl.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllAnimals(project: Project): Collection<RRAnimal_> {
        val file = findRRFile("descr_animals.txt", project)
        val opt = Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrAnimals::class.java
                )
            }
            .map { it: RRDescrAnimals? -> it!!.animal_List }
        return opt.orElse(ArrayList())
    }

    @JvmStatic
    fun findAllAnimalsAsStrings(project: Project): Collection<String> {
        return findAllAnimals(project).stream()
            .map { it: RRAnimal_ -> it.animalNameDecl.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllDisasters(project: Project): Collection<RRDisaster_> {
        val file = findRRFile("descr_disasters.txt", project)
        val opt = Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrDisasters::class.java
                )
            }
            .map { it: RRDescrDisasters? -> it!!.disaster_List }
        return opt.orElse(ArrayList())
    }

    @JvmStatic
    fun findAllDisastersAsStrings(project: Project): Collection<String> {
        return findAllDisasters(project).stream()
            .map { it: RRDisaster_ -> it.disasterNameDecl.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllCounters(element: PsiElement): Collection<RRCounterNameDecl> {
        val file = element.containingFile as RRFile
        return PsiTreeUtil.findChildrenOfAnyType(file, RRCounterNameDecl::class.java)
    }

    @JvmStatic
    fun findAllCountersAsStrings(element: PsiElement): Collection<String> {
        return findAllCounters(element).stream()
            .map { it: RRCounterNameDecl -> it.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllMounts(project: Project): Collection<RRMount_> {
        val file = findRRFile("descr_mount.txt", project)
        val opt = Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrMount::class.java
                )
            }
            .map { it: RRDescrMount? -> it!!.mount_List }
        return opt.orElse(ArrayList())
    }

    @JvmStatic
    fun findAllMountsAsStrings(project: Project): Collection<String> {
        return findAllMounts(project).stream()
            .map { it: RRMount_ ->
                it.mountNameDecl!!
                    .text
            }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllShips(project: Project): Collection<RRShip_> {
        val file = findRRFile("descr_ship.txt", project)
        val opt = Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrShip::class.java
                )
            }
            .map { it: RRDescrShip? -> it!!.ship_List }
        return opt.orElse(ArrayList())
    }

    @JvmStatic
    fun findAllShipsAsStrings(project: Project): Collection<String> {
        return findAllShips(project).stream()
            .map { it: RRShip_ -> it.shipNameDecl.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllModels(project: Project): Collection<RRModel_> {
        val battle_file = findRRFile("descr_model_battle.txt", project)
        val battle_models = Optional.ofNullable(battle_file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrModelBattle::class.java
                )
            }
            .map { it: RRDescrModelBattle? -> it!!.model_List }
        val strat_file = findRRFile("descr_model_strat.txt", project)
        val strat_models = Optional.ofNullable(strat_file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrModelStrat::class.java
                )
            }
            .map { it: RRDescrModelStrat? -> it!!.model_List }
        return Stream.concat(
            battle_models.orElse(ArrayList()).stream(),
            strat_models.orElse(ArrayList()).stream()
        ).collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllModelsAsStrings(project: Project): Collection<String> {
        return findAllModels(project).stream()
            .map { it: RRModel_ -> it.modelNameDecl.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllEthnicityMakeups(project: Project): Collection<RREthnicityMakeup_> {
        val file = findRRFile("descr_unit_variation.txt", project)
        val opt = Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrUnitVariation::class.java
                )
            }
            .map { it: RRDescrUnitVariation? -> it!!.ethnicityMakeup_List }
        return opt.orElse(ArrayList())
    }

    @JvmStatic
    fun findAllEthnicityMakeupsAsStrings(project: Project): Collection<String> {
        return findAllEthnicityMakeups(project).stream()
            .map { it: RREthnicityMakeup_ -> it.ethnicityMakeupNameDecl.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllFactions(project: Project): Collection<RRFactionNameDecl> {
        val file = findRRFile("descr_sm_factions.txt", project)
        return Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrSmFactions::class.java
                )
            }
            .map { it: RRDescrSmFactions? -> it!!.factionDeclList }
            .orElse(ArrayList())
            .stream().map { it: RRFactionDecl -> it.factionNameDecl }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllFactionsAsStrings(project: Project): MutableCollection<String> {
        return findAllFactions(project).stream()
            .map { it: RRFactionNameDecl -> unquote(it.text) }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllReligions(project: Project): Collection<RRReligionNameDecl> {
        val file = findRRFile("descr_beliefs.txt", project)
        return Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrBeliefs::class.java
                )
            }
            .map { it: RRDescrBeliefs? -> it!!.religion_List }
            .orElse(ArrayList())
            .stream().map { it: RRReligion_ -> it.religionNameDecl }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllReligionsAsStrings(project: Project): Collection<String> {
        return findAllReligions(project).stream()
            .map { it: RRReligionNameDecl -> unquote(it.text) }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllFactionGroups(project: Project): Collection<RRFactionGroup> {
        val file = findRRFile("descr_faction_groups.txt", project)
        return Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrFactionGroups::class.java
                )
            }
            .map { it: RRDescrFactionGroups? -> it!!.factionGroupList }
            .orElse(ArrayList())
    }

    fun findAllFactionGroupsAsStrings(project: Project): Collection<String> {
        return findAllFactionGroups(project).stream()
            .map { it: RRFactionGroup -> it.factionGroupNameDecl.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllCultures(project: Project): Collection<RRCultureNameDecl> {
        val file = findRRFile("descr_cultures.txt", project)
        return Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrCultures::class.java
                )
            }
            .map { it: RRDescrCultures? -> it!!.cultureNameDeclList }
            .orElse(ArrayList())
    }

    @JvmStatic
    fun findAllCulturesAsStrings(project: Project): Collection<String> {
        return findAllCultures(project).stream()
            .map { it: RRCultureNameDecl -> unquote(it.text) }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllAmbientObjects(project: Project): Collection<RRAmbientObjectNameDecl> {
        val file = findRRFile("descr_sm_ambient_objects.txt", project)
        return Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRDescrSmAmbientObjects::class.java
                )
            }
            .map { it: RRDescrSmAmbientObjects? -> it!!.ambientObjectDeclList }
            .orElse(ArrayList())
            .stream().map { it: RRAmbientObjectDecl -> it.ambientObjectNameDecl }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllAmbientObjectsAsStrings(project: Project): Collection<String> {
        return findAllAmbientObjects(project).stream()
            .map { it: RRAmbientObjectNameDecl -> unquote(it.text) }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllResources(hidden: Boolean, project: Project): List<RRResourceNameDecl> {
        val file = findRRFile("descr_sm_resources.txt", project)

        var filter = { _: RRResourceDecl -> true }
        if (hidden) {
            filter = { it: RRResourceDecl -> unquote(it.resourceType.text) == "hidden" }
        }

        return file?.findChildByClass(RRDescrSmResources::class.java)
            ?.resourceDeclList
            ?.filter(filter)
            ?.map { it.resourceNameDecl } ?: emptyList()
    }

    @JvmStatic
    fun findAllResourcesAsStrings(hidden: Boolean, project: Project): Collection<String> {
        return findAllResources(hidden, project).stream()
            .map { it: RRResourceNameDecl -> unquote(it.text) }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllBuildingTrees(project: Project): Collection<RRBuildingTree> {
        val file = findRRFile("export_descr_buildings.txt", project)
        return Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRExportDescrBuildings::class.java
                )
            }
            .map { it: RRExportDescrBuildings? -> it!!.buildingTreeList }
            .orElse(ArrayList())
    }

    @JvmStatic
    fun findAllBuildingTreesAsStrings(project: Project): Collection<String> {
        return findAllBuildingTrees(project).stream()
            .map { it: RRBuildingTree -> it.firstChild.nextSibling.nextSibling.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllBuildingLevels(project: Project): Collection<RRBuildingLevel> {
        val file = findRRFile("export_descr_buildings.txt", project)
        return Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRExportDescrBuildings::class.java
                )
            }
            .map { it: RRExportDescrBuildings? -> it!!.buildingTreeList }
            .orElse(ArrayList()).stream()
            .flatMap { it: RRBuildingTree -> it.buildingLevelList.stream() }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllBuildingLevelsAsStrings(project: Project): Collection<String> {
        return findAllBuildingLevels(project).stream()
            .map { it: RRBuildingLevel -> it.firstChild.text }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllAncillaries(project: Project): Collection<RRAncillaryDef> {
        val file = findRRFile("export_descr_ancillaries.txt", project)
        return Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRExportDescrAncillaries::class.java
                )
            }
            .map { it: RRExportDescrAncillaries? -> it!!.ancillaryDefList }
            .orElse(ArrayList())
    }

    @JvmStatic
    fun findAllAncillariesAsStrings(project: Project): Collection<String> {
        return findAllAncillaries(project).stream()
            .map { it: RRAncillaryDef ->
                it.ancillaryNameDecl!!
                    .text
            }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun findAllAncillaryDescriptions(project: Project): List<RRAncillaryDescrDef> {
        return findAllAncillaries(project).stream()
            .flatMap { it: RRAncillaryDef -> it.ancillaryDescrDefList.stream() }
            .collect(Collectors.toList())
    }

    // I do that because the Traits file is huge, and the NonExistingTrait Inspection
    // would otherwise re-read that huge file many times in order to check it.
    private val findAllTraitCache: MutableMap<Long, List<RRTraitDef>> = HashMap()
    private fun findAllTraits(project: Project): List<RRTraitDef> {
        val file = findRRFile("export_descr_character_traits.txt", project)
            ?: return emptyList()

        // This file is huge, and apparently sometimes it becomes null. Go figure.
        val modificationStamp = file.virtualFile.modificationStamp
        if (findAllTraitCache.containsKey(modificationStamp)) {
            return findAllTraitCache[modificationStamp]!!
        }

        val items = file
            .findChildByClass(RRExportDescrCharacterTraits::class.java)
            ?.traitDefList ?: emptyList()

        findAllTraitCache.clear()
        findAllTraitCache[modificationStamp] = items
        return items
    }

    @JvmStatic
    fun findAllTraitsAsStrings(project: Project): List<String> {
        return findAllTraits(project)
            .map {it.traitNameDecl!!.text }
    }

    @JvmStatic
    fun findAllTraitDescriptions(project: Project): List<RRTraitDescrDef> {
        val allDescriptions = ArrayList<RRTraitDescrDef>()
        for (trait in findAllTraits(project)) {
            for (level in trait.traitLevelList) {
                allDescriptions.addAll(level.traitDescrDefList)
            }
        }
        return allDescriptions.stream().filter { it: RRTraitDescrDef? -> it != null }.collect(Collectors.toList())
    }

    fun findAllRebelFactionDescriptions(project: Project): List<RRRebelFactionDescrRef?> {
        val file = findRRFile("rebel_faction_descr.txt", project)
        return Optional.ofNullable(file)
            .map { it: RRFile ->
                it.findChildByClass(
                    RRRebelFactionDescr::class.java
                )
            }
            .map { it: RRRebelFactionDescr? -> it!!.rebelFactionDescrMappingList }
            .orElse(ArrayList()).stream()
            .map { it: RRRebelFactionDescrMapping -> it.rebelFactionDescrRef }
            .collect(Collectors.toList())
    }

    @JvmStatic
    fun removePostfixesFromExportBuildingsId(id: String?, project: Project): String {
        var name = removeLastOccurrence(id!!, "_name")
        name = removeLastOccurrence(name, "_desc_short")
        name = removeLastOccurrence(name, "_desc")
        val factionsAndCulturesNames = findAllFactionsAsStrings(project)
        factionsAndCulturesNames.addAll(findAllCulturesAsStrings(project))
        for (factionOrCultureName in factionsAndCulturesNames) {
            name = removeLastOccurrence(name, "_$factionOrCultureName")
        }
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
}
