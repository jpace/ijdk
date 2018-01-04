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
        assertEqual(toStr(expected), result, message("str", str, "ch", ch, "length", length));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padWithoutChar(String expected, String str, int length) {
        Str result = toStr(str).pad(length);
        assertEqual(toStr(expected), result, message("str", str, "length", length));
    }    

    public String pad(String str, char ch, int length) {
        return new Str(str).pad(ch, length).str();
    }

    public String pad(String str, int length) {
        return new Str(str).pad(length).str();
    }    

    public String padLeft(String str, char ch, int length) {
        return new Str(str).padLeft(ch, length).str();
    }    

    public String padLeft(String str, int length) {
        return new Str(str).padLeft(length).str();
    }    

    public String repeat(String str, int length) {
        return new Str(str).repeat(length);
    }    

    public String repeat(char ch, int length) {
        return new Str(ch).repeat(length);
    }

    public String left(String str, int length) {
        return new Str(str).left(length);
    }

    public String right(String str, int length) {
        return new Str(str).right(length);
    }

    public Character charAt(String str, int index) {
        return new Str(str).charAt(index);
    }

    public Character get(String str, int index) {
        return new Str(str).get(index);
    }    

    @Test
    @Parameters
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

    public String substring(String str, Integer fromIndex, Integer toIndex) {
        return new Str(str).substring(fromIndex, toIndex);
    }

    public boolean startsWith(String str, char ch) {
        return new Str(str).startsWith(ch);
    }

    public Integer indexOf(String str, Character ch) {
        return new Str(str).indexOf(ch);
    }    

    public boolean contains(String str, Character ch) {
        return new Str(str).contains(ch);
    }

    public String substringAfter(String str, Character ch) {
        return new Str(str).substringAfter(ch);
    }

    public String substringBefore(String str, Character ch) {
        return new Str(str).substringBefore(ch);
    }

    public Boolean eq(String a, String b) {
        return new Str(a).eq(b);
    }    

    public Boolean eqi(String a, String b) {
        return new Str(a).eqi(b);
    }

    public String snip(String str, int length) {
        return new Str(str).snip(length).str();
    }

    public boolean isEmpty(String str) {
        return new Str(str).isEmpty();
    }

    public int length(String str) {
        return new Str(str).length();
    }

    public String chomp(String str) {
        return new Str(str).chomp();
    }

    public String chompAll(String str) {
        return new Str(str).chompAll();
    }
    
    public String unquote(String str) {
        return new Str(str).unquote();
    }
    
    public String quote(String str) {
        return new Str(str).quote();
    }

    public Str toStr(String str) {
        return Str.of(str);
    }

    public String fromStr(Str str) {
        return str == null ? null : str.str();
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padLeftWithChar(String expected, String str, char ch, int length) {
        Str result = toStr(str).padLeft(ch, length);
        assertEqual(expected == null ? null : Str.of(expected), result, message("str", str, "ch", ch, "length", length));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void padLeftWithoutChar(String expected, String str, int length) {
        Str result = toStr(str).padLeft(length);
        assertEqual(expected == null ? null : Str.of(expected), result, message("str", str, "length", length));
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
                          params(0, null));
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

        s = str.left(4);
        System.out.println("s: " + s);

        s = str.right(3);
        System.out.println("s: " + s);

        Str text = Str.of("abc\ndef\n\n");
        s = text.chomp();

        System.out.println("s: " + s);
        System.out.println("text: " + text);

        s = text.chompAll();

        String[] lines = text.split("\n");
        System.out.println("lines: " + java.util.Arrays.asList(lines));

        List<String> chunks = Str.of("abc def\nghi\tjkl").toList();
        System.out.println("chunks: " + chunks);

        boolean b;

        b = str.startsWith("T");

        b = str.endsWith("t");        
    }
}
