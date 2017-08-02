package org.incava.ijdk.collect;

import java.util.List;
import java.util.Iterator;

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
 *     for (int index = 0; index < listOfStrings.size(); ++index) {
 *         System.out.println("string #" + index + ": " + listOfStrings.get(index));
 *     }
 * </pre>
 *
 * Using It:
 *
 * <pre>
 *     for (It<String> it : It.of(listOfStrings)) {
 *         System.out.println("string #" + it.index() + ": " + it.value());
 *     }
 * </pre>
 */
public class ItIterable<T> implements Iterable<It<T>> {
    private final List<T> list;
    
    public ItIterable(List<T> list) {
        this.list = list;
    }

    public Iterator<It<T>> iterator() {
        return new ItIterator<T>(list);
    }
    
}
