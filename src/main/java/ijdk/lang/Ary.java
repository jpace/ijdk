package ijdk.lang;

import java.util.*;
import org.incava.ijdk.util.EmptyIterable;
import org.incava.ijdk.util.Index;

/**
 * Wraps C-style arrays with common behavior. The wrapped array can be null.
 *
 * @see Array
 */
public class Ary<T extends Object> extends Obj {
    private final T[] ary;
    
    public Ary(T ... ary) {
        super(ary);
        this.ary = ary;
    }

    /**
     * Returns the wrapped array.
     */
    public T[] getArray() {
        return this.ary;
    }

    /**
     * Returns the wrapped array.
     */
    public T[] ary() {
        return this.ary;
    }

    /**
     * Returns the length, or zero if null.
     */
    public int length() {
        return isEmpty() ? 0 : ary().length;
    }

    /**
     * Returns the element at the given index. If the index is negative, then -1 means the last
     * element in the array, -2 is the second to last, etc. If the index is out of range, then null
     * is returned.
     */
    public T get(int index) {
        Integer idx = Index.getIndex(length(), index);
        return idx == null ? null : this.ary[idx];
    }

    /**
     * Returns the wrapped array, as an ArrayList. If the wrapped array is null, then null is
     * returned.
     */
    public ArrayList<T> asList() {
        return isNull() ? null : new ArrayList<T>(Arrays.asList(ary));
    }

    /**
     * Returns whether all elements in the wrapped array are null. Returns false if the wrapped
     * array is null.
     */
    public boolean areAllNull() {
        if (isNull()) {
            return false;
        }
        else {
            for (T obj : ary) {
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
        if (isNull()) {
            return false;
        }
        else {
            for (T obj : ary) {
                if (obj == null) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Returns an Iterable (iterator) for the wrapped array, which can be null. If the wrapped array
     * is null, an "empty" iterator will be returned.
     *
     * @return an interable, wrapping the array
     */
    public Iterable<T> iter() {
        return isNull() ? new EmptyIterable<T>() : Arrays.asList(this.ary);
    }
}
