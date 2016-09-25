package ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.incava.ijdk.io.StdOut;
import org.incava.ijdk.lang.ArrayExt;
import org.incava.ijdk.lang.ObjectExt;
import org.incava.ijdk.lang.StringExt;
import org.incava.ijdk.util.EmptyIterable;
import org.incava.ijdk.util.NumIterable;

public class ICore {
    /**
     * Returns whether the object is non-null and, if it is a collection or a
     * string, has a length greater than zero.
     *
     * @see #isEmpty
     * @see #isFalse
     * @see org.incava.ijdk.lang.ObjectExt#isTrue
     */
    public static boolean isTrue(Object obj) {
        return ObjectExt.isTrue(obj);
    }

    /**
     * Returns whether the array is not null and is not of zero length.
     *
     * @see #isEmpty
     * @see #isFalse
     */
    public static boolean isTrue(Object ... objs) {
        return ObjectExt.isTrue(objs);
    }

    /**
     * Returns whether the object is null or is a string or collection of zero length.
     *
     * @see #isEmpty
     */
    public static boolean isFalse(Object obj) {
        return ObjectExt.isFalse(obj);
    }

    /**
     * Returns whether the array is not null and is not of zero length.
     *
     * @see #isEmpty
     */
    public static boolean isFalse(Object ... objs) {
        return ObjectExt.isFalse(objs);
    }

    /**
     * Returns whether the object is null or is a string or collection of zero length.
     *
     * @see #isEmpty
     */
    public static boolean isEmpty(Object obj) {
        return ObjectExt.isEmpty(obj);
    }

    /**
     * Returns whether the string is null or of zero length.
     *
     * @see #isEmpty
     */
    public static boolean isEmpty(String str) {
        return StringExt.isEmpty(str);
    }

    /**
     * Returns whether the object is null. This method provides an alternative
     * syntax than "if (obj == null)".
     *
     * @see #isNotNull
     */
    public static boolean isNull(Object obj) {
        return Obj.isNull(obj);
    }

    /**
     * Returns whether the object is not null. This method provides an
     * alternative syntax than "if (obj != null)".
     *
     * @see #isNull
     */
    public static boolean isNotNull(Object obj) {
        return Obj.isNotNull(obj);
    }

    /**
     * Returns the first parameter if it is true, and otherwise returns the
     * default value.
     *
     * The following two statements are equivalent:
     *
     * <pre>
     *     String statusName = userName != null ? userName : "guest";
     *     String statusName = IUtil.or(userName, "guest");
     * </pre>
     *
     * This method is best for simple objects, since the default value is
     * evaluated before the method is executed. For example, in the second block
     * below, <code>db.initialize()</code> will be called.
     *
     * <pre>
     *     String status = IUtil.or(db.status(), db.initialize());
     * </pre>
     *
     * <pre>
     *     String status = db.status() != null ? db.status() : db.initialize());
     * </pre>
     *
     * @see #elvis
     * @see #isTrue
     */
    public static <T> T or(T a, T b) {
        return isTrue(a) ? a : (isTrue(b) ? b : null);
    }

    /**
     * If both parameters evaluate to true (via <code>isTrue</code>), then the
     * second value is returned. This results in code such as:
     * 
     * <pre>
     *    String statusName = userName != null &amp;&amp; lastName != null ? lastName : null;
     *    String statusName = IUtil.and(userName, lastName);
     * </pre>
     *
     * @see #or
     * @see #isTrue
     */
    public static <T> T and(T a, T b) {
        return isTrue(a) ? (isTrue(b) ? b : null) : null;
    }

    /**
     * An alias for the <code>or</code> method. So-named for the "?:" operator
     * in Groovy.
     *
     * @see #or
     */
    public static <T> T elvis(T obj, T defVal) {
        return or(obj, defVal);
    }

    /**
     * Returns the last parameter, if all parameters evaluate to true (via
     * <code>isTrue</code>).
     *
     * @see #or
     * @see #isTrue
     */
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
     * Returns the first parameter that evaluates to true (via
     * <code>isTrue</code>).
     *
     * @see #or
     * @see #isTrue
     */
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
     */
    public static <T> Iterable<T> iter(T[] ary) {
        return ArrayExt.iter(ary);
    }

    /**
     * Returns an Iterable (iterator) for the collection, which can be null. If
     * <code>coll</code> is null, an "empty" iterator will be returned.
     */
    public static <T> Iterable<T> iter(Iterable<T> coll) {
        return coll == null ? new EmptyIterable<T>() : coll;
    }

    /**
     * Returns an iterator to be executed <code>num</code> times.
     */
    public static NumIterable iter(int num) {
        return new NumIterable(num);
    }

    /**
     * Returns a list (an ArrayList) initialized with the given elements. The
     * list returned is of dynamic size (unlike <code>Arrays.asList(...)</code>,
     * which returns a fixed-size, immutable array). The following two blocks
     * are equivalent:
     *
     * <pre>
     *     List&lt;String&gt; names = new ArrayList&lt;String&gt;(Arrays.asList("kevin", "jacob", "isaac"));
     *     names.add("henry");
     * </pre>
     * <pre>
     *     List&lt;String&gt; names = list("kevin", "jacob", "isaac");
     *     names.add("henry");
     * </pre>
     */
    public static <T> List<T> list(T ... elements) {
        List<T> ary = new ArrayList<T>();
        for (T element : elements) {
            ary.add(element);
        }
        return ary;
    }

    /**
     * Writes to standard output. Returns true, so this can be used inside conditionals.
     */
    public static boolean puts(Object obj) {
        return StdOut.puts(obj);
    }

    /**
     * Writes to standard output. Returns true, so this can be used inside conditionals.
     */
    public static boolean printf(String fmt, Object ... args) {
        return StdOut.printf(fmt, args);
    }

    /**
     * Writes to standard output. Returns true, so this can be used inside conditionals.
     */
    public static boolean println(Object obj) {
        return StdOut.println(obj);
    }
}
