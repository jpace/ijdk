package ijdk.lang;

import java.util.*;

/**
 * Extensions to Integer.
 */
public class Integerr extends Obj {
    /**
     * Returns the str as an integer, or null if it is null or is not an integer.
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
    
    private final Integer integer;
    
    /**
     * Converts the given string to an integer, if it is a valid value. Otherwise the wrapped
     * integer will be null.
     */
    public Integerr(String string) {
        this(toInteger(string));
    }

    /**
     * Wraps the given integer, which can be null.
     */
    public Integerr(Integer in) {
        super(in);
        this.integer = in;
    }

    /**
     * Returns the wrapped integer.
     */
    public Integer getInteger() {
        return this.integer;
    }

    /**
     * Returns the wrapped integer.
     */
    public Integer integer() {
        return this.integer;
    }
}
