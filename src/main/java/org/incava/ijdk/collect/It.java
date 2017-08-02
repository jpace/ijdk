package org.incava.ijdk.collect;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * An iterator that has a value and an index. This is an alternative to:
 *
 * <pre>
 *     int index = 0;
 *     for (String str : listOfStrings) {
 *         System.out.println("string #" + index + ": " + str);
 *         ++index;
 *     }
 * </pre>
 *
 * <pre>
 *     for (int index = 0; index &lt; listOfStrings.size(); ++index) {
 *         System.out.println("string #" + index + ": " + listOfStrings.get(index));
 *     }
 * </pre>
 *
 * Using It:
 *
 * <pre>
 *     for (It&lt;String&gt; it : It.of(listOfStrings)) {
 *         System.out.println("string #" + it.index() + ": " + it.value());
 *     }
 * </pre>
 */
public class It<T> {
    public static <T> ItIterable<T> of(List<T> list) {
        return new ItIterable<T>(list);
    }

    public static <T> ItIterable<T> of(T[] list) {
        return new ItIterable<T>(Arrays.asList(list));
    }

    private final T value;
    private final int index;

    public It(T value, int index) {
        this.value = value;
        this.index = index;
    }

    public T value() {
        return this.value;
    }

    public int index() {
        return this.index;
    }
}
