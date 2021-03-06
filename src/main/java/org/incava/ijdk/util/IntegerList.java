package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.Collection;
import org.incava.ijdk.lang.Closure;
import org.incava.ijdk.lang.Pair;

public class IntegerList extends ArrayList<Integer> {
    private static final long serialVersionUID = 1405764141608264794L;
    
    /**
     * Creates an empty IntegerList.
     */
    public IntegerList() {
    }

    /**
     * Creates a IntegerList from the given collection.
     *
     * @param coll the collection of integers
     */
    public IntegerList(Collection<Integer> coll) {
        super(coll);
    }

    /**
     * Creates a IntegerList from the given array.
     *
     * @param ary the array of integers
     */
    public IntegerList(Integer ... ary) {
        for (Integer str : ary) {
            add(str);
        }
    }

    /**
     * Returns the minimum, or null if there are no elements in this list.
     *
     * @return the minimum value
     */
    public Integer minimum() {
        Closure<Integer, Pair<Integer, Integer>> closure = new Closure<Integer, Pair<Integer, Integer>>() {
                public Integer execute(Pair<Integer, Integer> nums) {
                    return nums.first() == null ? nums.second() : Math.min(nums.first(), nums.second());
                }
            };
        return apply(closure);
    }

    /**
     * Returns the maximum, or null if there are no elements in this list.
     *
     * @return the maximum value
     */
    public Integer maximum() {
        Closure<Integer, Pair<Integer, Integer>> closure = new Closure<Integer, Pair<Integer, Integer>>() {
                public Integer execute(Pair<Integer, Integer> nums) {
                    return nums.first() == null ? nums.second() : Math.max(nums.first(), nums.second());
                }
            };
        return apply(closure);
    }

    /**
     * Returns the value from the closure applied to all elements, or null if there are no elements
     * in this list.
     *
     * @param closure the closure to apply
     * @return the value returned by the last closure
     */
    public Integer apply(Closure<Integer, Pair<Integer, Integer>> closure) {
        Integer num = null;
        for (int idx = 0; idx < size(); ++idx) {
            num = closure.execute(Pair.of(num, get(idx)));
        }
        return num;
    }
}
