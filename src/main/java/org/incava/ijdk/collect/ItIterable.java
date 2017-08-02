package org.incava.ijdk.collect;

import java.util.List;
import java.util.Iterator;

/**
 * An iterator that has a value and an index.
 *
 * @see org.incava.ijdk.collect.It
 */
public class ItIterable<T> implements Iterable<It<T>> {
    private final List<T> list;
    
    public ItIterable(List<T> list) {
        this.list = list;
    }

    public Iterator<It<T>> iterator() {
        return new ItIterator<T>(list);
    }    
}
