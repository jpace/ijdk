package org.incava.ijdk.lang;


/**
 * Extensions to the Object class.
 */
public class ObjectExt {

    public static boolean equal(Object x, Object y) {
        if (x == null) {
            return y == null;
        }
        else if (y == null) {
            return false;
        }
        else {
            return x.equals(y);
        }
    }

    public static<A extends Comparable<A>> int compare(A x, A y) {
        if (x == null) {
            return y == null ? 0 : -1;
        }
        else if (y == null) {
            return 1;
        }
        else {
            return x.compareTo(y);
        }
    }

}
