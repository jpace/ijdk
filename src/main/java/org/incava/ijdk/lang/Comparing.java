package org.incava.ijdk.lang;

/**
 * Extensions to the Comparable interface. This normalizes the return value to -1, 0, and 1, and
 * adds the methods <code>lt</code>, <code>lte</code>, <code>gt</code>, <code>gte</code>, which
 * parallel the <code>equals</code> method. This is similar to Ruby, where defining the
 * <code>&lt;=&gt;</code> method (the "spaceship" method) results in the equivalent generation of
 * <code>&lt;</code>, <code>&lt;=</code>, etc.
 *
 * @param <T> the type
 */
public interface Comparing<T> extends Comparable<T> {
    /**
     * Compares the two objects, including testing for null.
     *
     * @param other the other value
     * @return less than zero, zero, or greater than zero
     */
    int compareTo(T other);
    
    /**
     * Returns whether x is less than y.
     *
     * @param other the other value
     * @return the comparison value
     */
    boolean lt(T other);

    /**
     * Returns whether x is less than or equal to y.
     *
     * @param other the other value
     * @return the comparison value
     */
    boolean lte(T other);
    
    /**
     * Returns whether x is greater than y.
     *
     * @param other the other value
     * @return the comparison value
     */
    boolean gt(T other);

    /**
     * Returns whether x is greater than or equal to y.
     *
     * @param other the other value
     * @return the comparison value
     */
    boolean gte(T other);
}
