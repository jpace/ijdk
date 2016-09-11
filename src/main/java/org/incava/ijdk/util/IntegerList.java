package org.incava.ijdk.util;

import java.util.*;
import org.incava.ijdk.lang.Closure;
import org.incava.ijdk.lang.Pair;

public class IntegerList extends ArrayList<Integer> {
    /**
     * Creates an empty IntegerList.
     */
    public IntegerList() {
    }

    /**
     * Creates a IntegerList from the given collection.
     */
    public IntegerList(Collection<Integer> coll) {
        super(coll);
    }

    /**
     * Creates a IntegerList from the given array.
     */
    public IntegerList(Integer ... ary) {
        for (Integer str : ary) {
            add(str);
        }
    }

    /**
     * Returns the minimum, or null if there are no elements in this list.
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
     */
    public Integer apply(Closure<Integer, Pair<Integer, Integer>> closure) {
        Integer num = null;
        for (int idx = 0; idx < size(); ++idx) {
            num = closure.execute(Pair.create(num, get(idx)));
        }
        return num;
    }
}
