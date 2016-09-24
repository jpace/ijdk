package ijdk.lang;

import java.util.*;

/**
 * Extensions to the Comparable interface.
 */
public class Comp {
    /**
     * Compares the two objects, including testing for null.
     */
    public static<Type extends Comparable<Type>> int compare(Type x, Type y) {
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
}
