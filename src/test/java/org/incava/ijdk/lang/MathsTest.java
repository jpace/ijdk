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

public class MathsTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void min(int expected, int[] nums) {
        int result = Maths.min(nums);
        assertEqual(expected, result, message("nums", Arrays.asList(nums)));
    }
    
    private List<Object[]> parametersForMin() {
        return paramsList(params(Integer.MIN_VALUE, new int[] { }),
                          params(4, new int[] { 4 }),
                          params(1, new int[] { 3, 5, 1, 9 }));
    }
}
