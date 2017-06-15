package org.incava.ijdk.tuple;

import org.junit.Test;

import static org.incava.test.Assertions.assertEqual;

public class PairTest {
    @Test
    public void init() {
        Pair<String, Integer> p = Pair.of("abc", 123);
        assertEqual("abc", p.getFirst());
        assertEqual("abc", p.first());
        assertEqual(123, p.getSecond());
        assertEqual(123, p.second());
    }
}
