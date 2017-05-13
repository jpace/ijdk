package org.incava.ijdk.collect;

import java.util.Arrays;
import java.util.Collection;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.incava.ijdk.lang.Common;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.ijdk.lang.Common.*;
import static org.incava.test.Assertions.*;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

@RunWith(JUnitParamsRunner.class)
public class TestDynamicArray {
    private DynamicArray<Integer> emptyIntegerList() {
        return DynamicArray.<Integer>of();
    }
    
    @Test
    public void ctorEmpty() {
        DynamicArray<Object> expected = new DynamicArray<Object>();
        DynamicArray<Object> actual = new DynamicArray<Object>();
        assertEqual(expected, actual);
    }

    @Test
    public void ctorCollection() {
        Collection<String> coll = Arrays.asList(new String[] { "a", "b", "c" });
        DynamicArray<Object> actual = new DynamicArray<Object>(coll);
        assertEqual(Arrays.asList(new Object[] { "a", "b", "c" }), actual);
    }

    @Test
    public void ctorNull() {
        java.util.Collection<Object> nullColl = null;
        DynamicArray<Object> expected = new DynamicArray<Object>();
        DynamicArray<Object> actual = new DynamicArray<Object>(nullColl);
        assertEqual(expected, actual);
    }

    @Test
    public void empty() {
        DynamicArray<Object> expected = new DynamicArray<Object>();
        DynamicArray<Object> actual = DynamicArray.<Object>empty();
        assertEqual(expected, actual);
    }    
    
    @Test
    public void toStringList() {
        DynamicArray<String> expected = new DynamicArray<String>();
        expected.add("1");
        expected.add("2");
        expected.add("3");

        DynamicArray<Integer> numbers = new DynamicArray<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        assertEqual(expected, numbers.toStringList());
    }

    @Test
    @Parameters
    public void containsAnyCollection(Boolean expected, DynamicArray<Integer> list, java.util.List<Integer> coll) {
        assertEqual(expected, list.containsAny(coll));
    }

    public java.util.List<Object[]> parametersForContainsAnyCollection() {
        return paramsList(params(true,  new DynamicArray<Integer>(1, 2, 3), Common.list(1)),
                          params(true,  new DynamicArray<Integer>(1, 2, 3), Common.list(2, 4)),
                          params(false, new DynamicArray<Integer>(1, 2, 3), Common.list(4)),
                          params(false, new DynamicArray<Integer>(1, 2, 3), Common.list(4, 5)));
    }
    
    @Test
    @Parameters
    public void containsAnyArray(Boolean expected, DynamicArray<Integer> list, Integer ... args) {
        assertEqual(expected, list.containsAny(args));
    }
    
    @Test
    public void containsAnyArrayEmpty() {
        assertEqual(false, new DynamicArray<Integer>(1, 2, 3).containsAny());
    }

    public java.util.List<Object[]> parametersForContainsAnyArray() {
        return paramsList(params(true,  new DynamicArray<Integer>(1, 2, 3), 1),
                          params(true,  new DynamicArray<Integer>(1, 2, 3), 4, 3),
                          params(false, new DynamicArray<Integer>(1, 2, 3), 4));
    }

    @Test
    @Parameters
    public void first(Object expected, DynamicArray<Object> list) {
        assertEqual(expected, list.first(), message("list", list));
    }

    public java.util.List<Object[]> parametersForFirst() {
        return paramsList(params(new Integer(6), DynamicArray.of(6)),
                          params(new Integer(6), DynamicArray.of(6, 7)),
                          params((Integer)null,  emptyIntegerList()));
    }

    @Test
    @Parameters
    public void last(Object expected, DynamicArray<Object> list) {
        assertEqual(expected, list.last(), message("list", list));
    }

    public java.util.List<Object[]> parametersForLast() {
        return paramsList(params(new Integer(6), DynamicArray.of(6)),
                          params(new Integer(7), DynamicArray.of(6, 7)),
                          params((Integer)null,  emptyIntegerList()));
    }

