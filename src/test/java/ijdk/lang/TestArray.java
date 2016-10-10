package ijdk.lang;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestArray extends TestCase {
    public TestArray(String name) {
        super(name);
    }

    // ctor

    public <T> Array<T> assertCtor(Array<T> expected, Array<T> actual) {
        assertEquals(expected, actual);
        return expected;
    }

    public void testCtorEmpty() {
        Array<Object> expected = new Array<Object>();
        Array<Object> actual = new Array<Object>();

        assertCtor(expected, actual);
    }

    public void testCtorCollection() {
        Collection<String> coll = Arrays.asList(new String[] { "a", "b", "c" });
        Array<Object> expected = new Array<Object>(coll);
        Array<Object> actual = new Array<Object>(coll);

        assertCtor(expected, actual);
    }
}
