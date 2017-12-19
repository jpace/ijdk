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
    private List<Object[]> compParams() {
        String empty = new String();        
        String abc = "abc";
        String def = "def";
        
        return paramsList(params(0,  false, false, true,  true,  null,  null),
                          params(-1, true,  false, true,  false, null,  empty),
                          params(1,  false, true,  false, true,  empty, null),
                          params(0,  false, false, true,  true,  empty, empty),
                          params(0,  false, false, true,  true,  abc,   new String("abc")),
                          params(-1, true,  false, true,  false, abc,   def),
                          params(1,  false, true,  false, true,  def,   abc));
    }
    @Test @Parameters(method="compParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void compare(int expCmp, boolean expLt, boolean expGt, boolean expLte, boolean expGte, T x, T y) {
        int result = Comp.compare(x, y);
        assertThat(result, withContext(message("x", x, "y", y), equalTo(expCmp)));
    }
    
    @Test @Parameters(method="compParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void lessThan(int expCmp, boolean expLt, boolean expGt, boolean expLte, boolean expGte, T x, T y) {
        assertBooleanEqual(expLt, Comp.lessThan(x, y), x, y);
    }
    
    @Test @Parameters(method="compParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void lt(int expCmp, boolean expLt, boolean expGt, boolean expLte, boolean expGte, T x, T y) {
        assertBooleanEqual(expLt, Comp.lt(x, y), x, y);
    }
    
    @Test @Parameters(method="compParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void lte(int expCmp, boolean expLt, boolean expGt, boolean expLte, boolean expGte, T x, T y) {
        assertBooleanEqual(expLte, Comp.lte(x, y), x, y);
    }

    @Test @Parameters(method="compParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void greaterThan(int expCmp, boolean expLt, boolean expGt, boolean expLte, boolean expGte, T x, T y) {
        assertBooleanEqual(expGt, Comp.greaterThan(x, y), x, y);
    }

    @Test @Parameters(method="compParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void gt(int expCmp, boolean expLt, boolean expGt, boolean expLte, boolean expGte, T x, T y) {
        assertBooleanEqual(expGt, Comp.gt(x, y), x, y);
    }

    @Test @Parameters(method="compParams") @TestCaseName("{method} {index} {params}")
    public <T extends Comparable<T>> void gte(int expCmp, boolean expLt, boolean expGt, boolean expLte, boolean expGte, T x, T y) {
        assertBooleanEqual(expGte, Comp.gte(x, y), x, y);
    }

    private <T> void assertBooleanEqual(boolean expected, boolean result, T x, T y) {
        assertThat(result, withContext(message("x", x, "y", y), equalTo(expected)));
    }    
}
