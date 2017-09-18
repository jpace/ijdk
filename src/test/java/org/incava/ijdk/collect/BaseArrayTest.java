package org.incava.ijdk.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.lang.Common;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public class BaseArrayTest extends Parameterized {
    public static class ExampleArray<T> extends BaseArray<T, ExampleArray<T>> {
        public static final long serialVersionUID = 1L;

        public ExampleArray() {
        }

        /**
         * Creates a list from the collection, copying its elements. <code>coll</code> can be null.
         */
        public ExampleArray(Collection<? extends T> coll) {
            if (coll != null) {
                addAll(coll);
            }
        }

        /**
         * Creates the list from a varargs array.
         */
        @SafeVarargs
        @SuppressWarnings("varargs")
        public ExampleArray(T ... ary) {
            for (T el : ary) {
                add(el);
            }
        }        
        
        public ExampleArray<T> newInstance() {
            return new ExampleArray<T>();
        }
    }
    
    private ExampleArray<Integer> emptyIntegerList() {
        return new ExampleArray<Integer>();
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void init(List<Object> expected, ExampleArray<Object> result) {
        assertThat(expected, equalTo(result));
    }
    
    private List<Object[]> parametersForInit() {
        List<String> abcList = Arrays.asList(new String[] { "a", "b", "c" });
        
        return paramsList(
            params(new ExampleArray<Object>(), new ExampleArray<Object>()),
            params(abcList,             new ExampleArray<Object>(abcList)),
            params(abcList,             new ExampleArray<Object>("a", "b", "c")),
            params(new ExampleArray<Object>(), new ExampleArray<Object>((Collection<Object>)null)),
            params(new ExampleArray<Object>(), new ExampleArray<Object>()));
    }    
    
    @Test
    public void toStringList() {
        ExampleArray<String> expected = new ExampleArray<String>("1", "2", "3");
        ExampleArray<Integer> numbers = new ExampleArray<Integer>(1, 2, 3);
        assertThat(expected, equalTo(numbers.toStringList()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void containsAnyCollection(Boolean expected, ExampleArray<Integer> list, List<Integer> coll) {
        assertEqual(expected, list.containsAny(coll));
    }

    public List<Object[]> parametersForContainsAnyCollection() {
        ExampleArray<Integer> ary123 = new ExampleArray<Integer>(1, 2, 3);
        return paramsList(
            params(true,  ary123, Common.list(1)),
            params(true,  ary123, Common.list(2, 4)),
            params(false, ary123, Common.list(4)),
            params(false, ary123, Common.list(4, 5)));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void containsAnyArray(Boolean expected, ExampleArray<Integer> list, Integer ... args) {
        assertThat(expected, equalTo(list.containsAny(args)));
    }
    
    @Test
    public void containsAnyArrayEmpty() {
        assertThat(false, equalTo(new ExampleArray<Object>().containsAny()));
    }

    public List<Object[]> parametersForContainsAnyArray() {
        ExampleArray<Integer> ary123 = new ExampleArray<Integer>(1, 2, 3);       
        return paramsList(
            params(true,  ary123, 1),
            params(true,  ary123, 4, 3),
            params(false, ary123, 4));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void first(Object expected, ExampleArray<Object> list) {
        assertThat(expected, withContext("list: " + list, equalTo(list.first())));
    }

    public List<Object[]> parametersForFirst() {
        return paramsList(
            params(new Integer(6), new ExampleArray<Integer>(6)),
            params(new Integer(6), new ExampleArray<Integer>(6, 7)),
            params((Integer)null,  emptyIntegerList()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void last(Object expected, ExampleArray<Object> list) {
        assertThat(expected, withContext("list: " + list, equalTo(list.last())));
    }

    public List<Object[]> parametersForLast() {
        return paramsList(
            params(new Integer(6), new ExampleArray<Integer>(6)),
            params(new Integer(7), new ExampleArray<Integer>(6, 7)),
            params((Integer)null,  emptyIntegerList()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void get(Object expected, ExampleArray<Object> list, int idx) {
        assertThat(expected, withContext("list: " + list, equalTo(list.get(idx))));
    }

    public List<Object[]> parametersForGet() {
        ExampleArray<Object> ary6 = new ExampleArray<Object>(6);
        ExampleArray<Object> ary67 = new ExampleArray<Object>(6, 7);
        ExampleArray<Object> empty = new ExampleArray<Object>();
        
        return paramsList(
            params(new Integer(6), ary6,   0),
            params(null,           ary6,   1),
            params(new Integer(6), ary6,  -1),
            params(new Integer(7), ary67, -1),
            params(new Integer(6), ary67, -2),
            params(null,           ary67,  2),
            params(null,           ary67, -3),
            params(null,           empty,  0),
            params(null,           empty, -1),
            params(null,           empty,  1));                         
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void getRange(ExampleArray<Object> expected, ExampleArray<Object> list, int from, int to) {
        assertEqual(expected, list.get(from, to), message("list", list, "from", from, "to", to));
    }

    public List<Object[]> parametersForGetRange() {
        return paramsList(
            params(new ExampleArray<Integer>(),     new ExampleArray<Integer>(),     0,  0),
            params(new ExampleArray<Integer>(6),    new ExampleArray<Integer>(6),    0,  0),
            params(new ExampleArray<Integer>(6),    new ExampleArray<Integer>(6),    0,  1),
            params(new ExampleArray<Integer>(6),    new ExampleArray<Integer>(6),    0,  2),
            params(new ExampleArray<Integer>(6),    new ExampleArray<Integer>(6, 7), 0,  0),
            params(new ExampleArray<Integer>(6, 7), new ExampleArray<Integer>(6, 7), 0,  1),
            params(new ExampleArray<Integer>(7),    new ExampleArray<Integer>(6, 7), 1,  1),
            params(new ExampleArray<Integer>(),     new ExampleArray<Integer>(6, 7), 1,  0),
            params(new ExampleArray<Integer>(6, 7), new ExampleArray<Integer>(6, 7), 0, -1),
            params(new ExampleArray<Integer>(6),    new ExampleArray<Integer>(6, 7), 0, -2),
            params(new ExampleArray<Integer>(7),    new ExampleArray<Integer>(6, 7), 1, -1),
            params(new ExampleArray<Integer>(),     new ExampleArray<Integer>(6, 7), 1, -2),
            params(new ExampleArray<Integer>(),     new ExampleArray<Integer>(),     0, -1));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void append(ExampleArray<Object> expected, ExampleArray<Object> list, Object obj) {
        String msg = message("list", list, "obj", obj);
        // the returned value is also the list
        assertEqual(expected, list.append(obj), msg);
        assertEqual(expected, list,             msg);
    }

    public List<Object[]> parametersForAppend() {
        return paramsList(
            params(new ExampleArray<Integer>(6, 7),    new ExampleArray<Integer>(6),        7),
            params(new ExampleArray<Integer>(6, null), new ExampleArray<Integer>(6),        (Integer)null),
            params(new ExampleArray<Integer>(7),       emptyIntegerList(), 7));                                     
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void set(ExampleArray<Object> expected, ExampleArray<Object> list, int index, Object obj) {
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

    public List<Object[]> parametersForSet() {
        return paramsList(
            params(new ExampleArray<Integer>(6, 7),    new ExampleArray<Integer>(6),         1, 7),
            params(new ExampleArray<Integer>(6, null), new ExampleArray<Integer>(6),         1, (Integer)null),
            params(new ExampleArray<Integer>(7),       emptyIntegerList(),  0, 7),
            params(null,              emptyIntegerList(), -1, 7),
            params(new ExampleArray<Integer>(7),       new ExampleArray<Integer>(6),        -1, 7),
            params(null,              new ExampleArray<Integer>(6),        -2, 7));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void removeAll(Boolean expReturned, ExampleArray<Object> expected, ExampleArray<Object> list, Object toRemove) {
        Boolean result = list.removeAll(toRemove);
        assertEqual(expReturned, result, message("list", list, "toRemove", toRemove));
        assertEqual(expected,    list,   message("list", list, "toRemove", toRemove));
    }

    public List<Object[]> parametersForRemoveAll() {
        return paramsList(
            params(true,  new ExampleArray<Integer>(1),        new ExampleArray<Integer>(1, 2),      2),
            params(true,  new ExampleArray<Integer>(1),        new ExampleArray<Integer>(1, 2, 2),   2),
            params(true,  emptyIntegerList(), new ExampleArray<Integer>(2, 2),      2),
            params(false, emptyIntegerList(), emptyIntegerList(),  2),
            params(false, new ExampleArray<Integer>(1),        new ExampleArray<Integer>(1),         2));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void getRandomElement(Boolean exp, ExampleArray<Object> list) {
        Object result = list.getRandomElement();
        assertEqual(exp, result != null, message("list", list, "exp", exp, "result", result));
    }

    public List<Object[]> parametersForGetRandomElement() {
        return paramsList(
            params(true,  new ExampleArray<Integer>(1)),
            params(true,  new ExampleArray<Integer>(1, 2)),
            params(false, emptyIntegerList()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void takeFirst(Object expReturn, ExampleArray<Object> expList, ExampleArray<Object> list) {
        ExampleArray<Object> origList = new ExampleArray<Object>(list);
        Object result = list.takeFirst();
        assertEqual(true, expReturn == result, message("origList", origList, "expReturn", expReturn, "result", result));
        assertEqual(expList, list, message("origList", origList));
    }

    public List<Object[]> parametersForTakeFirst() {
        return paramsList(
            params(1,             emptyIntegerList(),         new ExampleArray<Object>(1)),
            params(1,             new ExampleArray<Object>(2),        new ExampleArray<Object>(1, 2)),
            params("a",           new ExampleArray<String>(), new ExampleArray<Object>("a")),
            params("a",           new ExampleArray<Object>("b"),      new ExampleArray<Object>("a", "b")),
            params((Integer)null, emptyIntegerList(), emptyIntegerList()));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void takeLast(Object expReturn, ExampleArray<Object> expList, ExampleArray<Object> list) {
        ExampleArray<Object> origList = new ExampleArray<Object>(list);
        Object result = list.takeLast();
        assertEqual(true, expReturn == result, message("origList", origList, "expReturn", expReturn, "result", result));
        assertEqual(expList, list, message("origList", origList));
    }

    public List<Object[]> parametersForTakeLast() {
        return paramsList(
            params(1,             emptyIntegerList(), new ExampleArray<Object>(1)),
            params(2,             new ExampleArray<Object>(1),        new ExampleArray<Object>(1, 2)),
            params("a",           new ExampleArray<Object>(), new ExampleArray<Object>("a")),
            params("b",           new ExampleArray<Object>("a"),      new ExampleArray<Object>("a", "b")),
            params((Integer)null, emptyIntegerList(), emptyIntegerList()));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void unique(ExampleArray<Object> expected, ExampleArray<Object> list) {
        ExampleArray<Object> origList = new ExampleArray<Object>(list);
        ExampleArray<Object> result = list.unique();
        assertEqual(expected, result, message("origList", origList));
        assertEqual(origList, list, message("origList", origList));
    }

    public List<Object[]> parametersForUnique() {
        return paramsList(
            params(new ExampleArray<Integer>(1),        new ExampleArray<Integer>(1)),
            params(new ExampleArray<Integer>(1),        new ExampleArray<Integer>(1, 1)),
            params(new ExampleArray<Integer>(1, 2),     new ExampleArray<Integer>(1, 2)),
            params(new ExampleArray<Integer>(2, 1),     new ExampleArray<Integer>(2, 1)),
            params(new ExampleArray<Integer>(1, 2),     new ExampleArray<Integer>(1, 2, 1)),
            params(emptyIntegerList(), emptyIntegerList()));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compact(ExampleArray<Object> expected, ExampleArray<Object> list) {
        ExampleArray<Object> origList = new ExampleArray<Object>(list);
        ExampleArray<Object> result = list.compact();
        assertEqual(expected, result, message("origList", origList));
        assertEqual(origList, list, message("origList", origList));
    }

    public List<Object[]> parametersForCompact() {
        ExampleArray<Integer> emptyList = emptyIntegerList();
        return paramsList(
            params(emptyList,      emptyList),
            params(new ExampleArray<Integer>(1),    new ExampleArray<Integer>(1)),
            params(emptyList,      new ExampleArray<Integer>((Integer)null)),
            params(new ExampleArray<Integer>(1),    new ExampleArray<Integer>(1, null)),
            params(new ExampleArray<Integer>(1),    new ExampleArray<Integer>(1, null, null)),
            params(new ExampleArray<Integer>(1),    new ExampleArray<Integer>(null, 1)),
            params(new ExampleArray<Integer>(1),    new ExampleArray<Integer>(null, 1, null)),
            params(new ExampleArray<Integer>(1, 1), new ExampleArray<Integer>(1, 1, null)),
            params(new ExampleArray<Integer>(1, 2), new ExampleArray<Integer>(1, 2, null)),
            params(new ExampleArray<Integer>(2, 1), new ExampleArray<Integer>(2, 1, null)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void join(String expected, ExampleArray<Object> list, String delimiter) {
        String result = list.join(delimiter);
        assertEqual(expected, result, message("list", list, "delimiter", delimiter));
    }

    public List<Object[]> parametersForJoin() {
        ExampleArray<Integer> emptyList = emptyIntegerList();
        return paramsList(
            params("",      emptyList,         ""),
            params("",      emptyList,         "x"),
            params("1",     new ExampleArray<Integer>(1),       ""),
            params("1null", new ExampleArray<Integer>(1, null), ""),
            params("1",     new ExampleArray<Integer>(1),       "x"),
            params("1x2",   new ExampleArray<Integer>(1, 2),    "x"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void plus(ExampleArray<Object> expected, ExampleArray<Object> list, ExampleArray<Object> other) {
        assertThat(list.plus(other), withContext(message("list", list, "other", other), equalTo(expected)));
        
    }
    
    private List<Object[]> parametersForPlus() {
        return paramsList(
            params(new ExampleArray<Integer>(1, 2, 3, 4), new ExampleArray<Integer>(1, 2),    new ExampleArray<Integer>(3, 4)),
            params(new ExampleArray<Integer>(1, 2, 3),    new ExampleArray<Integer>(1, 2),    new ExampleArray<Integer>(3)),
            params(new ExampleArray<Integer>(1, 2),       new ExampleArray<Integer>(1, 2),    emptyIntegerList()),
            params(new ExampleArray<Integer>(1, 2, 3, 3), new ExampleArray<Integer>(1, 2, 3), new ExampleArray<Integer>(3)),
            params(new ExampleArray<Integer>(1, 2, 3, 1), new ExampleArray<Integer>(1, 2, 3), new ExampleArray<Integer>(1)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void minus(ExampleArray<Object> expected, ExampleArray<Object> list, ExampleArray<Object> other) {
        ExampleArray<Object> result = list.minus(other);
        assertEqual(expected, result);
    }
    
    private List<Object[]> parametersForMinus() {
        return paramsList(
            params(new ExampleArray<Integer>(2),        new ExampleArray<Integer>(1, 2),     new ExampleArray<Integer>(1)),
            params(new ExampleArray<Integer>(1),        new ExampleArray<Integer>(1, 2),     new ExampleArray<Integer>(2)),
            params(new ExampleArray<Integer>(1, 1),     new ExampleArray<Integer>(1, 1),     new ExampleArray<Integer>(2)),
            params(new ExampleArray<Integer>(1, 1),     new ExampleArray<Integer>(1, 2, 1),  new ExampleArray<Integer>(2)),
        
            params(emptyIntegerList(), emptyIntegerList(), emptyIntegerList()),
            params(emptyIntegerList(), emptyIntegerList(), new ExampleArray<Integer>(1)),
            params(new ExampleArray<Integer>(1),        new ExampleArray<Integer>(1),        emptyIntegerList()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void elements(ExampleArray<Object> expected, ExampleArray<Object> list, int ... indices) {
        ExampleArray<Object> result = list.elements(indices);
        assertEqual(expected, result, message("indices", indices));
    }
    
    private List<Object[]> parametersForElements() {
        ExampleArray<Integer> list678 = new ExampleArray<Integer>(6, 7, 8);
        ExampleArray<Integer> listOfNull = new ExampleArray<Integer>((Integer)null);
        
        return paramsList(
            params(new ExampleArray<Integer>(6),    list678, new int[] { 0 }),
            params(new ExampleArray<Integer>(7),    list678, new int[] { 1 }),
            params(new ExampleArray<Integer>(8),    list678, new int[] { 2 }),

            params(new ExampleArray<Integer>(6, 7), list678, new int[] { 0, 1 }),
            params(new ExampleArray<Integer>(7, 6), list678, new int[] { 1, 0 }),

            params(new ExampleArray<Integer>(6, 6), list678, new int[] { 0, 0 }),

            params(new ExampleArray<Integer>(8),    list678, new int[] { -1 }),
            params(new ExampleArray<Integer>(8, 7), list678, new int[] { -1, 1 }),
            params(new ExampleArray<Integer>(8, 7), list678, new int[] { -1, -2 }),
        
            params(listOfNull,     list678, new int[] { -4 }));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void intersection(ExampleArray<Object> expected, ExampleArray<Object> x, ExampleArray<Object> y) {
        ExampleArray<Object> result = x.intersection(y);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForIntersection() {
        ExampleArray<Object> x1  = new ExampleArray<Object>(1);
        ExampleArray<Object> x11 = new ExampleArray<Object>(1, 1);
        ExampleArray<Object> x12 = new ExampleArray<Object>(1, 2);
        ExampleArray<Object> x2  = new ExampleArray<Object>(2);
        ExampleArray<Object> x21 = new ExampleArray<Object>(2, 1);
        ExampleArray<Object> emp = new ExampleArray<Object>();
        
        return paramsList(
            params(x1,  x12, x1),
            params(x2,  x12, x2),
            params(emp, x11, x2),
            params(x2,  new ExampleArray<Integer>(1, 2, 1), x2),
            params(x21, x21, x12),        
            params(emp, emp, emp),
            params(emp, emp, x1),
            params(emp, x1,  emp)
                          );
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void sorted(ExampleArray<Object> expArray, String expException, ExampleArray<Object> ary) {
        try {
            ExampleArray<Object> result = ary.sorted();
            assertThat(result, equalTo(expArray));
        }
        catch (Exception ex) {
            assertThat(ex.getMessage(), equalTo(expException));
        }
    }
    
    private List<Object[]> parametersForSorted() {
        ExampleArray<Object> ab  = new ExampleArray<Object>("a", "b");
        ExampleArray<Object> ba  = new ExampleArray<Object>("b", "a");
        
        ExampleArray<Object> sba  = new ExampleArray<Object>(new StringBuilder("a"), new StringBuilder("b"));
        
        return paramsList(params(ab, null, ab),
                          params(ab, null, ba),
                          params(ab, "array contains class java.lang.StringBuilder, which is not Comparable", sba));
    }    

    @Test
    public void demo() {
        Integer x = null;
        ExampleArray<Integer> nums = new ExampleArray<Integer>(1, 3, 5, 7);
        x = nums.get(0);
        assertEqual(1, x);
        
        x = nums.get(-1);
        assertEqual(7, x);
        
        nums.append(9).append(11).append(13);
        assertEqual(new ExampleArray<Integer>(1, 3, 5, 7, 9, 11, 13), nums);

        x = nums.get(-3);
        assertEqual(9, x);
        
        x = nums.first();
        assertEqual(1, x);

        x = nums.last();
        assertEqual(13, x);
        
        x = nums.takeFirst();
        assertEqual(1, x);
        assertEqual(new ExampleArray<Integer>(3, 5, 7, 9, 11, 13), nums);
        
        x = nums.takeFirst();
        assertEqual(3, x);
        assertEqual(new ExampleArray<Integer>(5, 7, 9, 11, 13), nums);

        x = nums.takeLast();
        assertEqual(13, x);
        assertEqual(new ExampleArray<Integer>(5, 7, 9, 11), nums);

        StringList strList = nums.toStringList();
        assertEqual(StringList.of("5", "7", "9", "11"), strList);

        nums.append(2).append(2).append(2);
        assertEqual(new ExampleArray<Integer>(5, 7, 9, 11, 2, 2, 2), nums);

        ExampleArray<Integer> uniq = nums.unique();
        assertEqual(new ExampleArray<Integer>(5, 7, 9, 11, 2), uniq);

        assertEqual(true, nums.containsAny(2, 3));
        assertEqual(false, nums.containsAny(3, 4));

        nums.removeAll(2);
        assertEqual(new ExampleArray<Integer>(5, 7, 9, 11), nums);

        nums.set(0, 4);
        assertEqual(new ExampleArray<Integer>(4, 7, 9, 11), nums);

        nums.set(-1, 10);
        assertEqual(new ExampleArray<Integer>(4, 7, 9, 10), nums);

        nums.set(-2, 8);
        assertEqual(new ExampleArray<Integer>(4, 7, 8, 10), nums);

        nums.set(1, 6);
        assertEqual(new ExampleArray<Integer>(4, 6, 8, 10), nums);

        x = nums.getRandomElement();
        assertEqual(true, new ExampleArray<Integer>(4, 6, 8, 10).contains(x));

        String str = nums.join(" + ");
        assertEqual("4 + 6 + 8 + 10", str);
        
        ExampleArray<Integer> odds = new ExampleArray<Integer>(1, 3, 5);
        ExampleArray<Integer> evens = new ExampleArray<Integer>(2, 4, 6);
        ExampleArray<Integer> numbers = odds.plus(evens);
        assertEqual(new ExampleArray<Integer>(1, 3, 5, 2, 4, 6), numbers);
        
        ExampleArray<Integer> squares = numbers.minus(new ExampleArray<Integer>(2, 3, 5, 6));
        assertEqual(new ExampleArray<Integer>(1, 4), squares);

        ExampleArray<Integer> elements = numbers.elements(1, 0, -2, 0, -4);
        assertEqual(new ExampleArray<Integer>(3, 1, 4, 1, 5), elements);
    }
}
