package ijdk.collect;

import ijdk.collect.List;
import ijdk.lang.Common;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.*;
import static ijdk.lang.Common.*;

@RunWith(JUnitParamsRunner.class)
public class TestMap {
    @Test
    @Parameters
    public <K, V> void of(java.util.TreeMap<K, V> expected, Map<K, V> result) {
        assertEqual(expected, result);        
    }
    
    private List<Object[]> parametersForOf() {
        java.util.TreeMap<String, Integer> expected;
        
        List<Object[]> params = Common.<Object[]>list();

        expected = new java.util.TreeMap<String, Integer>();
        params.add(objary(expected, Map.<String, Integer>of()));

        expected = new java.util.TreeMap<String, Integer>();
        expected.put("one", 1);
        params.add(objary(expected, Map.of("one", 1)));

        expected = new java.util.TreeMap<String, Integer>();
        expected.put("two", 2);
        expected.put("three", 3);
        params.add(objary(expected, Map.of("two", 2, "three", 3)));

        expected = new java.util.TreeMap<String, Integer>();
        expected.put("four", 4);
        expected.put("five", 5);
        expected.put("six", 6);
        params.add(objary(expected, Map.of("four", 4, "five", 5, "six", 6)));
        
        return params;
    }

    @Test
    public void set() {
        java.util.TreeMap<String, Integer> expected = new java.util.TreeMap<String, Integer>();
        expected.put("one", 1);
        expected.put("two", 2);

        Map<String, Integer> m = Map.<String, Integer>of();
        m.set("one", 1).set("two", 2);

        assertEqual(expected, m);
    }
}
