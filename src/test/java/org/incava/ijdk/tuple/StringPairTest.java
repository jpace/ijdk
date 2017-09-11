package org.incava.ijdk.tuple;

import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;

public class StringPairTest {
    @Test
    public void init() {
        StringPair p = StringPair.of("abc", "def");
        assertEqual("abc", p.first());
        assertEqual("def", p.second());
    }
}
