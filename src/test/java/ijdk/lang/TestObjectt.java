package ijdk.lang;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestObjectt extends TestCase {
    public TestObjectt(String name) {
        super(name);
    }

    public void testCtorNull() {
        Objectt objt = new Objectt(null);
        assertEquals(null, objt.getObject());
        assertEquals(null, objt.obj());
    }

    public void testCtorNotNull() {
        Object obj = new Object();
        Objectt objt = new Objectt(obj);
        assertEquals(obj, objt.getObject());
        assertEquals(obj, objt.obj());
    }
}
