package org.incava.ijdk.lang;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.ijdk.lang.StringTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

@RunWith(JUnitParamsRunner.class)
public class TestStringExt extends StringTest {
    // the unquoting functionality is in StringExt, but not Str:
    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void toListQuoted(String[] expected, String str) {
        List<String> result = toList(str);
        assertEqual(expected == null ? null : Arrays.asList(expected), result, message("str", str));
    }
    
    private List<Object[]> parametersForToListQuoted() {
        return paramsList(params(new String[] { "fee", "fi", "foo", "fum" }, "\"fee, fi, foo, fum\""),
                          params(new String[] { "fee", "fi", "foo", "fum" }, "\'fee, fi, foo, fum\'"),
                          params(new String[] { "fee", "fi", "foo", "fum" }, "\'fee,\tfi,\nfoo,\rfum\'"));
    }
    
    public String[] split(String str, char delim, int max) {
        return StringExt.split(str, delim, max);
    }

    public String[] split(String str, String delim, int max) {
        return StringExt.split(str, delim, max);
    }

    public List<String> toList(String str) {
        return StringExt.toList(str);
    }

    public String pad(String str, char ch, int length) {
        return StringExt.pad(str, ch, length);
    }

    public String pad(String str, int length) {
        return StringExt.pad(str, length);
    }    

    public String padLeft(String str, char ch, int length) {
        return StringExt.padLeft(str, ch, length);
    }    

    public String padLeft(String str, int length) {
        return StringExt.padLeft(str, length);
    }    

    public String repeat(String str, int length) {
        return StringExt.repeat(str, length);
    }    

    public String repeat(char ch, int length) {
        return StringExt.repeat(ch, length);
    }

    public String left(String str, int length) {
        return StringExt.left(str, length);
    }

    public String right(String str, int length) {
        return StringExt.right(str, length);
    }

    public String join(String[] ary, String delim) {
        return StringExt.join(ary, delim);
    }

    public String join(Collection<String> coll, String delim) {
        return StringExt.join(coll, delim);
    }

    public Character charAt(String str, int index) {
        return StringExt.charAt(str, index);
    }

    public Character get(String str, int index) {
        return StringExt.get(str, index);
    }

    public String substring(String str, Integer fromIndex, Integer toIndex) {
        return StringExt.substring(str, fromIndex, toIndex);
    }

    public boolean startsWith(String str, char ch) {
        return StringExt.startsWith(str, ch);
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
