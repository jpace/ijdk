package org.incava.ijdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.incava.ijdk.collect.StringArray;
import org.incava.ijdk.lang.KeyValue;
import org.incava.ijdk.lang.Str;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.Assertions.message;

public class StrExample {
    public void println(String name, Object val) {
        System.out.printf("    // %-12s: %s\n", name, val);
        // System.out.printf("%s => %s\n", name, val);
    }

    public void println(String str) {
        System.out.printf("%s\n", str);
    }

    @Before
    public void setup() {
        println("");
    }
    
    @Test
    public void of() {
        println("of");
        
        String x = "abc";
        println("x", x);

        Str y = Str.of("abc");
        println("y", y);
    }
    
    @Test
    public void join() {
        println("join");
        
        String[] ary = new String[] { "abc", "def", "ghi" };
        
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String str : ary) {
            if (isFirst) {
                isFirst = false;
            }
            else {
                sb.append(", ");
            }
            sb.append(str);
        }
        String x = sb.toString();
        println("x", x);

        Str y = Str.join(ary, ", ");
        println("y", y);
    }
    
    @Test
    public void repeatString() {
        println("repeatString");
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; ++i) {
            sb.append("ho");
        }
        String x = sb.toString();
        println("x", x);

        Str y = new Str("ho", 3);
        println("y", y);
    }
    
    @Test
    public void repeatCharacter() {
        println("repeatCharacter");
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            sb.append('m');
        }
        String x = sb.toString();
        println("x", x);

        Str t = new Str('m', 8);
        println("t", t);
    }
    
    @Test
    public void split() {
        println("split");
        
        // String.split() works on a regular expression, not a literal

        int max = 3;

        Pattern pat = Pattern.compile(".", Pattern.LITERAL);
        String[] ary = pat.split("abc.def.ghi.jkl.mno", max);
        List<String> x = new ArrayList<>(Arrays.asList(ary));
        println("x", x);

        List<String> y = new Str("abc.def.ghi.jkl.mno").split(".", max);
        println("y", y);
    }

    @Test
    public void toList() {
        println("toList");
        
        String s = "first,    second  \nthird";
        
        List<String> x = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(s, " \t\n\r\f,");
        while (st.hasMoreTokens()) {
            String tk = st.nextToken();
            x.add(tk);
        }
        println("x", x);

        List<String> y = new Str(s).toList();
        println("y", y);
    }

    @Test
    public void pad() {
        println("pad");
        
        StringBuilder sb = new StringBuilder("abc");
        for (int idx = 3; idx < 8; ++idx) {
            sb.append('*');
        }
        String x = sb.toString();
        println("x", x);

        String y = new Str("abc").pad('*', 8).toString();
        println("y", y);
    }    

    @Test
    public void padLeft() {
        println("padLeft");
        
        StringBuilder sb = new StringBuilder("abc");
        for (int idx = 3; idx < 8; ++idx) {
            sb.insert(0, '*');
        }
        String x = sb.toString();
        println("x", x);

        String y = new Str("abc").padLeft('*', 8).toString();
        println("y", y);
    }    

    @Test
    public void repeat() {
        println("repeat");
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; ++i) {
            sb.append("ho");
        }
        String x = sb.toString();
        println("x", x);

        Str y = new Str("ho").repeat(3);
        println("y", y);
    }    

    @Test
    public void left() {
        println("left");
        
        String s = "abcdef";
        int n = 4;
        String x = n < s.length() ? s.substring(0, n) : "";
        println("x", x);

        Str y = new Str(s).left(n);
        println("y", y);
    }    

    @Test
    public void right() {
        println("right");
        
        String s = "abcdef";
        int n = 4;
        String x = n < s.length() ? s.substring(s.length() - n, s.length()) : "";
        println("x", x);

        Str y = new Str(s).right(n);
        println("y", y);
    }    

    @Test
    public void charAtPositive() {
        println("charAtPositive");
        
        String s = "abcdef";
        int n = 2;
        Character x = n < s.length() ? s.charAt(n) : null;
        println("x", x);

        Character y = new Str(s).charAt(n);
        // or y = new Str(s).get(n);
        println("y", y);
    }    

    @Test
    public void charNegative() {
        println("charNegative");
        
        String s = "abcdef";
        int n = -3;
        Character x = Math.abs(n) < s.length() ? s.charAt(s.length() + n) : null;
        println("x", x);

        Character y = new Str(s).charAt(n);
        // or y = new Str(s).get(n);
        println("y", y);
    }    

    @Test
    public void substringPositive() {
        println("substringPositive");
        
        String s = "abcdef";
        int i = 2;
        int j = 5;
        
        String x = s.substring(i, j + 1);
        println("x", x);

        String y = new Str(s).substring(i, j);
        // or get(i, j)
        println("y", y);
    }    

    @Test
    public void substringNegative() {
        println("substringNegative");
        
        String s = "abcdef";
        int i = 1;
        int j = -3;
        
        String x = s.substring(i, s.length() + j + 1);
        println("x", x);

        String y = new Str(s).substring(i, j);
        // or get(i, j)
        println("y", y);
    }    

    @Test
    public void substringBefore() {
        println("substringBefore");
        
        String s = "abcdef";

        int idx = s.indexOf('d');
        String x = s.substring(idx, s.length());
        println("x", x);

        String y = new Str(s).substringBefore('d');
        println("y", y);
    }    

    @Test
    public void substringAfter() {
        println("substringAfter");
        
        String s = "abcdef";

        int idx = s.indexOf('d');
        String x = s.substring(s.length() - idx + 1, s.length());
        println("x", x);

        String y = new Str(s).substringAfter('d');
        println("y", y);
    }

    @Test
    public void startsWithChar() {
        println("startsWithChar");
        
        String s = "abcdef";

        boolean x = s.length() >= 1 && s.charAt(0) == 'a';
        println("x", x);

        boolean y = new Str(s).startsWith('a');
        println("y", y);
    }

    @Test
    public void startsWithString() {
        println("startsWithString");
        
        String s = "abcdef";

        boolean x = s.length() >= 3 && s.substring(0, 3).equals("abc");
        println("x", x);

        boolean y = new Str(s).startsWith("abc");
        println("y", y);
    }

    @Test
    public void startsWithStringIgnoreCase() {
        println("startsWithStringIgnoreCase");
        
        String s = "AbCdEf";

        boolean x = s.length() >= 3 && s.substring(0, 3).equalsIgnoreCase("abc");
        println("x", x);

        boolean y = new Str(s).startsWith("abc", EnumSet.of(Str.Option.IGNORE_CASE));
        println("y", y);
    }

    @Test
    public void endsWithChar() {
        println("endsWithChar");
        
        String s = "abcdef";

        boolean x = s.length() >= 1 && s.charAt(s.length() - 1) == 'a';
        println("x", x);

        boolean y = new Str(s).endsWith('a');
        println("y", y);
    }

    @Test
    public void endsWithString() {
        println("endsWithString");
        
        String s = "abcdef";

        boolean x = s.length() >= 3 && s.substring(3, s.length()).equals("def");
        println("x", x);

        boolean y = new Str(s).endsWith("def");
        println("y", y);
    }

    @Test
    public void chomp() {
        println("chomp");
        
        String s = "abcdef\n";

        String x = s.length() >= 1 && "\r\n".indexOf(s.charAt(s.length() - 1)) >= 0 ? s.substring(0, s.length() - 1) : s;
        println("x", x);

        Str y = new Str(s).chomp();
        println("y", y);
    }

    @Test
    public void chompAll() {
        println("chompAll");
        
        String s = "abcdef\n";

        String x = s;
        while (x.length() >= 1 && "\r\n".indexOf(x.charAt(x.length() - 1)) >= 0) {
            x = x.substring(0, x.length() - 1);
        }
        println("x", x);

        Str y = new Str(s).chompAll();
        println("y", y);
    }    

    @Test
    public void contains() {
        println("contains");
        
        String s = "abcdef";

        boolean x = s.contains(String.valueOf('d'));
        println("x", x);

        boolean y = new Str(s).contains('d');
        println("y", y);
    }    

    @Test
    public void indexOf() {
        println("indexOf");
        
        String s = "abcdef";

        int x = s.indexOf('d');
        println("x", x);

        int y = new Str(s).indexOf('d');
        println("y", y);
    }    

    @Test
    public void eq() {
        println("eq");
        
        String s = "abcdef";
        String t = "abcdef";

        boolean x = s.equals(t);
        println("x", x);

        boolean y = new Str(s).eq(t);
        println("y", y);
    }    

    @Test
    public void eqi() {
        println("eqi");
        
        String s = "abcdef";
        String t = "abcdef";

        boolean x = s.equalsIgnoreCase(t);
        println("x", x);

        boolean y = new Str(s).eq(t);
        println("y", y);
    }    

    @Test
    public void snip() {
        println("snip");
        
        String s = "abcdef";

        String x = s.substring(0, 3) + '-';
        println("x", x);

        Str y = new Str(s).snip(4);
        println("y", y);
    }    

    @Test
    public void isEmpty() {
        println("isEmpty");
        
        String s = "abcdef";

        boolean x = s.trim().isEmpty();
        println("x", x);

        boolean y = new Str(s).isEmpty(Str.Option.IGNORE_WHITESPACE);
        println("y", y);
    }    

    @Test
    public void quote() {
        println("quote");
        
        String s = "abcdef";

        String x = '"' + s + '"';
        println("x", x);

        Str y = new Str(s).quote();
        println("y", y);
    }    

    @Test
    public void unquote() {
        println("unquote");
        
        String s = "\"abcdef\"";

        String x = s.substring(1, s.length() - 2);
        println("x", x);

        Str y = new Str(s).unquote();
        println("y", y);
    }    

    @Test
    public void compareToAlphanumeric() {
        println("compareToAlphanumeric");
        
        String s = "abc12";
        String t = "abc3";

        int x = Integer.valueOf(s.substring(3, s.length())).compareTo(Integer.valueOf(t.substring(3, t.length())));
        println("x", x);
        
        int y = new Str(s).compareTo(new Str(t), EnumSet.of(Str.Option.ALPHANUMERIC));
        println("y", y);
    }    

    @Test
    public void compareToIgnorecase() {
        println("compareToIgnorecase");
        
        String s = "Abc";
        String t = "def";

        int x = s.toUpperCase().compareTo(t.toUpperCase());
        println("x", x);
        
        int y = new Str(s).compareTo(new Str(t), EnumSet.of(Str.Option.IGNORE_CASE));
        println("y", y);
    }    

    @Test
    public void lt() {
        println("lt");
        
        String s = "Abc";
        String t = "def";

        boolean x = s.compareTo(t) < 0;
        println("x", x);
        
        boolean y = new Str(s).lt(new Str(t));
        println("y", y);
    }    

    @Test
    public void lte() {
        println("lte");
        
        String s = "Abc";
        String t = "def";

        boolean x = s.compareTo(t) <= 0;
        println("x", x);
        
        boolean y = new Str(s).lte(new Str(t));
        println("y", y);
    }

    @Test
    public void gt() {
        println("gt");
        
        String s = "Abc";
        String t = "def";

        boolean x = s.compareTo(t) > 0;
        println("x", x);
        
        boolean y = new Str(s).gt(new Str(t));
        println("y", y);
    }    

    @Test
    public void gte() {
        println("gte");
        
        String s = "Abc";
        String t = "def";

        boolean x = s.compareTo(t) >= 0;
        println("x", x);
        
        boolean y = new Str(s).gte(new Str(t));
        println("y", y);
    }    

    @Test
    public void firstChar() {
        println("firstChar");
        
        String s = "abcdef";

        char x = s.charAt(0);
        println("x", x);
        
        char y = new Str(s).first();
        println("y", y);
    }    

    @Test
    public void firstString() {
        println("firstString");
        
        String s = "abcdef";

        String x = s.substring(0, 3);
        println("x", x);
        
        Str y = new Str(s).first(3);
        println("y", y);
    }    

    @Test
    public void lastChar() {
        println("lastChar");
        
        String s = "abcdef";

        char x = s.charAt(s.length() - 1);
        println("x", x);
        
        char y = new Str(s).last();
        println("y", y);
    }    

    @Test
    public void lastString() {
        println("lastString");
        
        String s = "abcdef";

        String x = s.substring(s.length() - 3, s.length());
        println("x", x);
        
        Str y = new Str(s).last(3);
        println("y", y);
    }    

    @Test
    public void replaceAll() {
        println("replaceAll");
        
        String s = "ab.ac.ad";
        String t = ".a";
        String u = ".x";

        String x = "";
        int prev = 0;
        int idx = s.indexOf(t, prev);
        while (idx >= 0) {
            x += s.substring(prev, idx);
            x += u;
            prev = idx + t.length();
            idx = s.indexOf(t, prev);
        }
        x += s.substring(prev, s.length());
        println("x", x);
        
        Str y = new Str(s).replaceAll(t, u);
        println("y", y);
    }    

    @Test
    public void replaceAllIgnoreCase() {
        println("replaceAllIgnoreCase");
        
        String s = "ab.Ac.ad";
        String t = ".a";
        String u = ".x";

        String x = "";
        String su = s.toUpperCase();
        String tu = t.toUpperCase();
        int prev = 0;
        int idx = su.indexOf(tu, prev);
        while (idx >= 0) {
            x += s.substring(prev, idx);
            x += u;
            prev = idx + t.length();
            idx = su.indexOf(tu, prev);
        }
        x += s.substring(prev, s.length());
        println("x", x);
        
        Str y = new Str(s).replaceAll(t, u, EnumSet.of(Str.Option.IGNORE_CASE));
        println("y", y);
    }    

    @Test
    public void indexOfIgnoreCase() {
        println("indexOfIgnoreCase");
        
        String s = "AbCdEf";
        String t = "bc";
        
        int x = s.toUpperCase().indexOf(t, 0);
        println("x", x);
        
        int y = new Str(s).indexOf(t, 0, true);
        println("y", y);
    }    

    @Test
    public void trimLeft() {
        println("trimLeft");
        
        String s = "   abc def   ";

        int idx = 0;
        while (idx < s.length() && Character.isWhitespace(s.charAt(idx))) {
            ++idx;
        }
        String x = idx < s.length() ? s.substring(idx, s.length()) : "";
        println("x", x);
        
        Str y = new Str(s).trimLeft();
        println("y", y);
    }    

    @Test
    public void trimRight() {
        println("trimRight");
        
        String s = "   abc def   ";

        int idx = s.length() - 1;
        while (idx >= 0 && Character.isWhitespace(s.charAt(idx))) {
            --idx;
        }
        String x = idx >= 0 ? s.substring(0, idx + 1) : "";
        println("x", x);
        
        Str y = new Str(s).trimRight();
        println("y", y);
    }
}