    @Test
    @Parameters
    public void get(Object expected, DynamicArray<Object> list, int idx) {
        assertEqual(expected, list.get(idx), message("list", list));
    }

    public java.util.List<Object[]> parametersForGet() {
        return paramsList(params(new Integer(6), DynamicArray.of(6), 0),
                          params(null,           DynamicArray.of(6), 1),
                          params(new Integer(6), DynamicArray.of(6), -1),
                          params(new Integer(7), DynamicArray.of(6, 7), -1),
                          params(new Integer(6), DynamicArray.of(6, 7), -2),
                          params(null,           DynamicArray.of(6, 7),  2),
                          params(null,           DynamicArray.of(6, 7), -3),
                          params(null,           emptyIntegerList(), 0),
                          params(null,           emptyIntegerList(), -1),
                          params(null,           emptyIntegerList(), 1));
                                     
    }

    @Test
    @Parameters
    public void getRange(DynamicArray<Object> expected, DynamicArray<Object> list, int from, int to) {
        assertEqual(expected, list.get(from, to), message("list", list, "from", from, "to", to));
    }

    public java.util.List<Object[]> parametersForGetRange() {
        return paramsList(params(DynamicArray.of(),     DynamicArray.of(),  0, 0),
                          params(DynamicArray.of(6),    DynamicArray.of(6), 0, 0),
                          params(DynamicArray.of(6),    DynamicArray.of(6), 0, 1),
                          params(DynamicArray.of(6),    DynamicArray.of(6), 0, 2),
                          params(DynamicArray.of(6),    DynamicArray.of(6, 7), 0, 0),
                          params(DynamicArray.of(6, 7), DynamicArray.of(6, 7), 0, 1),
                          params(DynamicArray.of(7),    DynamicArray.of(6, 7), 1, 1),
                          params(DynamicArray.of(),     DynamicArray.of(6, 7), 1, 0),
                          params(DynamicArray.of(6, 7), DynamicArray.of(6, 7), 0, -1),
                          params(DynamicArray.of(6),    DynamicArray.of(6, 7), 0, -2),
                          params(DynamicArray.of(7),    DynamicArray.of(6, 7), 1, -1),
                          params(DynamicArray.of(),     DynamicArray.of(6, 7), 1, -2),
                          params(DynamicArray.of(),     DynamicArray.of(), 0, -1));
                                     
    }

    @Test
    @Parameters
    public void append(DynamicArray<Object> expected, DynamicArray<Object> list, Object obj) {
        String msg = message("list", list, "obj", obj);
        // the returned value is also the list
        assertEqual(expected, list.append(obj), msg);
        assertEqual(expected, list,             msg);
    }

    public java.util.List<Object[]> parametersForAppend() {
        return paramsList(params(DynamicArray.of(6, 7),     DynamicArray.of(6), 7),
                          params(DynamicArray.of(6, null),  DynamicArray.of(6), (Integer)null),
                          params(DynamicArray.of(7),        emptyIntegerList(), 7));                                     
    }

    @Test
    @Parameters
    public void set(DynamicArray<Object> expected, DynamicArray<Object> list, int index, Object obj) {
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
        return paramsList(params(DynamicArray.of(6, 7),     DynamicArray.of(6),          1, 7),
                          params(DynamicArray.of(6, null),  DynamicArray.of(6),          1, (Integer)null),
                          params(DynamicArray.of(7),        emptyIntegerList(),  0, 7),
                          params(null,              emptyIntegerList(), -1, 7),
                          params(DynamicArray.of(7),        DynamicArray.of(6),         -1, 7),
                          params(null,              DynamicArray.of(6),         -2, 7));
    }

    @Test
    @Parameters
    public void removeAll(Boolean expReturned, DynamicArray<Object> expected, DynamicArray<Object> list, Object toRemove) {
        Boolean result = list.removeAll(toRemove);
        assertEqual(expReturned, result, message("list", list, "toRemove", toRemove));
        assertEqual(expected,    list,   message("list", list, "toRemove", toRemove));
    }

