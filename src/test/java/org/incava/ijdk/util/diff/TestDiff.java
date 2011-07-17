package org.incava.ijdk.util.diff;

import java.util.*;
import junit.framework.TestCase;


public class TestDiff extends TestCase {
    public TestDiff(String name) {
        super(name);
    }

    public void testStrings1() {
        Object[] a = new Object[] {
            "a",
            "b",
            "c",
            "e",
            "h",
            "j",
            "l",
            "m",
            "n",
            "p"
        };

        Object[] b = new Object[] {
            "b",
            "c",
            "d",
            "e",
            "f",
            "j",
            "k",
            "l",
            "m",
            "r",
            "s",
            "t"
        };

        Difference[] expected = new Difference[] {
            new Difference(0,  0,  0, -1),
            new Difference(3, -1,  2,  2),
            new Difference(4,  4,  4,  4),
            new Difference(6, -1,  6,  6),
            new Difference(8,  9,  9, 11),
        };

        runDiff(a, b, expected);
    }

    public void testStrings2() {
        Object[] a = new Object[] {
            "a",
            "b",
            "c",
            "d"
        };

        Object[] b = new Object[] {
            "c",
            "d"
        };

        Difference[] expected = new Difference[] {
            new Difference(0, 1, 0, -1),
        };

        runDiff(a, b, expected);
    }

    public void testStrings3() {
        Object[] a = new Object[] {
            "a", "b", "c", "d", "x", "y", "z"
        };

        Object[] b = new Object[] {
            "c", "d"
        };

        Difference[] expected = new Difference[] {
            new Difference(0,  1,  0, -1),
            new Difference(4,  6,  2, -1)
        };

        runDiff(a, b, expected);
    }

    public void testStrings4() {
        Object[] a = new Object[] {
            "a",
            "b",
            "c",
            "d",
            "e"
        };

        Object[] b = new Object[] {
            "a",
            "x",
            "y",
            "b",
            "c",
            "j",
            "e",
        };

        Difference[] expected = new Difference[] {
            new Difference(1, -1,  1,  2),
            new Difference(3,  3,  5,  5),
        };

        runDiff(a, b, expected);
    }

    public void testInteger() {
        Object[] a = new Integer[] {
            new Integer(1), 
            new Integer(2), 
            new Integer(3), 
        };

        Object[] b = new Integer[] {
            new Integer(2), 
            new Integer(3), 
        };

        Difference[] expected = new Difference[] {
            new Difference(0, 0, 0, -1)
        };

        runDiff(a, b, expected);
    }

    static class NoncomparableObject
    {
        private String x;

        public NoncomparableObject(String x) {
            this.x = x;
        }

        public boolean equals(Object obj) {
            return compareTo(obj) == 0;
        }

        public int compareTo(Object obj) {
            if (obj instanceof NoncomparableObject) {
                NoncomparableObject other = (NoncomparableObject)obj;
                return x.compareTo(other.x);
            }
            else {
                return -1;
            }
        }

        public String toString() {
            return getClass().getName() + " \"" + x + "\"";
        }
    }

    public void testComparator() {
        Object[] a = new NoncomparableObject[] {
            new NoncomparableObject("a"), 
            new NoncomparableObject("b"), 
            new NoncomparableObject("c"), 
            new NoncomparableObject("g"), 
            new NoncomparableObject("h1"), 
        };

        Object[] b = new NoncomparableObject[] {
            new NoncomparableObject("b"), 
            new NoncomparableObject("c"), 
            new NoncomparableObject("d"), 
            new NoncomparableObject("e"), 
            new NoncomparableObject("f"), 
            new NoncomparableObject("g"), 
            new NoncomparableObject("h2"), 
        };

        Difference[] expected = new Difference[] {
            new Difference(0,  0,  0, -1),
            new Difference(3, -1 , 2,  4),
            new Difference(4,  4,  6,  6),
        };

        Comparator<Object> comparator = new Comparator<Object>() {
                public int compare(Object x, Object y) {
                    if (x == null) {
                        if (y == null) {
                            return 0;
                        }
                        else {
                            return -1;
                        }
                    }
                    else if (y == null) {
                        return 1;
                    }
                    else if (x instanceof NoncomparableObject && y instanceof NoncomparableObject) {
                        NoncomparableObject xno = (NoncomparableObject)x;
                        NoncomparableObject yno = (NoncomparableObject)y;
                        return xno.x.compareTo(yno.x);
                    }
                    else {
                        return -1;
                    }
                }
                
                public boolean equals(Object x, Object y) {
                    return compare(x, y) == 0;
                }
            };
        
        runDiff(new Diff<Object>(a, b, comparator), expected);
    }

