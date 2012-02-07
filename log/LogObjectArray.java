package org.incava.ijdk.log;

import java.util.EnumSet;

/**
 * Wraps C-style arrays for output.
 */
public class LogObjectArray {
    public static boolean stack(LogLevel level, 
                                LogColors logColors,
                                String name,
                                Object[] ary,
                                int numFrames) {
        if (ary.length == 0) {
            return LogCollection.stackEmptyCollection(level, logColors, name, numFrames);
        }
        else {
            return stackNonEmptyArray(level, logColors, name, ary, numFrames);
        }
    }

    protected static boolean stackNonEmptyArray(LogLevel level, LogColors logColors, String name, Object[] ary, int numFrames) {
        boolean ret = true;
        for (int ai = 0; ai < ary.length; ++ai) {
            int nFrames = ai == ary.length - 1 ? numFrames : 1;
            ret = Log.stack(level, logColors, name + "[" + ai + "]", ary[ai], nFrames);
        }
        return ret;
    }
}
