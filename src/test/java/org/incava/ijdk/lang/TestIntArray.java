package org.incava.ijdk.lang;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestIntArray extends TestCase {
    public TestIntArray(String name) {
        super(name);
    }

    // max

    protected void assertMax(int expected, int[] ary) {
        assertEquals("ary: " + ary, expected, IntArray.max(ary));
    }

    public void testMaxNull() {
        assertMax(Integer.MIN_VALUE, null);
    }

    public void testMaxEmpty() {
        assertMax(Integer.MIN_VALUE, new int[] { });
    }    

    public void testMaxOneElementPositive() {
        assertMax(3, new int[] { 3 });
    }    

    public void testMaxOneElementNegative() {
        assertMax(-17, new int[] { -17 });
    }    

    public void testMaxOneElementZero() {
        assertMax(0, new int[] { 0 });
    }    

    public void testMaxTwoElementsAscending() {
        assertMax(17, new int[] { 3, 17 });
    }    

    public void testMaxTwoElementsDescending() {
        assertMax(17, new int[] { 17, 3 });
    }

    // min

    protected void assertMin(int expected, int[] ary) {
        assertEquals("ary: " + ary, expected, IntArray.min(ary));
    }

    public void testMinNull() {
        assertMin(Integer.MAX_VALUE, null);
    }

    public void testMinEmpty() {
        assertMin(Integer.MAX_VALUE, new int[] { });
    }    

    public void testMinOneElementPositive() {
        assertMin(3, new int[] { 3 });
    }    

    public void testMinOneElementNegative() {
        assertMin(-17, new int[] { -17 });
    }    

    public void testMinOneElementZero() {
        assertMin(0, new int[] { 0 });
    }    

    public void testMinTwoElementsAscending() {
        assertMin(3, new int[] { 3, 17 });
    }    

    public void testMinTwoElementsDescending() {
        assertMin(3, new int[] { 17, 3 });
    }

    // sum

    protected void assertSum(int expected, int[] ary) {
        assertEquals("ary: " + ary, expected, IntArray.sum(ary));
    }

    public void testSumNull() {
        assertSum(0, null);
    }

    public void testSumEmpty() {
        assertSum(0, new int[] { });
    }    

    public void testSumOneElementPositive() {
        assertSum(3, new int[] { 3 });
    }    

    public void testSumOneElementNegative() {
        assertSum(-17, new int[] { -17 });
    }    

    public void testSumOneElementZero() {
        assertSum(0, new int[] { 0 });
    }    

    public void testSumTwoElementsAscending() {
        assertSum(20, new int[] { 3, 17 });
    }    

    public void testSumTwoElementsDescending() {
        assertSum(20, new int[] { 17, 3 });
    }

    // length

    protected void assertLength(int expected, int[] ary) {
        assertEquals("ary: " + ary, expected, IntArray.length(ary));
    }

    public void testLengthNull() {
        assertLength(0, null);
    }

    public void testLengthOneElement() {
        assertLength(1, new int[] { 3 });
    }    

    public void testLengthTwoElements() {
        assertLength(2, new int[] { 3, 17 });
    }

    // average

    protected void assertAverage(int expected, int[] ary) {
        assertEquals("ary: " + ary, expected, IntArray.average(ary));
    }

    public void testAverageNull() {
        assertAverage(0, null);
    }

    public void testAverageEmpty() {
        assertAverage(0, new int[] { });
    }    

    public void testAverageOneElementPositive() {
        assertAverage(3, new int[] { 3 });
    }    

    public void testAverageOneElementNegative() {
        assertAverage(-17, new int[] { -17 });
    }    

    public void testAverageOneElementZero() {
        assertAverage(0, new int[] { 0 });
    }    

    public void testAverageTwoIntegerAverage() {
        assertAverage(10, new int[] { 3, 17 });
    }    

    public void testAverageTwoDoubleAverage() {
        assertAverage(10, new int[] { 17, 4 });
    }

    // contains

    protected void assertContains(boolean expected, int[] ary, int value) {
        assertEquals("ary: " + ary + "; value: " + value, expected, IntArray.contains(ary, value));
    }

    public void testContainsNull() {
        assertContains(false, null, 3);
    }

    public void testContainsEmpty() {
        assertContains(false, new int[] { }, 3);
    }    

    public void testContainsOneElementMatch() {
        assertContains(true, new int[] { 3 }, 3);
    }    

    public void testContainsOneElementNoMatch() {
        assertContains(false, new int[] { 3 }, 4);
    }    

    public void testContainsTwoElementsMatch() {
        assertContains(true, new int[] { 3, 17 }, 17);
    }    

    public void testContainsTwoElementsNoMatch() {
        assertContains(false, new int[] { 3, 17 }, 8);
    }

    // toStringArray

    protected void assertToStringArray(String[] expected, int[] ary) {
        assertEquals("ary: " + ary, Arrays.asList(expected), Arrays.asList(IntArray.toStringArray(ary)));
    }

    public void testToStringArrayNull() {
        assertToStringArray(new String[] { }, null);
    }

    public void testToStringArrayEmpty() {
        assertToStringArray(new String[] { }, new int[] { });
    }    

    public void testToStringArrayOneElement() {
        assertToStringArray(new String[] { "3" }, new int[] { 3 });
    }    

    public void testToStringArrayTwoElements() {
        assertToStringArray(new String[] { "3", "17" }, new int[] { 3, 17 });
    }    
}
