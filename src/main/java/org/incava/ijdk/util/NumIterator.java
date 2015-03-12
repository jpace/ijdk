package org.incava.ijdk.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator that executes <code>num</code> times. Similar to Ruby "10.times".
 */
public class NumIterator implements Iterator<Integer> {
    private final int limit;
    
    private int current;

    public NumIterator(int limit) {
        this.limit = limit;
        current = 0;
    }
    
    public boolean hasNext() {
        return current < limit;
    }

    public Integer next() {
        if (hasNext()) {
            int nxt = current;
            ++current;
            return nxt;
        }
        throw new NoSuchElementException("limit: " + limit);
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
