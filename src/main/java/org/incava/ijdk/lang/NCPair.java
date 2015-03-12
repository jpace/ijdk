package org.incava.ijdk.lang;

/**
 * A pair of objects that do not implement Comparable.
 */
public class NCPair<FirstType, SecondType> {
    public static <X, Y> NCPair<X, Y> create(X first, Y second) {
        return new NCPair<X, Y>(first, second);
    }

    private final FirstType first;    
    private final SecondType second;
    
    public NCPair(FirstType first, SecondType second) {
        this.first  = first;
        this.second = second;
    }

    public FirstType getFirst() {
        return first;
    }

    public SecondType getSecond() {
        return second;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair<?, ?> other = (Pair<?, ?>)obj;
            return getFirst().equals(other.getFirst()) && getSecond().equals(other.getSecond());
        }
        else {
            return false;
        }
    }

    public String toString() {
        return getFirst().toString() + ", " + getSecond().toString();
    }

    public int hashCode() {
        return getFirst().hashCode() * 31 + getSecond().hashCode();
    }
}
