package org.incava.ijdk.lang;

import org.incava.ijdk.util.EmptyIterable;
import java.util.Arrays;

/**
 * Extensions for C-style arrays (e.g., Foo[]).
 */
public class ArrayExt {
    /**
     * Returns an Iterable (iterator) for the C-style array, which can be null.
     * If <code>ary</code> is null, an "empty" iterator will be returned.
     */
    public static <T> Iterable<T> iter(T[] ary) {
        return ary == null ? new EmptyIterable<T>() : Arrays.asList(ary);
    }
}
