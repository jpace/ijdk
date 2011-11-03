package org.incava.ijdk.log;

import java.util.Collection;
import java.util.EnumSet;


/**
 * Wraps Collections for output.
 */
public class QlCollection {
    public static boolean stack(QlLevel level, 
                                EnumSet<ANSIColor> msgColors,
                                String name,
                                Collection<?> c,
                                ANSIColor fileColor,
                                ANSIColor classColor,
                                ANSIColor methodColor,
                                int numFrames) {
        Object[] ary = c.toArray();
        return QlObjectArray.stack(level, msgColors, name, ary, fileColor, classColor, methodColor, numFrames);
    }
}

