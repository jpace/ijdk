package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
    public void ofCharacter(Str expected, char ch) {
        StrFactory sf = new StrFactory();
        Str result = sf.of(ch);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForOfCharacter() {
        return paramsList(params(new Str("a"), 'a'), 
                          params(new Str("b"), 'b'));
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void joinCollection(Str expected, Collection<String> coll, String delim) {
        StrFactory sf = new StrFactory();
        Str result = sf.join(coll, delim);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForJoinCollection() {
        return paramsList(params(Str.of(null),      (ArrayList<String>)null,           "~"),
                          params(Str.of(""),        Arrays.<String>asList(),           null),
                          params(Str.of("a~b~c~d"), Arrays.asList("a", "b", "c", "d"), "~"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void joinArray(Str expected, String[] ary, String delim) {
        StrFactory sf = new StrFactory();
        Str result = sf.join(ary, delim);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForJoinArray() {
        return paramsList(params(new Str(null),      (String[])null,                 "~"),
                          params(new Str(""),        new String[] { },               null),
                          params(new Str("a~b~c"),   new String[] { "a", "b", "c" }, "~"),
                          params(new Str("aXXbXXc"), new String[] { "a", "b", "c" }, "XX"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void ofRepeatChar(Str expected, char ch, int num) {
        StrFactory sf = new StrFactory();
        Str result = sf.of(ch, num);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForOfRepeatChar() {
        return paramsList(params(new Str("a"),  'a',  1),
                          params(new Str("aa"), 'a',  2),
                          params(new Str(""),   'a',  0),
                          params(new Str(""),   'a', -1));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void ofRepeatString(Str expected, String str, int num) {
        StrFactory sf = new StrFactory();
        Str result = sf.of(str, num);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForOfRepeatString() {
        return paramsList(params(new Str("ab"),   "ab",  1),
                          params(new Str("abab"), "ab",  2),
                          params(new Str(""),     "a",   0),
                          params(new Str(""),     "a",  -1),
                          params(new Str(""),     "",    0),
                          params(new Str(""),     "",    1),
                          params(new Str(null),   null,  1));
    }
}
