package org.incava.ijdk.util;

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
public class VersionTest {
    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public void initFromString(Integer expMajor, Integer expMinor, Integer expPatch, Integer expRevision, String str) {
        Version version = new Version(str);
        assertEqual(expMajor, version.getMajor(), message("str", str));
        assertEqual(expMinor, version.getMinor(), message("str", str));
        assertEqual(expPatch, version.getPatch(), message("str", str));
        assertEqual(expPatch, version.getBuild(), message("str", str));
        assertEqual(expRevision, version.getRevision(), message("str", str));
    }
    
    private List<Object[]> parametersForInitFromString() {
        return paramsList(params(1, null, null, null, "1"),
                          params(1, 2,    null, null, "1.2"),
                          params(1, 2,       3, null, "1.2.3"),
                          params(1, 2,       3,    4, "1.2.3.4"));
    }

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public void initFromIntegers(Integer expMajor, Integer expMinor, Integer expPatch, Integer expRevision, Integer ... args) {
        Version version = new Version(args);
        assertEqual(expMajor, version.getMajor(), message("args", args));
        assertEqual(expMinor, version.getMinor(), message("args", args));
        assertEqual(expPatch, version.getPatch(), message("args", args));
        assertEqual(expPatch, version.getBuild(), message("args", args));
        assertEqual(expRevision, version.getRevision(), message("args", args));
    }
    
    private List<Object[]> parametersForInitFromIntegers() {
        return paramsList(params(1, null, null, null, new Integer[] { 1 }),
                          params(1, 2,    null, null, new Integer[] { 1, 2 }),
                          params(1, 2,       3, null, new Integer[] { 1, 2, 3 }),
                          params(1, 2,       3, 4,    new Integer[] { 1, 2, 3, 4 }));
    }

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
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

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public void equals(Boolean expected, Version x, Version y) {
        assertEqual(expected, x.equals(y), message("x", x, "y", y));
    }
    
    private List<Object[]> parametersForEquals() {
        return paramsList(params(true,  new Version(), new Version()),
                          params(true,  new Version(1), new Version(1)),
                          params(true,  new Version(1, 2), new Version(1, 2)),
                          params(true,  new Version(1, 2, 3), new Version(1, 2, 3)),
                          params(true,  new Version(1, 2, 3, 4), new Version(1, 2, 3, 4)),
                          params(false, new Version(1), new Version(2)),
                          params(false, new Version(1), new Version(1, 2)),
                          params(false, new Version(1, 2), new Version(1, 2, 3)),
                          params(false, new Version(1, 2, 3), new Version(1, 2, 3, 4)));
    }

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public void compareTo(Integer expected, Version x, Version y) {
        assertEqual(expected, x.compareTo(y), message("x", x, "y", y));
    }
    
    private List<Object[]> parametersForCompareTo() {
        return paramsList(params(0,  new Version(), new Version()),
                          params(0,  new Version(1), new Version(1)),
                          params(0,  new Version(1, 2), new Version(1, 2)),
                          params(0,  new Version(1, 2, 3), new Version(1, 2, 3)),
                          params(0,  new Version(1, 2, 3, 4), new Version(1, 2, 3, 4)),
                                     
                          params(-1,  new Version(1), new Version(2)),
                          params(-1,  new Version(1, 2), new Version(1, 3)),
                          params(-1,  new Version(1, 2, 3), new Version(1, 2, 4)),
                          params(-1,  new Version(1, 2, 3, 4), new Version(1, 2, 3, 5)),
                                     
                          params(1,  new Version(2), new Version(1)),
                          params(1,  new Version(1, 3), new Version(1, 2)),
                          params(1,  new Version(1, 2, 4), new Version(1, 2, 3)),
                          params(1,  new Version(1, 2, 3, 5), new Version(1, 2, 3, 4)));
    }

    @Test
    public void isComparable() {
        Version v = new Version();
        assertEqual(true, v instanceof Comparable);
    }
}
