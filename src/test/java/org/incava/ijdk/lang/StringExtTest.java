package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.ijdk.lang.StringTest;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
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

    public String padLeft(String str, char ch, int length) {
        return StringExt.padLeft(str, ch, length);
    }    

    public String padLeft(String str, int length) {
        return StringExt.padLeft(str, length);
    }    

    public String repeat(String str, int length) {
        return StringExt.repeat(str, length);
    }    

    public String repeat(Character ch, int length) {
        return StringExt.repeat(ch, length);
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

    public Integer indexOf(String str, Character ch) {
        return StringExt.indexOf(str, ch);
    }

    public boolean contains(String str, Character ch) {
        return StringExt.contains(str, ch);
    }

    public String substringAfter(String str, Character ch) {
        return StringExt.substringAfter(str, ch);
    }

    public String substringBefore(String str, Character ch) {
        return StringExt.substringBefore(str, ch);
    }

    public Boolean eq(String a, String b) {
        return StringExt.eq(a, b);
    }

    public Boolean eqi(String a, String b) {
        return StringExt.eqi(a, b);
    }

    public String snip(String str, int length) {
        return StringExt.snip(str, length);
    }

    public boolean isEmpty(String str) {
        return StringExt.isEmpty(str);
    }

    public int length(String str) {
        return StringExt.length(str);
    }

    public String chomp(String str) {
        return StringExt.chomp(str);
    }

    public String chompAll(String str) {
        return StringExt.chompAll(str);
    }
    
    public String unquote(String str) {
        return StringExt.unquote(str);
    }
    
    public String quote(String str) {
        return StringExt.quote(str);
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
