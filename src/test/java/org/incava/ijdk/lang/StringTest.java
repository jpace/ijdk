package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import junit.framework.TestCase;
import org.junit.Test;

public abstract class StringTest {
    // split

    public abstract String[] assertSplit(String[] expected, String str, char delim, int max);

    public abstract String[] assertSplit(String[] expected, String str, String delim, int max);

    public void testSplitNull() {
        assertSplit(null, null, ';', -1);
    }

    @Test
    public void testSplitSingleChar() {
        assertSplit(new String[] { "this", "is", "a", "test" }, "this;is;a;test", ';', -1);
    }

    @Test
    public void testSplitStringOneChar() {
        assertSplit(new String[] { "this", "is", "a", "test" }, "this;is;a;test", ";", -1);
    }
    
    @Test
    public void testSplitStringWithWhitespace() {
        assertSplit(new String[] { "this", "is", "a", "test" }, "this ; is ; a ; test", " ; ", -1);
    }

    @Test
    public void testSplitStringCharEmptyBlock() {
        assertSplit(new String[] { "this", "is", "a", "", "test" }, "this;is;a;;test", ';', -1);
    }

    @Test
    public void testSplitStringCharEmptyBlockMaxOne() {
        assertSplit(new String[] { "this;is;a;;test" }, "this;is;a;;test", ';', 1);
    }
    
    @Test
    public void testSplitStringCharEmptyBlockMaxTwo() {
        assertSplit(new String[] { "this", "is;a;;test" }, "this;is;a;;test", ';', 2);
    }

    @Test
    public void testSplitStringCharEmptyBlockMaxThree() {   
        assertSplit(new String[] { "this", "is", "a;;test" }, "this;is;a;;test", ';', 3);
    }
        
    @Test
    public void testSplitStringCharEmptyBlockMaxFour() {
        assertSplit(new String[] { "this", "is", "a", ";test" }, "this;is;a;;test", ';', 4);
    }

    // toList
    
    public abstract void assertToList(String[] exp, String str);
        
    @Test
    public void testToListNull() {
        assertToList(null, null);
    }
    
