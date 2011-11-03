package org.incava.ijdk.log;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.EnumSet;


/**
 * Wraps Enumerations for output.
 */
public class QlEnumeration {
    
    public static <T> boolean stack(QlLevel level, 
                                    EnumSet<ANSIColor> msgColors,
                                    String name,
                                    Enumeration<T> en,
                                    ANSIColor fileColor,
                                    ANSIColor classColor,
                                    ANSIColor methodColor,
                                    int numFrames) {
        Collection<T> ary = Collections.list(en);

        return QlCollection.stack(level, msgColors, name, ary, fileColor, classColor, methodColor, numFrames);
    }
}

