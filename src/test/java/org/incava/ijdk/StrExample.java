package org.incava.ijdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        String x = "abc";
        println("x", x);

        Str y = Str.of("abc");
        println("y", y);
    }
    
    @Test
    public void join() {
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
        String s = "abcdef";
        int n = 4;
        String x = n < s.length() ? s.substring(0, n) : "";
        println("x", x);

        Str y = new Str(s).left(n);
        println("y", y);
    }    

    @Test
    public void right() {
        String s = "abcdef";
        int n = 4;
        String x = n < s.length() ? s.substring(s.length() - n, s.length()) : "";
        println("x", x);

        Str y = new Str(s).right(n);
        println("y", y);
    }    

    @Test
    public void charAtPositive() {
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
        String s = "abcdef";

        int idx = s.indexOf('d');
        String x = s.substring(idx, s.length());
        println("x", x);

        String y = new Str(s).substringBefore('d');
        println("y", y);
    }    

    @Test
    public void substringAfter() {
        String s = "abcdef";

        int idx = s.indexOf('d');
        String x = s.substring(s.length() - idx + 1, s.length());
        println("x", x);

        String y = new Str(s).substringAfter('d');
        println("y", y);
    }    
}
