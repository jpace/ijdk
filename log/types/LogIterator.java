package org.incava.ijdk.log.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.incava.ijdk.log.Level;
import org.incava.ijdk.log.output.ItemColors;

/**
 * Wraps Iterators for output.
 */
public class LogIterator extends LogCollection {
    public static <T> LogIterator create(Level level, ItemColors colors, String name, Iterator<T> it, int numFrames) {
        return new LogIterator(level, colors, name, it, numFrames);
    }

    public static <T> Collection<T> iteratorToCollection(Iterator<T> it) {
        Collection<T> coll = new ArrayList<T>();
        while (it.hasNext()) {
            coll.add(it.next());
        }
        return coll;
    }

    public <T> LogIterator(Level level, ItemColors colors, String name, Iterator<T> it, int numFrames) {
        super(level, colors, name, iteratorToCollection(it), numFrames);
    }
}