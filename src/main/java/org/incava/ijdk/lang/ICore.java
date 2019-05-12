package org.incava.ijdk.lang;

import java.util.Iterator;
import java.util.TreeMap;
import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.Iterate;
import org.incava.ijdk.collect.StringArray;
import org.incava.ijdk.io.StdOut;
import org.incava.ijdk.lang.Objects;
import org.incava.ijdk.lang.Strings;

public class ICore {
    /**
     * Returns whether the object is non-null and, if it is a collection or a
     * string, has a length greater than zero.
     *
     * @param obj the object to check
     * @see #isEmpty
     * @see #isFalse
     * @see org.incava.ijdk.lang.Objects#isTrue
     * @return whether the object is true
     */
    public static boolean isTrue(Object obj) {
        return Objects.isTrue(obj);
    }

    /**
     * Returns whether the array is not null and is not of zero length.
     *
     * @param objs the array
     * @see #isEmpty
     * @see #isFalse
     * @return whether the array is true
     */
    public static boolean isTrue(Object ... objs) {
        return Objects.isTrue(objs);
    }

    /**
     * Returns whether the object is null or is a string or collection of zero length.
     *
     * @param obj the object to check
     * @see #isEmpty
     * @return whether the object is false
     */
    public static boolean isFalse(Object obj) {
        return Objects.isFalse(obj);
    }

    /**
     * Returns whether the array is not null and is not of zero length.
     *
     * @param objs the array
     * @see #isEmpty
     * @return whether the array is false
     */
    public static boolean isFalse(Object ... objs) {
        return Objects.isFalse(objs);
    }

    /**
     * Returns whether the object is null or is a string or collection of zero length.
     *
     * @param obj the object to check
     * @see #isEmpty
     * @return whether the object is empty
     */
    public static boolean isEmpty(Object obj) {
        return Objects.isEmpty(obj);
    }

    /**
     * Returns whether the string is null or of zero length.
     *
     * @param str the string to check
     * @see #isEmpty
     * @return whether the string is empty
     */
    public static boolean isEmpty(String str) {
        return Strings.isEmpty(str);
    }

    /**
     * Returns whether the object is null. This method provides an alternative
     * syntax than "if (obj == null)".
     *
     * @param obj the object to check
     * @see #isNotNull
     * @return whether the object is null
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * Returns whether the object is not null. This method provides an
     * alternative syntax than "if (obj != null)".
     *
     * @param obj the object to check
     * @see #isNull
     * @return whether the object is not null
     */
    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    /**
     * Returns the first parameter if it is true, and otherwise returns the default value.
     *
     *
     * The following two statements are equivalent:
     *
     * <pre>
     *     String statusName = userName != null ? userName : "guest";
     *     String statusName = IUtil.or(userName, "guest");
     * </pre>
     *
     * This method is best for simple objects, since the default value is evaluated before the
     * method is executed. For example, in the first block below, <code>db.initialize()</code> will
     * be called.
     *
     * <pre>
     *     String status = IUtil.or(db.status(), db.initialize());
     * </pre>
     *
     * <pre>
     *     String status = db.status() != null ? db.status() : db.initialize());
     * </pre>
     *
     * @param <T> the type of a and b
     * @param a the value returned if true
     * @param b the value returned if a is not true, and b is true
     * @see #isTrue
     * @return a if true, b if true, else null
     */
    public static <T> T or(T a, T b) {
        return isTrue(a) ? a : (isTrue(b) ? b : null);
    }

    /**
     * If both parameters evaluate to true (via <code>isTrue</code>), then the second value is
     * returned. This results in code such as:
     *
     * <pre>
     *    String statusName = userName != null &amp;&amp; lastName != null ? lastName : null;
     *    String statusName = IUtil.and(userName, lastName);
     * </pre>
     *
     * @param <T> the type of a and b
     * @param a the value returned if true
     * @param b the value returned if a is not true, and b is true
     * @see #or
     * @see #isTrue
     * @return b if a and b are true, else null
     */
    public static <T> T and(T a, T b) {
        return isTrue(a) ? (isTrue(b) ? b : null) : null;
    }

    /**
     * Returns the last parameter, if all parameters evaluate to true (via <code>isTrue</code>).
     *
     * @param <T> the type of operands
     * @param operands the array of type T
     * @see #or
     * @see #isTrue
     * @return the last element in operands if all are true
     */
    @SafeVarargs
    public static <T> T and(T ... operands) {
        if (isFalse(operands)) {
            return null;
        }
        for (T op : operands) {
            if (isFalse(op)) {
                return null;
            }
        }
        return operands[operands.length - 1];
    }

    /**
     * Returns the first parameter that evaluates to true (via <code>isTrue</code>).
     *
     * @param <T> the type of operands
     * @param operands the array of type T
     * @see #or
     * @see #isTrue
     * @return the first element in operands that is true
     */
    @SafeVarargs
    public static <T> T or(T ... operands) {
        if (isFalse(operands)) {
            return null;
        }
        for (T op : operands) {
            if (isTrue(op)) {
                return op;
            }
        }
        return null;
    }

