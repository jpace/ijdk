package org.incava.ijdk.util;

import junit.framework.TestCase;

public class TestIndex extends TestCase {
    public TestIndex(String name) {
        super(name);
    }
    
    // getIndex

    public void assertGetIndex(Integer expected, Integer size, int index) {
        Integer result = Index.getIndex(size, index);
        assertEquals("size: '" + size + "'; index: " + index, expected, result);
    }

    public void testGetIndexNull() {
        assertGetIndex(null, null, 0);
    }

    public void testGetIndexZeroLength() {
        assertGetIndex(null, 0, 0);
    }

    public void testGetIndexZero() {
        assertGetIndex(0, 4, 0);
    }

    public void testGetIndexOne() {
        assertGetIndex(1, 4, 1);
    }

    public void testGetIndexLast() {
        assertGetIndex(3, 4, 3);
    }

    public void testGetIndexOffEnd() {
        assertGetIndex(null, 4, 4);
    }

    public void testGetIndexNegativeOne() {
        assertGetIndex(3, 4, -1);
    }

    public void testGetIndexNegativeTwo() {
        assertGetIndex(2, 4, -2);
    }

    public void testGetIndexNegativeAtSecond() {
        assertGetIndex(1, 4, -3);
    }

    public void testGetIndexNegativeAtFirst() {
        assertGetIndex(0, 4, -4);
    }

    public void testGetIndexNegativeOffStart() {
        assertGetIndex(null, 4, -5);
    }
}
