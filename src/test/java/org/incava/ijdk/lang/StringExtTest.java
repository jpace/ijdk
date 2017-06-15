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

public class StringExtTest extends StringTest {
    // the unquoting functionality is in StringExt, but not Str:
    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
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
}
