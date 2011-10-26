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
}
