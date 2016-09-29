package org.incava.ijdk.lang;

/**
 * Math extensions.
 */
public class MathExt {
    /**
     * Returns the minimum value in the array, which can be empty or null. Returns Integer.MIN_VALUE
     * if not found.
     * 
     * @param ary the array of integers
     * @return the maximum value, or Integer.MIN_VALUE if the array is empty
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
     * 
     * @param value the value to compare
     * @param lowerBound the lower boundary, inclusive
     * @param upperBound the upper boundary, inclusive
     * @return the maximum value, or Integer.MIN_VALUE if the array is empty
     */
    public static boolean isWithin(int value, int lowerBound, int upperBound) {
        return value >= lowerBound && value <= upperBound;
    }
}
