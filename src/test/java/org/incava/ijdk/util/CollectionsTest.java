package org.incava.ijdk.util;

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

public class CollectionsTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void join(String expected, Collection<String> coll, String delim) {
        String result = Collections.join(coll, delim);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForJoin() {
        return paramsList(params(null,      (ArrayList<String>)null,           "~"),
                          params("",        Arrays.<String>asList(),           null),
                          params("abcd",    Arrays.asList("a", "b", "c", "d"), null),
                          params("abcd",    Arrays.asList("a", "b", "c", "d"), ""),
                          params("",        new ArrayList<String>(),           "~"),
                          params("a~b~c~d", Arrays.asList("a", "b", "c", "d"), "~"));
    }
}
