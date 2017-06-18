package org.incava.ijdk.lang;

import java.util.Collection;
import java.util.Arrays;

/**
 * Extension to the Object class, wrapping a Java Object with additional methods. The referenced
 * object can be null.
 */
public class NullableObject<T> implements Bool {
    /**
     * A single variable representing all wrapped null objects.
     */
    public static NullableObject<Object> NULL = new NullableObject<Object>(null);

    /**
     * Creates a wrapper for the given object. If the object is null, then common variable
     * <code>NULL</code> is returned, thus eliminating unnecessary object creation.
     */
    @SuppressWarnings("unchecked")
    public static <T> NullableObject<T> of(T obj) {
        return obj == null ? (NullableObject<T>)NULL : new NullableObject<T>(obj);
    }
    
    /**
     * Returns whether the object is null. This method provides an alternative syntax to "if (obj ==
     * null)".
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

    private final T object;

    /**
     * Creates a wrapper for the given object.
     */
    public NullableObject(T object) {
        this.object = object;
    }

    /**
     * Returns the wrapped object.
     */
    public T get() {
        return this.object;
    }

    /**
     * Returns the wrapped object.
     */
    public T obj() {
        return this.object;
    }
    
    /**
     * Returns whether the other object is equal to the wrapped object, including whether they are
     * both null. If <code>other</code> is a <code>NullableObject</code>, then its wrapped object is
     * compared.
     *
     * @param other the object to compare to the wrapped object
     * @return whether the given object equals this one
     */
    public boolean equals(Object other) {
        if (isNull()) {
            if (other instanceof NullableObject) {
                return ((NullableObject)other).isNull();
            }
            else {
                return other == null;
            }
        }
        else if (other == null) {
            return false;
        }
        else if (this == other) {
            return true;
        }
        else if (other instanceof NullableObject) {
            Object oobj = ((NullableObject)other).obj();
            return obj().equals(oobj);
        }
        else {
            return obj().equals(other);
        }
    }

    /**
     * Returns the hash code of the wrapped object. Returns 0 if the referenced object is null.
     *
     * @return the hash code
     */
    public int hashCode() {
        return isNull() ? 0 : obj().hashCode();
    }

    /**
     * Returns whether the wrapped object is non-null and, if it is a collection or a string, has a
     * length greater than zero.
     *
     * @return whether the wrapped object is not null and is true for that object type
     * @see #isEmpty
     * @see #isFalse
     */
    public boolean isTrue() {
        return !isEmpty();
    }

    /**
     * Returns whether the wrapped object is null or is a string or collection of zero length.
     *
     * @return whether the wrapped object is null or is false for that object type
     * @see #isEmpty
     * @see #isTrue
     */
    public boolean isFalse() {
        return isEmpty();
    }

    /**
     * Returns whether the wrapped object is null or is a string or collection of zero length.
     *
     * @return whether the wrapped object is null or is empty for that object type
     * @see #isEmpty
     * @see #isFalse
     */
    public boolean isEmpty() {
        Object obj = obj();
        if (obj == null) {
            return true;
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
     * Returns whether the wrapped object is null. This method provides an alternative syntax to "if
     * (obj == null)".
     *
     * @return whether the wrapped object is null
     * @see #isNotNull
     */
    public boolean isNull() {
        return obj() == null;
    }

    /**
     * Returns whether the wrapped object is not null. This method provides an alternative syntax to
     * "if (obj != null)".
     *
     * @return whether the wrapped object is not null
     * @see #isNull
     */
    public boolean isNotNull() {
        return obj() != null;
    }

    /**
     * Returns the wrapped object as a string. C style arrays (e.g., Double[]) are run through the
     * toString for java.util.List, giving them better output, <code>"[abc, def, ghi]"</code>
     * instead of <code>"[Ljava.lang.Object;@15db9742"</code>.
     */
    public String toString() {
        Object obj = obj();
        if (obj == null) {
            return "null";
        }
        else if (obj instanceof Object[]) {
            return Arrays.asList((Object[])obj).toString();
        }
        else {
            return obj.toString();
        }
    }    
}
