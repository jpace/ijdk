package org.incava.ijdk.util;

import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;

public class IndexTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void getIndex(Integer expected, Integer size, int index) {
        Integer result = Index.getIndex(size, index);
        assertEqual(expected, result, message("size", size, "index", index));
    }

    private java.util.List<Object[]> parametersForGetIndex() {
        return paramsList(params(0,    4,  0),
                          params(1,    4,  1),
                          params(null, 4,  4),
                          params(null, 4,  5),

                          params(3,    4, -1),
                          params(2,    4, -2),
                          params(1,    4, -3),
                          params(0,    4, -4),
                          params(null, 4, -5),
                          params(null, 4, -6),

                          params(null, 0,  0),
                          params(null, 0,  1),
                          params(null, 0, -1));
    }
}
