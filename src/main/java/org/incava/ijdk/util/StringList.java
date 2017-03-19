package org.incava.ijdk.util;

import java.util.*;
import org.incava.ijdk.lang.Closure;

/**
 * An extension of List&lt;String&gt;, with a constructor for varargs, and selectors that use closures.
 */
public class StringList extends ijdk.lang.StringList {
    private static final long serialVersionUID = -5489075883851520676L;
    
    /**
     * Creates a StringList with the given initial capacity.
     */
    public StringList(int capacity) {
        super(capacity);
    }

    /**
     * Creates an empty StringList.
     */
    public StringList() {
    }

    /**
     * Creates a StringList from the given collection.
     */
    public StringList(Collection<String> coll) {
        super(coll);
    }

    /**
     * Creates a StringList from the given array.
     */
    public StringList(String ... ary) {
        super(ary);
    }
}
