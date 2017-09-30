package org.incava.ijdk.str;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

@RunWith(JUnitParamsRunner.class)
public class TestCriteria {
    @Test
    @Parameters
    public void init(Boolean expected, String x, final String y) {
        Criteria<Boolean> crit = new Criteria<Boolean>() {
                public Boolean apply(String str) {
                    return str.startsWith(y);
                }
            };
        assertEqual(expected, crit.execute(x));
    }
    
    private java.util.List<Object[]> parametersForInit() {
        return paramsList(params(true, "abc", "a"),
                          params(false, "def", "a"),
                          params(null, null, "a"));
    }

    @Test
    @Parameters
    public void startsWith(Boolean expected, final String x, final String y) {
        Criteria<Boolean> crit = Criteria.startsWith(y);
        assertEqual(expected, crit.execute(x));
        
    }
    
    private java.util.List<Object[]> parametersForStartsWith() {
        return paramsList(params(true, "abc", "a"),
                          params(false, "def", "a"),
                          params(null, null, "a"));
    }

    @Test
    @Parameters
    public void contains(Boolean expected, final String x, final String y) {
        Criteria<Boolean> crit = Criteria.contains(y);
        assertEqual(expected, crit.execute(x));
        
    }
    
    private java.util.List<Object[]> parametersForContains() {
        return paramsList(params(true, "abc", "b"),
                          params(false, "def", "b"),
                          params(null, null, "b"));
    }

    @Test
    @Parameters
    public void endsWith(Boolean expected, final String x, final String y) {
        Criteria<Boolean> crit = Criteria.endsWith(y);
        assertEqual(expected, crit.execute(x));
        
    }
    
    private java.util.List<Object[]> parametersForEndsWith() {
        return paramsList(params(true, "abc", "c"),
                          params(false, "abc", "a"),
                          params(null, null, "a"));
    }

    @Test
    @Parameters
    public void equalsIgnoreCase(Boolean expected, final String x, final String y) {
        Criteria<Boolean> crit = Criteria.equalsIgnoreCase(y);
        assertEqual(expected, crit.execute(x));        
    }
    
    private java.util.List<Object[]> parametersForEqualsIgnoreCase() {
        return paramsList(params(true, "abc", "abc"),
                          params(true, "abc", "ABC"),
                          params(false, "def", "b"),
                          params(null, null, "b"),
                          // since null is filtered out, the comparison isn't done:
                          params(null, null, null));
    }

    @Test
    @Parameters
    public void equals(Boolean expected, final String x, final String y) {
        Criteria<Boolean> crit = Criteria.equals(y);
        assertEqual(expected, crit.execute(x));        
    }
    
    private java.util.List<Object[]> parametersForEquals() {
        return paramsList(params(true, "abc", "abc"),
                          params(false, "abc", "ABC"),
                          params(false, "def", "b"),
                          params(null, null, "b"),
                          // since null is filtered out, the comparison isn't done:
                          params(null, null, null));
    }    
}
