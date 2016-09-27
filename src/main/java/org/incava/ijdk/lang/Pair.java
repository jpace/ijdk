package org.incava.ijdk.lang;

/**
 * A two-element tuple, for classes that are comparable. See NCPair for an alternative class, for
 * non-comparable clases.
 */
public class Pair<FirstType extends Comparable<? super FirstType>, SecondType extends Comparable<? super SecondType>> 
    implements Comparable<Pair<FirstType, SecondType>> {

    /**
     * Class method that allows inference of the parameterized type. The parameters must implement
     * Comparable.
     *
     * <pre>
     *     // compare:
     *     Pair&lt;String, Integer&gt; pair = new Pair&lt;String, Integer&gt;("Homer", 34);
     *     Pair&lt;String, Integer&gt; pair = Pair.create("Homer", 34);
     * </pre>
     *
     * @param first the first value in the pair
     * @param second the second value in the pair
     * @return the created pair
     */
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

    /**
     * Returns the first element.
     *
     * @return the first elememt
     */
    public FirstType getFirst() {
        return first;
    }

    /**
     * Returns the second element.
     *
     * @return the second elememt
     */
    public SecondType getSecond() {
        return second;
    }

    /**
     * Returns the first element, with a shorter method name than <code>getFirst</code>.
     *
     * @return the first elememt
     */
    public FirstType first() {
        return first;
    }

    /**
     * Returns the second element, with a shorter method name than <code>getSecond~</code>.
     *
     * @return the second elememt
     */
    public SecondType second() {
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
        return String.valueOf(first) + ", " + String.valueOf(second);
    }

    public int hashCode() {
        return (first == null ? 1 : first.hashCode()) * 31 + (second == null ? 1 : second.hashCode());
    }
}
