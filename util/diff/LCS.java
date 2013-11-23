package org.incava.ijdk.util.diff;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LCS<ObjectType> {
    /**
     * The source array, AKA the "from" values.
     */
    private final List<ObjectType> from;

    /**
     * The target array, AKA the "to" values.
     */
    private final List<ObjectType> to;

    /**
     * The comparator used, if any.
     */
    private final Comparator<ObjectType> comparator;

    /**
     * Constructs an LCS for the two arrays, using the given comparator.
     */
    public LCS(ObjectType[] from, ObjectType[] to, Comparator<ObjectType> comp) {
        this(Arrays.asList(from), Arrays.asList(to), comp);
    }

    /**
     * Constructs an LCS for the two arrays, using the default comparison
     * mechanism between the objects, such as <code>equals</code> and
     * <code>compareTo</code>.
     */
    public LCS(ObjectType[] from, ObjectType[] to) {
        this(from, to, null);
    }

    /**
     * Constructs an LCS for the two collections, using the default comparison
     * mechanism between the objects, such as <code>equals</code> and
     * <code>compareTo</code>.
     */
    public LCS(List<ObjectType> from, List<ObjectType> to) {
        this(from, to, null);
    }

    /**
     * Constructs an LCS for the two collections, using the given comparator.
     */
    public LCS(List<ObjectType> from, List<ObjectType> to, Comparator<ObjectType> comp) {
        this.from = from;
        this.to = to;
        this.comparator = comp;
    }

    /**
     * Compares the two objects, using the comparator provided with the
     * constructor, if any.
     */
    protected boolean equals(Comparator<ObjectType> comparator, ObjectType x, ObjectType y) {
        return comparator == null ? x.equals(y) : comparator.compare(x, y) == 0;
    }

    /**
     * Converts the map into an array.
     */
    protected static Integer[] toArray(TreeMap<Integer, Integer> map) {
        int size = map.isEmpty() ? 0 : 1 + map.lastKey();
        Integer[] ary  = new Integer[size];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ary[entry.getKey()] = entry.getValue();
        }
        return ary;
    }

}
