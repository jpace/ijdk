package org.incava.ijdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.StringArray;
import org.incava.ijdk.lang.Range;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;
import static org.incava.attest.ExistsMatcher.exists;

public class Cookbook {
    public void println(String name, Object val) {
        System.out.printf("    // %-20s: %s\n", name, val);
        // System.out.printf("%s => %s\n", name, val);
    }

    public void code(String code, Object result) {
        System.out.printf("    %-25s // %s\n", code + ";", result);
    }

    public void println(String str) {
        System.out.printf("// %s\n", str);
    }
    
    @Test
    public void firstLast() {
        println("");
        println("firstLast");
        Range rg = new Range(3, 7);
        code("rg.toString()", rg.toString());
        
        code("rg.first()", rg.first());
        code("rg.last()", rg.last());

        println("iteration (inclusive)");
        for (Integer num : rg) {
            println("num", num);
        }

        println("iteration (exclusive)");
        for (Integer num : rg.upTo()) {
            println("num", num);
        }

        println("includes");
        code("rg.includes(4)", rg.includes(4));
        code("rg.includes(9)", rg.includes(9));

        println("to array");
        Array<Integer> ary = rg.toArray();
        code("ary", ary);

        println("comparison");
        Range other = new Range(5, 10);
        println("other", other);
        code("rg.equals(other)", rg.equals(other));
        code("rg.compareTo(other)", rg.compareTo(other));
    }
}
