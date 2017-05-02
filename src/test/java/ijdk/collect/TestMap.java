package ijdk.collect;

import ijdk.collect.List;
import ijdk.lang.Common;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.incava.ijdk.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;

import static ijdk.lang.Common.*;
import static org.incava.test.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class TestMap {
    @Test
    public void init() {
        java.util.Map<String, Integer> jdkMap = new java.util.TreeMap<String, Integer>();
        jdkMap.put("one", 1);
        jdkMap.put("two", 2);

        Map<String, Integer> ijdkMap = new Map<String, Integer>(jdkMap);
        assertEqual(ijdkMap, jdkMap);
    }   
    
    @Test
    public void ofList() {
        List<Pair<String, Integer>> list = List.of(Pair.of("one", 1), Pair.of("two", 2));

        java.util.TreeMap<String, Integer> expected = new java.util.TreeMap<String, Integer>();
        expected.put("one", 1);
        expected.put("two", 2);

        assertEqual(expected, Map.of(list));
    }   
    
    @Test
    public void empty() {
        assertEqual(new Map<String, Integer>(), Map.<String, Integer>empty());
    }   
    
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
        Map<String, Integer> result = m.set("one", 1).set("two", 2);

        assertEqual(expected, m);
        assertSame(result, m);
    }

    @Test
    public void keys() {
        Map<String, Integer> map = Map.<String, Integer>of("one", 1, "two", 2);
        java.util.Set<String> expected = new java.util.TreeSet<String>();
        expected.add("one");
        expected.add("two");
        assertEqual(expected, map.keys());
    }

    @Test
    public void entries() {
        Map<String, Integer> map = Map.<String, Integer>of("one", 1, "two", 2);
        java.util.Set<java.util.Map.Entry<String, Integer>> expected = new java.util.HashSet<java.util.Map.Entry<String, Integer>>();
        expected.add(new java.util.AbstractMap.SimpleEntry<String, Integer>("one", 1));
        expected.add(new java.util.AbstractMap.SimpleEntry<String, Integer>("two", 2));
        assertEqual(expected, map.entries());
    }

    @Test
    public void iterator() {
        Map<String, Integer> map = Map.<String, Integer>of("one", 1, "two", 2);
        java.util.Iterator<java.util.Map.Entry<String, Integer>> it = map.iterator();
        assertEqual(true, it.hasNext());
        assertEqual(new java.util.AbstractMap.SimpleEntry<String, Integer>("one", 1), it.next());
        assertEqual(new java.util.AbstractMap.SimpleEntry<String, Integer>("two", 2), it.next());
        assertEqual(false, it.hasNext());
    }
}
