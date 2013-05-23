package org.incava.ijdk.util.diff;

/**
 * Represents a difference, as used in <code>Diff</code>. A difference consists
 * of two pairs of starting and ending points, each pair representing either the
 * "from" or the "to" collection passed to <code>Diff</code>. If an ending point
 * is -1, then the difference was either a deletion or an addition. For example,
 * if <code>getDeletedEnd()</code> returns -1, then the difference represents an
 * addition.
 */
public class Difference {
    public static final Integer NONE = -1;
    
    /**
     * The point at which the deletion starts.
     */
    private Integer delStart = NONE;

    /**
     * The point at which the deletion ends.
     */
    private Integer delEnd = NONE;

    /**
     * The point at which the addition starts.
     */
    private Integer addStart = NONE;

    /**
     * The point at which the addition ends.
     */
    private Integer addEnd = NONE;

    /**
     * Creates the difference for the given start and end points for the
     * deletion and addition.
     */
    public Difference(Integer delStart, Integer delEnd, Integer addStart, Integer addEnd) {
        this.delStart = delStart;
        this.delEnd   = delEnd;
        this.addStart = addStart;
        this.addEnd   = addEnd;
    }

    /**
     * The point at which the deletion starts, if any. A value equal to
     * <code>NONE</code> means this is an addition.
     */
    public Integer getDeletedStart() {
        return delStart;
    }

    /**
     * The point at which the deletion ends, if any. A value equal to
     * <code>NONE</code> means this is an addition.
     */
    public Integer getDeletedEnd() {
        return delEnd;
    }

    /**
     * The point at which the addition starts, if any. A value equal to
     * <code>NONE</code> means this must be a deletion.
     */
    public Integer getAddedStart() {
        return addStart;
    }

    /**
     * The point at which the addition ends, if any. A value equal to
     * <code>NONE</code> means this must be a deletion.
     */
    public Integer getAddedEnd() {
        return addEnd;
    }

    /**
     * Sets the point as deleted. The start and end points will be modified to
     * include the given line.
     */
    public void setDeleted(int line) {
        delStart = Math.min(line, delStart);
        delEnd   = Math.max(line, delEnd);
    }

    /**
     * Sets the point as added. The start and end points will be modified to
     * include the given line.
     */
    public void setAdded(int line) {
        addStart = Math.min(line, addStart);
        addEnd   = Math.max(line, addEnd);
    }

    /**
     * Compares this object to the other for equality. Both objects must be of
     * type Difference, with the same starting and ending points.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Difference) {
            Difference other = (Difference)obj;
            return (delStart == other.delStart && 
                    delEnd   == other.delEnd && 
                    addStart == other.addStart && 
                    addEnd   == other.addEnd);
        }
        else {
            return false;
        }
    }

    public int hashCode() {
        int hash = 1;
        for (Integer i : new Integer[] { delStart, delEnd, addStart, addEnd }) {
            hash = hash * 17 + i.hashCode();
        }
        return hash;
    }

    /**
     * Returns a string representation of this difference.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("del: [").append(delStart).append(", ").append(delEnd).append(']');
        sb.append(' ');
        sb.append("add: [").append(addStart).append(", ").append(addEnd).append(']');
        return sb.toString();
    }
}
