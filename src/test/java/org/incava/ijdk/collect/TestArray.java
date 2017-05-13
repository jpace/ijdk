package org.incava.ijdk.collect;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.*;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;
import static org.incava.ijdk.lang.Common.*;

@RunWith(JUnitParamsRunner.class)
public class TestArray {
    public <T> Array<T> assertAccessors(Object value, int index, Array<T> ary) {
        assertEqual(value, ary.getArray()[index]);
        assertEqual(value, ary.ary()[index]);
        return ary;
    }

    @Test
    public void ctorNullArray() {
        Array<Object> ary = new Array<Object>((Object[])null);
        assertEqual(null, ary.getArray());
        assertEqual(null, ary.ary());
    }

    @Test
    public void ctorNullObject() {
        Array<Object> ary = new Array<Object>((Object)null);
        assertAccessors(null, 0, ary);
    }

    @Test
    public void ctorNotEmpty() {
        Array<String> ary = new Array<String>("a", "b", "c");
        assertAccessors("a", 0, ary);
        assertAccessors("b", 1, ary);
        assertAccessors("c", 2, ary);
    }

    @Test
    @Parameters
    public <T> void asList(java.util.ArrayList<T> expected, Array<T> ary) {
        java.util.ArrayList<T> result = ary.asList();
        assertEqual(expected, result, message("ary", ary));
    }
    
    private java.util.List<Object[]> parametersForAsList() {
        return paramsList(params(null, new Array<Object>((Object[])null)),
                          params(new java.util.ArrayList<Object>(java.util.Arrays.asList(new Object[] { null })), new Array<Object>((Object)null)),
                          params(new java.util.ArrayList<String>(java.util.Arrays.asList(new String[] { "a", "b", "c" })), new Array<String>("a", "b", "c")));
    }

    @Test
    @Parameters
    public <T> void areAllNull(boolean expected, Array<T> ary) {
        boolean result = ary.areAllNull();
        assertEqual(expected, result, message("ary", ary));
    }
    
    private java.util.List<Object[]> parametersForAreAllNull() {
        return paramsList(params(false, new Array<Object>((Object[])null)),
                          params(true, new Array<Object>((Object)null)),
                          params(false, new Array<String>("a", null)),
                          params(false, new Array<String>("a", "b", "c")));
    }

    @Test
    @Parameters
    public <T> void isAnyNull(boolean expected, Array<T> ary) {
        boolean result = ary.isAnyNull();
        assertEqual(expected, result, message("ary", ary));
    }
    
    private java.util.List<Object[]> parametersForIsAnyNull() {
        return paramsList(params(false, new Array<Object>((Object[])null)),
                          params(true, new Array<Object>((Object)null)),
                          params(true, new Array<String>("a", null)),
                          params(false, new Array<String>("a", "b", "c")));
    }

    @Test
    @Parameters
    public <T> void length(int expected, Array<T> ary) {
        int result = ary.length();
        assertEqual(expected, result);
    }
    
    private java.util.List<Object[]> parametersForLength() {
        return paramsList(params(0, new Array<Object>()),
                          params(1, new Array<String>("a")),
                          params(2, new Array<String>("a", "b")));
    }

    @Test
    @Parameters
    public void get(Object expected, int idx, Object ... elmts) {
        Array<Object> ary = new Array<Object>(elmts);
        Object result = ary.get(idx);
        assertEqual(expected, result, message("ary", ary));
    }
    
    private java.util.List<Object[]> parametersForGet() {
        Object[] emptyAry = new Object[0];
        Object[] oneElementAry = new Object[] { "a" };
        Object[] twoElementAry = new Object[] { "a", "b" };
        
        return paramsList(params(null, 0, emptyAry),
                          params(null, 1, emptyAry),
                          params(null, -1, emptyAry),
                          params("a", 0, oneElementAry),
                          params(null, 1, oneElementAry),
                          params("a", -1, oneElementAry),
                          params("a", 0, twoElementAry),
                          params("b", 1, twoElementAry),
                          params(null, 2, twoElementAry),
                          params("b", -1, twoElementAry),
                          params("a", -2, twoElementAry),
                          params(null, -3, twoElementAry));
    }

    @Test
    @Parameters
    public void isEmpty(boolean expected, Object[] args) {
        Array<Object> obj = new Array<Object>(args);
        boolean result = obj.isEmpty();
        assertEqual(expected, result, message("args", args));
    }
    
    private java.util.List<Object[]> parametersForIsEmpty() {
        return paramsList(params(true, null),
                          params(true, new String[0]),
                          params(false, new String[] { "x" }));
    }    
}
