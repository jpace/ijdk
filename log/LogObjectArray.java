package org.incava.ijdk.log;

import org.incava.ijdk.lang.*;

/**
 * Wraps C-style arrays for output.
 */
public class LogObjectArray extends LogElement {
    public static LogObjectArray create(LogLevel level, LogColors logColors, String name, Object obj, int numFrames) {
        Object[] objAry;

        if (obj == null) {
            objAry = null;
        }
        else if (obj instanceof Object[]) {
            objAry = (Object[])obj;
        }
        else if (obj instanceof boolean[]) {
            boolean[] ary = (boolean[])obj;
            objAry = BooleanArray.toStringArray(ary);
        }
        else if (obj instanceof byte[]) {
            byte[] ary = (byte[])obj;
            objAry = ByteArray.toStringArray(ary);
        }
        else if (obj instanceof char[]) {
            char[] ary = (char[])obj;
            objAry = CharArray.toStringArray(ary);
        }
        else if (obj instanceof double[]) {
            double[] ary = (double[])obj;
            objAry = DoubleArray.toStringArray(ary);
        } 
        else if (obj instanceof float[]) {
            float[] ary = (float[])obj;
            objAry = FloatArray.toStringArray(ary);
        }
        else if (obj instanceof int[]) {
            int[] ary = (int[])obj;
            objAry = IntArray.toStringArray(ary);
        }
        else if (obj instanceof long[]) {
            long[] ary = (long[])obj;
            objAry = LongArray.toStringArray(ary);
        }
        else if (obj instanceof short[]) {
            short[] ary = (short[])obj;
            objAry = ShortArray.toStringArray(ary);
        }
        else {
            objAry = null;
        }
        return new LogObjectArray(level, logColors, name, objAry, numFrames);
    }

    private final Object[] ary;
    
    public LogObjectArray(LogLevel level, LogColors logColors, String name, Object[] ary, int numFrames) {
        super(level, logColors, name, ary, numFrames);
        this.ary = ary;
    }

    public boolean stack(LogWriter lw) {
        LogLevel level = getLevel();
        LogColors logColors = getColors();
        String name = getName();
        int numFrames = getNumFrames();

        // @todo: add usage of writer
        if (ary == null || ary.length == 0) {
            return stackEmptyCollection();
        }

        boolean ret = true;
        for (int ai = 0; ai < ary.length; ++ai) {
            int nFrames = ai == ary.length - 1 ? numFrames : 1;
            ret = Log.stack(level, logColors, name + "[" + ai + "]", ary[ai], nFrames);
        }
        return ret;
    }
}
