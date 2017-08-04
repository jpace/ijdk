package org.incava.ijdk.util;

import java.util.Iterator;

/**
 * An iterator that has nothing over which to iterate. For use by Iterate.over(), which handles null
 * objects by returning an empty iterator.
 */
public class EmptyIterator<E extends Object> implements Iterator<E> {
    public boolean hasNext() {
        return false;
    }

    public E next() {
        // cannot be called
        throw new UnsupportedOperationException();
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
