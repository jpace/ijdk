package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Pattern;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.ijdk.collect.StringArray;
import org.incava.ijdk.regexp.MatchData;
import org.incava.ijdk.regexp.Regexp;
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
        return new Str(str);
    }

    public String fromStr(Str str) {
        return str == null ? null : str.str();
    }

    private StringArray strAry(String ... strs) {
        return StringArray.of(strs);
    }

    private List<String> list(String ... strs) {
        return new ArrayList<>(Arrays.asList(strs));
    }

    private List<Object> list(Object ... ary) {
        return new ArrayList<>(Arrays.asList(ary));
    }

    @Test @Parameters(method="parametersForSplit") @TestCaseName("{method} {index} {params}")
    public void splitStrWithMax(StringArray expected, String str, String delim, Integer max) {
        StringArray result = new Str(str).split(new Str(delim), max);
        if (expected != null) {
            assertThat(result.size(), equalTo(expected.size()));
        }
        assertThat(result, equalTo(expected));
    }

    @Test @Parameters(method="parametersForSplit") @TestCaseName("{method} {index} {params}")
    public void splitStringWithMax(StringArray expected, String str, String delim, Integer max) {
        StringArray result = new Str(str).split(delim, max);
        if (expected != null) {
            assertThat(result.size(), equalTo(expected.size()));
        }
        assertThat(result, equalTo(expected));
    }

    @Test @Parameters(method="parametersForSplit") @TestCaseName("{method} {index} {params}")
    public void splitStrWithoutMax(StringArray expected, String str, String delim, Integer max) {
        if (max == null) {
            StringArray result = new Str(str).split(new Str(delim));
            if (expected != null) {
                assertThat(result.size(), equalTo(expected.size()));
            }
            assertThat(result, equalTo(expected));
        }
    }

    @Test @Parameters(method="parametersForSplit") @TestCaseName("{method} {index} {params}")
    public void splitStringWithoutMax(StringArray expected, String str, String delim, Integer max) {
        if (max == null) {
            StringArray result = new Str(str).split(delim);
            if (expected != null) {
                assertThat(result.size(), equalTo(expected.size()));
            }
            assertThat(result, equalTo(expected));
        }
    }
    
    public List<Object[]> parametersForSplit() {
        return paramsList(params(null,                         null,            ";", -1),
                          params(strAry("ab;cd;e"),            "ab;cd;e",       ";",  1),
                          params(strAry("ab", "cd;ef"),        "ab;cd;ef",      ";",  2),
                          params(strAry("ab", "cd", "e"),      "ab;cd;e",       ";",  3),
                          params(strAry("ab", "cd", "e"),      "ab;cd;e",       ";",  4),
                          params(strAry("ab", "cd", "e"),      "ab;cd;e",       ";",  null),
                          params(strAry("ab", "cd", "e"),      "ab;cd;e",       ";",  0),
                          params(strAry("ab", "cd", "", "e"),  "ab;cd;;e",      ";",  null),
                          params(strAry("", "ab", "cd"),       ";ab;cd",        ";",  null),
                          params(strAry("ab", "cd"),           "ab;cd;",        ";",  null),
                          params(strAry("ab", "cd", "ef"),     "ab;cd;ef",      ";",  null),
                          params(strAry("ab", "cd", "ef"),     "ab--cd--ef",    "--", null),
                          params(strAry("ab", "", "cd", "ef"), "ab----cd--ef",  "--", null),
                          params(strAry("", "ab", "cd", "ef"), "--ab--cd--ef",  "--", null),
                          params(strAry("-ab", "cd", "ef"),    "-ab--cd--ef",   "--", null),
                          params(strAry(""),                   "",              ";",  null),
                          params(strAry("ab", "cd", "e"),      "ab.cd.e",       ".",  null),
                          params(strAry("ab", "cd", "e"),      "ab-cd-e-",      "-",  null),
                          params(strAry("ab", "cd", "e"),      "ab-cd-e--",     "-",  null),
                          params(strAry("ab", "cd", "e"),      "ab-cd-e---",    "-",  null),
                          params(strAry("ab", "cd", "e"),      "ab-cd-e----",   "-",  null),
                          params(strAry("ab", "cd", "e"),      "ab\ncd\ne\n\n", "\n", null));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toList(String[] expected, String str) {
        List<String> result = new Str(str).toList();
        assertThat(result, expected == null ? nullValue() : equalTo(StringArray.of(expected)));
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
    
    private List<Object[]> parametersForGetIndex() {
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
    public void startsWithString(boolean expected, Str str, String substr) {
        boolean result = str.startsWith(substr);
        assertThat(result, equalTo(expected));
    }

    public List<Object[]> parametersForStartsWithString() {
        Str s = new Str("a");
        return paramsList(params(true,  s, "a"),
                          params(false, s, "b"));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void startsWith(boolean expected, Str str, String substr, Integer offset, Str.Option ... options) {
        Str t = new Str(substr);
        boolean result = offset == null ? str.startsWith(t, 0, options) : str.startsWith(t, offset, options);
        assertThat(result, equalTo(expected));
    }
    
    public List<Object[]> parametersForStartsWith() {
        Str s = new Str("abc");
        Str.Option[] empty = new Str.Option[0];
        return paramsList(params(true,  s, "a",    null, empty),
                          params(true,  s, "a",    0,    empty),
                          params(false, s, "b",    0,    empty),
                          params(true,  s, "b",    1,    empty),
                          params(false, s, "A",    0,    empty),
                          params(true,  s, "A",    0,    Str.Option.IGNORE_CASE),
                          params(false, s, "aB",   0,    empty),
                          params(true,  s, "aB",   0,    Str.Option.IGNORE_CASE),
                          params(true,  s, "abc",  0,    empty),
                          params(true,  s, "bc",   1,    empty),
                          params(false, s, "bc",   2,    empty),
                          params(false, s, "abcd", 0,    empty));
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

    @Test @Parameters(method="parametersForSubstringBefore") @TestCaseName("{method} {index} {params}")
    public void substringBeforeStr(String expected, String str, Character ch) {
        Str result = new Str(str).substringBefore(ch, Str.EMPTY);
        Str exp = expected == null ? null : new Str(expected);
        assertThat(result, withContext(message("str", str, "ch", ch), equalTo(exp)));
    }

    @Test @Parameters(method="parametersForSubstringBefore") @TestCaseName("{method} {index} {params}")
    public void substringBeforeString(String expected, String str, Character ch) {
        String result = new Str(str).substringBefore(ch);
        assertThat(result, withContext(message("str", str, "ch", ch), equalTo(expected)));
    }

    @Test @Parameters(method="parametersForSubstringAfter") @TestCaseName("{method} {index} {params}")
    public void substringAfterStr(String expected, String str, Character ch) {
        Str result = new Str(str).substringAfter(ch, Str.EMPTY);
        Str exp = expected == null ? null : new Str(expected);
        assertThat(result, withContext(message("str", str, "ch", ch), equalTo(exp)));
    }

    @Test @Parameters(method="parametersForSubstringAfter") @TestCaseName("{method} {index} {params}")
    public void substringAfterString(String expected, String str, Character ch) {
        String result = new Str(str).substringAfter(ch);
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

    @Test @Parameters(method="parametersForIsEmpty") @TestCaseName("{method} {index} {params}")
    public void isEmptyNullOption(boolean expected, String str) {
        boolean result = new Str(str).isEmpty(null);
        assertThat(result, withContext(message("str", str), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index}; params: {params}")
    public void isEmptyIgnoreWhitespace(boolean expected, String str) {
        boolean result = new Str(str).isEmpty(Str.Option.IGNORE_WHITESPACE);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForIsEmptyIgnoreWhitespace() {
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

    private List<Object[]> parametersForEquals() {
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
        Integer relResult = Integer.signum(result);
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
        Integer relResult = Integer.signum(result);
        assertThat(relResult, withContext(message("x", x, "y", y), equalTo(expected)));
    }
    
    private List<Object[]> parametersForCompareToNull() {
        return paramsList(params(-1, new Str("abc"),        null),
                          params(0,  new Str((String)null), null),
                          params(0,  new Str((String)null), new Str((String)null)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compareToWithOptions(Integer expected, String x, String y, EnumSet<Str.Option> options) {
        Integer result = new Str(x).compareTo(new Str(y), options);
        Integer relResult = Integer.signum(result);
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
    public void compareToWithOptionsVarargs(Integer expected, String x, String y, Str.Option ... options) {
        Integer result = new Str(x).compareTo(new Str(y), options);
        Integer relResult = Integer.signum(result);
        assertThat(relResult, withContext(message("x", x, "y", y, "options", options), equalTo(expected)));
    }
    
    private List<Object[]> parametersForCompareToWithOptionsVarargs() {
        return paramsList(params(0,  "abc",   "abc",  Str.Option.IGNORE_CASE),
                          params(0,  "Abc",   "abc",  Str.Option.IGNORE_CASE),
                          params(-1, "Abc",   "abc",  new Str.Option[0]),
                          params(1,  "abc",   "Abc",  new Str.Option[0]),
                          params(1,  "abcd",  "abc",  new Str.Option[0]),
                          params(-1, "abc",   "abcd", new Str.Option[0]),
                          params(1,  "abcde", "abc",  new Str.Option[0]),
                          params(1,  "abcde", "abc",  null));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compareToAlphanumeric(Integer expected, String x, String y) {
        Integer result = new Str(x).compareTo(new Str(y), EnumSet.of(Str.Option.ALPHANUMERIC));
        Integer relResult = Integer.signum(result);
        assertThat(relResult, equalTo(expected));
    }
    
    private List<Object[]> parametersForCompareToAlphanumeric() {
        return paramsList(params(0,  "1",  "1"),
                          params(0,  "a",  "a"),
                          params(-1, "2",  "3"),
                          params(1,  "3",  "2"),
                          params(-1, "9",  "12"),
                          params(1,  "12", "9"),
                          params(-1, "a2", "a3"),
                          params(1,  "a1", "a"),
                          params(-1, "a",  "a1"),
                          params(-1, "a9", "a12"));
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
        return paramsList(params(new Str("a"),  'a',  1),
                          params(new Str("aa"), 'a',  2),
                          params(new Str(""),   'a',  0),
                          params(new Str(""),   'a', -1));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void initChar(Str expected, char ch) {
        assertThat(new Str(ch), equalTo(expected));
    }
    
    private List<Object[]> parametersForInitChar() {
        return paramsList(params(new Str("a"),  'a'),
                          params(new Str("b"),  'b'));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void initRepeatString(Str expected, String str, int num) {
        assertThat(new Str(str, num), equalTo(expected));
    }
    
    private List<Object[]> parametersForInitRepeatString() {
        return paramsList(params(new Str("ab"),   "ab",  1),
                          params(new Str("abab"), "ab",  2),
                          params(new Str(""),     "a",   0),
                          params(new Str(""),     "a",  -1),
                          params(new Str(""),     "",    0),
                          params(new Str(""),     "",    1),
                          params(new Str(null),   null,  1));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void replaceAllWithOptions(Str expected, String str, String from, String to, EnumSet<Str.Option> options) {
        Str result = new Str(str).replaceAll(from, to, options);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForReplaceAllWithOptions() {
        return paramsList(params(new Str("One"), "One", "one", "1", EnumSet.noneOf(Str.Option.class)),
                          params(new Str("1"),   "One", "one", "1", EnumSet.of(Str.Option.IGNORE_CASE)));
    }    

    @Test @Parameters(method="parametersForReplace") @TestCaseName("{method} {index} {params}")
    public void replaceAllNoOptions(String expWithCase, String expIgnoreCase, String line, String from, String to) {
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

        pl.add(params(null,    null,     null,             "one", "1"));
        
        return pl;
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void replaceAllLiteral(Str expected, String str, String from, String to) {
        Str result = new Str(str).replaceAll(from, to);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForReplaceAllLiteral() {
        // not applied as regular expressions:
        return paramsList(params(new Str("1"),       "$one",        "$one",        "1"),
                          params(new Str("one two"), "one two",     "one.*",       "1"),
                          params(new Str("1 two"),   "one.* two",   "one.*",       "1"),
                          params(new Str("one"),     "one",         "(?:one|two)", "12"),
                          params(new Str("two"),     "two",         "(?:one|two)", "12"),
                          params(new Str("12"),      "(?:one|two)", "(?:one|two)", "12"));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void indexOfString(int expected, String x, String y, int pos, boolean ignoreCase) {
        String msg = message("x", x, "y", y, "pos", pos, "ignoreCase", ignoreCase);
        assertThat(new Str(x).indexOf(y, pos, ignoreCase), withContext(msg, equalTo(expected)));
    }
    
    private List<Object[]> parametersForIndexOfString() {
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
    public void ofString(Str expected, String s) {
        assertThat(Str.of(s), equalTo(expected));
    }
    
    private List<Object[]> parametersForOfString() {
        return paramsList(params(new Str("abc"), "abc"),
                          params(new Str("def"), "def"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void ofObject(Str expected, Object obj) {
        assertThat(Str.of(obj), equalTo(expected));
    }
    
    private List<Object[]> parametersForOfObject() {
        return paramsList(params(new Str("1"), Integer.valueOf(1)),
                          params(new Str("2.3"), Double.valueOf(2.3)));
    }

    @Test
    public void joinArray() {
        String[] ary = new String[] { "a", "b", "c" };
        String delim = "~";
        Str expected = new Str("a~b~c");
        Str result = Str.join(ary, delim);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void joinCollection() {
        Collection<String> coll = Arrays.asList("a", "b", "c", "d");
        String delim = "~";
        Str expected = new Str("a~b~c~d");
        Str result = Str.join(coll, delim);
        assertThat(result, equalTo(expected));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void scan(List<List<String>> expected, String s, String re) {
        Str str = new Str(s);
        List<List<String>> result = str.scan(Pattern.compile(re));
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForScan() {
        return paramsList(params(list(list("a"), list("a")), "aba", "a"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void matches(List<MatchData> expected, String s, String re) {
        Str str = new Str(s);
        List<MatchData> result = str.matches(Pattern.compile(re));
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForMatches() {
        return paramsList(params(list(new MatchData(StringArray.of("a"))),                                       "ab",    "a"),
                          params(list(new MatchData(StringArray.of("a")), new MatchData(StringArray.of("a"))),   "aba",   "a"),
                          params(list(new MatchData(StringArray.of("ab", "b"))),                                 "abaca", "a(b)"),
                          params(list(new MatchData(StringArray.of("abac", "b", "c"))),                          "abaca", "a(b).(.)"),
                          params(list(new MatchData(StringArray.of("a"))),                                       "ab",    "^a"),
                          params(list(new MatchData(StringArray.of("b"))),                                       "ab",    "b$"));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void firstNoArg(Character expected, String str) {
        Character result = new Str(str).first();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFirstNoArg() {
        return paramsList(params('a',  "abc"),
                          params('d',  "def"),
                          params(null, ""),
                          params(null, null));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void firstWithArg(String expected, String str, Integer num) {
        Str result = new Str(str).first(num);
        assertThat(result, expected == null ? nullValue() : equalTo(new Str(expected)));
    }
    
    private List<Object[]> parametersForFirstWithArg() {
        return paramsList(params("a",   "abc",  1),
                          params("ab",  "abc",  2),
                          params("abc", "abc",  4),
                          params("",    "abc",  0),
                          params("",    "abc", -1),
                          params(null,  null,   1));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void lastNoArg(Character expected, String str) {
        Character result = new Str(str).last();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLastNoArg() {
        return paramsList(params('c',  "abc"),
                          params('f',  "def"),
                          params(null, ""),
                          params(null, null));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void lastWithArg(String expected, String str, Integer num) {
        Str result = new Str(str).last(num);
        assertThat(result, expected == null ? nullValue() : equalTo(new Str(expected)));
    }
    
    private List<Object[]> parametersForLastWithArg() {
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

    private List<Object[]> parametersForTrim() {
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

    @Test @Parameters @TestCaseName("{method}(...) #{index}; params: {params}")
    public void trimLeft(String expected, String str) {
        Str result = new Str(str).trimLeft();
        assertThat(result, equalTo(new Str(expected)));
    }

    private List<Object[]> parametersForTrimLeft() {
        return paramsList(params("",    ""),
                          params("",    " "),
                          params("a",   "a"),
                          params("a",   " a"),
                          params("a",   "  a"),
                          params("a ",  "a "),
                          params("a ",  " a "),
                          params("a\t", "\ta\t"));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index}; params: {params}")
    public void trimRight(String expected, String str) {
        Str result = new Str(str).trimRight();
        assertThat(result, equalTo(new Str(expected)));
    }

    private List<Object[]> parametersForTrimRight() {
        return paramsList(params("",    ""),
                          params("",    " "),
                          params("a",   "a"),
                          params(" a",  " a"),
                          params("  a", "  a"),
                          params("a",   "a "),
                          params("a",   "a  "),
                          params(" a",  " a "),
                          params("\ta", "\ta\t"));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index}; params: {params}")
    public void escape(String expected, String str) {
        Str result = new Str(str).escape();
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForEscape() {
        return paramsList(params("",          ""),
                          params("\\ ",       " "),
                          params("a\\ b\\ c", "a b c"));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index}; params: {params}")
    public void plus(String expected, String str, String other) {
        Str result = new Str(str).plus(new Str(other));
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForPlus() {
        return paramsList(params("",   "",  ""),
                          params("ab", "a", "b"),
                          params("b",  "",  "b"));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index}; params: {params}")
    public void appendString(String expected, String str, String other) {
        Str result = new Str(str).append(other);
        assertThat(result, equalTo(expected));
    }

    @Test @Parameters(method="parametersForAppendString") @TestCaseName("{method}(...) #{index}; params: {params}")
    public void appendStr(String expected, String str, String other) {
        Str result = new Str(str).append(new Str(other));
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForAppendString() {
        return paramsList(params("",     "",   ""),   
                          params("abcd", "ab", "cd"), 
                          params("ab",   "",   "ab"));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index}; params: {params}")
    public void appendChar(String expected, String str, Character ch) {
        Str result = new Str(str).append(ch);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForAppendChar() {
        return paramsList(params("a",  "",  'a'),
                          params("ab", "a", 'b'));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index}; params: {params}")
    public void indexOfPattern(Integer expected, String str, String pat) {
        Pattern pattern = Pattern.compile(pat);
        Integer result = new Str(str).indexOf(pattern);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForIndexOfPattern() {
        return paramsList(params(0,    "",    ""),
                          params(null, "a",   "b"),
                          params(0,    "ab",  "a."),
                          params(0,    "ab",  "a"),
                          params(1,    "bab", "ab"),
                          params(0,    "aba", "a"));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index}; params: {params}")
    public void indexOfPatternWithOffset(Integer expected, String str, String pat, Integer offset) {
        Pattern pattern = Pattern.compile(pat);
        Integer result = new Str(str).indexOf(pattern, offset);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForIndexOfPatternWithOffset() {
        return paramsList(params(0,    "",    "",   0),
                          params(null, "a",   "b",  0),
                          params(0,    "ab",  "a",  0),
                          params(null, "ab",  "a",  1),
                          params(1,    "bab", "ab", 0),
                          params(1,    "bab", "ab", 1),
                          params(0,    "aba", "a",  0),
                          params(2,    "aba", "a",  1));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index}; params: {params}")
    public void matchRegexpNoOffset(MatchData expected, String pat, String str) {
        Regexp regexp = Regexp.of(pat);
        MatchData result = new Str(str).match(regexp);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForMatchRegexpNoOffset() {
        return paramsList(params(new MatchData(StringArray.of("")),   "",   ""),
                          params(null,                                "b",  "a"),
                          params(new MatchData(StringArray.of("ab")), "a.", "ab"),
                          params(new MatchData(StringArray.of("a")),  "a",  "ab"),
                          params(new MatchData(StringArray.of("ab")), "ab", "bab"),
                          params(new MatchData(StringArray.of("a")),  "a",  "a"));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index}; params: {params}")
    public void matchRegexpWithOffset(MatchData expected, String pat, String str, Integer offset) {
        Regexp regexp = Regexp.of(pat);
        MatchData result = new Str(str).match(regexp, offset);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForMatchRegexpWithOffset() {
        return paramsList(params(new MatchData(StringArray.of("a")), "a", "a", 0),
                          params(null,                               "a", "a", 1));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index}; params: {params}")
    public void toString(String expected, Str str) {
        String result = str.toString();
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForToString() {
        return paramsList(params("abc",  new Str("abc")), 
                          params("def",  new Str("def")), 
                          params("null", new Str("null")));
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
