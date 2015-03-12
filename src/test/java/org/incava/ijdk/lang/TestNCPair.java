package org.incava.ijdk.lang;

import junit.framework.TestCase;
import java.util.*;

public class TestNCPair extends TestCase {
    public TestNCPair(String name) {
        super(name);
    }

    public void testCreate() {
        // StringBuffers are not comparable
        NCPair<StringBuffer, String> sbStrPair = NCPair.create(new StringBuffer("thirty-four"), "thirty-four");
        assertTrue(sbStrPair instanceof NCPair);
    }

    public void testNonComparable() {
        NCPair<StringBuffer, Integer> a = NCPair.create(new StringBuffer("hello"), 4);
        NCPair<StringBuffer, Integer> b = NCPair.create(new StringBuffer("hello"), 4);
        NCPair<StringBuffer, Integer> c = NCPair.create(new StringBuffer("hello"), 5);
        NCPair<StringBuffer, Integer> d = NCPair.create(new StringBuffer("ha"),    4);

        TreeMap<StringBuffer, Integer> tm = new TreeMap<StringBuffer, Integer>();

        // StringBuffers are not Comparable, nor do they define equals.
        assertFalse(a.equals(b));
        assertFalse(a.equals(c));
    }
}
