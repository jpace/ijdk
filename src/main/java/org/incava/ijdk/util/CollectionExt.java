package org.incava.ijdk.util;

import java.util.Collection;
import java.util.Set;
import org.incava.ijdk.lang.ObjectExt;

/**
 * @deprecated use Collections instead
 */
@Deprecated
public class CollectionExt extends ObjectExt {
    public static <Type> Set<Type> intersection(Collection<Type> a, Collection<Type> b) {
        return Collections.intersection(a, b);
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <Type> Set<Type> intersection(Collection<Type> ... colls) {
        return Collections.intersection(colls);
    }

    public static <Type> boolean contains(Collection<Type> coll, Type val) {
        return Collections.contains(coll, val);
    }

    public static <Type> boolean hasAll(Collection<Type> src, Collection<Type> tgt) {
        return Collections.hasAll(src, tgt);
    }

    public static <Type> boolean hasAny(Collection<Type> src, Collection<Type> tgt) {
        return Collections.hasAny(src, tgt);
    }

    public static <Type> boolean any(Collection<Type> coll) {
        return Collections.any(coll);
    }
}
