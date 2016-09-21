package org.incava.ijdk.lang;

/**
 * A range of integers, inclusive. Supports iteration and array expansion.
 *
 * This class has been moved to ijdk.lang.Range
 *
 * @see ijdk.lang.Range
 */
public class Range extends ijdk.lang.Range {
    /**
     * Creates a range of <code>first</code> through <code>last<code> (i.e., inclusive). First can
     * be less than, equal to, or greater than, last.
     */
    public Range(Integer first, Integer last) {
        super(first, last);
    }
}
