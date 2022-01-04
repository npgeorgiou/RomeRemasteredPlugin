package rr.language;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import rr.language.psi.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RRUtil {

    public static Collection<RRFile> findAllRRFiles(Project project) {
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(RRFileType.INSTANCE, GlobalSearchScope.allScope(project));
        return virtualFiles.stream()
            .map(it -> (RRFile) PsiManager.getInstance(project).findFile(it))
            .collect(Collectors.toList());
    }

    public static RRFile findRRFile(String path, Project project) {
        Collection<RRFile> files = findAllRRFiles(project);
        files.removeIf(it -> !it.getVirtualFile().getPath().endsWith("/".concat(path)));

        if (files.isEmpty()) {
            return null;
        }

        return files.iterator().next();
    }

    public static Collection<RRUnitItem_> findAllUnits(Project project) {
        RRFile file = RRUtil.findRRFile("export_descr_unit.txt", project);

        if (file == null) {
            return new ArrayList<>();
        }

        return file.findChildByClass(RRExportDescrUnit.class).getUnitItem_List();
    }

    public static Collection<String> findAllUnitsAsStrings(Project project) {
        return findAllUnits(project).stream()
            .map(it -> it.getUnitNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRAiPersonality> findAllAiPersonalities(Project project) {
        RRFile file = RRUtil.findRRFile("feral_descr_ai_personality.txt", project);

        if (file == null) {
            return new ArrayList<>();
        }

        return file.findChildByClass(RRFeralDescrAiPersonality.class).getAiPersonalityList();
    }

    public static Collection<String> findAllAiPersonalitiesAsStrings(Project project) {
        return findAllAiPersonalities(project).stream()
            .map(it -> it.getAiPersonalityNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRRegionDef> findAllRegions(Project project) {
        RRFile file = RRUtil.findRRFile("descr_regions.txt", project);

        if (file == null) {
            return new ArrayList<>();
        }

        return file.findChildByClass(RRDescrRegions.class).getRegionDefList();
    }

    public static Collection<String> findAllRegionsAsStrings(Project project) {
        return findAllRegions(project).stream()
            .map(it -> it.getFirstChild().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RREthnicityMakeup_> findAllEthnicityMakeups(Project project) {
        RRFile file = RRUtil.findRRFile("descr_unit_variation.txt", project);

        if (file == null) {
            return new ArrayList<>();
        }

        return file.findChildByClass(RRDescrUnitVariation.class).getEthnicityMakeup_List();
    }

    public static Collection<String> findAllEthnicityMakeupsAsStrings(Project project) {
        return findAllEthnicityMakeups(project).stream()
            .map(it -> it.getEthnicityMakeupNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRFactionNameDecl> findAllFactions(Project project) {
        RRFile file = RRUtil.findRRFile("descr_sm_factions.txt", project);

        if (file == null) {
            return new ArrayList<>();
        }

        return file.findChildByClass(RRDescrSmFactions.class).getFactionDeclList().stream()
            .map(it -> it.getFactionNameDecl())
            .collect(Collectors.toList());
    }

    public static Collection<String> findAllFactionsAsStrings(Project project) {
        return findAllFactions(project).stream()
            .map(it -> Util.unquote(it.getText()))
            .collect(Collectors.toList());
    }

    public static Collection<RRFactionGroup> findAllFactionGroups(Project project) {
        RRFile file = RRUtil.findRRFile("descr_faction_groups.txt", project);

        if (file == null) {
            return new ArrayList<>();
        }

        return file.findChildByClass(RRDescrFactionGroups.class)
            .getFactionGroupList().stream()
            .collect(Collectors.toList());
    }

    public static Collection<String> findAllFactionGroupsAsStrings(Project project) {
        return findAllFactionGroups(project).stream()
            .map(it -> it.getFactionGroupNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRCultureNameDecl> findAllCultures(Project project) {
        RRFile file = RRUtil.findRRFile("descr_cultures.txt", project);

        if (file == null) {
            return new ArrayList<>();
        }

        return file.findChildByClass(RRDescrCultures.class).getCultureNameDeclList();
    }

    public static Collection<String> findAllCulturesAsStrings(Project project) {
        return findAllCultures(project).stream()
            .map(it -> Util.unquote(it.getText()))
            .collect(Collectors.toList());
    }

    public static Collection<RRBuildingTree> findAllBuildingTrees(Project project) {
        RRFile file = RRUtil.findRRFile("export_descr_buildings.txt", project);

        if (file == null) {
            return new ArrayList<>();
        }

        return file.findChildByClass(RRExportDescrBuildings.class).getBuildingTreeList();
    }

    public static Collection<String> findAllBuildingTreesAsStrings(Project project) {
        return findAllBuildingTrees(project).stream()
            .map(it -> it.getFirstChild().getNextSibling().getNextSibling().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRBuildingLevel> findAllBuildingLevels(Project project) {
        RRFile file = RRUtil.findRRFile("export_descr_buildings.txt", project);

        if (file == null) {
            return new ArrayList<>();
        }

        return file.findChildByClass(RRExportDescrBuildings.class)
            .getBuildingTreeList().stream()
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

        if (file == null) {
            return new ArrayList<>();
        }

        return file.findChildByClass(RRExportDescrAncillaries.class).getAncillaryDefList();
    }

    public static Collection<String> findAllAncillariesAsStrings(Project project) {
        return findAllAncillaries(project).stream()
            .map(it -> it.getAncillaryNameDecl().getText())
            .collect(Collectors.toList());
    }

    public static Collection<RRTraitDef> findAllTraits(Project project) {
        RRFile file = RRUtil.findRRFile("export_descr_character_traits.txt", project);

        if (file == null) {
            return new ArrayList<>();
        }

        return file.findChildByClass(RRExportDescrTraits.class).getTraitDefList();
    }

    public static Collection<String> findAllTraitsAsStrings(Project project) {
        return findAllTraits(project).stream()
            .map(it -> it.getTraitNameDecl().getText())
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
        Predicate<PsiElement> stopCondition,
        Predicate<PsiElement> foundCondition
    ) {
        PsiElement prev = element;

        while ((prev = prev.getPrevSibling()) != null) {
            if (stopCondition != null && stopCondition.test(prev)) break;

            if (foundCondition.test(prev)) return (T) prev;
        }

        return null;
    }

    public static <T extends PsiElement> T nextSibling(
        T element,
        Predicate<PsiElement> stopCondition,
        Predicate<PsiElement> foundCondition
    ) {
        PsiElement next = element;

        while ((next = next.getNextSibling()) != null) {
            if (stopCondition != null && stopCondition.test(next)) break;

            if (foundCondition.test(next)) return (T) next;
        }

        return null;
    }
}
