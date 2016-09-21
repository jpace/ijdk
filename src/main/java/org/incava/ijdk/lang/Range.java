package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A range of integers, inclusive. Supports iteration and array expansion.
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
