package org.incava.ijdk.lang;

import java.util.Arrays;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class TestMathExt {
    private static Object[] objary(Object ... args) {
        return args;
    }    
    
    @Test
    @Parameters
    public void min(int expected, int[] nums) {
        int result = MathExt.min(nums);
        assertEqual(expected, result, message("nums", Arrays.asList(nums)));
    }
    
    private List<Object[]> parametersForMin() {
        return Arrays.asList(objary(Integer.MIN_VALUE, new int[] { }),
                             objary(4, new int[] { 4 }),
                             objary(1, new int[] { 3, 5, 1, 9 }));
    }
}
