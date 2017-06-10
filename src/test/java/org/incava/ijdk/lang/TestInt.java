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
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

@RunWith(JUnitParamsRunner.class)
public class TestInt {
    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public void init(Integer expected, Int intr) {
        assertEqual(expected, intr.get(), message("intr", intr));
        assertEqual(expected, intr.integer(), message("intr", intr));
    }
    
    private List<Object[]> parametersForInit() {
        return paramsList(params(null, new Int((Integer)null)),
                          params(new Integer(17), new Int(17)));
    }

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public void ofString(Int expected, String str) {
        Int result = Int.of(str);
        assertEqual(expected, result, message("str", str));
    }
    
    private List<Object[]> parametersForOfString() {
        Int inNull = new Int((Integer)null);
        Int inOne = new Int(1);
        
        return paramsList(params(inNull, null),
                          params(inNull, ""),
                          params(inNull, "abc"),
                          params(inNull, "a1"),
                          params(inOne, "1"),
                          params(inNull, "1a"));
    }

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public void ofInteger(Int expected, Integer i) {
        Int result = Int.of(i);
        assertEqual(expected, result, message("i", i));
    }
    
    private List<Object[]> parametersForOfInteger() {
        Int inNull = new Int((Integer)null);
        
        return paramsList(params(inNull, null),
                          params(new Int(1), 1),
                          params(new Int(0), 0));
    }
}
