package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.List;

public class ListExt extends CollectionExt {
    public static <T> T first(List<T> list) {
        return new org.incava.ijdk.collect.Array<T>(list).first();
    }

    public static <T> T last(List<T> list) {
        return new org.incava.ijdk.collect.Array<T>(list).last();
    }

    public static <T> T get(List<T> list, int index) {
        return new org.incava.ijdk.collect.Array<T>(list).get(index);
    }

    public static Integer getIndex(Integer size, Integer index) {
        return new Indexable(size).get(index);
    }

    public static <T> boolean removeAll(List<T> list, T element) {
        return new org.incava.ijdk.collect.Array<T>(list).removeAll(element);
    }

    public static <Type> Type getRandomElement(List<Type> list) {
        return new org.incava.ijdk.collect.Array<Type>(list).getRandomElement();
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
     *
     * @param <T> the element type of the array
     * @param elements the elements
     * @return the new list of elements
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
