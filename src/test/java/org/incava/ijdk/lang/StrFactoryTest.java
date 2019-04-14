package org.incava.ijdk.lang;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StrFactoryTest extends Parameterized {
    @Test
    public void empty() {
        StrFactory sf = new StrFactory();
        Str str = sf.empty();
        assertThat(str, equalTo(Str.empty()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void ofString(Str expected, String s) {
        StrFactory sf = new StrFactory();
        Str result = sf.of(s);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForOfString() {
        return paramsList(params(new Str("abc"), "abc"), 
                          params(new Str("def"), "def"), 
                          params(Str.NULL,       null));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void ofObject(Str expected, Object obj) {
        StrFactory sf = new StrFactory();
        Str result = sf.of(obj);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForOfObject() {
        return paramsList(params(new Str("abc"), new StringBuilder("abc")), 
                          params(new Str("1"),   Integer.valueOf(1)),       
                          params(new Str("3.4"), Double.valueOf(3.4)),      
                          params(Str.NULL,       null));
    }
}
