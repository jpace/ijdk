package org.incava.ijdk.util;

import java.util.Arrays;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

@RunWith(JUnitParamsRunner.class)
public class IntegerListTest {
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

    @Test
    @Parameters
    public void minimum(Integer expected, Integer ... values) {
        IntegerList sl = new IntegerList(values);
        Integer min = sl.minimum();
        assertEqual(expected, min, message("values", Arrays.asList(values)));
    }
    
    private List<Object[]> parametersForMinimum() {
        return paramsList(params(null, new Integer[0]),
                          params(1, 1),
                          params(1, 1, 2),
                          params(1, 2, 1));
    }

    @Test
    @Parameters
    public void maximum(Integer expected, Integer ... values) {
        IntegerList sl = new IntegerList(values);
        Integer max = sl.maximum();
        assertEqual(expected, max, message("values", Arrays.asList(values)));        
    }
    
    private List<Object[]> parametersForMaximum() {
        return paramsList(params(null, new Integer[0]),
                          params(1, 1),
                          params(2, 1, 2),
                          params(2, 2, 1));
    }
}
