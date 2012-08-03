package org.incava.ijdk.log.types;

import java.util.Collections;
import java.util.Enumeration;
import org.incava.ijdk.log.Level;
import org.incava.ijdk.log.output.ItemColors;

/**
 * Wraps Enumerations for output.
 */
public class LogEnumeration extends LogCollection {
    public LogEnumeration(Level level, ItemColors colors, String name, Enumeration<?> en, int numFrames) {
        super(level, colors, name, Collections.list(en), numFrames);
    }
}
