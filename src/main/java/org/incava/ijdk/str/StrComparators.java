package org.incava.ijdk.str;

import java.util.Comparator;
import org.incava.ijdk.collect.Array;
import org.incava.ijdk.lang.Str;

public class StrComparators {
    public Comparator<Str> create(Str.Option ... options) {
        Array<Str.Option> opts = Array.of(options);
        if (opts.contains(Str.Option.IGNORE_CASE)) {
            return new StrIgnoreCaseComparator();
        }
        else if (opts.contains(Str.Option.ALPHANUMERIC)) {
            return new StrAlphanumericComparator();
        }
        else {
            return new StrComparator();
        }
    }
}
