package org.incava.ijdk.tuple;

import org.incava.ijdk.lang.KeyValue;

/**
 * A pair of Integers.
 */
public class IntegerPair extends Pair<Integer, Integer> {
    public static IntegerPair of(Integer first, Integer second) {
        return new IntegerPair(first, second);
    }
    
    public IntegerPair(Integer first, Integer second) {
        super(first, second);
    }
}
