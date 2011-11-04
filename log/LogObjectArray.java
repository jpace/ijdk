package org.incava.ijdk.log;

import java.util.EnumSet;


/**
 * Wraps C-style arrays for output.
 */
public class LogObjectArray {
    public static boolean stack(LogLevel level, 
                                EnumSet<ANSIColor> msgColors,
                                String name,
                                Object[] ary,
                                ANSIColor fileColor,
                                ANSIColor classColor,
                                ANSIColor methodColor,
                                int numFrames) {
        if (ary.length == 0) {
            return Log.stack(level, msgColors, name, "()", fileColor, classColor, methodColor, numFrames);
        }
        else {
            boolean ret = true;
            for (int ai = 0; ai < ary.length; ++ai) {
                int nFrames = ai == ary.length - 1 ? numFrames : 1;
                ret = Log.stack(level, msgColors, name + "[" + ai + "]", ary[ai], fileColor, classColor, methodColor, nFrames);
            }
            return ret;
        }
    }
}

