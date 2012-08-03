package org.incava.ijdk.log.types;

import java.util.Collection;
import org.incava.ijdk.log.Level;
import org.incava.ijdk.log.output.LogColors;

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
