package org.incava.ijdk.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
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

    private static ExampleArray<Object> emptyObjectArray = new ExampleArray<Object>();
    private static ExampleArray<Integer> emptyIntegerArray = new ExampleArray<Integer>();
    private static ExampleArray<Integer> array123 = new ExampleArray<Integer>(1, 2, 3);
    
    private static ExampleArray<Integer> emptyIntegerList() {
        return new ExampleArray<Integer>();
    }

    private static ExampleArray<Integer> integerArray(Integer ... ary) {
        return new ExampleArray<Integer>(ary);
    }

    private static ExampleArray<Object> objectArray(Object ... ary) {
        return new ExampleArray<Object>(ary);
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void init(List<Object> expected, ExampleArray<Object> result) {
        assertThat(expected, equalTo(result));
    }
    
    private List<Object[]> parametersForInit() {
        List<String> abcList = Arrays.asList(new String[] { "a", "b", "c" });
        
        return paramsList(params(emptyObjectArray, emptyObjectArray),
                          params(abcList,          new ExampleArray<Object>(abcList)),
                          params(abcList,          objectArray("a", "b", "c")),
                          params(emptyObjectArray, new ExampleArray<Object>((Collection<Object>)null)),
                          params(emptyObjectArray, emptyObjectArray));
    }    
    
    @Test
    public void toStringList() {
        ExampleArray<String> expected = new ExampleArray<String>("1", "2", "3");
        ExampleArray<Integer> numbers = array123;
        assertThat(expected, equalTo(numbers.toStringList()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void containsAnyCollection(Boolean expected, ExampleArray<Integer> list, List<Integer> coll) {
        assertThat(list.containsAny(coll), equalTo(expected));
    }

    public List<Object[]> parametersForContainsAnyCollection() {
        ExampleArray<Integer> ary123 = array123;
        return paramsList(params(true,  ary123, integerArray(1)),
                          params(true,  ary123, integerArray(2, 4)),
                          params(false, ary123, integerArray(4)),
                          params(false, ary123, integerArray(4, 5)));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void containsAnyArray(Boolean expected, ExampleArray<Integer> list, Integer ... args) {
        assertThat(expected, equalTo(list.containsAny(args)));
    }
    
    @Test
    public void containsAnyArrayEmpty() {
        assertThat(false, equalTo(objectArray().containsAny()));
    }

    public List<Object[]> parametersForContainsAnyArray() {
        ExampleArray<Integer> ary123 = array123;       
        return paramsList(params(true,  ary123, 1),
                          params(true,  ary123, 4, 3),
                          params(false, ary123, 4));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void first(Object expected, ExampleArray<Object> list) {
        assertThat(expected, withContext("list: " + list, equalTo(list.first())));
    }

    public List<Object[]> parametersForFirst() {
        return paramsList(params(new Integer(6), integerArray(6)),
                          params(new Integer(6), integerArray(6, 7)),
                          params((Integer)null,  emptyIntegerList()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void last(Object expected, ExampleArray<Object> list) {
        assertThat(expected, withContext("list: " + list, equalTo(list.last())));
    }

    public List<Object[]> parametersForLast() {
        return paramsList(params(new Integer(6), integerArray(6)),
                          params(new Integer(7), integerArray(6, 7)),
                          params((Integer)null,  emptyIntegerList()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void get(Object expected, ExampleArray<Object> list, int idx) {
        assertThat(expected, withContext("list: " + list, equalTo(list.get(idx))));
    }

    public List<Object[]> parametersForGet() {
        ExampleArray<Object> ary6 = objectArray(6);
        ExampleArray<Object> ary67 = objectArray(6, 7);
        ExampleArray<Object> empty = objectArray();
        
        return paramsList(params(new Integer(6), ary6,   0),
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
        assertThat(list.get(from, to), withContext(message("list", list, "from", from, "to", to), equalTo(expected)));
    }

    public List<Object[]> parametersForGetRange() {
        return paramsList(params(integerArray(),     integerArray(),     0,  0),
                          params(integerArray(6),    integerArray(6),    0,  0),
                          params(integerArray(6),    integerArray(6),    0,  1),
                          params(integerArray(6),    integerArray(6),    0,  2),
                          params(integerArray(6),    integerArray(6, 7), 0,  0),
                          params(integerArray(6, 7), integerArray(6, 7), 0,  1),
                          params(integerArray(7),    integerArray(6, 7), 1,  1),
                          params(integerArray(),     integerArray(6, 7), 1,  0),
                          params(integerArray(6, 7), integerArray(6, 7), 0, -1),
                          params(integerArray(6),    integerArray(6, 7), 0, -2),
                          params(integerArray(7),    integerArray(6, 7), 1, -1),
                          params(integerArray(),     integerArray(6, 7), 1, -2),
                          params(integerArray(),     integerArray(),     0, -1));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void append(ExampleArray<Object> expected, ExampleArray<Object> list, Object obj) {
        String msg = message("list", list, "obj", obj);
        // the returned value is also the list
        assertThat(list.append(obj), withContext(msg, equalTo(expected)));
        assertThat(list, withContext(msg, equalTo(expected)));
    }

    public List<Object[]> parametersForAppend() {
        return paramsList(params(integerArray(6, 7),    integerArray(6),        7),
                          params(integerArray(6, null), integerArray(6),        (Integer)null),
                          params(integerArray(7),       emptyIntegerList(), 7));
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
            assertThat(list, withContext(message("index", index, "obj", obj), equalTo(expected)));
        }
    }

    public List<Object[]> parametersForSet() {
        return paramsList(params(integerArray(6, 7),    integerArray(6),     1, 7),
                          params(integerArray(6, null), integerArray(6),     1, (Integer)null),
                          params(integerArray(7),       emptyIntegerList(),  0, 7),
                          params(null,                  emptyIntegerList(), -1, 7),
                          params(integerArray(7),       integerArray(6),    -1, 7),
                          params(null,                  integerArray(6),    -2, 7));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void removeAll(Boolean expReturned, ExampleArray<Object> expected, ExampleArray<Object> list, Object toRemove) {
        Boolean result = list.removeAll(toRemove);
        String msg = message("list", list, "toRemove", toRemove);
        assertThat(result, withContext(msg, equalTo(expReturned)));
        assertThat(list, withContext(msg, equalTo(expected)));
    }

    public List<Object[]> parametersForRemoveAll() {
        return paramsList(params(true,  integerArray(1),    integerArray(1, 2),    2),
                          params(true,  integerArray(1),    integerArray(1, 2, 2), 2),
                          params(true,  emptyIntegerList(), integerArray(2, 2),    2),
                          params(false, emptyIntegerList(), emptyIntegerList(),    2),
                          params(false, integerArray(1),    integerArray(1),       2));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void getRandomElement(Boolean expected, ExampleArray<Object> list) {
        assertThat(list.getRandomElement() != null, withContext(message("list", list), equalTo(expected)));
    }

    public List<Object[]> parametersForGetRandomElement() {
        return paramsList(params(true,  integerArray(1)),
                          params(true,  integerArray(1, 2)),
                          params(false, emptyIntegerList()));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void takeFirst(Object expReturn, ExampleArray<Object> expList, ExampleArray<Object> list) {
        ExampleArray<Object> origList = new ExampleArray<Object>(list);
        Object result = list.takeFirst();
        assertThat(result, sameInstance(expReturn));
        assertThat(list, equalTo(expList));
    }

    public List<Object[]> parametersForTakeFirst() {
        return paramsList(params(1,             emptyIntegerList(),         objectArray(1)),
                          params(1,             objectArray(2),             objectArray(1, 2)),
                          params("a",           new ExampleArray<String>(), objectArray("a")),
                          params("a",           objectArray("b"),           objectArray("a", "b")),
                          params((Integer)null, emptyIntegerList(),         emptyIntegerList()));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void takeLast(Object expReturn, ExampleArray<Object> expList, ExampleArray<Object> list) {
        ExampleArray<Object> origList = new ExampleArray<Object>(list);
        Object result = list.takeLast();
        assertThat(result, sameInstance(expReturn));
        assertThat(list, equalTo(expList));
    }

    public List<Object[]> parametersForTakeLast() {
        return paramsList(params(1,             emptyIntegerList(), objectArray(1)),
                          params(2,             objectArray(1),     objectArray(1, 2)),
                          params("a",           objectArray(),      objectArray("a")),
                          params("b",           objectArray("a"),   objectArray("a", "b")),
                          params((Integer)null, emptyIntegerList(), emptyIntegerList()));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void unique(ExampleArray<Object> expected, ExampleArray<Object> list) {
        ExampleArray<Object> origList = new ExampleArray<Object>(list);
        ExampleArray<Object> result = list.unique();
        assertThat(result, equalTo(expected));
        assertThat(list, equalTo(origList));
    }

    public List<Object[]> parametersForUnique() {
        return paramsList(params(integerArray(1),    integerArray(1)),
                          params(integerArray(1),    integerArray(1, 1)),
                          params(integerArray(1, 2), integerArray(1, 2)),
                          params(integerArray(2, 1), integerArray(2, 1)),
                          params(integerArray(1, 2), integerArray(1, 2, 1)),
                          params(emptyIntegerList(), emptyIntegerList()));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compact(ExampleArray<Object> expected, ExampleArray<Object> list) {
        ExampleArray<Object> origList = new ExampleArray<Object>(list);
        ExampleArray<Object> result = list.compact();
        assertThat(result, equalTo(expected));
        assertThat(list, equalTo(origList));
    }

    public List<Object[]> parametersForCompact() {
        return paramsList(params(emptyIntegerArray,  emptyIntegerArray),
                          params(integerArray(1),    integerArray(1)),
                          params(emptyIntegerArray,  integerArray((Integer)null)),
                          params(integerArray(1),    integerArray(1, null)),
                          params(integerArray(1),    integerArray(1, null, null)),
                          params(integerArray(1),    integerArray(null, 1)),
                          params(integerArray(1),    integerArray(null, 1, null)),
                          params(integerArray(1, 1), integerArray(1, 1, null)),
                          params(integerArray(1, 2), integerArray(1, 2, null)),
                          params(integerArray(2, 1), integerArray(2, 1, null)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void join(String expected, ExampleArray<Object> list, String delimiter) {
        assertThat(list.join(delimiter), withContext(message("list", list, "delimiter", delimiter), equalTo(expected)));
    }

    public List<Object[]> parametersForJoin() {
        return paramsList(params("",      emptyIntegerArray,     ""),
                          params("",      emptyIntegerArray,     "x"),
                          params("1",     integerArray(1),       ""),
                          params("1null", integerArray(1, null), ""),
                          params("1",     integerArray(1),       "x"),
                          params("1x2",   integerArray(1, 2),    "x"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void plus(ExampleArray<Object> expected, ExampleArray<Object> list, ExampleArray<Object> other) {
        assertThat(list.plus(other), withContext(message("list", list, "other", other), equalTo(expected)));
    }
    
    private List<Object[]> parametersForPlus() {
        return paramsList(params(integerArray(1, 2, 3, 4), integerArray(1, 2), integerArray(3, 4)),
                          params(array123,                 integerArray(1, 2), integerArray(3)),
                          params(integerArray(1, 2),       integerArray(1, 2), emptyIntegerList()),
                          params(integerArray(1, 2, 3, 3), array123,           integerArray(3)),
                          params(integerArray(1, 2, 3, 1), array123,           integerArray(1)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void minus(ExampleArray<Object> expected, ExampleArray<Object> list, ExampleArray<Object> other) {
        assertThat(list.minus(other), equalTo(expected));
    }
    
    private List<Object[]> parametersForMinus() {
        return paramsList(params(integerArray(2),    integerArray(1, 2),    integerArray(1)),
                          params(integerArray(1),    integerArray(1, 2),    integerArray(2)),
                          params(integerArray(1, 1), integerArray(1, 1),    integerArray(2)),
                          params(integerArray(1, 1), integerArray(1, 2, 1), integerArray(2)),
        
                          params(emptyIntegerList(), emptyIntegerList(),    emptyIntegerList()),
                          params(emptyIntegerList(), emptyIntegerList(),    integerArray(1)),
                          params(integerArray(1),    integerArray(1),       emptyIntegerList()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void elements(ExampleArray<Object> expected, ExampleArray<Object> list, int ... indices) {
        assertThat(list.elements(indices), withContext(message("indices", indices), equalTo(expected)));
    }
    
    private List<Object[]> parametersForElements() {
        ExampleArray<Integer> list678 = integerArray(6, 7, 8);
        ExampleArray<Integer> listOfNull = integerArray((Integer)null);
        
        return paramsList(params(integerArray(6),    list678, new int[] { 0 }),
                          params(integerArray(7),    list678, new int[] { 1 }),
                          params(integerArray(8),    list678, new int[] { 2 }),

                          params(integerArray(6, 7), list678, new int[] { 0, 1 }),
                          params(integerArray(7, 6), list678, new int[] { 1, 0 }),

                          params(integerArray(6, 6), list678, new int[] { 0, 0 }),

                          params(integerArray(8),    list678, new int[] { -1 }),
                          params(integerArray(8, 7), list678, new int[] { -1, 1 }),
                          params(integerArray(8, 7), list678, new int[] { -1, -2 }),
        
                          params(listOfNull,         list678, new int[] { -4 }));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void intersection(ExampleArray<Object> expected, ExampleArray<Object> x, ExampleArray<Object> y) {
        assertThat(x.intersection(y), equalTo(expected));
    }
    
    private List<Object[]> parametersForIntersection() {
        ExampleArray<Object> x1  = objectArray(1);
        ExampleArray<Object> x11 = objectArray(1, 1);
        ExampleArray<Object> x12 = objectArray(1, 2);
        ExampleArray<Object> x2  = objectArray(2);
        ExampleArray<Object> x21 = objectArray(2, 1);
        ExampleArray<Object> emp = objectArray();
        
        return paramsList(params(x1,  x12,                   x1),
                          params(x2,  x12,                   x2),
                          params(emp, x11,                   x2),
                          params(x2,  integerArray(1, 2, 1), x2),
                          params(x21, x21,                   x12),        
                          params(emp, emp,                   emp),
                          params(emp, emp,                   x1),
                          params(emp, x1,                    emp));
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
        ExampleArray<Object> ab  = objectArray("a", "b");
        ExampleArray<Object> ba  = objectArray("b", "a");
        
        ExampleArray<Object> sba  = objectArray(new StringBuilder("a"), new StringBuilder("b"));
        
        return paramsList(params(ab, null, ab),
                          params(ab, null, ba),
                          params(ab, "array contains class java.lang.StringBuilder, which is not Comparable", sba));
    }    
}
