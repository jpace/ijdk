package org.incava.ijdk.lang;

import java.util.Arrays;
import org.incava.ijdk.collect.Array;

/**
 * An alternative basic object.
 *
 * @see org.incava.ijdk.lang.Obj
 */
public abstract class BasicObject implements HasInstanceValues {
    /**
     * Returns whether the second object is equal to the first object.
     *
     * @param other the object to compare to
     * @return whether the other object is equal to this one
     */
    public boolean equals(HasInstanceValues other) {
        return other != null && getInstanceValues().equals(other.getInstanceValues());
    }
    
    /**
     * Returns the hash code of this object.
     *
     * @return the hash code
     */
    public int hashCode() {
        return getInstanceValues().hashCode();
    }

    /**
     * Returns a string, joining the values with commas.
     *
     * @return the string
     */
    public String toString() {
        return toString(", ");
    }

    /**
     * Returns a string, joining the values with the given string.
     *
     * @param delim the delimiter with which to join the string
     * @return the string
     */
    public String toString(String delim) {
        return getInstanceValues().join(delim);
    }
}
