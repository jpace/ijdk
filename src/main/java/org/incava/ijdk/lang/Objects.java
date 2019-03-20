package org.incava.ijdk.lang;

import java.util.Arrays;
import org.incava.ijdk.collect.Array;
import org.incava.ijdk.lang.Comp;
import org.incava.ijdk.lang.Obj;

/**
 * Extensions to the Object class.
 *
 * @see org.incava.ijdk.lang.Obj
 */
public class Objects {
    /**
     * Returns whether the objects are equal, including whether they are both null.
     *
     * @param x the first object to compare
     * @param y the second object to compare
     * @return whether the objects are equal or both are null
     */
    public static boolean areEqual(Object x, Object y) {
        return Obj.of(x).equals(y);
    }

    /**
     * Returns whether the objects are equal, including whether they are both null. This is the same
     * as <code>areEqual</code>, only more concise.
     *
     * @param x the first object to compare
     * @param y the second object to compare
     * @return whether the objects are equal or both are null
     * @see #areEqual
     */
    public static boolean equal(Object x, Object y) {
        return Obj.of(x).equals(y);
    }

    /**
     * Compares the two objects, including testing for null.
     *
     * @param <A> the type of objects; is Comparable
     * @param x the first object to compare
     * @param y the second object to compare
     * @return a negative, zero, or positive integer
     */
    public static<A extends Comparable<A>> int compare(A x, A y) {
        return Comp.compare(x, y);
    }

    /**
     * Returns whether the object is non-null and, if it is a collection or a string, has a length
     * greater than zero.
     *
     * @param obj the object
     * @return whether the object is not null and is true for that object type
     * @see #isEmpty
     * @see #isFalse
     * @see org.incava.ijdk.lang.Obj#isTrue
     */
    public static boolean isTrue(Object obj) {
        return Obj.of(obj).isTrue();
    }

    /**
     * Returns whether the object is null or is a string or collection of zero length.
     *
     * @param obj the object
     * @return whether the object is null or is false for that object type
     * @see #isEmpty
     * @see #isFalse
     * @see org.incava.ijdk.lang.Obj#isFalse
     */
    public static boolean isFalse(Object obj) {
        return Obj.of(obj).isFalse();
    }

    /**
     * Returns whether the object is null or is a string or collection of zero length.
     *
     * @param obj the object
     * @return whether the object is null or is empty for that object type
     * @see #isEmpty
     * @see #isFalse
     * @see org.incava.ijdk.lang.Obj#isEmpty
     */
    public static boolean isEmpty(Object obj) {
        return Obj.of(obj).isEmpty();
    }

    /**
     * Returns whether the object is null. This method provides an alternative syntax to "if (obj
     * == null)".
     *
     * @param obj the object
     * @return whether the object is null
     * @see #isNotNull
     */
    public static boolean isNull(Object obj) {
        return Obj.of(obj).isNull();
    }

    /**
     * Returns whether the object is not null. This method provides an alternative syntax to "if
     * (obj != null)".
     *
     * @param obj the object
     * @return whether the object is not null
     * @see #isNull
     */
    public static boolean isNotNull(Object obj) {
        return Obj.of(obj).isNotNull();
    }

    /**
     * Returns whether the second object is equal to the first object.
     *
     * @param x the first object to compare
     * @param y the second object to compare
     * @return whether the values are equal
     */
    public static boolean equals(HasInstanceValues x, HasInstanceValues y) {
        if (x == null) {
            return y == null;
        }
        else if (y == null) {
            return false;
        }
        else {
            return x.getInstanceValues().equals(y.getInstanceValues());
        }
    }
 
    /**
     * Returns the hash code of the given object. Returns 0 if the referenced object is null.
     *
     * @param x the object of which to calculate the hash code
     * @return the hash code
     */
    public static int hashCode(HasInstanceValues x) {
        return x == null ? 0 : x.getInstanceValues().hashCode();
    }

    /**
     * Returns a string, joining the values with commas.
     *
     * @param x the object to generate the string from
     * @return the string
     */
    public static String toString(HasInstanceValues x) {
        return toString(x, ", ");
    }

    /**
     * Returns a string, joining the values with the given string.
     *
     * @param x the object to generate the string from
     * @param delim the delimiter with which to join the string
     * @return the string
     */
    public static String toString(HasInstanceValues x, String delim) {
        return x == null ? null : x.getInstanceValues().join(delim);
    }
}
