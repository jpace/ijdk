package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

@RunWith(JUnitParamsRunner.class)
public class ListExtTest {
    public static final List<Integer> NUMS = Arrays.asList(new Integer[] { 2, 4, 6, 8 });
    public static final List<String> WORDS  = Arrays.asList(new String[] { "the", "whole", "kit", "and", "caboodle" });
    public static final List<String> EMPTY  = new ArrayList<String>();

    @Test
    @Parameters
    public <T> void get(T expected, List<T> list, int idx) {
        T result = ListExt.get(list, idx);
        assertEqual(expected, result, message("list", list, "idx", idx));
    }
    
    private List<Object[]> parametersForGet() {
        return paramsList(params(2,    NUMS, 0),
                          params(4,    NUMS, 1),
                          params(6,    NUMS, 2),
                          params(8,    NUMS, 3),
                          params(null, NUMS, 4),

                          params(8,    NUMS, -1),
                          params(6,    NUMS, -2),
                          params(4,    NUMS, -3),
                          params(2,    NUMS, -4),
                          params(null, NUMS, -5),

                          params(null, EMPTY,  0),
                          params(null, EMPTY,  1),
                          params(null, EMPTY, -1),

                          params(null, null,  0),
                          params(null, null,  1),
                          params(null, null, -1));
    }

    @Test
    @Parameters
    public <T> void first(T expected, List<T> list) {
        T result = ListExt.first(list);
        assertEqual(expected, result);
    }
    
    private List<Object[]> parametersForFirst() {
        return paramsList(params(null, null),
                          params(null, EMPTY),
                          params("the", WORDS));
    }

    @Test
    @Parameters
    public <T> void last(T expected, List<T> list) {
        T result = ListExt.last(list);
        assertEqual(expected, result);        
    }
    
    private List<Object[]> parametersForLast() {
        return paramsList(params(null, null),
                          params(null, EMPTY),
                          params("caboodle", WORDS));
    }

    @Test
    @Parameters
    public <T> void removeAll(List<T> expected, List<T> list, T element) {
        ListExt.removeAll(list, element);
        assertEqual(expected, list);
    }
    
    private List<Object[]> parametersForRemoveAll() {
        List<String> letters = Arrays.asList("a", "a", "b", "b", "b", "c");
        
        return paramsList(params(Arrays.asList("a", "a", "b", "b", "b"), new ArrayList<String>(letters), "c"),
                          params(Arrays.asList("b", "b", "b", "c"), new ArrayList<String>(letters), "a"),
                          params(letters, new ArrayList<String>(letters), "d"));
    }

    @Test
    @Parameters
    public void getIndex(Integer expected, Integer size, Integer index) {
        Integer result = ListExt.getIndex(size, index);
        assertEqual(expected, result, message("size", size, "index", index));
    }
    
    private List<Object[]> parametersForGetIndex() {
        return paramsList(params(0,    4,  0),
                          params(1,    4,  1),
                          params(null, 4,  4),
                          params(null, 4,  5),

                          params(3,    4, -1),
                          params(2,    4, -2),
                          params(1,    4, -3),
                          params(0,    4, -4),
                          params(null, 4, -5),
                          params(null, 4, -6),

                          params(null, 0,  0),
                          params(null, 0,  1),
                          params(null, 0, -1));
    }

    @Test
    @Parameters
    public <T> void contains(Boolean expected, List<T> list, T value) {
        Boolean result = ListExt.contains(list, value);
        assertEqual(expected, result, message("list", list, "value", value));
    }
    
    private List<Object[]> parametersForContains() {
        List<String> strList = Arrays.asList("one", "two", "three");
        List<Integer> intList = Arrays.asList(1, 2, 3);
        
        return paramsList(params(true, strList, "one"),
                          params(true, strList, "three"),
                          params(false, strList, "four"),
                          params(false, strList, "ONE"),
                          params(false, null, null),
                          params(true, intList, 1),
                          params(true, intList, 3),
                          params(false, intList, 4));
    }

    @Test
    @Parameters
    public <T> void getRandomElement(Boolean expObject, List<T> list) {
        T result = ListExt.getRandomElement(list);
        assertEqual(expObject, result != null, message("list", list, "result", result));
    }
    
    private List<Object[]> parametersForGetRandomElement() {
        return paramsList(params(true, WORDS),
                          params(false, null),
                          params(false, EMPTY));
    }
}
