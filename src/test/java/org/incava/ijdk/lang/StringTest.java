package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
        assertJoin("abcd", Arrays.asList("a", "b", "c", "d"), null);
    }

    public void testJoinCollectionDelimEmpty() {
        assertJoin("abcd", Arrays.asList("a", "b", "c", "d"), "");
    }

    public void testJoinCollectionEmpty() {
        assertJoin("", new ArrayList<String>(), ",");
    }

    public void testJoinCollectionNotEmpty() {
        assertJoin("a,b,c,d", Arrays.asList("a", "b", "c", "d"), ",");
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

    // get

    public abstract Character assertGet(Character expected, String str, int index);
    
    public void testGetNullString() {
        assertGet(null, null, 0);
    }
    
    public void testGetZero() {
        assertGet('a', "abc", 0);
    }
    
    public void testGetOne() {
        assertGet('b', "abc", 1);
    }
    
    public void testGetEnd() {
        assertGet('c', "abc", 2);
    }
    
    public void testGetPastRange() {
        assertGet(null, "abc", 3);
    }
    
    public void testGetNegativeOne() {
        assertGet('c', "abc", -1);
    }
    
    public void testGetNegativeTwo() {
        assertGet('b', "abc", -2);
    }
    
    public void testGetNegativeAtStart() {
        assertGet('a', "abc", -3);
    }
    
    public void testGetNegativeBeforeRange() {
        assertGet(null, "abc", -4);
    }

    // substring

    public abstract String assertSubstring(String expected, String str, Integer fromIndex, Integer toIndex);

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

    // startsWith

    public abstract boolean assertStartsWith(boolean expected, String str, char ch);

    public void testStartsWithNull() {
        assertStartsWith(false, null, 'j');
    }

    public void testStartsWithMatch() {
        assertStartsWith(true, "java", 'j');
    }

    public void testStartsWithNoMatch() {
        assertStartsWith(false, "java", 'a');
    }

    public void testStartsWithMismatchedCase() {
        assertStartsWith(false, "java", 'J');
    }

    // indexOf

    public abstract Integer assertIndexOf(Integer expected, String str, Character ch);
    
    public void testIndexOfNullCharacter() {
        assertIndexOf(null, "abc", null);
    }
    
    public void testIndexOfNullString() {
        assertIndexOf(null, null,  'a');
    }
    
    public void testIndexOfBothNull() {
        assertIndexOf(null, null,  null);
    }
    
    public void testIndexOfFirstChar() {
        assertIndexOf(0, "abc", 'a');
    }
    
    public void testIndexOfLastChar() {
        assertIndexOf(2, "abc", 'c');
    }

    public void testIndexOfNoMatch() {
        assertIndexOf(null, "abc", 'd');
    }

    public void testIndexOfMismatchedCase() {
        assertIndexOf(null, "abc", 'A');
    }

    // contains

    public abstract boolean assertContains(boolean expected, String str, Character ch);
    
    public void testContainsNullCharacter() {
        assertContains(false, "abc", null);
    }
    
    public void testContainsNullString() {
        assertContains(false, null,  'a');
    }
    
    public void testContainsBothNull() {
        assertContains(false, null,  null);
    }
    
    public void testContainsFirstChar() {
        assertContains(true, "abc", 'a');
    }
    
    public void testContainsLastChar() {
        assertContains(true, "abc", 'c');
    }

    public void testContainsNoMatch() {
        assertContains(false, "abc", 'd');
    }    

    public void testContainsMismatchedCase() {
        assertContains(false, "abc", 'A');
    }

    // substringAfter

    public abstract String assertSubstringAfter(String expected, String str, Character ch);
    
    public void testSubstringAfterNullCharacter() {
        assertSubstringAfter(null, "abc", null);
    }
    
    public void testSubstringAfterNullString() {
        assertSubstringAfter(null, null,  'a');
    }
    
    public void testSubstringAfterBothNull() {
        assertSubstringAfter(null, null,  null);
    }
    
    public void testSubstringAfterFirstChar() {
        assertSubstringAfter("bc", "abc", 'a');
    }
    
    public void testSubstringAfterLastChar() {
        assertSubstringAfter("", "abc", 'c');
    }

    public void testSubstringAfterNoMatch() {
        assertSubstringAfter(null, "abc", 'd');
    }    

    public void testSubstringAfterMismatchedCase() {
        assertSubstringAfter(null, "abc", 'A');
    }

    // substringBefore

    public abstract String assertSubstringBefore(String expected, String str, Character ch);

    public void testSubstringBeforeNullCharacter() {
        assertSubstringBefore(null, "abc", null);
    }
    
    public void testSubstringBeforeNullString() {
        assertSubstringBefore(null, null,  'a');
    }
    
    public void testSubstringBeforeBothNull() {
        assertSubstringBefore(null, null,  null);
    }
    
    public void testSubstringBeforeFirstChar() {
        assertSubstringBefore("", "abc", 'a');
    }
    
    public void testSubstringBeforeLastChar() {
        assertSubstringBefore("ab", "abc", 'c');
    }

    public void testSubstringBeforeNoMatch() {
        assertSubstringBefore(null, "abc", 'd');
    }    

    public void testSubstringBeforeMismatchedCase() {
        assertSubstringBefore(null, "abc", 'A');
    }

    // eq

    public abstract Boolean assertEq(Boolean expected, String a, String b);

    public void testEqNullEmptyString() {
        assertEq(false, null, "");
    }

    public void testEqEmptyStringNull() {
        assertEq(false, "", null);
    }

    public void testEqNullNull() {
        assertEq(true, null, null);
    }

    public void testEqNullNonEmptyString() {
        assertEq(false, null, "a");
    }

    public void testEqNonEmptyStringNull() {
        assertEq(false, "a", null);
    }

    public void testEqCharCharMatch() {
        assertEq(true, "a", "a");
    }

    public void testEqCharCharNoMatch() {
        assertEq(false, "a", "b");
    }

    public void testEqCharCharMismatchedCase() {
        assertEq(false, "a", "A");
    }

    public void testEqDifferentLengths() {
        assertEq(false, "a", "ab");
    }

    // eqi

    public abstract Boolean assertEqi(Boolean expected, String a, String b);

    public void testEqiNullEmptyString() {
        assertEqi(false, null, "");
    }

    public void testEqiEmptyStringNull() {
        assertEqi(false, "", null);
    }

    public void testEqiNullNull() {
        assertEqi(true, null, null);
    }

    public void testEqiNullNonEmptyString() {
        assertEqi(false, null, "a");
    }

    public void testEqiNonEmptyStringNull() {
        assertEqi(false, "a", null);
    }

    public void testEqiCharCharMatch() {
        assertEqi(true, "a", "a");
    }

    public void testEqiCharCharNoMatch() {
        assertEqi(false, "a", "b");
    }

    public void testEqiCharCharMismatchedCase() {
        assertEqi(true, "A", "a");
    }

    public void testEqiDifferentLengths() {
        assertEqi(false, "a", "ab");
    }

    // snip

    public abstract String assertSnip(String expected, String str, int length);

    public void testSnipNull() {
        assertSnip(null, null, 3);
    }

    public void testSnipEmpty() {
        assertSnip("", "", 3);
    }

    public void testSnipToEmpty() {
        assertSnip("-", "abc", 1);
    }

    public void testSnipLongerAtEnd() {
        assertSnip("a-", "abc", 2);
    }

    public void testSnipAtLength() {
        assertSnip("abc", "abc", 3);
    }

    public void testSnipTwoLonger() {
        assertSnip("ab-", "abcd", 3);
    }

    // isEmpty

    public abstract boolean assertIsEmpty(boolean expected, String str);

    public void testIsEmptyNull() {
        assertIsEmpty(true, null);
    }

    public void testIsEmptyEmpty() {
        assertIsEmpty(true, "");
    }

    public void testIsEmptyNonEmpty() {
        assertIsEmpty(false, "a");
    }

    // length

    public abstract int assertLength(int expected, String str);

    public void testLengthNull() {
        assertLength(0, null);
    }

    public void testLengthEmpty() {
        assertLength(0, "");
    }

    public void testLengthNonEmpty() {
        assertLength(1, "a");
    }

    // chomp

    public abstract String assertChomp(String expected, String str);

    public void testChompNull() {
        assertChomp(null, null);
    }

    public void testChompEmpty() {
        assertChomp("", "");
    }

    public void testChompNoEoln() {
        assertChomp("a", "a");
    }

    public void testChompN() {
        assertChomp("a", "a\n");
    }

    public void testChompNN() {
        assertChomp("a\n", "a\n\n");
    }

    public void testChompR() {
        assertChomp("a", "a\r");
    }

    public void testChompRR() {
        assertChomp("a\r", "a\r\r");
    }

    public void testChompRN() {
        assertChomp("a\r", "a\r\n");
    }

    // chompAll

    public abstract String assertChompAll(String expected, String str);

    public void testChompAllNull() {
        assertChompAll(null, null);
    }

    public void testChompAllEmpty() {
        assertChompAll("", "");
    }

    public void testChompAllNoEoln() {
        assertChompAll("a", "a");
    }

    public void testChompAllN() {
        assertChompAll("a", "a\n");
    }

    public void testChompAllNN() {
        assertChompAll("a", "a\n\n");
    }

    public void testChompAllR() {
        assertChompAll("a", "a\r");
    }

    public void testChompAllRR() {
        assertChompAll("a", "a\r\r");
    }

    public void testChompAllRN() {
        assertChompAll("a", "a\r\n");
    }    
    
    // unquote
    
    public abstract String assertUnquote(String expected, String str);
    
    public void testUnquoteNull() {
        assertUnquote(null, null);
    }
    
    public void testUnquoteEmpty() {
        assertUnquote("", "");
    }
    
    public void testUnquoteNeitherQuote() {
        assertUnquote("abc", "abc");
    }
    
    public void testUnquoteSingleDouble() {
        assertUnquote("\'abc\"", "\'abc\"");
    }
    
    public void testUnquoteDoubleSingle() {
        assertUnquote("\"abc\'", "\"abc\'");
    }
    
    public void testUnquoteSingleSingle() {
        assertUnquote("abc", "\'abc\'");
    }
    
    public void testUnquoteDoubleDouble() {
        assertUnquote("abc", "\"abc\"");
    }
    
    // quote
    
    public abstract String assertQuote(String expected, String str);
    
    public void testQuoteNull() {
        assertQuote(null, null);
    }
    
    public void testQuoteEmpty() {
        assertQuote("\"\"", "");
    }
    
    public void testQuoteNotEmpty() {
        assertQuote("\"abc\"", "abc");
    }
}
