package org.incava.ijdk.lang;

import java.util.*;
import junit.framework.TestCase;

public class TestObjectExt extends TestCase {
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

    public void testIsTrueNull() {
        assertIsTrue(false, null);
    }

    public void testIsTrueObject() {
        assertIsTrue(true, new Object());
    }

    public void testIsTrueEmptyString() {
        assertIsTrue(false, "");
    }

    public void testIsTrueNotEmptyString() {
        assertIsTrue(true, "abc");
    }

    public void testIsTrueEmptyCollection() {
        assertIsTrue(false, new ArrayList<String>());
    }

    public void testIsTrueNotEmptyCollection() {
        assertIsTrue(true, Arrays.asList(new Integer[] { 1 }));
    }

    public void testIsTrueEmptyArray() {
        assertIsTrue(false, new Integer[0]);
    }

    public void testIsTrueNotEmptyArray() {
        assertIsTrue(true, new Integer[] { 1 });
    }

    // isFalse

    public boolean assertIsFalse(boolean expected, Object obj) {
        boolean result = ObjectExt.isFalse(obj);
        assertEquals("obj: " + obj, expected, result);
        return result;
    }

    public void testIsFalseNull() {
        assertIsFalse(true, null);
    }

    public void testIsFalseObject() {
        assertIsFalse(false, new Object());
    }

    public void testIsFalseEmptyString() {
        assertIsFalse(true, "");
    }

    public void testIsFalseNotEmptyString() {
        assertIsFalse(false, "abc");
    }

    public void testIsFalseEmptyCollection() {
        assertIsFalse(true, new ArrayList<String>());
    }

    public void testIsFalseNotEmptyCollection() {
        assertIsFalse(false, Arrays.asList(new Integer[] { 1 }));
    }

    public void testIsFalseEmptyArray() {
        assertIsFalse(true, new Integer[0]);
    }

    public void testIsFalseNotEmptyArray() {
        assertIsFalse(false, new Integer[] { 1 });
    }    

    // isEmpty

    public boolean assertIsEmpty(boolean expected, Object obj) {
        boolean result = ObjectExt.isEmpty(obj);
        assertEquals("obj: " + obj, expected, result);
        return result;
    }

    public void testIsEmptyNull() {
        assertIsEmpty(true, null);
    }

    public void testIsEmptyObject() {
        assertIsEmpty(false, new Object());
    }

    public void testIsEmptyEmptyString() {
        assertIsEmpty(true, "");
    }

    public void testIsEmptyNotEmptyString() {
        assertIsEmpty(false, "abc");
    }

    public void testIsEmptyEmptyCollection() {
        assertIsEmpty(true, new ArrayList<String>());
    }

    public void testIsEmptyNotEmptyCollection() {
        assertIsEmpty(false, Arrays.asList(new Integer[] { 1 }));
    }

    public void testIsEmptyEmptyArray() {
        assertIsEmpty(true, new Integer[0]);
    }

    public void testIsEmptyNotEmptyArray() {
        assertIsEmpty(false, new Integer[] { 1 });
    }

    // isNull

    public boolean assertIsNull(boolean expected, Object obj) {
        boolean result = ObjectExt.isNull(obj);
        assertEquals("obj: " + obj, expected, result);
        return result;
    }

    public void testIsNullNull() {
        assertIsNull(true, null);
    }

    public void testIsNullObject() {
        assertIsNull(false, new Object());
    }

    // isNotNull

    public boolean assertIsNotNull(boolean expected, Object obj) {
        boolean result = ObjectExt.isNotNull(obj);
        assertEquals("obj: " + obj, expected, result);
        return result;
    }

    public void testIsNotNullNull() {
        assertIsNotNull(false, null);
    }

    public void testIsNotNullObject() {
        assertIsNotNull(true, new Object());
    }
}
