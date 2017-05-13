package org.incava.ijdk.lang;

public class StringArray {
    /**
     * Returns a string of the form "[foo, bar]", or "[]" if the array is null or empty.
     */
    public static String toString(String[] ary) {
        return new org.incava.ijdk.collect.StringArray(ary).toString();
    }

    /**
     * Returns whether the array is null or empty.
     */
    public static boolean isEmpty(String[] ary) {
        return new org.incava.ijdk.collect.StringArray(ary).isEmpty();
    }

    /**
     * Returns whether the two arrays are equal in content or both are empty.
     *
     * @see #isEmpty
     */
    public static boolean areEqual(String[] x, String[] y) {
        return new org.incava.ijdk.collect.StringArray(x).equals(y);
    }
}
