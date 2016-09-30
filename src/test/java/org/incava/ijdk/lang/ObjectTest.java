package org.incava.ijdk.lang;

import java.util.*;
import junit.framework.TestCase;

public abstract class ObjectTest extends TestCase {
    public ObjectTest(String name) {
        super(name);
    }

    // ObjectExt: equal, Obj: equals

    public abstract boolean assertObjectsEqual(boolean expected, Object x, Object y);

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

    public abstract boolean assertIsTrue(boolean expected, Object obj);

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

    public abstract boolean assertIsFalse(boolean expected, Object obj);

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

    public abstract boolean assertIsEmpty(boolean expected, Object obj);

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

    public abstract boolean assertIsNull(boolean expected, Object obj);

    public void testIsNullNull() {
        assertIsNull(true, null);
    }

    public void testIsNullObject() {
        assertIsNull(false, new Object());
    }

    // isNotNull

    public abstract boolean assertIsNotNull(boolean expected, Object obj);

    public void testIsNotNullNull() {
        assertIsNotNull(false, null);
    }

    public void testIsNotNullObject() {
        assertIsNotNull(true, new Object());
    }    
}
