package org.incava.ijdk.lang;

/**
 * Math extensions.
 */
public class MathExt {
    /**
     * Returns the minimum value in the array, which can be empty or null. Returns Integer.MIN_VALUE
     * if not found.
     */
    public static int min(int ... ary) {
        int m = Integer.MIN_VALUE;
        if (ary == null) {
            return m;
        }
        for (int el : ary) {
            if (m == Integer.MIN_VALUE || el < m) {
                m = el;
            }
        }
        return m;
    }

    /**
     * Returns whether the value is between the lower and upper bound, inclusive.
     */
    public static boolean isWithin(int val, int lowerBound, int upperBound) {
        return val >= lowerBound && val <= upperBound;
    }
}
