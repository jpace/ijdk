package org.incava.ijdk.lang;

import java.util.Arrays;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;

public class CommonTest {
    @Test
    public void keyValue() {
        String result = Common.keyValue("one", "abc");
        assertEqual("one: abc", result);
    }

    @Test
    public void toStringString() {
        assertEqual("one", Common.toString("one"));
    }

    @Test
    public void toStringNull() {
        assertEqual("null", Common.toString(null));
    }

    @Test
    public void toStringArray() {
        assertEqual("[a, b, c]", Common.toString(new Object[] { "a", "b", "c" }));
    }

    @Test
    public void list() {
        assertEqual(Arrays.asList(new String[] { "a", "b", "c" }), Common.list("a", "b", "c"));
    }

    @Test
    public void objary() {
        assertEqual(new Object[] { Boolean.TRUE, "b", new Integer(1) }, Common.objary(Boolean.TRUE, "b", new Integer(1)));
    }
}
