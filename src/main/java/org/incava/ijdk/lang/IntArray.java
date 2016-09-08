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

    public static String[] toStringArray(int[] ary) {
        String[] strs = new String[ary.length];
        for (int ai = 0; ai < ary.length; ++ai) {
            strs[ai] = String.valueOf(ary[ai]);
        }
        return strs;
    }

    public static boolean contains(int[] array, int value) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the maximum in the array, or Integer.MIN_VALUE if null.
     */
    public static int getMax(int[] ary) {
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
}
