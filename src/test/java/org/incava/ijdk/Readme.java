package org.incava.ijdk;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.ijdk.collect.Hash;
import org.incava.attest.Parameterized;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.incava.attest.Assertions.message;

public class Readme extends Parameterized {
    @Rule public TestName name = new TestName();
    
    @SuppressWarnings("unchecked")
    public static <T> T assertEqual(T expected, T actual, String msg) {
        System.out.println(String.valueOf(expected) +  "; " + msg);
        return org.incava.attest.Assertions.assertEqual(expected, actual, msg);
    }

    @Before
    public void setUp() {
        System.out.println(name.getMethodName() + ":");
    }

    @After
    public void tearDown() {
        System.out.println();
    }
    
    @Test
    public void empty() {
        // generic type inferred by context:
        Hash<String, String> h = Hash.empty();
        assertEqual(new HashMap<String, String>(), h, message("h", h));
    }

    @Test
    public void ofOne() {
        // creates a map from one key/value pair
        Hash<String, String> h = Hash.of("one", "1");
        HashMap<String, String> expected = new HashMap<String, String>();
        expected.put("one", "1");        
        assertEqual(expected, h, message("h", h));
    }

    @Test
    public void ofTwo() {
        // creates a map from two key/value pairs
        Hash<String, String> h = Hash.of("one", "1", "two", "2");
        HashMap<String, String> expected = new HashMap<String, String>();
        expected.put("one", "1");        
        expected.put("two", "2");
        assertEqual(expected, h, message("h", h));
    }

    @Test
    public void ofThree() {
        // creates a map from three key/value pairs
        Hash<String, String> h = Hash.of("first", "abc", "second", "def", "third", "ghi");
        HashMap<String, String> expected = new HashMap<String, String>();
        expected.put("first", "abc");
        expected.put("second", "def");
        expected.put("third", "ghi");
        assertEqual(expected, h, message("h", h));
    }

    @Test
    public void set() {
        // set() returns the map, so methods can be chained:
        Hash<String, String> h = Hash.of("first", "abc");
        h.set("second", "def").set("third", "ghi").set("fourth", "jkl");
        
        HashMap<String, String> expected = new HashMap<String, String>();
        expected.put("first", "abc");
        expected.put("second", "def");
        expected.put("third", "ghi");
        expected.put("fourth", "jkl");
        
        assertEqual(expected, h, message("h", h));
    }

    @Test
    public void keys() {
        // same as keySet, but a shorter name:
        Hash<String, String> h = Hash.of("first", "abc", "second", "def", "third", "ghi");
        
        Set<String> expected = new HashSet<String>();
        expected.add("first");
        expected.add("second");
        expected.add("third");
        
        assertEqual(expected, h.keys(), message("h.keys", h.keys()));
    }

    @Test
    public void entries() {
        // same as entrySet, but, like keys/keySet, a shorter name:
        Hash<String, String> h = Hash.of("first", "abc", "second", "def", "third", "ghi");

        HashMap<String, String> expected = new HashMap<String, String>();
        expected.put("first", "abc");
        expected.put("second", "def");
        expected.put("third", "ghi");
        
        assertEqual(expected.entrySet(), h.entries(), message("h.entries", h.entries()));
    }

    @Test
    public void iterator() {
        // Hash is iterable, and can be used in for loops:
        Hash<String, String> h = Hash.of("first", "abc", "second", "def", "third", "ghi");
        for (java.util.Map.Entry<String, String> entry : h) {
            String exp = h.get(entry.getKey());
            assertEqual(exp, entry.getValue(), message("entry", entry));
        }
    }

    @Test
    public void fetch() {
        Hash<String, String> h = Hash.of("first", "abc", "second", "def", "third", "ghi");        
        assertEqual("abc", h.fetch("first"), message("h", h));
        assertEqual("xyz", h.fetch("fourth", "xyz"), message("h", h));
        assertEqual(null, h.fetch("fourth", null), message("h", h));
        try {
            h.fetch("fourth");
        }
        catch (IllegalArgumentException iae) {
            // expected
        }
    }
}
