package org.incava.ijdk.collect;

import java.util.Iterator;
import java.util.Enumeration;

public class EnumerationIterable<T> implements Iterable<T> {
    private final Enumeration<T> en;
    
    public EnumerationIterable(Enumeration<T> en) {
        this.en = en;
    }
    
    public Iterator<T> iterator() {
        return new EnumerationIterator<T>(en);
    }
}
