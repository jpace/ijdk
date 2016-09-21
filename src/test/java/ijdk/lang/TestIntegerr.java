package ijdk.lang;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestIntegerr extends TestCase {
    public TestIntegerr(String name) {
        super(name);
    }

    public Integerr assertAccessors(Integer expected, Integerr intr) {
        assertEquals(expected, intr.getInteger());
        assertEquals(expected, intr.integer());
        return intr;
    }

    // ctor from integer

    public void testCtorNull() {
        assertAccessors(null, new Integerr((Integer)null));
    }

    public void testCtorNotNull() {
        Integer i = new Integer(17);
        assertAccessors(i, new Integerr(i));
    }

    // ctor from string
    
    public Integer assertStringCtor(Integer expected, String str) {
        Integer result = new Integerr(str).integer();
        assertEquals("str: '" + str + "'", expected, result);
        return result;
    }

    public void testStringCtorNull() {
        assertStringCtor(null, null);
    }

    public void testStringCtorEmpty() {
        assertStringCtor(null, "");
    }

    public void testStringCtorAlpha() {
        assertStringCtor(null, "abc");
    }

    public void testStringCtorAlphaNumber() {
        assertStringCtor(null, "a1");
    }

    public void testStringCtorNumber() {
        assertStringCtor(1, "1");
    }

    public void testStringCtorNumberAlpha() {
        assertStringCtor(null, "1a");
    }
}
