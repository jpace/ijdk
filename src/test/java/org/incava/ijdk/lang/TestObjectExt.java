package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
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
public class TestObjectExt {
    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void compare(int expected, T x, T y) {
        int result = ObjectExt.compare(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }
    
    private List<Object[]> parametersForCompare() {
        String empty = new String();
        String abc = new String("abc");
        String def = new String("def");
        
        return paramsList(params(0,  null,  null),
                          params(-1, null,  empty),
                          params(-1, null,  abc),
                          params(1,  empty, null),
                          params(0,  empty, empty),
                          params(0,  abc,   abc),
                          params(-1, abc,   def),
                          params(1,  def,   abc));
    }    

    @Test
    @Parameters(method="parametersForCompare")
    @TestCaseName("{method} {index} {params}")
    public void equal(int cmpValue, Object x, Object y) {
        boolean result = ObjectExt.equal(x, y);
        assertEqual(cmpValue == 0, result, message("x", x, "y", y, "cmpValue", cmpValue));
    }
    

    @Test
    @Parameters(method="parametersForIsBoolean")
    @TestCaseName("{method} {index} {params}")
    public void isTrue(boolean expected, Object obj) {
        boolean result = ObjectExt.isTrue(obj);
        assertEqual(expected, result, message("obj", obj));
    }

    @Test
    @Parameters(method="parametersForIsBoolean")
    @TestCaseName("{method} {index} {params}")
    public void isFalse(boolean expected, Object obj) {
        boolean result = ObjectExt.isFalse(obj);
        assertEqual(!expected, result, message("obj", obj));
    }

    @Test
    @Parameters(method="parametersForIsBoolean")
    @TestCaseName("{method} {index} {params}")
    public void isEmpty(boolean expected, Object obj) {
        boolean result = ObjectExt.isEmpty(obj);
        assertEqual(!expected, result, message("obj", obj));
    }
    
    private List<Object[]> parametersForIsBoolean() {
        return paramsList(params(false, null),
                          params(true,  new Object()),
                          params(false, ""),
                          params(true,  "abc"),
                          params(false, new ArrayList<String>()),
                          params(true,  Arrays.asList(new Integer[] { 1 })),
                          params(false, new Integer[0]),
                          params(true,  new Integer[] { 1 }));
    }

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public void isNull(boolean expected, Object obj) {
        boolean result = ObjectExt.isNull(obj);
        assertEqual(expected, result, message("obj", obj));
    }

    @Test
    @Parameters(method="parametersForIsNull")
    @TestCaseName("{method} {index} {params}")
    public void isNotNull(boolean expected, Object obj) {
        boolean result = ObjectExt.isNotNull(obj);
        assertEqual(!expected, result, message("obj", obj));
    }
    
    private List<Object[]> parametersForIsNull() {
        return paramsList(params(true, null),
                          params(false, new Object()));
    }
}
