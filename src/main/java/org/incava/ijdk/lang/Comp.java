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
     * @param <Type> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return the comparison value
     */
    public static <Type extends Comparable<Type>> int compare(Type x, Type y) {
        if (x == null) {
            return y == null ? 0 : -1;
        }
        else if (y == null) {
            return 1;
        }
        else {
            int cmp = x.compareTo(y);
            return Integer.signum(cmp);
        }
    }    

    /**
     * Returns whether x is less than y.
     *
     * @param <Type> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return whether x is less than y
     */
    public static <Type extends Comparable<Type>> boolean lessThan(Type x, Type y) {
        return compare(x, y) < 0;
    }

    /**
     * Returns whether x is less than y.
     *
     * @param <Type> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return whether x is less than y
     */
    public static <Type extends Comparable<Type>> boolean lt(Type x, Type y) {
        return compare(x, y) < 0;
    }

    /**
     * Returns whether x is less than or equal to y.
     *
     * @param <Type> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return whether x is less than or equal to y
     */
    public static <Type extends Comparable<Type>> boolean lte(Type x, Type y) {
        return compare(x, y) <= 0;
    }

    /**
     * Returns whether x is greater than y.
     *
     * @param <Type> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return whether x is greater than y
     */
    public static <Type extends Comparable<Type>> boolean greaterThan(Type x, Type y) {
        return compare(x, y) > 0;
    }

    /**
     * Returns whether x is greater than y.
     *
     * @param <Type> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return whether x is greater than y
     */
    public static <Type extends Comparable<Type>> boolean gt(Type x, Type y) {
        return compare(x, y) > 0;
    }

    /**
     * Returns whether x is greater than or equal to y.
     *
     * @param <Type> the type, which is Comparable
     * @param x the first value
     * @param y the second value
     * @return whether x is greater than or equal to y
     */
    public static <Type extends Comparable<Type>> boolean gte(Type x, Type y) {
        return compare(x, y) >= 0;
    }
}
