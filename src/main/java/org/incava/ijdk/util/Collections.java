package org.incava.ijdk.util;

import java.util.Collection;

public class Collections {
    public static <T> String join(Collection<T> coll, String delimiter) {
        if (coll == null) {
            return null;
        }
        StringBuilder sb = null;
        for (Object obj : coll) {
            if (sb == null) {
                sb = new StringBuilder();
            }
            else if (delimiter != null) {
                sb.append(delimiter);
            }
            sb.append(String.valueOf(obj));
        }
        return sb == null ? "" : sb.toString();
    }
}