    @Test
    public void testToListDefault() {
        String[] expected = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expected, "fee, fi, foo, fum");
    }
    
    @Test
    public void testToListWhitespaceChars() {
        String[] expected = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expected, "fee,\tfi,\nfoo,\rfum");
    }
    
    // pad

    public abstract String assertPad(String expected, String str, char ch, int length);

    public abstract String assertPad(String expected, String str, int length);

    @Test
    public void testPadCharNull() {
        assertPad(null, null, '*', 8);
    }
    
    @Test
    public void testPadCharDefault() {
        assertPad("abcd****", "abcd", '*', 8);
    }
    
    @Test
    public void testPadCharShorter() {
        assertPad("abcd", "abcd", '*', 3);
    }
    
    @Test
    public void testPadCharAtLength() {
        assertPad("abcd", "abcd", '*', 4);
    }
    
    @Test
    public void testPadCharOneChar() {
        assertPad("abcd*", "abcd", '*', 5);
    }

    @Test
    public void testPadSpaceNull() {
        assertPad(null, null, 8);
    }    
    
    @Test
    public void testPadSpaceDefault() {
        assertPad("abcd    ", "abcd", 8);
    }
    
    @Test
    public void testPadSpaceShorter() {
        assertPad("abcd", "abcd", 3);
    }
    
    @Test
    public void testPadSpaceAtLength() {
        assertPad("abcd", "abcd", 4);
    }
    
    @Test
    public void testPadSpaceOneChar() {
        assertPad("abcd ", "abcd", 5);
    }

    // padLeft

    public abstract String assertPadLeft(String expected, String str, char ch, int length);

    public abstract String assertPadLeft(String expected, String str, int length);
    
    @Test
    public void testPadLeftCharNull() {
        assertPadLeft(null, null, '*', 8);
    }
    
    @Test
    public void testPadLeftCharDefault() {
        assertPadLeft("****abcd", "abcd", '*', 8);
    }
    
    @Test
    public void testPadLeftCharShorter() {
        assertPadLeft("abcd", "abcd", '*', 3);
    }
    
    @Test
    public void testPadLeftCharAtLength() {
        assertPadLeft("abcd", "abcd", '*', 4);
    }
    
    @Test
    public void testPadLeftCharOneChar() {
        assertPadLeft("*abcd", "abcd", '*', 5);
    }
    
    @Test
    public void testPadLeftSpaceNull() {
        assertPadLeft(null, null, 8);
    }
    
    @Test
    public void testPadLeftSpaceDefault() {
        assertPadLeft("    abcd", "abcd", 8);
    }
    
    @Test
    public void testPadLeftSpaceShorter() {
        assertPadLeft("abcd", "abcd", 3);
    }
    
    @Test
    public void testPadLeftSpaceAtLength() {
        assertPadLeft("abcd", "abcd", 4);
     }
    
    @Test
    public void testPadLeftSpaceOneChar() {
       assertPadLeft(" abcd", "abcd", 5);
    }
    
    // repeat

    public abstract String assertRepeat(String expected, String str, int length);

    public abstract String assertRepeat(String expected, char ch, int length);

    @Test
    public void testRepeatStringNull() {
        assertRepeat("", null,  0);
    }

    @Test
    public void testRepeatStringNegative() {
        assertRepeat("", "abcd",  -1);
    }

    @Test
    public void testRepeatStringZero() {
        assertRepeat("", "abcd",  0);
    }

    @Test
    public void testRepeatStringOne() {
        assertRepeat("abcd", "abcd",  1);
    }

    @Test
    public void testRepeatStringTwo() {
        assertRepeat("abcdabcd", "abcd",  2);
    }

    @Test
    public void testRepeatCharNegative() {
        assertRepeat("", 'a', -1);
    }

    @Test
    public void testRepeatCharZero() {
        assertRepeat("", 'a', 0);
    }

    @Test
    public void testRepeatCharOne() {
        assertRepeat("a", 'a', 1);
    }

    @Test
    public void testRepeatCharTwo() {
        assertRepeat("aa", 'a', 2);
    }

    // left

    public abstract String assertLeft(String expected, String str, int length);

    @Test
    public void testLeftNull() {
        assertLeft(null, null,  1);
    }

    @Test
    public void testLeftLonger() {
        assertLeft("abcd", "abcdefgh", 4);
    }

    @Test
    public void testLeftAtLimit() {
        assertLeft("abcd", "abcd", 4);
    }

    @Test
    public void testLeftShorter() {
        assertLeft("abcd", "abcd", 5);
    }

    @Test
    public void testLeftZero() {
        assertLeft("", "abcd", 0);
    }

    @Test
    public void testLeftNegative() {
        assertLeft("", "abcd", -1);
    }

    // right

    public abstract String assertRight(String expected, String str, int length);

    @Test
    public void testRightNull() {
        assertRight(null, null,  1);
    }

    @Test
    public void testRightLonger() {
        assertRight("efgh", "abcdefgh", 4);
    }

    @Test
    public void testRightAtLimit() {
        assertRight("abcd", "abcd", 4);
    }

    @Test
    public void testRightShorter() {
        assertRight("abcd", "abcd", 5);
    }

    @Test
    public void testRightZero() {
        assertRight("", "abcd", 0);
    }

    @Test
    public void testRightNegative() {
        assertRight("", "abcd", -1);
    }

    // join

    public abstract String assertJoin(String expected, String[] ary, String delim);

    public abstract String assertJoin(String expected, Collection<String> coll, String delim);

    @Test
    public void testJoinArrayNull() {
        assertJoin(null, (String[])null, ",");
    }

    @Test
    public void testJoinArrayDelimNull() {
        assertJoin("abcd", new String[] { "a", "b", "c", "d" }, null);
    }

    @Test
    public void testJoinArrayDelimEmpty() {
        assertJoin("abcd", new String[] { "a", "b", "c", "d" }, "");
    }

    @Test
    public void testJoinArrayEmpty() {
        assertJoin("", new String[] { "" }, ",");
    }

    @Test
    public void testJoinArrayNotEmpty() {
        assertJoin("a,b,c,d", new String[] { "a", "b", "c", "d" }, ",");
    }

    @Test
    public void testJoinCollectionNull() {
        assertJoin(null, (ArrayList<String>)null, ",");
    }

    @Test
    public void testJoinCollectionDelimNull() {
        assertJoin("abcd", Arrays.asList("a", "b", "c", "d"), null);
    }

    @Test
    public void testJoinCollectionDelimEmpty() {
        assertJoin("abcd", Arrays.asList("a", "b", "c", "d"), "");
    }

    @Test
    public void testJoinCollectionEmpty() {
        assertJoin("", new ArrayList<String>(), ",");
    }

    @Test
    public void testJoinCollectionNotEmpty() {
        assertJoin("a,b,c,d", Arrays.asList("a", "b", "c", "d"), ",");
    }

    // charAt

    public abstract Character assertCharAt(Character expected, String str, int index);
    
    @Test
    public void testCharAtNullString() {
        assertCharAt(null, null, 0);
    }
    
    @Test
    public void testCharAtZero() {
        assertCharAt('a', "abc", 0);
    }
    
    @Test
    public void testCharAtOne() {
        assertCharAt('b', "abc", 1);
    }
    
    @Test
    public void testCharAtEnd() {
        assertCharAt('c', "abc", 2);
    }
    
    @Test
    public void testCharAtPastRange() {
        assertCharAt(null, "abc", 3);
    }
    
    @Test
    public void testCharAtNegativeOne() {
        assertCharAt('c', "abc", -1);
    }
    
    @Test
    public void testCharAtNegativeTwo() {
        assertCharAt('b', "abc", -2);
    }
    
    @Test
    public void testCharAtNegativeAtStart() {
        assertCharAt('a', "abc", -3);
    }
    
    @Test
    public void testCharAtNegativeBeforeRange() {
        assertCharAt(null, "abc", -4);
    }

    // get

    public abstract Character assertGet(Character expected, String str, int index);
    
    @Test
    public void testGetNullString() {
        assertGet(null, null, 0);
    }
    
    @Test
    public void testGetZero() {
        assertGet('a', "abc", 0);
    }
    
    @Test
    public void testGetOne() {
        assertGet('b', "abc", 1);
    }
    
    @Test
    public void testGetEnd() {
        assertGet('c', "abc", 2);
    }
    
    @Test
    public void testGetPastRange() {
        assertGet(null, "abc", 3);
    }
    
    @Test
    public void testGetNegativeOne() {
        assertGet('c', "abc", -1);
    }
    
    @Test
    public void testGetNegativeTwo() {
        assertGet('b', "abc", -2);
    }
    
    @Test
    public void testGetNegativeAtStart() {
        assertGet('a', "abc", -3);
    }
    
    @Test
    public void testGetNegativeBeforeRange() {
        assertGet(null, "abc", -4);
    }

    // substring

    public abstract String assertSubstring(String expected, String str, Integer fromIndex, Integer toIndex);

    @Test
    public void testSubstringNull() {
        assertSubstring(null, null, 1, 0);
    }

    @Test
    public void testSubstringPositiveFull() {
        assertSubstring("abcd", "abcd", 0, 3);
    }

    @Test
    public void testSubstringPositiveFromLessThanTo() {
        assertSubstring("abc", "abcd", 0, 2);
    }

    @Test
    public void testSubstringPositiveFromEqualsTo() {
        assertSubstring("a", "abcd", 0, 0);
    }

    @Test
    public void testSubstringPositiveFromGreaterThanTo() {
        // expect "", not null, per Ruby behavior
        assertSubstring("", "abcd", 1, 0);
    }

    @Test
    public void testSubstringPositiveFromPastEnd() {
        assertSubstring("", "abcd", 4, 5);
    }
    
    @Test
    public void testSubstringNegativeFull() {
        assertSubstring("abcd", "abcd", -4, -1);
    }
    
    @Test
    public void testSubstringNegativeFromLessThanTo() {
        assertSubstring("abc", "abcd", -4, -2);
    }    
    
    @Test
    public void testSubstringNegativeFromEqualsTo() {
        assertSubstring("a", "abcd", -4, -4);
    }
    
    @Test
    public void testSubstringNegativeFromGreaterThanTo() {
        // expect "", not null, per Ruby behavior
        assertSubstring("", "abcd", -1, -2);
    }

    @Test
    public void testSubstringNullFrom() {
        // null == first in string
        assertSubstring("abc", "abcd", null, -2);
    }

    @Test
    public void testSubstringNullTo() {
        assertSubstring("cd", "abcd", -2, null);
    }

    @Test
    public void testSubstringNullFromNullTo() {
        assertSubstring("abcd", "abcd", null, null);
    }

    // startsWith

    public abstract boolean assertStartsWith(boolean expected, String str, char ch);

    @Test
    public void testStartsWithNull() {
        assertStartsWith(false, null, 'j');
    }

    @Test
    public void testStartsWithMatch() {
        assertStartsWith(true, "java", 'j');
    }

    @Test
    public void testStartsWithNoMatch() {
        assertStartsWith(false, "java", 'a');
    }

    @Test
    public void testStartsWithMismatchedCase() {
        assertStartsWith(false, "java", 'J');
    }

    // indexOf

    public abstract Integer assertIndexOf(Integer expected, String str, Character ch);
    
    @Test
    public void testIndexOfNullCharacter() {
        assertIndexOf(null, "abc", null);
    }
    
    @Test
    public void testIndexOfNullString() {
        assertIndexOf(null, null,  'a');
    }
    
    @Test
    public void testIndexOfBothNull() {
        assertIndexOf(null, null,  null);
    }
    
    @Test
    public void testIndexOfFirstChar() {
        assertIndexOf(0, "abc", 'a');
    }
    
    @Test
    public void testIndexOfLastChar() {
        assertIndexOf(2, "abc", 'c');
    }

    @Test
    public void testIndexOfNoMatch() {
        assertIndexOf(null, "abc", 'd');
    }

    @Test
    public void testIndexOfMismatchedCase() {
        assertIndexOf(null, "abc", 'A');
    }

    // contains

    public abstract boolean assertContains(boolean expected, String str, Character ch);
    
    @Test
    public void testContainsNullCharacter() {
        assertContains(false, "abc", null);
    }
    
    @Test
    public void testContainsNullString() {
        assertContains(false, null,  'a');
    }
    
    @Test
    public void testContainsBothNull() {
        assertContains(false, null,  null);
    }
    
    @Test
    public void testContainsFirstChar() {
        assertContains(true, "abc", 'a');
    }
    
    @Test
    public void testContainsLastChar() {
        assertContains(true, "abc", 'c');
    }

    @Test
    public void testContainsNoMatch() {
        assertContains(false, "abc", 'd');
    }    

    @Test
    public void testContainsMismatchedCase() {
        assertContains(false, "abc", 'A');
    }

    // substringAfter

    public abstract String assertSubstringAfter(String expected, String str, Character ch);
    
    @Test
    public void testSubstringAfterNullCharacter() {
        assertSubstringAfter(null, "abc", null);
    }
    
    @Test
    public void testSubstringAfterNullString() {
        assertSubstringAfter(null, null,  'a');
    }
    
    @Test
    public void testSubstringAfterBothNull() {
        assertSubstringAfter(null, null,  null);
    }
    
    @Test
    public void testSubstringAfterFirstChar() {
        assertSubstringAfter("bc", "abc", 'a');
    }
    
    @Test
    public void testSubstringAfterLastChar() {
        assertSubstringAfter("", "abc", 'c');
    }

    @Test
    public void testSubstringAfterNoMatch() {
        assertSubstringAfter(null, "abc", 'd');
    }    

    @Test
    public void testSubstringAfterMismatchedCase() {
        assertSubstringAfter(null, "abc", 'A');
    }

    // substringBefore

    public abstract String assertSubstringBefore(String expected, String str, Character ch);

    @Test
    public void testSubstringBeforeNullCharacter() {
        assertSubstringBefore(null, "abc", null);
    }
    
    @Test
    public void testSubstringBeforeNullString() {
        assertSubstringBefore(null, null,  'a');
    }
    
    @Test
    public void testSubstringBeforeBothNull() {
        assertSubstringBefore(null, null,  null);
    }
    
    @Test
    public void testSubstringBeforeFirstChar() {
        assertSubstringBefore("", "abc", 'a');
    }
    
    @Test
    public void testSubstringBeforeLastChar() {
        assertSubstringBefore("ab", "abc", 'c');
    }

    @Test
    public void testSubstringBeforeNoMatch() {
        assertSubstringBefore(null, "abc", 'd');
    }    

    @Test
    public void testSubstringBeforeMismatchedCase() {
        assertSubstringBefore(null, "abc", 'A');
    }

    // eq

    public abstract Boolean assertEq(Boolean expected, String a, String b);

    @Test
    public void testEqNullEmptyString() {
        assertEq(false, null, "");
    }

    @Test
    public void testEqEmptyStringNull() {
        assertEq(false, "", null);
    }

    @Test
    public void testEqNullNull() {
        assertEq(true, null, null);
    }

    @Test
    public void testEqNullNonEmptyString() {
        assertEq(false, null, "a");
    }

    @Test
    public void testEqNonEmptyStringNull() {
        assertEq(false, "a", null);
    }

    @Test
    public void testEqCharCharMatch() {
        assertEq(true, "a", "a");
    }

    @Test
    public void testEqCharCharNoMatch() {
        assertEq(false, "a", "b");
    }

    @Test
    public void testEqCharCharMismatchedCase() {
        assertEq(false, "a", "A");
    }

    @Test
    public void testEqDifferentLengths() {
        assertEq(false, "a", "ab");
    }

    // eqi

    public abstract Boolean assertEqi(Boolean expected, String a, String b);

    @Test
    public void testEqiNullEmptyString() {
        assertEqi(false, null, "");
    }

    @Test
    public void testEqiEmptyStringNull() {
        assertEqi(false, "", null);
    }

    @Test
    public void testEqiNullNull() {
        assertEqi(true, null, null);
    }

    @Test
    public void testEqiNullNonEmptyString() {
        assertEqi(false, null, "a");
    }

    @Test
    public void testEqiNonEmptyStringNull() {
        assertEqi(false, "a", null);
    }

    @Test
    public void testEqiCharCharMatch() {
        assertEqi(true, "a", "a");
    }

    @Test
    public void testEqiCharCharNoMatch() {
        assertEqi(false, "a", "b");
    }

    @Test
    public void testEqiCharCharMismatchedCase() {
        assertEqi(true, "A", "a");
    }

    @Test
    public void testEqiDifferentLengths() {
        assertEqi(false, "a", "ab");
    }

    // snip

    public abstract String assertSnip(String expected, String str, int length);

    @Test
    public void testSnipNull() {
        assertSnip(null, null, 3);
    }

    @Test
    public void testSnipEmpty() {
        assertSnip("", "", 3);
    }

    @Test
    public void testSnipToEmpty() {
        assertSnip("-", "abc", 1);
    }

    @Test
    public void testSnipLongerAtEnd() {
        assertSnip("a-", "abc", 2);
    }

    @Test
    public void testSnipAtLength() {
        assertSnip("abc", "abc", 3);
    }

    @Test
    public void testSnipTwoLonger() {
        assertSnip("ab-", "abcd", 3);
    }

    // isEmpty

    public abstract boolean assertIsEmpty(boolean expected, String str);

    @Test
    public void testIsEmptyNull() {
        assertIsEmpty(true, null);
    }

    @Test
    public void testIsEmptyEmpty() {
        assertIsEmpty(true, "");
    }

    @Test
    public void testIsEmptyNonEmpty() {
        assertIsEmpty(false, "a");
    }

    // length

    public abstract int assertLength(int expected, String str);

    @Test
    public void testLengthNull() {
        assertLength(0, null);
    }

    @Test
    public void testLengthEmpty() {
        assertLength(0, "");
    }

    @Test
    public void testLengthNonEmpty() {
        assertLength(1, "a");
    }

    // chomp

    public abstract String assertChomp(String expected, String str);

    @Test
    public void testChompNull() {
        assertChomp(null, null);
    }

    @Test
    public void testChompEmpty() {
        assertChomp("", "");
    }

    @Test
    public void testChompNoEoln() {
        assertChomp("a", "a");
    }

    @Test
    public void testChompN() {
        assertChomp("a", "a\n");
    }

    @Test
    public void testChompNN() {
        assertChomp("a\n", "a\n\n");
    }

    @Test
    public void testChompR() {
        assertChomp("a", "a\r");
    }

    @Test
    public void testChompRR() {
        assertChomp("a\r", "a\r\r");
    }

    @Test
    public void testChompRN() {
        assertChomp("a\r", "a\r\n");
    }

    // chompAll

    public abstract String assertChompAll(String expected, String str);

    @Test
    public void testChompAllNull() {
        assertChompAll(null, null);
    }

    @Test
    public void testChompAllEmpty() {
        assertChompAll("", "");
    }

    @Test
    public void testChompAllNoEoln() {
        assertChompAll("a", "a");
    }

    @Test
    public void testChompAllN() {
        assertChompAll("a", "a\n");
    }

    @Test
    public void testChompAllNN() {
        assertChompAll("a", "a\n\n");
    }

    @Test
    public void testChompAllR() {
        assertChompAll("a", "a\r");
    }

    @Test
    public void testChompAllRR() {
        assertChompAll("a", "a\r\r");
    }

    @Test
    public void testChompAllRN() {
        assertChompAll("a", "a\r\n");
    }    
    
    // unquote
    
    public abstract String assertUnquote(String expected, String str);
    
    @Test
    public void testUnquoteNull() {
        assertUnquote(null, null);
    }
    
    @Test
    public void testUnquoteEmpty() {
        assertUnquote("", "");
    }
    
    @Test
    public void testUnquoteNeitherQuote() {
        assertUnquote("abc", "abc");
    }
    
    @Test
    public void testUnquoteSingleDouble() {
        assertUnquote("\'abc\"", "\'abc\"");
    }
    
    @Test
    public void testUnquoteDoubleSingle() {
        assertUnquote("\"abc\'", "\"abc\'");
    }
    
    @Test
    public void testUnquoteSingleSingle() {
        assertUnquote("abc", "\'abc\'");
    }
    
    @Test
    public void testUnquoteDoubleDouble() {
        assertUnquote("abc", "\"abc\"");
    }
    
    // quote
    
    public abstract String assertQuote(String expected, String str);
    
    @Test
    public void testQuoteNull() {
        assertQuote(null, null);
    }
    
    @Test
    public void testQuoteEmpty() {
        assertQuote("\"\"", "");
    }
    
    @Test
    public void testQuoteNotEmpty() {
        assertQuote("\"abc\"", "abc");
    }
}
