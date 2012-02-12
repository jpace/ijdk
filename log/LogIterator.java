package org.incava.ijdk.log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Wraps Iterators for output.
 */
public class LogIterator extends LogCollection {
    public static <T> LogIterator create(LogLevel level, LogColors logColors, String name, Iterator<T> it, int numFrames) {
        return new LogIterator(level, logColors, name, it, numFrames);
    }

    public static <T> Collection<T> iteratorToCollection(Iterator<T> it) {
        Collection<T> coll = new ArrayList<T>();
        while (it.hasNext()) {
            coll.add(it.next());
        }
        return coll;
    }

    public <T> LogIterator(LogLevel level, LogColors logColors, String name, Iterator<T> it, int numFrames) {
        super(level, logColors, name, iteratorToCollection(it), numFrames);
    }
}
