package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Pattern;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public class StrTest extends StringTest {
    public void assertResult(String expected, Str result, String msg) {
        if (expected == null) {
            assertThat(result, withContext(msg, nullValue()));
        }
        else {
            assertThat(result, withContext(msg, equalTo(toStr(expected))));
        }
    }

    public void assertResult(String expected, String result, String msg) {
        if (expected == null) {
            assertThat(result, withContext(msg, nullValue()));
        }
        else {
            assertThat(result, withContext(msg, equalTo(expected)));
        }
    }

    public Str toStr(String str) {
        return Str.of(str);
    }

    public String fromStr(Str str) {
        return str == null ? null : str.str();
    }
    
    @Test @Parameters(method="parametersForSplitCharDelim") @TestCaseName("{method} {index} {params}")
    public void splitToArrayCharDelim(String[] expected, String str, char delim, int max) {
        String[] result = new Str(str).split(delim, max, new String[0]);
        assertThat(result, equalTo(expected));
    }

    @Test @Parameters(method="parametersForSplitStringDelim") @TestCaseName("{method} {index} {params}")
    public void splitToArrayStringDelim(String[] expected, String str, String delim, int max) {
        String[] result = new Str(str).split(delim, max, new String[0]);
        assertThat(result, equalTo(expected));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void splitToListStringDelim(List<String> expected, String str, String delim, Integer max) {
        List<String> result = new Str(str).split(delim, max);
        assertThat(result, equalTo(expected));
    }

    private List<String> list(String ... strs) {
        return new ArrayList<>(Arrays.asList(strs));
    }

    public List<Object[]> parametersForSplitToListStringDelim() {
        return paramsList(params(null,                       null,           ";", -1),
                          params(list("ab;cd;e"),            "ab;cd;e",      ";",  1),
                          params(list("ab", "cd;ef"),        "ab;cd;ef",     ";",  2),
                          params(list("ab", "cd", "e"),      "ab;cd;e",      ";",  3),
                          params(list("ab", "cd", "e"),      "ab;cd;e",      ";",  4),
                          params(list("ab", "cd", "e"),      "ab;cd;e",      ";",  null),
                          params(list("ab", "cd", "e"),      "ab;cd;e",      ";",  0),
                          params(list("ab", "cd", "", "e"),  "ab;cd;;e",     ";",  null),
                          params(list("", "ab", "cd"),       ";ab;cd",       ";",  null),
                          // params(list("ab", "cd", ""),       "ab;cd;",       ";",  null),
                          params(list("ab", "cd"),       "ab;cd;",       ";",  null),
                          params(list("ab", "cd", "ef"),     "ab;cd;ef",     ";",  null),
                          params(list("ab", "cd", "ef"),     "ab--cd--ef",   "--", null),
                          params(list("ab", "", "cd", "ef"), "ab----cd--ef", "--", null),
                          params(list("", "ab", "cd", "ef"), "--ab--cd--ef", "--", null),
                          params(list("-ab", "cd", "ef"),    "-ab--cd--ef",  "--", null),
                          params(list(""),                   "",             ";",  null),
                          params(list("ab", "cd", "e"),      "ab.cd.e",      ".",  null));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toList(String[] expected, String str) {
        List<String> result = new Str(str).toList();
        assertThat(result, expected == null ? nullValue() : equalTo(Arrays.asList(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padWithChar(String expected, String str, char ch, int length) {
        Str result = toStr(str).pad(ch, length);
        assertResult(expected, result, message("str", str, "ch", ch, "length", length));        
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padWithoutChar(String expected, String str, int length) {
        Str result = toStr(str).pad(length);
        assertThat(result, withContext(message("str", str, "length", length), equalTo(toStr(expected))));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padLeftWithChar(String expected, String str, char ch, int length) {
        Str result = toStr(str).padLeft(ch, length);
        assertResult(expected, result, message("str", str, "ch", ch, "length", length));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padLeftWithoutChar(String expected, String str, int length) {
        Str result = toStr(str).padLeft(length);
        assertResult(expected, result, message("str", str, "length", length));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void repeatString(String expected, String str, int length) {
        Str result = toStr(str).repeat(length);
        assertResult(expected, result, message("str", str, "length", length));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void repeatChar(String expected, char ch, int length) {
        Str result = new Str(ch).repeat(length);
        assertResult(expected, result, message("ch", ch, "length", length));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void left(String expected, String str, int length) {
        Str result = new Str(str).left(length);
        assertResult(expected, result, message("str", str, "length", length));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void right(String expected, String str, int length) {
        Str result = new Str(str).right(length);
        assertResult(expected, result, message("str", str, "length", length));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void charAt(Character expected, String str, int index) {
        Character result = new Str(str).charAt(index);
        assertThat(result, withContext(message("str", str, "index", index), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void get(Character expected, String str, int index) {
        Character result = new Str(str).get(index);
        assertThat(result, withContext(message("str", str, "index", index), equalTo(expected)));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void getIndex(Integer expected, String str, int index) {
        Integer result = new Str(str).getIndex(index);
        assertThat(result, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForGetIndex() {
        String abcd = "abcd";
        return paramsList(params(null, null, 0),
                          params(0,    abcd, 0),
                          params(1,    abcd, 1),
                          params(3,    abcd, 3),
                          params(null, abcd, 4),
                          params(3,    abcd, -1),
                          params(2,    abcd, -2),
                          params(1,    abcd, -3),
                          params(null, abcd, -5));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void substring(String expected, String str, Integer fromIndex, Integer toIndex) {
        String result = new Str(str).substring(fromIndex, toIndex);
        assertResult(expected, result, message("str", str, "fromIndex", fromIndex, "toIndex", toIndex));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void startsWith(boolean expected, String str, char ch) {
        boolean result = new Str(str).startsWith(ch);
        assertThat(result, equalTo(expected));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void startsWithString(boolean expected, String str, String substr) {
        boolean result = new Str(str).startsWith(substr);
        assertThat(result, equalTo(expected));
    }    

    public List<Object[]> parametersForStartsWithString() {
        return paramsList(params(false, null,   "a"),     
                          params(true,  "abcd", "a"),     
                          params(true,  "abcd", "ab"),    
                          params(true,  "abcd", "abc"),
                          params(true,  "abcd", ""),     
                          params(false, "ab",   "abc"),   
                          params(true,  "abcd", "abcd"),  
                          params(false, "abcd", "abcde"), 
                          params(false, "abcd", "b"),     
                          params(false, "abcd", "A"),     
                          params(true,  "Abcd", "A"));
    }

    @Test @Parameters(method="parametersForStartsWith") @TestCaseName("{method} {index} {params}")
    public void startsWithUseCase(boolean expected, String str, char ch) {
        boolean result = new Str(str).startsWith(ch);
        assertThat(result, equalTo(expected));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void startsWithIgnoreCase(boolean expected, String str, String substr) {
        boolean result = new Str(str).startsWith(substr, EnumSet.of(Str.Option.IGNORE_CASE));
        assertThat(result, equalTo(expected));
    }

    public List<Object[]> parametersForStartsWithIgnoreCase() {
        return paramsList(params(false, null,   "a"),     
                          params(true,  "abcd", "a"),     
                          params(true,  "abcd", "ab"),    
                          params(true,  "abcd", "abc"),
                          params(false, "ab",   "abc"),   
                          params(true,  "abcd", "abcd"),  
                          params(false, "abcd", "abcde"), 
                          params(false, "abcd", "b"),     
                          params(true,  "abcd", "A"),     
                          params(true,  "Abcd", "A"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void startsWithOffset(boolean expected, String str, String substr, int offset) {
        boolean result = new Str(str).startsWith(substr, offset);
        assertThat(result, equalTo(expected));
    }

    public List<Object[]> parametersForStartsWithOffset() {
        return paramsList(params(false, null,   "a",   0), 
                          params(true,  "abcd", "a",   0), 
                          params(true,  "abcd", "b",   1), 
                          params(false, "abcd", "a",   1), 
                          params(false, "ab",   "abc", 0), 
                          params(false, "abcd", "A",   0), 
                          params(true,  "abcd", "ab",  0));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void indexOf(Integer expected, String str, Character ch) {
        Integer result = new Str(str).indexOf(ch);
        assertThat(result, equalTo(expected));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void contains(boolean expected, String str, Character ch) {
        boolean result = new Str(str).contains(ch);
        assertThat(result, withContext(message("str", str, "ch", ch), equalTo(expected)));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void substringAfter(String expected, String str, Character ch) {
        String result = new Str(str).substringAfter(ch);
        assertThat(result, withContext(message("str", str, "ch", ch), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void substringBefore(String expected, String str, Character ch) {
        String result = new Str(str).substringBefore(ch);
        assertThat(result, withContext(message("str", str, "ch", ch), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void eq(Boolean expected, String a, String b) {
        Boolean result = new Str(a).eq(b);
        assertThat(result, withContext(message("a", a, "b", b), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void eqi(Boolean expected, String a, String b) {
        Boolean result = new Str(a).eqi(b);
        assertThat(result, withContext(message("a", a, "b", b), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void snip(String expected, String str, int length) {
        Str result = new Str(str).snip(length);
        assertResult(expected, result, message("str", str, "length", length));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void isEmpty(boolean expected, String str) {
        boolean result = new Str(str).isEmpty();
        assertThat(result, withContext(message("str", str), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index}; params: {params}")
    public void isEmptyIgnoreWhitespace(boolean expected, String str) {
        boolean result = new Str(str).isEmpty(Str.Option.IGNORE_WHITESPACE);
        assertThat(result, equalTo(expected));
    }

    private java.util.List<Object[]> parametersForIsEmptyIgnoreWhitespace() {
        return paramsList(params(true, null),
                          params(true, ""),
                          params(false,  "a"),
                          params(true,  " "),
                          params(true,  "\t"),
                          params(true,  "\n"));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index}; [{params}]")
    public void length(int expected, String str) {
        int result = new Str(str).length();
        assertThat(result, equalTo(expected));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void chomp(String expected, String str) {
        Str result = new Str(str).chomp();
        assertThat(result, withContext(message("str", str), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void chompAll(String expected, String str) {
        Str result = new Str(str).chompAll();
        assertThat(result, withContext(message("str", str), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void unquote(String expected, String str) {
        Str result = new Str(str).unquote();
        assertThat(result, withContext(message("str", str), equalTo(toStr(expected))));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void quote(String expected, String str) {
        Str result = new Str(str).quote();
        assertResult(expected, result, message("str", str));
    }
    
    @Test @Parameters(method="parametersForEquals") @TestCaseName("{method} {index} {params}")
    public void equalsObject(boolean expected, String a, Object b) {
        Str sa = new Str(a);
        assertThat(sa.equals(b), withContext(message("a", a, "b", b), equalTo(expected)));
    }    

    @Test @Parameters(method="parametersForEquals") @TestCaseName("{method} {index} {params}")
    public void equalsString(boolean expected, String a, String b) {
        Str sa = new Str(a);
        assertThat(sa.equals(b), withContext(message("a", a, "b", b), equalTo(expected)));
    }    

    @Test @Parameters(method="parametersForEquals") @TestCaseName("{method} {index} {params}")
    public void equalsStr(boolean expected, String a, String b) {
        Str sa = toStr(a);
        Str sb = toStr(b);
        assertThat(sa.equals(sb), withContext(message("a", a, "b", b), equalTo(expected)));
    }

    private java.util.List<Object[]> parametersForEquals() {
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
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void endsWithChar(boolean expected, String str, char ch) {
        assertThat(toStr(str).endsWith(ch), withContext(message("str", str, "ch", ch), equalTo(expected)));
    }
    
    private List<Object[]> parametersForEndsWithChar() {
        return paramsList(params(true,  "abc", 'c'), 
                          params(false, "abc", ' '), 
                          params(false, null,  'c'), 
                          params(false, "abc", 'd'));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void endsWithString(boolean expected, String str, String s) {
        assertThat(toStr(str).endsWith(s), withContext(message("str", str, "s", s), equalTo(expected)));
    }
    
    private List<Object[]> parametersForEndsWithString() {
        return paramsList(params(true,  "abc",   "c"),   
                          params(true,  "abc",   "bc"),  
                          params(true,  "abc",   "abc"), 
                          params(false, "abcd",  "abc"), 
                          params(true,  "abc",   ""),    
                          params(false, null,    ""),    
                          params(true,  "abc.x", ".x"),  
                          params(false, "abc.x", ".y"),  
                          params(false, "abc",   "d"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compareTo(Integer expected, String x, String y) {
        Integer result = new Str(x).compareTo(new Str(y));
        Integer relResult = result == 0 ? 0 : (result / Math.abs(result));
        assertThat(relResult, withContext(message("x", x, "y", y), equalTo(expected)));
    }
    
    private List<Object[]> parametersForCompareTo() {
        return paramsList(params(0,  "abc", "abc"), 
                          params(-1, "abc", "def"), 
                          params(1,  "def", "abc"), 
                          params(-1, "abc", null),  
                          params(-1, "ab",  "abc"),  
                          params(0,  null,  null),  
                          params(1,  null,  "abc"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compareToNull(Integer expected, Str x, Str y) {
        Integer result = x.compareTo(y);
        Integer relResult = result == 0 ? 0 : (result / Math.abs(result));
        assertThat(relResult, withContext(message("x", x, "y", y), equalTo(expected)));
    }
    
    private List<Object[]> parametersForCompareToNull() {
        return paramsList(params(-1, Str.of("abc"), null), 
                          params(0,  Str.of(null),  null), 
                          params(0,  Str.of(null),  Str.of(null)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compareToWithOptions(Integer expected, String x, String y, EnumSet<Str.Option> options) {
        Integer result = new Str(x).compareTo(new Str(y), options);
        Integer relResult = result == 0 ? 0 : (result / Math.abs(result));
        assertThat(relResult, withContext(message("x", x, "y", y, "options", options), equalTo(expected)));
    }
    
    private List<Object[]> parametersForCompareToWithOptions() {
        return paramsList(params(0,  "abc",   "abc",  EnumSet.of(Str.Option.IGNORE_CASE)), 
                          params(0,  "Abc",   "abc",  EnumSet.of(Str.Option.IGNORE_CASE)), 
                          params(-1, "Abc",   "abc",  EnumSet.noneOf(Str.Option.class)),   
                          params(1,  "abc",   "Abc",  EnumSet.noneOf(Str.Option.class)),   
                          params(1,  "abcd",  "abc",  EnumSet.noneOf(Str.Option.class)),   
                          params(-1, "abc",   "abcd", EnumSet.noneOf(Str.Option.class)),   
                          params(1,  "abcde", "abc",  EnumSet.noneOf(Str.Option.class)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compareToAlphanumeric(Integer expected, String x, String y) {
        Integer result = new Str(x).compareTo(new Str(y), EnumSet.of(Str.Option.ALPHANUMERIC));
        Integer relResult = result == 0 ? 0 : (result / Math.abs(result));
        assertThat(relResult, equalTo(expected));
    }
    
    private List<Object[]> parametersForCompareToAlphanumeric() {
        return paramsList(params(0,  "1",   "1"),
                          params(0,  "a",   "a"),
                          params(-1, "2",   "3"),
                          params(1,  "3",   "2"),
                          params(-1, "9",   "12"),
                          params(1,  "12",  "9"),
                          params(-1, "a2",  "a3"),
                          params(1, "a1",  "a"),
                          params(-1,  "a",   "a1"),
                          params(-1, "a9",  "a12"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void hashCodeTest(Integer expected, String x) {
        assertThat(new Str(x).hashCode(), equalTo(expected));
    }
    
    private List<Object[]> parametersForHashCodeTest() {
        return paramsList(params("abc".hashCode(), "abc"),
                          params(0,                null));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void initRepeatChar(Str expected, char ch, int num) {
        assertThat(new Str(ch, num), equalTo(expected));
    }
    
    private List<Object[]> parametersForInitRepeatChar() {
        return paramsList(params(new Str("a"),  'a',   1),
                          params(new Str("aa"), 'a',   2),  
                          params(new Str(""),   'a',   0),
                          params(new Str(""),   'a',  -1));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void initRepeatString(Str expected, String str, int num) {
        assertThat(new Str(str, num), equalTo(expected));
    }
    
    private List<Object[]> parametersForInitRepeatString() {
        return paramsList(params(new Str("ab"),  "ab",   1),
                          params(new Str("abab"), "ab",   2),  
                          params(new Str(""),   "a",   0),  
                          params(new Str(""),   "a",  -1), 
                          params(new Str(""),   "",    0),  
                          params(new Str(""),   "",    1),  
                          params(new Str(null), null,  1));
    }

    @Test @Parameters(method="parametersForReplace") @TestCaseName("{method} {index} {params}")
    public void replaceAll(String expWithCase, String expIgnoreCase, String line, String from, String to) {
        Str str = toStr(line);
        Str result = str.replaceAll(from, to);
        assertThat(result, expWithCase == null ? nullValue() : equalTo(toStr(expWithCase)));
    }

    @Test @Parameters(method="parametersForReplace") @TestCaseName("{method} {index} {params}")
    public void replaceAllIgnoreCase(String expWithCase, String expIgnoreCase, String line, String from, String to) {
        Str str = toStr(line);
        Str result = str.replaceAll(from, to, EnumSet.of(Str.Option.IGNORE_CASE));        
        assertThat(result, expIgnoreCase == null ? nullValue() : equalTo(toStr(expIgnoreCase)));
    }
    
    private List<Object[]> parametersForReplace() {
        List<Object[]> pl = paramsList();
        
        pl.add(params("1",     "1",      "one",            "one", "1"));
        pl.add(params("ONE",   "1",      "ONE",            "one", "1"));
        pl.add(params("One",   "1",      "One",            "one", "1"));
        
        pl.add(params("1 two", "1 two",  "one two",        "one", "1"));
        pl.add(params("11",    "11",     "oneone",         "one", "1"));
        pl.add(params("1 1",   "1 1",    "one one",        "one", "1"));
        pl.add(params("one 2", "one 2",  "one two",        "two", "2"));
        
        // not applied as regular expressions:
        pl.add(params("1",       "1",      "$one",         "$one", "1"));
        pl.add(params("one two", "one two", "one two",     "one.*", "1"));
        pl.add(params("1 two",   "1 two",   "one.* two",   "one.*", "1"));
        pl.add(params("one",     "one",     "one",         "(?:one|two)", "12"));
        pl.add(params("two",     "two",     "two",         "(?:one|two)", "12"));
        pl.add(params("12",      "12",      "(?:one|two)", "(?:one|two)", "12"));

        pl.add(params(null,      null,      null,          "one", "1"));
        
        return pl;
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void indexOfTest(int expected, String x, String y, int pos, boolean ignoreCase) {
        String msg = message("x", x, "y", y, "pos", pos, "ignoreCase", ignoreCase);
        assertThat(new Str(x).indexOf(y, pos, ignoreCase), withContext(msg, equalTo(expected)));
    }
    
    private List<Object[]> parametersForIndexOfTest() {
        return paramsList(params(0,  "abc", "a", 0, true),
                          params(0,  "abc", "a", 0, false),
                          params(-1, "abc", "a", 1, true),
                          
                          params(1,  "abc", "b", 0, true),
                          params(1,  "abc", "b", 1, true),
                          
                          params(-1, "abc", "d", 0, true),
                          params(0,  "abc", "A", 0, true),
                          params(-1, "abc", "A", 0, false),

                          params(-1, null,  "A", 0, false));
    }
    
    @Test
    public void empty() {
        Str empty = Str.empty();
        assertThat(empty.str().length(), equalTo(0));
        assertThat(Str.EMPTY, sameInstance(empty));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void joinArray(String expected, String[] ary, String delim) {
        String result = Str.join(ary, delim).str();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForJoinArray() {
        return paramsList(params(null,      (String[])null,                 "~"),
                          params("",        new String[] { },               null),
                          params("a~b~c",   new String[] { "a", "b", "c" }, "~"),
                          params("aXXbXXc", new String[] { "a", "b", "c" }, "XX"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void joinCollection(String expected, Collection<String> coll, String delim) {
        String result = Str.join(coll, delim).str();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForJoinCollection() {
        return paramsList(params(null,      (ArrayList<String>)null,           "~"),
                          params("",        Arrays.<String>asList(),           null),
                          params("a~b~c~d", Arrays.asList("a", "b", "c", "d"), "~"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void scan(List<List<String>> expected, String s, String re) {
        Str str = Str.of(s);
        List<List<String>> result = str.scan(Pattern.compile(re));
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForScan() {
        List<Object[]> pl = paramsList();
        pl.add(params(Arrays.asList(new Object[] { list("a") }),              "ab",    "a"));
        pl.add(params(Arrays.asList(new Object[] { list("a"), list("a") }),   "aba",   "a"));
        pl.add(params(Arrays.asList(new Object[] { list("ab", "b") }),        "abaca", "a(b)"));
        pl.add(params(Arrays.asList(new Object[] { list("abac", "b", "c") }), "abaca", "a(b).(.)"));
        return pl;
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void firstNoArg(Character expected, String str) {
        Character result = Str.of(str).first();
        assertThat(result, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForFirstNoArg() {
        return paramsList(params('a', "abc"),
                          params('d', "def"),
                          params(null, ""),
                          params(null, null));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void firstWithArg(String expected, String str, Integer num) {
        Str result = Str.of(str).first(num);
        assertThat(result, expected == null ? nullValue() : equalTo(Str.of(expected)));
    }
    
    private java.util.List<Object[]> parametersForFirstWithArg() {
        return paramsList(params("a",   "abc",  1),  
                          params("ab",  "abc",  2),  
                          params("abc", "abc",  4),  
                          params("",    "abc",  0),  
                          params("",    "abc", -1), 
                          params(null,  null,   1));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void lastNoArg(Character expected, String str) {
        Character result = Str.of(str).last();
        assertThat(result, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForLastNoArg() {
        return paramsList(params('c', "abc"),
                          params('f', "def"),
                          params(null, ""),
                          params(null, null));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void lastWithArg(String expected, String str, Integer num) {
        Str result = Str.of(str).last(num);
        assertThat(result, expected == null ? nullValue() : equalTo(Str.of(expected)));
    }
    
    private java.util.List<Object[]> parametersForLastWithArg() {
        return paramsList(params("c",   "abc",  1),  
                          params("bc",  "abc",  2),  
                          params("abc", "abc",  3),  
                          params("abc", "abc",  4),  
                          params("",    "abc",  0),  
                          params("",    "abc", -1), 
                          params(null,  null,   1));
    }


    @Test @Parameters @TestCaseName("{method}(...) #{index}; params: {params}")
    public void trim(String expected, String str) {
        Str result = new Str(str).trim();
        assertThat(result, equalTo(expected));
    }

    private java.util.List<Object[]> parametersForTrim() {
        return paramsList(params("",   ""),      
                          params("",   " "),     
                          params("a",  "a"),     
                          params("a",  "a "),    
                          params("ab", "ab "),   
                          params("a",  " a"),    
                          params("a",  " a "),   
                          params("a",  "a  "),   
                          params("a",  "  a"),   
                          params("a",  "a\t"),   
                          params("a",  "a\n"),   
                          params("a",  "a\r"),   
                          params("a",  "a\r\n"), 
                          params("a",  "\r\na"));
    }
    
    @Test
    public void demo() {
        Str s = Str.of("hello, world");
        assertThat(s, equalTo("hello, world"));

        Character ch = s.get(5);
        assertThat(ch, equalTo(','));

        ch = s.get(-5);
        assertThat(ch, equalTo('w'));

        ch = s.first();
        assertThat(ch, equalTo('h'));

        ch = s.last();
        assertThat(ch, equalTo('d'));

        Boolean b = s.startsWith("hello");
        assertThat(b, equalTo(true));

        b = s.endsWith("rld");
        assertThat(b, equalTo(true));

        Str t = s.left(2);
        assertThat(t, equalTo("he"));

        t = s.right(5);
        assertThat(t, equalTo("world"));

        Str u = t.padLeft('.', 10);
        assertThat(u, equalTo(".....world"));

        u = t.pad('*', 8);
        assertThat(u, equalTo("world***"));

        u = new Str("ho! ", 3);
        assertThat(u, equalTo("ho! ho! ho! "));

        List<String> list = Str.of("first,    second  \nthird").toList();
        assertThat(list, equalTo(org.incava.ijdk.collect.Array.of("first", "second", "third")));

        t = s.quote();
        assertThat(t, equalTo("\"hello, world\""));

        u = t.unquote();
        assertThat(u, equalTo("hello, world"));

        t = s.snip(6);
        assertThat(t, equalTo("hello-"));
    }    
}
