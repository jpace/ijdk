package org.incava.ijdk.tuple;

import org.incava.ijdk.collect.List;
import org.incava.ijdk.lang.Common;
import org.junit.Test;

import static org.incava.test.Assertions.*;
import static org.incava.ijdk.lang.Common.*;

public class TestPair {
    @Test
    public void init() {
        Pair<String, Integer> p = Pair.of("abc", 123);
        assertEqual("abc", p.getFirst());
        assertEqual("abc", p.first());
        assertEqual(123, p.getSecond());
        assertEqual(123, p.second());
    }
}
