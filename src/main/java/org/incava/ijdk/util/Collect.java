package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Collects a collection into a collection. That is, applies quasi-closures to
 * the collection to transform it into a new one.
 */
public abstract class Collect<FromType, ToType> extends ArrayList<ToType> {
    private static final long serialVersionUID = 9151352237292973573L;
    
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
     * Creates a new collection, where the condition passes the condition.
     *
     * @param ary the array
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
     * @return whether the condition is satisfied
     */
    public abstract boolean where(FromType obj);
    
    /**
     * Returns the object to add to the collection.
     *
     * @param obj An object from the collection passed to the constructor.
     * @return the object after being applied by the block
     */
    public abstract ToType block(FromType obj);
}
