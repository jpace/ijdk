package org.incava.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * For usage in parameterized tests.
 */
public class Parameters {
    public static List<Object[]> paramsList(Object[] ... args) {
        return new ArrayList<Object[]>(Arrays.asList(args));
    }

    public static Object[] params(Object ... args) {
        return args;
    }    
}
