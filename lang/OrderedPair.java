package org.incava.ijdk.lang;


/**
 * An ordered pair is comparable.
 */
public class OrderedPair<FirstType extends Comparable<? super FirstType>, SecondType extends Comparable<? super SecondType>>
    extends Pair<FirstType, SecondType> 
    implements Comparable<OrderedPair<FirstType, SecondType>> {

    public static <X extends Comparable<X>, Y extends Comparable<Y>> OrderedPair<X, Y> create(X first, Y second) {
        return new OrderedPair<X, Y>(first, second);
    }

    private final FirstType first;
    private final SecondType second;
    
    public OrderedPair(FirstType first, SecondType second) {
        super(first, second);
        
        this.first  = first;
        this.second = second;
    }

    public FirstType getFirst() {
        return first;
    }

    public SecondType getSecond() {
        return second;
    }

    public int compareTo(OrderedPair<FirstType, SecondType> other) {
        int cmp = getFirst().compareTo(other.getFirst());

        if (cmp == 0) {
            cmp = getSecond().compareTo(other.getSecond());
        }
        
        return cmp;
    }
}
