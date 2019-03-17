package org.incava.ijdk.str;

import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.lang.Str;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StringAlphanumericComparatorTest extends Parameterized {
    private void addParams(java.util.List<Object[]> params, Integer num, String x, String y) {
        params.add(params(num, x, y));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compare(Integer expected, String x, String y) {
        StringAlphanumericComparator comp = new StringAlphanumericComparator();
        Integer result = comp.compare(x, y);
        Integer relResult = result == 0 ? 0 : (result / Math.abs(result));
        assertThat(relResult, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForCompare() {
        java.util.List<Object[]> pl = paramsList();

        addParams(pl, 0,  "a",        "a");
        addParams(pl, 0,  "a1",       "a1");
        addParams(pl, 0,  "a9",       "a9");

        // the rest are in StrAlphanumericComparator ...

        addParams(pl, -1, "a",        "b");
        addParams(pl,  1, "b",        "a");
        
        addParams(pl, -1, "a",        "ab");
        addParams(pl,  1, "ab",       "a");
        
        return pl;
    }
}
