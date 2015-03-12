package org.incava.ijdk.lang;

public class Pair<FirstType extends Comparable<? super FirstType>, SecondType extends Comparable<? super SecondType>> 
    implements Comparable<Pair<FirstType, SecondType>> {
    
    public static <X extends Comparable<? super X>, Y extends Comparable<? super Y>>
        Pair<X, Y> create(X first, Y second) {
        return new Pair<X, Y>(first, second);
    }

    private final FirstType first;    
    private final SecondType second;
    
    public Pair(FirstType first, SecondType second) {
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

    public int compareTo(Pair<FirstType, SecondType> other) {
        int cmp = getFirst().compareTo(other.getFirst());

        if (cmp == 0) {
            cmp = getSecond().compareTo(other.getSecond());
        }
        
        return cmp;
    }

    public String toString() {
        return getFirst().toString() + ", " + getSecond().toString();
    }

    public int hashCode() {
        return getFirst().hashCode() * 31 + getSecond().hashCode();
    }
}