    public java.util.List<Object[]> parametersForRemoveAll() {
        return paramsList(params(true,   DynamicArray.of(1),         DynamicArray.of(1, 2),       2),
                          params(true,   DynamicArray.of(1),         DynamicArray.of(1, 2, 2),    2),
                          params(true,   emptyIntegerList(), DynamicArray.of(2, 2),       2),
                          params(false,  emptyIntegerList(), emptyIntegerList(),  2),
                          params(false,  DynamicArray.of(1),         DynamicArray.of(1), 2));
    }

    @Test
    @Parameters
    public void getRandomElement(Boolean exp, DynamicArray<Object> list) {
        Object result = list.getRandomElement();
        assertEqual(exp, result != null, message("list", list, "exp", exp, "result", result));
    }

    public java.util.List<Object[]> parametersForGetRandomElement() {
        return paramsList(params(true,   DynamicArray.of(1)),
                          params(true,   DynamicArray.of(1, 2)),
                          params(false,  emptyIntegerList()));
    }

    @Test
    @Parameters
    public void takeFirst(Object expReturn, DynamicArray<Object> expList, DynamicArray<Object> list) {
        DynamicArray<Object> origList = new DynamicArray<Object>(list);
        Object result = list.takeFirst();
        assertEqual(true, expReturn == result, message("origList", origList, "expReturn", expReturn, "result", result));
        assertEqual(expList, list, message("origList", origList));
    }

    public java.util.List<Object[]> parametersForTakeFirst() {
        return paramsList(params(1, emptyIntegerList(), DynamicArray.of(1)),
                          params(1, DynamicArray.of(2), DynamicArray.of(1, 2)),
                          params("a", DynamicArray.<String>of(), DynamicArray.of("a")),
                          params("a", DynamicArray.of("b"), DynamicArray.of("a", "b")),
                          params((Integer)null, emptyIntegerList(), emptyIntegerList()));
    }    

    @Test
    @Parameters
    public void takeLast(Object expReturn, DynamicArray<Object> expList, DynamicArray<Object> list) {
        DynamicArray<Object> origList = new DynamicArray<Object>(list);
        Object result = list.takeLast();
        assertEqual(true, expReturn == result, message("origList", origList, "expReturn", expReturn, "result", result));
        assertEqual(expList, list, message("origList", origList));
    }

    public java.util.List<Object[]> parametersForTakeLast() {
        return paramsList(params(1, emptyIntegerList(), DynamicArray.of(1)),
                          params(2, DynamicArray.of(1), DynamicArray.of(1, 2)),
                          params("a", DynamicArray.<String>of(), DynamicArray.of("a")),
                          params("b", DynamicArray.of("a"), DynamicArray.of("a", "b")),
                          params((Integer)null, emptyIntegerList(), emptyIntegerList()));
    }
    
    @Test
    @Parameters
    public void unique(DynamicArray<Object> expected, DynamicArray<Object> list) {
        DynamicArray<Object> origList = new DynamicArray<Object>(list);
        DynamicArray<Object> result = list.unique();
        assertEqual(expected, result, message("origList", origList));
        assertEqual(origList, list, message("origList", origList));
    }

    public java.util.List<Object[]> parametersForUnique() {
        return paramsList(params(DynamicArray.of(1), DynamicArray.of(1)),
                          params(DynamicArray.of(1), DynamicArray.of(1, 1)),
                          params(DynamicArray.of(1, 2), DynamicArray.of(1, 2)),
                          params(DynamicArray.of(2, 1), DynamicArray.of(2, 1)),
                          params(DynamicArray.of(1, 2), DynamicArray.of(1, 2, 1)),
                          params(emptyIntegerList(), emptyIntegerList()));
    }    

    @Test
    @Parameters
    public void compact(DynamicArray<Object> expected, DynamicArray<Object> list) {
        DynamicArray<Object> origList = new DynamicArray<Object>(list);
        DynamicArray<Object> result = list.compact();
        assertEqual(expected, result, message("origList", origList));
        assertEqual(origList, list, message("origList", origList));
    }

