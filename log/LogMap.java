package org.incava.ijdk.log;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;


/**
 * Wraps Java maps for output.
 */
public class LogMap {
    public static boolean stack(LogLevel level, LogColors logColors, String name, Map<?, ?> map, int numFrames) {
        if (map.isEmpty()) {
            return LogCollection.stackEmptyCollection(level, logColors, name, numFrames);
        }
        else {
            return stackNonEmptyMap(level, logColors, name, map, numFrames);
        }
    }

    protected static boolean stackNonEmptyMap(LogLevel level, LogColors logColors, String name, Map<?, ?> map, int numFrames) {
        Set<?> keySet = map.keySet();
        Object[] keys = keySet.toArray();        
        boolean ret = true;
        for (int ki = 0; ki < keys.length; ++ki) {
            int nFrames = ki == keys.length - 1 ? numFrames : 1;
            ret = Log.stack(level, logColors, name + "[" + keys[ki] + "]", map.get(keys[ki]), nFrames);
        }
        return ret;
    }
}
