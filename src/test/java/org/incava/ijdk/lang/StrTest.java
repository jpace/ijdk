package org.incava.ijdk.lang;

import java.util.Collection;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.ijdk.lang.StringTest;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

public class StrTest extends StringTest {
    public String[] split(String str, char delim, int max) {
        return new Str(str).split(delim, max);
    }

    public String[] split(String str, String delim, int max) {
        return new Str(str).split(delim, max);
    }

    public List<String> toList(String str) {
        return new Str(str).toList();
    }

    public String pad(String str, char ch, int length) {
        return new Str(str).pad(ch, length);
    }

    public String pad(String str, int length) {
        return new Str(str).pad(length);
    }    

    public String padLeft(String str, char ch, int length) {
        return new Str(str).padLeft(ch, length);
    }    

    public String padLeft(String str, int length) {
        return new Str(str).padLeft(length);
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

    public String join(String[] ary, String delim) {
        return Str.join(ary, delim).str();
    }

    public String join(Collection<String> coll, String delim) {
        return Str.join(coll, delim).str();
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
        return new Str(str).snip(length);
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

    @Test @Parameters(method="parametersForEquals") @TestCaseName("{method} {index} {params}")
    public void equalsObject(boolean expected, String a, Object b) {
        Str sa = new Str(a);
        boolean result = sa.equals(b);
        assertThat(result, withContext(message("a", a, "b", b), equalTo(expected)));
    }    

    @Test @Parameters(method="parametersForEquals") @TestCaseName("{method} {index} {params}")
    public void equalsString(boolean expected, String a, String b) {
        Str sa = new Str(a);
        boolean result = sa.equals(b);
        assertThat(result, withContext(message("a", a, "b", b), equalTo(expected)));
    }    

    @Test @Parameters(method="parametersForEquals") @TestCaseName("{method} {index} {params}")
    public void equalsStr(boolean expected, String a, String b) {
        Str sa = new Str(a);
        Str sb = new Str(b);
        boolean result = sa.equals(sb);
        assertThat(result, withContext(message("a", a, "b", b), equalTo(expected)));
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
        boolean result = new Str(str).endsWith(ch);
        assertThat(result, withContext(message("str", str, "ch", ch), equalTo(expected)));
    }
    
    private List<Object[]> parametersForEndsWithChar() {
        return paramsList(params(true, "abc", 'c'),
                          params(false, "abc", ' '),
                          params(false, null, 'c'),
                          params(false, "abc", 'd'));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void endsWithString(boolean expected, String str, String s) {
        boolean result = new Str(str).endsWith(s);
        assertThat(result, withContext(message("str", str, "s", s), equalTo(expected)));
    }
    
    private List<Object[]> parametersForEndsWithString() {
        return paramsList(params(true, "abc", "c"),
                          params(true, "abc", "bc"),
                          params(true, "abc", "abc"),
                          params(false, "abcd", "abc"),
                          params(true, "abc", ""),
                          params(false, null, ""),
                          params(true, "abc.x", ".x"),
                          params(false, "abc.x", ".y"),
                          params(false, "abc", "d"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compareTo(Integer expected, String x, String y) {
        Integer result = new Str(x).compareTo(new Str(y));
        Integer relResult = result == 0 ? 0 : (result / Math.abs(result));
        assertThat(relResult, withContext(message("x", x, "y", y), equalTo(expected)));
    }
    
    private List<Object[]> parametersForCompareTo() {
        return paramsList(params(0, "abc", "abc"),
                          params(-1, "abc", "def"),
                          params(1, "def", "abc"),
                          params(1, "abc", null),
                          params(0, null, null),
                          params(-1, null, "abc"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void hashCodeTest(Integer expected, String x) {
        Integer result = new Str(x).hashCode();
        assertThat(result, withContext(message("x", x), equalTo(expected)));
    }
    
    private List<Object[]> parametersForHashCodeTest() {
        return paramsList(params("abc".hashCode(), "abc"),
                          params(0, null));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void initRepeat(Str expected, String str, int num) {
        Str result = new Str(str, num);
        assertThat(result, withContext(message("str", str, "num", num), equalTo(expected)));
    }
    
    private List<Object[]> parametersForInitRepeat() {
        return paramsList(params(new Str("a"), "a", 1),
                          params(new Str("aa"), "a", 2),
                          params(new Str(""), "a", 0),
                          params(new Str(""), "a", -0),
                          params(new Str(""), "", 0),
                          params(new Str(""), "", 1),
                          params(new Str(null), null, 1));
    }
}
