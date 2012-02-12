package org.incava.ijdk.log;

import java.util.Collections;
import java.util.Enumeration;

/**
 * Wraps Enumerations for output.
 */
public class LogEnumeration extends LogCollection {
    public LogEnumeration(LogLevel level, LogColors logColors, String name, Enumeration<?> en, int numFrames) {
        super(level, logColors, name, Collections.list(en), numFrames);
    }
}
