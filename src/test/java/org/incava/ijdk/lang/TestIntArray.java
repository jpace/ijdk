package org.incava.ijdk.lang;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestIntArray extends TestCase {
    public TestIntArray(String name) {
        super(name);
    }

    protected void assertGetMax(int expected, int[] ary) {
        assertEquals("ary: " + ary, expected, IntArray.getMax(ary));
    }

    public void testGetMaxNull() {
        assertGetMax(Integer.MIN_VALUE, null);
    }

    public void testGetMaxOneElementPositive() {
        assertGetMax(3, new int[] { 3 });
    }    

    public void testGetMaxOneElementNegative() {
        assertGetMax(-17, new int[] { -17 });
    }    

    public void testGetMaxOneElementZero() {
        assertGetMax(0, new int[] { 0 });
    }    

    public void testGetMaxTwoElementsAscending() {
        assertGetMax(17, new int[] { 3, 17 });
    }    

    public void testGetMaxTwoElementsDescending() {
        assertGetMax(17, new int[] { 17, 3 });
    }    
}
