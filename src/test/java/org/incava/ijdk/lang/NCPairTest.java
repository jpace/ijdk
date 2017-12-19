package org.incava.ijdk.lang;

import java.util.TreeMap;
import org.junit.Assert;
import org.junit.Test;

public class NCPairTest {
    @Test
    public void testCreate() {
        // StringBuffers are not comparable
        NCPair<StringBuffer, String> sbStrPair = NCPair.create(new StringBuffer("thirty-four"), "thirty-four");
        Assert.assertTrue(sbStrPair instanceof NCPair);
    }

    @Test    
    public void testNonComparable() {
        NCPair<StringBuffer, Integer> a = NCPair.create(new StringBuffer("hello"), 4);
        NCPair<StringBuffer, Integer> b = NCPair.create(new StringBuffer("hello"), 4);
        NCPair<StringBuffer, Integer> c = NCPair.create(new StringBuffer("hello"), 5);
        NCPair<StringBuffer, Integer> d = NCPair.create(new StringBuffer("ha"),    4);

        TreeMap<StringBuffer, Integer> tm = new TreeMap<StringBuffer, Integer>();

        // StringBuffers are not Comparable, nor do they define equals.
        Assert.assertFalse(a.equals(b));
        Assert.assertFalse(a.equals(c));
    }
}
