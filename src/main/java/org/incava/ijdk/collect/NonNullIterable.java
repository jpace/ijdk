package org.incava.ijdk.collect;

import java.util.Iterator;

/**
 * An iterable for skipping null objects.
 *
 * @see NonNullIterator
 */
public class NonNullIterable<T> implements Iterable<T> {
    private final Iterable<T> iterable;
    
    public NonNullIterable(Iterable<T> iterable) {
        this.iterable = iterable;
    }
    
    public Iterator<T> iterator() {
        return new NonNullIterator<T>(iterable);
    }
}
