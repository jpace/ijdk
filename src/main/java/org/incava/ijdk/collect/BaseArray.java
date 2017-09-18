package org.incava.ijdk.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.incava.ijdk.lang.Sequence;
import org.incava.ijdk.lang.Str;

/**
 * A dynamically-sized collection, wrapping ArrayList.
 */
public class BaseArray<T extends Object, C extends BaseArray<T, C>> extends ArrayList<T> implements Sequence<T> {
    public static final long serialVersionUID = 1L;

    /**
     * Creates an empty list.
     */
    public BaseArray() {
    }

    /**
     * Creates a list from the collection, copying its elements. <code>coll</code> can be null.
     */
    public BaseArray(Collection<? extends T> coll) {
        if (coll != null) {
            addAll(coll);
        }
    }

    /**
     * Creates the list from a varargs array.
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public BaseArray(T ... ary) {
        for (T el : ary) {
            add(el);
        }
    }

    /**
     * Returns a new instance of this type.
     */
    public C newInstance() {
        return null;
    }

    /**
     * Returns the list as a StringList, with each element converted via <code>toString()</code>.
     *
     * @see #toStringArrayList
     * @see org.incava.ijdk.collect.StringList
     * @return this array, as an array of strings.
     */
    public StringList toStringList() {
        StringList list = new StringList();
        for (T obj : this) {
            list.add(String.valueOf(obj));
        }
        return list;
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
    @SafeVarargs
    final public boolean containsAny(T ... args) {
        return containsAny(new BaseArray<T, C>(args));
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
     * Returns the <code>n</code>th element in the list.
     *
     * <p>Unlike <code>java.util.ArrayList#get</code>, <code>org.incavaijdk.collect.Array#get</code>
     * supports negative indices, as do languages such as Perl and Ruby.</p>
     *
     * <p>If <code>n</code> is negative, then the index is the offset from the end, where
     * <code>-1</code> is the last element, <code>-2</code> is the second to last element, and so on
     * and so forth. If <code>n</code> is out of range (not within <code>0 ... size()</code>), then
     * null is returned.</p>
     */
    public T get(int index) {
        Integer idx = getIndex(index);
        return idx == null ? null : super.get(idx);
    }

    /**
     * Returns a Array containing the <code>m</code>th element through the <code>n</code>th element
     * in the list, both inclusive.
     *
     * <p> If <code>m</code> <code>n</code> is negative, then the index is the offset from the end,
     * where <code>-1</code> is the last element, <code>-2</code> is the second to last element, and
     * so on and so forth. If <code>m</code> or <code>n</code> is out of range (not within <code>0
     * ... size()</code>), then an empty set is returned.</p>
     */
    public Array<T> get(int fromIndex, int toIndex) {
        Integer fromIdx = getIndex(fromIndex);
        Integer toIdx   = toIndex < 0 ? getIndex(toIndex) : Integer.valueOf(toIndex);
        Array<T> list = new Array<T>();
        if (fromIdx != null && toIdx != null) {
            while (fromIdx <= toIdx && fromIdx < size()) {
                list.add(get(fromIdx));
                ++fromIdx;
            }
        }
        return list;
    }

    /**
     * Adds the element to the list, similar to <code>java.util.ArrayList#add</code>, and returns
     * the list, so this method can be chained:
     *
     * <pre>
     *    Array&lt;Integer&gt; nums = Array.of(1, 2, 3);
     *    nums.append(4).append(5);
     * </pre>
     *
     * @see java.util.List#add
     */
    public BaseArray<T, C> append(T obj) {
        add(obj);
        return this;
    }

    /**
     * Sets the <code>n</code>th element in the list.
     *
     * <p>If <code>n</code> is negative, then the index is the offset from the end, where
     * <code>-1</code> is the last element, <code>-2</code> is the second to last element. Unlike
     * <code>ArrayList#set</code>, this method honors the dynamically sized aspect of the list, and
     * resizes it accordingly when <code>set(index, obj) &amp;&amp; index &gt; size()</code>.
     * However, <code>set(-index, obj)</code> (a negative index value) will result in an
     * IllegalArgumentException when <code>-index &gt; -size()</code>.</p>
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
     *
     * @param element the element to remove
     * @return whether any elements were removed from this list
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
     * Removes and returns the first element in the list. Returns null if the list is empty.
     */
    public T takeFirst() {
        return isEmpty() ? null : remove(0);
    }

    /**
     * Removes and returns the last element in the list. Returns null if the list is empty.
     */
    public T takeLast() {
        return isEmpty() ? null : remove(size() - 1);
    }

    /**
     * Returns a new list that contains unique elements from the list, in the same order as in this
     * list.
     */
    public C unique() {
        C uniqueList = newInstance();
        for (T obj : this) {
            if (!uniqueList.contains(obj)) {
                uniqueList.append(obj);
            }
        }
        return uniqueList;
    }

    /**
     * Returns a new list that contains only the non-null elements from this list, in the same
     * order as in this list.
     */
    public C compact() {
        C uniqueList = newInstance();
        for (T obj : this) {
            if (obj != null) {
                uniqueList.append(obj);
            }
        }
        return uniqueList;
    }

    /**
     * Returns the list as a String, joined by the delimiter.
     */
    public String join(String delimiter) {
        return Str.join(this, delimiter).toString();
    }

    /**
     * Returns a new list, concatenating the other list with this one. Elements will be in the order
     * of this list, then the elements of the other.
     */
    public C plus(C other) {
        C newList = newInstance();
        for (T obj : this) {
            newList.append(obj);
        }
        for (T obj : other) {
            newList.append(obj);
        }
        return newList;
    }

    /**
     * Returns a new list, containing the elements of this list that are not in the other. The
     * retained elements are in the same order as they are in this list.
     */
    public C minus(C other) {
        C newList = newInstance();
        for (T obj : this) {
            if (!other.contains(obj)) {
                newList.append(obj);
            }
        }
        return newList;
    }

    /**
     * Returns a list of the elements at the given indices. As with <code>get</code>, negative
     * indices are treated as offsets from the last element.

     * <pre>
     *     Array&lt;Integer&gt; list = Array.of(6, 7, 8);
     *     Array&lt;Integer&gt; l2 = list.elements(0);         // [ 6 ]
     *     Array&lt;Integer&gt; l3 = list.elements(0, 1);      // [ 6, 7 ]
     *     Array&lt;Integer&gt; l4 = list.elements(1, 0);      // [ 7, 6 ]
     *     Array&lt;Integer&gt; l5 = list.elements(-1, -2, 1); // [ 8, 7, 7 ]
     * </pre>
     *
     * @see Array#get
     */
    public C elements(int ... indices) {
        C elmts = newInstance();
        for (int idx : indices) {
            elmts.append(get(idx));
        }
        return elmts;
    }

    /**
     * Returns a list of elements in both this array and the other. The order is the same as in this array.
     */
    public C intersection(C other) {
        C ary = newInstance();
        for (T obj : this) {
            if (other.contains(obj)) {
                ary.add(obj);
            }
        }
        return ary;
    }

    /**
     * Returns a copy of this array, sorted.
     *
     * @return a sorted array
     */
    @SuppressWarnings("unchecked")
    public C sorted() {
        Object[] copy = toArray();
        if (copy.length == 0) {
            return newInstance();
        }
        else if (copy[0] instanceof Comparable) {
            Arrays.sort(copy);
            C ary = newInstance();
            for (Object x : copy) {
                ary.add((T)x);
            }
            return ary;
        }
        else {
            throw new RuntimeException("array contains " + copy[0].getClass() + ", which is not Comparable");
        }
    }

    private Integer getIndex(Integer n) {
        return new org.incava.ijdk.util.Indexable(size()).get(n);
    }
}    
