package org.incava.ijdk.collect;

import java.util.Iterator;

/**
 * An iterable that has nothing over which to iterate. For use by IUtil.iter(),
 * which handles null objects by returning an empty iterator.
 *
 * @see EmptyIterator
 */
public class EmptyIterable<E> implements Iterable<E> {
    public Iterator<E> iterator() {
        return new EmptyIterator<E>();
    }
}
