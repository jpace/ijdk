package org.incava.ijdk.lang;

import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;

public class PairTest {
    private Pair<String, Integer> abc123 = Pair.of("abc", 123);
    
    public <A extends Comparable<? super A>, B extends Comparable<? super B>> void assertCompareToEquals(Pair<A, B> a, Pair<A, B> b) {
        String msg = message("a", a, "b", b);

        assertEqual(a, b, msg);
        assertEqual(0, a.compareTo(b), msg);
        assertEqual(0, b.compareTo(a), msg);
    }

    public <A extends Comparable<? super A>, B extends Comparable<? super B>> void assertCompareToLessThan(Pair<A, B> a, Pair<A, B> b) {
        String msg = message("a", a, "b", b);
        
        assertEqual(false, a.equals(b), msg);
        assertEqual(true, a.compareTo(b) < 0, msg);
        assertEqual(true, b.compareTo(a) > 0, msg);
    }

    @Test
    public void of() {
        Pair<Integer, String> pair = Pair.of(Integer.valueOf(34), "thirty-four");
        assertEqual(true, pair instanceof Pair, message("pair", pair));
    }
    
    @Test
    public void testComparable() {
        Pair<String, Integer> a = Pair.of("hello", Integer.valueOf(4));
        Pair<String, Integer> b = Pair.of("hello", 4);
        Pair<String, Integer> c = Pair.of("hello", 5);
        Pair<String, Integer> d = Pair.of("ha",    4);

        assertCompareToEquals(a, b);
        assertCompareToLessThan(a, c);
        assertCompareToLessThan(d, a);
    }

    @Test
    public void testGetFirstDefault() {
        assertEqual("abc", abc123.getFirst());
    }

    @Test
    public void testFirstDefault() {
        assertEqual("abc", abc123.first());
    }

    @Test
    public void testGetSecondDefault() {
        assertEqual(Integer.valueOf(123), abc123.getSecond());
    }

    @Test
    public void testSecondDefault() {
        assertEqual(Integer.valueOf(123), abc123.second());
    }
}