    static class ComparableObject implements Comparable
    {
        private String x;

        public ComparableObject(String x) {
            this.x = x;
        }

        public boolean equals(Object obj) {
            return compareTo(obj) == 0;
        }

        public int compareTo(Object obj) {
            if (obj instanceof ComparableObject) {
                ComparableObject other = (ComparableObject)obj;
                return x.compareTo(other.x);
            }
            else {
                return -1;
            }
        }

        public String toString() {
            return getClass().getName() + " \"" + x + "\"";
        }
    }

    public void testComparableObject() {
        Object[] a = new ComparableObject[] {
            new ComparableObject("a"), 
            new ComparableObject("b"), 
            new ComparableObject("c"), 
            new ComparableObject("g"), 
            new ComparableObject("h1"), 
        };

        Object[] b = new ComparableObject[] {
            new ComparableObject("b"), 
            new ComparableObject("c"), 
            new ComparableObject("d"), 
            new ComparableObject("e"), 
            new ComparableObject("f"), 
            new ComparableObject("g"), 
            new ComparableObject("h2"), 
        };

        Difference[] expected = new Difference[] {
            new Difference(0,  0,  0, -1),
            new Difference(3, -1,  2,  4),
            new Difference(4,  4,  6,  6),
        };

        runDiff(a, b, expected);
    }
    
    public void testLongArray() {
        Object[] a = new Object[] {
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "i",
            "j",
            "k",
            "l",
        };

        Object[] b = new Object[] {
            "a",
            "b",
            "p",                // insert
            "q",                // ...
            "r",                // ...
            "s",                // ...
            "t",                // ...
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "i",
            "j",
            "u",                // change
            "l",
        };
            
        Difference[] expected = new Difference[] {
            new Difference(2,  -1,  2,  6),
            new Difference(10, 10, 15, 15),
        };

        runDiff(a, b, expected);
    }

    public void testRepeated() {
        Object[] a = new Object[] {
            "a",
            "a",
            "a",
            "a",
            "b",
            "b",
            "b",
            "a",
            "a",
            "a",
            "a",
            "b",
            "b",
            "b",
            "a",
            "a",
            "a",
            "a",
            "b",
            "b",
            "b",
            "a",
            "a",
            "a",
            "a",
            "b",
            "b",
            "b",
        };

        Object[] b = new Object[] {
            "a",
            "a",
            "a",
            "a",
            "b",
            "b",
            "b",
            "a",
            "b",
            "b",
            "b",
            "a",
            "a",
            "a",
            "a",
        };
            
        Difference[] expected = new Difference[] {
            new Difference(8,  10,  8, -1),
            new Difference(18, 27, 15, -1),
        };

        runDiff(a, b, expected);
    }

    public void testReallyBig() {
        Object[] a = new Object[] {
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "A",
            "H",
            "I",
            "J",
            "D",
            "K",
            "L",
            "C",
            "G",
            "M",
            "H",
            "N",
            "J",
            "I",
            "K",
            "O",
            "C",
            "G",
            "M",
            "P",
            "Q",
            "J",
            "R",
            "K",
            "S",
            "C",
            "C",
            "F",
            "G",
            "D",
            "T",
            "N",
            "G",
            "M",
            "U",
            "V",
            "J",
            "Q",
            "K",
            "W",
            "C",
            "G",
            "M",
            "X",
            "C",
            "V",
            "K",
            "Y",
            "C",
            "G",
            "G",
            "A",
            "Z",
            "AA",
            "J",
            "C",
            "Z",
            "G",
            "V",
            "K",
            "BB",
            "C",
            "G",
            "M",
            "CC",
            "DD",
            "J",
            "EE",
            "K",
            "FF",
            "C",
            "AA",
            "G",
            "M",
            "GG",
            "K",
            "HH",
            "C",
            "DD",
            "G",
            "M",
            "II",
            "II",
            "II",
        };

        Object[] b = new Object[] {
            "A",
            "B",
            "C",
            "JJ",
            "G",
            "A",
            "II",
            "KK",
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "A",
            "H",
            "I",
            "J",
            "D",
            "K",
            "L",
            "C",
            "G",
            "M",
            "H",
            "N",
            "J",
            "I",
            "K",
            "O",
            "C",
            "G",
            "M",
            "P",
            "Q",
            "J",
            "R",
            "K",
            "S",
            "C",
            "C",
            "F",
            "G",
            "D",
            "T",
            "N",
            "G",
            "M",
            "U",
            "V",
            "J",
            "Q",
            "K",
            "W",
            "C",
            "G",
            "M",
            "X",
            "C",
            "V",
            "K",
            "Y",
            "C",
            "G",
            "G",
            "A",
            "Z",
            "AA",
            "J",
            "C",
            "Z",
            "G",
            "V",
            "K",
            "BB",
            "C",
            "G",
            "M",
            "CC",
            "DD",
            "J",
            "EE",
            "K",
            "FF",
            "C",
            "AA",
            "G",
            "M",
            "GG",
            "K",
            "HH",
            "C",
            "DD",
            "G",
            "M",
            "II",
            "II",
            "II",
            "II",
        };
        
        Difference[] expected = new Difference[] {
            new Difference(3,  -1,  3, 10),
            new Difference(88, -1, 96, 96),
        };

        runDiff(a, b, expected);
    }

