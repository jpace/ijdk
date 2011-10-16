package org.incava.ijdk;

import java.util.*;


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

    public static <T> T elvis(T obj, T defVal) {
        return isTrue(obj) ? obj : defVal;
    }

    public static <T> T and(T a, T b) {
        return isTrue(a) ? (isTrue(b) ? b : null) : null;
    }

    public static <T> T or(T a, T b) {
        return isTrue(a) ? a : (isTrue(b) ? b : null);
    }

    public static <T> Iterator<T> iterator(T[] ary) {
        return ary == null ? (new ArrayList<T>()).iterator() : Arrays.asList(ary).iterator();
    }

    public static <T> Iterator<T> iterator(Iterable<T> coll) {
        return coll == null ? (new ArrayList<T>()).iterator() : coll.iterator();
    }

}
