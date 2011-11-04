package org.incava.ijdk.log;

import java.util.Collection;
import java.util.EnumSet;


/**
 * Wraps Collections for output.
 */
public class LogCollection {
    public static boolean stack(LogLevel level, 
                                EnumSet<ANSIColor> msgColors,
                                String name,
                                Collection<?> c,
                                ANSIColor fileColor,
                                ANSIColor classColor,
                                ANSIColor methodColor,
                                int numFrames) {
        Object[] ary = c.toArray();
        return LogObjectArray.stack(level, msgColors, name, ary, fileColor, classColor, methodColor, numFrames);
    }
}

