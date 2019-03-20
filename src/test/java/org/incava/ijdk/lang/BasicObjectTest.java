package org.incava.ijdk.lang;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

public class BasicObjectTest extends Parameterized {
    public static class BasicObjectExample extends BasicObject {
        private final Object x;
        private final Object y;

        public BasicObjectExample(Object x, Object y) {
            this.x = x;
            this.y = y;
        }
        
        public Array<Object> getInstanceValues() {
            return Array.of(x, y);
        }
    }

    private static BasicObject create(Object x, Object y) {
        return new BasicObjectExample(x, y);
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void hashCode(int expected, BasicObjectExample obj) {
        int result = obj.hashCode();
        assertEqual(expected, result, message("obj", obj));
    }
    
    private List<Object[]> parametersForHashCode() {
        return paramsList(params(962, create(0, 1)),
                          params(994, create(1, 2)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void equals(boolean expected, BasicObjectExample obj, BasicObjectExample other) {
        boolean result = obj.equals(other);
        assertEqual(expected, result, message("obj", obj, "other", other));
    }
    
    private List<Object[]> parametersForEquals() {
        return paramsList(params(true, create(0, 1), create(0, 1)),
                          params(false, create(0, 1), create(0, 2)),
                          params(false, create(0, 1), null));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toStringNoDelim(String expected, BasicObjectExample obj) {
        String result = obj.toString();
        assertEqual(expected, result, message("obj", obj));
    }
    
    private List<Object[]> parametersForToStringNoDelim() {
        return paramsList(params("0, 1", create(0, 1)),
                          params("1, 2", create(1, 2)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toStringWithDelim(String expected, BasicObjectExample obj, String delim) {
        String result = obj.toString(delim);
        assertEqual(expected, result, message("obj", obj));
    }
    
    private List<Object[]> parametersForToStringWithDelim() {
        return paramsList(params("0==1", create(0, 1), "=="),
                          params("0+1",  create(0, 1), "+"));
    }
}
