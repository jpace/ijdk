package org.incava.ijdk.lang;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Arrays;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

public abstract class ObjectTest extends TestCase {
    public ObjectTest(String name) {
        super(name);
    }

    // ObjectExt: equal, Obj: equals

    public boolean assertObjectsEqual(boolean expected, Object x, Object y) {
        return assertEqual(expected, objectsEqual(x, y), message("x", x, "y", y));
    }

    public abstract boolean objectsEqual(Object x, Object y);

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

    public abstract boolean isTrue(Object obj);

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
    
    public abstract boolean isFalse(Object obj);

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

    public abstract boolean isEmpty(Object obj);

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

    public abstract boolean isNull(Object obj);    

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

    public abstract boolean isNotNull(Object obj);
    
    public void testIsNotNullNull() {
        assertIsNotNull(false, null);
    }

    public void testIsNotNullObject() {
        assertIsNotNull(true, new Object());
    }    
}
