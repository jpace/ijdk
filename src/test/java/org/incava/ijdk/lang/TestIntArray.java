package org.incava.ijdk.lang;

import java.util.Arrays;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static ijdk.lang.Common.objary;
import static org.incava.test.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class TestIntArray {
    @Test
    @Parameters
    public void max(int expected, int[] nums) {
        assertEqual(expected, IntArray.max(nums), message("nums", nums == null ? null : Arrays.asList(nums)));
    }
    
    private List<Object[]> parametersForMax() {
        return Arrays.asList(objary(Integer.MIN_VALUE, null),
                             objary(Integer.MIN_VALUE, new int[] { }),
                             objary(3, new int[] { 3 }),
                             objary(-17, new int[] { -17 }),
                             objary(0, new int[] { 0 }),
                             objary(17, new int[] { 3, 17 }),
                             objary(17, new int[] { 17, 3 }));
    }

    @Test
    @Parameters
    public void min(int expected, int[] nums) {
        assertEqual(expected, IntArray.min(nums), message("nums", nums == null ? null : Arrays.asList(nums)));
    }
    
    private List<Object[]> parametersForMin() {
        return Arrays.asList(objary(Integer.MAX_VALUE, null),
                             objary(Integer.MAX_VALUE, new int[] { }),
                             objary(3, new int[] { 3 }),
                             objary(-17, new int[] { -17 }),
                             objary(0, new int[] { 0 }),
                             objary(3, new int[] { 3, 17 }),
                             objary(3, new int[] { 17, 3 }));
    }

    @Test
    @Parameters
    public void sum(int expected, int[] nums) {
        assertEqual(expected, IntArray.sum(nums), message("nums", nums == null ? null : Arrays.asList(nums)));
        
    }
    
    private List<Object[]> parametersForSum() {
        return Arrays.asList(objary(0, null),
                             objary(0, new int[] { }),
                             objary(3, new int[] { 3 }),
                             objary(-17, new int[] { -17 }),
                             objary(0, new int[] { 0 }),
                             objary(20, new int[] { 3, 17 }),
                             objary(20, new int[] { 17, 3 }));
    }

    @Test
    @Parameters
    public void length(int expected, int[] nums) {
        assertEqual(expected, IntArray.length(nums), message("nums", nums == null ? null : Arrays.asList(nums)));
        
    }
    
    private List<Object[]> parametersForLength() {
        return Arrays.asList(objary(0, null),
                             objary(1, new int[] { 3 }),
                             objary(2, new int[] { 3, 17 }));
    }

    @Test
    @Parameters
    public void average(int expected, int[] nums) {
        assertEqual(expected, IntArray.average(nums), message("nums", nums == null ? null : Arrays.asList(nums)));
    }
    
    private List<Object[]> parametersForAverage() {
        return Arrays.asList(objary(0, null),
                             objary(0, new int[] { }),
                             objary(3, new int[] { 3 }),
                             objary(-17, new int[] { -17 }),
                             objary(0, new int[] { 0 }),
                             objary(10, new int[] { 3, 17 }),
                             objary(10, new int[] { 17, 4 }));
    }

    @Test
    @Parameters
    public void contains(boolean expected, int[] nums, int value) {
        assertEqual(expected, IntArray.contains(nums, value), message("nums", nums == null ? null : Arrays.asList(nums), "value", value));
    }
    
    private List<Object[]> parametersForContains() {
        return Arrays.asList(objary(false, null, 3),
                             objary(false, new int[] { }, 3),
                             objary(true, new int[] { 3 }, 3),
                             objary(false, new int[] { 3 }, 4),
                             objary(true, new int[] { 3, 17 }, 17),
                             objary(false, new int[] { 3, 17 }, 8));
    }

    @Test
    @Parameters
    public void toStringArray(String[] expected, int[] nums) {
        assertEqual(expected, IntArray.toStringArray(nums), message("nums", nums == null ? null : Arrays.asList(nums)));
        
    }
    
    private List<Object[]> parametersForToStringArray() {
        return Arrays.asList(objary(new String[] { }, null),
                             objary(new String[] { }, new int[] { }),
                             objary(new String[] { "3" }, new int[] { 3 }),
                             objary(new String[] { "3", "17" }, new int[] { 3, 17 }));
    }    
}
