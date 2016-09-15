package org.incava.ijdk.lang;

import org.incava.test.TestCaseExt;

public class TestRange extends TestCaseExt {
    public TestRange(String name) {
        super(name);
    }

    public void assertIncludes(boolean expected, Range rg, Integer val) {
        assertEquals("range: '" + rg + "' includes '" + val + "'", expected, rg.includes(val));
    }

    public void testFirstLast() {
        Range rg = new Range(3, 17);

        assertFirstEquals(3, rg);
        assertLastEquals(17, rg);
    }

    public void assertFirstEquals(Integer expected, Range rg) {
        assertEquals("range: " + rg, expected, rg.getFirst());
    }

    public void assertLastEquals(Integer expected, Range rg) {
        assertEquals("range: " + rg, expected, rg.getLast());
    }

    public void assertNotEqual(Object obj0, Object obj1) {
        assertFalse("obj0: " + obj0 + "; obj1" + obj1, obj0.equals(obj1));
    }

    public void testIncludes() {
        Range rg = new Range(-11, 1112);

        assertIncludes(true, rg, -11);
        assertIncludes(true, rg, -10);
        assertIncludes(true, rg, -9);
        assertIncludes(true, rg, 1110);
        assertIncludes(true, rg, 1111);
        assertIncludes(true, rg, 1112);

        assertIncludes(false, rg, -12);
        assertIncludes(false, rg, -100);
        assertIncludes(false, rg, 1113);
        assertIncludes(false, rg, 1114);
    }

    public void testEquals() {
        Range rg0 = new Range(8, 14);
        Range rg1 = new Range(8, 14);

        assertEquals(rg0, rg1);

        assertNotEqual(rg0, new Range(7, 14));
        assertNotEqual(rg0, new Range(9, 14));
        assertNotEqual(rg0, new Range(8, 13));
        assertNotEqual(rg0, new Range(8, 15));

        Object obj = new Object();
        assertNotEqual(rg0, obj);
    }

    public void testToArray() {
        Range rg = new Range(18, 81);
        assertEquals(new Integer[] { 18, 81 }, rg.toArray());
        assertNotEqual(new Integer[] { 17, 81 }, rg.toArray());
    }

    public void testToExpandedArray() {
        Range rg = new Range(1, 4);
        assertEquals(new Integer[] { 1, 2, 3, 4 }, rg.toExpandedArray());
    }

    public void testIterator() {
        Range rg11_14 = new Range(11, 14);
        Integer currExp = 11;
        for (Integer i : rg11_14) {
            assertEquals(currExp, i);
            currExp++;
        }
        assertEquals(new Integer(15), currExp);

        // first == last
        Range rg7_7 = new Range(7, 7);
        currExp = 7;
        for (Integer i : rg7_7) {
            assertEquals(currExp, i);
            currExp++;
        }
        assertEquals(new Integer(8), currExp);

        // first > last
        Range rg8_7 = new Range(8, 7);
        for (Integer i : rg8_7) {
            fail("rg8_7: " + rg8_7 + "; should not iterate");
        }
    }

    public void assertCompareTo(int expCmp, Range rg0, Range rg1) {
        String msg = "rg0: " + rg0 + "; rg1: " + rg1;
        assertEquals(msg, expCmp, rg0.compareTo(rg1));
    }

    public void testCompareTo() {
        Range rg100_110_0 = new Range(100, 110);
        Range rg100_110_1 = new Range(100, 110);

        assertCompareTo(0, rg100_110_0, rg100_110_1);

        // first < other.first
        Range rg99_110 = new Range(99, 110);
        assertCompareTo(1, rg100_110_0, rg99_110);
        assertCompareTo(-1, rg99_110, rg100_110_0);

        // last < other.last
        Range rg100_109 = new Range(100, 109);
        assertCompareTo(1, rg100_110_0, rg100_109);
        assertCompareTo(-1, rg100_109, rg100_110_0);
    }

    public void testToString() {
        Range rg = new Range(4, 8);
        assertEquals("[4 .. 8]", rg.toString());
    }
}
