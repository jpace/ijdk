package ijdk.lang;

import java.util.*;

/**
 * Extensions to the Comparable interface.
 */
public class Comp {
    /**
     * Compares the two objects, including testing for null.
     */
    public static <Type extends Comparable<Type>> int compare(Type x, Type y) {
        if (x == null) {
            return y == null ? 0 : -1;
        }
        else if (y == null) {
            return 1;
        }
        else {
            return x.compareTo(y);
        }
    }

    /**
     * Returns whether x is less than y.
     */
    public static <Type extends Comparable<Type>> boolean lessThan(Type x, Type y) {
        return compare(x, y) < 0;
    }

    /**
     * Returns whether x is greater than y.
     */
    public static <Type extends Comparable<Type>> boolean greaterThan(Type x, Type y) {
        return compare(x, y) > 0;
    }
}
