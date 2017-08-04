package org.incava.ijdk.collect;

import java.util.List;
import java.util.Iterator;

/**
 * An iterator that has a value and an index.
 *
 * @see org.incava.ijdk.collect.It
 */
public class ItIterable<T> implements Iterable<It<T>> {
    private final Iterable<T> elements;
    
    public ItIterable(Iterable<T> elements) {
        this.elements = elements;
    }

    public Iterator<It<T>> iterator() {
        return new ItIterator<T>(elements);
    }    
}
