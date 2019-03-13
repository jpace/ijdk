package org.incava.ijdk.regexp;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MatchDataTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void of(Object expected, Matcher matcher) {
        MatchData result = MatchData.of(matcher);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForOf() {
        Pattern p1 = Pattern.compile("(a.)(..)ef");
        String s1 = "abcdef";
        Matcher m1 = p1.matcher(s1);
        m1.matches();

        String s2 = "--abcdef--";
        Matcher m2 = p1.matcher(s1);
        m2.find();
        
        return paramsList(params(StringArray.of("abcdef", "ab", "cd"), m1),
                          params(StringArray.of("abcdef", "ab", "cd"), m2));
    }
}
