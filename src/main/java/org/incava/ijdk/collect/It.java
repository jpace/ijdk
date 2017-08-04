package org.incava.ijdk.collect;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * An iterator that has a value and an index.
 */
public class It<T> {
    private final T value;
    private final int index;

    public It(T value, int index) {
        this.value = value;
        this.index = index;
    }

    public T value() {
        return this.value;
    }

    public int index() {
        return this.index;
    }
}
