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

@SuppressWarnings("deprecation")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void sum(Integer expected, Integer ... values) {
        IntegerList sl = new IntegerList(values);
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
    public void unique(IntegerList expected, IntegerList ilist) {
        IntegerList result = ilist.unique();
        assertEqual(expected, result, message("ilist", ilist));
    }

    private List<Object[]> parametersForUnique() {
        return paramsList(params(IntegerList.of(2, 3, 5), IntegerList.of(2, 3, 2, 3, 3, 5, 5, 3)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void get(IntegerList expected, IntegerList ilist, Integer from, Integer to) {
        IntegerList result = ilist.get(from, to);
        assertEqual(expected, result, message("ilist", ilist));
    }

    private List<Object[]> parametersForGet() {
        return paramsList(params(IntegerList.of(3, 1, 7), IntegerList.of(2, 3, 1, 7, 8), 1, -2));
    }
}
