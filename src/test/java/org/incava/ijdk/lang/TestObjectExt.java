package org.incava.ijdk.lang;

import java.util.*;
import junit.framework.TestCase;

public class TestObjectExt extends ObjectTest {
    public TestObjectExt(String name) {
        super(name);
    }

    // areEqual

    public boolean assertAreEqual(boolean expected, Object x, Object y) {
        boolean result = ObjectExt.areEqual(x, y);
        assertEquals("x: " + x + "; y: " + y, expected, result);
        return result;
    }

    public void testAreEqualNullNull() {
        assertAreEqual(true, null, null);
    }

    public void testAreEqualNullObject() {
        assertAreEqual(false, null, new Object());
    }

    public void testAreEqualObjectNull() {
        assertAreEqual(false, new Object(), null);
    }

    public void testAreEqualSameObject() {
        Object obj = new Object();
        assertAreEqual(true, obj, obj);
    }

    public void testAreEqualDifferentObject() {
        assertAreEqual(false, new Object(), new Object());
    }

    public void testAreEqualEquivalentObject() {
        assertAreEqual(true, new String("abc"), new String("abc"));
    }

    // equal

    public boolean assertEqual(boolean expected, Object x, Object y) {
        boolean result = ObjectExt.equal(x, y);
        assertEquals("x: " + x + "; y: " + y, expected, result);
        return result;
    }

    public void testEqualNullNull() {
        assertEqual(true, null, null);
    }

    public void testEqualNullObject() {
        assertEqual(false, null, new Object());
    }

    public void testEqualObjectNull() {
        assertEqual(false, new Object(), null);
    }

    public void testEqualSameObject() {
        Object obj = new Object();
        assertEqual(true, obj, obj);
    }

    public void testEqualDifferentObject() {
        assertEqual(false, new Object(), new Object());
    }

    public void testEqualEquivalentObject() {
        assertEqual(true, new String("abc"), new String("abc"));
    }

    // compare

    public<T extends Comparable<T>> int assertCompare(int expected, T x, T y) {
        int result = ObjectExt.compare(x, y);
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

    // isTrue

    public boolean assertIsTrue(boolean expected, Object obj) {
        boolean result = ObjectExt.isTrue(obj);
        assertEquals("obj: " + obj, expected, result);
        return result;
    }

    // isFalse

    public boolean assertIsFalse(boolean expected, Object obj) {
        boolean result = ObjectExt.isFalse(obj);
        assertEquals("obj: " + obj, expected, result);
        return result;
    }

    // isEmpty

    public boolean assertIsEmpty(boolean expected, Object obj) {
        boolean result = ObjectExt.isEmpty(obj);
        assertEquals("obj: " + obj, expected, result);
        return result;
    }

    // isNull

    public boolean assertIsNull(boolean expected, Object obj) {
        boolean result = ObjectExt.isNull(obj);
        assertEquals("obj: " + obj, expected, result);
        return result;
    }

    // isNotNull

    public boolean assertIsNotNull(boolean expected, Object obj) {
        boolean result = ObjectExt.isNotNull(obj);
        assertEquals("obj: " + obj, expected, result);
        return result;
    }
}
