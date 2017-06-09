package org.incava.ijdk.lang;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Arrays;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

public class TestObjectExt extends TestCase {
    public TestObjectExt(String name) {
        super(name);
    }

    // areEqual, equal

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

    // ObjectExt: equal, Obj: equals

    public boolean objectsEqual(Object x, Object y) {
        return ObjectExt.equal(x, y);
    }    

    public boolean assertObjectsEqual(boolean expected, Object x, Object y) {
        return assertEqual(expected, objectsEqual(x, y), message("x", x, "y", y));
    }

    public void testObjectsEqualNullNull() {
        assertObjectsEqual(true, null, null);
    }

    public void testObjectsEqualNullObject() {
        assertObjectsEqual(false, null, new Object());
    }

    public void testObjectsEqualObjectNull() {
        assertObjectsEqual(false, new Object(), null);
    }

    public void testObjectsEqualSameObject() {
        Object obj = new Object();
        assertObjectsEqual(true, obj, obj);
    }

    public void testObjectsEqualDifferentObject() {
        assertObjectsEqual(false, new Object(), new Object());
    }

    public void testObjectsEqualEquivalentObject() {
        assertObjectsEqual(true, new String("abc"), new String("abc"));
    }
    
    // isTrue

    public boolean assertIsTrue(boolean expected, Object obj) {
        return assertEqual(expected, isTrue(obj), message("obj", obj));
    }

    public boolean isTrue(Object obj) {
        return ObjectExt.isTrue(obj);
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
        return assertEqual(expected, isFalse(obj), message("obj", obj));
    }

    public boolean isFalse(Object obj) {
        return ObjectExt.isFalse(obj);
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
        return assertEqual(expected, isEmpty(obj), message("obj", obj));
    }    

    public boolean isEmpty(Object obj) {
        return ObjectExt.isEmpty(obj);
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
        return assertEqual(expected, isNull(obj), message("obj", obj));
    }    

    public boolean isNull(Object obj) {
        return ObjectExt.isNull(obj);
    }

    public void testIsNullNull() {
        assertIsNull(true, null);
    }

    public void testIsNullObject() {
        assertIsNull(false, new Object());
    }

    // isNotNull    

    public boolean assertIsNotNull(boolean expected, Object obj) {
        return assertEqual(expected, isNotNull(obj), message("obj", obj));
    }

    public boolean isNotNull(Object obj) {
        return ObjectExt.isNotNull(obj);
    }
    
    public void testIsNotNullNull() {
        assertIsNotNull(false, null);
    }

    public void testIsNotNullObject() {
        assertIsNotNull(true, new Object());
    }    
}
