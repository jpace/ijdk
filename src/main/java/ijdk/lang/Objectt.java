package ijdk.lang;

import java.util.Collection;

/**
 * Extensions to the Object class, wrapping a Java Object with additional methods.
 */
public class Objectt {        
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

    private final Object object;

    public Objectt(Object object) {
        this.object = object;
    }

    /**
     * Returns the wrapped object.
     */
    public Object getObject() {
        return this.object;
    }

    /**
     * Returns the wrapped object.
     */
    public Object obj() {
        return this.object;
    }
    
    /**
     * Returns whether the other object is equal to the wrapped object, including whether they are
     * both null.
     */
    public boolean equals(Object other) {
        if (isNull()) {
            return other == null;
        }
        else if (other == null) {
            return false;
        }
        else {
            return obj().equals(other);
        }
    }

    /**
     * Returns the hash code for the wrapped object.
     */
    public int hashCode() {
        return isNull() ? 0 : obj().hashCode();
    }

    /**
     * Returns whether the wrapped object is non-null and, if it is a collection or a string, has a
     * length greater than zero.
     *
     * @see #isEmpty
     * @see #isFalse
     */
    public boolean isTrue() {
        return !isEmpty();
    }

    /**
     * Returns whether the wrapped object is null or is a string or collection of zero length.
     *
     * @see #isEmpty
     */
    public boolean isFalse() {
        return isEmpty();
    }

    /**
     * Returns whether the wrapped object is null or is a string or collection of zero length.
     *
     * @see #isEmpty
     */
    public boolean isEmpty() {
        if (isNull()) {
            return true;
        }
        else if (obj() instanceof String) {
            return new Stringg((String)obj()).isEmpty();
        }
        else if (obj() instanceof Object[]) {
            return ((Object[])obj()).length == 0;
        }
        else if (obj() instanceof Collection) {
            return ((Collection<?>)obj()).isEmpty();
        }
        else {
            return false;
        }
    }
    
    /**
     * Returns whether the wrapped object is null. This method provides an alternative syntax to "if
     * (obj == null)".
     *
     * @see #isNotNull
     */
    public boolean isNull() {
        return obj() == null;
    }

    /**
     * Returns whether the wrapped object is not null. This method provides an alternative syntax to
     * "if (obj != null)".
     *
     * @see #isNull
     */
    public boolean isNotNull() {
        return obj() != null;
    }
}
