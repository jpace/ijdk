package org.incava.ijdk.lang;

import org.incava.test.AbstractTestCaseExt;
import java.util.*;

public class TestStringExt extends AbstractTestCaseExt {
    public TestStringExt(String name) {
        super(name);
    }

    public void testSplit() {
        assertEquals(new String[] { "this", "is", "a",     "test"  }, StringExt.split("this;is;a;test", ';',         -1));
        assertEquals(new String[] { "this", "is", "a",     "test"  }, StringExt.split("this;is;a;test", ";",         -1));
        assertEquals(new String[] { "this", "is", "a",     "test"  }, StringExt.split("this ; is ; a ; test", " ; ", -1));
        assertEquals(new String[] { "this", "is", "a", "", "test"  }, StringExt.split("this;is;a;;test", ';',        -1));
    
        assertEquals(new String[] { "this;is;a;;test"              }, StringExt.split("this;is;a;;test", ';',         1));
        assertEquals(new String[] { "this", "is;a;;test"           }, StringExt.split("this;is;a;;test", ';',         2));
        assertEquals(new String[] { "this", "is", "a;;test"        }, StringExt.split("this;is;a;;test", ';',         3));
        assertEquals(new String[] { "this", "is", "a",     ";test" }, StringExt.split("this;is;a;;test", ';',         4));
    }

    public void assertToList(String[] exp, String str) {
        assertEquals(Arrays.asList(exp), StringExt.toList(str));
    }
    
    public void testToList() {
        String[] expList = new String[] { "fee", "fi", "foo", "fum" };
        assertToList(expList, "fee, fi, foo, fum");
        assertToList(expList, "\"fee, fi, foo, fum\"");
        assertToList(expList, "\'fee, fi, foo, fum\'");
        assertToList(expList, "\'fee,\tfi,\nfoo,\rfum\'");
    }
    
    public void testPad() {
        assertEquals("abcd****", StringExt.pad("abcd", '*', 8));
        assertEquals("abcd",     StringExt.pad("abcd", '*', 3));
        assertEquals("abcd",     StringExt.pad("abcd", '*', 4));
        assertEquals("abcd*",    StringExt.pad("abcd", '*', 5));

        assertEquals("abcd    ", StringExt.pad("abcd", 8));
        assertEquals("abcd",     StringExt.pad("abcd", 3));
        assertEquals("abcd",     StringExt.pad("abcd", 4));
        assertEquals("abcd ",    StringExt.pad("abcd", 5));
    }

    public void testPadLeft() {
        assertEquals("****abcd", StringExt.padLeft("abcd", '*', 8));
        assertEquals("abcd",     StringExt.padLeft("abcd", '*', 3));
        assertEquals("abcd",     StringExt.padLeft("abcd", '*', 4));
        assertEquals("*abcd",    StringExt.padLeft("abcd", '*', 5));

        assertEquals("    abcd", StringExt.padLeft("abcd", 8));
        assertEquals("abcd",     StringExt.padLeft("abcd", 3));
        assertEquals("abcd",     StringExt.padLeft("abcd", 4));
        assertEquals(" abcd",    StringExt.padLeft("abcd", 5));
    }

    public void testRepeat() {
        assertEquals("abcdabcdabcdabcdabcd",                                    StringExt.repeat("abcd",  5));
        assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", StringExt.repeat('a',    55));
        assertEquals("",                                                        StringExt.repeat('a',     0));
        assertEquals("",                                                        StringExt.repeat('a',    -1));
    }

    public void testLeft() {
        assertEquals("abcd", StringExt.left("abcdefgh",  4));
        assertEquals("abcd", StringExt.left("abcd",      4));
        assertEquals("abcd", StringExt.left("abcd",      5));
        assertEquals("abcd", StringExt.left("abcd",      6));
    }

    public void testRight() {
        assertEquals("abcd", StringExt.right("zyxwabcd",  4));
        assertEquals("abcd", StringExt.right("abcd",      4));
        assertEquals("abcd", StringExt.right("abcd",      5));
        assertEquals("abcd", StringExt.right("abcd",      6));
    }

