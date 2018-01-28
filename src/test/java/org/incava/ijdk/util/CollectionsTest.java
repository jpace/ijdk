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
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public class CollectionsTest extends Parameterized {
    public static final Collection<String> ABC     = Arrays.asList(new String[] { "a", "b", "c" });
    public static final Collection<String> ABC_DUP = Arrays.asList(new String[] { "a", "b", "c" });
    public static final Collection<String> A       = Arrays.asList(new String[] { "a" });
    public static final Collection<String> ABCD    = Arrays.asList(new String[] { "a", "b", "c", "d" });
    public static final Collection<String> BCD     = Arrays.asList(new String[] { "b", "c", "d" });
    public static final Collection<String> DE      = Arrays.asList(new String[] { "d", "e" });
    public static final Collection<String> EMPTY   = new ArrayList<String>();

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void hasAny(boolean expected, Collection<T> src, Collection<T> tgt) {
        boolean result = Collections.hasAny(src, tgt);
        assertThat(result, withContext(message("src", src, "tgt", tgt), equalTo(expected)));
    }
    
    private List<Object[]> parametersForHasAny() {
        return paramsList(params(true, ABC, ABC),
                          params(true, ABC, ABC_DUP),
                          params(true, ABC, A),
                          params(true, ABC, ABCD),
                          params(true, ABC, BCD),

                          params(false, ABC, EMPTY),
                          params(false, ABC, null),
                          params(false, ABC, DE),

                          params(false, null, EMPTY),
                          params(false, null, null),
                          params(false, null, DE));            
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void hasAll(boolean expected, Collection<T> src, Collection<T> tgt) {
        boolean result = Collections.hasAll(src, tgt);
        assertThat(result, withContext(message("src", src, "tgt", tgt), equalTo(expected)));
    }
    
    private List<Object[]> parametersForHasAll() {
        return paramsList(params(true, ABC, ABC),
                          params(true, ABC, ABC_DUP),
                          params(true, ABC, A),
                          params(true, ABC, EMPTY),

                          params(true, EMPTY, EMPTY),

                          params(false, ABC, ABCD),
                          params(false, ABC, BCD),
                          params(false, ABC, null),
                          params(false, ABC, DE),

                          params(false, null, EMPTY),
                          params(false, null, null),
                          params(false, null, DE));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <T> void any(boolean expected, Collection<T> coll) {
        boolean result = Collections.any(coll);
        assertThat(result, withContext(message("coll", coll), equalTo(expected)));
    }
    
    private List<Object[]> parametersForAny() {
        return paramsList(params(true, ABC),
                          params(false, EMPTY));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void join(String expected, Collection<String> coll, String delim) {
        String result = Collections.join(coll, delim);
        assertThat(result, withContext(message("coll", coll), equalTo(expected)));
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
