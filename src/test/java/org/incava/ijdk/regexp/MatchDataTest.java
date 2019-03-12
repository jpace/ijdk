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
    public void init(Object expected, Matcher matcher) {
        MatchData result = new MatchData(matcher);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForInit() {
        Pattern p1 = Pattern.compile("(a.)(..)ef");
        String s1 = "abcdef";
        Matcher m1 = p1.matcher(s1);
        m1.matches();

        String s2 = "--abcdef--";
        Matcher m2 = p1.matcher(s1);
        m2.find();

        String s3 = "xyz";
        Matcher m3 = p1.matcher(s3);
        m3.matches();
        
        return paramsList(params(StringArray.of("abcdef", "ab", "cd"), m1),
                          params(StringArray.of("abcdef", "ab", "cd"), m2),
                          params(StringArray.empty(), m3));
    }
}
