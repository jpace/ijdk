package org.incava.ijdk.util;

import junit.framework.TestCase;
import java.util.*;

public class TestMultiMap extends TestCase {
    public static final Collection<Integer> EVENS = java.util.Arrays.asList(new Integer[] { 2, 4, 6, 8 });
    public static final Collection<Integer> ODDS  = java.util.Arrays.asList(new Integer[] { 1, 3, 5, 7, 9 });

    public TestMultiMap(String name) {
        super(name);
    }

    public void testPutGetCollection() {
        MultiMap<String, Integer> numMap = new MultiMap<String, Integer>();

        Object prevEven = numMap.put("evens", EVENS);
        assertNull(prevEven);

        Object prevOdd = numMap.put("odds", ODDS);
        assertNull(prevOdd);

        assertNotNull(numMap.get("evens"));
        assertEquals(EVENS, numMap.get("evens"));

        assertNotNull(numMap.get("odds"));
        assertEquals(ODDS, numMap.get("odds"));
    }

    public void testPutScalar() {
        MultiMap<String, Integer> numMap = new MultiMap<String, Integer>();

        for (Integer num : EVENS) {
            numMap.add("evens", num);
        }

        for (Integer num : ODDS) {
            numMap.add("odds", num);
        }

        assertNotNull(numMap.get("evens"));
        assertEquals(EVENS, numMap.get("evens"));

        assertNotNull(numMap.get("odds"));
        assertEquals(ODDS, numMap.get("odds"));
    }

    public void testPutCollection() {
        MultiMap<String, Integer> numMap = new MultiMap<String, Integer>();

        for (Integer num : EVENS) {
            numMap.add("evens", num);
        }

        for (Integer num : ODDS) {
            numMap.add("odds", num);
        }

        assertNotNull(numMap.get("evens"));
        assertEquals(EVENS, numMap.get("evens"));

        assertNotNull(numMap.get("odds"));
        assertEquals(ODDS, numMap.get("odds"));
    }
}
