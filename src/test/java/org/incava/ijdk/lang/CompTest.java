package org.incava.ijdk.lang;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public class CompTest extends Parameterized {
    @Test @Parameters(method="compParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void compare(int expCmp, boolean expLt, boolean expGt, boolean expLte, boolean expGte, T x, T y) {
        int result = Comp.compare(x, y);
        assertThat(result, withContext(message("x", x, "y", y), equalTo(expCmp)));
    }
    
    @Test @Parameters(method="compParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void lessThan(int expCmp, boolean expLt, boolean expGt, boolean expLte, boolean expGte, T x, T y) {
        boolean result = Comp.lessThan(x, y);
        assertThat(result, equalTo(expLt));
    }
    
    @Test @Parameters(method="compParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void lt(int expCmp, boolean expLt, boolean expGt, boolean expLte, boolean expGte, T x, T y) {
        boolean result = Comp.lt(x, y);
        assertThat(result, equalTo(expLt));
    }
    
    @Test @Parameters(method="compParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void lte(int expCmp, boolean expLt, boolean expGt, boolean expLte, boolean expGte, T x, T y) {
        boolean result = Comp.lte(x, y);
        assertThat(result, equalTo(expLte));
    }

    @Test @Parameters(method="compParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void greaterThan(int expCmp, boolean expLt, boolean expGt, boolean expLte, boolean expGte, T x, T y) {
        boolean result = Comp.greaterThan(x, y);
        assertThat(result, equalTo(expGt));
    }

    @Test @Parameters(method="compParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void gt(int expCmp, boolean expLt, boolean expGt, boolean expLte, boolean expGte, T x, T y) {
        boolean result = Comp.gt(x, y);
        assertThat(result, equalTo(expGt));
    }

    @Test @Parameters(method="compParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void gte(int expCmp, boolean expLt, boolean expGt, boolean expLte, boolean expGte, T x, T y) {
        boolean result = Comp.gte(x, y);
        assertThat(result, equalTo(expGte));
    }

    private List<Object[]> compParams() {
        String empty = new String();
        String x = "x";
        String y = "y";
        
        return paramsList(params(0,  false, false, true,  true,  null,  null),            
                          params(-1, true,  false, true,  false, null,  empty),           
                          params(1,  false, true,  false, true,  empty, null),            
                          params(0,  false, false, true,  true,  empty, empty),           
                          params(0,  false, false, true,  true,  x,     new String("x")), 
                          params(-1, true,  false, true,  false, x,     y),               
                          params(1,  false, true,  false, true,  y,     x));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void compareObjects(int expected, T x, T y) {
        int result = Comp.compare(x, y);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForCompareObjects() {
        String x = "xxx";
        String y = "yyy";

        // StringBuilder is not comparable:
        StringBuilder sb1 = new StringBuilder("zzz");
        StringBuilder sb2 = new StringBuilder("zzz");
        
        return paramsList(params(0,  x,    x),     
                          params(0,  x,    "xxx"), 
                          params(-1, x,    y),     
                          params(1,  x,    null),  
                          params(-1, null, x),     
                          params(0,  null, null),
                          params(-1, null, sb1),     
                          params(1,  sb1,  null),
                          // misleading, because they are equal, but not comparable:
                          params(-1, sb1,  sb2));
    }
}