    // This test doesn't yet work. The problem is that LCSes are logically
    // incorrect, with regard to source code. That is, after a failed mapping
    // (i.e., null), the "next" LCS is the *previous* LCS, not the current one,
    // that is, starting at the next element after the null. Not sure when this
    // is an artifact of the algorithm, or whether it is "correct" and my
    // expectations aren't.

    public void misleading_diffs_testFromZipDiff() {
        Object[] a = new Object[] {
            "{",
            "ZipEntry",
            "e",
            "=",
            "entry",
            ";",
            "if",
            "(",
            "e",
            "!=",
            "null",
            ")",
            "{",
            "switch",
            "(",
            "e",
            ".",
            "method",
            ")",
            "{",
            "case",
            "DEFLATED",
            ":",
            "if",
            "(",
            "(",
            "e",
            ".",
            "flag",
            "&",
            "8",
            ")",
            "==",
            "0",
            ")",
            "{",
            "if",
            "(",
            "e",
            ".",
            "size",
            "!=",
            "def",
            ".",
            "getTotalIn",
            "(",
            ")",
            ")",
            "{",
            "throw",
            "new",
            "ZipException",
            "(",
            "\"invalid entry size (expected \"",
            "+",
            "e",
            ".",
            "size",
            "+",
            "\" but got \"",
            "+",
            "def",
            ".",
            "getTotalIn",
            "(",
            ")",
            "+",
            "\" bytes)\"",
            ")",
            ";",
            "}",
            "if",
            "(",
            "e",
            ".",
            "csize",
            "!=",
            "def",
            ".",
            "getTotalOut",
            "(",
            ")",
            ")",
            "{",
            "throw",
            "new",
            "ZipException",
            "(",
            "\"invalid entry compressed size (expected \"",
            "+",
            "e",
            ".",
            "csize",
            "+",
            "\" but got \"",
            "+",
            "def",
            ".",
            "getTotalOut",
            "(",
            ")",
            "+",
            "\" bytes)\"",
            ")",
            ";",
            "}",
            "if",
            "(",
            "e",
            ".",
            "crc",
            "!=",
            "crc",
            ".",
            "getValue",
            "(",
            ")",
            ")",
            "{",
            "throw",
            "new",
            "ZipException",
            "(",
            "\"invalid entry CRC-32 (expected 0x\"",
            "+",
            "Long",
            ".",
            "toHexString",
            "(",
            "e",
            ".",
            "crc",
            ")",
            "+",
            "\" but got 0x\"",
            "+",
            "Long",
            ".",
            "toHexString",
            "(",
            "crc",
            ".",
            "getValue",
            "(",
            ")",
            ")",
            "+",
            "\")\"",
            ")",
            ";",
            "}",
            "}",
            "else",
            "{",
            "e",
            ".",
            "size",
            "=",
            "def",
            ".",
            "getTotalIn",
            "(",
            ")",
            ";",
            "e",
            ".",
            "csize",
            "=",
            "def",
            ".",
            "getTotalOut",
            "(",
            ")",
            ";",
            "e",
            ".",
            "crc",
            "=",
            "crc",
            ".",
            "getValue",
            "(",
            ")",
            ";",
            "writeEXT",
            "(",
            "e",
            ")",
            ";",
            "}",
            "def",
            ".",
            "reset",
            "(",
            ")",
            ";",
            "written",
            "+=",
            "e",
            ".",
            "csize",
            ";",
            "break",
            ";",
            "}",
            "}",
            "}",
        };

        Object[] b = new Object[] {
            "{",
            "ZipEntry",
            "e",
            "=",
            "entry",
            ";",
            "if",
            "(",
            "e",
            "!=",
            "null",
            ")",
            "{",
            "switch",
            "(",
            "e",
            ".",
            "method",
            ")",
            "{",
            "case",
            "DEFLATED",
            ":",
            "if",
            "(",
            "(",
            "e",
            ".",
            "flag",
            "&",
            "8",
            ")",
            "==",
            "0",
            ")",
            "{",
            "if",
            "(",
            "e",
            ".",
            "size",
            "!=",
            "def",
            ".",
            "getBytesRead",
            "(",
            ")",
            ")",
            "{",
            "throw",
            "new",
            "ZipException",
            "(",
            "\"invalid entry size (expected \"",
            "+",
            "e",
            ".",
            "size",
            "+",
            "\" but got \"",
            "+",
            "def",
            ".",
            "getBytesRead",
            "(",
            ")",
            "+",
            "\" bytes)\"",
            ")",
            ";",
            "}",
            "if",
            "(",
            "e",
            ".",
            "csize",
            "!=",
            "def",
            ".",
            "getBytesWritten",
            "(",
            ")",
            ")",
            "{",
            "throw",
            "new",
            "ZipException",
            "(",
            "\"invalid entry compressed size (expected \"",
            "+",
            "e",
            ".",
            "csize",
            "+",
            "\" but got \"",
            "+",
            "def",
            ".",
            "getBytesWritten",
            "(",
            ")",
            "+",
            "\" bytes)\"",
            ")",
            ";",
            "}",
            "if",
            "(",
            "e",
            ".",
            "crc",
            "!=",
            "crc",
            ".",
            "getValue",
            "(",
            ")",
            ")",
            "{",
            "throw",
            "new",
            "ZipException",
            "(",
            "\"invalid entry CRC-32 (expected 0x\"",
            "+",
            "Long",
            ".",
            "toHexString",
            "(",
            "e",
            ".",
            "crc",
            ")",
            "+",
            "\" but got 0x\"",
            "+",
            "Long",
            ".",
            "toHexString",
            "(",
            "crc",
            ".",
            "getValue",
            "(",
            ")",
            ")",
            "+",
            "\")\"",
            ")",
            ";",
            "}",
            "}",
            "else",
            "{",
            "e",
            ".",
            "size",
            "=",
            "def",
            ".",
            "getBytesRead",
            "(",
            ")",
            ";",
            "e",
            ".",
            "csize",
            "=",
            "def",
            ".",
            "getBytesWritten",
            "(",
            ")",
            ";",
            "e",
            ".",
            "crc",
            "=",
            "crc",
            ".",
            "getValue",
            "(",
            ")",
            ";",
            "writeEXT",
            "(",
            "e",
            ")",
            ";",
            "}",
            "def",
            ".",
            "reset",
            "(",
            ")",
            ";",
            "written",
            "+=",
            "e",
            ".",
            "csize",
            ";",
            "break",
            ";",
            "}",
            "}",
            "}",
        };
        
        Difference[] expected = new Difference[] {
            new Difference(3,  -1,  3, 10),
            new Difference(88, -1, 96, 96),
        };

        runDiff(a, b, expected);
    }

