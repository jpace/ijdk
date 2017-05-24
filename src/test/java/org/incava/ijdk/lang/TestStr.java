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
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

@RunWith(JUnitParamsRunner.class)
public class TestStr extends StringTest {
    public String[] split(String str, char delim, int max) {
        return new Str(str).split(delim, max);
    }

    public String[] split(String str, String delim, int max) {
        return new Str(str).split(delim, max);
    }

    public List<String> toList(String str) {
        return new Str(str).toList();
    }

    public String pad(String str, char ch, int length) {
        return new Str(str).pad(ch, length);
    }

    public String pad(String str, int length) {
        return new Str(str).pad(length);
    }    

    public String padLeft(String str, char ch, int length) {
        return new Str(str).padLeft(ch, length);
    }    

    public String padLeft(String str, int length) {
        return new Str(str).padLeft(length);
    }    

    public String repeat(String str, int length) {
        return new Str(str).repeat(length);
    }    

    public String repeat(char ch, int length) {
        return new Str(ch).repeat(length);
    }

    public String left(String str, int length) {
        return new Str(str).left(length);
    }

    public String right(String str, int length) {
        return new Str(str).right(length);
    }

    // join

    public String assertJoin(String expected, String[] ary, String delim) {
        String result = Str.join(ary, delim).str();
        return assertEqual(expected, result, message("ary", ary, "delim", delim));
    }

    public String assertJoin(String expected, Collection<String> coll, String delim) {
        String result = Str.join(coll, delim).str();
        return assertEqual(expected, result, message("coll", coll, "delim", delim));
    }

    // charAt

    public Character assertCharAt(Character expected, String str, int index) {
        Character result = new Str(str).charAt(index);
        return assertEqual(expected, result, message("str", str, "index", index));
    }    

    // get

    public Character assertGet(Character expected, String str, int index) {
        Character result = new Str(str).get(index);
        return assertEqual(expected, result, message("str", str, "index", index));
    }    

    @Test
    @Parameters
    public void getIndex(Integer expected, String str, int index) {
        Integer result = new Str(str).getIndex(index);
        assertEqual(expected, result, message("str", str, "index", index));
    }
    
    private java.util.List<Object[]> parametersForGetIndex() {
        return paramsList(params(null, null, 0),
                          params(0, "abcd", 0),
                          params(1, "abcd", 1),
                          params(3, "abcd", 3),
                          params(null, "abcd", 4),
                          params(3, "abcd", -1),
                          params(2, "abcd", -2),
                          params(1, "abcd", -3),
                          params(null, "abcd", -5));
    }

    // substring

    public String assertSubstring(String expected, String str, Integer fromIndex, Integer toIndex) {
        String result = new Str(str).substring(fromIndex, toIndex);
        return assertEqual(expected, result, message("str", str, "fromIndex", fromIndex, "toIndex", toIndex));
    }

    // startsWith

    public boolean assertStartsWith(boolean expected, String str, char ch) {
        boolean result = new Str(str).startsWith(ch);
        return assertEqual(expected, result, message("str", str, "ch", ch));
    }

    // indexOf

    public Integer assertIndexOf(Integer expected, String str, Character ch) {
        Integer result = new Str(str).indexOf(ch);
        return assertEqual(expected, result, message("str", str, "ch", ch));
    }    

    // contains

    public boolean assertContains(boolean expected, String str, Character ch) {
        boolean result = new Str(str).contains(ch);
        return assertEqual(expected, result, message("str", str, "ch", ch));
    }

    // substringAfter

    public String assertSubstringAfter(String expected, String str, Character ch) {
        String result = new Str(str).substringAfter(ch);
        return assertEqual(expected, result, message("str", str, "ch", ch));
    }

    // substringBefore

    public String assertSubstringBefore(String expected, String str, Character ch) {
        String result = new Str(str).substringBefore(ch);
        return assertEqual(expected, result, message("str", str, "ch", ch));
    }

    // eq

    public Boolean assertEq(Boolean expected, String a, String b) {
        Boolean result = new Str(a).eq(b);
        return assertEqual(expected, result, message("a", a, "b", b));
    }

    // eqi

    public Boolean assertEqi(Boolean expected, String a, String b) {
        Boolean result = new Str(a).eqi(b);
        return assertEqual(expected, result, message("a", a, "b", b));
    }

