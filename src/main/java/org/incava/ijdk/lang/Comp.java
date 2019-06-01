package org.incava.ijdk.lang;

/**
 * Extensions to the Comparable interface. This normalizes the return value to -1, 0, and 1, and has
 * the methods <code>lt</code>, <code>lte</code>, <code>gt</code>, <code>gte</code>, which parallel
 * the <code>equals</code> method. This is similar to Ruby, where defining the <code>&lt;=&gt;</code>
 * method (the "spaceship" method) results in the equivalent generation of <code>&lt;</code>,
 * <code>&lt;=</code>, etc.
 */
public class Comp {
    /**
     * Compares the two objects, including testing for null. Returns only -1, 0, or 1.
     *
     * @param <T> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return the comparison value, which is -1, 0, or 1
     */
    public static <T extends Comparable<T>> int compare(T x, T y) {
        Integer cmp = compareForNull(x, y);
        if (cmp == null) {
            cmp = x.compareTo(y);
        }
        return Integer.signum(cmp);
    }

    /**
     * Compares the given objects, using their <code>compareTo</code> method if they implement
     * <code>Comparable</code>. Returns -1 if the objects do not implement <code>Comparable</code>.
     *
     * @param <T> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return the comparison value, which is -1, 0, or 1
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> int compare(T x, T y) {
        Integer cmp = compareForNull(x, y);
        if (cmp == null) {
            if (x instanceof Comparable) {
                Comparable cx = (Comparable)x;
                Comparable cy = (Comparable)y;
                cmp = cx.compareTo(cy);
            }
            else {
                cmp = -1;
            }
        }
        return Integer.signum(cmp);
    }

    private static <T> Integer compareForNull(T x, T y) {
        if (x == y) {
            return 0;
        }
        else if (x == null) {
            return -1;
        }
        else if (y == null) {
            return 1;
        }
        else {
            return null;
        }
    }

    /**
     * Returns whether x is less than y.
     *
     * @param <T> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return whether x is less than y
     */
    public static <T extends Comparable<T>> boolean lessThan(T x, T y) {
        return compare(x, y) < 0;
    }

    /**
     * Returns whether x is less than y.
     *
     * @param <T> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return whether x is less than y
     */
    public static <T extends Comparable<T>> boolean lt(T x, T y) {
        return compare(x, y) < 0;
    }

    /**
     * Returns whether x is less than or equal to y.
     *
     * @param <T> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return whether x is less than or equal to y
     */
    public static <T extends Comparable<T>> boolean lte(T x, T y) {
        return compare(x, y) <= 0;
    }

    /**
     * Returns whether x is greater than y.
     *
     * @param <T> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return whether x is greater than y
     */
    public static <T extends Comparable<T>> boolean greaterThan(T x, T y) {
        return compare(x, y) > 0;
    }

    /**
     * Returns whether x is greater than y.
     *
     * @param <T> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return whether x is greater than y
     */
    public static <T extends Comparable<T>> boolean gt(T x, T y) {
        return compare(x, y) > 0;
    }

    /**
     * Returns whether x is greater than or equal to y.
     *
     * @param <T> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return whether x is greater than or equal to y
     */
    public static <T extends Comparable<T>> boolean gte(T x, T y) {
        return compare(x, y) >= 0;
    }
}
