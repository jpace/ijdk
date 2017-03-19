package org.incava.ijdk.util;

import org.incava.ijdk.lang.Closure;

/**
 * Criteria for filtering/selection of strings.
 */
public class StringCriteria {
    /**
     * Returns true if the string starts with the substring.
     */
    public static Closure<Boolean, String> startsWith(final String substr) {
        return new Closure<Boolean, String>() {
            public Boolean execute(String str) {
                return str != null && str.startsWith(substr);
            }
        };
    }
    
    /**
     * Returns true if the string contains the substring.
     */
    public static Closure<Boolean, String> contains(final String substr) {
        return new Closure<Boolean, String>() {
            public Boolean execute(String str) {
                return str != null && str.contains(substr);
            }
        };
    }
    
    /**
     * Returns true if the string ends with the substring.
     */
    public static Closure<Boolean, String> endsWith(final String substr) {
        return new Closure<Boolean, String>() {
            public Boolean execute(String str) {
                return str != null && str.endsWith(substr);
            }
        };
    }
    
    /**
     * Returns true if an element equals the given string, without regard to case.
     */
    public static Closure<Boolean, String> equalsIgnoreCase(final String other) {
        return new Closure<Boolean, String>() {
            public Boolean execute(String str) {
                return str != null && str.equalsIgnoreCase(other);
            }
        };
    }
}
