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
     * Creates a range of <code>first</code> through <code>last</code> (i.e., inclusive).
     * <code>first</code> can be less than, equal to, or greater than, <code>last</code>, although
     * iteration will work only when <code>first</code> is less than or equal to <code>last</code>.
     */
    public Range(Integer first, Integer last) {
        super(first, last);
    }
}
