package org.incava.ijdk.lang;

import java.util.Arrays;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.ijdk.lang.Common.objary;
import static org.incava.test.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class TestComp {
    @Test
    @Parameters
    public <T extends Comparable<T>> void compare(int expected, T x, T y) {
        int result = Comp.compare(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }

    public java.util.List<Object[]> parametersForCompare() {
        java.util.List<Object[]> params = new java.util.ArrayList<Object[]>();
        
        String obj = new String();
        
        params.add(objary(0,  null, null));
        params.add(objary(-1, null, new String()));
        params.add(objary(1,  new String(), null));
        params.add(objary(0,  obj, obj));
        params.add(objary(0,  new String(), new String()));
        params.add(objary(0,  new String("abc"), new String("abc")));
        params.add(objary(-1, new String("abc"), new String("def")));
        params.add(objary(1,  new String("def"), new String("abc")));
        
        return params;
    }
    
    @Test
    @Parameters(method="lessThanParams")
    public <T extends Comparable<T>> void lessThan(boolean expected, T x, T y) {
        boolean result = Comp.lessThan(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }
    
    @Test
    @Parameters(method="lessThanParams")
    public <T extends Comparable<T>> void lt(boolean expected, T x, T y) {
        boolean result = Comp.lt(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }
    
    @Test
    @Parameters(method="equalParams")
    public <T extends Comparable<T>> void lte(boolean expected, T x, T y) {
        boolean result = Comp.lte(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }

    public java.util.List<Object[]> lessThanParams() {
        java.util.List<Object[]> params = new java.util.ArrayList<Object[]>();        
        String obj = new String();
        
        params.add(objary(false, null, null));
        params.add(objary(true,  null, new String()));
        params.add(objary(false, new String(), null));
        params.add(objary(false, obj, obj));
        params.add(objary(false, new String(), new String()));
        params.add(objary(false, new String("abc"), new String("abc")));
        params.add(objary(true,  new String("abc"), new String("def")));
        params.add(objary(false, new String("def"), new String("abc")));

        return params;
    }    

    @Test
    @Parameters(method="greaterThanParams")
    public <T extends Comparable<T>> void greaterThan(boolean expected, T x, T y) {
        boolean result = Comp.greaterThan(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }

    @Test
    @Parameters(method="greaterThanParams")
    public <T extends Comparable<T>> void gt(boolean expected, T x, T y) {
        boolean result = Comp.gt(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }

    @Test
    @Parameters(method="equalParams")
    public <T extends Comparable<T>> void gte(boolean expected, T x, T y) {
        boolean result = Comp.gte(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }

    public java.util.List<Object[]> greaterThanParams() {
        java.util.List<Object[]> params = new java.util.ArrayList<Object[]>();        
        
        String obj = new String();
        
        params.add(objary(false, null, null));
        params.add(objary(false, null, new String()));
        params.add(objary(true,  new String(), null));
        params.add(objary(false, obj, obj));
        params.add(objary(false, new String(), new String()));
        params.add(objary(false, new String("abc"), new String("abc")));
        params.add(objary(false, new String("abc"), new String("def")));
        params.add(objary(true,  new String("def"), new String("abc")));

        return params;
    }

    public java.util.List<Object[]> equalParams() {
        java.util.List<Object[]> params = new java.util.ArrayList<Object[]>();
        
        String obj = new String();
        
        params.add(objary(true, null, null));
        params.add(objary(true, obj, obj));
        params.add(objary(true, new String(), new String()));
        params.add(objary(true, new String("abc"), new String("abc")));
        
        return params;
    }
    
}
