package org.incava.ijdk.lang;

import ijdk.lang.Comparablee;
import ijdk.lang.Objectt;

/**
 * Extensions to the Object class.
 */
public class ObjectExt {
    /**
     * Returns whether the objects are equal, including whether they are both null.
     */
    public static boolean areEqual(Object x, Object y) {
        return new Objectt(x).equals(y);
    }

    /**
     * Returns whether the objects are equal, including whether they are both null. This is the same
     * as <code>areEqual</code>, only more concise.
     *
     * @see #areEqual
     */
    public static boolean equal(Object x, Object y) {
        return new Objectt(x).equals(y);
    }

    /**
     * Compares the two objects, including testing for null.
     */
    public static<A extends Comparable<A>> int compare(A x, A y) {
        return Comparablee.compare(x, y);
    }

    /**
     * Returns whether the object is non-null and, if it is a collection or a string, has a length
     * greater than zero.
     *
     * @see #isEmpty
     * @see #isFalse
     */
    public static boolean isTrue(Object obj) {
        return new Objectt(obj).isTrue();
    }

    /**
     * Returns whether the object is null or is a string or collection of zero length.
     *
     * @see #isEmpty
     */
    public static boolean isFalse(Object obj) {
        return new Objectt(obj).isFalse();
    }

    /**
     * Returns whether the object is null or is a string or collection of zero length.
     *
     * @see #isEmpty
     */
    public static boolean isEmpty(Object obj) {
        return new Objectt(obj).isEmpty();
    }

    /**
     * Returns whether the object is null. This method provides an alternative syntax to "if (obj
     * == null)".
     *
     * @see #isNotNull
     */
    public static boolean isNull(Object obj) {
        return new Objectt(obj).isNull();
    }

    /**
     * Returns whether the object is not null. This method provides an alternative syntax to "if
     * (obj != null)".
     *
     * @see #isNull
     */
    public static boolean isNotNull(Object obj) {
        return new Objectt(obj).isNotNull();
    }
}
