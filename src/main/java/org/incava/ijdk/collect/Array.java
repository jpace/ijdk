package org.incava.ijdk.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.incava.ijdk.lang.Sequence;
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

    /**
     * Returns the list as a String, joined by the delimiter.
     */
    public String join(String delimiter) {
        return Str.join(this, delimiter).toString();
    }

    /**
     * Returns a new list, concatenating the other list with this one. Elements will be in the order
     * of this list, then the elements of the other.
     */
    public Array<T> plus(Array<T> other) {
        Array<T> newList = new Array<T>(this);
        for (T obj : other) {
            newList.append(obj);
        }
        return newList;
    }

    /**
     * Returns a new list, containing the elements of this list that are not in the other. The
     * retained elements are in the same order as they are in this list.
     */
    public Array<T> minus(Array<T> other) {
        Array<T> newList = new Array<T>();
        for (T obj : this) {
            if (!other.contains(obj)) {
                newList.append(obj);
            }
        }
        return newList;
    }

    /**
     * Returns a list of the elements at the given indices. As with <code>get</code>, negative
     * indices are treated as offsets from the last element.

     * <pre>
     *     Array&lt;Integer&gt; list = Array.of(6, 7, 8);
     *     Array&lt;Integer&gt; l2 = list.elements(0);         // [ 6 ]
     *     Array&lt;Integer&gt; l3 = list.elements(0, 1);      // [ 6, 7 ]
     *     Array&lt;Integer&gt; l4 = list.elements(1, 0);      // [ 7, 6 ]
     *     Array&lt;Integer&gt; l5 = list.elements(-1, -2, 1); // [ 8, 7, 7 ]
     * </pre>
     *
     * @see Array#get
     */
    public Array<T> elements(int ... indices) {
        Array<T> elmts = new Array<T>();
        for (int idx : indices) {
            elmts.append(get(idx));
        }
        return elmts;
    }

    /**
     * Returns a list of elements in both this array and the other. The order is the same as in this array.
     */
    public Array<T> intersection(Array<T> other) {
        Array<T> ary = new Array<T>();
        for (T obj : this) {
            if (other.contains(obj)) {
                ary.add(obj);
            }
        }
        return ary;
    }

    /**
     * Returns a copy of this array, sorted.
     *
     * @return a sorted array
     */
    @SuppressWarnings("unchecked")
    public Array<T> sorted() {
        Object[] copy = toArray();
        if (copy.length == 0) {
            return new Array<T>();
        }
        else {
            if (copy[0] instanceof Comparable) {
                Arrays.sort(copy);
                Array<T> ary = new Array<T>();
                for (Object x : copy) {
                    ary.add((T)x);
                }
                return ary;
            }
            else {
                throw new RuntimeException("array contains " + copy[0].getClass() + ", which is not Comparable");
            }
        }
    }

    private Integer getIndex(Integer n) {
        return new org.incava.ijdk.util.Indexable(size()).get(n);
    }
}    
