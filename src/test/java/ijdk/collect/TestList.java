package ijdk.collect;

import ijdk.lang.Common;
import java.util.Arrays;
import java.util.Collection;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class TestList {
    // ctor

    public <T> List<T> assertCtor(List<T> expected, List<T> actual) {
        return assertEqual(expected, actual);
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
    public void containsAnyCollection(Boolean expected, List<Integer> list, java.util.List<Integer> coll) {
        Assert.assertEquals(expected, list.containsAny(coll));
    }

    public java.util.List<Object[]> parametersForContainsAnyCollection() {
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

    public java.util.List<Object[]> parametersForContainsAnyArray() {
        return Common.<Object[]>list(Common.ary(true,  new List<Integer>(1, 2, 3), 1),
                                     Common.ary(true,  new List<Integer>(1, 2, 3), 4, 3),
                                     Common.ary(false, new List<Integer>(1, 2, 3), 4));
    }

    @Test
    @Parameters
    public void first(Object expected, List<Object> list) {
        System.out.println("list: " + list);
        assertEqual(expected, list.first(), message("list", list));
    }

    public java.util.List<Object[]> parametersForFirst() {
        return Common.<Object[]>list(Common.ary(new Integer(6), List.of(6)),
                                     Common.ary(new Integer(6), List.of(6, 7)),
                                     Common.ary((Integer)null,  List.<Integer>of()));
    }

    @Test
    @Parameters
    public void last(Object expected, List<Object> list) {
        System.out.println("list: " + list);
        assertEqual(expected, list.last(), message("list", list));
    }

    public java.util.List<Object[]> parametersForLast() {
        return Common.<Object[]>list(Common.ary(new Integer(6), List.of(6)),
                                     Common.ary(new Integer(7), List.of(6, 7)),
                                     Common.ary((Integer)null,  List.<Integer>of()));
    }

    @Test
    @Parameters
    public void get(Object expected, List<Object> list, int idx) {
        System.out.println("list: " + list);
        assertEqual(expected, list.get(idx), message("list", list));
    }

    public java.util.List<Object[]> parametersForGet() {
        return Common.<Object[]>list(Common.ary(new Integer(6), List.of(6), 0),
                                     Common.ary(null,           List.of(6), 1),
                                     Common.ary(new Integer(6), List.of(6), -1),
                                     Common.ary(new Integer(7), List.of(6, 7), -1),
                                     Common.ary(new Integer(6), List.of(6, 7), -2),
                                     Common.ary(null,           List.of(6, 7),  2),
                                     Common.ary(null,           List.of(6, 7), -3),
                                     Common.ary(null,           List.<Integer>of(), 0),
                                     Common.ary(null,           List.<Integer>of(), -1),
                                     Common.ary(null,           List.<Integer>of(), 1));
                                     
    }
}
