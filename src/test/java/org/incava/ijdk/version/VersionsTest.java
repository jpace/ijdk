package org.incava.ijdk.version;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.sameInstance;

public class VersionsTest extends Parameterized {
    private static Version a;
    private static Version b;
    private static Versions ab;
    private static Versions ba;
    
    static {
        a = Version.of("1");
        b = Version.of("2");
        ab = new Versions(Array.of(a, b));
        ba = new Versions(Array.of(b, a));
    }

    @Test
    public void initEmpty() {
        Versions vs = new Versions();
        assertThat(vs, empty());
    }
    
    @Test
    public void initArray() {
        assertThat(ab, hasSize(2));
    }
    
    @Test @Parameters(method="paramsForFind") @TestCaseName("{method} {index} {params}")
    public void findThrough(Version expFindUpTo, Version expFindThrough, Versions versions, String version) {
        Version result = versions.findThrough(Version.of(version));
        assertThat(result, sameInstance(expFindThrough));
    }
    
    @Test @Parameters(method="paramsForFind") @TestCaseName("{method} {index} {params}")
    public void findUpTo(Version expFindUpTo, Version expFindThrough, Versions versions, String version) {
        Version result = versions.findUpTo(Version.of(version));
        assertThat(result, sameInstance(expFindUpTo));
    }
    
    private List<Object[]> paramsForFind() {
        return paramsList(params(b,    b,    ab, "latest"),
                          params(b,    b,    ba, "latest"),
                          params(null, null, ba, "0"),
                          params(null, a,    ba, "1"),
                          params(a,    b,    ba, "2"),
                          params(b,    b,    ba, "3"));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void findLatest(Version expected, Versions versions) {
        Version result = versions.findLatest();
        assertThat(expected, sameInstance(expected));
    }
    
    private List<Object[]> parametersForFindLatest() {
        return paramsList(params(b, ab),
                          params(b, ba));
    }    
}
