package ijdk.lang;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class TestCommon {
    @Test
    public void keyValue() {
        String result = Common.keyValue("one", "abc");
        Assert.assertEquals("one: abc", result);
    }

    @Test
    public void toStringString() {
        Assert.assertEquals("one", Common.toString("one"));
    }

    @Test
    public void toStringNull() {
        Assert.assertEquals("null", Common.toString(null));
    }

    @Test
    public void toStringArray() {
        Assert.assertEquals("[a, b, c]", Common.toString(new Object[] { "a", "b", "c" }));
    }

    @Test
    public void list() {
        Assert.assertEquals(Arrays.asList(new String[] { "a", "b", "c" }), Common.list("a", "b", "c"));
    }

    @Test
    public void objary() {
        Assert.assertArrayEquals(new Object[] { Boolean.TRUE, "b", new Integer(1) }, Common.objary(Boolean.TRUE, "b", new Integer(1)));
    }
}
