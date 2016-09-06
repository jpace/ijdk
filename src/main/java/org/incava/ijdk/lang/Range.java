package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A range of integers, inclusive. Supports iteration and array expansion.
 */
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
    
    /**
     * Creates a range of <code>first</code> through <code>last<code> (i.e., inclusive).
     */
    public Range(Integer first, Integer last) {
        this.first = first;
        this.last = last;
    }

    /**
     * Returns an iterator for the range. Usage:
     *
     * <pre>
     *  Range rg = new Range(11, 14);
     *  for (Integer i : rg) {
     *  }
     * </pre>
     */
    public Iterator<Integer> iterator() {
        return new RangeIterator(this.first, this.last);
    }

    /*
     * Returns the first number of the range.
     */
    public Integer getFirst() {
        return first;
    }

    /**
     * Returns the last number of the range.
     */
    public Integer getLast() {
        return last;
    }

    /**
     * Returns whether the given number is within the range.
     */
    public boolean includes(Integer n) {
        return n >= first && n <= last;
    }

    /**
     * Returns the range as an array. For example:
     *
     * <pre>
     *  Range rg = new Range(11, 14);
     *  Integer[] ary = rg.toArray();
     *  // ary == new Integer[] { 11, 14 }
     * </pre>
     */
    public Integer[] toArray() {
        return new Integer[] { first, last };
    }

    /**
     * Returns the range as an expanded array, with elements for each number
     * within the range. For example:
     *
     * <pre>
     *  Range rg = new Range(7, 14);
     *  Integer[] ary = rg.toExpandedArray();
     *  // ary == new Integer[] { 7, 8, 9, 10, 11, 12, 13, 14 }
     * </pre>
     */
    public Integer[] toExpandedArray() {
        List<Integer> list = new ArrayList<Integer>();
        for (Integer num = first; num <= last; ++num) {
            list.add(num);
        }
        return list.toArray(new Integer[list.size()]);
    }

    /**
     * Returns whether this range equals the other.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Range) {
            Range other = (Range)obj;
            return first == other.first && last == other.last;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the hashCode for this range. Thus ranges can be used as keys in a
     * HashMap.
     */
    public int hashCode() {
        return first.hashCode() * 17 + last.hashCode();
    }

    /**
     * Returns the range as a string.
     */
    public String toString() {
        return "[" + first + " .. " + last + "]";
    }

    /**
     * Compares this range to the other. A range is less than another range if
     * either the first or last elements are less than the other's. A range is
     * greater than another range if either the first or last element are
     * greater than the other's.
     */
    public int compareTo(Range other) {
        int cmp = ObjectExt.compare(first, other.first);
        if (cmp == 0) {
            cmp = ObjectExt.compare(last, other.last);
        }
        return cmp;
    }
}