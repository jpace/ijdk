package ijdk.lang;

import java.util.*;
import junit.framework.TestCase;

public class TestObj extends TestCase {
    public TestObj(String name) {
        super(name);
    }

    public Obj assertAccessors(Object expected, Obj objt) {
        assertEquals(expected, objt.getObject());
        assertEquals(expected, objt.obj());
        return objt;
    }

    // ctor

    public void testCtorNull() {
        assertAccessors(null, new Obj(null));
    }

    public void testCtorNotNull() {
        Object obj = new Object();
        assertAccessors(obj, new Obj(obj));
    }

    // equals

    public boolean assertEquals(boolean expected, Object x, Object y) {
        boolean result = new Obj(x).equals(y);
        assertEquals("x: " + x + "; y: " + y, expected, result);
        return result;
    }

    public void testEqualsNullNull() {
        assertEquals(true, null, null);
    }

    public void testEqualsNullNotNull() {
        assertEquals(false, null, "x");
    }

    public void testEqualsNotNullNotNull() {
        assertEquals(false, "x", null);
    }

    public void testEqualsMatch() {
        assertEquals(true, "x", "x");
    }

    public void testEqualsNoMatch() {
        assertEquals(false, "x", "y");
    }

    // isTrue

    public boolean assertIsTrue(boolean expected, Object obj) {
        boolean result = new Obj(obj).isTrue();
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
        boolean result = new Obj(obj).isFalse();
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
        boolean result = new Obj(obj).isEmpty();
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
        boolean result = new Obj(obj).isNull();
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
        boolean result = new Obj(obj).isNotNull();
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
