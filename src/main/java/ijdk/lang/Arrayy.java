package ijdk.lang;

import java.util.*;

/**
 * Wraps C-style arrays.
 */
public class Arrayy {
    private final Object[] ary;
    
    public Arrayy(Object ... ary) {
        this.ary = ary;
    }

    public Object[] getArray() {
        return this.ary;
    }

    public Object[] ary() {
        return this.ary;
    }

    public List<Object> toList() {
        return null;
    }
}

