package org.incava.ijdk.lang;

import java.util.Collection;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.assertSame;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

@RunWith(JUnitParamsRunner.class)
public class TestNullableObject {
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

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void get(Object expected, Object fromObj) {
        NullableObject<Object> obj = NullableObject.of(fromObj);
        Object result = obj.get();
        assertSame(expected, result, message("fromObj", fromObj));
    }
    
    private List<Object[]> parametersForGet() {
        return paramsList(params("abc", "abc"));
    }

}
