package org.incava.ijdk.str;

import java.util.Comparator;
import org.incava.ijdk.lang.Char;
import org.incava.ijdk.lang.Str;

public class StrIgnoreCaseComparator implements Comparator<Str> {
    public int compare(Str x, Str y) {
        String s = x.str();
        String t = y.str();
            
        int len = Math.min(s.length(), t.length());
        for (int idx = 0; idx < len; ++idx) {
            Character c = s.charAt(idx);
            Character d = t.charAt(idx);
            int cmp = Char.compareIgnoreCase(c, d);
            if (cmp != 0) {
                return cmp;
            }
        }
        // "ab" <=> "abc" == -1
        return s.length() - t.length();
    }

    public boolean equals(Object obj) {
        return obj instanceof StrIgnoreCaseComparator;
    }

    public int hashCode() {
        return super.hashCode();
    }    
}
