package org.incava.ijdk.lang;


/**
 * Wraps boolean[] and Boolean[].
 */
public class BooleanArray {
    public static String[] toStringArray(boolean[] ary) {
        String[] strs = new String[ary.length];
        for (int ai = 0; ai < ary.length; ++ai) {
            strs[ai] = String.valueOf(ary[ai]);
        }
        return strs;
    }
}
