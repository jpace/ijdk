package org.incava.ijdk.lang;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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

    // the unquoting functionality is in StringExt, but not Str:
    
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

    // get

    public Character assertGet(Character expected, String str, int index) {
        Character result = StringExt.get(str, index);
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

    // startsWith

    public boolean assertStartsWith(boolean expected, String str, char ch) {
        boolean result = StringExt.startsWith(str, ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // indexOf

    public Integer assertIndexOf(Integer expected, String str, Character ch) {
        Integer result = StringExt.indexOf(str, ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // contains

    public boolean assertContains(boolean expected, String str, Character ch) {
        boolean result = StringExt.contains(str, ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // substringAfter

    public String assertSubstringAfter(String expected, String str, Character ch) {
        String result = StringExt.substringAfter(str, ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // substringBefore

    public String assertSubstringBefore(String expected, String str, Character ch) {
        String result = StringExt.substringBefore(str, ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // eq

    public Boolean assertEq(Boolean expected, String a, String b) {
        Boolean result = StringExt.eq(a, b);
        String msg = "a: '" + a + "'; b: '" + b + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // eqi

    public Boolean assertEqi(Boolean expected, String a, String b) {
        Boolean result = StringExt.eqi(a, b);
        String msg = "a: '" + a + "'; b: '" + b + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // snip

    public String assertSnip(String expected, String str, int length) {
        String result = StringExt.snip(str, length);
        String msg = "str: '" + str + "'; length: " + length;
        assertEquals(msg, expected, result);
        return result;
    }

    // isEmpty

    public boolean assertIsEmpty(boolean expected, String str) {
        boolean result = StringExt.isEmpty(str);
        String msg = "str: '" + str + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // length

    public int assertLength(int expected, String str) {
        int result = StringExt.length(str);
        String msg = "str: '" + str + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // chomp

    public String assertChomp(String expected, String str) {
        String result = StringExt.chomp(str);
        String msg = "str: '" + str + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // chompAll

    public String assertChompAll(String expected, String str) {
        String result = StringExt.chompAll(str);
        String msg = "str: '" + str + "'";
        assertEquals(msg, expected, result);
        return result;
    }
    
    // unquote
    
    public String assertUnquote(String expected, String str) {
        String result = StringExt.unquote(str);
        assertEquals("str: '" + str + "'", expected, result);
        return result;
    }

    // quote
    
    public String assertQuote(String expected, String str) {
        String result = StringExt.quote(str);
        assertEquals("str: '" + str + "'", expected, result);
        return result;
    }    
}
