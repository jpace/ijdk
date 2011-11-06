package org.incava.ijdk.log;

import java.util.Collection;
import java.util.EnumSet;


/**
 * Wraps Collections for output.
 */
public class LogCollection {
    public static boolean stack(LogLevel level, LogColors logColors, String name, Collection<?> coll, int numFrames) {
        Object[] ary = coll.toArray();
        return LogObjectArray.stack(level, logColors, name, ary, numFrames);
    }
    
    public static boolean stackEmptyCollection(LogLevel level, LogColors logColors, String name, int numFrames) {
        return Log.stack(level, logColors, name, "()", numFrames);
    }
}
