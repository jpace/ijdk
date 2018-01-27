package org.incava.ijdk.lang;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CharTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void isMatch(Boolean expected, Character x, Character y, Boolean ignoreCase) {
        Boolean result = Char.isMatch(x, y, ignoreCase);
        assertThat(result, equalTo(expected));
    }    
    
    private List<Object[]> parametersForIsMatch() {
        return paramsList(params(true,  'a', 'a', false), 
                          params(false, 'b', 'a', false), 
                          params(true,  'a', 'a', true),  
                          params(true,  'a', 'A', true),  
                          params(false, 'a', 'A', false));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compare(Integer expected, Character x, Character y, Boolean ignoreCase) {
        Integer result = Char.compare(x, y, ignoreCase);
        assertThat(result, equalTo(expected));
    }    
    
    private List<Object[]> parametersForCompare() {
        return paramsList(params(0,   'a', 'a', false), 
                          params(0,   'a', 'a', true),  
                          params(0,   'A', 'a', true),  
                          params(-32, 'A', 'a', false));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compareIgnoreCase(Integer expected, Character x, Character y) {
        Integer result = Char.compareIgnoreCase(x, y);
        assertThat(result, equalTo(expected));
    }    
    
    private List<Object[]> parametersForCompareIgnoreCase() {
        return paramsList(params(0, 'a', 'a'), 
                          params(0, 'a', 'a'), 
                          params(0, 'A', 'a'), 
                          params(0, 'A', 'a'));
    }    
}
