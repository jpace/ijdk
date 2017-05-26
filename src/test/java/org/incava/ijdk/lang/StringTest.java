package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junit.framework.TestCase;
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
public abstract class StringTest {
    public abstract String[] split(String str, char delim, int max);

    public abstract String[] split(String str, String delim, int max);    

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void splitCharDelim(String[] expected, String str, char delim, int max) {
        String[] result = split(str, delim, max);
        assertEqual(expected, result, message("str", str, "delim", delim, "max", max));
    }
    
    private List<Object[]> parametersForSplitCharDelim() {
        return paramsList(params(null, null, ';', -1),
                          params(new String[] { "this", "is", "a", "test" }, "this;is;a;test", ';', -1),
                          params(new String[] { "this", "is", "a", "", "test" }, "this;is;a;;test", ';', -1),
                          params(new String[] { "this", "is;a;;test" }, "this;is;a;;test", ';', 2),
                          params(new String[] { "this", "is", "a;;test" }, "this;is;a;;test", ';', 3),
                          params(new String[] { "this", "is", "a", ";test" }, "this;is;a;;test", ';', 4));
    }

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void splitStringDelim(String[] expected, String str, String delim, int max) {
        String[] result = split(str, delim, max);
        assertEqual(expected, result, message("str", str, "delim", delim, "max", max));
    }
    
    private List<Object[]> parametersForSplitStringDelim() {
        return paramsList(params(null, null, ";", -1),
                          params(new String[] { "this", "is", "a", "test" }, "this;is;a;test", ";", -1),
                          params(new String[] { "this", "is", "a", "test" }, "this ; is ; a ; test", " ; ", -1));
    }

    public abstract List<String> toList(String str);

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void toList(String[] expected, String str) {
        List<String> result = toList(str);
        assertEqual(expected == null ? null : Arrays.asList(expected), result, message("str", str));
    }
    
    private List<Object[]> parametersForToList() {
        return paramsList(params(null, null),
                          params(new String[] { "fee", "fi", "foo", "fum" }, "fee, fi, foo, fum"),
                          params(new String[] { "fee", "fi", "foo", "fum" }, "fee,\tfi,\nfoo,\rfum"));
    }
    
    // pad

    public abstract String pad(String str, char ch, int length);

    public abstract String pad(String str, int length);

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void padWithChar(String expected, String str, char ch, int length) {
        String result = pad(str, ch, length);
        assertEqual(expected, result, message("str", str, "ch", ch, "length", length));
    }
    
