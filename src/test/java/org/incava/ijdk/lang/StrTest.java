package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public class StrTest extends StringTest {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void splitCharDelim(String[] expected, String str, char delim, int max) {
        String[] result = new Str(str).split(delim, max);
        assertEqual(expected, result, message("str", str, "delim", delim, "max", max));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void splitStringDelim(String[] expected, String str, String delim, int max) {
        String[] result = new Str(str).split(delim, max);
        assertEqual(expected, result, message("str", str, "delim", delim, "max", max));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toList(String[] expected, String str) {
        List<String> result = new Str(str).toList();
        assertEqual(expected == null ? null : Arrays.asList(expected), result, message("str", str));
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
        assertEqual(expected, result, message("str", str, "index", index));
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
        assertThat(result, withContext(message("str", str, "ch", ch), equalTo(expected)));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void indexOf(Integer expected, String str, Character ch) {
        Integer result = new Str(str).indexOf(ch);
        assertThat(result, withContext(message("str", str, "ch", ch), equalTo(expected)));
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

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void length(int expected, String str) {
        int result = new Str(str).length();
        assertThat(result, withContext(message("str", str), equalTo(expected)));
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
        String result = new Str(str).quote();
        assertEqual(expected, result, message("str", str));
    }

    public Str toStr(String str) {
        return Str.of(str);
    }

    public String fromStr(Str str) {
        return str == null ? null : str.str();
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
                          params(1,  "abc", null),  
                          params(0,  null,  null),  
                          params(-1, null,  "abc"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void hashCodeTest(Integer expected, String x) {
        assertThat(new Str(x).hashCode(), withContext(message("x", x), equalTo(expected)));
    }
    
    private List<Object[]> parametersForHashCodeTest() {
        return paramsList(params("abc".hashCode(), "abc"),
                          params(0,                null));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void initRepeat(Str expected, String str, int num) {
        assertThat(new Str(str, num), withContext(message("str", str, "num", num), equalTo(expected)));
    }
    
    private List<Object[]> parametersForInitRepeat() {
        return paramsList(params(new Str("a"),  "a",  1),
                          params(new Str("aa"), "a",  2),  
                          params(new Str(""),   "a",  0),  
                          params(new Str(""),   "a",  -0), 
                          params(new Str(""),   "",   0),  
                          params(new Str(""),   "",   1),  
                          params(new Str(null), null, 1));
    }

    @Test @Parameters(method="parametersForReplace") @TestCaseName("{method} {index} {params}")
    public void replaceAll(String expWithCase, String expIgnoreCase, String line, String from, String to) {
        String msg = message("line", line, "from", from, "to", to);
        Str str = toStr(line);
        assertThat(str.replaceAll(from, to), withContext(msg, equalTo(expWithCase)));
    }

    @Test @Parameters(method="parametersForReplace") @TestCaseName("{method} {index} {params}")
    public void replaceAllIgnoreCase(String expWithCase, String expIgnoreCase, String line, String from, String to) {
        String msg = message("line", line, "from", from, "to", to);
        Str str = toStr(line);
        assertThat(str.replaceAllIgnoreCase(from, to), withContext(msg, equalTo(expIgnoreCase)));
    }
    
    private List<Object[]> parametersForReplace() {
        List<Object[]> pl = paramsList();
        
        pl.add(params("1",     "1",      "one",            "one", "1"));
        pl.add(params("ONE",   "1",      "ONE",            "one", "1"));
        pl.add(params("One",   "1",      "One",            "one", "1"));
        
        pl.add(params("1 two", "1 two",  "one two",        "one", "1"));
        pl.add(params("11",    "11",     "oneone",         "one", "1"));
        pl.add(params("1 1",   "1 1",    "one one",        "one", "1"));
        
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
        assertEqual(expected, result, message("ary", ary, "delim", delim));
    }
    
    private List<Object[]> parametersForJoinArray() {
        return paramsList(params(null,      (String[])null,                      "~"),
                          params("",        new String[] { },                    null),
                          params("abcd",    new String[] { "a", "b", "c", "d" }, null),
                          params("abcd",    new String[] { "a", "b", "c", "d" }, ""),
                          params("",        new String[] { "" },                 "~"),
                          params("a~b~c~d", new String[] { "a", "b", "c", "d" }, "~"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void joinCollection(String expected, Collection<String> coll, String delim) {
        String result = Str.join(coll, delim).str();
        assertEqual(expected, result, message("coll", coll, "delim", delim));
    }
    
    private List<Object[]> parametersForJoinCollection() {
        return paramsList(params(null,      (ArrayList<String>)null,           "~"),
                          params("",        Arrays.<String>asList(),           null),
                          params("abcd",    Arrays.asList("a", "b", "c", "d"), null),
                          params("abcd",    Arrays.asList("a", "b", "c", "d"), ""),
                          params("",        new ArrayList<String>(),           "~"),
                          params("a~b~c~d", Arrays.asList("a", "b", "c", "d"), "~"));
    }
    
    @Test
    public void example() {
        Str str = Str.of("This Is a Test");
        System.out.println("str: " + str);

        Character c;

        c = str.get(0);

        c = str.get(-1);
        c = str.get(-2);

        String s;
        Str t;

        t = str.left(4);
        System.out.println("t: " + t);

        t = str.right(3);
        System.out.println("t: " + t);

        Str text = Str.of("abc\ndef\n\n");
        t = text.chomp();

        System.out.println("t: " + t);
        System.out.println("text: " + text);

        t = text.chompAll();

        String[] lines = text.split("\n");
        System.out.println("lines: " + java.util.Arrays.asList(lines));

        List<String> chunks = Str.of("abc def\nghi\tjkl").toList();
        System.out.println("chunks: " + chunks);

        boolean b;

        b = str.startsWith("T");

        b = str.endsWith("t");        
    }
}
