package org.incava.ijdk.log;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.EnumSet;


/**
 * Wraps Enumerations for output.
 */
public class LogEnumeration {    
    public static <T> boolean stack(LogLevel level, LogColors logColors, String name, Enumeration<T> en, int numFrames) {
        Collection<T> ary = Collections.list(en);
        return LogCollection.stack(level, logColors, name, ary, numFrames);
    }
}

