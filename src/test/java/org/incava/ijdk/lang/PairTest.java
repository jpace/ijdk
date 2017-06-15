package org.incava.ijdk.lang;

import junit.framework.TestCase;
import java.util.*;

public class PairTest extends TestCase {
    public PairTest(String name) {
        super(name);
    }

    public <A extends Comparable<? super A>, B extends Comparable<? super B>> void assertCompareToEquals(Pair<A, B> a, Pair<A, B> b) {
        String ab = "a: " + a + "; b: " + b;

        assertEquals(ab, a, b);
        assertEquals(ab, 0, a.compareTo(b));
        assertEquals(ab, 0, b.compareTo(a));
    }

    public <A extends Comparable<? super A>, B extends Comparable<? super B>> void assertCompareToLessThan(Pair<A, B> a, Pair<A, B> b) {
        String ab = "a: " + a + "; b: " + b;
        
        assertFalse(ab, a.equals(b));
        assertTrue(ab, a.compareTo(b) < 0);
        assertTrue(ab, b.compareTo(a) > 0);
    }

    public <A extends Comparable<? super A>, B extends Comparable<? super B>> void doComparableTest(Pair<A, B> a, Pair<A, B> b, Pair<A, B> c, Pair<A, B> d) {
        String ab = "a: " + a + "; b: " + b;
        String ac = "a: " + a + "; c: " + c;
        String ad = "a: " + a + "; d: " + d;

        assertCompareToEquals(a, b);
        assertCompareToLessThan(a, c);
        assertCompareToLessThan(d, a);
    }

    public void testCreate() {
        Pair<Integer, String> intStrPair = Pair.create(Integer.valueOf(34), "thirty-four");
        assertTrue(intStrPair instanceof Pair);
    }
    
    public void testComparable() {
        Pair<String, Integer> a = Pair.create("hello", Integer.valueOf(4));
        Pair<String, Integer> b = Pair.create("hello", 4);
        Pair<String, Integer> c = Pair.create("hello", 5);
        Pair<String, Integer> d = Pair.create("ha",    4);

        doComparableTest(a, b, c, d);
    }

    public void testGetFirstDefault() {
        Pair<String, Integer> pair = Pair.create("abc", 123);
        assertEquals("abc", pair.getFirst());
    }

    public void testFirstDefault() {
        Pair<String, Integer> pair = Pair.create("abc", 123);
        assertEquals("abc", pair.first());
    }

    public void testGetSecondDefault() {
        Pair<String, Integer> pair = Pair.create("abc", 123);
        assertEquals(Integer.valueOf(123), pair.getSecond());
    }

    public void testSecondDefault() {
        Pair<String, Integer> pair = Pair.create("abc", 123);
        assertEquals(Integer.valueOf(123), pair.second());
    }
}
