package org.incava.ijdk.lang;


public class MathExt {

    public static int min(int ... ary) {
        int m = ary[0];
        for (int el : ary) {
            if (el < m) {
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
