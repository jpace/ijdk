package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Assert;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

public class ICoreTest extends Parameterized {
    @Test
    public void mapEmpty() {
        TreeMap<String, String> expected = new TreeMap<String, String>();
        assertEqual(expected, ICore.<String, String>map());
    }

    @Test
    public void mapOne() {
        TreeMap<String, Integer> expected = new TreeMap<String, Integer>();
        expected.put("one", 1);
        assertEqual(expected, ICore.map("one", 1));
    }

    @Test
    public void mapTwo() {
        TreeMap<String, Integer> expected = new TreeMap<String, Integer>();
        expected.put("one", 1);
        expected.put("two", 2);
        assertEqual(expected, ICore.map("one", 1, "two", 2));
    }

    @Test
    public void ary() {
        assertEqual(Arrays.asList(new String[] { "one", "two" }), Arrays.asList(ICore.ary("one", "two")));
        // results in warnings; use Common.objary instead:
        assertEqual(Arrays.asList(new Object[] { Boolean.TRUE, "two" }), Arrays.asList(ICore.<Object>ary(Boolean.TRUE, "two")));
    }

    @Test
    public void strlist() {
        List<String> expected = new ArrayList<String>();
        assertEqual(expected, ICore.strlist());
    }

    @Test
    public void intlist() {
        List<Integer> expected = new ArrayList<Integer>();
        assertEqual(expected, ICore.intlist());
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void isTrue(boolean expected, Object input) {
        assertEqual(expected, ICore.isTrue(input));
    }
    
    private List<Object[]> parametersForIsTrue() {
        return paramsList(params(true, "foo"),
                          params(true, new Integer(17)),
                          params(true, 0),
                          params(true, Arrays.asList(new String[] { "foo" })),
                          
                          params(false, null),
                          params(false, new ArrayList<String>()));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void isFalse(boolean expected, Object input) {
        assertEqual(expected, ICore.isFalse(input));
    }
    
    private List<Object[]> parametersForIsFalse() {
        return paramsList(params(true,  null),                    
                          params(true,  new ArrayList<Double>()), 
                          params(false, new Integer(317)),        
                          params(false, Arrays.asList(new String[] { "ord" })));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void and(Object expected, Object x, Object y) {
        assertEqual(expected, ICore.and(x, y));
    }
    
    private List<Object[]> parametersForAnd() {
        ArrayList<String> one   = new ArrayList<String>(Arrays.asList(new String[] { "one",   "eine" }));
        ArrayList<String> two   = new ArrayList<String>(Arrays.asList(new String[] { "two",   "dos" }));
        ArrayList<String> three = new ArrayList<String>(Arrays.asList(new String[] { "three", "san" }));
        ArrayList<String> empty = new ArrayList<String>();

        return paramsList(params("bar", "foo", "bar"), 
                          params(null,  "foo", null),  
                          params(null,  null,  "bar"), 
                          params("bar", "bar", "bar"), 
                          params(null,  null,  null),  
                          params(two,   one,   two),   
                          params(three, one,   three), 
                          params(three, two,   three), 
                          params(null,  one,   empty), 
                          params(null,  one,   null),  
                          params(null,  empty, one),   
                          params(null,  null,  one));
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void or(Object expected, Object x, Object y) {
        assertEqual(expected, ICore.or(x, y));
    }
    
    private List<Object[]> parametersForOr() {
        ArrayList<String> one   = new ArrayList<String>(Arrays.asList(new String[] { "one",   "eine" }));
        ArrayList<String> two   = new ArrayList<String>(Arrays.asList(new String[] { "two",   "dos" }));
        ArrayList<String> three = new ArrayList<String>(Arrays.asList(new String[] { "three", "san" }));
        ArrayList<String> empty = new ArrayList<String>();

        return paramsList(params("foo", "foo", "bar"), 
                          params("foo", "foo", null),  
                          params("bar", null,  "bar"), 
                          params("bar", "bar", "bar"), 
                          params(null,  null,  null),  

                          params(one,   one,   two),   
                          params(one,   one,   three), 
                          params(two,   two,   three), 
                
                          params(one,   one,   empty), 
                          params(one,   one,   null),  
                
                          params(one,   empty, one),   
                          params(one,   null,  one),   
                
                          params(null,  empty, null),  
                          params(null,  null,  empty));
    }
    
    @Test
    public void iterArray() {
        String[] listOne = new String[] { "one", "eine" };
        String[] listEmpty = new String[0];
        String[] listNull = null;

        Iterator<String> litOne = ICore.iter(listOne).iterator();
        assertEqual(true, litOne.hasNext());
        assertEqual("one", litOne.next());
        assertEqual("eine", litOne.next());
        assertEqual(false, litOne.hasNext());

        Iterator<String> litEmpty = ICore.iter(listEmpty).iterator();
        assertEqual(false, litEmpty.hasNext());

        Iterator<String> litNull = ICore.iter(listNull).iterator();
        assertEqual(false, litNull.hasNext());
    }

    @Test
    public void iterCollection() {
        ArrayList<String> aryOne = new ArrayList<String>(Arrays.asList(new String[] { "one", "eine" }));
        ArrayList<String> aryEmpty = new ArrayList<String>();
        ArrayList<String> aryNull = null;

        Iterator<String> aitOne = ICore.iter(aryOne).iterator();
        assertEqual(true, aitOne.hasNext());
        assertEqual("one", aitOne.next());
        assertEqual("eine", aitOne.next());
        assertEqual(false, aitOne.hasNext());

        Iterator<String> aitEmpty = ICore.iter(aryEmpty).iterator();
        assertEqual(false, aitEmpty.hasNext());

        Iterator<String> aitNull = ICore.iter(aryNull).iterator();
        assertEqual(false, aitNull.hasNext());
    }

    public void assertHasNext(boolean exp, Iterator<Integer> it) {
        assertEqual(exp, it.hasNext());
    }

    public void assertNext(Integer exp, Iterator<Integer> it) {
        assertEqual(exp, it.next());
    }

    @Test
    public void iterNumber() {
        Iterator<Integer> it = ICore.iter(3).iterator();
        assertHasNext(true, it);
        assertNext(0, it);

        assertEqual(true, it.hasNext());
        assertNext(1, it);

        assertEqual(true, it.hasNext());
        assertNext(2, it);

        assertEqual(false, it.hasNext());
        try {
            assertNext(null, it);
            Assert.fail("exception expected");
        }
        catch (NoSuchElementException e) {
            // good
        }
    }

    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public final <T> void list(List<T> expected, T[] elements) {
        List<T> result = ICore.list(elements);
        assertEqual(expected, result);
    }

    private List<Object[]> parametersForList() {
        return paramsList(params(Arrays.asList(new String[] { "one", "eine" }), new String[] { "one", "eine" }),
                          params(new ArrayList<String>(),                       new String[0]),
                          params(Arrays.asList(new Integer[] { 1, 2, 3 }),      new Integer[] { 1, 2, 3 }));
        
    }    
}
