package org.incava.ijdk.util;

import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.test.Parameterized;
import org.junit.Test;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

public class IndexTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void getIndex(Integer expected, Integer size, int index) {
        Integer result = Index.getIndex(size, index);
        assertEqual(expected, result, message("size", size, "index", index));
    }

    public java.util.List<Object[]> parametersForGetIndex() {
        return paramsList(params(null, null, 0),
                          params(null, 0, 0),
                          params(0, 4, 0),
                          params(1, 4, 1),
                          params(3, 4, 3),
                          params(null, 4, 4),
                          params(3, 4, -1),
                          params(2, 4, -2),
                          params(1, 4, -3),
                          params(0, 4, -4),
                          params(null, 4, -5));
    }            
}
