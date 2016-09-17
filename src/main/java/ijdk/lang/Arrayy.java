package ijdk.lang;

import java.util.*;

/**
 * Wraps C-style arrays with common behavior.
 */
public class Arrayy {
    private final Object[] ary;
    
    public Arrayy(Object ... ary) {
        this.ary = ary;
    }

    /**
     * Returns the wrapped array.
     */
    public Object[] getArray() {
        return this.ary;
    }

    /**
     * Returns the wrapped array.
     */
    public Object[] ary() {
        return this.ary;
    }

    /**
     * Returns the wrapped array, as an ArrayList. If the wrapped array is null, then null is
     * returned.
     */
    public ArrayList<Object> asList() {
        return this.ary == null ? null : new ArrayList<Object>(Arrays.asList(ary));
    }

    /**
     * Returns whether all elements in the wrapped array are null. Returns false if the wrapped
     * array is null.
     */
    public boolean areAllNull() {
        if (this.ary == null) {
            return false;
        }
        else {
            for (Object obj : ary) {
                if (obj != null) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Returns whether any elements in the wrapped array is null. Returns false if the wrapped
     * array is null.
     */
    public boolean isAnyNull() {
        if (this.ary == null) {
            return false;
        }
        else {
            for (Object obj : ary) {
                if (obj == null) {
                    return true;
                }
            }
            return false;
        }
    }
}

