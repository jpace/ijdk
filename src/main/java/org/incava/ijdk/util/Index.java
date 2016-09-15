package org.incava.ijdk.util;

public class Index {
    /**
     * Converts the index, which can be positive or negative, to one within the given size,
     * representing the length of a collection or string. A negative index will result in the
     * distance from the end of the list, with index -1 meaning the last element in the list, -2 the
     * second to last, etc. Returns null if the resulting index is out of range. Returns null is
     * <code>size</code> is 0 or null. Returns null if the resulting value is out of range (such as
     * getIndex(4, -5) or getIndex(1, 2)).
     */
    public static Integer getIndex(Integer size, Integer index) {
        if (size == null || index == null) {
            return null;
        }
        else {
            int idx = index < 0 ? size + index : index;
            return idx < 0 || idx >= size ? null : idx;
        }
    }
}
