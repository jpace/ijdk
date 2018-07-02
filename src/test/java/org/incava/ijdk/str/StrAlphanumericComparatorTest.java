package org.incava.ijdk.str;

import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.lang.Str;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StrAlphanumericComparatorTest extends Parameterized {
    private void addParams(java.util.List<Object[]> params, Integer num, String x, String y) {
        params.add(params(num, x, y));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compare(Integer expected, String x, String y) {
        StrAlphanumericComparator comp = new StrAlphanumericComparator();
        Integer result = comp.compare(Str.of(x), Str.of(y));
        assertThat(result, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForCompare() {
        java.util.List<Object[]> pl = paramsList();

        addParams(pl, 0,  "a",        "a");
        addParams(pl, 0,  "a1",       "a1");
        addParams(pl, 0,  "a9",       "a9");

        addParams(pl, -1, "a",        "b");
        addParams(pl,  1, "b",        "a");
        
        addParams(pl, -1, "a",        "ab");
        addParams(pl,  1, "ab",       "a");
        addParams(pl, -1, "a",        "abc");

        addParams(pl, -1, "8",        "9");
        addParams(pl,  1, "9",        "8");
        
        addParams(pl, -1, "1.8",      "1.9");
        addParams(pl,  1, "1.9",      "1.8");

        addParams(pl, -1, "a1",       "a2");
        addParams(pl,  1, "a3",       "a2");
        addParams(pl, -1, "a9",       "a10");
        addParams(pl,  1, "a10",      "a9");
        
        addParams(pl, -1, "a",        "a9");
        addParams(pl,  1, "a9",       "a");

        addParams(pl, -1, "a1.2",     "a1.3");
        addParams(pl, -1, "a2.2",     "a2.3");
        addParams(pl,  1, "a3.2",     "a2.1");
        
        addParams(pl, -1, "a9.2",     "a10.1");
        addParams(pl, -1, "a9.2",     "a10.1.0");
        
        addParams(pl,  0, "x1y",      "x1y");
        addParams(pl, -1, "x1y1",     "x1y2");
        addParams(pl, -1, "x1y9",     "x1y10");
        addParams(pl,  1, "x1y10",    "x1y9");

        addParams(pl,  0, "x1.00",    "x1.00");
        addParams(pl, -1, "x1.00",    "x1.01");
        addParams(pl,  0, "x1.0-y2",  "x1.0-y2");
        addParams(pl,  0, "x1.00-y2", "x1.00-y2");
        
        // same number, but different lengths:
        addParams(pl,  1,  "x1.00",   "x1.0");
        
        addParams(pl, -1,  "a1.",     "a2.");
        
        return pl;
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void matchNumber(String expected, String x, Integer idx) {
        StrAlphanumericComparator comp = new StrAlphanumericComparator();
        String result = comp.matchNumber(Str.of(x), idx);
        assertThat(result, equalTo(expected));
        System.out.println("");
    }
    
    private java.util.List<Object[]> parametersForMatchNumber() {
        return paramsList(params(null,   "a",    0), 
                          params(null,   "a",    0), 
                          params(null,   "a1",   0), 
                          params("1",    "a1",   1), 
                          params("1",    "1",    0), 
                          params("1",    "1.",   0), 
                          params("1.2",  "1.2",  0), 
                          params("11.2", "11.2", 0));
    }    
}
