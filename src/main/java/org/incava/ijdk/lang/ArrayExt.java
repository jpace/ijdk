package org.incava.ijdk.lang;

import org.incava.ijdk.collect.Array;

/**
 * Extensions for C-style arrays (e.g., Foo[]). Use <code>ijdk.collect.Array</code> instead.
 */
public class ArrayExt {
    /**
     * Returns an Iterable (iterator) for the C-style array, which can be null.
     * If <code>ary</code> is null, an "empty" iterator will be returned.
     *
     * @param <T> the type of the array
     * @param ary the array to iterator over; can be null
     * @return an interable, wrapping the array
     */
    public static <T> Iterable<T> iter(T[] ary) {
        return new Array<T>(ary).iter();
    }
}
