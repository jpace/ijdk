package org.incava.ijdk.collect;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.incava.ijdk.util.EmptyIterable;
import org.incava.ijdk.util.NumIterable;

public class Iterate {
    /**
     * Returns an iterator to be executed <code>num</code> times.
     *
     * @param num the number of times to iterate
     * @return an iterator
     */
    public static Iterable<Integer> count(int num) {
        return new NumIterable(num);
    }

    /**
     * Returns an Iterable (iterator) for the C-style array, which can be null.
     * If <code>ary</code> is null, an "empty" iterator will be returned.
     *
     * @param ary the array to iterate over; can be null
     * @return an iterator for the array
     */
    public static <T> Iterable<T> over(T[] ary) {
        return ary == null ? new EmptyIterable<T>() : Array.of(ary);
    }

    /**
     * Returns an Iterable (iterator) for the collection, which can be null. If
     * <code>coll</code> is null, an "empty" iterator will be returned.
     *
     * @param coll the collection to iterate over; can be null
     * @return an iterator for the collection
     */
    public static <T> Iterable<T> over(Iterable<T> coll) {
        return coll == null ? new EmptyIterable<T>() : coll;
    }

    /**
     * An iterator that has a value and an index.
     *
     * <pre>
     *     for (It&lt;String&gt; it : Iterate.each(listOfStrings)) {
     *         System.out.println("string #" + it.index() + ": " + it.value());
     *     }
     * </pre>
     */
    public static <T> ItIterable<T> each(List<T> list) {
        return new ItIterable<T>(list);
    }

    public static <T> ItIterable<T> each(T[] list) {
        return new ItIterable<T>(list == null ? null : Arrays.asList(list));
    }    
}
