package ijdk.lang;

import org.incava.ijdk.lang.ObjectTest;
import org.junit.Assert;
import org.junit.Test;

public class TestObj {
    @Test
    public void toStringString() {
        Assert.assertEquals("one", new Obj("one").toString());
    }

    @Test
    public void toStringNull() {
        Assert.assertEquals("null", new Obj(null).toString());
    }

    @Test
    public void toStringArray() {
        Assert.assertEquals("[a, b, c]", new Obj(new Object[] { "a", "b", "c" }).toString());
    }
    
}
