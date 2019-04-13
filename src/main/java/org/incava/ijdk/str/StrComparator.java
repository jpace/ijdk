package org.incava.ijdk.str;

import java.util.Comparator;
import org.incava.ijdk.lang.Str;

public class StrComparator implements Comparator<Str> {
    public int compare(Str x, Str y) {
        int len = Math.min(x.length(), y.length());
        for (int idx = 0; idx < len; ++idx) {
            Character c = x.get(idx);
            Character d = y.get(idx);
            int cmp = c.compareTo(d);
            if (cmp != 0) {
                return cmp;
            }
        }
        // "ab" <=> "abc" == -1
        return x.length() - y.length();
    }

    public boolean equals(Object obj) {
        return obj instanceof StrComparator;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
