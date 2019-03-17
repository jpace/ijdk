package org.incava.ijdk.collect;

import java.util.Collection;
import java.util.List;
import org.incava.ijdk.lang.Str;

/**
 * A dynamically-sized collection, wrapping ArrayList.
 *
 * @param <T> the element type
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
     *
     * @param <T> the element type
     * @param ary the elements for the new array
     * @return the new array
     * @see #empty
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
     * Array&lt;String&gt; list = Array.&lt;&gt;empty();
     * </pre>
     *
     * @param <T> the element type
     * @return the new array
     * @see #of
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
     *
     * @param coll the collection to copy the elements from
     */
    public Array(Collection<? extends T> coll) {
        if (coll != null) {
            addAll(coll);
        }
    }

    /**
     * Creates the list from a varargs array.
     *
     * @param ary the elements for the new array
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public Array(T ... ary) {
        for (T el : ary) {
            add(el);
        }
    }

    /**
     * Creates a new Array with the same element type. The returned array is <em>not</em> copied
     * from this array.
     *
     * @return the new array of this type
     */
    public Array<T> newInstance() {
        return new Array<T>();
    }
}
