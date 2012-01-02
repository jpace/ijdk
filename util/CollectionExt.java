package org.incava.ijdk.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CollectionExt {
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
}
