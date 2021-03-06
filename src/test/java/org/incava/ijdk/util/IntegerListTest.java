package org.incava.ijdk.util;

import java.util.Arrays;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

public class IntegerListTest extends Parameterized {
    @Test
    public void ctorEmpty() {
        IntegerList sl = new IntegerList();
        assertEqual(0, sl.size());
    }

    @Test
    public void ctorCollection() {
        List<Integer> list = Arrays.asList(new Integer[] { 1, 2, 3 });
        IntegerList sl = new IntegerList(list);
        assertEqual(3, sl.size());
    }

    @Test
    public void ctorOneArg() {
        IntegerList sl = new IntegerList(1);
        assertEqual(1, sl.size());
        assertEqual(1, sl.get(0));
    }

    @Test
    public void ctorTwoArgs() {
        IntegerList sl = new IntegerList(1, 2);
        assertEqual(2, sl.size());
        assertEqual(1, sl.get(0));
        assertEqual(2, sl.get(1));
    }

    @Test
    public void ctorThreeArgs() {
        IntegerList sl = new IntegerList(1, 2, 3);
        assertEqual(3, sl.size());
        assertEqual(1, sl.get(0));
        assertEqual(2, sl.get(1));
        assertEqual(3, sl.get(2));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void minimum(Integer expected, Integer ... values) {
        IntegerList sl = new IntegerList(values);
        Integer result = sl.minimum();
        assertEqual(expected, result, message("values", Arrays.asList(values)));
    }
    
    private List<Object[]> parametersForMinimum() {
        return paramsList(params(null, new Integer[0]),
                          params(1, 1),
                          params(1, 1, 2),
                          params(1, 2, 1));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void maximum(Integer expected, Integer ... values) {
        IntegerList sl = new IntegerList(values);
        Integer result = sl.maximum();
        assertEqual(expected, result, message("values", Arrays.asList(values)));
    }
    
    private List<Object[]> parametersForMaximum() {
        return paramsList(params(null, new Integer[0]),
                          params(1, 1),
                          params(2, 1, 2),
                          params(2, 2, 1));
    }
}
