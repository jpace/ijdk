package org.incava.ijdk.collect;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.tuple.Pair;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public class SortedMapTest extends Parameterized {
    @Test
    public void init() {
        java.util.Map<String, Integer> jdkMap = new java.util.TreeMap<String, Integer>();
        jdkMap.put("one", 1);
        jdkMap.put("two", 2);
        jdkMap.put("three", 3);

        SortedMap<String, Integer> ijdkMap = new SortedMap<String, Integer>(jdkMap);
        assertThat(ijdkMap, equalTo(jdkMap));
        assertThat(jdkMap, equalTo(ijdkMap));
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

        assertThat(expected, equalTo(SortedMap.of(list)));
    }   
    
    @Test
    public void empty() {
        assertThat(SortedMap.<String, Integer>empty(), equalTo(new SortedMap<String, Integer>()));
    }   
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <K, V> void of(java.util.TreeMap<K, V> expected, SortedMap<K, V> result) {
        assertThat(result, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForOf() {
        java.util.TreeMap<String, Integer> expected;
        
        java.util.List<Object[]> params = paramsList();

        expected = new java.util.TreeMap<String, Integer>();
        params.add(params(expected, SortedMap.<String, Integer>of()));

        expected = new java.util.TreeMap<String, Integer>();
        expected.put("one", 1);
        params.add(params(expected, SortedMap.of("one", 1)));

        expected = new java.util.TreeMap<String, Integer>();
        expected.put("two", 2);
        expected.put("three", 3);
        params.add(params(expected, SortedMap.of("two", 2, "three", 3)));

        expected = new java.util.TreeMap<String, Integer>();
        expected.put("four", 4);
        expected.put("five", 5);
        expected.put("six", 6);
        params.add(params(expected, SortedMap.of("four", 4, "five", 5, "six", 6)));
        
        return params;
    }

    @Test
    public void set() {
        java.util.TreeMap<String, Integer> expected = new java.util.TreeMap<String, Integer>();
        expected.put("one", 1);
        expected.put("two", 2);

        SortedMap<String, Integer> m = SortedMap.empty();
        SortedMap<String, Integer> result = m.set("one", 1).set("two", 2);

        assertThat(m, equalTo(expected));
        assertThat(result, sameInstance(m));
    }

    @Test
    public void keys() {
        SortedMap<String, Integer> map = SortedMap.<String, Integer>of("one", 1, "two", 2);
        java.util.Set<String> expected = new java.util.TreeSet<String>();
        expected.add("one");
        expected.add("two");
        assertThat(map.keys(), equalTo(expected));
    }

    @Test
    public void entries() {
        SortedMap<String, Integer> map = SortedMap.<String, Integer>of("one", 1, "two", 2);
        java.util.TreeMap<String, Integer> expected = new java.util.TreeMap<String, Integer>();
        expected.put("one", 1);
        expected.put("two", 2);
        assertThat(map.entries(), equalTo(expected.entrySet()));
    }

    @Test
    public void iterator() {
        SortedMap<String, Integer> map = SortedMap.<String, Integer>of("one", 1, "two", 2);
        java.util.TreeMap<String, Integer> expected = new java.util.TreeMap<String, Integer>();
        expected.put("one", 1);
        expected.put("two", 2);
        
        java.util.Iterator<java.util.Map.Entry<String, Integer>> it = map.iterator();
        java.util.Iterator<java.util.Map.Entry<String, Integer>> expIt = expected.entrySet().iterator();

        assertThat(it.hasNext(), equalTo(true));        
        assertThat(expIt.next(), equalTo(it.next()));
        assertThat(expIt.next(), equalTo(it.next()));
        assertThat(it.hasNext(), equalTo(false));        
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <K, V> void fetchWithDefValue(V expected, SortedMap<K, V> map, K key, V defValue) {
        V result = map.fetch(key, defValue);
        assertThat(result, withContext(message("map", map, "key", key, "defValue", defValue), equalTo(expected)));
    }
    
    private List<Object[]> parametersForFetchWithDefValue() {
        SortedMap<String, String> h = SortedMap.of("first", "abc", "second", "def", "third", "ghi");        
        return paramsList(params("abc", h, "first", "xyz"),
                          params("def", h, "second", "xyz"),
                          params("yyy", h, "fourth", "yyy"),
                          params("zzz", h, "fourth", "zzz"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <K, V> void fetchNoDefault(String expError, SortedMap<K, V> map, K key) {
        try {
            map.fetch(key);
            assertThat(null, withContext(message("map", map, "key", key), equalTo(expError)));
        }
        catch (IllegalArgumentException iae) {
            assertThat(iae.getMessage(), withContext(message("map", map, "key", key), equalTo(expError)));
        }
    }
    
    private List<Object[]> parametersForFetchNoDefault() {
        SortedMap<String, String> h = SortedMap.of("first", "abc", "second", "def", "third", "ghi");        
        return paramsList(params(null, h, "first"),
                          params("key not found: 'fourth'", h, "fourth"),
                          params("key not found: 'fifth'",  h, "fifth"));
    }
}
