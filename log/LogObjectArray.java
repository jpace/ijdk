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
            return LogCollection.stackEmptyCollection(level, msgColors, name, fileColor, classColor, methodColor, numFrames);
        }
        else {
            return stackNonEmptyArray(level, msgColors, name, ary, fileColor, classColor, methodColor, numFrames);
        }
    }

    // public static boolean stack(LogLevel level, 
    //                             EnumSet<ANSIColor> msgColors,
    //                             String name,
    //                             byte[] ary,
    //                             ANSIColor fileColor,
    //                             ANSIColor classColor,
    //                             ANSIColor methodColor,
    //                             int numFrames) {

    //         String[] strs = null;
    //         if (obj instanceof byte[]) {
    //             byte[] ary = (byte[])obj;
    //             strs = new String[ary.length];
    //             for (int ai = 0; ai < ary.length; ++ai) {
    //                 strs[ai] = String.valueOf(ary[ai]);
    //             }
    //         }
    //         else if (obj instanceof char[]) {
    //             char[] ary = (char[])obj;
    //             strs = new String[ary.length];
    //             for (int ai = 0; ai < ary.length; ++ai) {
    //                 strs[ai] = String.valueOf(ary[ai]);
    //             }
    //         }
    //         else if (obj instanceof double[]) {
    //             double[] ary = (double[])obj;
    //             strs = new String[ary.length];
    //             for (int ai = 0; ai < ary.length; ++ai) {
    //                 strs[ai] = String.valueOf(ary[ai]);
    //             }
    //         } 
    //         else if (obj instanceof float[]) {
    //             float[] ary = (float[])obj;
    //             strs = new String[ary.length];
    //             for (int ai = 0; ai < ary.length; ++ai) {
    //                 strs[ai] = String.valueOf(ary[ai]);
    //             }
    //         }
    //         else if (obj instanceof int[]) {
    //             int[] ary = (int[])obj;
    //             strs = new String[ary.length];
    //             for (int ai = 0; ai < ary.length; ++ai) {
    //                 strs[ai] = String.valueOf(ary[ai]);
    //             }
    //         }
    //         else if (obj instanceof long[]) {
    //             long[] ary = (long[])obj;
    //             strs = new String[ary.length];
    //             for (int ai = 0; ai < ary.length; ++ai) {
    //                 strs[ai] = String.valueOf(ary[ai]);
    //             }
    //         }

    //         return LogObjectArray.stack(level, msgColors, nm, strs, fileColor, classColor, methodColor, numFrames);

    protected static boolean stackNonEmptyArray(LogLevel level, 
                                                EnumSet<ANSIColor> msgColors,
                                                String name,
                                                Object[] ary,
                                                ANSIColor fileColor,
                                                ANSIColor classColor,
                                                ANSIColor methodColor,
                                                int numFrames) {
        boolean ret = true;
        for (int ai = 0; ai < ary.length; ++ai) {
            int nFrames = ai == ary.length - 1 ? numFrames : 1;
            ret = Log.stack(level, msgColors, name + "[" + ai + "]", ary[ai], fileColor, classColor, methodColor, nFrames);
        }
        return ret;
    }
}
