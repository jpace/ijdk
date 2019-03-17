package org.incava.ijdk.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.incava.ijdk.collect.Array;

/**
 * An iterator from one number to another, inclusive, using a step, which defaults to 1.
 */
public class IntegerIterator implements Iterator<Integer> {
    private Integer current;
    private final Integer last;
    private final Integer step;
        
    public IntegerIterator(Integer first, Integer last) {
        this(first, last, 1);
    }

    public IntegerIterator(Integer first, Integer last, Integer step) {
        this.current = first;
        this.last = last;
        this.step = step;
    }

    public Integer next() {
        if (hasNext()) {
            Integer value = this.current;
            this.current += step;
            return value;
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public boolean hasNext() {
        return this.current.intValue() <= this.last.intValue();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
