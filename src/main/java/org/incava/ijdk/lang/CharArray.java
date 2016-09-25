package org.incava.ijdk.lang;

public class CharArray {
    public static String[] toStringArray(char[] ary) {
        String[] strs = new String[ary.length];
        for (int ai = 0; ai < ary.length; ++ai) {
            strs[ai] = String.valueOf(ary[ai]);
        }
        return strs;
    }
}
