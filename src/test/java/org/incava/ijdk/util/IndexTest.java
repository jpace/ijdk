package org.incava.ijdk.util;

import org.incava.ijdk.lang.Common;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.*;
import static org.incava.ijdk.lang.Common.*;

@RunWith(JUnitParamsRunner.class)
public class IndexTest {
    @Test
    @Parameters
    public void getIndex(Integer expected, Integer size, int index) {
        Integer result = Index.getIndex(size, index);
        assertEqual(expected, result, message("size", size, "index", index));
    }

    public java.util.List<Object[]> parametersForGetIndex() {
        return Common.<Object[]>list(ary(null, null, 0),
                                     ary(null, 0, 0),
                                     ary(0, 4, 0),
                                     ary(1, 4, 1),
                                     ary(3, 4, 3),
                                     ary(null, 4, 4),
                                     ary(3, 4, -1),
                                     ary(2, 4, -2),
                                     ary(1, 4, -3),
                                     ary(0, 4, -4),
                                     ary(null, 4, -5));
    }            
}
