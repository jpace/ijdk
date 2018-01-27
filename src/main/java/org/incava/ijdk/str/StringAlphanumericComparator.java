package org.incava.ijdk.str;

import java.util.Comparator;
import org.incava.ijdk.lang.Str;

public class StringAlphanumericComparator implements Comparator<String> {
    public StringAlphanumericComparator() {
    }

    public int compare(String s, String t) {
        return new StrAlphanumericComparator().compare(Str.of(s), Str.of(t));
    }
}
