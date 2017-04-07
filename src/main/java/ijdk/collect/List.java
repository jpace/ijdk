package ijdk.collect;

import ijdk.lang.Str;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A dynamically-sized collection, wrapping ArrayList.
 */
public class List<T extends Object> extends ArrayList<T> {
    /**
     * Creates a list from the given array.
     */
    public static <T extends Object> List<T> of(T ... ary) {
        return new List<T>(ary);
    }
    
    public static final long serialVersionUID = 1L;
    
    /**
     * Creates a List with the given initial capacity.
     *
     * @deprecated this may be removed because of collisions with <code>new List<Integer>(3)</code>.
     */
    @Deprecated
    public List(int capacity) {
        super(capacity);
    }

    /**
     * Creates an empty list.
     */
    public List() {
    }

    /**
     * Creates a list from the collection, copying its elements. <code>coll</code> can be null.
     */
    public List(Collection<? extends T> coll) {
        if (coll != null) {
            addAll(coll);
        }
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
     * Returns whether this list contains any element in the given collection.
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
     * Returns whether this list contains any element in the given array.
     */
    public boolean containsAny(T ... args) {
        return containsAny(new List<T>(args));
    }

    /**
     * Returns the first element in the list, or null if the list is empty.
     */
    public T first() {
        return isEmpty() ? null : get(0);
    }

    /**
     * Returns the last element in the list, or null if the list is empty.
     */
    public T last() {
        return isEmpty() ? null : get(size() - 1);
    }

    /**
     * Returns the <code>n</code>th element in the list. If <code>n</code> is negative, then the
     * index is the offset from the end, where <code>-1</code> is the last element, <code>-2</code>
     * is the second to last element, and so on and so forth. If <code>n</code> is out of range (not
     * within <code>0 ... size()</code>), then null is returned.
     */
    public T get(int index) {
        Integer idx = org.incava.ijdk.util.Index.getIndex(size(), index);
        return idx == null ? null : super.get(idx);
    }

    /**
     * Returns a List containing the <code>m</code>th element through the <code>n</code>th element
     * in the list, both inclusive. If <code>m</code> <code>n</code> is negative, then the index is
     * the offset from the end, where <code>-1</code> is the last element, <code>-2</code> is the
     * second to last element, and so on and so forth. If <code>m</code> or <code>n</code> is out of
     * range (not within <code>0 ... size()</code>), then an empty set is returned.
     */
    public List<T> get(int fromIndex, int toIndex) {
        Integer fromIdx = org.incava.ijdk.util.Index.getIndex(size(), fromIndex);
        Integer toIdx   = toIndex < 0 ? org.incava.ijdk.util.Index.getIndex(size(), toIndex) : Integer.valueOf(toIndex);
        List<T> list = new List<T>();
        if (fromIdx != null && toIdx != null) {
            while (fromIdx <= toIdx && fromIdx < size()) {
                list.add(get(fromIdx));
                ++fromIdx;
            }
        }
        return list;
    }

    /**
     * Adds the element to the list, and returns the list, so this method can be chained:
     *
     * <pre>
     *    List<Integer> nums = List.of(1, 2, 3);
     *    nums.append(4).append(5);
     * </pre>
     *
     * @see java.util.List#add
     */
    public List<T> append(T obj) {
        add(obj);
        return this;
    }

    /**
     * Sets the <code>n</code>th element in the list. If <code>n</code> is negative, then the index
     * is the offset from the end, where <code>-1</code> is the last element, <code>-2</code> is the
     * second to last element. Unlike <code>ArrayList#set</code>, this method honors the dynamically
     * sized aspect of the list, and resizes it accordingly when <code>set(index, obj) &amp;&amp;
     * index > size()</code>. However, <code>set(-index, obj)</code> (a negative index value) will
     * result in an IllegalArgumentException when <code>-index < -size()</code>.
     */
    public T set(int index, T value) {
        if (index < 0) {
            if (-index > size()) {
                throw new IllegalArgumentException("index: " + index + " is below the minimum " + -size());
            }
            else {
                index = size() + index;
            }
        }
        
        T val = get(index);
        while (size() - 1 < index) {
            add(null);
        }
        super.set(index, value);
        return val;
    }
    
    /**
     * Removes all occurrances of <code>element</code> from <code>list</code>, returning whether any
     * were found.
     */
    public boolean removeAll(T element) {
        boolean found = remove(element);
        boolean origFound = found;
        while (found) {
            found = remove(element);
        }
        return origFound;
    }

    /**
     * Returns a random element from the list, or null if the list is empty.
     */
    public T getRandomElement() {
        if (isEmpty()) {
            return null;
        }
        else {
            int sz = size();
            int idx = new java.util.Random().nextInt(sz);
            return get(idx);
        }
    }

    /**
     * Removes and returns the first element in the list. Returns null if empty.
     */
    public T takeFirst() {
        return isEmpty() ? null : remove(0);
    }

    /**
     * Removes and returns the last element in the list. Returns null if empty.
     */
    public T takeLast() {
        return isEmpty() ? null : remove(size() - 1);
    }

    /**
     * Returns a list containing unique elements from the list, in the order in which they occur in
     * the list.
     */
    public List<T> unique() {
        List<T> uniqueList = new List<T>();
        for (T obj : this) {
            if (!uniqueList.contains(obj)) {
                uniqueList.append(obj);
            }
        }
        return uniqueList;
    }

    /**
     * Returns a new list, containing the non-null elements from this list, in the same order.
     */
    public List<T> compact() {
        List<T> compactList = new List<T>();
        for (T obj : this) {
            if (obj != null) {
                compactList.append(obj);
            }
        }
        return compactList;
    }

    /**
     * Returns the list, joined by the delimiter.
     */
    public String join(String delimeter) {
        return Str.join(this, delimeter).toString();
    }
}
