package org.incava.ijdk.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator that yields only non-null elements.
 */
public class NonNullIterator<T extends Object> implements Iterator<T> {
    private final Iterator<T> iterator;
    private T current;

    public NonNullIterator(Iterable<T> iterable) {
        this.iterator = iterable.iterator();
        gotoNext();
    }
    
    public boolean hasNext() {
        return this.current != null;
    }

    public T next() {
        if (this.current == null) {
            throw new NoSuchElementException();
        }
        else {
            T nextObj = this.current;
            gotoNext();
            return nextObj;
        }
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private void gotoNext() {
        while (this.iterator.hasNext()) {
            T obj = this.iterator.next();
            if (obj != null) {
                this.current = obj;
                return;
            }
        }
        this.current = null;
    }
}
