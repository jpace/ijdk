package org.incava.ijdk.io;

public class StdOut {
    /**
     * Writes to standard output. Returns true, so this can be used in conditionals.
     *
     * @param obj the object to write
     * @return always returns true
     */
    public static boolean puts(Object obj) {
        return println(obj);
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
        System.out.printf(fmt, args);
        return true;
    }

    /**
     * Writes to standard output. Returns true, so this can be used in conditionals.
     *
     * @param obj the object to write
     * @return always returns true
     */
    public static boolean println(Object obj) {
        System.out.println(obj);
        return true;
    }
}
