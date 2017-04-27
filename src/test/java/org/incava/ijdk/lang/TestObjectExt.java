package org.incava.ijdk.lang;

public class TestObjectExt extends ObjectTest {
    public TestObjectExt(String name) {
        super(name);
    }

    // areEqual, equal

    public boolean assertObjectsEqual(boolean expected, Object x, Object y) {
        boolean result = ObjectExt.equal(x, y);
        assertEquals("x: " + x + "; y: " + y, expected, result);

        result = ObjectExt.areEqual(x, y);
        assertEquals("x: " + x + "; y: " + y, expected, result);
        
        return result;
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
