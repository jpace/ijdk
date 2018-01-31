package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.List;

public class Lists extends Collections {
    /**
     * Returns the first element in the list.
     *
     * @param <T> the element type
     * @param list the list
     * @return the first element in the list, or null if empty
     */
    public static <T> T first(List<T> list) {
        return new org.incava.ijdk.collect.Array<T>(list).first();
    }

    /**
     * Returns the last element in the list.
     *
     * @param <T> the element type
     * @param list the list
     * @return the first element in the list, or null if empty
     */
    public static <T> T last(List<T> list) {
        return new org.incava.ijdk.collect.Array<T>(list).last();
    }

    /**
     * Returns the nth element in the list, where n can be negative, to index
     * from the end of the list. Returns null if out of bounds.
     *
     * @param <T> the element type
     * @param list the list
     * @param index the index, zero-indexed
     * @return the element at the given index, or null if not in bounds
     */
    public static <T> T get(List<T> list, int index) {
        return new org.incava.ijdk.collect.Array<T>(list).get(index);
    }

    /**
     * Removes all occurrances of <code>element</code> from <code>list</code>, returning whether any
     * were found. Returns false if <code>list</code> is null.
     *
     * @param <T> the element type
     * @param list the list to modify
     * @param element the element to seek
     * @return whether any matching elements were found
     */
    public static <T> boolean removeAll(List<T> list, T element) {
        if (list != null && list.remove(element)) {
            while (list.remove(element)) {
                // nothing
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns a random element from the list, or null if the list is null or empty.
     *
     * @param <Type> the element type
     * @param list the list to seek
     * @return a random element, or null if the list is empty
     */
    public static <Type> Type getRandomElement(List<Type> list) {
        return new org.incava.ijdk.collect.Array<Type>(list).getRandomElement();
    }

    /**
     * Returns a list (an ArrayList) initialized with the given elements. The list returned is of
     * dynamic size (unlike <code>Arrays.asList(...)</code>, which returns a fixed-size array). The
     * following two blocks are equivalent:
     *
     * <pre>
     *     List&lt;String&gt; names = new ArrayList&lt;String&gt;(Arrays.asList("kevin", "jacob", "isaac"));
     *     names.add("henry");
     * </pre>
     * <pre>
     *     List&lt;String&gt; names = list("kevin", "jacob", "isaac");
     *     names.add("henry");
     * </pre>
     *
     * @param <T> the element type
     * @param elements the elements
     * @return the new List
     */
    @SafeVarargs
    public static <T> List<T> create(T ... elements) {
        List<T> ary = new ArrayList<T>();
        for (T element : elements) {
            ary.add(element);
        }
        return ary;
    }
}
