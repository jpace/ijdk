package org.incava.ijdk.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class NonNullIteratorTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void hasNext(List<Boolean> expected, Collection<String> coll) {
        NonNullIterator<String> iter = new NonNullIterator<>(coll);
        for (Boolean it : expected) {
            assertThat(iter.hasNext(), equalTo(it));
        }
    }
    
    private List<Object[]> parametersForHasNext() {
        return paramsList(params(Arrays.asList(false), Arrays.asList()),
                          params(Arrays.asList(false), Arrays.asList((Object)null)),
                          params(Arrays.asList(true),  Arrays.asList("a", "b", "c")));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void next(List<String> expected, Collection<String> coll) {
        NonNullIterator<String> iter = new NonNullIterator<>(coll);
        for (String it : expected) {
            assertThat(iter.next(), equalTo(it));
        }
    }
    
    private List<Object[]> parametersForNext() {
        return paramsList(params(Arrays.asList(), Arrays.asList()),
                          params(Arrays.asList(), Arrays.asList((Object)null)),
                          params(Arrays.asList("a"),          Arrays.asList("a")),
                          params(Arrays.asList("a", "b"),     Arrays.asList("a", "b")),
                          params(Arrays.asList("a", "b"),     Arrays.asList("a", null, "b")),
                          params(Arrays.asList("a", "b"),     Arrays.asList("a", null, "b", null, null)));
    }
}
