package org.incava.ijdk.collect;

import java.util.Iterator;
import org.incava.ijdk.util.EmptyIterator;

public class ItIterator<T> implements Iterator<It<T>> {
    private final Iterator<T> iterator;
    private int index;
        
    public ItIterator(Iterable<T> elements) {
        this.iterator = elements == null ? new EmptyIterator<T>(): elements.iterator();
        this.index = 0;
    }

    public int index() {
        return this.index;
    }
        
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public It<T> next() {
        T element = this.iterator.next();
        It<T> it = new It<T>(element, this.index);
        ++this.index;
        return it;
    }
    
    public void remove() {
        this.iterator.remove();
    }
}