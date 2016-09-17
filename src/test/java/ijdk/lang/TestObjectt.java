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

    public void testCtorNull() {
        assertAccessors(null, new Objectt(null));
    }

    public void testCtorNotNull() {
        Object obj = new Object();
        assertAccessors(obj, new Objectt(obj));
    }    
}
