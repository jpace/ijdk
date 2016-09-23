package org.incava.ijdk.lang;

import ijdk.lang.StringAry;
import java.util.Arrays;

public class StringArray {
    /**
     * Returns a string of the form "[foo, bar]", or "[]" if the array is null or empty.
     */
    public static String toString(String[] ary) {
        return new StringAry(ary).toString();
    }

    /**
     * Returns whether the array is null or empty.
     */
    public static boolean isEmpty(String[] ary) {
        return new StringAry(ary).isEmpty();
    }

    /**
     * Returns whether the two arrays are equal in content or both are empty.
     *
     * @see #isEmpty
     */
    public static boolean areEqual(String[] x, String[] y) {
        return new StringAry(x).equals(y);
    }
}
