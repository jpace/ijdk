package ijdk.lang;

import java.util.Collection;
import java.util.ArrayList;

/**
 * Wraps ArrayList.
 */
public class Array<T extends Object> extends ArrayList<T> {
    public Array() {
    }

    public Array(Collection<? extends T> coll) {
        super(coll);
    }
}
