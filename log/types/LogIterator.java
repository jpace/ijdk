package org.incava.ijdk.log.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.incava.ijdk.log.Level;
import org.incava.ijdk.log.LogColors;

/**
 * Wraps Iterators for output.
 */
public class LogIterator extends LogCollection {
    public static <T> LogIterator create(Level level, LogColors logColors, String name, Iterator<T> it, int numFrames) {
        return new LogIterator(level, logColors, name, it, numFrames);
    }

    public static <T> Collection<T> iteratorToCollection(Iterator<T> it) {
        Collection<T> coll = new ArrayList<T>();
        while (it.hasNext()) {
            coll.add(it.next());
        }
        return coll;
    }

    public <T> LogIterator(Level level, LogColors logColors, String name, Iterator<T> it, int numFrames) {
        super(level, logColors, name, iteratorToCollection(it), numFrames);
    }
}
