package org.incava.ijdk.collect;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * Creates various types of Iterators, all of which are null safe (they can accept a null input).
 */
public class Iterate {
    /**
     * Returns an iterator to be executed <code>num</code> times.
     *
     * @param num the number of times to iterate
     * @return an iterator
     */
    public static Iterable<Integer> count(int num) {
        return new IntegerIterable(0, num - 1);
    }

    /**
     * Returns an Iterable (iterator) for the C-style array, which can be null.
     * If <code>ary</code> is null, an "empty" iterator will be returned.
     *
     * @param ary the array to iterate over; can be null
     * @param <T> the type of elements
     * @return an iterator for the array
     */
    public static <T> Iterable<T> over(T[] ary) {
        return ary == null ? new EmptyIterable<T>() : Array.of(ary);
    }
    
    /**
     * Returns an Iterable (iterator) for the collection, which can be null. If
     * <code>coll</code> is null, an "empty" iterator will be returned.
     *
     * @param elements the elements to iterate over; can be null
     * @param <T> the type of elements
     * @return an iterator for the collection
     */
    public static <T> Iterable<T> over(Iterable<T> elements) {
        return elements == null ? new EmptyIterable<T>() : elements;
    }

    /**
     * Returns an Iterable (iterator) for the enumeration, which can be null. If
     * <code>coll</code> is null, an "empty" iterator will be returned.
     *
     * @param elements the elements to iterate over; can be null
     * @param <T> the type of elements
     * @return an iterator for the enumeration
     */
    public static <T> Iterable<T> over(Enumeration<T> elements) {
        return elements == null ? new EmptyIterable<T>() : new EnumerationIterable<T>(elements);
    }

    /**
     * Returns an Iterable (iterator) for the collection, which can be null. If <code>coll</code> is
     * null, an "empty" iterator will be returned. Only non-null elements will be in the Iterable.
     *
     * @param elements the elements to iterate over; can be null
     * @param <T> the type of elements
     * @return an iterator for the collection
     */
    public static <T> Iterable<T> overNonNull(Iterable<T> elements) {
        return elements == null ? new EmptyIterable<T>() : new NonNullIterable<T>(elements);
    }

    /**
     * An iterator that has a value (<code>it.value()</code>) and an index
     * (<code>it.key()</code>).
     *
     * <pre>
     *     for (KeyValue&lt;Integer, String&gt; it : Iterate.eachWithIndex(array)) {
     *         System.out.println("element #" + it.key() + ": " + it.value());
     *     }
     * </pre>
     *
     * @param ary the array to iterate over; can be null
     * @param <T> the type of elements
     * @return an iterator for the array
     * @see #over
     * @see org.incava.ijdk.lang.KeyValue
     */
    public static <T> IndexIterable<T> eachWithIndex(T[] ary) {
        return new IndexIterable<T>(ary == null ? null : Arrays.asList(ary));
    }

    /**
     * An iterator that has a value (<code>it.value()</code>) and an index
     * (<code>it.index()</code>).
     *
     * <pre>
     *     for (It&lt;String&gt; it : Iterate.each(list)) {
     *         System.out.println("element #" + it.index() + ": " + it.value());
     *     }
     * </pre>
     *
     * @param elements the elements to iterate over; can be null
     * @param <T> the type of elements
     * @return an iterator for the elements
     * @see #over
     * @see org.incava.ijdk.collect.IndexIterable
     */
    public static <T> IndexIterable<T> eachWithIndex(Iterable<T> elements) {
        return new IndexIterable<T>(elements);
    }
}
