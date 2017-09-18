package org.incava.ijdk.collect;

import java.util.Collection;
import java.util.List;
import org.incava.ijdk.lang.Str;

/**
 * A dynamically-sized collection, wrapping ArrayList.
 */
public class Array<T extends Object> extends BaseArray<T, Array<T>> {
    /**
     * Creates a list from the given array.
     *
     * <pre>
     * Array&lt;Integer&gt; list = Array.&lt;Integer&gt;of();
     * Array&lt;Integer&gt; list = Array.of(6);
     * Array&lt;Integer&gt; list = Array.of(6, 7);
     * Array&lt;Integer&gt; list = Array.of(6, 7, 8);
     * </pre>
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T extends Object> Array<T> of(T ... ary) {
        return new Array<T>(ary);
    }
    
    /**
     * Creates an empty list from the given array. The syntax <code>Array.&lt;Type&gt;empty()</code>
     * is used to denote the generic type.
     *
     * <pre>
     * Array&lt;String&gt; list = Array.&lt;String&gt;empty();
     * </pre>
     *
     * @return an empty array
     */
    public static <T extends Object> Array<T> empty() {
        return new Array<T>();
    }
    
    public static final long serialVersionUID = 1L;

    /**
     * Creates an empty list.
     */
    public Array() {
    }

    /**
     * Creates a list from the collection, copying its elements. <code>coll</code> can be null.
     */
    public Array(Collection<? extends T> coll) {
        if (coll != null) {
            addAll(coll);
        }
    }

    /**
     * Creates the list from a varargs array.
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public Array(T ... ary) {
        for (T el : ary) {
            add(el);
        }
    }

    public Array<T> newInstance() {
        return new Array<T>();
    }
}    
