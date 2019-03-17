package org.incava.ijdk.lang;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.ijdk.collect.Array;
import org.incava.ijdk.lang.Common;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

public class RangeTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void first(Integer expected, Range rg) {
        Integer result = rg.first();
        assertEqual(expected, result, message("rg", rg));
    }

    @Test @Parameters(method="parametersForFirst") @TestCaseName("{method} {index} {params}")
    public void getFirst(Integer expected, Range rg) {
        Integer result = rg.getFirst();
        assertEqual(expected, result, message("rg", rg));
    }
    
    private List<Object[]> parametersForFirst() {
        return paramsList(params(3, new Range(3, 17)),
                          params(17, new Range(17, 3)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void last(Integer expected, Range rg) {
        Integer result = rg.last();
        assertEqual(expected, result, message("rg", rg));
    }

    @Test @Parameters(method="parametersForLast") @TestCaseName("{method} {index} {params}")
    public void getLast(Integer expected, Range rg) {
        Integer result = rg.getLast();
        assertEqual(expected, result, message("rg", rg));
    }
    
    private List<Object[]> parametersForLast() {
        return paramsList(params(17, new Range(3, 17)),
                          params(3, new Range(17, 3)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void equalsTest(boolean expected, Range rg, Object other) {
        boolean result = rg.equals(other);
        assertEqual(expected, result, message("rg", rg, "other", other));
    }
    
    private List<Object[]> parametersForEqualsTest() {
        Range rg12 = new Range(1, 2);
        Range rg21 = new Range(2, 1);
        Range rg13 = new Range(1, 3);
        
        return paramsList(params(true,  rg12, rg12),
                          params(false, rg12, null),
                          params(false, rg12, "abc"),
                          params(true,  rg12, new Range(1, 2)),
                          params(false, rg12, rg21),
                          params(false, rg12, rg13));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void includes(boolean expected, Range rg, Integer val) {
        boolean result = rg.includes(val);
        assertEqual(expected, result, message("rg", rg, "val", val));
    }
    
    private List<Object[]> parametersForIncludes() {
        Range rg35 = new Range(3, 5);
        Range rg53 = new Range(5, 3);
        Range rg33 = new Range(3, 3);
        return paramsList(params(false, rg35, 2),
                          params(true,  rg35, 3),
                          params(true,  rg35, 4),
                          params(true,  rg35, 5),
                          params(false, rg35, 6),
                          params(false, rg35, null),
                          
                          params(true,  rg53, 3),
                          params(false, rg53, 4),
                          params(true,  rg53, 5),
                          
                          params(true,  rg33, 3),
                          params(false, rg33, 4),
                          params(false, rg33, 5));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toArray(Array<Integer> expected, Range rg) {
        Array<Integer> result = rg.toArray();
        assertEqual(expected, result, message("rg", rg));
    }
    
    private List<Object[]> parametersForToArray() {
        return paramsList(params(Array.of(3, 4, 5, 6, 7), new Range(3, 7)),
                          params(Array.of(3),             new Range(3, 3)),
                          params(Array.<Integer>empty(),  new Range(7, 3)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void iterator(Integer[] expected, Range rg) {
        int idx = 0;
        for (Integer i : rg) {
            assertEqual(i, expected[idx], message("rg", rg, "idx", idx));
            ++idx;
        }
        assertEqual(expected.length, idx, message("rg", rg));
    }
    
    private List<Object[]> parametersForIterator() {
        return paramsList(params(new Integer[] { 2, 3, 4 }, new Range(2, 4)),
                          params(new Integer[] { 2 },       new Range(2, 2)),
                          params(new Integer[] { },         new Range(3, 2)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void upTo(Integer[] expected, Range rg) {
        int idx = 0;
        for (Integer i : rg.upTo()) {
            assertEqual(i, expected[idx], message("rg", rg, "idx", idx));
            ++idx;
        }
        assertEqual(expected.length, idx, message("rg", rg));
    }
    
    private List<Object[]> parametersForUpTo() {
        return paramsList(params(new Integer[] { 2, 3 }, new Range(2, 4)),
                          params(new Integer[] { 2 },    new Range(2, 3)),
                          params(new Integer[] { },      new Range(2, 2)),
                          params(new Integer[] { },      new Range(3, 2)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compareTo(int expected, Range x, Range y) {
        int result = x.compareTo(y);
        assertEqual(expected, result, message("x", x, "y", y));
    }
    
    private List<Object[]> parametersForCompareTo() {
        Range rg26 = new Range(2, 6);
        Range rg27 = new Range(2, 7);
        Range rg36 = new Range(3, 6);
        Range rg37 = new Range(3, 7);
        return paramsList(params(1,  rg26, null),
                          params(0,  rg26, rg26),
                          params(0,  rg26, new Range(2, 6)),
                          params(-1, rg26, rg36),
                          params(-1, rg26, rg27),
                          params(1,  rg36, rg26),
                          params(1,  rg37, rg36));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toStringTest(String expected, Range rg) {
        String result = rg.toString();
        assertEqual(expected, result);
    }
    
    private List<Object[]> parametersForToStringTest() {
        return paramsList(params("[3 .. 7]", new Range(3, 7)),
                          params("[3 .. 3]", new Range(3, 3)),
                          params("[7 .. 3]", new Range(7, 3)));
    }
}
