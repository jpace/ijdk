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
    public void match(MatchData expected, String pattern, String str) {
        Regexp re = new Regexp(pattern);
        MatchData result = re.match(str);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForMatch() {
        return paramsList(params(new MatchData(StringArray.of("abcdef", "ab", "cd")), "(a.)(..)ef", "abcdef"),
                          params(new MatchData(StringArray.of("abcdef", "ab", "cd")), "(a.)(..)ef", "abcdefgh"),
                          params(null, "xyz", "abcdef"));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void isMatch(boolean expected, String pattern, String str) {
        Regexp re = new Regexp(pattern);
        boolean result = re.isMatch(str);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForIsMatch() {
        return paramsList(params(true, "(a.)(..)ef", "abcdef"),
                          params(true, "(a.)(..)ef", "abcdefgh"),
                          params(false, "xyz", "abcdef"));
    }
}
