package org.incava.ijdk.util;

import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationIterator<T> implements Iterator<T> {
    private final Enumeration<T> en;
    
    public EnumerationIterator(Enumeration<T> en) {
        this.en = en;
    }

    public boolean hasNext() {
        return this.en.hasMoreElements();
    }

    public T next() {
        return this.en.nextElement();
    }
    
    public void remove() {
        throw new RuntimeException("remove is not supported for Enumerations");
    }
}
