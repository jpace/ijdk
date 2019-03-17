package org.incava.ijdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.incava.ijdk.collect.Iterate;
import org.incava.ijdk.collect.StringArray;
import org.incava.ijdk.lang.KeyValue;
import org.junit.Before;
import org.junit.Test;

public class IterateExample {
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
    public void count() {
        println("count - old");
        for (int i = 0; i < 3; ++i) {
            println("i", i);
        }
        
        println("count - new");
        for (Integer i : Iterate.count(3)) {
            println("i", i);
        }
    }

    @Test
    public void overArray() {
        String[] ary = new String[] { "a", "b", "c" };

        println("over array - old");
        for (String s : ary) {
            println("s", s);
        }
        
        println("over array - new");
        for (String s : Iterate.over(ary)) {
            println("s", s);
        }
    }

    @Test
    public void overArrayNull() {
        String[] ary = null;

        println("over null array - old");
        if (ary != null) {
            for (String s : ary) {
                println("s", s);
            }
        }
        
        println("over null array - new");
        for (String s : Iterate.over(ary)) {
            println("s", s);
        }
    }

    @Test
    public void overIterable() {
        List<String> list = Arrays.asList(new String[] { "a", "b", "c" });

        println("over iterable - old");
        for (String s : list) {
            println("s", s);
        }
        
        println("over iterable - new");
        for (String s : Iterate.over(list)) {
            println("s", s);
        }
    }

    @Test
    public void overIterableNull() {
        List<String> list = null;

        println("over null iterable - old");
        if (list != null) {
            for (String s : list) {
                println("s", s);
            }
        }
        
        println("over null iterable - new");
        for (String s : Iterate.over(list)) {
            println("s", s);
        }
    }

    @Test
    public void overNonNull() {
        List<String> list = Arrays.asList("a", null, "c");

        println("over non null - old");
        for (String s : list) {
            if (s != null) {
                println("s", s);
            }
        }

        println("over non null - new");
        for (String s : Iterate.overNonNull(list)) {
            println("s", s);
        }
    }

    @Test
    public void eachWithIndexList() {
        List<String> list = Arrays.asList(new String[] { "a", "b", "c" });
        
        println("each with index - old");
        int index = 0;
        for (String s : list) {
            println("index", index);
            println("s", s);
            ++index;
        }
        
        println("each with index, list - new");
        for (KeyValue<Integer, String> it : Iterate.eachWithIndex(list)) {
            println("it.key()", it.key());
            println("it.value()", it.value());
        }
    }

    @Test
    public void eachWithIndexListNull() {
        List<String> list = null;

        println("each with index, null list - old");
        if (list != null) {
            int index = 0;
            for (String s : list) {
                println("index", index);
                println("s", s);
                ++index;
            }
        }

        println("each with index, null list - new");
        for (KeyValue<Integer, String> it : Iterate.eachWithIndex(list)) {
            println("it.key()", it.key());
            println("it.value()", it.value());
        }
    }
    
    @Test
    public void eachWithIndexArray() {
        String[] ary = new String[] { "a", "b", "c" };

        println("each with index, array - old");
        int index = 0;
        for (String s : ary) {
            println("index", index);
            println("s", s);
            ++index;
        }
        
        println("each with index, array - new");
        for (KeyValue<Integer, String> it : Iterate.eachWithIndex(ary)) {
            println("it.key()", it.key());
            println("it.value()", it.value());
        }
    }
    
    @Test
    public void eachWithIndexArrayNull() {
        String[] ary = null;

        println("each with index, null array - old");
        if (ary != null) {
            int index = 0;
            for (String s : ary) {
                println("index", index);
                println("s", s);
                ++index;
            }
        }
        
        println("each with index, null array - new");
        for (KeyValue<Integer, String> it : Iterate.eachWithIndex(ary)) {
            println("it.key()", it.key());
            println("it.value()", it.value());
        }
    }
}
