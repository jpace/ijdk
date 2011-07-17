package org.incava.ijdk.lang;


/**
 * An ordered pair is comparable.
 */
public class OrderedPair<FirstType extends Comparable<? super FirstType>, SecondType extends Comparable<? super SecondType>> extends Pair<FirstType, SecondType> {

    public static <X extends Comparable<X>, Y extends Comparable<Y>> OrderedPair<X, Y> create(X first, Y second) {
        return new OrderedPair<X, Y>(first, second);
    }

    private FirstType _first;
    
    private SecondType _second;
    
    public OrderedPair(FirstType first, SecondType second) {
        super(first, second);
        
        _first  = first;
        _second = second;
    }

    public FirstType getFirst() {
        return _first;
    }

    public SecondType getSecond() {
        return _second;
    }

    public int compareTo(Pair<FirstType, SecondType> other) {
        tr.Ace.log("other", other);
        if (other instanceof OrderedPair) {
            return compareTo((OrderedPair<FirstType, SecondType>)other);
        }
        else {
            return super.compareTo(other);
        }
    }

    public int compareTo(OrderedPair<FirstType, SecondType> other) {
        tr.Ace.cyan("this", this);
        tr.Ace.cyan("other", other);

        int cmp = getFirst().compareTo(other.getFirst());
        tr.Ace.cyan("cmp", String.valueOf(cmp));

        if (cmp == 0) {
            cmp = getSecond().compareTo(other.getSecond());
            tr.Ace.cyan("cmp", String.valueOf(cmp));
        }

        tr.Ace.cyan("cmp", String.valueOf(cmp));
        
        return cmp;
    }

}
