package org.incava.ijdk.lang;

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

@RunWith(JUnitParamsRunner.class)
public class TestStr extends StringTest {
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
        return paramsList(params(null, null, 0),
                          params(0, "abcd", 0),
                          params(1, "abcd", 1),
                          params(3, "abcd", 3),
                          params(null, "abcd", 4),
                          params(3, "abcd", -1),
                          params(2, "abcd", -2),
                          params(1, "abcd", -3),
                          params(null, "abcd", -5));
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

    @Test
    @Parameters
    public void equalsString(boolean expected, String a, String b) {
        Str sa = new Str(a);
        boolean result = sa.equals(b);
        assertEqual(expected, result);
    }
    
    private java.util.List<Object[]> parametersForEqualsString() {
        return paramsList(params(false, null, ""),
                          params(false, "", null),
                          params(true, null, null),
                          params(false, null, "a"),
                          params(false, "a", null),
                          params(true, "a", "a"),
                          params(false, "a", "b"),
                          params(false, "a", "A"),
                          params(false, "a", "ab"));
    }

    // equals Str

    public Boolean assertEqualStr(Boolean expected, String a, String b) {
        Boolean result = new Str(a).equals(new Str(b));
        return assertEqual(expected, result, message("a", a, "b", b));
    }

    @Test
    public void testEqualsStrNullEmptyString() {
        assertEqualStr(false, null, "");
    }

    @Test
    public void testEqualsStrEmptyStringNull() {
        assertEqualStr(false, "", null);
    }

    @Test
    public void testEqualsStrNullNull() {
        assertEqualStr(true, null, null);
    }

    @Test
    public void testEqualsStrNullNonEmptyString() {
        assertEqualStr(false, null, "a");
    }

    @Test
    public void testEqualsStrNonEmptyStringNull() {
        assertEqualStr(false, "a", null);
    }

    @Test
    public void testEqualsStrCharCharMatch() {
        assertEqualStr(true, "a", "a");
    }

    @Test
    public void testEqualsStrCharCharNoMatch() {
        assertEqualStr(false, "a", "b");
    }

    @Test
    public void testEqualsStrCharCharMismatchedCase() {
        assertEqualStr(false, "a", "A");
    }

    @Test
    public void testEqualsStrDifferentLengths() {
        assertEqualStr(false, "a", "ab");
    }

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void endsWithChar(boolean expected, String str, char ch) {
        boolean result = new Str(str).endsWith(ch);
        assertEqual(expected, result, message("str", str, "ch", ch));
    }
    
    private List<Object[]> parametersForEndsWithChar() {
        return paramsList(params(true, "abc", 'c'),
                          params(false, "abc", ' '),
                          params(false, null, 'c'),
                          params(false, "abc", 'd'));
    }

    @Test
    @Parameters
    @TestCaseName("{index} {method} {params}")
    public void endsWithString(boolean expected, String str, String s) {
        boolean result = new Str(str).endsWith(s);
        assertEqual(expected, result, message("str", str, "s", s));
    }
    
    private List<Object[]> parametersForEndsWithString() {
        return paramsList(params(true, "abc", "c"),
                          params(true, "abc", "bc"),
                          params(true, "abc", "abc"),
                          //$$$ fix this -- it's inconsistent with String#endsWith:
                          params(false, "abc", ""),
                          params(false, null, ""),
                          params(false, "abc", "d"));
    }

    @Test
    public void testCompareTo() {
        assertEqual(true, new Str("abc").compareTo(new Str("abc")) == 0);
        assertEqual(true, new Str("abc").compareTo(new Str("def")) < 0);
        assertEqual(true, new Str("def").compareTo(new Str("abc")) > 0);
        assertEqual(true, new Str("abc").compareTo(new Str(null)) > 0);
        assertEqual(true, new Str(null).compareTo(new Str(null)) == 0);
        assertEqual(true, new Str(null).compareTo(new Str("abc")) < 0);
    }

    @Test
    public void testHashCode() {
        assertEqual("abc".hashCode(), new Str("abc").hashCode());
        assertEqual(0, new Str(null).hashCode());
    }
}
