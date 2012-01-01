package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Range implements Comparable<Range>, Iterable<Integer> {
    private final Integer first;
    private final Integer last;

    class RangeIterator implements Iterator<Integer> {
        private Integer current;
        private final Integer last;
        
        public RangeIterator(Integer first, Integer last) {
            this.current = first;
            this.last = last;
        }

        public Integer next() {
            return this.current++;
        }

        public boolean hasNext() {
            return this.current.intValue() <= this.last.intValue();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        } 
    }
    
    public Range(Integer first, Integer last) {
        this.first = first;
        this.last = last;
    }

    public Iterator<Integer> iterator() {
        return new RangeIterator(this.first, this.last);
    }

    public Integer getFirst() {
        return first;
    }

    public Integer getLast() {
        return last;
    }

    public boolean includes(Integer n) {
        return n >= first && n <= last;
    }

    public Integer[] toArray() {
        return new Integer[] { first, last };
    }

    public Integer[] toExpandedArray() {
        List<Integer> list = new ArrayList<Integer>();
        for (Integer num = first; num <= last; ++num) {
            list.add(num);
        }
        return list.toArray(new Integer[list.size()]);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Range) {
            Range other = (Range)obj;
            return first == other.first && last == other.last;
        }
        else {
            return false;
        }
    }

    public String toString() {
        return "[" + first + " ... " + last + "]";
    }

    public int compareTo(Range other) {
        int cmp = ObjectExt.compare(first, other.first);
        if (cmp == 0) {
            cmp = ObjectExt.compare(last, other.last);
        }
        return cmp;
    }
}
