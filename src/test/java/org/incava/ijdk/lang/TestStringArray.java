package org.incava.ijdk.lang;

import java.util.*;
import junit.framework.TestCase;

public class TestStringArray extends TestCase {
    public TestStringArray(String name) {
        super(name);
    }

    public void assertIsEmpty(boolean expected, String[] ary) {
        assertEquals("ary: " + StringArray.toString(ary), expected, StringArray.isEmpty(ary));
    }

    public void testNull() {
        assertIsEmpty(true, null);
    }    

    public void testEmpty() {
        assertIsEmpty(true, new String[0]);
    }    

    public void testHasElement() {
        assertIsEmpty(false, new String[] { "x" });
    }    

    public void assertAreEqual(boolean expected, String[] x, String[] y) {
        assertEquals("x: " + StringArray.toString(x) + "; y: " + StringArray.toString(y), expected, StringArray.areEqual(x, y));
    }

    public void testBothNull() {
        assertAreEqual(true, null, null);
    }    

    public void testNullEmpty() {
        assertAreEqual(true, null, new String[0]);
    }    

    public void testEmptyNull() {
        assertAreEqual(true, new String[0], null);
    }    

    public void testEmptyEmpty() {
        assertAreEqual(true, new String[0], new String[0]);
    }    

    public void testElementEmpty() {
        assertAreEqual(false, new String[] { "x" }, new String[0]);
    }

    public void testEmptyElement() {
        assertAreEqual(false, new String[0], new String[] { "x" });
    }

    public void testElementNull() {
        assertAreEqual(false, new String[] { "x" }, null);
    }

    public void testNullElement() {
        assertAreEqual(false, null, new String[] { "x" });
    }

    public void testSingleElementMatch() {
        assertAreEqual(true, new String[] { "x" }, new String[] { "x" });
    }

    public void testSingleElementNoMatch() {
        assertAreEqual(false, new String[] { "x" }, new String[] { "y" });
    }

    public void testTwoOneElements() {
        assertAreEqual(false, new String[] { "x", "y" }, new String[] { "x" });
    }

    public void testOneTwoElements() {
        assertAreEqual(false, new String[] { "x" }, new String[] { "x", "y" });
    }

    public void testTwoElementsMatch() {
        assertAreEqual(true, new String[] { "x", "y" }, new String[] { "x", "y" });
    }

    public void testTwoElementsNoMatch() {
        assertAreEqual(false, new String[] { "x", "y" }, new String[] { "x", "z" });
    }

    public void testTwoElementsMisordered() {
        assertAreEqual(false, new String[] { "x", "y" }, new String[] { "y", "x" });
    }
}
