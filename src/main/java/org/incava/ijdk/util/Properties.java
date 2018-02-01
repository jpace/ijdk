package org.incava.ijdk.util;

import static org.incava.ijdk.util.IUtil.isNull;

public class Properties {
    /**
     * Returns the boolean value of the property, or null if it does not exist.
     *
     * @param name the name of the property
     * @return the property value, as a boolean
     */
    public static Boolean getBoolean(String name) {
        String val = System.getProperty(name);
        return isNull(val) ? null : Boolean.valueOf(val);
    }

    /**
     * Returns the integer value of the property, or null if it does not exist.
     *
     * @param name the name of the property
     * @return the property value, as an integerx
     */
    public static Integer getInteger(String name) {
        String val = System.getProperty(name);
        return isNull(val) ? null : Integer.valueOf(val);
    }
}