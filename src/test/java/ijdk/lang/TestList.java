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
    @Parameters
    public void containsAnyCollection(Boolean expected, List<Integer> list, List<Integer> coll) {
        Assert.assertEquals(expected, list.containsAny(coll));
    }

    public List<Object[]> parametersForContainsAnyCollection() {
        return Common.<Object[]>list(Common.ary(true,  new List<Integer>(1, 2, 3), Common.list(1)),
                                     Common.ary(true,  new List<Integer>(1, 2, 3), Common.list(2, 4)),
                                     Common.ary(false, new List<Integer>(1, 2, 3), Common.list(4)),
                                     Common.ary(false, new List<Integer>(1, 2, 3), Common.list(4, 5)));
    }
    
    @Test
    @Parameters
    public void containsAnyArray(Boolean expected, List<Integer> list, Integer ... args) {
        Assert.assertEquals(expected, list.containsAny(args));
    }
    
    @Test
    public void containsAnyArrayEmpty() {
        Assert.assertEquals(false, new List<Integer>(1, 2, 3).containsAny());
    }

    public List<Object[]> parametersForContainsAnyArray() {
        return Common.<Object[]>list(Common.ary(true,  new List<Integer>(1, 2, 3), 1),
                                     Common.ary(true,  new List<Integer>(1, 2, 3), 4, 3),
                                     Common.ary(false, new List<Integer>(1, 2, 3), 4));
    }
}
