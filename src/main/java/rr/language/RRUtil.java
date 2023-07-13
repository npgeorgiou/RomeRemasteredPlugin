package rr.language;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import rr.language.psi.*;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RRUtil {

    public static Collection<PsiFile> findAllTgaFiles(Project project) {
        return FilenameIndex.getAllFilesByExt(project, "tga").stream()
            .map(it -> PsiManager.getInstance(project).findFile(it))
            .collect(Collectors.toList());
    }

    public static PsiFile findTgaFile(String path, Project project) {
        Collection<PsiFile> files = findAllTgaFiles(project);
        files.removeIf(it -> !it.getVirtualFile().getPath().endsWith("/".concat(path)));

        if (files.isEmpty()) {
            return null;
        }

        return files.iterator().next();
    }

    public static Collection<RRFile> findAllRRFiles(Project project) {
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(RRFileType.INSTANCE, GlobalSearchScope.allScope(project));

        return virtualFiles.stream()
            .map(it -> {
                // Very big files cant be cast to RRfiles.
                try {
                    return (RRFile) PsiManager.getInstance(project).findFile(it);
                } catch (Exception e) {
                    return null;
                }
            })
            .filter(it -> it != null)
            .collect(Collectors.toList());
    }

    public static RRFile findRRFile(String path, Project project) {
        var files = findAllRRFiles(project);
        files.removeIf(it -> !it.getVirtualFile().getPath().endsWith("/".concat(path)));

        if (files.isEmpty()) {
            return null;
        }

        return files.iterator().next();
    }

    public static RRFile findRRFileThatEndsWith(String path, Project project) {
        var files = findAllRRFiles(project);
        files.removeIf(it -> !it.getVirtualFile().getPath().endsWith(path));

        if (files.isEmpty()) {
            return null;
        }

        return files.iterator().next();
    }

    public static Collection<RRTextMappingItem> findTextMappingsInFile(String fileName, Project project) {
        RRFile file = RRUtil.findRRFile(fileName, project);
        return findTextMappingsInFile(file);
    }

    public static Collection<RRTextMappingItem> findTextMappingsInFile(RRFile file) {
        if (file == null) {
            return new ArrayList<>();
        }

        return ((RRTextMappingFormat) file.getFirstChild()).getTextMappingItemList();
    }

    public static Collection<RRUnitItem_> findAllUnits(Project project) {
        RRFile file = RRUtil.findRRFile("export_descr_unit.txt", project);

        var opt = Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRExportDescrUnit.class))
            .map(it -> it.getUnitItem_List());

        return opt.orElse(new ArrayList<>());
    }

    public static Collection<String> findAllUnitsAsStrings(Project project) {
        return findAllUnits(project).stream()
            .map(it -> it.getUnitNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRAiPersonality> findAllAiPersonalities(Project project) {
        RRFile file = RRUtil.findRRFile("feral_descr_ai_personality.txt", project);

        var opt = Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRFeralDescrAiPersonality.class))
            .map(it -> it.getAiPersonalityList());

        return opt.orElse(new ArrayList<>());
    }

    public static Collection<String> findAllAiPersonalitiesAsStrings(Project project) {
        return findAllAiPersonalities(project).stream()
            .map(it -> it.getAiPersonalityNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRRegionDef> findAllRegions(Project project) {
        RRFile file = RRUtil.findRRFile("descr_regions.txt", project);

        var opt = Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrRegions.class))
            .map(it -> it.getRegionDefList());

        return opt.orElse(new ArrayList<>());
    }

    public static Collection<String> findAllRegionsAsStrings(Project project) {
        return findAllRegions(project).stream()
            .map(it -> it.getFirstChild().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRSettlementNameDecl> findAllSettlements(Project project) {
        RRFile file = RRUtil.findRRFile("descr_regions.txt", project);

        return Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrRegions.class))
            .map(it -> it.getRegionDefList())
            .orElse(new ArrayList<>())
            .stream().map(it -> it.getSettlementNameDecl())
            .collect(Collectors.toList());
    }

    public static Collection<String> findAllSettlementsAsStrings(Project project) {
        return findAllSettlements(project).stream()
            .map(it -> it.getFirstChild().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRRebelFaction> findAllRebelFactions(Project project) {
        RRFile file = RRUtil.findRRFile("descr_rebel_factions.txt", project);

        var opt = Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrRebelFactions.class))
            .map(it -> it.getRebelFactionList());

        return opt.orElse(new ArrayList<>());
    }

    public static Collection<String> findAllRebelFactionsAsStrings(Project project) {
        return findAllRebelFactions(project).stream()
            .map(it -> it.getRebelFactionNameDef().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRWonderNameDecl> findAllWonders(Project project) {
        RRFile file = RRUtil.findRRFile("descr_sm_landmarks.txt", project);

        var opt = Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrSmLandmarks.class))
            .map(it -> it.getWonderNameDeclList());

        return opt.orElse(new ArrayList<>());
    }

    public static Collection<String> findAllWondersAsStrings(Project project) {
        return findAllWonders(project).stream()
            .map(it -> it.getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRProjectile_> findAllProjectiles(Project project) {
        RRFile file = RRUtil.findRRFile("descr_projectile_new.txt", project);

        var opt = Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrProjectile.class))
            .map(it -> it.getProjectile_List());

        return opt.orElse(new ArrayList<>());
    }

    public static Collection<String> findAllProjectilesAsStrings(Project project) {
        return findAllProjectiles(project).stream()
            .map(it -> it.getProjectileNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRAnimal_> findAllAnimals(Project project) {
        RRFile file = RRUtil.findRRFile("descr_animals.txt", project);

        var opt = Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrAnimals.class))
            .map(it -> it.getAnimal_List());

        return opt.orElse(new ArrayList<>());
    }

    public static Collection<String> findAllAnimalsAsStrings(Project project) {
        return findAllAnimals(project).stream()
            .map(it -> it.getAnimalNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRDisaster_> findAllDisasters(Project project) {
        RRFile file = RRUtil.findRRFile("descr_disasters.txt", project);

        var opt = Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrDisasters.class))
            .map(it -> it.getDisaster_List());

        return opt.orElse(new ArrayList<>());
    }

    public static Collection<String> findAllDisastersAsStrings(Project project) {
        return findAllDisasters(project).stream()
            .map(it -> it.getDisasterNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRCounterNameDecl> findAllCounters(PsiElement element) {
        RRFile file = (RRFile) element.getContainingFile();

        if (file == null) {
            return new ArrayList<>();
        }

        return PsiTreeUtil.findChildrenOfAnyType(file, RRCounterNameDecl.class);
    }

    public static Collection<String> findAllCountersAsStrings(PsiElement element) {
        return findAllCounters(element).stream()
            .map(it -> it.getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRMount_> findAllMounts(Project project) {
        RRFile file = RRUtil.findRRFile("descr_mount.txt", project);

        var opt = Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrMount.class))
            .map(it -> it.getMount_List());

        return opt.orElse(new ArrayList<>());
    }

    public static Collection<String> findAllMountsAsStrings(Project project) {
        return findAllMounts(project).stream()
            .map(it -> it.getMountNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRShip_> findAllShips(Project project) {
        RRFile file = RRUtil.findRRFile("descr_ship.txt", project);

        var opt = Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrShip.class))
            .map(it -> it.getShip_List());

        return opt.orElse(new ArrayList<>());
    }

    public static Collection<String> findAllShipsAsStrings(Project project) {
        return findAllShips(project).stream()
            .map(it -> it.getShipNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRModel_> findAllModels(Project project) {
        RRFile battle_file = RRUtil.findRRFile("descr_model_battle.txt", project);
        var battle_models = Optional.ofNullable(battle_file)
            .map(it -> it.findChildByClass(RRDescrModelBattle.class))
            .map(it -> it.getModel_List());

        RRFile strat_file = RRUtil.findRRFile("descr_model_strat.txt", project);
        var strat_models = Optional.ofNullable(strat_file)
            .map(it -> it.findChildByClass(RRDescrModelStrat.class))
            .map(it -> it.getModel_List());

        return Stream.concat(
            battle_models.orElse(new ArrayList<>()).stream(),
            strat_models.orElse(new ArrayList<>()).stream()
        ).collect(Collectors.toList());
    }

    public static Collection<String> findAllModelsAsStrings(Project project) {
        return findAllModels(project).stream()
            .map(it -> it.getModelNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RREthnicityMakeup_> findAllEthnicityMakeups(Project project) {
        RRFile file = RRUtil.findRRFile("descr_unit_variation.txt", project);

        var opt = Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrUnitVariation.class))
            .map(it -> it.getEthnicityMakeup_List());

        return opt.orElse(new ArrayList<>());
    }

    public static Collection<String> findAllEthnicityMakeupsAsStrings(Project project) {
        return findAllEthnicityMakeups(project).stream()
            .map(it -> it.getEthnicityMakeupNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRFactionNameDecl> findAllFactions(Project project) {
        RRFile file = RRUtil.findRRFile("descr_sm_factions.txt", project);

        return Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrSmFactions.class))
            .map(it -> it.getFactionDeclList())
            .orElse(new ArrayList<>())
            .stream().map(it -> it.getFactionNameDecl())
            .collect(Collectors.toList());
    }

    public static Collection<String> findAllFactionsAsStrings(Project project) {
        return findAllFactions(project).stream()
            .map(it -> Util.unquote(it.getText()))
            .collect(Collectors.toList());
    }

    public static Collection<RRReligionNameDecl> findAllReligions(Project project) {
        RRFile file = RRUtil.findRRFile("descr_beliefs.txt", project);

        return Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrBeliefs.class))
            .map(it -> it.getReligion_List())
            .orElse(new ArrayList<>())
            .stream().map(it -> it.getReligionNameDecl())
            .collect(Collectors.toList());
    }

    public static Collection<String> findAllReligionsAsStrings(Project project) {
        return findAllReligions(project).stream()
            .map(it -> Util.unquote(it.getText()))
            .collect(Collectors.toList());
    }

    public static Collection<RRFactionGroup> findAllFactionGroups(Project project) {
        RRFile file = RRUtil.findRRFile("descr_faction_groups.txt", project);

        return Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrFactionGroups.class))
            .map(it -> it.getFactionGroupList())
            .orElse(new ArrayList<>());
    }

    public static Collection<String> findAllFactionGroupsAsStrings(Project project) {
        return findAllFactionGroups(project).stream()
            .map(it -> it.getFactionGroupNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRCultureNameDecl> findAllCultures(Project project) {
        RRFile file = RRUtil.findRRFile("descr_cultures.txt", project);

        return Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrCultures.class))
            .map(it -> it.getCultureNameDeclList())
            .orElse(new ArrayList<>());
    }

    public static Collection<String> findAllCulturesAsStrings(Project project) {
        return findAllCultures(project).stream()
            .map(it -> Util.unquote(it.getText()))
            .collect(Collectors.toList());
    }

    public static Collection<RRAmbientObjectNameDecl> findAllAmbientObjects(Project project) {
        RRFile file = RRUtil.findRRFile("descr_sm_ambient_objects.txt", project);

        return Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrSmAmbientObjects.class))
            .map(it -> it.getAmbientObjectDeclList())
            .orElse(new ArrayList<>())
            .stream().map(it -> it.getAmbientObjectNameDecl())
            .collect(Collectors.toList());
    }

    public static Collection<String> findAllAmbientObjectsAsStrings(Project project) {
        return findAllAmbientObjects(project).stream()
            .map(it -> Util.unquote(it.getText()))
            .collect(Collectors.toList());
    }

    public static Collection<RRResourceNameDecl> findAllResources(boolean hidden, Project project) {
        RRFile file = RRUtil.findRRFile("descr_sm_resources.txt", project);

        Predicate<RRResourceDecl> filter = it -> true;
        if (hidden) {
            filter = it -> Util.unquote(it.getResourceType().getText()).equals("hidden");
        }

        return Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRDescrSmResources.class))
            .map(it -> it.getResourceDeclList())
            .orElse(new ArrayList<>()).stream()
            .filter(filter)
            .map(it -> it.getResourceNameDecl())
            .collect(Collectors.toList());
    }

    public static Collection<String> findAllResourcesAsStrings(boolean hidden, Project project) {
        return findAllResources(hidden, project).stream()
            .map(it -> Util.unquote(it.getText()))
            .collect(Collectors.toList());
    }

    public static Collection<RRBuildingTree> findAllBuildingTrees(Project project) {
        RRFile file = RRUtil.findRRFile("export_descr_buildings.txt", project);

        return Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRExportDescrBuildings.class))
            .map(it -> it.getBuildingTreeList())
            .orElse(new ArrayList<>());
    }

    public static Collection<String> findAllBuildingTreesAsStrings(Project project) {
        return findAllBuildingTrees(project).stream()
            .map(it -> it.getFirstChild().getNextSibling().getNextSibling().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRBuildingLevel> findAllBuildingLevels(Project project) {
        RRFile file = RRUtil.findRRFile("export_descr_buildings.txt", project);

        return Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRExportDescrBuildings.class))
            .map(it -> it.getBuildingTreeList())
            .orElse(new ArrayList<>()).stream()
            .flatMap(it -> it.getBuildingLevelList().stream())
            .collect(Collectors.toList());
    }

    public static Collection<String> findAllBuildingLevelsAsStrings(Project project) {
        return findAllBuildingLevels(project).stream()
            .map(it -> it.getFirstChild().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRAncillaryDef> findAllAncillaries(Project project) {
        RRFile file = RRUtil.findRRFile("export_descr_ancillaries.txt", project);

        return Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRExportDescrAncillaries.class))
            .map(it -> it.getAncillaryDefList())
            .orElse(new ArrayList<>());
    }

    public static Collection<String> findAllAncillariesAsStrings(Project project) {
        return findAllAncillaries(project).stream()
            .map(it -> it.getAncillaryNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static List<RRAncillaryDescrDef> findAllAncillaryDescriptions(Project project) {
        return findAllAncillaries(project).stream()
            .flatMap(it -> it.getAncillaryDescrDefList().stream())
            .collect(Collectors.toList());
    }

    // I do that because the Traits file is huge, and the NonExistingTrait Inspection
    // would otherwise re-read that huge file many times in order to check it.
    private static Map<Long, List<RRTraitDef>> findAllTraitCache = new HashMap<>();
    private static Collection<RRTraitDef> findAllTraits(Project project) {
        RRFile file = RRUtil.findRRFile("export_descr_character_traits.txt", project);
        var modificationStamp = file.getVirtualFile().getModificationStamp();

        if (findAllTraitCache.containsKey(modificationStamp)) {
            return findAllTraitCache.get(modificationStamp);
        }

        var items = Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRExportDescrCharacterTraits.class))
            .map(it -> it.getTraitDefList())
            .orElse(new ArrayList<>());

        findAllTraitCache.clear();
        findAllTraitCache.put(modificationStamp, items);
        return items;
    }

    public static Collection<String> findAllTraitsAsStrings(Project project) {
        return findAllTraits(project).stream()
            .map(it -> it.getTraitNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static List<RRTraitDescrDef> findAllTraitDescriptions(Project project) {
        var allDescriptions = new ArrayList<RRTraitDescrDef>();

        for (var trait : findAllTraits(project)) {
            for (var level : trait.getTraitLevelList()) {
                allDescriptions.addAll(level.getTraitDescrDefList());
            }
        }

        return allDescriptions.stream().filter(it -> it != null).collect(Collectors.toList());
    }

    public static List<RRRebelFactionDescrRef> findAllRebelFactionDescriptions(Project project) {
        RRFile file = RRUtil.findRRFile("rebel_faction_descr.txt", project);

        return Optional.ofNullable(file)
            .map(it -> it.findChildByClass(RRRebelFactionDescr.class))
            .map(it -> it.getRebelFactionDescrMappingList())
            .orElse(new ArrayList<>()).stream()
            .map(it -> it.getRebelFactionDescrRef())
            .collect(Collectors.toList());
    }

    public static String removePostfixesFromExportBuildingsId(String id, Project project) {
        String name = Util.removeLastOccurrence(id, "_name");
        name = Util.removeLastOccurrence(name, "_desc_short");
        name = Util.removeLastOccurrence(name, "_desc");

        Collection<String> factionsAndCulturesNames = RRUtil.findAllFactionsAsStrings(project);
        factionsAndCulturesNames.addAll(RRUtil.findAllCulturesAsStrings(project));

        for (String factionOrCultureName : factionsAndCulturesNames) {
            name = Util.removeLastOccurrence(name, "_" + factionOrCultureName);
        }

        return name;
    }

    public static boolean isScript(PsiFile file) {
        return file.getText().contains("script\n") && file.getText().contains("end_script");
    }

    public static Collection<PsiElement> collectLeaves(
        PsiElement element,
        Function<PsiElement, Collection<PsiElement>> mapper,
        Collection<PsiElement> acc
    ) {
        acc.addAll(mapper.apply(element));

        for (var child : element.getChildren()) {
            collectLeaves(child, mapper, acc);
        }

        return acc;
    }

    public static Collection<PsiElement> getSiblingsBetween(PsiElement start, PsiElement end) {
        Collection<PsiElement> list = new ArrayList<>();

        list.add(start);
        PsiElement next = start.getNextSibling();

        while (true) {
            if (next == null) {
                return null;
            }

            list.add(next);

            if (next.equals(end)) {
                break;
            }

            next = next.getNextSibling();
        }

        return list;
    }

    public static <T extends PsiElement> T previousSibling(
        T element,
        Predicate<PsiElement> foundCondition
    ) {
        PsiElement prev = element;

        while ((prev = prev.getPrevSibling()) != null) {
            if (foundCondition.test(prev)) return (T) prev;
        }

        return null;
    }

    public static <T extends PsiElement> T nextSibling(
        T element,
        Predicate<PsiElement> foundCondition
    ) {
        PsiElement next = element;

        while ((next = next.getNextSibling()) != null) {
            if (foundCondition.test(next)) return (T) next;
        }

        return null;
    }
}
