package org.incava.ijdk.lang;

/**
 * Utilities for <code>int[]</code>.
 */
public class IntArray extends IntegerArray {
    public int getInt(int index, int defValue) {
        Integer i = get(index);
        if (i == null) {
            return defValue;
        }
        else {
            return i.intValue();
        }
    }

    public int getInt(int index) {
        return getInt(index, 0);
    }

    /**
     * Returns an array of strings for the given int array, or an zero-length string array if the
     * int array is null or empty.
     *
     * @param ary the array to convert
     * @return an array of zero length or more, the int array converted to strings
     */
    public static String[] toStringArray(int[] ary) {
        if (ary == null || ary.length == 0) {
            return new String[0];
        }
        else {
            String[] strs = new String[ary.length];
            for (int idx = 0; idx < ary.length; ++idx) {
                strs[idx] = String.valueOf(ary[idx]);
            }
            return strs;
        }
    }

    /**
     * Returns whether the given value equals any element in the array.
     *
     * @param ary the array to search
     * @param value the value to match an element against
     * @return whether an element matched <code>value</code>
     */
    public static boolean contains(int[] ary, int value) {
        if (ary == null) {
            return false;
        }
        else {
            for (int val : ary) {
                if (val == value) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the maximum in the array, or Integer.MIN_VALUE if null.
     *
     * @param ary the array of which to find the maximum
     * @return the maximum of the array, or Integer.MIN_VALUE if null
     */
    public static int max(int[] ary) {
        int max = Integer.MIN_VALUE;
        if (ary != null) {
            for (int val : ary) {
                if (val > max) {
                    max = val;
                }
            }
        }
        return max;
    }

    /**
     * Returns the minimum in the array, or Integer.MAX_VALUE if null.
     *
     * @param ary the array of which to find the minimum
     * @return the minimum of the array, or Integer.MAX_VALUE if null
     */
    public static int min(int[] ary) {
        int min = Integer.MAX_VALUE;
        if (ary != null) {
            for (int val : ary) {
                if (val < min) {
                    min = val;
                }
            }
        }
        return min;
    }

    /**
     * Returns the sum (total) of array, or 0 if null.
     *
     * @param ary the array to sum
     * @return the sum of the array, or 0 if null
     */
    public static int sum(int[] ary) {
        int sum = 0;
        if (ary != null) {
            for (int val : ary) {
                sum += val;
            }
        }
        return sum;
    }

    /**
     * Returns the number of elements in the array, or 0 if null.
     *
     * @param ary the array
     * @return the length of the array, or 0 if null
     */
    public static int length(int[] ary) {
        return ary == null ? 0 : ary.length;
    }

    /**
     * Returns the average of the elements in array, or 0 if null or the number of elements is zero.
     */
    public static int average(int[] ary) {
        int len = length(ary);
        return len == 0 ? 0 : sum(ary) / len;
    }    
}
