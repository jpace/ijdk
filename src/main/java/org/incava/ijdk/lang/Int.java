package org.incava.ijdk.lang;

/**
 * Extensions to Integer, wrapping a possibly-null Integer with better null checking.
 */
public class Int extends NullableObject<Integer> {
    /**
     * Returns an Int (integer) for the number.
     *
     * @return an Int wrapping the given Integer
     */
    public static Int of(Integer num) {
        // this may become optimized to reduce object creation
        return new Int(num);
    }
    
    /**
     * Returns an Int (integer) for the string.
     *
     * @return an Int wrapping the given string, converted to an Integer
     */
    public static Int of(String str) {
        // this too may become optimized to reduce object creation
        return new Int(toInteger(str));
    }
    
    /**
     * Returns the str as an integer, or null if it is null or is not an integer.
     *
     * @return an Int wrapping the given string, converted to an Integer
     */
    public static Integer toInteger(String str) {
        if (str == null) {
            return null;
        }
        try {
            return Integer.valueOf(str);
        }
        catch (NumberFormatException nfe) {
            return null;
        }
    }
    
    /**
     * Converts the given string to an integer, if it is a valid value. Otherwise the wrapped
     * integer will be null.
     */
    public Int(String string) {
        this(toInteger(string));
    }

    /**
     * Wraps the given integer, which can be null.
     */
    public Int(Integer in) {
        super(in);
    }

    /**
     * Returns the wrapped integer.
     */
    public Integer integer() {
        return obj();
    }
}
