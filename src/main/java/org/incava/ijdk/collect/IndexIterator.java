package org.incava.ijdk.collect;

import java.util.Iterator;
import org.incava.ijdk.lang.KeyValue;

public class IndexIterator<T> implements Iterator<KeyValue<Integer, T>> {
    private final Iterator<T> iterator;
    private int index;
        
    public IndexIterator(Iterable<T> elements) {
        this.iterator = elements == null ? new EmptyIterator<T>(): elements.iterator();
        this.index = 0;
    }

    public int index() {
        return this.index;
    }
        
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public KeyValue<Integer, T> next() {
        return new KeyValue<Integer, T>(this.index++, this.iterator.next());
    }
    
    public void remove() {
        this.iterator.remove();
    }
}