    public java.util.List<Object[]> parametersForCompact() {
        DynamicArray<Integer> emptyList = emptyIntegerList();
        return paramsList(params(emptyList, emptyList),
                          params(DynamicArray.of(1), DynamicArray.of(1)),
                          params(emptyList, DynamicArray.<Integer>of((Integer)null)),
                          params(DynamicArray.of(1), DynamicArray.of(1, null)),
                          params(DynamicArray.of(1), DynamicArray.of(1, null, null)),
                          params(DynamicArray.of(1), DynamicArray.of(null, 1)),
                          params(DynamicArray.of(1), DynamicArray.of(null, 1, null)),
                          params(DynamicArray.of(1, 1), DynamicArray.of(1, 1, null)),
                          params(DynamicArray.of(1, 2), DynamicArray.of(1, 2, null)),
                          params(DynamicArray.of(2, 1), DynamicArray.of(2, 1, null)));
    }


    @Test
    @Parameters
    public void join(String expected, DynamicArray<Object> list, String delimiter) {
        String result = list.join(delimiter);
        assertEqual(expected, result, message("list", list, "delimiter", delimiter));
    }

    public java.util.List<Object[]> parametersForJoin() {
        DynamicArray<Integer> emptyList = emptyIntegerList();
        return paramsList(params("", emptyList, ""),
                          params("", emptyList, "x"),
                          params("1", DynamicArray.of(1), ""),
                          params("1null", DynamicArray.of(1, null), ""),
                          params("1", DynamicArray.of(1), "x"),
                          params("1x2", DynamicArray.of(1, 2), "x"));
    }

    @Test
    @Parameters
    public void plus(DynamicArray<Object> expected, DynamicArray<Object> list, DynamicArray<Object> other) {
        DynamicArray<Object> result = list.plus(other);
        assertEqual(expected, result);
    }
    
    private DynamicArray<Object[]> parametersForPlus() {
        DynamicArray<Object[]> params = DynamicArray.<Object[]>of();

        params.add(params(DynamicArray.of(1, 2, 3, 4), DynamicArray.of(1, 2), DynamicArray.of(3, 4)));
        params.add(params(DynamicArray.of(1, 2, 3), DynamicArray.of(1, 2), DynamicArray.of(3)));
        params.add(params(DynamicArray.of(1, 2), DynamicArray.of(1, 2), emptyIntegerList()));
        params.add(params(DynamicArray.of(1, 2, 3, 3), DynamicArray.of(1, 2, 3), DynamicArray.of(3)));
        params.add(params(DynamicArray.of(1, 2, 3, 1), DynamicArray.of(1, 2, 3), DynamicArray.of(1)));
        
        return params;
    }

    @Test
    @Parameters
    public void minus(DynamicArray<Object> expected, DynamicArray<Object> list, DynamicArray<Object> other) {
        DynamicArray<Object> result = list.minus(other);
        assertEqual(expected, result);
    }
    
    private java.util.List<Object[]> parametersForMinus() {
        java.util.List<Object[]> params = paramsList();
        
        params.add(params(DynamicArray.of(2), DynamicArray.of(1, 2), DynamicArray.of(1)));
        params.add(params(DynamicArray.of(1), DynamicArray.of(1, 2), DynamicArray.of(2)));
        params.add(params(DynamicArray.of(1, 1), DynamicArray.of(1, 1), DynamicArray.of(2)));
        params.add(params(DynamicArray.of(1, 1), DynamicArray.of(1, 2, 1), DynamicArray.of(2)));
        
        params.add(params(emptyIntegerList(), emptyIntegerList(), emptyIntegerList()));
        params.add(params(emptyIntegerList(), emptyIntegerList(), DynamicArray.of(1)));
        params.add(params(DynamicArray.of(1), DynamicArray.of(1), emptyIntegerList()));
        
        return params;
    }

    @Test
    @Parameters
    public void elements(DynamicArray<Object> expected, DynamicArray<Object> list, int ... indices) {
        DynamicArray<Object> result = list.elements(indices);
        assertEqual(expected, result, message("indices", indices));
    }
    
