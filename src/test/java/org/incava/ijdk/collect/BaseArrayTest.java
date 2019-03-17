package org.incava.ijdk.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
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
import static org.incava.attest.ExistsMatcher.exists;

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

    private static ExampleArray<Integer> integerArray(Integer ... ary) {
        return new ExampleArray<Integer>(ary);
    }

    private static ExampleArray<Object> objectArray(Object ... ary) {
        return new ExampleArray<Object>(ary);
    }

    private static ExampleArray<String> stringArray(String ... ary) {
        return new ExampleArray<String>(ary);
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void init(List<Object> expected, ExampleArray<Object> result) {
        assertThat(expected, equalTo(result));
    }
    
    private List<Object[]> parametersForInit() {
        List<String> abcList = Arrays.asList(new String[] { "a", "b", "c" });
        
        return paramsList(params(objectArray(), objectArray()),
                          params(abcList,       new ExampleArray<Object>(abcList)),
                          params(abcList,       objectArray("a", "b", "c")),
                          params(objectArray(), new ExampleArray<Object>((Collection<Object>)null)),
                          params(objectArray(), objectArray()));
    }
    
    @Test
    public void toStringArray() {
        ExampleArray<String> expected = new ExampleArray<String>("1", "2", "3");
        ExampleArray<Integer> numbers = new ExampleArray<Integer>(1, 2, 3);
        assertThat(expected, equalTo(numbers.toStringArray()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void containsAnyCollection(Boolean expected, ExampleArray<Integer> list, List<Integer> coll) {
        Boolean result = list.containsAny(coll);
        assertThat(result, equalTo(expected));
    }

    public List<Object[]> parametersForContainsAnyCollection() {
        ExampleArray<Integer> ary123 = integerArray(1, 2, 3);
        return paramsList(params(true,  ary123, integerArray(1)),
                          params(true,  ary123, integerArray(2, 4)),
                          params(false, ary123, integerArray(4)),
                          params(false, ary123, integerArray(4, 5)));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void containsAnyArray(Boolean expected, ExampleArray<Integer> list, Integer ... args) {
        Boolean result = list.containsAny(args);
        assertThat(result, equalTo(expected));
    }
    
    @Test
    public void containsAnyArrayEmpty() {
        assertThat(false, equalTo(objectArray().containsAny()));
    }

    public List<Object[]> parametersForContainsAnyArray() {
        ExampleArray<Integer> ary123 = integerArray(1, 2, 3);
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
                          params((Integer)null,  integerArray()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void last(Object expected, ExampleArray<Object> list) {
        assertThat(expected, withContext("list: " + list, equalTo(list.last())));
    }

    public List<Object[]> parametersForLast() {
        return paramsList(params(new Integer(6), integerArray(6)),
                          params(new Integer(7), integerArray(6, 7)),
                          params((Integer)null,  integerArray()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void get(T expected, ExampleArray<T> list, int idx) {
        T result = list.get(idx);
        assertThat(result, withContext("list: " + list, equalTo(expected)));
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
        ExampleArray<Object> result = list.get(from, to);
        assertThat(result, withContext(message("list", list, "from", from, "to", to), equalTo(expected)));
    
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
    public <T> void append(ExampleArray<T> expected, ExampleArray<T> list, T obj) {
        String msg = message("list", list, "obj", obj);
        // the returned value is also the list
        ExampleArray<T> result = list.append(obj);
        assertThat(result, withContext(msg, equalTo(expected)));
        assertThat(list, withContext(msg, equalTo(expected)));
    }

    public List<Object[]> parametersForAppend() {
        return paramsList(params(integerArray(6, 7),    integerArray(6), 7),
                          params(integerArray(6, null), integerArray(6), (Integer)null),
                          params(integerArray(7),       integerArray(),  7));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void set(ExampleArray<T> expected, ExampleArray<T> list, int index, T obj) {
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
        return paramsList(params(integerArray(6, 7),    integerArray(6),  1, 7),
                          params(integerArray(6, null), integerArray(6),  1, (Integer)null),
                          params(integerArray(7),       integerArray(),   0, 7),
                          params(null,                  integerArray(),  -1, 7),
                          params(integerArray(7),       integerArray(6), -1, 7),
                          params(null,                  integerArray(6), -2, 7));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void removeAll(Boolean expReturned, ExampleArray<T> expected, ExampleArray<T> list, T toRemove) {
        Boolean result = list.removeAll(toRemove);
        String msg = message("list", list, "toRemove", toRemove);
        assertThat(result, withContext(msg, equalTo(expReturned)));
        assertThat(list, withContext(msg, equalTo(expected)));
    }

    public List<Object[]> parametersForRemoveAll() {
        return paramsList(params(true,  integerArray(1), integerArray(1, 2),    2),
                          params(true,  integerArray(1), integerArray(1, 2, 2), 2),
                          params(true,  integerArray(),  integerArray(2, 2),    2),
                          params(false, integerArray(),  integerArray(),        2),
                          params(false, integerArray(1), integerArray(1),       2));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void getRandomElement(Boolean expected, ExampleArray<T> list) {
        T elmt = list.getRandomElement();
        assertThat(elmt, withContext(message("list", list), exists(expected)));
    }

    public List<Object[]> parametersForGetRandomElement() {
        return paramsList(params(true,  integerArray(1)),
                          params(true,  integerArray(1, 2)),
                          params(false, integerArray()));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void takeFirst(T expReturn, ExampleArray<T> expList, ExampleArray<T> list) {
        ExampleArray<T> origList = new ExampleArray<T>(list);
        T result = list.takeFirst();
        assertThat(result, sameInstance(expReturn));
        assertThat(list, equalTo(expList));
    }

    public List<Object[]> parametersForTakeFirst() {
        return paramsList(params(1,             integerArray(),             objectArray(1)),
                          params(1,             objectArray(2),             objectArray(1, 2)),
                          params("a",           new ExampleArray<String>(), objectArray("a")),
                          params("a",           objectArray("b"),           objectArray("a", "b")),
                          params((Integer)null, integerArray(),             integerArray()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void takeLast(T expReturn, ExampleArray<T> expList, ExampleArray<T> list) {
        ExampleArray<T> origList = new ExampleArray<T>(list);
        T result = list.takeLast();
        assertThat(result, sameInstance(expReturn));
        assertThat(list, equalTo(expList));
    }

    public List<Object[]> parametersForTakeLast() {
        return paramsList(params(1,             integerArray(),   objectArray(1)),
                          params(2,             objectArray(1),   objectArray(1, 2)),
                          params("a",           objectArray(),    objectArray("a")),
                          params("b",           objectArray("a"), objectArray("a", "b")),
                          params((Integer)null, integerArray(),   integerArray()));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void unique(ExampleArray<T> expected, ExampleArray<T> list) {
        ExampleArray<T> origList = new ExampleArray<T>(list);
        ExampleArray<T> result = list.unique();
        assertThat(result, equalTo(expected));
        assertThat(list, equalTo(origList));
    }

    public List<Object[]> parametersForUnique() {
        return paramsList(params(integerArray(1),    integerArray(1)),
                          params(integerArray(1),    integerArray(1, 1)),
                          params(integerArray(1, 2), integerArray(1, 2)),
                          params(integerArray(2, 1), integerArray(2, 1)),
                          params(integerArray(1, 2), integerArray(1, 2, 1)),
                          params(integerArray(),     integerArray()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void compact(ExampleArray<T> expected, ExampleArray<T> list) {
        ExampleArray<T> origList = new ExampleArray<T>(list);
        ExampleArray<T> result = list.compact();
        assertThat(result, equalTo(expected));
        assertThat(list, equalTo(origList));
    }

    public List<Object[]> parametersForCompact() {
        return paramsList(params(integerArray(),     integerArray()),
                          params(integerArray(1),    integerArray(1)),
                          params(integerArray(),     integerArray((Integer)null)),
                          params(integerArray(1),    integerArray(1, null)),
                          params(integerArray(1),    integerArray(1, null, null)),
                          params(integerArray(1),    integerArray(null, 1)),
                          params(integerArray(1),    integerArray(null, 1, null)),
                          params(integerArray(1, 1), integerArray(1, 1, null)),
                          params(integerArray(1, 2), integerArray(1, 2, null)),
                          params(integerArray(2, 1), integerArray(2, 1, null)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void join(String expected, ExampleArray<T> list, String delimiter) {
        String result = list.join(delimiter);
        assertThat(result, withContext(message("list", list, "delimiter", delimiter), equalTo(expected)));
    }

    public List<Object[]> parametersForJoin() {
        return paramsList(params("",      integerArray(),        ""),
                          params("",      integerArray(),        "x"),
                          params("1",     integerArray(1),       ""),
                          params("1null", integerArray(1, null), ""),
                          params("1",     integerArray(1),       "x"),
                          params("1x2",   integerArray(1, 2),    "x"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void plus(ExampleArray<T> expected, ExampleArray<T> list, Collection<T> other) {
        ExampleArray<T> result = list.plus(other);
        assertThat(result, withContext(message("list", list, "other", other), equalTo(expected)));
    }
    
    private List<Object[]> parametersForPlus() {
        return paramsList(params(integerArray(1, 2, 3, 4), integerArray(1, 2),    integerArray(3, 4)),
                          params(integerArray(1, 2, 3, 4), integerArray(1, 2),    Arrays.asList(new Integer[] { 3, 4 })),
                          params(integerArray(1, 2, 3),    integerArray(1, 2),    integerArray(3)),
                          params(integerArray(1, 2),       integerArray(1, 2),    integerArray()),
                          params(integerArray(1, 2, 3, 3), integerArray(1, 2, 3), integerArray(3)),
                          params(integerArray(1, 2, 3, 1), integerArray(1, 2, 3), integerArray(1)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void minus(ExampleArray<T> expected, ExampleArray<T> list, Collection<T> other) {
        ExampleArray<T> result = list.minus(other);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForMinus() {
        return paramsList(params(integerArray(2),    integerArray(1, 2),    integerArray(1)),
                          params(integerArray(2),    integerArray(1, 2),    Arrays.asList(new Integer[] { 1 })),
                          params(integerArray(1),    integerArray(1, 2),    integerArray(2)),
                          params(integerArray(1, 1), integerArray(1, 1),    integerArray(2)),
                          params(integerArray(1, 1), integerArray(1, 2, 1), integerArray(2)),
        
                          params(integerArray(),     integerArray(),        integerArray()),
                          params(integerArray(),     integerArray(),        integerArray(1)),
                          params(integerArray(1),    integerArray(1),       integerArray()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void elements(ExampleArray<T> expected, ExampleArray<T> list, int ... indices) {
        ExampleArray<T> result = list.elements(indices);
        assertThat(result, withContext(message("list", list, "indices", indices), equalTo(expected)));
    }
    
    private List<Object[]> parametersForElements() {
        ExampleArray<Integer> ary678 = integerArray(6, 7, 8);
        ExampleArray<Integer> arrayOfNull = integerArray((Integer)null);
        
        return paramsList(params(integerArray(6),    ary678, new int[] { 0 }),
                          params(integerArray(7),    ary678, new int[] { 1 }),
                          params(integerArray(8),    ary678, new int[] { 2 }),

                          params(integerArray(6, 7), ary678, new int[] { 0, 1 }),
                          params(integerArray(7, 6), ary678, new int[] { 1, 0 }),

                          params(integerArray(6, 6), ary678, new int[] { 0, 0 }),

                          params(integerArray(8),    ary678, new int[] { -1 }),
                          params(integerArray(8, 7), ary678, new int[] { -1, 1 }),
                          params(integerArray(8, 7), ary678, new int[] { -1, -2 }),
        
                          params(arrayOfNull,        ary678, new int[] { -4 }));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void intersection(ExampleArray<T> expected, ExampleArray<T> x, ExampleArray<T> y) {
        ExampleArray<T> result = x.intersection(y);
        assertThat(result, equalTo(expected));
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
    public <T> void sorted(ExampleArray<T> expArray, ExampleArray<T> ary) {
        ExampleArray<T> result = ary.sorted();
        assertThat(result, equalTo(expArray));
    }
    
    private List<Object[]> parametersForSorted() {
        ExampleArray<Object> ab  = objectArray("a", "b");
        ExampleArray<Object> ba  = objectArray("b", "a");
        
        return paramsList(params(ab, ab),
                          params(ab, ba));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void sortedComparator(ExampleArray<T> expected, ExampleArray<T> ary, Comparator<T> comparator) {
        ExampleArray<T> result = ary.sorted(comparator);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForSortedComparator() {
        Comparator<String> strLenComp = new Comparator<String>() {
                public int compare(String x, String y) {
                    return Integer.valueOf(x.length()).compareTo(Integer.valueOf(y.length()));
                }
            };
        ExampleArray<String> ab  = stringArray("aaa", "bb", "c");
        return paramsList(params(stringArray("c", "bb", "aaa"), ab, strLenComp));
    }


    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void reverse(ExampleArray<T> expected, ExampleArray<T> ary) {
        ExampleArray<T> result = ary.reverse();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForReverse() {
        ExampleArray<Object> ab  = objectArray("a", "b");
        ExampleArray<Object> ba  = objectArray("b", "a");
        ExampleArray<Object> empty  = objectArray();
        
        return paramsList(params(ba, ab),
                          params(ab, ba),
                          params(empty, empty));
    }
}
