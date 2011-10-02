package org.incava.ijdk.lang;


/**
 * Extensions to the Object class.
 */
public class ObjectExt {

    /**
     * Returns whether the objects are equal, including whether they are both null.
     */
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

    /**
     * Compares the two objects, including testing for null.
     */
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
