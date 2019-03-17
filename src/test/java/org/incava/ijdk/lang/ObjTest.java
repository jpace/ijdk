package org.incava.ijdk.lang;

import java.util.Arrays;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.assertSame;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

public class ObjTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void of(Obj<T> expected, T obj) {
        Obj<T> result = Obj.of(obj);
        assertEqual(expected, result);
    }
    
    private List<Object[]> parametersForOf() {
        return paramsList(params(new Obj<String>("abc"), "abc"),
                          params(new Obj<Integer>(1), 1));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void ofNull(Obj<?> expected, T obj) {
        Obj<T> result = Obj.of(obj);
        assertSame(expected, result);
    }
    
    private List<Object[]> parametersForOfNull() {
        Obj<Object> nil = Obj.<Object>of(null);
        return paramsList(params(nil, (String)null),
                          params(nil, (Integer)null));
    }
    
    @Test @Parameters(method="parametersForGetObj") @TestCaseName("{method} {index} {params}")
    public void get(Object expected, Object fromObj) {
        Obj<Object> obj = Obj.of(fromObj);
        Object result = obj.get();
        assertSame(expected, result, message("fromObj", fromObj));
    }

    @Test @Parameters(method="parametersForGetObj") @TestCaseName("{method} {index} {params}")
    public void obj(Object expected, Object fromObj) {
        Obj<Object> obj = Obj.of(fromObj);
        Object result = obj.obj();
        assertSame(expected, result, message("fromObj", fromObj));
    }
    
    private List<Object[]> parametersForGetObj() {
        return paramsList(params("abc", "abc"),
                          params(1, 1),
                          params(null, null));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void equalsTest(boolean expected, Obj<T> nobj, Object obj) {
        boolean result = nobj.equals(obj);
        assertEqual(expected, result, message("nobj", nobj, "obj", obj));
    }
    
    private List<Object[]> parametersForEqualsTest() {
        Obj<String>  objStr     = new Obj<String>("abc");
        Obj<Integer> objInt     = new Obj<Integer>(1);
        Obj<String>  objStrNull = new Obj<String>(null);
        Obj<Integer> objIntNull = new Obj<Integer>(null);
        
        return paramsList(params(true,  objStr, "abc"),
                          params(true,  objStr, new Obj<String>("abc")),
                          params(true,  objStr, objStr),
                          params(false, objStr, "def"),
                          params(false, objStr, 1),
                          params(false, objStr, objStrNull),
                          params(false, objStrNull, objStr),
                          params(false, objStr, objIntNull),
                          params(true,  objInt, 1),
                          params(true,  objInt, new Obj<Integer>(1)),
                          params(true,  objInt, objInt),
                          params(false, objInt, objIntNull),
                          params(false, objIntNull, objInt),
                          params(false, objInt, 2));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void hashCodeTest(int expected, T obj) {
        Obj<T> nobj = new Obj<T>(obj);
        int result = nobj.hashCode();
        assertEqual(expected, result, message("nobj", nobj, "obj", obj));
    }
    
    private List<Object[]> parametersForHashCodeTest() {
        // Arrays.hashCode offsets the "real" value by 31
        return paramsList(params(31 + 0, null),
                          params(31 + 1, 1),
                          params(31 + "abc".hashCode(), "abc"));
    }

    @Test @Parameters(method="parametersForIsBoolean") @TestCaseName("{method} {index} {params}")
    public <T> void isTrue(boolean expected, T obj) {
        Obj<T> nobj = new Obj<T>(obj);
        boolean result = nobj.isTrue();
        assertEqual(expected, result, message("nobj", nobj, "obj", obj));
    }

    @Test @Parameters(method="parametersForIsBoolean") @TestCaseName("{method} {index} {params}")
    public <T> void isFalse(boolean expected, T obj) {
        Obj<T> nobj = new Obj<T>(obj);
        boolean result = nobj.isFalse();
        assertEqual(!expected, result, message("nobj", nobj, "obj", obj));
    }

    @Test @Parameters(method="parametersForIsBoolean") @TestCaseName("{method} {index} {params}")
    public <T> void isEmpty(boolean expected, T obj) {
        Obj<T> nobj = new Obj<T>(obj);
        boolean result = nobj.isEmpty();
        assertEqual(!expected, result, message("nobj", nobj, "obj", obj));
    }
    
    private List<Object[]> parametersForIsBoolean() {
        return paramsList(params(false, null),
            
                          // Integer
                          params(true, 0),
                          params(true, 1),
                          params(true, -1),

                          // T[]
                          params(true, new Object[] { "abc" }),
                          params(true, new Object[] { "" }),
                          params(false, new Object[] { }),

                          // Collection<T>
                          params(true, Arrays.asList(new Object[] { "abc" })),
                          params(true, Arrays.asList(new Object[] { "" })),
                          params(false, Arrays.asList(new Object[] { })));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void isNull(boolean expected, T obj) {
        Obj<T> nobj = new Obj<T>(obj);
        boolean result = nobj.isNull();
        assertEqual(expected, result, message("nobj", nobj, "obj", obj));
    }

    @Test @Parameters(method="parametersForIsNull") @TestCaseName("{method} {index} {params}")
    public <T> void isNotNull(boolean expected, T obj) {
        Obj<T> nobj = new Obj<T>(obj);
        boolean result = nobj.isNotNull();
        assertEqual(!expected, result, message("nobj", nobj, "obj", obj));
    }
    
    private List<Object[]> parametersForIsNull() {
        return paramsList(params(true, null),
                          params(false, "abc"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toStringTest(String expected, Object x) {
        Obj<Object> obj = Obj.of(x);
        String result = obj.toString();
        assertEqual(expected, result, message("x", x));
    }
    
    private List<Object[]> parametersForToStringTest() {
        return paramsList(params("one", "one"),
                          params("null", null),
                          params("[a, b, c]", new Object[] { "a", "b", "c" }));
    }
}
