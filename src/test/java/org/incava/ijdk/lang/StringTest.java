package org.incava.ijdk.lang;

import ijdk.lang.ICore;
import java.util.*;
import junit.framework.TestCase;

public abstract class StringTest extends TestCase {
    public StringTest(String name) {
        super(name);
    }
    
    // split

    public abstract String[] assertSplit(String[] expected, String str, char delim, int max);

    public abstract String[] assertSplit(String[] expected, String str, String delim, int max);

    public void testSplitNull() {
        assertSplit(null, null, ';', -1);
    }

    public void testSplitSingleChar() {
        assertSplit(new String[] { "this", "is", "a", "test" }, "this;is;a;test", ';', -1);
    }

    public void testSplitStringOneChar() {
        assertSplit(new String[] { "this", "is", "a", "test" }, "this;is;a;test", ";", -1);
    }
    
    public void testSplitStringWithWhitespace() {
        assertSplit(new String[] { "this", "is", "a", "test" }, "this ; is ; a ; test", " ; ", -1);
    }

    public void testSplitStringCharEmptyBlock() {
        assertSplit(new String[] { "this", "is", "a", "", "test" }, "this;is;a;;test", ';', -1);
    }

    public void testSplitStringCharEmptyBlockMaxOne() {
        assertSplit(new String[] { "this;is;a;;test" }, "this;is;a;;test", ';', 1);
    }
    
    public void testSplitStringCharEmptyBlockMaxTwo() {
        assertSplit(new String[] { "this", "is;a;;test" }, "this;is;a;;test", ';', 2);
    }

    public void testSplitStringCharEmptyBlockMaxThree() {   
        assertSplit(new String[] { "this", "is", "a;;test" }, "this;is;a;;test", ';', 3);
    }
        
    public void testSplitStringCharEmptyBlockMaxFour() {
        assertSplit(new String[] { "this", "is", "a", ";test" }, "this;is;a;;test", ';', 4);
    }

    // toList
    
    public abstract void assertToList(String[] exp, String str);
        
    public void testToListNull() {
        assertToList(null, null);
    }
    
