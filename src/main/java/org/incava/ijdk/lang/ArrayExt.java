package org.incava.ijdk.lang;

import ijdk.lang.Arrayy;
import java.util.Arrays;
import org.incava.ijdk.util.EmptyIterable;

/**
 * Extensions for C-style arrays (e.g., Foo[]).
 */
public class ArrayExt {
    /**
     * Returns an Iterable (iterator) for the C-style array, which can be null.
     * If <code>ary</code> is null, an "empty" iterator will be returned.
     */
    public static <T> Iterable<T> iter(T[] ary) {
        return new Arrayy<T>(ary).iter();
    }
}
