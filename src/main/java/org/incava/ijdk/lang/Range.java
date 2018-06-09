package org.incava.ijdk.lang;

import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.IntegerIterable;
import org.incava.ijdk.collect.IntegerIterator;
import java.util.Iterator;

/**
 * A range of integers, inclusive. Supports iteration and array expansion.
 */
public class Range implements Comparable<Range>, Iterable<Integer>, HasInstanceValues {
    private final Integer first;
    private final Integer last;

    /**
     * Creates a range of <code>first</code> through <code>last</code> (i.e., inclusive). First can
     * be less than, equal to, or greater than, last.
     *
     * @param first the first element in the range
     * @param last the last element in the range
     */
    public Range(Integer first, Integer last) {
        this.first = first;
        this.last = last;
    }

    /**
     * Returns an iterator for the range, inclusive of both <code>first</code> and
     * <code>last</code>. Usage:
     *
     * <pre>
     *  Range rg = new Range(11, 14);
     *  for (Integer i : rg) { // block called with 11, 12, 13, 14
     *  }
     * </pre>
     *
     * A range is iterated over only if <code>first</code> &lt;= <code>last</code>.
     *
     * @return an iterator of [from .. to]
     * @see #upTo
     */
    public Iterator<Integer> iterator() {
        return new IntegerIterator(this.first, this.last);
    }

    /**
     * Returns an iterator for the range, inclusive of the first value, but <em>exclusive</em> of
     * the last one. Will not execute if <code>first</code> &lt;= <code>last - 1</code>. Usage:
     *
     * <pre>
     *  Range rg = new Range(11, 14);
     *  for (Integer i : rg.upTo()) {
     *      // block called with 11, 12, 13
     *  }
     * </pre>
     *
     * A range is iterated over only if <code>first</code> &lt;= <code>last</code>.
     *
     * @return an iterable of [first ... last]
     */
    public Iterable<Integer> upTo() {
        return new IntegerIterable(this.first, this.last - 1);
    }

    /**
     * Returns the first number of the range.
     *
     * @return the first number
     */
    public Integer getFirst() {
        return first;
    }

    /**
     * Returns the first number of the range.
     *
     * @return the first number
     */
    public Integer first() {
        return first;
    }

    /**
     * Returns the last number of the range.
     *
     * @return the last number
     */
    public Integer getLast() {
        return last;
    }

    /**
     * Returns the last number of the range.
     *
     * @return the last number
     */
    public Integer last() {
        return last;
    }

    /**
     * Returns whether the given number is within the range, or is either the first or last value.
     *
     * @param n the number to check
     * @return whether last &gt;= n &gt;= first
     */
    public boolean includes(Integer n) {
        return n != null && (n == first || n == last || (n > first && n < last));
    }

    /**
     * Returns the range as a list of elements for each number within the range. For example:
     *
     * <pre>
     *  Range rg = new Range(3, 7);
     *  List&lt;Integer&gt; ary = rg.toExpandedList(); // ary == [ 3, 4, 5, 6, 7 ]
     * </pre>
     *
     * @return an array of integers
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
     *
     * @return the comparison value
     */
    public boolean equals(Object obj) {
        if (obj instanceof Range) {
            Range other = (Range)obj;
            return Objects.equals(this, other);
        }
        else {
            return false;
        }
    }

    /**
     * Returns the hashCode for this range.
     *
     * @return the hash code
     */
    public int hashCode() {
        return Objects.hashCode(this);
    }

    /**
     * Returns the range as a string.
     *
     * @return a string
     */
    public String toString() {
        return "[" + first + " .. " + last + "]";
    }

    /**
     * Compares this range to the other. A range is less than another range if either the first or
     * last elements are less than the other's. A range is greater than another range if either the
     * first or last element are greater than the other's.
     *
     * @return the comparison value
     */
    public int compareTo(Range other) {
        if (other == null) {
            return 1;
        }
        else if (this == other) {
            return 0;
        }
        else {
            int cmp = Comp.compare(first, other.first);
            if (cmp == 0) {
                cmp = Comp.compare(last, other.last);
            }
            return cmp;
        }
    }

    public Array<Object> getInstanceValues() {
        return Array.of(first, last);
    }
}
