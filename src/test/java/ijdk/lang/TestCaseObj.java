package ijdk.lang;

import org.incava.ijdk.lang.ObjectTest;

public class TestCaseObj extends ObjectTest {
    public TestCaseObj(String name) {
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

    public boolean assertObjectsEqual(boolean expected, Object x, Object y) {
        boolean result = new Obj(x).equals(y);
        assertEquals("x: " + x + "; y: " + y, expected, result);
        return result;
    }

    // isTrue

    public boolean assertIsTrue(boolean expected, Object obj) {
        boolean result = new Obj(obj).isTrue();
        assertEquals("obj: " + obj, expected, result);
        return result;
    }

    // isFalse

    public boolean assertIsFalse(boolean expected, Object obj) {
        boolean result = new Obj(obj).isFalse();
        assertEquals("obj: " + obj, expected, result);
        return result;
    }

    // isEmpty

    public boolean assertIsEmpty(boolean expected, Object obj) {
        boolean result = new Obj(obj).isEmpty();
        assertEquals("obj: " + obj, expected, result);
        return result;
    }

    // isNull

    public boolean assertIsNull(boolean expected, Object obj) {
        boolean result = new Obj(obj).isNull();
        assertEquals("obj: " + obj, expected, result);
        return result;
    }

    // isNotNull

    public boolean assertIsNotNull(boolean expected, Object obj) {
        boolean result = new Obj(obj).isNotNull();
        assertEquals("obj: " + obj, expected, result);
        return result;
    }

}
