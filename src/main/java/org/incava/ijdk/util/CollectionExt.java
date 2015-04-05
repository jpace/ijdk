package org.incava.ijdk.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.incava.ijdk.lang.ObjectExt;

public class CollectionExt extends ObjectExt {
    /**
     * Returns a list of the elements in common.
     */
    public static <Type> Set<Type> intersection(Collection<Type> a, Collection<Type> b) {
        Set<Type> intSet = new HashSet<Type>(a);
        intSet.retainAll(b);        
        return intSet;
    }

    /**
     * Returns a list of the elements in common.
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
     */
    public static <Type> boolean contains(Collection<Type> coll, Type val) {
        return coll != null && coll.contains(val);
    }

    /**
     * Returns whether all elements in <code>tgt</code> are in <code>src</code>.
     * If <code>src</code> or <code>tgt</code> is null, false is returned.
     */
    public static <Type> boolean hasAll(Collection<Type> src, Collection<Type> tgt) {
        return checkHas(src, tgt, true);
    }   

    /**
     * Returns whether any elements in <code>tgt</code> is in <code>src</code>.
     * If <code>src</code> or <code>tgt</code> is null, false is returned.
     */
    public static <Type> boolean hasAny(Collection<Type> src, Collection<Type> tgt) {
        return checkHas(src, tgt, false);
    }

    /**
     * Returns whether there are any elements in <code>coll</code>, which can be null.
     */
    public static <Type> boolean any(Collection<Type> coll) {
        return coll != null && !coll.isEmpty();
    }

    /**
     * Returns whether the elements in <code>tgt</code> are in <code>src</code>.
     * If <code>src</code> or <code>tgt</code> is null, false is returned. If
     * <code>forAll</code> is true, then this method returns true if all
     * elements from <code>tgt</code> are in <code>src</code>. If
     * <code>forAll</code> is false, then true is returned if any element in
     * <code>tgt</code> is in <code>src</code>.
     */
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
