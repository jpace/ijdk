package org.incava.ijdk.lang;

/**
 * Contains various means to create an Str.
 */
public class StrFactory {
    /**
     * Creates an empty Str. There is only one shared, immutable empty string object, to conserve
     * memory.
     *
     * @return the empty Str
     */
    public Str empty() {
        return Str.EMPTY;
    }
    
    /**
     * Creates an Str. In a future implementation, this may pool frequently-used objects for reduced memory.
     *
     * @param str the string; may be null
     * @return the new Str
     */
    public Str of(String str) {
        return str == null ? Str.NULL : new Str(str);
    }
    
    /**
     * Creates an Str, by invoking <code>toString</code> on the object. If the object is null, the
     * wrapped string is also null.
     *
     * @param obj the object; may be null
     * @return the new Str
     */
    public Str of(Object obj) {
        return obj == null ? Str.NULL : new Str(obj.toString());
    }
}
