package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

@RunWith(JUnitParamsRunner.class)
public class CollectionExtTest {
    public static final Collection<String> ABC = Arrays.asList(new String[] { "a", "b", "c" });
    public static final Collection<String> ABC_DUP = Arrays.asList(new String[] { "a", "b", "c" });
    public static final Collection<String> A = Arrays.asList(new String[] { "a" });
    public static final Collection<String> ABCD = Arrays.asList(new String[] { "a", "b", "c", "d" });
    public static final Collection<String> BCD = Arrays.asList(new String[] { "b", "c", "d" });
    public static final Collection<String> DE = Arrays.asList(new String[] { "d", "e" });
    public static final Collection<String> EMPTY = new ArrayList<String>();

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public <T> void hasAny(boolean expected, Collection<T> src, Collection<T> tgt) {
        boolean result = CollectionExt.hasAny(src, tgt);
        assertEqual(expected, result, message("src", src, "tgt", tgt));
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

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public <T> void hasAll(boolean expected, Collection<T> src, Collection<T> tgt) {
        boolean result = CollectionExt.hasAll(src, tgt);
        assertEqual(expected, result, message("src", src, "tgt", tgt));
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

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public <T> void any(boolean expected, Collection<T> coll) {
        boolean result = CollectionExt.any(coll);
        assertEqual(expected, result);
    }
    
    private List<Object[]> parametersForAny() {
        return paramsList(params(true, ABC),
                          params(false, EMPTY));
    }
}
