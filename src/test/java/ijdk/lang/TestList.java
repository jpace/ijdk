package ijdk.lang;

import java.util.Arrays;
import java.util.Collection;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TestList {
    // ctor

    public <T> List<T> assertCtor(List<T> expected, List<T> actual) {
        Assert.assertEquals(expected, actual);
        return expected;
    }

    @Test
    public void ctorEmpty() {
        List<Object> expected = new List<Object>();
        List<Object> actual = new List<Object>();

        assertCtor(expected, actual);
    }

    @Test
    public void ctorCollection() {
        Collection<String> coll = Arrays.asList(new String[] { "a", "b", "c" });
        List<Object> expected = new List<Object>(coll);
        List<Object> actual = new List<Object>(coll);

        assertCtor(expected, actual);
    }

    @Test
    public void toStringList() {
        List<String> expected = new List<String>();
        expected.add("1");
        expected.add("2");
        expected.add("3");

        List<Integer> numbers = new List<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        Assert.assertEquals(expected, numbers.toStringList());
    }

    @Test
    public void containsAnyCollection() {
        Assert.assertEquals(true,  new List<Integer>(1, 2, 3).containsAny(Common.list(1)));
        Assert.assertEquals(true,  new List<Integer>(1, 2, 3).containsAny(Common.list(2, 4)));
        Assert.assertEquals(false, new List<Integer>(1, 2, 3).containsAny(Common.list(4)));
        Assert.assertEquals(false, new List<Integer>(1, 2, 3).containsAny(Common.list(4, 5)));
    }

    @Test
    public void containsAnyArray() {
        Assert.assertEquals(true,  new List<Integer>(1, 2, 3).containsAny(1));
        Assert.assertEquals(true,  new List<Integer>(1, 2, 3).containsAny(4, 3));
        Assert.assertEquals(false, new List<Integer>(1, 2, 3).containsAny());
        Assert.assertEquals(false, new List<Integer>(1, 2, 3).containsAny(4));
    }
}
