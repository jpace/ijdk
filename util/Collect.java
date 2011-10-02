package org.incava.ijdk.util;

import java.util.*;


/**
 * Collects a collections into a collection.
 */
public abstract class Collect<FromType, ToType> extends ArrayList<ToType> {
    
    /**
     * Creates a new collection, where the condition passes the condition.
     *
     * @param c The collection from which to build the new collection.
     */
    public Collect(Collection<FromType> c) {
        for (FromType obj : c) {
            if (where(obj)) {
                add(block(obj));
            }
        }
    }

    /**
     * Ditto, but for real arrays.
     */
    public Collect(FromType[] ary) {
        for (FromType obj : ary) {
            if (where(obj)) {
                add(block(obj));
            }
        }
    }

    /**
     * Must be defined to return where the given object satisfies the condition.
     *
     * @param obj An object from the collection passed to the constructor.
     */
    public abstract boolean where(FromType obj);
    
    /**
     * Returns the object to add to the collection.
     *
     * @param obj An object from the collection passed to the constructor.
     */
    public abstract ToType block(FromType obj);
}
