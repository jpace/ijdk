package org.incava.ijdk.io;

public class StdOut {
    /**
     * Writes to standard output. Returns true, so this can be used in conditionals.
     */
    public static boolean puts(Object obj) {
        return println(obj);
    }

    /**
     * Writes to standard output. Returns true, so this can be used in conditionals.
     */
    public static boolean printf(String fmt, Object ... args) {
        System.out.printf(fmt, args);
        return true;
    }

    /**
     * Writes to standard output. Returns true, so this can be used in conditionals.
     */
    public static boolean println(Object obj) {
        System.out.println(obj);
        return true;
    }
}