    /**
     * Returns an Iterable (iterator) for the C-style array, which can be null.
     * If <code>ary</code> is null, an "empty" iterator will be returned.
     *
     * @param <T> the type of ary elements
     * @param ary the array to iterate over; can be null
     * @return an iterator for the array
     */
    public static <T> Iterable<T> iter(T[] ary) {
        return Iterate.over(ary);
    }

    /**
     * Returns an Iterable (iterator) for the collection, which can be null. If
     * <code>coll</code> is null, an "empty" iterator will be returned.
     *
     * @param <T> the type of elements in the collection
     * @param coll the collection to iterate over; can be null
     * @return an iterator for the collection
     */
    public static <T> Iterable<T> iter(Iterable<T> coll) {
        return Iterate.over(coll);
    }

    /**
     * Returns an iterator to be executed <code>num</code> times.
     *
     * @param num the number of times to iterate
     * @return an iterator
     */
    public static Iterable<Integer> iter(int num) {
        return Iterate.count(num);
    }

    /**
     * Returns a list (a Array) initialized with the given elements. The list returned is of
     * dynamic size (unlike <code>Arrays.asList(...)</code>, which returns a fixed-size, immutable
     * array). The following two blocks are equivalent:
     *
     * <pre>
     *     List&lt;String&gt; names = new Array&lt;String&gt;(Arrays.asList("kevin", "jacob", "isaac"));
     *     names.add("henry");
     * </pre>
     * <pre>
     *     List&lt;String&gt; names = list("kevin", "jacob", "isaac");
     *     names.add("henry");
     * </pre>
     *
     * @param <T> the type of elements
     * @param elements the array of type T
     * @return the Array
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> Array<T> list(T ... elements) {
        return Array.of(elements);
    }

    /**
     * Returns an string list, which can be empty. This exists as an internate to
     * <code>ICore.&lt;String&gt;list()</code> for empty string lists.
     *
     * @param elements the elements for the new array
     * @return the StringArray
     */
    @SuppressWarnings("unchecked")
    public static StringArray strlist(String ... elements) {
        return new StringArray(elements);
    }

    /**
     * Returns an integer list, which can be empty. This exists as an internate to
     * <code>ICore.&lt;Integer&gt;list()</code> for empty integer lists.
     *
     * @param elements the elements for the new array
     * @return the Integer array
     */
    @SuppressWarnings("unchecked")
    public static Array<Integer> intlist(Integer ... elements) {
        return ICore.list(elements);
    }

    /**
     * Writes to standard output. Returns true, so this can be used inside conditionals.
     *
     * @param obj the object to write
     * @return true always
     */
    public static boolean puts(Object obj) {
        return StdOut.puts(obj);
    }

    /**
     * Writes to standard output. Returns true, so this can be used inside conditionals.
     *
     * @param fmt the format
     * @param args the arguments for the format
     * @return true always
     */
    public static boolean printf(String fmt, Object ... args) {
        return StdOut.printf(fmt, args);
    }

    /**
     * Writes to standard output. Returns true, so this can be used inside conditionals.
     *
     * @param obj the object to write
     * @return true always
     */
    public static boolean println(Object obj) {
        return StdOut.println(obj);
    }

    /**
     * Creates an empty tree map.
     *
     * @param <KeyType> the type of keys
     * @param <ValueType> the type of values
     * @return an empty TreeMap
     */
    public static <KeyType, ValueType> TreeMap<KeyType, ValueType> map() {
        return new TreeMap<KeyType, ValueType>();
    }

    /**
     * Creates a map with one key/value pair.
     *
     * @param <KeyType> the type of keys
     * @param <ValueType> the type of values
     * @param k1 the first key
     * @param v1 the first value
     * @return a TreeMap containing the k1 =&gt; v1 mapping
     */
    public static <KeyType, ValueType> TreeMap<KeyType, ValueType> map(KeyType k1, ValueType v1) {
        TreeMap<KeyType, ValueType> map = map();
        map.put(k1, v1);
        return map;
    }

    /**
     * Creates a map with two key/value pairs.
     *
     * @param <KeyType> the type of keys
     * @param <ValueType> the type of values
     * @param k1 the first key
     * @param v1 the first value
     * @param k2 the second key
     * @param v2 the second value
     * @return a TreeMap containing the k1 =&gt; v1 and k2 =&gt; v2 mappings
     */
    public static <KeyType, ValueType> TreeMap<KeyType, ValueType> map(KeyType k1, ValueType v1, KeyType k2, ValueType v2) {
        TreeMap<KeyType, ValueType> map = map(k1, v1);
        map.put(k2, v2);
        return map;
    }

    /**
     * Converts varargs to T[]. <code>ary("one", 1)</code> is shorter than <code>new Object[] {
     * "one", 1 }</code>. For Object arrays (i.e., to work around the "unchecked generic array
     * creation" warning), use <code>Common#objary</code>.
     *
     * @param <T> the type of elements of the array
     * @param args the elements of the array
     * @return an array containing the arguments
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> T[] ary(T ... args) {
        return args;
    }
}
