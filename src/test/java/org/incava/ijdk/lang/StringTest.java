package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.test.Parameterized;
import org.junit.Test;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

public abstract class StringTest extends Parameterized {
    public abstract String[] split(String str, char delim, int max);

    public abstract String[] split(String str, String delim, int max);    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
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

    public abstract Integer indexOf(String str, Character ch);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void indexOf(Integer expected, String str, Character ch) {
        Integer result = indexOf(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }
    
    private List<Object[]> parametersForIndexOf() {
        return paramsList(params(null, "abc", null),
                          params(null, null,  'a'),
                          params(null, null,  null),
                          params(0, "abc", 'a'),
                          params(2, "abc", 'c'),
                          params(null, "abc", 'd'),
                          params(null, "abc", 'A'));
    }

    // contains

    public abstract boolean contains(String str, Character ch);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void contains(boolean expected, String str, Character ch) {
        boolean result = contains(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }
    
    private List<Object[]> parametersForContains() {
        return paramsList(params(false, "abc", null),
                          params(false, null,  'a'),
                          params(false, null,  null),
                          params(true, "abc", 'a'),
                          params(true, "abc", 'c'),
                          params(false, "abc", 'd'),
                          params(false, "abc", 'A'));
    }

    // substringAfter

    public abstract String substringAfter(String str, Character ch);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void substringAfter(String expected, String str, Character ch) {
        String result = substringAfter(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }
    
    private List<Object[]> parametersForSubstringAfter() {
        return paramsList(params(null, "abc", null),
                          params(null, null,  'a'),
                          params(null, null,  null),
                          params("bc", "abc", 'a'),
                          params("", "abc", 'c'),
                          params(null, "abc", 'd'),
                          params(null, "abc", 'A'));
    }

    // substringBefore

    public abstract String substringBefore(String str, Character ch);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void substringBefore(String expected, String str, Character ch) {
        String result = substringBefore(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }
    
    private List<Object[]> parametersForSubstringBefore() {
        return paramsList(params(null, "abc", null),
                          params(null, null,  'a'),
                          params(null, null,  null),
                          params("", "abc", 'a'),
                          params("ab", "abc", 'c'),
                          params(null, "abc", 'd'),
                          params(null, "abc", 'A'));
    }

    // eq

    public abstract Boolean eq(String a, String b);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void eq(Boolean expected, String a, String b) {
        Boolean result = eq(a, b);
        assertEqual(expected, result, message("a", a, "b", b));
    }
    
    private List<Object[]> parametersForEq() {
        return paramsList(params(false, null, ""),
                          params(false, "", null),
                          params(true, null, null),
                          params(false, null, "a"),
                          params(false, "a", null),
                          params(true, "a", "a"),
                          params(false, "a", "b"),
                          params(false, "a", "A"),
                          params(false, "a", "ab"));
    }

    // eqi

    public abstract Boolean eqi(String a, String b);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void eqi(Boolean expected, String a, String b) {
        Boolean result = eqi(a, b);
        assertEqual(expected, result, message("a", a, "b", b));
    }
    
    private List<Object[]> parametersForEqi() {
        return paramsList(params(false, null, ""),
                          params(false, "", null),
                          params(true, null, null),
                          params(false, null, "a"),
                          params(false, "a", null),
                          params(true, "a", "a"),
                          params(false, "a", "b"),
                          params(true, "A", "a"),
                          params(false, "a", "ab"));
    }

    // snip

    public abstract String snip(String str, int length);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void snip(String expected, String str, int length) {
        String result = snip(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }
    
    private List<Object[]> parametersForSnip() {
        return paramsList(params(null, null, 3),
                          params("", "", 3),
                          params("-", "abc", 1),
                          params("a-", "abc", 2),
                          params("abc", "abc", 3),
                          params("ab-", "abcd", 3));
    }

    // isEmpty

    public abstract boolean isEmpty(String str);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void isEmpty(boolean expected, String str) {
        boolean result = isEmpty(str);
        assertEqual(expected, result, message("str", str));
    }
    
    private List<Object[]> parametersForIsEmpty() {
        return paramsList(params(true, null),
                          params(true, ""),
                          params(false, "a"));
    }

    // length

    public abstract int length(String str);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void length(int expected, String str) {
        int result = length(str);
        assertEqual(expected, result, message("str", str));
    }
    
    private List<Object[]> parametersForLength() {
        return paramsList(params(0, null),
                          params(0, ""),
                          params(1, "a"));
    }

    // chomp

    public abstract String chomp(String str);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void chomp(String expected, String str) {
        String result = chomp(str);
        assertEqual(expected, result, message("str", str));
    }
    
    private List<Object[]> parametersForChomp() {
        return paramsList(params(null, null),
                          params("", ""),
                          params("a", "a"),
                          params("a", "a\n"),
                          params("a\n", "a\n\n"),
                          params("a", "a\r"),
                          params("a\r", "a\r\r"),
                          params("a\r", "a\r\n"));
    }

    // chompAll

    public abstract String chompAll(String str);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void chompAll(String expected, String str) {
        String result = chompAll(str);
        assertEqual(expected, result, message("str", str));
    }
    
    private List<Object[]> parametersForChompAll() {
        return paramsList(params(null, null),
                          params("", ""),
                          params("a", "a"),
                          params("a", "a\n"),
                          params("a", "a\n\n"),
                          params("a", "a\r"),
                          params("a", "a\r\r"),
                          params("a", "a\r\n"));
    }
    
    // unquote
    
    public abstract String unquote(String str);    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void unquote(String expected, String str) {
        String result = unquote(str);
        assertEqual(expected, result, message("str", str));
    }
    
    private List<Object[]> parametersForUnquote() {
        return paramsList(params(null, null),
                          params("", ""),
                          params("abc", "abc"),
                          params("\'abc\"", "\'abc\""),
                          params("\"abc\'", "\"abc\'"),
                          params("abc", "\'abc\'"),
                          params("abc", "\"abc\""));
    }
    
    // quote
    
    public abstract String quote(String str);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void quote(String expected, String str) {
        String result = quote(str);
        assertEqual(expected, result, message("str", str));
    }
    
    private List<Object[]> parametersForQuote() {
        return paramsList(params(null, null),
                          params("\"\"", ""),
                          params("\"abc\"", "abc"));
    }
}
