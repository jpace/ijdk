package org.incava.ijdk.io;

import org.incava.ijdk.lang.Output;

public class StdOut {
    /**
     * Writes to standard output. Returns true, so this can be used in conditionals.
     *
     * @param obj the object to write
     * @return always returns true
     */
    public static boolean puts(Object obj) {
        return Output.puts(obj);
    }

    /**
     * Writes to standard output. Returns true, so this can be used in conditionals.
     *
     * @param fmt the format of the string
     * @param args arguments to the format
     * @return always returns true
     * 
     * @see java.lang.String#format
     */
    public static boolean printf(String fmt, Object ... args) {
        return Output.printf(fmt, args);
    }

    /**
     * Writes to standard output. Returns true, so this can be used in conditionals.
     *
     * @param obj the object to write
     * @return always returns true
     */
    public static boolean println(Object obj) {
        return Output.println(obj);
    }
}
