package org.incava.ijdk.str;

import org.incava.ijdk.lang.Closure;

public abstract class Criteria<T> implements Closure<T, String> {
    /**
     * Returns true if the string starts with the substring.
     *
     * @param substr the substring
     * @return whether the applied string starts with the substring
     */
    public static Criteria<Boolean> startsWith(final String substr) {
        return new Criteria<Boolean>() {
            public Boolean apply(final String str) {
                return str.startsWith(substr);
            }
        };
    }
    
    /**
     * Returns true if the string contains the substring.
     *
     * @param substr the substring
     * @return whether the applied string contain the substring
     */
    public static Criteria<Boolean> contains(final String substr) {
        return new Criteria<Boolean>() {
            public Boolean apply(final String str) {
                return str.contains(substr);
            }
        };
    }
    
    /**
     * Returns true if the string ends with the substring.
     *
     * @param substr the substring
     * @return whether the applied string ends with the substring
     */
    public static Criteria<Boolean> endsWith(final String substr) {
        return new Criteria<Boolean>() {
            public Boolean apply(final String str) {
                return str.endsWith(substr);
            }
        };
    }
    
    /**
     * Returns true if an element equals the given string, without regard to case.
     *
     * @param other the other string
     * @return whether the applied string equals, ignoring case, the other
     */
    public static Criteria<Boolean> equalsIgnoreCase(final String other) {
        return new Criteria<Boolean>() {
            public Boolean apply(final String str) {
                return str.equalsIgnoreCase(other);
            }
        };
    }
    
    /**
     * Returns true if an element equals the given string.
     *
     * @param other the other string
     * @return whether the applied string equals the other
     */
    public static Criteria<Boolean> equals(final String other) {
        return new Criteria<Boolean>() {
            public Boolean apply(final String str) {
                return str.equals(other);
            }
        };
    }

    public T execute(String str) {
        return str == null ? null : apply(str);
    }

    public abstract T apply(String str);
}
