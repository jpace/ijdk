package org.incava.ijdk.lang;

/**
 * Extensions to the Comparable interface.
 */
public class Comp {
    /**
     * Compares the two objects, including testing for null. Returns only -1, 0, or 1.
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
            return cmp == 0 ? 0 : (cmp < 0 ? -1 : 1);
        }
    }    

    /**
     * Returns whether x is less than y.
     */
    public static <Type extends Comparable<Type>> boolean lessThan(Type x, Type y) {
        return compare(x, y) < 0;
    }

    /**
     * Returns whether x is less than y.
     */
    public static <Type extends Comparable<Type>> boolean lt(Type x, Type y) {
        return compare(x, y) < 0;
    }

    /**
     * Returns whether x is less than or equal to y.
     */
    public static <Type extends Comparable<Type>> boolean lte(Type x, Type y) {
        return compare(x, y) <= 0;
    }

    /**
     * Returns whether x is greater than y.
     */
    public static <Type extends Comparable<Type>> boolean greaterThan(Type x, Type y) {
        return compare(x, y) > 0;
    }

    /**
     * Returns whether x is greater than y.
     */
    public static <Type extends Comparable<Type>> boolean gt(Type x, Type y) {
        return compare(x, y) > 0;
    }

    /**
     * Returns whether x is greater than or equal to y.
     */
    public static <Type extends Comparable<Type>> boolean gte(Type x, Type y) {
        return compare(x, y) >= 0;
    }
}
