package ijdk.collect;

import java.util.Collection;
import java.util.ArrayList;

/**
 * A dynamically-sized collection, wrapping ArrayList.
 */
public class List<T extends Object> extends ArrayList<T> {
    public static <T extends Object> List<T> of(T ... ary) {
        return new List<T>(ary);
    }
    
    public static final long serialVersionUID = 1L;
    
    /**
     * Creates a List with the given initial capacity.
     */
    public List(int capacity) {
        super(capacity);
    }

    /**
     * Creates an empty list.
     */
    public List() {
    }

    /**
     * Creates a list from the collection, copying its elements.
     */
    public List(Collection<? extends T> coll) {
        super(coll);
    }

    /**
     * Creates the list from a varargs array.
     */
    public List(T ... ary) {
        for (T el : ary) {
            add(el);
        }
    }

    /**
     * Returns the list as a StringList.
     */
    public StringList toStringList() {
        StringList list = new StringList();
        for (T obj : this) {
            list.add(String.valueOf(obj));
        }
        return list;
    }

    /**
     * Returns the list as a list of strings.
     */
    public ArrayList<String> toStringArrayList() {
        return toStringList();
    }

    /**
     * Returns whewther this list contains any element in the given collection.
     */
    public boolean containsAny(Collection<T> coll) {
        for (Object obj : coll) {
            if (contains(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whewther this list contains any element in the given array.
     */
    public boolean containsAny(T ... args) {
        return containsAny(new List<T>(args));
    }
}
