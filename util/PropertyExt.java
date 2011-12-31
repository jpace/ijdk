package org.incava.ijdk.util;

import static org.incava.ijdk.util.IUtil.*;

public class PropertyExt {
    /**
     * Returns the boolean value of the property, or null if it does not exist.
     */
    public static Boolean getBooleanProperty(String name) {
        String val = System.getProperty(name);
        return isNull(val) ? null : new Boolean(val);
    }

    /**
     * Returns the integer value of the property, or null if it does not exist.
     */
    public static Integer getIntegerProperty(String name) {
        String val = System.getProperty(name);
        return isNull(val) ? null : new Integer(val);
    }
}
