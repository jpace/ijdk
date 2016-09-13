package org.incava.ijdk.lang;

import java.util.*;

/**
 * Extensions to Integer.
 */
public class Integerr extends IntegerExt {
    /**
     * Returns the str as an integer, or null if it is null or is not an integer.
     */
    public static Integer toInteger(String str) {
        if (str == null) {
            return null;
        }
        try {
            Integer i = Integer.valueOf(str);
            return i;
        }
        catch (NumberFormatException nfe) {
            return null;
        }
    }
}
