package org.incava.test;

import ijdk.collect.List;
import ijdk.lang.Common;

/**
 * For usage in parameterized tests.
 */
public class Parameters {
    public static List<Object[]> paramsList(Object[] ... args) {
        return List.<Object[]>of(args);
    }

    public static Object[] params(Object ... args) {
        return Common.objary(args);
    }    
}
