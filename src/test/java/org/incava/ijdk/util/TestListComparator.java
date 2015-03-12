package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import org.incava.ijdk.util.IUtil;
import static org.incava.ijdk.util.IUtil.*;

public class TestListComparator extends TestCase {
    public TestListComparator(String name) {
        super(name);
    }

    public <Type> void assertComparison(List<Integer> expExactMatches, 
                                        Map<Integer, Integer> expMisorderedMatches, 
                                        List<Type> from, List<Type> to) {
        
        ListComparator<Type> lc = new ListComparator<Type>(from, to);
        ListComparison comp = lc.getComparison();
        assertEquals(expExactMatches, comp.getExactMatches());
        assertEquals(expMisorderedMatches, comp.getMisorderedMatches());
    }

    public Map<Integer, Integer> map(Integer ... vals) {
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        Integer k = null;
        for (Integer val : vals) {
            if (k == null) {
                k = val;
            }
            else {
                m.put(k, val);
                k = null;
            }
        }
        return m;
    }

    public void testEmptyLists() {
        assertComparison(IUtil.<Integer>list(), map(), IUtil.<String>list(), IUtil.<String>list());
    }

    public void testExactMatchOneElement() {
        assertComparison(IUtil.list(0), map(), list("x"), list("x"));
    }

    public void testExactMatchTwoElements() {
        assertComparison(IUtil.list(0, 1), map(), list("x", "y"), list("x", "y"));
    }

    public void testExactMatchTwoElementsNoMatchOne() {
        assertComparison(IUtil.list(0, 1), map(), list("x", "y", "z"), list("x", "y", "a"));
    }

    public void testExactMisorderedMatchesTwo() {
        assertComparison(IUtil.<Integer>list(), map(0, 1, 1, 0), list("x", "y"), list("y", "x"));
    }

    public void testExactMisorderedMatchesThree() {
        assertComparison(IUtil.<Integer>list(), map(0, 1, 1, 2, 2, 0), list("x", "y", "z"), list("z", "x", "y"));
    }

    public void testExactMisorderedMatchesThreeOneRemaining() {
        assertComparison(IUtil.<Integer>list(), map(0, 1, 1, 2, 2, 0), list("x", "y", "z"), list("z", "x", "y", "a"));
    }
}
