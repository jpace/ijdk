package org.incava.ijdk.lang;

import java.util.Arrays;

public class StringArray {
    /**
     * Returns a string of the form "[foo, bar]", or "[]" if the array is null or empty.
     */
    public static String toString(String[] ary) {
        return isEmpty(ary) ? "[]" : Arrays.asList(ary).toString();
    }

    /**
     * Returns whether the array is null or empty.
     */
    public static boolean isEmpty(String[] ary) {
        return ary == null || ary.length == 0;
    }

    /**
     * Returns whether the two arrays are equal in content or both are empty.
     *
     * @see #isEmpty
     */
    public static boolean areEqual(String[] x, String[] y) {
        if (isEmpty(x)) {
            return isEmpty(y);
        }
        else if (isEmpty(y)) {
            return false;
        }
        else if (x.length != y.length) {
            return false;
        }
        else {
            for (int idx = 0; idx < x.length; ++idx) {
                if (!ObjectExt.areEqual(x[idx], y[idx])) {
                    return false;
                }
            }
            return true;
        }
    }
}
