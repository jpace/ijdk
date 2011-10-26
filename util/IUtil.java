package org.incava.ijdk.util;

import java.util.*;


public class IUtil {

    public static boolean isTrue(Object obj) {
        return obj != null;
    }

    public static <T> T elvis(T obj, T defVal) {
        return isTrue(obj) ? obj : defVal;
    }

}
