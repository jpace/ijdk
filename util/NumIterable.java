package org.incava.ijdk.util;

import java.util.Iterator;

/**
 * An iterable for executing <code>num</code> times.
 *
 * @see NumIterator
 */
public class NumIterable implements Iterable<Integer> {
    private final int num;
    
    public NumIterable(int num) {
        this.num = num;
    }
    
    public Iterator<Integer> iterator() {
        return new NumIterator(num);
    }
}
