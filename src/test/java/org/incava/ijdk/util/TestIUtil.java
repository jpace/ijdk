package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import junit.framework.TestCase;

public class TestIUtil extends TestCase {
    public TestIUtil(String name) {
        super(name);
    }

    public void assertIsTrue(boolean exp, Object input) {
        assertEquals(exp, IUtil.isTrue(input));
    }

    public void assertIsFalse(boolean exp, Object input) {
        assertEquals(exp, IUtil.isFalse(input));
    }

    public void assertElvis(Object exp, Object a, Object b) {
        assertEquals(exp, IUtil.elvis(a, b));
    }

    public void assertAnd(Object exp, Object a, Object b) {
        assertEquals(exp, IUtil.and(a, b));
    }

    public void assertOr(Object exp, Object a, Object b) {
        assertEquals(exp, IUtil.or(a, b));
    }

    public void testIsTrue() {
        assertIsTrue(true, "foo");
        assertIsTrue(true, new Integer(17));
        assertIsTrue(true, 0);
        assertIsTrue(true, Arrays.asList(new String[] { "foo" }));
        
        assertIsTrue(false, "");
        assertIsTrue(false, null);
        assertIsTrue(false, new ArrayList<String>());
    }

    public void testIsFalse() {
        assertIsFalse(true, null);
        assertIsFalse(true, "");
        assertIsFalse(true, new ArrayList<Double>());

        assertIsFalse(false, new Integer(317));
        assertIsFalse(false, Arrays.asList(new String[] { "ord" }));
    }

    public void testElvis() {
        assertElvis("foo", "foo", "bar");
        assertElvis("foo", "foo", null);
        assertElvis("foo", null,  "foo");

        Object collOne = Arrays.asList(new String[] { "foo", "bar" });
        Object collTwo = Arrays.asList(new String[] { "BAR", "FOO" });

        assertElvis(collOne, collOne, collTwo);
        assertElvis(collOne, null, collOne);
        assertElvis(collOne, collOne, null);
    }

    public void testAnd() {
        assertAnd("bar", "foo", "bar");
        assertAnd(null, "foo", null);
        assertAnd(null, null, "bar");
        assertAnd("bar", "bar", "bar");
        assertAnd(null, null, null);

        ArrayList<String> one = new ArrayList<String>(Arrays.asList(new String[] { "one", "eine" }));
        ArrayList<String> two = new ArrayList<String>(Arrays.asList(new String[] { "two", "dos" }));
        ArrayList<String> three = new ArrayList<String>(Arrays.asList(new String[] { "three", "san" }));
        ArrayList<String> empty = new ArrayList<String>();

        assertAnd(two, one, two);
        assertAnd(three, one, three);
        assertAnd(three, two, three);

        assertAnd(null, one, empty);
        assertAnd(null, one, null);

        assertAnd(null, empty, one);
        assertAnd(null, null, one);                                      
    }

    public void testOr() {
        assertOr("foo", "foo", "bar");
        assertOr("foo", "foo", null);
        assertOr("bar", null, "bar");
        assertOr("bar", "bar", "bar");
        assertOr(null, null, null);

        ArrayList<String> one = new ArrayList<String>(Arrays.asList(new String[] { "one", "eine" }));
        ArrayList<String> two = new ArrayList<String>(Arrays.asList(new String[] { "two", "dos" }));
        ArrayList<String> three = new ArrayList<String>(Arrays.asList(new String[] { "three", "san" }));
        ArrayList<String> empty = new ArrayList<String>();

        assertOr(one, one, two);
        assertOr(one, one, three);
        assertOr(two, two, three);

        assertOr(one, one, empty);
        assertOr(one, one, null);

        assertOr(one, empty, one);
        assertOr(one, null, one);

        assertOr(null, empty, null);
        assertOr(null, null, empty);
    }

    public void testIterArray() {
        String[] listOne = new String[] { "one", "eine" };
        String[] listEmpty = new String[0];
        String[] listNull = null;

        Iterator<String> litOne = IUtil.iter(listOne).iterator();
        assertTrue(litOne.hasNext());
        assertEquals("one", litOne.next());
        assertEquals("eine", litOne.next());
        assertFalse(litOne.hasNext());        

        Iterator<String> litEmpty = IUtil.iter(listEmpty).iterator();
        assertFalse(litEmpty.hasNext());

        Iterator<String> litNull = IUtil.iter(listNull).iterator();
        assertFalse(litNull.hasNext());
    }

    public void testIterCollection() {
        ArrayList<String> aryOne = new ArrayList<String>(Arrays.asList(new String[] { "one", "eine" }));
        ArrayList<String> aryEmpty = new ArrayList<String>();
        ArrayList<String> aryNull = null;

        Iterator<String> aitOne = IUtil.iter(aryOne).iterator();
        assertTrue(aitOne.hasNext());
        assertEquals("one", aitOne.next());
        assertEquals("eine", aitOne.next());
        assertFalse(aitOne.hasNext());        

        Iterator<String> aitEmpty = IUtil.iter(aryEmpty).iterator();
        assertFalse(aitEmpty.hasNext());

        Iterator<String> aitNull = IUtil.iter(aryNull).iterator();
        assertFalse(aitNull.hasNext());
    }

    public void assertHasNext(boolean exp, Iterator<Integer> it) {
        assertEquals(exp, it.hasNext());
    }

    public void assertNext(Integer exp, Iterator<Integer> it) {
        assertEquals(exp, it.next());
    }

    public void testIterNumber() {
        Iterator<Integer> it = IUtil.iter(3).iterator();
        assertHasNext(true, it);
        assertNext(0, it);

        assertTrue(it.hasNext());
        assertNext(1, it);

        assertTrue(it.hasNext());
        assertNext(2, it);

        assertFalse(it.hasNext());
        try {
            assertNext(null, it);
            fail("exception expected");
        }
        catch (NoSuchElementException e) {
            assertEquals("limit: " + 3, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public final <T> void assertList(List<T> expected, T ... elements) {
        assertEquals(expected, IUtil.list(elements));
    }

    public void testList() {
        String[] listOne = new String[] { "one", "eine" };
        assertList(new ArrayList<String>(Arrays.asList(listOne)), "one", "eine");

        assertList(new ArrayList<String>());
        assertList(new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 2, 3 })), 1, 2, 3);
    }

    // public void literate() {
    //     Object[] ary = or(input(), defValue);
    //     String x = and(userName(), userPassword());

    //     if (isTrue(ary) && isFalse(x)) {
            
    //     }

    //     for (Object x : iter(ary)) {
    //     }
        
    // }
    
}
