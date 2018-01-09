package org.incava.ijdk.util;

/**
 * Wraps index calculations of a sequence similar to Ruby, where index <code>-2</code> means the
 * next-to-last element in a list.
 */
public class Indexable {
    private Integer size;

    /**
     * Creates an instance.
     *
     * @param size the size against which the index is applied
     */
    public Indexable(Integer size) {
        this.size = size;
    }
    
    /**
     * <p>Converts <code>index</code>, which can be positive or negative, to an offset within the
     * given <code>size</code>, representing the length of a list (including strings). A negative
     * <code>index</code> will result in the distance from the end of the list, with <code>index
     * -1</code> meaning the last element in the list, -2 the second to last, etc. Returns null
     * if any::</p>
     *
     * <ul>
     *     <li>the resulting index is out of range (&lt; 0 or &ge; size)</li>
     *     <li><code>size</code> is 0 or null</li>
     *     <li>the resulting value is out of range (such as <code>getIndex(2, -3)</code> or <code>getIndex(1, 2))</code></li>
     * </ul>
     *
     * <p>Examples:</p>
     *
     * <pre>
     *     Indexable idx = new Indexable(null);  
     *     idx.get(null, 0);  // == null
     *
     *     idx = new Indexable(0);  
     *     idx.get(0, 0);     // == null
     *
     *     idx = new Indexable(4);  
     *     idx.get(0);     // == 0
     *     idx.get(1);     // == 1
     *     idx.get(3);     // == 3
     *     idx.get(4);     // == null
     *     idx.get(-1);    // == 3
     *     idx.get(-2);    // == 2
     *     idx.get(-3);    // == 1
     *     idx.get(-4);    // == 0
     *     idx.get(-5);    // == null
     * </pre>
     *
     * @param index the index within the size
     * @return the offset value for the index for the given size
     */
    public Integer get(Integer index) {
        if (this.size == null || index == null) {
            return null;
        }
        else {
            int idx = toIndex(index);
            return idx >= 0 && idx < this.size ? idx : null;
        }
    }

    private Integer toIndex(Integer index) {
        return index < 0 ? this.size + index : index;
    }
}
