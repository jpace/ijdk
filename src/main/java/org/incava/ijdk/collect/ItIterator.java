package org.incava.ijdk.collect;

import java.util.List;
import java.util.Iterator;

public class ItIterator<T> implements Iterator<It<T>> {
    private final List<T> list;
    private int index;
        
    public ItIterator(List<T> list) {
        this.list = list;
        this.index = 0;
    }

    public int index() {
        return this.index;
    }
        
    public boolean hasNext() {
        return this.list != null && this.index < list.size();
    }

    public It<T> next() {
        It<T> it = new It<T>(this.list.get(this.index), this.index);
        ++this.index;
        return it;
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
