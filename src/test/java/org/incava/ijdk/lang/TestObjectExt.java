package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

public class TestObjectExt extends ObjectTest {
    public TestObjectExt(String name) {
        super(name);
    }

    // areEqual, equal

    public boolean objectsEqual(Object x, Object y) {
        return ObjectExt.equal(x, y);
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
        assertCompare(-1, new String("abc"), new String("def"));
    }

    public void testCompareGreaterObject() {
        assertCompare(1, new String("def"), new String("abc"));
    }

    // isTrue

    public boolean isTrue(Object obj) {
        return ObjectExt.isTrue(obj);
    }

    public boolean isFalse(Object obj) {
        return ObjectExt.isFalse(obj);
    }

    public boolean isEmpty(Object obj) {
        return ObjectExt.isEmpty(obj);
    }

    public boolean isNull(Object obj) {
        return ObjectExt.isNull(obj);
    }

    public boolean isNotNull(Object obj) {
        return ObjectExt.isNotNull(obj);
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
}
