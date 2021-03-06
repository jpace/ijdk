package org.incava.ijdk.lang;

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

public class IntArrayTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void max(int expected, int[] nums) {
        assertEqual(expected, IntArray.max(nums), message("nums", nums == null ? null : Arrays.asList(nums)));
    }
    
    private List<Object[]> parametersForMax() {
        return paramsList(params(Integer.MIN_VALUE, null),
                          params(Integer.MIN_VALUE, new int[] { }),
                          params(3, new int[] { 3 }),
                          params(-17, new int[] { -17 }),
                          params(0, new int[] { 0 }),
                          params(17, new int[] { 3, 17 }),
                          params(17, new int[] { 17, 3 }));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void min(int expected, int[] nums) {
        assertEqual(expected, IntArray.min(nums), message("nums", nums == null ? null : Arrays.asList(nums)));
    }
    
    private List<Object[]> parametersForMin() {
        return paramsList(params(Integer.MAX_VALUE, null),
                          params(Integer.MAX_VALUE, new int[] { }),
                          params(3, new int[] { 3 }),
                          params(-17, new int[] { -17 }),
                          params(0, new int[] { 0 }),
                          params(3, new int[] { 3, 17 }),
                          params(3, new int[] { 17, 3 }));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void sum(int expected, int[] nums) {
        assertEqual(expected, IntArray.sum(nums), message("nums", nums == null ? null : Arrays.asList(nums)));
        
    }
    
    private List<Object[]> parametersForSum() {
        return paramsList(params(0, null),
                          params(0, new int[] { }),
                          params(3, new int[] { 3 }),
                          params(-17, new int[] { -17 }),
                          params(0, new int[] { 0 }),
                          params(20, new int[] { 3, 17 }),
                          params(20, new int[] { 17, 3 }));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void length(int expected, int[] nums) {
        assertEqual(expected, IntArray.length(nums), message("nums", nums == null ? null : Arrays.asList(nums)));
    }
    
    private List<Object[]> parametersForLength() {
        return paramsList(params(0, null),
                          params(1, new int[] { 3 }),
                          params(2, new int[] { 3, 17 }));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void average(int expected, int[] nums) {
        assertEqual(expected, IntArray.average(nums), message("nums", nums == null ? null : Arrays.asList(nums)));
    }
    
    private List<Object[]> parametersForAverage() {
        return paramsList(params(0, null),
                          params(0, new int[] { }),
                          params(3, new int[] { 3 }),
                          params(-17, new int[] { -17 }),
                          params(0, new int[] { 0 }),
                          params(10, new int[] { 3, 17 }),
                          params(10, new int[] { 17, 4 }));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void contains(boolean expected, int[] nums, int value) {
        assertEqual(expected, IntArray.contains(nums, value), message("nums", nums == null ? null : Arrays.asList(nums), "value", value));
    }
    
    private List<Object[]> parametersForContains() {
        return paramsList(params(false, null, 3),
                          params(false, new int[] { }, 3),
                          params(true, new int[] { 3 }, 3),
                          params(false, new int[] { 3 }, 4),
                          params(true, new int[] { 3, 17 }, 17),
                          params(false, new int[] { 3, 17 }, 8));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toStringArray(String[] expected, int[] nums) {
        assertEqual(expected, IntArray.toStringArray(nums), message("nums", nums == null ? null : Arrays.asList(nums)));
    }
    
    private List<Object[]> parametersForToStringArray() {
        return paramsList(params(new String[] { }, null),
                          params(new String[] { }, new int[] { }),
                          params(new String[] { "3" }, new int[] { 3 }),
                          params(new String[] { "3", "17" }, new int[] { 3, 17 }));
    }
}
