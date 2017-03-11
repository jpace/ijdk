package ijdk.lang;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TestArray {
    // ctor

    public <T> Array<T> assertCtor(Array<T> expected, Array<T> actual) {
        Assert.assertEquals(expected, actual);
        return expected;
    }

    @Test
    public void ctorEmpty() {
        Array<Object> expected = new Array<Object>();
        Array<Object> actual = new Array<Object>();

        assertCtor(expected, actual);
    }

    @Test
    public void ctorCollection() {
        Collection<String> coll = Arrays.asList(new String[] { "a", "b", "c" });
        Array<Object> expected = new Array<Object>(coll);
        Array<Object> actual = new Array<Object>(coll);

        assertCtor(expected, actual);
    }

    @Test
    public void toStringArray() {
        Array<String> expected = new Array<String>();
        expected.add("1");
        expected.add("2");
        expected.add("3");

        Array<Integer> numbers = new Array<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        Assert.assertEquals(expected, numbers.toStringArray());
    }
}
