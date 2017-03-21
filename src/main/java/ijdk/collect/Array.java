package ijdk.collect;

import ijdk.lang.Obj;
import java.util.Arrays;
import org.incava.ijdk.util.EmptyIterable;
import org.incava.ijdk.util.Index;

/**
 * Wraps C-style arrays with common behavior. The wrapped array can be null.
 */
public class Array<T extends Object> extends Obj {
    private final T[] ary;
    
    public Array(T ... ary) {
        super(ary);
        this.ary = ary;
    }

    /**
     * Returns the wrapped array.
     */
    public T[] getArray() {
        return ary();
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
     * Returns the wrapped array, as a List (dynamically sized collection, with a copy of the
     * wrapped array). If the wrapped array is null, then null is returned.
     */
    public List<T> asList() {
        return isNull() ? null : new List<T>(Arrays.asList(ary));
    }

    /**
     * Returns the wrapped array, as a fixed-size list. If the wrapped array is null, then null is returned.
     */
    public java.util.List<T> asFixedSizeList() {
        return isNull() ? null : Arrays.asList(ary);
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