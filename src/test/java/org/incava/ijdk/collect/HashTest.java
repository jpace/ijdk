package org.incava.ijdk.collect;

import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.ijdk.tuple.Pair;
import org.junit.Test;
import org.incava.test.Parameterized;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.assertSame;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

public class HashTest extends Parameterized {
    @Test
    public void init() {
        java.util.Map<String, Integer> jdkMap = new java.util.TreeMap<String, Integer>();
        jdkMap.put("one", 1);
        jdkMap.put("two", 2);
        jdkMap.put("three", 3);

        Hash<String, Integer> ijdkMap = new Hash<String, Integer>(jdkMap);
        assertEqual(ijdkMap, jdkMap);
        assertEqual(jdkMap, ijdkMap);
    }   
    
    @Test
    public void ofList() {
        java.util.List<Pair<String, Integer>> list = new java.util.ArrayList<Pair<String, Integer>>();
        list.add(Pair.of("one", 1));
        list.add(Pair.of("two", 2));
        list.add(Pair.of("three", 3));

        java.util.TreeMap<String, Integer> expected = new java.util.TreeMap<String, Integer>();
        expected.put("one", 1);
        expected.put("two", 2);
        expected.put("three", 3);

        assertEqual(expected, Hash.of(list));
    }   
    
    @Test
    public void empty() {
        assertEqual(new Hash<String, Integer>(), Hash.<String, Integer>empty());
    }   
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <K, V> void of(java.util.TreeMap<K, V> expected, Hash<K, V> result) {
        assertEqual(expected, result);        
    }
    
    private java.util.List<Object[]> parametersForOf() {
        java.util.TreeMap<String, Integer> expected;
        
        java.util.List<Object[]> params = paramsList();

        expected = new java.util.TreeMap<String, Integer>();
        params.add(params(expected, Hash.<String, Integer>of()));

        expected = new java.util.TreeMap<String, Integer>();
        expected.put("one", 1);
        params.add(params(expected, Hash.of("one", 1)));

        expected = new java.util.TreeMap<String, Integer>();
        expected.put("two", 2);
        expected.put("three", 3);
        params.add(params(expected, Hash.of("two", 2, "three", 3)));

        expected = new java.util.TreeMap<String, Integer>();
        expected.put("four", 4);
        expected.put("five", 5);
        expected.put("six", 6);
        params.add(params(expected, Hash.of("four", 4, "five", 5, "six", 6)));
        
        return params;
    }

    @Test
    public void set() {
        java.util.TreeMap<String, Integer> expected = new java.util.TreeMap<String, Integer>();
        expected.put("one", 1);
        expected.put("two", 2);

        Hash<String, Integer> m = Hash.<String, Integer>of();
        Hash<String, Integer> result = m.set("one", 1).set("two", 2);

        assertEqual(expected, m);
        assertSame(result, m);
    }

    @Test
    public void keys() {
        Hash<String, Integer> map = Hash.<String, Integer>of("one", 1, "two", 2);
        java.util.Set<String> expected = new java.util.TreeSet<String>();
        expected.add("one");
        expected.add("two");
        assertEqual(expected, map.keys());
    }

    @Test
    public void entries() {
        Hash<String, Integer> map = Hash.<String, Integer>of("one", 1, "two", 2);
        java.util.Set<java.util.Map.Entry<String, Integer>> expected = new java.util.HashSet<java.util.Map.Entry<String, Integer>>();
        expected.add(new java.util.AbstractMap.SimpleEntry<String, Integer>("one", 1));
        expected.add(new java.util.AbstractMap.SimpleEntry<String, Integer>("two", 2));
        assertEqual(expected, map.entries());
    }

    @Test
    public void iterator() {
        Hash<String, Integer> map = Hash.<String, Integer>of("one", 1, "two", 2);
        java.util.Iterator<java.util.Map.Entry<String, Integer>> it = map.iterator();
        assertEqual(true, it.hasNext());
        assertEqual(new java.util.AbstractMap.SimpleEntry<String, Integer>("one", 1), it.next());
        assertEqual(new java.util.AbstractMap.SimpleEntry<String, Integer>("two", 2), it.next());
        assertEqual(false, it.hasNext());
    }
}