    public void testBlanks() {
        // many thanks to Oliver Koll for finding and submitting this.

        Object[] a = new Object[] {
            "same",
            "same",
            "same",
            "",
            "same",
            "del",      // delete
            "",         // ...
            "del"       // ...
        };

        Object[] b = new Object[] {
            "ins",      // insert
            "",         // ...
            "same",
            "same",
            "same",
            "",
            "same"
        };

        Difference[] expected = new Difference[] {
            new Difference(0, -1,  0,  1),
            new Difference(5,  7,  7, -1),
        };

        runDiff(a, b, expected);
    }

    protected void assertLCS(Object[] a, Object[] b, Integer[] expected) {
        tr.Ace.log("a", a);
        tr.Ace.log("b", b);
        tr.Ace.log("expected", expected);

        Diff<Object> diff = new Diff<Object>(a, b);
        
        Integer[] lcses = diff.getLongestCommonSubsequences();
        tr.Ace.log("lcses", lcses);

        assertEquals("length of output", expected.length, lcses.length);
        for (int ei = 0; ei < expected.length; ++ei) {
            Integer exp = expected[ei];
            Integer lcs = lcses[ei];
            assertEquals("expected[" + ei +"]", exp, lcs);
        }
    }    
    
    protected void runDiff(Object[] a, Object[] b, Difference[] expected) {
        tr.Ace.log("a", a);
        tr.Ace.log("b", b);

        Diff<Object> diff = new Diff<Object>(a, b);
        runDiff(diff, expected);
    }
    
    protected void runDiff(Diff<Object> diff, Difference[] expected) {
        tr.Ace.log("expected", expected);

        List<Difference> diffOut = diff.diff();
        tr.Ace.log("diffOut", diffOut);

        assertEquals("length of output", expected.length, diffOut.size());
        for (int ei = 0; ei < expected.length; ++ei) {
            Difference d   = diffOut.get(ei);
            Difference exp = expected[ei];
            assertEquals("expected[" + ei +"]", exp, d);
        }
    }
    
}
