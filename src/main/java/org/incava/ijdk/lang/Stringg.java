package org.incava.ijdk.lang;

import java.util.*;

/**
 * Extensions to String.
 */
public class Stringg extends StringExt {
    /**
     * Returns the str as an integer, or null if it is null or is not an integer.
     */
    public static String toString(String str) {
        if (str == null) {
            return null;
        }
        try {
            String i = String.valueOf(str);
            return i;
        }
        catch (NumberFormatException nfe) {
            return null;
        }
    }
}
