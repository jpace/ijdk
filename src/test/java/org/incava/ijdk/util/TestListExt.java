package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;

public class TestListExt extends TestCase {
    public static final List<Integer> NUMS = Arrays.asList(new Integer[] { 2, 4, 6, 8 });
    public static final List<String> WORDS  = Arrays.asList(new String[] { "the", "whole", "kit", "and", "caboodle" });
    public static final List<String> EMPTY  = new ArrayList<String>();

    public TestListExt(String name) {
        super(name);
    }

    public <T> void assertListExtGet(T exp, List<T> list, int idx) {
        String msg = "ListExt.get of '" + idx + "' in list: '" + list + "'";
        assertEquals(msg, exp, ListExt.get(list, idx));
    }

    public <T> void assertListExtLast(T exp, List<T> list) {
        String msg = "ListExt.last of list: '" + list + "'";
        assertEquals(msg, exp, ListExt.last(list));
    }

    public <T> void assertRandomElement(List<T> list) {
        T elmt = ListExt.getRandomElement(list);
        assertNotNull(elmt);
    }

    public void testGet() {
        assertListExtGet(2,    NUMS, 0);
        assertListExtGet(4,    NUMS, 1);
        assertListExtGet(6,    NUMS, 2);
        assertListExtGet(8,    NUMS, 3);
        assertListExtGet(null, NUMS, 4);

        assertListExtGet(8,    NUMS, -1);
        assertListExtGet(6,    NUMS, -2);
        assertListExtGet(4,    NUMS, -3);
        assertListExtGet(2,    NUMS, -4);
        assertListExtGet(null, NUMS, -5);

        assertListExtGet(null, EMPTY,  0);
        assertListExtGet(null, EMPTY,  1);
        assertListExtGet(null, EMPTY, -1);

        assertListExtGet(null, null,  0);
        assertListExtGet(null, null,  1);
        assertListExtGet(null, null, -1);
    }

    public void testLast() {
        assertListExtLast(8,          NUMS);
        assertListExtLast("caboodle", WORDS);

        assertListExtLast(null, EMPTY);
        assertListExtLast(null, null);
    }

    public void testRemoveAll() {
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("one");
        list.add("two");
        list.add("two");
        list.add("two");
        list.add("three");

        ListExt.removeAll(list, "three");
        List<String> expectedOneTwo = Arrays.asList("one", "one", "two", "two", "two");
        assertEquals(expectedOneTwo, list);

        ListExt.removeAll(list, "one");
        List<String> expectedTwo = Arrays.asList("two", "two", "two");
        assertEquals(expectedTwo, list);
        
        ListExt.removeAll(list, "two");
        assertTrue(list.isEmpty());
    }

    public void assertGetIndex(Integer exp, Integer size, Integer index) {
        assertEquals("index: " + index + " within size: " + size, exp, ListExt.getIndex(size, index));
    }

    public void testGetIndex() {
        assertGetIndex(0,    4,  0);
        assertGetIndex(1,    4,  1);
        assertGetIndex(null, 4,  4);
        assertGetIndex(null, 4,  5);

        assertGetIndex(3,    4, -1);
        assertGetIndex(2,    4, -2);
        assertGetIndex(1,    4, -3);
        assertGetIndex(0,    4, -4);
        assertGetIndex(null, 4, -5);
        assertGetIndex(null, 4, -6);

        assertGetIndex(null, 0,  0);
        assertGetIndex(null, 0,  1);
        assertGetIndex(null, 0, -1);
    }

    public <T> void assertContains(boolean exp, List<T> list, T val) {
        assertEquals("list: " + list + "; contains: '" + val + "'", exp, ListExt.contains(list, val));
    }
    
    public void testContains() {
        List<String> strList = new ArrayList<String>();
        strList.add("one");
        strList.add("two");
        strList.add("three");

        assertContains(true, strList, "one");
        assertContains(true, strList, "three");
        assertContains(false, strList, "four");
        assertContains(false, strList, "ONE");

        assertContains(false, null, null);

        List<Integer> intList = new ArrayList<Integer>();
        intList.add(1);
        intList.add(2);
        intList.add(3);

        assertContains(true, intList, 1);
        assertContains(true, intList, 3);
        assertContains(false, intList, 4);
    }

    public void testRandomElementNonEmpty() {
        String elmt = ListExt.getRandomElement(WORDS);
        assertNotNull(elmt);
    }

    public void testRandomElementNull() {
        String elmt = ListExt.getRandomElement(null);
        assertNull(elmt);
    }

    public void testRandomElementEmpty() {
        String elmt = ListExt.getRandomElement(EMPTY);
        assertNull(elmt);
    }
}
