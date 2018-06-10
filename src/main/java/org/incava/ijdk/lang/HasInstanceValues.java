package org.incava.ijdk.lang;

import org.incava.ijdk.collect.Array;

public interface HasInstanceValues {
    /**
     * Returns the values that comprise this object in terms of equality and hash codes.
     *
     * @return an array of the values
     */
    Array<Object> getInstanceValues();
}
