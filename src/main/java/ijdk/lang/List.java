package ijdk.lang;

import java.util.Collection;
import java.util.ArrayList;

/**
 * A dynamically-sized collection, wrapping ArrayList.
 */
public class List<T extends Object> extends ArrayList<T> {
    public static final long serialVersionUID = 1L;
    
    /**
     * Creates a List with the given initial capacity.
     */
    public List(int capacity) {
        super(capacity);
    }
    
    public List() {
    }

    public List(Collection<? extends T> coll) {
        super(coll);
    }

    public List(T ... ary) {
        for (T el : ary) {
            add(el);
        }
    }
    
    public StringList toStringList() {
        StringList list = new StringList();
        for (T obj : this) {
            list.add(String.valueOf(obj));
        }
        return list;
    }
    
    public ArrayList<String> toStringArray() {
        return toStringList();
    }
}
