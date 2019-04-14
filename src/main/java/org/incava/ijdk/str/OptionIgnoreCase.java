package org.incava.ijdk.str;

import java.util.Comparator;
import org.incava.ijdk.lang.Str;

public class OptionIgnoreCase {
    public Comparator<Str> getComparator() {
        return new StrIgnoreCaseComparator();
    }
}
