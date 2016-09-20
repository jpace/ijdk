package ijdk.lang;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestComparablee extends TestCase {
    public TestComparablee(String name) {
        super(name);
    }

    // compare

    public<T extends Comparable<T>> int assertCompare(int expected, T x, T y) {
        int result = Comparablee.compare(x, y);
        assertEquals("x: " + x + "; y: " + y, expected, result);
        return result;
    }

    public void testCompareNullNull() {
        assertCompare(0, null, null);
    }

    public void testCompareNullObject() {
        assertCompare(-1, null, new String());
    }

    public void testCompareObjectNull() {
        assertCompare(1, new String(), null);
    }

    public void testCompareSameObject() {
        String obj = new String();
        assertCompare(0, obj, obj);
    }

    public void testCompareDifferentObject() {
        assertCompare(0, new String(), new String());
    }

    public void testCompareEquivalentObject() {
        assertCompare(0, new String("abc"), new String("abc"));
    }

    public void testCompareLesserObject() {
        assertCompare(-3, new String("abc"), new String("def"));
    }

    public void testCompareGreaterObject() {
        assertCompare(3, new String("def"), new String("abc"));
    }    
}
