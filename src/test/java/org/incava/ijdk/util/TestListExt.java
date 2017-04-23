package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class TestListExt {
    public static final List<Integer> NUMS = Arrays.asList(new Integer[] { 2, 4, 6, 8 });
    public static final List<String> WORDS  = Arrays.asList(new String[] { "the", "whole", "kit", "and", "caboodle" });
    public static final List<String> EMPTY  = new ArrayList<String>();

    private static Object[] objary(Object ... args) {
        return args;
    }    

    @Test
    @Parameters
    public <T> void get(T expected, List<T> list, int idx) {
        T result = ListExt.get(list, idx);
        assertEqual(expected, result, message("list", list, "idx", idx));
    }
    
    private List<Object[]> parametersForGet() {
        return Arrays.asList(
            objary(2,    NUMS, 0),
            objary(4,    NUMS, 1),
            objary(6,    NUMS, 2),
            objary(8,    NUMS, 3),
            objary(null, NUMS, 4),

            objary(8,    NUMS, -1),
            objary(6,    NUMS, -2),
            objary(4,    NUMS, -3),
            objary(2,    NUMS, -4),
            objary(null, NUMS, -5),

            objary(null, EMPTY,  0),
            objary(null, EMPTY,  1),
            objary(null, EMPTY, -1),

            objary(null, null,  0),
            objary(null, null,  1),
            objary(null, null, -1));
    }

    @Test
    @Parameters
    public <T> void first(T expected, List<T> list) {
        T result = ListExt.first(list);
        assertEqual(expected, result);
    }
    
    private List<Object[]> parametersForFirst() {
        return Arrays.asList(
            objary(null, null),
            objary(null, EMPTY),
            objary("the", WORDS));
    }

    @Test
    @Parameters
    public <T> void last(T expected, List<T> list) {
        T result = ListExt.last(list);
        assertEqual(expected, result);        
    }
    
    private List<Object[]> parametersForLast() {
        return Arrays.asList(
            objary(null, null),
            objary(null, EMPTY),
            objary("caboodle", WORDS));
    }

    @Test
    @Parameters
    public <T> void removeAll(List<T> expected, List<T> list, T element) {
        ListExt.removeAll(list, element);
        assertEqual(expected, list);
    }
    
    private List<Object[]> parametersForRemoveAll() {
        List<String> letters = Arrays.asList("a", "a", "b", "b", "b", "c");
        
        return Arrays.asList(objary(Arrays.asList("a", "a", "b", "b", "b"), new ArrayList<String>(letters), "c"),
                             objary(Arrays.asList("b", "b", "b", "c"), new ArrayList<String>(letters), "a"),
                             objary(letters, new ArrayList<String>(letters), "d"));
    }

    @Test
    @Parameters
    public void getIndex(Integer expected, Integer size, Integer index) {
        Integer result = ListExt.getIndex(size, index);
        assertEqual(expected, result, message("size", size, "index", index));
    }
    
    private List<Object[]> parametersForGetIndex() {
        return Arrays.asList(
            objary(0,    4,  0),
            objary(1,    4,  1),
            objary(null, 4,  4),
            objary(null, 4,  5),

            objary(3,    4, -1),
            objary(2,    4, -2),
            objary(1,    4, -3),
            objary(0,    4, -4),
            objary(null, 4, -5),
            objary(null, 4, -6),

            objary(null, 0,  0),
            objary(null, 0,  1),
            objary(null, 0, -1));
    }

    @Test
    @Parameters
    public <T> void contains(Boolean expected, List<T> list, T value) {
        Boolean result = ListExt.contains(list, value);
        assertEqual(expected, result, message("list", list, "value", value));
    }
    
    private List<Object[]> parametersForContains() {
        List<Object[]> params = new ArrayList<Object[]>();

        List<String> strList = Arrays.asList("one", "two", "three");
        
        params.add(objary(true, strList, "one"));
        params.add(objary(true, strList, "three"));
        params.add(objary(false, strList, "four"));
        params.add(objary(false, strList, "ONE"));
        
        params.add(objary(false, null, null));

        List<Integer> intList = Arrays.asList(1, 2, 3);
        
        params.add(objary(true, intList, 1));
        params.add(objary(true, intList, 3));
        params.add(objary(false, intList, 4));

        return params;
    }

    @Test
    @Parameters
    public <T> void getRandomElement(Boolean expObject, List<T> list) {
        T result = ListExt.getRandomElement(list);
        assertEqual(expObject, result != null, message("list", list, "result", result));
    }
    
    private List<Object[]> parametersForGetRandomElement() {
        return Arrays.asList(
            objary(true, WORDS),
            objary(false, null),
            objary(false, EMPTY));
    }
}
