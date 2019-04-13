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
    public void printName(String str) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        StackTraceElement whence = stack[2];
        String methodName = whence.getMethodName();
        System.out.println("# " + methodName);
        // String fileName = whence.getFileName();
        // System.out.println("fileName: " + fileName);
        Integer line = whence.getLineNumber();
        System.out.println(line);
    }

    public void printValue(String name, Object value) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        StackTraceElement whence = stack[2];
        String methodName = whence.getMethodName();
        // System.out.println("methodName: " + methodName);
        Integer line = whence.getLineNumber();
        System.out.printf("%d:    // %-12s: %s\n", line, name, value);
    }

    @Before
    public void setup() {
        System.out.println();
    }
    
    @Test
    public void of() {
        String x = "abc";
        printValue("x", x);

        Str y = Str.of("abc");
        printValue("y", y);
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
        printValue("x", x);

        Str y = Str.join(ary, ", ");
        printValue("y", y);
    }
    
    @Test
    public void repeatString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; ++i) {
            sb.append("ho");
        }
        String x = sb.toString();
        printValue("x", x);

        Str y = new Str("ho", 3);
        printValue("y", y);
    }
    
    @Test
    public void repeatCharacter() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            sb.append('m');
        }
        String x = sb.toString();
        printValue("x", x);

        Str y = new Str('m', 8);
        printValue("y", y);
    }
    
    @Test
    public void split() {
        // String.split() works on a regular expression, not a literal

        int max = 3;

        Pattern pat = Pattern.compile(".", Pattern.LITERAL);
        String[] ary = pat.split("abc.def.ghi.jkl.mno", max);
        List<String> x = new ArrayList<>(Arrays.asList(ary));
        printValue("x", x);

        List<String> y = new Str("abc.def.ghi.jkl.mno").split(".", max);
        printValue("y", y);
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
        printValue("x", x);

        List<String> y = new Str(s).toList();
        printValue("y", y);
    }

    @Test
    public void pad() {
        StringBuilder sb = new StringBuilder("abc");
        for (int idx = 3; idx < 8; ++idx) {
            sb.append('*');
        }
        String x = sb.toString();
        printValue("x", x);

        String y = new Str("abc").pad('*', 8).toString();
        printValue("y", y);
    }

    @Test
    public void padLeft() {
        StringBuilder sb = new StringBuilder("abc");
        for (int idx = 3; idx < 8; ++idx) {
            sb.insert(0, '*');
        }
        String x = sb.toString();
        printValue("x", x);

        String y = new Str("abc").padLeft('*', 8).toString();
        printValue("y", y);
    }

    @Test
    public void repeat() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; ++i) {
            sb.append("ho");
        }
        String x = sb.toString();
        printValue("x", x);

        Str y = new Str("ho").repeat(3);
        printValue("y", y);
    }

    @Test
    public void left() {
        String s = "abcdef";
        int n = 4;
        String x = s.substring(0, n);
        printValue("x", x);

        Str y = new Str(s).left(n);
        printValue("y", y);
    }

    @Test
    public void right() {
        String s = "abcdef";
        int n = 4;
        String x = s.substring(s.length() - n, s.length());
        printValue("x", x);

        Str y = new Str(s).right(n);
        printValue("y", y);
    }

    @Test
    public void charAtPositive() {
        String s = "abcdef";
        int n = 2;
        Character x = n < s.length() ? s.charAt(n) : null;
        printValue("x", x);

        Character y = new Str(s).charAt(n);
        // or y = new Str(s).get(n);
        printValue("y", y);
    }

    @Test
    public void charAtNegative() {
        String s = "abcdef";
        int n = -3;
        Character x = Math.abs(n) < s.length() ? s.charAt(s.length() + n) : null;
        printValue("x", x);

        Character y = new Str(s).charAt(n);
        // or y = new Str(s).get(n);
        printValue("y", y);
    }

    @Test
    public void substringPositive() {
        String s = "abcdef";
        int i = 2;
        int j = 5;
        
        String x = s.substring(i, j + 1);
        printValue("x", x);

        String y = new Str(s).substring(i, j);
        // or get(i, j)
        printValue("y", y);
    }

    @Test
    public void substringNegative() {
        String s = "abcdef";
        int i = 1;
        int j = -3;
        
        String x = s.substring(i, s.length() + j + 1);
        printValue("x", x);

        String y = new Str(s).substring(i, j);
        // or get(i, j)
        printValue("y", y);
    }

    @Test
    public void substringBefore() {
        String s = "abcdef";

        int idx = s.indexOf('d');
        String x = s.substring(0, idx);
        printValue("x", x);

        String y = new Str(s).substringBefore('d');
        printValue("y", y);
    }

    @Test
    public void substringAfter() {
        String s = "abcdef";

        int idx = s.indexOf('d');
        String x = s.substring(s.length() - idx + 1, s.length());
        printValue("x", x);

        String y = new Str(s).substringAfter('d');
        printValue("y", y);
    }

    @Test
    public void startsWithChar() {
        String s = "abcdef";

        boolean x = s.length() >= 1 && s.charAt(0) == 'a';
        printValue("x", x);

        boolean y = new Str(s).startsWith('a');
        printValue("y", y);
    }

    @Test
    public void startsWithString() {
        String s = "abcdef";

        boolean x = s.length() >= 3 && s.substring(0, 3).equals("abc");
        printValue("x", x);

        boolean y = new Str(s).startsWith("abc");
        printValue("y", y);
    }

    @Test
    public void startsWithStringIgnoreCase() {
        String s = "AbCdEf";

        boolean x = s.length() >= 3 && s.substring(0, 3).equalsIgnoreCase("abc");
        printValue("x", x);

        boolean y = new Str(s).startsWith("abc", Str.Option.IGNORE_CASE);
        printValue("y", y);
    }

    @Test
    public void endsWithChar() {
        String s = "abcdef";

        boolean x = s.length() >= 1 && s.charAt(s.length() - 1) == 'a';
        printValue("x", x);

        boolean y = new Str(s).endsWith('a');
        printValue("y", y);
    }

    @Test
    public void endsWithString() {
        String s = "abcdef";

        boolean x = s.length() >= 3 && s.substring(3, s.length()).equals("def");
        printValue("x", x);

        boolean y = new Str(s).endsWith("def");
        printValue("y", y);
    }

    @Test
    public void chomp() {
        String s = "abcdef\n";

        String x = s.length() >= 1 && "\r\n".indexOf(s.charAt(s.length() - 1)) >= 0 ? s.substring(0, s.length() - 1) : s;
        printValue("x", x);

        Str y = new Str(s).chomp();
        printValue("y", y);
    }

    @Test
    public void chompAll() {
        String s = "abcdef\n";

        String x = s;
        while (x.length() >= 1 && "\r\n".indexOf(x.charAt(x.length() - 1)) >= 0) {
            x = x.substring(0, x.length() - 1);
        }
        printValue("x", x);

        Str y = new Str(s).chompAll();
        printValue("y", y);
    }

    @Test
    public void contains() {
        String s = "abcdef";

        boolean x = s.contains(String.valueOf('d'));
        printValue("x", x);

        boolean y = new Str(s).contains('d');
        printValue("y", y);
    }

    @Test
    public void indexOf() {
        String s = "abcdef";

        int x = s.indexOf('d');
        printValue("x", x);

        int y = new Str(s).indexOf('d');
        printValue("y", y);
    }

    @Test
    public void eq() {
        String s = "abcdef";
        String t = "abcdef";

        boolean x = s.equals(t);
        printValue("x", x);

        boolean y = new Str(s).eq(t);
        printValue("y", y);
    }

    @Test
    public void eqi() {
        String s = "abcdef";
        String t = "abcdef";

        boolean x = s.equalsIgnoreCase(t);
        printValue("x", x);

        boolean y = new Str(s).eq(t);
        printValue("y", y);
    }

    @Test
    public void snip() {
        String s = "abcdef";

        String x = s.substring(0, 3) + '-';
        printValue("x", x);

        Str y = new Str(s).snip(4);
        printValue("y", y);
    }

    @Test
    public void isEmpty() {
        String s = "abcdef";

        boolean x = s.trim().isEmpty();
        printValue("x", x);

        boolean y = new Str(s).isEmpty(Str.Option.IGNORE_WHITESPACE);
        printValue("y", y);
    }

    @Test
    public void quote() {
        String s = "abcdef";

        String x = '"' + s + '"';
        printValue("x", x);

        Str y = new Str(s).quote();
        printValue("y", y);
    }

    @Test
    public void unquote() {
        String s = "\"abcdef\"";

        String x = s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"' ? s.substring(1, s.length() - 1) : s;
        printValue("x", x);

        Str y = new Str(s).unquote();
        printValue("y", y);
    }

    @Test
    public void compareToAlphanumeric() {
        String s = "abc12";
        String t = "abc3";

        int x = Integer.valueOf(s.substring(3, s.length())).compareTo(Integer.valueOf(t.substring(3, t.length())));
        printValue("x", x);
        
        int y = new Str(s).compareTo(new Str(t), Str.Option.ALPHANUMERIC);
        printValue("y", y);
    }

    @Test
    public void compareToIgnorecase() {
        String s = "Abc";
        String t = "def";

        int x = s.toUpperCase().compareTo(t.toUpperCase());
        printValue("x", x);
        
        int y = new Str(s).compareTo(new Str(t), Str.Option.IGNORE_CASE);
        printValue("y", y);
    }

    @Test
    public void lt() {
        String s = "Abc";
        String t = "def";

        boolean x = s.compareTo(t) < 0;
        printValue("x", x);
        
        boolean y = new Str(s).lt(new Str(t));
        printValue("y", y);
    }

    @Test
    public void lte() {
        String s = "Abc";
        String t = "def";

        boolean x = s.compareTo(t) <= 0;
        printValue("x", x);
        
        boolean y = new Str(s).lte(new Str(t));
        printValue("y", y);
    }

    @Test
    public void gt() {
        String s = "Abc";
        String t = "def";

        boolean x = s.compareTo(t) > 0;
        printValue("x", x);
        
        boolean y = new Str(s).gt(new Str(t));
        printValue("y", y);
    }

    @Test
    public void gte() {
        String s = "Abc";
        String t = "def";

        boolean x = s.compareTo(t) >= 0;
        printValue("x", x);
        
        boolean y = new Str(s).gte(new Str(t));
        printValue("y", y);
    }

    @Test
    public void firstChar() {
        String s = "abcdef";

        char x = s.charAt(0);
        printValue("x", x);
        
        char y = new Str(s).first();
        printValue("y", y);
    }

    @Test
    public void firstString() {
        String s = "abcdef";

        String x = s.substring(0, 3);
        printValue("x", x);
        
        Str y = new Str(s).first(3);
        printValue("y", y);
    }

    @Test
    public void lastChar() {
        String s = "abcdef";

        char x = s.charAt(s.length() - 1);
        printValue("x", x);
        
        char y = new Str(s).last();
        printValue("y", y);
    }

    @Test
    public void lastString() {
        String s = "abcdef";

        String x = s.substring(s.length() - 3, s.length());
        printValue("x", x);
        
        Str y = new Str(s).last(3);
        printValue("y", y);
    }

    @Test
    public void replaceAll() {
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
        printValue("x", x);
        
        Str y = new Str(s).replaceAll(t, u);
        printValue("y", y);
    }

    @Test
    public void replaceAllIgnoreCase() {
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
        printValue("x", x);
        
        Str y = new Str(s).replaceAll(t, u, Str.Option.IGNORE_CASE);
        printValue("y", y);
    }

    @Test
    public void indexOfIgnoreCase() {
        String s = "AbCdEf";
        String t = "bc";
        
        int x = s.toUpperCase().indexOf(t, 0);
        printValue("x", x);
        
        int y = new Str(s).indexOf(t, 0, true);
        printValue("y", y);
    }

    @Test
    public void trimLeft() {
        String s = "   abc def   ";

        int idx = 0;
        while (idx < s.length() && Character.isWhitespace(s.charAt(idx))) {
            ++idx;
        }
        String x = idx < s.length() ? s.substring(idx, s.length()) : "";
        printValue("x", "\"" + x + "\"");
        
        Str y = new Str(s).trimLeft();
        printValue("y", "\"" + y + "\"");
    }

    @Test
    public void trimRight() {
        String s = "   abc def   ";

        int idx = s.length() - 1;
        while (idx >= 0 && Character.isWhitespace(s.charAt(idx))) {
            --idx;
        }
        String x = idx >= 0 ? s.substring(0, idx + 1) : "";
        printValue("x", "\"" + x + "\"");
        
        Str y = new Str(s).trimRight();
        printValue("y", "\"" + y + "\"");
    }
}
