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
    private List<Integer> emptyIntegerList() {
        return List.<Integer>of();
    }
    
    @Test
    public void ctorEmpty() {
        List<Object> expected = new List<Object>();
        List<Object> actual = new List<Object>();
        assertEqual(expected, actual);
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
        assertEqual(expected, actual);
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

        assertEqual(expected, numbers.toStringList());
    }

    @Test
    @Parameters
    public void containsAnyCollection(Boolean expected, List<Integer> list, java.util.List<Integer> coll) {
        assertEqual(expected, list.containsAny(coll));
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
        assertEqual(expected, list.containsAny(args));
    }
    
    @Test
    public void containsAnyArrayEmpty() {
        assertEqual(false, new List<Integer>(1, 2, 3).containsAny());
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
                                     ary((Integer)null,  emptyIntegerList()));
    }

    @Test
    @Parameters
    public void last(Object expected, List<Object> list) {
        assertEqual(expected, list.last(), message("list", list));
    }

    public java.util.List<Object[]> parametersForLast() {
        return Common.<Object[]>list(ary(new Integer(6), List.of(6)),
                                     ary(new Integer(7), List.of(6, 7)),
                                     ary((Integer)null,  emptyIntegerList()));
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
                                     ary(null,           emptyIntegerList(), 0),
                                     ary(null,           emptyIntegerList(), -1),
                                     ary(null,           emptyIntegerList(), 1));
                                     
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
        String msg = message("list", list, "obj", obj);
        // the returned value is also the list
        assertEqual(expected, list.append(obj), msg);
        assertEqual(expected, list,             msg);
    }

    public java.util.List<Object[]> parametersForAppend() {
        return Common.<Object[]>list(ary(List.of(6, 7),     List.of(6), 7),
                                     ary(List.of(6, null),  List.of(6), (Integer)null),
                                     ary(List.of(7),        emptyIntegerList(), 7));                                     
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
        return Common.<Object[]>list(ary(List.of(6, 7),     List.of(6),          1, 7),
                                     ary(List.of(6, null),  List.of(6),          1, (Integer)null),
                                     ary(List.of(7),        emptyIntegerList(),  0, 7),
                                     ary(null,              emptyIntegerList(), -1, 7),
                                     ary(List.of(7),        List.of(6),         -1, 7),
                                     ary(null,              List.of(6),         -2, 7));
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
                                     ary(true,   emptyIntegerList(), List.of(2, 2),       2),
                                     ary(false,  emptyIntegerList(), emptyIntegerList(),  2),
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
                                     ary(false,  emptyIntegerList()));
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
        return Common.<Object[]>list(ary(1, emptyIntegerList(), List.of(1)),
                                     ary(1, List.of(2), List.of(1, 2)),
                                     ary("a", List.<String>of(), List.of("a")),
                                     ary("a", List.of("b"), List.of("a", "b")),
                                     ary((Integer)null, emptyIntegerList(), emptyIntegerList()));
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
        return Common.<Object[]>list(ary(1, emptyIntegerList(), List.of(1)),
                                     ary(2, List.of(1), List.of(1, 2)),
                                     ary("a", List.<String>of(), List.of("a")),
                                     ary("b", List.of("a"), List.of("a", "b")),
                                     ary((Integer)null, emptyIntegerList(), emptyIntegerList()));
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
                                     objary(emptyIntegerList(), emptyIntegerList()));
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
        List<Integer> emptyList = emptyIntegerList();
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
        List<Integer> emptyList = emptyIntegerList();
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

        params.add(objary(List.of(1, 2, 3, 4), List.of(1, 2), List.of(3, 4)));
        params.add(objary(List.of(1, 2, 3), List.of(1, 2), List.of(3)));
        params.add(objary(List.of(1, 2), List.of(1, 2), emptyIntegerList()));
        params.add(objary(List.of(1, 2, 3, 3), List.of(1, 2, 3), List.of(3)));
        params.add(objary(List.of(1, 2, 3, 1), List.of(1, 2, 3), List.of(1)));
        
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

        params.add(objary(List.of(2), List.of(1, 2), List.of(1)));
        params.add(objary(List.of(1), List.of(1, 2), List.of(2)));
        params.add(objary(List.of(1, 1), List.of(1, 1), List.of(2)));
        params.add(objary(List.of(1, 1), List.of(1, 2, 1), List.of(2)));
        
        params.add(objary(emptyIntegerList(), emptyIntegerList(), emptyIntegerList()));
        params.add(objary(emptyIntegerList(), emptyIntegerList(), List.of(1)));
        params.add(objary(List.of(1), List.of(1), emptyIntegerList()));
        
        return params;
    }

    @Test
    @Parameters
    public void elements(List<Object> expected, List<Object> list, int ... indices) {
        List<Object> result = list.elements(indices);
        assertEqual(expected, result, message("indices", indices));
    }
    
    private List<Object[]> parametersForElements() {
        List<Object[]> params = List.<Object[]>of();

        List<Integer> list = List.of(6, 7, 8);

        params.add(objary(List.of(6), list, new int[] { 0 }));
        params.add(objary(List.of(7), list, new int[] { 1 }));
        params.add(objary(List.of(8), list, new int[] { 2 }));

        params.add(objary(List.of(6, 7), list, new int[] { 0, 1 }));
        params.add(objary(List.of(7, 6), list, new int[] { 1, 0 }));

        params.add(objary(List.of(6, 6), list, new int[] { 0, 0 }));

        params.add(objary(List.of(8), list, new int[] { -1 }));
        params.add(objary(List.of(8, 7), list, new int[] { -1, 1 }));
        params.add(objary(List.of(8, 7), list, new int[] { -1, -2 }));
        
        params.add(objary(List.<Integer>of((Integer)null), list, new int[] { -4 }));
        
        return params;
    }

    @Test
    public void demo() {
        Integer x = null;
        List<Integer> nums = List.of(1, 3, 5, 7);
        x = nums.get(0);
        assertEqual(1, x);
        
        x = nums.get(-1);
        assertEqual(7, x);
        
        nums.append(9).append(11).append(13);
        assertEqual(List.of(1, 3, 5, 7, 9, 11, 13), nums);

        x = nums.get(-3);
        assertEqual(9, x);
        
        x = nums.first();
        assertEqual(1, x);

        x = nums.last();
        assertEqual(13, x);
        
        x = nums.takeFirst();
        assertEqual(1, x);
        assertEqual(List.of(3, 5, 7, 9, 11, 13), nums);
        
        x = nums.takeFirst();
        assertEqual(3, x);
        assertEqual(List.of(5, 7, 9, 11, 13), nums);

        x = nums.takeLast();
        assertEqual(13, x);
        assertEqual(List.of(5, 7, 9, 11), nums);

        StringList strList = nums.toStringList();
        assertEqual(StringList.of("5", "7", "9", "11"), strList);

        nums.append(2).append(2).append(2);
        assertEqual(List.of(5, 7, 9, 11, 2, 2, 2), nums);

        List<Integer> uniq = nums.unique();
        assertEqual(List.of(5, 7, 9, 11, 2), uniq);

        assertEqual(true, nums.containsAny(2, 3));
        assertEqual(false, nums.containsAny(3, 4));

        nums.removeAll(2);
        assertEqual(List.of(5, 7, 9, 11), nums);

        nums.set(0, 4);
        assertEqual(List.of(4, 7, 9, 11), nums);

        nums.set(-1, 10);
        assertEqual(List.of(4, 7, 9, 10), nums);

        nums.set(-2, 8);
        assertEqual(List.of(4, 7, 8, 10), nums);

        nums.set(1, 6);
        assertEqual(List.of(4, 6, 8, 10), nums);

        x = nums.getRandomElement();
        assertEqual(true, List.of(4, 6, 8, 10).contains(x));

        String str = nums.join(" + ");
        assertEqual("4 + 6 + 8 + 10", str);
        
        List<Integer> odds = List.of(1, 3, 5);
        List<Integer> evens = List.of(2, 4, 6);
        List<Integer> numbers = odds.plus(evens);
        assertEqual(List.of(1, 3, 5, 2, 4, 6), numbers);
        
        List<Integer> squares = numbers.minus(List.of(2, 3, 5, 6));
        assertEqual(List.of(1, 4), squares);

        List<Integer> elements = numbers.elements(1, 0, -2, 0, -4);
        assertEqual(List.of(3, 1, 4, 1, 5), elements);
    }
}
