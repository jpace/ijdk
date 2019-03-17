package org.incava.ijdk.collect;

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

public class IntegerArrayTest extends Parameterized {
    @Test
    public void ctorEmpty() {
        IntegerArray sl = new IntegerArray();
        assertEqual(0, sl.size());
    }

    @Test
    public void ctorCollection() {
        List<Integer> list = Arrays.asList(new Integer[] { 1, 2, 3 });
        IntegerArray sl = new IntegerArray(list);
        assertEqual(3, sl.size());
    }

    @Test
    public void ctorOneArg() {
        IntegerArray sl = new IntegerArray(1);
        assertEqual(1, sl.size());
        assertEqual(1, sl.get(0));
    }

    @Test
    public void ctorTwoArgs() {
        IntegerArray sl = new IntegerArray(1, 2);
        assertEqual(2, sl.size());
        assertEqual(1, sl.get(0));
        assertEqual(2, sl.get(1));
    }

    @Test
    public void ctorThreeArgs() {
        IntegerArray sl = new IntegerArray(1, 2, 3);
        assertEqual(3, sl.size());
        assertEqual(1, sl.get(0));
        assertEqual(2, sl.get(1));
        assertEqual(3, sl.get(2));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void minimum(Integer expected, Integer ... values) {
        IntegerArray sl = new IntegerArray(values);
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
        IntegerArray sl = new IntegerArray(values);
        Integer result = sl.maximum();
        assertEqual(expected, result, message("values", Arrays.asList(values)));
    }
    
    private List<Object[]> parametersForMaximum() {
        return paramsList(params(null, new Integer[0]),
                          params(1, 1),
                          params(2, 1, 2),
                          params(2, 2, 1));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void sum(Integer expected, Integer ... values) {
        IntegerArray sl = new IntegerArray(values);
        Integer result = sl.sum();
        assertEqual(expected, result, message("values", Arrays.asList(values)));
    }
    
    private List<Object[]> parametersForSum() {
        return paramsList(params(0, new Integer[0]),
                          params(1, 1),
                          params(3, 1, 2),
                          params(3, 1, 2, null),
                          params(3, 2, 1));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void unique(IntegerArray expected, IntegerArray ilist) {
        IntegerArray result = ilist.unique();
        assertEqual(expected, result, message("ilist", ilist));
    }

    private List<Object[]> parametersForUnique() {
        return paramsList(params(IntegerArray.of(2, 3, 5), IntegerArray.of(2, 3, 2, 3, 3, 5, 5, 3)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void get(IntegerArray expected, IntegerArray ilist, Integer from, Integer to) {
        IntegerArray result = ilist.get(from, to);
        assertEqual(expected, result, message("ilist", ilist));
    }

    private List<Object[]> parametersForGet() {
        return paramsList(params(IntegerArray.of(3, 1, 7), IntegerArray.of(2, 3, 1, 7, 8), 1, -2));
    }
}
