package org.incava.ijdk.lang;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.incava.ijdk.lang.StringTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;

@RunWith(JUnitParamsRunner.class)
public class TestStringExt extends StringTest {
    // split

    public String[] assertSplit(String[] expected, String str, char delim, int max) {
        String[] result = StringExt.split(str, delim, max);
        return assertEqual(expected, result, message("str", str, "delim", delim, "max", max));
    }

    public String[] assertSplit(String[] expected, String str, String delim, int max) {
        String[] result = StringExt.split(str, delim, max);
        return assertEqual(expected, result, message("str", str, "delim", delim, "max", max));
    }

    // toList
    
    public void assertToList(String[] exp, String str) {
        List<String> result = StringExt.toList(str);
        assertEqual(exp == null ? null : Arrays.asList(exp), result, message("str", str));
    }

    // the unquoting functionality is in StringExt, but not Str:

    @Test
    public void testToListDoubleQuoted() {
        String[] expected = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expected, "\"fee, fi, foo, fum\"");
    }
    
    @Test
    public void testToListSingleQuoted() {
        String[] expected = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expected, "\'fee, fi, foo, fum\'");
    }
    
    @Test
    public void testToListSingleQuotedWhitespaceChars() {
        String[] expected = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expected, "\'fee,\tfi,\nfoo,\rfum\'");
    }
    
    // pad

    public String assertPad(String expected, String str, char ch, int length) {
        String result = StringExt.pad(str, ch, length);
        return assertEqual(expected, result, message("str", str, "ch", ch, "length", length));
    }

    public String assertPad(String expected, String str, int length) {
        String result = StringExt.pad(str, length);
        return assertEqual(expected, result, message("str", str, "length", length));
    }

    // padLeft

    public String assertPadLeft(String expected, String str, char ch, int length) {
        String result = StringExt.padLeft(str, ch, length);
        return assertEqual(expected, result, message("str", str, "ch", ch, "length", length));
    }

    public String assertPadLeft(String expected, String str, int length) {
        String result = StringExt.padLeft(str, length);
        return assertEqual(expected, result, message("str", str, "length", length));
    }

    // repeat

    public String assertRepeat(String expected, String str, int length) {
        String result = StringExt.repeat(str, length);
        return assertEqual(expected, result, message("str", str, "length", length));
    }

    public String assertRepeat(String expected, char ch, int length) {
        String result = StringExt.repeat(ch, length);
        return assertEqual(expected, result, message("ch", ch, "length", length));
    }

    // left

    public String assertLeft(String expected, String str, int length) {
        String result = StringExt.left(str, length);
        return assertEqual(expected, result, message("str", str, "length", length));
    }

    // right

    public String assertRight(String expected, String str, int length) {
        String result = StringExt.right(str, length);
        return assertEqual(expected, result, message("str", str, "length", length));
    }

    // join

    public String assertJoin(String expected, String[] ary, String delim) {
        String result = StringExt.join(ary, delim);
        return assertEqual(expected, result, message("ary", ary, "delim", delim));
    }

    public String assertJoin(String expected, Collection<String> coll, String delim) {
        String result = StringExt.join(coll, delim);
        return assertEqual(expected, result, message("coll", coll, "delim", delim));
    }

    // charAt

    public Character assertCharAt(Character expected, String str, int index) {
        Character result = StringExt.charAt(str, index);
        return assertEqual(expected, result, message("str", str, "index", index));
    }

    // get

    public Character assertGet(Character expected, String str, int index) {
        Character result = StringExt.get(str, index);
        return assertEqual(expected, result, message("str", str, "index", index));
    }

    // substring

    public String assertSubstring(String expected, String str, Integer fromIndex, Integer toIndex) {
        String result = StringExt.substring(str, fromIndex, toIndex);
        return assertEqual(expected, result, message("str", str, "fromIndex", fromIndex, "toIndex", toIndex));
    }

    // startsWith

    public boolean assertStartsWith(boolean expected, String str, char ch) {
        boolean result = StringExt.startsWith(str, ch);
        return assertEqual(expected, result, message("str", str, "ch", ch));
    }

    // indexOf

    public Integer assertIndexOf(Integer expected, String str, Character ch) {
        Integer result = StringExt.indexOf(str, ch);
        return assertEqual(expected, result, message("str", str, "ch", ch));
    }

    // contains

    public boolean assertContains(boolean expected, String str, Character ch) {
        boolean result = StringExt.contains(str, ch);
        return assertEqual(expected, result, message("str", str, "ch", ch));
    }

    // substringAfter

    public String assertSubstringAfter(String expected, String str, Character ch) {
        String result = StringExt.substringAfter(str, ch);
        return assertEqual(expected, result, message("str", str, "ch", ch));
    }

    // substringBefore

    public String assertSubstringBefore(String expected, String str, Character ch) {
        String result = StringExt.substringBefore(str, ch);
        return assertEqual(expected, result, message("str", str, "ch", ch));
    }

    // eq

    public Boolean assertEq(Boolean expected, String a, String b) {
        Boolean result = StringExt.eq(a, b);
        return assertEqual(expected, result, message("a", a, "b", b));
    }

    // eqi

    public Boolean assertEqi(Boolean expected, String a, String b) {
        Boolean result = StringExt.eqi(a, b);
        return assertEqual(expected, result, message("a", a, "b", b));
    }

    // snip

    public String assertSnip(String expected, String str, int length) {
        String result = StringExt.snip(str, length);
        return assertEqual(expected, result, message("str", str, "length", length));
    }

    // isEmpty

    public boolean assertIsEmpty(boolean expected, String str) {
        boolean result = StringExt.isEmpty(str);
        return assertEqual(expected, result, message("str", str));
    }

    // length

    public int assertLength(int expected, String str) {
        int result = StringExt.length(str);
        return assertEqual(expected, result, message("str", str));
    }

    // chomp

    public String assertChomp(String expected, String str) {
        String result = StringExt.chomp(str);
        return assertEqual(expected, result, message("str", str));
    }

    // chompAll

    public String assertChompAll(String expected, String str) {
        String result = StringExt.chompAll(str);
        return assertEqual(expected, result, message("str", str));
    }
    
    // unquote
    
    public String assertUnquote(String expected, String str) {
        String result = StringExt.unquote(str);
        return assertEqual(expected, result, message("str", str));
    }

    // quote
    
    public String assertQuote(String expected, String str) {
        String result = StringExt.quote(str);
        return assertEqual(expected, result, message("str", str));
    }    
}
