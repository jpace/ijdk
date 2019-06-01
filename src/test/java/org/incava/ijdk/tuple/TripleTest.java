package org.incava.ijdk.tuple;

import java.util.Arrays;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

public class TripleTest extends Parameterized {
    @Test
    public void init() {
        Triple<String, Integer, Double> t = Triple.of("abc", 123, 3.14);
        assertEqual("abc", t.getFirst());
        assertEqual("abc", t.first());
        
        assertEqual(123,   t.getSecond());
        assertEqual(123,   t.second());
        
        assertEqual(3.14,  t.getThird());
        assertEqual(3.14,  t.third());
    }

    @Test
    public void toString_test() {
        Triple<String, Integer, Double> t = Triple.of("abc", 123, 3.14);
        assertEqual("(abc, 123, 3.14)", t.toString());
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <A, B, C> void compareTo(Integer expected, Triple<A, B, C> x, Triple<A, B, C> y) {
        int result = x.compareTo(y);
        assertEqual(expected, result, message("x", x, "y", y));
    }
    
    private java.util.List<Object[]> parametersForCompareTo() {
        Triple<String, Integer, Integer> a = Triple.of("xxx", 1, 2);
        Triple<String, Integer, Integer> b = Triple.of("xxx", 1, 3);
        Triple<String, Integer, Integer> c = Triple.of("xxx", 2, 1);
        Triple<String, Integer, Integer> d = Triple.of("yyy", 1, 1);

        return paramsList(params(0,  a, a),
                          params(-1, a, b),
                          params(1,  d, a),
                          params(-1, a, c),
                          params(1,  c, a),
                          params(1,  d, a));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <A, B, C> void equals(boolean expected, Triple<A, B, C> tr, Object obj) {
        boolean result = tr.equals(obj);
        assertEqual(expected, result, message("tr", tr, "obj", obj));
    }
    
    private java.util.List<Object[]> parametersForEquals() {
        Triple<String, Double, Integer> aaa = Triple.of("one", 1.2, 1);
        Triple<String, Double, Integer> aab = Triple.of("one", 1.2, 2);
        Triple<String, Double, Integer> aba = Triple.of("one", 2.3, 1);
        Triple<String, Double, Integer> baa = Triple.of("two", 1.2, 1);

        // StringBuilder is not comparable:
        StringBuilder nc = new StringBuilder("one");

        return paramsList(params(true,  aaa, aaa),
                          params(true,  aaa, Triple.of("one", 1.2, 1)),
                          params(true,  Triple.of("one", 1.2, 1), aaa),
                          params(false, aaa, aab),
                          params(false, baa, aaa),
                          params(false, aaa, aba),
                          params(false, aba, aaa),
                          params(false, baa, aaa),
                          params(false, aaa, "xyz"),
                          params(true,  Triple.of(nc, 1.2, 0), Triple.of(nc, 1.2, 0)),
                          params(true,  Triple.of(1.2, nc, 0), Triple.of(1.2, nc, 0)),
                          params(true,  Triple.of(1.2, 0, nc), Triple.of(1.2, 0, nc)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <A, B, C> void getInstanceValues(Array<?> expected, Triple<A, B, C> tr) {
        Array<?> result = tr.getInstanceValues();
        assertEqual(expected, result, message("tr", tr));
    }
    
    private java.util.List<Object[]> parametersForGetInstanceValues() {
        Triple<String, Double, Integer> aaa = Triple.of("one", 1.2, 1);
        Triple<String, Double, Integer> aab = Triple.of("one", 1.2, 2);
        Triple<String, Double, Integer> aba = Triple.of("one", 2.3, 1);
        Triple<String, Double, Integer> baa = Triple.of("two", 1.2, 1);

        return paramsList(params(Array.of("one", 1.2, 1), aaa),
                          params(Array.of("one", 1.2, 2), aab),
                          params(Array.of("one", 2.3, 1), aba),
                          params(Array.of("two", 1.2, 1), baa));
    }
}
