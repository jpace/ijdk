package org.incava.ijdk.tuple;

import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;

public class IntegerPairTest {
    @Test
    public void init() {
        IntegerPair p = IntegerPair.of(1, 2);
        assertEqual(1, p.first());
        assertEqual(2, p.second());
    }
}
