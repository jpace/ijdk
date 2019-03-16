package org.incava.ijdk.regexp;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RegexpTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void matchNoOffset(MatchData expected, String pattern, String str) {
        Regexp re = new Regexp(pattern);
        MatchData result = re.match(str);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForMatchNoOffset() {
        return paramsList(params(new MatchData(StringArray.of("abcdef", "ab", "cd")), "(a.)(..)ef", "abcdef"),
                          params(new MatchData(StringArray.of("abcdef", "ab", "cd")), "(a.)(..)ef", "abcdefgh"),
                          params(null, "xyz", "abcdef"));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void matchOffset(MatchData expected, String pattern, String str, Integer offset) {
        Regexp re = new Regexp(pattern);
        MatchData result = re.match(str, offset);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForMatchOffset() {
        return paramsList(params(new MatchData(StringArray.of("abcdef", "ab", "cd")), "(a.)(..)ef", "abcdef", 0),
                          params(null,                                                "(a.)(..)ef", "abcdef", 1),
                          params(new MatchData(StringArray.of("abcdef", "abcd")), "(.+)ef", "abcdef", 0),
                          params(new MatchData(StringArray.of("bcdef", "bcd")),   "(.+)ef", "abcdef", 1),
                          params(new MatchData(StringArray.of("cdef", "cd")),     "(.+)ef", "abcdef", 2),
                          params(new MatchData(StringArray.of("def", "d")),       "(.+)ef", "abcdef", 3),
                          params(null,                                            "(.+)ef", "abcdef", 4),
                          params(new MatchData(StringArray.of("abcdef", "ab", "cd")), "(a.)(..)ef", "abcdefgh", 0));
    }    
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void isMatchNoOffset(boolean expected, String pattern, String str) {
        Regexp re = new Regexp(pattern);
        boolean result = re.isMatch(str);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForIsMatchNoOffset() {
        return paramsList(params(true, "(a.)(..)ef", "abcdef"),
                          params(true, "(a.)(..)ef", "abcdefgh"),
                          params(false, "xyz", "abcdef"));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void isMatchWithOffset(boolean expected, String pattern, String str, Integer offset ) {
        Regexp re = new Regexp(pattern);
        boolean result = re.isMatch(str, offset);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForIsMatchWithOffset() {
        return paramsList(params(true,  "(a.)(..)ef", "abcdef",   0), 
                          params(false, "(a.)(..)ef", "abcdef",   1), 
                          params(true,  "(a.)(..)ef", "abcdefgh", 0), 
                          params(false, "xyz",        "abcdef",   0));
    }
}
