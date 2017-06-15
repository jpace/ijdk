package org.incava.ijdk.lang;

import java.util.Arrays;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.test.Parameterized;
import org.junit.Test;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.assertSame;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

public class NullableObjectTest extends Parameterized {
    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public <T> void of(NullableObject<T> expected, T obj) {
        NullableObject<T> result = NullableObject.of(obj);
        assertEqual(expected, result);
    }
    
    private List<Object[]> parametersForOf() {
        return paramsList(params(new NullableObject<String>("abc"), "abc"),
                          params(new NullableObject<Integer>(1), 1));
    }
    
    @Test
    @Parameters(method="parametersForGetObj")
    @TestCaseName("{method} {index} {params}")
    public void get(Object expected, Object fromObj) {
        NullableObject<Object> obj = NullableObject.of(fromObj);
        Object result = obj.get();
        assertSame(expected, result, message("fromObj", fromObj));
    }

    @Test
    @Parameters(method="parametersForGetObj")
    @TestCaseName("{method} {index} {params}")
    public void obj(Object expected, Object fromObj) {
        NullableObject<Object> obj = NullableObject.of(fromObj);
        Object result = obj.obj();
        assertSame(expected, result, message("fromObj", fromObj));
    }
    
    private List<Object[]> parametersForGetObj() {
        return paramsList(params("abc", "abc"),
                          params(1, 1),
                          params(null, null));
    }

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public <T> void equalsTest(boolean expected, NullableObject<T> nobj, Object obj) {
        boolean result = nobj.equals(obj);
        assertEqual(expected, result, message("nobj", nobj, "obj", obj));
    }
    
    private List<Object[]> parametersForEqualsTest() {
        NullableObject<String>  objStr     = new NullableObject<String>("abc");
        NullableObject<Integer> objInt     = new NullableObject<Integer>(1);
        NullableObject<String>  objStrNull = new NullableObject<String>(null);
        NullableObject<Integer> objIntNull = new NullableObject<Integer>(null);
        
        return paramsList(params(true,  objStr, "abc"),
                          params(true,  objStr, new NullableObject<String>("abc")),
                          params(true,  objStr, objStr),
                          params(false, objStr, "def"),
                          params(false, objStr, 1),
                          params(false, objStr, objStrNull),
                          params(false, objStrNull, objStr),
                          
                          params(false, objStr, objIntNull),
                          
                          params(true,  objInt, 1),
                          params(true,  objInt, new NullableObject<Integer>(1)),
                          params(true,  objInt, objInt),
                          params(false, objInt, objIntNull),
                          params(false, objIntNull, objInt),
                          params(false, objInt, 2));
    }

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public <T> void hashCodeTest(int expected, T obj) {
        NullableObject<T> nobj = new NullableObject<T>(obj);
        int result = nobj.hashCode();
        assertEqual(expected, result, message("nobj", nobj, "obj", obj));
    }
    
    private List<Object[]> parametersForHashCodeTest() {
        return paramsList(params(0, null),
                          params(1, 1),
                          params("abc".hashCode(), "abc"));
    }

    @Test
    @Parameters(method="parametersForIsBoolean")
    @TestCaseName("{method} {index} {params}")
    public <T> void isTrue(boolean expected, T obj) {
        NullableObject<T> nobj = new NullableObject<T>(obj);
        boolean result = nobj.isTrue();
        assertEqual(expected, result, message("nobj", nobj, "obj", obj));
    }

    @Test
    @Parameters(method="parametersForIsBoolean")
    @TestCaseName("{method} {index} {params}")
    public <T> void isFalse(boolean expected, T obj) {
        NullableObject<T> nobj = new NullableObject<T>(obj);
        boolean result = nobj.isFalse();
        assertEqual(!expected, result, message("nobj", nobj, "obj", obj));
    }    

    @Test
    @Parameters(method="parametersForIsBoolean")
    @TestCaseName("{method} {index} {params}")
    public <T> void isEmpty(boolean expected, T obj) {
        NullableObject<T> nobj = new NullableObject<T>(obj);
        boolean result = nobj.isEmpty();
        assertEqual(!expected, result, message("nobj", nobj, "obj", obj));
    }    
    
    private List<Object[]> parametersForIsBoolean() {
        return paramsList(
            params(false, null),
            
            // Integer
            params(true, 0),
            params(true, 1),
            params(true, -1),

            // String
            params(true, "a"),
            params(false, ""),

            // T[]
            params(true, new Object[] { "abc" }),
            params(true, new Object[] { "" }),
            params(false, new Object[] { }),

            // Collection<T>            
            params(true, Arrays.asList(new Object[] { "abc" })),
            params(true, Arrays.asList(new Object[] { "" })),
            params(false, Arrays.asList(new Object[] { }))
                          );
    }

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public <T> void isNull(boolean expected, T obj) {
        NullableObject<T> nobj = new NullableObject<T>(obj);
        boolean result = nobj.isNull();
        assertEqual(expected, result, message("nobj", nobj, "obj", obj));
    }

    @Test
    @Parameters(method="parametersForIsNull")
    @TestCaseName("{method} {index} {params}")
    public <T> void isNotNull(boolean expected, T obj) {
        NullableObject<T> nobj = new NullableObject<T>(obj);
        boolean result = nobj.isNotNull();
        assertEqual(!expected, result, message("nobj", nobj, "obj", obj));
    }
    
    private List<Object[]> parametersForIsNull() {
        return paramsList(params(true, null),
                          params(false, "abc"));
    }

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public void toStringTest(String expected, Object x) {
        NullableObject<Object> obj = NullableObject.of(x);
        String result = obj.toString();
        assertEqual(expected, result, message("x", x));
    }
    
    private List<Object[]> parametersForToStringTest() {
        return paramsList(params("one", "one"),
                          params("null", null),
                          params("[a, b, c]", new Object[] { "a", "b", "c" }));
    }    
}
