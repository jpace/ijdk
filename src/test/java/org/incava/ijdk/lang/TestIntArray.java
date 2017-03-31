package org.incava.ijdk.lang;

import java.util.Arrays;
import java.util.TreeMap;
import org.junit.Assert;
import org.junit.Test;

import static org.incava.test.Assertions.*;

public class TestIntArray {
    // max

    protected void assertMax(int expected, int[] ary) {
        assertEqual(expected, IntArray.max(ary), message("ary", ary));
    }

    @Test
    public void testMaxNull() {
        assertMax(Integer.MIN_VALUE, null);
    }

    @Test
    public void testMaxEmpty() {
        assertMax(Integer.MIN_VALUE, new int[] { });
    }    

    @Test
    public void testMaxOneElementPositive() {
        assertMax(3, new int[] { 3 });
    }    

    @Test
    public void testMaxOneElementNegative() {
        assertMax(-17, new int[] { -17 });
    }    

    @Test
    public void testMaxOneElementZero() {
        assertMax(0, new int[] { 0 });
    }    

    @Test
    public void testMaxTwoElementsAscending() {
        assertMax(17, new int[] { 3, 17 });
    }    

    @Test
    public void testMaxTwoElementsDescending() {
        assertMax(17, new int[] { 17, 3 });
    }

    // min

    protected void assertMin(int expected, int[] ary) {
        assertEqual(expected, IntArray.min(ary), message("ary", ary));
    }

    @Test
    public void testMinNull() {
        assertMin(Integer.MAX_VALUE, null);
    }

    @Test
    public void testMinEmpty() {
        assertMin(Integer.MAX_VALUE, new int[] { });
    }    

    @Test
    public void testMinOneElementPositive() {
        assertMin(3, new int[] { 3 });
    }    

    @Test
    public void testMinOneElementNegative() {
        assertMin(-17, new int[] { -17 });
    }    

    @Test
    public void testMinOneElementZero() {
        assertMin(0, new int[] { 0 });
    }    

    @Test
    public void testMinTwoElementsAscending() {
        assertMin(3, new int[] { 3, 17 });
    }    

    @Test
    public void testMinTwoElementsDescending() {
        assertMin(3, new int[] { 17, 3 });
    }

    // sum

    protected void assertSum(int expected, int[] ary) {
        assertEqual(expected, IntArray.sum(ary), message("ary", ary));
    }

    @Test
    public void testSumNull() {
        assertSum(0, null);
    }

    @Test
    public void testSumEmpty() {
        assertSum(0, new int[] { });
    }    

    @Test
    public void testSumOneElementPositive() {
        assertSum(3, new int[] { 3 });
    }    

    @Test
    public void testSumOneElementNegative() {
        assertSum(-17, new int[] { -17 });
    }    

    @Test
    public void testSumOneElementZero() {
        assertSum(0, new int[] { 0 });
    }    

    @Test
    public void testSumTwoElementsAscending() {
        assertSum(20, new int[] { 3, 17 });
    }    

    @Test
    public void testSumTwoElementsDescending() {
        assertSum(20, new int[] { 17, 3 });
    }

    // length

    protected void assertLength(int expected, int[] ary) {
        assertEqual(expected, IntArray.length(ary), message("ary", ary));
    }

    @Test
    public void testLengthNull() {
        assertLength(0, null);
    }

    @Test
    public void testLengthOneElement() {
        assertLength(1, new int[] { 3 });
    }    

    @Test
    public void testLengthTwoElements() {
        assertLength(2, new int[] { 3, 17 });
    }

    // average

    protected void assertAverage(int expected, int[] ary) {
        assertEqual(expected, IntArray.average(ary), message("ary", ary));
    }

    @Test
    public void testAverageNull() {
        assertAverage(0, null);
    }

    @Test
    public void testAverageEmpty() {
        assertAverage(0, new int[] { });
    }    

    @Test
    public void testAverageOneElementPositive() {
        assertAverage(3, new int[] { 3 });
    }    

    @Test
    public void testAverageOneElementNegative() {
        assertAverage(-17, new int[] { -17 });
    }    

    @Test
    public void testAverageOneElementZero() {
        assertAverage(0, new int[] { 0 });
    }    

    @Test
    public void testAverageTwoIntegerAverage() {
        assertAverage(10, new int[] { 3, 17 });
    }    

    @Test
    public void testAverageTwoDoubleAverage() {
        assertAverage(10, new int[] { 17, 4 });
    }

    // contains

    protected void assertContains(boolean expected, int[] ary, int value) {
        assertEqual(expected, IntArray.contains(ary, value), message("ary", ary, "value", value));
    }

    @Test
    public void testContainsNull() {
        assertContains(false, null, 3);
    }

    @Test
    public void testContainsEmpty() {
        assertContains(false, new int[] { }, 3);
    }    

    @Test
    public void testContainsOneElementMatch() {
        assertContains(true, new int[] { 3 }, 3);
    }    

    @Test
    public void testContainsOneElementNoMatch() {
        assertContains(false, new int[] { 3 }, 4);
    }    

    @Test
    public void testContainsTwoElementsMatch() {
        assertContains(true, new int[] { 3, 17 }, 17);
    }    

    @Test
    public void testContainsTwoElementsNoMatch() {
        assertContains(false, new int[] { 3, 17 }, 8);
    }

    // toStringArray

    protected void assertToStringArray(String[] expected, int[] ary) {
        assertEqual(Arrays.asList(expected), Arrays.asList(IntArray.toStringArray(ary)), message("ary", ary));
    }

    @Test
    public void testToStringArrayNull() {
        assertToStringArray(new String[] { }, null);
    }

    @Test
    public void testToStringArrayEmpty() {
        assertToStringArray(new String[] { }, new int[] { });
    }    

    @Test
    public void testToStringArrayOneElement() {
        assertToStringArray(new String[] { "3" }, new int[] { 3 });
    }    

    @Test
    public void testToStringArrayTwoElements() {
        assertToStringArray(new String[] { "3", "17" }, new int[] { 3, 17 });
    }    
}
