package org.incava.ijdk.lang;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

public class DefaultComparingTest extends Parameterized {
    static class TestString extends DefaultComparing<String> {
        public TestString(String str) {
            super(str);
        }
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compare(int expected, String x, String y) {
        TestString tx = new TestString(x);
        int result = tx.compareTo(y);
        assertEqual(expected, result, message("x", x, "y", y));
    }
    
    private List<Object[]> parametersForCompare() {
        String empty = "";
        String abc = "abc";
        String def = "def";
        
        return paramsList(params(0,  null,  null),
                          params(-1, null,  empty),
                          params(-1, null,  abc),
                          params(1,  empty, null),
                          params(0,  empty, empty),
                          params(0,  abc,   abc),
                          params(-1, abc,   def),
                          params(1,  def,   abc));
    }
}
