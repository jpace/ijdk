package org.incava.ijdk.tuple;

import org.incava.ijdk.lang.Comp;

/**
 * A set of three objects.
 */
public class Triple<A, B, C> extends Pair<A, B> {
    /**
     * Shorter syntax than the default constructor, allowing the compiler to discern the object types.
     */
    public static <A, B, C> Triple<A, B, C> of(A first, B second, C third) {
        return new Triple<A, B, C>(first, second, third);
    }

    private final C third;
    
    public Triple(A first, B second, C third) {
        super(first, second);
        this.third = third;
    }

    public C getThird() {
        return third;
    }

    public C third() {
        return third;
    }

    public String toString() {
        return "(" + first() + ", " + second() + ", " + third() + ")";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int compareTo(Triple<A, B, C> other) {
        int cmp = super.compareTo(other);
        if (cmp == 0) {
            if (this.third instanceof Comparable) {
                Comparable ctt = (Comparable)this.third;
                Comparable cot = (Comparable)other.third;
                return Comp.compare(ctt, cot);
            }
            else {
                return -1;
            }
        }
        else {
            return cmp;
        }
    }    
}