    private List<Object[]> parametersForPadWithChar() {
        return paramsList(params(null, null, '*', 8),
                          params("abcd****", "abcd", '*', 8),
                          params("abcd", "abcd", '*', 3),
                          params("abcd", "abcd", '*', 4),
                          params("abcd*", "abcd", '*', 5));
    }    

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void padWithoutChar(String expected, String str, int length) {
        String result = pad(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }
    
    private List<Object[]> parametersForPadWithoutChar() {
        return paramsList(params("abcd    ", "abcd", 8),
                          params("abcd", "abcd", 3),
                          params("abcd", "abcd", 4),
                          params("abcd ", "abcd", 5));
    }

    // padLeft

    public abstract String padLeft(String str, char ch, int length);

    public abstract String padLeft(String str, int length);

    public String assertPadLeft(String expected, String str, char ch, int length) {
        String result = padLeft(str, ch, length);
        return assertEqual(expected, result, message("str", str, "ch", ch, "length", length));
    }

    public String assertPadLeft(String expected, String str, int length) {
        String result = padLeft(str, length);
        return assertEqual(expected, result, message("str", str, "length", length));
    }

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void padLeftWithChar(String expected, String str, char ch, int length) {
        String result = padLeft(str, ch, length);
        assertEqual(expected, result, message("str", str, "ch", ch, "length", length));
    }
    
    private List<Object[]> parametersForPadLeftWithChar() {
        return paramsList(params(null, null, '*', 8),
                          params("****abcd", "abcd", '*', 8),
                          params("abcd", "abcd", '*', 3),
                          params("abcd", "abcd", '*', 4),
                          params("*abcd", "abcd", '*', 5));
    }

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void padLeftWithoutChar(String expected, String str, int length) {
        String result = padLeft(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }
    
    private List<Object[]> parametersForPadLeftWithoutChar() {
        return paramsList(params(null, null, 8),
                          params("    abcd", "abcd", 8),
                          params("abcd", "abcd", 3),
                          params("abcd", "abcd", 4),
                          params(" abcd", "abcd", 5));
    }
    
    // repeat

    public abstract String repeat(String str, int length);

    public abstract String repeat(char ch, int length);

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void repeatString(String expected, String str, int length) {
        String result = repeat(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }
    
    private List<Object[]> parametersForRepeatString() {
        return paramsList(params("", null,  0),
                          params("", "abcd",  -1),
                          params("", "abcd",  0),
                          params("abcd", "abcd",  1),
                          params("abcdabcd", "abcd",  2));
    }

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void repeatChar(String expected, char ch, int length) {
        String result = repeat(ch, length);
        assertEqual(expected, result, message("ch", ch, "length", length));
    }
    
    private List<Object[]> parametersForRepeatChar() {
        return paramsList(params("", 'a', -1),
                          params("", 'a', 0),
                          params("a", 'a', 1),
                          params("aa", 'a', 2));
    }

    // left

    public abstract String left(String str, int length);

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void left(String expected, String str, int length) {
        String result = left(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }
    
    private List<Object[]> parametersForLeft() {
        return paramsList(params(null, null,  1),
                          params("abcd", "abcdefgh", 4),
                          params("abcd", "abcd", 4),
                          params("abcd", "abcd", 5),
                          params("", "abcd", 0),
                          params("", "abcd", -1));
    }

    // right

    public abstract String right(String str, int length);

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void right(String expected, String str, int length) {
        String result = right(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }
    
    private List<Object[]> parametersForRight() {
        return paramsList(params(null, null,  1),
                          params("efgh", "abcdefgh", 4),
                          params("abcd", "abcd", 4),
                          params("abcd", "abcd", 5),
                          params("", "abcd", 0),
                          params("", "abcd", -1));
    }

    // join

    public abstract String join(String[] ary, String delim);

    public abstract String join(Collection<String> coll, String delim);

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void joinArray(String expected, String[] ary, String delim) {
        String result = join(ary, delim);
        assertEqual(expected, result, message("ary", ary, "delim", delim));
    }
    
    private List<Object[]> parametersForJoinArray() {
        return paramsList(params(null, (String[])null, ","),
                          params("abcd", new String[] { "a", "b", "c", "d" }, null),
                          params("abcd", new String[] { "a", "b", "c", "d" }, ""),
                          params("", new String[] { "" }, ","),
                          params("a,b,c,d", new String[] { "a", "b", "c", "d" }, ","));
    }

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void joinCollection(String expected, Collection<String> coll, String delim) {
        String result = join(coll, delim);
        assertEqual(expected, result, message("coll", coll, "delim", delim));
    }
    
    private List<Object[]> parametersForJoinCollection() {
        return paramsList(params(null, (ArrayList<String>)null, ","),
                          params("abcd", Arrays.asList("a", "b", "c", "d"), null),
                          params("abcd", Arrays.asList("a", "b", "c", "d"), ""),
                          params("", new ArrayList<String>(), ","),
                          params("a,b,c,d", Arrays.asList("a", "b", "c", "d"), ","));
    }

    // charAt
    
    public abstract Character charAt(String str, int index);

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void charAt(Character expected, String str, int index) {
        Character result = charAt(str, index);
        assertEqual(expected, result, message("str", str, "index", index));
    }
    
    private List<Object[]> parametersForCharAt() {
        return paramsList(params(null, null, 0),
                          params('a', "abc", 0),
                          params('b', "abc", 1),
                          params('c', "abc", 2),
                          params(null, "abc", 3),
                          params('c', "abc", -1),
                          params('b', "abc", -2),
                          params('a', "abc", -3),
                          params(null, "abc", -4));
    }

    // get

    public abstract Character get(String str, int index);

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void get(Character expected, String str, int index) {
        Character result = get(str, index);
        assertEqual(expected, result, message("str", str, "index", index));
    }
    
    private List<Object[]> parametersForGet() {
        return paramsList(params(null, null, 0),
                          params('a', "abc", 0),
                          params('b', "abc", 1),
                          params('c', "abc", 2),
                          params(null, "abc", 3),
                          params('c', "abc", -1),
                          params('b', "abc", -2),
                          params('a', "abc", -3),
                          params(null, "abc", -4));
    }

    // substring

    public abstract String substring(String str, Integer fromIndex, Integer toIndex);

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void substring(String expected, String str, Integer fromIndex, Integer toIndex) {
        String result = substring(str, fromIndex, toIndex);
        assertEqual(expected, result, message("str", str, "fromIndex", fromIndex, "toIndex", toIndex));
    }
    
    private List<Object[]> parametersForSubstring() {
        return paramsList(params(null, null, 1, 0),
                          params("abcd", "abcd", 0, 3),
                          params("abc", "abcd", 0, 2),
                          params("a", "abcd", 0, 0),
                          // expect "", not null, per Ruby behavior
                          params("", "abcd", 1, 0),
                          params("", "abcd", 4, 5),
                          params("abcd", "abcd", -4, -1),
                          params("abc", "abcd", -4, -2),
                          params("a", "abcd", -4, -4),
                          // expect "", not null, per Ruby behavior
                          params("", "abcd", -1, -2),
                          // null == first in string
                          params("abc", "abcd", null, -2),
                          params("cd", "abcd", -2, null),
                          params("abcd", "abcd", null, null));
    }

    // startsWith

    public abstract boolean startsWith(String str, char ch);

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void startsWith(boolean expected, String str, char ch) {
        boolean result = startsWith(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }
    
    private List<Object[]> parametersForStartsWith() {
        return paramsList(params(false, null, 'j'),
                          params(true, "java", 'j'),
                          params(false, "java", 'a'),
                          params(false, "java", 'J'));
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
