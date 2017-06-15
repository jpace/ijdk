package org.incava.ijdk.util;

import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

@RunWith(JUnitParamsRunner.class)
public class IndexableTest {
    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public void getIndex(Integer expected, Integer size, Integer index) {
        Integer result = new Indexable(size).get(index);
        assertEqual(expected, result, message("size", size, "index", index));
    }

    public List<Object[]> parametersForGetIndex() {
        return paramsList(params(null, null, 0),
                          params(null, 0,    null),
                          params(null, 0,    0),
                          params(0,    4,    0),
                          params(1,    4,    1),
                          params(3,    4,    3),
                          params(null, 4,    4),
                          params(3,    4,   -1),
                          params(2,    4,   -2),
                          params(1,    4,   -3),
                          params(0,    4,   -4),
                          params(null, 4,   -5));
    }    
}