    public void testToListDefault() {
        String[] expected = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expected, "fee, fi, foo, fum");
    }
    
    public void testToListWhitespaceChars() {
        String[] expected = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expected, "fee,\tfi,\nfoo,\rfum");
    }

    
    // pad

    public abstract String assertPad(String expected, String str, char ch, int length);

    public abstract String assertPad(String expected, String str, int length);

    public void testPadCharNull() {
        assertPad(null, null, '*', 8);
    }
    
    public void testPadCharDefault() {
        assertPad("abcd****", "abcd", '*', 8);
    }
    
    public void testPadCharShorter() {
        assertPad("abcd", "abcd", '*', 3);
    }
    
    public void testPadCharAtLength() {
        assertPad("abcd", "abcd", '*', 4);
    }
    
    public void testPadCharOneChar() {
        assertPad("abcd*", "abcd", '*', 5);
    }

    public void testPadSpaceNull() {
        assertPad(null, null, 8);
    }    
    
    public void testPadSpaceDefault() {
        assertPad("abcd    ", "abcd", 8);
    }
    
    public void testPadSpaceShorter() {
        assertPad("abcd", "abcd", 3);
    }
    
    public void testPadSpaceAtLength() {
        assertPad("abcd", "abcd", 4);
    }
    
    public void testPadSpaceOneChar() {
        assertPad("abcd ", "abcd", 5);
    }

    // padLeft

    public abstract String assertPadLeft(String expected, String str, char ch, int length);

    public abstract String assertPadLeft(String expected, String str, int length);
    
    public void testPadLeftCharNull() {
        assertPadLeft(null, null, '*', 8);
    }
    
    public void testPadLeftCharDefault() {
        assertPadLeft("****abcd", "abcd", '*', 8);
    }
    
    public void testPadLeftCharShorter() {
        assertPadLeft("abcd", "abcd", '*', 3);
    }
    
    public void testPadLeftCharAtLength() {
        assertPadLeft("abcd", "abcd", '*', 4);
    }
    
    public void testPadLeftCharOneChar() {
        assertPadLeft("*abcd", "abcd", '*', 5);
    }
    
    public void testPadLeftSpaceNull() {
        assertPadLeft(null, null, 8);
    }
    
    public void testPadLeftSpaceDefault() {
        assertPadLeft("    abcd", "abcd", 8);
    }
    
    public void testPadLeftSpaceShorter() {
        assertPadLeft("abcd", "abcd", 3);
    }
    
    public void testPadLeftSpaceAtLength() {
        assertPadLeft("abcd", "abcd", 4);
     }
    
    public void testPadLeftSpaceOneChar() {
       assertPadLeft(" abcd", "abcd", 5);
    }
    
    // repeat

    public abstract String assertRepeat(String expected, String str, int length);

    public abstract String assertRepeat(String expected, char ch, int length);

    public void testRepeatStringNull() {
        assertRepeat("", null,  0);
    }

    public void testRepeatStringNegative() {
        assertRepeat("", "abcd",  -1);
    }

    public void testRepeatStringZero() {
        assertRepeat("", "abcd",  0);
    }

    public void testRepeatStringOne() {
        assertRepeat("abcd", "abcd",  1);
    }

    public void testRepeatStringTwo() {
        assertRepeat("abcdabcd", "abcd",  2);
    }

    public void testRepeatCharNegative() {
        assertRepeat("", 'a', -1);
    }

    public void testRepeatCharZero() {
        assertRepeat("", 'a', 0);
    }

    public void testRepeatCharOne() {
        assertRepeat("a", 'a', 1);
    }

    public void testRepeatCharTwo() {
        assertRepeat("aa", 'a', 2);
    }

    // left

    public abstract String assertLeft(String expected, String str, int length);

    public void testLeftNull() {
        assertLeft(null, null,  1);
    }

    public void testLeftLonger() {
        assertLeft("abcd", "abcdefgh", 4);
    }

    public void testLeftAtLimit() {
        assertLeft("abcd", "abcd", 4);
    }

    public void testLeftShorter() {
        assertLeft("abcd", "abcd", 5);
    }

    public void testLeftZero() {
        assertLeft("", "abcd", 0);
    }

    public void testLeftNegative() {
        assertLeft("", "abcd", -1);
    }

    // right

    public abstract String assertRight(String expected, String str, int length);

    public void testRightNull() {
        assertRight(null, null,  1);
    }

    public void testRightLonger() {
        assertRight("efgh", "abcdefgh", 4);
    }

    public void testRightAtLimit() {
        assertRight("abcd", "abcd", 4);
    }

    public void testRightShorter() {
        assertRight("abcd", "abcd", 5);
    }

    public void testRightZero() {
        assertRight("", "abcd", 0);
    }

    public void testRightNegative() {
        assertRight("", "abcd", -1);
    }

    // join

    public abstract String assertJoin(String expected, String[] ary, String delim);

    public abstract String assertJoin(String expected, Collection<String> coll, String delim);

    public void testJoinArrayNull() {
        assertJoin(null, (String[])null, ",");
    }

    public void testJoinArrayDelimNull() {
        assertJoin("abcd", new String[] { "a", "b", "c", "d" }, null);
    }

    public void testJoinArrayDelimEmpty() {
        assertJoin("abcd", new String[] { "a", "b", "c", "d" }, "");
    }

    public void testJoinArrayEmpty() {
        assertJoin("", new String[] { "" }, ",");
    }

    public void testJoinArrayNotEmpty() {
        assertJoin("a,b,c,d", new String[] { "a", "b", "c", "d" }, ",");
    }

    public void testJoinCollectionNull() {
        assertJoin(null, (ArrayList<String>)null, ",");
    }

    public void testJoinCollectionDelimNull() {
        assertJoin("abcd", ICore.list("a", "b", "c", "d"), null);
    }

    public void testJoinCollectionDelimEmpty() {
        assertJoin("abcd", ICore.list("a", "b", "c", "d"), "");
    }

    public void testJoinCollectionEmpty() {
        assertJoin("", new ArrayList<String>(), ",");
    }

    public void testJoinCollectionNotEmpty() {
        assertJoin("a,b,c,d", ICore.list("a", "b", "c", "d"), ",");
    }

    // charAt

    public abstract Character assertCharAt(Character expected, String str, int index);
    
    public void testCharAtNullString() {
        assertCharAt(null, null, 0);
    }
    
    public void testCharAtZero() {
        assertCharAt('a', "abc", 0);
    }
    
    public void testCharAtOne() {
        assertCharAt('b', "abc", 1);
    }
    
    public void testCharAtEnd() {
        assertCharAt('c', "abc", 2);
    }
    
    public void testCharAtPastRange() {
        assertCharAt(null, "abc", 3);
    }
    
    public void testCharAtNegativeOne() {
        assertCharAt('c', "abc", -1);
    }
    
    public void testCharAtNegativeTwo() {
        assertCharAt('b', "abc", -2);
    }
    
    public void testCharAtNegativeAtStart() {
        assertCharAt('a', "abc", -3);
    }
    
    public void testCharAtNegativeBeforeRange() {
        assertCharAt(null, "abc", -4);
    }
}
