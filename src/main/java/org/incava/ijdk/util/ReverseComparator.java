package org.incava.ijdk.util;

import java.util.Comparator;

/**
 * A comparator for reversed order.
 *
 * @param <T> a type that extends Comparable
 */
public class ReverseComparator<T extends Comparable<T>> implements Comparator<T> {
    /**
     * Compares o2 to o1. <code>o2</code> must implement Comparable.
     *
     * @param o1 the first object to compare
     * @param o2 the second object to compare
     * @return whether o2 &lt;=&gt; o1 (reverse)
     */
    public int compare(T o1, T o2) {
        return o2.compareTo(o1);
    }
    
    /**
     * Returns <code>o1.equals(o2)</code>.
     *
     * @param o1 the first object to compare
     * @param o2 the second object to compare
     * @return whether o1 == o2
     */
    public boolean equals(T o1, T o2) {
        return o1.equals(o2);
    }
}
