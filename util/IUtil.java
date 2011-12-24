package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class IUtil {

    public static boolean isTrue(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isFalse(Object obj) {
        return isEmpty(obj);
    }

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

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static <T> T elvis(T obj, T defVal) {
        return isTrue(obj) ? obj : defVal;
    }

    public static <T> T and(T a, T b) {
        return isTrue(a) ? (isTrue(b) ? b : null) : null;
    }

    public static <T> T or(T a, T b) {
        return isTrue(a) ? a : (isTrue(b) ? b : null);
    }

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

    public static <T> Iterable<T> iter(T[] ary) {
        return ary == null ? new ArrayList<T>() : Arrays.asList(ary);
    }

    public static <T> Iterable<T> iter(Iterable<T> coll) {
        return coll == null ? new ArrayList<T>() : coll;
    }

    public static <T> List<T> list(T ... elements) {
        List<T> ary = Arrays.asList(elements);
        return new ArrayList<T>(ary);
    }

}
