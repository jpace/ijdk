package org.incava.ijdk.lang;

/**
 * A two-element tuple, for classes that are comparable. Consider using KeyValue instead, which is
 * more flexible in variable types.
 *
 * @see KeyValue
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
        Pair<X, Y> of(X first, Y second) {
        return new Pair<X, Y>(first, second);
    }

    private final FirstType first;    
    private final SecondType second;
    
    /**
     * @param first the first value in the pair
     * @param second the second value in the pair
     */
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

    /**
     * Compares this pair to the object, which should be of type <code>Pair</code>, or otherwise
     * false is returned.
     *
     * @param obj the object to compare to; can be null
     * @return whether the object equals this pair
     */
    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair<?, ?> other = (Pair<?, ?>)obj;
            return getFirst().equals(other.getFirst()) && getSecond().equals(other.getSecond());
        }
        else {
            return false;
        }
    }

    /**
     * Compares this pair to the other.
     */
    public int compareTo(Pair<FirstType, SecondType> other) {
        int cmp = getFirst().compareTo(other.getFirst());

        if (cmp == 0) {
            cmp = getSecond().compareTo(other.getSecond());
        }
        
        return cmp;
    }

    /**
     * Returns the pair as a string in the form "first, second".
     *
     * @return the pair in the form "first, second".
     */
    public String toString() {
        return String.valueOf(first) + ", " + String.valueOf(second);
    }

    /**
     * Returns a hash code for the pair.
     * 
     * @return the hash code
     */
    public int hashCode() {
        return (first == null ? 1 : first.hashCode()) * 31 + (second == null ? 1 : second.hashCode());
    }
}
