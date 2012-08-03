package org.incava.ijdk.log;

import java.util.Collection;

/**
 * Wraps Collections for output.
 */
public class LogCollection extends LogObjectArray {
    private final Collection coll;

    public LogCollection(Level level, LogColors logColors, String name, Collection coll, int numFrames) {
        super(level, logColors, name, coll.toArray(), numFrames);
        this.coll = coll;
    }
}
