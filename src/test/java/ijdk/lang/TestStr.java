package ijdk.lang;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.incava.ijdk.lang.StringTest;
import org.junit.Assert;
import org.junit.Test;

import static org.incava.test.Assertions.*;

public class TestStr extends StringTest {
    public TestStr(String name) {
        super(name);
    }

    // split

    public String[] assertSplit(String[] expected, String str, char delim, int max) {
        String[] result = new Str(str).split(delim, max);
        Assert.assertArrayEquals("str: '" + str + "'; delim: '" + delim + "'; max: " + max, expected, result);
        return result;
    }

    public String[] assertSplit(String[] expected, String str, String delim, int max) {
        String[] result = new Str(str).split(delim, max);
        Assert.assertArrayEquals("str: '" + str + "'; delim: '" + delim + "'; max: " + max, expected, result);
        return result;
    }

    // toList
    
    public void assertToList(String[] exp, String str) {
        List<String> result = new Str(str).toList();
        assertEquals("str: '" + str + "'", exp == null ? null : Arrays.asList(exp), result);
    }

    // assertions
    
    public String assertEquals(String expected, String result, String str, Character ch) {
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // pad

    public String assertPad(String expected, String str, char ch, int length) {
        String result = new Str(str).pad(ch, length);
        assertEquals("str: '" + str + "'; ch: " + ch + "; length: " + length, expected, result);
        return result;
    }

    public String assertPad(String expected, String str, int length) {
        String result = new Str(str).pad(length);
        assertEquals("str: '" + str + "'; length: " + length, expected, result);
        return result;
    }

    // padLeft

    public String assertPadLeft(String expected, String str, char ch, int length) {
        String result = new Str(str).padLeft(ch, length);
        assertEquals("str: '" + str + "'; ch: " + ch + "; length: " + length, expected, result);
        return result;
    }

    public String assertPadLeft(String expected, String str, int length) {
        String result = new Str(str).padLeft(length);
        assertEquals("str: '" + str + "'; length: " + length, expected, result);
        return result;
    }

    // repeat

    public String assertRepeat(String expected, String str, int length) {
        String result = new Str(str).repeat(length);
        assertEquals("str: '" + str + "'; length: " + length, expected, result);
        return result;
    }

    public String assertRepeat(String expected, char ch, int length) {
        String result = new Str(ch).repeat(length);
        assertEquals("ch: '" + ch + "'; length: " + length, expected, result);
        return result;
    }

    // left

    public String assertLeft(String expected, String str, int length) {
        String result = new Str(str).left(length);
        assertEquals("str: '" + str + "'; length: " + length, expected, result);
        return result;        
    }

    // right

    public String assertRight(String expected, String str, int length) {
        String result = new Str(str).right(length);
        assertEquals("str: '" + str + "'; length: " + length, expected, result);
        return result;        
    }

    // join

    public String assertJoin(String expected, String[] ary, String delim) {
        String result = Str.join(ary, delim).str();
        assertEquals(ary == null ? null : Arrays.asList(ary).toString(), expected, result);
        return result;
    }

    public String assertJoin(String expected, Collection<String> coll, String delim) {
        String result = Str.join(coll, delim).str();
        assertEquals(coll == null ? null : coll.toString(), expected, result);
        return result;
    }

    // charAt

    public Character assertCharAt(Character expected, String str, int index) {
        Character result = new Str(str).charAt(index);
        assertEquals("str: '" + str + "'; index: " + index, expected, result);
        return result;
    }    

    // get

    public Character assertGet(Character expected, String str, int index) {
        Character result = new Str(str).get(index);
        assertEquals("str: '" + str + "'; index: " + index, expected, result);
        return result;
    }
    
    // getIndex

    public Integer assertGetIndex(Integer exp, String str, int index) {
        Integer result = new Str(str).getIndex(index);
        assertEquals("str: '" + str + "'; index: " + index, exp, result);
        return result;
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

    // substring

    public String assertSubstring(String expected, String str, Integer fromIndex, Integer toIndex) {
        String result = new Str(str).substring(fromIndex, toIndex);
        String msg    = "\"" + str + "\"[" + fromIndex + " ... " + toIndex + "]";
        assertEquals(msg, expected, result);
        return result;
    }

    // startsWith

    public boolean assertStartsWith(boolean expected, String str, char ch) {
        boolean result = new Str(str).startsWith(ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // indexOf

    public Integer assertIndexOf(Integer expected, String str, Character ch) {
        Integer result = new Str(str).indexOf(ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }    

    // contains

    public boolean assertContains(boolean expected, String str, Character ch) {
        boolean result = new Str(str).contains(ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // substringAfter

    public String assertSubstringAfter(String expected, String str, Character ch) {
        String result = new Str(str).substringAfter(ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // substringBefore

    public String assertSubstringBefore(String expected, String str, Character ch) {
        String result = new Str(str).substringBefore(ch);
        String msg = "str: '" + str + "'; ch: '" + ch + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // eq

    public Boolean assertEq(Boolean expected, String a, String b) {
        Boolean result = new Str(a).eq(b);
        String msg = "a: '" + a + "'; b: '" + b + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // eqi

    public Boolean assertEqi(Boolean expected, String a, String b) {
        Boolean result = new Str(a).eqi(b);
        String msg = "a: '" + a + "'; b: '" + b + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // snip

    public String assertSnip(String expected, String str, int length) {
        String result = new Str(str).snip(length);
        String msg = "str: '" + str + "'; length: " + length;
        assertEquals(msg, expected, result);
        return result;
    }

    // isEmpty

    public boolean assertIsEmpty(boolean expected, String str) {
        boolean result = new Str(str).isEmpty();
        String msg = "str: '" + str + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // length

    public int assertLength(int expected, String str) {
        int result = new Str(str).length();
        String msg = "str: '" + str + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // chomp

    public String assertChomp(String expected, String str) {
        String result = new Str(str).chomp();
        String msg = "str: '" + str + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    // chompAll

    public String assertChompAll(String expected, String str) {
        String result = new Str(str).chompAll();
        String msg = "str: '" + str + "'";
        assertEquals(msg, expected, result);
        return result;
    }
    
    // unquote
    
    public String assertUnquote(String expected, String str) {
        String result = new Str(str).unquote();
        assertEquals("str: '" + str + "'", expected, result);
        return result;
    }

    // quote
    
    public String assertQuote(String expected, String str) {
        String result = new Str(str).quote();
        assertEquals("str: '" + str + "'", expected, result);
        return result;
    }

    // equals String

    public Boolean assertEqualsString(Boolean expected, String a, String b) {
        Boolean result = new Str(a).equals(b);
        String msg = "a: '" + a + "'; b: '" + b + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    public void testEqualsStringNullEmptyString() {
        assertEqualsString(false, null, "");
    }

    public void testEqualsStringEmptyStringNull() {
        assertEqualsString(false, "", null);
    }

    public void testEqualsStringNullNull() {
        assertEqualsString(true, null, null);
    }

    public void testEqualsStringNullNonEmptyString() {
        assertEqualsString(false, null, "a");
    }

    public void testEqualsStringNonEmptyStringNull() {
        assertEqualsString(false, "a", null);
    }

    public void testEqualsStringCharCharMatch() {
        assertEqualsString(true, "a", "a");
    }

    public void testEqualsStringCharCharNoMatch() {
        assertEqualsString(false, "a", "b");
    }

    public void testEqualsStringCharCharMismatchedCase() {
        assertEqualsString(false, "a", "A");
    }

    public void testEqualsStringDifferentLengths() {
        assertEqualsString(false, "a", "ab");
    }    

    // equals Str

    public Boolean assertEqualsStr(Boolean expected, String a, String b) {
        Boolean result = new Str(a).equals(new Str(b));
        String msg = "a: '" + a + "'; b: '" + b + "'";
        assertEquals(msg, expected, result);
        return result;
    }

    public void testEqualsStrNullEmptyString() {
        assertEqualsStr(false, null, "");
    }

    public void testEqualsStrEmptyStringNull() {
        assertEqualsStr(false, "", null);
    }

    public void testEqualsStrNullNull() {
        assertEqualsStr(true, null, null);
    }

    public void testEqualsStrNullNonEmptyString() {
        assertEqualsStr(false, null, "a");
    }

    public void testEqualsStrNonEmptyStringNull() {
        assertEqualsStr(false, "a", null);
    }

    public void testEqualsStrCharCharMatch() {
        assertEqualsStr(true, "a", "a");
    }

    public void testEqualsStrCharCharNoMatch() {
        assertEqualsStr(false, "a", "b");
    }

    public void testEqualsStrCharCharMismatchedCase() {
        assertEqualsStr(false, "a", "A");
    }

    public void testEqualsStrDifferentLengths() {
        assertEqualsStr(false, "a", "ab");
    }

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

    public void testCompareTo() {
        assertEqual(true, new Str("abc").compareTo(new Str("abc")) == 0);
        assertEqual(true, new Str("abc").compareTo(new Str("def")) < 0);
        assertEqual(true, new Str("def").compareTo(new Str("abc")) > 0);
        assertEqual(true, new Str("abc").compareTo(new Str(null)) > 0);
        assertEqual(true, new Str(null).compareTo(new Str(null)) == 0);
        assertEqual(true, new Str(null).compareTo(new Str("abc")) < 0);
    }

    public void testHashCode() {
        assertEqual("abc".hashCode(), new Str("abc").hashCode());
        assertEqual(0, new Str(null).hashCode());
    }
}
