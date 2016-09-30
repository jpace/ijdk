package ijdk.lang;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestPathname extends TestCase {
    public TestPathname(String name) {
        super(name);
    }

    // equals

    public boolean assertEquals(boolean expected, Pathname x, Pathname y) {
        boolean result = x.equals(y);
        assertEquals("x: " + x + "; y: " + y, expected, result);
        return result;
    }

    public void testEqualsFileNamesTrue() {
        assertEquals(true, new Pathname("abc.txt"), new Pathname("abc.txt"));
    }

    public void testEqualsFilesTrue() {
        assertEquals(true, new Pathname(new File("abc.txt")), new Pathname(new File("abc.txt")));
    }

    public void testEqualsFileNamesFalse() {
        assertEquals(false, new Pathname("abc.txt"), new Pathname("def.txt"));
    }

    public void testEqualsFilesFalse() {
        assertEquals(false, new Pathname(new File("abc.txt")), new Pathname(new File("def.txt")));
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
        Pathname expected = new Pathname("foo.txt");
        assertCtor(expected, "foo.txt");
    }

    public void testCtorFile() {
        Pathname expected = new Pathname(new File("foo.txt"));
        assertCtor(expected, new File("foo.txt"));
    }

    // name

    public Pathname assertName(String expected, Pathname pn) {
        assertEquals(expected, pn.name());
        return pn;
    }

    public void testNameFileName() {
        assertName("foo.txt", new Pathname("foo.txt"));
    }

    public void testNameFile() {
        assertName(null, new Pathname(new File("foo.txt")));
    }

    // file

    public Pathname assertFile(File expected, Pathname pn) {
        assertEquals(expected, pn.file());
        return pn;
    }

    public void testFileFileName() {
        assertFile(null, new Pathname("foo.txt"));
    }

    public void testFileFile() {
        assertFile(new File("foo.txt"), new Pathname(new File("foo.txt")));
    }
}