    // snip

    public String assertSnip(String expected, String str, int length) {
        String result = new Str(str).snip(length);
        return assertEqual(expected, result, message("str", str, "length", length));
    }

    // isEmpty

    public boolean assertIsEmpty(boolean expected, String str) {
        boolean result = new Str(str).isEmpty();
        return assertEqual(expected, result, message("str", str));
    }

    // length

    public int assertLength(int expected, String str) {
        int result = new Str(str).length();
        return assertEqual(expected, result, message("str", str));
    }

    // chomp

    public String assertChomp(String expected, String str) {
        String result = new Str(str).chomp();
        return assertEqual(expected, result, message("str", str));
    }

    // chompAll

    public String assertChompAll(String expected, String str) {
        String result = new Str(str).chompAll();
        return assertEqual(expected, result, message("str", str));
    }
    
    // unquote
    
    public String assertUnquote(String expected, String str) {
        String result = new Str(str).unquote();
        return assertEqual(expected, result, message("str", str));
    }

    // quote
    
    public String assertQuote(String expected, String str) {
        String result = new Str(str).quote();
        return assertEqual(expected, result, message("str", str));
    }

    @Test
    @Parameters
    public void equalsString(boolean expected, String a, String b) {
        Str sa = new Str(a);
        boolean result = sa.equals(b);
        assertEqual(expected, result);
    }
    
    private java.util.List<Object[]> parametersForEqualsString() {
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

    // equals Str

    public Boolean assertEqualStr(Boolean expected, String a, String b) {
        Boolean result = new Str(a).equals(new Str(b));
        return assertEqual(expected, result, message("a", a, "b", b));
    }

    @Test
    public void testEqualsStrNullEmptyString() {
        assertEqualStr(false, null, "");
    }

    @Test
    public void testEqualsStrEmptyStringNull() {
        assertEqualStr(false, "", null);
    }

    @Test
    public void testEqualsStrNullNull() {
        assertEqualStr(true, null, null);
    }

    @Test
    public void testEqualsStrNullNonEmptyString() {
        assertEqualStr(false, null, "a");
    }

    @Test
    public void testEqualsStrNonEmptyStringNull() {
        assertEqualStr(false, "a", null);
    }

    @Test
    public void testEqualsStrCharCharMatch() {
        assertEqualStr(true, "a", "a");
    }

    @Test
    public void testEqualsStrCharCharNoMatch() {
        assertEqualStr(false, "a", "b");
    }

    @Test
    public void testEqualsStrCharCharMismatchedCase() {
        assertEqualStr(false, "a", "A");
    }

    @Test
    public void testEqualsStrDifferentLengths() {
        assertEqualStr(false, "a", "ab");
    }

    @Test
    public void testEndsWith() {
        assertEqual(true, new Str("abc").endsWith("c"));
        assertEqual(true, new Str("abc").endsWith("bc"));
        assertEqual(true, new Str("abc").endsWith("abc"));
        //$$$ fix this -- it's inconsistent with String#endsWith:
        assertEqual(false, new Str("abc").endsWith(""));
        assertEqual(false, new Str(null).endsWith(""));
        assertEqual(false, new Str("abc").endsWith("d"));

        assertEqual(true, new Str("abc").endsWith('c'));
        assertEqual(false, new Str("abc").endsWith(' '));
        assertEqual(false, new Str(null).endsWith('c'));
        assertEqual(false, new Str("abc").endsWith('d'));
    }

    @Test
    public void testCompareTo() {
        assertEqual(true, new Str("abc").compareTo(new Str("abc")) == 0);
        assertEqual(true, new Str("abc").compareTo(new Str("def")) < 0);
        assertEqual(true, new Str("def").compareTo(new Str("abc")) > 0);
        assertEqual(true, new Str("abc").compareTo(new Str(null)) > 0);
        assertEqual(true, new Str(null).compareTo(new Str(null)) == 0);
        assertEqual(true, new Str(null).compareTo(new Str("abc")) < 0);
    }

    @Test
    public void testHashCode() {
        assertEqual("abc".hashCode(), new Str("abc").hashCode());
        assertEqual(0, new Str(null).hashCode());
    }
}
