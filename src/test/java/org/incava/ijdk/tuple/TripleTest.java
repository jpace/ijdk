package org.incava.ijdk.tuple;

import java.util.Arrays;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
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
        Triple<String, Double, Integer> aaa = Triple.of("one", 1.2, 1);
        Triple<String, Double, Integer> aab = Triple.of("one", 1.2, 2);
        Triple<String, Double, Integer> aba = Triple.of("one", 2.3, 1);
        Triple<String, Double, Integer> baa = Triple.of("two", 1.2, 1);

        // StringBuilder is not comparable:
        StringBuilder notComparable = new StringBuilder("one");

        return paramsList(params(0, aaa, aaa),
                          params(0, aaa, Triple.of("one", 1.2, 1)),
                          params(0, Triple.of("one", 1.2, 1), aaa),
                          params(-1, aaa, aab),
                          params(1,  baa, aaa),
                          params(-1, aaa, aba),
                          params(1,  aba, aaa),
                          params(1,  baa, aaa),
                          params(-1, Triple.of(notComparable, 1.2, 0), Triple.of(notComparable, 1.2, 0)),
                          params(-1, Triple.of(1.2, notComparable, 0), Triple.of(1.2, notComparable, 0)),
                          params(-1, Triple.of(1.2, 0, notComparable), Triple.of(1.2, 0, notComparable)));
    }    
}
