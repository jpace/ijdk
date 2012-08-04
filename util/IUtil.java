package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class IUtil {
    /**
     * Returns whether the object is non-null and, if it is a collection or a
     * string, has a length greater than zero.
     *
     * @see #isEmpty
     * @see #isFalse
     */
    public static boolean isTrue(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * Returns whether the object is null or is a string or collection of zero length.
     *
     * @see #isEmpty
     */
    public static boolean isFalse(Object obj) {
        return isEmpty(obj);
    }

    /**
     * Returns whether the object is null or is a string or collection of zero length.
     *
     * @see #isEmpty
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        else if (obj instanceof String) {
            return ((String)obj).isEmpty();
        }
        else if (obj instanceof Object[]) {
            return ((Object[])obj).length == 0;
        }
        else if (obj instanceof Collection) {
            return ((Collection)obj).isEmpty();
        }
        else {
            return false;
        }
    }

    /**
     * Returns whether the object is null. This method provides an alternative
     * syntax than "if (obj == null)".
     *
     * @see #isNotNull
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * Returns whether the object is not null. This method provides an
     * alternative syntax than "if (obj != null)".
     *
     * @see #isNull
     */
    public static boolean isNotNull(Object obj) {
        return obj != null;
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
     *     String status = db.status() != null ? db.status : db.initialize());
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
     *    String statusName = userName != null && lastName != null ? lastName : null;
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
        return ary == null ? new EmptyIterable<T>() : Arrays.asList(ary);
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
     * which returned a fixed-size array). The following two blocks are
     * equivalent:
     *
     * <pre>
     *     List<String> names = new ArrayList<String>(Arrays.asList("kevin", "jacob", "isaac"));
     *     names.add("henry");
     * </pre>
     * <pre>
     *     List<String> names = list("kevin", "jacob", "isaac");
     *     names.add("henry");
     * </pre>
     */
    public static <T> List<T> list(T ... elements) {
        List<T> ary = Arrays.asList(elements);
        return new ArrayList<T>(ary);
    }

    public static void puts(Object obj) {
        System.out.println(obj);
    }

    public static void printf(String fmt, Object ... args) {
        System.out.printf(fmt, args);
    }
}
