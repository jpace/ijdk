package org.incava.ijdk.tuple;

import org.incava.ijdk.lang.KeyValue;

/**
 * A pair of objects.
 */
public class Pair<A, B> extends KeyValue<A, B> {
    /**
     * Shorter syntax than the default constructor, allowing the compiler to discern the object types.
     */
    public static <A, B> Pair<A, B> of(A first, B second) {
        return new Pair<A, B>(first, second);
    }
    
    public Pair(A first, B second) {
        super(first, second);
    }

    public A getFirst() {
        return getKey();
    }

    public B getSecond() {
        return getValue();
    }

    public A first() {
        return key();
    }

    public B second() {
        return value();
    }
}
