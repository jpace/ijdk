package org.incava.ijdk.collect;

import java.util.Iterator;

/**
 * An iterable over integers
 *
 * @see IntegerIterator
 */
public class IntegerIterable implements Iterable<Integer> {
    private final Integer from;
    private final Integer to;
    private final Integer step;
    
    public IntegerIterable(Integer from, Integer to) {
        this(from, to, 1);
    }
    
    public IntegerIterable(Integer from, Integer to, Integer step) {
        this.from = from;
        this.to = to;
        this.step = step;
    }
    
    public Iterator<Integer> iterator() {
        return new IntegerIterator(from, to, step);
    }
}
