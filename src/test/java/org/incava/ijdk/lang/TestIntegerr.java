package org.incava.ijdk.lang;

import java.util.*;
import junit.framework.TestCase;

public class TestIntegerr extends TestCase {
    public TestIntegerr(String name) {
        super(name);
    }

    public Integer assertToInteger(Integer expected, String str) {
        Integer result = Integerr.toInteger(str);
        assertEquals("str: '" + str + "'", expected, result);
        return result;
    }

    public void testToIntegerNull() {
        assertToInteger(null, null);
    }

    public void testToIntegerEmpty() {
        assertToInteger(null, "");
    }

    public void testToIntegerAlpha() {
        assertToInteger(null, "abc");
    }

    public void testToIntegerAlphaNumber() {
        assertToInteger(null, "a1");
    }

    public void testToIntegerNumber() {
        assertToInteger(1, "1");
    }

    public void testToIntegerNumberAlpha() {
        assertToInteger(null, "1a");
    }
}
