package org.incava.ijdk.lang;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import static org.incava.ijdk.lang.ICore.list;

public class TestPathname extends TestCase {
    public TestPathname(String name) {
        super(name);
    }

    // shortcut used within this test

    public Pathname pnName(String fileName) {
        return new Pathname(fileName);
    }

    public Pathname pnFile(File file) {
        return new Pathname(file);
    }

    public void testPnName() {
        String fileName = "f.x";
        Pathname pn = pnName(fileName);
        assertEquals(new Pathname(fileName), pn);
    }

    public void testPnFile() {
        File file = new File("f.x");
        Pathname pn = pnFile(file);
        assertEquals(new Pathname(file), pn);
    }

    // equals

    public boolean assertEquals(boolean expected, Pathname x, Pathname y) {
        boolean result = x.equals(y);
        assertEquals("x: " + x + "; y: " + y, expected, result);
        return result;
    }

    public void testEqualsFileNamesTrue() {
        assertEquals(true, pnName("f.x"), pnName("f.x"));
    }

    public void testEqualsFilesTrue() {
        assertEquals(true, pnFile(new File("f.x")), pnFile(new File("f.x")));
    }

    public void testEqualsFileNamesFalse() {
        assertEquals(false, pnName("f.x"), pnName("g.x"));
    }

    public void testEqualsFilesFalse() {
        assertEquals(false, pnFile(new File("f.x")), pnFile(new File("g.x")));
    }

    // ctor

    public Pathname assertCtor(Pathname expected, Pathname pn) {
        assertEquals(expected, pn);
        return pn;
    }

    public Pathname assertCtor(Pathname expected, String fileName) {
        Pathname pn = new Pathname(fileName);
        assertEquals("fileName: " + fileName, expected, pn);
        return pn;
    }

    public Pathname assertCtor(Pathname expected, File file) {
        Pathname pn = new Pathname(file);
        assertEquals("file: " + file, expected, pn);
        return pn;
    }

    public void testCtorFileName() {
        Pathname expected = pnName("f.x");
        assertCtor(expected, "f.x");
    }

    public void testCtorFile() {
        Pathname expected = pnFile(new File("f.x"));
        assertCtor(expected, new File("f.x"));
    }

    public void testCtorFileNamePath() {
        Pathname expected = pnName("d/f.x");
        assertCtor(expected, "d/f.x");
    }

    public void testCtorFilePath() {
        Pathname expected = pnFile(new File("d/f.x"));
        assertCtor(expected, new File("d/f.x"));
    }

    public void testCtorUserDir() {
        Pathname expected = pnName(System.getProperty("user.dir"));
        assertCtor(expected, new Pathname(System.getProperty("user.dir")));
    }

    // baseName

    public Pathname assertBaseName(String expected, Pathname pn) {
        assertEquals(expected, pn.baseName());
        return pn;
    }

    public void testBaseNameFileName() {
        assertBaseName("f.x", pnName("f.x"));
    }

    public void testBaseNameFile() {
        assertBaseName("f.x", pnFile(new File("f.x")));
    }

    public void testBaseNameFileNamePath() {
        assertBaseName("f.x", pnName("d/f.x"));
    }

    public void testBaseNameFilePath() {
        assertBaseName("f.x", pnFile(new File("d/f.x")));
    }

    public void testBaseNameDot() {
        assertBaseName(".", pnName("."));
    }

    // rootName

    public Pathname assertRootName(String expected, Pathname pn) {
        assertEquals(expected, pn.rootName());
        return pn;
    }

    public void testRootNameFileName() {
        assertRootName("f", pnName("f.x"));
    }

    public void testRootNameFileNamePath() {
        assertRootName("f", pnName("d/f.x"));
    }

    public void testRootNameNoExtension() {
        assertRootName("f", pnName("d/f"));
    }

    public void testRootNameMultipleExtensions() {
        assertRootName("f.x", pnName("d/f.x.y"));
    }

    public void testRootNameEndingDot() {
        assertRootName("f", pnName("d/f."));
    }

    public void testRootNameDot() {
        assertRootName(".", pnName("."));
    }

    // relativePath

    public Pathname assertRelativePath(String expected, Pathname pn) {
        assertEquals(expected, pn.relativePath());
        return pn;
    }

    public void testRelativePathFileName() {
        assertRelativePath("f.x", pnName("f.x"));
    }

    public void testRelativePathFile() {
        assertRelativePath("f.x", pnFile(new File("f.x")));
    }

