package ijdk.lang;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestThrowablee extends TestCase {
    public TestThrowablee(String name) {
        super(name);
    }
    
    public void testGetStackTraceStringNPE() {
        Throwable t = new NullPointerException("nope");
        String expected = "java.lang.NullPointerException: nope\n" +
            "\tat ijdk.lang.TestThrowablee.testGetStackTraceStringNPE(TestThrowablee.java:13)\n" + 
            "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)";
        assertGetStackTraceString(expected, t);
    }

    public void testGetStackTraceStringNull() {
        assertGetStackTraceString(null, null);
    }

    public String assertGetStackTraceString(String expected, Throwable t) {
        String result = new Throwablee(t).getStackTraceString();
        System.out.println("result: " + result);
        if (expected == null) {
            assertEquals("t: " + t, expected, result);
        }
        else {
            assertTrue("t: " + t + "; result " + result + "; expected: " + expected, result.startsWith(expected));
        }
        return result;
    }
}
