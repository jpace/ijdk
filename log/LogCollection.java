package org.incava.ijdk.log;

import java.util.Collection;

/**
 * Wraps Collections for output.
 */
public class LogCollection extends LogObjectArray {
    private final Collection coll;

    public LogCollection(LogLevel level, LogColors logColors, String name, Collection coll, int numFrames) {
        super(level, logColors, name, coll.toArray(), numFrames);
        this.coll = coll;
    }

    public static boolean stack(LogLevel level, LogColors logColors, String name, Collection coll, int numFrames) {
        Object[] ary = coll.toArray();
        return LogObjectArray.stack(level, logColors, name, ary, numFrames);
    }
    
    public static boolean stackEmptyCollection(LogLevel level, LogColors logColors, String name, int numFrames) {
        return Log.stack(level, logColors, name, "()", numFrames);
    }
}
