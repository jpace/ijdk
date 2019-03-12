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
}
