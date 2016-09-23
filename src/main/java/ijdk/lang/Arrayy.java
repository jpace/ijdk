package ijdk.lang;

import java.util.*;
import org.incava.ijdk.util.EmptyIterable;

/**
 * Wraps C-style arrays with common behavior. The wrapped array can be null.
 */
public class Arrayy<T extends Object> extends Objectt {
    private final T[] ary;
    
    public Arrayy(T ... ary) {
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