    private DynamicArray<Object[]> parametersForElements() {
        DynamicArray<Object[]> params = DynamicArray.<Object[]>of();

        DynamicArray<Integer> list = DynamicArray.of(6, 7, 8);

        params.add(params(DynamicArray.of(6), list, new int[] { 0 }));
        params.add(params(DynamicArray.of(7), list, new int[] { 1 }));
        params.add(params(DynamicArray.of(8), list, new int[] { 2 }));

        params.add(params(DynamicArray.of(6, 7), list, new int[] { 0, 1 }));
        params.add(params(DynamicArray.of(7, 6), list, new int[] { 1, 0 }));

        params.add(params(DynamicArray.of(6, 6), list, new int[] { 0, 0 }));

        params.add(params(DynamicArray.of(8), list, new int[] { -1 }));
        params.add(params(DynamicArray.of(8, 7), list, new int[] { -1, 1 }));
        params.add(params(DynamicArray.of(8, 7), list, new int[] { -1, -2 }));
        
        params.add(params(DynamicArray.<Integer>of((Integer)null), list, new int[] { -4 }));
        
        return params;
    }

    @Test
    public void demo() {
        Integer x = null;
        DynamicArray<Integer> nums = DynamicArray.of(1, 3, 5, 7);
        x = nums.get(0);
        assertEqual(1, x);
        
        x = nums.get(-1);
        assertEqual(7, x);
        
        nums.append(9).append(11).append(13);
        assertEqual(DynamicArray.of(1, 3, 5, 7, 9, 11, 13), nums);

        x = nums.get(-3);
        assertEqual(9, x);
        
        x = nums.first();
        assertEqual(1, x);

        x = nums.last();
        assertEqual(13, x);
        
        x = nums.takeFirst();
        assertEqual(1, x);
        assertEqual(DynamicArray.of(3, 5, 7, 9, 11, 13), nums);
        
        x = nums.takeFirst();
        assertEqual(3, x);
        assertEqual(DynamicArray.of(5, 7, 9, 11, 13), nums);

        x = nums.takeLast();
        assertEqual(13, x);
        assertEqual(DynamicArray.of(5, 7, 9, 11), nums);

        StringList strList = nums.toStringList();
        assertEqual(StringList.of("5", "7", "9", "11"), strList);

        nums.append(2).append(2).append(2);
        assertEqual(DynamicArray.of(5, 7, 9, 11, 2, 2, 2), nums);

        DynamicArray<Integer> uniq = nums.unique();
        assertEqual(DynamicArray.of(5, 7, 9, 11, 2), uniq);

        assertEqual(true, nums.containsAny(2, 3));
        assertEqual(false, nums.containsAny(3, 4));

        nums.removeAll(2);
        assertEqual(DynamicArray.of(5, 7, 9, 11), nums);

        nums.set(0, 4);
        assertEqual(DynamicArray.of(4, 7, 9, 11), nums);

        nums.set(-1, 10);
        assertEqual(DynamicArray.of(4, 7, 9, 10), nums);

        nums.set(-2, 8);
        assertEqual(DynamicArray.of(4, 7, 8, 10), nums);

        nums.set(1, 6);
        assertEqual(DynamicArray.of(4, 6, 8, 10), nums);

        x = nums.getRandomElement();
        assertEqual(true, DynamicArray.of(4, 6, 8, 10).contains(x));

        String str = nums.join(" + ");
        assertEqual("4 + 6 + 8 + 10", str);
        
        DynamicArray<Integer> odds = DynamicArray.of(1, 3, 5);
        DynamicArray<Integer> evens = DynamicArray.of(2, 4, 6);
        DynamicArray<Integer> numbers = odds.plus(evens);
        assertEqual(DynamicArray.of(1, 3, 5, 2, 4, 6), numbers);
        
        DynamicArray<Integer> squares = numbers.minus(DynamicArray.of(2, 3, 5, 6));
        assertEqual(DynamicArray.of(1, 4), squares);

        DynamicArray<Integer> elements = numbers.elements(1, 0, -2, 0, -4);
        assertEqual(DynamicArray.of(3, 1, 4, 1, 5), elements);
    }
}
