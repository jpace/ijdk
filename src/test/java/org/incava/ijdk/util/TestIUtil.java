package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.*;
import static org.junit.Assert.*;
import static org.incava.ijdk.lang.Common.objary;

@RunWith(JUnitParamsRunner.class)
public class TestIUtil {
    @Test
    @Parameters
    public void isTrue(boolean expected, Object input) {
        assertEqual(expected, IUtil.isTrue(input));
    }
    
    private List<Object[]> parametersForIsTrue() {
        return Arrays.asList(new Object[][] {
                objary(true, "foo"),
                objary(true, new Integer(17)),
                objary(true, 0),
                objary(true, Arrays.asList(new String[] { "foo" })),
                
                objary(false, ""),
                objary(false, null),
                objary(false, new ArrayList<String>())
            });
    }

    @Test
    @Parameters
    public void isFalse(boolean expected, Object input) {
        assertEqual(expected, IUtil.isFalse(input));
    }
    
    private List<Object[]> parametersForIsFalse() {
        return Arrays.asList(new Object[][] {
                objary(true, null),
                objary(true, ""),
                objary(true, new ArrayList<Double>()),
                objary(false, new Integer(317)),
                objary(false, Arrays.asList(new String[] { "ord" }))
            });
    }

    @Test
    @Parameters
    public void elvis(Object expected, Object x, Object y) {
        assertEqual(expected, IUtil.elvis(x, y));
    }
    
    private List<Object[]> parametersForElvis() {
        Object collOne = Arrays.asList(new String[] { "foo", "bar" });
        Object collTwo = Arrays.asList(new String[] { "BAR", "FOO" });
        
        return Arrays.asList(new Object[][] {
                objary("foo", "foo", "bar"),
                objary("foo", "foo", null),
                objary("foo", null,  "foo"),
                objary(collOne, collOne, collTwo),
                objary(collOne, null, collOne),
                objary(collOne, collOne, null)
            });
    }
    
    @Test
    @Parameters
    public void and(Object expected, Object x, Object y) {
        assertEqual(expected, IUtil.and(x, y));
    }
    
    private List<Object[]> parametersForAnd() {
        ArrayList<String> one = new ArrayList<String>(Arrays.asList(new String[] { "one", "eine" }));
        ArrayList<String> two = new ArrayList<String>(Arrays.asList(new String[] { "two", "dos" }));
        ArrayList<String> three = new ArrayList<String>(Arrays.asList(new String[] { "three", "san" }));
        ArrayList<String> empty = new ArrayList<String>();

        return Arrays.asList(new Object[][] {
                objary("bar", "foo", "bar"),
                objary(null, "foo", null),
                objary(null, null, "bar"),
                objary("bar", "bar", "bar"),
                objary(null, null, null),
                objary(two, one, two),
                objary(three, one, three),
                objary(three, two, three),
                objary(null, one, empty),
                objary(null, one, null),
                objary(null, empty, one),
                objary(null, null, one)
            });
    }

    @Test
    @Parameters
    public void or(Object expected, Object x, Object y) {
        assertEqual(expected, IUtil.or(x, y));
    }
    
    private List<Object[]> parametersForOr() {
        ArrayList<String> one = new ArrayList<String>(Arrays.asList(new String[] { "one", "eine" }));
        ArrayList<String> two = new ArrayList<String>(Arrays.asList(new String[] { "two", "dos" }));
        ArrayList<String> three = new ArrayList<String>(Arrays.asList(new String[] { "three", "san" }));
        ArrayList<String> empty = new ArrayList<String>();

        return Arrays.asList(new Object[][] {
                objary("foo", "foo", "bar"),
                objary("foo", "foo", null),
                objary("bar", null, "bar"),
                objary("bar", "bar", "bar"),
                objary(null, null, null),

                objary(one, one, two),
                objary(one, one, three),
                objary(two, two, three),
                
                objary(one, one, empty),
                objary(one, one, null),
                
                objary(one, empty, one),
                objary(one, null, one),
                
                objary(null, empty, null),
                objary(null, null, empty)
            });
    }
    
    @Test
    public void iterArray() {
        String[] listOne = new String[] { "one", "eine" };
        String[] listEmpty = new String[0];
        String[] listNull = null;

        Iterator<String> litOne = IUtil.iter(listOne).iterator();
        assertTrue(litOne.hasNext());
        assertEqual("one", litOne.next());
        assertEqual("eine", litOne.next());
        assertFalse(litOne.hasNext());        

        Iterator<String> litEmpty = IUtil.iter(listEmpty).iterator();
        assertFalse(litEmpty.hasNext());

        Iterator<String> litNull = IUtil.iter(listNull).iterator();
        assertFalse(litNull.hasNext());
    }

    @Test
    public void iterCollection() {
        ArrayList<String> aryOne = new ArrayList<String>(Arrays.asList(new String[] { "one", "eine" }));
        ArrayList<String> aryEmpty = new ArrayList<String>();
        ArrayList<String> aryNull = null;

        Iterator<String> aitOne = IUtil.iter(aryOne).iterator();
        assertTrue(aitOne.hasNext());
        assertEqual("one", aitOne.next());
        assertEqual("eine", aitOne.next());
        assertFalse(aitOne.hasNext());        

        Iterator<String> aitEmpty = IUtil.iter(aryEmpty).iterator();
        assertFalse(aitEmpty.hasNext());

        Iterator<String> aitNull = IUtil.iter(aryNull).iterator();
        assertFalse(aitNull.hasNext());
    }

    public void assertHasNext(boolean exp, Iterator<Integer> it) {
        assertEqual(exp, it.hasNext());
    }

    public void assertNext(Integer exp, Iterator<Integer> it) {
        assertEqual(exp, it.next());
    }

    @Test
    public void iterNumber() {
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
            assertEqual("limit: " + 3, e.getMessage());
        }
    }

    @SuppressWarnings("varargs")
    @SafeVarargs
    public final <T> void assertList(List<T> expected, T ... elements) {
        assertEqual(expected, IUtil.list(elements));
    }

    @Test
    public void list() {
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
