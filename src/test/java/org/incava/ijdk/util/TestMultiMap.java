package org.incava.ijdk.util;

import java.util.Arrays;
import java.util.Collection;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class TestMultiMap {
    public static final Collection<Integer> EVENS = Arrays.asList(new Integer[] { 2, 4, 6, 8 });
    public static final Collection<Integer> ODDS  = Arrays.asList(new Integer[] { 1, 3, 5, 7, 9 });

    @Test
    public void putGetCollection() {
        MultiMap<String, Integer> numMap = new MultiMap<String, Integer>();

        Object prevEven = numMap.put("evens", EVENS);
        assertNull(prevEven);

        Object prevOdd = numMap.put("odds", ODDS);
        assertNull(prevOdd);

        assertNotNull(numMap.get("evens"));
        assertEqual(EVENS, numMap.get("evens"));

        assertNotNull(numMap.get("odds"));
        assertEqual(ODDS, numMap.get("odds"));
    }

    @Test
    public void add() {
        MultiMap<String, Integer> numMap = new MultiMap<String, Integer>();

        for (Integer num : EVENS) {
            numMap.add("evens", num);
        }

        for (Integer num : ODDS) {
            numMap.add("odds", num);
        }

        assertNotNull(numMap.get("evens"));
        assertEqual(EVENS, numMap.get("evens"));

        assertNotNull(numMap.get("odds"));
        assertEqual(ODDS, numMap.get("odds"));
    }

    @Test
    public void putCollection() {
        MultiMap<String, Integer> numMap = new MultiMap<String, Integer>();

        numMap.put("evens", EVENS);
        numMap.put("odds", ODDS);

        assertNotNull(numMap.get("evens"));
        assertEqual(EVENS, numMap.get("evens"));

        assertNotNull(numMap.get("odds"));
        assertEqual(ODDS, numMap.get("odds"));
    }
}
