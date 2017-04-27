package org.incava.ijdk.tuple;

import ijdk.collect.List;
import java.util.Arrays;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static ijdk.lang.Common.*;
import static org.incava.test.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class TestTriple {
    @Test
    public void init() {
        Triple<String, Integer, Double> t = Triple.of("abc", 123, 3.14);
        assertEqual("abc", t.getFirst());
        assertEqual("abc", t.first());
        assertEqual(123, t.getSecond());
        assertEqual(123, t.second());
        assertEqual(3.14, t.getThird());
        assertEqual(3.14, t.third());
    }

    @Test
    public void toString_test() {
        Triple<String, Integer, Double> t = Triple.of("abc", 123, 3.14);
        assertEqual("(abc, 123, 3.14)", t.toString());
    }

    @Test
    @Parameters
    public <A, B, C> void compareTo(Integer expected, Triple<A, B, C> x, Triple<A, B, C> y) {
        int result = x.compareTo(y);
        assertEqual(expected, result, message("x", x, "y", y));        
    }
    
    private java.util.List<Object[]> parametersForCompareTo() {
        Triple<String, Double, Integer> aaa = Triple.of("one", 1.2, 1);
        Triple<String, Double, Integer> aab = Triple.of("one", 1.2, 2);
        Triple<String, Double, Integer> aba = Triple.of("one", 2.3, 1);
        Triple<String, Double, Integer> baa = Triple.of("two", 1.2, 1);

        StringBuilder notComparable = new StringBuilder("one");

        return Arrays.asList(new Object[][] {
                new Object[] {  0, aaa, aaa },
                new Object[] {  0, aaa, Triple.of("one", 1.2, 1) },
                new Object[] {  0, Triple.of("one", 1.2, 1), aaa },
                new Object[] { -1, aaa, aab },
                new Object[] {  1, baa, aaa },
                new Object[] { -1, aaa, aba },
                new Object[] {  1, aba, aaa },
                new Object[] {  1, baa, aaa },
                // StringBuilder is not comparable:
                new Object[] { -1, Triple.of(new StringBuilder("sb"), 1.2, 0), Triple.of(new StringBuilder("sb"), 1.2, 0) },
                new Object[] { -1, Triple.of(1.2, new StringBuilder("sb"), 0), Triple.of(1.2, new StringBuilder("sb"), 0) } ,
                new Object[] { -1, Triple.of(1.2, 0, new StringBuilder("sb")), Triple.of(1.2, 0, new StringBuilder("sb")) } 
            });
    }    
}
