package org.incava.ijdk.util;

import java.util.*;
import junit.framework.TestCase;
import org.incava.ijdk.lang.Closure;

public class TestIntegerList extends TestCase {
    public TestIntegerList(String name) {
        super(name);
    }

    public void assertEquals(int expected, Integer value) {
        TestCase.assertEquals(Integer.valueOf(expected), value);
    }

    public void testCtorEmpty() {
        IntegerList sl = new IntegerList();
        assertEquals(0, sl.size());
    }

    public void testCtorCollection() {
        List<Integer> list = Arrays.asList(new Integer[] { 1, 2, 3 });
        IntegerList sl = new IntegerList(list);
        assertEquals(3, sl.size());
    }

    public void testCtorOneArg() {
        IntegerList sl = new IntegerList(1);
        assertEquals(1, sl.size());
        assertEquals(1, sl.get(0));
    }

    public void testCtorTwoArgs() {
        IntegerList sl = new IntegerList(1, 2);
        assertEquals(2, sl.size());
        assertEquals(1, sl.get(0));
        assertEquals(2, sl.get(1));
    }

    public void testCtorThreeArgs() {
        IntegerList sl = new IntegerList(1, 2, 3);
        assertEquals(3, sl.size());
        assertEquals(1, sl.get(0));
        assertEquals(2, sl.get(1));
        assertEquals(3, sl.get(2));
    }

    public void assertMinimum(Integer expected, Integer ... values) {
        IntegerList sl = new IntegerList(values);
        assertEquals("values: " + Arrays.asList(values), expected, sl.minimum());
    }

    public void testMinEmpty() {
        assertMinimum(null);
    }

    public void testMinOneElement() {
        assertMinimum(1, 1);
    }

    public void testMinTwoElementsOrdered() {
        assertMinimum(1, 1, 2);
    }

    public void testMinTwoElementsReversed() {
        assertMinimum(1, 2, 1);
    }

    public void assertMaximum(Integer expected, Integer ... values) {
        IntegerList sl = new IntegerList(values);
        assertEquals("values: " + Arrays.asList(values), expected, sl.maximum());
    }

    public void testMaxEmpty() {
        assertMaximum(null);
    }

    public void testMaxOneElement() {
        assertMaximum(1, 1);
    }

    public void testMaxTwoElementsOrdered() {
        assertMaximum(2, 1, 2);
    }

    public void testMaxTwoElementsReversed() {
        assertMaximum(2, 2, 1);
    }
}
