package ijdk.lang;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestPathnameGlob extends TestCase {
    public TestPathnameGlob(String name) {
        super(name);
    }

    public String assertPattern(String expected, String glob) {
        String result = PathnameGlob.toPattern(glob);
        assertEquals("glob: '" + glob + "'", expected, result);
        return result;
    }

    public void testEmpty() {
        assertPattern("", "");
    }

    public void testAB() {
        assertPattern("ab", "ab");
    }

    public void testASlashB() {
        assertPattern("a/b", "a/b");
    }

    public void testAStar() {
        assertPattern("a[^/]*", "a*");
    }

    public void testASlashStarStar() {
        assertPattern("a/.*", "a/**");
    }

    public void testASlashStarStarSlashB() {
        assertPattern("a/.*/b", "a/**/b");
    }

    // a**b == a*b, per Ruby

    public void testAStarStarSlashB() {
        assertPattern("a[^/]*[^/]*/b", "a**/b");
    }

    public void testADotB() {
        assertPattern("a\\.b", "a.b");
    }

    public void testADotBDotC() {
        assertPattern("a\\.b\\.c", "a.b.c");
    }
 }
