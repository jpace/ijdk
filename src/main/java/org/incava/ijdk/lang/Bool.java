package org.incava.ijdk.lang;

import java.util.*;

/**
 * Has boolean characteristics, being true, false, or empty.
 */
public interface Bool {
    /**
     * Returns whether the object is true.
     *
     * @return whether the object is true
     * @see #isEmpty
     * @see #isFalse
     */
    public boolean isTrue();

    /**
     * Returns whether the object is not true. Generally <code>isTrue() == !isFalse()</code>.
     *
     * @return whether the object is not true
     * @see #isEmpty
     * @see #isTrue
     */
    public boolean isFalse();

    /**
     * Returns whether the object is empty, which may be the same as being false.
     *
     * @return whether the object is empty
     * @see #isEmpty
     * @see #isFalse
     */
    public boolean isEmpty();
}
