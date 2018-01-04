package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.ijdk.lang.StringTest;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

public class StringExtTest extends StringTest {
    // the unquoting functionality is in StringExt, but not Str:
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toListQuoted(String[] expected, String str) {
        List<String> result = StringExt.toList(str);
        assertEqual(expected == null ? null : Arrays.asList(expected), result, message("str", str));
    }
    
    private List<Object[]> parametersForToListQuoted() {
        return paramsList(params(new String[] { "fee", "fi", "foo", "fum" }, "\"fee, fi, foo, fum\""),
                          params(new String[] { "fee", "fi", "foo", "fum" }, "\'fee, fi, foo, fum\'"),
                          params(new String[] { "fee", "fi", "foo", "fum" }, "\'fee,\tfi,\nfoo,\rfum\'"));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void splitCharDelim(String[] expected, String str, char delim, int max) {
        String[] result = StringExt.split(str, delim, max);
        assertEqual(expected, result, message("str", str, "delim", delim, "max", max));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void splitStringDelim(String[] expected, String str, String delim, int max) {
        String[] result = StringExt.split(str, delim, max);
        assertEqual(expected, result, message("str", str, "delim", delim, "max", max));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toList(String[] expected, String str) {
        List<String> result = StringExt.toList(str);
        assertEqual(expected == null ? null : Arrays.asList(expected), result, message("str", str));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padWithChar(String expected, String str, char ch, int length) {
        String result = StringExt.pad(str, ch, length);
        assertEqual(expected, result, message("str", str, "ch", ch, "length", length));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padWithoutChar(String expected, String str, int length) {
        String result = StringExt.pad(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padLeftWithChar(String expected, String str, char ch, int length) {
        String result = StringExt.padLeft(str, ch, length);
        assertEqual(expected, result, message("str", str, "ch", ch, "length", length));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padLeftWithoutChar(String expected, String str, int length) {
        String result = StringExt.padLeft(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void repeatString(String expected, String str, int length) {
        String result = StringExt.repeat(str, length);
        assertThat(result, withContext(message("str", str, "length", length), equalTo(expected)));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void left(String expected, String str, int length) {
        String result = StringExt.left(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void right(String expected, String str, int length) {
        String result = StringExt.right(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void charAt(Character expected, String str, int index) {
        Character result = StringExt.charAt(str, index);
        assertEqual(expected, result, message("str", str, "index", index));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void get(Character expected, String str, int index) {
        Character result = StringExt.get(str, index);
        assertEqual(expected, result, message("str", str, "index", index));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void substring(String expected, String str, Integer fromIndex, Integer toIndex) {
        String result = StringExt.substring(str, fromIndex, toIndex);
        assertEqual(expected, result, message("str", str, "fromIndex", fromIndex, "toIndex", toIndex));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void startsWith(boolean expected, String str, char ch) {
        boolean result = StringExt.startsWith(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void indexOf(Integer expected, String str, Character ch) {
        Integer result = StringExt.indexOf(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void contains(boolean expected, String str, Character ch) {
        boolean result = StringExt.contains(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void substringAfter(String expected, String str, Character ch) {
        String result = StringExt.substringAfter(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void substringBefore(String expected, String str, Character ch) {
        String result = StringExt.substringBefore(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void eq(Boolean expected, String a, String b) {
        Boolean result = StringExt.eq(a, b);
        assertEqual(expected, result, message("a", a, "b", b));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void eqi(Boolean expected, String a, String b) {
        Boolean result = StringExt.eqi(a, b);
        assertEqual(expected, result, message("a", a, "b", b));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void snip(String expected, String str, int length) {
        String result = StringExt.snip(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void isEmpty(boolean expected, String str) {
        boolean result = StringExt.isEmpty(str);
        assertEqual(expected, result, message("str", str));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void length(int expected, String str) {
        int result = StringExt.length(str);
        assertEqual(expected, result, message("str", str));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void chomp(String expected, String str) {
        String result = StringExt.chomp(str);
        assertEqual(expected, result, message("str", str));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void chompAll(String expected, String str) {
        String result = StringExt.chompAll(str);
        assertEqual(expected, result, message("str", str));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void unquote(String expected, String str) {
        String result = StringExt.unquote(str);
        assertEqual(expected, result, message("str", str));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void quote(String expected, String str) {
        String result = StringExt.quote(str);
        assertEqual(expected, result, message("str", str));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void joinArray(String expected, String[] ary, String delim) {
        String result = StringExt.join(ary, delim);
        assertEqual(expected, result, message("ary", ary, "delim", delim));
    }
    
    private List<Object[]> parametersForJoinArray() {
        return paramsList(params(null, (String[])null, ","),
                          params("abcd",    new String[] { "a", "b", "c", "d" }, null),
                          params("abcd",    new String[] { "a", "b", "c", "d" }, ""),
                          params("",        new String[] { "" },                 ","),
                          params("a,b,c,d", new String[] { "a", "b", "c", "d" }, ","));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void joinCollection(String expected, Collection<String> coll, String delim) {
        String result = StringExt.join(coll, delim);
        assertEqual(expected, result, message("coll", coll, "delim", delim));
    }
    
    private List<Object[]> parametersForJoinCollection() {
        return paramsList(params(null,      (ArrayList<String>)null,           ","),
                          params("abcd",    Arrays.asList("a", "b", "c", "d"), null),
                          params("abcd",    Arrays.asList("a", "b", "c", "d"), ""),
                          params("",        new ArrayList<String>(),           ","),
                          params("a,b,c,d", Arrays.asList("a", "b", "c", "d"), ","));
    }    
}
