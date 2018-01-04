package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public abstract class StringTest extends Parameterized {
    public List<Object[]> parametersForSplitCharDelim() {
        return paramsList(params(null, null, ';', -1),
                          params(new String[] { "this", "is", "a", "test" }, "this;is;a;test", ';', -1),
                          params(new String[] { "this", "is", "a", "", "test" }, "this;is;a;;test", ';', -1),
                          params(new String[] { "this", "is;a;;test" }, "this;is;a;;test", ';', 2),
                          params(new String[] { "this", "is", "a;;test" }, "this;is;a;;test", ';', 3),
                          params(new String[] { "this", "is", "a", ";test" }, "this;is;a;;test", ';', 4));
    }
    
    public List<Object[]> parametersForSplitStringDelim() {
        return paramsList(params(null, null, ";", -1),
                          params(new String[] { "this", "is", "a", "test" }, "this;is;a;test", ";", -1),
                          params(new String[] { "this", "is", "a", "test" }, "this ; is ; a ; test", " ; ", -1));
    }
    
    public List<Object[]> parametersForToList() {
        return paramsList(params(null, null),
                          params(new String[] { "fee", "fi", "foo", "fum" }, "fee, fi, foo, fum"),
                          params(new String[] { "fee", "fi", "foo", "fum" }, "fee,\tfi,\nfoo,\rfum"));
    }
    
    protected List<Object[]> parametersForPadWithChar() {
        return paramsList(params(null,       null,   '*', 8), 
                          params("abcd****", "abcd", '*', 8), 
                          params("abcd",     "abcd", '*', 3), 
                          params("abcd",     "abcd", '*', 4), 
                          params("abcd*",    "abcd", '*', 5));
    }    
    
    public List<Object[]> parametersForPadWithoutChar() {
        return paramsList(params("abcd    ", "abcd", 8), 
                          params("abcd",     "abcd", 3), 
                          params("abcd",     "abcd", 4), 
                          params("abcd ",    "abcd", 5));
    }

    public abstract String padLeft(String str, char ch, int length);

    public abstract String padLeft(String str, int length);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padLeftWithChar(String expected, String str, char ch, int length) {
        String result = padLeft(str, ch, length);
        assertEqual(expected, result, message("str", str, "ch", ch, "length", length));
    }
    
    public List<Object[]> parametersForPadLeftWithChar() {
        return paramsList(params(null,       null,   '*', 8), 
                          params("****abcd", "abcd", '*', 8), 
                          params("abcd",     "abcd", '*', 3), 
                          params("abcd",     "abcd", '*', 4), 
                          params("*abcd",    "abcd", '*', 5));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padLeftWithoutChar(String expected, String str, int length) {
        String result = padLeft(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }
    
    private List<Object[]> parametersForPadLeftWithoutChar() {
        return paramsList(params(null,       null,   8), 
                          params("    abcd", "abcd", 8), 
                          params("abcd",     "abcd", 3), 
                          params("abcd",     "abcd", 4), 
                          params(" abcd",    "abcd", 5));
    }

    public abstract String repeat(String str, int length);

    public abstract String repeat(char ch, int length);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void repeatString(String expected, String str, int length) {
        String result = repeat(str, length);
        assertThat(result, withContext(message("str", str, "length", length), equalTo(expected)));
    }
    
    private List<Object[]> parametersForRepeatString() {
        return paramsList(params(null,       null,   0),  
                          params("",         "abcd", -1), 
                          params("",         "abcd", 0),  
                          params("abcd",     "abcd", 1),  
                          params("abcdabcd", "abcd", 2));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void repeatChar(String expected, char ch, int length) {
        String result = repeat(ch, length);
        assertEqual(expected, result, message("ch", ch, "length", length));
    }
    
    private List<Object[]> parametersForRepeatChar() {
        return paramsList(params("",   'a', -1), 
                          params("",   'a', 0),  
                          params("a",  'a', 1),  
                          params("aa", 'a', 2));
    }

    public abstract String left(String str, int length);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void left(String expected, String str, int length) {
        String result = left(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }
    
    private List<Object[]> parametersForLeft() {
        return paramsList(params(null,   null,       1), 
                          params("abcd", "abcdefgh", 4), 
                          params("abcd", "abcd",     4), 
                          params("abcd", "abcd",     5), 
                          params("",     "abcd",     0), 
                          params("",     "abcd",     -1));
    }

    public abstract String right(String str, int length);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void right(String expected, String str, int length) {
        String result = right(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }
    
    private List<Object[]> parametersForRight() {
        return paramsList(params(null,   null,       1), 
                          params("efgh", "abcdefgh", 4), 
                          params("abcd", "abcd",     4), 
                          params("abcd", "abcd",     5), 
                          params("",     "abcd",     0), 
                          params("",     "abcd",     -1));
    }
    
    public abstract Character charAt(String str, int index);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void charAt(Character expected, String str, int index) {
        Character result = charAt(str, index);
        assertEqual(expected, result, message("str", str, "index", index));
    }
    
    private List<Object[]> parametersForCharAt() {
        return paramsList(params(null, null,  0),  
                          params('a',  "abc", 0),  
                          params('b',  "abc", 1),  
                          params('c',  "abc", 2),  
                          params(null, "abc", 3),  
                          params('c',  "abc", -1), 
                          params('b',  "abc", -2), 
                          params('a',  "abc", -3), 
                          params(null, "abc", -4));
    }

    public abstract Character get(String str, int index);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void get(Character expected, String str, int index) {
        Character result = get(str, index);
        assertEqual(expected, result, message("str", str, "index", index));
    }
    
    private List<Object[]> parametersForGet() {
        return paramsList(params(null, null,  0),  
                          params('a',  "abc", 0),  
                          params('b',  "abc", 1),  
                          params('c',  "abc", 2),  
                          params(null, "abc", 3),  
                          params('c',  "abc", -1), 
                          params('b',  "abc", -2), 
                          params('a',  "abc", -3), 
                          params(null, "abc", -4));
    }

    public abstract String substring(String str, Integer fromIndex, Integer toIndex);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void substring(String expected, String str, Integer fromIndex, Integer toIndex) {
        String result = substring(str, fromIndex, toIndex);
        assertEqual(expected, result, message("str", str, "fromIndex", fromIndex, "toIndex", toIndex));
    }
    
    private List<Object[]> parametersForSubstring() {
        return paramsList(params(null,   null,     1,    0),    
                          params("abcd", "abcd",   0,    3),    
                          params("abc",  "abcd",   0,    2),    
                          params("a",    "abcd",   0,    0),    
                          // expect "",  not null, per Ruby behavior
                          params("",     "abcd",   1,    0),    
                          params("",     "abcd",   4,    5),    
                          params("abcd", "abcd",   -4,   -1),   
                          params("abc",  "abcd",   -4,   -2),   
                          params("a",    "abcd",   -4,   -4),   
                          // expect "",  not null, per Ruby behavior
                          params("",     "abcd",   -1,   -2),   
                          // null == first in string
                          params("abc",  "abcd",   null, -2),   
                          params("cd",   "abcd",   -2,   null), 
                          params("abcd", "abcd",   null, null));
    }

    public abstract boolean startsWith(String str, char ch);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void startsWith(boolean expected, String str, char ch) {
        boolean result = startsWith(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }
    
    private List<Object[]> parametersForStartsWith() {
        return paramsList(params(false, null,   'j'), 
                          params(true,  "java", 'j'), 
                          params(false, "java", 'a'), 
                          params(false, "java", 'J'));
    }

    public abstract Integer indexOf(String str, Character ch);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void indexOf(Integer expected, String str, Character ch) {
        Integer result = indexOf(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }
    
    private List<Object[]> parametersForIndexOf() {
        return paramsList(params(null, "abc", null), 
                          params(null, null,  'a'),  
                          params(null, null,  null), 
                          params(0,    "abc", 'a'),  
                          params(2,    "abc", 'c'),  
                          params(null, "abc", 'd'),  
                          params(null, "abc", 'A'));
    }

    // contains

    public abstract boolean contains(String str, Character ch);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void contains(boolean expected, String str, Character ch) {
        boolean result = contains(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }
    
    private List<Object[]> parametersForContains() {
        return paramsList(params(false, "abc", null), 
                          params(false, null,  'a'),  
                          params(false, null,  null), 
                          params(true,  "abc", 'a'),  
                          params(true,  "abc", 'c'),  
                          params(false, "abc", 'd'),  
                          params(false, "abc", 'A'));
    }

    public abstract String substringAfter(String str, Character ch);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void substringAfter(String expected, String str, Character ch) {
        String result = substringAfter(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }
    
    private List<Object[]> parametersForSubstringAfter() {
        return paramsList(params(null, "abc", null), 
                          params(null, null,  'a'),  
                          params(null, null,  null), 
                          params("bc", "abc", 'a'),  
                          params("",   "abc", 'c'),  
                          params(null, "abc", 'd'),  
                          params(null, "abc", 'A'));
    }

    public abstract String substringBefore(String str, Character ch);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void substringBefore(String expected, String str, Character ch) {
        String result = substringBefore(str, ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }
    
    private List<Object[]> parametersForSubstringBefore() {
        return paramsList(params(null, "abc", null), 
                          params(null, null,  'a'),  
                          params(null, null,  null), 
                          params("",   "abc", 'a'),  
                          params("ab", "abc", 'c'),  
                          params(null, "abc", 'd'),  
                          params(null, "abc", 'A'));
    }

    public abstract Boolean eq(String a, String b);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void eq(Boolean expected, String a, String b) {
        Boolean result = eq(a, b);
        assertEqual(expected, result, message("a", a, "b", b));
    }
    
    private List<Object[]> parametersForEq() {
        return paramsList(params(false, null, ""),   
                          params(false, "",   null), 
                          params(true,  null, null), 
                          params(false, null, "a"),  
                          params(false, "a",  null), 
                          params(true,  "a",  "a"),  
                          params(false, "a",  "b"),  
                          params(false, "a",  "A"),  
                          params(false, "a",  "ab"));
    }

    public abstract Boolean eqi(String a, String b);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void eqi(Boolean expected, String a, String b) {
        Boolean result = eqi(a, b);
        assertEqual(expected, result, message("a", a, "b", b));
    }
    
    private List<Object[]> parametersForEqi() {
        return paramsList(params(false, null, ""),   
                          params(false, "",   null), 
                          params(true,  null, null), 
                          params(false, null, "a"),  
                          params(false, "a",  null), 
                          params(true,  "a",  "a"),  
                          params(false, "a",  "b"),  
                          params(true,  "A",  "a"),  
                          params(false, "a",  "ab"));
    }

    public abstract String snip(String str, int length);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void snip(String expected, String str, int length) {
        String result = snip(str, length);
        assertEqual(expected, result, message("str", str, "length", length));
    }
    
    private List<Object[]> parametersForSnip() {
        return paramsList(params(null,  null,   3), 
                          params("",    "",     3), 
                          params("-",   "abc",  1), 
                          params("a-",  "abc",  2), 
                          params("abc", "abc",  3), 
                          params("ab-", "abcd", 3));
    }

    public abstract boolean isEmpty(String str);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void isEmpty(boolean expected, String str) {
        boolean result = isEmpty(str);
        assertEqual(expected, result, message("str", str));
    }
    
    private List<Object[]> parametersForIsEmpty() {
        return paramsList(params(true,  null), 
                          params(true,  ""),   
                          params(false, "a"));
    }

    public abstract int length(String str);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void length(int expected, String str) {
        int result = length(str);
        assertEqual(expected, result, message("str", str));
    }
    
    private List<Object[]> parametersForLength() {
        return paramsList(params(0, null), 
                          params(0, ""),   
                          params(1, "a"));
    }

    public abstract String chomp(String str);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void chomp(String expected, String str) {
        String result = chomp(str);
        assertEqual(expected, result, message("str", str));
    }
    
    private List<Object[]> parametersForChomp() {
        return paramsList(params(null,  null),    
                          params("",    ""),      
                          params("a",   "a"),     
                          params("a",   "a\n"),   
                          params("a\n", "a\n\n"), 
                          params("a",   "a\r"),   
                          params("a\r", "a\r\r"), 
                          params("a\r", "a\r\n"));
    }

    public abstract String chompAll(String str);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void chompAll(String expected, String str) {
        String result = chompAll(str);
        assertEqual(expected, result, message("str", str));
    }
    
    private List<Object[]> parametersForChompAll() {
        return paramsList(params(null, null),    
                          params("",   ""),      
                          params("a",  "a"),     
                          params("a",  "a\n"),   
                          params("a",  "a\n\n"), 
                          params("a",  "a\r"),   
                          params("a",  "a\r\r"), 
                          params("a",  "a\r\n"));
    }
    
    public abstract String unquote(String str);    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void unquote(String expected, String str) {
        String result = unquote(str);
        assertEqual(expected, result, message("str", str));
    }
    
    private List<Object[]> parametersForUnquote() {
        return paramsList(params(null,      null),
                          params("",        ""),
                          params("abc",     "abc"),
                          params("\'abc\"", "\'abc\""),
                          params("\"abc\'", "\"abc\'"),
                          params("abc",     "\'abc\'"),
                          params("abc",     "\"abc\""),
                          params("\"",      "\""),
                          params("",        "\"\""));
    }
    
    public abstract String quote(String str);

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void quote(String expected, String str) {
        String result = quote(str);
        assertEqual(expected, result, message("str", str));
    }
    
    private List<Object[]> parametersForQuote() {
        return paramsList(params(null,      null), 
                          params("\"\"",    ""),   
                          params("\"abc\"", "abc"));
    }
}
