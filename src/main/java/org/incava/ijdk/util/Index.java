package org.incava.ijdk.util;

/**
 * Wraps index calculations similar to Ruby, where <code>index -2</code> means the next-to-last
 * element in a list.
 */
public class Index {
    /**
     * <p>Converts <code>index</code>, which can be positive or negative, to an offset within the
     * given <code>size</code>, representing the length of a list (including strings). A negative
     * <code>index</code> will result in the distance from the end of the list, with <code>index
     * -1</code> meaning the last element in the list, -2 the second to last, etc. Returns null
     * if:</p>
     *
     * <ul>
     *     <li>the resulting index is out of range (&lt; 0 or &ge; size)</li>
     *     <li><code>size</code> is 0 or null</li>
     *     <li>the resulting value is out of range (such as <code>getIndex(2, -3)</code> or
     * <code>getIndex(1, 2))</code></li>
     * </ul>
     *
     * <p>Examples:</p>
     *
     * <pre>
     *     getIndex(null, 0);  // == null
     *     getIndex(0, 0);     // == null
     *     getIndex(4, 0);     // == 0
     *     getIndex(4, 1);     // == 1
     *     getIndex(4, 3);     // == 3
     *     getIndex(4, 4);     // == null
     *     getIndex(4, -1);    // == 3
     *     getIndex(4, -2);    // == 2
     *     getIndex(4, -3);    // == 1
     *     getIndex(4, -4);    // == 0
     *     getIndex(4, -5);    // == null
     * </pre>
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