    public void testJoin() {
        String[] contents    = new String[] { "a", "b", "c", "d" };
        String   commaJoined = "a,b,c,d";
        String   xxxJoined   = "axxxbxxxcxxxd";
        
        assertEquals("string array", commaJoined, StringExt.join(contents, ","));
        assertEquals("string array", xxxJoined,   StringExt.join(contents, "xxx"));

        List<String> contentList = Arrays.asList(contents);

        Collection<Collection<String>> collections = new ArrayList<Collection<String>>();
        collections.add(contentList);
        collections.add(new TreeSet<String>(contentList));
        collections.add(new Vector<String>(contentList));
        
        for (Collection<String> collection : collections) {
            assertEquals("collection.type: " + collection.getClass().getName(), commaJoined, StringExt.join(collection, ","));
            assertEquals("collection.type: " + collection.getClass().getName(), xxxJoined,   StringExt.join(collection, "xxx"));
        }
    }

    public void assertCharAt(Character exp, String str, int index) {
        assertEquals(exp, StringExt.charAt(str, index));
    }

    public void testCharAt() {
        assertCharAt('a', "abc", 0);
        assertCharAt('b', "abc", 1);
        assertCharAt('c', "abc", 2);

        assertCharAt(null, "abc", 3);

        assertCharAt('c', "abc", -1);
        assertCharAt('b', "abc", -2);
        assertCharAt('a', "abc", -3);

        assertCharAt(null, "abc", -4);
    }

    public void assertGetIndex(Integer exp, String str, int index) {
        assertEquals(exp, StringExt.getIndex(str, index));
    }

    public void testGetIndex() {
        assertGetIndex(0, "abcd", 0);
        assertGetIndex(1, "abcd", 1);
        assertGetIndex(2, "abcd", 2);
        assertGetIndex(3, "abcd", 3);
        assertGetIndex(null, "abcd", 4);

        assertGetIndex(3, "abcd", -1);
        assertGetIndex(2, "abcd", -2);
        assertGetIndex(1, "abcd", -3);
        assertGetIndex(0, "abcd", -4);

        assertGetIndex(null, "abcd", -5);
    }

    public void assertSubstring(String exp, String str, Integer fromIndex, Integer toIndex) {
        String substr = StringExt.substring(str, fromIndex, toIndex);
        String msg    = "\"" + str + "\"[" + fromIndex + " ... " + toIndex + "]";
        assertEquals(msg, exp, substr);
    }

    public void testSubstring() {
        assertSubstring(null, null, 1, 0);
        assertSubstring(null, null, 1, 1);
        assertSubstring(null, null, 1, 2);

        assertSubstring("abcd", "abcd", 0, 3);
        assertSubstring("abc", "abcd", 0, 2);
        assertSubstring("ab", "abcd", 0, 1);
        assertSubstring("a", "abcd", 0, 0);

        assertSubstring("bcd", "abcd", 1, 3);
        assertSubstring("bc", "abcd", 1, 2);
        assertSubstring("b", "abcd", 1, 1);
        assertSubstring("", "abcd", 1, 0);

        assertSubstring("", "abcd", 1, 0);
        assertSubstring("", "abcd", 5, 0);
        assertSubstring("", "abcd", 4, 5);

        assertSubstring("abcd", "abcd", -4, -1);
        assertSubstring("abc", "abcd", -4, -2);
        assertSubstring("ab", "abcd", -4, -3);
        assertSubstring("a", "abcd", -4, -4);

        assertSubstring("bcd", "abcd", -3, -1);
        assertSubstring("bc", "abcd", -3, -2);
        assertSubstring("b", "abcd", -3, -3);
        assertSubstring("", "abcd", -3, -4);

        assertSubstring("a", "abcd", -4, 0);
        assertSubstring("ab", "abcd", -4, 1);
        assertSubstring("abc", "abcd", -4, 2);
        assertSubstring("abcd", "abcd", -4, 3);
        assertSubstring("abcd", "abcd", -4, 4);

        assertSubstring("", "abcd", -3, 0);
        assertSubstring("b", "abcd", -3, 1);
        assertSubstring("bc", "abcd", -3, 2);
        assertSubstring("bcd", "abcd", -3, 3);
        assertSubstring("bcd", "abcd", -3, 4);

        // this is like Ruby:
        assertSubstring("", "abcd", -8, -1);

        assertSubstring("abc", "abcd", null, -2);
        assertSubstring("cd", "abcd", -2, null);
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