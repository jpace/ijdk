package org.incava.ijdk.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.incava.ijdk.lang.Sequence;
import org.incava.ijdk.lang.Str;

/**
 * A dynamically-sized collection, wrapping ArrayList. <code>Array</code>, a subclass of
 * <code>BaseArray</code>, has a simpler name.
 *
 * To subclass this, the subclass should pass its class as a self type. For example:
 *
 * <pre>
 *    public class StringList extends BaseArray&lt;String, StringList&gt;
 * </pre>
 *
 * @param <T> the element type of the array
 * @param <C> the class of the array subtype
 */
public abstract class BaseArray<T extends Object, C extends BaseArray<T, C>> extends ArrayList<T> implements Sequence<T, C> {
    public static final long serialVersionUID = 1L;

    /**
     * Creates an empty list.
     */
    public BaseArray() {
    }

    /**
     * Creates a list from the collection, copying its elements. <code>coll</code> can be null.
     *
     * @param coll the collection with which to populate this array
     */
    public BaseArray(Collection<? extends T> coll) {
        if (coll != null) {
            addAll(coll);
        }
    }

    /**
     * Creates the list from a varargs array.
     *
     * @param ary the ary to populate this list
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public BaseArray(T ... ary) {
        for (T el : ary) {
            add(el);
        }
    }

    /**
     * Returns a new instance of this type. The contents of this array are <em>not</em> copied.
     *
     * @return a new instance of this type
     */
    public abstract C newInstance();

    /**
     * Returns the list as a StringList, with each element converted via <code>toString()</code>.
     *
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
     *
     * @param coll the collection to compare against this array
     * @return whether this array contains any element in <code>coll</code>
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
     *
     * @param args the array to compare against this array
     * @return whether this array contains any element in <code>args</code>
     */
    @SafeVarargs
    final public boolean containsAny(T ... args) {
        for (Object obj : args) {
            if (contains(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the first element in the list, or null if the list is empty.
     *
     * @return the first element, or null if empty
     */
    public T first() {
        return isEmpty() ? null : get(0);
    }

    /**
     * Returns the last element in the list, or null if the list is empty.
     *
     * @return the last element, or null if empty
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
     *
     * @param index the index into this array
     * @return the element at the given index, or null if invalid
     */
    public T get(int index) {
        Integer idx = getIndex(index);
        return idx == null ? null : super.get(idx);
    }

    /**
     * Returns an array (of the generic subtype) containing the <code>m</code>th element through the
     * <code>n</code>th element in the list, both inclusive.
     *
     * <p> If <code>m</code> <code>n</code> is negative, then the index is the offset from the end,
     * where <code>-1</code> is the last element, <code>-2</code> is the second to last element, and
     * so on and so forth. If <code>m</code> or <code>n</code> is out of range (not within <code>0
     * ... size()</code>), then an empty set is returned.</p>
     *
     * @param fromIndex the beginning index into this array, inclusive
     * @param toIndex the ending index into this array, inclusive
     * @return the element at the given index, or null if invalid
     */
    public C get(int fromIndex, int toIndex) {
        Integer fromIdx = getIndex(fromIndex);
        Integer toIdx   = toIndex < 0 ? getIndex(toIndex) : Integer.valueOf(toIndex);
        C list = newInstance();
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
     * @param obj the object to append to this array
     * @return this instance, with the appended object
     * @see java.util.List#add
     */
    @SuppressWarnings("unchecked")
    public C append(T obj) {
        add(obj);
        return (C)this;
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
     *
     * @param index the index at which to set the element
     * @param value the value to set
     * @return the old value, or null if none
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
     *
     * @return a random element from the list
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
     *
     * @return the first element, or null if none
     */
    public T takeFirst() {
        return isEmpty() ? null : remove(0);
    }

    /**
     * Removes and returns the last element in the list. Returns null if the list is empty.
     *
     * @return the last element, or null if none
     */
    public T takeLast() {
        return isEmpty() ? null : remove(size() - 1);
    }

    /**
     * Returns a new list that contains unique elements from the list, in the same order as in this
     * list.
     *
     * @return a new list of unique elements
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
     *
     * @return a new list of non-null elements
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
     *
     * @param delimiter the delimiter with which to join the elements
     * @return the joined string
     */
    public String join(String delimiter) {
        return Str.join(this, delimiter).toString();
    }

    /**
     * Returns a new list, concatenating the other list with this one. Elements will be in the order
     * of this list, then the elements of the other.
     *
     * @param other the collection to add
     * @return the new array, containing elements of this and the other collection
     */
    public C plus(Collection<T> other) {
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
     *
     * @param other the collection to remove
     * @return the new array, containing elements of this array not in the other collection
     */
    public C minus(Collection<T> other) {
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
     * @param indices the indices to get
     * @return a new array, containing the elements for the indices
     * @see #get
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
     *
     * @param other the other collection
     * @return a new array, containing elements in both this and the other collection
     */
    public C intersection(Collection<T> other) {
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
        return sorted(null);
    }

    /**
     * Returns a copy of this array, sorted, using the given comparator
     *
     * @param comparator the comparator to sort with
     * @return a sorted array
     */
    @SuppressWarnings("unchecked")
    public C sorted(Comparator<? super T> comparator) {
        T[] copy = (T[])toArray();
        if (copy.length == 0) {
            return newInstance();
        }
        else {
            Arrays.sort(copy, comparator);
            C ary = newInstance();
            for (Object x : copy) {
                ary.add((T)x);
            }
            return ary;
        }
    }

    private Integer getIndex(Integer n) {
        return new org.incava.ijdk.util.Indexable(size()).get(n);
    }
}    
