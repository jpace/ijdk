package org.incava.ijdk.lang;

import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;

public class ThrwTest {
    @Test
    public void testGetStackTraceStringNPE() {
        Throwable t = new NullPointerException("nope");
        String expected = "java.lang.NullPointerException: nope\n" +
            "\tat org.incava.ijdk.lang.ThrwTest.testGetStackTraceStringNPE(ThrwTest.java:11)\n" +
            "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)";
        assertGetStackTraceString(expected, t);
    }

    @Test
    public void testGetStackTraceStringNull() {
        assertGetStackTraceString(null, null);
    }

    public String assertGetStackTraceString(String expected, Throwable t) {
        String result = new Thrw(t).getStackTraceString();
        if (expected == null) {
            assertEqual(expected, result, message("t", t));
        }
        else {
            assertEqual(true, result.startsWith(expected), message("t", t, "result", result, "expected", expected));
        }
        return result;
    }
}
