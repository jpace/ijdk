package org.incava.ijdk.tuple;

import org.incava.ijdk.lang.KeyValue;

/**
 * A pair of objects.
 *
 * @param <A> the type of the first element
 * @param <B> the type of the second element
 */
public class Pair<A, B> extends KeyValue<A, B> {
    /**
     * Shorter syntax than the default constructor, allowing the compiler to discern the object types.
     *
     * @param <A> the type of the first object
     * @param <B> the type of the second object
     * @param first the first object
     * @param second the second object
     * @return the new pair
     */
    public static <A, B> Pair<A, B> of(A first, B second) {
        return new Pair<A, B>(first, second);
    }
    
    /**
     * Creates a pair.
     *
     * @param first the first of the pair
     * @param second the second of the pair
     */
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
