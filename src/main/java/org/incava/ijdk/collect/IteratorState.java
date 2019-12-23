package org.incava.ijdk.collect;

import java.util.Iterator;

public class IteratorState<E extends Object> implements Iterator<E> {
    private final Iterator<E> iterator;
    private Boolean isFirst;
    
    public IteratorState(Iterable<E> elements) {
        this(elements.iterator());
    }
    
    public IteratorState(Iterator<E> iter) {
        iterator = iter;
        isFirst = null;
    }

    public Boolean isFirst() {
        return Boolean.TRUE.equals(isFirst);
    }

    public Boolean isLast() {
        return !hasNext();
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public E next() {
        if (isFirst == null) {
            isFirst = true;
        }
        else if (isFirst) {
            isFirst = false;
        }
        return iterator.next();
    }
    
    public void remove() {
        this.iterator.remove();
    }
}
