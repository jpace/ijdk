package org.incava.ijdk.log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;


/**
 * Wraps Iterators for output.
 */
public class LogIterator {
    public static <T> boolean stack(LogLevel level, LogColors logColors, String name, Iterator<T> it, int numFrames) {
        Collection<T> ary = new ArrayList<T>();
        while (it.hasNext()) {
            ary.add(it.next());
        }

        return LogCollection.stack(level, logColors, name, ary, numFrames);
    }
}

