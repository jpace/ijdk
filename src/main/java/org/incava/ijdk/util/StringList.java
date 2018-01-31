package org.incava.ijdk.util;

import java.util.Collection;

/**
 * An extension of List&lt;String&gt;, with a constructor for varargs, and selectors that use
 * closures. Use <code>org.incava.ijdk.collect.StringList</code> instead.
 */
public class StringList extends org.incava.ijdk.collect.StringList {
    private static final long serialVersionUID = -5489075883851520676L;
    
    /**
     * Creates an empty StringList.
     */
    public StringList() {
    }

    /**
     * Creates a StringList from the given collection.
     *
     * @param coll the collection of strings with which to populate this object
     */
    public StringList(Collection<String> coll) {
        super(coll);
    }

    /**
     * Creates a StringList from the given array.
     *
     * @param ary the array of strings with which to populate this object
     */
    public StringList(String ... ary) {
        super(ary);
    }
}
