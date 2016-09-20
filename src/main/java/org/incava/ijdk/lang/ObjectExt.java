package org.incava.ijdk.lang;

import java.util.Collection;

/**
 * Extensions to the Object class.
 */
public class ObjectExt {
    /**
     * Returns whether the objects are equal, including whether they are both null.
     */
    public static boolean areEqual(Object x, Object y) {
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
     * Returns whether the objects are equal, including whether they are both null. This is the same
     * as <code>areEqual</code>, only more concise.
     *
     * @see #areEqual
     */
    public static boolean equal(Object x, Object y) {
        return areEqual(x, y);
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

    /**
     * Returns whether the object is non-null and, if it is a collection or a string, has a length
     * greater than zero.
     *
     * @see #isEmpty
     * @see #isFalse
     */
    public static boolean isTrue(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * Returns whether the array is not null and is not of zero length.
     *
     * @see #isEmpty
     * @see #isFalse
     */
    public static boolean isTrue(Object ... obj) {
        return obj != null && obj.length > 0;
    }

    /**
     * Returns whether the object is null or is a string or collection of zero length.
     *
     * @see #isEmpty
     */
    public static boolean isFalse(Object obj) {
        return isEmpty(obj);
    }

    /**
     * Returns whether the array is not null and is not of zero length.
     *
     * @see #isEmpty
     */
    public static boolean isFalse(Object ... obj) {
        return obj == null || obj.length == 0;
    }

    /**
     * Returns whether the object is null or is a string or collection of zero length.
     *
     * @see #isEmpty
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        else if (obj instanceof String) {
            return StringExt.isEmpty((String)obj);
        }
        else if (obj instanceof Object[]) {
            return ((Object[])obj).length == 0;
        }
        else if (obj instanceof Collection) {
            return ((Collection<?>)obj).isEmpty();
        }
        else {
            return false;
        }
    }

    /**
     * Returns whether the object is null. This method provides an alternative syntax to "if (obj
     * == null)".
     *
     * @see #isNotNull
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * Returns whether the object is not null. This method provides an alternative syntax to "if
     * (obj != null)".
     *
     * @see #isNull
     */
    public static boolean isNotNull(Object obj) {
        return obj != null;
    }
}
