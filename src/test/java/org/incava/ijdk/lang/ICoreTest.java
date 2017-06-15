package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import org.junit.Test;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;

public class ICoreTest {
    @Test
    public void mapEmpty() {
        TreeMap<String, String> expected = new TreeMap<String, String>();
        assertEqual(expected, ICore.<String, String>map());        
    }

    @Test
    public void mapOne() {
        TreeMap<String, Integer> expected = new TreeMap<String, Integer>();
        expected.put("one", 1);
        assertEqual(expected, ICore.map("one", 1));
    }

    @Test
    public void mapTwo() {
        TreeMap<String, Integer> expected = new TreeMap<String, Integer>();
        expected.put("one", 1);
        expected.put("two", 2);
        assertEqual(expected, ICore.map("one", 1, "two", 2));
    }

    @Test
    public void ary() {
        assertEqual(Arrays.asList(new String[] { "one", "two" }), Arrays.asList(ICore.ary("one", "two")));
        // results in warnings; use Common.objary instead:
        assertEqual(Arrays.asList(new Object[] { Boolean.TRUE, "two" }), Arrays.asList(ICore.<Object>ary(Boolean.TRUE, "two")));
    }

    @Test
    public void strlist() {
        List<String> expected = new ArrayList<String>();
        assertEqual(expected, ICore.strlist());
    }

    @Test
    public void intlist() {
        List<Integer> expected = new ArrayList<Integer>();
        assertEqual(expected, ICore.intlist());
    }    
}
