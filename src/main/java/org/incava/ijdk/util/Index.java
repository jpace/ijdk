package org.incava.ijdk.util;

public class Index {
    /**
     * Converts <code>index</code>, which can be positive or negative, to an offset within the given
     * <code>size</code>, representing the length of a collection or string. A negative
     * <code>index</code> will result in the distance from the end of the list, with <code>index
     * -1</code> meaning the last element in the list, -2 the second to last, etc. Returns null if
     * the resulting index is out of range. Returns null is <code>size</code> is 0 or null. Returns
     * null if the resulting value is out of range (such as <code>getIndex(2, -3)</code> or
     * <code>getIndex(1, 2))</code>.
     *
     * @param size the size into which the index is applied
     * @param index the index within the size
     * @return the offset value for the index for the given size
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
