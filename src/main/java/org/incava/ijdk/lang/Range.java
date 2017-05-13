package org.incava.ijdk.lang;

import org.incava.ijdk.collect.Array;
import java.util.Iterator;

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
     * Creates a range of <code>first</code> through <code>last</code> (i.e., inclusive). First can
     * be less than, equal to, or greater than, last.
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
     *  for (Integer i : rg) { // block called with 11, 12, 13, 14
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
     * Returns whether the given number is within the range. Returns false if the first value is
     * greater than the last value in the range.
     */
    public boolean includes(Integer n) {
        return n >= first && n <= last;
    }

    /**
     * Returns the range as a list of elements for each number within the range. For example:
     *
     * <pre>
     *  Range rg = new Range(3, 7);
     *  List&lt;Integer&gt; ary = rg.toExpandedList(); // ary == [ 3, 4, 5, 6, 7 ]
     * </pre>
     */
    public Array<Integer> toArray() {
        Array<Integer> list = Array.<Integer>empty();
        for (Integer num = first; num <= last; ++num) {
            list.append(num);
        }
        return list;
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
     * Compares this range to the other. A range is less than another range if either the first or
     * last elements are less than the other's. A range is greater than another range if either the
     * first or last element are greater than the other's.
     */
    public int compareTo(Range other) {
        if (other == null) {
            return 1;
        }
        int cmp = ObjectExt.compare(first, other.first);
        if (cmp == 0) {
            cmp = ObjectExt.compare(last, other.last);
        }
        return cmp;
    }
}
