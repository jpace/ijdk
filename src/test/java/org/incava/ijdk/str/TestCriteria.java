package org.incava.ijdk.str;

import org.incava.ijdk.collect.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.*;
import static org.incava.ijdk.lang.Common.*;

@RunWith(JUnitParamsRunner.class)
public class TestCriteria {
    @Test
    @Parameters
    public void init(Boolean expected, String x, String y) {
        Criteria<Boolean> crit = new Criteria<Boolean>() {
                public Boolean apply(String str) {
                    return str.startsWith(y);
                }
            };
        assertEqual(expected, crit.execute(x));
    }
    
    private List<Object[]> parametersForInit() {
        return List.<Object[]>of(objary(true, "abc", "a"),
                                 objary(false, "def", "a"),
                                 objary(null, null, "a"));
    }

    @Test
    @Parameters
    public void startsWith(Boolean expected, final String x, final String y) {
        Criteria<Boolean> crit = Criteria.startsWith(y);
        assertEqual(expected, crit.execute(x));
        
    }
    
    private List<Object[]> parametersForStartsWith() {
        return List.<Object[]>of(objary(true, "abc", "a"),
                                 objary(false, "def", "a"),
                                 objary(null, null, "a"));
    }

    @Test
    @Parameters
    public void contains(Boolean expected, final String x, final String y) {
        Criteria<Boolean> crit = Criteria.contains(y);
        assertEqual(expected, crit.execute(x));
        
    }
    
    private List<Object[]> parametersForContains() {
        return List.<Object[]>of(objary(true, "abc", "b"),
                                 objary(false, "def", "b"),
                                 objary(null, null, "b"));
    }

    @Test
    @Parameters
    public void endsWith(Boolean expected, final String x, final String y) {
        Criteria<Boolean> crit = Criteria.endsWith(y);
        assertEqual(expected, crit.execute(x));
        
    }
    
    private List<Object[]> parametersForEndsWith() {
        return List.<Object[]>of(objary(true, "abc", "c"),
                                 objary(false, "abc", "a"),
                                 objary(null, null, "a"));
    }

    @Test
    @Parameters
    public void equalsIgnoreCase(Boolean expected, final String x, final String y) {
        Criteria<Boolean> crit = Criteria.equalsIgnoreCase(y);
        assertEqual(expected, crit.execute(x));        
    }
    
    private List<Object[]> parametersForEqualsIgnoreCase() {
        return List.<Object[]>of(objary(true, "abc", "abc"),
                                 objary(true, "abc", "ABC"),
                                 objary(false, "def", "b"),
                                 objary(null, null, "b"),
                                 // since null is filtered out, the comparison isn't done:
                                 objary(null, null, null));
    }

    @Test
    @Parameters
    public void equals(Boolean expected, final String x, final String y) {
        Criteria<Boolean> crit = Criteria.equals(y);
        assertEqual(expected, crit.execute(x));        
    }
    
    private List<Object[]> parametersForEquals() {
        return List.<Object[]>of(objary(true, "abc", "abc"),
                                 objary(false, "abc", "ABC"),
                                 objary(false, "def", "b"),
                                 objary(null, null, "b"),
                                 // since null is filtered out, the comparison isn't done:
                                 objary(null, null, null));
    }    
}
