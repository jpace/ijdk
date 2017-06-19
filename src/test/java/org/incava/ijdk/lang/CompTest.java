package org.incava.ijdk.lang;

import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

public class CompTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void compare(int expected, T x, T y) {
        int result = Comp.compare(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }

    public java.util.List<Object[]> parametersForCompare() {
        String obj = new String();
        
        return paramsList(params(0,  null, null),
                          params(-1, null, new String()),
                          params(1,  new String(), null),
                          params(0,  obj, obj),
                          params(0,  new String(), new String()),
                          params(0,  new String("abc"), new String("abc")),
                          params(-1, new String("abc"), new String("def")),
                          params(1,  new String("def"), new String("abc")));
    }
    
    @Test @Parameters(method="lessThanParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void lessThan(boolean expected, T x, T y) {
        boolean result = Comp.lessThan(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }
    
    @Test @Parameters(method="lessThanParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void lt(boolean expected, T x, T y) {
        boolean result = Comp.lt(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }
    
    @Test @Parameters(method="equalParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void lte(boolean expected, T x, T y) {
        boolean result = Comp.lte(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }

    public java.util.List<Object[]> lessThanParams() {
        String obj = new String();
        
        return paramsList(params(false, null, null),
                          params(true,  null, new String()),
                          params(false, new String(), null),
                          params(false, obj, obj),
                          params(false, new String(), new String()),
                          params(false, new String("abc"), new String("abc")),
                          params(true,  new String("abc"), new String("def")),
                          params(false, new String("def"), new String("abc")));
    }    

    @Test @Parameters(method="greaterThanParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void greaterThan(boolean expected, T x, T y) {
        boolean result = Comp.greaterThan(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }

    @Test @Parameters(method="greaterThanParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void gt(boolean expected, T x, T y) {
        boolean result = Comp.gt(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }

    @Test @Parameters(method="equalParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void gte(boolean expected, T x, T y) {
        boolean result = Comp.gte(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }

    public java.util.List<Object[]> greaterThanParams() {
        String obj = new String();        
        
        return paramsList(params(false, null, null),
                          params(false, null, new String()),
                          params(true,  new String(), null),
                          params(false, obj, obj),
                          params(false, new String(), new String()),
                          params(false, new String("abc"), new String("abc")),
                          params(false, new String("abc"), new String("def")),
                          params(true,  new String("def"), new String("abc")));
    }

    public java.util.List<Object[]> equalParams() {
        String obj = new String();
        
        return paramsList(params(true, null, null),
                          params(true, obj, obj),
                          params(true, new String(), new String()),
                          params(true, new String("abc"), new String("abc")));
    }    
}
