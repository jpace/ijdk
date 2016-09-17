package ijdk.lang;

import junit.framework.TestCase;

public class TestObjectt extends TestCase {
    public TestObjectt(String name) {
        super(name);
    }

    public Objectt assertAccessors(Object expected, Objectt objt) {
        assertEquals(expected, objt.getObject());
        assertEquals(expected, objt.obj());
        return objt;
    }

    // ctor

    public void testCtorNull() {
        assertAccessors(null, new Objectt(null));
    }

    public void testCtorNotNull() {
        Object obj = new Object();
        assertAccessors(obj, new Objectt(obj));
    }

    // areEqual

    public boolean assertAreEqual(boolean expected, Object x, Object y) {
        boolean result = Objectt.areEqual(x, y);
        assertEquals("x: " + x + "; y: " + y, expected, result);
        return result;
    }

    public void testAreEqualNullNull() {
        assertAreEqual(true, null, null);
    }

    public void testAreEqualNullNotNull() {
        assertAreEqual(false, null, "x");
    }

    public void testAreEqualNotNullNotNull() {
        assertAreEqual(false, "x", null);
    }

    public void testAreEqualMatch() {
        assertAreEqual(true, "x", "x");
    }

    public void testAreEqualNoMatch() {
        assertAreEqual(false, "x", "y");
    }
}
