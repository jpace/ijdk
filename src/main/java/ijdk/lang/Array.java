package ijdk.lang;

import java.util.Collection;
import java.util.ArrayList;

/**
 * Wraps ArrayList.
 */
public class Array<T extends Object> extends ArrayList<T> {
    public static final long serialVersionUID = 1L;
    
    public Array() {
    }

    public Array(Collection<? extends T> coll) {
        super(coll);
    }

    public Array<String> toStringArray() {
        Array<String> strAry = new Array<String>();
        for (T obj : this) {
            strAry.add(String.valueOf(obj));
        }
        return strAry;
    }
}
