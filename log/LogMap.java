package org.incava.ijdk.log;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;


/**
 * Wraps Java maps for output.
 */
public class LogMap {
    public static boolean stack(LogLevel level, 
                                EnumSet<ANSIColor> msgColors,
                                String name,
                                Map<?, ?> map,
                                ANSIColor fileColor,
                                ANSIColor classColor,
                                ANSIColor methodColor,
                                int numFrames) {
        if (map.isEmpty()) {
            return LogCollection.stackEmptyCollection(level, msgColors, name, fileColor, classColor, methodColor, numFrames);
        }
        else {
            return stackNonEmptyMap(level, msgColors, name, map, fileColor, classColor, methodColor, numFrames);
        }
    }

    protected static boolean stackNonEmptyMap(LogLevel level, 
                                              EnumSet<ANSIColor> msgColors,
                                              String name,
                                              Map<?, ?> map,
                                              ANSIColor fileColor,
                                              ANSIColor classColor,
                                              ANSIColor methodColor,
                                              int numFrames) {
        Set<?> keySet = map.keySet();
        Object[] keys = keySet.toArray();        
        boolean ret = true;
        for (int ki = 0; ki < keys.length; ++ki) {
            int nFrames = ki == keys.length - 1 ? numFrames : 1;
            ret = Log.stack(level, msgColors, name + "[" + keys[ki] + "]", map.get(keys[ki]), fileColor, classColor, methodColor, nFrames);
        }
        return ret;
    }
}
