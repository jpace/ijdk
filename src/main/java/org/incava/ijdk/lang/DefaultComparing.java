package org.incava.ijdk.lang;

/**
 * A class that implements <code>Comparing</code>, handling null as the object wrapped and used in
 * the <code>compareTo</code> method.
 */
public class DefaultComparing<T extends Comparable<T>> implements Comparing<T> {
    private final T value;

    public DefaultComparing(T value) {
        this.value = value;
    }
    
    public boolean lt(T other) {
        return compareTo(other) < 0;
    }

    public boolean lte(T other) {
        return compareTo(other) <= 0;
    }

    public boolean gt(T other) {
        return compareTo(other) > 0;
    }

    public boolean gte(T other) {
        return compareTo(other) >= 0;
    }

    public int compareTo(T other) {
        if (value == other) {
            return 0;
        }
        else if (value == null) {
            return -1;
        }
        else if (other == null) {
            return 1;
        }
        else {
            int cmp = value.compareTo(other);
            return Integer.signum(cmp);
        }
    }    
}
