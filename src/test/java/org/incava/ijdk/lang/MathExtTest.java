package org.incava.ijdk.lang;

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
public class MathExtTest {
    @Test
    @Parameters
    public void min(int expected, int[] nums) {
        int result = MathExt.min(nums);
        assertEqual(expected, result, message("nums", Arrays.asList(nums)));
    }
    
    private List<Object[]> parametersForMin() {
        return paramsList(params(Integer.MIN_VALUE, new int[] { }),
                          params(4, new int[] { 4 }),
                          params(1, new int[] { 3, 5, 1, 9 }));
    }
}
