package org.incava.ijdk.collect;

import java.util.Arrays;
import java.util.Collection;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.ijdk.lang.Common;
import org.incava.attest.Parameterized;
import org.junit.Assert;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

public class ArrayTest extends Parameterized {
    private Array<Integer> emptyIntegerList() {
        return Array.<Integer>of();
    }
    
    @Test
    public void ctorEmpty() {
        Array<Object> expected = new Array<Object>();
        Array<Object> actual = new Array<Object>();
        assertEqual(expected, actual);
    }

    @Test
    public void ctorCollection() {
        Collection<String> coll = Arrays.asList(new String[] { "a", "b", "c" });
        Array<Object> actual = new Array<Object>(coll);
        assertEqual(Arrays.asList(new Object[] { "a", "b", "c" }), actual);
    }

    @Test
    public void ctorNull() {
        java.util.Collection<Object> nullColl = null;
        Array<Object> expected = new Array<Object>();
        Array<Object> actual = new Array<Object>(nullColl);
        assertEqual(expected, actual);
    }

    @Test
    public void empty() {
        Array<Object> expected = new Array<Object>();
        Array<Object> actual = Array.<Object>empty();
        assertEqual(expected, actual);
    }    
    
    @Test
    public void toStringList() {
        Array<String> expected = new Array<String>();
        expected.add("1");
        expected.add("2");
        expected.add("3");

        Array<Integer> numbers = new Array<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        assertEqual(expected, numbers.toStringList());
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void containsAnyCollection(Boolean expected, Array<Integer> list, java.util.List<Integer> coll) {
        assertEqual(expected, list.containsAny(coll));
    }

    public java.util.List<Object[]> parametersForContainsAnyCollection() {
        return paramsList(params(true,  new Array<Integer>(1, 2, 3), Common.list(1)),
                          params(true,  new Array<Integer>(1, 2, 3), Common.list(2, 4)),
                          params(false, new Array<Integer>(1, 2, 3), Common.list(4)),
                          params(false, new Array<Integer>(1, 2, 3), Common.list(4, 5)));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void containsAnyArray(Boolean expected, Array<Integer> list, Integer ... args) {
        assertEqual(expected, list.containsAny(args));
    }
    
    @Test
    public void containsAnyArrayEmpty() {
        assertEqual(false, new Array<Integer>(1, 2, 3).containsAny());
    }

    public java.util.List<Object[]> parametersForContainsAnyArray() {
        return paramsList(params(true,  new Array<Integer>(1, 2, 3), 1),
                          params(true,  new Array<Integer>(1, 2, 3), 4, 3),
                          params(false, new Array<Integer>(1, 2, 3), 4));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void first(Object expected, Array<Object> list) {
        assertEqual(expected, list.first(), message("list", list));
    }

    public java.util.List<Object[]> parametersForFirst() {
        return paramsList(params(new Integer(6), Array.of(6)),
                          params(new Integer(6), Array.of(6, 7)),
                          params((Integer)null,  emptyIntegerList()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void last(Object expected, Array<Object> list) {
        assertEqual(expected, list.last(), message("list", list));
    }

    public java.util.List<Object[]> parametersForLast() {
        return paramsList(params(new Integer(6), Array.of(6)),
                          params(new Integer(7), Array.of(6, 7)),
                          params((Integer)null,  emptyIntegerList()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void get(Object expected, Array<Object> list, int idx) {
        assertEqual(expected, list.get(idx), message("list", list));
    }

    public java.util.List<Object[]> parametersForGet() {
        return paramsList(params(new Integer(6), Array.of(6), 0),
                          params(null,           Array.of(6), 1),
                          params(new Integer(6), Array.of(6), -1),
                          params(new Integer(7), Array.of(6, 7), -1),
                          params(new Integer(6), Array.of(6, 7), -2),
                          params(null,           Array.of(6, 7),  2),
                          params(null,           Array.of(6, 7), -3),
                          params(null,           emptyIntegerList(), 0),
                          params(null,           emptyIntegerList(), -1),
                          params(null,           emptyIntegerList(), 1));
                                     
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void getRange(Array<Object> expected, Array<Object> list, int from, int to) {
        assertEqual(expected, list.get(from, to), message("list", list, "from", from, "to", to));
    }

    public java.util.List<Object[]> parametersForGetRange() {
        return paramsList(params(Array.of(),     Array.of(),  0, 0),
                          params(Array.of(6),    Array.of(6), 0, 0),
                          params(Array.of(6),    Array.of(6), 0, 1),
                          params(Array.of(6),    Array.of(6), 0, 2),
                          params(Array.of(6),    Array.of(6, 7), 0, 0),
                          params(Array.of(6, 7), Array.of(6, 7), 0, 1),
                          params(Array.of(7),    Array.of(6, 7), 1, 1),
                          params(Array.of(),     Array.of(6, 7), 1, 0),
                          params(Array.of(6, 7), Array.of(6, 7), 0, -1),
                          params(Array.of(6),    Array.of(6, 7), 0, -2),
                          params(Array.of(7),    Array.of(6, 7), 1, -1),
                          params(Array.of(),     Array.of(6, 7), 1, -2),
                          params(Array.of(),     Array.of(), 0, -1));
                                     
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void append(Array<Object> expected, Array<Object> list, Object obj) {
        String msg = message("list", list, "obj", obj);
        // the returned value is also the list
        assertEqual(expected, list.append(obj), msg);
        assertEqual(expected, list,             msg);
    }

    public java.util.List<Object[]> parametersForAppend() {
        return paramsList(params(Array.of(6, 7),     Array.of(6), 7),
                          params(Array.of(6, null),  Array.of(6), (Integer)null),
                          params(Array.of(7),        emptyIntegerList(), 7));                                     
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void set(Array<Object> expected, Array<Object> list, int index, Object obj) {
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
        return paramsList(params(Array.of(6, 7),     Array.of(6),          1, 7),
                          params(Array.of(6, null),  Array.of(6),          1, (Integer)null),
                          params(Array.of(7),        emptyIntegerList(),  0, 7),
                          params(null,              emptyIntegerList(), -1, 7),
                          params(Array.of(7),        Array.of(6),         -1, 7),
                          params(null,              Array.of(6),         -2, 7));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void removeAll(Boolean expReturned, Array<Object> expected, Array<Object> list, Object toRemove) {
        Boolean result = list.removeAll(toRemove);
        assertEqual(expReturned, result, message("list", list, "toRemove", toRemove));
        assertEqual(expected,    list,   message("list", list, "toRemove", toRemove));
    }

    public java.util.List<Object[]> parametersForRemoveAll() {
        return paramsList(params(true,   Array.of(1),         Array.of(1, 2),       2),
                          params(true,   Array.of(1),         Array.of(1, 2, 2),    2),
                          params(true,   emptyIntegerList(), Array.of(2, 2),       2),
                          params(false,  emptyIntegerList(), emptyIntegerList(),  2),
                          params(false,  Array.of(1),         Array.of(1), 2));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void getRandomElement(Boolean exp, Array<Object> list) {
        Object result = list.getRandomElement();
        assertEqual(exp, result != null, message("list", list, "exp", exp, "result", result));
    }

    public java.util.List<Object[]> parametersForGetRandomElement() {
        return paramsList(params(true,   Array.of(1)),
                          params(true,   Array.of(1, 2)),
                          params(false,  emptyIntegerList()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void takeFirst(Object expReturn, Array<Object> expList, Array<Object> list) {
        Array<Object> origList = new Array<Object>(list);
        Object result = list.takeFirst();
        assertEqual(true, expReturn == result, message("origList", origList, "expReturn", expReturn, "result", result));
        assertEqual(expList, list, message("origList", origList));
    }

    public java.util.List<Object[]> parametersForTakeFirst() {
        return paramsList(params(1, emptyIntegerList(), Array.of(1)),
                          params(1, Array.of(2), Array.of(1, 2)),
                          params("a", Array.<String>of(), Array.of("a")),
                          params("a", Array.of("b"), Array.of("a", "b")),
                          params((Integer)null, emptyIntegerList(), emptyIntegerList()));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void takeLast(Object expReturn, Array<Object> expList, Array<Object> list) {
        Array<Object> origList = new Array<Object>(list);
        Object result = list.takeLast();
        assertEqual(true, expReturn == result, message("origList", origList, "expReturn", expReturn, "result", result));
        assertEqual(expList, list, message("origList", origList));
    }

    public java.util.List<Object[]> parametersForTakeLast() {
        return paramsList(params(1, emptyIntegerList(), Array.of(1)),
                          params(2, Array.of(1), Array.of(1, 2)),
                          params("a", Array.<String>of(), Array.of("a")),
                          params("b", Array.of("a"), Array.of("a", "b")),
                          params((Integer)null, emptyIntegerList(), emptyIntegerList()));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void unique(Array<Object> expected, Array<Object> list) {
        Array<Object> origList = new Array<Object>(list);
        Array<Object> result = list.unique();
        assertEqual(expected, result, message("origList", origList));
        assertEqual(origList, list, message("origList", origList));
    }

    public java.util.List<Object[]> parametersForUnique() {
        return paramsList(params(Array.of(1), Array.of(1)),
                          params(Array.of(1), Array.of(1, 1)),
                          params(Array.of(1, 2), Array.of(1, 2)),
                          params(Array.of(2, 1), Array.of(2, 1)),
                          params(Array.of(1, 2), Array.of(1, 2, 1)),
                          params(emptyIntegerList(), emptyIntegerList()));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compact(Array<Object> expected, Array<Object> list) {
        Array<Object> origList = new Array<Object>(list);
        Array<Object> result = list.compact();
        assertEqual(expected, result, message("origList", origList));
        assertEqual(origList, list, message("origList", origList));
    }

    public java.util.List<Object[]> parametersForCompact() {
        Array<Integer> emptyList = emptyIntegerList();
        return paramsList(params(emptyList, emptyList),
                          params(Array.of(1), Array.of(1)),
                          params(emptyList, Array.<Integer>of((Integer)null)),
                          params(Array.of(1), Array.of(1, null)),
                          params(Array.of(1), Array.of(1, null, null)),
                          params(Array.of(1), Array.of(null, 1)),
                          params(Array.of(1), Array.of(null, 1, null)),
                          params(Array.of(1, 1), Array.of(1, 1, null)),
                          params(Array.of(1, 2), Array.of(1, 2, null)),
                          params(Array.of(2, 1), Array.of(2, 1, null)));
    }


    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void join(String expected, Array<Object> list, String delimiter) {
        String result = list.join(delimiter);
        assertEqual(expected, result, message("list", list, "delimiter", delimiter));
    }

    public java.util.List<Object[]> parametersForJoin() {
        Array<Integer> emptyList = emptyIntegerList();
        return paramsList(params("", emptyList, ""),
                          params("", emptyList, "x"),
                          params("1", Array.of(1), ""),
                          params("1null", Array.of(1, null), ""),
                          params("1", Array.of(1), "x"),
                          params("1x2", Array.of(1, 2), "x"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void plus(Array<Object> expected, Array<Object> list, Array<Object> other) {
        Array<Object> result = list.plus(other);
        assertEqual(expected, result);
    }
    
    private Array<Object[]> parametersForPlus() {
        Array<Object[]> params = Array.<Object[]>of();

        params.add(params(Array.of(1, 2, 3, 4), Array.of(1, 2), Array.of(3, 4)));
        params.add(params(Array.of(1, 2, 3), Array.of(1, 2), Array.of(3)));
        params.add(params(Array.of(1, 2), Array.of(1, 2), emptyIntegerList()));
        params.add(params(Array.of(1, 2, 3, 3), Array.of(1, 2, 3), Array.of(3)));
        params.add(params(Array.of(1, 2, 3, 1), Array.of(1, 2, 3), Array.of(1)));
        
        return params;
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void minus(Array<Object> expected, Array<Object> list, Array<Object> other) {
        Array<Object> result = list.minus(other);
        assertEqual(expected, result);
    }
    
    private java.util.List<Object[]> parametersForMinus() {
        java.util.List<Object[]> params = paramsList();
        
        params.add(params(Array.of(2), Array.of(1, 2), Array.of(1)));
        params.add(params(Array.of(1), Array.of(1, 2), Array.of(2)));
        params.add(params(Array.of(1, 1), Array.of(1, 1), Array.of(2)));
        params.add(params(Array.of(1, 1), Array.of(1, 2, 1), Array.of(2)));
        
        params.add(params(emptyIntegerList(), emptyIntegerList(), emptyIntegerList()));
        params.add(params(emptyIntegerList(), emptyIntegerList(), Array.of(1)));
        params.add(params(Array.of(1), Array.of(1), emptyIntegerList()));
        
        return params;
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void elements(Array<Object> expected, Array<Object> list, int ... indices) {
        Array<Object> result = list.elements(indices);
        assertEqual(expected, result, message("indices", indices));
    }
    
    private Array<Object[]> parametersForElements() {
        Array<Object[]> params = Array.<Object[]>of();

        Array<Integer> list = Array.of(6, 7, 8);

        params.add(params(Array.of(6), list, new int[] { 0 }));
        params.add(params(Array.of(7), list, new int[] { 1 }));
        params.add(params(Array.of(8), list, new int[] { 2 }));

        params.add(params(Array.of(6, 7), list, new int[] { 0, 1 }));
        params.add(params(Array.of(7, 6), list, new int[] { 1, 0 }));

        params.add(params(Array.of(6, 6), list, new int[] { 0, 0 }));

        params.add(params(Array.of(8), list, new int[] { -1 }));
        params.add(params(Array.of(8, 7), list, new int[] { -1, 1 }));
        params.add(params(Array.of(8, 7), list, new int[] { -1, -2 }));
        
        params.add(params(Array.<Integer>of((Integer)null), list, new int[] { -4 }));
        
        return params;
    }

    @Test
    public void demo() {
        Integer x = null;
        Array<Integer> nums = Array.of(1, 3, 5, 7);
        x = nums.get(0);
        assertEqual(1, x);
        
        x = nums.get(-1);
        assertEqual(7, x);
        
        nums.append(9).append(11).append(13);
        assertEqual(Array.of(1, 3, 5, 7, 9, 11, 13), nums);

        x = nums.get(-3);
        assertEqual(9, x);
        
        x = nums.first();
        assertEqual(1, x);

        x = nums.last();
        assertEqual(13, x);
        
        x = nums.takeFirst();
        assertEqual(1, x);
        assertEqual(Array.of(3, 5, 7, 9, 11, 13), nums);
        
        x = nums.takeFirst();
        assertEqual(3, x);
        assertEqual(Array.of(5, 7, 9, 11, 13), nums);

        x = nums.takeLast();
        assertEqual(13, x);
        assertEqual(Array.of(5, 7, 9, 11), nums);

        StringList strList = nums.toStringList();
        assertEqual(StringList.of("5", "7", "9", "11"), strList);

        nums.append(2).append(2).append(2);
        assertEqual(Array.of(5, 7, 9, 11, 2, 2, 2), nums);

        Array<Integer> uniq = nums.unique();
        assertEqual(Array.of(5, 7, 9, 11, 2), uniq);

        assertEqual(true, nums.containsAny(2, 3));
        assertEqual(false, nums.containsAny(3, 4));

        nums.removeAll(2);
        assertEqual(Array.of(5, 7, 9, 11), nums);

        nums.set(0, 4);
        assertEqual(Array.of(4, 7, 9, 11), nums);

        nums.set(-1, 10);
        assertEqual(Array.of(4, 7, 9, 10), nums);

        nums.set(-2, 8);
        assertEqual(Array.of(4, 7, 8, 10), nums);

        nums.set(1, 6);
        assertEqual(Array.of(4, 6, 8, 10), nums);

        x = nums.getRandomElement();
        assertEqual(true, Array.of(4, 6, 8, 10).contains(x));

        String str = nums.join(" + ");
        assertEqual("4 + 6 + 8 + 10", str);
        
        Array<Integer> odds = Array.of(1, 3, 5);
        Array<Integer> evens = Array.of(2, 4, 6);
        Array<Integer> numbers = odds.plus(evens);
        assertEqual(Array.of(1, 3, 5, 2, 4, 6), numbers);
        
        Array<Integer> squares = numbers.minus(Array.of(2, 3, 5, 6));
        assertEqual(Array.of(1, 4), squares);

        Array<Integer> elements = numbers.elements(1, 0, -2, 0, -4);
        assertEqual(Array.of(3, 1, 4, 1, 5), elements);
    }
}
