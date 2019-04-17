package org.incava.ijdk.lang;

import java.util.Arrays;
import java.util.Collection;
import org.incava.ijdk.util.Collections;

/**
 * Contains various means to create an Str.
 */
public class StrFactory {
    /**
     * Creates an empty Str. There is only one shared, immutable empty string object, to conserve
     * memory.
     *
     * @return the empty Str
     */
    public Str empty() {
        return Str.EMPTY;
    }
    
    /**
     * Creates an Str. In a future implementation, this may pool frequently-used objects for reduced memory.
     *
     * @param str the string; may be null
     * @return the new Str
     */
    public Str of(String str) {
        return str == null ? Str.NULL : new Str(str);
    }
    
    /**
     * Creates an Str, by invoking <code>toString</code> on the object. If the object is null, the
     * wrapped string is also null.
     *
     * @param obj the object; may be null
     * @return the new Str
     */
    public Str of(Object obj) {
        return obj == null ? Str.NULL : new Str(obj.toString());
    }
    
    /**
     * Creates an Str from the collection, joined by <code>delim</code>. If <code>coll</code> is
     * null, then the wrapped string is null. If <code>delim</code> is null, it is treated as the
     * empty string ("").
     *
     * @param coll the collection to be joined
     * @param delim the delimiter within the joined string
     * @return the joined string
     */
    public Str join(Collection<?> coll, String delim) {
        String joined = Collections.join(coll, delim);
        return Str.of(joined);
    }

    /**
     * Creates an Str from the array, joined by <code>delim</code>. If <code>ary</code> is null,
     * then the wrapped string is null. If <code>delim</code> is null, it is treated as the empty
     * string.
     *
     * @param ary the array to join
     * @param delim the delimiter within the joined string
     * @return the joined string
     */
    public Str join(Object[] ary, String delim) {
        return join(ary == null ? null : Arrays.asList(ary), delim);
    }
}
