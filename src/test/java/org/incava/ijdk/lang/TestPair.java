package org.incava.ijdk.lang;

import junit.framework.TestCase;
import java.util.*;


public class TestPair extends TestCase {

    public TestPair(String name) {
        super(name);
    }

    public <A, B> void doComparableTest(Pair<A, B> a, Pair<A, B> b, Pair<A, B> c, Pair<A, B> d) {
        tr.Ace.setVerbose(true);

        tr.Ace.yellow("a", a);
        tr.Ace.yellow("b", b);
        tr.Ace.yellow("c", c);
        tr.Ace.yellow("d", d);

        String ab = "a: " + a + "; b: " + b;
        String ac = "a: " + a + "; c: " + c;
        String ad = "a: " + a + "; d: " + d;

        assertEquals(ab, a, b);
        assertEquals(ab, 0, a.compareTo(b));
        assertEquals(ab, 0, b.compareTo(a));

        assertFalse(ac, a.equals(c));
        assertTrue(ac, a.compareTo(c) < 0);
        assertTrue(ac, c.compareTo(a) > 0);

        assertFalse(ad, a.equals(d));
        assertTrue(ad, a.compareTo(d) > 0);
        assertTrue(ad, d.compareTo(a) < 0);
    }

    public void testCreate() {
        assertEquals(Pair.class, Pair.create(Integer.valueOf(34), "thirty-four").getClass());

        // StringBuffers are not comparable
        assertEquals(Pair.class, Pair.create(new StringBuffer("thirty-four"), "thirty-four").getClass());
        assertFalse(Pair.create(new StringBuffer("thirty-four"), "thirty-four") instanceof OrderedPair);
    }
    
    public void testComparable() {
        Pair<String, Integer> a = Pair.create("hello", 4);
        Pair<String, Integer> b = Pair.create("hello", 4);
        Pair<String, Integer> c = Pair.create("hello", 5);
        Pair<String, Integer> d = Pair.create("ha",    4);

        doComparableTest(a, b, c, d);
    }

    public void testNonComparable() {
        Pair<StringBuffer, Integer> a = Pair.create(new StringBuffer("hello"), 4);
        Pair<StringBuffer, Integer> b = Pair.create(new StringBuffer("hello"), 4);
        Pair<StringBuffer, Integer> c = Pair.create(new StringBuffer("hello"), 5);
        Pair<StringBuffer, Integer> d = Pair.create(new StringBuffer("ha"),    4);

        assertFalse(a.equals(b));
        assertFalse(a.equals(c));
    }
    
}
