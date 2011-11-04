package org.incava.ijdk.log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;


/**
 * Wraps Iterators for output.
 */
public class LogIterator {

    public static <T> boolean stack(LogLevel level, 
                                    EnumSet<ANSIColor> msgColors,
                                    String name,
                                    Iterator<T> it,
                                    ANSIColor fileColor,
                                    ANSIColor classColor,
                                    ANSIColor methodColor,
                                    int numFrames) {
        Collection<T> ary = new ArrayList<T>();
        while (it.hasNext()) {
            ary.add(it.next());
        }

        return LogCollection.stack(level, msgColors, name, ary, fileColor, classColor, methodColor, numFrames);
    }
}

