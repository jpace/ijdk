package org.incava.ijdk.lang;

import java.io.*;
import java.util.*;


public class Range implements Comparable<Range> {

    private final int first;

    private final int last;
    
    public Range(int first, int last) {
        this.first = first;
        this.last = last;
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    public boolean includes(int n) {
        return n >= first && n <= last;
    }

    public Integer[] toArray() {
        return new Integer[] { first, last };
    }

    public boolean equals(Object obj) {
        if (obj instanceof Range) {
            Range other = (Range)obj;
            return first == other.first && last == other.last;
        }
        else {
            return false;
        }
    }

    public String toString() {
        return "[" + first + ", " + last + "]";
    }

    public int compareTo(Range other) {
        int cmp = IntegerExt.compare(first, other.first);
        if (cmp == 0) {
            cmp = IntegerExt.compare(last, other.last);
        }
        return cmp;
    }
}
