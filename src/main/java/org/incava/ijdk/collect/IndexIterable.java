package org.incava.ijdk.collect;

import java.util.Iterator;
import org.incava.ijdk.lang.KeyValue;

/**
 * An iterator that has a value and an index (key).
 *
 * @see org.incava.ijdk.lang.KeyValue
 */
public class IndexIterable<T> implements Iterable<KeyValue<Integer, T>> {
    private final Iterable<T> elements;
    
    public IndexIterable(Iterable<T> elements) {
        this.elements = elements;
    }

    public Iterator<KeyValue<Integer, T>> iterator() {
        return new IndexIterator<T>(elements);
    }    
}
