package org.incava.ijdk.lang;


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
}
