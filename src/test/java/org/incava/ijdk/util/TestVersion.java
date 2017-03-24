package org.incava.ijdk.util;

import ijdk.collect.List;
import ijdk.lang.Common;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static ijdk.lang.Common.*;
import static org.incava.test.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class TestVersion {
    @Test
    @Parameters
    public void initFromString(Integer expMajor, Integer expMinor, Integer expPatch, Integer expRevision, String str) {
        Version version = new Version(str);
        assertEqual(expMajor, version.getMajor(), message("str", str));
        assertEqual(expMinor, version.getMinor(), message("str", str));
        assertEqual(expPatch, version.getPatch(), message("str", str));
        assertEqual(expPatch, version.getBuild(), message("str", str));
        assertEqual(expRevision, version.getRevision(), message("str", str));
    }
    
    private List<Object[]> parametersForInitFromString() {
        return Common.<Object[]>list(Common.<Object>ary(1, null, null, null, "1"),
                                     Common.<Object>ary(1, 2,    null, null, "1.2"),
                                     Common.<Object>ary(1, 2,       3, null, "1.2.3"),
                                     Common.<Object>ary(1, 2,       3,    4, "1.2.3.4"));
    }

    @Test
    @Parameters
    public void initFromIntegers(Integer expMajor, Integer expMinor, Integer expPatch, Integer expRevision, Integer ... args) {
        Version version = new Version(args);
        assertEqual(expMajor, version.getMajor(), message("args", args));
        assertEqual(expMinor, version.getMinor(), message("args", args));
        assertEqual(expPatch, version.getPatch(), message("args", args));
        assertEqual(expPatch, version.getBuild(), message("args", args));
        assertEqual(expRevision, version.getRevision(), message("args", args));
    }
    
    private List<Object[]> parametersForInitFromIntegers() {
        return Common.<Object[]>list(Common.<Object>ary(1, null, null, null, new Integer[] { 1 }),
                                     Common.<Object>ary(1, 2,    null, null, new Integer[] { 1, 2 }),
                                     Common.<Object>ary(1, 2,       3, null, new Integer[] { 1, 2, 3 }),
                                     Common.<Object>ary(1, 2,       3, 4,    new Integer[] { 1, 2, 3, 4 }));
    }

    @Test
    @Parameters
    public void toString(String expected, String str) {
        assertEqual(expected, new Version(str).toString(), message("str", str));
    }
    
    private List<Object[]> parametersForToString() {
        return Common.<Object[]>list(ary("1.2.3.4", "1.2.3.4"),
                                     ary("1.2.3", "1.2.3"),
                                     ary("1.2", "1.2"),
                                     ary("1", "1"),
                                     ary("1.0", "1.0"));
    }

    @Test
    @Parameters
    public void equals(Boolean expected, Version x, Version y) {
        assertEqual(expected, x.equals(y), message("x", x, "y", y));
    }
    
    private List<Object[]> parametersForEquals() {
        return Common.<Object[]>list(Common.<Object>ary(true,  new Version(), new Version()),
                                     Common.<Object>ary(true,  new Version(1), new Version(1)),
                                     Common.<Object>ary(true,  new Version(1, 2), new Version(1, 2)),
                                     Common.<Object>ary(true,  new Version(1, 2, 3), new Version(1, 2, 3)),
                                     Common.<Object>ary(true,  new Version(1, 2, 3, 4), new Version(1, 2, 3, 4)),
                                     Common.<Object>ary(false, new Version(1), new Version(2)),
                                     Common.<Object>ary(false, new Version(1), new Version(1, 2)),
                                     Common.<Object>ary(false, new Version(1, 2), new Version(1, 2, 3)),
                                     Common.<Object>ary(false, new Version(1, 2, 3), new Version(1, 2, 3, 4)));
    }

    @Test
    @Parameters
    public void compareTo(Integer expected, Version x, Version y) {
        assertEqual(expected, x.compareTo(y), message("x", x, "y", y));
    }
    
    private List<Object[]> parametersForCompareTo() {
        return Common.<Object[]>list(Common.<Object>ary(0,  new Version(), new Version()),
                                     Common.<Object>ary(0,  new Version(1), new Version(1)),
                                     Common.<Object>ary(0,  new Version(1, 2), new Version(1, 2)),
                                     Common.<Object>ary(0,  new Version(1, 2, 3), new Version(1, 2, 3)),
                                     Common.<Object>ary(0,  new Version(1, 2, 3, 4), new Version(1, 2, 3, 4)),
                                     
                                     Common.<Object>ary(-1,  new Version(1), new Version(2)),
                                     Common.<Object>ary(-1,  new Version(1, 2), new Version(1, 3)),
                                     Common.<Object>ary(-1,  new Version(1, 2, 3), new Version(1, 2, 4)),
                                     Common.<Object>ary(-1,  new Version(1, 2, 3, 4), new Version(1, 2, 3, 5)),
                                     
                                     Common.<Object>ary(1,  new Version(2), new Version(1)),
                                     Common.<Object>ary(1,  new Version(1, 3), new Version(1, 2)),
                                     Common.<Object>ary(1,  new Version(1, 2, 4), new Version(1, 2, 3)),
                                     Common.<Object>ary(1,  new Version(1, 2, 3, 5), new Version(1, 2, 3, 4)));
    }
}
