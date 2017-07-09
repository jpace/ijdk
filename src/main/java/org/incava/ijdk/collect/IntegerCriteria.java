package org.incava.ijdk.str;

import org.incava.ijdk.lang.Closure;

public abstract class IntegerCriteria<T> implements Closure<T, Integer> {
    public static IntegerCriteria<Boolean> lessThan(Integer num) {
        return new IntegerCriteria<Boolean>() {
            public Boolean apply(Integer i) {
                return i < num;
            }
        };
    }

    public T execute(Integer i) {
        return i == null ? null : apply(i);
    }

    public abstract T apply(Integer i);
}
