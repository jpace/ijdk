package ijdk.lang;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

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
        String fileName = "abc.txt";
        Pathname pn = pnName(fileName);
        assertEquals(new Pathname(fileName), pn);
    }

    public void testPnFile() {
        File file = new File("abc.txt");
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
        assertEquals(true, pnName("abc.txt"), pnName("abc.txt"));
    }

    public void testEqualsFilesTrue() {
        assertEquals(true, pnFile(new File("abc.txt")), pnFile(new File("abc.txt")));
    }

    public void testEqualsFileNamesFalse() {
        assertEquals(false, pnName("abc.txt"), pnName("def.txt"));
    }

    public void testEqualsFilesFalse() {
        assertEquals(false, pnFile(new File("abc.txt")), pnFile(new File("def.txt")));
    }

    // ctor

    public Pathname assertCtor(Pathname expected, String fileName) {
        Pathname pn = new Pathname(fileName);
        assertEquals(expected, pn);
        return pn;
    }

    public Pathname assertCtor(Pathname expected, File file) {
        Pathname pn = new Pathname(file);
        assertEquals(expected, pn);
        return pn;
    }

    public void testCtorFileName() {
        Pathname expected = pnName("foo.txt");
        assertCtor(expected, "foo.txt");
    }

    public void testCtorFile() {
        Pathname expected = pnFile(new File("foo.txt"));
        assertCtor(expected, new File("foo.txt"));
    }

    public void testCtorFileNamePath() {
        Pathname expected = pnName("abc/foo.txt");
        assertCtor(expected, "abc/foo.txt");
    }

    public void testCtorFilePath() {
        Pathname expected = pnFile(new File("abc/foo.txt"));
        assertCtor(expected, new File("abc/foo.txt"));
    }

    // baseName

    public Pathname assertBaseName(String expected, Pathname pn) {
        assertEquals(expected, pn.baseName());
        return pn;
    }

    public void testNameFileName() {
        assertBaseName("foo.txt", pnName("foo.txt"));
    }

    public void testNameFile() {
        assertBaseName("foo.txt", pnFile(new File("foo.txt")));
    }

    public void testNameFileNamePath() {
        assertBaseName("foo.txt", pnName("abc/foo.txt"));
    }

    public void testNameFilePath() {
        assertBaseName("foo.txt", pnFile(new File("abc/foo.txt")));
    }

    // relativePath

    public Pathname assertRelativePath(String expected, Pathname pn) {
        assertEquals(expected, pn.relativePath());
        return pn;
    }

    public void testRelativePathFileName() {
        assertRelativePath("foo.txt", pnName("foo.txt"));
    }

    public void testRelativePathFile() {
        assertRelativePath("foo.txt", pnFile(new File("foo.txt")));
    }

    public void testRelativePathFileNamePath() {
        assertRelativePath("abc/foo.txt", pnName("abc/foo.txt"));
    }

    public void testRelativePathFilePath() {
        assertRelativePath("abc/foo.txt", pnFile(new File("abc/foo.txt")));
    }

    // file

    public Pathname assertFile(File expected, Pathname pn) {
        assertEquals(expected, pn.file());
        return pn;
    }

    public void testFileFileName() {
        assertFile(new File("foo.txt"), pnName("foo.txt"));
    }

    public void testFileFile() {
        assertFile(new File("foo.txt"), pnFile(new File("foo.txt")));
    }

    // extension

    public Pathname assertExtension(String expected, Pathname pn) {
        assertEquals(expected, pn.extension());
        return pn;
    }

    public void testExtensionSingle() {
        assertExtension("txt", pnName("abc.txt"));
    }

    public void testExtensionMultiple() {
        assertExtension("gz", pnName("abc.tar.gz"));
    }

    public void testExtensionEmpty() {
        assertExtension("", pnName("abc."));
    }

    public void testExtensionNone() {
        assertExtension(null, pnFile(new File("abc")));
    }
    
}
