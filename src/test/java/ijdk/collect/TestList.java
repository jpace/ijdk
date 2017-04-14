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
import static ijdk.lang.Common.*;

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
        List<Object> actual = new List<Object>(coll);

        assertEqual(Arrays.asList(new Object[] { "a", "b", "c" }), actual);
    }

    @Test
    public void ctorNull() {
        java.util.Collection<Object> nullColl = null;
        List<Object> expected = new List<Object>();
        List<Object> actual = new List<Object>(nullColl);

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
        return Common.<Object[]>list(ary(true,  new List<Integer>(1, 2, 3), Common.list(1)),
                                     ary(true,  new List<Integer>(1, 2, 3), Common.list(2, 4)),
                                     ary(false, new List<Integer>(1, 2, 3), Common.list(4)),
                                     ary(false, new List<Integer>(1, 2, 3), Common.list(4, 5)));
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
        return Common.<Object[]>list(ary(true,  new List<Integer>(1, 2, 3), 1),
                                     ary(true,  new List<Integer>(1, 2, 3), 4, 3),
                                     ary(false, new List<Integer>(1, 2, 3), 4));
    }

    @Test
    @Parameters
    public void first(Object expected, List<Object> list) {
        assertEqual(expected, list.first(), message("list", list));
    }

    public java.util.List<Object[]> parametersForFirst() {
        return Common.<Object[]>list(ary(new Integer(6), List.of(6)),
                                     ary(new Integer(6), List.of(6, 7)),
                                     ary((Integer)null,  List.<Integer>of()));
    }

    @Test
    @Parameters
    public void last(Object expected, List<Object> list) {
        assertEqual(expected, list.last(), message("list", list));
    }

    public java.util.List<Object[]> parametersForLast() {
        return Common.<Object[]>list(ary(new Integer(6), List.of(6)),
                                     ary(new Integer(7), List.of(6, 7)),
                                     ary((Integer)null,  List.<Integer>of()));
    }

    @Test
    @Parameters
    public void get(Object expected, List<Object> list, int idx) {
        assertEqual(expected, list.get(idx), message("list", list));
    }

    public java.util.List<Object[]> parametersForGet() {
        return Common.<Object[]>list(ary(new Integer(6), List.of(6), 0),
                                     ary(null,           List.of(6), 1),
                                     ary(new Integer(6), List.of(6), -1),
                                     ary(new Integer(7), List.of(6, 7), -1),
                                     ary(new Integer(6), List.of(6, 7), -2),
                                     ary(null,           List.of(6, 7),  2),
                                     ary(null,           List.of(6, 7), -3),
                                     ary(null,           List.<Integer>of(), 0),
                                     ary(null,           List.<Integer>of(), -1),
                                     ary(null,           List.<Integer>of(), 1));
                                     
    }

    @Test
    @Parameters
    public void getRange(List<Object> expected, List<Object> list, int from, int to) {
        assertEqual(expected, list.get(from, to), message("list", list, "from", from, "to", to));
    }

    public java.util.List<Object[]> parametersForGetRange() {
        return Common.<Object[]>list(ary(List.of(),     List.of(),  0, 0),
                                     ary(List.of(6),    List.of(6), 0, 0),
                                     ary(List.of(6),    List.of(6), 0, 1),
                                     ary(List.of(6),    List.of(6), 0, 2),
                                     ary(List.of(6),    List.of(6, 7), 0, 0),
                                     ary(List.of(6, 7), List.of(6, 7), 0, 1),
                                     ary(List.of(7),    List.of(6, 7), 1, 1),
                                     ary(List.of(),     List.of(6, 7), 1, 0),
                                     ary(List.of(6, 7), List.of(6, 7), 0, -1),
                                     ary(List.of(6),    List.of(6, 7), 0, -2),
                                     ary(List.of(7),    List.of(6, 7), 1, -1),
                                     ary(List.of(),     List.of(6, 7), 1, -2),
                                     ary(List.of(),     List.of(), 0, -1));
                                     
    }

    @Test
    @Parameters
    public void append(List<Object> expected, List<Object> list, Object obj) {
        // the returned value is also the list
        assertEqual(expected, list.append(obj), message("list", list, "obj", obj));
        assertEqual(expected, list, message("list", list, "obj", obj));
    }

    public java.util.List<Object[]> parametersForAppend() {
        return Common.<Object[]>list(ary(List.of(6, 7),     List.of(6), 7),
                                     ary(List.of(6, null),  List.of(6), (Integer)null),
                                     ary(List.of(7),        List.<Integer>of(), 7));                                     
    }

    @Test
    @Parameters
    public void set(List<Object> expected, List<Object> list, int index, Object obj) {
        if (expected == null) {
            try {
                list.set(index, obj);
                Assert.fail("expected IllegalArgumentException exception");
            }
            catch (IllegalArgumentException iae) {
                // good
            }
        }
        else {
            list.set(index, obj);
            assertEqual(expected, list, message("index", index, "obj", obj));
        }
    }

    public java.util.List<Object[]> parametersForSet() {
        return Common.<Object[]>list(ary(List.of(6, 7),     List.of(6),           1, 7),
                                     ary(List.of(6, null),  List.<Integer>of(6),  1, (Integer)null),
                                     ary(List.of(7),        List.<Integer>of(),   0, 7),
                                     ary(null,              List.<Integer>of(),  -1, 7),
                                     ary(List.of(7),        List.<Integer>of(6), -1, 7),
                                     ary(null,              List.<Integer>of(6), -2, 7));
    }

    @Test
    @Parameters
    public void removeAll(Boolean expReturned, List<Object> expected, List<Object> list, Object toRemove) {
        Boolean result = list.removeAll(toRemove);
        assertEqual(expReturned, result, message("list", list, "toRemove", toRemove));
        assertEqual(expected,    list,   message("list", list, "toRemove", toRemove));
    }

    public java.util.List<Object[]> parametersForRemoveAll() {
        return Common.<Object[]>list(ary(true,   List.of(1),         List.of(1, 2),       2),
                                     ary(true,   List.of(1),         List.of(1, 2, 2),    2),
                                     ary(true,   List.<Integer>of(), List.of(2, 2),       2),
                                     ary(false,  List.<Integer>of(), List.<Integer>of(),  2),
                                     ary(false,  List.of(1),         List.of(1), 2));
    }

    @Test
    @Parameters
    public void getRandomElement(Boolean exp, List<Object> list) {
        Object result = list.getRandomElement();
        assertEqual(exp, result != null, message("list", list, "exp", exp, "result", result));
    }

    public java.util.List<Object[]> parametersForGetRandomElement() {
        return Common.<Object[]>list(ary(true,   List.of(1)),
                                     ary(true,   List.of(1, 2)),
                                     ary(false,  List.<Integer>of()));
    }

    @Test
    @Parameters
    public void takeFirst(Object expReturn, List<Object> expList, List<Object> list) {
        List<Object> origList = new List<Object>(list);
        Object result = list.takeFirst();
        assertEqual(true, expReturn == result, message("origList", origList, "expReturn", expReturn, "result", result));
        assertEqual(expList, list, message("origList", origList));
    }

    public java.util.List<Object[]> parametersForTakeFirst() {
        return Common.<Object[]>list(ary(1, List.<Integer>of(), List.of(1)),
                                     ary(1, List.of(2), List.of(1, 2)),
                                     ary("a", List.<String>of(), List.of("a")),
                                     ary("a", List.of("b"), List.of("a", "b")),
                                     ary((Integer)null, List.<Integer>of(), List.<Integer>of()));
    }    

    @Test
    @Parameters
    public void takeLast(Object expReturn, List<Object> expList, List<Object> list) {
        List<Object> origList = new List<Object>(list);
        Object result = list.takeLast();
        assertEqual(true, expReturn == result, message("origList", origList, "expReturn", expReturn, "result", result));
        assertEqual(expList, list, message("origList", origList));
    }

    public java.util.List<Object[]> parametersForTakeLast() {
        return Common.<Object[]>list(ary(1, List.<Integer>of(), List.of(1)),
                                     ary(2, List.of(1), List.of(1, 2)),
                                     ary("a", List.<String>of(), List.of("a")),
                                     ary("b", List.of("a"), List.of("a", "b")),
                                     ary((Integer)null, List.<Integer>of(), List.<Integer>of()));
    }
    
    @Test
    @Parameters
    public void unique(List<Object> expected, List<Object> list) {
        List<Object> origList = new List<Object>(list);
        List<Object> result = list.unique();
        assertEqual(expected, result, message("origList", origList));
        assertEqual(origList, list, message("origList", origList));
    }

    public java.util.List<Object[]> parametersForUnique() {
        return Common.<Object[]>list(objary(List.of(1), List.of(1)),
                                     objary(List.of(1), List.of(1, 1)),
                                     objary(List.of(1, 2), List.of(1, 2)),
                                     objary(List.of(2, 1), List.of(2, 1)),
                                     objary(List.of(1, 2), List.of(1, 2, 1)),
                                     objary(List.<Integer>of(), List.<Integer>of()));
    }    

    @Test
    @Parameters
    public void compact(List<Object> expected, List<Object> list) {
        List<Object> origList = new List<Object>(list);
        List<Object> result = list.compact();
        assertEqual(expected, result, message("origList", origList));
        assertEqual(origList, list, message("origList", origList));
    }

    public java.util.List<Object[]> parametersForCompact() {
        List<Integer> emptyList = List.<Integer>of();
        return Common.<Object[]>list(objary(emptyList, emptyList),
                                     objary(List.of(1), List.of(1)),
                                     objary(emptyList, List.<Integer>of((Integer)null)),
                                     objary(List.of(1), List.of(1, null)),
                                     objary(List.of(1), List.of(1, null, null)),
                                     objary(List.of(1), List.of(null, 1)),
                                     objary(List.of(1), List.of(null, 1, null)),
                                     objary(List.of(1, 1), List.of(1, 1, null)),
                                     objary(List.of(1, 2), List.of(1, 2, null)),
                                     objary(List.of(2, 1), List.of(2, 1, null)));
    }


    @Test
    @Parameters
    public void join(String expected, List<Object> list, String delimiter) {
        String result = list.join(delimiter);
        assertEqual(expected, result, message("list", list, "delimiter", delimiter));
    }

    public java.util.List<Object[]> parametersForJoin() {
        List<Integer> emptyList = List.<Integer>of();
        return Common.<Object[]>list(objary("", emptyList, ""),
                                     objary("", emptyList, "x"),
                                     objary("1", List.of(1), ""),
                                     objary("1null", List.of(1, null), ""),
                                     objary("1", List.of(1), "x"),
                                     objary("1x2", List.of(1, 2), "x"));
    }

    @Test
    @Parameters
    public void plus(List<Object> expected, List<Object> list, List<Object> other) {
        List<Object> result = list.plus(other);
        assertEqual(expected, result);
    }
    
    private List<Object[]> parametersForPlus() {
        List<Object[]> params = List.<Object[]>of();

        params.add(objary(List.<Integer>of(1, 2, 3, 4), List.<Integer>of(1, 2), List.<Integer>of(3, 4)));
        params.add(objary(List.<Integer>of(1, 2, 3), List.<Integer>of(1, 2), List.<Integer>of(3)));
        params.add(objary(List.<Integer>of(1, 2), List.<Integer>of(1, 2), List.<Integer>of()));
        params.add(objary(List.<Integer>of(1, 2, 3, 3), List.<Integer>of(1, 2, 3), List.<Integer>of(3)));
        params.add(objary(List.<Integer>of(1, 2, 3, 1), List.<Integer>of(1, 2, 3), List.<Integer>of(1)));
        
        return params;
    }

    @Test
    @Parameters
    public void minus(List<Object> expected, List<Object> list, List<Object> other) {
        List<Object> result = list.minus(other);
        assertEqual(expected, result);
    }
    
    private List<Object[]> parametersForMinus() {
        List<Object[]> params = List.<Object[]>of();

        params.add(objary(List.<Integer>of(2), List.<Integer>of(1, 2), List.<Integer>of(1)));
        params.add(objary(List.<Integer>of(1), List.<Integer>of(1, 2), List.<Integer>of(2)));
        params.add(objary(List.<Integer>of(1, 1), List.<Integer>of(1, 1), List.<Integer>of(2)));
        params.add(objary(List.<Integer>of(1, 1), List.<Integer>of(1, 2, 1), List.<Integer>of(2)));
        
        params.add(objary(List.<Integer>of(), List.<Integer>of(), List.<Integer>of()));
        params.add(objary(List.<Integer>of(), List.<Integer>of(), List.<Integer>of(1)));
        params.add(objary(List.<Integer>of(1), List.<Integer>of(1), List.<Integer>of()));
        
        return params;
    }
}
