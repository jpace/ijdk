package org.incava.ijdk.lang;

import java.util.*;
import org.incava.test.AbstractTestCaseExt;
import org.junit.Assert;

public class TestStringExt extends AbstractTestCaseExt {
    public TestStringExt(String name) {
        super(name);
    }

    // split

    public String[] assertSplit(String[] expected, String str, char delim, int max) {
        String[] result = StringExt.split(str, delim, max);
        Assert.assertArrayEquals("str: '" + str + "'; delim: '" + delim + "'; max: " + max, expected, result);
        return result;
    }

    public String[] assertSplit(String[] expected, String str, String delim, int max) {
        String[] result = StringExt.split(str, delim, max);
        Assert.assertArrayEquals("str: '" + str + "'; delim: '" + delim + "'; max: " + max, expected, result);
        return result;
    }

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
    
    public void assertToList(String[] exp, String str) {
        List<String> result = StringExt.toList(str);
        assertEquals("str: '" + str + "'", exp == null ? null : Arrays.asList(exp), result);
    }
    
    public void testToListNull() {
        assertToList(null, null);
    }
    
    public void testToListDefault() {
        String[] expected = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expected, "fee, fi, foo, fum");
    }
    
    public void testToListDoubleQuoted() {
        String[] expected = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expected, "\"fee, fi, foo, fum\"");
    }
    
    public void testToListSingleQuoted() {
        String[] expected = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expected, "\'fee, fi, foo, fum\'");
    }
    
    public void testToListWhitespaceChars() {
        String[] expected = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expected, "\'fee,\tfi,\nfoo,\rfum\'");
    }

    // pad

    public String assertPad(String expected, String str, char ch, int length) {
        String result = StringExt.pad(str, ch, length);
        assertEquals("str: '" + str + "'; ch: " + ch + "; length: " + length, expected, result);
        return result;
    }

    public String assertPad(String expected, String str, int length) {
        String result = StringExt.pad(str, length);
        assertEquals("str: '" + str + "'; length: " + length, expected, result);
        return result;
    }

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

    public String assertPadLeft(String expected, String str, char ch, int length) {
        String result = StringExt.padLeft(str, ch, length);
        assertEquals("str: '" + str + "'; ch: " + ch + "; length: " + length, expected, result);
        return result;
    }

    public String assertPadLeft(String expected, String str, int length) {
        String result = StringExt.padLeft(str, length);
        assertEquals("str: '" + str + "'; length: " + length, expected, result);
        return result;
    }
    
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

    public String assertRepeat(String expected, String str, int length) {
        String result = StringExt.repeat(str, length);
        assertEquals("str: '" + str + "'; length: " + length, expected, result);
        return result;
    }

    public String assertRepeat(String expected, char ch, int length) {
        String result = StringExt.repeat(ch, length);
        assertEquals("ch: '" + ch + "'; length: " + length, expected, result);
        return result;
    }

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

    public String assertLeft(String expected, String str, int length) {
        String result = StringExt.left(str, length);
        assertEquals("str: '" + str + "'; length: " + length, expected, result);
        return result;        
    }

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

    public String assertRight(String expected, String str, int length) {
        String result = StringExt.right(str, length);
        assertEquals("str: '" + str + "'; length: " + length, expected, result);
        return result;        
    }

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

    public String assertJoin(String expected, String[] ary, String delim) {
        String result = StringExt.join(ary, delim);
        assertEquals(ary == null ? null : Arrays.asList(ary).toString(), expected, result);
        return result;
    }

    public String assertJoin(String expected, Collection<String> coll, String delim) {
        String result = StringExt.join(coll, delim);
        assertEquals(coll == null ? null : coll.toString(), expected, result);
        return result;
    }

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

    public Character assertCharAt(Character expected, String str, int index) {
        Character result = StringExt.charAt(str, index);
        assertEquals("str: '" + str + "'; index: " + index, expected, result);
        return result;
    }
    
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

    // getIndex

    public void assertGetIndex(Integer exp, String str, int index) {
        Integer result = StringExt.getIndex(str, index);
        assertEquals("str: '" + str + "'; index: " + index, exp, result);
    }

    public void testGetIndexNull() {
        assertGetIndex(null, null, 0);
    }

    public void testGetIndexZero() {
        assertGetIndex(0, "abcd", 0);
    }

    public void testGetIndexOne() {
        assertGetIndex(1, "abcd", 1);
    }

    public void testGetIndexLast() {
        assertGetIndex(3, "abcd", 3);
    }

    public void testGetIndexOffEnd() {
        assertGetIndex(null, "abcd", 4);
    }

    public void testGetIndexNegativeOne() {
        assertGetIndex(3, "abcd", -1);
    }

    public void testGetIndexNegativeTwo() {
        assertGetIndex(2, "abcd", -2);
    }

    public void testGetIndexNegativeAtStart() {
        assertGetIndex(1, "abcd", -3);
    }

    public void testGetIndexNegativeOffStart() {
        assertGetIndex(null, "abcd", -5);
    }

    public void assertSubstring(String exp, String str, Integer fromIndex, Integer toIndex) {
        String substr = StringExt.substring(str, fromIndex, toIndex);
        String msg    = "\"" + str + "\"[" + fromIndex + " ... " + toIndex + "]";
        assertEquals(msg, exp, substr);
    }

    public void testSubstringNull() {
        assertSubstring(null, null, 1, 0);
    }

    public void testSubstringPositiveFull() {
        assertSubstring("abcd", "abcd", 0, 3);
    }

    public void testSubstringPositiveFromLessThanTo() {
        assertSubstring("abc", "abcd", 0, 2);
    }

    public void testSubstringPositiveFromEqualsTo() {
        assertSubstring("a", "abcd", 0, 0);
    }

    public void testSubstringPositiveFromGreaterThanTo() {
        // expect "", not null, per Ruby behavior
        assertSubstring("", "abcd", 1, 0);
    }

    public void testSubstringPositiveFromPastEnd() {
        assertSubstring("", "abcd", 4, 5);
    }
    
    public void testSubstringNegativeFull() {
        assertSubstring("abcd", "abcd", -4, -1);
    }
    
    public void testSubstringNegativeFromLessThanTo() {
        assertSubstring("abc", "abcd", -4, -2);
    }    
    
    public void testSubstringNegativeFromEqualsTo() {
        assertSubstring("a", "abcd", -4, -4);
    }
    
    public void testSubstringNegativeFromGreaterThanTo() {
        // expect "", not null, per Ruby behavior
        assertSubstring("", "abcd", -1, -2);
    }

    public void testSubstringNullFrom() {
        // null == first in string
        assertSubstring("abc", "abcd", null, -2);
    }

    public void testSubstringNullTo() {
        assertSubstring("cd", "abcd", -2, null);
    }

    public void testSubstringNullFromNullTo() {
        assertSubstring("abcd", "abcd", null, null);
    }

    public void assertStartsWith(boolean exp, String str, char ch) {
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, exp, StringExt.startsWith(str, ch));
    }

    public void testStartsWith() {
        assertStartsWith(true, "java", 'j');
        assertStartsWith(false, "java", 'a');
        assertStartsWith(false, "java", 'J');

        assertStartsWith(false, null, 'j');
    }

    public void assertIndexOf(Integer exp, String str, Character ch) {
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, exp, StringExt.indexOf(str, ch));
    }

    public void testIndexOf() {
        assertIndexOf(2,    "abc", 'c');
        assertIndexOf(1   , "abc", 'b');
        assertIndexOf(0,    "abc", 'a');
        assertIndexOf(null, "abc", 'd');

        assertIndexOf(2,    "abcdc", 'c');

        assertIndexOf(null, "abc", null);
        assertIndexOf(null, null,  'a');
        assertIndexOf(null, null,  null);
    }

    public void assertContains(boolean exp, String str, Character ch) {
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, exp, StringExt.contains(str, ch));
    }

    public void testContains() {
        assertContains(true, "abc", 'a');
        assertContains(true, "abc", 'b');
        assertContains(true, "abc", 'c');
        assertContains(false, "abc", 'd');
        assertContains(false, null, 'd');
        assertContains(false, "abc", null);
        assertContains(false, null, null);
    }

    public void assertSubstringAfter(String exp, String str, Character ch) {
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, exp, StringExt.substringAfter(str, ch));
    }

    public void testSubstringAfter() {
        assertSubstringAfter("bc", "abc", 'a');
        assertSubstringAfter("c",  "abc", 'b');
        assertSubstringAfter("",   "abc", 'c');
        assertSubstringAfter(null, "abc", 'd');

        assertSubstringAfter("ebeef", "cafebeef", 'f');
        assertSubstringAfter("beef",  "cafebeef", 'e');
    }

    public void assertSubstringBefore(String exp, String str, Character ch) {
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, exp, StringExt.substringBefore(str, ch));
    }

    public void testSubstringBefore() {
        assertSubstringBefore("ab", "abc", 'c');
        assertSubstringBefore("a",  "abc", 'b');
        assertSubstringBefore("",   "abc", 'a');
        assertSubstringBefore(null, "abc", 'd');

        assertSubstringBefore(null, "abc", null);
        assertSubstringBefore(null, null,  'a');
        assertSubstringBefore(null, null,  null);
    }

    public void assertEq(Boolean exp, String a, String b) {
        String msg = "a: '" + a + "'; b: '" + b + "'";
        assertEquals(msg, exp, StringExt.eq(a, b));
    }

    public void testEq() {
        assertEq(true,  "a", "a");
        assertEq(true,  "abc", "abc");
        assertEq(false, "a", "b");
        assertEq(false, "b", "a");
        assertEq(false, "a", "A");
        assertEq(false, "A", "a");
        assertEq(false, "abC", "abc");
        assertEq(false, "a", null);
        assertEq(false, null, "a");
        assertEq(true,  null, null);
    }

    public void assertEqi(Boolean exp, String a, String b) {
        String msg = "a: '" + a + "'; b: '" + b + "'";
        assertEquals(msg, exp, StringExt.eqi(a, b));
    }

    public void testEqi() {
        assertEqi(true,  "a",  "a");
        assertEqi(false, "a",  "b");
        assertEqi(false, "b",  "a");
        assertEqi(true,  "a",  "A");
        assertEqi(false, "a",  null);
        assertEqi(false, null, "a");
        assertEqi(true,  null, null);
    }

    public void assertSnip(String exp, String str, int len) {
        String msg = "str: '" + str + "'; len: " + len;
        assertEquals(msg, exp, StringExt.snip(str, len));
    }

    public void testSnip() {
        assertSnip("this", "this", 5);
        assertSnip("this-", "this is a test", 5);
        assertSnip("", "this is a test", -1);
    }
}
