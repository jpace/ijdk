package org.incava.ijdk.lang;

public class ShortArray {
    public static String[] toStringArray(short[] ary) {
        String[] strs = new String[ary.length];
        for (int ai = 0; ai < ary.length; ++ai) {
            strs[ai] = String.valueOf(ary[ai]);
        }
        return strs;
    }
}
