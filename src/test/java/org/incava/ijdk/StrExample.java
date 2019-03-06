package org.incava.ijdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        String s = "abc";
        println("s", s);

        Str t = Str.of("abc");
        println("t", t);
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
        println("sb", sb);

        Str t = Str.join(ary, ", ");
        println("t", t);
    }
    
    @Test
    public void repeatString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; ++i) {
            sb.append("ho");
        }
        println("sb", sb);

        Str t = new Str("ho", 3);
        println("t", t);
    }
    
    @Test
    public void repeatCharacter() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            sb.append('m');
        }
        println("sb", sb);

        Str t = new Str('m', 8);
        println("t", t);
    }
    
}
