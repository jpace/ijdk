package org.incava.ijdk.lang;

import ijdk.lang.ICore;
import java.util.*;
import org.incava.test.TestCaseExt;
import org.junit.Assert;

public class TestStringExt extends StringTest {
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

    // toList
    
    public void assertToList(String[] exp, String str) {
        List<String> result = StringExt.toList(str);
        assertEquals("str: '" + str + "'", exp == null ? null : Arrays.asList(exp), result);
    }
    
    public void testToListDoubleQuoted() {
        String[] expected = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expected, "\"fee, fi, foo, fum\"");
    }
    
    public void testToListSingleQuoted() {
        String[] expected = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expected, "\'fee, fi, foo, fum\'");
    }
    
    public void testToListSingleQuotedWhitespaceChars() {
        String[] expected = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expected, "\'fee,\tfi,\nfoo,\rfum\'");
    }

    // assertions
    
    public String assertEquals(String expected, String result, String str, Character ch) {
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
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

    // left

    public String assertLeft(String expected, String str, int length) {
        String result = StringExt.left(str, length);
        assertEquals("str: '" + str + "'; length: " + length, expected, result);
        return result;        
    }

    // right

    public String assertRight(String expected, String str, int length) {
        String result = StringExt.right(str, length);
        assertEquals("str: '" + str + "'; length: " + length, expected, result);
        return result;        
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

    // charAt

    public Character assertCharAt(Character expected, String str, int index) {
        Character result = StringExt.charAt(str, index);
        assertEquals("str: '" + str + "'; index: " + index, expected, result);
        return result;
    }

    // substring

    public String assertSubstring(String expected, String str, Integer fromIndex, Integer toIndex) {
        String result = StringExt.substring(str, fromIndex, toIndex);
        String msg    = "\"" + str + "\"[" + fromIndex + " ... " + toIndex + "]";
        assertEquals(msg, expected, result);
        return result;
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

    // startsWith

    public boolean assertStartsWith(boolean expected, String str, char ch) {
        boolean result = StringExt.startsWith(str, ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }

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

    public Integer assertIndexOf(Integer expected, String str, Character ch) {
        Integer result = StringExt.indexOf(str, ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }
    
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

    public boolean assertContains(boolean expected, String str, Character ch) {
        boolean result = StringExt.contains(str, ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }
    
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

    public String assertSubstringAfter(String expected, String str, Character ch) {
        String result = StringExt.substringAfter(str, ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }
    
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

    public String assertSubstringBefore(String expected, String str, Character ch) {
        String result = StringExt.substringBefore(str, ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }

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

    public Boolean assertEq(Boolean expected, String a, String b) {
        Boolean result = StringExt.eq(a, b);
        String msg = "a: '" + a + "'; b: '" + b + "'";
        assertEquals(msg, expected, result);
        return result;
    }

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

    public Boolean assertEqi(Boolean expected, String a, String b) {
        Boolean result = StringExt.eqi(a, b);
        String msg = "a: '" + a + "'; b: '" + b + "'";
        assertEquals(msg, expected, result);
        return result;
    }

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

    public String assertSnip(String expected, String str, int length) {
        String result = StringExt.snip(str, length);
        String msg = "str: '" + str + "'; length: " + length;
        assertEquals(msg, expected, result);
        return result;
    }

    public void testSnipNull() {
        assertSnip(null, null, 3);
    }

    public void testSnipEmpty() {
        assertSnip("", "", 3);
    }

    public void testSnipLongerAtStart() {
        assertSnip("a-", "abc", 1);
    }

    public void testSnipLongerAtEnd() {
        assertSnip("ab-", "abc", 2);
    }

    public void testSnipAtLength() {
        assertSnip("abc", "abc", 3);
    }

    // isEmpty

    public boolean assertIsEmpty(boolean expected, String str) {
        boolean result = StringExt.isEmpty(str);
        String msg = "str: '" + str + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    public void testIsEmptyNull() {
        assertIsEmpty(true, null);
    }

    public void testIsEmptyEmpty() {
        assertIsEmpty(true, "");
    }

    public void testIsEmptyNonEmpty() {
        assertIsEmpty(false, "a");
    }

    // chomp

    public String assertChomp(String expected, String str) {
        String result = StringExt.chomp(str);
        String msg = "str: '" + str + "'";
        assertEquals(msg, expected, result);
        return result;
    }

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

    public String assertChompAll(String expected, String str) {
        String result = StringExt.chompAll(str);
        String msg = "str: '" + str + "'";
        assertEquals(msg, expected, result);
        return result;
    }

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
}