    public void testRelativePathFileNamePath() {
        assertRelativePath("d/f.x", pnName("d/f.x"));
    }

    public void testRelativePathFilePath() {
        assertRelativePath("d/f.x", pnFile(new File("d/f.x")));
    }

    // file

    public Pathname assertFile(File expected, Pathname pn) {
        assertEquals(expected, pn.file());
        return pn;
    }

    public void testFileFileName() {
        assertFile(new File("f.x"), pnName("f.x"));
    }

    public void testFileFile() {
        assertFile(new File("f.x"), pnFile(new File("f.x")));
    }

    // extension

    public Pathname assertExtension(String expected, Pathname pn) {
        assertEquals(expected, pn.extension());
        return pn;
    }

    public void testExtensionSingle() {
        assertExtension("x", pnName("f.x"));
    }

    public void testExtensionMultiple() {
        assertExtension("y", pnName("f.x.y"));
    }

    public void testExtensionEmpty() {
        assertExtension("", pnName("f."));
    }

    public void testExtensionNone() {
        assertExtension(null, pnFile(new File("f")));
    }

    // parent

    public Pathname assertParent(Pathname expected, Pathname pn) {
        Pathname result = pn.parent();
        assertEquals("pn: " + pn, expected, result);
        return result;
    }

    public void testParentOneLevelToCurrentDirectory() {
        assertParent(pnName("."), pnName("a"));
    }

    public void testParentOneLevelToSuper() {
        assertParent(pnName(".."), pnName("."));
    }

    public void testParentOneLevelFromDots() {
        assertParent(pnName("../.."), pnName(".."));
    }

    public void testParentTwoLevelsToParent() {
        assertParent(pnName("d"), pnName("d/f"));
    }

    public void testParentTwoLevelsIncludesDot() {
        assertParent(pnName("d"), pnName("d/./f"));
    }

    public void testParentTwoLevelsIncludesDots() {
        assertParent(pnName("d"), pnName("d/././f"));
    }

    public void testParentTwoLevelsIncludesDoubleDots() {
        assertParent(pnName("d/.."), pnName("d/../f"));
    }

    public void testParentEmpty() {
        assertParent(pnName(".."), pnName(""));
    }

    // expand path

    public String assertExpandPath(String expected, Pathname pn) {
        String result = pn.expandPath();
        assertEquals("pn: " + pn, expected, result);
        return result;
    }

    public void testExpandPathOneLevelToSuper() {
        String userDir = System.getProperty("user.dir");
        String separator = "/";
        assertExpandPath(userDir + separator + "a", pnName("a"));
    }

    public void testExpandPathTwoLevels() {
        String userDir = System.getProperty("user.dir");
        String separator = "/";
        assertExpandPath(userDir + separator + "a/b", pnName("a/b"));
    }

    public void testExpandPathContainsSingleDot() {
        String userDir = System.getProperty("user.dir");
        String separator = "/";
        assertExpandPath(userDir + separator + "a/./b", pnName("a/./b"));
    }    

    public void testExpandPathContainsDoubleDots() {
        String userDir = System.getProperty("user.dir");
        String separator = "/";
        assertExpandPath(userDir + separator + "a/../b", pnName("a/../b"));
    }    

    public void testExpandPathEmpty() {
        String userDir = System.getProperty("user.dir");
        String separator = "/";
        assertExpandPath(userDir, pnName(""));
    }

    public void testExpandPathFullPathOneElement() {
        assertExpandPath("/a", pnName("/a"));
    }

    public void testExpandPathFullPathRelative() {
        assertExpandPath("/a/../b/c", pnName("/a/../b/c"));
    }

    // glob

    public List<Pathname> assertGlob(List<Pathname> expected, String glob) {
        String msg = "glob: " + glob;
        List<Pathname> result = Pathname.glob(glob);
        System.out.println("glob: " + glob);
        System.out.println("result: " + result);
        assertEquals(msg, expected, result);
        return result;
    }

    // public void testSingleDirectory() {
    //     assertGlob(list(new Pathname("main")), "main");
    // }

    // children

    public List<Pathname> assertChildren(List<Pathname> expected, Pathname dir) {
        List<Pathname> result = dir.children();
        assertEquals("dir: " + dir, expected, result);
        return result;
    }

    // public void testChildrenUserDir() {
    //     Pathname dir = new Pathname();
    //     List<Pathname> expected = new ArrayList<Pathname>();
    //     assertChildren(expected, dir);
    // }
}
