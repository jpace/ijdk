package org.incava.ijdk.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.incava.ijdk.lang.Objects;

public class Collections extends Objects {
    /**
     * Creates a string from the collection, with each element separated by the delimiter.
     *
     * @param <Type> the element type
     * @param coll the collection
     * @param delimiter the delimiter
     * @return the joined string
     */
    public static <Type> String join(Collection<Type> coll, String delimiter) {
        if (coll == null) {
            return null;
        }
        StringBuilder sb = null;
        for (Object obj : coll) {
            if (sb == null) {
                sb = new StringBuilder();
            }
            else if (delimiter != null) {
                sb.append(delimiter);
            }
            sb.append(String.valueOf(obj));
        }
        return sb == null ? "" : sb.toString();
    }

    /**
     * Returns a set of the elements in common.
     *
     * @param <Type> the element type
     * @param a the first collection
     * @param b the first collection
     * @return a set of the common elements
     */
    public static <Type> Set<Type> intersection(Collection<Type> a, Collection<Type> b) {
        Set<Type> intSet = new HashSet<Type>(a);
        intSet.retainAll(b);
        return intSet;
    }

    /**
     * Returns a set of the elements in common.
     *
     * @param <Type> the element type
     * @param colls the collections to take the intersection of
     * @return a set of the common elements
     */
    @SafeVarargs
    public static <Type> Set<Type> intersection(Collection<Type> ... colls) {
        Set<Type> intSet = null;

        for (Collection<Type> coll : colls) {
            if (intSet == null) {
                intSet = new HashSet<Type>(coll);
            }
            else {
                intSet.retainAll(coll);
            }
        }
        return intSet;
    }

    /**
     * Returns whether the collection (if not null) contains the given value.
     * Returns false if <code>coll</code> is null.
     *
     * @param <Type> the element type
     * @param coll the collection
     * @param val the value to search for
     * @return whether the element is in the collection
     */
    public static <Type> boolean contains(Collection<Type> coll, Type val) {
        return coll != null && coll.contains(val);
    }

    /**
     * Returns whether all elements in <code>tgt</code> are in <code>src</code>.
     * If <code>src</code> or <code>tgt</code> is null, false is returned.
     *
     * @param <Type> the element type
     * @param src the first (source) collection
     * @param tgt the target (sought) collection
     * @return whether <code>tgt</code> is in <code>src</code>
     */
    public static <Type> boolean hasAll(Collection<Type> src, Collection<Type> tgt) {
        return checkHas(src, tgt, true);
    }

    /**
     * Returns whether any element in <code>tgt</code> is in <code>src</code>.
     * If <code>src</code> or <code>tgt</code> is null, false is returned.
     *
     * @param <Type> the element type
     * @param src the first (source) collection
     * @param tgt the target (sought) collection
     * @return whether any element in <code>tgt</code> is in <code>src</code>
     */
    public static <Type> boolean hasAny(Collection<Type> src, Collection<Type> tgt) {
        return checkHas(src, tgt, false);
    }

    /**
     * Returns whether there are any elements in <code>coll</code>, which can be null.
     *
     * @param <Type> the element type
     * @param coll the collection
     * @return whether any element in is in <code>coll</code>
     */
    public static <Type> boolean any(Collection<Type> coll) {
        return coll != null && !coll.isEmpty();
    }

    private static <Type> boolean checkHas(Collection<Type> src, Collection<Type> tgt, boolean forAll) {
        if (src == null || tgt == null) {
            return false;
        }

        for (Type elmt : tgt) {
            if (forAll != src.contains(elmt)) {
                return !forAll;
            }
        }

        return forAll;
    }
}
