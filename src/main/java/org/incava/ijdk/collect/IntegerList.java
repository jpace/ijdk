package org.incava.ijdk.collect;

import java.util.Collection;
import org.incava.ijdk.lang.Closure;
import org.incava.ijdk.lang.Pair;

public class IntegerList extends Array<Integer> {
    /**
     * Creates a new IntegerList.
     *
     * @param args the integers to populate the new array
     * @return the newly-created array
     */
    public static IntegerList of(Integer ... args) {
        return new IntegerList(args);
    }
    
    /**
     * Creates an empty IntegerList.
     *
     * @return the newly-created array
     */
    @SuppressWarnings("unchecked")
    public static IntegerList empty() {
        return new IntegerList();
    }
    
    private static final long serialVersionUID = 1405764141608264794L;
    
    /**
     * Creates an empty IntegerList.
     */
    public IntegerList() {
    }

    /**
     * Creates a IntegerList from the given collection.
     *
     * @param coll the collection from which to initialize this list
     */
    public IntegerList(Collection<Integer> coll) {
        super(coll);
    }

    /**
     * Creates a IntegerList from the given array.
     *
     * @param ary the array from which to initialize this list
     */
    public IntegerList(Integer ... ary) {
        for (Integer str : ary) {
            add(str);
        }
    }

    /**
     * Returns the minimum, or null if there are no elements in this list.
     *
     * @return the minimum
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
     * @return the maximum
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
     * Returns the sum of all non-null elements, or 0 if there are no elements in the list.
     *
     * @return the sum
     */
    public Integer sum() {
        if (isEmpty()) {
            return 0;
        }
        
        Closure<Integer, Pair<Integer, Integer>> closure = new Closure<Integer, Pair<Integer, Integer>>() {
                public Integer execute(Pair<Integer, Integer> nums) {
                    Integer s = nums.first();
                    Integer n = nums.second();

                    if (s == null) {
                        s = 0;
                    }
                    if (n == null) {
                        return s;
                    }
                    else {
                        return s + n;
                    }
                }
            };
        return apply(closure);
    }

    /**
     * Returns the value from the closure applied to all elements, or null if there are no elements
     * in this list.
     *
     * @param closure the closure to be appied to each element
     * @return the result
     */
    public Integer apply(Closure<Integer, Pair<Integer, Integer>> closure) {
        Integer num = null;
        for (int idx = 0; idx < size(); ++idx) {
            num = closure.execute(Pair.of(num, get(idx)));
        }
        return num;
    }
}
