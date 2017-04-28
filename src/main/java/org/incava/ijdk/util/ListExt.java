package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.List;

public class ListExt extends CollectionExt {
    /**
     * Returns the first element in the list.
     */
    public static <T> T first(List<T> list) {
        return new ijdk.collect.List<T>(list).first();
    }

    /**
     * Returns the last element in the list.
     */
    public static <T> T last(List<T> list) {
        return new ijdk.collect.List<T>(list).last();
    }

    /**
     * Returns the nth element in the list, where n can be negative, to index
     * from the end of the list. Returns null if out of bounds.
     */
    public static <T> T get(List<T> list, int index) {
        return new ijdk.collect.List<T>(list).get(index);
    }

    /**
     * Converts the index, which can be positive or negative, to one within the
     * given size, representing the length of a list. A negative index will
     * result in the distance from the end of the list, with index -1 meaning
     * the last element in the list. Returns null if the resulting index is out
     * of range.
     *
     * @see Index#getIndex
     */
    public static Integer getIndex(Integer size, Integer index) {
        return Index.getIndex(size, index);
    }

    /**
     * Removes all occurrances of <code>element</code> from <code>list</code>, returning whether any
     * were found. Returns false if <code>list</code> is null.
     */
    public static <T> boolean removeAll(List<T> list, T element) {
        if (list == null) {
            return false;
        }
        boolean found = list.remove(element);
        boolean origFound = found;
        while (found) {
            found = list.remove(element);
        }
        return origFound;
    }

    /**
     * Returns a random element from the list, or null if the list is null or empty.
     */
    public static <Type> Type getRandomElement(List<Type> list) {
        return new ijdk.collect.List<Type>(list).getRandomElement();
    }

    /**
     * Returns a list (an ArrayList) initialized with the given elements. The
     * list returned is of dynamic size (unlike <code>Arrays.asList(...)</code>,
     * which returns a fixed-size array). The following two blocks are
     * equivalent:
     *
     * <pre>
     *     List&lt;String&gt; names = new ArrayList&lt;String&gt;(Arrays.asList("kevin", "jacob", "isaac"));
     *     names.add("henry");
     * </pre>
     * <pre>
     *     List&lt;String&gt; names = list("kevin", "jacob", "isaac");
     *     names.add("henry");
     * </pre>
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> create(T ... elements) {
        List<T> ary = new ArrayList<T>();
        for (T element : elements) {
            ary.add(element);
        }
        return ary;
    }
}
