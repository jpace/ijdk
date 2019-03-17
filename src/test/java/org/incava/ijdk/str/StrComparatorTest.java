package org.incava.ijdk.str;

import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.lang.Str;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StrComparatorTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compare(Integer expected, String x, String y) {
        StrComparator comp = new StrComparator();
        Integer result = comp.compare(Str.of(x), Str.of(y));
        Integer relResult = result == 0 ? 0 : (result / Math.abs(result));
        assertThat(relResult, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForCompare() {
        return paramsList(params(0,  "a",   "a"),
                          params(1,  "a",   "A"),
                          params(-1, "A",   "a"),
                          params(-1, "a",   "ab"),
                          params(-1, "a",   "abc"),
                          params(1,  "ab",  "a"),
                          params(1,  "abc", "a"));
    }
}
