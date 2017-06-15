package org.incava.ijdk.lang;

import junit.framework.TestCase;

public class ThrwTest extends TestCase {
    public ThrwTest(String name) {
        super(name);
    }
    
    public void testGetStackTraceStringNPE() {
        Throwable t = new NullPointerException("nope");
        String expected = "java.lang.NullPointerException: nope\n" +
            "\tat org.incava.ijdk.lang.ThrwTest.testGetStackTraceStringNPE(ThrwTest.java:11)\n" + 
            "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)";
        assertGetStackTraceString(expected, t);
    }

    public void testGetStackTraceStringNull() {
        assertGetStackTraceString(null, null);
    }

    public String assertGetStackTraceString(String expected, Throwable t) {
        String result = new Thrw(t).getStackTraceString();
        if (expected == null) {
            assertEquals("t: " + t, expected, result);
        }
        else {
            assertTrue("t: " + t + "; result " + result + "; expected: " + expected, result.startsWith(expected));
        }
        return result;
    }
}
