package org.incava.ijdk.version;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;
import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

public class VersionTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void initFromString(Integer expMajor, Integer expMinor, Integer expPatch, Integer expRevision, String str) {
        String msg = message("str", str);
        Version version = new Version(str);
        assertEqual(expMajor, version.getMajor(), msg);
        assertEqual(expMinor, version.getMinor(), msg);
        assertEqual(expPatch, version.getPatch(), msg);
        assertEqual(expPatch, version.getBuild(), msg);
        assertEqual(expRevision, version.getRevision(), msg);
    }
    
    private List<Object[]> parametersForInitFromString() {
        return paramsList(params(1, null, null, null, "1"),
                          params(1, 2,    null, null, "1.2"),
                          params(1, 2,    3,    null, "1.2.3"),
                          params(1, 2,    3,    4,    "1.2.3.4"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void initFromIntegers(Integer expMajor, Integer expMinor, Integer expPatch, Integer expRevision, Integer ... args) {
        String msg = message("args", args);
        Version version = new Version(args);
        assertEqual(expMajor, version.getMajor(), msg);
        assertEqual(expMinor, version.getMinor(), msg);
        assertEqual(expPatch, version.getPatch(), msg);
        assertEqual(expPatch, version.getBuild(), msg);
        assertEqual(expRevision, version.getRevision(), msg);
    }
    
    private List<Object[]> parametersForInitFromIntegers() {
        return paramsList(params(null, null, null, null, new Integer[] { }),
                          params(1,    null, null, null, new Integer[] { 1 }),
                          params(1,    2,    null, null, new Integer[] { 1, 2 }),
                          params(1,    2,    3,    null, new Integer[] { 1, 2, 3 }),
                          params(1,    2,    3,    4,    new Integer[] { 1, 2, 3, 4 }));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toString(String expected, String str) {
        assertEqual(expected, new Version(str).toString(), message("str", str));
    }
    
    private List<Object[]> parametersForToString() {
        return paramsList(params("1.2.3.4", "1.2.3.4"),
                          params("1.2.3", "1.2.3"),
                          params("1.2", "1.2"),
                          params("1", "1"),
                          params("1.0", "1.0"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void equals(Boolean expected, Version x, Version y) {
        assertEqual(expected, x.equals(y), message("x", x, "y", y));
    }
    
    private List<Object[]> parametersForEquals() {
        Version v = new Version();
        Version v1 = new Version(1);
        Version v12 = new Version(1, 2);
        Version v123 = new Version(1, 2, 3);
        Version v1234 = new Version(1, 2, 3, 4);        
        
        return paramsList(params(true,  v, v),
                          params(true,  v, new Version()),
                          params(true,  v1, new Version(1)),
                          params(true,  v12, new Version(1, 2)),
                          params(true,  v123, new Version(1, 2, 3)),
                          params(true,  v1234, new Version(1, 2, 3, 4)),
                          params(false, v1, new Version(2)),
                          params(false, v1, new Version(1, 2)),
                          params(false, v12, new Version(1, 2, 3)),
                          params(false, v123, new Version(1, 2, 3, 4)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compareTo(Integer expected, Version x, Version y) {
        assertEqual(expected, x.compareTo(y), message("x", x, "y", y));
    }
    
    private List<Object[]> parametersForCompareTo() {
        Version v = new Version();
        Version v1 = new Version(1);
        Version v12 = new Version(1, 2);
        Version v123 = new Version(1, 2, 3);
        Version v1234 = new Version(1, 2, 3, 4);
        
        return paramsList(params(0,  v, v),
                          params(0,  v, new Version()),
                          params(0,  v1, new Version(1)),
                          params(0,  v12, new Version(1, 2)),
                          params(0,  v123, new Version(1, 2, 3)),
                          params(0,  v1234, new Version(1, 2, 3, 4)),
                                     
                          params(-1,  v1, new Version(2)),
                          params(-1,  v12, new Version(1, 3)),
                          params(-1,  v123, new Version(1, 2, 4)),
                          params(-1,  v1234, new Version(1, 2, 3, 5)),
                                     
                          params(1,  new Version(2), v1),
                          params(1,  new Version(1, 3), v12),
                          params(1,  new Version(1, 2, 4), v123),
                          params(1,  new Version(1, 2, 3, 5), v1234));
    }

    @Test
    public void isComparable() {
        Version v = new Version();
        assertEqual(true, v instanceof Comparable);
    }

    @Test
    public void latest() {
        assertThat(Version.LATEST, hasToString("latest"));
    }
}
