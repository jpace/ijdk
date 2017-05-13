package org.incava.ijdk.lang;

import org.incava.ijdk.collect.Array;
import org.incava.test.TestCaseExt;

public class TestRange extends TestCaseExt {
    public TestRange(String name) {
        super(name);
    }

    // first

    public void assertFirstEquals(Integer expected, Range rg) {
        assertEquals("range: " + rg, expected, rg.getFirst());
    }

    // last

    public void assertLastEquals(Integer expected, Range rg) {
        assertEquals("range: " + rg, expected, rg.getLast());
    }

    // ctor

    public void testFirstLessThanLast() {
        Range rg = new Range(3, 17);

        assertFirstEquals(3, rg);
        assertLastEquals(17, rg);
    }

    public void testFirstGreaterThanLast() {
        Range rg = new Range(17, 3);

        assertFirstEquals(17, rg);
        assertLastEquals(3, rg);
    }

    public void testFirstEqualsLast() {
        Range rg = new Range(3, 3);

        assertFirstEquals(3, rg);
        assertLastEquals(3, rg);
    }

    public void assertNotEqual(Object obj0, Object obj1) {
        assertFalse("obj0: " + obj0 + "; obj1" + obj1, obj0.equals(obj1));
    }

    // includes
    
    public void assertIncludes(boolean expected, Range rg, Integer val) {
        assertEquals("range: '" + rg + "' includes '" + val + "'", expected, rg.includes(val));
    }

    public void testIncludesFirstLessThanLast() {
        Range rg = new Range(3, 5);

        assertIncludes(true, rg, 3);
        assertIncludes(true, rg, 4);
        assertIncludes(true, rg, 5);

        assertIncludes(false, rg, 2);
        assertIncludes(false, rg, 6);
    }

    public void testIncludesFirstEqualsLast() {
        Range rg = new Range(3, 3);

        assertIncludes(true, rg, 3);

        assertIncludes(false, rg, 2);
        assertIncludes(false, rg, 4);
    }

    public void testIncludesFirstGreaterThanLast() {
        Range rg = new Range(5, 3);


        assertIncludes(false, rg, 2);
        assertIncludes(false, rg, 3);
        assertIncludes(false, rg, 4);
        assertIncludes(false, rg, 5);
        assertIncludes(false, rg, 6);
    }

    // equals

    public void testEqualsRangeNull() {
        Range rg = new Range(3, 7);
        assertNotEqual(rg, null);
    }

    public void testEqualsRangeNotRange() {
        Range rg = new Range(3, 7);
        Object obj = new Object();
        assertNotEqual(rg, obj);
    }

    public void testEqualsBothMatch() {
        Range x = new Range(3, 7);
        Range y = new Range(3, 7);
        assertEquals(x, y);
    }

    public void testEqualsMismatchFirst() {
        Range x = new Range(3, 7);
        Range y = new Range(2, 7);
        assertNotEqual(x, y);
    }

    public void testEqualsMismatchLast() {
        Range x = new Range(3, 7);
        Range y = new Range(3, 6);
        assertNotEqual(x, y);
    }

    public void testEqualsBothMismatch() {
        Range x = new Range(3, 7);
        Range y = new Range(2, 6);
        assertNotEqual(x, y);
    }

    // toArray

    public Range assertToArray(Array<Integer> expected, Range rg) {
        assertEquals("range: " + rg, expected, rg.toArray());
        return rg;
    }    

    public void testToArrayFirstLessThanLast() {
        assertToArray(Array.of(3, 4, 5, 6, 7), new Range(3, 7));
    }

    public void testToArrayFirstEqualsLast() {
        assertToArray(Array.of(3), new Range(3, 3));
    }

    public void testToArrayFirstGreaterThanLast() {
        assertToArray(Array.<Integer>empty(), new Range(7, 3));
    }

    // iterator

    public Range assertIterator(Integer[] expected, Range rg) {
        int idx = 0;
        for (Integer i : rg) {
            assertEquals("idx: " + idx, expected[idx], i);
            ++idx;
        }
        assertEquals(expected.length, idx);
        return rg;
    }    

    public void testIteratorFirstLessThanLast() {
        assertIterator(new Integer[] { 3, 4, 5, 6, 7 }, new Range(3, 7));
    }

    public void testIteratorFirstEqualsLast() {
        assertIterator(new Integer[] { 3 }, new Range(3, 3));
    }

    public void testIteratorFirstGreaterThanLast() {
        assertIterator(new Integer[] { }, new Range(7, 3));
    }

    // compareTo
    
    public void assertCompareTo(int expCmp, Range rg0, Range rg1) {
        String msg = "rg0: " + rg0 + "; rg1: " + rg1;
        assertEquals(msg, expCmp, rg0.compareTo(rg1));
    }

    public void testCompareToRangeNull() {
        Range rg = new Range(3, 7);
        assertCompareTo(1, rg, null);
    }

    public void testCompareToBothMatch() {
        Range x = new Range(3, 7);
        Range y = new Range(3, 7);
        assertCompareTo(0, x, y);
    }

    public void testCompareFirstLessThanOther() {
        Range x = new Range(2, 7);
        Range y = new Range(3, 7);
        assertCompareTo(-1, x, y);
    }

    public void testCompareLastLessThanOther() {
        Range x = new Range(3, 6);
        Range y = new Range(3, 7);
        assertCompareTo(-1, x, y);
    }

    // toString
    
    public String assertToString(String expected, Range rg) {
        String msg = "rg: " + rg;
        String result = rg.toString();
        assertEquals(msg, expected, result);
        return result;
    }

    public void testToStringFirstLessThanLast() {
        assertToString("[3 .. 7]", new Range(3, 7));
    }

    public void testToStringFirstEqualsLast() {
        assertToString("[3 .. 3]", new Range(3, 3));
    }

    public void testToStringFirstGreaterThanLast() {
        assertToString("[7 .. 3]", new Range(7, 3));
    }    
}
