package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;

public class ListsTest extends Parameterized {
    public static final List<Integer> NUMS = Arrays.asList(new Integer[] { 2, 4, 6, 8 });
    public static final List<String> WORDS  = Arrays.asList(new String[] { "the", "whole", "kit", "and", "caboodle" });
    public static final List<String> EMPTY  = new ArrayList<String>();

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void get(T expected, List<T> list, int idx) {
        T result = Lists.get(list, idx);
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void first(T expected, List<T> list) {
        T result = Lists.first(list);
        assertEqual(expected, result);
    }
    
    private List<Object[]> parametersForFirst() {
        return paramsList(params(null, null),
                          params(null, EMPTY),
                          params("the", WORDS));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void last(T expected, List<T> list) {
        T result = Lists.last(list);
        assertEqual(expected, result);        
    }
    
    private List<Object[]> parametersForLast() {
        return paramsList(params(null, null),
                          params(null, EMPTY),
                          params("caboodle", WORDS));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void removeAll(Boolean expectedReturn, List<T> expectedList, List<T> list, T element) {
        Boolean result = Lists.removeAll(list, element);
        assertEqual(expectedList, list);
        assertEqual(expectedReturn, result);
    }
    
    private List<Object[]> parametersForRemoveAll() {
        List<String> letters = Arrays.asList("a", "a", "b", "b", "b", "c");
        
        return paramsList(params(true, Arrays.asList("a"), new ArrayList<>(Arrays.asList("a", "b")), "b"),
                          params(true, Arrays.asList("a"), new ArrayList<>(Arrays.asList("b", "a")), "b"),
                          params(true, Arrays.asList("b"), new ArrayList<>(Arrays.asList("a", "b")), "a"),
                          params(false, Arrays.asList("a", "b"), new ArrayList<>(Arrays.asList("a", "b")), null),
                          params(false, Arrays.asList("a", "b"), new ArrayList<>(Arrays.asList("a", "b")), "c"),
                          params(true, Arrays.asList("a"), new ArrayList<>(Arrays.asList("a", "b", "b")), "b"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void contains(Boolean expected, List<T> list, T value) {
        Boolean result = Lists.contains(list, value);
        assertEqual(expected, result, message("list", list, "value", value));
    }
    
    private List<Object[]> parametersForContains() {
        List<String> strList = Arrays.asList("one", "two", "three");
        List<Integer> intList = Arrays.asList(1, 2, 3);
        
        return paramsList(params(true,  strList, "one"),   
                          params(true,  strList, "three"), 
                          params(false, strList, "four"),  
                          params(false, strList, "ONE"),   
                          params(false, null,    null),    
                          params(true,  intList, 1),       
                          params(true,  intList, 3),       
                          params(false, intList, 4));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void getRandomElement(Boolean expObject, List<T> list) {
        T result = Lists.getRandomElement(list);
        assertEqual(expObject, result != null, message("list", list, "result", result));
    }
    
    private List<Object[]> parametersForGetRandomElement() {
        return paramsList(params(true, WORDS),
                          params(false, null),
                          params(false, EMPTY));
    }
}
