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
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

public class StringsTest extends StringTest {
    // the unquoting functionality is in Strings, but not Str:
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toListQuoted(String[] expected, String str) {
        List<String> result = Strings.toList(str);
        assertThat(result, equalTo(expected == null ? null : Arrays.asList(expected)));
    }
    
    private List<Object[]> parametersForToListQuoted() {
        return paramsList(params(new String[] { "fee", "fi", "foo", "fum" }, "\"fee, fi, foo, fum\""),
                          params(new String[] { "fee", "fi", "foo", "fum" }, "\'fee, fi, foo, fum\'"),
                          params(new String[] { "fee", "fi", "foo", "fum" }, "\'fee,\tfi,\nfoo,\rfum\'"));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void splitCharDelim(String[] expected, String str, char delim, int max) {
        String[] result = Strings.split(str, delim, max);
        assertThat(result, withContext(message("str", str, "delim", delim, "max", max), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void splitStringDelim(String[] expected, String str, String delim, int max) {
        String[] result = Strings.split(str, delim, max);
        assertThat(result, withContext(message("str", str, "delim", delim, "max", max), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toList(String[] expected, String str) {
        List<String> result = Strings.toList(str);
        assertThat(result, equalTo(expected == null ? null : Arrays.asList(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padWithChar(String expected, String str, char ch, int length) {
        String result = Strings.pad(str, ch, length);
        assertThat(result, equalTo(expected));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padWithoutChar(String expected, String str, int length) {
        String result = Strings.pad(str, length);
        assertThat(result, equalTo(expected));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padLeftWithChar(String expected, String str, char ch, int length) {
        String result = Strings.padLeft(str, ch, length);
        assertThat(result, equalTo(expected));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padLeftWithoutChar(String expected, String str, int length) {
        String result = Strings.padLeft(str, length);
        assertThat(result, equalTo(expected));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void repeatString(String expected, String str, int length) {
        String result = Strings.repeat(str, length);
        assertThat(result, withContext(message("str", str, "length", length), equalTo(expected)));
    }    

    @Test @Parameters(method="parametersForRepeatString") @TestCaseName("{method} {index} {params}")
    public void repeatStringBuilder(String expected, String str, int length) {
        StringBuilder sb = new StringBuilder();
        String result = Strings.repeat(sb, str, length);
        assertThat(result, withContext(message("str", str, "length", length), equalTo(expected)));
        if (expected == null) {
            assertThat(sb.toString(), withContext(message("str", str, "length", length), equalTo("")));
        }
        else {
            assertThat(sb.toString(), withContext(message("str", str, "length", length), equalTo(expected)));
        }
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void left(String expected, String str, int length) {
        String result = Strings.left(str, length);
        assertThat(result, withContext(message("str", str, "length", length), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void right(String expected, String str, int length) {
        String result = Strings.right(str, length);
        assertThat(result, withContext(message("str", str, "length", length), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void charAt(Character expected, String str, int index) {
        Character result = Strings.charAt(str, index);
        assertThat(result, withContext(message("str", str, "index", index), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void get(Character expected, String str, int index) {
        Character result = Strings.get(str, index);
        assertThat(result, withContext(message("str", str, "index", index), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void substring(String expected, String str, Integer fromIndex, Integer toIndex) {
        String result = Strings.substring(str, fromIndex, toIndex);
        assertThat(result, withContext(message("str", str, "fromIndex", fromIndex, "toIndex", toIndex), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void startsWith(boolean expected, String str, char ch) {
        boolean result = Strings.startsWith(str, ch);
        assertThat(result, withContext(message("str", str, "ch", ch), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void indexOf(Integer expected, String str, Character ch) {
        Integer result = Strings.indexOf(str, ch);
        assertThat(result, withContext(message("str", str, "ch", ch), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void contains(boolean expected, String str, Character ch) {
        boolean result = Strings.contains(str, ch);
        assertThat(result, withContext(message("str", str, "ch", ch), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void substringAfter(String expected, String str, Character ch) {
        String result = Strings.substringAfter(str, ch);
        assertThat(result, withContext(message("str", str, "ch", ch), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void substringBefore(String expected, String str, Character ch) {
        String result = Strings.substringBefore(str, ch);
        assertThat(result, withContext(message("str", str, "ch", ch), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void eq(Boolean expected, String a, String b) {
        Boolean result = Strings.eq(a, b);
        assertThat(result, withContext(message("a", a, "b", b), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void eqi(Boolean expected, String a, String b) {
        Boolean result = Strings.eqi(a, b);
        assertThat(result, withContext(message("a", a, "b", b), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void snip(String expected, String str, int length) {
        String result = Strings.snip(str, length);
        assertThat(result, withContext(message("str", str, "length", length), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void isEmpty(boolean expected, String str) {
        boolean result = Strings.isEmpty(str);
        assertThat(result, withContext(message("str", str), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void length(int expected, String str) {
        int result = Strings.length(str);
        assertThat(result, withContext(message("str", str), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void chomp(String expected, String str) {
        String result = Strings.chomp(str);
        assertThat(result, withContext(message("str", str), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void chompAll(String expected, String str) {
        String result = Strings.chompAll(str);
        assertThat(result, withContext(message("str", str), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void unquote(String expected, String str) {
        String result = Strings.unquote(str);
        assertThat(result, withContext(message("str", str), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void quote(String expected, String str) {
        String result = Strings.quote(str);
        assertThat(result, withContext(message("str", str), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void joinArray(String expected, String[] ary, String delim) {
        String result = Strings.join(ary, delim);
        assertThat(result, withContext(message("ary", ary, "delim", delim), equalTo(expected)));
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
        String result = Strings.join(coll, delim);
        assertThat(result, withContext(message("coll", coll, "delim", delim), equalTo(expected)));
    }
    
    private List<Object[]> parametersForJoinCollection() {
        return paramsList(params(null,      (ArrayList<String>)null,           ","),
                          params("abcd",    Arrays.asList("a", "b", "c", "d"), null),
                          params("abcd",    Arrays.asList("a", "b", "c", "d"), ""),
                          params("",        new ArrayList<String>(),           ","),
                          params("a,b,c,d", Arrays.asList("a", "b", "c", "d"), ","));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compareChars(int expected, String s, String t, int idx) {
        int result = Strings.compareChars(s, t, idx);
        assertThat(result, withContext(message("s", s, "t", t, "idx", idx), equalTo(expected)));
    }

    private List<Object[]> parametersForCompareChars() {
        return paramsList(params(0,  "a",  "a",  0), 
                          params(-1, "a",  "b",  0), 
                          params(-1, "ab", "ac", 1));
    }    
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void matchInteger(Integer expected, String str, int idx) {
        Integer result = Strings.matchInteger(str, idx);
        assertThat(result, withContext(message("str", str, "idx", idx), equalTo(expected)));
    }

    private List<Object[]> parametersForMatchInteger() {
        return paramsList(params(null,   "a",    0), 
                          params(null,   "a",    0), 
                          params(null,   "1",    1), 
                          params(null,   "a1",   0), 
                          params("1",    "a1",   1), 
                          params("1",    "1",    0), 
                          params("12",   "12",   0), 
                          params("2",    "12",   1), 
                          params("1",    "1.",   0));
    }    
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void matchDouble(Double expected, String str, int idx) {
        Double result = Strings.matchDouble(str, idx);
        assertThat(result, withContext(message("str", str, "idx", idx), equalTo(expected)));
    }

    private List<Object[]> parametersForMatchDouble() {
        return paramsList(params(null,   "a",    0), 
                          params(null,   "a",    0), 
                          params(null,   "1",    1), 
                          params(null,   "a1",   0), 
                          params("1.2",  "1.2",  0), 
                          params("11.2", "11.2", 0));
    }    
}
